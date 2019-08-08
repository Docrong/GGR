<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpNetVO" %>

<script language="javascript">

    Ext.onReady(function () {
        colorRows('list-table');
    })

    function onNetAdd(titleId) {
        location.href = "netadd.do";
    }

</script>

<%
    TawwpNetVO tawwpNetVO = null;
    List list = (List) request.getAttribute("netlist");
%>

<table border="0" width="760" class="listTable" id="list-table">
    <caption><bean:message key="netquerylist.title.formTitle"/></caption>
    <thead>
    <tr>
        <td width="80">
            <bean:message key="netquerylist.title.formNetName"/>
        </td>
        <td width="120">
            <bean:message key="netquerylist.title.formSerialNo"/>
        </td>
        <td width="60">
            <bean:message key="netquerylist.title.formNetType"/>
        </td>
        <td width="100">
            <bean:message key="netquerylist.title.formRoomName"/>
        </td>
        <td width="180">
            <bean:message key="netquerylist.title.formDeptName"/>
        </td>
        <td width="60">
            <bean:message key="netquerylist.title.formVendor"/>
        </td>
        <td width="50">
            <bean:message key="netquerylist.title.formEdit"/>
        </td>
        <td width="50">
            <bean:message key="netquerylist.title.formRemove"/>
        </td>
    </tr>
    </thead>
    <tbody>
    <%
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                tawwpNetVO = (TawwpNetVO) list.get(i);
    %>
    <tr>
        <td width="80">
            <%=tawwpNetVO.getName()%>
        </td>
        <td width="120">
            <%=tawwpNetVO.getSerialNo()%>
        </td>
        <td width="60">
            <%=tawwpNetVO.getNetTypeName()%>
        </td>
        <td width="100">
            <%=tawwpNetVO.getRoomName()%>
        </td>
        <td width="180">
            <%=tawwpNetVO.getDeptName()%>
        </td>
        <td width="60">
            <%=tawwpNetVO.getVendor()%>
        </td>
        <td width="50">
            <a href="netedit.do?netid=<%=tawwpNetVO.getId()%>">
                <img src="${app }/images/icons/edit.gif" width="19" height="19">
            </a>
        </td>
        <td width="50">
            <a href="netremove.do?netid=<%=tawwpNetVO.getId()%>">
                <img src="${app }/images/icons/icon.gif" width="25" height="25">
            </a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td width="700" colspan="8">
            <bean:message key="netquerylist.title.nolist"/>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>


<%@ include file="/common/footer_eoms.jsp" %>
