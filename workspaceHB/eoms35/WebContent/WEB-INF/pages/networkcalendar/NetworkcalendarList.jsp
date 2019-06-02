<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/common/header_eoms.jsp"%>
<%
String date=(String)request.getAttribute("date");

%>
<script type="text/javascript" src="${app}/scripts/form/validation.js"></script>

<div class="content">
  <h3>${eoms:a2u("本日有")}${fn:length(networkcalendarList)}${eoms:a2u("项安排")}
    <a onclick="$('sscontent').toggleClass('hide')" style="cursor:pointer">>${eoms:a2u("添加一条")}</a>
  </h3>


  <div id="sscontent" class="hide">
  <html:form action="/networkcalendarmain.do?method=saveNetworkcalendar&date=${param.date}" styleId="networkcalendarForm">
	<input type="hidden" name="tasktime" value="<%=date%>">
	
     <table border="0" width="50%" cellspacing="1">
    	<tr class="tr_show">
		<td class="clsfth"><bean:message key='name'/>:</td></tr>
		<tr class="tr_show">
		<td>
		
	 	  <input type="text" name="taskname" id ="taskname"  style="width:400px"  styleClass="text" alt="allowBlank:false,vtext:'${eoms:a2u('请输入标题')}'"/>
	 	   </td>
	    </tr>
	    <tr class="tr_show">
	   	<td class="clsfth"><bean:message key='time'/>:</td>
		  </tr>
		<tr class="tr_show">
		<td><%=date%><bean:message key='day'/>
		<select name="taskhours" size="1">
               <%
                 
            
                      for(int i=0;i<24;i++) {
                String taskminutes=String.valueOf(i); 
                   if(taskminutes.length()==1){
                   taskminutes="0"+taskminutes;
               } 
                %>
               <option value='<%=taskminutes%>'><%=taskminutes%></option>
               <%}%>
               </select><bean:message key='taskhours'/>
               <select name="taskminutes" size="1">
               <%
               for(int i=0;i<60;i++) {
                String taskminutes=String.valueOf(i); 
                  if(taskminutes.length()==1){
                   taskminutes="0"+taskminutes;
               } 
            
               %>
               <option value='<%=taskminutes%>'><%=taskminutes%></option>
               <%}%>
               </select><bean:message key='taskminutes'/>
		 </td>
    </tr>
    	<tr class="tr_show">
		
	   	<td class="clsfth"><bean:message key='taskRemarks'/>:</td>
		  </tr>
		  <tr class="tr_show">
		<td>
		<textarea rows="4" cols="30" name="taskremarks" style="width:400px" class="textarea" alt="allowBlank:false,vtext:'${eoms:a2u('请输入任务概要')}'"></textarea>
		 </td>
    </tr>
  <tr>
    <td width="100%" height="32" align="left">
                    <html:submit property="strutsButton" styleClass="clsbtn2">
                   <bean:message key='save'/>
                    </html:submit>
                    &nbsp;&nbsp;</td>
  </tr>
 </table>
 </html:form>
</div>


 

  
  <c:forEach items="${networkcalendarList}" var="list" varStatus="status">
    <h3>${status.count}:${list.taskname}</h3>
    <h4><bean:message key='time'/>:${list.tasktime}</h4>
    <h4><bean:message key='taskRemarks'/>:${list.taskremarks}</h4>
    <h4><A href="networkcalendarmain.do?method=xdelete&id=${list.id}"><bean:message key='delete'/></A> <A href="networkcalendarmain.do?method=detail&id=${list.id}"><bean:message key='edit'/></A></h4>
   
  </c:forEach>
</div>


<script type="text/javascript">
   var	v = new eoms.form.Validation({form:'networkcalendarForm'});
</script>

<%@ include file="/common/footer_eoms.jsp"%>