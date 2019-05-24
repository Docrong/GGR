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
           var id = window.projectForm.task_dept_id.value;

          if(temp.length == null){
             if(temp.value == id){
                temp.checked = false;
                window.projectForm.task_dept_id.value=0;
                window.projectForm.task_dept_name.value="";
             }
             else{
               window.projectForm.task_dept_id.value=temp.value;
               window.projectForm.task_dept_name.value=temp3;
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
           window.projectForm.task_dept_id.value=0;
           window.projectForm.task_dept_name.value="";
         }
         else{
           window.projectForm.task_dept_id.value=temp[flag].value;
           window.projectForm.task_dept_name.value=temp3;
         }
          }

var task_dept_id  = window.projectForm.task_dept_id.value;
        var sel_sprlen=document.projectForm.task_executor.options.length-1;
        for(j=sel_sprlen;j>=0;j--)
        {
             document.all.projectForm.task_executor.options[j]=null;
        }

         var m=0;
         document.projectForm.task_executor.options[m]=new Option("","");
         if ((task_dept_id != "0") && (User[task_dept_id]))
         {
            var i;
            var k = User[task_dept_id].length;
            for (i=0;i*2<k;i++)
             {
             var tempoption=new Option(User[task_dept_id][i*2],User[task_dept_id][i*2+1]);
             document.projectForm.task_executor.options[++m]=tempoption;
            }
         }
       }
</script>
<html>
<head>
<script language="javascript" src="<%=request.getContextPath()%>/css/checkform.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script type="text/javascript" src="<%=path%>/css/finallytree.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/css/table_calendar.js"></script>
</head>

<bean:define id="name" name="projectForm" property="project_name" type="java.lang.String"/>
<bean:define id="scale" name="projectForm" property="project_scale" type="java.lang.Integer"/>
<bean:define id="id" name="projectForm" property="project_id" type="java.lang.Integer"/>
<bean:define id="exec_time" name="projectForm" property="project_exec_time" type="java.lang.String"/>
<bean:define id="comp_time" name="projectForm" property="project_comp_time" type="java.lang.String"/>

<% int rest =100-Integer.parseInt(scale.toString()); %>

<script language="javascript">
//派发树
function selectTree(){
	dWinOrnaments = "status:no;scroll:no;resizable:no;dialogHeight:450px;dialogWidth:480px;";
	dWin = showModalDialog('<%=request.getContextPath()%>/css/listbox/listbox_inf.jsp?sort=3&user=yes&checkall=no&selectself=yes', window, dWinOrnaments);
}

   function onSave()
{
    if ((projectForm.task_name.value)=="")
  {
    alert("子任务名称不能为空");
    return false;
  }
  if ((projectForm.task_exec_time.value)=="")
  {
    alert("请选择开始时间");
    return false;
  }
    if (projectForm.task_exec_time.value.substring(0,10)< "<%=exec_time.substring(0,10)%>")
  {
    alert("子任务的开始时间不得早于整个项目的开始时间");
    return false;
  }
  if ((projectForm.task_comp_time.value)=="")
  {
    alert("请选择完成时间");
    return false;
  }
if (projectForm.task_comp_time.value.substring(0,10)>"<%=comp_time.substring(0,10)%>")
  {
    alert("子任务的结束时间不得晚于整个项目的结束时间");
    return false;
  }
    if ((projectForm.task_dept_name.value)=="")
  {
    alert("选择执行部门");
    return false;
  }
  if ((projectForm.task_executor.value)=="")
  {
    alert("选择执行人");
    return false;
  }
      if ((projectForm.task_scale.value)=="")
  {
    alert("所占项目比例不能为空");
    return false;
  }
    if ((projectForm.task_scale.value)><%=rest%>)
  {
    alert("所占项目比例大于剩余比例");
    return false;
  }

  projectForm.submit();
  return true;

}
</script>
<title></title>

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


<html:form method="post" action="/project/savetask">
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
              "window.projectForm.task_dept_id",
              "window.projectForm.task_dept_name","tree");
else
   createTree10(Tree,<%=regionId%>,0,path,domids,"",
                "window.tawRmUserForm.cid","ifQuery",
                "window.projectForm.task_dept_id",
                "window.projectForm.task_dept_name","tree");

</script>
</div>

<center>

<br>
<table border="0" width="95%" cellspacing="0">

<html:hidden name="projectForm" property="parent_id" value="<%=id.toString()%>"/>
<html:hidden name="projectForm" property="task_send_user" value="<%=userName%>"/>

  <tr>
    <td width="106%" class="table_title" align="center"><center><%=name%>----新增项目子任务信息(剩余可分配比例<font color="red">  <%=rest%>%</font>)</center></td>
  </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
   <td width="25%" height="25" class="clsfth"><center>子任务名称</center></td>
    <td width="70%" height="25">
      <html:text property="task_name" size="30" styleClass="clstext"/></td>
  </tr>
      <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		  子任务开始时间</center>
		</td>
		<td width="75%" height="25">
          <html:text styleClass="clstext" property="task_exec_time" readonly="true" size="20" onfocus="setday(this)"  /><font color=crimson>*</font>
	      不得早于  <font  color="red"><%=exec_time.substring(0,10)%></font></td>

      </tr>
       <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		  子任务完成时间</center>
		</td>
		<td width="75%" height="25">
          <html:text styleClass="clstext" property="task_comp_time" readonly="true" size="20" onfocus="setday(this)"  /><font color=crimson>*</font>
               不得晚于  <font color="red"><%=comp_time.substring(0,10)%></font></td>

      </tr>
  <tr class="tr_show">
    <td width="30%" height="25" class="clsfth"><center>子任务执行部门</center></td>
    <td width="70%" height="25">
        <html:text property="task_dept_name" size="30" styleClass="clstext" readonly="true"/>
<A HREF="javascript:headerDisplay(1);"><font face="宋体" style="font-size: 9pt"><bean:message key="label.deptTree"/></FONT></A>
<html:hidden property="task_dept_id"/><font color=crimson>*</font>
    </td>
  </tr>
  <tr class="tr_show">
    <td width="30%" height="25" class="clsfth"><center>子任务执行人</center></td>
    <td width="70%" height="25">
       <select size=1 name="task_executor" style="width:220;z-index:-1">
          <option value=""></option>
       </select><font color=crimson>*</font>
    </td>
  </tr>
    <tr class="tr_show">
   <td width="25%" height="25" class="clsfth"><center>所占项目比例</center></td>
    <td width="70%" height="25">
      <html:text property="task_scale" value="" size="2" styleClass="clstext"/>%</td>
  </tr>
   <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		  子任务内容</center>
		</td>
		<td width="75%" height="25">
                  <html:textarea property="task_remark" rows="4" style="width:100%" value="" title="问题描述"/>
		</td>
      </tr>

</table>
    <table border="0" width="100%" cellspacing="0" align="center">
	  <tr>
          <td width="100%" colspan="2" height="32" align="right">
			<input Class="clsbtn2" type="button" name="tosubmit" value="<bean:message key="label.save"/>"
                         onclick="return onSave();">

			<input Class="clsbtn2" type="reset" name="toreset" value="<bean:message key="label.reset"/>">
          </td>
	  </tr>
	</table>
        </center>
</html:form>

</body>

</html>

