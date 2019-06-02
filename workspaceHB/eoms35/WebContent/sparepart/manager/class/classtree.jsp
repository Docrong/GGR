
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%
String regionId = StaticMethod.nullObject2String(request.getAttribute("REGIONID"),"1");

String path = request.getContextPath();
TawClassMsgTree wk_tree = new TawClassMsgTree();
String strTree = wk_tree.strWKTree(Integer.parseInt(regionId));
String url = "";
String dept1 = "";
String wsClass = "-1";
%>
<html>
<title><bean:message key="class.maintenance"/></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<link rel="StyleSheet" href="<%=path%>/css/tree.css" type="text/css">
	<script type="text/javascript" src="<%=path%>/css/onlytree.js"></script>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">
<table width="1200">
<tr>
<td width="250" align="center" valign="top">
<br>
<div id="tree" align="left">
<input type="hidden" name="url" id="url" value="<%=url%>">
<input type="hidden" name="wsClass" id="wsClass" value="<%=wsClass%>">
<input type="hidden" name="path" id="path" value="<%=path%>">
<input type="hidden" name="fromDept" id="fromDept" value="<%=dept1%>">
<script type="text/javascript">
var dept1=document.all.fromDept.value;
var url=document.all.url.value;
var wsclass=document.all.wsClass.value;
var path=document.all.path.value;

var Tree = new Array;
<%=strTree%>
createTree3(Tree,<%=regionId%>,-1,path,url,"1",wsclass);
</script>
</div>
</td>

<td width="400" valign="top" align="center">
<logic:present name="tawClassMsg">
<div align="center">
  <center>
<br>
<form name="buttonbar">
<table align="center" border="0" width="95%" cellspacing="0">
  <tr>
    <td width="100%" class="table_title" align="center">
        <b>&nbsp;&nbsp;<bean:message key="label.view"/>&nbsp;</b>��</td>
  </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
    <td width="30%" class="clsfth" height="25">&nbsp;<bean:message key="class.name"/></td>
    <td width="70%" height="25"><bean:write name="tawClassMsg" property="cname" scope="request"/></td>
  </tr>
  <tr class="tr_show">
    <td width="30%" class="clsfth" height="25">&nbsp;<bean:message key="class.note"/></td>
    <td width="70%" height="25"><bean:write name="tawClassMsg" property="note" scope="request"/></td>
  </tr>
</table>
<table align="center" border="0" width="95%" cellspacing="0">
  <tr>
    <td width="100%" align="right" height="32">
     &nbsp;&nbsp;
     <INPUT class="clsbtn2" id=button type=button value=<bean:message key="label.add"/> name=button Onclick="window.location.href ='../storage/addclass.do?id=<bean:write name='tawClassMsg' property='id' scope='request'/>&deleted=<bean:write name='tawClassMsg' property='deleted' scope='request'/>'">
     &nbsp;&nbsp;
     <INPUT class="clsbtn2" id=button type=button value=<bean:message key="label.edit"/> name=button Onclick="window.location.href ='../storage/editclass.do?id=<bean:write name='tawClassMsg' property='id' scope='request'/>'">
     &nbsp;&nbsp;
     <INPUT class="clsbtn2" id=button type=button value=<bean:message key="label.remove"/> name=button Onclick="onPublic(<bean:write name='tawClassMsg' property='id' scope='request'/>);">
     &nbsp;&nbsp;
    </td>
  </tr>
</table>
</form>
  </center>
</div>
</logic:present>
</td>
<script language="javascript">
function onPublic(aaa)
    {
      if( !confirm('${eoms:a2u("删除后不能恢复，您是否确认删除该类型信息？��")}') ) return ;
      window.navigate("../storage/dropclass.do?id="+aaa);
    }
</script>
</tr>
</table>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>