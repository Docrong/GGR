<script type="text/javascript">

//dept tree
    	var	deptTreeAction='${app}/xtree.do?method=dept';
    	deptetree = new xbox({
    		btnId:'${sheetPageName}showDept',dlgId:'dlg3',
    		treeDataUrl:deptTreeAction,treeRootId:'-1',treeRootText:'Dept',treeChkMode:'single',treeChkType:'dept',
    		showChkFldId:'${sheetPageName}showDept',saveChkFldId:'${sheetPageName}toDeptId'
    	});
 </script>
<%
 String roleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
 System.out.println("roleId===="+roleId);
 %>
		<!-- base info -->

		<table width="640">
		<tr >
		 <td colspan="2">
		   <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.title"/></label>
            <div class="x-form-element">
                <input type="text" name="${sheetPageName}title" id="${sheetPageName}title" value="${sheetMain.title}" alt="width:'500px',allowBlank:false"/>
            </div>
           </div>
		 </td>
		</tr>
		<tr>
		 <td width="320">
		  <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.sendUserName"/></label>
            <div class="x-form-element">
            <input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetMain.id}"/>
            <input type="hidden" name="${sheetPageName}sheetKey" id="${sheetPageName}sheetKey" value="${sheetMain.id}"/>
            <input type="hidden" name="${sheetPageName}mainId" id="${sheetPageName}mainId" value="${sheetMain.id}"/>
            <input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
            <input type="hidden" name="${sheetPageName}sendUserId" id="${sheetPageName}sendUserId" value="${sheetMain.sendUserId}"/>
            <input type="hidden" name="${sheetPageName}sendOrgType" id="${sheetPageName}sendOrgType" value="${sheetMain.sendOrgType}"/>
            <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="${status}"/>
            <input type="hidden" name="${sheetPageName}aiid" id="${sheetPageName}aiid" value="${taskId}"/>
		    <input type="hidden" name="${sheetPageName}piid" id="${sheetPageName}piid" value="${piid}"/>
		    <input type="hidden" name="${sheetPageName}activeTemplateId" id="${sheetPageName}activeTemplateId" value="${taskName}"/>		    
		    
		    
		    <input type="hidden" name="${sheetPageName}correlationKey" id="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>
		    
		    <input type="hidden" name="${sheetPageName}parentCorrelation" id="${sheetPageName}parentCorrelation" value="${parentCorrelation}"/>
		    <input type="hidden" name="${sheetPageName}parentSheetName" id="${sheetPageName}parentSheetName" value="${parentSheetName}"/>
		    <input type="hidden" name="${sheetPageName}parentSheetId" id="${sheetPageName}parentSheetId" value="${parentSheetId}"/>
		    <input type="hidden" name="${sheetPageName}operateUserId" id="${sheetPageName}operateUserId" value="${sheetMain.sendUserId}"/>
		    <input type="hidden" name="${sheetPageName}operateDeptId" id="${sheetPageName}operateDeptId" value="${sheetMain.sendDeptId}"/>
		    <input type="hidden" name="${sheetPageName}tkid" value="${tkid}"/>
		    <input type="hidden" name="${sheetPageName}preLinkId" value="${preLink.id}"/>
		    <input type="hidden" name="${sheetPageName}operateType" value="${preLink.operateType}"/>
		    <input type="hidden" name="${sheetPageName}taskCompleteTime" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"/>
            <eoms:id2nameDB id="${sheetMain.sendUserId}" beanId="tawSystemUserDao"/>
            </div>
          </div>
		 </td>
		<td>
				<div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.sendDeptName"/></label>
                  <div class="x-form-element">
                      <input type="hidden" name="${sheetPageName}sendDeptId" value="${sheetMain.sendDeptId}"/>
                        <eoms:id2nameDB id="${sheetMain.sendDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
                  </div>
               </div>
		</td>
		</tr>
		<tr>
		<td colspan="2">
		        <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.sendRoleName"/></label>
                   <div class="x-form-element">
                    <c:if test="${sheetMain.sendRoleId==''}">  
                      <eoms:roleComboBox name="${sheetPageName}sendRoleId" id="${sheetPageName}sendRoleId" userId="${sheetMain.sendUserId}" roleId="${roleId}" defaultValue="${sheetMain.sendRoleId}"/>     
                    </c:if>
                    <c:if test="${sheetMain.sendRoleId!=''}">
                       <input type="hidden" name="${sheetPageName}sendRoleId" id="${sheetPageName}sendRoleId" value="${sheetMain.sendRoleId}"/>
                       <c:if test="${endRoleId==''}">                       
		                   <input type="hidden" name="${sheetPageName}operateRoleId" id="${sheetPageName}operateRoleId" value="${sheetMain.sendRoleId}"/>                       
		               </c:if>
		               <c:if test="${endRoleId!=''}">                       
		                   <input type="hidden" name="${sheetPageName}operateRoleId" id="${sheetPageName}operateRoleId" value="${endRoleId}"/>                       
		               </c:if>
                       <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemSubRoleDao"/>
                     </c:if>
                   </div>
                </div>
		   </td>
		</tr>
		<c:if test="${status!=1}">
		<tr>
		<td >
			<div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.sendContact"/></label>
            <div class="x-form-element">
                 <input type="text" name="${sheetPageName}sendContact" id="${sheetPageName}sendContact" value="${sheetMain.sendContact}" alt="allowBlank:false"/>
            </div>
        	</div>
		</td>
		<td>
		 
		 <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.toDeptId"/></label>
		 	<div class="x-form-element">
				<input type="text"  readonly="readonly" name="${sheetPageName}showDept" id="${sheetPageName}showDept" value="<eoms:id2nameDB id="${sheetMain.toDeptId}" beanId="tawSystemDeptDao"/>"/>
				<input type="hidden" name="${sheetPageName}toDeptId" id="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
			</div>
        </div>
      
		</td>
		</tr>
		</c:if>
		<c:if test="${status==1}">
		<tr>
		<td colspan="2">
			<div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.sendContact"/></label>
            <div class="x-form-element">
                 <input type="text" name="${sheetPageName}sendContact" id="${sheetPageName}sendContact" value="${sheetMain.sendContact}" alt="allowBlank:false"/>
                 <input type="hidden" name="${sheetPageName}showDept" id="${sheetPageName}showDept" value="<eoms:id2nameDB id="${sheetMain.toDeptId}" beanId="tawSystemDeptDao"/>"/>
                 <input type="hidden" name="${sheetPageName}toDeptId" id="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
            </div>
        	</div>
		</td>
		
		</tr>
		</c:if>
		<tr>
		<td>
		        <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.completeLimit"/></label>
            <div class="x-form-element">
                <input type="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" alt="timer:true"/>
            </div>
        </div>
		</td>
		</tr>
		</table>

     <c:if test="${status==1}">

            <table width="640">
              <tr>
                 <td width="320">
                   <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.endUserName"/></label>
                      <div class="x-form-element">
                       <input type="hidden" name="${sheetPageName}endUserId" value="${endUserId}"/>
                       <eoms:id2nameDB id="${endUserId}" beanId="tawSystemUserDao"/>&nbsp;
                      </div>
                      </div>
                 </td>
                  <td>
		              <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.endDeptName"/></label>
                         <div class="x-form-element">
                            <input type="hidden" name="${sheetPageName}endDeptId" value="${endDeptId}"/>
                           <eoms:id2nameDB id="${endDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
                         </div>
                     </div>
                 </td>
              </tr>
            <tr>
               <td colspan="2">
                  <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.endRoleName"/></label>
                    <div class="x-form-element">
                      <input type="hidden" name="${sheetPageName}endRoleId" value="${endRoleId}"/>
                     <eoms:id2nameDB id="${endRoleId}" beanId="tawSystemSubRoleDao"/>&nbsp;
                    </div>
                  </div>
               </td>
            </tr>
            <tr>
              <td colspan="2">
                  <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.holdStatisfied"/></label>
                    <div class="x-form-element">
                      <eoms:comboBox name="${sheetPageName}holdStatisfied" id="${sheetPageName}holdStatisfied" initDicId="10303" />
                    </div>
                 </div>
              </td>
            </tr>
            <tr>
            <td colspan="2">
             <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.endResult"/></label>
              <div class="x-form-element">
                <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" alt="width:'500px',allowBlank:false"></textarea>
              </div>
		  </div>
            </td>
            </tr>
            </table>

     </c:if>
     
     <c:if test="${status==-1}">
 
            <legend><bean:message bundle="sheet" key="mainForm.holdStatisfied"/></legend>
            <table width="640">
              <tr>
                 <td width="320">
                   <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.endUserName"/></label>
                      <div class="x-form-element">
                       <input type="hidden" name="${sheetPageName}endUserId" value="${endUserId}"/>
                      <eoms:id2nameDB id="${sheetMain.endUserId}" beanId="tawSystemUserDao"/>&nbsp;
                      </div>
                      </div>
                 </td>
                  <td>
		              <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.endDeptName"/></label>
                         <div class="x-form-element">
                            <input type="hidden" name="${sheetPageName}endDeptId" value="${endDeptId}"/>
                           <eoms:id2nameDB id="${endDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
                         </div>
                     </div>
                 </td>
              </tr>
            <tr>
               <td colspan="2">
                  <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.endRoleName"/></label>
                    <div class="x-form-element">
                      <input type="hidden" name="${sheetPageName}endRoleId" value="${endRoleId}"/>
                      <eoms:id2nameDB id="${endRoleId}" beanId="tawSystemSubRoleDao"/>&nbsp;
                    </div>
                  </div>
               </td>
            </tr>
            <tr>
               <td colspan="2">
                  <div class="x-form-item"><label><bean:message bundle="sheet" key="mainForm.cancelReason"/></label>
                     <div class="x-form-element">               
                       <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" alt="width:'500px',allowBlank:false"></textarea>
                     </div>
		          </div>
               </td>
            </tr>
            </table>

     </c:if>