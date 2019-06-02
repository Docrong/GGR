<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<title></title>
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'smsModuleForm'});
});
</script>

<html:form action="/smsServices.do?method=xsave" method="post" styleId="smsModuleForm"> 


<input type="hidden" name="id" value="<bean:write name='smsServiceForm' property='id'/>"/>

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
    			<html:text property="userId" styleClass="text" readonly="true"></html:text>
			</td>
		</tr>
    
    	<tr>
    		<td> 
    			<bean:message key='smsModule.remark'/>
    		</td>
    		<td>
    			<html:text property="remark" styleClass="text"></html:text>
    		</td>
    	</tr>
    </table>        
    <input type="hidden" name="parentId" value="<bean:write name='smsServiceForm' property='parentId'/>"/>
    <input type="hidden" name="leaf" value="<bean:write name='smsServiceForm' property='leaf'/>"/>
    <input type="hidden" name="deleted" value="<bean:write name='smsServiceForm' property='deleted'/>"/>
    <input type="hidden" name="status" value="<bean:write name='smsServiceForm' property='status'/>"/>
    
        <html:submit styleClass="button" property="method.save">
            <bean:message key="button.save"/>
        </html:submit>
 

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>