package com.boco.eoms.km.exam.webapp.action;

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
import com.boco.eoms.km.exam.mgr.KmExamSpecialtyMgr;
import com.boco.eoms.km.exam.model.KmExamSpecialty;
import com.boco.eoms.km.exam.webapp.form.KmExamSpecialtyForm;
import com.boco.eoms.km.table.mgr.KmTableThemeMgr;
import com.boco.eoms.km.table.model.KmTableTheme;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:专业表
 * </p>
 * <p>
 * Description:专业表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 *
 * @moudle.getAuthor() hsl
 * @moudle.getVersion() 1.0
 */

public final class KmExamSpecialtyAction extends BaseAction {

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
     * 专业表树页面
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
     * 生成专业表树JSON数据
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
        KmExamSpecialtyMgr kmExamSpecialtyMgr = (KmExamSpecialtyMgr) getBean("kmExamSpecialtyMgr");
        // 取下级节点
        List list = kmExamSpecialtyMgr.getNextLevelKmExamSpecialtys(nodeId);
        for (Iterator nodeIter = list.iterator(); nodeIter.hasNext(); ) {
            KmExamSpecialty kmExamSpecialty = (KmExamSpecialty) nodeIter.next();
            JSONObject jitem = new JSONObject();
            jitem.put("id", kmExamSpecialty.getNodeId());
            // TODO 添加节点名称
            jitem.put("text", kmExamSpecialty.getSpecialtyName());
            // 设置右键菜单
            jitem.put("allowChild", true);
            jitem.put("allowEdit", true);
            jitem.put("allowDelete", true);
            // 设置左键点击可触发action
            jitem.put("allowClick", true);
            // 设置是否为叶子节点
            boolean leafFlag = true;
            if (kmExamSpecialtyMgr.isHasNextLevel(kmExamSpecialty.getNodeId())) {
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
        KmExamSpecialtyMgr kmExamSpecialtyMgr = (KmExamSpecialtyMgr) getBean("kmExamSpecialtyMgr");
        // 取下级节点
        List list = kmExamSpecialtyMgr.getNextLevelKmExamSpecialtys(nodeId);
        for (Iterator nodeIter = list.iterator(); nodeIter.hasNext(); ) {
            KmExamSpecialty kmExamSpecialty = (KmExamSpecialty) nodeIter.next();
            JSONObject jitem = new JSONObject();
            jitem.put("id", kmExamSpecialty.getNodeId());
            // TODO 添加节点名称
            jitem.put("text", kmExamSpecialty.getSpecialtyName());
            jitem.put(UIConstants.JSON_NODETYPE, "folder");
            jitem.put("iconCls", "folder");
            jitem.put("qtip", kmExamSpecialty.getSpecialtyName());

            // 设置是否为叶子节点
            boolean leafFlag = true;
            if (kmExamSpecialtyMgr.isHasNextLevel(kmExamSpecialty.getNodeId())) {
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
     * 新增专业表
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
        KmExamSpecialtyForm kmExamSpecialtyForm = (KmExamSpecialtyForm) form;
        kmExamSpecialtyForm.setParentNodeId(nodeId);
        updateFormBean(mapping, request, kmExamSpecialtyForm);
        return mapping.findForward("edit");
    }

    /**
     * 修改专业表
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
        KmExamSpecialtyMgr kmExamSpecialtyMgr = (KmExamSpecialtyMgr) getBean("kmExamSpecialtyMgr");
        KmExamSpecialty kmExamSpecialty = kmExamSpecialtyMgr.getKmExamSpecialtyByNodeId(nodeId);
        KmExamSpecialtyForm kmExamSpecialtyForm = (KmExamSpecialtyForm) convert(kmExamSpecialty);
        updateFormBean(mapping, request, kmExamSpecialtyForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存专业表
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
        KmExamSpecialtyMgr kmExamSpecialtyMgr = (KmExamSpecialtyMgr) getBean("kmExamSpecialtyMgr");
        KmExamSpecialtyForm kmExamSpecialtyForm = (KmExamSpecialtyForm) form;
        KmExamSpecialty kmExamSpecialty = (KmExamSpecialty) convert(kmExamSpecialtyForm);
        kmExamSpecialtyMgr.saveKmExamSpecialty(kmExamSpecialty);
        return mapping.findForward("success");
    }

    /**
     * 删除专业表
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
        KmExamSpecialtyMgr kmExamSpecialtyMgr = (KmExamSpecialtyMgr) getBean("kmExamSpecialtyMgr");
        kmExamSpecialtyMgr.removeKmExamSpecialtyByNodeId(nodeId);
        return mapping.findForward("success");
    }
}