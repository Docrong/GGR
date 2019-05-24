<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>

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
        tabs.addTab('form', '${eoms:a2u('会议增加')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>
<script language="javascript">

Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawHieApplyForm'});
});
</script>

<content tag="heading">${eoms:a2u('会议第一步（选择会议信息）')}</content>
<div id="tabs">
<div id="form" class="tab-content">
<html:form action="/TawHieApply/addconf" method="post"
	 >
<table border="0" width="95%" cellspacing="1">
       
	
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('发起人')}</td>
		<td colspan=3>
		<html:select   style="width: 5.8cm;"   property="confOrganizer" styleClass="clstext" >
        <html:options collection="CONFORGLIST" property="value" labelProperty="label"/>
      </html:select>
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('会议主题')}</td>
		<td colspan=3>
	<html:text property="confName" styleId="confName" styleClass="text medium"  alt="allowBlank:false"/>
		
	   </td>
 </tr>
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('会议组号')}</td>
		<td colspan=3>
	 <!--  input type="text" name="name">-->

	 			<html:text property="confTrunkNo" value="0" styleClass="text medium"
				alt="allowBlank:false,vtext:'${eoms:a2u('会议组号不能为空')}'" />

	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('会议状态')}</td>
		<td colspan=3>
		<html:radio property="confState"  value="0" styleClass="clstext" />
           ${eoms:a2u('未分配资源')}
        <html:radio property="confState"  value="1" styleClass="clstext"/>
           ${eoms:a2u('已分配资源')}
        <html:radio property="confState"  value="2" styleClass="clstext"/>
           ${eoms:a2u('已结束')}
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('是否录音')}</td>
		<td colspan=3>
	
		<html:radio property="confRecord"  value="0" styleClass="clstext" />
           ${eoms:a2u('否')}
        <html:radio property="confRecord"  value="1" styleClass="clstext" />
           ${eoms:a2u('是')}
	   </td>
 </tr>
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('是否直接呼叫所有号码')}</td>
		<td colspan=3>
	
		<html:radio property="isCallout"  value="0" styleClass="clstext" />
           ${eoms:a2u('否')}
        <html:radio property="isCallout"  value="1" styleClass="clstext" />
           ${eoms:a2u('是')}
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('起始时间')}</td>
		<td colspan=3>
	<html:text property="confBeginTime" styleId="confBeginTime" styleClass="text medium" alt="allowBlank:false" readonly="true" onclick="popUpCalendar(this, this);"/>
	
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('结束时间')}</td>
		<td colspan=3>
	<html:text property="confEndTime" styleId="confEndTime" styleClass="text medium" alt="allowBlank:false" readonly="true" onclick="popUpCalendar(this, this);"/>
	   </td>
 </tr>
 
</table>
<table border="0" width="70%" cellspacing="0">
  <tr>
    <td width="100%" height="32" align="right">
                    <html:submit property="strutsButton" styleClass="clsbtn2">
                     ${eoms:a2u('下一步')}
                    </html:submit>
                    &nbsp;&nbsp;</td>
  </tr>
  </table>
  
</html:form>
</div>
  <div id="info">
    ${eoms:a2u('请填写会议信息，填写完毕后，请点击下一步')}
  </div>
</div>
</body>
</html>
