package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

    private static String username = "eoms35";
    private static String password = "hbeoms35boco";
    private static String driver = "oracle.jdbc.OracleDriver";
    private static String url = "jdbc:oracle:thin:@133.0.252.4:1521:eoms10g";

    public static void main(String[] args) throws Exception {
        List excelList = read();
//		List wblist = analysisList(excelList);
//		Map resultMap=connectQuery(wblist);
//		List fail=(List) resultMap.get("failList");
//		List success=(List) resultMap.get("successList");
//		for(int i=0;i<success.size();i++) {
//			System.out.println(success.get(i));
//		}
//		insertOrUpdate(success);//插入数据
    }

    public static List read() {
//		String excelPath = "外包人员信息反馈.xlsx";
        String excelPath = "售后日报表20190412汽贸-OK.xlsx";
        List excelList = new ArrayList();
        try {
            // String encoding = "GBK";
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) { // 判断文件是否存在

                String[] split = excel.getName().split("\\."); // .是特殊字符，需要转义！！！！！
                Workbook wb;
                // 根据文件后缀（xls/xlsx）进行判断
                if ("xls".equals(split[1])) {
                    FileInputStream fis = new FileInputStream(excel); // 文件流对象
                    wb = new HSSFWorkbook(fis);
                } else if ("xlsx".equals(split[1])) {
                    wb = new XSSFWorkbook(excel);
                } else {
                    System.out.println("文件类型错误!");
                    return null;
                }

                // 开始解析
                Sheet sheet = wb.getSheetAt(0); // 读取sheet 0
                for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                    System.out.println(wb.getSheetName(i));
                }

                System.out.println(sheet.getSheetName());
                int firstRowIndex = sheet.getFirstRowNum() + 0; // 第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();
                System.out.println("$$nfirstRowIndex: " + firstRowIndex);
                System.out.println("^^lastRowIndex: " + lastRowIndex);

                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) { // 遍历行
                    System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        List list = new ArrayList();
                        int lastCellIndex = row.getLastCellNum();
                        for (int cIndex = 0; cIndex < 8; cIndex++) { // 遍历列
                            Cell cell = row.getCell(cIndex);
                            list.add(cell);
                            if (cell != null) {
                                System.out.print(cell.toString() + "--");
                            }
                        }
                        excelList.add(list);
                    }
                }
                System.out.println(excelList.size());

            } else {
                System.out.println("找不到指定的文件");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelList;
    }

    public static List analysisList(List excelList) {
        List wblist = new ArrayList();
        for (int i = 0; i < excelList.size(); i++) {
            List list = (List) excelList.get(i);
            String wb = list.get(5).toString();
            if (wb.equals("外包") || wb.equals("代维")) {
                if (list.get(6) != null)
                    wblist.add(list);
            }
        }
        System.out.println(excelList.size());
        for (int i = 0; i < wblist.size(); i++) {
            List list = (List) wblist.get(i);
            if (list.get(3).toString().indexOf("E") > 0) {
                String userid = list.get(3).toString();
                userid = userid.replace(".", "");
                userid = userid.substring(0, userid.indexOf("E"));
                if (userid.length() == 10) {
                    userid = userid + "0";
//					System.out.print(userid);
//					System.out.print("-->"+userid+"\n");
                }
                list.set(3, userid);
            }
            // System.out.println(list);
        }
        System.out.println(wblist.size());
        return wblist;

    }


    public static Map connectQuery(List list) throws Exception {

        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        Class.forName(driver);
        conn = DriverManager.getConnection(url, username, password);
        state = conn.createStatement();

        List queryList = new ArrayList();// 查询结果由ResultSet转换成list
        List successList = new ArrayList();// 可以添加
        List failList = new ArrayList();// 不能添加
        try {
            System.out.println("开始list:" + list.size());
            for (int listindex = 0; listindex < list.size(); listindex++) {
                List sonIndex = (List) list.get(listindex);
                String sql = "select * from taw_system_Maintenance_user where is_deleted=0 and user_id='"
                        + sonIndex.get(3).toString() + "'";
                rs = state.executeQuery(sql);

                ResultSetMetaData md = rs.getMetaData(); // 获得结果集结构信息,元数据
                int columnCount = md.getColumnCount(); // 获得列数
                if (rs.next()) {
                    Map<String, Object> rowData = new HashMap<String, Object>();
                    for (int i = 1; i <= columnCount; i++) {
                        rowData.put(md.getColumnName(i), rs.getObject(i));
                    }
                    queryList.add(rowData);
                    successList.add(sonIndex);
                } else {
                    failList.add(sonIndex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            conn.close();
            state.close();
        }
//		System.out.println("list大小:" + successList.size());
//		System.out.println("list大小:" + queryList.size());
        System.out.println("不能导入人数:" + failList.size());
        Map map = new HashMap();
        map.put("successList", successList);
        map.put("failList", failList);
        return map;
    }

    public static void insertOrUpdate(List list) throws Exception {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        Class.forName(driver);
        conn = DriverManager.getConnection(url, username, password);
        state = conn.createStatement();
        try {

            for (int indexlist = 0; indexlist < list.size(); indexlist++) {
                List sonList = (List) list.get(indexlist);
                String uuid = UUID.randomUUID().toString();

                String deptid = "";
                String querySql =
                        "select deptid\n" +
                                "  from (select *\n" +
                                "          from taw_system_dept t\n" +
                                "         where t.deleted = 0\n" +
                                "           and deptname = '" + sonList.get(6).toString() + "') t2\n" +
                                " where substr(t2.deptid, 0, 5) =\n" +
                                "       (select deptid\n" +
                                "          from taw_system_dept\n" +
                                "         where deleted = 0\n" +
                                "           and deptname = '" + sonList.get(0).toString() + "')";
                rs = state.executeQuery(querySql);
                if (rs.next()) {
                    deptid = rs.getObject("deptid").toString();
                }
                String sql = "insert into taw_system_Maintenance_userBU(id,deleted,userid,wbCompany,wbCompanyid)values('" + uuid + "',"
                        + "'0','" + sonList.get(3).toString() + "','" + sonList.get(6).toString() + "','" + deptid + "')";
                state.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
            state.close();
        }

    }
}
