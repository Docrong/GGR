<%@ include file="/common/header_tpl_json.jsp" %>
<%@ page import="com.boco.eoms.commons.system.dict.model.TawSystemDictType" %>
<%@ page import="com.boco.eoms.commons.system.role.model.TawSystemSubRole" %>
<%@ page import="com.boco.eoms.commons.system.role.service.IRoleMgr" %>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%
    List dictlist = (List) request.getAttribute("list");
    List subrolelist = (List) request.getAttribute("subrolelist");
    JSONArray json = new JSONArray();
    if (dictlist.size() > 0) {
        int dictLength = dictlist.size();
        for (int i = 0; i < dictLength; i++) {
            TawSystemDictType dict = (TawSystemDictType) dictlist.get(i);
            JSONObject jitem = new JSONObject();
            jitem.put(UIConstants.JSON_ID, dict.getDictId());
            jitem.put(UIConstants.JSON_TEXT, dict.getDictName());
            jitem.put(UIConstants.JSON_NODETYPE, UIConstants.NODETYPE_DICT);
            jitem.put(UIConstants.JSON_ICONCLS, UIConstants.NODETYPE_DICT);
            json.put(jitem);
        }
    }

    if (subrolelist.size() > 0) {
        IRoleMgr subroleMgr = (IRoleMgr) ApplicationContextHolder.getInstance().getBean("RoleMgrImpl");
        int subroleLength = subrolelist.size();
        for (int j = 0; j < subroleLength; j++) {
            TawSystemSubRole subrole = (TawSystemSubRole) subrolelist.get(j);
            JSONObject jitem = new JSONObject();
            jitem.put(UIConstants.JSON_ID, subrole.getId());
            jitem.put(UIConstants.JSON_TEXT, subrole.getSubRoleName());
            jitem.put(UIConstants.JSON_NODETYPE, UIConstants.NODETYPE_SUBROLE);
            jitem.put(UIConstants.JSON_ICONCLS, UIConstants.NODETYPE_SUBROLE);

            String[] leaderInfo = subroleMgr.getRoleLeaderBySubRoleid(subrole.getId());
            if (leaderInfo != null) {
                jitem.put("leaderId", leaderInfo[0]);
                jitem.put("leaderName", leaderInfo[1]);
            }
            json.put(jitem);
        }
    }
    out.print(json);
%>
