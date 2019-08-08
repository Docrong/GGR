package com.boco.eoms.km.score.webapp.action;

import java.util.HashMap;
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

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.km.kmOperate.mgr.KmOperateMgr;
import com.boco.eoms.km.kmOperate.model.KmOperate;
import com.boco.eoms.km.score.mgr.KmScoreSetMgr;
import com.boco.eoms.km.score.mgr.KmScoreTreeMgr;
import com.boco.eoms.km.score.mgr.KmScoreWeightMgr;
import com.boco.eoms.km.score.model.KmScoreTree;
import com.boco.eoms.km.score.model.KmScoreWeight;
import com.boco.eoms.km.score.util.KmScoreSetConstants;
import com.boco.eoms.km.score.util.KmScoreWeightConstants;
import com.boco.eoms.km.score.webapp.form.KmScoreWeightForm;

/**
 * <p>
 * Title:积分权重
 * </p>
 * <p>
 * Description:积分权重表
 * </p>
 * <p>
 * Fri Aug 21 09:06:28 CST 2009
 * </p>
 *
 * @moudle.getAuthor() me
 * @moudle.getVersion() 1.0
 */
public final class KmScoreWeightAction extends BaseAction {

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
     * 新增积分权重
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
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
        KmScoreWeightMgr kmScoreWeightMgr = (KmScoreWeightMgr) getBean("kmScoreWeightMgr");
        KmScoreWeight kmScoreWeight = kmScoreWeightMgr.getKmScoreWeightByNodeId(nodeId);

        KmScoreWeightForm kmScoreWeightForm = (KmScoreWeightForm) form;
        kmScoreWeightForm.getId();
        kmScoreWeightForm.setNodeId(nodeId);
        kmScoreWeightForm.setPowerName(kmScoreWeight.getPowerName());
        kmScoreWeightForm.setPowerWeight(kmScoreWeight.getPowerWeight());
        kmScoreWeightForm.setArea(kmScoreWeight.getArea());
        kmScoreWeightForm.setIsDeleted(new Integer("0"));
        updateFormBean(mapping, request, kmScoreWeightForm);
        return mapping.findForward("addedit");
    }

    /**
     * 积分权重树页面
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
        KmScoreWeightMgr kmScoreWeightMgr = (KmScoreWeightMgr) getBean("kmScoreWeightMgr");
//		KmScoreTreeMgr kmScoreTreeMgr = (KmScoreTreeMgr) getBean("kmScoreTreeMgr");
        // 取下级节点
        List list = kmScoreWeightMgr.getNextLevelKmScoreWeights(nodeId, Integer.valueOf(nodeId.length() + 2));

//		KmFileTreeMgr netdiskMgr = (KmFileTreeMgr) getBean("kmFileTreeMgr");
//		List subNodeList = netdiskMgr.getNextLevelKmFileTrees(nodeId);
        JSONArray jsonRoot = new JSONArray();
        if (list == null) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            KmScoreWeight kmScoreWeight = (KmScoreWeight) list.get(i);
            String operateType = StaticMethod.nullObject2String(kmOperateMap.get(kmScoreWeight.getNodeId()));

            if (("".equals(operateType) || operateType.indexOf("N") != -1) && !"admin".equals(operateUserId)) {
                continue;
            }
            JSONObject jitem = new JSONObject();
            jitem.put("id", kmScoreWeight.getNodeId());
            jitem.put("allowClick", true);
            if (kmScoreWeight.getNodeId().length() == 3) {
                jitem.put("text", kmScoreWeight.getPowerName());
                if (operateType.indexOf("W") != -1 || "admin".equals(operateUserId)) {
                    jitem.put("allowChild", true);
                    jitem.put("allowEdit", true);
                    jitem.put("allowDelete", true);
                }
                if (operateType.indexOf("G") != -1 || "admin".equals(operateUserId)) {
                    jitem.put("allowaudnode", true);
                    jitem.put("allowoperate", true);
                }
            } else {
                jitem.put("text", kmScoreWeight.getActionName());
                if (operateType.indexOf("W") != -1 || "admin".equals(operateUserId)) {
                    jitem.put("allowEdit", true);
                    jitem.put("allowDelete", true);
                }
                if (operateType.indexOf("G") != -1 || "admin".equals(operateUserId)) {
                    jitem.put("allowaudnode", true);
                    jitem.put("allowoperate", true);
                }
            }
            // 设置是否叶节点
            List list1 = kmScoreWeightMgr.getNextLevelKmScoreWeights(kmScoreWeight.getNodeId(), Integer.valueOf(kmScoreWeight.getNodeId().length() + 2));
            String leaf = list1.size() + "";
            if (leaf.trim().equals("0")) {
                jitem.put("leaf", true);
            } else {
                jitem.put("leaf", false);
            }
            // 设置图标
            jitem.put("iconCls", "folder");
            jsonRoot.put(jitem);
        }
        return jsonRoot;
    }

    /**
     * 修改积分权重
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
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
        KmScoreWeightMgr kmScoreWeightMgr = (KmScoreWeightMgr) getBean("kmScoreWeightMgr");
        KmScoreWeight kmScoreWeight = kmScoreWeightMgr.getKmScoreWeightByNodeId(nodeId);

        KmScoreWeightForm kmScoreWeightForm = (KmScoreWeightForm) convert(kmScoreWeight);
        kmScoreWeightForm.setIsDeleted(new Integer(0));
        updateFormBean(mapping, request, kmScoreWeightForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存积分权重
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
        KmScoreWeightMgr kmScoreWeightMgr = (KmScoreWeightMgr) getBean("kmScoreWeightMgr");
        KmScoreWeightForm kmScoreWeightForm = (KmScoreWeightForm) form;
        String nodeId = kmScoreWeightForm.getNodeId();
        List list = kmScoreWeightMgr.getNextLevelKmScoreWeights(nodeId, Integer.valueOf(nodeId.length() + 2));
        if (list.size() == 0) {
            nodeId = nodeId + "01";
        } else {
            KmScoreWeight kmScoreWeight = (KmScoreWeight) list.get(list.size() - 1);
            Integer integer = Integer.valueOf(kmScoreWeight.getNodeId());
            int i = integer.intValue() + 1;
            nodeId = new Integer(i).toString();
        }
        boolean isNew = (null == kmScoreWeightForm.getId() || "".equals(kmScoreWeightForm.getId()));
        KmScoreWeight kmScoreWeight = (KmScoreWeight) convert(kmScoreWeightForm);

        kmScoreWeight.setNodeId(nodeId);
        if (isNew) {
            kmScoreWeightMgr.saveKmScoreWeight(kmScoreWeight);
        } else {
            kmScoreWeightMgr.saveKmScoreWeight(kmScoreWeight);
        }
        return mapping.findForward("success");
    }

    public ActionForward editSave(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmScoreWeightMgr kmScoreWeightMgr = (KmScoreWeightMgr) getBean("kmScoreWeightMgr");
        KmScoreWeightForm kmScoreWeightForm = (KmScoreWeightForm) form;
//		String nodeId = kmScoreWeightForm.getNodeId();
//		List list = kmScoreWeightMgr.getNextLevelKmScoreWeights(nodeId,Integer.valueOf(nodeId.length()+2));
//		if(list.size()==0){
//			nodeId = nodeId+"01";
//		}else{
//			KmScoreWeight kmScoreWeight = (KmScoreWeight) list.get(0);
//			Integer integer =Integer.valueOf(kmScoreWeight.getNodeId());
//			int i = integer.intValue()+1;
//			nodeId = new Integer(i).toString();
//		}
        boolean isNew = (null == kmScoreWeightForm.getId() || "".equals(kmScoreWeightForm.getId()));
        KmScoreWeight kmScoreWeight = (KmScoreWeight) convert(kmScoreWeightForm);

//		kmScoreWeight.setNodeId(nodeId);
        if (isNew) {
            kmScoreWeightMgr.saveKmScoreWeight(kmScoreWeight);
        } else {
            kmScoreWeightMgr.saveKmScoreWeight(kmScoreWeight);
        }
        return mapping.findForward("success");
    }

    /**
     * 删除积分权重
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
        KmScoreWeightMgr kmScoreWeightMgr = (KmScoreWeightMgr) getBean("kmScoreWeightMgr");
        KmScoreWeightForm kmScoreWeightForm = (KmScoreWeightForm) form;

        String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
        KmScoreWeight kmScoreWeight = kmScoreWeightMgr.getKmScoreWeightByNodeId(nodeId);
        KmScoreSetMgr kmScoreSetMgr = (KmScoreSetMgr) getBean("kmScoreSetMgr");
        if (nodeId.length() == 3) {
            kmScoreSetMgr.removeKmScoreSetByPowerName(kmScoreWeight.getPowerName());
            kmScoreWeightMgr.removeKmScoreWeightByNodeId(nodeId);
        } else {
            kmScoreSetMgr.removeKmScoreSet(kmScoreWeight.getId());
            kmScoreWeightMgr.removeKmScoreWeightByNodeId(nodeId);
        }
        kmScoreWeightMgr.removeKmScoreWeightByNodeId(nodeId);
        return mapping.findForward("success");
    }

    /**
     * 分页显示积分权重列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String nodeId = StaticMethod.nullObject2String(request.getParameter("nodeId"));
        ;

        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmScoreWeightConstants.KMSCOREWEIGHT_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmScoreWeightMgr kmScoreWeightMgr = (KmScoreWeightMgr) getBean("kmScoreWeightMgr");
        KmScoreWeight kmScoreWeight = kmScoreWeightMgr.getKmScoreWeightByNodeId(nodeId);
        Map map = null;
        if (nodeId.length() == 3) {
            map = (Map) kmScoreWeightMgr.getKmScoreWeights(pageIndex, pageSize, " WHERE kmScoreWeight.isDeleted='0' and kmScoreWeight.powerName='" + kmScoreWeight.getPowerName() + "' and kmScoreWeight.nodeId like '" + nodeId.substring(0, nodeId.length() - 2) + "%'");
        } else {
            map = (Map) kmScoreWeightMgr.getKmScoreWeights(pageIndex, pageSize, " WHERE kmScoreWeight.isDeleted='0' and kmScoreWeight.actionName='" + kmScoreWeight.getPowerName() + "' and kmScoreWeight.nodeId like '" + nodeId.substring(0, nodeId.length() - 2) + "%'");
        }
        List list = (List) map.get("result");
        request.setAttribute("nodeId", nodeId);
        request.setAttribute(KmScoreWeightConstants.KMSCOREWEIGHT_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 分页显示积分权重列表，支持Atom方式接入Portal
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
//	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		try {
//			// --------------用于分页，得到当前页号-------------
//			final Integer pageIndex = new Integer(request
//					.getParameter("pageIndex"));
//			final Integer pageSize = new Integer(request
//					.getParameter("pageSize"));
//			KmScoreWeightMgr kmScoreWeightMgr = (KmScoreWeightMgr) getBean("kmScoreWeightMgr");
//			Map map = (Map) kmScoreWeightMgr.getKmScoreWeights(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			KmScoreWeight kmScoreWeight = new KmScoreWeight();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmScoreWeight = (KmScoreWeight) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmScoreWeight/kmScoreWeights.do?method=edit&id="
//						+ kmScoreWeight.getId() + "' target='_blank'>"
//						+ display name for list + "</a>");
//				entry.setSummary(summary);
//				entry.setContent(content);
//				entry.setLanguage(language);
//				entry.setText(text);
//				entry.setRights(tights);
//				
//				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
//				entry.setUpdated(new java.util.Date());
//				entry.setPublished(new java.util.Date());
//				entry.setEdited(new java.util.Date());
//				
//				// 为person的name属性赋值，entry.addAuthor可以随意赋值
//				Person person = entry.addAuthor(userId);
//				person.setName(userName);
//			}
//			
//			// 每页显示条数
//			feed.setText(map.get("total").toString());
//		    OutputStream os = response.getOutputStream();
//		    PrintStream ps = new PrintStream(os);
//		    feed.getDocument().writeTo(ps);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}