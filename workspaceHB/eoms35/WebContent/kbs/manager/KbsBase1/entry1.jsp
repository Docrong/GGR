<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" import="java.util.*,java.lang.*,java.io.*,org.apache.struts.util.*"%>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*"%>
<%@ page import ="com.boco.eoms.common.tree.WKTree"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.common.util.StaticVariable"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.taglib.AttachmentTag"%>
<%
String webapp = request.getContextPath();
SaveSessionBeanForm saveSessionBeanForm =
          (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");

String authorName=saveSessionBeanForm.getWrf_UserName();
String authorDeptName=saveSessionBeanForm.getWrf_DeptName();
String author =saveSessionBeanForm.getWrf_UserID();
int authorDept =saveSessionBeanForm.getWrf_DeptID();

//String levels =StaticMethod.nullObject2String(request.getAttribute("LEVELLIST"));

String regionId =StaticMethod.nullObject2String(request.getAttribute("REGIONID"),"1");
String path = request.getContextPath();
WKTree wk_tree = new WKTree();
String strTree = wk_tree.strWKTree(Integer.parseInt(regionId));
String sdomIds =StaticMethod.nullObject2String(request.getAttribute("SDOMIDS"));
String deal =StaticMethod.nullObject2String(request.getAttribute("DEAL"),"");
String code =StaticMethod.nullObject2String(request.getAttribute("CODE"),"");
String name =StaticMethod.nullObject2String(request.getAttribute("NAME"),"");
String description =StaticMethod.nullObject2String(request.getAttribute("DESCRIPTION"),"");
String cause =StaticMethod.nullObject2String(request.getAttribute("CAUSE"),"");
String software =StaticMethod.nullObject2String(request.getAttribute("SOFTWARE"),"");
String sheetid =StaticMethod.nullObject2String(request.getAttribute("SHEETID"),"");
String keyword =StaticMethod.nullObject2String(request.getAttribute("KEYWORD"),"");
String worksheetId =StaticMethod.nullObject2String(request.getAttribute("WORKSHEETID"),"");
String zuozhe =StaticMethod.nullObject2String(request.getAttribute("ZUOZHE"),"");

%>
<SCRIPT type="text/javascript">

function goto_transmit()
{
    if(checkInput())
    document.kbsBaseForm.submit();
}
function onDraft()
{   document.kbsBaseForm.action="<%=request.getContextPath()%>/kbs/KbsBase/draft.do";
    if(checkInput())
    document.kbsBaseForm.submit();
}
function checkInput(){
     var name = document.kbsBaseForm.name.value;
     var code = document.kbsBaseForm.code.value;

     if(code==""||code==null){
       alert("资料编码不能为空.");
       return false;
     }

    else if(name==""||name==null){
       alert("资料名称不能为空.");
       return false;
    }

      return true;
  }

function transmitRadio(flag){

	var frm = document.forms[0];;
	if (flag == 1) {
          //申告
            document.all.flag.value=1;
            document.all.compliant.style.display="block";
            document.all.fault.style.display="none";
            document.all.compliantdescription.style.display="block";
            document.all.compliantcause.style.display="block";
            document.all.faultdescription.style.display="none";
            document.all.faultcause.style.display="none";
            document.all.faultType.value="";
            document.all.specialtyType.value="";
	}
	else if(flag==0){
          //故障
            document.all.flag.value=0;
            document.all.fault.style.display="block";
            document.all.compliant.style.display="none";
            document.all.compliantdescription.style.display="none";
            document.all.compliantcause.style.display="none";
            document.all.faultdescription.style.display="block";
            document.all.faultcause.style.display="block";
            document.all.applyType.value="";
            document.all.custType.value="";
	}

	return true;
}
function dealTimeLimit(){
}
</SCRIPT>
<link rel="stylesheet" href="<%=path%>/css/table_style.css" type="text/css">
<?import namespace=BOCOimplementation="<%=path%>/css/button/genericButton.htc"/>

<script language="javascript" src="<%=path%>/css/table_calendar.js"></script>
<script type="text/javascript" src="<%=path%>/css/finallytree.js"></script>

<style type="text/css">

body{font-size: 9pt;color: #000000;LINE-HEIGHT: 18px}
#tree {position: absolute;
visibility: hidden;
left: 72%; top: 10%;
z-index:2;
background-color:#ECF2FE;
padding:12px;
border-top:1px solid #FeFeFe;
border-left:1px solid #FeFeFe;
border-right:3px solid #8E9295;
border-bottom:3px solid #8E9295;
}
</style>
<SCRIPT language=javascript>
<!--
//以下四行用于日历显示
var outObject;
var old_dd = null;
writeCalender();
bltSetDay(bltTheYear,bltTheMonth);
//-->
</SCRIPT>

<html:html>
<head>
<title>案例入库</title>
<html:base/>
</head>
<eoms:DictType typeName="ApplyType"/>
<eoms:DictType typeName="CompliantType"/>
<body >
<!--<base target="_self">-->
<html:form method="post" action="/KbsBase/save" >
<script language="javascript">
<html:javascript formName="kbsBaseForm" dynamicJavascript="true" staticJavascript="true"/>
</script>
<input type="hidden" name="sdomids" id="sdomids" value="<%=sdomIds%>">
<input type="hidden" name="path" id="path" value="<%=path%>">


<html:hidden property="worksheetid" value="<%=worksheetId%>"/>
<div id="tree">
<font face="宋体" style="font-size: 9pt" COLOR="#990000" ><B><bean:message key="label.deptTree"/></B>&nbsp;[&nbsp;<A HREF="javascript:headerDisplay(0);"><bean:message key="label.hide"/></A>&nbsp;]</font>
<BR>
<script type="text/javascript">

var path=document.all.path.value;
var domids = document.all.sdomids.value;
var Tree = new Array;
<%=strTree%>
if( domids == "")
   createTree9(Tree,<%=regionId%>,0,path,"","",
   "window.kbsBaseForm.cid","selOnly",
   "window.kbsBaseForm.deptId","window.kbsBaseForm.deptName","tree");
else
   createTree10(Tree,<%=regionId%>,0,path,domids,"",
   "window.kbsBaseForm.cid","selOnly",
   "window.kbsBaseForm.deptId","window.kbsBaseForm.deptName","tree");

</script>
</div>
<center>
<br>
<table border="0" width="95%" cellspacing="0">
  <tr>
    <td width="100%" class="table_title" align="center">
        <b>
        案例入库
        </b>　
    </td>
  </tr>
</table>

<table border="0" width="95%" cellspacing="1" cellpadding="1"
class="table_show" align=center>

 <tr class="tr_show">
    <td width="25%" height="25" class="clsfth">&nbsp;案例编码
  </td>
  <td width="75%" height="25" colspan="3" >
        <html:text  styleClass="clstext" property="code" value="<%=code%>" size="30"/>
          <font color="#FF0000">**</font>
  </td>
  </tr>
<%if(!sheetid.equals("")){%>
 <tr class="tr_show">
    <td width="25%" height="25" class="clsfth">&nbsp;工单号</td>
  <td width="75%" height="25" colspan="3" >
        <html:text  styleClass="clstext" property="sheetid" value="<%=sheetid%>" size="30" readonly="true"/>
          <font color="#FF0000">**</font>
  </td>
</tr>
<%}%>
  <tr class="tr_show">
    <td width="25%" height="25" class="clsfth">&nbsp;案例主题</td>
  <td width="75%" height="25" colspan="3" >
        <html:text  styleClass="clstext" property="name" value="<%=name%>" size="30"/>
          <font color="#FF0000">**</font>
  </td>
</tr>
  <tr class="tr_show">
  <td width="25%" class="clsfth">&nbsp;提交人</td>
  <td width="75%" colspan="3"><%=authorDeptName%> <%=authorName%></td>
</tr>
<tr class="tr_show">
  <td width="25%" class="clsfth">&nbsp;作者</td>
  <td width="75%" height="25" colspan="3" >
 <html:text  styleClass="clstext" property="zuozhe" value="<%=StaticMethod.getGBString(StaticMethod.null2String(zuozhe,""))%>" size="30"/>
    </td>
</tr>
 <tr class="tr_show">
    <td width="25%" height="25" class="clsfth" >&nbsp;关键字
  </td>
  <td width="75%" height="25" colspan="3">
        <html:text  styleClass="clstext" property="keyword" value="<%=keyword%>" size="30"/>
  </td>
  </tr>
<tr class="tr_show">
  <td width="25%" class="clsfth">&nbsp;工单类型</td>
   <td colspan = 3>
      <html:radio  property="flag" onclick="transmitRadio(0);" value="0" styleClass="clstext"/>故障&nbsp;&nbsp;&nbsp;&nbsp;
      <html:radio  property="flag" onclick="transmitRadio(1);"value="1" styleClass="clstext"/>申告
   </td>
</tr>
<tr class="tr_show" id="fault" style="display: block;">
    <td class= "clsfth">&nbsp;<bean:message key="Faultsheet.mainSpecialty"/><!--专业类型--></td>
    <td >
      <eoms:SelectDictRel jsDealTimeLimit="true" name="specialtyType" typeName="Specialty" formName="kbsBaseForm" relProperty="faultType" relTypeName="FaultType" value="15" />
   </td>
   <td class= "clsfth">&nbsp;<bean:message key="Faultsheet.mainFaultTypeName"/><!--故障类型--></td>
   <td >
	<html:select property="faultType" style="width: 3.6cm;">
         <html:options collection="faultType" property="value" labelProperty="label"/>
       </html:select>
  </td>
</tr>
<!--<tr class="tr_show" id="compliant" style="display: none;">
		<td class= "clsfth">&nbsp;<bean:message key="Applysheet.mainApplyType"/></td>
    <td >
      <eoms:SelectDictRel jsDealTimeLimit="true" name="specialtyType" typeName="CustType" formName="kbsBaseForm" relProperty="faultType" relTypeName="FaultType" value="15" />
   </td>
                <td class= "clsfth">&nbsp;<bean:message key="Applysheet.mainCustType"/></td>
		<td>
		<html:select property="custType" style="width: 3.6cm;">
                <html:options collection="CompliantType" property="value" labelProperty="label"/>
                </html:select>
		</td>
</tr>-->

<tr class="tr_show" id="compliant" style="display: none;">
		<td class= "clsfth">&nbsp;<bean:message key="Applysheet.mainApplyType"/></td>
		<td>
		<html:select property="applyType" style="width: 3.6cm;"  >
		<OPTION VALUE="*15">其他网络投诉</OPTION>
<OPTION VALUE="*14">他数据业务类投诉</OPTION>

<OPTION VALUE="*13">CMNET业务类投诉</OPTION>
<OPTION VALUE="*12">GPRS业务投诉类</OPTION>
<OPTION VALUE="*11">话音增值业务类投诉</OPTION>
<OPTION VALUE="*10">交叉覆盖类投诉</OPTION>
<OPTION VALUE="*9">HLR故障类投诉</OPTION>
<OPTION VALUE="*8">智能网平台类投诉</OPTION>
<OPTION VALUE="*7">彩信业务类投诉</OPTION>
<OPTION VALUE="*6">短信业务类投诉</OPTION>
<OPTION VALUE="*5">IP电话类投诉</OPTION>

<OPTION VALUE="*4">互联互通类投诉</OPTION>
<OPTION VALUE="*3">漫游类</OPTION>
<OPTION VALUE="*2">通信质量</OPTION>
<OPTION VALUE="*1">网络覆盖</OPTION>

 </html:select>
		</td>
                <td class= "clsfth">&nbsp;<bean:message key="Applysheet.mainCustType"/></td>
		<td>
		<html:select property="custType" style="width: 3.6cm;">
                <html:options collection="CompliantType" property="value" labelProperty="label"/>
                </html:select>
		</td>
</tr>





  <tr class="tr_show" >
     <td width="25%" id="faultdescription" style="display: block;" height="25" class="clsfth">
       &nbsp;故障描述
  </td>
    <td width="25%" id="compliantdescription" style="display: none;" height="25" class="clsfth">&nbsp;投诉描述
  </td>
  <td width="75%" height="25" colspan="3">
        <html:textarea   rows="6" style="width:10.8cm;" property="description"  styleClass="clstext"  value="<%=description%>" />
  </td>
  </tr>
  <tr class="tr_show" >
    <td width="25%" id="faultcause" style="display: block;" height="25" class="clsfth">
      &nbsp;故障原因分析
   </td>
    <td width="25%" id="compliantcause" style="display: none;" height="25" class="clsfth">&nbsp;投诉原因分析
  </td>
  <td width="75%" height="25" colspan="3">
        <html:textarea  rows="6" style="width:10.8cm;"  styleClass="clstext" property="cause" value="<%=cause%>"/>
  </td>
  </tr>

  <tr class="tr_show">
    <td width="25%" height="50" class="clsfth">&nbsp;处理过程
   </td>
    <td width="75%" height="50" colspan="3">
      <html:textarea property="deal" rows="6" style="width:10.8cm;" value="<%=deal%>" styleClass="clstext"/>
     </td>
  </tr>
<tr class="tr_show">
    <td class= "clsfth"><bean:message key="taw.attach"/></td>
    <td colspan=3><eoms:attachment idList="" idField="attachName" appCode="kbs"/></td>
 </tr>
</table>
<table border="0" width="95%" cellspacing="0">
  <tr>
    <html:hidden property="author" value="<%=author%>" />
    <td width="100%" height="32" align="right">
      <input type="button" class="clsbtn2"  onclick="goto_transmit()" value="提交" name="button1" >&nbsp;&nbsp;
      <input type="button" Class="clsbtn2" value="保存为草稿" onClick="return onDraft();">&nbsp;&nbsp;
      <html:reset styleClass="clsbtn2"><bean:message key="label.reset"/>
      </html:reset>
     </td>
  </tr>
</table>
</center>

</html:form>
<logic:messagesPresent>
                  <html:messages id="error">
	<script type="text/javascript">
		<!--
                    alert("<bean:write name="error"/>");
		-->
	</script>
                  </html:messages>
</logic:messagesPresent>

</body>
</html:html>
