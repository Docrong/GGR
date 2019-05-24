<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>



<form method="post" id="theform" action="autot1crm.do?method=save">
<input type="hidden" name="templatename" value="${flowId }"> 
<table class="formTable">
			<caption>自动接单人配置</caption>

		   
           	<!-- T1受理角色 -->  
			<tr>
	            <td class="label"> 
	              	受理角色 *
	            </td>
	            <td  class="content">
	            	<eoms:id2nameDB id="${autot1Crm.autoacceptrolet1}" beanId="tawSystemSubRoleDao"/>
	            	<br/>
	               <select id="sendsel" ${type == 'open' ? 'disabled="disabled"': ''}>
	                 <option value=""><bean:message bundle="sheet" key="query.status.pleaseSelect"/></option>
	                 <option value="role"><bean:message bundle="sheet" key="query.status.role"/></option>
	               </select>
	               <div id="viewer" class="viewer-list"></div>
	               <input type="button" name="showSendRole" value="<bean:message bundle="sheet" key="query.status.selectRole"/>" class="btn" id="showSendRole" >
	               <input type="hidden" name="autoacceptrolet1" id="toSendRoleId" value="${autot1Crm.autoacceptrolet1}"/>
	               
	               
	         
	            </td> 
            </tr>   
             	<!-- T1受理对象 -->  
			<tr>
	            <td class="label"> 
	              	受理人员 * 
	            </td>
	            <td  class="content">
	            	<eoms:id2nameDB id="${autot1Crm.remark1}" beanId="tawSystemUserDao"/>
	            	<br/> 
	               <select id="usersel" ${type == 'open' ? 'disabled="disabled"': ''}>
	                 <option value=""><bean:message bundle="sheet" key="query.status.pleaseSelect"/></option>
	                 <option value="user"><bean:message bundle="sheet" key="query.status.user"/></option>
	               </select>
	               <div id="viewer-user" class="viewer-list"></div>
	               <input type="button" name="showSendUser" value="<bean:message bundle="sheet" key="query.status.selectUser"/>" class="btn" id="showSendUser" >
	               <input type="hidden" name="remark1" id="toSendUserId" value="${autot1Crm.remark1}"/>	        
	            </td> 
            </tr>
            
             <tr > 
	            <td class="label">
	              	分派对象 *
	            </td>
	            <td class="content">
	            	<eoms:id2nameDB id="${autot1Crm.dealrolet2}" beanId="tawSystemUserDao"/>
	            	<br/>
	               <select id="dealsel" ${type == 'open' ? 'disabled="disabled"': ''}>
	                 <option value=""><bean:message bundle="sheet" key="query.status.pleaseSelect"/></option>
	                 <option value="user"><bean:message bundle="sheet" key="query.status.user"/></option>
	               </select>
	            	<div id="viewer-deal" class="viewer-list"></div>
	                <input type="hidden" name="dealrolet2" id="toDealRoleId" value="${autot1Crm.dealrolet2}"/>
	                <input type="button" name="showDealRole" value="<bean:message bundle="sheet" key="query.status.selectRole"/>" class="btn" id="showDealRole" >
	                
	            </td>
            </tr> 
		   	<tr>
			  		<td class="label">地区 *：</td>
			  		<td width="100%"> 
			  			<select id="faultsite" name="faultsite" ${type == 'open' ? 'disabled="disabled"': ''}>
			  				<option value="${autot1Crm.faultsite}">${autot1Crm.faultsite}</option>
			  	 			<option value="HB.WH">武汉</option>
			  	 			<option value="HB.YC">宜昌</option>
			  	 			<option value="HB.ES">恩施</option>
			  	 			<option value="HB.JZ">荆州</option>
			  	 			<option value="HB.JH">江汉</option>
			  	 			<option value="HB.JM">荆门</option>
			  	 			<option value="HB.HS">黄石</option>
			  	 			<option value="HB.EZ">鄂州</option>
			  	 			<option value="HB.XN">咸宁</option>
			  	 			<option value="HB.HG">黄冈</option>
			  	 			<option value="HB.XF">襄樊</option>
			  	 			<option value="HB.SZ">随州</option>
			  	 			<option value="HB.SY">十堰</option>
			  	 			<option value="HB.XG">孝感</option>
			  	 		</select>
			  		</td>					    
		   	</tr>
		   	<tr>
		   	<td class="label">备注：</td>
		   	<td width="100%">
		   		<textarea class="textarea max" ${type == 'open' ? 'disabled="disabled"': ''} name="remark2">${autot1Crm.remark2 }</textarea>
		   		
		   	</td>
		   	</tr>
		   	<tr>
			  		<td class="label">功能开/关：</td>
			  		<td width="100%">
			  	 		<select name="colseswitch" ${type == 'open' ? 'disabled="disabled"': ''}>
			  	 			
			  	 			<option value="yes" ${autot1Crm.colseswitch == 'yes' ? 'selected' : ''}>开</option>
			  	 			<option value="no" ${autot1Crm.colseswitch == 'no' ? 'selected' : ''}>关</option>
			  	 		</select>
			  		</td>					  
		   	</tr>
		<logic:notEqual name="type" value="open">
			<tr>
				<td colspan="2">
					<input type="hidden" name="id" value="${autot1Crm.id}">
					<input type="submit" value="提交" class="btn" >
				</td>
			</tr>
		</logic:notEqual>
		<logic:equal name="type" value="open">
			<tr>
				<td colspan="2">
					<input type="button" value="编辑" class="btn" onclick="toedit();">
					<input type="button" value="删除" class="btn" onclick="todelete();">
				</td>
			</tr>
		</logic:equal>
        
</table>
<script type="text/javascript">  
Ext.onReady(function(){					
    //地域
    var showAreaId = document.getElementById("showArea");
    if (showAreaId != null) {
	    var	areatreeAction='${app}/xtree.do?method=areaTree';
	    deptetree = new xbox({
	      btnId:'showArea',
	      treeDataUrl:areatreeAction,treeRootId:'-1',treeRootText:'地市',treeChkMode:'radio',treeChkType:'area',
	      showChkFldId:'showArea',saveChkFldId:'toAreaId'
	    });
    }
    
    var treeId = "sendrole";
    var flowId = "${flowId}";
    var	RoleTreeAction='${app}/role/tawSystemRoles.do?method=xGetSubRoleNodesFromFlow';  
    var	DptTreeAction='${app}/xtree.do?method=dept';   
    var	UserTreeAction='${app}/xtree.do?method=userFromDept';
    
    Viewer = new Ext.JsonView("viewer",
		'<div class="viewlistitem-{nodeType}">{name};</div>',
		{emptyText : '<div>没有选择项目</div>'}
	);
	Viewer2 = new Ext.JsonView("viewer-deal",
		'<div class="viewlistitem-{nodeType}">{name};</div>',
		{emptyText : '<div>没有选择项目</div>'}
	);
	Viewer3 = new Ext.JsonView("viewer-user",
		'<div class="viewlistitem-{nodeType}">{name};</div>',
		{emptyText : '<div>没有选择项目</div>'}
	);
	
    //受理角色
    var sendcfg = {
      btnId:'showSendRole',
      treeDataUrl: RoleTreeAction,treeRootId:flowId,treeRootText:'角色',treeChkMode:'single',treeChkType:'subrole',
      saveChkFldId:'toSendRoleId',viewer:Viewer
    };
    sendRoletree = new xbox(sendcfg);
    
    //处理角色
    var dealcfg = {
      btnId:'showDealRole',
      treeDataUrl: UserTreeAction,treeRootId:'1',treeRootText:'部门人员',treeChkMode:'single',treeChkType:'user',
      saveChkFldId:'toDealRoleId',viewer:Viewer2
    };
    dealRoletree = new xbox(dealcfg);
    //受理人员
    var usercfg = {
      btnId:'showSendUser',
      treeDataUrl: RoleTreeAction,treeRootId:flowId,treeRootText:'角色',treeChkMode:'single',treeChkType:'subrole',
      saveChkFldId:'toSendUserId',viewer:Viewer3
    };
    acceptusertree = new xbox(usercfg);
    
    Ext.get("showSendRole").setDisplayed(false);
    Ext.get("showDealRole").setDisplayed(false);
    Ext.get("showSendUser").setDisplayed(false);
   
    function sendroletree(){
    			sendRoletree.defaultTree.root.id = flowId;	
    			sendRoletree.defaultTree.root.setText("角色");
    			sendRoletree.defaultPanel.setTitle("角色");
    			sendRoletree.defaultTree.checktype = "subrole";
    			sendRoletree.resetRoot(RoleTreeAction);
    			sendRoletree.reset();
    			Ext.get("showSendRole").setDisplayed(true);
    };
   
    function dealroletree(){
    			dealRoletree.defaultTree.root.id = 1;
    			dealRoletree.defaultTree.root.setText("部门人员");
    			dealRoletree.defaultPanel.setTitle("部门人员");
    			dealRoletree.defaultTree.checktype = "user";
    			dealRoletree.resetRoot(UserTreeAction);
    			dealRoletree.reset();
    			Ext.get("showDealRole").setDisplayed(true);
    };
    
   function usertree(){
    			acceptusertree.defaultTree.root.id = flowId;
    			acceptusertree.defaultTree.root.setText("人员");
    			acceptusertree.defaultPanel.setTitle("人员");
    			acceptusertree.defaultTree.checktype = "user";
    			acceptusertree.resetRoot(RoleTreeAction);
    			acceptusertree.reset();
    			Ext.get("showSendUser").setDisplayed(true);
    };
   
   
    Ext.get("sendsel").on("change",function(event,el){
    	switch(el.value){
    		case "role":
    			sendroletree();
    			break;
    		default:
    			sendRoletree.reset();
    			Ext.getDom("toSendRoleId").value = "${autot1Crm.autoacceptrolet1}";	
    			Ext.get("showSendRole").setDisplayed(false);   			
    	}
    	
    });
    
     Ext.get("dealsel").on("change",function(event,el){
    	switch(el.value){
    		case "user":
    			dealroletree();
    			break;
    		default:
    			dealRoletree.reset();
    			Ext.getDom("toDealRoleId").value = "${autot1Crm.dealrolet2}";
    			Ext.get("showDealRole").setDisplayed(false);
    	}
    	
    });
    
     Ext.get("usersel").on("change",function(event,el){
    	switch(el.value){
    		case "user":
    			usertree();
    			break;
    		default:
    			acceptusertree.reset();
    			Ext.getDom("toSendUserId").value = "${autot1Crm.remark1}";
    			Ext.get("showSendUser").setDisplayed(false);   			
    	}
    	
    });
    
    
    
    Ext.get("showSendRole").on("click",function(){
    	sendRoletree.show(this,null,'toSendRoleId');
    });
    
    //处理角色
    Ext.get("showDealRole").on("click",function(){
    	dealRoletree.show(this,null,'toDealRoleId');
    }); 
    //受理对象
    Ext.get("showSendUser").on("click",function(){
    	acceptusertree.show(this,null,'toSendUserId');
    }); 
});

function selectStep(statusChoice){
	var stepId = document.getElementById("stepId");
	if (statusChoice.value == "0")
		stepId.disabled = false;
	else {
		stepId.selectedIndex=0;
		stepId.disabled = true;
		
	}
}
</script>
</form>
 <script type="text/javascript">


 	function tosubmit(e) {
	 		var faultsite = document.getElementById("faultsite");
	 		var acceptRoleId = document.getElementById("toSendRoleId");
	 		var userId = document.getElementById("toSendUserId");
			var dealRoleId = document.getElementById("toDealRoleId");
	 		if (acceptRoleId.value == ""){ 
	 			alert("请选择受理角色");
	 			return false;
	 		}
	 		if (userId.value == ""){
	 			alert("请选择受理人员");
	 			return false;
	 		}
	 		if (dealRoleId.value == "") { 
	 			alert("请选择分派处理角色"); 
	 			return false;
	 		}
	 		if (faultsite.value == "") {
	 			alert("请选择地区");
	 			return false;
	 		}
			var form = document.getElementById("theform");
    		var ajaxForm = Ext.getDom(form);
			e = e || window.event;
			eoms.stopEvent(e);
			try{
			Ext.Ajax.request({
				form: ajaxForm,
				method:"post",
				url: "autot1crm.do?method=checkRepeateRecord",
				success : handleResponse,
				failure : function(){
					alert("fail");
				}
	  		});
	  		}catch(e){
	  			alert(e.message);
	  		}
	  
	  	function handleResponse(x) {
			var o = eoms.JSONDecode(x.responseText);
			if (o.number == 0) {
				form.submit();
			} 	else {
				alert("已经有此流转配置规则，请重新配置！");
			}
		}  
	}
	
	eoms.$("theform").onsubmit = tosubmit;

 	function toedit(){
		location.href = "./autot1crm.do?method=edit&type=edit&flowId=${flowId}&id=${autot1Crm.id}";
	}
	
	function todelete(){
		location.href = "./autot1crm.do?method=remove&id=${autot1Crm.id}";
	}
 </script>