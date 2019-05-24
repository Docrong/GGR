<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

 	  
<!-- 处理环节中的按钮 -->
<input type="submit" class="submit"  id="method.save"  onclick="javascript:isCopy()" value="<bean:message bundle='sheet' key='button.done'/>" > 
<input type="button" class="submit"  id="method.save" onclick="v.passing=true;javascript:saveDealInfo();" value="<bean:message bundle='sheet' key='button.save'/>">
<input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/${module}/${module}.do?method=newPerformSaveInfoAndExit&dealTemplateId=${dealTemplateId}'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
<input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/${module}/${module}.do?method=newShowListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />  


<!-- operateType不等于归档,抄送,驳回上一级,待归档，处理完成, 确认受理,被驳回的情况下显示下面的模板按钮  -->
<c:if test="${operateType != '' && operateType != '18' && operateType != '-10'&& operateType != '4' && operateType != '116' && operateType != '46'&& operateType != '61' && taskName != 'RejectTask'}">
	<!-- 引用模板 -->
	<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="window.open('./${module}.do?method=newGetDealTemplatesByUserId&sheetKey=${sheetMain.id}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskStatus=${taskStatus}&operateType=${operateType}&draft=${taskName}',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
	        <bean:message bundle="sheet" key="button.refereTemplate"/>
	</html:button>
	
	<!-- 保存模板 -->
	 <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;javascript:saveDealTemplate('new')">
	     	<bean:message bundle="sheet" key="button.saveTemplate"/>
	 </html:button>
	 
	 <!-- 保存当前模板 -->
	 <c:if test="${dealTemplateId != null && dealTemplateId != ''}">
	<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;javascript:saveDealTemplate('current')">
	        	<bean:message bundle="sheet" key="button.saveCurTemplate"/>
	  	</html:button>
	</c:if>
	
	<!-- 保存模板时需要配置的规则 -->
	<input type="hidden" name="dealTemplateNameRule" value="title;operateType"/>
	<input type="hidden" name="title" value="${sheetMain.title}"/>
	<input type="hidden" name="dictKey" value="dict-sheet-${module}"/>
</c:if>

