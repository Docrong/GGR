<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<% 
 request.setAttribute("roleId","318");
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<script type="text/javascript">
   	selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "itrequirement.do?method=newShowLimit&flowName=ITRequirementProcess",
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	   //$("${sheetPageName}sheetAcceptLimit").value = "";
        	    //$('${sheetPageName}sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	        	var dt1 = new Date(times).add(Date.MINUTE,parseInt(o.acceptLimit,10));
	        	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	$("${sheetPageName}sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('${sheetPageName}sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/itrequirement/baseinputmainhtmlnew.jsp"%> 


	<input type="hidden" name="${sheetPageName}processTemplateName" value="ITRequirementProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newSheet" />
	<input type="hidden" name="${sheetPageName}sheetTemplateName" value="ITRequirementProcess" />
	<c:if test="${status==0}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask" />
	   <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${operateType}" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iITRequirementMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.itrequirement.model.ITRequirementMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.itrequirement.model.ITRequirementLink"/><br/>
	<!-- sheet info -->
     <table id=sheet class="formTable">
      <c:if test="${status!=1}">
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
		                 <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.mainNetSystem"/></td>
		                <td class="content">  
				        <eoms:comboBox name="${sheetPageName}mainNetSystem" id="${sheetPageName}mainNetSystem" 
            	      initDicId="1011404"  alt="allowBlank:true" styleClass="select-class" defaultValue="${sheetMain.mainNetSystem}"/>
			        </td>	                

		              <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.mainUrgentDegree"/>*</td>
		                <td class="content">  
				        <eoms:comboBox name="${sheetPageName}mainUrgentDegree" id="${sheetPageName}mainUrgentDegree" 
            	      initDicId="1010102"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetMain.mainUrgentDegree}"/>
			        </td>	                
		            </tr>


			      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.mainSheetID"/></td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}mainSheetID" id="${sheetPageName}mainSheetID" value="${sheetMain.mainSheetID}" alt="allowBlank:true"/></td>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.mainBusinessTarget"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}mainBusinessTarget" id="${sheetPageName}mainBusinessTarget" value="${sheetMain.mainBusinessTarget}" alt="allowBlank:false,maxLength:50,vtext:'请填入业务目标，最多输入25字'"/></td>
		          </tr>

			      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.mainUser"/>*</td>
		           <!--  <td  class="content"> <input type="text"  class="text" name="${sheetPageName}mainUser" id="${sheetPageName}mainUser" value="${sheetMain.mainUser}" alt="allowBlank:false,maxLength:50,vtext:'请填入使用人员，最多输入25字'"/></td>-->
		               <td>
						<div id="areaview" class="hide"></div>
								    <script type="text/javascript"> 
						            //viewer
									var areaViewer = new Ext.JsonView("areaview",
										'<div class="viewlistitem-{nodeType}">{name}</div>',
										{ 
											emptyText : '<div>没有选择项目</div>'
									}
									);
									var data = "[{id:'${sheetMain.mainUser}',name:'<eoms:id2nameDB id='${sheetMain.mainUser}' beanId='tawSystemUserDao'/>',nodeType:'user'}]";
									areaViewer.jsonData = eoms.JSONDecode(data);
									areaViewer.refresh();
									 
									//area tree
						            var	deptTreeAction='${app}/xtree.do?method=userFromDept';
						            deptetree = new xbox({
					
					    	          btnId:'${sheetPageName}showDept',dlgId:'dlg3',
					
					    	          treeDataUrl:deptTreeAction,treeRootId:'-1',treeRootText:'人员',treeChkMode:'single',treeChkType:'user',
					    	          showChkFldId:'${sheetPageName}showDept',saveChkFldId:'${sheetPageName}mainUser',viewer:areaViewer
						            });
					               </script>
					               <input type="text" class="text"  readonly="readonly" name="${sheetPageName}showDept" id="${sheetPageName}showDept" alt="allowBlank:false,vtext:'请选择人员'" value="<eoms:id2nameDB id='${sheetMain.mainUser}' beanId='tawSystemUserDao'/>"/>
					               <input type="hidden" name="${sheetPageName}mainUser" id="${sheetPageName}mainUser" value="${sheetMain.mainUser}"/>			  
					  </td> 

 					<td class="label"><bean:message bundle="itrequirement" key="itrequirement.mainCompleteTime"/></td>
	   				
	   				
				  <td class="content">
				    <input type="text" class="text" name="${sheetPageName}mainCompleteTime" readonly="readonly" 
						id="${sheetPageName}mainCompleteTime" value="${eoms:date2String(sheetMain.mainCompleteTime)}" 
						 onclick="popUpCalendar(this, this)" alt="true"/>   
					
				  </td>
		            </tr>

			      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.mainRequirementDesc"/>*</td>
		              <td class="content" colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}mainRequirementDesc" id="${sheetPageName}mainRequirementDesc" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入需求概述，最多输入125字'">${sheetMain.mainRequirementDesc}</textarea>
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
  						<td class="label"><bean:message bundle="itrequirement" key="itrequirement.mainRequirementDetail"/>*</td>
		   				 <td  class="content" colspan='3'>
		   				 <eoms:attachment name="sheetMain" property="mainRequirementDetail" idList=""
            			  scope="request" idField="${sheetPageName}mainRequirementDetail" appCode="itrequirementsheet" 
            			  alt="allowBlank:false"/> 
		                </td>
		            </tr>

     </c:if>
	</table>
	<br/>
	<c:if test="${status!=1}">
	<fieldset id="link1">
	      <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			 <bean:message bundle="itrequirement" key="role.applyleader"/>
	      </legend>
	  		<eoms:chooser id="test1" type="role" roleId="319" flowId="091" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},
			              {id:'copyPerformer',childType:'user,subrole',allowBlank:true,limit:'none',text:'抄送',vtext:'请选择抄送对象'}]"
			   data="${sendUserAndRoles}"/>
     </fieldset>
     
	</c:if>
	<script type="text/javascript">
	    getparentsheetname();
   function getparentsheetname(){  
        if('${parentProcessName}'!=null&&'${parentProcessName}'!=''){
   				document.all.${sheetPageName}mainSheetID.value='${parentSheetId}';
          }
         }
</script>	
