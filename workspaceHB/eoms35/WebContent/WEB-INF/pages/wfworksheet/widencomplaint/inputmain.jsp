<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
 
 request.setAttribute("roleId","1740");
 
 
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/widencomplaint/baseinputmainhtmlnew.jsp"%>
 <script type="text/javascript">
     	//selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "widencomplaint.do?method=newShowLimit&flowName=WidenComplaint",
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	   // $("sheetAcceptLimit").value = "";
        	   // $('sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	        	var dt1 = new Date().add(Date.MINUTE,parseInt(o.acceptLimit,10));
	        	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	$("sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
   </script>
	<input type="hidden" name="processTemplateName" value="WidenComplaint" />
	<input type="hidden" name="operateName" value="newWorkSheet" />
	<c:if test="${status!=1}">
	   
	  
<input type="hidden" name="phaseId" id="phaseId" value="ExcuteHumTask" />

       <input type="hidden" id="operateType" name="operateType" value="0" />
       <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="phaseId" id="phaseId" value="OverTask" />
    </c:if>
    <input type="hidden" name="beanId" value="iWidenComplaintMainManager"/> 
    <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.widencomplaint.model.WidenComplaintMain"/>	
    <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.widencomplaint.model.WidenComplaintLink"/>
    <br>

    <!-- 工单基本信息 --> 
<table id="sheet" class="formTable" >
 
			<tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.urgentDegree"/>*</td>
			  <td colspan="3">
		  		<eoms:comboBox name="${sheetPageName}urgentDegree" id="${sheetPageName}urgentDegree" initDicId="1010606" defaultValue="${sheetMain.urgentDegree}" alt="allowBlank:false"/>
			  </td>			 					  
			</tr>		
			<tr>
			  <td class="label">受理时限*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
					id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"
					/> 
		  			  
			  </td>				
			  <td class="label">处理时限*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
					id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"
					/>   
			  </td>		  
			</tr>
			 <tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.mainCompleteLimitT1"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}mainCompleteLimitT1" readonly="readonly" 
					id="${sheetPageName}mainCompleteLimitT1" value="${eoms:date2String(sheetMain.mainCompleteLimitT1)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'lessThen',link:'${sheetPageName}mainCompleteLimitT2',vtext:'T1时限不能晚于T2时限',allowBlank:false"
					/> 
		  			  
			  </td>				
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.mainCompleteLimitT2"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}mainCompleteLimitT2" readonly="readonly" 
					id="${sheetPageName}mainCompleteLimitT2" value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'moreThen',link:'${sheetPageName}mainCompleteLimitT1',vtext:'T2时限不能早于T1时限',allowBlank:false"
					/>   
			  </td>		  
			</tr> 	
			 <tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType1"/>*</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}complaintType1" id="${sheetPageName}complaintType1" 
			  	      sub="${sheetPageName}complaintType2" initDicId="1010601" defaultValue="${sheetMain.complaintType1}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>				
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType2"/>*</td>
			  <td>
			    <eoms:comboBox name="${sheetPageName}complaintType2" id="${sheetPageName}complaintType2" 
			  	      sub="${sheetPageName}complaintType" initDicId="${sheetMain.complaintType1}" defaultValue="${sheetMain.complaintType2}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>		  
			</tr>					
            <tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType"/></td>
			  <td >
			  	 <eoms:comboBox name="${sheetPageName}complaintType" id="${sheetPageName}complaintType"
			  	      sub="${sheetPageName}complaintType4" initDicId="${sheetMain.complaintType2}" defaultValue="${sheetMain.complaintType}" alt="allowBlank:true" onchange="selectLimit(this.value);"/>
			  </td>	 
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType4"/></td>
			  <td >
			  	 <eoms:comboBox name="${sheetPageName}complaintType4" id="${sheetPageName}complaintType4"
			  	      sub="${sheetPageName}complaintType5" initDicId="${sheetMain.complaintType}" defaultValue="${sheetMain.complaintType4}" alt="allowBlank:true" />
			  </td>			   
			</tr>
		   <tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType5"/></td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}complaintType5" id="${sheetPageName}complaintType5" 
			  	      sub="${sheetPageName}complaintType6" initDicId="${sheetMain.complaintType4}" defaultValue="${sheetMain.complaintType5}" alt="allowBlank:ture"/>
			  </td>				
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType6"/></td>
			  <td>
			    <eoms:comboBox name="${sheetPageName}complaintType6" id="${sheetPageName}complaintType6" 
			  	      sub="${sheetPageName}complaintType7" initDicId="${sheetMain.complaintType5}" defaultValue="${sheetMain.complaintType6}" alt="allowBlank:ture"/>
			  </td>		  
			</tr>
		   <tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType7"/></td>
			  <td colspan="3">
			  	 <eoms:comboBox name="${sheetPageName}complaintType7" id="${sheetPageName}complaintType7" 
			  	      									   initDicId="${sheetMain.complaintType6}" defaultValue="${sheetMain.complaintType7}" alt="allowBlank:ture"/>
			  </td>					  
		   </tr>
 
</table>
<br/>
<table id="sheet" class="formTable">
		   <tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.btype1"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}btype1" id="${sheetPageName}btype1" value="${sheetMain.btype1}" alt="allowBlank:true,maxLength:25,vtext:'派发联系人，最多输入25字符'"/>
			  </td>				
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.bdeptContact"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}bdeptContact" id="${sheetPageName}bdeptContact" value="${sheetMain.bdeptContact}" alt="allowBlank:true,maxLength:50,vtext:'联系人电话，最多输入50字符'"/>
			  </td>		  
			</tr>
            <tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.bdeptContactPhone"/></td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}bdeptContactPhone" id="${sheetPageName}bdeptContactPhone" initDicId="10308" defaultValue="${sheetMain.bdeptContactPhone}" alt="allowBlank:true"/>
			  </td>				
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.repeatComplaintTimes"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}repeatComplaintTimes" id="${sheetPageName}repeatComplaintTimes" value="${sheetMain.repeatComplaintTimes}" alt="allowBlank:true,maxLength:16,vtext:'请输入重复投诉次数，最多输入16字符'"/>
			  </td>		  
			</tr>
			 <tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.customerName"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}customerName" id="${sheetPageName}customerName" value="${sheetMain.customerName}" alt="allowBlank:true"/>
			  </td>				
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.customPhone"/>*</td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}customPhone" id="${sheetPageName}customPhone" value="${sheetMain.customPhone}" alt="allowBlank:false"/>
			  </td>		  
			</tr>
			<tr>
			  <!-- <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.customType"/></td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}customType" id="${sheetPageName}customType" initDicId="1010603" defaultValue="${sheetMain.customType}" alt="allowBlank:true"/>
			  </td>	 -->
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.customLevel"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}customLevel" id="${sheetPageName}customLevel" 
			  	value="${sheetMain.customLevel}" alt="allowBlank:true,maxLength:25,vtext:'用户级别，最多输入25字符'"/>
			  </td>				
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.customBrand"/></td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}customBrand" id="${sheetPageName}customBrand" initDicId="1010604" defaultValue="${sheetMain.customBrand}" alt="allowBlank:true"/>
			  </td>		  
			</tr>					
			<tr>
			   <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.startDealCity"/></td>
			  <!--<td>
			  	<input type="text" class="text" name="${sheetPageName}startDealCity" id="${sheetPageName}startDealCity" 
			  	value="${sheetMain.startDealCity}" alt="allowBlank:false,maxLength:200,vtext:'受理地市，最多输入100汉字'"/>
			  </td>-->
        	  <td >
			  	<div id="areaview" class="hide"></div>
			    <script type="text/javascript">
	             
	            //viewer
				var areaViewer = new Ext.JsonView("areaview",
					'<div class="viewlistitem-{nodeType}">{name}</div>',
					{ 
						emptyText : '<div>没有选择项目</div>'
				}
				);
				var data = "[{id:'${sheetMain.toDeptId}',name:'<eoms:id2nameDB id='${sheetMain.toDeptId}' beanId='tawSystemAreaDao'/>',nodeType:'area'}]";
				areaViewer.jsonData = eoms.JSONDecode(data);
				areaViewer.refresh();
				 
				//area tree
	            var	deptTreeAction='${app}/xtree.do?method=areaTree';
	            deptetree = new xbox({

    	          btnId:'${sheetPageName}showDept',dlgId:'dlg3',

    	          treeDataUrl:deptTreeAction,treeRootId:'-1',treeRootText:'地市',treeChkMode:'single',treeChkType:'area',
    	          showChkFldId:'${sheetPageName}showDept',saveChkFldId:'${sheetPageName}toDeptId',viewer:areaViewer
	            });
               </script>

               <input type="text" class="text"  readonly="readonly" name="${sheetPageName}showDept" id="${sheetPageName}showDept" alt="allowBlank:true,vtext:'请选择地域名称'" value="<eoms:id2nameDB id='${sheetMain.toDeptId}' beanId='tawSystemAreaDao'/>"/>
               <input type="hidden" name="${sheetPageName}toDeptId" id="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>			  
              </td>					
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.customAttribution"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}customAttribution" id="${sheetPageName}customAttribution" 
			  	value="${sheetMain.customAttribution}" alt="allowBlank:true,maxLength:64,vtext:'用户归属地，最多输入32汉字'"/>
			  </td>		  
			</tr>								
		  	<tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.faultTime"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}faultTime" readonly="readonly" 
					id="${sheetPageName}faultTime" value="${eoms:date2String(sheetMain.faultTime)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'lessThen',link:'${sheetPageName}complaintTime',vtext:'故障时间不能晚于投诉时间',allowBlank:false"
					/>   
			  </td>				
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintTime"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}complaintTime" readonly="readonly" 
					id="${sheetPageName}complaintTime" value="${eoms:date2String(sheetMain.complaintTime)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'moreThen',link:'${sheetPageName}faultTime',vtext:'投诉时间不能早于故障时间',allowBlank:false"
					/> 
		  			  
			  </td>
			</tr>	
			<tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintNum"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}complaintNum" id="${sheetPageName}complaintNum" value="${sheetMain.complaintNum}" alt="allowBlank:true"/>
			  </td>				
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.faultSite"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}faultSite" id="${sheetPageName}faultSite" value="${sheetMain.faultSite}" alt="allowBlank:true"/>
			  </td>		  
			</tr>	
			<tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.terminalType"/></td>
			  <td colspan="3">
		  		<textarea name="${sheetPageName}terminalType" id="${sheetPageName}terminalType" class="textarea max" alt="allowBlank:true,maxLength:2000,vtext:'终端描述，最多输入1000汉字'">${sheetMain.terminalType}</textarea>	
			  </td>			 					  
			</tr>			
            <tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintDesc"/>*</td>
			  <td colspan="3">
		  		<textarea name="${sheetPageName}complaintDesc" id="${sheetPageName}complaintDesc" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'投诉内容，最多输入1000汉字'">${sheetMain.complaintDesc}</textarea>	
			  </td>			 					  
			</tr>
            <tr>
			  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.preDealResult"/></td>
			  <td colspan="3">
		  		<textarea name="${sheetPageName}preDealResult" id="${sheetPageName}preDealResult" class="textarea max" alt="allowBlank:true,maxLength:2000,vtext:'预处理情况，最多输入1000汉字'">${sheetMain.preDealResult}</textarea>	
			  </td>			 					  
			</tr>
</table>
	
<!-- 附件 -->
<table id="sheet" class="formTable">
		<!--附件模板-->
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
		            scope="request" idField="sheetAccessories" appCode="widencomplaint" alt="allowBlank:true"/> 				
		    </td>
	   </tr>			  
</table>


<!--派单树-->
<br/>
<fieldset>
 	<legend>
     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
		 <span id="roleName">
		 	 投诉处理组
		 </span>
  	</legend>
    <div class="x-form-item" >
			<eoms:chooser id="sendObject"  type="role" roleId="1742" flowId="58" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sheetMain.sendObject}" />
	</div>	
</fieldset>