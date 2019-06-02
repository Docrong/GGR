<%@ page contentType="text/html; charset=gb2312"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import ="com.boco.eoms.common.tree.WKTree"%>
<%@ page import="java.util.*,org.apache.struts.util.*,com.boco.eoms.common.util.*,com.boco.eoms.common.controller.SaveSessionBeanForm"%>
<%
SaveSessionBeanForm saveSessionBeanForm
    = (SaveSessionBeanForm)session.getAttribute("SaveSessionBeanForm");
int WrfDeptId = saveSessionBeanForm.getWrf_DeptID();

String path = request.getContextPath();
WKTree wk_tree = new WKTree();
String strTree = wk_tree.getSameLevRegion2(1,StaticVariable.UNDELETED);
String url = "";
String dept1 = "";
String wsClass = "-1";
String sdomIds = "";
%>
<head>
<title>table</title>
<style type="text/css">
body{font-size: 9pt;color: #000000;LINE-HEIGHT: 18px}
#tree {position: absolute;
visibility: hidden;
left: 74%; top: 6%;
z-index:2;
background-color:#ECF2FE;
padding:6px;
border-top:1px solid #FeFeFe;
border-left:1px solid #FeFeFe;
border-right:3px solid #8E9295;
border-bottom:3px solid #8E9295;
}
Label{
	background-color:#48AFFE;
	cursor:hand;
	border:1px solid #485555;
	height:18px;
	padding:2 2 2 2;
}
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/css/finallytree.js"></script>
</head>
<body leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmDefineTree/summary">
<input type="hidden" name="YearMonth">
<input type="hidden" name="path" id="path" value="<%=path%>">
<input type="hidden" name="sdomids" id="sdomids" value="<%=sdomIds%>">
<div id="tree" style="#tree">
<font face="宋体" style="font-size: 9pt" COLOR="#990000" ><B><bean:message key="label.deptTree"/></B>&nbsp;[&nbsp;<A HREF="javascript:headerDisplay(0);"><bean:message key="label.hide"/></A>&nbsp;]</font>
<BR>
<script type="text/javascript">
var path=document.all.path.value;
var domids = "";
var Tree = new Array;
<%=strTree%>
if( domids == "")
   /*
   createTree9(Tree,1,0,path,"","",
   "window.tawRmDefineTreeForm.deptId","selMore",
   "window.tawRmDefineTreeForm.deptId","window.tawRmDefineTreeForm.deptName","tree");
   */
createTreeGao(Tree,<%=WrfDeptId%>,0,path,"","","window.tawRmDefineTreeForm.deptId","selMore","window.tawRmDefineTreeForm.deptId","window.tawRmDefineTreeForm.deptName","tree");
else
   createTree10(Tree,1,0,path,domids,"",
   "window.tawRmDefineTreeForm.allId","selOnly",
   "window.tawRmDefineTreeForm.deptId","window.tawRmDefineTreeForm.deptName","tree");
javascript:headerDisplay(0);
</script>
</div>

<br>
<table cellpadding="0" cellspacing="0" border="0" width="400" align="center">
<tr>
<td align="center" class="table_title">
    <b>机房排班查询</b><!--&nbsp;<bean:message key="TawRmAssignwork.tablename"/>-->
  </td>
</tr>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="400" align="center">
<tr>
<td>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<tr class="tr_show">
<td class="clsfth">
选择部门
</td>
<td>
        <input type="text" name="deptName" size="30" readonly="true"/>
        <A href="javascript:headerDisplay(1)">
            <font face="宋体" style="font-size: 9pt"><bean:message key="label.deptTree"/></FONT>
        </A>&nbsp;&nbsp;
        <%--
        <input type="hidden" name="deptId"/>
        --%>
</td>
</tr>
<tr class="tr_show">
<td class="clsfth">
考核月份
</td>
<td>
<SELECT size=1 name="time_fromyear" >
<%
Calendar cal=Calendar.getInstance();
for (int i=2000;i<2030;i++)
{
if(i==cal.get(Calendar.YEAR)){
%>
<OPTION value="<%=i%>" selected ><%=i%></OPTION>
<%}else %>
<OPTION value="<%=i%>" ><%=i%></OPTION>
<%}%>
</SELECT>年
<SELECT size=1 name="time_frommonth" >
<%
for(int i=1;i<13;i++)
{
if(i==cal.get(Calendar.MONTH)+1){
%>
<OPTION value="<%=i%>" selected ><%=i%></OPTION>
<%}else%>
<OPTION value="<%=i%>"><%=i%></OPTION>
<%}%>
</SELECT>月
</td>
</tr>
</table>
</td>
</tr>
<tr>
<td>
<table cellpadding="0" cellspacing="0" border="0" width="400" align="center">
<tr align="right" height="32">
<td>
     <input type="submit" class="clsbtn2" value="查询" onclick="putValue();">
</td>
</tr>
</table>
</td>
</tr>
</table>
</html:form>
</body>
<script language="javascript">
function putValue()
{
var len=tawRmDefineTreeForm.time_frommonth.value.length;
var monthvalue=tawRmDefineTreeForm.time_frommonth.value;
if(len<2)
{
monthvalue='0'+monthvalue;
}
tawRmDefineTreeForm.YearMonth.value=tawRmDefineTreeForm.time_fromyear.value+'-'+monthvalue;
}
</script>

