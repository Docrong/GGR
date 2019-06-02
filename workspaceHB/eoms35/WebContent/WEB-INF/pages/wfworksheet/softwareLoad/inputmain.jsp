<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%

 request.setAttribute("roleId","249"); 

long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
 
<%@ include file="/WEB-INF/pages/wfworksheet/softwareLoad/baseinputmainhtmlnew.jsp"%>
<script type="text/javascript">
			function onTdChange(tx){
				var value = tx.value;
				
				if(value=='101150301'){
					document.getElementById('mainSoftwareNoLabel').style.display='none';
					document.getElementById('mainSoftwareNoText').style.display='none';
					document.getElementById('mainSoftwareversionLabel').style.display='';
					document.getElementById('mainSoftwareversionText').style.display='';
				}else{
					document.getElementById('mainSoftwareNoLabel').style.display='';
					document.getElementById('mainSoftwareNoText').style.display='';
					document.getElementById('mainSoftwareversionLabel').style.display='none';
					document.getElementById('mainSoftwareversionText').style.display='none';
				}
			}
			</script>
	<input type="hidden" name="${sheetPageName}processTemplateName" value="SoftwareLoadProcess" />
	<input type="hidden" name="${sheetPageName}sheetTemplateName" value="SoftwareLoadProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newWorkSheet" />
	<c:if test="${status!=1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DevelopTask" />
       <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${operateType}" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iSoftwareLoadMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.softwareLoad.model.SoftwareLoadMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.softwareLoad.model.SoftwareLoadLink"/>
    <br>
	<!-- sheet info --> 
     <table class="formTable">
         <tr>
		  <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
		  <td class="content">
		    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
				id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
				onclick="popUpCalendar(this, this)" alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/> 
	  			  
		  </td>				
		  <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
		  <td class="content">
		    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
				id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
				onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>   
			
		  </td>
		  </tr>
		  
		  
		  
		  <tr>
		  <td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.mainSoftwareType"/>*</td>
		  <td class="content">
	  			<eoms:comboBox name="${sheetPageName}mainSoftwareType" id="${sheetPageName}mainSoftwareType" 
            	      initDicId="1011503"  alt="allowBlank:false"  defaultValue="101150301" styleClass="select-class" onchange="onTdChange(this)"/>  
		  </td>				
		  <td class="label" id="mainSoftwareversionLabel" style="display: "><bean:message bundle="softwareLoad" key="softwareLoad.mainSoftwareversion"/>*</td>
		  <td class="content" id="mainSoftwareversionText" style="display: ">
		    <input type="text" class="text" value="${sheetMain.mainSoftwareversion }" name="${sheetPageName}mainSoftwareversion" /> 
	  			  
		  </td>
		  <td class="label" id="mainSoftwareNoLabel" style="display: none;"><bean:message bundle="softwareLoad" key="softwareLoad.mainSoftwareNo"/>*</td>
		  <td class="content" id="mainSoftwareNoText" style="display: none;">
		    <input type="text" class="text" value="${sheetMain.mainSoftwareNo }" name="${sheetPageName}mainSoftwareNo" /> 
	  			  
		  </td>	
		  
		  </tr>
		  <tr>
		  
		  <td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.mainNetwork"/>*</td>
		  <td class="content">
		    <input type="text" class="text" name="${sheetPageName}mainNetwork" value="${sheetMain.mainNetwork }" alt="allowBlank:false"  /> 
	  			  
		  </td>					
		  </tr>
		  <tr>
				  <td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.mainFactory"/>*</td>
				  <td class="content" colspan="3">
						    <div id="factoryview" class="hide"></div>
							    <script type="text/javascript"> 
					            //viewer
								var factoryViewer = new Ext.JsonView("factoryview",
									'<div class="viewlistitem-{nodeType}">{name}</div>',
									{ 
										emptyText : '<div>没有选择项目</div>'
								});
													 
								//area tree
					            var	factoryTreeAction='${app}/xtree.do?method=dict';
					            factoryTree = new xbox({
				
				    	          btnId:'${sheetPageName}showFactory',
				
				    	          treeDataUrl:factoryTreeAction,treeRootId:'1010103',treeRootText:'设备厂家',treeChkMode:'',treeChkType:'dict',
				    	          showChkFldId:'${sheetPageName}showFactory',saveChkFldId:'${sheetPageName}mainFactory',viewer:factoryViewer
					            });
				               </script>
				      <textarea class="textarea max" readonly="true" name="${sheetPageName}showFactory" style="height:50px" id="${sheetPageName}showFactory"  alt="allowBlank:false,maxLength:250,vtext:'请填入设备厂家，最多输入125个汉字'"><c:forTokens items="${sheetMain.mainFactory}" delims="," var="mainFactory" varStatus="status"><eoms:id2nameDB id="${mainFactory}" beanId="ItawSystemDictTypeDao"/><c:choose><c:when test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>              
				      <input type="hidden" name="${sheetPageName}mainFactory" id="${sheetPageName}mainFactory" value="${sheetMain.mainFactory}"/>			  
				  </td>
		  </tr>
		  <tr>
		  <td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.mainPlans"/>*</td>
		  <td colspan="3">
			<div id="areaview" class="hide"></div>
					    <script type="text/javascript"> 
			            //viewer
						var areaViewer = new Ext.JsonView("areaview",
							'<div class="viewlistitem-{nodeType}">{name}</div>',
							{ 
								emptyText : '<div>没有选择项目</div>'
						}
						);
						var data = "[]";
						areaViewer.jsonData = eoms.JSONDecode(data);
						areaViewer.refresh();
											 
						//area tree
			            var	deptTreeAction='${app}/xtree.do?method=areaTree';
			            deptetree = new xbox({
		
		    	          btnId:'${sheetPageName}showDept',dlgId:'dlg3',
		
		    	          treeDataUrl:deptTreeAction,treeRootId:'-1',treeRootText:'地市',treeChkMode:'',treeChkType:'area',
		    	          showChkFldId:'${sheetPageName}showDept',saveChkFldId:'${sheetPageName}mainPlans',viewer:areaViewer
			            });
		               </script>
		               
		               
		      <textarea class="textarea max" readonly="true" name="${sheetPageName}showDept" style="height:50px" id="${sheetPageName}showDept"  alt="allowBlank:false,maxLength:50,vtext:'请填入地市，最多输入50字符'"><c:forTokens items="${sheetMain.mainPlans}" delims="," var="mainPlans" varStatus="status"><eoms:id2nameDB id="${mainPlans}" beanId="tawSystemAreaDao"/><c:choose><c:when test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>              
		      <input type="hidden" name="${sheetPageName}mainPlans" id="${sheetPageName}mainPlans" value="${sheetMain.mainPlans}"/>			  
		  </td>
  		  </tr>
		  <tr>
		  <td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.mainProgramtime"/>*</td>
		  <td class="content">
		   <input type="text" class="text" name="${sheetPageName}mainProgramtime" readonly="readonly" 
				id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.mainProgramtime)}" 
				onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'计划装载时间不能早于受理时限',allowBlank:false"/>   
			
	  			  
		  </td>				
		  </tr>
		  <tr>
		  <td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.maintestunit"/>*</td>
		  <td class="content">
		    <input type="text" class="text" name="${sheetPageName}maintestunit" value="${sheetMain.maintestunit }" alt="allowBlank:false" /> 
	  			  
		  </td>	
		  <td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.mainUrgency"/>*</td>
		  <td class="content">
		  <eoms:comboBox name="${sheetPageName}mainUrgency" id="${sheetPageName}mainUrgency" 
            	      initDicId="1010102"  alt="allowBlank:false"  defaultValue="101010202" styleClass="select-class"/>  
		  
	  			  
		  </td>				
		  </tr>
		  <tr>
		  			
		  <td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.mainPointName"/>*</td>
		  <td class="content">
		    <input type="text" class="text" value="${sheetMain.mainPointName }" name="${sheetPageName}mainPointName" alt="allowBlank:false"  /> 
	  			  
		  </td>
		  <td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.mainissuing"/>*</td>
		  <td class="content">
	  		 <eoms:comboBox name="${sheetPageName}mainissuing" id="${sheetPageName}mainissuing" 
            	      initDicId="1011504"  alt="allowBlank:false"  defaultValue="101150401" styleClass="select-class"/>  
		  	  
		  </td>	
		  </tr>
		  <tr>
		  <td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.mainpointweights"/>*</td>
		  <td class="content">
		    <input type="text" class="text" value="${sheetMain.mainpointweights }" name="${sheetPageName}mainpointweights" alt="allowBlank:false"  /> 
	  			  
		  </td>				
		  <td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.mainApplication"/>*</td>
		  <td class="content">
		    <input type="text" class="text" name="${sheetPageName}mainApplication" value="${sheetMain.mainApplication }"  alt="allowBlank:false"  /> 
	  			  
		  </td>	
		  </tr>
		  <tr>
		  	<td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.mainKeyword"/>*</td>
		  	<td class="content" colspan="3">
		   		 <textarea class="textarea max" name="${sheetPageName}mainKeyword" id="${sheetPageName}mainKeyword" alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入影响业务分析，最多输入2000字'">${sheetMain.mainKeyword}</textarea>
		  	</td>	
		  </tr>
		  <tr>
		  <td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.mainFunction"/>*</td>
		  <td class="content" colspan="3">
		    <textarea class="textarea max" name="${sheetPageName}mainFunction" id="${sheetPageName}mainFunction" alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入影响业务分析，最多输入2000字'">${sheetMain.mainFunction}</textarea> 
	  			  
		  </td>	
		  </tr>
		  <tr>
		  <td class="label"><bean:message bundle="softwareLoad" key="softwareLoad.mainNotes"/>*</td>
		  <td class="content" colspan="3">
		    <textarea class="textarea max" name="${sheetPageName}mainNotes" id="${sheetPageName}mainNotes" alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入影响业务分析，最多输入2000字'">${sheetMain.mainNotes}</textarea>
	  			  
		  </td>				
		  </tr>
		 <tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			</td>	
			<td colspan="3">					
		    <eoms:attachment name="tawSheetAccess" property="accesss" 
		            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
		    </td>
		  </tr>		
		   <tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="mainForm.accessories"/>
			</td>	
			<td colspan="3">					

		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="${sheetPageName}sheetAccessories" appCode="commontask" /> 				
		    </td>
		  </tr>	 			    
	</table>
	<c:if test="${status!=1}">
	<fieldset id="link1">
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName">:方案制定
			 </span>
	      </legend>
	        <div class="x-form-item" >
			<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',childType:'user,dept',limit:'none',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			   panels="[{text:'部门与人员',dataUrl:'/xtree.do?method=userFromDept'},{text:'个性化部门树',dataUrl:'/sheet/userdefinegroup/userdefinegroup.do?method=showTree&flowId=1'}]"
			   data="${sendUserAndRoles}"/>			   
			 </div>
	</fieldset>
	</c:if>
