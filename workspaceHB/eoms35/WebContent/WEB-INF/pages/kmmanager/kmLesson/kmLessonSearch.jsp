<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmLessonForm'});
	
	new xbox({
        btnId: 'userName',
        dlgId: 'showuser-dlg',
        treeDataUrl: '${app}/xtree.do?method=userFromDept',
        treeRootId: '-1',
        treeRootText: '人员列表',
        treeChkMode: 'single',
        treeChkType: 'user',
        showChkFldId: 'userName',
        saveChkFldId: 'createUser'
    });
});
</script>

<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<html:form action="/kmLesson.do?method=searchDo" styleId="kmLessonForm" method="post">	
<table class="formTable">
	<caption>
		<div class="header center">
			<b><fmt:message key="kmLesson.list.heading" /></b>
		</div>
	</caption>

    <tr>
        <td class="label" nowrap="nowrap" width="15%">
            <fmt:message key="kmLesson.createTime" />&nbsp;<font color='red'>*</font>
        </td>
        <td nowrap="nowrap" width="85%">
            从： <input type="text" size="20" readonly="readonly" class="text" 
                    name="startTime" id="startTime"
					onclick="popUpCalendar(this,this,null,null,null,true,-1);"
					alt="allowBlank:false,vtext:'请选择起始时间...'" value="${kmLessonForm.startTime}" />
					&nbsp;&nbsp;
		   到： <input type="text" size="20" readonly="readonly" class="text"
					name="endTime" id="endTime"
					onclick="popUpCalendar(this,this,null,null,null,true,-1);"
					alt="allowBlank:false,vtext:'请选择截至时间...'" value="${kmLessonForm.endTime}" />
					&nbsp;&nbsp;
        </td>
    </tr>

    <tr>
	    <td class="label">
		    <fmt:message key="kmLesson.createUser" /><!-- 创建者 -->					
		</td>
		<td class="content">
			<input type="text"   id="userName" name="userName" class="text" readonly="readonly" value="" alt="allowBlank:true"/>
			<input type="hidden" id="createUser"  name="createUser" value="${kmLessonForm.createUser}" />
		</td>
	</tr>

    <tr>
      <td class="label">
          <fmt:message key="kmLesson.lessonName" />
      </td>
      <td class="content">
          <html:text property="lessonName" styleId="lessonName" 
                     styleClass="text" maxlength="30" 
					 alt="allowBlank:true,vtext:'请输入课题名称...'" value="${kmLessonForm.lessonName}" />
		  <font color="red">（模糊匹配）</font>					 
      </td>
    </tr>

    <tr>
      <td class="label">
          <fmt:message key="kmLesson.lessonTheme" />
      </td>
      <td class="content">
          <html:text property="lessonTheme" styleId="lessonTheme"
			         styleClass="text" maxlength="30"
					 alt="allowBlank:true,vtext:'请输入主题...'" value="${kmLessonForm.lessonTheme}" />
		  <font color="red">（模糊匹配）</font>
      </td>
    </tr>

    <tr>
      <td class="label">
          <fmt:message key="kmLesson.lessonClass" />
      </td>
      <td class="content">
          <html:text property="lessonClass" styleId="lessonClass"
			         styleClass="text" maxlength="30"
					 alt="allowBlank:true,vtext:'请输入业务类别...'" value="${kmLessonForm.lessonClass}" />
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

</html:form>
</fmt:bundle>

<br>
<font color="red">注意：查询模糊匹配字段会影响查询速度，建议使用时缩小查询时间段。</font>

<%@ include file="/common/footer_eoms.jsp"%>