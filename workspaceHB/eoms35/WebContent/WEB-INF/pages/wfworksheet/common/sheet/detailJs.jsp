<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	var tabConfig = {
		items : [{ 
			id   : 'sheetinfo',
			text : '<bean:message bundle="sheet" key="sheet.sheetInfo"/>'
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
	
	var tabs = new eoms.TabPanel('sheet-detail-page', tabConfig);
	
	//进入详细查看页面后自己加载流程处理环节中的步骤
	eoms.Sheet.setPageLoader("dealSelector","sheet-deal-content");
	var url = "";
	try{
		url = $("dealSelector").value + "&taskStatus=${taskStatus}";
	}catch(e){}
		
	var taskName = "${taskName}";
	var entryAdmin = "${entryAdmin}";
	var taskStatus = "${taskStatus}";
	//如果是从管理者入口进入的时候，不要加载
	if (taskName == 'cc' || taskName == 'reply' || taskName == 'advice' || entryAdmin == 'true') {
	} else {
		eoms.util.appendPage("sheet-deal-content",url);	
	}
	
	//阶段通知后的已阅，回复和抄送界面
	if (taskStatus == "2" || taskStatus == "3" || taskStatus == "8") {
		if (taskName == "cc") {
			var url2="${module}.do?method=showCCPageNew&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
			eoms.util.appendPage("sheet-deal-content",url2);	
		}
		if (taskName == "reply") {
			var url2="${module}.do?method=newShowRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
			eoms.util.appendPage("sheet-deal-content",url2);	
		}
		if (taskName == "advice") {
			var url2="${module}.do?method=newShowRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
			eoms.util.appendPage("sheet-deal-content",url2);	
		}
	}
});


//强制归档，强制作废
function forceOperation(obj){
	if(obj == 1){
		if (confirm('<bean:message bundle="sheet" key="button.forceHold.alert"/>')) {
		    var url2="${module}.do?method=newShowForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
		    eoms.util.appendPage("sheet-deal-content",url2); 
		}
	} else if(obj == 2){
		if (confirm('<bean:message bundle="sheet" key="button.forceHold.alert"/>')) {
		     var url2="${module}.do?method=newShowForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
		     eoms.util.appendPage("sheet-deal-content-self-force",url2,true);
	    }
	} else {
		if (confirm('<bean:message bundle="sheet" key="button.forceNullity.alert"/>')) {
	         var url2="${module}.do?method=newShowForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
		     eoms.util.appendPage("sheet-deal-content",url2);
	     }
    }
}

//阶段建议
function eventOperation(obj){
	if(obj == 1){
	     var url2="${module}.do?method=newShowPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
         eoms.util.appendPage("sheet-deal-content-self-force",url2,true);
	}
 }
 	
function loadDealTemplate(url) {
  eoms.util.appendPage("sheet-deal-content",url,true);  
}
	
</script>
