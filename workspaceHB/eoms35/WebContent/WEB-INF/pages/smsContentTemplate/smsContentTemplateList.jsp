<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<style type="text/css">
    .tr-over td {
        background-color: #b3f3bb;
    }
</style>
<script type="text/javascript">
    <!--
    function setFormValue(content) {
        var arr = content.split("#");
        window.opener.smsSendForm.content.value = arr[0];
        window.opener.smsSendForm.contentRemark.value = arr[1];
    }

    //-->
</script>
<!--根据给定的实例名生成标题 -->
<title><fmt:message key="smsContentTemplateDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="smsContentTemplateDetail.heading"/></content> -->


<html:form action="/smsContentTemplates.do?method=forward2New" method="post" styleId="smsContentTemplateForm">
    <table class="formTable">
        <tr>
            <td colspan="3" align="center">
                <bean:message key='smsTitle.mgrContent'/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message key='smsContentTemplate.name'/>
            </td>
            <td class="label">
                <bean:message key='smsContentTemplate.content'/>
            </td>
            <td class="label">
                <bean:message key='smsContentTemplate.remark'/>
            </td>
        </tr>
        <logic:iterate id="contentList" name="contentList">
            <tr ondblclick="setFormValue('<bean:write name="contentList" property="content"/>#<bean:write
                    name="contentList" property="remark"/>');" title="双击鼠标即可选择" onMouseOver="this.className='tr-over'"
                onMouseOut="this.className=''">
                <td>
                    <bean:write name="contentList" property="name"/>
                </td>

                <td id="cid">
                    <bean:write name="contentList" property="content"/>
                </td>

                <td>
                    <bean:write name="contentList" property="remark"/>
                </td>
            </tr>
        </logic:iterate>
    </table>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>