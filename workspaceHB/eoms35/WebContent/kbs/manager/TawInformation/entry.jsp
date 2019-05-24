<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.taglib.AttachmentTag"%>
<%@ page import ="com.boco.eoms.common.tree.WKTree"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat,com.boco.eoms.kbs.util.*"%>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.common.util.StaticVariable"%>

<%
SaveSessionBeanForm saveSessionBeanForm =
          (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
String webapp = request.getContextPath();
String publicerName=saveSessionBeanForm.getWrf_UserName();
String publicDeptName=saveSessionBeanForm.getWrf_DeptName();
String publicer =saveSessionBeanForm.getWrf_UserID();
int publiceDept =saveSessionBeanForm.getWrf_DeptID();

String boardId=String.valueOf(request.getParameter("boardId"));
String regionId =StaticMethod.nullObject2String(request.getAttribute("REGIONID"),"1");
String path = request.getContextPath();
WKTree wk_tree = new WKTree();
String strTree = wk_tree.strWKTree(Integer.parseInt(regionId));
String sdomIds =StaticMethod.nullObject2String(request.getAttribute("SDOMIDS"));
String infCode =StaticMethod.nullObject2String(request.getAttribute("INFCODE"),"");
String spes=StaticMethod.nullObject2String(request.getAttribute("SPELIST"));
String subspes=StaticMethod.nullObject2String(request.getAttribute("SUBSPELIST"));

Date currentDate = new Date();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String date = dateFormat.format(currentDate);
String imgText="";
String imgValue="";

%>

<script language="javascript">
var spe_arr = new Array;
<%=spes%>

var subspe_arr = new Array;
<%=subspes%>

function onSave()
{

  if (tawInformationForm.name.value==""||tawInformationForm.name.value==null)
  { alert(tawInformationForm.name.value);
    alert("主题不能为空");
    return false;
  }
  else if (tawInformationForm.content.value.length>1000)
  {
    alert("内容超过限制")
  }

  tawInformationForm.action="<%=request.getContextPath()%>/kbs/TawInformation/save.do?";
  tawInformationForm.submit();
  return true;
}

function init(){

            var i;
            var sel_sprlen=document.tawInformationForm.speciality.options.length-1;
            for(i=sel_sprlen;i>=0;i--)
            {
               document.all.tawInformationForm.speciality.options[i]=null;
             }
            var j=spe_arr.length;
            var m=0;
            for (i=0;i*2<j;i++)
            {
             var tempoption=new Option(spe_arr[i*2],spe_arr[i*2+1]);
             document.tawInformationForm.speciality.options[++m]=tempoption;
            }

            sel_sprlen=document.tawInformationForm.subSpeciality.options.length-1;
            for(i=sel_sprlen;i>=0;i--)
            {
               document.all.tawInformationForm.subSpeciality.options[i]=null;
             }
            j=subspe_arr.length;
            m=0;
            for (i=0;i*2<j;i++)
            {
             tempoption=new Option(subspe_arr[i*2],subspe_arr[i*2+1]);
             document.tawInformationForm.subSpeciality.options[++m]=tempoption;
            }
  }

function selectReceivers(){
   dWinOrnaments = "status:no;scroll:no;resizable:yes;dialogHeight:600px;dialogWidth:650px;";
   var obj = document.tawInformationForm;
   var ret = showModalDialog('<%=webapp%>/kbs/TawInformation/receedit.do?id=', obj, dWinOrnaments);
   }

</script>

<link title="style" href="<%=path%>/css/wsstyle.css" type="text/css" rel="stylesheet">
<link rel="StyleSheet" href="<%=path%>/css/tree.css" type="text/css">
<link rel="stylesheet" href="<%=path%>/css/table_style.css" type="text/css">
<?import namespace=BOCOimplementation="<%=path%>/css/button/genericButton.htc"/>
<script type="text/javascript" src="<%=path%>/css/onlytree.js"></script>
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
<title>新增资料</title>
<html:base/>
</head>

<body onLoad="init();">

<html:form  action="/TawInformation/save" >
<html:hidden property="strutsAction"/>
<input type="hidden" name="path" id="path" value="<%=path%>">
<input type="hidden" name="sdomids" id="sdomids" value="<%=sdomIds%>">

<div id="tree">
<font face="宋体" style="font-size: 9pt" COLOR="#990000" ><B><bean:message key="label.deptTree"/></B>&nbsp;[&nbsp;<A
HREF="javascript:headerDisplay(0);"><bean:message key="label.hide"/></A>&nbsp;]</font>
<BR>
<script type="text/javascript">

var path=document.all.path.value;
var domids = document.all.sdomids.value;
var Tree = new Array;
<%=strTree%>
if( domids == "")
   createTree9(Tree,<%=regionId%>,0,path,"","",
   "window.tawInformationForm.cid","selOnly",
   "window.tawInformationForm.belongDept","window.tawInformationForm.deptName1","tree");
else
   createTree10(Tree,<%=regionId%>,0,path,domids,"",
   "window.tawInformationForm.cid","selOnly",
   "window.tawInformationForm.belongDept","window.tawInformationForm.deptName1","tree");

</script>
</div>


<table align="center" width="90%" cellspacing="1" border="0" cellpadding="1">
<tr>
  <td width="100%" class="table_title">
        <bean:message key="label.add"/>&nbsp;信息
  </td>
</tr>
</table>

<table border="0" width="90%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<tr class="tr_show">
  <td width="25%" class="clsfth">&nbsp;资料编号</td>
  <td width="75%">
    <html:text property="code" readonly="true" value="<%=infCode%>" size="40" maxlength="80" styleClass="clstext"/>
   </td>
</tr>
<tr class="tr_show">
  <td width="25%" class="clsfth">&nbsp;资料名称</td>
  <td width="75%">
    <html:text property="name" size="20" maxlength="50" styleClass="clstext"/><font color="red">**</font>
  </td>
</tr>

<tr class="tr_show">
		<td class= "clsfth">&nbsp;专业类型</td>
		<td >
                <select size=1 name="speciality" style="width: 4.0cm;">
                  <option value=""></option>
                </select>
                </td>
</tr>
 <tr class="tr_show">
		<td class= "clsfth">&nbsp;子专业类型</td>
		<td >
                 <select size=1 name="subSpeciality" style="width: 4.0cm;">
                  <option value=""></option>
                </select>
		</td>
	</tr>
<tr class="tr_show">
  <td width="25%" class="clsfth">&nbsp;发布人</td>
  <td width="75%"><%=publicDeptName%> <%=publicerName%></td>
</tr>

<tr class="tr_show">
  <td width="25%" class="clsfth" align="right">&nbsp;发布时间</td>
  <td width="75%" align="left">
  <%=date%>
  </td>
</tr>

 <tr class="tr_show">
    <td width="30%" height="25"  class="clsfth">&nbsp;所属部门</td>
    <td width="70%" height="25">
        <html:text property="deptName1" size="30" styleClass="clstext" readonly="true"/>
        <A HREF="javascript:headerDisplay(1);" ><font face="宋体" style="font-size: 9pt"><bean:message
key="label.deptTree"/></FONT>
        </A>
        <html:hidden property="belongDept"/>
    </td>
 </tr>
 <tr class="tr_show">
    <td width="30%" height="25"  class="clsfth">&nbsp;接收者</td>
    <td width="70%" height="25">
      <html:text property="receiverName" size="30"  styleClass="clstext" readonly="true"/>
      <html:hidden property="deptids"/>
      <html:hidden property="postids"/>
      <html:hidden property="userids"/>
        <A HREF="javascript:selectReceivers();" >
            <font face="宋体" style="font-size: 9pt">请选择</FONT>
        </A>
    </td>
  </tr>
<tr class="tr_show">
  <td width="25%" class="clsfth">&nbsp;适用范围</td>
  <td width="75%">
    <html:text property="usedArea" size="20" maxlength="50" styleClass="clstext"/>
  </td>
</tr>

<tr class="tr_show">
  <td width="25%" class="clsfth">&nbsp;关键字</td>
  <td width="75%">
    <html:text property="keyword" size="40" maxlength="100" styleClass="clstext"/>
  </td>
</tr>

<tr class="tr_show">
  <td width="25%" class="clsfth" align="right">&nbsp;内容</td>
  <td width="75%" align="left"><html:textarea property="content" rows="12" cols="80" styleClass="clstext"/></td>
</tr>

<tr class="tr_show">


<html:hidden property="boardId" value="<%=boardId%>" />
<html:hidden property="publicer" value="<%=publicer%>" />
<html:hidden property="publicTime" value="<%=date%>" />
<html:hidden property="audit"/>



<td align="right" colspan="2">
      <input type="button" Class="clsbtn2" value="<bean:message key="label.save"/>" onclick="return onSave();">
      <input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>" onclick="history.back();">
</td>
</tr>
</table>
<table align="center" width="90%" cellspacing="1" border="0" cellpadding="1">
<eoms:attachment idList="" idField="attachName" appCode="kbs" />
</table>
</html:form>

</body>
</html:html>