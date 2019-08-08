<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    switcher = new detailSwitcher();
    switcher.init({
        container: 'showInvokeRelationShipList',
        handleEl: 'div.history-item-title'
    });
</script>

<div id="showInvokeRelationShipList" class="panel">
    <div class="history-item" width="100%"><!-- add space to hack ie-->&nbsp;

        <div class="history-item-content">
            <table class="listTable" width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td class="label">
                        <bean:message bundle="sheet" key="task.sheetId"/>
                    </td>
                    <td class="label">
                        <bean:message bundle="sheet" key="process.invoke.list.sheetSubject"/>
                    </td>
                    <td class="label">
                        <bean:message bundle="sheet" key="process.invoke.list.invokedSheetSubject"/>
                    </td>
                    <td class="label">
                        <bean:message bundle="sheet" key="process.invoke.list.invokeStep"/>
                    </td>
                    <td class="label">
                        <bean:message bundle="sheet" key="process.invoke.list.sheetName"/>
                    </td>
                    <td class="label">
                        <bean:message bundle="sheet" key="task.status"/>
                    </td>

                </tr>
                <logic:present name="showInvokeRelationShipList">
                    <logic:iterate id="showInvokeRelationShip" name="showInvokeRelationShipList"
                                   type="com.boco.eoms.sheet.complaint.model.ComplaintMain" scope="request">
                        <tr>
                            <td nowrap="nowrap">
                                <a href="${app}/sheet/complaint/complaint.do?method=showDetailPage&sheetKey=${showInvokeRelationShip.id}"
                                   target="_blank">${showInvokeRelationShip.sheetId}</a>
                            </td>
                            <td nowrap="nowrap">
                                    ${proccessName}
                            </td>
                            <td nowrap="nowrap">
                                    ${invokedproccessName}
                            </td>
                            <td nowrap="nowrap">
                                <eoms:dict key="dict-sheet-commonfault" dictId="activeTemplateId"
                                           itemId="${activeTemplateId}" beanId="id2descriptionXML"/>
                            </td>
                            <td nowrap="nowrap">
                                    ${showInvokeRelationShip.title}
                            </td>
                            <td nowrap="nowrap">
                                <eoms:dict key="dict-sheet-common" dictId="sheetStatus"
                                           itemId="${showInvokeRelationShip.status}" beanId="id2descriptionXML"/>
                            </td>
                        </tr>
                    </logic:iterate>
                </logic:present>
            </table>
        </div>
    </div>

</div>