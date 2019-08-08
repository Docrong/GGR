<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<style type="text/css">
    .x-form-column {
        width: 320px
    }
</style>

<script type="text/javascript">
    function showPage() {
        var pars = '';
        var url = '';
        url = '${app}/sheet/citycustom/citycustom.do?method=showInputRejectPage&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}';
        if (url != '') {
            var myAjax = new Ajax.Request(
                url,
                {method: 'get', parameters: pars, onComplete: showResponse}
            );
        }
    }

    function showResponse(originalRequest) {
        $('idSpecial').innerHTML = originalRequest.responseText;

        //init Form validation and styles
        valider({form: 'theform', vbtn: 'method.save'});
    }

    Ext.onReady(function () {
        showPage();
    });
</script>

<div id="sheetform">
    <html:form action="/citycustom.do?method=performDeal" styleId="theform">
        <div ID="idSpecial"></div>
        <!-- buttons -->
        <div class="x-form-item">
            <div class="form-btns">
                <html:button styleClass="btn" property="method.save" styleId="method.save">
                    <fmt:message key="button.save"/>
                </html:button></div>
        </div>

    </html:form>

</div>