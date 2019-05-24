<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/pages/wfworksheet/resourceaffirm/baseinputmainhtmlnew.jsp"%>
<%
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<script type="text/javascript">
   	selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "resourceaffirm.do?method=newShowLimit&flowName=ResourceAffirmProcess",
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
	<input type="hidden" name="${sheetPageName}processTemplateName" value="ResourceAffirmMainFlowProcess" />
	<input type="hidden" name="${sheetPageName}sheetTemplateName" value="ResourceAffirmProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newSheet" />
	<c:if test="${status==0}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCreateAuditHumTask" />
	   <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
	   <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iResourceAffirmMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.resourceaffirm.model.ResourceAffirmMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.resourceaffirm.model.ResourceAffirmLink"/>
	<!-- sheet info -->
	<br/>
     <table class="formTable">

	                <tr>
	                <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.sheetacceptlimit"/>*</td>
		            <td> 
		            <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
					   id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
					   defaultValue="${sheetMain.sheetAcceptLimit}" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
					onclick="popUpCalendar(this, this)"/> </td>
		          	<td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.sheetcompletelimit"/>*</td>
		            <td >  <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
					  id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
					  onclick="popUpCalendar(this, this)"
					  defaultValue="${sheetMain.sheetCompleteLimit}"/>   </td>
	                </tr>
	                
	                <tr>
		                <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.urgentDegree"/>*</td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}urgentDegree" id="${sheetPageName}urgentDegree" 
            	      initDicId="1010102"  alt="allowBlank:true" defaultValue="${sheetMain.urgentDegree}" alt="allowBlank:false" styleClass="select-class"/>
			        	</td>
		                <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.businesstype1"/>*</td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}btype1" id="${sheetPageName}btype1" 
            	      initDicId="1011201"  alt="allowBlank:true" defaultValue="${sheetMain.btype1}" alt="allowBlank:false" styleClass="select-class"/>
			        </td>	                
		            </tr>

	               <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.bdeptContact"/>&nbsp;&nbsp;*</td>
		                <td>  
				        <input type="text"  class="text" name="${sheetPageName}bdeptContact" id="${sheetPageName}bdeptContact" 
				        value="${sheetMain.bdeptContact}"  alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入业务部门联系人，最大长度为100个汉字！')}'" defaultValue="${sheetMain.bdeptContactPhone}"/>
			        </td>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.bdeptContactPhone"/>&nbsp;&nbsp;*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}bdeptContactPhone" id="${sheetPageName}bdeptContactPhone"  value="${sheetMain.bdeptContactPhone}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入业务部门联系人电话，最大长度为100个汉字！')}'" defaultValue="${sheetMain.bdeptContactPhone}"/></td>
		          </tr>

			      <tr>
		          	<td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.customNo"/>&nbsp;&nbsp;*</td>
		            <td > <input type="text"  class="text" name="${sheetPageName}customNo" id="${sheetPageName}customNo" value="${sheetMain.customNo}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入集团客户编号，最大长度为100个汉字！')}'" defaultValue="${sheetMain.customNo}"/></td>
		          	<td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.customName"/></td>
		            <td > <input type="text"  class="text" name="${sheetPageName}customName" 
		                    id="${sheetPageName}customName"  value="${sheetMain.customName}" alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户名称，最大长度为100个汉字！')}'" value="${sheetMain.customName}" defaultValue="${sheetMain.customName}"/></td>
		          </tr>

			      <tr>
		          	<td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.customContact"/></td>
		            <td> <input type="text"  class="text" name="${sheetPageName}customContact" id="${sheetPageName}customContact" value="${sheetMain.customContact}" alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户联系人，最大长度为100个汉字！')}'"  defaultValue="${sheetMain.customContact}"/></td>
		          	<td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.customContactPhone"/></td>
		            <td> <input type="text"  class="text" name="${sheetPageName}customContactPhone" id="${sheetPageName}customContactPhone" value="${sheetMain.customContactPhone}" alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户联系人电话，最大长度为100个汉字！')}'"   defaultValue="${sheetMain.customContactPhone}"/></td>
		          </tr>

			    

        <tr>
	          <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.cityName"/></td>
	          <td > <input type="text"  class="text" name="${sheetPageName}cityName" id="${sheetPageName}cityName" value="${sheetMain.cityName}"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入所属地市，最大长度为100个汉字！')}'"/></td>
	       
	          <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.countyName"/></td>
	          <td > <input type="text"  class="text" name="${sheetPageName}countyName" id="${sheetPageName}countyName" value="${sheetMain.countyName}" alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入所属区县，最大长度为100个汉字！')}'"/></td>
        </tr>
        
         <tr>
	          <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.cmanagerPhone"/></td>
	          <td > <input type="text"  class="text" name="${sheetPageName}cmanagerPhone" id="${sheetPageName}cmanagerPhone" value="${sheetMain.cmanagerPhone}"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入客户经理，最大长度为100个汉字！')}'"/></td>
	       
	          <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.cmanagerContactPhone"/></td>
	          <td > <input type="text"  class="text" name="${sheetPageName}cmanagerContactPhone" id="${sheetPageName}cmanagerContactPhone" value="${sheetMain.cmanagerContactPhone}" alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入客户经理联系电话，最大长度为100个汉字！')}'"/></td>
        </tr>

	      <tr>
	           <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.customLevel"/>*</td>
               <td>  
	        <eoms:comboBox name="${sheetPageName}customLevel" id="${sheetPageName}customLevel"  defaultValue="${sheetMain.customLevel}"
         	      initDicId="1010107"  alt="allowBlank:false" styleClass="select-class"/>
              </td>
	           <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.productName"/></td>
	          <td > <input type="text"  class="text" name="${sheetPageName}productName" id="${sheetPageName}productName" value="${sheetMain.productName}" alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入产品名称，最大长度为100个汉字！')}'"/></td>
	      
	      
	      </tr>
	      
	        <tr>
		          
		          	  <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.provinceName"/></td>
	          <td colspan='3'> <input type="text"  class="text" name="${sheetPageName}provinceName" id="${sheetPageName}provinceName" value="${sheetMain.provinceName}"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入所属省份，最大长度为100个汉字！')}'"/></td>
        </tr>
		          
		          
		     
		          <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.bandwidth"/>&nbsp;&nbsp;*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}bandwidth" id="${sheetPageName}bandwidth" defaultValue="${sheetMain.bandwidth}" value="${sheetMain.bandwidth}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入带宽，最大长度为100个汉字！')}'"/></td>
		          	<td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.Number"/>&nbsp;&nbsp;*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}numbere" id="${sheetPageName}numbere" defaultValue="${sheetMain.numbere}" value="${sheetMain.numbere}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入数量，最大长度为100个汉字！')}'"/></td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.cityA"/>&nbsp;&nbsp;*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}cityA" id="${sheetPageName}cityA" defaultValue="${sheetMain.cityA}" value="${sheetMain.cityA}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入城市A，最大长度为100个汉字！')}'"/></td>
		          	<td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.cityZ"/>&nbsp;&nbsp;*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}cityZ" id="${sheetPageName}cityZ" defaultValue="${sheetMain.cityZ}" value="${sheetMain.cityZ}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入城市Z，最大长度为100个汉字！')}'"/></td>
		          </tr>
					
				  <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.portA"/>&nbsp;&nbsp;*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}portA" id="${sheetPageName}portA" defaultValue="${sheetMain.portA}" value="${sheetMain.portA}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点A，最大长度为100个汉字！')}'"/></td>
		         	<td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.transfBusiness"/>*</td>
		                <td>  
				        <eoms:comboBox name="${sheetPageName}transfBusiness" id="${sheetPageName}transfBusiness" 
            	      initDicId="1011049"  alt="allowBlank:true" defaultValue="${sheetMain.transfBusiness}" alt="allowBlank:false" styleClass="select-class"/>
			        </td>
		         	</tr>
		         	<tr>
		         	<td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.isBeforehandOccupy"/>*</td>
		                <td colspan='3'>  
				        <eoms:comboBox name="${sheetPageName}isBeforehandOccupy" id="${sheetPageName}isBeforehandOccupy" 
            	      initDicId="1011017"  alt="allowBlank:true" defaultValue="${sheetMain.isBeforehandOccupy}" alt="allowBlank:false" styleClass="select-class"/><bean:message bundle="resourceaffirm" key="resourceaffirm.prompt"/>
			        </td>
		         
		          </tr>
					
				  <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.portAInterfaceType"/>&nbsp;&nbsp;*</td>
		            <td> 
					<eoms:comboBox name="${sheetPageName}portAInterfaceType" id="${sheetPageName}portAInterfaceType" 
            	      initDicId="1011016"  alt="allowBlank:false" defaultValue="${sheetMain.portAInterfaceType}" styleClass="select-class"/>
					</td>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.portAContactPhone"/>&nbsp;&nbsp;*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}portAContactPhone" id="${sheetPageName}portAContactPhone" defaultValue="${sheetMain.portAContactPhone}" value="${sheetMain.portAContactPhone}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端口A联系人及电话，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.portABDeviceName"/>&nbsp;&nbsp;*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}portABDeviceName" id="${sheetPageName}portABDeviceName" defaultValue="${sheetMain.portABDeviceName}" value="${sheetMain.portABDeviceName}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点A业务设备名称，最大长度为100个汉字！')}'"/></td>
		          	<td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.portABDevicePort"/>&nbsp;&nbsp;*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}portABDevicePort" id="${sheetPageName}portABDevicePort" defaultValue="${sheetMain.portABDevicePort}" value="${sheetMain.portABDevicePort}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点A业务设备端口，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.portADetailAdd"/>&nbsp;&nbsp;*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}portADetailAdd" id="${sheetPageName}portADetailAdd" defaultValue="${sheetMain.portADetailAdd}"  value="${sheetMain.portADetailAdd}"  alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点A详细地址，最大长度为100个汉字！')}'"/></td>
		          
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.portZ"/>&nbsp;&nbsp;*</td>
		            <td> 
		            <input type="text"  class="text" name="${sheetPageName}portZ" id="${sheetPageName}portZ" defaultValue="${sheetMain.portZ}" value="${sheetMain.portZ}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点Z，最大长度为100个汉字！')}'"/>
		            </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.portZInterfaceType"/>&nbsp;&nbsp;*</td>
		            <td> 
		             <eoms:comboBox name="${sheetPageName}portZInterfaceType" id="${sheetPageName}portZInterfaceType" 
            	      initDicId="1011016"  alt="allowBlank:false" defaultValue="${sheetMain.portZInterfaceType}" styleClass="select-class"/>
		            </td>
		          	<td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.portZContactPhone"/>&nbsp;&nbsp;*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}portZContactPhone" id="${sheetPageName}portZContactPhone" defaultValue="${sheetMain.portZContactPhone}" value="${sheetMain.portZContactPhone}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点Z联系人及电话，最大长度为100个汉字！')}'"/></td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.portZBDevicePort"/>&nbsp;&nbsp;*</td>
		            <td> <input type="text"  class="text" name="${sheetPageName}portZBDevicePort" id="${sheetPageName}portZBDevicePort" defaultValue="${sheetMain.portZBDevicePort}" value="${sheetMain.portZBDevicePort}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点Z业务设备端口，最大长度为100个汉字！')}'"/></td>
		          	<td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.portZBDeviceName"/>&nbsp;&nbsp;*</td>
		            <td  > <input type="text"  class="text" name="${sheetPageName}portZBDeviceName" id="${sheetPageName}portZBDeviceName" defaultValue="${sheetMain.portZBDeviceName}" value="${sheetMain.portZBDeviceName}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点Z详细地址，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          
			       <tr>
	            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.bRequirementDesc"/>*</td>
	            <td colspan="3"> 	
   				  <textarea class="textarea max" name="${sheetPageName}brequirementDesc" id="${sheetPageName}brequirementDesc" value="${sheetMain.brequirementDesc}" alt="width:500,allowBlank:false,maxLength:255,vtext:'${eoms:a2u('请输入业务需求描述，最大长度为125个汉字！')}'">${sheetMain.brequirementDesc}</textarea>
                 </td>
		     </tr>
		          
		          <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.applyInfo"/>&nbsp;&nbsp;*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}applyInfo" id="${sheetPageName}applyInfo"  alt="width:500,allowBlank:false,maxLength:255,vtext:'${eoms:a2u('请输入业务需求描述，最大长度为125个汉字！')}'">${sheetMain.applyInfo}</textarea>
                    </td>
		          </tr>
		 <tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="mainForm.accessories"/>
			</td>	
			<td colspan="3">					
			 <!--<eoms:attachment idList="" idField="${sheetPageName}sheetAccessories" appCode="netchange" />-->
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="${sheetPageName}sheetAccessories" appCode="resourceAffirm" /> 				
		    </td>
		  </tr>		
		          
	</table>
	<br/>
	<c:if test="${status!=1}">
	<fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName">:
			 </span>
	      </legend>
		
<eoms:chooser id="test" category="[{id:'dealPerformer',childType:'user',limit:'none',text:'${eoms:a2u('派发')}',allowBlank:false,vtext:'${eoms:a2u('请选择任务执行人')}'},{id:'auditPerformer',childType:'user',text:'${eoms:a2u('审批')}'},{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
	
	</fieldset>
	</c:if>
	
