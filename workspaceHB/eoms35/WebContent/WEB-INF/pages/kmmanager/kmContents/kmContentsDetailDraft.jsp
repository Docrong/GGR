<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
var tree;
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmContentsForm'});
});

function onTableChg(table){
    var selValue = table.options[table.options.selectedIndex].value;
	var url = '${app}/kmmanager/kmContentss.do?method=add&TABLE_ID='+ selValue;
	location.href = url;
}

function addHiddenValue(){
	var draftButton=document.getElementById("draft");
	draftButton.value="yes";
	return true;
}
</script>

<html:form action="/kmContentss.do?method=save" styleId="kmContentsForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<!-- 定义知识内容变量 -->
<c:set var="KmContentsMap" scope="page" value="${KmContents}"/>	

<!-- 知识ID -->
<input type="hidden" name="TableInfo/ID" value="${KmContentsMap.ID}" />
<!-- 修改人 -->
<input type="hidden" name="TableInfo/MODIFY_USER" value="<bean:write name="SessionUserId"/>" />
<!-- 修改人部门 -->
<input type="hidden" name="TableInfo/MODIFY_DEPT" value="<bean:write name="SessionDeptId"/>" />
<!-- 知识被修改的次数 -->
<input type="hidden" name="TableInfo/MODIFY_COUNT[@field='ADD']" value="1" />
<!-- 知识状态：1-草稿，2-有效，3-失效，4-删除 -->
<input type="hidden" name="TableInfo/CONTENT_STATUS" value="${KmContentsMap.CONTENT_STATUS}" />	

*号为必填内容

<table class="formTable">
	<caption>
		<div class="header center">知识内容草稿&nbsp;查看</div>
	</caption>
	<tr>
		<td class="label">
			<fmt:message key="kmContents.tableId" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<eoms:id2nameDB id="${KmContentsMap.TABLE_ID}" beanId="kmTableGeneralDao" /><!-- 知识所属知识库 -->
			<input type="hidden" name="TableInfo/TABLE_ID" value="${KmContentsMap.TABLE_ID}" />
		</td>

		<td class="label">
			<fmt:message key="kmContents.themeId" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">								
		  	<eoms:id2nameDB id="${KmContentsMap.THEME_ID}" beanId="kmTableThemeDao" /><!-- 知识所属分类 -->
		  	<input type="hidden" name="TableInfo/THEME_ID" value="${KmContentsMap.THEME_ID}" />		
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContents.rolestrFlag" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="rolestrFlag" isQuery="false" 
			           defaultId="${KmContentsMap.ROLESTR_FLAG}" selectId="TableInfo/ROLESTR_FLAG" beanId="selectXML" 
			           alt="allowBlank:false,vtext:'请选择知识等级(字典)...'"/>	
		</td>

		<td class="label">
			<fmt:message key="kmContents.levelFlag" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="levelFlag" isQuery="false" 
			           defaultId="${KmContentsMap.LEVEL_FLAG}" selectId="TableInfo/LEVEL_FLAG" beanId="selectXML" 
			           alt="allowBlank:false,vtext:'请选择知识难易度(字典)...'"/>
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContents.createUser" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<eoms:id2nameDB id="${KmContentsMap.CREATE_USER}" beanId="tawSystemUserDao" />
			<input type="hidden" name="TableInfo/CREATE_USER" value="${KmContentsMap.CREATE_USER}" />
		</td>

		<td class="label">
			<fmt:message key="kmContents.createDept" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<eoms:id2nameDB id="${KmContentsMap.CREATE_DEPT}" beanId="tawSystemDeptDao" />
			<input type="hidden" name="TableInfo/CREATE_DEPT" value="${KmContentsMap.CREATE_DEPT}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContents.contentTitle" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" colspan="3">
		    <input type="text" name="TableInfo/CONTENT_TITLE" id="CONTENT_TITLE" value="${KmContentsMap.CONTENT_TITLE}" maxLength="100" class="text max" alt="allowBlank:false,vtext:'请输入知识标题...'" />		    		 
		</td>
	</tr>

	<tr>
	<%--
		modify by mios:
		mark有4个状态： 0：半行， 1：整行， 2：左半行，右为空行， 3：右半行，左为空行,通过mark控制是否输出空列
		flag用来控制是否换行，每输出两列后，flag+1 
	--%>
	<c:set var="flag" value="${0}"/>
	<c:forEach items="${KmTableColumnList}" var="item" varStatus="status">
	
	<c:if test="${item.mark == 3}"> <%-- 有右半行，填充左半行为空行--%>
	  <td class="label"></td>
	  <td class="content" role="4"></td>
	  <c:set var="flag" value="${flag+1}"/>
	</c:if>
	
	<c:if test="${item.isOpen == 1}">
	  <td class="label">
	  	${item.colChname}<c:if test="${item.isNullable == 0}">&nbsp;<font color='red'>*</font></c:if>
	  </td>
	  <td class="content" <c:if test="${item.mark == 1}">colspan="3"</c:if> role="${item.mark}">
		    <c:choose>
		    <c:when test="${item.colDictType == 0}">
		        <c:choose>
		        <c:when test="${item.colType == 1}">
		         <!-- 不绑定_普通文本 -->
		        <input type="text" name="TableInfo/${item.colName}" id="${item.colName}" value="${KmContentsMap[item.colName]}" maxLength="${item.colSize}" class="text medium" 
			       alt="allowBlank:<c:if test="${item.isNullable == 1}">true</c:if><c:if test="${item.isNullable == 0}">false</c:if>,vtext:'${item.colChname}'" />
		        </c:when>

		        <c:when test="${item.colType == 2}">
		        <!-- 不绑定_大文本域 -->
		        <textarea name="TableInfo/${item.colName}" id="${item.colName}" 
			          cols="50" class="textarea max" 
			          alt="allowBlank:<c:if test="${item.isNullable == 1}">true</c:if><c:if test="${item.isNullable == 0}">false</c:if>,vtext:'请输入${item.colChname}...'" type="_moz">${KmContentsMap[item.colName]}</textarea>			              										
		        </c:when>
		        
		        <c:when test="${item.colType == 3}">
		        <!-- 不绑定_数字类型 -->
		        <input type="text" name="TableInfo/${item.colName}" id="${item.colName}" value="${KmContentsMap[item.colName]}" class="text medium" 
			       alt="allowBlank:<c:if test="${item.isNullable == 1}">true</c:if><c:if test="${item.isNullable == 0}">false</c:if>,vtext:'请输入${item.colChname}(正整数类型)...',vtype:'number'" />
		        </c:when>

		        <c:when test="${item.colType == 4}">
		        <!-- 不绑定_日期时间 -->
		        <input type="text" name="TableInfo/${item.colName}" id="${item.colName}" value="${KmContentsMap[item.colName]}" size="20" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true" class="text" 
			       alt="allowBlank:<c:if test="${item.isNullable == 1}">true</c:if><c:if test="${item.isNullable == 0}">false</c:if>,vtext:'请选择${item.colChname}...'" />
		        </c:when>
		        
		        <c:when test="${item.colType == 7}">
		        <!-- 不绑定_附件上传 -->
		        <eoms:attachment name="KmContents" property="${item.colName}" scope="request" idField="TableInfo/${item.colName}" appCode="kmmanager" />
		        </c:when>
		        </c:choose>
		    </c:when>

		    <c:when test="${item.colDictType == 1}">
		        <c:choose>
		        <c:when test="${item.colType == 5}">
		            <!-- 有联动的子节点，没有父节点 -->
		            <c:if test="${item.subNode != '' and (item.parentNode == null or item.parentNode == '')}">
						<c:if test="${item.isNullable == 1}">
						<eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" sub="${item.subNode}" defaultValue="${KmContentsMap[item.colName]}" 
		                    alt="allowBlank:true,vtext:'请选择${item.colChname}(单选字典)...'"/>
						</c:if>
						<c:if test="${item.isNullable == 0}">
						<eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" sub="${item.subNode}" defaultValue="${KmContentsMap[item.colName]}" 
		                    alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
						</c:if>
		            </c:if>

		            <!-- 有联动的子节点，有父节点 -->
		            <c:if test="${item.subNode != '' and item.parentNode != null and item.parentNode != ''}">
		                <c:if test="${item.isNullable == 1}">
						<eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${KmContentsMap[item.parentNode]}" sub="${item.subNode}" defaultValue="${KmContentsMap[item.colName]}" 
		                    alt="allowBlank:true,vtext:'请选择${item.colChname}(单选字典)...'"/>
						</c:if>
						<c:if test="${item.isNullable == 0}">
						<eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${KmContentsMap[item.parentNode]}" sub="${item.subNode}" defaultValue="${KmContentsMap[item.colName]}" 
		                    alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
						</c:if>
		            </c:if>

		            <!-- 没有联动的子节点，有父节点 -->
		            <c:if test="${item.subNode == '' and item.parentNode != null and item.parentNode != ''}">
		                <c:if test="${item.isNullable == 1}">
		                <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${KmContentsMap[item.parentNode]}" defaultValue="${KmContentsMap[item.colName]}" 		            
		                    alt="allowBlank:true,vtext:'请选择${item.colChname}(单选字典)...'"/>
						</c:if>
						<c:if test="${item.isNullable == 0}">
		                <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${KmContentsMap[item.parentNode]}" defaultValue="${KmContentsMap[item.colName]}" 		            
		                    alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
						</c:if>
		            </c:if>

		            <!-- 没有联动的子节点，没有父节点 -->
		            <c:if test="${item.subNode == '' and (item.parentNode == null or item.parentNode == '')}">
		                <c:if test="${item.isNullable == 1}">
		                <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="${KmContentsMap[item.colName]}" 		            
		                    alt="allowBlank:true,vtext:'请选择${item.colChname}(单选字典)...'"/>
						</c:if>
						<c:if test="${item.isNullable == 0}">
		                <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="${KmContentsMap[item.colName]}" 		            
		                    alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
						</c:if>
		            </c:if>
			    </c:when>

			    <c:when test="${item.colType == 6}">
			        <!-- 普通字典_多选字段 -->
			    	<c:if test="${item.isNullable == 1}">
		                <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="${KmContentsMap[item.colName]}" 		            
		                    alt="allowBlank:true,vtext:'请选择${item.colChname}(单选字典)...'"/>
					</c:if>
					<c:if test="${item.isNullable == 0}">
		                <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="${KmContentsMap[item.colName]}" 		            
		                    alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
					</c:if>
			    </c:when>
			    </c:choose>
		    </c:when>

		    <c:when test="${item.colDictType == 2}">
		        <c:choose>
		        <c:when test="${item.colType == 5}">
		        <!-- 文件字典_单选字段 -->
		        <c:if test="${item.isNullable == 1}">
		        <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="${KmContentsMap[item.colName]}" 
			       alt="allowBlank:true,vtext:'请选择${item.colChname}(单选字典)...'"/>
				</c:if>
				<c:if test="${item.isNullable == 0}">
		        <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="${KmContentsMap[item.colName]}" 
			       alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
				</c:if>
			    </c:when>
			    
			    <c:when test="${item.colType == 6}">
			    <!-- 知识字典_多选字段 -->
		        <c:if test="${item.isNullable == 1}">
			    <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="${KmContentsMap[item.colName]}" 
			       alt="allowBlank:true,vtext:'请选择${item.colChname}(单选字典)...'"/>
				</c:if>
				<c:if test="${item.isNullable == 0}">
			    <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="${KmContentsMap[item.colName]}" 
			       alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
				</c:if>
			    </c:when>
			    </c:choose>		    
		    </c:when>

		    <c:when test="${item.colDictType == 3}">
		        <c:choose>
		        <c:when test="${item.colType == 5}">
		        <!-- 文件字典_单选字段 -->
		        <c:if test="${item.isNullable == 1}">
		        <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="${KmContentsMap[item.colName]}" 
			       alt="allowBlank:true,vtext:'请选择${item.colChname}(单选字典)...'"/>
				</c:if>
				<c:if test="${item.isNullable == 0}">
		        <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="${KmContentsMap[item.colName]}" 
			       alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
				</c:if>
			    </c:when>
			    
			    <c:when test="${item.colType == 6}">
			    <!-- 文件字典_多选字段 -->
		        <c:if test="${item.isNullable == 1}">
			    <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="${KmContentsMap[item.colName]}" 
			       alt="allowBlank:true,vtext:'请选择${item.colChname}(单选字典)...'"/>
				</c:if>
				<c:if test="${item.isNullable == 0}">
			    <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="${KmContentsMap[item.colName]}" 
			       alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
				</c:if>
			    </c:when>
			    </c:choose>		    
		    </c:when>
		    </c:choose>
		</td>
	</c:if>	

	<c:if test="${item.mark != 1}"> <%-- 半行--%>
	  <c:set var="flag" value="${flag+1}"/>
	</c:if>
		 
	 <c:if test="${item.mark == 2}"> <%-- 有左半行，填充右半行为空行--%>
	  <td class="label"></td>
	  <td class="content" role="4"></td>
	  <c:set var="flag" value="${flag+1}"/>
	</c:if>
	
	<c:if test="${item.mark == 1 || flag>=2}"> <%-- 整行，可以换行--%>
	  </tr>
	  ${!status.last?"<tr>":""}
	  <c:set var="flag" value="${0}"/>
	</c:if>
	
	</c:forEach>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContents.contentKeys" />&nbsp;<font color='red'>*</font>			
		</td>
		<td class="content" colspan="3">
		    <input type="text" name="TableInfo/CONTENT_KEYS" id="CONTENT_KEYS" value="${KmContentsMap.CONTENT_KEYS}" maxLength="100" class="text max" alt="allowBlank:false,vtext:'请输入知识关键字...'" />
		</td>
	</tr>
</table>

</fmt:bundle>

<br>

<table>
	<tr>
		<td>
		<input type="submit" class="btn" value="提交到知识库" />&nbsp;&nbsp;
		<input type="submit" class="btn" value="存到草稿箱" onclick="return addHiddenValue()"/>
		<input type="hidden" id="draft" name="draft" value=""/>
		</td>
	</tr>
</table>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>