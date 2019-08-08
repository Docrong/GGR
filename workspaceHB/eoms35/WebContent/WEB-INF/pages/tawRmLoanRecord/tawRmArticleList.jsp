<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<fmt:bundle basename="config/ApplicationResources-duty">

    <display:table name="tawRmArticleList" cellspacing="0" cellpadding="0"
                   id="tawRmArticleList" pagesize="12" class="table tawRmArticleList"
                   requestURI="/duty/tawRmArticle.do?method=xquery" sort="external" size="resultSize">

        <display:column property="articleName" titleKey="tawRmArticleForm.articleName"/>

        <display:column property="articleType" titleKey="tawRmArticleForm.articleType"/>

        <display:column property="onlineNum" titleKey="tawRmArticleForm.onlineNum"/>


        <display:column property="loanNum" titleKey="tawRmArticleForm.loanNum"/>

        <display:column property="allNum" titleKey="tawRmArticleForm.allNum"/>

        <display:column titleKey="tawRmArticleForm.search">
            <html:link href='${app}/duty/tawRmArticle.do?method=xdetail' paramId="id" paramProperty="id"
                       paramName="tawRmArticleList" target="_blank">
                <fmt:message key="tawRmArticleForm.search"/>
            </html:link>
        </display:column>

        <display:column titleKey="tawRmArticleForm.loan">
            <html:link href='${app}/duty/tawRmArticle.do?method=xloan ' paramId="id" paramProperty="id"
                       paramName="tawRmArticleList">
                <fmt:message key="tawRmArticleForm.loan"/>
            </html:link>
        </display:column>

        <display:column titleKey="tawRmArticleForm.change">
            <html:link href="${app}/duty/tawRmArticle.do?method=xchange" paramId="id" paramProperty="id"
                       paramName="tawRmArticleList">
                <bean:message key="tawRmArticleForm.change"/>
            </html:link>
        </display:column>

    </display:table>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp" %>

