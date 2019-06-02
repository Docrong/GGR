package com.boco.eoms.common.resource;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2004-12-9
 * Time: 20:33:19
 * To change this template use File | Settings | File Templates.
 */
import com.boco.eoms.db.util.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Transformer;
import java.sql.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;

public class PaginationBean {
    //private Category cat = null;
    private final int DIRECT_FIRST = 0;
    private final int DIRECT_PRE = 1;
    private final int DIRECT_NEXT = 2;
    private final int DIRECT_LAST = 3;

    private int intCountTopic = 0; //主题总数
    int intPageSize; //每页显示主题数
    private int intPageCount; //总页数
    private int intPage;  //当前页数
    public int direction;
    private int intIndex;
    private int intCount;

    // int i;
    private Locale locale;
    private String filterParam = "";
    private String queryString = "";
    private String xslFilePath = "";
    private String fileName = "";

    private boolean ifQryParam = false;

    //定义构造器， 。
    public PaginationBean() {
        intPageSize = 10;
        intPage = 1;
        intIndex = 0;
        intCount = 0;
        filterParam = "intPage";
    }

    public boolean isIfQryParam() {
        return ifQryParam;
    }

    public void setIfQryParam(boolean ifQryParam) {
        this.ifQryParam = ifQryParam;
    }

    public int getIntCount() {
        return intCount;
    }

    public void setIntCount(int Count) {
        this.intCount = Count;
    }

    public String getFilterParam() {
        return filterParam;
    }

    public void setFilterParam(String filterParam) {
        this.filterParam = filterParam;
    }

    public int getIntIndex() {
        return intIndex;
    }

    public void setIntIndex(int index) {
        this.intIndex = index;
    }

    public int getIntCountTopic() {
        return intCountTopic;
    }  //获取总页数。

    public int getIntPageCount() {
        return intPageCount;
    }  //获取当前页数。

    public int getIntPage() {
        return intPage;
    }

    public void setIntPage(int intTem) {
        intPage = intTem;
    }

    public void setIntPageSize(int intTem) {
        intPageSize = intTem;
    }

    public int getIntPageSize() {
        return intPageSize;
    }

    public void setQueryString(String temStr) {
        this.queryString = temStr;
    }

    public void setXslFilePath(String temStr) {
        this.xslFilePath = temStr;
    }

    public void setFirstPage(String temp) {
        direction = DIRECT_FIRST;
        this.intPage = 1;
    }

    public void setPrePage(String temp) {
        direction = DIRECT_PRE;
        this.intPage--;
    }

    public void setNextPage(String temp) {
        direction = DIRECT_NEXT;
        this.intPage++;
    }

    public void setLastPage(String temp) {
        direction = DIRECT_LAST;
        this.intPage = intPageCount;
    }

    public String getResultSet(HttpServletRequest request) {

        StringBuffer sb = this.getQueryResult(queryString, request);
        locale = request.getLocale();
        String result = "";
        //System.out.println("########sorce"+sb.toString());
        if (sb.length() > 0) {
            try {
                Transformer pageXsl = XmlUtil.DOM2Transformer(xslFilePath);
                // System.out.println("####after pageXsl");
                result = XmlUtil.XMLStr2XMLStr(sb.toString(), pageXsl);
            } catch (Exception e) {
                System.out.println(Util.UNI2GBK(e.getMessage()));
            }
        }
         //System.out.println("#####result"+result);
        return result;
    }

    public void update(String sql) {
        try {
            Connection con = ConnectionPool.getInstance().getConnection();
            con.createStatement().executeQuery(sql);
            con.close();
        } catch (SQLException se) {
            System.out.println("sql error " + se.toString());
        } catch (Exception e) {
            System.out.println("update error " + e.toString());
        }
    }

    //Countsql:总记录的Query字符串。[形式为select count(*) from tablename]
    //Pagisql :要分页的Query字符串。[形式为select * from tablename where ...]
    //request :参数传递过程中的变量。[用来控制翻页时的pages变量]
    private  StringBuffer getCountSQL(String sql){
        StringBuffer result = new StringBuffer();
        result.append("select count(*) ");
        int sub_begin = sql.indexOf("from");

        if(sql.indexOf("order by")>0)
            result.append(sql.substring(sub_begin,sql.indexOf("order by")));
        else
            result.append(sql.substring(sub_begin)) ;
        return result;
    }

    private StringBuffer getQueryResult(String Pagisql, HttpServletRequest request) {

        StringBuffer sb = new StringBuffer();
        ResultSet Pagirs = null; //初始化分页时Rs变量
        Connection con = ConnectionPool.getInstance().getConnection();
        try {

            Pagirs = con.createStatement().executeQuery(Util.getCountSQL(Pagisql).toString());
            if(Pagirs.next()){
                intCountTopic = Pagirs.getInt(1);
            }

            Pagirs = con.createStatement().executeQuery(Pagisql);
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(Util.UNI2GBK(e.toString() + Pagisql));
            sb.append("<error>")
                    .append(Util.UNI2GBK(e.getMessage()))
                    .append("</error>");

        } finally {

            if (Pagirs != null) {

                if (intPageSize == -1)
                    intPageCount = 1;
                else
                    intPageCount = (intCountTopic + intPageSize - 1) / intPageSize;


                if (intPage > intPageCount)
                    intPage = intPageCount;

                sb.append("<rows>");
                if (intPageCount > 0) {

                    try {

                        if (intIndex > 0) intIndex--;

                        //intPage是当前页码，intCountTopics是结果集总数
                        //intPageSize 当前页的大小 endPos结束位置，最后一个

                        Pagirs.absolute((intPage - 1) * intPageSize + intIndex + 1);
                        int i = intIndex;
                        int endPos = intPageSize;
                        if (-1 == endPos) endPos = 1;

                        if (intCount != 0) endPos = intIndex + intCount;

                        boolean flag = true;
                        //3层嵌套的if，第一个是当查询出的链接数为1个时，游标移动到首位
                        if (intCountTopic == 1) {

                            Pagirs.beforeFirst();
                            Pagirs.next();
                            sb.append(TransRsToXML(Pagirs, "row"));

                        } else {

                            //第二个是当查询出的当前页面数据数为1个时，游标移动到前一位
                            if (intPageSize > intCountTopic) {
                                //System.out.println("in > 1 ");
                                Pagirs.beforeFirst();
                                for (int f = 0; f < intCountTopic; f++) {
                                    Pagirs.next();
                                    sb.append(TransRsToXML(Pagirs, "row"));
                                }
                            } else {
                                //第三个是当查询出的当前页面数据数为多个时，游标不动
                                do {
                                    sb.append(TransRsToXML(Pagirs, "row"));
                                    i++;
                                } while (i < endPos && Pagirs.next());
                            }
                        }
                        sb.append(PageFooter(request));
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(Util.UNI2GBK(e.toString()));
                        sb.append("<error>")
                                .append(e.getMessage())
                                .append("</error>");
                    }
                }

                sb.append("</rows>");

            }
            try {
                if(con!=null)con.close();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            return sb;
        }
    }

    private StringBuffer getParameters(HttpServletRequest request) {

        StringBuffer temStr = new StringBuffer();

        try {

            if (this.ifQryParam) {

                temStr.append("<param name='QryStr' value='");
                temStr.append(StrTrans.transXml(this.queryString));
                temStr.append("'/>");

            } else {

                String name = "",value = "";
                Enumeration enum = request.getParameterNames();

                while (enum.hasMoreElements()) {

                    name = (String) enum.nextElement();
                    value = request.getParameter(name);

                    temStr.append("<param")
                            .append(getAttr("name", name))
                            .append(getAttr("value", value))
                            .append("/>");

                }

            }

        } catch (Exception e) {

            System.out.println("Error:getParameters:" + e.getMessage());
            e.printStackTrace();

        } finally {

            return temStr;

        }
    }


    private String getAttr(String attr_name, String attr_value) {

        return " " + attr_name + "=\"" + attr_value + "\"";

    }


//分页栏函数。
    private StringBuffer PageFooter(HttpServletRequest request) {

        StringBuffer str = new StringBuffer();

        int next, prev;
        prev = intPage - 1;
        next = intPage + 1;

        str.append("<foot ")
                .append(getAttr("root", request.getContextPath()))
                .append(getAttr("url", request.getRequestURI()))
                .append(getAttr("total", String.valueOf(getIntCountTopic())))
                .append(getAttr("pageSize", String.valueOf(intPageSize)))
                .append(getAttr("page", String.valueOf(getIntPageCount())))
                .append(getAttr("intPage", String.valueOf(intPage)));

        if (intPage > 1)
            str.append(getAttr("afirst", "1"));

        if (intPage > 1)
            str.append(getAttr("bprevious", String.valueOf(prev)));

        if (intPage < intPageCount)
            str.append(getAttr("cnext", String.valueOf(next)));

        if (intPageCount > 1 && intPage != intPageCount)
            str.append(getAttr("dlast", String.valueOf(intPageCount)));

        str.append(">")
                .append(getParameters(request))
                .append("</foot>");

        return str;
    }

    private StringBuffer TransRsToXML(ResultSet tmpRs, String nodeName) {

        StringBuffer result = new StringBuffer();

        try {

            String theName = "", theValue = "";

            result.append("<" + nodeName);

            ResultSetMetaData rsmd = tmpRs.getMetaData();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {

                theName = rsmd.getColumnName(i + 1);
                theValue = tmpRs.getString(i + 1);

                if (theName.equals("file_name"))
                    fileName = theValue;


                if (rsmd.getColumnType(i + 1) == 93) {  //It is a date

                    Timestamp date = tmpRs.getTimestamp(i + 1);

                    if (date == null) {
                        theValue = "";
                    } else {
                        java.util.Date theDate = new java.util.Date(date.getTime());
                        theValue = DateUtil.getDateString(theDate, "yyyy-MM-dd HH:mm");
                    }

                } else {
                    if (theValue == null)
                        theValue = "";
                    else
                        theValue = StrTrans.transXml(theValue.trim());  // ;


                }

                result.append(getAttr(theName, theValue));

            }
            result.append("/>");

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());

        } finally {
            return result;
        }
    }

    public String formatDate(Date theDate) {

        String dateString = "";

        try {

            Calendar cal = Calendar.getInstance(locale);
            cal.setTime(theDate);
            DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM, locale);
            dateString = dateFormatter.format(cal.getTime());

        } catch (Exception e) {

            System.out.println("test result:" + e.getMessage());

        } finally {

            return dateString;

        }
    }

    public String getFileName() {

        return fileName;

    }
}
