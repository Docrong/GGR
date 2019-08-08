<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<script type="text/javascript">

    var checkflag = "false";

    function choose() {
        var objs = document.getElementsByName("ids");
        if (checkflag == "false") {
            for (var i = 0; i < objs.length; i++) {
                if (objs[i].type.toLowerCase() == "checkbox")
                    objs[i].checked = true;
                checkflag = "true";
            }
        } else if (checkflag == "true") {
            for (var i = 0; i < objs.length; i++) {
                if (objs[i].type.toLowerCase() == "checkbox")
                    objs[i].checked = false;
                checkflag = "false"
            }
        }
    }

    function ConfirmCheckBox() {
        var flag = false;
        var objs = document.getElementsByName("ids");
        for (var i = 0; i < objs.length; i++) {
            if (objs[i].type.toLowerCase() == "checkbox")
                if (objs[i].checked) {
                    flag = true;
                }
        }
        if (flag) {
            return true;
        } else {
            alert("<bean:message key='testCardForm.tips.nochoose'/>");
            return false;
        }
    }

</script>
<html:form action="tawRmInoutRecords" method="post" styleId="tawRmInoutRecordForm">
    <content tag="heading"><fmt:message key="tawRmTestcardList.heading"/></content>

    <display:table name="tawRmTestcardList" cellspacing="0" cellpadding="0"
                   id="tawRmTestcardList" pagesize="${pageSize }" class="table tawRmTestcardList"
                   export="true" sort="list" partialList="true" size="resultSize"
                   requestURI="${app}/testcard/tawRmInoutRecords.do?method=outEdit"
                   decorator="com.boco.eoms.otherwise.displaytag.support.TestCardListDisplaytagDecorator">

        <display:column property="id" title="<input type='checkbox' onclick='javascript:choose();'/>"/>

        <display:column property="iccid" sortable="true" headerClass="sortable"
                        titleKey="tawRmTestcardForm.iccid"/>

        <display:column property="msisdn" sortable="true" headerClass="sortable"
                        titleKey="tawRmTestcardForm.msisdn"/>

        <display:column property="imsi" sortable="true" headerClass="sortable"
                        titleKey="tawRmTestcardForm.imsi"/>

        <display:column property="pin" sortable="true" headerClass="sortable"
                        titleKey="tawRmTestcardForm.pin"/>

        <display:column property="puk" sortable="true" headerClass="sortable"
                        titleKey="tawRmTestcardForm.puk"/>

        <display:column property="openAccountDate" sortable="true" headerClass="sortable"
                        titleKey="tawRmTestcardForm.openAccountDate"/>

        <display:column property="logoutDate" sortable="true" headerClass="sortable"
                        titleKey="tawRmTestcardForm.logoutDate"/>

        <display:column property="takeOverDate" sortable="true" headerClass="sortable"
                        titleKey="tawRmTestcardForm.takeOverDate"/>

        <display:column property="state" sortable="true" headerClass="sortable"
                        titleKey="tawRmTestcardForm.state"/>

        <display:column property="oldNumber" sortable="true" headerClass="sortable"
                        titleKey="tawRmTestcardForm.oldNumber"/>

        <display:column property="supplyer" sortable="true" headerClass="sortable"
                        titleKey="tawRmTestcardForm.supplyer"/>

        <display:column property="userId" sortable="true" headerClass="sortable"
                        titleKey="tawRmTestcardForm.userId"/>

        <display:setProperty name="paging.banner.item_name" value="tawRmTestcard"/>
        <display:setProperty name="paging.banner.items_name" value="tawRmTestcards"/>
    </display:table>
    <br>
    <html:submit styleClass="button" property="method.toOutStorage" onclick="return ConfirmCheckBox()">
        <fmt:message key="button.outStorage"/>
    </html:submit>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>

