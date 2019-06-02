<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
String jsonString = request.getAttribute("jsonString").toString();
%>
  <script language="javascript">
	  	Ext.onReady(function(){
	var	userTreeAction='${app}/msg/smsApplys.do?method=getNodes';
	userViewer = new Ext.JsonView("user-list",	
		'<div id="user-{id}" class="viewlistitem-user">{name}</div>',
		{ 
			multiSelect: true,		
			emptyText : '<div><bean:message key="smsApply.chooseservice"/></div>'								
		}
	);
	var s = '<%=jsonString%>';
	userViewer.jsonData = eoms.JSONDecode(s);
	userViewer.refresh();
	
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',	
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'<bean:message key="smsService.servicename"/>',treeChkMode:'',treeChkType:'smsService',
		viewer:userViewer,saveChkFldId:'servicesId',returnJSON:true  
	});
})
  </script>
    <html:form action="/smsApplys.do?method=xsaveApply" method="post" styleId="smsApplyForm"> 
    	<table>
    		
    		<tr>	    		
	    		<td class="label"> <div id="user-list" class="viewer-list"></div>
	    		<input type="button" value="<bean:message key='smsService.modifyservicelist'/>" id="userTreeBtn" class="btn"/>
				</td>
			</tr>
			<br/>
    		<tr>
    			<td>
    				<input type="submit" class="button" value="<bean:message key="button.save"/>"/>
    			</td>
    		</tr>
    	</table> 
	<input type="hidden" id="servicesId" name="servicesId"/>  	
    </html:form>
    <%@ include file="/common/footer_eoms.jsp"%>
