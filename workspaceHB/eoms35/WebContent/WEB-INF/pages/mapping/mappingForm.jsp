<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<html:form action="/mappings.do?method=save" styleId="mappingForm" method="post">

    <fmt:bundle basename="config/applicationResource-mapping">

        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="mapping.form.heading"/></div>
            </caption>

            <tr>
                <td class="label">
                    <fmt:message key="mapping.appcode"/>
                </td>
                <td class="content">
                    <html:text property="app_code" styleId="app_code"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${mappingForm.app_code}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="mapping.appname"/>
                </td>
                <td class="content">
                    <html:text property="app_name" styleId="app_name"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${mappingForm.app_name}"/>
                </td>
            </tr>


            <tr>
                <td class="label">
                    <fmt:message key="mapping.newtable"/>
                </td>
                <td class="newtable">
                    <html:text property="new_table" styleId="new_table"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${mappingForm.new_table}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="mapping.beanid"/>
                </td>
                <td class="newtable">
                    <html:text property="beanid" styleId="mapping.beanid"
                               styleClass="text medium"
                               alt="allowBlank:true,vtext:''" value="${mappingForm.beanid}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="mapping.context"/>
                </td>
                <td class="content">
                    <html:textarea cols="20" rows="5" property="context" styleId="context"
                                   styleClass="text medium"
                                   alt="allowBlank:true,vtext:''" value="${mappingForm.context}"/>
                </td>
            </tr>


        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
                <c:if test="${not empty mappingForm.id}">
                    <input type="button" class="btn" value="<fmt:message key="button.delete"/>"
                           onclick="javascript:if(confirm('confirm?')){
                                   var url='${app}/mappingstorage/mappings.do?method=remove&id=${mappingForm.id}';
                                   location.href=url}"/>
                </c:if>
            </td>
        </tr>
    </table>
    <html:hidden property="id" value="${mappingForm.id}"/>
</html:form>
<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'mappingForm'});

        var checkunique = function (ev, el) {
            if (el.value.trim() == "") return;

            if (/^\d/.test(el.value.trim())) {
                v.markInvalid(el, '不能以数字开头');
                alert('不能以数字开头');
                return;
            }

            Ext.lib.Ajax.request("post", eoms.appPath
                + "/mappingstorage/mappings.do?method=checkunique", {
                success: function (x) {
                    if (x.responseText == "false") {
                        v.markInvalid(el, '表名已存在！请选择其它名字');
                        alert('表名已存在！请选择其它名字');
                    } else {
                        v.clearInvalid(el);
                    }
                }
            }, "name=" + el.value);
        };

        var el = Ext.get('new_table');
        el.on('blur', checkunique);
    });

</script>
<%@ include file="/common/footer_eoms.jsp" %>