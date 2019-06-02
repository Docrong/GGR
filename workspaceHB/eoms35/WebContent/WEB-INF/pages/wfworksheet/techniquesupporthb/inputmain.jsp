﻿<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
 request.setAttribute("roleId","4017"); 
long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/techniquesupporthb/baseinputmainhtmlnew.jsp"%>
 <script type="text/javascript">
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "techniquesupporthb.do?method=newShowLimit&flowName=TechniqueSupportHbProcess",
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	   // $("${sheetPageName}sheetAcceptLimit").value = "";
        	   // $('${sheetPageName}sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	       	var dt1 = new Date().add(Date.MINUTE,parseInt(o.acceptLimit,10));
	       	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	$("${sheetPageName}sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('${sheetPageName}sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
 
  changeOperate = function(input){
	 
	   //	if(input==101470101){
		//	eoms.form.enableArea('GSM');
		//	eoms.form.disableArea('gzjb',true);
		//	eoms.form.disableArea('bksl',true);
		//	eoms.form.disableArea('SJZY',true);
		//	eoms.form.disableArea('WXZY',true);
		//	eoms.form.disableArea('CSZY',true);
		//	eoms.form.disableArea('KTPTZY',true);
			
       //	}else 
       		
       	if(input==101470102 || input==101470101 || input==101470106 || input==101470107 || input==101470108){	
			eoms.form.enableArea('SJZY');
			eoms.form.disableArea('gzjb',true);
			eoms.form.disableArea('bksl',true);
			eoms.form.disableArea('GSM',true);
			eoms.form.disableArea('WXZY',true);
			eoms.form.disableArea('CSZY',true);
			eoms.form.disableArea('KTPTZY',true); 
				b('SJZY');
		}else if(input==101470103){
		

			eoms.form.enableArea('WXZY');
			eoms.form.disableArea('gzjb',true);
			eoms.form.disableArea('bksl',true);
			eoms.form.disableArea('SJZY',true);
			eoms.form.disableArea('GSM',true);
			eoms.form.disableArea('CSZY',true);
			eoms.form.disableArea('KTPTZY',true);  
			b('WXZY');
		}else if(input==101470104){
			eoms.form.enableArea('CSZY');
			eoms.form.disableArea('gzjb',true);
			eoms.form.disableArea('bksl',true);
			eoms.form.disableArea('SJZY',true);
			eoms.form.disableArea('WXZY',true);
			eoms.form.disableArea('GSM',true);
			eoms.form.disableArea('KTPTZY',true); 
		}else if(input==101470105){
			eoms.form.enableArea('KTPTZY');
			eoms.form.disableArea('gzjb',true);
			eoms.form.disableArea('bksl',true);
			eoms.form.disableArea('SJZY',true);
			eoms.form.disableArea('WXZY',true);
			eoms.form.disableArea('CSZY',true);
			eoms.form.disableArea('GSM',true);        
	   	}else{
	   		eoms.form.disableArea('gzjb',true);
			eoms.form.disableArea('bksl',true);
	   		eoms.form.disableArea('GSM',true);
	   		eoms.form.disableArea('GPRS',true);  
	   		eoms.form.disableArea('WXZY',true);
			eoms.form.disableArea('CSZY',true);
			eoms.form.disableArea('KTPTZY',true);  
	   	}
	}
   
   function b(input){
   var tbody = document.getElementById(input);//获取到指定的tbody
   var tr = tbody.children;//获取tbody下面全部的tr
   var tds = tr[0].children;//找到tr下面的子元素
   var obj=tds[1].getElementsByTagName("OPTION");
   for(var i=0;i<obj.length;i++)
   {
			if(obj[i].outerHTML.match("selected")!=null)
			{
				if(obj[i].value==101470204)
				{
				eoms.form.enableArea('bksl');
				}
				
				if(obj[i].value==101470201&&input=='WXZY')
				 {
				 eoms.form.enableArea('gzjb');
				 }
			}
   }
   
   }
   


 	var frm = document.forms[0];
    var temp = frm.mainTechSupportType ? frm.mainTechSupportType.value : '';
	if(temp != ''){
		changeOperate(temp);
	}
	
	eoms.form.disableArea('gzjb',true);
	eoms.form.disableArea('bksl',true);
	changeFaultLevel = function(input){
		if(input==101470201){
			eoms.form.enableArea('gzjb');
			eoms.form.disableArea('bksl',true);
    } else if(input==101470204){
    	eoms.form.enableArea('bksl');
    	eoms.form.disableArea('gzjb',true);
    }else{
    	eoms.form.disableArea('gzjb',true);
    	eoms.form.disableArea('bksl',true);
    }
	}
	
	
	changeCardNumber = function(input){
    if(input==101470204){
    //alert("input="+input);
			eoms.form.enableArea('bksl');
    } else{
    	eoms.form.disableArea('bksl',true);
    }
    
	}
	
	
	
</script>
	<input type="hidden" name="${sheetPageName}processTemplateName" value="TechniqueSupportHbProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet" />
	<c:if test="${status!=1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DoneImplTask" />
       <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${sheetPageName}operateType" />
       <input type="hidden" name="${sheetPageName}gatherPhaseId" id="${sheetPageName}gatherPhaseId" value="HoldTask" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iTechniqueSupportHbMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbLink"/>
    <br>

	<!-- sheet info --> 
     <table class="formTable">
     	 <tr>
	   		<td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
	  		<td class="content">
	    	<input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
			id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
			onclick="popUpCalendar(this, this)"
			alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>   
	  		</td>
	  
	  		<td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
	  		<td class="content">
	    	<input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
			id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
			onclick="popUpCalendar(this, this)"
			alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>   
	  		</td>
		</tr>
	    <tr>
		         <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainTechSupportType"/>*</td>
		         <td colspan="3"><eoms:comboBox name="${sheetPageName}mainTechSupportType" id="${sheetPageName}mainTechSupportType" onchange="changeOperate(this.value);"
            	      initDicId="1014701"   defaultValue="${sheetMain.mainTechSupportType}" alt="allowBlank:false" styleClass="select-class"/>
			      </td>
                
		</tr>
		<!-- GSM技术支持： -->
		<tbody id='GSM' style="display:none"> 
		<tr>
		         <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainSheetType"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainSheetType" id="${sheetPageName}mainSheetType" 
            	      initDicId="1014702"   defaultValue="${sheetMain.mainSheetType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
			     <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainCaseType"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainCaseType" id="${sheetPageName}mainCaseType" 
            	      initDicId="1014703"   defaultValue="${sheetMain.mainCaseType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
                
		</tr>
		<tr>
		         <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainSpecifyType"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainSpecifyType" id="${sheetPageName}mainSpecifyType" 
            	      initDicId="1014704"   defaultValue="${sheetMain.mainSpecifyType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
			     <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainFaultPhoneType"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainFaultPhoneType" id="${sheetPageName}mainFaultPhoneType" 
            	      initDicId="1014705"   defaultValue="${sheetMain.mainFaultPhoneType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
                
		</tr>
		</tbody>
		<!-- 数据专业技术支持 SJZY-->
		<tbody id='SJZY' style="display:none"> 
		<tr>
		         <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainSheetType"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainSheetType" id="${sheetPageName}mainSheetType" onchange="changeCardNumber(this.value);"
            	      initDicId="1014702"   defaultValue="${sheetMain.mainSheetType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
			     <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainFaultType"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainFaultType" id="${sheetPageName}mainFaultType" 
            	      initDicId="1014706"   defaultValue="${sheetMain.mainFaultType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
                
		</tr>
		<tr>
		         <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainIsMainFault"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainIsMainFault" id="${sheetPageName}mainIsMainFault" 
            	      initDicId="1014707"   defaultValue="${sheetMain.mainIsMainFault}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
			     <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainFaultPhoneType"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainFaultPhoneType" id="${sheetPageName}mainFaultPhoneType" 
            	      initDicId="1014705"   defaultValue="${sheetMain.mainFaultPhoneType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
                
		</tr>
		</tbody>
		<!-- 无线专业技术支持 WXZY -->
		<tbody id='WXZY' style="display:none"> 
		<tr>
		         <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainSheetType"/>*</td>
		         <td colspan='3'><eoms:comboBox name="${sheetPageName}mainSheetType" id="${sheetPageName}mainSheetType" onchange="changeFaultLevel(this.value);"
            	      initDicId="1014702"   defaultValue="${sheetMain.mainSheetType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
			     <!--<td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainFaultType"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainFaultType" id="${sheetPageName}mainFaultType" 
            	      initDicId="1014706"   defaultValue="${sheetMain.mainFaultType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>-->
                
		</tr>
		<tr>
		         <td  class="label">子专业*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainProfessional" id="${sheetPageName}mainProfessional" 
            	      initDicId="1014712"   defaultValue="${sheetMain.mainProfessional}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
			     <td  class="label">厂商*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainManufacturer" id="${sheetPageName}mainManufacturer" 
            	      initDicId="1014713"   defaultValue="${sheetMain.mainManufacturer}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
                
		</tr>
		<!--<tr>
		         <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainIsMainFault"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainIsMainFault" id="${sheetPageName}mainIsMainFault" 
            	      initDicId="1014707"   defaultValue="${sheetMain.mainIsMainFault}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
			     <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainDevType"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainDevType" id="${sheetPageName}mainDevType" 
            	      initDicId="1014708"   defaultValue="${sheetMain.mainDevType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
                
		</tr>
		<tr>
			     <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainFaultPhoneType"/>*</td>
		         <td  colspan='3'><eoms:comboBox name="${sheetPageName}mainFaultPhoneType" id="${sheetPageName}mainFaultPhoneType" 
            	      initDicId="1014705"   defaultValue="${sheetMain.mainFaultPhoneType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
                
		</tr>-->
		</tbody>
		<tbody id='gzjb' style="display:none">
		<tr>
			     <td  class="label">故障级别*</td>
		         <td  colspan='3'><eoms:comboBox name="${sheetPageName}mainFaultLevel" id="${sheetPageName}mainFaultLevel" 
            	      initDicId="1014714"   defaultValue="${sheetMain.mainFaultLevel}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
                
		</tr>
		</tbody>
		<tbody id='bksl' style="display:none">
		<tr>
			     <td  class="label">板卡数量*</td>
		         <td  colspan='3'>
		         <input type="text" class="text" name="${sheetPageName}mainCardNumber" id="${sheetPageName}mainCardNumber" value="${sheetMain.mainCardNumber}" alt="allowBlank:false,maxLength:1000,vtext:'板卡数量，最多输入1000字符'"/>
		        
			     </td>
                
		</tr>
		</tbody>
		<!-- 传输专业技术支持 CSZY -->
		<tbody id='CSZY' style="display:none"> 
		<tr>
		         <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainTrasitionDevType"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainTrasitionDevType" id="${sheetPageName}mainTrasitionDevType" 
            	      initDicId="1014709"   defaultValue="${sheetMain.mainTrasitionDevType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
			     <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainFaultType"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainFaultType" id="${sheetPageName}mainFaultType" 
            	      initDicId="1014706"   defaultValue="${sheetMain.mainFaultType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
                
		</tr>
		<tr>
		         <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainIsMainFault"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainIsMainFault" id="${sheetPageName}mainIsMainFault" 
            	      initDicId="1014707"   defaultValue="${sheetMain.mainIsMainFault}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
			     <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainFaultPhoneType"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainFaultPhoneType" id="${sheetPageName}mainFaultPhoneType" 
            	      initDicId="1014710"   defaultValue="${sheetMain.mainFaultPhoneType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
                
		</tr>
		</tbody>
		<!-- 空调和配套专业技术支持 KTPTZY -->
		<tbody id='KTPTZY' style="display:none"> 
		<tr>
			<td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainFaultType"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainFaultType" id="${sheetPageName}mainFaultType" 
            	      initDicId="1014706"   defaultValue="${sheetMain.mainFaultType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
                <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainIsMainFault"/>*</td>
		         <td ><eoms:comboBox name="${sheetPageName}mainIsMainFault" id="${sheetPageName}mainIsMainFault" 
            	      initDicId="1014707"   defaultValue="${sheetMain.mainIsMainFault}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
		</tr>
		<tr>

			     <td  class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainFaultPhoneType"/>*</td>
		         <td colspan="3"><eoms:comboBox name="${sheetPageName}mainFaultPhoneType" id="${sheetPageName}mainFaultPhoneType" 
            	      initDicId="1014711"   defaultValue="${sheetMain.mainFaultPhoneType}" alt="allowBlank:false" styleClass="select-class"/>
			     </td>
                
		</tr>
		</tbody>

		<tr>
	        <td class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.mainQuestionDescript"/>*</td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}mainQuestionDescript" class="textarea max" id="${sheetPageName}mainQuestionDescript" 
		        alt="allowBlank:false,width:200,vtext:'请最多输入100字'" alt="width:'200px'">${sheetMain.mainQuestionDescript}</textarea>
		    </td>
		</tr> 
		<tr>
	        <td class="label">初步处理意见*</td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}mainInitDealOpinition" class="textarea max" id="${sheetPageName}mainInitDealOpinition" 
		        alt="allowBlank:false,width:200,vtext:'请最多输入100字'" alt="width:'200px'">${sheetMain.mainInitDealOpinition}</textarea>
		    </td>
		</tr>  	
		
 	
	  
	<%-- accessories --%>
	
	

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
		            scope="request" idField="${sheetPageName}sheetAccessories" appCode="techniquesupporthb" /> 	
		    </td>
		  </tr>				          

	</table>

	
	<br/>
	<c:if test="${status!=1}">
	<fieldset id="link1" >
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName">:技术支援专家组
			 </span>
	      </legend>
	        <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="4018" flowId="93" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sendUserAndRoles}" />			   
			 </div>
	</fieldset>
</c:if>