
package com.boco.eoms.sheet.modifytime.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.base.util.ConvertUtil;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.sheet.modifytime.model.ModifyTime;
import com.boco.eoms.sheet.modifytime.service.IModifyTimeManager;
import com.boco.eoms.sheet.modifytime.webapp.form.ModifyTimeForm;

import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;
import com.boco.eoms.sheet.tool.access.webapp.form.TawSheetAccessForm;

import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a ModifyTime object
 *
 * @struts.action name="modifytimeForm" path="/modifytimes" scope="request"
 * validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="main"
 * path="/WEB-INF/pages/modifytime/modifytimeTree.jsp" contextRelative="true"
 * @struts.action-forward name="edit"
 * path="/WEB-INF/pages/modifytime/modifytimeForm.jsp" contextRelative="true"
 * @struts.action-forward name="list"
 * path="/WEB-INF/pages/modifytime/modifytimeList.jsp" contextRelative="true"
 */
public final class ModifyTimeAction extends BaseAction {
    public ActionForward main(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IModifyTimeManager mgr = (IModifyTimeManager) getBean("ImodifytimeManager");
        List modifytimeList = mgr.getModifyTimes();
        request.setAttribute("modifytimeList", modifytimeList);
        request.setAttribute("isAdd", StaticMethod.nullObject2String(request.getParameter("isAdd")));
        request.setAttribute("isEdit", StaticMethod.nullObject2String(request.getParameter("isEdit")));
        request.setAttribute("isDelete", StaticMethod.nullObject2String(request.getParameter("isDelete")));
        return mapping.findForward("list");
    }

    public ActionForward xsave(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ModifyTimeForm modifytimeForm = (ModifyTimeForm) form;

        IModifyTimeManager mgr = (IModifyTimeManager) getBean("ImodifytimeManager");
        ModifyTime modifytime = (ModifyTime) convert(modifytimeForm);
        mgr.saveModifyTime(modifytime);
        return xquery(mapping, form, request, response);
    }

    public ActionForward xdelete(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ModifyTimeForm modifytimeForm = (ModifyTimeForm) form;

        IModifyTimeManager mgr = (IModifyTimeManager) getBean("ImodifytimeManager");
        mgr.removeModifyTime(modifytimeForm.getId());
        request.setAttribute("operateType", "3");
        return xquery(mapping, form, request, response);

    }

    public ActionForward xeditinit(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModifyTimeForm modifytimeForm = (ModifyTimeForm) form;
        TawSystemSessionForm sessionForm = this.getUser(request);
        String curUserId = sessionForm.getUserid();
        String id = modifytimeForm.getId();
        if (id != null && !id.equals("")) {
            IModifyTimeManager mgr = (IModifyTimeManager) getBean("ImodifytimeManager");
            ModifyTime modifytime = mgr.getModifyTime("id");
            //when the Creater is not the Editer or the form has reported,edit should be forbid
            //if(!curUserId.equals(modifytime.getCreater() || "1".equals(modifytime.getStatus())) {
//			if(!curUserId.equals(modifytime.getCreater())) {
//				//璺宠浆鍒版棤鏉冮檺椤甸潰
//				return mapping.findForward("nopriv");
//			} else {
            modifytimeForm = (ModifyTimeForm) convert(modifytime);
            request.setAttribute("modifytimeForm", modifytimeForm);
            //璺宠浆鍒颁慨鏀归〉闈?
            return mapping.findForward("edit");
            //}
        }
        return mapping.findForward("error");
    }

    public ActionForward xedit(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModifyTimeForm modifytimeForm = (ModifyTimeForm) form;
        TawSystemSessionForm sessionForm = this.getUser(request);
        String curUserId = sessionForm.getUserid();
        String id = modifytimeForm.getId();
        if (id != null && !id.equals("")) {
            IModifyTimeManager mgr = (IModifyTimeManager) getBean("ImodifytimeManager");
            ModifyTime modifyTime = mgr.getModifyTime(modifytimeForm.getId());
            modifytimeForm = (ModifyTimeForm) ConvertUtil.populateObject(modifytimeForm, modifyTime);
            request.setAttribute("modifyTimeForm", modifytimeForm);
            //updateFormBean(mapping, request, modifytimeForm);
            request.setAttribute("isAdd", StaticMethod.nullObject2String(request.getParameter("isAdd")));
            request.setAttribute("isEdit", StaticMethod.nullObject2String(request.getParameter("isEdit")));
            request.setAttribute("isDelete", StaticMethod.nullObject2String(request.getParameter("isDelete")));
            request.setAttribute("operateType", "1");
            return mapping.findForward("edit");
        }

        return mapping.findForward("error");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward xquery(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModifyTimeForm modifytimeForm = (ModifyTimeForm) form;
        IModifyTimeManager mgr = (IModifyTimeManager) getBean("ImodifytimeManager");
        //String lanStartIp = modifytimeForm.getLanStartIp();
        StringBuffer condition = new StringBuffer("from ModifyTime where 1=1");
        //鏍规嵁瀹為檯鎯呭喌缁勫悎鏉′欢
        //if(lanStartIp != null && !lanStartIp.equals("")) {
        //	condition.append(" and lanStartIp like '%"+lanStartIp+"%'");
        //}
        condition.append(" and endTime >" + StaticMethod.getDateString(0));
        List modifytimeList = mgr.getModifyTimesByCondition(condition.toString());
        request.setAttribute("modifytimeList", modifytimeList);
        request.setAttribute("isAdd", StaticMethod.nullObject2String(request.getParameter("isAdd")));
        request.setAttribute("isEdit", StaticMethod.nullObject2String(request.getParameter("isEdit")));
        request.setAttribute("isDelete", StaticMethod.nullObject2String(request.getParameter("isDelete")));
        return mapping.findForward("list");
    }

    /**
     *
     */
    public void xget(ActionMapping mapping, ActionForm form,
                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String _strId = request.getParameter("id");
        IModifyTimeManager mgr = (IModifyTimeManager) getBean("ImodifytimeManager");
        ModifyTime modifytime = mgr.getModifyTime(_strId);

        JSONObject jsonRoot = new JSONObject();
        jsonRoot = JSONObject.fromObject(modifytime);

        JSONUtil.print(response, jsonRoot.toString());
    }

    public ActionForward report(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String _strId = request.getParameter("id");
        IModifyTimeManager mgr = (IModifyTimeManager) getBean("ImodifytimeManager");
        ModifyTime modifytime = mgr.getModifyTime(_strId);

        //report the form,condition:reportTime must be before 10th at next month
        return mapping.findForward("main");
    }

    public ActionForward approve(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String _strId = request.getParameter("id");
        IModifyTimeManager mgr = (IModifyTimeManager) getBean("ImodifytimeManager");
        ModifyTime modifytime = mgr.getModifyTime(_strId);

        //approve the form
        return mapping.findForward("main");
    }

    /**
     *
     */
    public void xGetChildNodes(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String nodeId = request.getParameter("node");

        IModifyTimeManager mgr = (IModifyTimeManager) getBean("ImodifytimeManager");
        JSONArray json = mgr.xGetChildNodes(nodeId);

        JSONUtil.print(response, json.toString());
    }

    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
            throws Exception {
        //return mapping.findForward("search");
        return null;
    }
}
