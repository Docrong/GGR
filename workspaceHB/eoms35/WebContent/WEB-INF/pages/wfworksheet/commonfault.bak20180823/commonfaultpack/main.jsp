<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
	var roleTree;
	var v;

	function initPage(){
		 v = new eoms.form.Validation({form:'theform'});
		 $('btns').show();   
	}
   Ext.onReady(function(){
    //showPage();
    var tabs = new Ext.TabPanel('main');
	tabs.addTab('sheetform', "<bean:message bundle="commonfaultpack" key="commonfaultpack.header"/>");
	tabs.activate('sheetform');     
    var el = Ext.get("idSpecial");
	var mgr = el.getUpdateManager();
	mgr.loadScripts = true;
	if ('${templateId}'!= null && '${templateId}' != "") {
		mgr.update("${app}/sheet/commonfault/commonfaultpack.do?method=showTemplateInputSheetPage&templateId=${templateId}");
	} else {
		mgr.update("${app}/sheet/commonfault/commonfaultpack.do?method=showNewInputSheetPage");
	}
	mgr.on("update", initPage);
   });
   
</script>

<div id="sheetform" class="tabContent">
    <html:form
	action="/commonfaultpack.do?method=performAdd" styleId="theform">
	<input type="hidden" name="${sheetPageName}mainId"
		id="${sheetPageName}mainId" value="${mainId}" />
	<div ID="idSpecial"></div> 
	<div class="form-btns" id="btns" style="display:none"><html:submit
		styleClass="btn" property="method.save" styleId="method.save">
		<bean:message bundle="sheet" key="button.save" />
	</html:submit>
	<input type = "button" value="${eoms:a2u('返回')}" class="button"  onclick="canCel();">
	</div>

</html:form></div>
<script language="javascript">
function canCel()
{
  window.location="${app}/sheet/commonfault/commonfaultpack.do?method=showOwnStarterList&mainId=${mainId}&alarmMethod=new";
}
</script>
<%@ include file="/common/footer_eoms.jsp"%>