<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<script type="text/javascript">
Ext.onReady(function(){
	var	userTreeAction='${app}/xtree.do?method=userFromDept';
			new xbox({
				btnId:'auditName',dlgId:'dlg-audit',dlgTitle:'请选择人员',
				treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'所有人员',treeChkMode:'single',treeChkType:'user',
				showChkFldId:'auditName',saveChkFldId:'auditer'
			}); 
})
function checkAuditer(){
    var auditer = document.getElementById("auditer");
    document.forms[0].action = "${app}/workbench/report/tawWorkbenchReports.do?method=save";
    if(auditer.value!=null&&auditer.value.trim()!=""){
        document.forms[0].submit();
        return true;
    }
    else {
	    alert("请选审核人！");
	    return false;   
    }
}
</script>
<!--根据给定的实例名生成标题 -->
<title><bean:message key="tawWorkbenchReportDetail.title"/></title>
<c:if test="${flag==null||flag!='week'}">
 <content tag="heading"><bean:message key="tawWorkbenchReportDetail.heading"/></content> 
</c:if>
<c:if test="${flag!=null&&flag=='week'}">
 <content tag="heading"><bean:message key="tawWorkbenchReportDetail.weekheading"/></content> 
</c:if>
<!--对表单的自动生成的处�?-->
<html:form action="tawWorkbenchReports" method="post" styleId="tawWorkbenchReportForm"> 
<ul>

    <!--表示对所有的域有�? -->
	       <html:hidden property="id"/>
	       <input type="hidden" name="flag" id="flag" value="${flag }">
		    <li>
		       
		        <eoms:label styleClass="desc" key="tawWorkbenchReportForm.reportPerson"/>
		        <html:errors property="reportPerson"/>
			   <%
			   String username = (String)request.getAttribute("username"); 
			   String userid = (String)request.getAttribute("userid");
			   %>
			        <html:hidden property="reportPerson" styleId="reportPerson" value="<%=userid%>"/>
			        <html:text property="reportPersonName" styleId="reportPersonName" readonly="true" value="<%=username %>" styleClass="text medium"/>
		    </li>
		    <li>
		        
		        <eoms:label styleClass="desc" key="tawWorkbenchReportForm.reportTime"/>
		        <html:errors property="reportTime"/>
			   <%String date_now = (String)request.getAttribute("date_now"); %>
			    <c:if test="${butian==null||butian!='y'}">
			        <html:text property="reportTime" styleId="reportTime" readonly="true" value="<%=date_now %>" styleClass="text medium"/>
		        </c:if>
		        <c:if test="${butian!=null&&butian=='y'}">
			        <html:text property="reportTime" styleId="reportTime" readonly="true" value="<%=date_now %>" styleClass="text medium" onclick="popUpCalendar(this, this,null,null,null,false,-1);"/>
		        </c:if>
		    </li>
		    <li>
		       
		        <eoms:label styleClass="desc" key="tawWorkbenchReportForm.summary"/>
		        <html:errors property="summary"/>
			    
			        <html:textarea property="summary" styleId="summary" styleClass="text medium" rows="3" />
		    </li>
		    <c:if test="${flag==null||flag!='week'}">
		    <li>
		        
		        <eoms:label styleClass="desc" key="tawWorkbenchReportForm.tomorrowTarget"/>
		        <html:errors property="tomorrowTarget"/>
			    
			        <html:textarea property="tomorrowTarget" styleId="tomorrowTarget" styleClass="text medium" rows="3" />
		    </li>
		    </c:if>
		    <li>
		        
		        <eoms:label styleClass="desc" key="tawWorkbenchReportForm.auditer"/>
		        <html:errors property="auditer"/>
			    	<html:hidden property="auditer" styleId="auditer"/>
			    	<html:text property="auditName" styleId="auditName" styleClass="text medium" readonly="true" />
		    </li>
			     <c:if test="${flag==null||flag!='week'}">
			        <html:hidden property="reportType" styleId="reportType" styleClass="text medium" value="0"/>
			     </c:if>
			     <c:if test="${flag!=null&&flag=='week'}">
			        <html:hidden property="reportType" styleId="reportType" styleClass="text medium" value="1"/>
			     </c:if>  
			        <html:hidden property="auditStatus" styleId="auditStatus" styleClass="text medium" value="0"/>
		
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false;">
            <fmt:message key="button.save"/>
        </html:submit>
        <html:button styleClass="button" property="save" styleId="save&submit" onclick="bCancel=false;document.getElementById('auditStatus').value=1;checkAuditer()">
            <bean:message key="save&submit"/>
        </html:button>
        
        <!--用自动生成的参数调用Javascript --> 
<!--     <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawWorkbenchReport')">
            <fmt:message key="button.delete"/>
        </html:submit>
 -->    
 
     <input type="button" style="margin-right: 5px" class="button"
        onclick="location.href='<c:url value="/workbench/report/tawWorkbenchReports.do?method=list&flag=${flag }"/>'"
        value="<fmt:message key="button.cancel"/>"/>
 
    </li>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>