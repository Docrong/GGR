<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>



<form method="post" id="theform" action="userdefinegroup.do?method=save">
<table class="formTable">
			<caption>用户自定义角色配置 </caption>

            <tr>
		            <td class="label"> 
		              	流程名
		            </td>
		            <td  class="content">
		            	 <input type="text"  id="flowName" name="flowName" readonly="readonly" class="text" value="${UserdefineGroup.flowName}"/>
		            	  <input type="hidden"  id="flowId" name="flowId" value="${UserdefineGroup.flowId}"/>
		            	  <logic:equal property="id" name="UserdefineGroup" value="">
		            	 	<input type="button" onclick="openWorkflow()" value="选择流程名" class="btn">
		            	  </logic:equal>
		            </td> 
	        </tr>
            <tr>
		            <td class="label"> 
		              	角色(请先选择流程后在选择角色)
		            </td>
		            <td  class="content">
		            	 <textarea  class="textarea max"  readonly="readonly" name="dealRoleName" id="showRole">
		            	 	${UserdefineGroup.dealRoleName}
		            	 </textarea>
               			 <input type="hidden" name="dealRoleId" id="toRoleId" value="${UserdefineGroup.dealRoleId}"/>
               			 <div id="areaviewRole" class="hide"></div>	

		            </td> 
	        </tr>
            <tr>
		            <td class="label"> 
		              	部门 
		            </td>
		            <td  class="content">
		            	 <textarea  class="textarea max"  readonly="readonly" name="dealDeptName" id="showDept" >
		            	 	${UserdefineGroup.dealDeptName}
		            	 </textarea>
               			 <input type="hidden" name="dealDeptId" id="toDeptId" value="${UserdefineGroup.dealDeptId}"/>
               			 <div id="areaviewDept" class="hide"></div>	
	
	
		            </td> 
	        </tr>
	         <tr>
		            <td class="label"> 
		              	人员 
		            </td>
		            <td  class="content">
		            	 <textarea  class="textarea max"  readonly="readonly" name="dealUserName" id="showUser" >
		            	 	${UserdefineGroup.dealUserName}
		            	 </textarea>
               			 <input type="hidden" name="dealUserId" id="toUserId" value="${UserdefineGroup.dealUserId}"/>
               			 <div id="areaviewUser" class="hide"></div>	
	
	
		            </td> 
	        </tr>      
 

		<logic:notEqual name="type" value="open">
			<tr>
				<td colspan="2">
					<input type="hidden" name="id" value="${UserdefineGroup.id}">
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

</form>
 <script type="text/javascript">

	   var roletree ;
	   Ext.onReady(function(){

  			  //viewer
			var areaViewer = new Ext.JsonView("areaviewDept",
				'<div class="viewlistitem-{nodeType}">{name}</div>',
				{ 
					emptyText : '<div>没有选择项目</div>'
				}
			);
			var data = "[]";
			areaViewer.jsonData = eoms.JSONDecode(data);
			areaViewer.refresh();
		 
			//area tree
		    var	deptTreeAction='${app}/xtree.do?method=dept';
		    deptetree = new xbox({
		      btnId:'showDept',
		      treeDataUrl:deptTreeAction,treeRootId:'-1',treeRootText:'部门',treeChkMode:'',treeChkType:'dept',
		      showChkFldId:'showDept',saveChkFldId:'toDeptId',viewer:areaViewer
		    });
		    
		    
		    var userViewer = new Ext.JsonView("areaviewUser",
				'<div class="viewlistitem-{nodeType}">{name}</div>',
				{ 
					emptyText : '<div>没有选择项目</div>'
				}
			);
			var data = "[]";
			areaViewer.jsonData = eoms.JSONDecode(data);
			areaViewer.refresh();
		 
			//area tree
		    var	userTreeAction='${app}/xtree.do?method=userFromDept';
		    deptetree = new xbox({
		      btnId:'showUser',
		      treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'人员',treeChkMode:'',treeChkType:'user',
		      showChkFldId:'showUser',saveChkFldId:'toUserId',viewer:userViewer
		    });
		    
		    
		    
		    
		   var roleViewer = new Ext.JsonView("areaviewRole",
				'<div class="viewlistitem-{nodeType}">{name}</div>',
				{ 
					emptyText : '<div>没有选择项目</div>'
				}
			);
			var data = "[]";
			areaViewer.jsonData = eoms.JSONDecode(data);
			areaViewer.refresh();
		 
			//area tree
		    var	roleTreeAction='${app}/role/tawSystemRoles.do?method=xGetSubRoleNodesFromFlow';
			var flowId = "${UserdefineGroup.flowId}";
		    roletree = new xbox({
		      btnId:'showRole',
		      treeDataUrl:roleTreeAction,treeRootId:flowId,treeRootText:'角色',treeChkMode:'',treeChkType:'subrole',
		      showChkFldId:'showRole',saveChkFldId:'toRoleId',viewer:roleViewer
		    });
		    
				            
  	 });
	
				            
	
 	function tosubmit(e) {
 			
			var form = document.getElementById("theform");
    		var ajaxForm = Ext.getDom(form);
			e = e || window.event;
			eoms.stopEvent(e);
			try{
			Ext.Ajax.request({
				form: ajaxForm,
				method:"post",
				url: "userdefinegroup.do?method=checkRepeateRecord&id=${UserdefineGroup.id}",
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
				alert("该流程已经配置了常用的角色，人员和部门！");
			}
		}  
	}
	
	eoms.$("theform").onsubmit = tosubmit;

 	function toedit(){
		location.href = "./userdefinegroup.do?method=edit&type=edit&id=${UserdefineGroup.id}";
	}
	
	function todelete(){
		location.href = "./userdefinegroup.do?method=remove&id=${UserdefineGroup.id}";
	}
	function openWorkflow() {
		window.open("./userdefinegroup.do?method=findFlow",null,"left=300,top=150,height=700,width=800,scrollbars=yes,resizable=yes");
	}
	function changeFlow(flowId) {
		roletree.defaultTree.root.id = flowId;
		roletree.reset();	
	}
 </script>