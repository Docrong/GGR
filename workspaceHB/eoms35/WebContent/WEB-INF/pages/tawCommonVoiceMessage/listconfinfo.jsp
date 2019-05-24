<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*"%>
<%

	
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
        tabs.addTab('form', '${eoms:a2u('会议添加结果')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>



<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/voiceMessage/TawHieApply/conf.do"/>'"
        value='${eoms:a2u('返回')}'/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">

<div id="form" class="tab-content">
<table cellpadding="0" class="table logList" cellspacing="0">
 <tr > 
            <td width='3%' align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('会议编号')}</p></td>
            <td width='5%' align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('会议主题')}</p></td>
		    <td width='5%' align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('会议组号')}</p></td>
		    <td width="7%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('发起人')}</p></td>
		    <td width="15%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('开始时间')}</p></td>

		   
		    <td width="15%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('结束时间')}</p></td>
		    <td width="5%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('会议状态')}</p></td>
		    <td width="5%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('是否录音')}</p></td>
		    <td width="5%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('语音名称')}</p></td>
		    <td width="5%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('是否直接呼叫')}</p></td>
		 
</tr>
 
 <logic:iterate id="tawHieApply" name="TAWCI" type="com.boco.eoms.commons.voiceMessage.model.TawConfInfo">
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
  </tr>
	</logic:iterate>

</table>
<br>
<br>
<table border="0" width="95%" cellspacing="1" cellpadding="1" align=center>
<tr>
    <td class="table_title" width="100%">
    	${eoms:a2u('会议成员：')}
    </td>
</tr>
</table>
<table cellpadding="0" class="table logList" cellspacing="0">
  
  <tr class="td_label">
      <td width="5%" height="25">${eoms:a2u('成员编号')}</td>
      <td width="5%" height="25">${eoms:a2u('姓名')}</td>
      <td width="5%" height="25">${eoms:a2u('联系方式')}</td>
      <td width="5%" height="25">${eoms:a2u('用户类型')}</td>
      <td width="7%" height="25">${eoms:a2u('参会方式')}</td>
  </tr>
  <logic:iterate id="tawHieMem" name="TAWMEM" type="com.boco.eoms.commons.voiceMessage.model.TawConMem">
  <tr class="tr_show">
    <td width="5%" height="25"><bean:write name="tawHieMem" property="userId" scope="page"/></td>
    <td width="7%" height="25"><bean:write name="tawHieMem" property="userName" scope="page"/></td>
    <td width="5%" height="25"><bean:write name="tawHieMem" property="userTel" scope="page"/></td>
    <td width="7%" height="25"><bean:write name="tawHieMem" property="userType" scope="page"/></td>
    <td width="7%" height="25"><bean:write name="tawHieMem" property="strJoinMode" scope="page"/></td>
  </tr>
	</logic:iterate>
</table>

</div>

  <div id="info">
    ${eoms:a2u('会议添加完成信息')}
  </div>
  
</div>
<c:out value="${buttons}" escapeXml="false"/>



<%@ include file="/common/footer_eoms.jsp"%>
