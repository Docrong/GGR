<%@ page language="java" contentType="text/html;charset=GB2312" %>

<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*,java.util.Date"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.boco.eoms.common.util.*"%>
<%

   //==============获取当前时间===========

    Calendar cal = Calendar.getInstance();
    cal.add(cal.DATE,0);

    int year1 = cal.get(cal.YEAR);
    int month1 =cal.get(cal.MONTH)+1;
    int date1 = cal.get(cal.DATE);
    int hour1 = cal.get(cal.HOUR);
    int second1   = cal.get(cal.SECOND);
    int minute = cal.get(cal.MINUTE);

  String excelname = Integer.toString(year1)+Integer.toString(month1)+Integer.toString(date1)+Integer.toString(hour1)+Integer.toString(minute)+Integer.toString(second1);
  %>
<SCRIPT LANGUAGE=javascript>
<!--
function criterion(){

	  if(form1.excelfile.value=="")
	  {
	   alert("你没有选择要必须的EXCEL 文件！！")
	   return false;
	  }

	return(true);
}
-->
</SCRIPT>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">
<table border="0" width="95%" cellspacing="1" bgcolor="#709ED5" style="border-right-style: solid; border-right-color: #d3d3d3; border-bottom-style: solid; border-bottom-color: #d3d3d3">
</table>
<form name="form1" method="post" action="excelupload.jsp" onsubmit="return criterion();"  ENCTYPE="multipart/form-data">
  <input type="hidden" name="excelname" value="<%=excelname%>">
  <table border="0" width="95%" cellspacing="1" bgcolor="#709ED5" style="border-right-style: solid; border-right-color: #d3d3d3; border-bottom-style: solid; border-bottom-color: #d3d3d3">
    <tbody>
    <tr>
    </tr>
    <tr>
      <td height="11" width="25%" bgcolor="#E5EDF8">选择源EXCEL 文件：</td>
      <td height="11" width="75%" bgcolor="#E5EDF8">
        <input type="file" name="excelfile" size="40">
      </td>
    </tr>
    <tr>
      <td height="11" bgcolor="#E5EDF8">报表时间:　　　　　　</td>
      <td height="11" bgcolor="#E5EDF8">
        <select name="s_year">
          <%
           for(int i=(year1-3);i<(year1+2);i++)
            {
         %>
          <option value="<%=i%>" <% if (i == year1) { %> selected <% } %>><%=i%></option>
          <% } %>
        </select>
        年
        <select name="s_month">
          <%
             String jj="";
             for(int j =1 ;j<=12;j++ )
             	{
                  if(j<10)
                   {
                     jj = "0"+j;
                   }
                   else
                   {
                     jj = (new Integer(j)).toString();
                   }
           %>
          <option value="<%=jj%>" <% if (j==month1) {%> selected<%} %>><%=jj%></option>
          <% } %>
        </select>
        月
        <select name="s_date">
          <%
          String zz ="";
          for (int z=1;z<=31;z++)
           {
           	 if(z<10)
                   {
                     zz = "0"+z;
                   }
                   else
                   {
                     zz = (new Integer(z)).toString();
                   }
        %>
          <option value="<%=zz%>" <% if (z==date1) {%> selected<% } %>><%=zz%></option>
          <%
          }
        %>
        </select>
        日</td>
    </tr>
    <tr bgcolor="#E5EDF8">
      <td height="11" colspan="2"> 　　　　　　　 　　　　　
        <input type="submit" name="Submit" value="转换" class="clsbtn2">
        <input type="reset" name="Submit2" value="重写" class="clsbtn2">
      </td>
    </tr>
    <tr bgcolor="#E5EDF8">
      <td height="11" colspan="2">注意<font color="#000000">： 1.</font><font color="#FF0000"><font color="#000000">上传的文件格式必须为源模板的EXCEL
        文件。</font></font></td>
    </tr>
    <tr bgcolor="#E8F3FF">
      <td height="11" colspan="2"> 　　　　　　</td>
    </tr>
  </table>
</form>