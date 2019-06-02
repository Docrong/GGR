<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
<!--
	Ext.onReady(function() {
		v = new eoms.form.Validation({form:'forumsForm'});
});
	function ConfirmDel(){
           if(confirm("<bean:message key='common.tips.delete'/>")){
             return true;
           }else{
             return false;
           }
    }
//-->
</script>
<title><bean:message key="forumsDetail.title"/></title>
<content tag="heading"><bean:message key="forumsDetail.heading"/></content>

<html:form action="forums.do" method="post" styleId="forumsForm"> 
	<html:hidden property="id"/>
	
	<html:hidden property="parentId"/>
	<html:hidden property="isDel"/>
	<html:hidden property="isLeaf"/>
	<html:hidden property="createTime"/>
	<html:hidden property="createrId"/>
<ul>
    <li>
        <bean:message key="forumsForm.parentId"/>
        > <eoms:id2nameDB id="${forumsForm.parentId}" beanId="forumsDao" />
    </li>


    <li>
        <eoms:label styleClass="desc" key="forumsForm.title"/>
        <html:errors property="title"/>
        <input type="text" name="title" id="title" styleClass="text medium" value="<bean:write name='forumsForm' property='title'/>" alt="allowBlank:false,vtext:'<bean:message key='forumsForm.tips.noname'/>'"/>
    </li>
    
    <li>
        <eoms:label styleClass="desc" key="forumsForm.description"/>
        <html:errors property="description"/>
        <html:textarea property="description" styleId="description" styleClass="textarea medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>
		<logic:notEmpty name="forumsForm" property="id">
        	<html:submit styleClass="button" property="method.delete" onclick="return ConfirmDel()">
            	<fmt:message key="button.delete"/>
       	 	</html:submit>
		</logic:notEmpty>
		<!-- 
        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
        -->
       
    </li>
</ul>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>