<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
 
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.duty.model.*"%>
<script language="JavaScript">
var callerDataObj = dialogArguments;
function init()
{
form1.PlanName.focus();
if(callerDataObj)
{
  document.all.PlanName.value=callerDataObj["PlanName"];
  document.all.cycle.value=callerDataObj["cycle"];
  document.all.specility.value=callerDataObj["specility"];
}
}
</script>
<%--<app:DictType typeName="Spec"/>
--%><html>
<head>
<title>值班作业计划</title>

</head>
<body leftmargin="0" topmargin="0" class="clssclbar" onload="init();">
<form name="form1" action="">
<table width="100%" align="center" height="100%">
    <tr>
      <td colspan="2"  align="center">值班作业</td>
    </tr>
    <tr >
      <td>
      值班作业名称
      </td>
      <td  align="left">
      <input type="text" name="PlanName" value="">
      </td>
    </tr>
    <tr>
    <td>专业</td>
    <td>
      <Select name="specility" >
      <%
      List Spec=(List)pageContext.getAttribute("Spec");
      if(Spec!=null && Spec.size()>0)
      {
      for(int i=0;i<Spec.size();i++)
      {
      LabelValueBean lb=(LabelValueBean)Spec.get(i);
      %>
	  <option value="<%=lb.getLabel()%>"><%=lb.getLabel()%></option>
      <%
      }
      }
      %>
      </select>
    </td>
    </tr>
    <tr>
      <td>
      执行周期
      </td>
      <td>
	  <Select name="cycle" >
	  <option value="workserial" selected>班次</option>
          <%--
	  <option value="day">天&nbsp;</option>
	  <option value="week">周&nbsp;</option>
	  <option value="month">月&nbsp;</option>
	  <option value="quarter">季度</option>
	  <option value="halfyear">半年</option>
          <option value="year">年&nbsp;</option>
          --%>
	  </select>
      </td>
    </tr>
    <tr>
      <td align="center" colspan="2">
	  <input type="button" Class="clsbtn2" value="确定" onclick="doSubmit()">
	  <input type="reset" Class="clsbtn2" value="取消" onclick="window.close()">
	  </td>
    </tr>
  </table>
</form>
</body>
<script language="javascript">
<!--
function doSubmit()
{
  ItemData=new Array;
  ItemData["PlanName"]=document.all.PlanName.value;
  ItemData["cycle"]=document.all.cycle.value;
  ItemData["specility"]=document.all.specility.value;
  window.returnValue=ItemData;
  window.close();
}
-->
</script>
</html>
