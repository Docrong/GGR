<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import ="java.util.List"%> 
<%@ page import ="com.boco.eoms.workplan.vo.TawwpMonthExecuteVO"%>

<script language="javascript">
	Ext.onReady(function(){
		colorRows('list-table');
	})
	function onAddons(url)
	{
	  var _sHeight = 600;
	  var _sWidth = 800;
	  var sTop=(window.screen.availHeight-_sHeight)/2;
	  var sLeft=(window.screen.availWidth-_sWidth)/2;
	  var sFeatures="dialogHeight: "+_sHeight+"px; dialogWidth: "+_sWidth+"px; dialogTop: "+sTop+"; dialogLeft: "+sLeft+"; center: Yes; scroll:Yes;help: No; resizable: No; status: No;";
	  window.showModalDialog(url,window,sFeatures);
	}
	function checkAll(){
 
		var e=document.getElementById("selectAll"); 
		var cbxList = document.getElementsByName("executeId");
		for(var i=0;i<cbxList.length;i++){
	       cbxList[i].checked=e.checked;
	   	}
	}
	function  submitThis(){
	   var result=false;
       var cbxList = document.getElementsByName("executeId");
		 
		 for(var i=0;i<cbxList.length;i++){
	      if(cbxList[i].checked){
	         result=true;
	         break;
	      }
	   	}
	   
	   	if(result){
	   	
	       var data=eval('('+document.dailyexecuteplan.saved2.value+')');
	        
	       var executer=""; 
	       var executeType="";
	        var i=0;
	   	    for(var j in data) {
	   	      if(i>2){
			   
			  	executer=executer+data[j].id+",";
			  	executeType=data[j].nodeType;
			  }
			  i=i+1;
		    }
	   	    document.dailyexecuteplan.executer.value=executer;
	   	    document.dailyexecuteplan.executeType.value=executeType;
	   	    document.dailyexecuteplan.action="executeSend.do";
	   	    document.dailyexecuteplan.submit();
	   	    //计划字符串(monthExecuteobj.value)的格式为 执行内容ID#执行人ID字符串#执行人类型#执行日期字符串
		 
	   	}   
	}	
</script>

<%
  List result = null;
  if(request.getAttribute("result")!=null){
  	result=(List)request.getAttribute("result");
  }
   
   
%>
<script type="text/javascript">
var tree;

Ext.onReady(function(){
	var	treeAction='${app}/xtree.do?method=dept';
	
	//创建xbox对象
	var config = {
		btnId:'usert',dlgId:'dlg',
		treeDataUrl:treeAction,treeRootId:'-1',treeRootText:'部门树',treeChkMode:'',treeChkType:'dept',
		showChkFldId:'showd2',saveChkFldId:'saved2',returnJSON:true
	};

	//添加人员树图和角色树图
	config.onLayout = function(cal,layout){
		var config = {
			treeDataUrl:'${app}/xtree.do?method=userFromDept',
			treeRootId:'-1',
			treeRootText:'人员树',
			treeChkType:'user'
		};
		layout.add('west', cal.newTreePanel(config));
		var config2 = {
			treeDataUrl:'${app}/xtree.do?method=getCptroomTree',
			treeRootId:'-1' , 
			treeRootText:'机房',
			treeChkType:'cptroom'
		};
		layout.add('west', cal.newTreePanel(config2));
	};
	
	//添加只能同时选择一种节点类型的判断
	config.onBeforeCheck = function(node,checked){
		if(checked && this.gridData.getCount() > 0){
			var r = this.gridData.getAt(0);
			if(r.json.nodeType != node.attributes.nodeType){
			  alert('执行人只能选择一种类型');
			  return;
			}
		}
		return true;
	}
	
	 
	userTree = new xbox(config); 

});
</script>

<!--  body begin -->

<form name="dailyexecuteplan" >

  <table class="listTable" id="list">
    <caption><bean:message key="dailyexecutelist.title.formTitle" /></caption>
    <%
      if(result!=null){
    %>
    <thead>
    <tr>
      <td width="14%">
      选择<input type="checkbox" mane="selectAll" value="" onclick='checkAll()' id="selectAll" />  
      </td>
      <td width="14%">
      执行内容名称
      </td>
      <td width="14%">
      执行周期
      </td>
      <td width="12%">
      执行类别
      </td>
      <td width="12%">
      执行人
      </td>
      <td width="12%">
      月份
      </td>
      <td width="12%">
      附加表
      </td>
      
    </tr>
    </thead>
    
    <tbody>
    <%  
        for(int i=0;i<result.size();i++){
         TawwpMonthExecuteVO tawwpMonthExecuteVO=(TawwpMonthExecuteVO)result.get(i) ;
         
         
  %> 
	   <tr>
	      <td width="14%">
	       <input type=checkbox name=executeId value='<%=tawwpMonthExecuteVO.getId()%>'/> 
	      </td> 
	   
	      <td width="14%">
	       <%=tawwpMonthExecuteVO.getName()%>
	      </td>
	      <td width="14%">
	      <%=tawwpMonthExecuteVO.getCycleName()%>
	      </td>
	      <td width="12%">
	      <%=tawwpMonthExecuteVO.getExecuterTypeName()%>
	      </td>
	      <td width="12%">
	      <%=tawwpMonthExecuteVO.getExecuterName()%>
	      <%=tawwpMonthExecuteVO.getExecuteDutyName()%>
	      <%=tawwpMonthExecuteVO.getExecuteRoomName()%>
	      </td>
	      <td width="12%">
	      <%=tawwpMonthExecuteVO.getTawwpMonthPlan().getMonthFlag()%>
	      </td>
	      <td width="12%">
<%			if(tawwpMonthExecuteVO.getFormId()!=null&&!tawwpMonthExecuteVO.getFormId().equals("")&&!tawwpMonthExecuteVO.getFormId().equals("0")){
%>
	       <a href="javascript:onAddons('../tawwpaddons/addonsread.do?action=new&window=new&addonsid=<%=tawwpMonthExecuteVO.getFormId()%>&reaction=/tawwpexecute/redirection.jsp');"> 
      		附加表</a>
<%		    }
 %>      		
	      </td>
	    </tr> 
	     
  <%       }
  %>	<%--<tr>
	      <td height="25" colspan="8">
	        是否本人执行<input type="radio" name="isself" value="0" checked="true" /> 否  <input type="radio" name="isself" value="1" /> 是
	      </td>
    	</tr>--%>
    	<tr>
	      <td height="25" colspan="8">
	        选择执行人<input type="button" name="usert" id="usert" value="请选择人员" class="btn"/>  
	      </td>
        </tr>
        <tr>
	      <td height="25" colspan="8">
	        <textarea id="showd2" class="textarea" ></textarea>
  			<input type="hidden" name="saved2" id="saved2"/>   
  			<input type="hidden" name="executer" id="executer"/> 
  			<input type="hidden" name="executeType" id="executeType"/>   
	      </td>
    	</tr>
 <%
      }
      else
      {
    %>
    
    <tr>
      <td height="25" colspan="8">
        <bean:message key="dailyexecutelist.title.nolist"/>
      </td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
   <div align=left ><input type="button" value="转派" class="btn" onclick="submitThis()" /></div>   
</form>

<!--  body end  -->
<%@ include file="/common/footer_eoms.jsp"%>
