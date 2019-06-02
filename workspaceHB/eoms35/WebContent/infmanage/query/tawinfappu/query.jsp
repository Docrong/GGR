<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import ="com.boco.eoms.common.tree.*"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>

<%
String regionId = StaticMethod.nullObject2String(request.getAttribute("REGIONID"),"-1");

String path = request.getContextPath();
WKTree wk_tree = new WKTree();
String strTree = wk_tree.strWKTree(Integer.parseInt(regionId));
String url = "";
String dept1 = "";
String wsClass = "-1";
String sdomIds = StaticMethod.nullObject2String(request.getAttribute("SDOMIDS"));

RelativeDrop rel = new RelativeDrop();

String users = rel.strRelativeDrop(sdomIds);
//String users = rel.strRelativeDrop("");
%>

<script language="JavaScript">
var User = new Array;
<%=users%>

       function ifQuery(temp,temp3,tempId,tempName){
	   var i;
	   var flag= -100;
           var id = window.tawInfInfoForm.deptId.value;

          if(temp.length == null){
             if(temp.value == id){
                temp.checked = false;
                window.tawInfInfoForm.deptId.value=0;
                window.tawInfInfoForm.deptName.value="";
             }
             else{
               window.tawInfInfoForm.deptId.value=temp.value;
               window.tawInfInfoForm.deptName.value=temp3;
             }
          }
          else{
	   for(i=0;i<temp.length;i++){
              if(temp[i].checked == true){
                 if(temp[i].value == id){
                   temp[i].checked = false;
                }
                else{
                   flag = i;
                }
             }
	   }
         if(flag == -100){
           window.tawInfInfoForm.deptId.value=0;
           window.tawInfInfoForm.deptName.value="";
         }
         else{
           window.tawInfInfoForm.deptId.value=temp[flag].value;
           window.tawInfInfoForm.deptName.value=temp3;
         }
          }


        var dept_Id  = window.tawInfInfoForm.deptId.value;
   }
</script>

<html:html>
<head>
<title><bean:message key="label.query"/><bean:write name="tawInfInfoForm" property="infSortName"/></title>
<html:base/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script type="text/javascript" src="<%=path%>/css/finallytree.js"></script>
</head>

<body onload="refreshIt()">
<html:form action="/TawInfAppu/list" method="post">

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

<input type="hidden" name="path" id="path" value="<%=path%>">
<input type="hidden" name="sdomids" id="sdomids" value="<%=sdomIds%>">

<div id="tree">

<font face="宋体" style="font-size: 9pt" COLOR="#990000" >
<B><bean:message key="label.deptTree"/></B>&nbsp;[&nbsp;<A HREF="javascript:headerDisplay(0);"><bean:message key="label.hide"/></A>&nbsp;]</FONT>
<br>
<script type="text/javascript">
var path=document.all.path.value;
var domids = document.all.sdomids.value;
var Tree = new Array;
<%=strTree%>
if( domids == "")
   //createTree9(Tree,<%=regionId%>,0,path,"","",
   //           "window.tawInfInfoForm.cid","ifQuery",
   //           "window.tawInfInfoForm.deptId",
   //           "window.tawInfInfoForm.deptName","tree");
   createTree9(Tree,1,0,path,"","",
              "window.tawInfInfoForm.cid","ifQuery",
              "window.tawInfInfoForm.deptId",
              "window.tawInfInfoForm.deptName","tree");              
else
   createTree10(Tree,1,0,path,domids,"",
                "window.tawInfInfoForm.cid","ifQuery",
                "window.tawInfInfoForm.deptId",
                "window.tawInfInfoForm.deptName","tree");
   //createTree10(Tree,<%=regionId%>,0,path,domids,"",
   //             "window.tawInfInfoForm.cid","ifQuery",
   //             "window.tawInfInfoForm.deptId",
   //             "window.tawInfInfoForm.deptName","tree");
</script>
</div>
  <br>
    <table border="0" width="100%" cellspacing="0" align="center">
      <tr>
        <td width="100%" align="center" class="table_title">
          <b>
            <bean:message key="label.query"/>&nbsp;<bean:write name="tawInfInfoForm" property="infSortName" scope="request"/>
          </b>
        </td>
      </tr>
	</table>
	<html:hidden name="tawInfInfoForm"  property="infSortId"/>
	<html:hidden name="tawInfInfoForm" property="infSortName"/>
　
	<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align="center">
      <tr class="tr_show">
		<td width="25%" height="25" class="clsfth">&nbsp;
		  &nbsp<bean:message key="TawInfAppu.InfInfoId"/>
		</td>
		<td width="75%" height="25">
		  <html:text styleClass="clstext" property="infInfoId" size="20" value="" />
		</td>
      </tr>
      <tr class="tr_show">
		<td width="25%" height="25" class="clsfth">&nbsp;
		  &nbsp<bean:message key="TawInfAppu.InfInfoName"/>
		</td>
		<td width="75%" height="25">
          <html:text styleClass="clstext" property="infInfoName" size="20" value="" />
		</td>
      </tr>
      <tr class="tr_show">
        <td width="25%" height="25" class="clsfth">&nbsp;&nbsp;&nbsp资料所属部门
        <td width="75%" height="25">
          <html:text property="deptName" size="30" value="" styleClass="clstext" readonly="true"/>
          <A HREF="javascript:headerDisplay(1);" >
          <font face="宋体" style="font-size: 9pt"><bean:message key="label.deptTree"/></FONT></A>
          <html:hidden property="deptId"/>
        </td>
      </tr>
      <tr class="tr_show">
        <td width="25%" height="25" class="clsfth">&nbsp;
          &nbsp<bean:message key="TawInfAppu.InfUpName"/>
        </td>
        <td width="75%" height="25">
          <html:text styleClass="clstext" property="infUpName" value="" />
        </td>
      </tr>
	</table>
    <table border="0" width="100%" cellspacing="0" align="center">
	  <tr>
          <td width="100%" colspan="2" height="32" align="right">
			<input Class="clsbtn2" type="button" name="toadd" value="<bean:message key="label.add"/>" onClick="toAdd()">
			&nbsp;
			<input Class="clsbtn2" type="button" name="tosubmit" value="<bean:message key="label.query"/>" onClick="toSubmit()">
			&nbsp;
          </td>
	  </tr>
	</table>
</html:form>
</body>
</html:html>
<script language="javascript">
  function toSubmit()
  {
    window.document.tawInfInfoForm.submit();
  }

  function toAdd()
  {
	var sortName;
	var sortId;
    sortName = window.document.all.infSortName.value;
	sortId = window.document.all.infSortId.value;
	window.location.href="add.do?sortId=" + sortId;
  }
 function refreshIt()
  {
    window.document.tawInfInfoForm.infInfoId.value = ""
    window.document.tawInfInfoForm.infInfoName.value = "";
    window.document.tawInfInfoForm.deptName.value = "";
    window.document.tawInfInfoForm.infUpName.value = "";

  }
</script>