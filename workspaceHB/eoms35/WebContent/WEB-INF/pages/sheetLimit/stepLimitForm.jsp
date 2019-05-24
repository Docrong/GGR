<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="com.boco.eoms.sheet.limit.util.TimeFilter"%>
<script type="text/javascript">
   var v;
   Ext.onReady(function(){
     v = new eoms.form.Validation({form:'stepLimitForm'});
  });
 
 </script> 
<title><bean:message bundle="sheetLimit" key="sheetLimit.title"/></title>


<html:form action="sheetLimit.do" method="post" styleId="stepLimitForm"> 
<table class="formTable">
	<caption><bean:message bundle="sheetLimit" key="sheetLimit.title"/></caption>
	<!-- 分类 -->
	<c:if test="${!empty columnMap}">
		<%
		HashMap columnMap = (HashMap)request.getAttribute("columnMap");
		HashMap htmlMap = (HashMap)request.getAttribute("htmlMap");
		List list = (List)request.getAttribute("columnList");
		HashMap defaultValueMap = (HashMap)request.getAttribute("defaultValue");
		for(int i=0;i<list.size();i++){
			String key = (String)list.get(i);
			String columnCnName = (String)columnMap.get(key);
			TimeFilter filter = (TimeFilter)htmlMap.get(key);
		%>
		<tr>
			<td class="label">
				<%=(String)columnMap.get(key) %>
			</td>
			<td>
				<%if(filter.getHtmlType().equals("dict")){%>
					<eoms:comboBox 
						name="<%=key %>" 
						id="<%=key %>" 
						defaultValue="<%=(String)defaultValueMap.get(key) %>"
						initDicId="<%=filter.getDictId() %>" 
						sub="<%=filter.getSub() %>"
						size="<%=filter.getSize() %>" 
						alt="allowBlank:false" 
						styleClass="select-class"/>
				<%} %>
				<%if(filter.getHtmlType().equals("text")){%>
					<input
						type="text" 
						class="text" 
						name="<%=key %>" 
						id="<%=key %>" 
						value="<%=(String)defaultValueMap.get(key) %>" 
						alt="allowBlank:true,maxLength:25"/>
				<%} %>
			</td>
		</tr>
		<%} %>
	</c:if>
	<!-- 步骤 -->
		<tr>
			<td>
				<bean:message bundle="sheetLimit" key="sheetLimit.limit"/>
			</td>
			<td>
				<eoms:comboBox name="taskName" id="taskName" initDicId="10121"  defaultValue="${sheetLimitForm.stepId}" styleClass="select-class"/>
			</td>
		</tr>
	
		<tr>
			<td class="label">
				<bean:message bundle="sheetLimit" key="sheetLimit.limit"/>
			</td>
			<td>
        		<html:text property="limit" styleId="limit"	styleClass="text medium" alt="allowBlank:false,maxLength:9,vtext:'请填入预超时提醒时间，最多输入9个字符'" value="${stepLimit.limit}"/>
			</td>
		</tr>
</table>
	<input type="hidden" name="levelId" value="${stepLimit.levelId }"/>
  	<input type="hidden" name="id" value="${stepLimit.id }"/>
        <html:submit styleClass="button" property="method.save" onclick="return limitCheck();">
        	<bean:message bundle='sheet' key='button.save'/>
        </html:submit>
        <html:submit styleClass="button" property="method.delete" onclick="v.passing=true;return deleteCheck();">
            <bean:message bundle='sheet' key='button.delete'/>
        </html:submit>
        <html:cancel styleClass="button" onclick="v.passing=true">
        	<bean:message bundle='sheet' key='button.back'/>
        </html:cancel>
 </html:form>
<script type="text/javascript">
 	if('${isCanSave}'!='1'){
 		document.all.overtimeLimit.readOnly='true';
 	}
 </script>
<%@ include file="/common/footer_eoms.jsp"%>