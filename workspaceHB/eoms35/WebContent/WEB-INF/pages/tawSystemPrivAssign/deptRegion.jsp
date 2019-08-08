<%@ include file="/common/taglibs.jsp" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.base.util.StaticVariable" %>
<%@page import="com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager" %>
<%@page import="com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo" %>
<%@page import="com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo" %>
<%@page import="com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion" %>
<%@page import="net.sf.json.JSONArray" %>
<%@page import="net.sf.json.JSONObject" %>
<%@ include file="/common/header_eoms_innerpage.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<%
    String objId = StaticMethod.null2String(request.getParameter("objId"));
    String objType = StaticMethod.null2String(request.getParameter("nodeType"));
    String regionType = StaticVariable.PRIV_TYPE_REGION_DEPT;

    if ("user".equalsIgnoreCase(objType)) {
        objType = StaticVariable.PRIV_ASSIGNTYPE_USER;
    } else if ("subrole".equalsIgnoreCase(objType)) {
        objType = StaticVariable.PRIV_ASSIGNTYPE_ROLE;
    }

    TawSystemAssignBo assignbo = TawSystemAssignBo.getInstance();
    List list = assignbo.getPermissions(objId, objType, regionType);

    TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();

    ArrayList savedRegions = new ArrayList();
    JSONArray json = new JSONArray();

    for (int i = 0; i < list.size(); i++) {
        TawSystemPrivRegion assign = (TawSystemPrivRegion) list.get(i);
        String deptId = assign.getRegionid();
        String deptName = deptbo.getDeptnameBydeptid(deptId);

        JSONObject jitem = new JSONObject();
        jitem.put("id", deptId);
        jitem.put("name", deptName);
        json.put(jitem);
    }
%>
<script type="text/javascript">
    Ext.onReady(function () {
        deptViewer = new Ext.JsonView("deptList",
            '<div  style="width:200">{name}<input type="hidden" name="regionId" value="{id}"></div>',
            {
                emptyText: '<div>${eoms:a2u("没有选择部门")}</div>'
            }
        );
        deptViewer.jsonData = eoms.JSONDecode('<%=json.toString()%>');
        deptViewer.refresh();

        var treeAction = '${app}/xtree.do?method=dept';
        deptTree = new xbox({
            btnId: 'btnDept',
            dlgId: 'hello-dlg2',
            treeDataUrl: treeAction,
            treeRootId: '-1',
            treeRootText: "${eoms:a2u('部门树')}",
            treeChkMode: '',
            treeChkType: 'dept',
            viewer: deptViewer
        });
    });
</script>
<form id="cptRoomForm" method="post" action="${app}/priv/tawSystemPrivAssigns.do?method=xSaveRegionConfig">
    <input type="hidden" name="objId" value="<%=objId%>">
    <input type="hidden" name="objType" value="<%=objType%>">
    <input type="hidden" name="regionType" value="<%=regionType%>">

    <table class="listTable">
        <tr>
            <td class="header"><input type="button" value="${eoms:a2u('请选择部门域')}" id="btnDept" class="btn"></td>
        </tr>
        <tr>
            <td>
                <div id="deptList"></div>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="${eoms:a2u('提交')}" class="btn"/></td>
        </tr>
    </table>

</form>
<%@ include file="/common/footer_eoms_innerpage.jsp" %>
