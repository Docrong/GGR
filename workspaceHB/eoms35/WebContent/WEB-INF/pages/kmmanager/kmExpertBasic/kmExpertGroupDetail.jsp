<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
    Ext.onReady(function() {
        v = new eoms.form.Validation({form:'kmExpertGroupForm'});
    });

	//修改
	function onSubmitEdit(){
	    var id = document.getElementById("id").value;
	    var url='${app}/kmmanager/kmExpertGroup.do?method=edit&id=' + id ;
	    window.location.href(url);
    }
    
    //删除
    function onSubmitDele(){
	    var id = document.getElementById("id").value;
	    var url='${app}/kmmanager/kmExpertGroup.do?method=remove&id=' + id ;
	    window.location.href(url);    
    }    
</script>

<html:form action="/kmExpertGroup.do?method=save" styleId="kmExpertGroupForm" method="post">

    <fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager"><br>
        <table class="formTable">
        
            <caption>
                <div class="header center">团队名称</div>
            </caption>

			<tr class="tr_show">
				<td class="label" nowrap="nowrap" width="15%">
					<fmt:message key="kmExpertGroup.groupName" />
				</td>
				<td nowrap="nowrap" width="85%">
				    ${kmExpertGroupForm.groupName}
				</td>			
			</tr>
			<tr class="tr_show">
				<td class="label">
					<fmt:message key="kmExpertGroup.specialty" />
				</td>
				<td class="content">
				    <eoms:id2nameDB id="${kmExpertGroupForm.specialty}" beanId="ItawSystemDictTypeDao" />
				</td>			
			</tr>

			<tr class="tr_show">
				<td class="label">
					<fmt:message key="kmExpertGroup.groupLeader" />
				</td>
				<td class="content">
				    <eoms:id2nameDB id="${kmExpertGroupForm.groupLeader}" beanId="tawSystemUserDao" />
				</td>			
			</tr>

			<tr class="tr_show">
				<td class="label">
					<fmt:message key="kmExpertGroup.groupMember" />
				</td>
				<td class="content">
				    ${kmExpertGroupForm.groupMemberName}
				</td>			
			</tr>
			
			<tr class="tr_show">
				<td class="label">
					<fmt:message key="kmExpertGroup.createUser" />
				</td>
				<td class="content">
				    <eoms:id2nameDB id="${kmExpertGroupForm.createUser}" beanId="tawSystemUserDao" />
				</td>			
			</tr>

			<tr class="tr_show">
				<td class="label">
					<fmt:message key="kmExpertGroup.createTime" />
				</td>
				<td class="content">
				    ${kmExpertGroupForm.createTime}
				</td>			
			</tr>						
	    </table>
    </fmt:bundle>

	<html:hidden property="id" value="${kmExpertGroupForm.id}" />
	
	<table>
	    <tr>
		    <td>
		        <input type="button" class="btn" value="修改" onclick="javascript:onSubmitEdit();"/>&nbsp;
		        <input type="button" class="btn" value="删除" onclick="javascript:onSubmitDele();"/>&nbsp;
		    </td>
	    </tr>
	</table>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>