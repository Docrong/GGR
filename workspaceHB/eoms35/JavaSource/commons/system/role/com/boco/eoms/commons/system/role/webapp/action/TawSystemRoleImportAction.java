package com.boco.eoms.commons.system.role.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.system.role.model.TawSystemRoleImport;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemRoleImportForm;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawSystemRoleImport object
 *
 * @struts.action name="tawSystemRoleImportForm" path="/tawSystemRoleImports"
 * scope="request" validate="false" parameter="method"
 * input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="main"
 * path="/WEB-INF/pages/tawSystemRoleImport/tawSystemRoleImportTree.jsp"
 * contextRelative="true"
 * @struts.action-forward name="edit"
 * path="/WEB-INF/pages/tawSystemRoleImport/tawSystemRoleImportForm.jsp"
 * contextRelative="true"
 * @struts.action-forward name="list"
 * path="/WEB-INF/pages/tawSystemRoleImport/tawSystemRoleImportList.jsp"
 * contextRelative="true"
 */
public final class TawSystemRoleImportAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // return mapping.findForward("search");
        return null;
    }

    public ActionForward main(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("main");
    }

    /**
     * 根据父节点的id得到�?有子节点的JSON数据
     */
    public void xGetChildNodes(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String nodeId = request.getParameter("node");

        ITawSystemRoleImportManager mgr = (ITawSystemRoleImportManager) getBean("tawSystemRoleImportManager");
        JSONArray json = mgr.xGetChildNodes(nodeId);

        JSONUtil.print(response, json.toString());
    }

    /**
     * 保存
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TawSystemRoleImportForm tawSystemRoleImportForm = (TawSystemRoleImportForm) form;
        if (null == tawSystemRoleImportForm.getAccessoriesId() || "".equals(tawSystemRoleImportForm.getAccessoriesId())) {
            request.setAttribute("noAccessories", "请上传需要导入的文件！");
            return edit(mapping, form, request, response);
        } else {
            request.setAttribute("noAccessories", "");
        }
        ITawSystemRoleImportManager mgr = (ITawSystemRoleImportManager) getBean("tawSystemRoleImportManager");
        TawSystemRoleImport tawSystemRoleImport = (TawSystemRoleImport) convert(tawSystemRoleImportForm);
        tawSystemRoleImport.setVersionAt(new Date());
        mgr.saveTawSystemRoleImport(tawSystemRoleImport);
        return forwardlist(mapping);
    }

    /**
     * 转向历史列表
     *
     * @param mapping
     * @return
     */
    public ActionForward forwardlist(ActionMapping mapping) {
        ActionForward forward = mapping.findForward("forwardlist");
        String path = forward.getPath();
        return new ActionForward(path, false);
    }

    /**
     * 根据模块或功能的编码，删除该对象
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TawSystemRoleImportForm tawSystemRoleImportForm = (TawSystemRoleImportForm) form;

        ITawSystemRoleImportManager mgr = (ITawSystemRoleImportManager) getBean("tawSystemRoleImportManager");
        mgr.removeTawSystemRoleImport(tawSystemRoleImportForm.getId());
        return forwardlist(mapping);

    }

    /**
     * ajax请求修改某节点的详细信息
     */
    public String xedit(ActionMapping mapping, ActionForm form,
                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemRoleImportForm tawSystemRoleImportForm = (TawSystemRoleImportForm) form;

        if (tawSystemRoleImportForm.getId() != null) {
            ITawSystemRoleImportManager mgr = (ITawSystemRoleImportManager) getBean("tawSystemRoleImportManager");
            TawSystemRoleImport tawSystemRoleImport = (TawSystemRoleImport) convert(tawSystemRoleImportForm);

            mgr.saveTawSystemRoleImport(tawSystemRoleImport);
            // mgr.updateTawSystemRoleImport(tawSystemRoleImport);
        }

        return null;
    }

    /**
     * ajax请求获取某节点的详细信息
     */
    public void xget(ActionMapping mapping, ActionForm form,
                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String _strId = request.getParameter("id");
        ITawSystemRoleImportManager mgr = (ITawSystemRoleImportManager) getBean("tawSystemRoleImportManager");
        TawSystemRoleImport tawSystemRoleImport = mgr
                .getTawSystemRoleImport(_strId);

        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(tawSystemRoleImport);

        JSONUtil.print(response, jsonRoot.toString());
    }

    /**
     * 初使化页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("edit");
    }

    /**
     * 显示列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 版块id
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                "tawSystemRoleImportList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        // 每页显示条数
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        ITawSystemRoleImportManager mgr = (ITawSystemRoleImportManager) getBean("tawSystemRoleImportManager");
        // 取某版块未阅读记录列表
        Map map = (Map) mgr.getTawSystemRoleImports(pageIndex, pageSize);

        List list = (List) map.get("result");
        request.setAttribute("tawSystemRoleImportList", list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");

    }

    /**
     * 导入版本
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward importrole(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Integer sheet = new Integer(0);
        ITawSystemRoleImportManager mgr = (ITawSystemRoleImportManager) getBean("tawSystemRoleImportManager");
        TawSystemRoleImport tawSystemRoleImport = mgr
                .getTawSystemRoleImport(request.getParameter("id"));

        String filePath = AccessoriesMgrLocator
                .getTawCommonsAccessoriesManagerCOS().getFilePath(
                        RoleConstants.ROLE_ACCESSORIES_APP_ID);
        // 符件保存格式为'1234535.xls'，去掉单引号
        String fileName = tawSystemRoleImport.getAccessoriesId().replace("'",
                "");
        Map map = mgr.mappingRoleExcel(filePath + fileName);
        List list = (List) map.get(sheet);
        if (map == null || !map.containsKey(sheet) || list == null || list.isEmpty()) {
            // 导入失败
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "TawSystemRoleImportAction.import.error"));
            saveMessages(request, messages);
            return mapping.findForward("fail");
        }


//		mgr.importRole(list, tawSystemRoleImport);
        //------------2009-5-13 -----
        String info = mgr.importRole1(list, tawSystemRoleImport);
        if (!info.equals("")) {
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "TawSystemRoleImportAction.import.existInfo", info));
            saveMessages(request.getSession(), messages);
            request.setAttribute("message", info);
            return mapping.findForward("failAsExist");
        }
        return mapping.findForward("success");
    }

    /**
     * 恢复版本
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward restore(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ITawSystemRoleImportManager mgr = (ITawSystemRoleImportManager) getBean("tawSystemRoleImportManager");
        TawSystemRoleImport tawSystemRoleImport = mgr
                .getTawSystemRoleImport(request.getParameter("id"));
        mgr.restoreRole(tawSystemRoleImport.getVersion());
        return mapping.findForward("success");
    }
//	public ActionForward toExport(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//
//		return mapping.findForward("export");
//	}
}
