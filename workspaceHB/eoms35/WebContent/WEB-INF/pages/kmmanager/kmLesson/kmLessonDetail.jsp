<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmLessonForm'});
});

//修改
function onSubmitEdit(){
    var id = document.getElementById("id").value;
    var url='${app}/kmmanager/kmLesson.do?method=edit&id=' + id ;
    window.location.href(url);
    //alert(url);
}
    
//删除
function onSubmitDele(){
    var id = document.getElementById("id").value;
    var url='${app}/kmmanager/kmLesson.do?method=remove&id=' + id ;
    window.location.href(url);
    //alert(url);    
}
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
          ${kmLessonForm.lessonName}
      </td>
    </tr>

    <tr>
      <td class="label">
          <fmt:message key="kmLesson.lessonTheme" />
      </td>
      <td class="content" colspan=3>
          ${kmLessonForm.lessonTheme}
      </td>
    </tr>

    <tr>
      <td class="label">
          <fmt:message key="kmLesson.startTime" />&nbsp;<font color='red'>*</fon
      </td>
      <td class="content">
          ${kmLessonForm.startTime}
      </td>

      <td class="label">
          <fmt:message key="kmLesson.endTime" />&nbsp;<font color='red'>*</fon
      </td>
      <td class="content">
          ${kmLessonForm.endTime}
      </td>
    </tr>

    <tr>
      <td class="label">
          <fmt:message key="kmLesson.lessonClass" />&nbsp;<font color='red'>*</fon
      </td>
      <td class="content">
          ${kmLessonForm.lessonClass}
      </td>
      <td class="label">
          <fmt:message key="kmLesson.timeLength" />&nbsp;<font color='red'>*</fon
      </td>
      <td class="content">
          ${kmLessonForm.timeLength}&nbsp;小时
      </td>      
    </tr>

    <tr>
      <td class="label">
          <fmt:message key="kmLesson.lessonContents" />&nbsp;<font color='red'>*</fon
      </td>
      <td class="content" colspan=3>
          <textarea cols="50" class="textarea max" readonly="readonly">${kmLessonForm.lessonContents}</textarea>
      </td>
    </tr>

    <tr>
      <td class="label">
          <fmt:message key="kmLesson.attachment" />
      </td>
      <td class="content" colspan=3>
          <eoms:attachment name="kmLessonForm" property="attachment" scope="request" idField="attachment" appCode="kmmanager" viewFlag="Y"/>
      </td>
    </tr>
</table>

<!-- 只有作者才能修改和删除 -->
<c:if test="${kmLessonForm.createUser == sessionScope.sessionform.userid}">
<table>
    <tr>
	    <td>
	        <input type="button" class="btn" value="修改" onclick="javascript:onSubmitEdit();"/>&nbsp;
	        <input type="button" class="btn" value="删除" onclick="javascript:onSubmitDele();"/>&nbsp;
		</td>
	</tr>
</table>
</c:if>

<html:hidden property="id" value="${kmLessonForm.id}" />
<html:hidden property="isDelete" value="0" />

</html:form>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp"%>