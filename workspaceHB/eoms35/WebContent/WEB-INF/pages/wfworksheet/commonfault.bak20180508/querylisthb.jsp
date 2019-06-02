<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@page import="java.util.*"%>
<%
String oldsql = (String) request.getAttribute("oldsql");
String excelid = (String) request.getParameter("excelid");
System.out.println(excelid+"1111111111111111111111111111111111111");
%>
<script type="text/javascript">
  	var checkflag = "false";
function forceOperation(){
		var url="${app}/sheet/commonfault/commonfault.do?method=repealHB";
		eoms.util.appendPage("sheet-deal-content",url);
}
	function choose() {
		var objs = document.getElementsByName("ids");
		if(checkflag=="false"){
			for(var i=0; i<objs.length; i++) {
    			if(objs[i].type.toLowerCase() == "checkbox" )
      			objs[i].checked = true;
      			checkflag="true";
			}
		}else if(checkflag=="true"){
			for(var i=0; i<objs.length; i++) {
    			if(objs[i].type.toLowerCase() == "checkbox" )
      			objs[i].checked = false;
      			checkflag="false"
			}
		}
	}
	
Ext.onReady(function(){
	colorRows('list-table');
})
function openSheet(url){
	if(parent.frames['portal-north']){
		parent.frames['portal-north'].location.href = url;
	}
	else{
		location.href = url;
	}
}

</script>
<logic:notPresent name="recordTotal">
	<input type="hidden" id="oldsqls" value="${oldsql}"/>
	<input type="hidden" id="excelid" value="<%=excelid %>"/>
	<bean:define id="url" value="${app}/sheet/commonfault/commonfault.do" />
	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
		export="true" requestURI="${app}/sheet/commonfault/commonfault.do" sort="external" size="total"
		partialList="true"
		decorator="com.boco.eoms.sheet.commonfault.webapp.action.QueryListHBDisplaytagDecoratorHelper">

		<display:column property="id"
			title="<input type='checkbox' onclick='javascript:choose();'>" />
		<display:column property="title" sortable="true" sortName="title"
			headerClass="sortable" title="工单主题" />

		<display:column property="sheetId" sortable="true" sortName="sheetId"
			headerClass="sortable" title="工单流水号" />

		<display:column property="sheetAcceptLimit" sortable="true"
			sortName="sheetAcceptLimit" headerClass="sortable" title="受理时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />
		<display:column property="sendTime" sortable="true"
			sortName="sendTime" headerClass="sortable" title="派单时间"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />
		<display:column property="sheetCompleteLimit" sortable="true"
			sortName="sheetCompleteLimit" headerClass="sortable" title="处理时限"
			format="{0,date,yyyy-MM-dd HH:mm:ss}" />
		<display:setProperty name="export.pdf" value="false" />
		<display:setProperty name="export.xml" value="false" />
		<display:setProperty name="export.csv" value="false" />
	<display:footer>
			<tr>
				<td id="buttonDisplay" style="display:block" colspan="6">
					<input type="button" value="撤销" onclick="$('buttonDisplay').style.display='none';forceOperation();" class="btn">
				</td>
			<tr>
	</display:footer>
	</display:table>
	<div class="sheet-deal-content" id="sheet-deal-content"></div>
</logic:notPresent>
<logic:present name="recordTotal">
	<center><bean:message bundle="sheet"
		key="worksheet.query.total" />${recordTotal}<bean:message
		bundle="sheet" key="worksheet.query.totalNumber" /></center>
</logic:present>
<%@ include file="/common/footer_eoms.jsp"%>
