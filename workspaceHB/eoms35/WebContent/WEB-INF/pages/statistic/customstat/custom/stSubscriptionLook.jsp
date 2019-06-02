<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<fmt:bundle basename="config/statistic/customstat-config/ApplicationResources-custom">
<title>
</title>
</fmt:bundle>
<html:base/>


<html:form action="/stSubscriptions.do" method="post" styleId="stSubscriptionForm"> 
<table class="formTable middle" align="center">
			 <html:hidden property="id"/>
             
           
		<tr>
				<td colspan="2" align="center" class="label">
				
						<b><bean:message key="stSubscriptionForm.head" /></b>
					
				</td>
			</tr>
    
    	<tr>
    		<td class="label" nowrap="nowrap" align="right" width="40%">
    			<bean:message key='stSubscriptionForm.subId'/>
    		</td>
    		<td width="60%">	     
    	      <bean:write name="stSubscriptionForm" property="subId"   />
    	      </td>
    	</tr> 	
    	<tr>
    		<td class="label" nowrap="nowrap" align="right" width="40%">
    			<bean:message key='stSubscriptionForm.subscribeDept'/>
    		</td>
    		<td width="60%">	  
    		<eoms:id2nameDB id="${stSubscriptionForm.subscribeDept}"
					beanId="tawSystemDeptDao" />   
    	      </td>
    	</tr> 	
    	<tr>
    		<td class="label" nowrap="nowrap" align="right" width="40%">
    			<bean:message key='stSubscriptionForm.subscriber'/>
    		</td>
    		<td width="60%">	 
    		<eoms:id2nameDB id="${stSubscriptionForm.subscriber}"
					beanId="tawSystemUserDao" />     
    	      </td>
    	</tr> 	
    	<tr>
    		<td class="label" nowrap="nowrap" align="right" width="40%">
    			<bean:message key='stSubscriptionForm.itemName'/>
    		</td>
    		<td width="60%">	     
    	      <bean:write name="stSubscriptionForm" property="item"   />
    	      </td>
    	</tr> 	
    <tr>
    		<td class="label" nowrap="nowrap" align="right" width="40%">
    			<bean:message key='stSubscriptionForm.statMode'/>
    		</td>
    		<td width="60%">	     
    	      <bean:write name="stSubscriptionForm" property="itemName"   />
    	      </td>
    	</tr> 	
    <tr>
    	
    		<td class="label" nowrap="nowrap" align="right" width="40%">
    			<bean:message key='stSubscriptionForm.className'/>
    		</td>
    		<td width="60%">	     
    	      <bean:write name="stSubscriptionForm" property="className"   />
    	      </td>
    	
    </tr>
    	 <tr>
    		<td class="label" nowrap="nowrap" align="right" width="40%">
    			<bean:message key='stSubscriptionForm.subscribeTime'/>
    		</td>
    		<td width="60%">	     
    	      <bean:write name="stSubscriptionForm" property="subscribeTime"   />
    	      </td>
    	</tr> 	
    
    	
    	<tr>
    	
    		<td class="label" nowrap="nowrap" align="right" width="40%">
    			<bean:message key='stSubscriptionForm.cycle'/>
    		</td>
    		<td width="60%">	     
    	      <bean:write name="stSubscriptionForm" property="cycle"   />
    	      </td>
    
  </tr>
    	<tr>
    	 
    		<td class="label" nowrap="nowrap" align="right" width="40%">
    			<bean:message key='stSubscriptionForm.type'/>
    		</td>
    		<td width="60%">	     
    	      <bean:write name="stSubscriptionForm" property="type"   />
    	      </td>
    	</tr> 	
    <tr>
    	
		<td class="label" nowrap="nowrap" align="right" width="40%">
    			<bean:message key='stSubscriptionForm.statfromdate'/>
    		</td>
    		<td width="60%">	     
    	      <bean:write name="stSubscriptionForm" property="statfromdate"   />
    	      </td>
    	</tr> 	
    	<tr>
    		<td class="label" nowrap="nowrap" align="right" width="40%">
    			<bean:message key='stSubscriptionForm.stattodate'/>
    		</td>
    		<td width="60%">	     
    	      <bean:write name="stSubscriptionForm" property="stattodate"   />
    	      </td>
    	</tr> 	
    
    
    	 <tr>
    		<td class="label" nowrap="nowrap" align="right" width="40%">
    			<bean:message key='stSubscriptionForm.remark'/>
    		</td>
    		<td width="60%">	     
    	      <bean:write name="stSubscriptionForm" property="remark"   />
    	      </td>
    	</tr> 	
    
    	
    	
      <tr>
    		<td align= "center" colspan="2" >             
       			<input type="button" class="button" value="<bean:message key="button.close"/>" onclick="window.close();"/>
    		</td>
		</tr> 
 </table>
</html:form>

<script language="javascript">
  
  function toSave(){
    
	window.document.forms[0].action="/statistic/customstat/stSubscriptions.do?method=xsave";
	window.document.forms[0].submit();
	
  }
  function toReback(){
    window.document.forms[0].action="/statistic/customstat/stSubscriptions.do?method=reback";
    	window.document.forms[0].submit();
  }
 function toSaveReport(){
    alert("if you want to report,please press submit!");
	window.document.forms[0].action="/statistic/customstat/stSubscriptions.do?method=report";
	window.document.forms[0].submit();
  }
     
</script>

<%@ include file="/common/footer_eoms.jsp"%>
