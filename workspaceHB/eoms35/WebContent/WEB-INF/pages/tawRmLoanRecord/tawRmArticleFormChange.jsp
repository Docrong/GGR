<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<fmt:bundle basename="config/ApplicationResources-duty">
    <html:form action="tawRmArticle.do?" method="post" styleId="tawRmArticleForm">
        <!--表示对所有的域有-->
        <html:hidden property="id"/>
        <table class="formTable middle" align="center">
            <tr>
                <td align="center" colspan="2">
                    <h2>
                        <eoms:label styleClass="desc" key="tawRmArticleForm.manage"/>
                    </h2>
                </td>
            </tr>
            <tr>
                <td class="label" align="right">
                    <eoms:label styleClass="desc" key="tawRmArticleForm.articleName"/>
                </td>
                <td>
                    <html:errors property="articleName"/>
                    <html:text property="articleName" styleId="articleName"
                               styleClass="text medium" readonly="true"></html:text>
                </td>
            </tr>
            <tr>
                <td class="label" align="right">
                    <eoms:label styleClass="desc" key="tawRmArticleForm.articleType"/>
                </td>
                <td>
                    <html:errors property="articleType"/>
                    <html:text property="articleType" styleId="articleType"
                               styleClass="text medium" readonly="true"></html:text>
                </td>
            </tr>
            <tr>
                <td class="label" align="right">
                    <eoms:label styleClass="desc" key="tawRmArticleForm.allNum"/>
                </td>
                <td>
                    <html:errors property="allNum"/>
                    <html:text property="allNum" styleId="allNum"
                               styleClass="text medium"
                               alt="allowBlank:false,vtype:'number',vtext:'${eoms:a2u('请输入物品数量')}'"></html:text>
                </td>
            </tr>
            <tr>
                <td class="label" align="right">
                    <eoms:label styleClass="desc" key="tawRmArticleForm.describe"/>
                </td>
                <td>
                    <html:errors property="describe"/>
                    <html:textarea property="describe" styleId="describe"
                                   styleClass="text medium" cols="30" rows="4" readonly="true"></html:textarea>
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <html:submit styleClass="button" property="method.xadd"
                                 onclick="bCancel=false">
                        <fmt:message key="tawRmArticleForm.add"/>
                    </html:submit>
                    &nbsp;&nbsp;
                    <html:reset styleClass="button" onclick="bCancel=true">
                        <fmt:message key="button.reset"/>
                    </html:reset>
                    &nbsp;&nbsp;
                    <html:submit styleClass="button" property="method.xminus"
                                 onclick="bCancel=false">
                        <fmt:message key="tawRmArticleForm.minus"/>
                    </html:submit>
                </td>
            </tr>
        </table>
    </html:form>
    <!--自动生成的Javascript脚本-->
    <script type="text/javascript">
        Ext.onReady(function () {
            v = new eoms.form.Validation({form: 'TawRmArticleForm'});
        });

        function backtoList() {
            document.forms[0].action = "outerLinkmans.do?method=returnList";
            //document.forms.method = "POST";
            document.forms[0].submit();
        }
    </script>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp" %>
