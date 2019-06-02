<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<script src="<c:url value='/scripts/form/dynamictable.js'/>" type=text/javascript></script>
<link media="screen" href="${app}/styles/rule/style.css" type="text/css" rel="stylesheet"/>

<!-- ENDLIBS -->
<script type="text/javascript">

var iconshow = "${app}/images/icons/plus.png";
var iconhide = "${app}/images/icons/minus.png";

Dyna.set("group","group-table","group-template");

function togglet(id,img){
  var el = $(id);
  el.toggle();
  img.src = el.style.display=="none" ?  iconshow:iconhide;
}
function showResult(){
       $('ruleform').submit();
}
function toggleRows(el,str){
	var myRow = getParentForTag(el,"TR");
	var rows = getParentForTag(el,"TABLE").rows;
	for(var i=0;i<rows.length;i++){
		if(myRow!=rows[i]){
			if(str=="show"){
				rows[i].style.display = "";
			}
			else{
				var s = rows[i].style;
				s.display = s.display=="none" ? "":"none";
			}
		}
	}
}


	
	
	
	function delGroup(groupId)
	{
		//window.location='<html:rewrite page='/RuleWebToolAction.do?method=delGroup&id='/>'+id+'&groupId='+groupId+'&currPage=${currPage}&qgroupId=${qgroupId}&qdescription=${qdescription}';
	var groupId2=document.getElementById("groupId2");
	groupId2.value=groupId;
	document.getElementById("delGroupForm").submit();
		
	}
	
	function page(currPage){
	var curr=document.getElementById("currPage2");
	curr.value=currPage;
	document.getElementById("pageForm").submit();
	}
	
</script>
</head>

<body>
<div id=page>
<div class=clearfix id=content>
<div id=main>

<!-- 模板 -->
<div style="display:none">
    <table>
	<tbody id="rule-template">
	
	<!-- 规则行模板开始 -->
    <tr>
      <td class="micon">&nbsp;</td>
      <td class="mdesc"><bean:message key="rule.table.title.ruleId"/></td>
      <td class="msource">
        <select name="groupRuleId">
			<c:forEach items="${rules.rules.rule}" var="ruleIdItem">
				<option value="${ruleIdItem.id }" <c:if test="${ruleIdItem.id == groupRefItem.ruleId }">selected</c:if>>${ruleIdItem.id}</option>
			</c:forEach>
		</select>
      </td>
      <td class="mdesc"><bean:message key="rule.table.title.pri"/>></td>
      <td class="msource"><input value="-UID-" name="groupRulePri"></td>
	  <td class="mrowbtns">
		<input name="button2" type=button onClick="removeRow(this);" value="<bean:message key='rule.table.button.delete'/>"></td>
    </tr>
	<!-- 规则行模板结束 -->
	</tbody>
	</table>
	
	
	<div id="group-template">
	<!-- 规则组模板开始 -->
	
	<form action="<html:rewrite page='/RuleWebToolAction.do?method=addGroup'/>" method="post">
	<input type="hidden" name="currPage" value="${currPage }"/>
	<input type="hidden" name="id" value="${rule.id}"/>
	<input type="hidden" name="qgroupId" value="${qgroupId}"/>
	<input type="hidden" name="qdescription" value="${qdescription}"/>
	<script type="text/javascript">
		Dyna.set("ruleTable-UID-","ruleTable-UID-","rule-template","1");
    </script>
	<table class="member-table" cellspacing="0" id="ruleTable-UID-">
    <tr ondblclick="javascript:toggleRows(this)">
      <th class="micon">&nbsp;</th>
      <th class="mdesc"><bean:message key="rule.table.title.id"/></th>
      <th class="msource">
		<input class="wide" value="group-UID-" name="groupId"></th>
      <th class="mdesc"><bean:message key="rule.table.title.description"/></th>
	  <th class="msource"><input class="wide" value="Group description" name="groupDescription" size="20"></th>
	  <th class="msource" style="text-align:right">
	  	<input type="submit" onclick="" value="<bean:message key='rule.table.button.submit'/>"/>
		<input type="button" onclick="toggleRows(this,'show');Dyna.all['ruleTable-UID-'].add()" value="<bean:message key='rule.table.button.newRule'/>">
		<input type="button" onclick="removeTable(this);" value="<bean:message key='rule.table.button.deleteGroup'/>">
	  </th>
    </tr>
    <tr>
      <td class="micon">&nbsp;</td>
      <td class="mdesc"><bean:message key="rule.table.title.ruleId"/></td>
      <td class="msource">
        <select name="groupRuleId">
			<c:forEach items="${rules.rules.rule}" var="ruleIdItem">
				<option value="${ruleIdItem.id }" <c:if test="${ruleIdItem.id == groupRefItem.ruleId }">selected</c:if>>${ruleIdItem.id}</option>
			</c:forEach>
		</select>
      </td>
      <td class="mdesc"><bean:message key="rule.table.title.pri"/></td>
      <td class="msource"><input value="" name="groupRulePri"></td>
	  <td class="mrowbtns">
	    <!-- <a href="#" onclick="removeRow(this);">删除</a> -->
		<input name="button2" type=button onClick="removeRow(this);" value="<bean:message key='rule.table.button.delete'/>"</td>
    </tr>
	</table>
	</form>
	<!-- 规则组模板结束 -->
	</div>
</div>
    <table width="80%">
		<tr><td><strong>${rule.name}</strong><br></td></tr>
        <tr><td><strong>${rule.xmlPath}</strong></td></tr>
        
	</table>
<!-- 正文 -->
<h2><bean:message key="rule.table.title.ruleGroup"/>
<!-- 
<img src="${app}/images/icons/plus.png" onclick="javascript:togglet('rules',this);" alt="<bean:message key='rule.table.title.openclose'/>">
 -->
</h2>
<div class="detail-wrap" id="rules">
    <div class="mdetail">
        <input type="button" onClick="Dyna.all['group'].add();" value="<bean:message key='rule.table.button.newGroup'/>">
	</div>
<div id="group-table" class="mcontent">

	<c:forEach items="${rules.groups.group}" var="groupItem">
	<form action="<html:rewrite page='/RuleWebToolAction.do?method=updateGroup'/>" method="post">
	<script type="text/javascript">
	  Dyna.set("ruleTable-${groupItem.id}","ruleTable-${groupItem.id}","rule-template");
	</script>
	<table class="member-table" cellspacing="0" id="ruleTable-${groupItem.id}">
	<input type="hidden" name="qgroupId" value="${qgroupId}"/>
	<input type="hidden" name="qdescription" value="${qdescription}"/>
	<input type="hidden" name="id" value="${rule.id}"/>
	<input type="hidden" name="groupId" value="${groupItem.id}"/>
	<input type="hidden" name="currPage" value="${currPage }"/>
    <tr ondblclick="javascript:toggleRows(this)">
      <th class="micon">&nbsp;</th>
      <th class="mdesc"><bean:message key="rule.table.title.id"/></th>
      <th class="msource">
		<input type="text" value="${groupItem.id}" name="mgroupId" class="wide" size="30"/></th>
      <th class="mdesc"><bean:message key="rule.table.title.description"/></th>
	  <th class="msource"><input type="text" value="${groupItem.description }" name="groupDescription" size="30" class="wide"/></th>
	  <th class="msource" style="text-align:right">
	  	<input type="submit" onclick="" value="<bean:message key='rule.table.button.submit'/>">
		<input type="button" onclick="toggleRows(this,'show');Dyna.all['ruleTable-${groupItem.id}'].add()" value="<bean:message key='rule.table.button.newRule'/>">
		<!-- <input type="button" onclick="removeTable(this);" value="Delete Rule Group">-->
		<input type="button" onclick="delGroup('${groupItem.id }');" value="<bean:message key='rule.table.button.deleteGroup'/>"></th>
    </tr>
    <c:forEach items="${groupItem.groupRef}" var="groupRefItem" varStatus="status">
    <tr>
      <td class="micon">&nbsp;</td>
      <td class="mdesc"><bean:message key="rule.table.title.ruleId"/></td>
      <td class="msource">
        <select name="groupRuleId">
			<c:forEach items="${rules.rules.rule}" var="ruleIdItem">
				<option value="${ruleIdItem.id }" <c:if test="${ruleIdItem.id == groupRefItem.ruleId }">selected</c:if>>${ruleIdItem.id}</option>
			</c:forEach>
		</select>
      </td>
      <td class="mdesc"><bean:message key="rule.table.title.pri"/></td>
      <td class="msource"><input type="text" name="groupRulePri" value="${groupRefItem.pri}" class="wide"/></td>
	  <td class="mrowbtns">
	    <!-- <a href="#" onclick="removeRow(this);">删除</a> --><input name="button2" type=button onClick="removeRow(this);" value="<bean:message key='rule.table.button.delete'/>">
		<!--  <input type="button" value="Delete" onclick="delGroupRule('${rule.id }','${groupItem.id }','${ groupRefItem.ruleId}')"></td> -->
    </tr>
    </c:forEach>
	</table>
	</form>
	</c:forEach>

	<table width="600">
	<tr><td width="20%">
		<bean:message key="rule.table.title.currPage" arg0="${currPage }"/>
	</td>
	<td width="15%">
		<bean:message key="rule.table.title.totalRows" arg0="${total }"/>
	</td>
	<td width="15%">
		<bean:message key="rule.table.title.totalPages" arg0="${pageTotal }"/>
	</td>
	<td width="15%"><a href="#" onclick="javascript:page('${currPage-1 }');"><bean:message key="rule.table.title.lastPage"/></a></td>
	<td width="15%"><a href="#" onclick="javascript:page('${currPage+1 }');"><bean:message key="rule.table.title.nextPage"/></a></td>
	<td width="15%"><a href="<html:rewrite page='/RuleWebToolAction.do?method=listRule'/>"><bean:message key="rule.table.title.return"/></a></td>
	</tr>
	<form name="pageForm" id="pageForm" method="post" action="<c:url value='RuleWebToolAction.do?method=detailRuleGroup'/>">
	<input type="hidden" name="id" value="${rule.id }"/>
	<input type="hidden" name="currPage" value="1" id="currPage2"/>
	<input type="hidden" name="qgroupId" value="${qgroupId }"/>
	<input type="hidden" name="qdescription" value="${qdescription }"/>
	</form>
	<form name="delGroupForm" id="delGroupForm" method="post" action="<html:rewrite page='/RuleWebToolAction.do?method=delGroup'/>">
		<input type="hidden" name="id" value="${rule.id }"/>
		<input type="hidden" name="groupId" value="-1" id="groupId2"/>
		<input type="hidden" name="currPage" value="${currPage }"/>
		<input type="hidden" name="qgroupId" value="${qgroupId}"/>
		<input type="hidden" name="qdescription" value="${qdescription }"/>
	</form>
	
	</table>

</div>
</div>

</body>
</html>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>