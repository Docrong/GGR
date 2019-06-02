<%@ include file="/portal/common/taglibs-portal.jsp"%>
<tiles:insert page="/portal/layout/jstlLayoutAjax.jsp" flush="true">
    <tiles:put name="header" value="/portal/common/jstlHeader.jsp" />
    <tiles:put name="footer" value="/portal/common/jstlFooter.jsp" />
    <tiles:put name="menu"   value="/portal/common/jstlMenu.jsp" />
    <tiles:put name="body"   value="/portal/indexAjax.jsp" />
</tiles:insert>
