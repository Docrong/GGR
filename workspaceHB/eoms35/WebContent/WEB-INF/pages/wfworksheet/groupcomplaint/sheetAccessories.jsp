<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
 switcher = new detailSwitcher();
  switcher.init({
	container:'sheetAccessories',
  	handleEl:'div.history-item-title'
  });
</script>

<div id="sheetAccessories" class="panel"> 
     <div class="history-item" width="100%" ><!-- add space to hack ie-->&nbsp;
	   
       <div class="history-item-content">
  		 <table class="listTable" width="100%" cellpadding="0" cellspacing="0">
  				<tr>
  				  <td  class="label" nowrap="nowrap">
  				    <bean:message bundle="sheet" key="tawCommonsAccessoriesConfigForm.list.operatioType" />
  				  </td>
  				  <td class="label content" nowrap="nowrap">
  				 	<bean:message bundle="sheet" key="tawCommonsAccessoriesConfigForm.list.name" />
                  </td>
                  <td class="label" nowrap="nowrap">
  				    <bean:message bundle="sheet" key="tawCommonsAccessoriesConfigForm.list.uploadUser" />
  				  </td>
  				  <td class="label content" nowrap="nowrap">
  				  	 <bean:message bundle="sheet" key="tawCommonsAccessoriesConfigForm.list.uploadDate" />
                  </td>
  				</tr>
  	<logic:present name="sheetAccessories">
  	 <logic:iterate id="tawCommonsAccessoriesForm" name="sheetAccessories" type="com.boco.eoms.commons.accessories.webapp.form.TawCommonsAccessoriesForm" scope="request">
  				<tr>
  				  <td >
				       <eoms:dict key="dict-sheet-groupcomplaint" dictId="activeTemplateId" itemId="${tawCommonsAccessoriesForm.activeTemplateId}" beanId="id2descriptionXML" />  
  				  </td>
  				  <td>
  				    	<a href="${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=${tawCommonsAccessoriesForm.id}">${tawCommonsAccessoriesForm.accessoriesCnName}</a>
  				  </td>
  				  <td>
  				     <eoms:id2nameDB id="${tawCommonsAccessoriesForm.accessoriesUploader}" beanId="tawSystemUserDao"/>
  				  </td>
  				  <td>
  				     <bean:write name="tawCommonsAccessoriesForm" property="accessoriesUploadDate" format="yyyy-MM-dd HH:mm:ss"/>
                  </td>          
  				</tr>
	  </logic:iterate>	
	 </logic:present> 
  			</table>
  		  </div>
	    </div>

</div>