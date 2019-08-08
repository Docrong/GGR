package com.boco.eoms.sheet.kpi.service.impl;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.HibernateException;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.kpi.dao.IKpiBaseDAO;
import com.boco.eoms.sheet.kpi.service.IKpiBaseManager;

public class KpiBaseManagerImpl implements IKpiBaseManager {
    private IKpiBaseDAO kpiBaseDAO;

    public List getQuerySheetByCondition(String[] hsql, Map actionForm, Map condition, Integer curPage,
                                         Integer pageSize, int[] aTotal, String queryType, String xmlName) {
        String sql = null;
        condition.put("pageSize", pageSize);
        if (hsql != null) sql = hsql[0];
        if (sql == null || sql.equals("")) {
            List map = kpiBaseDAO.getXMLList(xmlName);
            sql = (String) map.get(0);
            if (actionForm.get("sendTimeStartDate") != null) {
                Object sendTimeStart[] = (Object[]) actionForm.get("sendTimeStartDate");
                sql = sql.replace("{0}", sendTimeStart[0].toString());
            }
            if (actionForm.get("sendTimeEndDate") != null) {
                Object sendTimeEnd[] = (Object[]) actionForm.get("sendTimeEndDate");
                sql = sql.replace("{1}", sendTimeEnd[0].toString());
            }
            if (actionForm.get("completeLimitStartDate") != null) {
                Object completeLimitsendTimeStart[] = (Object[]) actionForm.get("completeLimitStartDate");
                sql = sql.replace("{9}", completeLimitsendTimeStart[0].toString());
            }
            if (actionForm.get("completeLimitEndDate") != null) {
                Object completeLimitsendTimeEnd[] = (Object[]) actionForm.get("completeLimitEndDate");
                sql = sql.replace("{10}", completeLimitsendTimeEnd[0].toString());
            }
            if (actionForm.get("deptid") != null) {
                Object deptid[] = (Object[]) actionForm.get("deptid");
                if (deptid[0] != null && !"".equals(deptid[0].toString()))
                    sql = sql.replace("{2}", deptid[0].toString());
                else
                    sql = sql.replace("{2}", "%");
            } else {
                sql = sql.replace("{2}", "%");
            }
            if (actionForm.get("status") != null) {
                Object status[] = (Object[]) actionForm.get("status");
                if (status[0] != null && !"".equals(status[0].toString()))
                    sql = sql.replace("{3}", status[0].toString());
                else
                    sql = sql.replace("{3}", "%");
            } else {
                sql = sql.replace("{3}", "%");
            }
            if (actionForm.get("systype") != null) {
                Object systype[] = (Object[]) actionForm.get("systype");
                if (systype[0] != null && !"".equals(systype[0].toString()))
                    sql = sql.replace("{4}", systype[0].toString());
                else
                    sql = sql.replace("{4}", "%");
            } else {
                sql = sql.replace("{4}", "%");
            }
            if (actionForm.get("userid") != null) {
                Object userid[] = (Object[]) actionForm.get("userid");
                if (userid[0] != null && !"".equals(userid[0].toString()))
                    sql = sql.replace("{5}", userid[0].toString());
                else
                    sql = sql.replace("{5}", "%");
            } else {
                sql = sql.replace("{5}", "%");
            }
            if (actionForm.get("mainNetSortOneChoiceExpression") != null) {
                Object mainNetSortOneChoiceExpression[] = (Object[]) actionForm.get("mainNetSortOneChoiceExpression");
                if (mainNetSortOneChoiceExpression[0] != null && !"".equals(mainNetSortOneChoiceExpression[0].toString()))
                    sql = sql.replace("{6}", mainNetSortOneChoiceExpression[0].toString());
                else
                    sql = sql.replace("{6}", "%");
            } else {
                sql = sql.replace("{6}", "%");
            }
            if (actionForm.get("mainNetSortTwoChoiceExpression") != null) {
                Object mainNetSortTwoChoiceExpression[] = (Object[]) actionForm.get("mainNetSortTwoChoiceExpression");
                if (mainNetSortTwoChoiceExpression[0] != null && !"".equals(mainNetSortTwoChoiceExpression[0].toString()))
                    sql = sql.replace("{7}", mainNetSortTwoChoiceExpression[0].toString());
                else
                    sql = sql.replace("{7}", "%");
            } else {
                sql = sql.replace("{7}", "%");
            }
            if (actionForm.get("mainNetSortThreeChoiceExpression") != null) {
                Object mainNetSortThreeChoiceExpression[] = (Object[]) actionForm.get("mainNetSortThreeChoiceExpression");
                if (mainNetSortThreeChoiceExpression[0] != null && !"".equals(mainNetSortThreeChoiceExpression[0].toString()))
                    sql = sql.replace("{8}", mainNetSortThreeChoiceExpression[0].toString());
                else
                    sql = sql.replace("{8}", "%");
            } else {
                sql = sql.replace("{8}", "%");
            }
            if (actionForm.get("operatetype") != null) {
                Object operateType[] = (Object[]) actionForm.get("operatetype");
                if (operateType[0] != null && !"".equals(operateType[0].toString()))
                    if ("17".equals(operateType[0].toString()))
                        sql = sql + "and l5.operatetype = 17";
                    else if ("99".equals(operateType[0].toString()))
                        sql = sql + "and l5.operatetype is null";
            }
            if (actionForm.get("main.toDeptId") != null) {
                Object toDeptId[] = (Object[]) actionForm.get("main.toDeptId");
                if (toDeptId[0] != null && !"".equals(toDeptId[0].toString()))
                    sql = sql + "and mm.toDeptId in (" + toDeptId[0].toString() + ")";
            }
        }

        List result = kpiBaseDAO.getQuerySheetByCondition(sql, curPage, pageSize, aTotal, queryType);
        if (result != null) {
            System.out.println("查询数据条数:" + result.size());
        }
        return result;
    }

    public List getList(int startIndex, int endIndex, String sql) {
//		int
        return null;
    }


    public void excel(List list, List colMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fname = "detial";// Excel文件名
        OutputStream os = response.getOutputStream();// 取得输出流
        response.reset();// 清空输出流
        response.setHeader("Content-disposition", "attachment; filename=" + fname + ".xls");// 设定输出文件头,该方法有两个参数，分别表示应答头的名字和值。
        response.setContentType("application/msexcel");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("new sheet");
        wb.setSheetName(0, "sheet", HSSFWorkbook.ENCODING_UTF_16);
        HSSFRow row = sheet.createRow((short) 0);
        sheet.createFreezePane(0, 1);
        for (int a = 1; a < colMap.size(); a++) {
            String col = colMap.get(a).toString();
            String[] cols = col.split(",");
            cteateCell(wb, row, (short) (a - 1), cols[0]);
        }
        try {
            System.out.println("数据一共：" + list.size());
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                HSSFRow row2 = sheet.createRow((short) (i + 1));
                int size = map.size();
                if (map.size() > colMap.size()) {
                    size = colMap.size();
//						System.out.println("size:"+size);
                }
                for (int j = 1; j < size; j++) {
                    String col = colMap.get(j).toString();
//						System.out.println(j+":"+col);
                    String[] cols = col.split(",");
                    String ss = "";
                    if (map.get(cols[2]) == null)
                        ss = "空  null";
                    else
                        ss = map.get(cols[2]).toString();
                    cteateCell(wb, row2, (short) (j - 1), ss);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                wb.write(os);
            }
            if (os != null) {
                os.flush();
                os.close();
            }
        }

    }

    private void cteateCell(HSSFWorkbook wb, HSSFRow row, short col, String val) {
        HSSFCell cell = row.createCell(col);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(val);
        HSSFCellStyle cellstyle = wb.createCellStyle();
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        cell.setCellStyle(cellstyle);
    }


    public IKpiBaseDAO getKpiBaseDAO() {
        return kpiBaseDAO;
    }

    public void setKpiBaseDAO(IKpiBaseDAO kpiBaseDAO) {
        this.kpiBaseDAO = kpiBaseDAO;
    }

    public List getXMLList(String xmlName) throws HibernateException {
        return kpiBaseDAO.getXMLList(xmlName);
    }

    public List getReportByDept(String[] hsql, Map actionForm, String filename) throws HibernateException {
        String sql = null;

        if (hsql != null) sql = hsql[0];
        if (sql == null || sql.equals("")) {
            List map = kpiBaseDAO.getXMLList(filename);
            sql = (String) map.get(0);
            if (actionForm.get("sendTimeStartDate") != null) {
                //  Object[] sendTimeStart = (Object[]) actionForm.get("sendTimeStartDate");
                sql = sql.replace("{0}", actionForm.get("sendTimeStartDate").toString());
            }
            if (actionForm.get("sendTimeEndDate") != null) {
                //  Object[] sendTimeEnd = (Object[]) actionForm.get("sendTimeEndDate");
                sql = sql.replace("{1}", actionForm.get("sendTimeEndDate").toString());
            }
        }
        List result = kpiBaseDAO.getReportByDept(sql, filename);
        return result;
    }

}
