<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>
<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbLink">  
       <%         
        jNum += 1;       
        String divName ="buzhou"+jNum;
       %>
     <div class="history-item"><!-- add space to hack ie-->&nbsp;
       <div class="history-item-title">
		 <%=jNum%>.
		 <bean:write name="baselink" property="operateTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="page"/>&nbsp; 
  	     <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao" />	 
  	     <bean:message bundle="sheet" key="task.operateName"/>:
  	     <eoms:dict key="dict-sheet-techniquesupporthb" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
          <bean:message bundle="techniquesupporthb" key="techniquesupporthb.operateType"/>: 
  	     <eoms:dict key="dict-sheet-techniquesupporthb" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
  	     
		<img class="switchIcon" src="${app}/images/icons/closed.gif" align="absmiddle"/>
	   </div>
	   
       <div class="history-item-content hide">
         <c:if test="${taskName==baselink.activeTemplateId && ifWaitForSubTask=='false'&&(baselink.operateType=='11'||baselink.operateType=='55')}">
         <input type="checkbox" name="${sheetPageName}checkuse" id="${baselink.id}" value="${baselink.id}" onclick="copy(this.value);" />
         <bean:message bundle="sheet" key="common.copy"/>	     
  	     </c:if> 
  		 <table class="history-item-table" width="100%" cellpadding="0" cellspacing="0">
  				<tr>
  				  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateUserName"/>
  				  </td>
  				  <td class="column-content">
  				  <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao" />&nbsp;
                  </td>
                  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateDeptName"/>
  				  </td>
  				  <td class="column-content">
  	  				     <eoms:id2nameDB id="${baselink.operateDeptId}" beanId="tawSystemDeptDao" />&nbsp;
                  </td>
  				</tr>
  				<tr>
  <!-- 			<%if(baselink.getOperateRoleId()!=null && ! baselink.getOperateRoleId().equals("")) {%>
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
  				  </td>
  				  <td class="column-content">
  				    <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao" /> 
  				    <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao" />&nbsp;  				   
  			      </td>
  			     
  			      <%} %> -->	

                 <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateTime"/>
  				  </td>
  				  <td class="column-content">
  				     <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                  </td>                  
	      
  				 <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operaterContact"/>
  				  </td>
                   <td class="column-content">
  				     <bean:write name="baselink" property="operaterContact" scope="page"/>
                  </td>                 
  				</tr>
  		      
                <tr>
		  				  <td class="column-title">
                           <bean:message bundle="techniquesupporthb" key="techniquesupporthb.operateType"/>  
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:dict key="dict-sheet-techniquesupporthb" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
		                  </td>
		  				  <td class="column-title">
		  				     <bean:message bundle="sheet" key="linkForm.toOrgName"/>
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao"/>
  				           <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" />&nbsp;&nbsp;
		                  </td>		                  
		  		</tr>
		  		<%if(baselink.getNodeAcceptLimit()!= null && ! baselink.getNodeAcceptLimit().equals("")
		  		   &&baselink.getNodeCompleteLimit()!=null && ! baselink.getNodeCompleteLimit().equals("")) {%>
				<tr>
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.acceptLimit"/>
  				  </td>
  				  <td class="column-content">
  				       <bean:write name="baselink" property="nodeAcceptLimit" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
  			      </td>
  				  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.completeLimit"/>
  				  </td>
  				  <td class="column-content">
  				     <bean:write name="baselink" property="nodeCompleteLimit" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                  </td>                  
  				</tr>
                <%} %> 				
            <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'DoneImplTask')}">
            <c:if test="${!empty baselink.operateType && (baselink.operateType == '911'||baselink.operateType == '11')}">
                          <c:if test="${!empty baselink.workTime}">
                         <tr>  				
				  		    	<td class="column-title">
				  				 	工时
				  		    	</td>
				  		   	 	<td class="column-content" colspan="3">
		                          	<bean:write name="baselink" property="workTime" scope="page"/>
				            	</td>   	                  
				  		 </tr>
				  		 </c:if>
                         <tr>  				
				  		    	<td class="column-title">
				  				 	<bean:message bundle="techniquesupporthb" key="techniquesupporthb.linkDealOpinion"/>
				  		    	</td>
				  		   	 	<td class="column-content" colspan="3">
		                          	<bean:write name="baselink" property="linkDealOpinion" scope="page"/>
				            	</td>   	                  
				  		 </tr>
			</c:if>			  					  			
			</c:if>

<c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'ImplValidTask')}">
	<c:if test="${!empty baselink.operateType && (baselink.operateType == '920'||baselink.operateType == '921'||baselink.operateType == '11')}">
	
 
    </c:if>


</c:if>




<c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'HoldTask') }">
  <c:if test="${!empty baselink.operateType && (baselink.operateType == '17')}">
 
                <tr>
	  				  <td class="column-title">
	  				     备注说明
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>
				
  </c:if>			  					  			
</c:if>

	<c:if test="${!empty baselink.operateType && baselink.operateType == '8'}">
					  <tr>  				
			  		    	<td class="column-title">
			  				 	<bean:message bundle="techniquesupporthb" key="techniquesupporthb.yijiaoresion"/>
			  		    	</td>
			  		    	<td class="column-content" colspan="3">
	                          	<pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
			            	</td>                  
			  		 </tr>       		  		 
	</c:if>

    <c:if test="${!empty baselink.operateType && baselink.operateType == '10'}">
    
					  <tr>  				
			  		    	<td class="column-title">
			  				 	<bean:message bundle="techniquesupporthb" key="techniquesupporthb.fenpairesion"/>
			  		    	</td>
			  		    	<td class="column-content" colspan="3">
	                          	<pre><bean:write name="baselink" property="remark" scope="page"/></pre>
			            	</td>                  
			  		 </tr>    
     		  		 
	</c:if>

   
		<%if(baselink.getActiveTemplateId()!=null && (baselink.getOperateType().intValue() == 4 ||baselink.getOperateType().intValue() == -10||baselink.getOperateType().intValue() == -11||baselink.getOperateType().intValue() == -15) ){%> 
			 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>			  		 
			 <%} %>	  
			 <%if(baselink.getActiveTemplateId()!=null && baselink.getOperateType().intValue() == 9 ){%> 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>			  		 
			 <%} %>	  
		<%if(baselink.getActiveTemplateId()!=null && (baselink.getOperateType().intValue() == -12 ||baselink.getOperateType().intValue() == -14) ){%> 
			 
                <tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="sheet" key="mainForm.cancelReason"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>			  		 
			 <%} %>	 			 			 						

  			</table>
  		  </div>
	    </div>
      </logic:iterate>
</logic:present>
</div>
<script type="text/javascript">
 switcher = new detailSwitcher();
  switcher.init({
	container:'history',
  	handleEl:'div.history-item-title'
  });
  
 function copy(id){
  	var ifCheck = document.getElementById(id);
  	if(ifCheck.checked == true){
    Ext.Ajax.request({
		method:"get",
		url: "techniquesupporthb.do?method=getJsonLink&id="+id+"&beanName=TechniqueSupportHb",
		success: function(x){
				    var o = eoms.JSONDecode(x.responseText);
					for(p in o){
		            var a = eoms.$(p);
				    if(a && a.tagName == "TEXTAREA" ){
				        if(a.value == ""){
				        a.value = o[p];
				        }
				        else{
				        a.value +="   "+o[p];
				        }				        
				     }	     
                  }		     
        	   }
       });
     }
   }
</script>
