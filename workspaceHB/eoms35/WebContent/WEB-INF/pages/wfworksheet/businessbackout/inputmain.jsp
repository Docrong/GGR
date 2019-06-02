<%@ include file="/common/taglibs.jsp"%>
<%
long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/businessbackout/baseinputmainhtmlnew.jsp"%>
	<input type="hidden" name="${sheetPageName}operName" id="${sheetPageName}operName"/>
	<input type="hidden" name="${sheetPageName}processTemplateName" value="BusinessBackoutMainFlowProcess" />
	<input type="hidden" name="${sheetPageName}sheetTemplateName" value="BusinessBackoutProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newSheet" />
	<c:if test="${status==0}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCreateAuditHumTask" />
    </c:if>
    <c:if test="${status==1}">  
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
    </c:if>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0" />
    <input type="hidden" name="${sheetPageName}beanId" value="iBusinessBackoutMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businessbackout.model.BusinessBackoutMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessbackout.model.BusinessBackoutLink"/>
	<!-- sheet info -->
	<br/> 
	<table class="formTable">
		   <tr>
		          <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.btype1"/>*</td>
		          <td>  
				        <eoms:comboBox name="${sheetPageName}btype1" id="${sheetPageName}btype1" defaultValue="${sheetMain.btype1}"
            	      initDicId="1011001"  alt="allowBlank:false,vtext:'${eoms:a2u('请选择集团客户业务分类！')}'" styleClass="select-class" onchange="changeOperate(this);"/>
		          </td>		      
		            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.urgentDegree"/>*</td>
		            <td><eoms:comboBox name="${sheetPageName}urgentDegree" id="${sheetPageName}urgentDegree" 
            	      initDicId="1010102" defaultValue="${sheetMain.urgentDegree}" alt="allowBlank:false,vtext:'${eoms:a2u('请选择紧急程度！')}'" styleClass="select-class"/>
			        </td>
			      </tr>
		  

			      <tr>
		            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.sheetAcceptLimit"/>*</td>
		            <td> <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
					 id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
					 alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false" onclick="popUpCalendar(this, this)"/> 
		  		
		            </td>
		            <td class="label"><bean:message bundle="businessbackout" key="businessbackout.sheetCompleteLimit"/>*</td>
		            <td><input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
					id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
					onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false" />   

		            </td>
                   </tr>
        	      <tr>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.bdeptContact"/>*</td>
		            <td>
		            <%-- <eoms:comboBox name="${sheetPageName}bdeptContact" id="${sheetPageName}bdeptContact" 
            	      initDicId="1011202" defaultValue="${sheetMain.bdeptContact}" alt="allowBlank:false,vtext:'${eoms:a2u('请选择业务部门联系人！')}'" styleClass="select-class"/>
			        --%>
			        <input type="text" class="text" value="${sheetMain.bdeptContact}" name="${sheetPageName}bdeptContact" id="${sheetPageName}bdeptContact" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入业务部门联系人电话，最大长度为100个汉字！')}'"/>
			        </td>	                
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.bdeptContactPhone"/>*</td>
		            <td><input type="text" class="text" value="${sheetMain.bdeptContactPhone}" name="${sheetPageName}bdeptContactPhone" id="${sheetPageName}bdeptContactPhone" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入业务部门联系人电话，最大长度为100个汉字！')}'"/></td>
			      </tr>

			      <tr>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.customNo"/>*</td>
		            <td><input type="text"  class="text" value="${sheetMain.customNo}" name="${sheetPageName}customNo" id="${sheetPageName}customNo" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请选择集团客户编号，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.customName"/></td>
		            <td><input type="text"  class="text" value="${sheetMain.customName}" name="${sheetPageName}customName" id="${sheetPageName}customName"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户名称，最大长度为100个汉字！')}'"/></td>
		          </tr>

			      <tr>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.customContact"/></td>
		            <td><input type="text"  class="text" value="${sheetMain.customContact}" name="${sheetPageName}customContact" id="${sheetPageName}customContact"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户联系人，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.customContactPhone"/></td>
		            <td><input type="text"  class="text" value="${sheetMain.customContactPhone}" name="${sheetPageName}customContactPhone" id="${sheetPageName}customContactPhone"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户联系人电话，最大长度为100个汉字！')}'"/></td>
		          </tr>

			     

        <tr>
	          <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.cityName"/></td>
	          <td > <input type="text"  class="text" name="${sheetPageName}cityName" id="${sheetPageName}cityName" value="${sheetMain.cityName}"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入所属地市，最大长度为100个汉字！')}'"/></td>
	       
	          <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.countyName"/></td>
	          <td > <input type="text"  class="text" name="${sheetPageName}countyName" id="${sheetPageName}countyName" value="${sheetMain.countyName}" alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入所属区县，最大长度为100个汉字！')}'"/></td>
        </tr>
        
         <tr>
	          <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.cmanagerPhone"/></td>
	          <td > <input type="text"  class="text" name="${sheetPageName}cmanagerPhone" id="${sheetPageName}cmanagerPhone" value="${sheetMain.cmanagerPhone}"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入客户经理，最大长度为100个汉字！')}'"/></td>
	       
	          <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.cmanagerContactPhone"/></td>
	          <td > <input type="text"  class="text" name="${sheetPageName}cmanagerContactPhone" id="${sheetPageName}cmanagerContactPhone" value="${sheetMain.cmanagerContactPhone}" alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入客户经理联系电话，最大长度为100个汉字！')}'"/></td>
        </tr>

	      <tr>
	           <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.customLevel"/>*</td>
               <td>  
	        <eoms:comboBox name="${sheetPageName}customLevel" id="${sheetPageName}customLevel"  defaultValue="${sheetMain.customLevel}"
         	      initDicId="1010107"  alt="allowBlank:false" styleClass="select-class"/>
              </td>
	           <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.productName"/></td>
	          <td > <input type="text"  class="text" name="${sheetPageName}productName" id="${sheetPageName}productName" value="${sheetMain.productName}" alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入产品名称，最大长度为100个汉字！')}'"/></td>
	      
	      
	      </tr>
	      
	       <tr>
		            
		           <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.provinceName"/></td>
	          <td colspan='3'> <input type="text"  class="text" name="${sheetPageName}provinceName" id="${sheetPageName}provinceName" value="${sheetMain.provinceName}"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入所属省份，最大长度为100个汉字！')}'"/></td>
        </tr>
		          
		 <tbody id='GPRS' style="display:none" > 
			      <tr>
		            
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.APNName"/></td>
		            <td colspan='3'> <input type="text" class="text" value="${sheetMain.apnName}" name="${sheetPageName}apnName" id="${sheetPageName}apnName"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入APN名称，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          <tr>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.backoutCause"/>*</td>
		            <td colspan="3">
    				  <textarea class="textarea max" name="${sheetPageName}backoutCause" id="${sheetPageName}backoutCause" alt="width:500,allowBlank:false,maxLength:255,vtext:'${eoms:a2u('请输入拆除原因，最大长度为125个汉字！')}'">${sheetMain.backoutCause}</textarea>
		            </td>
		          </tr>
		 </tbody> 
		<tbody id='caixin' style="display:none">
			      <tr>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.bcode"/></td>
		            <td><input type="text" class="text" value="${sheetMain.bcode}" name="${sheetPageName}bcode" id="${sheetPageName}bcode"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入业务代码，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.enterpriseCode"/></td>
		            <td><input type="text" class="text" value="${sheetMain.enterpriseCode}" name="${sheetPageName}enterpriseCode" id="${sheetPageName}enterpriseCode"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入企业代码，最大长度为100个汉字！')}'"/></td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.SIName"/></td>
		            <td><input type="text" class="text" value="${sheetMain.siName}" name="${sheetPageName}siName" id="${sheetPageName}siName"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI名称，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.SIEnterpriseCode"/></td>
		            <td><input type="text" class="text" value="${sheetMain.siEnterpriseCode}" name="${sheetPageName}siEnterpriseCode" id="${sheetPageName}siEnterpriseCode"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI企业代码，最大长度为100个汉字！')}'"/></td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.SIServerCode"/></td>
		            <td colspan='3'><input type="text" class="text" value="${sheetMain.siServerCode}" name="${sheetPageName}siServerCode" id="${sheetPageName}siServerCode"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI服务代码，最大长度为100个汉字！')}'"/></td>
		            
		          </tr>
		</tbody>
		<tbody id='duanxin' style="display:none">
		<tr>
		   		 <td class="label"><bean:message bundle="businessbackout" key="businessbackout.urgentDegree"/>*</td>
		            <td colspan='3'><eoms:comboBox name="${sheetPageName}urgentDegree" id="${sheetPageName}urgentDegree" 
            	      initDicId="1010102" defaultValue="${sheetMain.urgentDegree}" alt="allowBlank:false,vtext:'${eoms:a2u('请选择紧急程度！')}'" styleClass="select-class"/>
			        </td>
			        </tr>
		   		<tr>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.enterpriseCode"/></td>
		            <td><input type="text" class="text" value="${sheetMain.enterpriseCode}" name="${sheetPageName}enterpriseCode" id="${sheetPageName}enterpriseCode"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入企业代码，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.serverCode"/></td>
		            <td><input type="text" class="text" value="${sheetMain.serverCode}" name="${sheetPageName}serverCode" id="${sheetPageName}serverCode"  alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入服务代码，最大长度为100个汉字！')}'"/></td>
		   		</tr>		   		
		</tbody>
		<tbody id='chuanshu' style="display:none">
			      <tr>
		            
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.circuitCode"/>*</td>
		            <td colspan='3'><input type="text"  class="text" value="${sheetMain.circuitCode}" name="${sheetPageName}circuitCode" id="${sheetPageName}circuitCode" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入传输专线电路代号，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          <tr>
		            <td  class="label"><bean:message bundle="businessbackout" key="businessbackout.backoutCause"/>*</td>
		            <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}backoutCause" id="${sheetPageName}backoutCause" alt="width:500,allowBlank:false,maxLength:255,vtext:'${eoms:a2u('请输入拆除原因，最大长度为125个汉字！')}'">${sheetMain.backoutCause}</textarea>
		            </td>
		          </tr>
		</tbody>
		    <!--   <tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			</td>	
			<td colspan="3">					
		    <eoms:attachment name="tawSheetAccess" property="accesss" 
		            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
		    </td>
		  </tr>	-->	
		  		<tr>
		    		<td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>	
					<td colspan="3"><eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="${sheetPageName}sheetAccessories" appCode="businessbackout" /> 				
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
	        <div class="x-form-item">
			<eoms:chooser id="test" category="[{id:'dealPerformer',childType:'user',limit:'none',text:'${eoms:a2u('派发')}',allowBlank:false,vtext:'${eoms:a2u('请选择任务执行人')}'},{id:'auditPerformer',childType:'user',text:'${eoms:a2u('审批')}'},{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
			</div>
	</fieldset>
	</c:if>

<script type="text/javascript">	
	var formMgr = eoms.form;
	function changeOperate(select){
		var value = select.value;
		if(value==''){
			formMgr.disableArea('GPRS',true);
			formMgr.disableArea('caixin',true);
			formMgr.disableArea('duanxin',true);
 	     	formMgr.disableArea('chuanshu',true);
		}
	   	if(value=='101100101'){
			formMgr.enableArea('GPRS');
			formMgr.disableArea('caixin',true);
			formMgr.disableArea('duanxin',true);
 	     	formMgr.disableArea('chuanshu',true);
	   	}else if(value=='101100102'){
			formMgr.disableArea('GPRS',true);
			formMgr.enableArea('caixin');
			formMgr.disableArea('duanxin',true);
 	     	formMgr.disableArea('chuanshu',true);
	   	}else if(value=='101100103'){
			formMgr.disableArea('GPRS',true);
			formMgr.disableArea('caixin',true);
			formMgr.enableArea('duanxin');
 	     	formMgr.disableArea('chuanshu',true);
	   	}else if(value=='101100104'){
			formMgr.disableArea('GPRS',true);
			formMgr.disableArea('caixin',true);
			formMgr.disableArea('duanxin',true);
 	     	formMgr.enableArea('chuanshu');
	   	} 
   }
   changeOperate(document.getElementById('btype1'));

	<%//operName初始化赋值%>
	try{
		var dealSel = document.getElementById('dealSelector');
		var selIndex = dealSel.selectedIndex;
		document.getElementById('${sheetPageName}operName').value = dealSel.options[selIndex].innerText;
	}catch(e){
		//alert(e.message);//用于测试
		document.getElementById('${sheetPageName}operName').value = '';
	}
	
	selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "businessbackout.do?method=newShowLimit&flowName=BusinessBackoutProcess",
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