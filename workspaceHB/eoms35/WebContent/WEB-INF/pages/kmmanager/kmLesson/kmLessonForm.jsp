<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmLessonForm'});
});

</script>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
*号为必填内容

<html:form action="/kmLesson.do?method=save" styleId="kmLessonForm" method="post">	    
<table class="formTable">
    <caption>
        <div class="header center">
            <fmt:message key="kmLesson.form.heading" />
        </div>
    </caption>
    
    <tr>
      <td class="label">
          <fmt:message key="kmLesson.lessonName" />&nbsp;<font color='red'>*</font>
      </td>
      <td class="content" colspan=3>
          <html:text property="lessonName" styleId="lessonName" 
                     styleClass="text max" maxlength="30" 
					 alt="allowBlank:false,vtext:'请输入课题名称...'" value="${kmLessonForm.lessonName}" />
      </td>
    </tr>

    <tr>
      <td class="label">
          <fmt:message key="kmLesson.lessonTheme" />
      </td>
      <td class="content" colspan=3>
          <html:text property="lessonTheme" styleId="lessonTheme"
			         styleClass="text max" maxlength="30"
					 alt="allowBlank:true,vtext:'请输入主题...'" value="${kmLessonForm.lessonTheme}" />
      </td>
    </tr>

    <tr>
      <td class="label">
          <fmt:message key="kmLesson.lessonClass" />&nbsp;<font color='red'>*</fon
      </td>
      <td class="content" colspan=3>
          <html:text property="lessonClass" styleId="lessonClass"
			         styleClass="text max" maxlength="30"
					 alt="allowBlank:false,vtext:'请输入业务类别...'" value="${kmLessonForm.lessonClass}" />
      </td>
    </tr>

    <tr>
      <td class="label">
          <fmt:message key="kmLesson.startTime" />&nbsp;<font color='red'>*</fon
      </td>
      <td class="content">
          <input type="text" size="20" readonly="readonly" class="text" 
                 name="startTime" id="startTime"
                 onclick="popUpCalendar(this,this,null,null,null,true,-1);"
                 alt="allowBlank:false,vtext:'请选择开始时间...'" value="${kmLessonForm.startTime}" />
      </td>

      <td class="label">
          <fmt:message key="kmLesson.endTime" />&nbsp;<font color='red'>*</fon
      </td>
      <td class="content">
          <input type="text" size="20" readonly="readonly" class="text" 
                 name="endTime" id="endTime"
                 onclick="popUpCalendar(this,this,null,null,null,true,-1);"
                 alt="allowBlank:false,vtext:'请选择结束时间...'" value="${kmLessonForm.endTime}" />
      </td>
    </tr>

    <tr>
      <td class="label">
          <fmt:message key="kmLesson.lessonContents" />&nbsp;<font color='red'>*</fon
      </td>
      <td class="content" colspan=3>
          <textarea name="lessonContents" id="lessonContents" cols="50" class="textarea max" alt="allowBlank:false,vtext:'请填写课题内容...'" >${kmLessonForm.lessonContents}</textarea>
      </td>
    </tr>

    <tr>
      <td class="label">
          <fmt:message key="kmLesson.attachment" />
      </td>
      <td class="content" colspan=3>
          <eoms:attachment name="kmLessonForm" property="attachment" scope="request" idField="attachment" appCode="kmmanager" />
      </td>
    </tr>
</table>

<table>
    <tr>
	    <td>
	        <input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
		</td>
	</tr>
</table>

<html:hidden property="id" value="${kmLessonForm.id}" />
<html:hidden property="timeLength" value="0" />
<html:hidden property="isDelete" value="0" />

</html:form>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp"%>