<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%
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
        tabs.addTab('form', '${eoms:a2u('人员添加')}');
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
<html:form action="/TawCommonHongXunAction/redsave" method="post"
	styleId="TawCommonHongXunForm">
<table border="0" width="95%" cellspacing="1">
       
	
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('机构名称')}</td>
		<td colspan=3>
		
		<html:select property="com_name" alt="allowBlank:false"> 
		<option value="">${eoms:a2u('请选择')}</option>
		<%for(int i=0;i<comList.size();i++){
          out.write("<option value='"+comList.get(i).toString()+"'>"+comList.get(i).toString()+"</option>");
          }
          
           %>
        </html:select> 
		
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('小组职务')}</td>
		<td colspan=3>
	
		<html:select property="xiaozu_name" alt="allowBlank:false"> 
		<option value="">${eoms:a2u('请选择')}</option>
		<%for(int i=0;i<xiaozuList.size();i++){
          out.write("<option value='"+xiaozuList.get(i).toString()+"'>"+xiaozuList.get(i).toString()+"</option>");
          }
          
           %>
        </html:select> 
	   </td>
 </tr>
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('姓名')}</td>
		<td colspan=3>
	 <!--  input type="text" name="name">-->

	 			<html:text property="name" styleId="name" styleClass="text medium"
				alt="allowBlank:false,vtext:'${eoms:a2u('姓名不能为空')}'" />

	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('电话')}</td>
		<td colspan=3>
	 <html:text property="tel" styleId="tel" styleClass="text medium"
				alt="allowBlank:false,vtext:'${eoms:a2u('电话不能为空')}'" />
		
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('职责')}</td>
		<td colspan=3>
	
			 <html:textarea property="zhize" styleId="zhize" styleClass="text medium"
				alt="allowBlank:false,vtext:'${eoms:a2u('职责不能为空')}'" />
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('备注')}</td>
		<td colspan=3>

	 	 <html:text property="remark" styleId="remark" styleClass="text medium"
				alt="allowBlank:false,vtext:'${eoms:a2u('备注不能为空')}'" />
		
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
    ${eoms:a2u('请填写防汛用户信息，填写完毕后，请点击保存')}
  </div>
</div>
</body>
</html>
