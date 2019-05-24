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
form1.NodeName.focus();
if(callerDataObj)
{
  document.all.NodeName.value=callerDataObj["ItemName"];
  document.all.defalut.value=callerDataObj["DefaultValue"];
  document.all.rows.value=callerDataObj["LineNum"];
}
}
</script>
<html>
<head>
<title>作业项目</title>

</head>
<body leftmargin="0" topmargin="0" class="clssclbar" onload="init();">
<form name="form1" action="">
<table width="100%" align="center" height="100%">
    <tr>
      <td colspan="2"  align="center">作业项目</td>
    </tr>
    <tr >
      <td>
      项目名称
      </td>
      <td  align="left">
      <input type="text" name="NodeName" value="">
      </td>
    </tr>
    <tr >
      <td>
      缺省内容
      </td>
      <td   align="left">
      <input type="text" name="defalut"  value="">
      </td>
    </tr>
    <tr>
      <td>
      显示行数
      </td>
      <td>
	  <Select name="rows" width="30">
	  <option value="1" selected>1</option>
	  <option value="2">2</option>
	  <option value="3">3</option>
	  <option value="4">4</option>
	  <option value="5">5</option>
	  <option value="6">6</option>
	  <option value="7">7</option>
	  <option value="8">8</option>
	  <option value="9">9</option>
	  <option value="10">10</option>
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
  ItemData["ItemName"]=document.all.NodeName.value;
  ItemData["DefaultValue"]=document.all.defalut.value;
  ItemData["LineNum"]=document.all.rows.value;
  window.returnValue=ItemData;
  window.close();
}
-->
</script>
</html>
