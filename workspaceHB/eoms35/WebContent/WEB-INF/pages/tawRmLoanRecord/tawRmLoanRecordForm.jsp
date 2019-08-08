<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ include file="/common/xtreelibs.jsp" %>
<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmLoanRecordDetail.title"/></title>
<content tag="heading"><fmt:message key="tawRmLoanRecordDetail.heading"/></content>

<%
    String recordId = (String) request.getParameter("id");
    String returnTime = (String) request.getAttribute("returnTime");
    String articleName = request.getParameter("tmpArticleName");
    String piece = request.getParameter("tmpPiece");
    String borrowerName = request.getParameter("tmpBorrowerName");
    String loanTime = request.getParameter("tmpLoanTime");
    String tmpReturnTime = request.getParameter("tmpReturnTime");
    String roomId = request.getParameter("roomId");
    String startTime = request.getParameter("startTime");
    String endTime = request.getParameter("endTime");
%>
<script language="javascript">

    function confirmDelete() {
        if (confirm('${eoms:a2u("是否要删除此借出记录")}')) {
            return true;
        } else {
            return false;
        }
    }

    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'tawRmLoanRecordForm'});
    });
</script>

<!--对表单的自动生成的处�?-->
<html:form action="tawRmLoanRecord" method="post" styleId="tawRmLoanRecordForm">
    <ul>

        <!--表示对所有的域有�? -->
        <html:hidden property="id"/>
        <html:hidden property="userId"/>
        <html:hidden property="workSerial"/>
        <html:hidden property="tmpArticleName"/>
        <html:hidden property="tmpPiece"/>
        <html:hidden property="tmpBorrowerName"/>
        <html:hidden property="tmpLoanTime"/>
        <html:hidden property="tmpReturnTime"/>
        <html:hidden property="roomId"/>
        <html:hidden property="startTime"/>
        <html:hidden property="endTime"/>
        <br>


        <table class="formTable">
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLoanRecordForm.articleName"/>
                </td>
                <td width="500" colspan="2">
                    <%if (recordId == null) {%>
                    <html:text property="articleName" styleId="articleName"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:'${eoms:a2u('请输入物品名称')}'"/>
                    <%} else {%>
                    <html:text property="articleName" styleId="articleName"
                               styleClass="text medium" readonly="true"/>
                    <%}%>
                </td>
            </tr>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmArticleForm.articleType"/>
                </td>
                <td width="500" colspan="2">
                    <%if (recordId == null) {%>
                    <html:text property="articleType" styleId="articleType"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:'${eoms:a2u('请输入物品类型')}'"/>
                    <%} else {%>
                    <html:text property="articleType" styleId="articleType"
                               styleClass="text medium" readonly="true"/>
                    <%}%>
                </td>
            </tr>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLoanRecordForm.piece"/>
                </td>
                <td width="500" colspan="2">
                    <%if (recordId == null) {%>
                    <html:text property="piece" styleId="piece"
                               styleClass="text medium"
                               alt="allowBlank:false,vtype:'number'"/>
                    <%} else {%>
                    <html:text property="piece" styleId="piece"
                               styleClass="text medium" readonly="true"/>
                    <%}%>
                </td>
            </tr>

            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLoanRecordForm.borrowerName"/>
                </td>
                <td width="500" colspan="2">
                    <%if (recordId == null) {%>
                    <html:text property="borrowerName" styleId="borrowerName"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:'${eoms:a2u('请输入借物人姓名')}'"/>
                    <%} else {%>
                    <html:text property="borrowerName" styleId="borrowerName"
                               styleClass="text medium" readonly="true"/>
                    <%}%>
                </td>
            </tr>

            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLoanRecordForm.loanTime"/>
                </td>
                <td width="500" colspan="2">
                    <%if (recordId == null) {%>
                    <html:text property="loanTime" styleId="loanTime"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:'${eoms:a2u('请输入物品借出时间')}'"
                               onclick="popUpCalendar(this, this);" readonly="true"/>
                    <%} else {%>
                    <html:text property="loanTime" styleId="loanTime"
                               styleClass="text medium" readonly="true"/>
                    <%}%>
                </td>
            </tr>
            <%if ((recordId != null) && (returnTime == null || returnTime.equals(""))) {%>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLoanRecordForm.returnTime"/>
                </td>
                <td width="500" colspan="2">
                    <html:text property="returnTime" styleId="returnTime"
                               styleClass="text medium"
                               onclick="popUpCalendar(this, this);" readonly="true"/>
                </td>
            </tr>
            <%} else if ((recordId != null) && (returnTime != null && !returnTime.equals(""))) {%>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLoanRecordForm.returnTime"/>
                </td>
                <td width="500" colspan="2">
                    <html:text property="returnTime" styleId="returnTime"
                               styleClass="text medium" readonly="true"/>
                </td>
            </tr>
            <%}%>
        </table>

        <br>
        <table>
            <tr>
                <%
                    if (returnTime == null || returnTime.equals("")) {
                %>
                <td>
                    <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
                        <fmt:message key="button.save"/>
                    </html:submit>
                </td>
                <%
                    }
                %>
                <%
                    if ((recordId != null) && (returnTime == null || returnTime.equals(""))) {
                %>
                <td>
                    <html:submit styleClass="button" property="method.delete"
                                 onclick="bCancel=true; return confirmDelete()">
                        <fmt:message key="button.delete"/>
                    </html:submit>
                </td>

                <%
                    }
                %>

                <%
                    if (recordId != null) {
                %>
                <td>
                    <html:submit styleClass="button" property="method.searchList2">
                        <fmt:message key="button.back"/>
                    </html:submit>
                </td>
                <%
                    }
                %>
            </tr>
        </table>
    </ul>
    <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp" %>