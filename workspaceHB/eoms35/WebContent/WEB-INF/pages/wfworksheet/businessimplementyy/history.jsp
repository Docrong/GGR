<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<div id="history" class="panel">
  <div class="tabtool-history-detail">&nbsp;
	<a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet" key="sheet.showDetail"/></a>
  </div>
<%int jNum=0;%>
<logic:present name="HISTORY" scope="request">
      <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYLink">  
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
  	     <eoms:dict key="dict-sheet-businessimplementyy" dictId="activeTemplateId" itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML" />
          <bean:message bundle="businessimplementyy" key="businessimplementyy.operateType"/>: 
  	     <eoms:dict key="dict-sheet-businessimplementyy" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />
  	     
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
                           <bean:message bundle="businessimplementyy" key="businessimplementyy.operateType"/>  
		  				  </td>
		  				  <td class="column-content">
		  				   <eoms:dict key="dict-sheet-businessimplementyy" dictId="mainOperateType" itemId="${baselink.operateType}" beanId="id2descriptionXML" />  
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
            <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'AuditTask')}">
            <c:if test="${!empty baselink.operateType && (baselink.operateType == '71'||baselink.operateType == '55' )}">

			<tr>
			    <td class="label">是否签收*</td>
		        <td colspan="3"> 
		         <eoms:id2nameDB id="${baselink.linkIsAppled}" beanId="ItawSystemDictTypeDao"/>
		        </td>
		    </tr>	   
			<tr>
			  	<td class="label">不签收意见</td>
			    <td colspan="3">
			   <bean:write name="baselink" property="linkNoAppledOpinition" scope="page"/>
			    </td> 
		    </tr>
		  
			<tr>
			    <td class="label">集团产品编码*</td>
		           <td class="content"> 
		        <bean:write name="baselink" property="linkGoupeProductCode" scope="page"/>
		          </td>
		      <td class="label">处理方式*</td>
		        <td class="content">
		         <eoms:id2nameDB id="${baselink.linkDealMethod}" beanId="ItawSystemDictTypeDao"/>
		        </td>
		    </tr>  
			<tr>
			  	<td class="label">处理意见</td>
			    <td colspan="3">
			     <bean:write name="baselink" property="linkDealOpinition" scope="page"/>
			    </td> 
		    </tr>
				
  </c:if>			  					  			
</c:if>

<c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'CodeDispthTask')}">
	<c:if test="${!empty baselink.operateType && (baselink.operateType == '72'||baselink.operateType == '11' ||baselink.operateType == '111')}">

				<tr>
				    <td class="label">信令方式*</td>
			        <td colspan="3""> 
			        <eoms:id2nameDB id="${baselink.linkInfoMethod}" beanId="ItawSystemDictTypeDao"/>
			        </td>
			   </tr>
				
			   <tr>
				    <td class="label">信令协议</td>
			        <td class="content"> 
			         <eoms:id2nameDB id="${baselink.linkInfoProtocol}" beanId="ItawSystemDictTypeDao"/>
			        </td>
			       <td class="label">集团产品编码*</td>
		           <td class="content"> 
		             <bean:write name="baselink" property="linkGoupeProductCode" scope="page"/>
		           </td>
		       </tr>
		    
		       <!-- 
		       <tr>
				    <td class="label">退回原因分类*</td>
			        <td colspan="3""> 
			             <eoms:comboBox   name="${sheetPageName}linkBackResonType" id="${sheetPageName}linkBackResonType"  initDicId="1013305" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkBackResonType}" />	     	
			        </td>
			   </tr> -->
			<tr>
			  	<td class="label">处理意见</td>
			    <td colspan="3">
			      <bean:write name="baselink" property="linkDealOpinition" scope="page"/>
			    </td> 
		     </tr>
					 

					
	
 
    </c:if>
</c:if>
<c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'GetWayTask')}">
	<c:if test="${!empty baselink.operateType && (baselink.operateType == '73'||baselink.operateType == '11')}">
				<tr>
				    <td class="label">传输电路编号</td>
			        <td class="content"> 
			         <bean:write name="baselink" property="linkCircuitCode" scope="page"/>
			        </td>
			        <td class="label">处理方式*</td>
		            <td class="content">
		              <eoms:id2nameDB id="${baselink.linkDealMethod}" beanId="ItawSystemDictTypeDao"/>
		        </td>
			   </tr>
				<tr>
				    <td class="label">业务接入网元</td>
			        <td colspan='3'> 
			         <bean:write name="baselink" property="linkBusiInputNet" scope="page"/>
			        </td>

			   </tr>
				<tr>
				    <td class="label">中继端口数量*</td>
			        <td class="content">
			         <bean:write name="baselink" property="linkPointNumber" scope="page"/> 
			        </td>
			        <td class="label">信令端口数量*</td>
		            <td class="content">
		             <bean:write name="baselink" property="linkInfoPointNumber" scope="page"/>
			   		</td>
			   </tr>
			   <!-- 
		       <tr>
				    <td class="label">退回原因分类*</td>
			        <td colspan="3""> 
			             <eoms:comboBox   name="${sheetPageName}linkBackResonType" id="${sheetPageName}linkBackResonType"  initDicId="1013305" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkBackResonType}" />	     	
			        </td>
			   </tr> -->
			<tr>
			  	<td class="label">处理意见</td>
			    <td colspan="3">
			     <bean:write name="baselink" property="linkDealOpinition" scope="page"/>
			    </td> 
		     </tr>	
 
    </c:if>
</c:if>
<c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'OpenTask')}">
	<c:if test="${!empty baselink.operateType && (baselink.operateType == '74'||baselink.operateType == '11'||baselink.operateType == '111')}">
				<tr>
				    <td class="label">电路编号</td>
			        <td class="content"> 
			          <bean:write name="baselink" property="linkCircuitCode" scope="page"/>
			        </td>
			        <td class="label">电路调度结果*</td>
		            <td class="content">
		             <eoms:id2nameDB id="${baselink.linkIsDataOk}" beanId="ItawSystemDictTypeDao"/>
		        </td>
			   </tr>
				 
				<tr>
				    <td class="label">不成功原因</td>
			        <td colspan='3'> 
			          <bean:write name="baselink" property="linkNoAppledOpinition" scope="page"/>
			        </td>

			   </tr>
			  
			   <!-- 
		       <tr>
				    <td class="label">退回原因分类*</td>
			        <td colspan="3""> 
			             <eoms:comboBox   name="${sheetPageName}linkBackResonType" id="${sheetPageName}linkBackResonType"  initDicId="1013305" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkBackResonType}" />	     	
			        </td>
			   </tr> -->
			<tr>
			  	<td class="label">回复意见</td>
			    <td colspan="3">
			      <bean:write name="baselink" property="linkDealOpinition" scope="page"/>
			    </td> 
		     </tr>	
 
    </c:if>
</c:if>
<c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'DataMakeTask')}">
	<c:if test="${!empty baselink.operateType && (baselink.operateType == '75'||baselink.operateType == '55')}">
				<tr>
			        <td class="label">数据是否完成*</td>
		            <td colspan='3'>
		             <eoms:id2nameDB id="${baselink.linkIsDataOk}" beanId="ItawSystemDictTypeDao"/>
		        </td>
			   </tr>
			 
				<tr>
				    <td class="label">数据制作结果</td>
			        <td colspan='3'> 
			         <bean:write name="baselink" property="linkDateMakeResult" scope="page"/>
			        </td>

			   </tr>	
 
    </c:if>
</c:if>
<c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'BusiTestTask')}">
	<c:if test="${!empty baselink.operateType && (baselink.operateType == '76'||baselink.operateType == '11')}">
			<tr>
			    <td class="label">是否签收*</td>
		        <td class="content"> 
		         <eoms:id2nameDB id="${baselink.linkIsAppled}" beanId="ItawSystemDictTypeDao"/>
		        </td>
		        <td class="label">处理方式*</td>
		        <td class="content">
		         <eoms:id2nameDB id="${baselink.linkDealMethod}" beanId="ItawSystemDictTypeDao"/>
		        </td>
		    </tr>  
		    <tbody id="NoApplied" style="display:none">		   
			<tr>
			  	<td class="label">不签收意见</td>
			    <td colspan="3">
			     <bean:write name="baselink" property="linkNoAppledOpinition" scope="page"/>
			    </td> 
		    </tr>
		    </tbody>
		  	<tr>
			  	<td class="label">处理意见</td>
			    <td colspan="3">
			     <bean:write name="baselink" property="linkDealOpinition" scope="page"/>
			    </td> 
		    </tr> 			
 
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
			  				 	<bean:message bundle="businessimplementyy" key="businessimplementyy.yijiaoresion"/>
			  		    	</td>
			  		    	<td class="column-content" colspan="3">
	                          	<pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
			            	</td>                  
			  		 </tr>       		  		 
	</c:if>

    <c:if test="${!empty baselink.operateType && baselink.operateType == '10'}">
    
					  <tr>  				
			  		    	<td class="column-title">
			  				 	<bean:message bundle="businessimplementyy" key="businessimplementyy.fenpairesion"/>
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
		url: "businessimplementyy.do?method=getJsonLink&id="+id+"&beanName=BusinessImplementYY",
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
