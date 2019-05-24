<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
<script type="text/javascript">
	var roleTree;
	var v;
	
	//检查表单验证
	function initPage(){
		 v = new eoms.form.Validation({form:'theform'});  
		  $('btns').show(); 	
	}
	
   Ext.onReady(function(){
   		var draft = "${draft}",reject = "${reject}", tabConfig;
	    if (draft == "true" || reject == "true") {
				tabConfig = {
					items : [{
						id   : 'sheetform',
						text : '<bean:message bundle="${module}" key="${module}.headerDraft"/>'
					}, {
						text : '<bean:message bundle="sheet" key="sheet.historyView"/>',
						url  : '${module}.do?method=newShowSheetDealList&sheetKey=${sheetMain.id}'
					}, {
						text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
						url  : '${module}.do?method=newShowWorkFlow&linkServiceName=${linkServiceName}&dictSheetName=${dictSheetName}&description=mainOperateType&sheetKey=${sheetMain.id}',
						isIframe : true
					}, {
						text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
						url  : '${module}.do?method=newShowSheetAccessoriesList&id=${sheetMain.id}',
						isIframe : true
					}, {
						text : '<bean:message bundle="sheet" key="sheet.allSheetsView"/>',
				 		url  : '../sheetRelation/sheetRelation.do?method=showInvokeRelationShipList&sheetKey=${sheetMain.id}',
						isIframe : true
					}]
				};
		} else {
			  tabConfig = {
				items : [{
						id   : 'sheetform',
						text : '<bean:message bundle="${module}" key="${module}.header"/>'
					}]
				};
		}
		var tabs = new eoms.TabPanel('main', tabConfig);
		initPage();
	});
	
	
   //公共保存模板(包括新建和保存当前模板)
  function saveTemplate(type) {
   	var form = document.getElementById("theform");
    var ajaxForm = Ext.getDom(form);
   	var templateManage = "${type}";
    if(v.check()){
	   	if (confirm("<bean:message bundle='sheet' key='template.confirmSaveTemplate'/>")) {
	   		v.passing = false; 
		   	if (templateManage == "templateManage") {
		   		form.action = "${app}/sheet/${module}/${module}.do?method=newSaveTemplate&templateId=${templateId}&type=${type}";
		   		form.submit();
		   	} else {
			   	if (type == "new"){
				   	Ext.Ajax.request({
				   		form: ajaxForm,
						method:"post",
						url: "${module}.do?method=newSaveTemplate&type=${type}",
						success: function(){
				        	alert("<bean:message bundle='sheet' key='template.saveTemplateSuccess'/>");
				 		}
				    });
			   	}else {
			   		Ext.Ajax.request({
				   		form: ajaxForm,
						method:"post",
						url: "${module}.do?method=newSaveTemplate&templateId=${templateId}&type=${type}",
						success: function(){
				        	alert("<bean:message bundle='sheet' key='template.saveTemplateSuccess'/>");
				 		}
				    });
			   	}
		   	}
	   	} else {
	   		v.passing = false; 
	   	}
	 }
  }
  
  //删除模板
  function removeTemplate() {
   if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
		var thisform = document.forms[0];
		thisform.action = "${app}/sheet/${module}/${module}.do?method=removeTemplate&templateId=${templateId}&type=${type}";
		thisform.submit();
	}
 }
 
 //保存草稿
 function saveDraft() {
 	var thisform = document.getElementById("theform");
 	thisform.action = "${app}/sheet/${module}/${module}.do?method=newSaveDraft";
 }
 
//保存按钮功能
 function saveInfo() {
 	var thisform = document.getElementById("theform");
 	thisform.action = "${app}/sheet/${module}/${module}.do?method=newSaveMainSheet";
 }
 
</script>