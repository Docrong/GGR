<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
<script type="text/javascript" src="${app}/scripts/Sheet.js"></script>

<script type="text/javascript">
Ext.onReady(function(){
	 v = new eoms.form.Validation({form:'theform'});
});


//保存按钮
function saveDealInfo(){
    var form = document.forms[0];
   	var ajaxForm = Ext.getDom(form);
   	if(v.check()){
	    Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "${app}/sheet/${module}/${module}.do?method=newPerformSaveInfo&dealTemplateId=${dealTemplateId}",
					success: function(){
			        	alert("<bean:message bundle="sheet" key="button.saveDealInfo.success"/>");
			 		}
			    });
		v.passing=false;
	}
}

//保存处理环节中的模板
function saveDealTemplate(type) {
   var form = document.forms[0];
   var ajaxForm = Ext.getDom(form);
   if(v.check()){
   		if (confirm("<bean:message bundle="sheet" key="template.save.alert"/>")) {
		    if (type == "new"){
			   	Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "${module}.do?method=newSaveDealTemplate&operateType=${operateType}",
					success: function(){
			        	alert('<bean:message bundle="sheet" key="template.save.success"/>');
			 		}
			    });
		   	}else {
		   		Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "${module}.do?method=newSaveDealTemplate&operateType=${operateType}&dealTemplateId=${dealTemplateId}",
					success: function(){
			        	alert('<bean:message bundle="sheet" key="template.save.success"/>');
			 		}
			    });
		 	}
		 }
	v.passing=false;
 }
}

//提交后，抄送的业务逻辑处理
function isCopy() {
  try{
	  var ope= '${operateType}';
	  if(ope == '11' || ope == '55' || ope == '4') {
	   	$('hasNextTaskFlag').value = 'true';  
	  } 
	  
	  var str = document.getElementById("copyPerformer");
	  if (str) {
	  	str = str.value;
	  	if ("${taskName}" == "cc" && str == "") {
	  		$('hasNextTaskFlag').value = 'true';  
	  	}
	  }
  } catch(e){}
}

</script>
