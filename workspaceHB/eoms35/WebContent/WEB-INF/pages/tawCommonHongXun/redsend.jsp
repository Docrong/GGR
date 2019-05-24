<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%
String id=(String)request.getAttribute("id");
String com_name=(String)request.getAttribute("com_name");
String xiaozu_name=(String)request.getAttribute("xiaozu_name");

String name=(String)request.getAttribute("name");
String tel=(String)request.getAttribute("tel");
String zhize=(String)request.getAttribute("zhize");
String remark=(String)request.getAttribute("remark");


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
        tabs.addTab('form', '${eoms:a2u('短信发送')}');
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
<html:form action="/TawCommonHongXunAction/redonegaojin" method="post"
	styleId="TawCommonHongXunForm">
<table border="0" width="95%" cellspacing="1">
   
  <input type="hidden" name="id" id="id" value="<%=id%>">   
	
 
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('人员姓名')}</td>
		<td colspan=3>
	 <%=name%>


	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('发送号码')}</td>
		<td colspan=3>
	 <html:text property="tel" styleId="tel" styleClass="text medium"  value="<%=tel%>"
				alt="allowBlank:false,vtext:'${eoms:a2u('电话不能为空')}'" />
		
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('发送内容(职责内容)')}</td>
		<td colspan=3>
	
			 <html:textarea property="zhize" styleId="zhize" rows="4" cols="30" styleClass="text medium"  value="<%=zhize%>"
				alt="allowBlank:false,vtext:'${eoms:a2u('职责不能为空')}'" />
	   </td>
 </tr>
 
 
</table>
<table border="0" width="70%" cellspacing="0">
  <tr>
    <td width="100%" height="32" align="right">
    <html:submit property="strutsButton" styleClass="clsbtn2" onclick="return checkForm()">
 
     ${eoms:a2u('短信发送')}
   
               </html:submit>
                    &nbsp;&nbsp;</td>
  </tr>
  </table>
  
</html:form>
</div>
  <div id="info">
    ${eoms:a2u('请正确输入电话和发送内容后，点击发送')}
  </div>
</div>
</body>
</html>
