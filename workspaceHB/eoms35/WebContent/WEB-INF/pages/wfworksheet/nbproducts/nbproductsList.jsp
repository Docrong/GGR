<%@ include file="/common/extlibs.jsp" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<!-- 查询控制 -->
<% String ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("ifReference"));
    if (ifReference.equals(""))
        ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifReference"));
%>
<logic:notPresent name="ifReference" scope="request">
    <% if (!ifReference.equals("")) {
        request.setAttribute("ifReference", ifReference);
    }
    %>
</logic:notPresent>
<!-- 返回控制 -->
<% String back = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("back")); %>
<logic:notPresent name="back" scope="request">
    <% if (!back.equals("")) {
        request.setAttribute("back", back);
    }
    %>
</logic:notPresent>
<script type="text/javascript">
    function check() {
        var templateIds = document.getElementsByName("procode");
        var nbPName = document.getElementsByName("nbPName");
        var i = 0;
        var templateId = "";
        var nName = "";
        for (i = 0; i < templateIds.length; i++) {
            if (templateIds[i].checked) {
                templateId = templateIds[i].value;
                nName = nbPName[i].value;
            }
        }
        if (templateId == "") {
            alert("${eoms:a2u('未选择，请确认后引用')} ");
            return false;
        } else {
            opener.inputProCode(templateId, nName);
            window.close();
        }
    }

    function query() {
        var formObj = document.forms[0];

        if ('${ifReference}' != null && '${ifReference}' != '') {
            formObj.action = "nbproductss.do?method=xsearch&ifReference=true&back=true&deleted=0";
        } else {
            formObj.action = "nbproductss.do?method=xsearch&back=true&deleted=0";
        }
        formObj.submit();
    }

    function deleted() {
        document.location.href = "nbproductss.do?method=xqueryDeleted&ifReference=${ifReference}";
    }

    function onkeydownEvt(evt) {
        evt = evt ? evt : (window.event ? window.event : null);
        if (evt.keyCode == 13) {
            query();
            return false;
        }
    }
</script>
<html:form action="nbproductss.do?method=xsearch" method="post" styleId="nbproductsForm">
    <table height="35" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td nowrap>
                <content tag="heading">${eoms:a2u('新业务产品目录库')}</content>
            </td>
            <td nowrap><a href="#" onclick="deleted()">${eoms:a2u('进入已删除产品目录库')}</a></td>
            <td width="380"></td>
            <td nowrap width="100" align="middle" style="vertical-align: middle">${eoms:a2u('请输入产品关键字')}</td>
            <td width="130" align="left">
                <input type="text" id="txtwords" name="txtwords" class="headinpout2" maxlength="20"
                       onkeydown="onkeydownEvt(event);"/>
                <input type="hidden" name="${sheetPageName}txtwords" id="${sheetPageName}txtwords"
                       value="${sheetPageName}txtwords"/>
                <input type="hidden" name="${sheetPageName}ifReference" id="${sheetPageName}ifReference"
                       value="<%=ifReference%>"/>
            </td>
            <td>
                <input id="btnSearch" type="button" align="right" name="button" onclick="query();"
                       value="<fmt:message key="button.query"/>">
                </input>
            </td>
        </tr>
    </table>

    <bean:define id="url" value="nbproductss.do"/>
    <display:table name="nbproductsList" cellspacing="0" cellpadding="0"
                   id="nbproducts" pagesize="15" class="table nbproductsList"
                   export="true" requestURI="/sheet/nBProducts/nbproductss.do?method=showDetail" sort="list">

        <display:column media="html">
            <input type="radio" name="procode" value="${nbproducts.procode}"/>
            <input type="hidden" name="nbPName" value="${nbproducts.nbPName}"/>
        </display:column>
        <display:column property="procode" sortable="true" headerClass="sortable"
                        url="/sheet/nBProducts/nbproductss.do?method=showDetail&flag=showDetail&ifReference=${ifReference}"
                        paramId="id" paramProperty="id" title="${eoms:a2u('产品编号')}"/>
        <display:column property="nbPName" sortable="true" headerClass="sortable"
                        url="/sheet/nBProducts/nbproductss.do?method=showDetail" paramId="id" paramProperty="id"
                        title="${eoms:a2u('新产品名称')}"/>
        <display:column sortable="true" headerClass="sortable" title="${eoms:a2u('业务分类')}">
            <eoms:id2nameDB id="${nbproducts.businessSort}" beanId="ItawSystemDictTypeDao"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="${eoms:a2u('产品名称')}">
            <eoms:id2nameDB id="${nbproducts.businessSortTwo}" beanId="ItawSystemDictTypeDao"/>
        </display:column>

        <display:column property="keyword" sortable="true" headerClass="sortable"
                        url="/sheet/nBProducts/nbproductss.do?method=showDetail" paramId="id" paramProperty="id"
                        title="${eoms:a2u('产品关键字')}"/>
        <display:column sortable="true" headerClass="sortable"
                        title="${eoms:a2u('记录人')}">
            <eoms:id2nameDB id="${nbproducts.recorder}" beanId="tawSystemUserDao"/>
        </display:column>
        <display:column property="recordTime" sortable="true" headerClass="sortable"
                        url="/sheet/nBProducts/nbproductss.do?method=showDetail" paramId="id" paramProperty="id"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}"
                        title="${eoms:a2u('记录时间')}"/>
        <display:setProperty name="paging.banner.item_name" value="nbproducts"/>
        <display:setProperty name="paging.banner.items_name" value="nbproductss"/>
        <display:setProperty name="export.rtf" value="false"/>
        <display:setProperty name="export.pdf" value="false"/>
        <display:setProperty name="export.xml" value="false"/>
        <display:setProperty name="export.csv" value="false"/>

    </display:table>
    <tr class="buttonBar bottom">
        <logic:present name="ifReference" scope="request">
            <input type="button" styleClass="button" value="${eoms:a2u('引用')}" onclick="check();">
        </logic:present>
        <input type="button" styleClass="button"
               onclick="location.href='<c:url
                       value="/sheet/nBProducts/nbproductss.do?method=xedit&type=add&ifReference=${ifReference}"/>'"
               value="${eoms:a2u('添加')}"/>
        <logic:present name="back" scope="request">
            <input type="button" styleClass="button"
                   onclick="location.href='<c:url
                           value="/sheet/nBProducts/nbproductss.do?method=xquery&ifReference=${ifReference}"/>'"
                   value="${eoms:a2u('返回')}"/>
        </logic:present>
    </tr>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>


