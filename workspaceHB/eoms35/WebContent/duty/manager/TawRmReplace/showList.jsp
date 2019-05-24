<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.boco.eoms.duty.model.TawRmReplace"%>
<jsp:directive.page import="com.boco.eoms.duty.util.DutyConstacts"/>
<% 	String roomId = (String)request.getAttribute("roomId");
%>
<script language="javascript">
  Ext.onReady(function(){
	colorRows('list-table');
  })
  
  var trueIcon = new Image();
  trueIcon.src = "../images/true.gif";
  var falseIcon = new Image();
  falseIcon.src = "../images/false.gif";
 
  
</script>
 
<html:form styleId="tawRmReplaceForm" method="post" action="/tawRmReplace.do?method=xgetshow">
<table class="listTable">
<tr>
<td>
<eoms:dict key="dict-duty" dictId="flag" beanId="selectXML"  defaultId="${tawRmReplaceForm.flag}" isQuery="false"  selectId="flag"/>
&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="submit" name="" class="button" value="${eoms:a2u('查询')}"> 
</td>
 
</tr>
</table>
<input type = "hidden" name="roomId" value="<%=roomId%>">
<table class="listTable" id="list-table">
  
 
  <thead>
  <tr>
   
   <td class="label"><bean:message key="tawRmReplaceForm.workserial" /></td>
	<td class="label"><bean:message key="tawRmReplaceForm.hander" /></td>
	<td class="label"><bean:message key="tawRmReplaceForm.inputdate" /></td>
    <td class="label"><bean:message key="tawRmReplaceForm.receiver" /></td>
    <td class="label"><bean:message key="tawRmReplaceForm.dutydate" /></td>
    <td class="label"><bean:message key="tawRmReplaceForm.roomId" /></td>
    <td class="label"><bean:message key="tawRmReplaceForm.reason" /></td>
     <td class="label"><bean:message key="tawRmReplaceForm.flag" /></td>
    <td class="label">${eoms:a2u('备注')}</td>
  </tr>
  </thead>
  <tbody>
<%
	TawRmReplace tawRmReplace = null;
	List list = (List) request.getAttribute("tawRmReplaceList");
	for (Iterator it = list.iterator(); it.hasNext();) {
		tawRmReplace = (TawRmReplace) it.next();
		 
 	 
%>
<tbody>
	
	<td>
		<%=tawRmReplace.getWorkserial()%>
	</td>
	<td>
		<%=tawRmReplace.getHanderName()%>
	</td>
	<td>
		<%=tawRmReplace.getInputdate()%>
	</td>
	<td>
		<%=tawRmReplace.getReceiverName()%>
	</td>
	<td>
		<%=tawRmReplace.getDutydate()%>
	</td>
 	<td>
		<%=tawRmReplace.getRoomName()%>
	</td>
	<td>
		<%=tawRmReplace.getReason()%>
	</td>
	<td>
				<%if (tawRmReplace.getFlag().equals("0")){%>
				${eoms:a2u('管理员审核中')}
				<%}if(tawRmReplace.getFlag().equals("1")){%>
				${eoms:a2u('管理员审核通过')}
				<%}if (tawRmReplace.getFlag().equals("2")){%>
				${eoms:a2u('管理员审核驳回')}
				<%} %>
	</td>
	<td>
		<%=tawRmReplace.getRemark()%>
	</td>
</tbody>
<%
}
%>
</table>
<br>
 </html:form>

<%@ include file="/common/footer_eoms.jsp"%>


