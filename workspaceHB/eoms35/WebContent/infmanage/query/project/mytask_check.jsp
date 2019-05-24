<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<html:html>
<head>
<title><bean:message key="label.list"/></title>
<html:base/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<form name="form1">
  <body>
	<br>
	  <table border="0" width="100%" cellspacing="0" align="center">
		<tr>


		  <td width="100%" align="center" class="table_title">
			<b>
			  &nbsp;&nbsp;我的任务待审核列表
			</b>
		  </td>
		</tr>
	  </table>
      <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
		<tr class="tr_show">
		 <td width="100%" colspan="12" height="25" align="center"><bean:write name="pagerHeader" scope="request" filter="false"/><%! String key;%></td>
        </tr>


        <tr class="tr_show">
          <td width="10%"  height="25" class="clsfth" align="center"><center>项目名称</center></td>
           <td width="10%"  height="25" class="clsfth" align="center"><center>子任务名称</center></td>
           <td width="5%" height="25" class="clsfth" align="center"><center>派发人</center></td>
           <td width="15%" height="25" class="clsfth" align="center"><center>开始时间</center></td>
           <td width="15%" height="25" class="clsfth" align="center"><center>完成时间</center></td>
           <td width="5%" height="25" class="clsfth" align="center"><center>执行人</center></td>
           <td width="5%" height="25" class="clsfth" align="center"><center>项目比重</center></td>
           <td width="5%" height="25" class="clsfth" align="center"><center>完成情况</center></td>
           <td width="15%" height="25" class="clsfth"align="center"><center><font color="#cc0000">相应操作</font></center></td>

		</tr>

<%
int listNum=0;

%>
		<logic:iterate id="project" name="Project_TaskList" type="com.boco.eoms.infmanage.model.Project">

 <bean:define id="sign" name="project" property="task_sign" type="java.lang.Integer"/>
<bean:define id="task_executor_name" name="project" property="task_executor_name" type="java.lang.String"/>
<bean:define id="task_send_user" name="project" property="task_send_user" type="java.lang.String"/>
<bean:define id="project_send_user" name="project" property="project_send_user" type="java.lang.String"/>
<bean:define id="check_project_group" name="project" property="check_project_group" type="java.lang.String"/>

                  <%java.util.HashMap map = new java.util.HashMap();

                       map.put("id",String.valueOf(project.getId()));  //id
			pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);%>

                  <tr class="tr_show">
                   <td width="10%" height="25" class="clsfth" align="center">
                    <center><bean:write name="project" property="project_name" scope="page"/></center></td>
		  <td width="10%" height="25" class="clsfth" align="center">

                    <center><bean:write name="project" property="task_name" scope="page"/></center>
                     </td>
                   <td width="5%" height="25" class="clsfth" align="center">
                    <center><bean:write name="project" property="task_send_user" scope="page"/></center></td>
                  <td width="15%" height="25" class="clsfth" align="center">
                    <center><bean:write name="project" property="task_exec_time" scope="page"/></center></td>
                    <td width="15%" height="25" class="clsfth" align="center">
                    <center><bean:write name="project" property="task_comp_time" scope="page"/></center></td>
                   <td width="5%" height="25" class="clsfth" align="center">
                     <center><bean:write name="project" property="task_executor_name" scope="page"/></center></td>

                  <td width="5%" height="25" class="clsfth" align="center">
                     <center><bean:write name="project" property="task_scale" scope="page"/>%</center></td>
 <% if(Integer.parseInt(sign.toString())<100){ %>
               <td width="5%" height="25" class="clsfth" align="center">
                      <center><%=sign%>%</center></td>

          <td width="15%" height="25" class="clsfth" align="center"><center><font color="#cc0000">
             <html:link page="/project/task_checkdone.do"   name="map" scope="page">
                子任务审核
               </html:link>&nbsp;</font></center></td>

<%}  else {%>
  <td width="15%" height="25" class="clsfth" align="center">
                      <center><font color="red">100%</font></center></td>
 <td width="15%" height="25" class="clsfth" align="center"><center><font color="#cc0000">
 <%
 String userName=StaticMethod.nullObject2String(request.getAttribute("userName"));
 String[] check_project_group1 = StaticMethod.TokenizerString2(check_project_group, ",");
 if(check_project_group1.length==1&&check_project_group1[0].equals(userName)
 ){
  %>
   <html:link page="/project/task_checkdone.do"   name="map" scope="page">
                子任务审核
   </html:link>&nbsp;</font></center>

  <%
  }
  else {
    for(int  i = 0; i < check_project_group1.length; i++){

      if((userName.equalsIgnoreCase(task_send_user)&&!(task_send_user.equalsIgnoreCase(task_executor_name)))||
      check_project_group1[i].equalsIgnoreCase(userName)
      ){
      %>
    <html:link page="/project/task_checkdone.do"   name="map" scope="page">
                子任务审核
   </html:link>&nbsp;</font></center>
   <%
   System.out.println(check_project_group1[i]);
   break;
   }
  }
  }
 %>
               </td>
 <%}
%>


           </tr>
		</logic:iterate>
    　</table>
      <table border="0" width="100%" cellspacing="0">
		  <tr>
		    <td width="100%" colspan="10" height="32" align="right">
		  <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="clsbtn2"/>
		    </td>
		  </tr>
      </table>
  </body>

</form>
</html:html>
