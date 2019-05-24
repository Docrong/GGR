<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<title></title>
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'smsServiceForm'});
});
</script>

<html:form action="/smsServices.do?method=xsave" method="post" styleId="smsServiceForm">
<% 
	String parentid = request.getParameter("parentid"); 
	String status = request.getParameter("status");
%>
<html:hidden property="id"/>

    <table>
    	<tr>
    		<td>
    			<bean:message key='smsModule.name'/>
    		</td>
    		<td>
    			<html:text property="name" styleClass="text" alt="allowBlank:false,vtext:'${eoms:a2u('请输入模块业务名称')}'"></html:text>
			</td>
		</tr>
    	
    	<tr>
    		<td>
    			<bean:message key='smsModule.userid'/>
    		</td>
    		<td>
        		<input type="text" name="userId" class="text" value="<bean:write scope="session" name="sessionform" property="userid"/>" readonly="readonly"/>
			</td>
		</tr>
    
    	<tr>
    		<td>
    			<bean:message key='smsModule.remark'/>
    		</td>
    		<td>
    			<html:textarea property="remark" styleClass="textarea"></html:textarea>
    		</td>
    	</tr>
    </table>
        
        

    
    
        
    <html:hidden property="parentId" value="<%=parentid%>"/>
    <html:hidden property="status" value="<%=status%>"/>
    <html:hidden property="leaf"/>
    <html:hidden property="deleted"/>
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <bean:message key="button.save"/>
        </html:submit>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>