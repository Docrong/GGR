<%@ include file="/common/header_tpl_json.jsp" %>
<%@page import="com.boco.eoms.commons.system.dept.model.TawSystemDept" %>
<%@page import="com.boco.eoms.commons.system.user.model.TawSystemUser" %>
<%@page import="com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo" %>
<%@page import="com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo" %>
<%
    List deptlist = (List) request.getAttribute("deptlist");
    List userlist = (List) request.getAttribute("userlist");
    JSONArray json = new JSONArray();

    TawSystemUserRoleBo userrolebo = TawSystemUserRoleBo.getInstance();
    TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();

    if (deptlist.size() > 0) {
        for (int i = 0; i < deptlist.size(); i++) {
            TawSystemDept dept = (TawSystemDept) deptlist.get(i);
            String deptId = dept.getDeptId();
            String deptName = dept.getDeptName();
            String isPartners = dept.getIsPartners();

            JSONObject jitem = new JSONObject();
            jitem.put(UIConstants.JSON_ID, deptId);
            jitem.put(UIConstants.JSON_TEXT, deptName);
            jitem.put(UIConstants.JSON_NODETYPE, "1".equals(isPartners) ? "partner-dept" : "dept");
            jitem.put("allowChild", true);
            jitem.put("allowDelete", false);
            jitem.put("allowEdit", false);
            jitem.put("isPartners", isPartners);
            if ("1".equals(isPartners)) {
                jitem.put("iconCls", "partner-dept");
            } else {
                jitem.put("iconCls", "dept");
            }

            //判断是否还有子节点
            List flaguser = userrolebo.getUserBydeptids(deptId);
            List flagdept = deptbo.getNextLevecDepts(deptId, "0");
            if (flagdept == null || flagdept.size() <= 0) {
                if (flaguser == null || flaguser.size() <= 0) {
                    jitem.put("leaf", 1);
                }
            } else {
                jitem.put("leaf", 0);
            }
            json.put(jitem);
        }
    }

    if (userlist.size() > 0) {
        for (int j = 0; j < userlist.size(); j++) {
            TawSystemUser user = (TawSystemUser) userlist.get(j);

            JSONObject jitem = new JSONObject();
            jitem.put(UIConstants.JSON_ID, user.getId());
            jitem.put(UIConstants.JSON_TEXT, user.getUsername());
            jitem.put(UIConstants.JSON_NODETYPE, "user");
            jitem.put("mobile", user.getMobile());
            jitem.put("iconCls", "user");
            jitem.put("leaf", 1);
            jitem.put("allowChild", false);
            jitem.put("allowDelete", true);
            jitem.put("allowEdit", true);
            json.put(jitem);
        }
    }
    out.print(json);
%>

