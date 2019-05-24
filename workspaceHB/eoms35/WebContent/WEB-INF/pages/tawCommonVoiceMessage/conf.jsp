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
        tabs.addTab('form', '${eoms:a2u('会议查询')}');
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
<div id="tabs">
<div id="form" class="tab-content">
<html:form action="/TawHieApply/listconfdo" method="post"
	styleId="tawHieApplyForm">
<table border="0" width="95%" cellspacing="1">
       
	
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('会议编号')}</td>
		<td colspan=3>
		<html:select   style="width: 5.8cm;"   property="confNo" styleClass="clstext" >
                     <html:options collection="CONFIDLIST" property="value"
                       labelProperty="label"/>
                  </html:select>
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('会议名称')}</td>
		<td colspan=3>
	<html:text property="confName" size="20" value=""></html:text>
		
	   </td>
 </tr>
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('会议时间')}</td>
		<td colspan=3>
	<html:text property="confBeginTime" styleId="confBeginTime" styleClass="text medium"  readonly="true" onclick="popUpCalendar(this, this);"/>
	

	   </td>
 </tr>
 
 
 
</table>
<table border="0" width="70%" cellspacing="0">
  <tr>
    <td width="100%" height="32" align="right">
                    <html:submit property="strutsButton" styleClass="clsbtn2">
                     ${eoms:a2u('会议查询')}
                    </html:submit>
                    &nbsp;&nbsp;</td>
  </tr>
  </table>
  
</html:form>
</div>
  <div id="info">
    ${eoms:a2u('请填写查询条件，然后点击查询')}
  </div>
</div>
</body>
</html>
