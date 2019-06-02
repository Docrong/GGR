<%@ include file="/common/taglibs.jsp"%>
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>
<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.netdata.model.NetDataLink">  
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
  	     <eoms:dict key="dict-sheet-netdata" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
  	     <bean:message bundle="netdata" key="netdata.operateType"/>:
  	     <eoms:dict key="dict-sheet-netdata" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
  	     
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
  			   <% if(baselink.getOperateRoleId()!=null&&!baselink.getOperateRoleId().equals("")) {%>
  				<tr>
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
  				  </td>
  				  <td class="column-content">
  				    <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao" />  	
  				    <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao" />&nbsp;			   
  			      </td>
  				  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateTime"/>
  				  </td>
  				  <td class="column-content">
  				     <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                  </td>                  
  				 </tr>
  			   <%} else { %>
  			      <tr>
  				 
  				  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateTime"/>
  				  </td>
  				  <td class="column-content" colspan="3">
  				     <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                  </td>                  
  				 </tr>
  			   <%} %>
  				<tr>
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.operaterContact"/> 
  				  </td>
  				  <%if(baselink.getOperateType().intValue()==0 || baselink.getOperateType().intValue() == 3 || baselink.getOperateType().intValue() == -12){%> 	
	  				  <td class="column-content">
	  				  		${sheetMain.sendContact}
	  			      </td>
  			      <%} else {%>
  			      		<td class="column-content">
	  				  		${baselink.operaterContact}
	  			      </td>
  			      <% }%>
  			      <td class="column-title">    
  				  </td>
  				  <td class="column-content">
  			      </td>                
  				</tr>
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.toOrgName"/>
	  				  </td>
	  				  <td class="column-content">
		  				    <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao" />
  				   			<eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" />
  				   			<eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemDeptDao" />
	                  </td>
	                  <%if(baselink.getNodeCompleteLimit() != null) {%> 	
	                  <td class="column-title">
 				      			<bean:message bundle="sheet" key="linkForm.acceptLimit"/>
	  				  </td>
	  				  <td class="column-content">
	  				       <bean:write name="baselink" property="nodeCompleteLimit" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
	  			      </td>	
	  			      <%} else {%>
	  			      	  <td class="column-title">    
		  				  </td>
		  				  <td class="column-content">
		  			      </td>
	  			      <%} %>		                  
		  		</tr>
  	<!-- 方案制定 -->
  		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ProjectCreateTask")&&baselink.getOperateType().intValue()==61){%> 	
  		 		<!--  
  		 		<tr>
  				  <td class="column-title">
  				       <bean:message bundle="sheet" key="linkForm.remark" />
  				  </td>
  				  <td colspan="3" >
  				       <pre>${baselink.remark}</pre>
  			      </td>                
  				</tr>
  				-->
  		 <%}%>
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ProjectCreateTask")&&(baselink.getOperateType().intValue()==110 || baselink.getOperateType().intValue()==11)){%> 	
  				 <tr>
			      	<!-- 完成时限 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkCompleteLimitTime"/></td>
		            <td> 
		   				${eoms:date2String(baselink.linkCompleteLimitTime)}          
		            </td>
		            <!-- 技术方案关键字 -->
		             <td  class="label"><bean:message bundle="netdata" key="netdata.linkDesignKey"/></td>
		             <td>${baselink.linkDesignKey}</td>
		          </tr>
		          <tr>
			      	<!-- 方案资源号 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.mainDesignId"/></td>
		             <td colspan="3"> 	
		   				${sheetMain.mainDesignId}
		            </td>           
		          </tr>
			      <tr>
			      	<!-- 技术方案说明 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkDesignComment"/></td>
		              <td colspan="3"> 	
    				 	<pre>${baselink.linkDesignComment}</pre>
                    </td>
		          </tr>
			      <tr>
			      	<!-- 变更涉及省份 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkInvolvedProvince"/></td>
		              <td colspan="3"> 	
    				  	<pre>${baselink.linkInvolvedProvince}</pre>
                    </td>
		          </tr>
			      <tr>
			      	<!-- 变更涉及地市 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkInvolvedCity"/></td>
		              <td colspan="3"> 	
    				  	<pre>${baselink.linkInvolvedCity}</pre>
                    </td>
		          </tr>
			      <tr>
			      	<!-- 风险评估 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkRiskEstimate"/></td>
		              <td colspan="3"> 	
    				  	<pre>${baselink.linkRiskEstimate}</pre>
                    </td>
		          </tr>
			      <tr>
			      	<!-- 影响业务分析 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkEffectAnalyse"/></td>
		              <td colspan="3"> 	
    				  	<pre>${baselink.linkEffectAnalyse}</pre>
                    </td>
		          </tr>
		          <tr>
			      	<!-- 技术方案附件 -->
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkDesignAttachment"/></td>
		              <td colspan="3"> 	
		             		<eoms:attachment name="baselink" property="nodeAccessories" 
				              scope="page" idField="nodeAccessories" appCode="netdata" 
				               viewFlag="Y"/> 
                    </td>
		          </tr>		
	 <%} %>	
	 <!-- 方案审核 -->
	   <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AuditTask")&&baselink.getOperateType().intValue()==61 ){%> 	
  		 		<!-- 
  		 		<tr>
  				  <td class="column-title">
  				       <bean:message bundle="sheet" key="linkForm.remark" />
  				  </td>
  				  <td colspan="3">
  				       <pre>${baselink.remark}</pre>
  			      </td>                
  				</tr>
  				-->
  		 <%}%>
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("AuditTask")&& (baselink.getOperateType().intValue()==111 || baselink.getOperateType().intValue() == 123  || baselink.getOperateType().intValue()==55)){%> 
		        <tr>
		                <td  class="label"><bean:message bundle="netdata" key="netdata.linkIsCheck"/></td>
		                <td colspan="3">  
			        		<eoms:id2nameDB id="${baselink.linkPermitResult}" beanId="ItawSystemDictTypeDao" />
			        	</td>	                
		         </tr>
			     <tr>
		            	<td  class="label"><bean:message bundle="netdata" key="netdata.linkCheckComment"/></td>
		             	<td colspan="3"> 	
	   				  		<pre>${baselink.linkCheckComment}</pre>
	                   	</td>
		         </tr>
	 			<tr>
		  				<td class="label"><bean:message bundle="netdata" key="netdata.linkIfStartOperationTest"/>*</td>
		     			<td colspan="3">  
		     			<eoms:id2nameDB id="${baselink.linkIfStartOperationTest}" beanId="ItawSystemDictTypeDao" />
			        	</td>
		  		</tr>
		  		<c:if test="${baselink.linkIfStartOperationTest=='1030101'}">
		  		<tr>
		  			<td class="label"><bean:message bundle="netdata" key="netdata.linkTestPerson"/>*</td>
		    		<td colspan="3">
		    			
		    			<c:forTokens items="${baselink.linkTestPerson}" delims="," var="linkTestPerson" varStatus="status">
                            <eoms:id2nameDB id="${linkTestPerson}" beanId="tawSystemSubRoleDao"/>
                               <c:choose>
                                 <c:when test="${status.last}">
                               </c:when>
                               <c:otherwise>,
                               </c:otherwise>
                            </c:choose> 	                  
                      </c:forTokens>
		    		</td>
		  		</tr>
		  		</c:if>
		 <%} %>
		 
	<!-- 方案审批 -->
		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("PermitTask")&&baselink.getOperateType().intValue()==61){%> 	
  		 		<!-- 
  		 		<tr>
  				  <td class="column-title">
  				       <bean:message bundle="sheet" key="linkForm.remark" />
  				  </td>
  				  <td colspan="3">
  				       <pre>${baselink.remark}</pre>
  			      </td>                
  				</tr>
  				-->
  		 <%}%>
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("PermitTask")&&(baselink.getOperateType().intValue()==112 || baselink.getOperateType().intValue()==121 || baselink.getOperateType().intValue()==122 || baselink.getOperateType().intValue()==55)){%> 
		        <tr>
		     		<!-- 审批结果 -->
		     		<td class="label"><bean:message bundle="netdata" key="netdata.linkPermitResult"/></td>
		     		<td colspan="3">
		     			<eoms:id2nameDB id="${baselink.linkPermitResult}" beanId="ItawSystemDictTypeDao" />
					  </td>
				</tr>
		        <tr>
		     		<!-- 审批意见 -->
		     		<td class="label"><bean:message bundle="netdata" key="netdata.linkPermitIdea"/></td>
		     		<td colspan="3">
		     			<pre>${baselink.linkPermitIdea}</pre>
					  </td>
				</tr>
			 
		 <%} %>
		 
	<!-- 排程 -->
		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("PlanTask")&&baselink.getOperateType().intValue()==61){%> 	
  		 		<!-- 
  		 		<tr>
  				  <td class="column-title">
  				       <bean:message bundle="sheet" key="linkForm.remark" />
  				  </td>
  				  <td colspan="3">
  				       <pre>${baselink.remark}</pre>
  			      </td>                
  				</tr>
  				-->
  		 <%}%>
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("PlanTask")&&(baselink.getOperateType().intValue()==113|| baselink.getOperateType().intValue()==11)){%> 
     		  <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkManager"/></td>
		            <td>${baselink.linkManager}</td>
		          	<td  class="label"><bean:message bundle="netdata" key="netdata.linkContact"/></td>
		            <td>${baselink.linkContact }</td>
	          </tr>
		      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkPlanStartTime"/></td>
		            <td> 
		            	${eoms:date2String(baselink.linkPlanStartTime)}            
		            </td>
		          	<td  class="label"><bean:message bundle="netdata" key="netdata.linkPlanEndTime"/></td>
		            <td>     
		            	${eoms:date2String(baselink.linkPlanEndTime)}           
		            </td>
	          </tr>
		      <tr>
		            <td  class="label"><bean:message bundle="netdata" key="netdata.linkCellInfo"/></td>
		            <td  colspan="3"> 	
	   				 	<pre>${baselink.linkCellInfo }</pre>
	                </td>
	          </tr>
              <tr>
	                 <td  class="label"><bean:message bundle="netdata" key="netdata.linkIsEffectBusiness"/></td>
	                <td colspan="3">  			      
		        		<eoms:id2nameDB id="${baselink.linkIsEffectBusiness}" beanId="ItawSystemDictTypeDao" />
		        	</td>	                
	          </tr>
		      <tr>
	            	<td  class="label"><bean:message bundle="netdata" key="netdata.linkEffectCondition"/></td>
		            <td colspan="3"> 	
	   				  <pre>	${baselink.linkEffectCondition}</pre>
	                </td>
	          </tr>
		      <tr>
	            	<td  class="label"><bean:message bundle="netdata" key="netdata.linkNetManage"/></td>
	                <td colspan="3"> 	
   				  		<pre>${baselink.linkNetManage}</pre>
                    </td>
	          </tr>
              <tr>
                <td  class="label"><bean:message bundle="netdata" key="netdata.linkBusinessDept"/></td>
                <td>  
	        		<eoms:id2nameDB id="${baselink.linkBusinessDept}" beanId="ItawSystemDictTypeDao" />
	        	</td>
	        	<td  class="label"><bean:message bundle="netdata" key="netdata.linkIsSendToFront"/></td>
                <td>      
	        		<eoms:id2nameDB id="${baselink.linkIsSendToFront}" beanId="ItawSystemDictTypeDao" />
	        	</td>	 	                
             </tr>
             <tr>
             	<td  class="label"><bean:message bundle="netdata" key="netdata.executeAttachment"/></td>
             	<td colspan="3">
				             <eoms:attachment name="baselink" property="nodeAccessories" 
				              scope="page" idField="nodeAccessories" appCode="netdata" 
				               viewFlag="Y"/>   
             	</td>
             </tr>
 
		 <%} %>
	<!-- 方案实施 -->
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ExecuteTask")&&baselink.getOperateType().intValue()==61){%> 	
  		 		<!-- 
  		 		<tr>
  				  <td class="column-title">
  				       <bean:message bundle="sheet" key="linkForm.remark" />
  				  </td>
  				  <td colspan="3">
  				       <pre>${baselink.remark}</pre>
  			      </td>                
  				</tr>
  				-->
  		 <%}%>
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ExecuteTask")&&(baselink.getOperateType().intValue()==114 || baselink.getOperateType().intValue()==11)){%> 
			  <tr>
	               <td  class="label"><bean:message bundle="netdata" key="netdata.linkCutResult"/></td>
	               <td>          
	        	  		<eoms:id2nameDB id="${baselink.linkCutResult}" beanId="ItawSystemDictTypeDao" />
	        	  </td>
	        	  <td  class="label"><bean:message bundle="netdata" key="netdata.linkIsPlan"/></td>
	              <td>  
	        	   		<eoms:id2nameDB id="${baselink.linkIsPlan}" beanId="ItawSystemDictTypeDao" />
	        	   </td>	                
	          </tr>
	          <tr>
	                <td  class="label"><bean:message bundle="netdata" key="netdata.linkTestResult"/></td>
	                <td colspan="3">  
			       		<eoms:id2nameDB id="${baselink.linkTestResult}" beanId="ItawSystemDictTypeDao" />
	        	    </td>	                
	            	
	          </tr>
		      <tr>
	            	<td  class="label"><bean:message bundle="netdata" key="netdata.linkCutComment"/></td>
	                <td colspan="3"> 	
	  				  <pre>${baselink.linkCutComment }</pre>
	                </td>
	          </tr>
		      <tr>
	            	<td  class="label"><bean:message bundle="netdata" key="netdata.linkBusinessComment"/></td>
	              	<td colspan="3"> 	
	                	<pre>${baselink.linkBusinessComment }</pre>
	                </td>
	          </tr>
	              
		      <tr>
	            	<td  class="label"><bean:message bundle="netdata" key="netdata.linkAlertRecord"/></td>
	              	<td colspan="3"> 	  
	                	<pre>${baselink.linkAlertRecord }</pre>
	                </td>
	          </tr>
		      <tr>
	            	<td  class="label"><bean:message bundle="netdata" key="netdata.linkUnnormalComment"/></td>
	              	<td colspan="3"> 	
	                 	<pre>${baselink.linkUnnormalComment }</pre>
	                 </td>
	          </tr>
		          
		          
		 <%} %>
	<!-- 数据核查 -->
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("CheckDataTask")&&baselink.getOperateType().intValue()==61){%> 	
  		 		<!-- 
  		 		<tr>
  				  <td class="column-title">
  				       <bean:message bundle="sheet" key="linkForm.remark" />
  				  </td>
  				  <td colspan="3">
  				       <pre>${baselink.remark}</pre>
  			      </td>                
  				</tr>
  				-->
  		 <%}%>
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("CheckDataTask")&&(baselink.getOperateType().intValue()==117 || baselink.getOperateType().intValue()==118 ||baselink.getOperateType().intValue()==11)){%> 
			  <tr>
	               <td  class="label"><bean:message bundle="netdata" key="netdata.linkDataCheck"/></td>
	               <td>          
	        	  		<pre>${baselink.linkDataCheck }</pre>
	        	  </td>
	        	  <td  class="label"><bean:message bundle="netdata" key="netdata.linkCheckExplain"/></td>
	              <td>  
	        	   		<pre>${baselink.linkCheckExplain }</pre>
	        	   </td>	                
	          </tr>
		 <%} %>
	<!-- 业务测试 -->
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("TestOperationTask")&&baselink.getOperateType().intValue()==61){%> 	
  		 		<!-- 
  		 		<tr>
  				  <td class="column-title">
  				       <bean:message bundle="sheet" key="linkForm.remark" />
  				  </td>
  				  <td colspan="3">
  				       <pre>${baselink.remark}</pre>
  			      </td>                
  				</tr>
  				-->
  		 <%}%>
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("TestOperationTask")&&(baselink.getOperateType().intValue()==119 || baselink.getOperateType().intValue()==11)){%> 
			  <tr>
	               <td  class="label"><bean:message bundle="netdata" key="netdata.linkTestResult"/></td>
	               <td colspan="3">          
	        	  		<eoms:id2nameDB id="${baselink.linkTestResult}" beanId="ItawSystemDictTypeDao" />
	        	  </td>
	          </tr>
	          <tr>
	        	  <td  class="label"><bean:message bundle="netdata" key="netdata.linkTestReport"/></td>
	              <td colspan="3">  
	        	   		<pre>${baselink.linkTestReport }</pre>
	        	   </td>
	          </tr>
		 <%} %>
	<!-- 实施结果验证 -->
		<%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ValidateTask")&&baselink.getOperateType().intValue()==61){%> 	
  		 		<!-- 
  		 		<tr>
  				  <td class="column-title">
  				       <bean:message bundle="sheet" key="linkForm.remark" />
  				  </td>
  				  <td colspan="3">
  				       <pre>${baselink.remark}</pre>
  			      </td>                
  				</tr>
  				-->
  		 <%}%>
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("ValidateTask") && (baselink.getOperateType().intValue()==115 || baselink.getOperateType().intValue()==11)){%> 
		 	  <tr>
	                <td  class="label"><bean:message bundle="netdata" key="netdata.linkIsUpdateWork"/></td>
	                <td>  
		        		<eoms:id2nameDB id="${baselink.linkIsUpdateWork}" beanId="ItawSystemDictTypeDao" />
		        	</td>
		        	<td  class="label"><bean:message bundle="netdata" key="netdata.linkCutResult"/></td>
	                <td>  
		        		<eoms:id2nameDB id="${baselink.linkCutResult}" beanId="ItawSystemDictTypeDao" />
		        	</td>	                
	          </tr>
	          <tr>
	              	<td  class="label"><bean:message bundle="netdata" key="netdata.linkIsNeedProject"/></td>
		            <td> 		  
	                   		<eoms:id2nameDB id="${baselink.linkIsNeedProject}" beanId="ItawSystemDictTypeDao" />
	                </td>
	          		<td  class="label"><bean:message bundle="netdata" key="netdata.linkIsPlan"/></td>
		            <td> 	 	
	                   	<eoms:id2nameDB id="${baselink.linkIsPlan}" beanId="ItawSystemDictTypeDao" />
	                </td>
	          </tr>
		      <tr>
	            	<td  class="label"><bean:message bundle="netdata" key="netdata.linkCutAnalyse"/></td>
	              	<td colspan="3"> 	
   				  		<pre>${baselink.linkCutAnalyse}</pre>
                   	</td>
	          </tr>	          
		      <tr>
	            	<td  class="label"><bean:message bundle="netdata" key="netdata.linkWorkUpdateAdvice"/></td>
	              	<td colspan="3"> 	
   				  		<pre>${baselink.linkWorkUpdateAdvice}</pre>
                   	</td>
	          </tr>
		      <tr>
	            	<td  class="label"><bean:message bundle="netdata" key="netdata.linkSoftVersionUpdate"/></td>
	              	<td colspan="3"> 	
   				  		<pre>${baselink.linkSoftVersionUpdate}</pre>
                   	</td>
	          </tr>
		      <tr>
	            	<td  class="label"><bean:message bundle="netdata" key="netdata.linkNextPlan"/></td>
	              	<td colspan="3"> 				  			
                   		<pre>${baselink.linkNextPlan}</pre>
                   	</td>
	          </tr>
		      <tr>
	            	<td  class="label"><bean:message bundle="netdata" key="netdata.linkProjectComment"/></td>
	              	<td colspan="3"> 	
                   		<pre>${baselink.linkProjectComment}</pre>
                   	</td>
	          </tr>		 
		 <%} %>
		 <%if(baselink.getActiveTemplateId()!=null && baselink.getActiveTemplateId().equals("HoldTask")){%> 
		 
		 <%} %>
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
		  <%if(baselink.getOperateType()!=null && (baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7 || baselink.getOperateType().intValue() == 10||baselink.getOperateType().intValue() == 30)){%> 
 
                <tr>
	  				  <td class="column-title">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td class="column-content" colspan=3>
	  				   	 <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	                  </td>
		  		</tr>	

			 <%}%>	
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
		url: "netdata.do?method=getJsonLink&id="+id+"&beanName=NetData",
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