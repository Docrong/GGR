package com.boco.eoms.km.ask.webapp.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.km.ask.mgr.KmAskSpecialityMgr;
import com.boco.eoms.km.ask.model.KmAskSpeciality;
import com.boco.eoms.km.ask.webapp.form.KmAskSpecialityForm;
import com.boco.eoms.km.train.mgr.TrainSpecialtyMgr;
import com.boco.eoms.km.train.model.TrainSpecialty;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:问答类型
 * </p>
 * <p>
 * Description:问答类型
 * </p>
 * <p>
 * Tue Aug 04 15:17:03 CST 2009
 * </p>
 *
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */

public final class KmAskSpecialityAction extends BaseAction {

    /**
     * 未指定方法时默认调用的方法
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return tree(mapping, form, request, response);
    }

    /**
     * 问答类型树页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward tree(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("tree");
    }

    /**
     * 生成问答类型树JSON数据
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String getNodes(ActionMapping mapping, ActionForm form,
                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String nodeId = StaticMethod.null2String(request.getParameter("node"));
        JSONArray jsonRoot = new JSONArray();
        KmAskSpecialityMgr kmAskSpecialityMgr = (KmAskSpecialityMgr) getBean("kmAskSpecialityMgr");
        // 取下级节点
        List list = kmAskSpecialityMgr.getNextLevelKmAskSpecialitys(nodeId);
        for (Iterator nodeIter = list.iterator(); nodeIter.hasNext(); ) {
            KmAskSpeciality kmAskSpeciality = (KmAskSpeciality) nodeIter.next();
            JSONObject jitem = new JSONObject();
            jitem.put("id", kmAskSpeciality.getNodeId());
            // TODO 添加节点名称
            jitem.put("text", kmAskSpeciality.getName());
            // 设置右键菜单
            jitem.put("allowChild", true);
            jitem.put("allowEdit", true);
            jitem.put("allowDelete", true);
            // 设置左键点击可触发action
            jitem.put("allowClick", true);
            // 设置是否为叶子节点
            boolean leafFlag = true;
            if (kmAskSpecialityMgr.isHasNextLevel(kmAskSpeciality.getNodeId())) {
                leafFlag = false;
            }
            jitem.put("leaf", leafFlag);
            // TODO 设置鼠标悬浮提示
            //jitem.put("qtip", your tips here);
            jsonRoot.put(jitem);
        }
        JSONUtil.print(response, jsonRoot.toString());
        return null;
    }

    /**
     * 获取模型分类树(第一层)
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void getNodesRadioTree(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String nodeId = StaticMethod.null2String(request.getParameter("node"));
        JSONArray jsonRoot = new JSONArray();
        KmAskSpecialityMgr kmAskSpecialityMgr = (KmAskSpecialityMgr) getBean("kmAskSpecialityMgr");
        // 取下级节点
        List list = kmAskSpecialityMgr.getNextLevelKmAskSpecialitys(nodeId);
        for (Iterator nodeIter = list.iterator(); nodeIter.hasNext(); ) {
            KmAskSpeciality kmAskSpeciality = (KmAskSpeciality) nodeIter.next();
            JSONObject jitem = new JSONObject();
            jitem.put("id", kmAskSpeciality.getNodeId());
            // TODO 添加节点名称
            jitem.put("text", kmAskSpeciality.getName());
            jitem.put(UIConstants.JSON_NODETYPE, "folder");
            jitem.put("iconCls", "folder");
            jitem.put("qtip", kmAskSpeciality.getName());

            // 设置是否为叶子节点
            boolean leafFlag = true;
            if (kmAskSpecialityMgr.isHasNextLevel(kmAskSpeciality.getNodeId())) {
                leafFlag = false;
            }
            jitem.put("leaf", leafFlag);
            // TODO 设置鼠标悬浮提示
            //jitem.put("qtip", your tips here);
            jitem.put("checked", false);
            jsonRoot.put(jitem);
        }
        //JSONUtil.print(response, jsonRoot.toString());
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(jsonRoot.toString());
    }

    /**
     * 新增问答类型
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String nodeId = StaticMethod.null2String(request.getParameter("node"));
        KmAskSpecialityForm kmAskSpecialityForm = (KmAskSpecialityForm) form;
        kmAskSpecialityForm.setParentNodeId(nodeId);
        updateFormBean(mapping, request, kmAskSpecialityForm);
        return mapping.findForward("edit");
    }

    /**
     * 修改问答类型
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
        String nodeId = StaticMethod.null2String(request.getParameter("node"));
        KmAskSpecialityMgr kmAskSpecialityMgr = (KmAskSpecialityMgr) getBean("kmAskSpecialityMgr");
        KmAskSpeciality kmAskSpeciality = kmAskSpecialityMgr.getKmAskSpecialityByNodeId(nodeId);
        KmAskSpecialityForm kmAskSpecialityForm = (KmAskSpecialityForm) convert(kmAskSpeciality);
        updateFormBean(mapping, request, kmAskSpecialityForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存问答类型
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
        KmAskSpecialityMgr kmAskSpecialityMgr = (KmAskSpecialityMgr) getBean("kmAskSpecialityMgr");
        KmAskSpecialityForm kmAskSpecialityForm = (KmAskSpecialityForm) form;
        KmAskSpeciality kmAskSpeciality = (KmAskSpeciality) convert(kmAskSpecialityForm);
        kmAskSpeciality.setCreateUser(this.getUser(request).getUserid());
        kmAskSpeciality.setCreateDept(this.getUser(request).getDeptid());
        kmAskSpeciality.setIsDelete("0");
        kmAskSpeciality.setCreateTime(StaticMethod.getLocalTime());
        kmAskSpecialityMgr.saveKmAskSpeciality(kmAskSpeciality);
        return mapping.findForward("success");
    }

    /**
     * 删除问答类型
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward remove(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
        KmAskSpecialityMgr kmAskSpecialityMgr = (KmAskSpecialityMgr) getBean("kmAskSpecialityMgr");
        kmAskSpecialityMgr.removeKmAskSpecialityByNodeId(nodeId);
        return mapping.findForward("success");
    }
}