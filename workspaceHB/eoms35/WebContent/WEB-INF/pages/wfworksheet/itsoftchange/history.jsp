<%@ include file="/common/taglibs.jsp"%>
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>

<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">  
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.itsoftchange.model.ITSoftChangeLink">  
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
  	     <eoms:dict key="dict-sheet-itsoftchange" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	     <bean:message bundle="itsoftchange" key="itsoftchange.operateType"/>:
  	     <eoms:dict key="dict-sheet-itsoftchange" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
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
  				<%if(baselink.getOperateRoleId()!=null && ! baselink.getOperateRoleId().equals("")){ %>
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
  				  </td>
  				  <td class="column-content">
  				    <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao" /> 
  				    <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao" />&nbsp; 				   
  			      </td>
  			      <%} %>
  				 <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operaterContact"/>
  				  </td>
                   <td class="column-content">
  				     <bean:write name="baselink" property="operaterContact" scope="page"/>
                  </td>            
  				</tr>
  		      
                <tr>
		  				  <td class="column-title">
		  				     <bean:message bundle="itsoftchange" key="itsoftchange.operateType"/>
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:dict key="dict-sheet-itsoftchange" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
		                  </td>
                 <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateTime"/>
  				  </td>
  				  <td class="column-content">
  				     <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
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
               <%if(baselink.getToOrgRoleId()!=null && ! baselink.getToOrgRoleId().equals("")) {%>
                 <tr>
		  				  <td class="column-title">
		  				     <bean:message bundle="sheet" key="linkForm.toOrgName"/>
		  				  </td>
		  				  <td class="column-content"  colspan="3">
		  				   <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao" />
  				           <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" />
		  				   
		                  </td>	
  				</tr>
 				<%} %>


		 <%if((baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("OperateTask"))){%> 
		 
			 <%if((baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 91|| baselink.getOperateType().intValue() == 11))){%> 
		       <tr>
		  		 <td class="column-title">  				    
		  		  <bean:message bundle="itsoftchange" key="itsoftchange.linkDevContacter"/>
		  	      </td>
		  		<td class="column-content" colspan=3>
		  	       <pre><bean:write name="baselink" property="linkDevContacter" scope="page"/></pre>
		        </td>
		  	</tr>
		  	<tr>
		  		 <td class="column-title">  				    
		  		  <bean:message bundle="itsoftchange" key="itsoftchange.linkMakerTestResult"/>
		  	      </td>
		  		<td class="column-content" colspan=3>
		  	       <pre><bean:write name="baselink" property="linkMakerTestResult" scope="page"/></pre>
		        </td>
		  	</tr>
		  	<tr>
		  		 <td class="column-title">  				    
		  		  <bean:message bundle="itsoftchange" key="itsoftchange.linkImpactElseDCL"/>
		  	      </td>
		  		<td class="column-content" colspan=3>
		  	       <pre><bean:write name="baselink" property="linkImpactElseDCL" scope="page"/></pre>
		        </td>
		  	</tr>
		  	<tr>
		  		 <td class="column-title">  				    
		  		  <bean:message bundle="itsoftchange" key="itsoftchange.linkIfNotifyInterfixSystem"/>
		  	      </td>
		  		<td class="column-content" colspan=3>
		  	       <pre><bean:write name="baselink" property="linkIfNotifyInterfixSystem" scope="page"/></pre>
		        </td>
		  	</tr>
		       <tr>
		  		 <td class="column-title">  				    
		  		  <bean:message bundle="itsoftchange" key="itsoftchange.linkCompleteDesc"/>
		  	      </td>
		  		<td class="column-content" colspan=3>
		  	       <pre><bean:write name="baselink" property="linkCompleteDesc" scope="page"/></pre>
		        </td>
		  	 </tr>	
               <tr>
						<td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestGuide"/></td>
   				 <td  colspan='3'>
   				 <eoms:attachment  name="baselink" property="linkTestGuide" scope="page"
   				 idField="${sheetPageName}linkTestGuide" appCode="itsoftchangesheet" 
   				 viewFlag="Y"/>
                </td>
            </tr>		  	 		 
			 <%}}%>	

			 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("TestTask")){%> 
	
			 <%if(baselink.getOperateType()!=null &&  (baselink.getOperateType().intValue() == 921
			                                          ||baselink.getOperateType().intValue() == 922
			                                          ||baselink.getOperateType().intValue() == 11)){%> 
			 	   <tr>
		                 <td  class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestResult"/></td>
		                <td class="column-content">  
		                <eoms:id2nameDB id="${baselink.linkTestResult}" beanId="ItawSystemDictTypeDao"/>

			        </td>	                
		                 <td  class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestSatisfaction"/></td>
		                <td class="column-content">  
				         <eoms:id2nameDB id="${baselink.linkTestSatisfaction}" beanId="ItawSystemDictTypeDao"/>
			        </td>	                
		            </tr>	
			       <tr>
			  		 <td class="column-title">  				    
			  		  <bean:message bundle="itsoftchange" key="itsoftchange.linkTestDesc"/>
			  	      </td>
			  		<td class="column-content" colspan=3>
			  	       <pre><bean:write name="baselink" property="linkTestDesc" scope="page"/></pre>
			        </td>
			  	 </tr>			            
               <tr>
						<td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestNote"/></td>
   				 <td  colspan='3'>
   				 <eoms:attachment  name="baselink" property="linkTestNote" scope="page"
   				 idField="${sheetPageName}linkTestNote" appCode="itsoftchangesheet" 
   				 viewFlag="Y"/>
                 </td>
              </tr>		            
			 <%} }%>	
	 
			 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("OnlineTask")){%> 
	
			 <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 93||baselink.getOperateType().intValue() == 11)){%> 		            
             <tr>
              		<td class="column-title"><bean:message bundle="itsoftchange" key="itsoftchange.linkOnlineTime"/></td>
	   				<td  class="column-content">
	   				 <bean:write  name="baselink"  property="linkOnlineTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="page"/> 
	                </td>
	                <td class="column-title"><bean:message bundle="itsoftchange" key="itsoftchange.linkSoftEdition"/></td>
	   				<td  class="column-content">
	   				 <bean:write  name="baselink"  property="linkSoftEdition" scope="page"/> 
	                </td>
		     </tr>
		     <tr>
		            <td  class="column-title"><bean:message bundle="itsoftchange" key="itsoftchange.linkSoftEditionDCL"/>*</td>
		              <td class="column-content" colspan="3"> 	
    				   <pre><bean:write name="baselink" property="linkSoftEditionDCL" scope="page"/></pre>
                    </td>
		      </tr> 
			       <tr>
			  		 <td class="column-title">  				    
			  		  <bean:message bundle="itsoftchange" key="itsoftchange.linkOnlineDesc"/>
			  	      </td>
			  		<td class="column-content" colspan=3>
			  	       <pre><bean:write name="baselink" property="linkOnlineDesc" scope="page"/></pre>
			        </td>
			  	 </tr>
			  	 <tr>
		            <td  class="column-title"><bean:message bundle="itsoftchange" key="itsoftchange.linkUserNoteChangeDCL"/>*</td>
		              <td class="column-content" colspan="3"> 	
    				   <pre><bean:write name="baselink" property="linkUserNoteChangeDCL" scope="page"/></pre>
                    </td>
		      </tr>
               <tr>
				<td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkUserNoteDesc"/></td>
   				 <td  colspan='3'>
   				 <eoms:attachment  name="baselink" property="linkUserNoteDesc" scope="page"
   				 idField="${sheetPageName}linkUserNoteDesc" appCode="itsoftchangesheet" 
   				 viewFlag="Y"/>
                 </td>
              </tr>				  	 
			 
			 <%} }%>
	 
			 
       <%if(baselink.getActiveTemplateId()!=null && (baselink.getActiveTemplateId().equals("cc")||baselink.getActiveTemplateId().equals("reply")||baselink.getActiveTemplateId().equals("advice")||baselink.getOperateType().intValue() == 4)){%> 
			 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>	
			  		 
			 <%} %>		 	
		 			 
		
 		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("HoldTask")){%>
		  <%if(baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 102){%>
		                  <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>
		  
			  		 
			 <%} else if (baselink.getOperateType()!=null && baselink.getOperateType().intValue() == 18){%>	

			 	 <tr>
		            <td  class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkAuditResult"/></td>
		                <td class="column-content">  
		                <eoms:id2nameDB id="${baselink.linkAuditResult}" beanId="ItawSystemDictTypeDao"/>

			             </td>
			       <td class="column-title">  				    
		  		  <bean:message bundle="itsoftchange" key="itsoftchange.linkAnalysisCount"/>
		  	      </td>
		  		  <td class="column-content">
		  	       <bean:write name="baselink" property="linkAnalysisCount" scope="page"/>
		        </td>
		        </tr>
		        <tr>
			       <td class="column-title">  				    
		  		  <bean:message bundle="itsoftchange" key="itsoftchange.linkTestCount"/>
		  	      </td>
		  		  <td class="column-content">
		  	       <bean:write name="baselink" property="linkTestCount" scope="page"/>
		        </td>
		         <td class="column-title">  				    
		  		  <bean:message bundle="itsoftchange" key="itsoftchange.linkDevCount"/>
		  	      </td>
		  		  <td class="column-content">
		  	       <bean:write name="baselink" property="linkDevCount" scope="page"/>
		        </td>		        
		        </tr>
		  
			  		 
			 <%}}%>				 
           <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 8||baselink.getOperateType().intValue() == 88)){%> 
			 
  				<tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="sheet" key="linkForm.transmitReason"/>
	  				  </td>
	  				   <td class="column-content" colspan=3>
	  				      <pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
	  			      </td>	  				                  
  				</tr>
			  		 
		 <%} %>	
		  <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 6||baselink.getOperateType().intValue() == 7||baselink.getOperateType().intValue() == 10||baselink.getOperateType().intValue() == 30)){%> 
			 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>	
			  		 
			 <%}%>			
	  	 <%if(baselink.getNodeAccessories()!=null&&!baselink.getNodeAccessories().equals("")){%>
	  		 	<tr>  				
	  				  <td>
	  				     <bean:message bundle="sheet" key="linkForm.accessories"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
                         <eoms:attachment name="baselink" property="nodeAccessories" 
				              scope="page" idField="nodeAccessories" appCode="businessoperation" 
				               viewFlag="Y"/>
	                  </td>                  
	  				</tr>		  					  		  				  		 
	  	 <%}%>		 	 
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
		url: "itsoftchange.do?method=getJsonLink&id="+id+"&beanName=ITSoftChange",
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