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
			new xbox({
				btnId:'reportPersonName',dlgId:'dlg-audit',dlgTitle:'请选择人员',
				treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'所有人员',treeChkMode:'single',treeChkType:'user',
				showChkFldId:'reportPersonName',saveChkFldId:'reportPerson'
			}); 
})
</script>
<!--根据给定的实例名生成标题 -->
<title><bean:message key="tawWorkbenchReportDetail.title"/></title>
<!--  <content tag="heading"><bean:message key="tawWorkbenchReportDetail.heading"/></content>   -->

<!--对表单的自动生成的处�?-->
<html:form action="tawWorkbenchReports" method="post" styleId="tawWorkbenchReportForm"> 
<ul>

    <!--表示对所有的域有�? -->
		    <li>
		       
		        <eoms:label styleClass="desc" key="tawWorkbenchReportForm.reportPerson"/>
		        <html:errors property="reportPerson"/>
		            <html:hidden property="reportPerson" styleId="reportPerson"/>
			        <html:text property="reportPersonName" styleId="reportPersonName" readonly="true"  styleClass="text medium"/>
		    </li>
		    <li>
		        
		        <eoms:label styleClass="desc" key="tawWorkbenchReportForm.reportTime"/>
		        <html:errors property="reportTime"/>
			        <html:text property="reportTime" styleId="reportTime" readonly="true" styleClass="text medium" onclick="popUpCalendar(this, this,null,null,null,false,-1);" />
		    </li>

		    <li>
		        
		        <eoms:label styleClass="desc" key="tawWorkbenchReportForm.auditer"/>
		        <html:errors property="auditer"/>
			    	<html:hidden property="auditer" styleId="auditer"/>
			    	<html:text property="auditName" styleId="auditName" styleClass="text medium" readonly="true" />
		    </li>
		   
		   <li>
		        
		        <eoms:label styleClass="desc" key="tawWorkbenchReportForm.auditTime"/>
		        <html:errors property="auditTime"/>
			    	<html:text property="auditTime" styleId="auditTime" styleClass="text medium" onclick="popUpCalendar(this, this,null,null,null,false,-1);" />
		    </li>
		    <li>
		        
		        <eoms:label styleClass="desc" key="tawWorkbenchReportForm.auditStatus"/>
		        <html:errors property="auditStatus"/>
		        <eoms:dict key="dict-workbench-report" dictId="auditStatus" selectId="auditStatus" beanId="selectXML" alt="allowBlank:false" defaultId="${tawWorkbenchReportForm.auditStatus }" />
		    </li>
			<c:if test="${flag!=null&&flag=='week'}"> 
			  <input type="hidden" name="flag" id="flag" value="<c:out value='${flag }'/>" >
			</c:if>
		
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.list" onclick="bCancel=false;">
            <fmt:message key="button.search"/>
        </html:submit>
        
        <!--用自动生成的参数调用Javascript --> 
<!--     <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawWorkbenchReport')">
            <fmt:message key="button.delete"/>
        </html:submit>
 -->   
  	<input type="reset" value="重置" onclick="bCancel=true" class="button" />
 <!--  
        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
 -->       
    </li>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>