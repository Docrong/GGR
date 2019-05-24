<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script language="javascript">
function toBack(){
		window.location="<%=request.getContextPath() %>/statistic/customstat/stSubscriptions.do?method=reback"
	}
</script>
<title></title>
<html:base/>



<html:form action="/stSubscriptions.do" method="post" styleId="stSubscriptionForm"> 
<body>
    <br>
	  <table border="0" width="80%" cellspacing="0" align="center">
	    <tr>
		  <td colspan="2" align="center">
				<h2>
					<bean:message key="stSubscriptionList.heading" />
				</h2>
				
			</td>
		</tr>
	  </table>
	   <html:hidden property="id"/>
	  
	  <table class="formTable middle" align="center">
	     
	    
	    <tr class="tr_show">
          <td width="20%" height="25" class="clsfth">
         <bean:message key='stSubscriptionForm.subId'/>
             </td> 
	  	     <td width="80%" height="25" class="clsfth">
	  	     
	  	      <html:text   property="subId" styleClass="clstext" readonly="true"/>
		  </td>
        
		</tr>
		 
	    <tr class="tr_show">
          <td width="20%" height="25" class="clsfth">
         <bean:message key='stSubscriptionForm.subscribeDept'/>
             </td> 
	  	     <td width="80%" height="25" class="clsfth">
	  	     
	  	      <html:text  property="subscribeDept" styleClass="clstext" readonly="true"/>
		  </td>
        
		</tr>
		 
	    <tr class="tr_show">
          <td width="20%" height="25" class="clsfth">
         <bean:message key='stSubscriptionForm.subscriber'/>
             </td> 
	  	     <td width="80%" height="25" class="clsfth">
	  	     
	  	      <html:text   property="subscriber" styleClass="clstext" readonly="true"/>
		  </td>
        
		</tr>
		
		 
	    <tr class="tr_show">
          <td width="20%" height="25" class="clsfth">
         <bean:message key='stSubscriptionForm.itemName'/>
             </td> 
	  	     <td width="80%" height="25" class="clsfth">
	  	     
	  	      <html:text  property="item" styleClass="clstext" readonly="true"/>
		  </td>
        
		</tr>
		
		 
	    <tr class="tr_show">
          <td width="20%" height="25" class="clsfth">
         <bean:message key='stSubscriptionForm.statMode'/>
             </td> 
	  	     <td width="80%" height="25" class="clsfth">
	  	     
	  	      <html:text  property="statMode" styleClass="clstext" readonly="true"/>
		  </td>
        
		</tr>
		
		 
	    
		
		 
	    <tr class="tr_show">
          <td width="20%" height="25" class="clsfth">
         <bean:message key='stSubscriptionForm.subscribeTime'/>
             </td> 
	  	     <td width="80%" height="25" class="clsfth">
	  	     
	  	      <html:text  property="subscribeTime" styleClass="clstext" />
		  </td>
        
		</tr>
		  <tr class="tr_show">
          <td width="20%" height="25" class="clsfth">
         <bean:message key='stSubscriptionForm.cycle'/>
             </td> 
	  	     <td width="80%" height="25" class="clsfth">
	  	     
	  	      <html:text  property="cycle" styleClass="clstext" />
		  </td>
        
		</tr>
		  <tr class="tr_show">
          <td width="20%" height="25" class="clsfth">
         <bean:message key='stSubscriptionForm.type'/>
             </td> 
	  	     <td width="80%" height="25" class="clsfth">
	  	     
	  	      <html:text  property="type" styleClass="clstext" />
		  </td>
        
		</tr>
		  <tr class="tr_show">
          <td width="20%" height="25" class="clsfth">
         <bean:message key='stSubscriptionForm.statfromdate'/>
             </td> 
	  	     <td width="80%" height="25" class="clsfth">
	  	     
	  	      <html:text property="statfromdate" styleClass="clstext" readonly="true"/>
		  </td>
        
		</tr>
		  <tr class="tr_show">
          <td width="20%" height="25" class="clsfth">
         <bean:message key='stSubscriptionForm.stattodate'/>
             </td> 
	  	     <td width="80%" height="25" class="clsfth">
	  	     
	  	      <html:text  property="stattodate" styleClass="clstext" readonly="true"/>
		  </td>
        
		</tr>
		<tr class="tr_show">
          <td width="20%" height="25" class="clsfth">
         <bean:message key='stSubscriptionForm.className'/>
             </td> 
	  	     <td width="80%" height="25" class="clsfth">
	  	     
	  	      <html:textarea rows="2" cols="50"  property="className" styleClass="clstext" />
		  </td>
        
		</tr>
			 <tr class="tr_show">
          <td width="40%" height="25" class="clsfth">
         <bean:message key='stSubscriptionForm.remark'/>
             </td> 
	  	     <td width="60%" height="25" class="clsfth">
	  	     
	  	      <html:textarea rows="12" cols="50"  property="remark" styleClass="clstext" />
		  </td>
        
		</tr>
          <td colspan="2" align="center">
    
          
			 <input  class="button" type="button" name="edite" value="<bean:message key="button.save"/>" onclick="toReback();"/>
		 		&nbsp;&nbsp;
				<html:reset styleClass="button">
					<fmt:message key="button.reset" />
				</html:reset>
				&nbsp;&nbsp;
				<input name="button" type="button" value="${eoms:a2u('返回')}" class="button" onclick="toBack();"/>
          </td>
		</tr>
      </table>
  </body>
</html:form>

<script language="javascript">
 
  function toSave(){
    
	window.document.forms[0].action="/statistic/customstat/stSubscriptions.do?method=xsave";
	window.document.forms[0].submit();
	
  }
  function toReback(){
    window.document.forms[0].action="<%=request.getContextPath() %>/statistic/customstat/stSubscriptions.do?method=xedit";
    	window.document.forms[0].submit();
  }
 function toSaveReport(){
    alert("if you want to report,please press submit!");
	window.document.forms[0].action="/statistic/customstat/stSubscriptions.do?method=report";
	window.document.forms[0].submit();
  }
     
</script>

<%@ include file="/common/footer_eoms.jsp"%>
