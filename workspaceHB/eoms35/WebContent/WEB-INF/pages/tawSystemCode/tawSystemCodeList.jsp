<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<script type="text/javascript">

    var temp = true;

    function checkAll() {
        var idd = document.getElementsByName("idd");
        if (temp == true) {
            for (var i = 0; i < idd.length; i++) {
                if (idd[i].disabled == false) {
                    idd[i].checked = true;
                }
            }
            temp = false;
        } else {
            for (var i = 0; i < idd.length; i++) {
                if (idd[i].disabled == false) {
                    idd[i].checked = false;
                }
            }
            temp = true;
        }

    }


    function ConfirmDel() {
        var msg = "${eoms:a2u('确定删除吗？')}";
        if (confirm(msg) == true) {
            return true;
        } else {
            return false;
        }
    }

    function ConfirmApprove() {
        var msg = "${eoms:a2u('确定上报？')}";
        if (confirm(msg) == true) {
            return true;
        } else {
            return false;
        }
    };

    function showDetail(link) {
        var par = "height=350,width=650,status=yes,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes";
        var yingjiDrillDetail = window.open(link, '', par);
        yingjiDrillDetail.focus();
    };

    function ConfirmApproveButton() {
        var msg = "${eoms:a2u('确定批量上报？')}";
        if (confirm(msg) == true) {
            document.forms[0].submit();
        } else {
            return false;
        }
    };
    var temp = true;

    function checkAll() {
        var idd = document.getElementsByName("idd");
        if (temp == true) {
            for (var i = 0; i < idd.length; i++) {
                if (idd[i].disabled == false) {
                    idd[i].checked = true;
                }
            }
            temp = false;
        } else {
            for (var i = 0; i < idd.length; i++) {
                if (idd[i].disabled == false) {
                    idd[i].checked = false;
                }
            }
            temp = true;
        }
    }

    function frsubmit() {
        document.fr.submit();
    }

    function newPage() {
        document.forms[0].action = "safeServiceNotes.do?method=newPage";
        document.forms[0].submit();
    }
</script>
<content tag="heading">
    <center><H2><bean:message key="tawSystemCodeForm.list"/></H2></center>
</content>
<form action="/codes.do?method=xquery" method="post">
    <fmt:bundle basename="config/ApplicationResources-role">


    <display:table name="tawSystemCodeList" cellspacing="0" cellpadding="0"
                   id="tawSystemCodeList" pagesize="10" class="table tawSystemCodeList"
                   requestURI="${app}/tawSystemCode/codes.do?method=main" sort="external" size="resultSize"
    >
    <logic:present name="tawSystemCodeList" property="id">

        <display:column property="name"
                        headerClass="sortable" titleKey="tawSystemCodeForm.mingcheng"/>
        <display:column property="code"
                        headerClass="sortable" titleKey="tawSystemCodeForm.bianma"/>

    <display:column headerClass="sortable" paramId="id"
                    paramProperty="id" titleKey="tawSystemCodeForm.chakan">
    <html:link href="${app}/tawSystemCode/codes.do?method=xview"
               paramId="id" paramProperty="id" paramName="tawSystemCodeList"
               onclick="showDetail(this);return false;">
        <bean:message key="tawSystemCodeForm.chakan"/>
    </html:link>
    </display:column>
    <display:column headerClass="sortable" paramId="id"
                    paramProperty="id" titleKey="tawSystemCodeForm.xiugai">
    <html:link href="${app}/tawSystemCode/codes.do?method=edit"
               paramId="id" paramProperty="id" paramName="tawSystemCodeList">
        <bean:message key="tawSystemCodeForm.xiugai"/>
    </html:link>
    </display:column>
    <display:column headerClass="sortable" titleKey="tawSystemCodeForm.shanchu">
    <html:link href="${app}/tawSystemCode/codes.do?method=xdelete"
               paramId="id" paramProperty="id" paramName="tawSystemCodeList"
               onclick="javascript:return ConfirmDel();">
        <bean:message key="tawSystemCodeForm.shanchu"/>
    </html:link>
    </display:column>
    </logic:present>
    </display:table>

    </fmt:bundle>
    <%@ include file="/common/footer_eoms.jsp" %>

