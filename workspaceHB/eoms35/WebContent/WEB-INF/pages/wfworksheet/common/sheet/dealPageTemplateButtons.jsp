<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

 <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="window.open('./${module}.do?method=getDealTemplatesByUserId&sheetKey=${sheetMain.id}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskStatus=${taskStatus}&operateType=${operateType}&draft=${taskName}',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
         <bean:message bundle="sheet" key="button.refereTemplate"/>
 </html:button>
 <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;javascript:saveDealTemplate('new')">
       	<bean:message bundle="sheet" key="button.saveTemplate"/>
 </html:button>
<c:if test="${dealTemplateId != null && dealTemplateId != ''}">
	<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;javascript:saveDealTemplate('current')">
        	<bean:message bundle="sheet" key="button.saveCurTemplate"/>
  	</html:button>
</c:if>
