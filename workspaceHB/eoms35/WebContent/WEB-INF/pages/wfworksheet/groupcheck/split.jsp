<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
  var  v = new eoms.form.Validation({form:'theform'});;
</script>
<%String subtaskName = request.getParameter("subtaskName");
 %>

<div id="sheetform">
    <html:form action="/groupcheck.do?method=performCreateSubTask" styleId="theform">
       <%@ include file="/WEB-INF/pages/wfworksheet/groupcheck/baseinputlinkhtmlnew.jsp"%>		
       <br/>		
      <table class="formTable">
      	<tr>        
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
		        <textarea name="remark" class="textarea max" id="remark" 
		        alt="allowBlank:false,maxLength:4000,vtext:'请填写意见，最多输入4000字符'">${sheetLink.remark}</textarea>
		        <input type="hidden" name="beanId" value="iGroupCheckMainManager"/>
    			<input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.groupcheck.model.GroupCheckMain"/>	
    			<input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.groupcheck.model.GroupCheckLink"/>
               <input type="hidden" name="subtaskName" value="<%=subtaskName%>"/> 
               <input type="hidden" name="taskNamespace" value="http://GroupCheck"/>  
               <input type="hidden" name="operateType" value="${operateType}"/>
		  </td>
		 </tr>
     </table>

  <fieldset>
  <legend>
   请选择派发对象   
  </legend>
     <script type="text/javascript">
     /*
      *以下代码用于选择人员时，用其所在的子角色id代替人员id提交。
      */
     Ext.onReady(function(){
       Chooser.prototype.doChoose = function(categoryId) {
		var r = this.layout.getRegion("west").getActivePanel();
		var selectNode = r.tree.getSelectionModel().selNode;
		var node = selectNode || this.categoryTreeSM.selNode;
		if(node==null)return;
		//删除
		if(node.getOwnerTree()==this.category.tree){
		  this.remove(node);
		  return;
		}
		//添加
		var nodeInfo = Ext.apply({},node.attributes);
		if (nodeInfo.nodeType=='root') {
			return;
		}		
		if (nodeInfo.nodeType=='subrole' && nodeInfo.user && nodeInfo.user.length == 0) {
			alert('该角色下没有人员，请选择其他角色。');
			return;
		}
		if (this.chosen.containsKey(this.getKey(nodeInfo))) {
			alert('该项目已被选择了。');
			return;
		}
		
		var c = this.categoryNodes[categoryId] || this.category.root.firstChild;
		var cAttr = c.attributes;
		
		if(cAttr.limit !== 'none' && cAttr.limit !== -1 && c.childNodes.length >= parseInt(cAttr.limit)){		
			alert(cAttr.categoryName+"中最多可以选择"+cAttr.limit+"个");
			return;
		}
		
		if (cAttr.childType != '' && !cAttr.childType.hasSubString(nodeInfo.nodeType,",")) {
			alert(cAttr.categoryName+"中只能选择"+cAttr.childTypeText);
			return;
		}
		
		nodeInfo['categoryId'] = cAttr.categoryId;
		nodeInfo['categoryName'] = cAttr.categoryName;
		
		//if node is user then save parentId
		if(nodeInfo.nodeType=='user'){
		  if(node.parentNode && node.parentNode.attributes.nodeType=='subrole'){
		    nodeInfo['parentId'] = node.parentNode.attributes.id;
		  }
		  else if(this.subroleId){
		    nodeInfo['parentId'] = this.subroleId;
		  }
		}
		
		this.add(nodeInfo,this.category.root.firstChild, true);
		this.refresh();
	};
	  
	  Chooser.prototype.update = function(){
		this.chosenView.jsonData = [];
		this.chosen.each(function(nodeInfo) {
			//如果未指定组长，则设定leaderId为选中对象的id
			if(!nodeInfo.leaderId || nodeInfo.leaderId==""){
				nodeInfo.leaderId = nodeInfo.id;
			}
			//如果有leaderName，则info为组长信息
			nodeInfo.info = nodeInfo.leaderName ? '(组长:'+nodeInfo.leaderName+')' : '';

			this.chosenView.jsonData.push(nodeInfo);
		},this);

		this.chosenView.refresh();
		
		function toStr(o,isShowLeader){
			var str = '{'
				+'id:\''+o.id+'\''
				+',nodeType:\''+o.nodeType+'\''
				+',categoryId:\''+o.categoryId+'\''
			if(o.nodeType=='subrole' && isShowLeader && o.leaderId){
				str += ',leaderId:\''+o.leaderId+'\''+',leaderName:\''+o.leaderName+'\'';
			}
			str += '}';
			return str;
		}		
		
		var totalJsonData =[];
		this.chosen.each(function(nodeInfo) {
			if(!nodeInfo.leaderId || nodeInfo.leaderId==""){
				nodeInfo.leaderId = nodeInfo.id;
			}
			nodeInfo.leaderName = nodeInfo.leaderName ? '(组长:'+nodeInfo.leaderName+')' : '';
			totalJsonData.push(toStr(nodeInfo,this.showLeader));
		},this);
		
		Ext.each(this.category, function(c) {
			var arrId = [],arrNodeType = [], arrLeaderId = [], arrJSON = [];
			this.chosen.each(function(nodeInfo) {
				if (nodeInfo.categoryId == c.id) {
					//if node is user and have parentId
				  if(nodeInfo.nodeType=='user' && nodeInfo.parentId){
					  arrId.push(nodeInfo.parentId);
					  arrNodeType.push('subrole');
				  }
				  else{
					  arrId.push(nodeInfo.id);
					  arrNodeType.push(nodeInfo.nodeType);				      
				  }
					arrLeaderId.push(nodeInfo.leaderId);
					if(this.returnJSON){
						arrJSON.push(toStr(nodeInfo,this.showLeader));				
					}
				}
			}, this);
			
			c['hiddenEl'].dom.value = arrId.toString();
			c['hiddenEl_nodeType'].dom.value = arrNodeType.toString();
			c['hiddenEl_leaderId'].dom.value = arrLeaderId.toString();
			if(this.returnJSON){
				c['hiddenEl_JSON'].dom.value = "[" + arrJSON.toString() + "]";
			}
		}, this);
		this.totalJSON.dom.value =  "[" + totalJsonData.toString() + "]";
	}
     });
     </script>
     
     <eoms:chooser id="test" config="{returnJSON:true,subroleId:'${sheetLink.operateRoleId}'}"
        category="[{id:'dealPerformer',limit:'none',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"
        panels="[
                 {text:'可选人员',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersBySubRole&subRoleId=${sheetLink.operateRoleId}'},
                 {text:'备选子角色',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersByRole&roleId=${roleId}&subRoleId=${sheetLink.operateRoleId}'}
                ]"
       data="${sendUserAndRoles}"/>
 </fieldset>	
     <div class="form-btns">
	      <input type="submit" class="submit" name="method.save" id="method.save"   value="<fmt:message key='button.done'/>" >
     </div> 
</html:form>

</div>
