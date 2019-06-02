<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<link media="screen" href="${app}/styles/rule/style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript">
var Page = {
	init : function(){
		var tabs = new Ext.TabPanel('indexpage');
		tabs.addTab('rules', "<bean:message key='rule.table.title.ruleList'/>");
        tabs.addTab('rulegroups', "<bean:message key='rule.table.title.ruleGroupList'/>");
		tabs.activate('rules');
	}
}
Ext.onReady(Page.init, Page, true);
</script>
<title></title>

<div id="indexpage">
<div id="rulegroups" style="padding:10px">


<table class="member-table" cellspacing="0">
	<form action="<html:rewrite page='/RuleWebToolAction.do?method=detailRuleGroup'/>" method="post">
	
	<th class="mdesc" colspan="2"><bean:message key="rule.table.title.queryRuleGroup"/></th>
	
	<tr>
		<td width="20%"><bean:message key="rule.table.title.ruleGroupId"/></td>
		<td><input type="text" name="qgroupId"/></td>
	</tr>
	<tr>
		<td><bean:message key="rule.table.title.description"/></td>
		<td><input type="text" name="qdescription"/></td>
	</tr>
	<tr>
	<td><bean:message key="rule.table.title.ruleList"/></td>
	<td>
	<select name="id">
	<c:forEach items="${ruleList}" var="item" >
		<option value="${item.id }">${item.name }</option>
    </c:forEach>
    </select>
    </td></tr>
	<tr><td colpsan="2"><input type="submit" value="<bean:message key='rule.table.button.query'/>"/></td></tr>
	</form>
</table>
<table class="member-table" cellspacing="0">
	<th class="mdesc"><bean:message key="rule.table.title.ruleGroupList"/></th>
	<c:forEach items="${ruleList}" var="item" >
		<tr><td><a href="<html:rewrite page='/RuleWebToolAction.do?method=detailRuleGroup&id=${item.id}'/>">${item.name}</a></td></tr>
    </c:forEach>
</table>

</div>

<div id="rules" style="padding:10px">

<table class="member-table" cellspacing="0">
	<form action="<html:rewrite page='/RuleWebToolAction.do?method=detailRule'/>" method="post">
	<th class="mdesc" colspan="2"><bean:message key="rule.table.title.queryRule"/></th>
	<tr>
		<td width="20%"><bean:message key="rule.table.title.ruleId"/></td>
		<td><input type="text" name="qid"/></td>
	</tr>
	<tr>
		<td><bean:message key="rule.table.title.class"/></td>
		<td><input type="text" name="qclassName"/></td>
	</tr>
	<tr>
		<td><bean:message key="rule.table.title.description"/></td>
		<td><input type="text" name="qdescription"/></td>
	</tr>
	<tr>
	<td><bean:message key="rule.table.title.ruleList"/></td>
	<td>
	<select name="id">
	<c:forEach items="${ruleList}" var="item" >
		<option value="${item.id }">${item.name }</option>
    </c:forEach>
    </select>
    </td></tr>
	<tr><td colpsan="2"><input type="submit" value="<bean:message key='rule.table.button.query'/>"/></td></tr>
	</form>
</table>

<table  class="member-table" cellspacing="0">
	<th class="mdesc"><bean:message key="rule.table.title.ruleList"/></th>
	<c:forEach items="${ruleList}" var="item" >
		<tr><td><a href="<html:rewrite page='/RuleWebToolAction.do?method=detailRule&id=${item.id}'/>">${item.name}</a></td></tr>
    </c:forEach>
</table>


</div>


</div>

<%@ include file="/common/footer_eoms.jsp"%>
