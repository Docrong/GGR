package com.boco.eoms.km.file.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.km.file.mgr.KmFileMgr;
import com.boco.eoms.km.file.mgr.KmFileTreeMgr;
import com.boco.eoms.km.file.model.KmFileTree;
import com.boco.eoms.km.file.webapp.form.KmFileTreeForm;
import com.boco.eoms.km.kmAuditer.mgr.KmAuditerMgr;
import com.boco.eoms.km.kmAuditer.model.KmAuditer;
import com.boco.eoms.km.kmAuditer.webapp.form.KmAuditerForm;
import com.boco.eoms.km.kmOperate.mgr.KmOperateMgr;
import com.boco.eoms.km.kmOperate.model.KmOperate;

/**
 * <p>
 * Title:文件管理树
 * </p>
 * <p>
 * Description:文件管理树
 * </p>
 * <p>
 * Wed Mar 25 17:09:37 CST 2009
 * </p>
 *
 * @moudle.getAuthor() eoms
 * @moudle.getVersion() 1.0
 */

public final class KmFileTreeAction extends BaseAction {

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
     * 文件管理树树页面
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
     * 新增文件管理树
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
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String nodeId = StaticMethod.null2String(request.getParameter("node"));
        KmFileTreeForm kmFileTreeForm = (KmFileTreeForm) form;
        kmFileTreeForm.setId("");
        kmFileTreeForm.setParentNodeId(nodeId);
        kmFileTreeForm.setUserId(sessionform.getUserid());
        kmFileTreeForm.setCreateTime(StaticMethod.getCurrentDateTime());
        updateFormBean(mapping, request, kmFileTreeForm);
        return mapping.findForward("edit");
    }

    /**
     * 修改文件管理树
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
        KmFileTreeMgr kmFileTreeMgr = (KmFileTreeMgr) getBean("kmFileTreeMgr");
        KmFileTree kmFileTree = kmFileTreeMgr.getKmFileTreeByNodeId(nodeId);
        KmFileTreeForm kmFileTreeForm = (KmFileTreeForm) convert(kmFileTree);
        updateFormBean(mapping, request, kmFileTreeForm);
        return mapping.findForward("edit");
    }

    /**
     * 审核人配置
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward audit(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String nodeId = StaticMethod.null2String(request.getParameter("node"));
        KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
        request.setAttribute("nodeId", nodeId);
        request.setAttribute("auditType", "file");
        KmAuditer kmAuditer = kmAuditerMgr.getKmAuditerByNodeid(nodeId);
        KmAuditerForm kmAuditerForm = (KmAuditerForm) convert(kmAuditer);
        request.setAttribute("kmAuditerForm", kmAuditerForm);
        return mapping.findForward("audit");
    }

    /**
     * 保存文件管理树
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
        KmFileTreeMgr kmFileTreeMgr = (KmFileTreeMgr) getBean("kmFileTreeMgr");
        KmFileTreeForm kmFileTreeForm = (KmFileTreeForm) form;
        KmFileTree kmFileTree = (KmFileTree) convert(kmFileTreeForm);

//		kmFileTree.setCreateTime(StaticMethod.getCurrentDateTime());
//		TawSystemSessionForm tawSystemSessionForm=this.getUser(request);
//		kmFileTree.setUserId(tawSystemSessionForm.getUserid());

        kmFileTreeMgr.saveKmFileTree(kmFileTree);

        return mapping.findForward("success");
    }

    /**
     * 删除文件管理树
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
        String userId = this.getUserId(request);
        if ("admin".equals(userId)) {
            String nodeId = StaticMethod.null2String(request.getParameter("node"));

            //删除当前节点
            KmFileTreeMgr kmFileTreeMgr = (KmFileTreeMgr) getBean("kmFileTreeMgr");
            KmFileTree treeNode = kmFileTreeMgr.getKmFileTreeByNodeId(nodeId);
            kmFileTreeMgr.removeKmFileTreeByNodeId(nodeId);

            //删除当前节点急子节点的文件
            KmFileMgr kmFileMgr = (KmFileMgr) getBean("kmFileMgr");
            kmFileMgr.moveFileByNode(treeNode.getNodeId(), treeNode.getParentNodeId());

            return mapping.findForward("success");
        }
        return mapping.findForward("nopriv");
    }

    /**
     * 生成文件夹树JSON数据
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
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String nodeId = request.getParameter("node");
        BocoLog.debug(this, "Init nodeId is:" + request.getParameter("node"));
        BocoLog.debug(this, "recovered nodeId is:" + nodeId);
        JSONArray jsonRoot = new JSONArray();
        jsonRoot = getSubNodes(nodeId, sessionform);
        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(jsonRoot.toString());
        return null;
    }

    /**
     * 生成文件夹树JSON数据
     */
    private JSONArray getSubNodes(String nodeId, TawSystemSessionForm sessionform) {
        //权限管理
        String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();

        // 部门权限
        KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");
        List kmOperateToDeptList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("file", operateDeptId, "dept");
        Map kmOperateMap = new HashMap();
        for (int i = 0; i < kmOperateToDeptList.size(); i++) {
            KmOperate kmOperate = (KmOperate) kmOperateToDeptList.get(i);
            kmOperateMap.put(kmOperate.getNodeId(), kmOperate.getOperateType());
        }
        // 人员权限
        List kmOperateToUserList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("file", operateUserId, "user");
        for (int i = 0; i < kmOperateToUserList.size(); i++) {
            KmOperate kmOperate = (KmOperate) kmOperateToUserList.get(i);
            kmOperateMap.put(kmOperate.getNodeId(), kmOperate.getOperateType());
        }

        KmFileTreeMgr netdiskMgr = (KmFileTreeMgr) getBean("kmFileTreeMgr");
        List subNodeList = netdiskMgr.getNextLevelKmFileTrees(nodeId);

        JSONArray jsonRoot = new JSONArray();
        if (subNodeList == null) {
            return null;
        }

        for (int i = 0; i < subNodeList.size(); i++) {
            KmFileTree treeNode = (KmFileTree) subNodeList.get(i);
            String operateType = StaticMethod.nullObject2String(kmOperateMap.get(treeNode.getNodeId()));
            if ("".equals(operateType) && "1".equals(treeNode.getHasParentOperate())) {
                String parentId = treeNode.getParentNodeId();
                operateType = StaticMethod.nullObject2String(kmOperateMap.get(parentId));
            }
            if (("".equals(operateType) || operateType.indexOf("N") != -1) && !"admin".equals(operateUserId)) {
                continue;
            }
            JSONObject jitem = new JSONObject();
            jitem.put("id", treeNode.getNodeId());
            jitem.put("text", treeNode.getNodeName());
            jitem.put("allowClick", true);
            if (operateType.indexOf("W") != -1 || "admin".equals(operateUserId)) {
                jitem.put("allowChild", true);
                jitem.put("allowEdit", true);
                jitem.put("allowDelete", true);
            }
            if (operateType.indexOf("G") != -1 || "admin".equals(operateUserId)) {
                jitem.put("allowaudnode", true);
                jitem.put("allowoperate", true);
            }
            // 设置是否叶节点
            String leaf = treeNode.getLeaf();
            if (leaf.trim().equals("0")) {
                jitem.put("leaf", false);
            } else {
                jitem.put("leaf", true);
            }
            // 设置图标
            jitem.put("iconCls", "folder");
            jsonRoot.put(jitem);
        }
        return jsonRoot;
    }
}