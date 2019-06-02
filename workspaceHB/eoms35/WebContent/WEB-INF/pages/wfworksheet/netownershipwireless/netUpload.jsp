<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	Ext.Ajax.request({
		url : 'netownershipwireless.do?method=showNetType',
		method : 'POST',
		success : function(response) {
			var pro = Ext.get('netTypeChoiceExpression');
			var data = Ext.util.JSON.decode(response.responseText);
			Ext.each(data,function(d){
				var mySelect = document.getElementById("netTypeChoiceExpression"); 
				var opp = new Option(d.netType,d.netType); 
				mySelect.add(opp); 
			});
		},
		failure : function(response, options) {
			Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
		}
	});
})
function importTemp() {
	eoms.form.disable("importTempButton");
	var template = document.getElementById("mainImportTemplate").value ;
	if(template.length<=24 && template.length != 0 ){
		//截取  xxx.xls 
		var firstSubTempValue = template.substring(1,19);
		//截取文件后缀
		var tempType = firstSubTempValue.substring(15,19);
		//alert(tempType+"....");
		if(tempType=='xls'){
			Ext.MessageBox.wait("正在验证数据，请稍候...","提示");  
			Ext.Ajax.timeout = 120000;
			Ext.Ajax.request({
				method:"post",
				url: "netownershipwireless.do?method=importExcel&accessoriesname="+template,
				success: function(x){
					var data = eoms.JSONDecode(x.responseText);					
					Ext.MessageBox.hide();															
					Ext.each(data,function(d){
						Ext.Msg.alert("提示",d.alarmMsg);
					});			
				},
				failure : function(response,options){
					var reqst=response.status;        // 根据返回的状态码值判断是否超时
					alert(response.status);
					Ext.MessageBox.hide();
					if(reqst=='-1'){                  // 超时的状态码为 -1
						Ext.Msg.alert("提示","验证超时,请减小数据量试试^-^,若还不行请联系管理员！");
					}else{
						Ext.Msg.alert('提示','Sorry！验证异常,很可能是您的excel有问题！');
					}
				}
			});
		}else{
			Ext.Msg.alert("提示",'模板必需是“XXX.xls”格式！');
			eoms.form.enable("importTempButton");
			return false; 
		}

	}else if(template.length>24 ){
		Ext.Msg.alert("提示",'一次只能导入一个模板！');
		eoms.form.enable("importTempButton");
		return false; 
	}else{
		Ext.Msg.alert("提示",'请上传EXCEL格式的模板！');
		eoms.form.enable("importTempButton");
		return false; 
	}
}
</script>
<html:form action="/netownershipwireless.do?method=exportExcel&appId=netownershipwireless" method="post" styleId="theform">
<table class="formTable">
  <tr>
			<td class="label" colspan="4">查询条件</td>
  </tr>
  <tr>
			<td class="label">地市/区县</td>
			<td class="content">
			  <input type="button" id="areabtn" value="选择涉及地市和区县" class="btn"/>
    		  <br/><br/>	
    		     地市:<textarea class="textarea max" readonly="true" name="eomsCity" style="height:50px" id="eomsCity"  ></textarea><br/>
    			 区县:<textarea class="textarea max" readonly="true" name="eomsCounty" style="height:50px" id="eomsCounty"></textarea>
                 <input type="hidden" name="eomsCountyIdStringExpression" value="in"/>
                 <input type="hidden" name="eomsCityIdStringExpression" value="in"/>
                 <input type="hidden" name="main.eomsCountyId" id="eomsCountyId"/>
                 <input type="hidden" name="main.eomsCityId" id="eomsCityId"/>  
<script type="text/javascript">
Ext.onReady(function(){
function callback(jsonData,str){
	var shengNameArr=[], shengIdArr=[],shiNameArr=[], shiIdArr=[];
	eoms.log(jsonData);
	Ext.each(jsonData,function(data){
		switch(data.id.length){
			case 4 :
			  shengNameArr.push(data.name);
			  shengIdArr.push(data.id);
			  break;
			case 6 :
			  shiNameArr.push(data.name);
			  shiIdArr.push(data.id);
			  break;
		}
	});
	$('eomsCity').value = shengNameArr.join(",");
	$('eomsCounty').value = shiNameArr.join(",");
	$('eomsCountyId').value = shiIdArr.join(",");
	$('eomsCityId').value = shengIdArr.join(",");
}
	  var treeAction='${app}/area/tawSystemAreas.do?method=getNodes';
	  var cfg = {
		btnId:'areabtn',
		baseAttrs:{checked:false},
		treeDataUrl:treeAction,
		treeRootId:'-1',
		treeRootText:'地域树图',
		treeChkMode:'',
		treeChkType:'area',
		callback:callback
	}
	var areaTree = new xbox(cfg);
	areaTree.onBeforeCheck = function(node,checked){
		if(checked && node.parentNode){
			node.parentNode.getUI().toggleCheck(true);
		}
		return true;
	}
});
</script> 
			</td>
		</tr>		
		<tr>
			<td class="label">网元类型</td>
			<td class="content">
              	  <input type="hidden" name="main.netType" id="netType"/> 
                  <select name="netTypeChoiceExpression"  id="netTypeChoiceExpression">
                  	<option value="" selected="selected">请选择 </option>
   	              </select> 
              </td>
		</tr>
		
		<tr>
			<td class="label">网元名称</td>
			<td class="content">
				<input type="text" name="main.netName" class="text max" id="netName" alt="allowBlank:false" />
				<input type="hidden" name="netNameStringExpression" value="like"/>
			</td>
		</tr>
		
			<tr>
			<td class="label">网元ID</td>
			<td class="content">
				<input type="text" name="main.netId" class="text max" id="netId" alt="allowBlank:false" />(支持用英文逗号隔开的多个查询)
				<input type="hidden" name="netIdStringExpression" value="like"/>
			</td>
		</tr>
		<tr>
			<td class="label">维护班组</td>
			<td class="content">
                  <select name="teamRoleIdChoice"  id="teamRoleIdChoice">
                  	<option value="allchoice" selected="selected">全选</option>
                  	<option value="isnull">为空</option>
                  	<option value="isnotnull">不为空</option>
   	              </select> 
              </td>
		</tr>
		<tr>
			<td colspan="4" > 
				<input type="submit" id="exportTempButton" value="生成模板"  class="button" onclick="javascript:exportTemp();">				
			</td>
		</tr>
</table>
<br>
<table class="formTable">
	<tr>
		<td class="label">批量导入</td>
		<td class="content" colspan="3">
			<eoms:attachment name="sheetLink" property="mainImportTemplate" scope="request" 
				idField="mainImportTemplate" appCode="netownershipwireless" /> 
		</td>
	</tr>
	<tr>
			<td colspan="4" > 
				<input type="button" id="importTempButton" value="提交" class="button"  onclick="javascript:importTemp();" />			
			</td>
		</tr>
</table>
</html:form>
<script type="text/javascript">
function exportTemp() {
var thisform = document.forms[0];
thisform.action = "${app}/sheet/netownershipwireless/netownershipwireless.do?method=exportExcel&appId=netownershipwireless";
thisform.submit();
eoms.form.disable("exportTempButton");
}
</script>