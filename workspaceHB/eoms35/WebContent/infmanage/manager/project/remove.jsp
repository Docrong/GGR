<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*"%>
<html:html>
<head>
<title>项目详细信息</title>
<html:base/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body >
<html:form action="/project/deldone" method="post">

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
  <br>
    <table border="0" width="95%" cellspacing="0">
  <tr>
  <bean:define id="name" name="projectForm" property="project_name" type="java.lang.String"/>
<bean:define id="code_name" name="projectForm" property="project_code_name" type="java.lang.String"/>
<bean:define id="level" name="projectForm" property="project_level" type="java.lang.String"/>
<bean:define id="state" name="projectForm" property="project_state" type="java.lang.String"/>
<bean:define id="instancy" name="projectForm" property="project_instancy" type="java.lang.String"/>
<bean:define id="exec_time" name="projectForm" property="project_exec_time" type="java.lang.String"/>
<bean:define id="comp_time" name="projectForm" property="project_comp_time" type="java.lang.String"/>
<bean:define id="dept_name" name="projectForm" property="dept_name" type="java.lang.String"/>
<bean:define id="executor_name" name="projectForm" property="project_executor_name" type="java.lang.String"/>
<bean:define id="desc" name="projectForm" property="project_desc" type="java.lang.String"/>
<bean:define id="check_project_group" name="projectForm" property="check_project_group" type="java.lang.String"/>

    <td width="106%" class="table_title" align="center"><center>详细项目信息</center></td>
  </tr>
</table>
	<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align="center">
             <tr class="tr_show">

       <html:hidden name="projectForm" property="project_id" />

	<td width="25%" height="25" class="clsfth"><center>项目名称</center></td>
       <td width="75%" height="25">
<%=name%>
	</tr>
      <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		 项目类别</center>
		</td>
		<td width="75%" height="25">
<%=code_name%>
		</td>
      </tr>
       <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		 难易程度</center>
		</td>
		<td width="75%" height="25">
<%=level%>
		</td>
      </tr>
      <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		 紧急程度</center>
		</td>
		<td width="75%" height="25">
<%=state%>
		</td>
      </tr>
      <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		 重要程度</center>
		</td>
		<td width="75%" height="25">
 <%=instancy%>
		</td>
      </tr>
      <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		  开始时间</center>
		</td>
		<td width="75%" height="25">
<%=exec_time%>

        </td>
      </tr>
       <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		  完成时间</center>
		</td>
		<td width="75%" height="25">
<%=comp_time%>

        </td>
      </tr>
      <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		 执行部门</center>
		</td>
		<td width="75%" height="25">
 <%=dept_name%>
		</td>
      </tr>

      <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		 执行人</center>
		</td>
		<td width="75%" height="25">
 <%=executor_name%>
		</td>
      </tr>
      <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		 审核人</center>
		</td>
		<td width="75%" height="25">
 <%=check_project_group%>
		</td>
      </tr>
      <tr class="tr_show">
		<td width="25%" height="25" class="clsfth"><center>
		  项目内容</center>
		</td>
		<td width="75%" height="25">
 <%=desc%>
		</td>
      </tr>

	</table>
        <table border="0" width="100%" cellspacing="0" align="center">
	  <tr>
          <td width="100%" colspan="2" height="32" align="right">
		 <input Class="clsbtn2" type="submit" name="tosubmit" value="删除"/>
                  <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="clsbtn2"/>
          </td>
	  </tr>
	</table>
</html:form>
</body>
</html:html>
