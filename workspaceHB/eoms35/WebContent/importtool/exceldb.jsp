<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.lang.*"%>

<%@ page import = "com.boco.RW_Excel.Checkin.CheckIn_Informix,com.boco.RW_Excel.DBInfo.DB_Informix_Info,com.boco.RW_Excel.XMLToDB.ValueInsDB,com.boco.RW_Excel.DBAccess.DBAccess"%>
<%@ page import = "com.boco.eoms.importtool.ImportUtil"%>
<%! DBAccess db=null;%>
<%! ResultSet rs=null;%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
</head>

<body background="<%=request.getContextPath()%>/images/img/bg001.gif">
<table border="0" width="95%" cellspacing="1" bgcolor="#709ED5" style="border-right-style: solid; border-right-color: #d3d3d3; border-bottom-style: solid; border-bottom-color: #d3d3d3">
  <tr>
    <td height="20">&nbsp;</td>
  </tr>
</table>
   <table border="0" width="95%" cellspacing="1" bgcolor="#709ED5" style="border-right-style: solid; border-right-color: #d3d3d3; border-bottom-style: solid; border-bottom-color: #d3d3d3">
    <tbody>
    <tr>

    <td height="27" align="center">&nbsp;</td>
    </tr>

    <tr bgcolor="#E5EDF8">

    <td height="27">
      <div align="center"><font color="#FF0000">EXCEL  数据正在导入请稍后</font><font color="#FF0000">。。。。。。。</font></div>
    </td>
    </tr>

  </table>

</body>
</html>

<%
	String s_date = request.getParameter("my_date");
	String excelname = request.getParameter("excelname");
	String xmlname = excelname.substring(0,excelname.length()-3)+"xml";
        File path = new File("");
        excelname = path.getAbsolutePath()+"/xls/"+excelname;
        System.out.print(excelname);

	try{
          ImportUtil util = new ImportUtil(excelname);
          util.Import();

        }catch(Exception e){
          e.printStackTrace();
        }

	out.println("<meta http-equiv='Refresh' content='0;url=excelover.jsp?excelname="+excelname+"&xmlname="+xmlname+"'>");
%>