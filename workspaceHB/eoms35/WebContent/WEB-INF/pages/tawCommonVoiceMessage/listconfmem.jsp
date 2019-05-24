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
        tabs.addTab('form', '${eoms:a2u('会议人员明细')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>



<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="history.back()"
        value='${eoms:a2u('返回')}'/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">

<div id="form" class="tab-content">

<table cellpadding="0" class="table logList" cellspacing="0">
<tr > 
    <td width="5%" height="25">${eoms:a2u('会议编号')}</td>
    <td width="5%" height="25">${eoms:a2u('会议主题')}</td>
    <td width="5%" height="25">${eoms:a2u('会议组号')}</td>
    <td width="5%" height="25">${eoms:a2u('发起人')}</td>
    <td width="7%" height="25">${eoms:a2u('开始时间')}</td>
    <td width="7%" height="25">${eoms:a2u('结束时间')}</td>
    <td width="5%" height="25">${eoms:a2u('会议状态')}</td>
    <td width="5%" height="25">${eoms:a2u('是否录音')}</td>
    <td width="7%" height="25">${eoms:a2u('语音名称')}</td>
    <td width="5%" height="25">${eoms:a2u('是否直接呼叫')}</td>
  </tr>
 <logic:iterate id="tawHieApply" name="TAWCI" type="com.boco.eoms.commons.voiceMessage.model.TawConfInfo">
  <tr class="tr_show">
    <td width="5%" height="25"><bean:write name="tawHieApply" property="confNo" scope="page"/></td>
    <td width="7%" height="25"><bean:write name="tawHieApply" property="confName" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieApply" property="confTrunkNo" scope="page"/></td>
    <td width="7%" height="25"><bean:write name="tawHieApply" property="confOrganizer" scope="page"/></td>
    <td width="7%" height="25"><bean:write name="tawHieApply" property="confBeginTime" scope="page"/></td>
    <td width="7%" height="25"><bean:write name="tawHieApply" property="confEndTime" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieApply" property="confState" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieApply" property="confRecord" scope="page"/></td>
    <td width="7%" height="25"><bean:write name="tawHieApply" property="confRFile" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieApply" property="isCallout" scope="page"/></td>
  </tr>
	</logic:iterate>

</table>
<br>
<br>
<table cellpadding="0" class="table logList" cellspacing="0" >
  <tr class="tr_show">
    <td width="100%" colspan="6" height="25" align="left">
       ${eoms:a2u('会议成员：')}
    </td>
  </tr>
  <tr class="tr_show">
      <td width="5%" height="25">${eoms:a2u('成员编号')}</td>
      <td width="5%" height="25">${eoms:a2u('姓名')}</td>
      <td width="5%" height="25">${eoms:a2u('联系方式')}</td>
      <td width="5%" height="25">${eoms:a2u('加入时间')}</td>
      <td width="5%" height="25">${eoms:a2u('退出时间')}</td>
      <td width="5%" height="25">${eoms:a2u('加入方式')}</td>
  </tr>
  <logic:iterate id="tawHieMem" name="TAWMEM" type="com.boco.eoms.commons.voiceMessage.model.TawConMem">
  <tr class="tr_show">
    <td width="5%" height="25"><bean:write name="tawHieMem" property="userId" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieMem" property="userName" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieMem" property="userTel" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieMem" property="joinTime" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieMem" property="exitTime" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieMem" property="strJoinMode" scope="page"/></td>
  </tr>
	</logic:iterate>
</table>
</div>

  <div id="info">
    ${eoms:a2u('会议及会议人员明细')}
  </div>
  
</div>
<c:out value="${buttons}" escapeXml="false"/>



<%@ include file="/common/footer_eoms.jsp"%>
