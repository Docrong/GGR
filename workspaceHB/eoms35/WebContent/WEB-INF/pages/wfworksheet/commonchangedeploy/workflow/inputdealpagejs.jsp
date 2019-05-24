<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
//增加自己环节中的JS
function executeResult(input){
		if(input=="101090301"){
			document.all.${sheetPageName}linkFailedReason.disabled=true;
			document.all.${sheetPageName}linkFailedReason.value='';
		}else{
			document.all.${sheetPageName}linkFailedReason.disabled=false;
		}
	}
function executeDemonstrateCase(input){
		if(input=="1030102"){
			document.all.${sheetPageName}linkCasesMainKey.disabled=true;
			document.all.${sheetPageName}linkCasesMainKey.value='';
		}else{
			document.all.${sheetPageName}linkCasesMainKey.disabled=false;
		}
	}
function executeEffectBusiness(input){
		if(input=="1030102"){
			document.all.${sheetPageName}linkEffectCondition.disabled=true;
			document.all.${sheetPageName}linkEffectCondition.value='';
		}else{
			document.all.${sheetPageName}linkEffectCondition.disabled=false;
		}
	}
</script> 