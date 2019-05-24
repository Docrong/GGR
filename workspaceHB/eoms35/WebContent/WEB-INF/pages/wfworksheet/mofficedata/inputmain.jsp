﻿<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	request.setAttribute("roleId", "58701");
	long localTimes = com.boco.eoms.base.util.StaticMethod
			.getLocalTime().getTime();
%>
<%@ include
	file="/WEB-INF/pages/wfworksheet/mofficedata/baseinputmainhtmlnew.jsp"%>
<script type="text/javascript">
var xboxName = "";
var xdictName = "";
var xdictNamede = "";
var currentDate = new Date();
function checkField(val)
{
	document.getElementById("linkPlanEndTime").value = val;
}
function newBoxPro(objName,objId){
	if(xboxName.indexOf(objName)<0){
		var	userFromDeptAction='${app}/xtree.do?method=userFromDept';	  
		var	flowAction='${app}/role/tawSystemRoles.do?method=xGetSubRoleNodesFromArea&flowId=587&roleId=58703';
		if('${chooseType}' == 'user'){
			new xbox({
				btnId:objName,dlgId:'hello-dlg3',
				treeDataUrl:userFromDeptAction,treeRootId:'-1',treeRootText:'人员',treeChkMode:'single',treeChkType:'user',
				showChkFldId:objName,saveChkFldId:objId
			});
		}
		if('${chooseType}' == 'subrole'){
			new xbox({
				btnId:objName,dlgId:'hello-dlg4',
				treeDataUrl:flowAction,treeRootId:'-1',treeRootText:'局数据制作人',treeChkMode:'single',treeChkType:'subrole',
				showChkFldId:objName,saveChkFldId:objId
			});
		}  
		xboxName=xboxName+','+objName;
	}
}


function newBoxPro3(objName,objId){ 
	if(xboxName.indexOf(objName)<0){
		var	flowAction='${app}/role/tawSystemRoles.do?method=xGetSubRoleNodesFromArea&flowId=587&roleId=58703';
		new xbox({
			btnId:objName,dlgId:'hello-dlg4',
			treeDataUrl:flowAction,treeRootId:'-1',treeRootText:'局数据制作人',treeChkMode:'single',treeChkType:'subrole',
			showChkFldId:objName,saveChkFldId:objId
		});
		xboxName=xboxName+','+objName;
	}

}

function newBoxDict(objName,objId,dictId,dictName){
	if(xdictName!=objName){
		var	dictAction='${app}/xtree.do?method=dict';
		new xbox({
			btnId:objName,dlgId:'hello-dlg5',
			treeDataUrl:dictAction,treeRootId:dictId,treeRootText:dictName,treeChkMode:'',treeChkType:'dict',
			showChkFldId:objName,saveChkFldId:objId
	   });
	   xdictName=objName;
	}
}

function newBoxDictde(objName,objId,dictId,dictName){
	if(xdictNamede!=objName){
		var	dictAction='${app}/xtree.do?method=dict';
		new xbox({
			btnId:objName,dlgId:'hello-dlg5',
			treeDataUrl:dictAction,treeRootId:dictId,treeRootText:dictName,treeChkMode:'',treeChkType:'dict',
			showChkFldId:objName,saveChkFldId:objId
	   });
	   xdictNamede=objName;
	}
}

function newBoxDictde1(objName,objId,dictId,dictName){
	if(xdictNamede.indexOf(objName)<0){
		var	dictAction='${app}/xtree.do?method=dict';
		new xbox({
			btnId:objName,dlgId:'hello-dlg5',
			treeDataUrl:dictAction,treeRootId:dictId,treeRootText:dictName,treeChkMode:'single',treeChkType:'dict',
			showChkFldId:objName,saveChkFldId:objId
	   });
	   xdictNamede=xdictNamede+','+objName;;
	}
}

function setDownLoadURL(obj,settleId){
	Ext.MessageBox.wait("正在验证数据，请稍候...","提示");  
	Ext.Ajax.request({
		method:"get",
		url: "mofficedata.do?method=getTemplatePath&tbn="+obj,
		success: function(x){
			var o = eoms.JSONDecode(x.responseText);
			Ext.MessageBox.hide();
			if(o.status=='2'){
				alert(o.text);
			}else if (o.status=='0' ){
				document.getElementById(settleId).href="/sheet/mofficedata/mofficedata.do?method=downfile&mainStyle=mainStyle1&url="+encodeURI(o.url);
				document.getElementById(settleId).innerHTML=o.filename;
			}
		},
		failure : function(response,options){
			var reqst=response.status;        // 根据返回的状态码值判断是否超时
			Ext.MessageBox.hide();
			if(reqst=='-1'){                  // 超时的状态码为 -1
				Ext.Msg.alert("提示","验证超时,请联系管理员！");
				return false ;
			}else{
				Ext.Msg.alert('提示','验证异常,请联系管理员！');
				return false ;
			}
		}
	});
}
var globalNetCutId='${prolistnum}';
if(''==globalNetCutId){
	globalNetCutId='1';
}
var flag = 0;
function addBasestation() {
	globalNetCutId ++;
	var tableObj = document.getElementById("sheet2");
	var length=tableObj.rows.length;
  	var tr=document.createElement("tr");
  	tr.id="sheet2"+globalNetCutId;
  	if (parseFloat(globalNetCutId)%2==1) {
  		tr.className="even";
  	}
  	var td1=document.createElement("td");
	innerStr = "<input type=\"hidden\" name=\"proid\" value=\"\" /><select name=\"buissType\" onchange=\"setDownLoadURL(this.value,'newProject"+globalNetCutId+"');\" alt=\"allowBlank:false\" >" +
			"<option value=\"\" selected=\"selected\">请选择</option>";
	<c:forEach var="buiss" items="${buiss}">
		innerStr += "<option value=\"${buiss.buisValue}\" >${buiss.buisName}</option>";
	</c:forEach>
	td1.innerHTML = innerStr;
	tr.appendChild(td1);
	
	var td2=document.createElement("td");
	innerStr = "<select name=\"majorType\" alt=\"allowBlank:false\" ><option value=\"\" selected=\"selected\">请选择</option>";
	<c:forEach var="maj" items="${majors}">
		innerStr += "<option value=\"${maj.dictId}\" >${maj.dictName}</option>";
	</c:forEach>
	td2.innerHTML = innerStr;
	tr.appendChild(td2);
	
	var td3=document.createElement("td");
	innerStr = "<input type=\"text\" class=\"text max\" readonly=\"readonly\" name=\"producerName\" id=\"producerName"+globalNetCutId+"\" onclick=\"newBoxPro('producerName"+globalNetCutId+"','producerId"+globalNetCutId+"');\"/> ";
	innerStr += "<input type=\"hidden\" name=\"producerId\"  id=\"producerId"+globalNetCutId+"\" alt=\"allowBlank:false,vtext:'请选择制作人！'\" /> ";
	td3.innerHTML = innerStr;
	tr.appendChild(td3);
	
	var td4=document.createElement("td");
	innerStr = "<input type=\"text\" class=\"text max\"  name=\"netName\" id=\"netName"+globalNetCutId+"\" alt=\"allowBlank:false,maxLength:100,vtext:'请填入变更网元，最多输入100字符'\"/> ";
	innerStr += "<input type=\"hidden\" name=\"netId\"  id=\"netId"+globalNetCutId+"\" alt=\"allowBlank:true,vtext:'请选择网元类型！'\" /> ";
	td4.innerHTML = innerStr;
	tr.appendChild(td4);
	
	
	
	
	var td5=document.createElement("td");
	innerStr = "<select name=\"mainStyle9\" alt=\"allowBlank:false\" ><option value=\"\" selected=\"selected\">请选择</option>";
	<c:forEach var="maj" items="${cityList}">
		innerStr += "<option value=\"${maj.keyId}\" >${maj.keyName}</option>";
	</c:forEach>
	td5.innerHTML = innerStr;
	tr.appendChild(td5);
	
	
	var td6=document.createElement("td");
	innerStr = "<input type=\"text\" class=\"text max\"  name=\"mainStyle10\" id=\"mainStyle10"+globalNetCutId+"\" alt=\"allowBlank:false,maxLength:1000,vtext:'请填入网元名称，最多输入1000字符'\"/> ";
	td6.innerHTML = innerStr;
	tr.appendChild(td6);
	
	
	
	
	
	var td7=document.createElement("td");
	innerStr = "<input type=\"text\" class=\"text max\" readonly=\"readonly\" name=\"deviceName\" id=\"deviceName"+globalNetCutId+"\" onclick=\"newBoxDictde('deviceName"+globalNetCutId+"','deviceId"+globalNetCutId+"','1010103','设备厂家');\"/> ";
	innerStr += "<input type=\"hidden\" name=\"deviceId\"  id=\"deviceId"+globalNetCutId+"\" alt=\"allowBlank:ture,vtext:'请选择设备厂家！' \" value=\"\" /> ";
	td7.innerHTML = innerStr;
	td7.style.display = "none";
	tr.appendChild(td7);
	
	var td8=document.createElement("td");
	innerStr = "<a id=\"newProject"+globalNetCutId+"\" href=\"\" target=\"_blank\"></a>";	
	td8.innerHTML = innerStr;
	tr.appendChild(td8);
	
	var td9=document.createElement("td");
	innerStr = "<iframe id=\"UIFrame1-accessories"+globalNetCutId+"\" name=\"UIFrame1-accessories"+globalNetCutId+"\" class=\"uploadframe\" frameborder=\"0\" "
            + "scrolling=\"auto\" src=\"${app}/accessories/pages/upload.jsp?appId=mofficedata&filelist=null&idField=accessories"+globalNetCutId+"\" style=\"height:80%;width:100%\"></iframe><input type=\"hidden\" "
            + "name=\"accessories\" id=\"accessories"+globalNetCutId+"\" alt=\"allowBlank:false,blankText:'请上传附件！'\" />";
	td9.innerHTML = innerStr;
	tr.appendChild(td9);
	
	var td10=document.createElement("td");
	innerStr = "<img id='' name='' src='${app}/images/icons/delete.gif' border='0' onclick='deleteMaterial(this.parentElement.parentElement)'style='vertical-align: middle;'>"; 
  	td10.innerHTML = innerStr;
	tr.appendChild(td10);
	
	
	var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
	if ( userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !userAgent.indexOf("Opera") > -1 ) {//判断是否IE浏览器
        tableObj.tBodies[0].insertBefore(tr,null);
    }else{
    	tableObj.insertBefore(tr,null);
    } 
}
function addBasestation3() {
	globalNetCutId ++;
	var tableObj = document.getElementById("sheet3");
	var length=tableObj.rows.length;
  	var tr=document.createElement("tr");
  	tr.id="sheet3"+globalNetCutId;
  	if (parseFloat(globalNetCutId)%2==1) {
  		tr.className="even";
  	}
	
	var td3=document.createElement("td");
	innerStr = "<input type=\"hidden\" name=\"proid\" value=\"\" /><input type=\"text\" class=\"text max\" readonly=\"readonly\" name=\"producerName1\" id=\"producerName"+globalNetCutId+"\" onclick=\"newBoxPro3('producerName"+globalNetCutId+"','producerId"+globalNetCutId+"');\"/> ";
	innerStr += "<input type=\"hidden\" name=\"producerId1\"  id=\"producerId"+globalNetCutId+"\" alt=\"allowBlank:false,vtext:'请选择制作人！'\" /> ";
	td3.innerHTML = innerStr;
	tr.appendChild(td3);
	
var td4=document.createElement("td");
	innerStr = "<input type=\"text\" class=\"text max\"  name=\"netName1\" id=\"netName"+globalNetCutId+"\" alt=\"allowBlank:false,maxLength:100,vtext:'请填入变更网元，最多输入100字符'\"/> ";
	innerStr += "<input type=\"hidden\" name=\"netId1\"  id=\"netId"+globalNetCutId+"\" alt=\"allowBlank:true,vtext:'请选择网元类型！'\" /> ";
	td4.innerHTML = innerStr;
	tr.appendChild(td4);
	
		var td5=document.createElement("td");
	innerStr = "<input type=\"text\" class=\"text max\" readonly=\"readonly\" name=\"deviceName1\" id=\"deviceName"+globalNetCutId+"\" onclick=\"newBoxDictde1('deviceName"+globalNetCutId+"','deviceId"+globalNetCutId+"','1014002','设备厂家');\"/> ";
	innerStr += "<input type=\"hidden\" name=\"deviceId1\"  id=\"deviceId"+globalNetCutId+"\" alt=\"allowBlank:ture,vtext:'请选择设备厂家！' \" value=\"\" /> ";
	td5.innerHTML = innerStr;
	tr.appendChild(td5);
	
	var td8=document.createElement("td");
	innerStr = "<img id='' name='' src='${app}/images/icons/delete.gif' border='0' onclick='deleteMaterial(this.parentElement.parentElement)'style='vertical-align: middle;'>"; 
  	td8.align = "center";
  	td8.innerHTML = innerStr;
  	
	tr.appendChild(td8);
	
	var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
	if ( userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !userAgent.indexOf("Opera") > -1 ) {//判断是否IE浏览器
        tableObj.tBodies[0].insertBefore(tr,null);
    }else{
    	tableObj.insertBefore(tr,null);
    } 
	
}



function deleteMaterial(tr){
   if(confirm('确认移除当前行吗？')){
   	 tr.parentNode.removeChild(tr);
   }
   
}
function deleteMaterialByNets(tr,id){
	Ext.Ajax.request({
		method:"get",
		url: "mofficedata.do?method=delProMatch&id="+id,
		success: function(x){
			var data = eoms.JSONDecode(x.responseText);
			if(data.status=='2'){//调用异常
				alert(data.text);
				return false;
			}else if (data.status=='0' ){//调用正常，回填值	
				alert("删除成功！");
				tr.parentNode.removeChild(tr);
			}
		},
		failure : function(response,options){
			var reqst=response.status;        // 根据返回的状态码值判断是否超时
			Ext.MessageBox.hide();
			if(reqst=='-1'){                  // 超时的状态码为 -1
				Ext.Msg.alert("提示","验证超时,请联系管理员！");
				return false ;
			}else{
				Ext.Msg.alert('提示','验证异常,请联系管理员！');
				return false ;
			}
		}
	});
}

function changeModeType(obj){
	if(obj!=''&&obj==0){
		eoms.form.enableArea('sheet2');
		eoms.form.disableArea('sheet3',true);
	}else if(obj==1){
		eoms.form.enableArea('sheet3');
	    eoms.form.disableArea('sheet2',true); 
	}
	xboxName = "";
    xdictName = "";
    xdictNamede = "";
}

function changeModeType1(obj){
	if(obj.value!=''&&obj.value==0){
		document.getElementById("mainSendModeType").options[1].selected=true;
		document.getElementById("mainSendModeType").disabled = 'disabled';
		changeModeType('0');
	}else{
		document.getElementById("mainSendModeType").disabled = false;
	}
}
</script>
<input type="hidden" name="processTemplateName" value="MofficeData" />
<input type="hidden" name="operateName" value="newWorkSheet" />
<c:if test="${status!=1}">
	<input type="hidden" name="phaseId" id="phaseId" value="AuditDataTask" />
	<input type="hidden" id="operateType" name="operateType" value="0" />
	<input type="hidden" name="gatherPhaseId" id="gatherPhaseId"
		value="HoldTask" />
</c:if>
<c:if test="${status==1}">
	<input type="hidden" name="phaseId" id="phaseId" value="OverTask" />
</c:if>
<input type="hidden" name="beanId" value="iMofficeDataMainManager" />
<input type="hidden" name="producerType" value="${chooseType}" />
<input type="hidden" name="mainClassName"
	value="com.boco.eoms.sheet.mofficedata.model.MofficeDataMain" />
<input type="hidden" name="linkClassName"
	value="com.boco.eoms.sheet.mofficedata.model.MofficeDataLink" />
<br>

<!-- 工单基本信息 -->
<table id="sheet" class="formTable">
	<tr>
		<td class="label">受理时限*</td>
		<td class="content"><input type="text" class="text"
			name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
			id="${sheetPageName}sheetAcceptLimit"
			value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
			onclick="popUpCalendar(this, this)"
			alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false" />
		</td>
		<td class="label">完成时限*</td>
		<td class="content"><input type="text" class="text"
			name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
			id="${sheetPageName}sheetCompleteLimit"
			value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
			onblur="checkField(this.value)"
			onclick="popUpCalendar(this, this)"
			alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false" />
		</td>
	</tr>
		<tr style="display:none">
		<td class="label"><!-- 设备厂家 --> <bean:message
			bundle="mofficedata" key="mofficeDataMain.mainDeviceFa" />*</td>
		<td class="content" colspan="3">
			<input type="text" class="text max" readonly="readonly" name="mainDeviceFas" id="mainDeviceFas" />            	
         
		</td>
	</tr>
	
	<tr>
		<td class="label">专业科室*</td>
		<td colspan="3">
		
				<select name="mainStyle5" id="mainStyle5" onchange="changeModeType1(this);" alt="allowBlank:false,vtext:'请选择专业科室！'">
				  <option value ="">请选择</option>
				  <option value ="0">核心网</option>
				  <option value ="1">互联网</option>
				</select>
	
		</td>
	</tr>
	
	
	<tr>
		<td class="label">制作模式*</td>
		<td class="content">
		
				<select name="mainSendModeType" id="mainSendModeType" onchange="changeModeType(this.value);" alt="allowBlank:false,vtext:'请选择制作模式！'">
				  <option value ="">请选择</option>
				  <option value ="0">自动模式</option>
				  <option value ="1">手动模式</option>
				</select>
	
		</td>
		<td class="label"><bean:message bundle="mofficedata"
			key="mofficeDataMain.mainDataNo" />*</td>
		<td class="content"><input type="text" class="text"
			name="mainDataNo" id="mainDataNo"
			alt="allowBlank:false,maxLength:100,vtext:'请填入 局数据编号 信息，最多输入 100 字符'"
			value="${sheetMain.mainDataNo}" /></td>
	</tr>
	<tr>
		<!-- 网络一级分类 -->
		<td class="label">网络一级分类*</td>
		<td><eoms:comboBox name="${sheetPageName}mainNetTypeOne"
			id="${sheetPageName}mainNetTypeOne"
			sub="${sheetPageName}mainNetTypeTwo" initDicId="1010104"
			defaultValue="${sheetMain.mainNetTypeOne}" alt="allowBlank:false" />
		</td>
		<!-- 网络二级分类 -->
		<td class="label">网络二级分类</td>
		<td><eoms:comboBox name="${sheetPageName}mainNetTypeTwo"
			id="${sheetPageName}mainNetTypeTwo"
			sub="${sheetPageName}mainNetTypeThree"
			initDicId="${sheetMain.mainNetTypeOne}"
			defaultValue="${sheetMain.mainNetTypeTwo}" /></td>
	</tr>
	<tr>
		<!-- 网络三级分类 -->
		<td class="label">网络三级分类</td>
		<td colspan="3"><eoms:comboBox
			name="${sheetPageName}mainNetTypeThree"
			id="${sheetPageName}mainNetTypeThree"
			initDicId="${sheetMain.mainNetTypeTwo}"
			defaultValue="${sheetMain.mainNetTypeThree}" /></td>
	</tr>
	<tr>
		<td class="label">计划开始时间*</td>
		<td><input type="text" class="text"
			name="${sheetPageName}linkPlanStartTime" readonly="readonly"
			id="${sheetPageName}linkPlanStartTime"
			value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
			onclick="popUpCalendar(this, this)"
			alt="vtype:'lessThen',link:'${sheetPageName}linkPlanEndTime',vtext:'计划开始时间不能晚于计划结束时间',allowBlank:false" />
		</td>
		<td class="label">计划结束时间*</td>
		<td><input type="text" class="text"
			name="${sheetPageName}linkPlanEndTime" readonly="readonly"
			id="${sheetPageName}linkPlanEndTime"
			value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
			onclick="popUpCalendar(this, this)"
			alt="vtype:'moreThen',link:'${sheetPageName}linkPlanStartTime',vtext:'计划结束时间不能早于计划开始时间',allowBlank:false" />
		</td>
	</tr>
	<tr>
		<td class="label">变更来源*</td>
		<td class="content" colspan="1"><eoms:comboBox
			name="${sheetPageName}mainChangeSource"
			id="${sheetPageName}mainChangeSource" initDicId="1010901"
			defaultValue="${sheetMain.mainChangeSource}" alt="allowBlank:false" />
		</td>
		<td class="label">技术方案关键字*</td>
		<td class="content"><input type="text" class="text"
			name="linkDesignKey" id="linkDesignKey"
			alt="allowBlank:false,maxLength:100,vtext:'请填入 技术方案关键字 信息，最多输入 50 字符'"
			value="${sheetMain.linkDesignKey}" /></td>
	</tr>
	<tr>
		<td class="label"><bean:message bundle="mofficedata"
			key="mofficeDataMain.mainDescRemark" />*</td>
		<td colspan="3"><textarea class="textarea max"
			name="mainDescRemark" id="mainDescRemark"
			alt="allowBlank:false,maxLength:500,vtext:'请填入 内容描述 信息，最多输入 500 字符'">${sheetMain.mainDescRemark}</textarea>
		</td>

	</tr>
	<tr>
		<td class="label"><bean:message bundle="sheet"
			key="mainForm.accessories" /></td>
		<td colspan="3"><eoms:attachment name="sheetMain"
			property="sheetAccessories" scope="request"
			idField="sheetAccessories" appCode="mofficedata"
			alt="allowBlank:true" /></td>
	</tr>
</table>
<br>

<c:choose>
	<c:when test="${prolist!=null }&&${delFlag=='true' }">
		<c:if test="${sheetMain.mainSendModeType==0 }">
		<table id="sheet2" class="formTable">
			<thead>
				<tr>
					<td class="label" align="center">业务类型</td>
					<td class="label" align="center">专业</td>
					<td class="label" align="center">制作人</td>
					<td class="label" align="center">网元类型</td>
					
					<td class="label" align="center">地市</td>
					<td class="label" align="center">网元名称</td>
					
					<td class="label" align="center" style="display:none" >设备厂家</td>
					<td class="label" align="center">业务标准模板</td>
					<td class="label" align="center">上传附件</td>
					<td class="label" align="center">操作<c:if test="${delFlag=='true' }">
							<img src='${app}/images/icons/add.gif' border='0' onclick="addBasestation()" style="vertical-align: middle;">
						</c:if></td>
				</tr>
				<c:forEach var="mat" items="${prolist}" varStatus="status">
					<input type="hidden" name="proid" value="${mat.id}" />
					<tr>
						<td><select name="buissType"
							onchange="setDownLoadURL(this.value,'newProject');">
							<option value="">请选择</option>
							<c:forEach var="buiss" items="${buiss}">
								<c:if test="${mat.buissType==buiss.buisValue}">
									<option value="${buiss.buisValue}" selected="selected">${buiss.buisName}</option>
								</c:if>
								<c:if test="${mat.buissType==buiss.buisValue}">
									<option value="${buiss.buisValue}">${buiss.buisName}</option>
								</c:if>
								<option value="${buiss.buisValue}">${buiss.buisName}</option>
							</c:forEach>
						</select></td>
						<td><select name="majorType" alt="allowBlank:false">
							<option value="">请选择</option>
							<c:forEach var="maj" items="${majors}">
								<c:if test="${mat.majorType==maj.dictId}">
									<option value="${maj.dictId}" selected="selected">${maj.dictName}</option>
								</c:if>
								<c:if test="${mat.majorType!=maj.dictId}">
									<option value="${maj.dictId}">${maj.dictName}</option>
								</c:if>
							</c:forEach>
						</select></td>
						<td>
							<input type="text" class="text max" readonly="readonly" name="producerName" id="producerName${status.index + 1}" value="${mat.producerName }"/> 
							<input type="hidden" name="producerId" id="producerId${status.index + 1}" alt="allowBlank:false,vtext:'请选择制作人！'" value="${mat.producerId}"/>
						</td>
						<td>
							<input type="text" class="text max" readonly="readonly" value="${mat.netName }" name="netName" id="netName${status.index + 1}" /> 
							<input type="hidden" name="netId" id="netId${status.index + 1}" value="${mat.netId }" alt="allowBlank:false,vtext:'请选择网元类型！'" />
						</td>
						
						
						<td>
							<select name="mainStyle9"  alt="allowBlank:false">
								<option value="">请选择</option>
								<logic:iterate id="cityList" name="cityList">
									<option value="${cityList.keyId}" <c:if test="${mat.mainStyle9==cityList.keyId}">selected="selected"</c:if>>${cityList.keyName}</option>
								</logic:iterate>
							</select>
						</td>
						
						<td>
							<input type="text" class="text max" readonly="readonly" value="${mat.mainStyle10}" name="mainStyle10" id="mainStyle10${status.index + 1}" /> 
						</td>
						
						
						
						<td>
							<input type="text" class="text max" readonly="readonly" name="deviceName" id="deviceName${status.index + 1}" value="${mat.deviceName }"/> 
							<input type="hidden" name="deviceId" id="deviceId${status.index + 1}" value="101010360" alt="allowBlank:false,vtext:'请选择设备厂家！'" />
						</td>
						<td><a id="newProject" href="" target="_blank"></a></td>
						<td>
							<iframe id="UIFrame1-accessories${status.index + 1}" name="UIFrame1-accessories${status.index + 1}" class="uploadframe" frameborder="0" scrolling="auto"
							src="${app}/accessories/pages/upload.jsp?appId=mofficedata&filelist=${mat.accessories }&idField=accessories${status.index + 1}"
							style="height:80%;width:100%"></iframe> 
							<input type="hidden" name="accessories" id="accessories${status.index + 1}" alt="allowBlank:false,blankText:'请上传附件！'" /></td>
						<td align="center">
							<c:if test="${delFlag=='true' }">
								<img src='${app}/images/icons/delete.gif' border="0" onclick="deleteMaterialByNets(this.parentElement.parentElement,'${mat.id}')" style="vertical-align: middle;">
							</c:if>
						</td>
					</tr>
				</c:forEach>
				
			</thead>
		</table>
		</c:if>
		<c:if test="${sheetMain.mainSendModeType==1}">
			<table id="sheet3" class="formTable">
			<thead>
				<tr>					
					<td class="label" align="center">制作人</td>
					<td class="label" align="center">网元类型</td>
					<td class="label" align="center">操作<c:if test="${delFlag=='true' }">
							<img src='${app}/images/icons/add.gif' border='0' onclick="addBasestation3()" style="vertical-align: middle;">
						</c:if></td>
				</tr>
				<c:forEach var="mat" items="${prolist}" varStatus="status">
					<input type="hidden" name="proid" value="${mat.id}" />
						<td>
							<input type="text" class="text max" readonly="readonly" name="producerName" id="producerName${status.index + 1}" value="${mat.producerName }"/> 
							<input type="hidden" name="producerId" id="producerId${status.index + 1}" alt="allowBlank:false,vtext:'请选择制作人！'" value="${mat.producerId}"/>
						</td>
						<td>
							<input type="text" class="text max" readonly="readonly" value="${mat.netName }" name="netName" id="netName${status.index + 1}" /> 
							<input type="hidden" name="netId" id="netId${status.index + 1}" value="${mat.netId }" alt="allowBlank:false,vtext:'请选择网元类型！'" />
						</td>
						<td>
							<input type="text" class="text max" readonly="readonly" name="deviceName" id="deviceName${status.index + 1}" value="${mat.deviceName }"/> 
							<input type="hidden" name="deviceId" id="deviceId${status.index + 1}" value="101010360" alt="allowBlank:false,vtext:'请选择设备厂家！'" />
						</td>
						<td align="center">
							<c:if test="${delFlag=='true' }">
								<img src='${app}/images/icons/delete.gif' border="0" onclick="deleteMaterialByNets(this.parentElement.parentElement,'${mat.id}')" style="vertical-align: middle;">
							</c:if>
						</td>
					</tr>
				</c:forEach>
				
			</thead>
		</table>
		</c:if>
	</c:when>
	<c:otherwise>
		<table id="sheet2" class="formTable">
			<thead>
				<tr>
					<td class="label" align="center">业务类型</td>
					<td class="label" align="center">专业</td>
					<td class="label" align="center">制作人</td>
					<td class="label" align="center">网元类型</td>
					
					<td class="label" align="center">地市</td>
					<td class="label" align="center">网元名称</td>
					
					<td class="label" align="center" style="display:none" >设备厂家</td>
					<td class="label" align="center">业务标准模板</td>
					<td class="label" align="center">上传附件</td>
					<td class="label" align="center">操作<img src='${app}/images/icons/add.gif' border='0' onclick="addBasestation()" style="vertical-align: middle;"></td>
				</tr>
				<tr>
					<input type="hidden" name="proid" value="" />
				</tr>
			</thead>
		</table>
		<table id="sheet3" class="formTable">
			<thead>
				<tr>
					<td class="label" align="center">制作人</td>
					<td class="label" align="center">网元类型</td>
					<td class="label" align="center">设备厂家</td>
					<td class="label" align="center">操作<img src='${app}/images/icons/add.gif' border='0' onclick="addBasestation3()" style="vertical-align: middle;">						</td>
				</tr>
				<tr>
					<input type="hidden" name="proid" value="" />
				</tr>
			</thead>
		</table>
	</c:otherwise>
</c:choose>

<!--派单树-->
<br />
<fieldset><legend> <bean:message bundle="sheet"
	key="mainForm.toOperateRoleName" />： <span id="roleName">局数据制作审批人
</span> </legend>
<div class="x-form-item">
	<eoms:chooser id="sendObject" type="role"
	roleId="58702" flowId="587" config="{returnJSON:true,showLeader:true}"
	category="[{id:'dealPerformer',childType:'subrole',limit:'1',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
	data="${xxxxxxx}" />
	</div>
</fieldset>



