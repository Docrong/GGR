<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">

    function onBack() {
        window.close();
    }

</script>
<html:form action="numberapply.do?method=performBatchSave" styleId="theform" method="post"
           enctype="multipart/form-data">
    <table id="sheet" class="formTable">
        <input type="hidden" name="sheetKey" id="sheetKey" value="${sheetKey}"/>
        <!--附件模板-->
        <c:if test="${actionForword=='hlrnew'}">
            <tr>
                <input type="hidden" name="actionForword" id="actionForword" value="hlrlist"/>
                <td class="label">
                    <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
                </td>
                <td colspan="3">
                    <a href="${app}/accessories/uploadfile/15.2期新建HLR信令点编码申请.xls">15.2期新建HLR信令点编码申请.xls</a>(请按照模板填写)
                </td>
            </tr>
        </c:if>
        <c:if test="${actionForword=='mscnew'}">
        <tr>
            <input type="hidden" name="actionForword" id="actionForword" value="msclist"/>
            <td class="label">
                <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
            </td>
            <td colspan="3">
                <a href="${app}/accessories/uploadfile/15.2期新建端局信令点编码申请.xls">15.2期新建端局信令点编码申请.xls</a>(请按照模板填写)
            </td>
        </tr>
        </c:if
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="mainForm.accessories"/>
            </td>
            <td colspan="3">
                <input id="theFile" name="theFile" type="file" class="file"/>
            </td>
        </tr>
    </table>


    <html:submit styleClass="btn" property="method.save" styleId="method.save">
        批量导入
    </html:submit>
    <input type="button" value="关闭" Onclick="onBack();" class="button">
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>
