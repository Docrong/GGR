<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
 var v1 = eoms.form;
 
function openSheet(url){
	if(parent.frames['north']){
		parent.frames['north'].location.href = url;
	}
	else{
		location.href = url;
	}
}
  //选择全选时的处理
  function check(obj) {
  	var checksheets = document.getElementsByName("checksheet");
  	var relationCheckSheets = document.getElementsByName("relationCheckSheet");
  	if (obj.checked) {
	  	for (var i = 0; i < checksheets.length; i ++) {
	  		var checksheet = checksheets[i];
	  		if(!checksheet.checked) {
	  			checksheet.checked = true;
	  		}
	  	}
	  	for(var i = 0; i < relationCheckSheets.length; i ++){
	  		var relationCheckSheet = relationCheckSheets[i];
	  		v1.enableArea('relation'+relationCheckSheet.id);
	  		if(!relationCheckSheet.checked) {
	  			relationCheckSheet.checked = true;
	  			ischeck=true;
	  			v1.enableArea('relation');
	  		}
	  	}
  	} else {
  		for (var i = 0; i < checksheets.length; i ++) {
	  		var checksheet = checksheets[i];
	  		if(checksheet.checked) {
	  			checksheet.checked = false;
	  		}
	  	}
	  	for(var i = 0; i < relationCheckSheets.length; i ++){
	  		var relationCheckSheet = relationCheckSheets[i];
	  		if(relationCheckSheet.checked) {
	  			relationCheckSheet.checked = false;
	  		}
	  		v1.disableArea('relation'+relationCheckSheet.id,true);
	  	}
	  	v1.disableArea('relation',true);
  	}
  }
  //表单提交时组成相应的id
  function ifcheckhide(){
  	var ifcheck = false;
  	var ids = '';
  	var checksheets = document.getElementsByName("checksheet");
  	for (var i = 0; i < checksheets.length; i ++) {
  			var checksheet = checksheets[i];
	  		if(checksheet.checked) {
	  			ifcheck = true;
	  			ids+=checksheet.id+",";
	  		}
  	}
  	var relationFlowNames = document.getElementsByName("relationFlowName");
  	var relationchecksheets = document.getElementsByName("relationCheckSheet");
  	var rids = '';
  	var flowNames = '';
  	for (var i = 0; i < relationchecksheets.length; i ++) {
  			var relationchecksheet = relationchecksheets[i];
	  		if(relationchecksheet.checked) {
	  			ifcheck = true;
	  			rids+=relationchecksheet.id+",";
	  			flowNames+=relationFlowNames[i].value+",";
	  		}
  	}
  	if(ifcheck==false){
  		alert('请选择要操作的工单！');
  	}else{
  		$('ids').value = ids.substring(0,ids.length-1);
  		if(rids!=''){
  			$('rids').value = rids.substring(0,rids.length-1);
  			$('flowNames').value = flowNames.substring(0,flowNames.length-1);
  		}
  		var s;
  		if('${isHided}'=='1'){
  			s = confirm("请确认是否取消隐藏工单？");
  		}else{
  			s = confirm("请确认是否隐藏工单？");
  		}
  		if(s){
  			ifcheck=true;
  		}else{
  			ifcheck=false;
  		}
  	}
  	return ifcheck;
  }
  //每个checkbox的点击事件
  function hidecheck(obj){
  		var relationCheckSheets = document.getElementsByName("relationCheckSheet");
  		var relationIds = document.getElementsByName("relationId");
  		var ifcheck = false;
  		var ifhasrelation = false;
	  	for (var i = 0; i < relationCheckSheets.length; i ++) {
	  		var relationCheckSheet = relationCheckSheets[i];
	  		var relationId = relationIds[i];
	  		if(relationId.value==obj.id){
	  			ifhasrelation = true;
	  			if(obj.checked) {
	  				v1.enableArea('relation');
	  				v1.enableArea('relation'+relationCheckSheet.id);
	  				relationCheckSheet.checked = true;
	  			}else{
	  				v1.disableArea('relation'+relationCheckSheet.id,true);
	  				relationCheckSheet.checked = false;
	  			}
	  			hidecheck(relationCheckSheet);
	  		}
	  		if(relationCheckSheet.checked==true){
	  			ifcheck=true;
	  		}
	  	}
	  	if(ifcheck==false&&obj.name=='checksheet'&&ifhasrelation==true){
	  		v1.disableArea('relation',true);
	  	}
	  	if(obj.checked==false){
	  		$('checkAll').checked=false;
	  	}
  }
  function openSheet(flowName, sheetKey){
 
    var url = '';
    if(flowName == 'BusinessPilotProcess'){//新业务试点工单
      url='../business/businesspilot.do?method=showMainDetailPage&sheetKey='+sheetKey;
    }    
    
    else if(flowName == 'NetDataMainProcess'){//网络数据管理工单
      url='../netdata/netdata.do?method=showMainDetailPage&sheetKey='+sheetKey;
    }    
    else if(flowName == 'SoftChangeMainProcess'){//软件变更流程
      url='../softchange/softchange.do?method=showMainDetailPage&sheetKey='+sheetKey;
    }  
    else if(flowName == 'NetChangeMainProcess'){//网络综合调整工单
      url='../netchange/netchange.do?method=showMainDetailPage&sheetKey='+sheetKey;
    } 
     
    else if(flowName == 'CircuitDispatchMainFlowProcess'){//电路调度工单
      url='../circuitdispatch/circuitdispatch.do?method=showMainDetailPage&sheetKey='+sheetKey;
    }        
    else if(flowName == 'BusinessPlanProcess'){//新业务设计工单
      url='../businessplan/businessplan.do?method=showMainDetailPage&sheetKey='+sheetKey;
    }
    else if(flowName == 'BusinessOperationProcess'){//新业务正式实施工单
      url='../businessoperation/businessoperation.do?method=showMainDetailPage&sheetKey='+sheetKey;
    }      

    else if(flowName == 'UrgentFaultMainFlowProcess'){//紧急故障工单
      url='../urgentfault/urgentfault.do?method=showMainDetailPage&sheetKey='+sheetKey;
    }
    else if(flowName == 'ComplaintProcess'){//投诉处理工单
      url='../complaint/complaint.do?method=showMainDetailPage&sheetKey='+sheetKey;
    }      
    else if(flowName == 'CommonFaultMainFlowProcess'){//故障处理工单
      url='../commonfault/commonfault.do?method=showMainDetailPage&sheetKey='+sheetKey;
    }     
    else if(flowName == 'BusinessChangeProcess'){ //业务变更工单
      url='../businesschange/businesschange.do?method=showMainDetailPage&sheetKey='+sheetKey;
    } 
     else if(flowName == 'BusinessBackoutProcess'){//业务拆除工单
      url='../businessbackout/businessbackout.do?method=showMainDetailPage&sheetKey='+sheetKey;
    } 
     else if(flowName == 'BusinessDredgeMainFlowProcess'){//业务开通工单
      url='../businessdredge/businessdredge.do?method=showMainDetailPage&sheetKey='+sheetKey;
    } 
     else if(flowName == 'ResourceAffirmProcess'){//资源确认工单
      url='../resourceaffirm/resourceaffirm.do?method=showMainDetailPage&sheetKey='+sheetKey;
    } 
     else if(flowName == 'ITRequirementProcess'){//IT需求申请工单
      url='../itrequirement/itrequirement.do?method=showMainDetailPage&sheetKey='+sheetKey;
    }
      else if(flowName == 'ITSoftChangeProcess'){//IT需求开发工单
      url='../itsoftchange/itsoftchange.do?method=showMainDetailPage&sheetKey='+sheetKey;
    }    
      else if(flowName == 'SecurityDealProcess'){//安全问题处理工单
      url='../itsoftchange/itsoftchange.do?method=showMainDetailPage&sheetKey='+sheetKey;
    } 
    
    window.open(url);
  
  }  
</script>
<html:form action="/commonfault.do?method=performHide" styleId="theform" onsubmit="return ifcheckhide();">
<bean:define id="url" value="commonfault.do" />
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table taskList"
		export="false" requestURI="commonfault.do"
		sort="list" size="total" partialList="true"
		decorator="com.boco.eoms.sheet.base.webapp.action.HideListDisplaytagDecoratorHelper">
        
        <display:column property="isCheck" sortable="true"
			headerClass="sortable" title="" />
			
        <display:column property="sheetId" sortable="true"
			headerClass="sortable" title="工单流水号" />
			
		<display:column property="title" sortable="true"
			headerClass="sortable" title="工单主题" />

		<display:column property="sheetAcceptLimit" sortable="true"
			headerClass="sortable" title="受理时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column property="sheetCompleteLimit" sortable="true"
			headerClass="sortable" title="完成时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />
			
        <display:column sortable="true" headerClass="sortable" title="工单状态">
           <eoms:dict key="dict-sheet-common" dictId="sheetStatus" itemId="${taskList.status}" beanId="id2descriptionXML" />  
		</display:column>
		
		<display:footer>
		<tr id="relation" style="display:none">
			<td colspan="6">
			<!-- 相关工单 start-->
			 <table class="table" width="100%" cellpadding="0" cellspacing="0">
			 <caption>相关工单</caption>
				<thead>
				  <tr>
					<th width="10px" style="width:10px">&nbsp;</th>
					<th class="max">工单流水号</th>
					<th class="lable">相关工单流水号</th>
					<th class="lable">相互关系</th>
				  </tr>
				</thead>
				<!-- 我调用的工单 -->
				<logic:present name="FROMRELATIONLIST" scope="request">
					<logic:iterate id="tawSheetRelation1" name="FROMRELATIONLIST" type="com.boco.eoms.sheet.tool.relation.webapp.form.TawSheetRelationForm">
						<tr id="relation<bean:write name="tawSheetRelation1" property="currentId" scope="page" />" style="display:none">
							<td>
							<input type="checkbox" name="relationCheckSheet" id="<bean:write name="tawSheetRelation1" property="currentId" scope="page"/>" onclick="hidecheck(this)"/>
							<input type="hidden" name="relationId" value="<bean:write name="tawSheetRelation1" property="parentId" scope="page"/>"/>
							<input type="hidden" name="relationFlowName" value="<bean:write name="tawSheetRelation1" property="currentFlowName" scope="page"/>"/>
							</td>
							<td>
							  <bean:write name="tawSheetRelation1" property="parentSheetId" scope="page" />
							</td>
							<td>
							  <a onclick=openSheet('<bean:write name="tawSheetRelation1" property="currentFlowName" scope="page" />','<bean:write name="tawSheetRelation1" property="currentId" scope="page" />')>
							    <bean:write name="tawSheetRelation1" property="currentSheetId" scope="page" />
							  </a>
							</td>
							<td><bean:write name="tawSheetRelation1" property="parentSheetId" scope="page" />-->调用--><bean:write name="tawSheetRelation1" property="currentSheetId" scope="page" /></td>
						    </tr>
					</logic:iterate>
				</logic:present>
				<!-- 调用我的的工单 -->
				<logic:present name="TORELATIONLIST" scope="request">
					<logic:iterate id="tawSheetRelation2" name="TORELATIONLIST" type="com.boco.eoms.sheet.tool.relation.webapp.form.TawSheetRelationForm">
						<tr id="relation<bean:write name="tawSheetRelation2" property="parentId" scope="page"/>" style="display:none">
							<td>
							<input type="checkbox" name="relationCheckSheet" id="<bean:write name="tawSheetRelation2" property="parentId" scope="page"/>" onclick="hidecheck(this)"/>
							<input type="hidden" name="relationId" value="<bean:write name="tawSheetRelation2" property="currentId" scope="page"/>"/>
							<input type="hidden" name="relationFlowName" value="<bean:write name="tawSheetRelation2" property="parentFlowName" scope="page"/>"/>
							</td>
							<td>
							  <bean:write name="tawSheetRelation2" property="currentSheetId" scope="page" />
							</td>
							<td>
							  <a onclick=openSheet('<bean:write name="tawSheetRelation2" property="parentFlowName" scope="page" />','<bean:write name="tawSheetRelation2" property="parentSheetId" scope="page" />')>
							    <bean:write name="tawSheetRelation2" property="parentSheetId" scope="page" />
							  </a>
							</td>
								<td><bean:write name="tawSheetRelation2" property="parentSheetId" scope="page" />-->调用--><bean:write name="tawSheetRelation2" property="currentSheetId" scope="page" /></td>
						    </tr>
					</logic:iterate>
				</logic:present>
			</table>
			<!-- 相关工单 end-->
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<input type="hidden" id="ids" name="ids"/>
				<input type="hidden" id="rids" name="rids"/>
				<input type="hidden" id="flowNames" name="flowNames"/>
 				全选&nbsp;<input type="checkbox" name="checkAll" id="checkAll" onclick="check(this)">&nbsp;&nbsp;
 				<c:if test="${isHided=='1'}">
 					<input type="hidden" id="isHide" name="isHide" value="0"/>
 					<input type="submit" class="submit" name="method.save" id="method.save" value="取消隐藏" >
 				</c:if>
 				<c:if test="${isHided=='0'}">
 					<input type="hidden" id="isHide" name="isHide" value="1"/>
 					<input type="submit" class="submit" name="method.save" id="method.save" value="隐藏工单" >
 				</c:if>
			</td>
		</tr>		
		</display:footer>
	</display:table>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
