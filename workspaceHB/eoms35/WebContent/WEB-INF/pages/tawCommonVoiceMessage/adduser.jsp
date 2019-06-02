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
        tabs.addTab('form', '${eoms:a2u('人员添加')}');
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
<html:form action="/TawHieApply/adduserdo" method="post"
	styleId="tawHieApplyForm">
<table border="0" width="95%" cellspacing="1">
       
	
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('用户编号')}</td>
		<td colspan=3>
		<html:text name="tawHieApplyForm" property="userId" readonly="true" />
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('姓名')}</td>
		<td colspan=3>
	<html:text property="userName" styleId="userName" styleClass="text medium"  alt="allowBlank:false"/>
		
	   </td>
 </tr>
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('电话')}</td>
		<td colspan=3>
	 <!--  input type="text" name="name">-->

	 			<html:text property="userTel" styleClass="text medium"
				alt="allowBlank:false,vtext:'${eoms:a2u('电话不能为空')}'" />

	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('用户类型')}</td>
		<td colspan=3>
		<eoms:dict key="dict-voiceMessage" dictId="userType" isQuery="false" selectId="userType" beanId="selectXML" alt="allowBlank:false"/>
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('认证码')}</td>
		<td colspan=3>
	
			 <html:text property="userCode" styleId="userCode" styleClass="text medium"
				alt="allowBlank:false,vtext:'${eoms:a2u('职责不能为空')}'" />
	   </td>
 </tr>
 
 
</table>
<table border="0" width="70%" cellspacing="0">
  <tr>
    <td width="100%" height="32" align="right">
                    <html:submit property="strutsButton" styleClass="clsbtn2">
                     ${eoms:a2u('保存')}
                    </html:submit>
                    &nbsp;&nbsp;</td>
  </tr>
  </table>
  
</html:form>
</div>
  <div id="info">
    ${eoms:a2u('请填写用户信息，填写完毕后，请点击保存')}
  </div>
</div>
</body>
</html>
