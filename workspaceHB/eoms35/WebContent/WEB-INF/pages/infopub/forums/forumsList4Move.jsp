<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
	<script type="text/javascript">
Ext.onReady(function(){
var treeAction='${app}/workbench/infopub/forums.do?method=getNodes4query';
	  var config4 = {
		btnId:'forumsName',
		dlgId:'dlg4',
		treeDataUrl:treeAction,
		treeRootId:'-1',
		treeRootText:'<bean:message key='forumsTree.title.choose'/>',
		treeChkMode:'single',
		treeChkType:'forums',
		showChkFldId:'forumsName',
		saveChkFldId:'toForumsId'
	}
	var t4 = new xbox(config4);
});
</script>
<content tag="heading">
<bean:message key="forumsList4Move.heading" />
</content>
<html:form action="/forums.do" method="post" >
	<input type="hidden" name="forumsId" value="${forumsId }"/>
	
	<html:submit styleClass="button" property="method.move"
				onclick="javascript:var o=$('toForumsId');if(o.value=='')o.value=-1;">
	<bean:message key='forumsList4Move.button.move'/>
	</html:submit>	
	
	<input type="text" id="forumsName" name="forumsName" class="text" readonly="readonly" value="<bean:message key='forumsTree.title.choose'/>"/>&nbsp;&nbsp;<bean:message key='forumsForm.tips.move'/>
	<html:hidden property="toForumsId" styleId="toForumsId" value="-1"/>
	
	<!-- 
	<logic:notEmpty name="forums">
		<html:select property="toForumsId">
			<html:option value="-1"><bean:message key='forumsForm.parentId'/></html:option>
			<html:options collection="forums" property="id" labelProperty="title" />
		</html:select>
		
	</logic:notEmpty>
	 -->
	
			
</html:form>



<%@ include file="/common/footer_eoms.jsp"%>
