<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<script type="text/javascript">
    var checkflag = "false";
	function chooseAll(){	
	    var objs = document.getElementsByName("checkbox11");    
	    if(checkflag=="false"){
	        for(var i=0; i<objs.length; i++){
	           objs[i].checked="checked";
	        } 
	        checkflag="checked";
	    }
	    else if(checkflag=="checked"){ 	    	    
		    for(var i=0; i<objs.length; i++){
		           objs[i].checked=false;
		    } 
		    checkflag="false";
	    }
	    
	}
	function isChecked(){
	    var objs = document.getElementsByName("checkbox11");
	    var flag = false;
	    for(var i=0; i<objs.length; i++){
	       if(objs[i].checked==true){
	           flag=true;
	           break;
	       }
	    }
	    if(flag==true)return true;
	    else if(flag==false){
	        alert("请选择删除项！");
	        document.forms[0].notsubmit=true;
	        return false;
	    }
	}
</script>
<html:form action="tawWorkbenchReports" method="post"  styleId="tawWorkbenchReportForm">
<c:if test="${flag==null||flag!='week'}">
<content tag="heading"><bean:message key="tawWorkbenchReportList.unAuditHeading"/></content>
</c:if>
<c:if test="${flag!=null&&flag=='week'}">
 <content tag="heading"><bean:message key="tawWorkbenchReportDetail.weekunAuditHeading"/></content> 
</c:if>

<c:set var="buttons">
<c:if test="${flag==null||flag!='week'}">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/workbench/report/tawWorkbenchReports.do?method=add"/>'"
        value="<fmt:message key="button.add"/>"/>
</c:if>

<c:if test="${flag!=null&&flag=='week'}"> 
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/workbench/report/tawWorkbenchReports.do?method=add&flag=week"/>'"
        value="<fmt:message key="button.add"/>"/>
</c:if>       
  
        
    <html:submit style="margin-right: 5px" property="method.delete" onclick="isChecked()"> 
        <fmt:message key="button.delete"/>
    </html:submit>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<fmt:setBundle basename="config.ApplicationResources-workbench-report"/>
<display:table name="tawWorkbenchReportList" cellspacing="0" cellpadding="0"
    id="tawWorkbenchReportList" pagesize="25" class="table tawWorkbenchReportList"
    export="false" requestURI="/workbench/report/tawWorkbenchReports.do?method=listUnAudited" sort="external" partialList="true" size="resultSize">

    <display:column  title="<input type='checkbox' onclick='javascript:chooseAll();'>" >
         <input type="checkbox" name="checkbox11" value="<c:out value='${tawWorkbenchReportList.id}'/>" >
    </display:column>
    
<c:if test="${flag==null||flag!='week'}">
    <display:column property="reportPersonName" sortable="true" headerClass="sortable"
         url="/workbench/report/tawWorkbenchReports.do?method=edit"  paramId="id" paramProperty="id" titleKey="tawWorkbenchReportForm.reportPersonName"/>
</c:if>

<c:if test="${flag!=null&&flag=='week'}"> 
    <display:column property="reportPersonName" sortable="true" headerClass="sortable"
         url="/workbench/report/tawWorkbenchReports.do?method=edit&flag=week"  paramId="id" paramProperty="id" titleKey="tawWorkbenchReportForm.reportPersonName"/>
</c:if>

    <display:column property="reportTime" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchReportForm.reportTime"/>

    <display:column property="summary" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchReportForm.summary"/>

<c:if test="${flag==null||flag!='week'}">
    <display:column property="tomorrowTarget" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchReportForm.tomorrowTarget"/>
</c:if>         

    <display:column property="auditName" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchReportForm.auditName"/>
     
    <display:column  sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchReportForm.auditStatus"> 
         <c:if test="${tawWorkbenchReportList.auditStatus==0}">未提交</c:if>
         <c:if test="${tawWorkbenchReportList.auditStatus==1}">已提交</c:if>
         <c:if test="${tawWorkbenchReportList.auditStatus==2}">已审核</c:if>
    </display:column> 
        
<c:if test="${flag==null||flag!='week'}">
     <display:column 
         titleKey="shenhe" url="/workbench/report/tawWorkbenchReports.do?method=audit" paramId="id" paramProperty="id">
         <bean:message key='shenhe'/>
     </display:column> 
</c:if>     
     
      
	<c:if test="${flag!=null&&flag=='week'}"> 
	 <display:column 
         titleKey="shenhe" url="/workbench/report/tawWorkbenchReports.do?method=audit&flag=<c:out value='${flag }'/>" paramId="id" paramProperty="id">
         <bean:message key='shenhe'/>
     </display:column> 
	  <input type="hidden" name="flag" id="flag" value="<c:out value='${flag }'/>" >
	</c:if>
	
    <display:setProperty name="paging.banner.item_name" value="tawWorkbenchReport"/>
    <display:setProperty name="paging.banner.items_name" value="tawWorkbenchReports"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>

