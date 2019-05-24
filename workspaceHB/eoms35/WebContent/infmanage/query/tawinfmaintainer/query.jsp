<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import ="com.boco.eoms.common.tree.*"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>

<%
String regionId = StaticMethod.nullObject2String(request.getAttribute("REGIONID"),"1");

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
           var id = window.tawInfMaintainerForm.deptId.value;

          if(temp.length == null){
             if(temp.value == id){
                temp.checked = false;
                window.tawInfMaintainerForm.deptId.value=0;
                window.tawInfMaintainerForm.deptName.value="";
             }
             else{
               window.tawInfMaintainerForm.deptId.value=temp.value;
               window.tawInfMaintainerForm.deptName.value=temp3;
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
           window.tawInfMaintainerForm.deptId.value=0;
           window.tawInfMaintainerForm.deptName.value="";
         }
         else{
           window.tawInfMaintainerForm.deptId.value=temp[flag].value;
           window.tawInfMaintainerForm.deptName.value=temp3;
         }
          }


        var dept_Id  = window.tawInfMaintainerForm.deptId.value;
   }
</script>

<html:html>
<head>
<title>查询维护人员资料</title>
<html:base/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script type="text/javascript" src="<%=path%>/css/finallytree.js"></script>
</head>

<html:form action="/TawInfMaintainer/list" method="post">
<body>
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
   createTree9(Tree,1,0,path,"","",
              "window.tawInfMaintainerForm.cid","ifQuery",
              "window.tawInfMaintainerForm.deptId",
              "window.tawInfMaintainerForm.deptName","tree");
   //createTree9(Tree,<%=regionId%>,0,path,"","",
   //           "window.tawInfMaintainerForm.cid","ifQuery",
   //           "window.tawInfMaintainerForm.deptId",
   //           "window.tawInfMaintainerForm.deptName","tree");              
else
   createTree10(Tree,1,0,path,domids,"",
                "window.tawInfMaintainerForm.cid","ifQuery",
                "window.tawInfMaintainerForm.deptId",
                "window.tawInfMaintainerForm.deptName","tree");
   //createTree10(Tree,<%=regionId%>,0,path,domids,"",
   //             "window.tawInfMaintainerForm.cid","ifQuery",
   //             "window.tawInfMaintainerForm.deptId",
   //             "window.tawInfMaintainerForm.deptName","tree");                

</script>
</div>

    <br>
	  <table border="0" width="100%" cellspacing="0" align="center">
	    <tr>
		  <td width="100%" align="center" class="table_title">
            <b>
              <bean:message key="label.query"/>&nbsp;<bean:message key="TawInfMaintainer.Name"/>
	  	    </b>
		  </td>
		</tr>
	  </table>
	  <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
		<tr class="tr_show">
    	  <td width="30%" height="25" class="clsfth">&nbsp;&nbsp;&nbsp;部门<font color="#FF0000">**</font></td>
          <td width="70%" height="25">
          <html:text property="deptName" size="30" styleClass="clstext" readonly="true"/>
          <A HREF="javascript:headerDisplay(1);" >
          <font face="宋体" style="font-size: 9pt"><bean:message key="label.deptTree"/></FONT></A>
          <html:hidden property="deptId"/>
          </td>
        </tr>
		<tr class="tr_show">
		  <td width="30%" height="25" class="clsfth">&nbsp;
			&nbsp<bean:message key="TawInfMaintainer.Maintainer_Name"/>
          </td>
		  <td width="70%" height="25">
		    <html:text styleClass="clstext" property="maintainerName" size="50"/>
		  </td>
		</tr>
		<tr class="tr_show">
          <td width="30%" height="25" class="clsfth">&nbsp;
			&nbsp<bean:message key="TawInfMaintainer.Maintainer_Sex"/>
          </td>
          <td width="70%" height="25">
			<html:select property="maintainerSex">
			  <html:optionsCollection name="tawInfMaintainerForm" property="collectionSex"/>
			</html:select>
		  </td>
		</tr>
		<tr class="tr_show">
		  <td width="30%" height="25" class="clsfth">&nbsp;
			&nbsp<bean:message key="TawInfMaintainer.Tele"/>
          </td>
          <td width="70%" height="25">
			<html:text styleClass="clstext" property="tele" size="50"/>
          </td>
		</tr>
		<tr class="tr_show">
		  <td width="30%" height="25" class="clsfth">&nbsp;
			&nbsp<bean:message key="TawInfMaintainer.Tele_Mobile"/>
          </td>
		  <td width="70%" height="25">
			<html:text styleClass="clstext" property="teleMobile" size="50"/>
		  </td>
		</tr>
	  </table>
      <table border="0" width="100%" cellspacing="0" align="center">
		<tr>
          <td width="100%" colspan="2" height="32" align="right">
			<input Class="clsbtn2" type="button" name="toadd" value="<bean:message key="label.add"/>" onClick="toAdd()">
			&nbsp;
			<input  Class="clsbtn2" type="button" name="tosubmit" value="<bean:message key="label.query"/>" onClick="toSubmit()">
			&nbsp;
          </td>
		</tr>
      </table>
  </body>
</html:form>
</html:html>

<script language="javascript">
  function toSubmit(){
    window.document.tawInfMaintainerForm.submit();
  }

  function toAdd(){
    window.location.href="add.do";
  }
</script>
