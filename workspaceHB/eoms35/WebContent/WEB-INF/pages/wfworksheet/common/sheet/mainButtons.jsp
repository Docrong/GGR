<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
	<!-- 如果是从新建页面进入 -->
	<logic:notPresent name="templateManage">
		<!-- 派发按钮 -->
		<html:submit styleClass="btn" property="method.save" styleId="method.save" onclick="performAddSubmit()">
	        <bean:message bundle="sheet" key="button.send" />
	    </html:submit>
	    
	    <!-- 不是草稿时才显示 -->
	    <logic:empty name="draft">
			<html:submit styleClass="btn" property="method.saveDraft" styleId="method.saveDraft" onclick="v.passing=true;javascript:saveDraft()">
		        <bean:message bundle="sheet" key="button.saveAsDraft" />
		    </html:submit>
			<!-- 引用模板和保存模板 -->
			<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="window.open('./${module}.do?method=newGetTemplatesByUserId&mainId=${mainId}',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
		        <bean:message bundle="sheet" key="button.refereTemplate" />
		    </html:button>
		    <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;javascript:saveTemplate('new')">
		         <bean:message bundle="sheet" key="button.saveTemplate" />
		     </html:button>
		     
		     <!-- 如果引用模板之后，将还出现保存当前模板 -->
			 <c:if test="${templateId != null && templateId != ''}">
				<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;javascript:saveTemplate('current')">
		           <bean:message bundle="sheet" key="button.saveCurTemplate" />
		        </html:button>
			 </c:if>
		 </logic:empty>
		 
		 <!-- 是草稿时 -->
		 <logic:notEmpty name="draft">
		 		<!-- 保存 -->
		 		<input type="submit" class="submit" onclick="v.passing=true;javascript:saveInfo()" value="<bean:message bundle='sheet' key='button.save'/>" >
		 		<!-- 返回 -->
				<input type="button" class="submit" onclick="history.go(-1);" value="<bean:message bundle='sheet' key='button.back'/>" />	   
		 </logic:notEmpty>
	</logic:notPresent>
	
	
	<!-- 如果是从模板管理列表进入 -->
	<logic:present name="templateManage">
		<c:if test="${templateId != null && templateId != ''}">
		 <logic:present name="type">
			<!-- 模板的标题栏 -->
			<table class="formTable">
				<tr>
					 <td  class="label">模板名称*</td>
					 <td clspan="3">  
			     			<input type="text" class="text max" name="sheetTemplateName" value="${sheetMain.sheetTemplateName}">
	    	 		</td>
				</tr>
			</table>
			<br>
			
			<!-- 保存当前模板和删除模板 -->
			<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;javascript:saveTemplate('current')">
	           	<bean:message bundle="sheet" key="button.saveCurTemplate" />
	        </html:button>
	        <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeTemplate()">
         		<bean:message bundle="sheet" key="button.delete" />
			</html:button>
		</logic:present>
		
		<!-- 返回按钮 -->
		<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="history.back(-1)">
		    <bean:message bundle="sheet" key="button.back" />
		</html:button>
		</c:if>	
	</logic:present>

