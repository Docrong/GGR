package com.boco.eoms.km.expert.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.km.expert.mgr.KmExpertScoreMgr;
import com.boco.eoms.km.expert.model.KmExpertScore;


/**
 * <p>
 * Title:知识库专家积分
 * </p>
 * <p>
 * Description:知识库专家积分
 * </p>
 * <p>
 * 2009-07-27
 * </p>
 *
 * @author daizhigang
 * @version 1.0
 */

public final class KmExpertScoreAction extends BaseAction {

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
        return null;
    }

    /**
     * 显示所有专家相关积分,以提供打分依据
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showExpertScores(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String treeType = StaticMethod.nullObject2String(request.getParameter("treeType"));
        String specialty = StaticMethod.null2String(request.getParameter("nodeId"));
        KmExpertScoreMgr kmExpertScoreMgr = (KmExpertScoreMgr) getBean("kmExpertScoreMgr");
        List list = new ArrayList();

        if (!"".equals(specialty)) {
            list = kmExpertScoreMgr.getKmExpertScores(specialty, treeType);
        } else {
            list = kmExpertScoreMgr.getKmExpertScores();
        }
        request.setAttribute("treeType", treeType);
        request.setAttribute("expertScores", list);
        return mapping.findForward("listAll");
    }

    /**
     * 显示所有专家相关积分,以提供打分依据
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showTreeUninputed(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setAttribute("treeType", "uninputed");
        return mapping.findForward("tree");
    }

    /**
     * 显示所有专家相关积分,以提供打分依据
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showTreeInputed(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setAttribute("treeType", "inputed");
        return mapping.findForward("tree");
    }

    /**
     * 显示所有专家相关积分,以提供打分依据
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveInputScore(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String[] id = request.getParameterValues("id");
        String[] expertUserId = request.getParameterValues("userId");
        String[] baseScore = request.getParameterValues("baseScore");
        String[] answerNum = request.getParameterValues("answerNum");
        String[] answerSati = request.getParameterValues("answerSati");
        String[] knowledgeScore = request.getParameterValues("knowledgeScore");
        String[] inputScore = request.getParameterValues("inputScore");
        int strLength = expertUserId.length;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newTime = dateFormat.format(new java.util.Date());
        KmExpertScoreMgr kmExpertScoreMgr = (KmExpertScoreMgr) getBean("kmExpertScoreMgr");
        for (int i = 0; i < strLength; i++) {
            if ("".equals(inputScore[i])) {
                continue;
            }
            KmExpertScore kmExpertScoreSum = new KmExpertScore();
            if (!"".equals(id)) {
                kmExpertScoreSum = kmExpertScoreMgr.getKmExpertScore(id[i]);
            }
            kmExpertScoreSum.setCreateTime(newTime);
            kmExpertScoreSum.setExpertUserId(expertUserId[i]);
            kmExpertScoreSum.setTypeFlag("sum");
            kmExpertScoreSum.setBaseScore(baseScore[i]);
            kmExpertScoreSum.setAnswerNum(answerNum[i]);
            kmExpertScoreSum.setAnswerSati(answerSati[i]);
            kmExpertScoreSum.setKnowledgeScore(knowledgeScore[i]);
            kmExpertScoreSum.setInputScore(inputScore[i]);
            kmExpertScoreMgr.saveExpertScore(kmExpertScoreSum);
        }
        return mapping.findForward("success");
    }

    /**
     * 根据专家积分，对专家进行排行
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward expertScoresOrder(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmExpertScoreMgr kmExpertScoreMgr = (KmExpertScoreMgr) getBean("kmExpertScoreMgr");
        int num = StaticMethod.nullObject2int("orderNum", 5);
        List scoresOrder = kmExpertScoreMgr.getExpertScoresOrder("", num);
        request.setAttribute("scoresOrder", scoresOrder);

        return mapping.findForward("expertScoresOrder");
    }


    /**
     * 返回某专家的上月累计积分
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward expertScores(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmExpertScoreMgr kmExpertScoreMgr = (KmExpertScoreMgr) getBean("kmExpertScoreMgr");
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();

        List scoresOrder = kmExpertScoreMgr.getExpertScoresOrder(operateUserId, 1);
        String score = ((KmExpertScore) scoresOrder.get(0)).getInputScore();
        request.setAttribute("score", score);

        return mapping.findForward("expertScoresOrder");
    }


    /**
     * 生成专业树
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
        String nodeId = StaticMethod.null2String(request.getParameter("node")); //当前选择的节点ID

        ITawSystemDictTypeManager sysDictMgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");

        JSONArray jsonRoot = new JSONArray();
        if (nodeId.equals("1010104")) {
            // 取下级节点
            List list = sysDictMgr.getDictSonsByDictid(nodeId);
            for (Iterator nodeIter = list.iterator(); nodeIter.hasNext(); ) {
                TawSystemDictType tawSystemDictType = (TawSystemDictType) nodeIter.next();

                JSONObject jitem = new JSONObject();
                jitem.put("id", tawSystemDictType.getDictId());
                // 添加节点名称
                jitem.put("text", tawSystemDictType.getDictName());
                // 设置右键菜单
                jitem.put("allowChild", true);
                jitem.put("allowEdit", true);
                jitem.put("allowDelete", true);
                // 设置左键点击可触发action
                jitem.put("allowClick", true);
                // 设置是否为叶子节点
                boolean leafFlag = true;
                if (tawSystemDictType.getLeaf().intValue() == 0) {
                    leafFlag = false;
                }
                jitem.put("leaf", 1);
                // 设置鼠标悬浮提示
                jitem.put("qtip", tawSystemDictType.getDictName());
                // 设置分类字段
                jsonRoot.put(jitem);
            }
        }

        JSONUtil.print(response, jsonRoot.toString());
        return null;
    }
}