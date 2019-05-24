<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%
String id=(String)request.getAttribute("id");
String username =(String)request.getAttribute("username");
String usertel =(String)request.getAttribute("usertel");

String user_type=(String)request.getAttribute("user_type");
String user_code=(String)request.getAttribute("user_code");
String flag=(String)request.getAttribute("flag");


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
        <%if(flag.equals("0")){%>
                tabs.addTab('form', '${eoms:a2u('人员删除')}');
        <%}else{%>
                tabs.addTab('form', '${eoms:a2u('人员更新')}');
        <%}%>

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
<html:form action="/TawHieApply/redupdatedo" method="post"
	styleId="tawHieApplyForm">
<table border="0" width="95%" cellspacing="1">
    <input type="hidden" name="flag" id="flag" value="<%=flag%>">
  <input type="hidden" name="id" id="id" value="<%=id%>">   
	
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('姓名')}</td>
		<td colspan=3>
		
	   <html:text property="userName" styleId="userName" styleClass="text medium" value="<%=username%>"
				alt="allowBlank:false,vtext:'${eoms:a2u('姓名不能为空')}'" />
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('电话')}</td>
		<td colspan=3>
	<html:text property="userTel" styleId="userTel" styleClass="text medium" value="<%=usertel%>"
				alt="allowBlank:false,vtext:'${eoms:a2u('电话不能为空')}'" />
		
	   </td>
 </tr>
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('用户类型')}</td>
		<td colspan=3>
	 <!--  input type="text" name="name">-->
<eoms:dict key="dict-voiceMessage" dictId="userType" isQuery="false" selectId="userType" beanId="selectXML" defaultId="<%=user_type%>" alt="allowBlank:false" />

	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('认证码')}</td>
		<td colspan=3>
	 <html:text property="userCode" styleId="userCode" styleClass="text medium"  value="<%=user_code%>"
				alt="allowBlank:false,vtext:'${eoms:a2u('认证码不能为空')}'" />
		
	   </td>
 </tr>

 
</table>
<table border="0" width="70%" cellspacing="0">
  <tr>
    <td width="100%" height="32" align="right">
    <html:submit property="strutsButton" styleClass="clsbtn2">
     <%if(flag.equals("0")){%>
      ${eoms:a2u('删除')}
     <%}else{%>
     ${eoms:a2u('更新')}
     <%}%>
                    </html:submit>
                    &nbsp;&nbsp;</td>
  </tr>
  </table>
  
</html:form>
</div>
  <div id="info">
    <%if(flag.equals("0")){%>
      ${eoms:a2u('点击删除，删除该人员')}
     <%}else{%>
     ${eoms:a2u('点击更新，更新人员信息')}
     <%}%>
  </div>
</div>
</body>
</html>
