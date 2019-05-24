<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>
<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.accountpopedomapply.model.AccountPopedomApplyLink">  
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
  	    <eoms:dict key="dict-sheet-accountpopedomapply" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	     <bean:message bundle="sheet" key="sheet.deal"/>:
  	     <eoms:dict key="dict-sheet-accountpopedomapply" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
  	     
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
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
  				  </td>
  				  <td class="column-content">
  				      <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao" />&nbsp;
  			      </td>
  				  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateTime"/>
  				  </td>
  				  <td class="column-content">
  				     <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                  </td>                  
  				</tr>
  				 <tr>                
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.operaterContact"/> 
  				  </td>
  				  <%if(baselink.getOperateType().intValue()==22||baselink.getOperateType().intValue()==-10||baselink.getOperateType().intValue()==0 || baselink.getOperateType().intValue() == 3 || baselink.getOperateType().intValue() == -12){%> 	
	  				  <td class="column-content">
	  				  		${sheetMain.sendContact}
	  			      </td>
  			      <%} else {%>
  			      		<td class="column-content">
	  				  		${baselink.operaterContact}
	  			      </td>
  			      <% }%>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.toOrgName"/>
	  				  </td>
	  				  <td class="column-content">
		  				    <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao" />
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
		  	   <!-- 待审核 -->
		  	 <%if(baselink.getActiveTemplateId()!=null&& baselink.getActiveTemplateId().equals("AuditHumTask") && (baselink.getOperateType().intValue()==51|| baselink.getOperateType().intValue()==52)){%> 	
	         <tr>
		            <td  class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.link.applyAttitude"/></td>
		              <td class="column-content" colspan="3"> 
		              <eoms:id2nameDB id="${baselink.applyAttitude}" beanId="ItawSystemDictTypeDao"/>
                    </td>
             </tr>
             <tr>
		            <td  class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.link.applyResult"/></td>
		              <td class="column-content" colspan="3"> 
		              <bean:write name="baselink" property="applyResult" scope="page"/>	
                    </td>
             </tr>
		                   		 
		  	 <%}%>
		  	  <!-- 实施中 -->
		  	  <%if(baselink.getActiveTemplateId()!=null&& baselink.getActiveTemplateId().equals("ActualizeHumTask") &&baselink.getOperateType().intValue()==46){%> 	 
		  	 <tr>
		            <td  class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.link.deResult"/></td>
		              <td colspan="3"> 	
		              <bean:write name="baselink" property="deResult" scope="page"/>	
                    </td>
             </tr> 
		  	 <%}%>
		    <!-- 移交、转审 -->
		    <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 8 || baselink.getOperateType().intValue() == 88)){%> 

  				<tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="sheet" key="linkForm.transmitReason"/>
	  				  </td>
	  				   <td class="column-content" colspan=3>
	  				      <pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
	  			      </td>	  				                  
  				</tr>
  				
			  		 
		   <%} %>
		   <!-- 阶段回复、分派、回复通过、回复不通过、会审、阶段通知、处理回复不通过、会审回复、分派回复 -->		 
		   <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 9 || baselink.getOperateType().intValue() == 1 || baselink.getOperateType().intValue() == 10 || baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7 || baselink.getOperateType().intValue() == 30 || baselink.getOperateType().intValue() == -11 || baselink.getOperateType().intValue() == 55 || baselink.getOperateType().intValue() == 11)){%> 
 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>	

			 <%}%>	
			 
		   <!-- 抄送、阶段回复、阶段通知、驳回上一级 --> 	 
           <% if(baselink.getRemark()!=null && !baselink.getRemark().equals("")){ %>
		  	 <%if(baselink.getActiveTemplateId()!=null && (baselink.getActiveTemplateId().equals("cc"))){%> 
             <%if(baselink.getOperateType()!=null && ( baselink.getOperateType().intValue() == -15 )){%>
             <%if(baselink.getOperateType()!=null && ( baselink.getOperateType().intValue() == -11 )){%>
              <%if( baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 9)){%>	
               <%}if( baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 10 )){%>			
  				 <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>	
  			 <%}
  			 }
  		   }
  		  }
  		}%>
  			 
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
		url: "accountpopedomapply.do?method=getJsonLink&id="+id+"&beanName=AccountPopedomApply",
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
