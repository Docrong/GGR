<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
 switcher = new detailSwitcher();
  switcher.init({
	container:'showInvokeRelationShipList',
  	handleEl:'div.history-item-title'
  });
</script>

<div id="showInvokeRelationShipList" class="panel"> 
     <div class="history-item" width="100%" ><!-- add space to hack ie-->&nbsp;
       <div class="history-item-content">
  		 <table class="listTable" width="100%" cellpadding="0" cellspacing="0">
  				<tr>
  				  <td class="label">
  				    <bean:message bundle="sheet" key="process.invoke.list.sheetSubject"/>
  				  </td>
  				  <td class="label">
  				 	<bean:message bundle="sheet" key="process.invoke.list.invokedSheetSubject"/>
                  </td>
                  <td class="label" >
  				    <bean:message bundle="sheet" key="process.invoke.list.sheetName"/>
  				  </td>
                  <td class="label" >
  				  	<bean:message bundle="sheet" key="task.sheetId"/>
                  </td>
                  <td class="label" >
  				  	<bean:message bundle="sheet" key="task.status"/>
                  </td>
                  <td class="label" >
  				  	<bean:message bundle="sheet" key="process.invoke.list.invokeStep"/>
                  </td>
  				</tr>
  	<logic:present name="showInvokeRelationShipList">
	 </logic:present> 
  			</table>
  		  </div>
	    </div>

</div>
