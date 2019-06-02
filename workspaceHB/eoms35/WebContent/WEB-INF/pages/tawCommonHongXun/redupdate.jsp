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
String flag=(String)request.getAttribute("flag");
	List xiaozuList = (List) request.getAttribute("xiaozuList");
	List comList = (List) request.getAttribute("comList");

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
	v = new eoms.form.Validation({form:'TawCommonHongXunForm'});
});
</script>
<div id="tabs">
<div id="form" class="tab-content">
<html:form action="/TawCommonHongXunAction/redupdo" method="post"
	styleId="TawCommonHongXunForm">
<table border="0" width="95%" cellspacing="1">
    <input type="hidden" name="flag" id="flag" value="<%=flag%>">
  <input type="hidden" name="id" id="id" value="<%=id%>">   
	
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('机构名称')}</td>
		<td colspan=3>
		<select name="com_name">
          
          <%for(int i=0;i<comList.size();i++){
          if(comList.get(i).toString().equals(com_name)){
          out.write("<option value='"+comList.get(i).toString()+"' selected='selected'>"+comList.get(i).toString()+"</option>");
          }else{
          out.write("<option value='"+comList.get(i).toString()+"'>"+comList.get(i).toString()+"</option>");
          }
          }
          
           %>
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('小组职务')}</td>
		<td colspan=3>
	<select name="xiaozu_name">
          
          <%for(int i=0;i<xiaozuList.size();i++){
          if(xiaozuList.get(i).toString().equals(xiaozu_name)){
          out.write("<option value='"+xiaozuList.get(i).toString()+"' selected='selected'>"+xiaozuList.get(i).toString()+"</option>");
          }else{
          out.write("<option value='"+xiaozuList.get(i).toString()+"'>"+xiaozuList.get(i).toString()+"</option>");
          }
          }
          
           %>
	   </td>
 </tr>
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('姓名')}</td>
		<td colspan=3>
	 <!--  input type="text" name="name">-->

	 			<html:text property="name" styleId="name" styleClass="text medium" value="<%=name%>"
				alt="allowBlank:false,vtext:'${eoms:a2u('姓名不能为空')}'" />

	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('电话')}</td>
		<td colspan=3>
	 <html:text property="tel" styleId="tel" styleClass="text medium"  value="<%=tel%>"
				alt="allowBlank:false,vtext:'${eoms:a2u('电话不能为空')}'" />
		
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('职责')}</td>
		<td colspan=3>
	
			 <html:textarea property="zhize" styleId="zhize" rows="4" cols="30" styleClass="text medium"  value="<%=zhize%>"
				alt="allowBlank:false,vtext:'${eoms:a2u('职责不能为空')}'" />
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('备注')}</td>
		<td colspan=3>

	 	 <html:text property="remark" styleId="remark" styleClass="text medium"  value="<%=remark%>"
				alt="allowBlank:false,vtext:'${eoms:a2u('备注不能为空')}'" />
		
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
