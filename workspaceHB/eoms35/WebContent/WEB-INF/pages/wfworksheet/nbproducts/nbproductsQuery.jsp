<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    switcher = new detailSwitcher();
    switcher.init({
        container: 'nbproducts',
        handleEl: 'div.history-item-title'
    });
</script>
<bean:define id="url" value="nbproductss.do"/>
<content tag="heading">${eoms:a2u('关联工单信息')}</content>
<div id="nbproducts" class="panel">
    <div class="history-item" width="100%"><!-- add space to hack ie-->&nbsp;

        <div class="history-item-content">
            <table class="listTable" width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td class="label">
                        ${eoms:a2u('工单流水号')}
                    </td>
                    <td class="label ">
                        ${eoms:a2u('工单主题')}
                    </td>
                    <td class="label">
                        ${eoms:a2u('受理时限')}
                    </td>
                    <td class="label">
                        ${eoms:a2u('完成时限')}
                    </td>
                    <td class="label">
                        ${eoms:a2u('工单状态')}
                    </td>

                </tr>
                <logic:present name="planSheets">
                    <logic:iterate id="planSheet" name="planSheets"
                                   type="com.boco.eoms.sheet.nbproducts.webapp.form.NBProductsForm" scope="request">
                        <tr>
                            <td nowrap="nowrap">
                                    ${planSheet.mainProductType }
                            </td>
                        </tr>
                    </logic:iterate>
                </logic:present>
            </table>

        </div>
    </div>

</div>