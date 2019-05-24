<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%
String info="省公司发布汛情黄色预警信号，请按照防汛应急预案要求做好应对汛情的准备工作。";
info=new String(info.getBytes("ISO8859-1"),"utf-8");
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
        tabs.addTab('form', '${eoms:a2u('三级防汛短信发送')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>
<script language="javascript">

Ext.onReady(function() {
	v = new eoms.form.Validation({form:'TawCommonHongXunForm'});
});
</script>
<script language="javascript">


function checkForm() {

 
  if(confirm('send message?'))
    return true;
  else
    return false;

}

</script>
<content tag="heading">${eoms:a2u('短信发送')}</content>
<div id="tabs">
<div id="form" class="tab-content">
<html:form action="/TawCommonHongXunAction/threegaojin" method="post"
	styleId="TawCommonHongXunForm">
<table border="0" width="95%" cellspacing="1">
   

 
 
 <tr class="tr_show">
 <td class="clsfth">${eoms:a2u('说明')}</td>
		<td class="clsfth">${eoms:a2u('短信发送(黄色预警，所有组员发送以下短信)')}</td>
	
 </tr>

 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('发送内容')}</td>
		<td colspan=3>
	
			 <html:textarea property="zhize" styleId="zhize" rows="4" cols="30" styleClass="text medium"  value="<%=info%>"
				alt="allowBlank:false,vtext:'${eoms:a2u('职责不能为空')}'" />
	   </td>
 </tr>
 
 
</table>
<table border="0" width="70%" cellspacing="0">
  <tr>
    <td width="100%" height="32" align="right">
    <html:submit property="strutsButton" styleClass="clsbtn2" onclick="return checkForm()">
 
     ${eoms:a2u('三级防汛短信发送')}
   
               </html:submit>
                    &nbsp;&nbsp;</td>
  </tr>
  </table>
  
</html:form>
</div>
  <div id="info">
    ${eoms:a2u('请正确输入发送内容后，点击发送')}
  </div>
</div>
</body>
</html>
