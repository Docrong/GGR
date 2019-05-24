<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*"%>
<%
List redList = (List) request.getAttribute("REDLIST");
%>
<style>
#tabs {
	width:90%;
}
#tabs .x-tabs-item-body {
	display:none;
	padding:10px;
}
</style>
<script type="text/javascript">
var Tabs = {
    init : function(){
        var tabs = new Ext.TabPanel('tabs');
        tabs.addTab('form', '${eoms:a2u('会议查询结果')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>



<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<c:url value="/voiceMessage/TawHieApply/listconf.do"/>'" value='${eoms:a2u(' 返回')}'/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">

	<div id="form" class="tab-content">
		<table cellpadding="0" class="table logList" cellspacing="0">
			<tr>
				<td width='3%' align='center' bgcolor='#66CCFF'>
					<p>
						${eoms:a2u('会议编号')}
					</p>
				</td>
				<td width='5%' align='center' bgcolor='#66CCFF'>
					<p>
						${eoms:a2u('会议主题')}
					</p>
				</td>
				<td width='5%' align='center' bgcolor='#66CCFF'>
					<p>
						${eoms:a2u('会议组号')}
					</p>
				</td>
				<td width="7%" align='center' bgcolor='#66CCFF'>
					<p>
						${eoms:a2u('发起人')}
					</p>
				</td>
				<td width="15%" align='center' bgcolor='#66CCFF'>
					<p>
						${eoms:a2u('开始时间')}
					</p>
				</td>
				<td width="15%" align='center' bgcolor='#66CCFF'>
					<p>
						${eoms:a2u('结束时间')}
					</p>
				</td>
				<td width="5%" align='center' bgcolor='#66CCFF'>
					<p>
						${eoms:a2u('会议状态')}
					</p>
				</td>
				<td width="5%" align='center' bgcolor='#66CCFF'>
					<p>
						${eoms:a2u('是否录音')}
					</p>
				</td>

				<td width="5%" align='center' bgcolor='#66CCFF'>
					<p>
						${eoms:a2u('语音名称')}
					</p>
				</td>
				<td width="5%" align='center' bgcolor='#66CCFF'>
					<p>
						${eoms:a2u('是否直接呼叫')}
					</p>
				</td>
				<td width="5%" align='center' bgcolor='#66CCFF'>
					<p>
						${eoms:a2u('显示会议成员')}
					</p>
				</td>

			</tr>

			<logic:iterate id="tawHieApply" name="TAWCONF" type="com.boco.eoms.commons.voiceMessage.model.TawConfInfo">
  <tr class="tr_show">
    <td width="3%" height="25"><bean:write name="tawHieApply" property="confNo" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieApply" property="confName" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieApply" property="confTrunkNo" scope="page"/></td>
    <td width="7%" height="25"><bean:write name="tawHieApply" property="confOrganizer" scope="page"/></td>
    <td width="15%" height="25"><bean:write name="tawHieApply" property="confBeginTime" scope="page"/></td>
    <td width="15%" height="25"><bean:write name="tawHieApply" property="confEndTime" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieApply" property="confState" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieApply" property="confRecord" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieApply" property="confRFile" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieApply" property="isCallout" scope="page"/></td>
    <td width="5%" height="25"><html:link page="/TawHieApply/listconmem.do" paramId="confNo" paramName="tawHieApply" paramProperty="confNo"><img src="<%=request.getContextPath()%>/images/icons/role.gif" border="0"></html:link></td>
  </tr>
	</logic:iterate>

		</table>

	</div>

	<div id="info">
		${eoms:a2u('会议查询结果，点击人员显示，可以明细到会员参加具体人员')}
	</div>

</div>
<c:out value="${buttons}" escapeXml="false" />



<%@ include file="/common/footer_eoms.jsp"%>
