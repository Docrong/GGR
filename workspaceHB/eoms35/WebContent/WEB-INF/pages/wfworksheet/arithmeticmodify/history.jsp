<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>
<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyLink">  
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
  	     <eoms:dict key="dict-sheet-arithmeticmodify" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
          <bean:message bundle="arithmeticmodify" key="arithmeticmodify.operateType"/>: 
  	     <eoms:dict key="dict-sheet-arithmeticmodify" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
  	     
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
  	  				     <eoms:id2nameDB id="${baselink.operateDeptId}" beanId="tawSystemDeptDao" />&nbsp;shihweifdsf
                  </td>
  				</tr>
  				<tr>
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
                           <bean:message bundle="arithmeticmodify" key="arithmeticmodify.operateType"/>  
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:dict key="dict-sheet-arithmeticmodify" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
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
  				<tr>
  				  <td class="column-title">
  				     指标级别
  				  </td>
  				  <td class="column-content">
  				      <eoms:id2nameDB id="${sheetMain.mainTargetLevel}" beanId="ItawSystemDictTypeDao"/>
  			      </td>
  				  <td class="column-title">
  				     指标分类
  				  </td>
  				  <td class="column-content">
  				     <eoms:id2nameDB id="${sheetMain.mainTargetType}" beanId="ItawSystemDictTypeDao"/>
                  </td>                  
  				</tr>
                <%} %> 				
          	<c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'ArithmeticModifyTask') }">
              <c:if test="${!empty baselink.operateType && (baselink.operateType == '90' || baselink.operateType == '11')}">
              <tr>
		             <td  class="label">解决时间</td>
		             <td class="column-content" colspan="3">
  				         <bean:write name="baselink" property="linkResolveTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
  			         </td>
		        </tr>
   		        <tr>
		             <td  class="label">后续处理过程</td>
		             <td class="column-content" colspan="3"><pre><bean:write name="baselink" property="linkArithmeticModifyProcess" scope="page"/></pre></td>
		       </tr>
		       <tr>
		             <td  class="label">测试报告等相关文档</td>
	             	 <td colspan="3">
	             		 <eoms:attachment name="baselink" property="linkTestReport" 
			            	scope="page" idField="linkTestReport" appCode="arithmeticmodify" viewFlag="Y"/>
	             	 </td>
	          </tr>	
		    </c:if>  					  			
           </c:if>
             	<c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'CheckTask') }">
              <c:if test="${!empty baselink.operateType && (baselink.operateType == '91' ||baselink.operateType == '911' ||baselink.operateType == '912' || baselink.operateType == '11')}">
                <tr>
		                <td  class="label">质检结果</td>
		                <td colspan="3"><eoms:id2nameDB id="${baselink.linkCheckResult}" beanId="ItawSystemDictTypeDao" /></td>
		        </tr>
		        <tr>
		                <td  class="label">质检概述</td>
		                <td class="column-content" colspan="3"><pre><bean:write name="baselink" property="linkCheckIdea" scope="page"/></pre></td>
		        </tr> 
		  </c:if>  					  			
      </c:if>
      <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'PermitTask') }">
    	 <c:if test="${!empty baselink.operateType && (baselink.operateType == '103')}">
             	<tr>
	  				  <td class="column-title">审批结果</td>
	  				  <td class="column-content" colspan=3><pre><eoms:id2nameDB id="${baselink.linkPermitResult}" beanId="ItawSystemDictTypeDao"/></pre></td>
		  		</tr> 
		  		<tr>
	  				  <td class="column-title">审批意见</td>
	  				  <td class="column-content" colspan=3><pre><bean:write name="baselink" property="linkPermitOpinion" scope="page"/></pre></td>
		  		</tr> 
          </c:if>			  					  			
        </c:if>
       <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'RequireConfirmTask') }">
    	 <c:if test="${!empty baselink.operateType && (baselink.operateType == '104')}">
             	<tr>
	  				  <td class="column-title">需求确认结果</td>
	  				  <td class="column-content" colspan=3><pre><bean:write name="baselink" property="linkRequireConfirm" scope="page"/></pre></td>
		  		</tr> 
		  		<tr>
	  				  <td class="column-title">测试开始时间</td>
	  				  <td class="column-content" ><pre>${eoms:date2String(baselink.linkTestStartTime)}</pre></td>
	  				  <td class="column-title">测试结束时间</td>
	  				  <td class="column-content" ><pre>${eoms:date2String(baselink.linkTestEndTime)}</pre></td>
		  		</tr> 
          </c:if>			  					  			
        </c:if>
       <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'DeployImplementTask') }">
    	 <c:if test="${!empty baselink.operateType && (baselink.operateType == '105')}">
    	 		<tr>
	  				  <td class="column-title">测试结果</td>
	  				  <td class="column-content" colspan=3><pre><bean:write name="baselink" property="linkTestResult" scope="page"/></pre></td>
		  		</tr> 
    	   </c:if>			  					  			
        </c:if>
        <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'ResultConfirmTask') }">
    	 <c:if test="${!empty baselink.operateType && (baselink.operateType == '106')}">
    	 		<tr>
	  				  <td class="column-title">测试结果</td>
	  				  <td class="column-content" colspan=3><pre><bean:write name="baselink" property="linkTestResult" scope="page"/></pre></td>
		  		</tr> 
    	   </c:if>			  					  			
        </c:if>
        <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'CheckDataSameTask') }">
    	 <c:if test="${!empty baselink.operateType && (baselink.operateType == '107')}">
    	 		<tr>
	  				  <td class="column-title">测试结果</td>
	  				  <td class="column-content" colspan=3><pre><bean:write name="baselink" property="linkTestResult" scope="page"/></pre></td>
		  		</tr> 
    	   </c:if>			  					  			
        </c:if>
        <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'ResultCheckTask') }">
    	 <c:if test="${!empty baselink.operateType && (baselink.operateType == '108')}">
    	 		<tr>
	  				  <td class="column-title">验证报告结果</td>
	  				  <td class="column-content" colspan=3><pre><bean:write name="baselink" property="linkYZReportResult" scope="page"/></pre></td>
		  		</tr> 
    	   </c:if>			  					  			
        </c:if>
        <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'TargetCheckTask') }">
    	 <c:if test="${!empty baselink.operateType && (baselink.operateType == '109')}">
    	 		<tr>
	  				  <td class="column-title">检查报告结果</td>
	  				  <td class="column-content" colspan=3><pre><bean:write name="baselink" property="linkJCReportResult" scope="page"/></pre></td>
		  		</tr> 
    	   </c:if>			  					  			
        </c:if>
         <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'TargetConfirmTask') }">
    	 <c:if test="${!empty baselink.operateType && (baselink.operateType == '110')}">
    	 		<tr>
	  				  <td class="column-title">指标检查结果</td>
	  				  <td class="column-content" colspan=3><pre><bean:write name="baselink" property="linkTargetCheckResult" scope="page"/></pre></td>
		  		</tr> 
		  		<tr>
	  				  <td class="column-title">相关OA号或算法审批工单号</td>
	  				  <td class="column-content" colspan=3><pre><bean:write name="baselink" property="linkSheetNumber" scope="page"/></pre></td>
		  		</tr> 
    	   </c:if>			  					  			
        </c:if>
        <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'FormalDeployTask') }">
    	 <c:if test="${!empty baselink.operateType && (baselink.operateType == '111')}">
    	 		<tr>
	  				  <td class="column-title">部署结果报告</td>
	  				  <td class="column-content" colspan=3><pre><bean:write name="baselink" property="linkBSResultReport" scope="page"/></pre></td>
		  		</tr> 
    	   </c:if>			  					  			
        </c:if>
        <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'PublishNoticeTask') }">
    	 <c:if test="${!empty baselink.operateType && (baselink.operateType == '112')}">
    	 		<tr>
	  				  <td class="column-title">算法发布时间</td>
	  				  <td class="column-content" colspan=3><pre>${eoms:date2String(baselink.linkPulishTime)}</pre></td>
		  		</tr> 
    	   </c:if>			  					  			
        </c:if>
     <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'HoldTask') }">
     <c:if test="${!empty baselink.operateType && (baselink.operateType == '17')}">
             <tr>
	  				  <td class="column-title">退回原因</td>
	  				  <td class="column-content" colspan=3><pre><bean:write name="baselink" property="remark" scope="page"/></pre></td>
		  		</tr> 
          </c:if>			  					  			
        </c:if>
	<c:if test="${!empty baselink.operateType && baselink.operateType == '8'}">
					  <tr>  				
			  		    	<td class="column-title"><bean:message bundle="arithmeticmodify" key="arithmeticmodify.yijiaoresion"/></td>
			  		    	<td class="column-content" colspan="3">
	                          	<pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
			            </td>                  
			  		 </tr>     		  		 
	</c:if>
	<c:if test="${!empty baselink.operateType && baselink.operateType == '88'}">
					  <tr>  				
			  		    	<td class="column-title">转审理由</td>
			  		    	<td class="column-content" colspan="3">
	                          	<pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
			            </td>                  
			  		 </tr>     		  		 
	</c:if>
  <c:if test="${!empty baselink.operateType && baselink.operateType == '10'}">    
					 <tr>  				
			  		    	<td class="column-title"><bean:message bundle="arithmeticmodify" key="arithmeticmodify.fenpairesion"/></td>
			  		    	<td class="column-content" colspan="3">
	                          	<pre><bean:write name="baselink" property="remark" scope="page"/></pre>
			            </td>                  
			  	 </tr>       		  		 
	</c:if>
	<c:if test="${!empty baselink.operateType && baselink.operateType == '30'}">    
					 <tr>  				
			  		    	<td class="column-title">会审原因</td>
			  		    	<td class="column-content" colspan="3">
	                          	<pre><bean:write name="baselink" property="remark" scope="page"/></pre>
			            </td>                  
			  	 </tr>       		  		 
	</c:if>
	<%if(baselink.getActiveTemplateId()!=null && (baselink.getOperateType().intValue() == 6 ||baselink.getOperateType().intValue() == 7 ||baselink.getOperateType().intValue() == -10||baselink.getOperateType().intValue() == -11||baselink.getOperateType().intValue() == -15) ){%> 			 
          <tr>
	  				  <td class="column-title"><bean:message bundle="sheet" key="linkForm.remark"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	         <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	            </td>
		  		</tr>			  		 
			 <%} %>	 
	<%if((baselink.getActiveTemplateId()!=null && baselink.getOperateType().intValue() == 4 ) || (baselink.getActiveTemplateId()!=null && baselink.getOperateType().intValue() == 61) ){%> 			 
          <tr>
	  		<td class="column-title">操作说明</td>
	  		<td class="column-content" colspan=3>
	  		<pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	        </td>
		 </tr>			  		 
	<%} %>	  
	    <%if(baselink.getActiveTemplateId()!=null && baselink.getOperateType().intValue() == 9 ){%> 			
          <tr>
	  				  <td class="column-title"><bean:message bundle="sheet" key="linkForm.remark"/></td>
	  				  <td class="column-content" colspan=3>
	  				   	          <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
	            </td>
		  		</tr>			  		 
			 <%} %>	  
		<%if(baselink.getActiveTemplateId()!=null && (baselink.getOperateType().intValue() == -12 ||baselink.getOperateType().intValue() == -14) ){%> 			 
          <tr>
	  				  <td class="column-title"><bean:message bundle="sheet" key="mainForm.cancelReason"/></td>
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
		url: "arithmeticmodify.do?method=getJsonLink&id="+id+"&beanName=ArithmeticModify",
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
