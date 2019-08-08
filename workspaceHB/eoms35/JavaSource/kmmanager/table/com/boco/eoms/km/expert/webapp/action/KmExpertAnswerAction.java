package com.boco.eoms.km.expert.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.km.cptroom.bo.KmsystemCptroomBo;
import com.boco.eoms.km.cptroom.model.KmsystemCptroom;
import com.boco.eoms.km.duty.bo.KmassignworkBO;
import com.boco.eoms.km.duty.dao.KmrecordDAO;
import com.boco.eoms.km.duty.model.KmrecordSub;
import com.boco.eoms.km.expert.mgr.KmExpertAnswerMgr;
import com.boco.eoms.km.expert.mgr.KmExpertBasicMgr;
import com.boco.eoms.km.expert.mgr.KmExpertScoreMgr;
import com.boco.eoms.km.expert.model.KmExpertAnswer;
import com.boco.eoms.km.expert.model.KmExpertBasic;
import com.boco.eoms.km.expert.model.KmExpertScore;
import com.boco.eoms.km.expert.util.KmExpertBasicConstants;
import com.boco.eoms.km.expert.util.KmExpertScoreConstants;
import com.boco.eoms.km.expert.webapp.form.KmExpertAnswerForm;
import com.boco.eoms.km.expert.webapp.form.KmExpertBasicForm;


/**
 * <p>
 * Title:知识库专家问答
 * </p>
 * <p>
 * Description:知识库专家问答
 * </p>
 * <p>
 * 2009-07-27
 * </p>
 *
 * @author daizhigang
 * @version 1.0
 */

public final class KmExpertAnswerAction extends BaseAction {

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
        return edit(mapping, form, request, response);
    }

    /**
     * 新增问题
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward newQuestion(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();

        request.setAttribute("SessionUserId", operateUserId);
        request.setAttribute("SessionUserName", sessionform.getUsername());
        request.setAttribute("SessionDeptId", operateDeptId);
        request.setAttribute("SessionDeptName", sessionform.getDeptname());

        return mapping.findForward("newQuestion");
    }

    /**
     * 问题详细信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward detailQuestion(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();
        KmExpertAnswerMgr kmExpertAnswerMgr = (KmExpertAnswerMgr) getBean("kmExpertAnswerMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        if (id.equals("")) {
            id = StaticMethod.nullObject2String(request.getAttribute("id"));
        }
        KmExpertAnswer kmExpertAnswer = kmExpertAnswerMgr.getKmExpertAnswer(id);
        request.setAttribute("kmExpertAnswer", kmExpertAnswer);
        request.setAttribute("SessionUserId", operateUserId);
//	KmExpertAnswerForm kmExpertAnswerForm = (KmExpertAnswerForm) convert(kmExpertAnswer);
//	updateFormBean(mapping, request, kmExpertAnswerForm);

        return mapping.findForward("detailQuestion");
    }


    /**
     * 新增基本信息
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
        return mapping.findForward("edit");
    }

    /**
     * 修改基本信息
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
        String userId = StaticMethod.null2String(request.getParameter("userId"));
        if (!"".equals(userId)) {
            KmExpertBasicMgr kmExpertBasicMgr = (KmExpertBasicMgr) getBean("kmExpertBasicMgr");
            KmExpertBasic kmExpertBasic = kmExpertBasicMgr.getKmExpertBasicByUserId(userId);

            KmExpertBasicForm kmExpertBasicForm = (KmExpertBasicForm) convert(kmExpertBasic);
            updateFormBean(mapping, request, kmExpertBasicForm);
        }
        return mapping.findForward("edit");
    }

    public ActionForward detail(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        edit(mapping, form, request, response);
        return mapping.findForward("detail");
    }

    /**
     * 保存基本信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveQuestion(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmExpertAnswerForm kmExpertAnswerForm = (KmExpertAnswerForm) form;
        KmExpertAnswer kmExpertAnswer = (KmExpertAnswer) convert(kmExpertAnswerForm);
        boolean isNew = (null == kmExpertAnswerForm.getId() || "".equals(kmExpertAnswerForm.getId()));
        KmExpertAnswerMgr kmExpertAnswerMgr = (KmExpertAnswerMgr) getBean("kmExpertAnswerMgr");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newTime = dateFormat.format(new java.util.Date());
        String operate = request.getParameter("operate");
        String dealAnswer = StaticMethod.nullObject2String(request.getParameter("dealAnswer"));
        if (isNew) {
            kmExpertAnswer.setCreateTime(newTime); //创建时间
            kmExpertAnswer.setCreateUserId(this.getUserId(request)); //创建人
            kmExpertAnswer.setState("0");
            kmExpertAnswerMgr.saveKmExpertAnswer(kmExpertAnswer);
        } else {
            if ("modifyAnswer".equals(operate)) {
                kmExpertAnswer.setAnswerTime(newTime);
            }
            if ("1".equals(dealAnswer)) {
                kmExpertAnswer.setState("2");
                //增加专家积分
                SimpleDateFormat dateFormaForScore = new SimpleDateFormat("yyyy-MM-dd");
                String scoreTime = dateFormaForScore.format(new java.util.Date());
                KmExpertScoreMgr kmExpertScoreMgr = (KmExpertScoreMgr) getBean("kmExpertScoreMgr");
                //增加scoreTime回答次数和满意度积分
                KmExpertScore kmExpertScore = new KmExpertScore();
                kmExpertScore.setCreateTime(scoreTime);
                kmExpertScore.setExpertUserId(kmExpertAnswer.getAnswerUserId());
                kmExpertScore.setTypeFlag("day");
                kmExpertScore.setAnswerNum("1");
                kmExpertScore.setAnswerSati(kmExpertAnswer.getScore());
                kmExpertScoreMgr.upOrSaveDayScore(kmExpertScore);

            } else if ("0".equals(dealAnswer)) {
                kmExpertAnswer.setState("3");
            }
            kmExpertAnswerMgr.saveKmExpertAnswer(kmExpertAnswer);
        }
        return mapping.findForward("search");
    }

    /**
     * 删除基本信息
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
        String id = StaticMethod.null2String(request.getParameter("id"));
        String userId = StaticMethod.null2String(request.getParameter("userId"));

        KmExpertBasicMgr kmExpertBasicMgr = (KmExpertBasicMgr) getBean("kmExpertBasicMgr");
        kmExpertBasicMgr.removeKmExpertBasic(id, userId);

        return mapping.findForward("search");
    }

    /**
     * 分页显示基本信息列表
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
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateUserId = sessionform.getUserid();

        String pageIndexName = new org.displaytag.util.ParamEncoder("kmExpertAnswerList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        KmExpertAnswerMgr kmExpertAnswerMgr = (KmExpertAnswerMgr) getBean("kmExpertAnswerMgr");
        Map map = (Map) kmExpertAnswerMgr.getKmExpertAnswers(pageIndex, pageSize);
        List list = (List) map.get("result");

        request.setAttribute("kmExpertAnswerList", list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("listTitle", "所有问题列表");
        return mapping.findForward("listQuestion");
    }

    /**
     * 分页显示所有自己提出的问题列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getAllSendList(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateUserId = sessionform.getUserid();
        String questionState = StaticMethod.nullObject2String(request.getParameter("state"));
        if ("".equals(questionState)) {
            questionState = StaticMethod.nullObject2String(request.getAttribute("state"));
        }
        String pageIndexName = new org.displaytag.util.ParamEncoder("kmExpertAnswerList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        KmExpertAnswerMgr kmExpertAnswerMgr = (KmExpertAnswerMgr) getBean("kmExpertAnswerMgr");
        Map map = (Map) kmExpertAnswerMgr.getKmExpertAnswerBySendUserId(pageIndex, pageSize, operateUserId, questionState);
        List list = (List) map.get("result");
        request.setAttribute("state", questionState);
        request.setAttribute("kmExpertAnswerList", list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("listTitle", "我的问题列表");
        return mapping.findForward("listQuestion");
    }

    /**
     * 分页显示所有自己解答和需要解答的问题列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getAllAnswerList(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateUserId = sessionform.getUserid();
        String questionState = StaticMethod.nullObject2String(request.getParameter("state"));
        if ("".equals(questionState)) {
            questionState = StaticMethod.nullObject2String(request.getAttribute("state"));
        }
        String pageIndexName = new org.displaytag.util.ParamEncoder("kmExpertAnswerList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        KmExpertAnswerMgr kmExpertAnswerMgr = (KmExpertAnswerMgr) getBean("kmExpertAnswerMgr");
        Map map = (Map) kmExpertAnswerMgr.getKmExpertAnswerByAnswerUserId(pageIndex, pageSize, operateUserId, questionState);
        List list = (List) map.get("result");
        request.setAttribute("state", questionState);
        request.setAttribute("kmExpertAnswerList", list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("listTitle", "我的回答列表");
        return mapping.findForward("listQuestion");
    }

    /**
     * 生成专家类型树JSON数据
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
        String nodeType = StaticMethod.null2String(request.getParameter("nodetype")); //当前选择的节点ID

        ITawSystemDictTypeManager sysDictMgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");

        JSONArray jsonRoot = new JSONArray();
        //---------------------------------------------------------------
        if (nodeId.equals("-1")) {
            JSONObject jitemAll = new JSONObject();
            jitemAll.put("id", "1010104");
            // 添加节点名称
            jitemAll.put("text", "所有专家");
            // 设置右键菜单
            jitemAll.put("allowChild", true);
            jitemAll.put("allowEdit", false);
            jitemAll.put("allowDelete", false);
            // 设置左键点击可触发action
            jitemAll.put("allowClick", false);
            // 设置是否为叶子节点
            jitemAll.put("leaf", 0);
            // 设置鼠标悬浮提示
            jitemAll.put("qtip", "所有专家--按专业分类");
            // 设置分类字段
            jsonRoot.put(jitemAll);

            JSONObject jitemDuty = new JSONObject();
            jitemDuty.put("id", "0");
            // 添加节点名称
            jitemDuty.put("text", "在线专家");
            // 设置右键菜单
            jitemDuty.put("allowChild", true);
            jitemDuty.put("allowEdit", true);
            jitemDuty.put("allowDelete", true);
            // 设置左键点击可触发action
            jitemDuty.put("allowClick", true);
            // 设置是否为叶子节点
            jitemDuty.put("leaf", false);
            // 设置鼠标悬浮提示
            jitemDuty.put("qtip", "在线专家--按班组分类");
            // 设置分类字段
            jsonRoot.put(jitemDuty);
        }


        //--------------------------------------------------------------
        if (nodeId.indexOf("1010104") > -1) {
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
                    jitem.put("leaf", false);
                    // 设置鼠标悬浮提示
                    jitem.put("qtip", tawSystemDictType.getDictName());
                    // 设置分类字段
                    jsonRoot.put(jitem);
                }
            } else {
                StringBuffer whereStr = new StringBuffer();
                whereStr.append(" where specialty  like '");
                whereStr.append(nodeId);
                whereStr.append("%' ");
                KmExpertBasicMgr kmExpertBasicMgr = (KmExpertBasicMgr) getBean("kmExpertBasicMgr");
                Map map = (Map) kmExpertBasicMgr.getKmExpertBasics(new Integer(0), new Integer(1000), whereStr.toString());
                List list = (List) map.get("result");

                for (Iterator nodeIter = list.iterator(); nodeIter.hasNext(); ) {
                    KmExpertBasic kmExpertBasic = (KmExpertBasic) nodeIter.next();
                    String userId = kmExpertBasic.getUserId();
                    ITawSystemUserManager iTawSystemUserManager = (ITawSystemUserManager) getBean("itawSystemUserManager");
                    TawSystemUser tawSystemUser = iTawSystemUserManager.getUserByuserid(kmExpertBasic.getUserId());
                    String userName = tawSystemUser.getUsername();
                    JSONObject jitem = new JSONObject();
                    jitem.put("id", kmExpertBasic.getUserId());
                    // 添加节点名称
                    jitem.put("text", userName);
                    jitem.put("name", tawSystemUser.getUsername());
                    jitem.put("iconCls", "user");
                    jitem.put("nodeType", "user");
                    // 设置左键点击可触发action
//			jitem.put("allowClick", true);
                    // 设置是否为叶子节点
                    jitem.put("leaf", true);
                    // 设置鼠标悬浮提示
//			jitem.put("qtip", tawSystemDictType.getDictName());
                    // 设置分类字段
                    jsonRoot.put(jitem);
                }
            }
        }
        // ----------------值班专家--------------------
        else if ("0".equals(nodeId)) {
            TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
                    .getSession().getAttribute("sessionform");
            Vector SelectRoom = null;
            Vector SelectRoomId = null;
            Vector SelectRoomName = null;
            KmassignworkBO tawRmAssignworkBO = null;
            KmsystemCptroomBo cptroomBO = KmsystemCptroomBo.getInstance();
            KmsystemCptroom tawApparatusroom = null;
            String strSelectRoomName = null;
            try {
                SelectRoom = new Vector();
                SelectRoomName = new Vector();
                SelectRoomId = new Vector();

                // if
                // (saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN))
                // {
                com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
                        .getInstance();
                tawRmAssignworkBO = new KmassignworkBO(ds);
                SelectRoom = tawRmAssignworkBO.getRoomSelect();
                tawApparatusroom = null;
                strSelectRoomName = "";
                if (SelectRoom.size() > 0) {
                    for (int i = 0; i < SelectRoom.size(); i++) {
                        JSONObject jitem = new JSONObject();
                        tawApparatusroom = cptroomBO.getKmsystemCptroomById(
                                new Integer((String) SelectRoom.elementAt(i)),
                                0);
                        if (tawApparatusroom != null) {
                            strSelectRoomName = StaticMethod
                                    .null2String(tawApparatusroom.getRoomname());
                            jitem.put("id", (String) SelectRoom.elementAt(i));
                            jitem.put("text", strSelectRoomName);
                            jitem.put(UIConstants.JSON_NODETYPE, "cptroom");
                            jitem.put("allowChild", true);
                            jitem.put("allowDelete", true);
                            jitem.put("allowList", true);
                            jitem.put("leaf", false);
                            jsonRoot.put(jitem);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // --------------------------------------------
        else {
            KmrecordDAO kmrecordDAO = new KmrecordDAO();
            int rootId = StaticMethod.nullObject2int(nodeId);
            int workSerial = kmrecordDAO.receivedrWorkSerial(rootId);
            List dutyUsers = kmrecordDAO.listDutymanInfo(workSerial);
            for (int i = 0; i < dutyUsers.size(); i++) {
                JSONObject jitem = new JSONObject();
                TawSystemUser tawSystemUser = new TawSystemUser();
                tawSystemUser = (TawSystemUser) dutyUsers.get(i);
                jitem.put("id", tawSystemUser.getUserid());
                // 添加节点名称
                jitem.put("text", tawSystemUser.getUsername());
                jitem.put("name", tawSystemUser.getUsername());
                jitem.put("iconCls", "user");
                jitem.put("nodeType", "user");
                // 设置左键点击可触发action
//			jitem.put("allowClick", true);
                // 设置是否为叶子节点
                jitem.put("leaf", true);
                // 设置鼠标悬浮提示
//			jitem.put("qtip", tawSystemDictType.getDictName());
                // 设置分类字段
                jsonRoot.put(jitem);
            }
        }


        JSONUtil.print(response, jsonRoot.toString());
        return null;
    }

    /**
     * 分页显示基本信息列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward selectUrl(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateUserId = sessionform.getUserid();
        String pageIndexName = new org.displaytag.util.ParamEncoder("kmExpertAnswerList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        KmExpertAnswerMgr kmExpertAnswerMgr = (KmExpertAnswerMgr) getBean("kmExpertAnswerMgr");
        String title = StaticMethod.null2String(request.getParameter("title"));
        String type = StaticMethod.null2String(request.getParameter("type"));
        String whereSql = "";

        if (!"".equals(title) || !"".equals(type)) {
            whereSql = " where";
        }
        if (!"".equals(title)) {
            whereSql = whereSql + " title like '%" + title + "%'";
        }
        if (!"".equals(type)) {
            whereSql = whereSql + " type = '" + type + "'";
        }

        Map map = (Map) kmExpertAnswerMgr.getKmExpertAnswers(pageIndex, pageSize, whereSql);
        List list = (List) map.get("result");

        request.setAttribute("title", title);
        request.setAttribute("type", type);
        request.setAttribute("kmExpertAnswerList", list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("listTitle", "所有问题列表");
        return mapping.findForward("selectUrlList");
    }
}