<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*,java.util.Date"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.boco.nmis.DBAccess.DBAccess"%>
<%@ page import="com.boco.nmis.excelXML.GetExcelXML"%>
<%
 String xmlname = request.getParameter("xmlname");
 String m_year = xmlname.substring(0,4);
 String m_month = xmlname.substring(5,7);
 String m_day = xmlname.substring(8,9);

 out.println(m_year+"<br>");
 out.println(m_month+"<br>");
 out.println(m_day+"<br>");
%>

<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">
<form name="form1" method="post" action="search_day_result.jsp" target="blank">
 <table border="0" width="95%" cellspacing="1" bgcolor="#709ED5" style="border-right-style: solid; border-right-color: #d3d3d3; border-bottom-style: solid; border-bottom-color: #d3d3d3">
    <tbody>
    <tr>
      <td align=middle width="48%" height="21">&nbsp;</td>
    </tr>
    <tr>
      <td width="48%" height="62" align="center">
        <table border="0" width="95%" cellspacing="1" bgcolor="#709ED5" style="border-right-style: solid; border-right-color: #d3d3d3; border-bottom-style: solid; border-bottom-color: #d3d3d3">
          <tr>
            <td height="25">
              <div align="center"><font size="4"><b> Excel 导入数据查询 </b></font></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td width="48%" align="center">
        <table border=4 cellpadding=0 cellspacing=40 width="80%" bgcolor="#709ED5" style="border-right-style: solid; border-right-color: #d3d3d3; border-bottom-style: solid; border-bottom-color: #d3d3d3">
          <tbody>
          <tr>
            <td>
              <table  cellpadding=2 cellspacing=6 width="100%" border=1  bgcolor="#709ED5" style="border-right-style: solid; border-right-color: #d3d3d3; border-bottom-style: solid; border-bottom-color: #d3d3d3">
                <tbody>
                <tr>
                  <td height=35 width="34%" bgcolor="#ffe2a6">
                    <div align=right>EXCEL 表名称：</div>
                  </td>
                  <td align=left height=35 valign=center width="66%" bgcolor="#ffffe6">
                  <select size="1" name="year1">
                  <%
		for(int i=0;i<(com.boco.nmis.excelXML.GetExcelXML.sheet_ename.length-1);i++)
 			{
 				//out.println(com.boco.nmis.excelXML.GetExcelXML.sheet_ename[i]);
 				String query_01 = "select nodeid,nodename from t_treelib where engname='"+com.boco.nmis.excelXML.GetExcelXML.sheet_ename[i]+"'";
 				//out.println(query_01);
 				DBAccess db_01 = new DBAccess();
 				db_01.getDbConnection();
 				ResultSet rs_01 = db_01.getResultSet(query_01);
 				while(rs_01.next())
 				{
 				String nodeid  = rs_01.getString(1);
 				String nodename = rs_01.getString(2);

		%>

                      <option value='<%=nodeid%>'><%=nodename%></option>
                      <% }
                      } %>
                    </select>
                    年&nbsp;

                </tr>
                <tr bgcolor=#ffe2a6>
                  <td height="35" colspan="2">
                    <div align="right"></div>
                    <div align="center">
                      <input type="submit" value="确定" name="confirm">
                      <input type="reset" name="reset" value="重置">
                    </div>
                  </td>
                </tr>
                </tbody>
              </table>
            </td>
          </tr>
          </tbody>
        </table>
      </td>
    </tr>
    <tr>
      <td width="48%">&nbsp;</td>
    </tr>
    <tr>
      <td width="48%">
        <div align=center> </div>
      </td>
    </tr>
    <tr>
      <td width="48%">&nbsp;</td>
    </tr>
    <tr>
      <td width="48%"><font
      color=#ffffff>---</font></td>
    </tr>
    <tr>
      <td width="48%">&nbsp;</td>
    </tr>
    </tbody>
  </table>
  </form>
<p>&nbsp;</p>
</body>
</html>
