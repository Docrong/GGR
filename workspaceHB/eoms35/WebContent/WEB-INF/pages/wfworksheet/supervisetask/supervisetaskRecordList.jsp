<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
	String startDate = com.boco.eoms.base.util.StaticMethod.getLocalString(-1);
	String endDate = com.boco.eoms.base.util.StaticMethod.getCurrentDateTime();;
 %>

<script type="text/javascript"><!--
 function onRemove(id){
    if (confirm("确认要删除该规则吗?")==true){
      location.href="../supervisetask/supervisetask.do?method=deletesupervisetaskRule&id=" + id;
    }
 }
 Ext.onReady(function(){
		
			initCity();
			openQuery(document.getElementById("openQuery"));
    });
    
    
//初始化地市
function initCity(){
	Ext.Ajax.request({
		url : "${app}/sheet/commonfault/commonfaultSmsRule.do?method=initCity&parentareaid=15",				
		method: 'post',
		success: function(data){
			var value = eoms.JSONDecode(data.responseText);
			document.getElementById("tdToDeptId").innerHTML=value[0].cityOpt;
		}
	});
}


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
function queryRecord(){
	var startTime=document.getElementsByName("sendTimeStartDate")[0].value;//开始时间
	var endTime=document.getElementsByName("sendTimeEndDate")[0].value;//截止时间
	var major=document.getElementById("major").value; //专业
	var city=document.getElementById("toDeptId").value; //地市
	var supervisetaskCycle=document.getElementById("supervisetaskCycle").value; 
	var supervisetaskType=document.getElementById("supervisetaskType").value;
	var phone=document.getElementById("phone").value;
	
	location.href="../supervisetask/supervisetask.do?method=supervisetaskRecordList&startTime="+startTime+"&endTime="+endTime+
				  "&major="+major+"&city="+city+"&supervisetaskCycle="+supervisetaskCycle+"&supervisetaskType="+supervisetaskType+
				  "&phone="+phone;
	//console.log(startTime);
	//console.log(endTime);
	//console.log(major);
	//console.log(city);
	//console.log(supervisetaskCycle);
	//console.log(supervisetaskType);
	//console.log(phone);
}
</script>

<div style="border:1px solid #98c0f4;padding:5px;width:98%;"
	class="x-layout-panel-hd">工具栏： <img
	src="${app}/images/icons/search.gif" align="absmiddle"
	style="cursor:pointer" /> <span id="openQuery" style="cursor:pointer"
	onclick="openQuery(this);">打开快速查询</span></div>

<div id="listQueryObject"
	style="display:none;border:1px solid #98c0f4;border-top:0;padding:5px;background-color:#eff6ff;width:98%">
<form name="queryform" method="post"
	action="../supervisetask//supervisetask.do?method=supervisetaskRuleList">

<table width="100%" class="formTable">


	<tr>
		<td class="label">督办时间段</td>
		<td>
			督办开始时间 <input
			type="hidden" id="sendTimeStartDateExpression"
			name="sendTimeStartDateExpression" value=">=" /> <input type="text"
			name="sendTimeStartDate"
			onclick="popUpCalendar(this, this, null, null, null, true, -1)"
			readonly="true" class="text" value="<%=startDate %>" /> &nbsp;&nbsp;
		<input type="hidden" name="sendTimeLogicExpression" value="and" /> 
			督办截止时间 <input type="hidden"
			id="sendTimeEndDateExpression" name="sendTimeEndDateExpression"
			value="<="> <input type="text" name="sendTimeEndDate"
			id="sendTimeEndDate"
			onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""
			value="<%=endDate %>" readonly="true" class="text" />
		</td>

		<td class="label">专业</td>
		<td>
			<eoms:comboBox name="major" id="major" initDicId="1010107" defaultValue="">
	    		</eoms:comboBox>
			
		</td>
	</tr>
	<tr>
		<td class="label">地市</td>
		<td id="tdToDeptId">
			<select id="toDeptId" name="toDeptId"></select>
		</td>
		<td class="label">挂牌周期</td>
		<td><input type="text" name="supervisetaskCycle" id="supervisetaskCycle"
			class="text" value="" /></td>
	</tr>
	<tr>
		<td class="label">挂牌类型</td>
		<td><input type="text" name="supervisetaskType" id="supervisetaskType"
			class="text" value="" /></td>
			
		<td class="label">手机号</td>		
		<td><input type="text" name="phone" id="phone">
		</td>
		
		
	</tr>

	<tr>
		<td colspan="2">
		<input type="button" value="查询" class="button" onclick="queryRecord();"></td>
	</tr>

</table>

</form>
</div>



<display:table name="taskList" cellspacing="0" cellpadding="0"
	id="taskList" pagesize="${pageSize}" class="listTable" export="true"
	size="${total }" partialList="true" requestURI="supervisetask.do">
	<display:column title="督办时间">
		${taskList.createTime }
	</display:column>
	<display:column title="督办类型">
		${taskList.superviseType }
	</display:column>
	<display:column title="督办对象">
		${taskList.noticeUser1 }
	</display:column>
	<display:column title="督办内容">
		${taskList.content }
	</display:column>
	<display:column title="督办规则">
		${taskList.supervisetaskRule }
	</display:column>
	<display:column title="督办结果">
		${taskList.content }
	</display:column>
	
	
	<display:column title="查看" media="html">
		<a href='../supervisetask/supervisetask.do?method=supervisetaskRecordView&id=${taskList.id}&isAdmin=${isAdmin }'
					target="_blank"> <img src="${app }/images/icons/note.gif" /> </a>
	</display:column>
		
		
	
	<c:if test="${isAdmin eq 'true' }">
		<display:column title="删除" media="html">
			<a href="javascript:onRemove('${taskList.id}')"> <img
				src="${app }/images/icons/nodetype/empty.gif"> </a>
		</display:column>
	</c:if>

	<!--
	-->
	<display:setProperty name="export.pdf" value="false" />
	<display:setProperty name="export.xml" value="false" />
	<display:setProperty name="export.csv" value="false" />
	<display:setProperty name="export.rtf" value="false" />
</display:table>
<%@ include file="/common/footer_eoms.jsp"%>
