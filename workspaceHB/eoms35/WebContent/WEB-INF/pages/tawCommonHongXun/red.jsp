<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
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
        tabs.addTab('form', '${eoms:a2u('一级防汛短信发送')}');
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

  if(confirm("send message?"))
    return true;
  else
    return false;
}



</script>
<content tag="heading"></content>
<div id="tabs">
<div id="form" class="tab-content">
<html:form action="/TawCommonHongXunAction/redgaojin" method="post"
	styleId="TawCommonHongXunForm">
<table border="0" width="95%" cellspacing="1">
   
   
	
 
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('一级防汛')}</td>
		<td colspan=3>
	   <html:submit property="strutsButton" styleClass="clsbtn2" onclick="return checkForm()">
 
     ${eoms:a2u('一级防汛短信发送')}
   
               </html:submit>


	   </td>
 </tr>
 
 
 
</table>

  
</html:form>
</div>
  <div id="info">
    ${eoms:a2u('点击发送，发送不同级别短信')}
  </div>
</div>
</body>
</html>
