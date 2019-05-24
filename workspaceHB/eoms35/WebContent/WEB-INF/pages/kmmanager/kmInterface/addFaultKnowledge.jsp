<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
var tree;
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmContentsForm'});	
});

</script>

<html:form action="/kmContentss.do?method=save" styleId="kmContentsForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<!-- 知识状态：1-草稿，2-有效，3-失效，4-删除 -->
<input type="hidden" name="TableInfo/CONTENT_STATUS" value="2" />	

*号为必填内容

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmContents.form.heading"/>&nbsp;新增</div>
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
			           defaultId="" selectId="TableInfo/ROLESTR_FLAG" beanId="selectXML" 
			           alt="allowBlank:false,vtext:'请选择知识等级(字典)...'"/>	
		</td>

		<td class="label">
			<fmt:message key="kmContents.levelFlag" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="levelFlag" isQuery="false" 
			           defaultId="" selectId="TableInfo/LEVEL_FLAG" beanId="selectXML" 
			           alt="allowBlank:false,vtext:'请选择知识难易度(字典)...'"/>
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContents.createUser" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content"><!-- 创建人 -->
			<eoms:id2nameDB id="${KmContentsMap.CREATE_USER}" beanId="tawSystemUserDao" />
			<input type="hidden" name="TableInfo/CREATE_USER" value="${KmContentsMap.CREATE_USER}" />
		</td>

		<td class="label">
			<fmt:message key="kmContents.createDept" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content"><!-- 创建人所在部门 -->
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

	<!-- 内容呈现 add by lian -->
	<tr>
	<%
	   int status = -1; 
	   int row = 1;
	   int sign = 0;
	   int sort = 0;
	%>
	<c:forEach items="${KmTableColumnList}" var="item">
	<%
	   	int flag = 0;
	   	sort++;
	%>

	<c:if test="${item.mark == '1'}">
		<% flag=-1; %>
	</c:if>	
	<%if((flag == -1&& row>1)||( flag==-1 && sort>1)) {
	      if(status==0){%>
			<td class="label"></td>
			<td class="content"></td>
		    <%
		       status =100;
		  }%>
		 </tr>
	 	<tr>
		<% row++; %>
	<% 
	}
	if(flag == 0){
		if(status==1||status==100||sign==1&&row==1){%>	
			<%
				status = -1;
				row++;
				%>
			 </tr>
	 		<tr>
			<%
			}			
			status++;	
	 	}
	%>
	<c:if test="${item.isOpen == 1}">
		<td class="label">
			${item.colChname}<c:if test="${item.isNullable == 0}">&nbsp;<font color='red'>*</font></c:if>
		</td>	
		<%if(flag == -1) {%>
			<td class="content" colspan="3">
		<%}else{%>
			<td class="content" >
			<% }%>
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
			          alt="allowBlank:<c:if test="${item.isNullable == 1}">true</c:if><c:if test="${item.isNullable == 0}">false</c:if>,vtext:'请输入${item.colChname}...'" >${KmContentsMap[item.colName]}</textarea>			              										
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
		        <eoms:attachment name="" property="" scope="request" idField="TableInfo/${item.colName}" appCode="kmmanager" />
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
			    <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="" 
			       alt="allowBlank:true,vtext:'请选择${item.colChname}(单选字典)...'"/>
				</c:if>
				<c:if test="${item.isNullable == 0}">
			    <eoms:comboBox name="TableInfo/${item.colName}" id="${item.colName}" initDicId="${item.colDictId}" defaultValue="" 
			       alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
				</c:if>

			    </c:when>
			    </c:choose>
		    </c:when>

		    <c:when test="${item.colDictType == 2}">
		        <c:choose>
		        <c:when test="${item.colType == 5}">
		        <!-- 知识字典_单选字段 -->
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
	<%if(flag == -1&& row==1) {
			sign = 1;
		}
	 %>	
	</c:forEach>
	  <%if(status==0){%>
			<td class="label"></td>
			<td class="content"></td>
	  <%}%>
	</tr>
	<!-- 内容呈现 结束   add by lian  -->

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
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
		</td>
	</tr>
</table>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>