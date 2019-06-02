<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<script language="javascript">
function upload() {
	var specialty = document.getElementById("specialtyID").value;
	if (specialty == '') {
  		alert('请选择专业');
  		return false;
 	}
 	var name = document.forms[0].file.value;
 	if (name == '' || name.length <= 0) {
  		alert('文件不能为空'); 
  		return false;
 	}else if(document.getElementById("file").value.indexOf(".xls")<0){
    	alert('确认选择的文件为Excel');
    	return false;
 	}
  	document.forms[0].submit();
}
</script> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<form enctype="multipart/form-data" name="kmExamQuestionsForm" method="post"
			action="${app}/kmmanager/kmExamQuestionss.do?method=upload"   
			>
 <eoms:xbox id="tree" dataUrl="${app}/kmmanager/kmExamSpecialtys.do?method=getNodesRadioTree" 
	  	rootId="1" 
	  	rootText='试题分类' 
	  	valueField="specialtyID" handler="specialtyName"
		textField="specialtyName"
		checktype="forums" single="true"		
	  ></eoms:xbox>
	<fmt:message key="kmExamQuestions.specialtyID" />
	<input type="text"   id="specialtyName" name="specialtyName" class="text" readonly="readonly" value='<eoms:id2nameDB id="${kmExamQuestionsForm.specialtyID}" beanId="kmExamSpecialtyDao" />' alt="allowBlank:false'"/>
	<input type="hidden" id="specialtyID"   name="specialtyID" value="${kmExamQuestionsForm.specialtyID}" />
	<br>
	<br>
	请选择文件
	<input type="hidden" name="fileName" />
	<input name="file" type="file" /> <br><br>
	<input id="submitButton" type="button" value="导入" class="btn"  onclick="upload();" />
</form>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>