package com.boco.eoms.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import com.boco.eoms.commons.util.xml.UserCount;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.download.Writer;


public class ExportExcel extends HttpServlet {
    //	  private SQLConnection cp;
    private int countTest;


    public int getCountTest() {
        return countTest;
    }

    public void setCountTest(int countTest) {
        this.countTest = countTest;
    }

    private DataSource ds;

    public void init() throws ServletException {
//		  String url = XmlManage.getFile("/config/export-util.xml").getProperty("url");
//		  String user = XmlManage.getFile("/config/export-util.xml").getProperty("user");
//		  String password = XmlManage.getFile("/config/export-util.xml").getProperty("password");
//		  cp = ConnectionPoolFactory.getConnectionPool("com.boco.eoms.database.SQLConnection");
//		  cp.setConnect(url, user, password);

        try {
            InitialContext ctx = new InitialContext();
            //通过JNDI查找DataSource
            ds = (DataSource) ctx.lookup("java:comp/env/jdbcoracle");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * The doPost method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 添加下载用户数量控制,最多10人同时下载
        synchronized (this) {
            new UserCount().login();// 下载用户+1
            if (UserCount.getCount() > UserCount.getMaxCount()) {
                System.out.println("下载用户超出最大限制,当前下载用户:" + UserCount.getCount()
                        + "人");
                UserCount.setCount();//下载人数保持为10,等待其他用户下载完成
                return;
            }
        }

        Connection conn = null;
        Statement ssel = null;
        ResultSet rs = null;
        int maxThreadCount = 10;//excel下载最大并行线程数量
        //String sql = request.getParameter("sql");
//		String sql = new String(request.getParameter("sql").getBytes("ISO-8859-1"),"utf-8");
        request.setCharacterEncoding("UTF-8");
        String sqlName = request.getParameter("sql");

        String[] sqls = sqlName.split(";");


        String querySql = XmlManage.getFile("/config/searchSQL.xml").getProperty(sqls[0]);
        String whereStr = "";
        if (sqls.length > 1) {
            whereStr = sqls[1];
            String[] whereStrs = sqls[1].split("and");
            for (int i = 0; i < whereStrs.length; i++) {
                if (whereStrs[i].indexOf("#") > 0) {
                    String param = whereStrs[i].substring(whereStrs[i].indexOf("#") + 1, whereStrs[i].lastIndexOf("#"));
                    String paramOld = whereStrs[i].substring(whereStrs[i].indexOf("#"), whereStrs[i].lastIndexOf("#") + 1);
                    String param4Xml = XmlManage.getFile("/config/searchSQL.xml").getProperty(param);
                    whereStr = whereStr.replace(paramOld, param4Xml);
                }

            }
        }
        String sql = querySql + " " + whereStr;
        System.out.println("sql" + sql);

        String countsql = "select count(*) count from (" + sql + ")";
        //String regex = "\\w+(?=\\s+as\\s+)";//匹配as之前的字段
        String regex2 = "(?<=\\s{1,100}as\\s{1,100})[\\w[\u4E00-\u9FA5]]+";//匹配as之后的字段
        //String[] englishName = matcherWord(sql, regex);
        String[] chineseName = matcherWord(sql, regex2);
        //String[] englishName = {"ID","SHEETID","TITLE","SHEETACCEPTLIMIT","SHEETCOMPLETELIMIT","SENDTIME","SENDUSERID"};
        //String[] chineseName = {"编号","工单号","标题","受理时限","完成时限","派单时间","派单人"};
        String filePath = XmlManage.getFile("/config/export-util.xml").getProperty("filePath");
//		System.out.println("filePath=" + filePath);
        String fileName = null;
        int count = 0;
        int part = Integer.parseInt(XmlManage.getFile("/config/export-util.xml").getProperty("part"));
        try {
            conn = ds.getConnection();
            ssel = conn.createStatement();
            System.out.println("countSql-->" + countsql);
            rs = ssel.executeQuery(countsql);
            while (rs.next()) {
                count = rs.getInt("count");
                System.out.println("count:" + count);
            }
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ssel != null) {
                    ssel.close();
                }
                if (conn != null) {
                    conn.close();
                }
                //cp.close(conn);
                //cp.printDebug();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        int remainder = count % part;
        int integer = (remainder == 0) ? count / part : count / part + 1;
        String[] fileNameArr = new String[integer];
        CountDownLatch latch = new CountDownLatch(integer);

        long startTimne = System.currentTimeMillis();
        for (int i = 0; i < integer; i++) {
            StringBuilder currentSql = new StringBuilder();
            if (i == integer - 1 && remainder != 0) {
                currentSql.append("select * from (select xhrbqk.*, rownum xjhrm from (").append(sql).append(") xhrbqk where rownum <= ").append(i * part + remainder).append(") WHERE xjhrm >= ").append(i * part + 1);
            } else {
                currentSql.append("select * from (select xhrbqk.*, rownum xjhrm from (").append(sql).append(") xhrbqk where rownum <= ").append((i + 1) * part).append(") WHERE xjhrm >= ").append(i * part + 1);
            }
            fileName = filePath + "\\" + UUID.randomUUID() + ".xls";
            fileNameArr[i] = fileName;


            if (integer == 1) {
                new Writer(latch, ds, currentSql.toString(), chineseName, fileName, countTest).run();
            } else {
                countTest++;
                new Writer(latch, ds, currentSql.toString(), chineseName, fileName, countTest).start();
            }
            System.out.println("countTest-->" + countTest);
            while (countTest >= maxThreadCount) {
                int threadCount = Writer.getThreadCount();

                try {
                    synchronized (Writer.class) {

                        Writer.class.wait();
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


        }
        System.out.println("等待子线程执行完毕...");
        try {
            latch.await();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Runtime.getRuntime().gc();
        System.out.println("子线程已经执行完毕");
        long endTime = System.currentTimeMillis();
        System.out.println("线程" + Thread.currentThread().getName() + "用时=" + ((endTime - startTimne) / 1000) + "秒");

        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", (new StringBuilder("attachment; filename=")).append(new String("导出文件.xls".getBytes("GBK"), "ISO8859-1")).toString());
        File upload = null;
        BufferedInputStream buff = null;
        byte[] b = new byte[1024];
        long k = 0;//该值用于计算当前实际下载了多少字节
        int j = 0;
        //从response对象中得到输出流,准备下载
        OutputStream myout = response.getOutputStream();
        //myout.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>".getBytes());

        StringTemplateGroup stGroup = new StringTemplateGroup("stringTemplate");
        StringTemplate head = stGroup.getInstanceOf("config/head");
        StringBuilder content = new StringBuilder();
        content.append(head.toString());
        content.append("\r\n<Worksheet ss:Name=\"1\">\r\n<Table ss:ExpandedColumnCount=\"");
        content.append(chineseName.length);
        content.append("\" ss:ExpandedRowCount=\"");
        content.append(count + 1);
        content.append("\" x:FullColumns=\"1\" x:FullRows=\"1\" ss:DefaultColumnWidth=\"54\" ss:DefaultRowHeight=\"14.25\">");

        content.append("\r\n<Row>\r\n");
        for (int m = 0; m < chineseName.length; m++) {
            content.append("<Cell><Data ss:Type=\"String\">");
            //System.out.println(englishName[k] + "=" + rs.getString(englishName[k]));
            content.append(chineseName[m]);
            content.append("</Data></Cell>");
        }
        content.append("\r\n</Row>");
        //System.out.println("content=" + new String(content.toString().getBytes("GBK"), "UTF-8"));
        myout.write(content.toString().getBytes());

        //myout.write("\r\n<Row>\r\n<Cell><Data ss:Type=\"String\">中文</Data></Cell><Cell><Data ss:Type=\"String\">中文</Data></Cell><Cell><Data ss:Type=\"String\">中文</Data></Cell><Cell><Data ss:Type=\"String\">中文</Data></Cell><Cell><Data ss:Type=\"String\">中文</Data></Cell><Cell><Data ss:Type=\"String\">中文</Data></Cell><Cell><Data ss:Type=\"String\">中文</Data></Cell>\r\n</Row>".toString().getBytes());

        myout.flush();
        for (int i = 0; i < fileNameArr.length; i++) {
            System.out.println("fileNameArr=" + fileNameArr[i]);
            upload = new File(fileNameArr[i]);
            //读出文件到i/o流
            buff = new BufferedInputStream(new FileInputStream(upload));
            //开始循环下载
            while (k < upload.length()) {
                j = buff.read(b, 0, 1024);
                k += j;
                //将b中的数据写到客户端的内存
                myout.write(b, 0, j);
            }
            k = 0;
            //将写入到客户端的内存的数据,刷新到磁盘
            myout.flush();

            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除  
//            if (upload.exists() && upload.isFile()) {  
//            	upload.delete();
//             }
        }
        String end = "\r\n</Table>\r\n</Worksheet>\r\n</Workbook>";
        myout.write(end.toString().getBytes());
        myout.flush();
        myout.close();

        new UserCount().logout();//下载完成,下载用户-1
    }

    public static String[] matcherWord(String input, String regex) {
        //CASE_INSENSITIVE表示不区分大小写
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        StringBuffer result = new StringBuffer();
        while (m.find()) {
            result.append(m.group() + ",");
        }
        return result.toString().split(",");
    }
}
