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
<title>ֵ����ҵ�ƻ�</title>

</head>
<body leftmargin="0" topmargin="0" class="clssclbar" onload="init();">
<form name="form1" action="">
<table width="100%" align="center" height="100%">
    <tr>
      <td colspan="2"  align="center">ֵ����ҵ</td>
    </tr>
    <tr >
      <td>
      ֵ����ҵ����
      </td>
      <td  align="left">
      <input type="text" name="PlanName" value="">
      </td>
    </tr>
    <tr>
    <td>רҵ</td>
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
      ִ������
      </td>
      <td>
	  <Select name="cycle" >
	  <option value="workserial" selected>���</option>
          <%--
	  <option value="day">��&nbsp;</option>
	  <option value="week">��&nbsp;</option>
	  <option value="month">��&nbsp;</option>
	  <option value="quarter">����</option>
	  <option value="halfyear">����</option>
          <option value="year">��&nbsp;</option>
          --%>
	  </select>
      </td>
    </tr>
    <tr>
      <td align="center" colspan="2">
	  <input type="button" Class="clsbtn2" value="ȷ��" onclick="doSubmit()">
	  <input type="reset" Class="clsbtn2" value="ȡ��" onclick="window.close()">
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
