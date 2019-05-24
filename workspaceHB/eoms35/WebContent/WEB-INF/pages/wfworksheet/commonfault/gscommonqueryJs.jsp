<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
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
    var flowId = "51";
    var	RoleTreeAction='${app}/role/tawSystemRoles.do?method=xGetSubRoleNodesFromFlow';  
    var	DptTreeAction='${app}/xtree.do?method=dept';   
    var	UserTreeAction='${app}/xtree.do?method=userFromDept';
    
    var viewerObj = document.getElementById("viewer");
    if (viewerObj != null) {
	    Viewer = new Ext.JsonView("viewer",
			'<div class="viewlistitem-{nodeType}">{name};</div>',
			{emptyText : '<div>没有选择项目</div>'}
		);
	}
	
	var viewerdealObj = document.getElementById("viewer-deal");
	if (viewerdealObj != null) {
	    Viewer2 = new Ext.JsonView("viewer-deal",
			'<div class="viewlistitem-{nodeType}">{name};</div>',
			{emptyText : '<div>没有选择项目</div>'}
		);
	}
	var vieweruserObj = document.getElementById("viewer-user");
	if (vieweruserObj != null) {
		Viewer3 = new Ext.JsonView("viewer-user",
			'<div class="viewlistitem-{nodeType}">{name};</div>',
			{emptyText : '<div>没有选择项目</div>'}
		);
	}
	
    //受理角色
    if (viewerObj != null) {
	    var sendcfg = {
	      btnId:'showSendRole',
	      treeDataUrl: RoleTreeAction,treeRootId:flowId,treeRootText:'角色',treeChkMode:'single',treeChkType:'subrole',
	      saveChkFldId:'toSendRoleId',viewer:Viewer
	    };
    	sendRoletree = new xbox(sendcfg);
    }
    
    //处理角色
    if (viewerdealObj != null) {
	    var dealcfg = {
	      btnId:'showDealRole',
	      treeDataUrl: RoleTreeAction,treeRootId:flowId,treeRootText:'角色',treeChkMode:'single',treeChkType:'subrole',
	      saveChkFldId:'toDealRoleId',viewer:Viewer2
	    };
	    dealRoletree = new xbox(dealcfg);
    }
    
    //受理人员
    if (vieweruserObj != null) {
	    var usercfg = {
	      btnId:'showSendUser',
	      treeDataUrl: RoleTreeAction,treeRootId:flowId,treeRootText:'角色',treeChkMode:'single',treeChkType:'subrole',
	      saveChkFldId:'toSendUserId',viewer:Viewer3
	    };
	    acceptusertree = new xbox(usercfg);
    }
    
    var showSendRoleObj = document.getElementById("showSendRole");
    if (showSendRoleObj != null) {
    	Ext.get("showSendRole").setDisplayed(false);
    }
    var showDealRoleObj = document.getElementById("showDealRole");
    if (showDealRoleObj != null) {
    	Ext.get("showDealRole").setDisplayed(false);
    }
    var showSendUserObj = document.getElementById("showSendUser");
    if (showSendUserObj != null) {
    	Ext.get("showSendUser").setDisplayed(false);
    }
   
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
    			dealRoletree.defaultTree.root.id = flowId;
    			dealRoletree.defaultTree.root.setText("角色");
    			dealRoletree.defaultPanel.setTitle("角色");
    			dealRoletree.defaultTree.checktype = "subrole";
    			dealRoletree.resetRoot(RoleTreeAction);
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
   var toSendRoleIdObj = document.getElementById("toSendRoleId");
    if (toSendRoleIdObj != null) {
	    Ext.get("sendsel").on("change",function(event,el){
	    	switch(el.value){
	    		case "role":
	    			sendroletree();
	    			break;
	    		default:
	    			sendRoletree.reset();
	    			Ext.getDom("toSendRoleId").value = "${complaintAutoT2.autoAcceptRoleT1}";	
	    			Ext.get("showSendRole").setDisplayed(false);   			
	    	}
	    	
	    });
    }
    
   var toDealRoleIdObj = document.getElementById("toDealRoleId");
    if (toDealRoleIdObj != null) {
	    Ext.get("dealsel").on("change",function(event,el){
	    	switch(el.value){
	    		case "role":
	    			dealroletree();
	    			break;
	    		default:
	    			dealRoletree.reset();
	    			Ext.getDom("toDealRoleId").value = "${complaintAutoT2.dealRoleT2}";
	    			Ext.get("showDealRole").setDisplayed(false);
	    	}
	    	
	    });
    }
    
    var toSendUserIdObj = document.getElementById("toSendUserId");
    if (toSendUserIdObj != null) {
	     Ext.get("usersel").on("change",function(event,el){
	    	switch(el.value){
	    		case "user":
	    			usertree();
	    			break;
	    		default:
	    			acceptusertree.reset();
	    			Ext.getDom("toSendUserId").value = "${complaintAutoT2.remark1}";
	    			Ext.get("showSendUser").setDisplayed(false);   			
	    	}
	    	
	    });
    }
    
    
    var showSendRoleObj = document.getElementById("showSendRole");
    if (showSendRoleObj != null) {
	    Ext.get("showSendRole").on("click",function(){
	    	sendRoletree.show(this,null,'toSendRoleId');
	    });
    }
    
    //处理角色
    var showDealRoleObj = document.getElementById("showDealRole");
    if (showDealRoleObj != null) {
	    Ext.get("showDealRole").on("click",function(){
	    	dealRoletree.show(this,null,'toDealRoleId');
	    });
    } 
    
    //受理对象
     var showSendUserObj = document.getElementById("showSendUser");
    if (showSendUserObj != null) {
	    Ext.get("showSendUser").on("click",function(){
	    	acceptusertree.show(this,null,'toSendUserId');
	    }); 
    }
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

<%@ include file="/common/footer_eoms.jsp"%>
