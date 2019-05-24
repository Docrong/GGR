		<!-- base info -->
		    <input type="hidden" name="${sheetPageName}linkId" id="${sheetPageName}linkId" value="${sheetLink.id}"/>
		    <input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetLink.id}"/>
		    <input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
			<input type="hidden" name="${sheetPageName}piid" value="${sheetMain.piid}"/>
		    <input type="hidden" name="${sheetPageName}aiid" value="${sheetLink.tkid}"/>
		    <input type="hidden" name="${sheetPageName}activeTemplateId" value="${taskName}"/>
		    <input type="hidden" name="${sheetPageName}mainId" value="${sheetMain.id}"/>
		    <input type="hidden" name="${sheetPageName}sheetKey" value="${sheetMain.id}"/>
		    <input type="hidden" name="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>		    
		    <input type="hidden" name="${sheetPageName}TKID" value="${sheetLink.tkid}"/>
		    <input type="hidden" name="${sheetPageName}preLinkId" value="${preLink.id}"/>
		    <input type="hidden" name="${sheetPageName}taskCompleteLimit" id="${sheetPageName}taskCompleteLimit" value="${eoms:date2String(preLink.nodeCompleteLimit)}"/>
		    <input type="hidden" name="${sheetPageName}taskAcceptLimit" id="${sheetPageName}taskAcceptLimit" value="${eoms:date2String(preLink.nodeAcceptLimit)}"/>
		    <input type="hidden" name="${sheetPageName}taskName" value="${taskName}"/>
		   <input type="hidden" name="${sheetPageName}taskStatus" value="${taskStatus}"/>
		   <input type="hidden" name="sheetPageName" id="sheetPageName" value="${sheetPageName}"/>
           <input type="hidden" name="methodBeanId"  id="methodBeanId" value="${methodBeanId}"/>
		<table class="listTable">
		  <caption><bean:message bundle="sheet" key="linkForm.header"/></caption>
		  <tr>
		   <td class="label">
		     <bean:message bundle="sheet" key="linkForm.operateUserName"/>*
             <input type="hidden" name="${sheetPageName}operateUserId" value="${sheetLink.operateUserId}"/>
           </td>
           <td class="content">
              <eoms:id2nameDB id="${sheetLink.operateUserId}" beanId="tawSystemUserDao"/>&nbsp;
           </td>
		   <td class="label">		     
             <bean:message bundle="sheet" key="linkForm.operateDeptName"/>*
             <input type="hidden" name="${sheetPageName}operateDeptId" value="${sheetLink.operateDeptId}"/>
           </td>
           <td class="content"> 
                 <eoms:id2nameDB id="${sheetLink.operateDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
           </td>
		  </tr>
		  
		  
		  <tr>
		    <td class="label">
		       <bean:message bundle="sheet" key="linkForm.operateRoleName"/>*
                  <input type="hidden" name="${sheetPageName}operateRoleId" value="${sheetLink.operateRoleId}"/>
               </td>
               <td colspan="3">
               <eoms:id2nameDB id="${sheetLink.operateRoleId}" beanId="tawSystemSubRoleDao"/>
               <eoms:id2nameDB id="${sheetLink.operateRoleId}" beanId="tawSystemUserDao"/>&nbsp;
               <eoms:id2nameDB id="${sheetLink.operateRoleId}" beanId="tawSystemUserDao"/>&nbsp;
           </td>
		  </tr>
		  
		  <tr>
		   <td class="label">
		     <bean:message bundle="sheet" key="linkForm.operaterContact"/> *           
           </td>
           <td class="content">
              <input type="text" class="text" name="${sheetPageName}operaterContact" 
                id="${sheetPageName}operaterContact" value="${sheetLink.operaterContact}" alt="allowBlank:false"/>
           </td>
		   <td class="label">		     
             <bean:message bundle="sheet" key="linkForm.operateTime"/>*
           </td>
           <td class="content"> 
            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
               name="${sheetPageName}operateTime" readonly="readonly" 
                  value="${eoms:date2String(sheetLink.operateTime)}" id="${sheetPageName}operateTime" alt="allowBlank:false"/>
           </td>
		  </tr>
		  
		   <tr>
		   <td class="label">
		     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
           </td>
           <td class="content">
              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="allowBlank:false"/>
          
           </td>
		   <td class="label">		     
             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
           </td>
           <td class="content"> 
            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="allowBlank:false"/>
           </td>
		  </tr>
		  
		  </table>     
