<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'tawContractPayForm'});
    });

    function fucCheck() {
        var r, re;
        re = /^\d*\.?\d{0,10}$/;
        r = document.forms[0].money.value.match(re);
        if (r != document.forms[0].money.value) {
            document.forms[0].money.value = '';
            alert("只能输入数字或者数字与小数点组合！");
        }
        ;
    }

</script>

<html:form action="/tawContractPays.do?method=paysave" styleId="tawContractPayForm" method="post">

    <fmt:bundle basename="config/applicationResource-tawcontractpay">

        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="tawContractPay.form.heading"/></div>
            </caption>

            <tr>
                <td class="label">
                    <fmt:message key="tawContractPay.contractname"/>
                </td>
                <td class="content">
                    <html:text property="contractname" styleId="contractname"
                               styleClass="text medium" readonly="true"
                               alt="allowBlank:false,vtext:'',maxLength:100"/>

                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawContractPay.payer"/>
                </td>
                <td class="content">
                    <html:text property="payer" styleId="payer"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:'',maxLength:100"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawContractPay.paymethod"/>
                </td>
                <td class="content">
                    <html:text property="paymethod" styleId="paymethod"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:'',maxLength:100"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawContractPay.payaccount"/>
                </td>
                <td class="content">
                    <html:text property="payaccount" styleId="payaccount"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:'',maxLength:100"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawContractPay.money"/>
                </td>
                <td class="content">
                    <html:text property="money" styleId="money"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:'',maxLength:50" onblur="fucCheck()"/>
                    (万元)
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawContractPay.paytime"/>
                </td>
                <td class="content">
                    <!--  html:text property="paytime" styleId="paytime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawContractPayForm.paytime}" /-->
                    <input id="paytime" name="paytime" type="text" class="text"
                           readonly="readonly"
                           alt="allowBlank:false,link:'endTime',vtext:''"
                           onclick="popUpCalendar(this, this,null,null,null,false,-1);"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawContractPay.remark"/>
                </td>
                <td class="content">
                    <html:textarea property="remark" styleId="remark"
                                   styleClass="text medium" alt="maxLength:900"
                                   value="${tawContractPayForm.remark}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="tawContractPay.accessory"/>
                </td>
                <td class="content">
                    <eoms:attachment idList="" idField="accessory" appCode="contract"
                                     property="accessory" name="accessory"/>
                </td>
            </tr>
            <html:hidden property="contractid" value="${tawContractPayForm.contractid}"/>
            <html:hidden property="contractname" value="${tawContractPayForm.contractname}"/>
        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
                <!-- <c:if test="${not empty tawContractPayForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/tawContractPay/tawContractPays.do?method=remove&nodeId=${tawContractPayForm.nodeId}';
						location.href=url}"
						/>
			</c:if> -->
            </td>
        </tr>
    </table>


</html:form>

<%@ include file="/common/footer_eoms.jsp" %>