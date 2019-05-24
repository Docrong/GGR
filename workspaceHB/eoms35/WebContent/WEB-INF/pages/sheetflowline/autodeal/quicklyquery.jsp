<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
function openQuery(handler){
	var el = Ext.get('listQueryObject');
	if(el.isVisible()){
		el.slideOut('t',{useDisplay:true});
		handler.innerHTML = "打开快速查询";
	}
	else{
		el.slideIn();
		handler.innerHTML = "关闭快速查询";
	}
}


function dealmodel(input){
	var v1 = document.getElementById("tranfer");
	var v2 = document.getElementById("notranfer");
	if(input=='T1'){//显示
       v1.style.display='block'; 
       v2.style.display='none'; 
	}else{//不显示
		v1.style.display='none';
		v2.style.display='block';    
	}
}
</script>
<div style="border:1px solid #98c0f4;padding:5px;width:98%;" class="x-layout-panel-hd">
工具栏： 
  <img src="${app}/images/icons/search.gif" align="absmiddle" style="cursor:pointer"/>
  <span id="openQuery"  style="cursor:pointer" onclick="openQuery(this);">打开快速查询</span>
</div>

<div id="listQueryObject" style="display:none;border:1px solid #98c0f4;border-top:0;padding:5px;background-color:#eff6ff;width:98%">
<form name="queryform" method="post" action="../sheetflowline/autoDealsop.do?method=queryList" >

<table width="100%" class="formTable">
  <tr>
    <td class="label">
     	网管告警ID
    </td>
    <td>
   		 <input type="text" name="alarmId" id="alarmId" class="text"  value="${object.alarmId}" />
    </td>
     <td class="label">
     	告警主题
    </td>
    <td>
		  <input type="text" name="alarmTitle" id="alarmTitle" class="text"  value="${object.alarmTitle}"  />
    </td>
  </tr>
  <tr>
  
    <td class="label">
     自动处理环节
    	
    </td>
    <td width="400">
   		<select id="autoDealTask" name="autoDealTask" class="select" onchange="dealmodel(this.value);" >
   			<c:choose>
   			<c:when test="${object.autoDealTask!=null&&object.autoDealTask!=''}">
   				    <option value="${object.autoDealTask}">${object.autoDealTask}</option>
   			</c:when>
   			<c:otherwise>
   					<option value="">请选择</option>
   			</c:otherwise>
   			</c:choose>
		   			<option value="T1">T1</option>
		   			<option value="T2">T2</option>
   			
   		</select>
   </td>
    <td class="label">
    	自动处理方式
    </td>
    <td>
    	 <div id="tranfer">
    	 <select id="autoDealMode" name="autoDealMode" class="select">
    	 	<c:choose>
	    	 	<c:when test="${object.autoDealMode!=null&&object.autoDealMode!=''}">
	    	 			<c:if test="${object.autoDealMode=='1'}">
	   				   	 <option value="${object.autoDealTask}" selected="selected">移交下一级</option>
	   				   	 <option value="2">回复并归档</option>
	   				    </c:if>
	   				    <c:if test="${object.autoDealMode=='2'}">
	   				     <option value="${object.autoDealTask}" selected="selected">回复并归档</option>
	   				     <option value="1">移交下一级</option>
	   				    </c:if>
	   			</c:when>
	   			<c:otherwise>
		   			<option value="">请选择</option>
		   			<option value="1">移交下一级</option>
		   			<option value="2">回复并归档</option>
	   			</c:otherwise>
   			</c:choose>
    	 	
   		</select>
   		</div>
   		 <div id="notranfer" style="display: none">
   		  <select id="autoDealMode" name="autoDealMode" class="select">
   			<option value="">请选择</option>
   			<option value="2" >回复并归档</option>
   		</select>
   		 </div>
    </td>
  </tr>


 <tr>
   
   <td colspan="4">
   		<input type="submit" value="查询" class="submit"/>
        <input type="reset"  value="重置" class="button"/>
   </td>
  </tr>

</table>

</form>

 </div>
