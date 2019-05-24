<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'fileForm'});
	
	new xbox({
        btnId: 'userName',
        dlgId: 'showuser-dlg',
        treeDataUrl: '${app}/xtree.do?method=userFromDept',
        treeRootId: '-1',
        treeRootText: '人员列表',
        treeChkMode: 'single',
        treeChkType: 'user',
        showChkFldId: 'userName',
        saveChkFldId: 'userId'
    });
});
</script>

<html:form action="/files.do?method=searchFile" styleId="fileForm" method="post">
<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
    <caption>
        <div class="header center">
            <fmt:message key="kmFile.title.query" />
        </div>
    </caption>
    
    <tr>
        <td class="label" nowrap="nowrap" width="15%">
            <fmt:message key="kmFile.uploadTime" />
        </td>
        <td nowrap="nowrap" width="85%">
            从： <input type="text" size="20" readonly="readonly" class="text" 
                    name="starttime" id="starttime"
					onclick="popUpCalendar(this,this,null,null,null,true,-1);"
					alt="allowBlank:false,vtext:'请选择起始时间...'" value="${fileForm.starttime}" />
					&nbsp;&nbsp;
		   到： <input type="text" size="20" readonly="readonly" class="text"
					name="endtime" id="endtime"
					onclick="popUpCalendar(this,this,null,null,null,true,-1);"
					alt="allowBlank:false,vtext:'请选择截至时间...'" value="${fileForm.endtime}" />
					&nbsp;&nbsp;
        </td>
    </tr>
    
    <tr>
        <td class="label">
            <fmt:message key="kmFile.fileName" /><!-- 文件名 -->
        </td>
        <td class="content">
            <input type="text" id="fileName" name="fileName" class="text" maxlength="20" value="${fileForm.fileName}" alt="allowBlank:true" />
            <font color="red">（模糊匹配）</font>
        </td>
    </tr>
    
    <tr>
	    <td class="label">
		    <fmt:message key="kmFile.maker" /><!-- 创建者 -->					
		</td>
		<td class="content">
			<input type="text"   id="userName" name="userName" class="text" readonly="readonly" value="" alt="allowBlank:true"/>
			<input type="hidden" id="userId"  name="userId" value="${fileForm.userId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmFile.fileKeywords" /><!-- 关键字 -->					
		</td>
		<td class="content">
			<input type="text" name="keywords" id="keywords" value="${fileForm.keywords}" maxlength="10" class="text" alt="allowBlank:true" />
			<font color="red">（模糊匹配）</font>
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmFile.style" /><!-- 文件格式 -->
		</td>
		<td class="content">
			<select id="expand" name="expand" class="text medium">
			<option value=""><fmt:message key="kmFile.choice" /></option>
			<option value="txt">txt</option>
			<option value="jpg">jpg</option>
			<option value="doc">doc</option>
			<option value="xls">xls</option>
            </select>
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<fmt:message key="kmFile.sort" />
		</td>
		<td class="content">
	        <select id="timeSort" name="timeSort" class="text medium">
			<option value="desc"><fmt:message key="kmFile.descTime" /></option>
			<option value="asc"><fmt:message key="kmFile.ascTime" /></option>
			</select>
		</td>
	</tr>
</table>

<br>

<table>
    <tr>
	    <td>
	    	<input type="submit" class="btn" value="<fmt:message key="button.query"/>" />&nbsp;&nbsp;
			<input type="reset"  class="btn" value="<fmt:message key="button.reset"/>" />&nbsp;&nbsp;
		</td>
	</tr>
</table>

</fmt:bundle>
</html:form>

<br>
<font color="red">注意：查询模糊匹配字段会影响查询速度，建议使用时缩小查询时间段。</font>

<%@ include file="/common/footer_eoms.jsp"%>