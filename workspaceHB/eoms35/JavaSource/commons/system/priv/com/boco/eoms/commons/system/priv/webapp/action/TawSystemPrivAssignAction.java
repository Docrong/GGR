package com.boco.eoms.commons.system.priv.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dept.model.ItemModel;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.bo.TawSystemPrivAssignOut;
import com.boco.eoms.commons.system.priv.bo.TawSystemUserAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu;
import com.boco.eoms.commons.system.priv.service.IPrivMgr;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager;
import com.boco.eoms.commons.system.priv.webapp.form.TawSystemPrivAssignForm;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawSystemPrivAssign object
 *
 * @struts.action name="tawSystemPrivAssignForm" path="/tawSystemPrivAssigns"
 * scope="request" validate="false" parameter="method"
 * input="mainMenu"
 * @struts.action name="tawSystemPrivAssignForm" path="/editTawSystemPrivAssign"
 * scope="request" validate="false" parameter="method"
 * input="list"
 * @struts.action name="tawSystemPrivAssignForm" path="/saveTawSystemPrivAssign"
 * scope="request" validate="true" parameter="method"
 * input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 * path="/WEB-INF/pages/tawSystemPrivAssign/tawSystemPrivAssignForm.jsp"
 * @struts.action-forward name="list"
 * path="/WEB-INF/pages/tawSystemPrivAssign/tawSystemPrivAssignList.jsp"
 * @struts.action-forward name="search" path="/tawSystemPrivAssigns.html"
 * redirect="true"
 */
public final class TawSystemPrivAssignAction extends BaseAction {
    public ActionForward init(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Logger logger = Logger.getLogger(TawSystemPrivAssignAction.class);
        TawSystemPrivAssign privassign = null;
        List menuList = new ArrayList();
        ITawSystemPrivMenuManager menuMgr = (ITawSystemPrivMenuManager) getBean("ItawSystemPrivMenuManager");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        String currentuserid = sessionform.getUserid();
        TawSystemPrivMenu menu = new TawSystemPrivMenu();
        if (sessionform.isAdmin()) {

            menuList = menuMgr.getTawSystemPrivMenus(menu);
        } else {
            IPrivMgr pirvmgr = (IPrivMgr) getBean("PrivMgrImpl");
            List list = new ArrayList();
            list = pirvmgr.listMenu(currentuserid);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    menu = (TawSystemPrivMenu) list.get(i);
                    menuList.add(menu);
                }
            }

        }
        request.setAttribute(Constants.TAWSYSTEMPRIVMENU_LIST, menuList);
        return mapping.findForward("list");

    }

    // 树结构的删除
    public void xdelete1(ActionMapping mapping, ActionForm form,
                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String id = StaticMethod.null2String(request.getParameter("id"));
        ITawSystemPrivAssignManager mgr = (ITawSystemPrivAssignManager) getBean("ItawSystemPrivAssignManager");
        TawSystemPrivAssign tawSystemPrivAssign = mgr
                .getTawSystemPrivAssign(id);
        // String objectid = "";
        // objectid = tawSystemPrivAssign.getObjectid();
        // TawSystemUserAssignBo userassignbo = TawSystemUserAssignBo
        // .getInstance();
        ITawSystemPrivUserAssignManager userAssignMgr = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivUserAssignManager");
        userAssignMgr.removeAuthorization(tawSystemPrivAssign);
        // if (tawSystemPrivAssign.getAssigntype().equals(
        // StaticVariable.PRIV_ASSIGNTYPE_ROLE)) {
        // TawSystemPrivAssignOut assignout = TawSystemPrivAssignOut
        // .getInstance();
        // mgr.removeTawSystemPrivAssign(id);
        // ITawSystemUserRefRoleManager tawsystemuserRefRoleManager =
        // (ITawSystemUserRefRoleManager)
        // getBean("itawSystemUserRefRoleManager");
        // List list = tawsystemuserRefRoleManager
        // .getUserbyroleid(tawSystemPrivAssign.getObjectid());
        // if (list != null) {
        // for (Iterator it = list.iterator(); it.hasNext();) {
        // TawSystemUser user = (TawSystemUser) it.next();
        // mgr.removePrivassign(user.getUserid());
        // }
        // }
        // assignout.removeRolePriv(objectid);
        // userassignbo.saveObjectPriv(objectid);
        // } else if (tawSystemPrivAssign.getAssigntype().equals(
        // StaticVariable.PRIV_ASSIGNTYPE_USER)) {
        //
        // userassignbo.removeUsermenu(objectid);
        // mgr.removeTawSystemPrivAssign(id);
        // userassignbo.saveObjectPriv(objectid);
        // }
        JSONUtil.success(response, "权限删除成功");
    }

    public void xdelete(ActionMapping mapping, ActionForm form,
                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String userid = saveSessionBeanForm.getUserid();
        String id = StaticMethod.null2String(request.getParameter("id"));
        IPrivMgr pirvmgr = (IPrivMgr) getBean("PrivMgrImpl");
        TawSystemPrivAssign privassign = pirvmgr.getTawSystemPrivAssign(id);
        String menuId = privassign.getPrivid();

        // TawSystemPrivAssignForm tawSystemPrivAssignForm =
        // (TawSystemPrivAssignForm) form;
        // String assigntype = tawSystemPrivAssignForm.getAssigntype();
        // if (assigntype.equals(StaticVariable.PRIV_ASSIGNTYPE_USER)) {}
        if (userid.equals(StaticVariable.ADMIN)) {
            pirvmgr.remove(id);
            JSONUtil.success(response, "权限删除成功");
        } else {
            if (pirvmgr.hasAssigned(userid, menuId)) {
                pirvmgr.remove(id);
                JSONUtil.success(response, "权限删除成功");
            } else {
                JSONUtil.success(response, "权限删除失败，您没有此菜单方案的权限，不能删除此菜单方案");
            }
        }
    }

    /**
     * ajax方式分配权限
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void xsave(ActionMapping mapping, ActionForm form,
                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TawSystemPrivAssignForm tawSystemPrivAssignForm = (TawSystemPrivAssignForm) form;
        String menuids = tawSystemPrivAssignForm.getMenuid();
        ITawSystemPrivAssignManager mgr = (ITawSystemPrivAssignManager) getBean("ItawSystemPrivAssignManager");
        ITawSystemPrivUserAssignManager userAssignMgr = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemPrivUserAssignManager");
        IPrivMgr pirvmgr = (IPrivMgr) getBean("PrivMgrImpl");
        TawSystemPrivAssign tawSystemPrivAssign = (TawSystemPrivAssign) convert(tawSystemPrivAssignForm);
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String operuserid = sessionform.getUserid();
        tawSystemPrivAssign.setOperuserid(operuserid);
        boolean isNew = ("".equals(tawSystemPrivAssignForm.getId()) || tawSystemPrivAssignForm
                .getId() == null);
        boolean bool = false;
        if (isNew) {

            bool = pirvmgr.savePrivAssign(tawSystemPrivAssign);

        } else {
            if (tawSystemPrivAssignForm.getAssigntype().equals(
                    StaticVariable.PRIV_ASSIGNTYPE_USER)) {
                String menuid = tawSystemPrivAssignForm.getPrivid();
                String userid = tawSystemPrivAssignForm.getObjectid();
                if (!menuids.equals(menuid)) {
                    TawSystemUserAssignBo userassignbo = TawSystemUserAssignBo
                            .getInstance();
                    userassignbo.removeUsermenu(tawSystemPrivAssignForm
                            .getObjectid());
                }

                TawSystemPrivAssignOut assignout = TawSystemPrivAssignOut
                        .getInstance();
                assignout.saveuserMenu(userid, menuid);
            } else if (tawSystemPrivAssignForm.getAssigntype().equals(
                    StaticVariable.PRIV_ASSIGNTYPE_ROLE)) {
                TawSystemPrivAssignOut assignout = TawSystemPrivAssignOut
                        .getInstance();
                String menuid = tawSystemPrivAssignForm.getPrivid();
                String roleid = tawSystemPrivAssignForm.getObjectid();
                if (!menuids.equals(menuid)) {
                    assignout.removeRolePriv(roleid);
                }
                assignout.saveRolemenu(roleid, menuid);
            }
            // 若已存在菜单方案赋给某组织，则不做操作
            if (!userAssignMgr.hasAssigned(tawSystemPrivAssign.getPrivid(),
                    tawSystemPrivAssign.getObjectid(), tawSystemPrivAssign
                            .getAssigntype())) {
                mgr.saveTawSystemPrivAssign(tawSystemPrivAssign);
            }

        }
        if (bool)
            JSONUtil.success(response, "权限分配成功");
        else
            JSONUtil.success(response, "权限分配失败");

    }

    public ActionForward xsearch(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'xsearch' method");
        }

        TawSystemUser tawSystemUser = new TawSystemUser();
        String _strDictId = request.getParameter("type");
        ITawSystemUserManager mgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
        ITawSystemRoleManager rolemgr = (ITawSystemRoleManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemRoleManager");
        List _objDictTypeList = new ArrayList();
        List list = new ArrayList();

        ItemModel itemtree = null;
        if (_strDictId.equals("1101102")) {

            list = mgr.getTawSystemUsers(tawSystemUser);
            if (list != null) {
                TawSystemUser systemuser = new TawSystemUser();

                for (int i = 0; i < list.size(); i++) {
                    itemtree = new ItemModel();
                    systemuser = (TawSystemUser) list.get(i);
                    itemtree.setValue(systemuser.getUserid());
                    itemtree.setText(systemuser.getUsername());
                    _objDictTypeList.add(itemtree);
                }
            }
        } else if (_strDictId.equals("1101103")) {
            TawSystemRole systeRole = new TawSystemRole();
            list = rolemgr.getTawSystemRoles();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    itemtree = new ItemModel();
                    systeRole = (TawSystemRole) list.get(i);
                    itemtree.setValue(String.valueOf(systeRole.getRoleId()));
                    itemtree.setText(systeRole.getRoleName());
                    _objDictTypeList.add(itemtree);
                }

            }
        }
        // 创建JSON对象
        JSONObject jsonRoot = new JSONObject();

        // 将查询结果的行数放入JSON对象中
        jsonRoot.put("results", _objDictTypeList.size());

        // 将查询记录转换为JSON数组放入JSON对象中
        jsonRoot.put("rows", JSONArray.fromObject(_objDictTypeList));

        response.setContentType("text/xml;charset=UTF-8");

        // 返回JSON对象
        response.getWriter().print(jsonRoot.toString());
        return null;
    }

    /**
     * ajax请求获取某节点的详细信息。 mios 070724
     */
    public String xget(ActionMapping mapping, ActionForm form,
                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemPrivAssignForm tawSystemPrivAssignForm = (TawSystemPrivAssignForm) form;
        String _strId = request.getParameter("id");

        TawSystemAssignBo assignbo = TawSystemAssignBo.getInstance();
        TawSystemPrivAssign privAssign = new TawSystemPrivAssign();
        privAssign = (TawSystemPrivAssign) assignbo.getObjectPriv(_strId);
        request.setAttribute("menuid", privAssign.getPrivid());
        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(privAssign);
        tawSystemPrivAssignForm.setMenuid(privAssign.getPrivid());
        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(jsonRoot.toString());
        return null;
    }

    /**
     * 用户树
     */
    public void getUserTreeNodes(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String nodeId = request.getParameter("node");
        TawSystemTreeBo treebo = TawSystemTreeBo.getInstance();
        JSONArray jsonRoot = treebo.getDeptUserTreeForPriv(nodeId);
        JSONUtil.print(response, jsonRoot.toString());
    }

    /**
     * 角色树
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void getRoleTreeNodes(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String nodeId = request.getParameter("node");
        TawSystemTreeBo treebo = TawSystemTreeBo.getInstance();
        JSONArray jsonRoot = treebo.getRoleAndSubroleTreeForPriv(nodeId);
        JSONUtil.print(response, jsonRoot.toString());
    }

    /**
     * 根据OBJECTID查询对应的权限
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void getAssign(ActionMapping mapping, ActionForm form,
                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String type = StaticMethod.null2String(request.getParameter("type"));
        String objectid = StaticMethod.null2String(request
                .getParameter("objectid"));
        IPrivMgr pirvmgr = (IPrivMgr) getBean("PrivMgrImpl");

        JSONObject jsonroot = new JSONObject();
        jsonroot = pirvmgr.getJSONObjectPriv(type, objectid);
        JSONUtil.print(response, jsonroot.toString());
    }

    public ActionForward xSaveRegionConfig(ActionMapping mapping,
                                           ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        try {
            String objId = StaticMethod.null2String(request
                    .getParameter("objId"));

            String objType = StaticMethod.null2String(request
                    .getParameter("objType"));

            String[] arrRegionId = request.getParameterValues("regionId");
            String regionType = StaticMethod.null2String(request
                    .getParameter("regionType"));

            TawSystemAssignBo assignbo = TawSystemAssignBo.getInstance();
            if (objType.equals(StaticVariable.PRIV_ASSIGNTYPE_ROLE)) {

            }
            assignbo.savePrivRegions(objId, objType, arrRegionId, regionType);
        } catch (Exception e) {
            BocoLog.error(this, "保存域信息时报错:" + e.getMessage());
            return mapping.findForward("fail");
        }
        return mapping.findForward("success");
    }

}
