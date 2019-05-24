<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import ="com.boco.eoms.common.tree.*"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
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

String userName = StaticMethod.nullObject2String(request.getAttribute("userName"),"");
%>


 <script language="JavaScript">

var User = new Array;
<%=users%>


       function ifQuery(temp,temp3,tempId,tempName){
	   var i;
	   var flag= -100;
           var id = window.projectForm.dept_id.value;

          if(temp.length == null){
             if(temp.value == id){
                temp.checked = false;
                window.projectForm.dept_id.value=0;
                window.projectForm.dept_name.value="";
             }
             else{
               window.projectForm.dept_id.value=temp.value;
               window.projectForm.dept_name.value=temp3;
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
           window.projectForm.dept_id.value=0;
           window.projectForm.dept_name.value="";
         }
         else{
           window.projectForm.dept_id.value=temp[flag].value;
           window.projectForm.dept_name.value=temp3;
         }
          }

var dept_id  = window.projectForm.dept_id.value;
        var sel_sprlen=document.projectForm.project_executor.options.length-1;
        for(j=sel_sprlen;j>=0;j--)
        {
             document.all.projectForm.project_executor.options[j]=null;
        }

         var m=0;
         document.projectForm.project_executor.options[m]=new Option("","");
         if ((dept_id != "0") && (User[dept_id]))
         {
            var i;
            var k = User[dept_id].length;
            for (i=0;i*2<k;i++)
            {
             var tempoption=new Option(User[dept_id][i*2],User[dept_id][i*2+1]);
             document.projectForm.project_executor.options[++m]=tempoption;
            }
         }
       }
</script>
<head>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script type="text/javascript" src="<%=path%>/css/finallytree.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/css/table_calendar.js"></script>
</head>

<title>项目管理</title>

<body >
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


<html:form method="post" action="/project/query1done.do?flag=1">
<SCRIPT language=javascript>
<!--
//以下四行用于日历显示
var outObject;
var old_dd = null;
writeCalender();
bltSetDay(bltTheYear,bltTheMonth);
//-->
</SCRIPT>
<input type="hidden" name="path" id="path" value="<%=path%>">
<input type="hidden" name="sdomids" id="sdomids" value="<%=sdomIds%>">
<div id="tree">
<font face="宋体" style="font-size: 9pt" COLOR="#990000" ><B><bean:message key="label.deptTree"/></B>&nbsp;[&nbsp;<A HREF="javascript:headerDisplay(0);"><bean:message key="label.hide"/></A>&nbsp;]</FONT>
<BR>
<script type="text/javascript">
var path=document.all.path.value;
var domids = document.all.sdomids.value;
var Tree = new Array;
<%=strTree%>
if( domids == "")
   createTree9(Tree,<%=regionId%>,0,path,"","",
              "window.projectForm.cid","ifQuery",
              "window.projectForm.dept_id",
              "window.projectForm.dept_name","tree");
else
   createTree10(Tree,<%=regionId%>,0,path,domids,"",
                "window.tawRmUserForm.cid","ifQuery",
                "window.projectForm.dept_id",
                "window.projectForm.dept_name","tree");

</script>
</div>

<center>
<eoms:DictType typeName="project_code"/>
<br>
<table border="0" width="95%" cellspacing="0">
  <tr>
    <td width="106%" class="table_title" align="center"><center>查询项目信息</center></td>
  </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
   <td width="25%" height="25" class="clsfth"><center>项目名称</center></td>
    <td width="70%" height="25">
      <html:text property="project_name" size="30" styleClass="clstext"/></td>
  </tr>
      <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		 项目类别</center>
		</td>
		<td width="75%" height="25">
               <html:select property="project_code" style="width: 3.6cm;">
                  <option value="0">请选择</option>
                <html:options collection="project_code" property="value" labelProperty="label"/>
                </html:select>
		</td>
      </tr>
<%
String pro_code =StaticMethod.getNodeName("SYSTEM.DICTTYPE.project_code");
%>

<html:hidden property="project_code"  value="<%=pro_code%>"/>
<html:hidden name="projectForm" property="project_send_user" value="<%=userName%>"/>


      <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		  开始时间</center>
		</td>
		<td width="75%" height="25">
          <html:text styleClass="clstext" property="project_exec_time" value="" readonly="true" size="20" onfocus="setday(this)"  />

        </td>
      </tr>
       <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		  完成时间</center>
		</td>
		<td width="75%" height="25">
          <html:text styleClass="clstext" property="project_comp_time" readonly="true" size="20" onfocus="setday(this)"  />

        </td>
      </tr>

       <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		  项目内容</center>
		</td>
		<td width="75%" height="25">
                  <html:textarea property="project_desc" rows="4" style="width:100%" value="" title="问题描述"/>
		</td>
      </tr>

</table>
    <table border="0" width="100%" cellspacing="0" align="center">
	  <tr>
          <td width="100%" colspan="2" height="32" align="right">
			<input Class="clsbtn2" type="submit" name="tosubmit" value="查询"/>

          </td>
	  </tr>
	</table>
        </center>
</html:form>

</body>

</html>

