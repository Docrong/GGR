<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<table class="formTable" width="80%" align="center">
    <tr>
        <td colspan="2" align="center" class="label">
            ${eoms:a2u('个人首页')}
        </td>
    </tr>
    <tr>
        <td align="center">
            <a href="${app}/help/index/index.ppt">${eoms:a2u('培训手册')}</a>
        </td>
        <td align="center">
            <a href="${app}/help/index/index.doc">${eoms:a2u('使用手册')}</a>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="label">
            ${eoms:a2u('组织管理')}
        </td>
    </tr>
    <tr>
        <td align="center">
            <a href="${app}/help/orgmgr/orgmgr.ppt">${eoms:a2u('培训手册')}</a>
        </td>
        <td align="center">
            <a href="${app}/help/orgmgr/orgmgr.doc">${eoms:a2u('使用手册')}</a>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="label">
            ${eoms:a2u('任务工单')}
        </td>
    </tr>
    <tr>
        <td align="center">
            <a href="${app}/help/sheets/task/task.ppt">${eoms:a2u('培训手册')}</a>
        </td>
        <td align="center">
            <a href="${app}/help/sheets/task/task.doc">${eoms:a2u('使用手册')}</a>
        </td>
    </tr>

    <tr>
        <td colspan="2" align="center" class="label">
            ${eoms:a2u('作业计划')}
        </td>
    </tr>
    <tr>
        <td align="center">
            <a href="${app}/help/workplan/workplan.ppt">${eoms:a2u('培训手册')}</a>
        </td>
        <td align="center">
            <a href="${app}/help/workplan/workplan.doc">${eoms:a2u('使用手册')}</a>
        </td>
    </tr>

    <tr>
        <td colspan="2" align="center" class="label">
            ${eoms:a2u('值班管理')}
        </td>
    </tr>
    <tr>
        <td align="center">
            <a href="${app}/help/duty/duty.ppt">${eoms:a2u('培训手册')}</a>
        </td>
        <td align="center">
            <a href="${app}/help/duty/duty.doc">${eoms:a2u('使用手册')}</a>
        </td>
    </tr>
    <tr>
        <td align="center" colspan="2">
            <font color="red"> ${eoms:a2u('如遇到系统使用问题请联系管理员！') }</font>
        </td>
    </tr>
</table>
<%@ include file="/common/footer_eoms.jsp" %>