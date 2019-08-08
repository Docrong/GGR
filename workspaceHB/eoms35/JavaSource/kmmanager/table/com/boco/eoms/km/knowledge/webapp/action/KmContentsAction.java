package com.boco.eoms.km.knowledge.webapp.action;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Feed;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.kmAuditer.mgr.KmAuditerMgr;
import com.boco.eoms.km.kmAuditer.model.KmAuditer;
import com.boco.eoms.km.kmAuditerStep.mgr.KmAuditerStepMgr;
import com.boco.eoms.km.kmAuditerStep.model.KmAuditerStep;
import com.boco.eoms.km.kmOperate.mgr.KmOperateMgr;
import com.boco.eoms.km.kmOperate.model.KmOperate;
import com.boco.eoms.km.knowledge.mgr.KmContentsApplyMgr;
import com.boco.eoms.km.knowledge.mgr.KmContentsMgr;
import com.boco.eoms.km.knowledge.mgr.KmContentsOpinionMgr;
import com.boco.eoms.km.knowledge.model.KmContents;
import com.boco.eoms.km.knowledge.model.KmContentsApply;
import com.boco.eoms.km.knowledge.util.KmContentsConstants;
import com.boco.eoms.km.knowledge.webapp.form.KmContentsForm;
import com.boco.eoms.km.log.mgr.KmOperateDateLogMgr;
import com.boco.eoms.km.log.mgr.KmOperateLogMgr;
import com.boco.eoms.km.log.util.KmOperateDefine;
import com.boco.eoms.km.rule.util.KmRuleConstants;
import com.boco.eoms.km.servlet.context.RequestContext;
import com.boco.eoms.km.table.mgr.KmTableColumnMgr;
import com.boco.eoms.km.table.mgr.KmTableGeneralMgr;
import com.boco.eoms.km.table.mgr.KmTableThemeMgr;
import com.boco.eoms.km.table.model.KmTableColumn;
import com.boco.eoms.km.table.model.KmTableGeneral;
import com.boco.eoms.km.table.model.KmTableTheme;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p> Title:知识内容管理 </p>
 * <p> Description:知识内容管理 </p>
 * <p> Tue Mar 24 10:32:12 CST 2009 </p>
 *
 * @author ZHANGXB
 * @version 1.0
 */
public final class KmContentsAction extends BaseAction {

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
        return search(mapping, form, request, response);
    }

    /**
     * 新增知识内容
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangxb
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();

        //知识申请相关
        String applyId = StaticMethod.null2String(request.getParameter("applyId"));
        request.setAttribute("applyId", applyId);
        request.setAttribute("SessionUserId", operateUserId);
        request.setAttribute("SessionUserName", sessionform.getUsername());
        request.setAttribute("SessionDeptId", operateDeptId);
        request.setAttribute("SessionDeptName", sessionform.getDeptname());

        // 部门权限
        KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");
        List kmOperateToDeptList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("content", operateDeptId, "dept");
        Map kmOperateMap = new HashMap();
        for (int i = 0; i < kmOperateToDeptList.size(); i++) {
            KmOperate kmOperate = (KmOperate) kmOperateToDeptList.get(i);
            kmOperateMap.put(kmOperate.getNodeId(), kmOperate.getOperateType());
        }
        // 人员权限
        List kmOperateToUserList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("content", operateUserId, "user");
        for (int i = 0; i < kmOperateToUserList.size(); i++) {
            KmOperate kmOperate = (KmOperate) kmOperateToUserList.get(i);
            kmOperateMap.put(kmOperate.getNodeId(), kmOperate.getOperateType());
        }

        // 读取：可以被使用的模型列表
        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        List generalAllList = kmTableGeneralMgr.getOpenKmTableGenerals();
        List generalList = new ArrayList();
        for (int i = 0; i < generalAllList.size(); i++) {
            KmTableGeneral kmTableGeneral = (KmTableGeneral) generalAllList.get(i);
            String operateType = StaticMethod.nullObject2String(kmOperateMap.get(kmTableGeneral.getThemeId()));
            if (("".equals(operateType) || operateType.indexOf("N") != -1) && !"admin".equals(operateUserId)) {
                continue;
            }
            generalList.add(generalAllList.get(i));
        }
        //
//		List generalList = kmTableGeneralMgr.getOpenKmTableGenerals();
        request.setAttribute("KmTableGeneralList", generalList);

        // 判断：是否有可以被使用的模型，如果没有则跳转到提示页面
        if (generalList.size() > 0) {
            // 读取：模型的ID
            String TABLE_ID = StaticMethod.null2String(request.getParameter("TABLE_ID"));
            // 定义：模型绑定的分类
            String THEME_ID = null;

            // 判断：是否选择了某类模型
            if (TABLE_ID.equals("")) {
                // 如果用户没有选择模型，则默认初始化一个
                KmTableGeneral kmTableGeneral = (KmTableGeneral) generalList.get(0);
                TABLE_ID = kmTableGeneral.getId();
                THEME_ID = kmTableGeneral.getThemeId();
            } else {
                // 如果用户选择了模型，则读取用户选择的模型的定义
                KmTableGeneral kmTableGeneral = kmTableGeneralMgr.getKmTableGeneral(TABLE_ID);
                THEME_ID = kmTableGeneral.getThemeId();
            }

            // 设置：用户默认选择的模型ID
            request.setAttribute("TABLE_ID", TABLE_ID);

            // 读取：模型绑定的分类
            KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
            KmTableTheme tableTheme = kmTableThemeMgr.getKmTableThemeByNodeId(THEME_ID);
            request.setAttribute("KmTableTheme", tableTheme);

            // 读取：模型字段的定义列表
            KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
            List columnList = kmTableColumnMgr.getKmTableColumnsByTableId(TABLE_ID);
            request.setAttribute("KmTableColumnList", columnList);

        } else {
            return mapping.findForward("notFindGeneralList");
        }

        return mapping.findForward("add");
    }

    /**
     * 修改知识内容
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangxb
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        //String operateDeptId = sessionform.getDeptid();
        //String operateUserId = sessionform.getUserid();

        request.setAttribute("SessionUserId", sessionform.getUserid());
        request.setAttribute("SessionUserName", sessionform.getUsername());
        request.setAttribute("SessionDeptId", sessionform.getDeptid());
        request.setAttribute("SessionDeptName", sessionform.getDeptname());

        // 读取：知识ID（主键）
        String ID = StaticMethod.null2String(request.getParameter("ID"));
        // 读取：知识所属模型ID（外键）
        String TABLE_ID = StaticMethod.null2String(request.getParameter("TABLE_ID"));
        // 读取：知识模型所属分类ID（外键）
        String THEME_ID = StaticMethod.null2String(request.getParameter("THEME_ID"));

        // 读取：根据知识的ID、TABLE_ID、THEME_ID查询知识内容详细信息
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        Map kmContentsMap = kmContentsMgr.getKmContents(ID, TABLE_ID, THEME_ID);
        request.setAttribute("KmContents", kmContentsMap);

        // 读取:模型字段的定义列表
        KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
        List columnList = kmTableColumnMgr.getKmTableColumnsByTableId(TABLE_ID);
        request.setAttribute("KmTableColumnList", columnList);

        return mapping.findForward("edit");
    }

    /**
     * 保存知识管理
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
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();
        String draft = StaticMethod.null2String(request.getParameter("draft"));//保存为草稿标志 以yes为标志
        System.out.println("获取的草稿状态为：" + draft);

        // 处理：页面数据格式转换
        RequestContext reqContext = new RequestContext();
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = (String) parameterNames.nextElement();
            String values[] = request.getParameterValues(name);
            if (values.length == 0) {
                reqContext.setEntityValue2(name, "");
            } else if (values.length <= 1) {
                reqContext.setEntityValue2(name, values[0]);
            } else {
                reqContext.setEntityValues(name, values);
            }
        }

        Integer operateId = null;
        String ID = StaticMethod.null2String(request.getParameter("TableInfo/ID"));
        String THEME_ID = StaticMethod.null2String(request.getParameter("TableInfo/THEME_ID"));
        String CONTENT_STATUS = StaticMethod.null2String(request.getParameter("TableInfo/CONTENT_STATUS"));

        //从知识申请中添加的知识  标识
        String applyId = StaticMethod.null2String(request.getParameter("applyId"));

        if (ID.equals("")) {
            //判断是否有审核配置
            KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
            KmAuditer kmAuditer = kmAuditerMgr.getKmAuditerByTheme(THEME_ID);
            // 生成：知识条件主键
            ID = UUIDHexGenerator.getInstance().getID();
            reqContext.setEntityValue2("TableInfo/ID", ID);
            if (!("yes".equals(draft))) {//如果不是保存为草稿
                if (kmAuditer.getId() != null) {
                    String masterAudit = kmAuditer.getMasterAudit();
                    String isSign = kmAuditer.getIsSign();
                    if (masterAudit.equals("1030101")) {
                        reqContext.setEntityValue2("TableInfo/CONTENT_STATUS", "5");
                    } else {
                        reqContext.setEntityValue2("TableInfo/CONTENT_STATUS", "6");
                    }
                    if (isSign.equals("1030101")) {
                        //request.setAttribute("TableInfo/STATE", "1");
                    }
                }
                //判断是否进行送审
                if (kmAuditer.getId() != null) {
                    KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
                    Map kmAuditerStepMap = new HashMap();
                    kmAuditerStepMap.put("type", "content");
                    kmAuditerStepMap.put("auditResult", "");
                    kmAuditerStepMap.put("remark", "");
                    kmAuditerStepMap.put("userId", operateUserId);
                    kmAuditerStepMap.put("kmId", ID);
                    kmAuditerStepMap.put("state", "1");
                    KmAuditerStep kmAuditerStep = kmAuditerStepMgr.saveKmAuditerStep(kmAuditer, kmAuditerStepMap);
                    request.setAttribute("auditName", kmAuditerStep.getToOrgName());
                }

            } else {
                reqContext.setEntityValue2("TableInfo/CONTENT_STATUS", "1");//状态设置为草稿
            }

            if (!(applyId.equals(""))) {
                //得到当前的申请信息
                KmContentsApplyMgr kmContentsApplyMgr = (KmContentsApplyMgr) getBean("kmContentsApplyMgr");
                KmContentsApply kmContentsApply = kmContentsApplyMgr.getKmContentsApply(applyId);
                kmContentsApply.setIsAdd("1");
                kmContentsApply.setContentId(ID);
                kmContentsApplyMgr.saveKmContentsApply(kmContentsApply);
            }

            // 执行：插入知识条目
            KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
            kmContentsMgr.saveKmContents(reqContext);

            // 记录：执行的操纵
            operateId = KmOperateDefine.KM_OPERATE_NAME_CONTENTS_ADD;


        } else {
            if (CONTENT_STATUS.equals("1")) {
                //如果是提交草稿到知识库
                if (!("yes".equals(draft))) {
                    KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
                    KmAuditer kmAuditer = kmAuditerMgr.getKmAuditerByTheme(THEME_ID);
                    if (kmAuditer.getId() != null) {
                        String masterAudit = kmAuditer.getMasterAudit();
                        String isSign = kmAuditer.getIsSign();
                        if (masterAudit.equals("1030101")) {
                            reqContext.setEntityValue2("TableInfo/CONTENT_STATUS", "5");
                        } else {
                            reqContext.setEntityValue2("TableInfo/CONTENT_STATUS", "6");
                        }
                        if (isSign.equals("1030101")) {
                            //request.setAttribute("TableInfo/STATE", "1");
                        }
                    } else {
                        reqContext.setEntityValue2("TableInfo/CONTENT_STATUS", "2");//将状态改为有效
                    }
                    if (kmAuditer.getId() != null) {
                        KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
                        Map kmAuditerStepMap = new HashMap();
                        kmAuditerStepMap.put("type", "content");
                        kmAuditerStepMap.put("auditResult", "");
                        kmAuditerStepMap.put("remark", "");
                        kmAuditerStepMap.put("userId", operateUserId);
                        kmAuditerStepMap.put("kmId", ID);
                        kmAuditerStepMap.put("state", "1");
                        kmAuditerStepMgr.saveKmAuditerStep(kmAuditer, kmAuditerStepMap);
                    }
                }
                //如果修改后还是保存到草稿箱
                else {
                    reqContext.setEntityValue2("TableInfo/CONTENT_STATUS", "1");//状态设置为草稿
                }
            }

            // 执行：修改知识条目
            KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
            kmContentsMgr.updateKmContents(reqContext);

            // 记录：执行的操纵
            operateId = KmOperateDefine.KM_OPERATE_NAME_CONTENTS_MODIFY;
        }

        // 设置：插入成功返回知识列表
        request.setAttribute("node", THEME_ID);
        request.setAttribute("nodeLeaf", "TRUE");

        String TABLE_ID = StaticMethod.null2String(request.getParameter("TableInfo/TABLE_ID"));

        //当添加的是草稿的时候不记录日志
        if (!("yes".equals(draft))) {

            //如果将草稿重新提交到知识库 将操作日志设置为添加
            if (CONTENT_STATUS.equals("1")) {
                operateId = KmOperateDefine.KM_OPERATE_NAME_CONTENTS_ADD;
            }

            // 记录:操作记录表-
            KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
            kmOperateLogMgr.saveKmOperateLog(THEME_ID, TABLE_ID, ID,
                    operateId,
                    operateUserId, operateDeptId, operateUserId);
            // 记录：日操作记录表-
            KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
            kmOperateDateLogMgr.saveKmOperateDateLog(new Date(),
                    operateId,
                    operateUserId, operateDeptId);
        }
        return mapping.findForward("success");
    }

    /**
     * 查看知识详情
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward detail(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();

        // 知识ID（主键）
        String ID = StaticMethod.null2String(request.getParameter("ID"));
        // 知识所属模型ID（外键）
        String TABLE_ID = StaticMethod.null2String(request.getParameter("TABLE_ID"));
        // 知识模型所属分类ID（外键）
        String THEME_ID = StaticMethod.null2String(request.getParameter("THEME_ID"));

        // 部门权限
        KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");
        List kmOperateToDeptList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("content", operateDeptId, "dept");
        Map kmOperateMap = new HashMap();
        for (int i = 0; i < kmOperateToDeptList.size(); i++) {
            KmOperate kmOperate = (KmOperate) kmOperateToDeptList.get(i);
            kmOperateMap.put(kmOperate.getNodeId(), kmOperate.getOperateType());
        }
        // 人员权限
        List kmOperateToUserList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("content", operateUserId, "user");
        for (int i = 0; i < kmOperateToUserList.size(); i++) {
            KmOperate kmOperate = (KmOperate) kmOperateToUserList.get(i);
            kmOperateMap.put(kmOperate.getNodeId(), kmOperate.getOperateType());
        }

        KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
        KmTableTheme tableTheme = kmTableThemeMgr.getKmTableThemeByNodeId(THEME_ID);

        String operateType = StaticMethod.nullObject2String(kmOperateMap.get(THEME_ID));
        if ("".equals(operateType) && "1".equals(tableTheme.getHasParentOperate())) {
            String parentId = tableTheme.getParentNodeId();
            operateType = StaticMethod.nullObject2String(kmOperateMap.get(parentId));
        }
        request.setAttribute("operateType", operateType);

        // 读取:模型字段的定义
        KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
        List columnList = kmTableColumnMgr.getKmTableColumnsByTableId(TABLE_ID);
        request.setAttribute("KmTableColumnList", columnList);

        // 读取:知识内容详细信息
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        Map kmContentsMap = kmContentsMgr.getKmContents(ID, TABLE_ID, THEME_ID);
        request.setAttribute("KmContents", kmContentsMap);

        // 读取:知识内容历史版本信息
        List kmContentsHistory = kmContentsMgr.getKmContentsHistoryList(ID, TABLE_ID, THEME_ID);
        request.setAttribute("KmContentsHistoryList", kmContentsHistory);

        // 查询:该知识审核的步骤
        KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
        String auditStepSql = "kmId = '" + ID + "' order by createTime";
        List stepList = kmAuditerStepMgr.getKmAuditerSteps(auditStepSql);
        List auditList = new ArrayList();
        for (int i = 1; i < stepList.size(); i++) {
            auditList.add(stepList.get(i));
        }
        request.setAttribute("KmAuditColumnList", auditList);

        // 查询:该知识的知识评论信息
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTSOPINION_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmContentsOpinionMgr kmContentsOpinionMgr = (KmContentsOpinionMgr) getBean("kmContentsOpinionMgr");
        String contentId = ID;
        String whereStr = " where kmContentsOpinion.contentId = '" + contentId + "'";
        Map map = (Map) kmContentsOpinionMgr.getKmContentsOpinions(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(KmContentsConstants.KMCONTENTSOPINION_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        // 记录:操作记录表-阅读操作
        KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
        kmOperateLogMgr.saveKmOperateLog(THEME_ID, TABLE_ID, ID,
                KmOperateDefine.KM_OPERATE_NAME_CONTENTS_READ,
                operateUserId, operateDeptId, operateUserId);
        // 记录：日操作记录表-阅读操作
        KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
        kmOperateDateLogMgr.saveKmOperateDateLog(new Date(),
                KmOperateDefine.KM_OPERATE_NAME_CONTENTS_READ,
                operateUserId, operateDeptId);

        return mapping.findForward("detail");
    }

    public ActionForward detailHistory(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 知识ID（主键）
        String ID = StaticMethod.null2String(request.getParameter("ID"));
        // 知识所属模型ID（外键）
        String TABLE_ID = StaticMethod.null2String(request.getParameter("TABLE_ID"));
        // 知识模型所属分类ID（外键）
        String THEME_ID = StaticMethod.null2String(request.getParameter("THEME_ID"));
        // 知识版本号
        String VERSION = StaticMethod.null2String(request.getParameter("VERSION"));

        // 读取:知识内容详细信息
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        Map kmContentsMap = kmContentsMgr.getKmContentsHistory(ID, TABLE_ID, THEME_ID, VERSION);
        kmContentsMap.put("VERSION", VERSION);
        request.setAttribute("KmContents", kmContentsMap);

        // 读取:模型字段的定义
        KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
        List columnList = kmTableColumnMgr.getKmTableColumnsByTableId(TABLE_ID);
        request.setAttribute("KmTableColumnList", columnList);

        return mapping.findForward("detailHistory");
    }

    /**
     * 删除知识管理
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
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();

        // 知识ID（主键）
        String ID = StaticMethod.null2String(request.getParameter("ID"));
        // 知识所属模型ID（外键）
        String TABLE_ID = StaticMethod.null2String(request.getParameter("TABLE_ID"));
        // 知识模型所属分类ID（外键）
        String THEME_ID = StaticMethod.null2String(request.getParameter("THEME_ID"));

        // 执行：删除知识内容
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        kmContentsMgr.removeKmContents(ID, TABLE_ID, THEME_ID);

        request.setAttribute("node", THEME_ID);
        request.setAttribute("nodeLeaf", "TRUE");

        // 记录:操作记录表-删除操作
        KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
        kmOperateLogMgr.saveKmOperateLog(THEME_ID, TABLE_ID, ID,
                KmOperateDefine.KM_OPERATE_NAME_CONTENTS_DELETE,
                operateUserId, operateDeptId, operateUserId);
        // 记录：日操作记录表-删除操作
        KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
        kmOperateDateLogMgr.saveKmOperateDateLog(new Date(),
                KmOperateDefine.KM_OPERATE_NAME_CONTENTS_DELETE,
                operateUserId, operateDeptId);

        return search(mapping, form, request, response);
    }

    /**
     * 删除知识管理
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward over(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();

        // 知识ID（主键）
        String ID = StaticMethod.null2String(request.getParameter("ID"));
        // 知识所属模型ID（外键）
        String TABLE_ID = StaticMethod.null2String(request.getParameter("TABLE_ID"));
        // 知识模型所属分类ID（外键）
        String THEME_ID = StaticMethod.null2String(request.getParameter("THEME_ID"));

        // 执行：删除知识内容
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        kmContentsMgr.overKmContents(ID, TABLE_ID, THEME_ID);

        request.setAttribute("node", THEME_ID);
        request.setAttribute("nodeLeaf", "TRUE");

        // 记录:操作记录表-失效操作
        KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
        kmOperateLogMgr.saveKmOperateLog(THEME_ID, TABLE_ID, ID,
                KmOperateDefine.KM_OPERATE_NAME_CONTENTS_OVER,
                operateUserId, operateDeptId, operateUserId);
        // 记录：日操作记录表-失效操作
        KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
        kmOperateDateLogMgr.saveKmOperateDateLog(new Date(),
                KmOperateDefine.KM_OPERATE_NAME_CONTENTS_OVER,
                operateUserId, operateDeptId);

        return search(mapping, form, request, response);
    }

    /**
     * 根据分类去查找该 分类下的所用的知识列表 分页显示知识管理列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward query(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String minTime = StaticMethod.null2String(request.getParameter("minTime"));
        String maxTime = StaticMethod.null2String(request.getParameter("maxTime"));
        request.setAttribute("minTime", minTime);
        request.setAttribute("maxTime", maxTime);
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        //String operateDeptId = sessionform.getDeptid();
        //String operateUserId = sessionform.getUserid();

        request.setAttribute("SessionUserId", sessionform.getUserid());
        request.setAttribute("SessionUserName", sessionform.getUsername());
        request.setAttribute("SessionDeptId", sessionform.getDeptid());
        request.setAttribute("SessionDeptName", sessionform.getDeptname());

        // 读取：可以被使用的模型列表
        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        List generalList = kmTableGeneralMgr.getOpenKmTableGenerals();
        request.setAttribute("KmTableGeneralList", generalList);

        // 判断：是否有可以被使用的模型，如果没有则跳转到提示页面
        if (generalList.size() > 0) {
            // 读取：模型的ID
            String TABLE_ID = StaticMethod.null2String(request.getParameter("TABLE_ID"));
            // 定义：模型绑定的分类
            String THEME_ID = null;

            // 判断：是否选择了某类模型
            if (TABLE_ID.equals("")) {
                // 如果用户没有选择模型，则默认初始化一个
                KmTableGeneral kmTableGeneral = (KmTableGeneral) generalList.get(0);
                TABLE_ID = kmTableGeneral.getId();
                THEME_ID = kmTableGeneral.getThemeId();
            } else {
                // 如果用户选择了模型，则读取用户选择的模型的定义
                KmTableGeneral kmTableGeneral = kmTableGeneralMgr.getKmTableGeneral(TABLE_ID);
                THEME_ID = kmTableGeneral.getThemeId();
            }

            // 设置：用户默认选择的模型ID
            KmContentsForm kmContentsForm = (KmContentsForm) form;
            kmContentsForm.setId(TABLE_ID);

            // 读取：模型绑定的分类
            KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
            KmTableTheme tableTheme = kmTableThemeMgr.getKmTableThemeByNodeId(THEME_ID);
            request.setAttribute("KmTableTheme", tableTheme);

            // 读取：模型字段的定义列表
            KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
            List columnList = kmTableColumnMgr.getKmTableColumnsByTableId(TABLE_ID);
            request.setAttribute("KmTableColumnList", columnList);
        } else {
            return mapping.findForward("notFindGeneralList");
        }

        return mapping.findForward("query");
    }

    /**
     * 根据分类去查找该 分类下的所用的知识列表 分页显示知识管理列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward complexQuery(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        //String operateDeptId = sessionform.getDeptid();
        //String operateUserId = sessionform.getUserid();

        request.setAttribute("SessionUserId", sessionform.getUserid());
        request.setAttribute("SessionUserName", sessionform.getUsername());
        request.setAttribute("SessionDeptId", sessionform.getDeptid());
        request.setAttribute("SessionDeptName", sessionform.getDeptname());

        // 读取：可以被使用的模型列表
        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        List generalList = kmTableGeneralMgr.getOpenKmTableGenerals();
        request.setAttribute("KmTableGeneralList", generalList);

        // 判断：是否有可以被使用的模型，如果没有则跳转到提示页面
        if (generalList.size() > 0) {
            // 读取：模型的ID
            String TABLE_ID = StaticMethod.null2String(request.getParameter("TABLE_ID"));
            // 定义：模型绑定的分类
            String THEME_ID = null;

            // 判断：是否选择了某类模型
            if (TABLE_ID.equals("")) {
                // 如果用户没有选择模型，则默认初始化一个
                KmTableGeneral kmTableGeneral = (KmTableGeneral) generalList.get(0);
                TABLE_ID = kmTableGeneral.getId();
                THEME_ID = kmTableGeneral.getThemeId();
                request.setAttribute("tableName", kmTableGeneral.getTableName());
            } else {
                // 如果用户选择了模型，则读取用户选择的模型的定义
                KmTableGeneral kmTableGeneral = kmTableGeneralMgr
                        .getKmTableGeneral(TABLE_ID);
                THEME_ID = kmTableGeneral.getThemeId();
                request.setAttribute("tableName", kmTableGeneral.getTableName());
            }

            // 设置：用户默认选择的模型ID
            KmContentsForm kmContentsForm = (KmContentsForm) form;
            kmContentsForm.setId(TABLE_ID);

            // 读取：模型绑定的分类
            KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
            KmTableTheme tableTheme = kmTableThemeMgr.getKmTableThemeByNodeId(THEME_ID);
            request.setAttribute("KmTableTheme", tableTheme);

            // 读取：模型字段的定义列表
            KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
            List columnList = kmTableColumnMgr.getKmTableColumnsByTableId(TABLE_ID);
            request.setAttribute("KmTableColumnList", columnList);

            List kmTableColumnList = new ArrayList();
            JSONArray root = new JSONArray();

            int length = KmRuleConstants.idArray.length;
            for (int i = 0; i < length; i++) {
                KmTableColumn kmTableColumn = new KmTableColumn();
                kmTableColumn.setColName(KmRuleConstants.idArray[i]);
                kmTableColumn.setColChname(KmRuleConstants.nameArray[i]);
                kmTableColumnList.add(kmTableColumn);

                JSONObject item = new JSONObject();
                item.put("id", KmRuleConstants.idArray[i]);
                item.put("name", KmRuleConstants.nameArray[i]);
                item.put("colType", KmRuleConstants.typeArray[i]);
                item.put("typeValue", KmRuleConstants.typeValueArray[i]);
                root.put(item);
            }

            IDictService service = (IDictService) ApplicationContextHolder.getInstance().getBean("DictService");
            for (Iterator nodeIter = columnList.iterator(); nodeIter.hasNext(); ) {
                KmTableColumn kmTableColumn = (KmTableColumn) nodeIter.next();
                kmTableColumnList.add(kmTableColumn);

                JSONObject item = new JSONObject();
                item.put("id", kmTableColumn.getColName());
                item.put("name", kmTableColumn.getColChname());
                String colType = (String) service.itemId2name(Util.constituteDictId("dict-kmmanager", "colType"), kmTableColumn.getColType());
                item.put("colType", colType);
                String typeValue = (String) service.itemId2description(Util.constituteDictId("dict-kmmanager", "colType"), kmTableColumn.getColType());
                item.put("typeValue", typeValue);
                root.put(item);
            }

            request.setAttribute("data", root);
            request.setAttribute("KmTableColumnList", kmTableColumnList);
        } else {
            return mapping.findForward("notFindGeneralList");
        }

        return mapping.findForward("complexQuery");
    }

    /**
     * 根据分类去查找该 分类下的所用的知识列表 分页显示知识管理列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward queryDo(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTS_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        // 处理：页面数据格式转换
        RequestContext reqContext = new RequestContext();
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = (String) parameterNames.nextElement();
            String values[] = request.getParameterValues(name);
            if (values.length == 0) {
                reqContext.setEntityValue2(name, "");
            } else if (values.length <= 1) {
                reqContext.setEntityValue2(name, values[0]);
            } else {
                reqContext.setEntityValues(name, values);
            }
        }

        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        Map map = kmContentsMgr.searchKmContentss(reqContext, pageIndex, pageSize);

        request.setAttribute(KmContentsConstants.KMCONTENTS_LIST, map.get("result"));
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("queryList");
    }

    /**
     * 根据分类去查找该 分类下的所用的知识列表 分页显示知识管理列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward complexQueryDo(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTS_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        String sql = StaticMethod.null2String(request
                .getParameter("ruleScript"));

        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        Map map = kmContentsMgr.complexQuery(sql, pageIndex, pageSize);
        request.setAttribute(KmContentsConstants.KMCONTENTS_LIST, map.get("result"));
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("queryList");
    }

    /**
     * 根据分类去查找该 分类下的所用的知识列表 分页显示知识管理列表
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
        String next = "list";
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTS_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        request.setAttribute("auditName", request.getAttribute("auditName"));
        // 知识模型所属分类ID（外键）
        String node = StaticMethod.null2String(request.getParameter("node"));
        if (node.equalsIgnoreCase("")) {
            node = StaticMethod.nullObject2String(request.getAttribute("node"));
        }
        // 知识模型所属分类ID（是否是叶子节点）
        String leaf = StaticMethod.null2String(request.getParameter("nodeLeaf"));
        if (leaf.equalsIgnoreCase("")) {
            leaf = StaticMethod.nullObject2String(request.getAttribute("nodeLeaf"));
        }

        // 判断是不是叶子节点
        boolean isLeaf = true;
        if (leaf.equalsIgnoreCase("FALSE")) {
            isLeaf = false;
        }

        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        Map map = kmContentsMgr.searchKmContentss(node, isLeaf, pageIndex, pageSize);

        request.setAttribute(KmContentsConstants.KMCONTENTS_LIST, map.get("result"));
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        //导出excel
        String flagexcel = StaticMethod.nullObject2String(request.getParameter("flagexcel"));
        String url = request.getContextPath() + "/kmmanager/kmContentss.do?method=search&node=" + node + "&nodeLeaf=false&flagexcel=true";
        request.setAttribute("excelUrl", url);
        if (flagexcel.equals("true")) {
            String dd = KmContentsAction.class.getResource("/").toString();
            List contentList = (List) map.get("result");
            generalExcelFile(request, "performContentsList",
                    contentList);
            next = "excelJsp";
        }
        return mapping.findForward(next);
    }

    /**
     * 根据分类去查找该 分类下的所用的知识列表 分页显示知识管理列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchDraft(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateUserId = sessionform.getUserid();

        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTS_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        // 知识模型所属分类ID（外键）
        String node = StaticMethod.null2String(request.getParameter("node"));
        if (node.equalsIgnoreCase("")) {
            node = StaticMethod.nullObject2String(request.getAttribute("node"));
        }
        // 知识模型所属分类ID（是否是叶子节点）
        String leaf = StaticMethod.null2String(request.getParameter("nodeLeaf"));
        if (leaf.equalsIgnoreCase("")) {
            leaf = StaticMethod.nullObject2String(request.getAttribute("nodeLeaf"));
        }

        // 判断是不是叶子节点
        boolean isLeaf = true;
        if (leaf.equalsIgnoreCase("FALSE")) {
            isLeaf = false;
        }

        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        Map map = kmContentsMgr.searchKmContentss(node, isLeaf, "1", operateUserId, pageIndex, pageSize);

        request.setAttribute(KmContentsConstants.KMCONTENTS_LIST, map.get("result"));
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("list");
    }


    /**
     * 查询当前登陆用户所创建的处于草稿状态的知识
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author daizhiang
     */
    public ActionForward searchAllDraft(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateUserId = sessionform.getUserid();

        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTS_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        Map map = new HashMap();
        String where = " where kmContents.contentStatus = '1' and kmContents.createUser = '" + operateUserId + "'";
        try {
            KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
            map = (Map) kmContentsMgr.getKmContentss(pageIndex, pageSize, where);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute(KmContentsConstants.KMCONTENTS_LIST, map.get("result"));
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("allList");
    }

    /**
     * 查询当前登陆用户所创建的知识
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author daizhigang
     */
    public ActionForward searchAllSend(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateUserId = sessionform.getUserid();

        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTS_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        Map map = new HashMap();
        String where = " where kmContents.createUser = '" + operateUserId + "'";
        try {
            KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
            map = (Map) kmContentsMgr.getKmContentss(pageIndex, pageSize, where);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute(KmContentsConstants.KMCONTENTS_LIST, map.get("result"));
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("allList");
    }

    /**
     * 分页显示知识管理列表，支持Atom方式接入Portal
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            // --------------用于分页，得到当前页号-------------
            final Integer pageIndex = new Integer(request
                    .getParameter("pageIndex"));
            final Integer pageSize = new Integer(request
                    .getParameter("pageSize"));
            KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
            Map map = (Map) kmContentsMgr.getKmContentss(pageIndex, pageSize,
                    "");
            List list = (List) map.get("result");
            KmContents kmContents = new KmContents();

            // 创建ATOM源
            Factory factory = Abdera.getNewFactory();
            Feed feed = factory.newFeed();

            // 分页
            // for (int i = 0; i < list.size(); i++) {
            // kmContents = (KmContents) list.get(i);
            //
            // // TODO 请按照下面的实例给entry赋值
            // Entry entry = feed.insertEntry();
            // entry.setTitle("<a href='" + request.getScheme() + "://"
            // + request.getServerName() + ":"
            // + request.getServerPort()
            // + request.getContextPath()
            // + "/kmContents/kmContentss.do?method=edit&id="
            // + kmContents.getId() + "' target='_blank'>"
            // + display name for list + "</a>");
            // entry.setSummary(summary);
            // entry.setContent(content);
            // entry.setLanguage(language);
            // entry.setText(text);
            // entry.setRights(tights);
            //
            // // 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
            // entry.setUpdated(new java.util.Date());
            // entry.setPublished(new java.util.Date());
            // entry.setEdited(new java.util.Date());
            //
            // // 为person的name属性赋值，entry.addAuthor可以随意赋值
            // Person person = entry.addAuthor(userId);
            // person.setName(userName);
            // }

            // 每页显示条数
            feed.setText(map.get("total").toString());
            OutputStream os = response.getOutputStream();
            PrintStream ps = new PrintStream(os);
            feed.getDocument().writeTo(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 回收站功能
     * 显示出所有的已删除知识
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward recycle(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //获得当前作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateUserId = sessionform.getUserid();

        Map map = new HashMap();
        map.clear();

        //构建数据查询语句
        StringBuffer str = new StringBuffer();
        str.append(" where kmContents.contentStatus='4' and createUser='");
        str.append(operateUserId);
        str.append("'");

        //模型绑定
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");

        //分页处理
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTSRECYCLE_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        map = (Map) kmContentsMgr.getKmContentss(pageIndex, pageSize, str.toString());

        //数据格式化，取得当前数据总条数
        Integer total = Integer.valueOf(map.get("total").toString());

        //控制页面中按钮的显情况,如果没有显示内容则按钮消失
        if (total.intValue() == 0) {
            request.setAttribute("disbutton", "style='display:none'");
        }

        request.setAttribute(KmContentsConstants.KMCONTENTSRECYCLE_LIST, map.get("result"));
        request.setAttribute("resultSize", total);
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("recycle");
    }


    /**
     * 实现excel导出
     *
     * @param request
     * @param excelname
     * @param list
     * @return
     */
    public void generalExcelFile(HttpServletRequest request, String excelname,
                                 List list) {
        String configPath = "";
        try {
            configPath = KmContentsAction.class.getResource("").toString();
            System.out.println("sPath===" + configPath);
            configPath = configPath.substring(5, configPath.indexOf("WEB-INF")) + "WEB-INF/classes/com/boco/eoms/km/config/";
            System.out.println("configPath===" + configPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.boco.eoms.km.excelmanage.PoiExcel poiExcel = new com.boco.eoms.km.excelmanage.PoiExcel(configPath);
        String path = poiExcel.getPoiExcel(excelname, list);
        request.setAttribute("excelfile", path);
        request.setAttribute("excelfilename", path.substring(path
                .lastIndexOf(File.separator) + 1, path.length()));
    }

    /**
     * 回收站功能
     * 逐条删除回收站中的内容
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward recycleDel(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //从页面中取得要删除数据的ID
        String themeid = request.getParameter("themeId");
        //模型绑定
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        //根据ID删除数据
        kmContentsMgr.deleteKmContentsByThemeId(themeid);

        return mapping.findForward("backRecycle");
    }

    /**
     * 清空回收站
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward recycleDelAll(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //模型绑定
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        //清除所的数据
        kmContentsMgr.deleteKmContents();

        return mapping.findForward("backRecycle");
    }

    /**
     * 知识草稿
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward kmDraft(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTSDRAFT_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        //当前登陆人
        String createUser = this.getUser(request).getUserid();
        String whereStr = " where kmContents.contentStatus='1' and createUser ='" + createUser + "'";
        String createDept = this.getUser(request).getDeptid();
        request.setAttribute("createDept", createDept);
        //模型绑定
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        Map map = kmContentsMgr.getKmContentss(pageIndex, pageSize, whereStr);
        request.setAttribute(KmContentsConstants.KMCONTENTSDRAFT_LIST, map.get("result"));
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("kmdraft");
    }

    /**
     * 查看草稿详情
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward detailDraft(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 知识ID（主键）
        String ID = StaticMethod.null2String(request.getParameter("ID"));
        // 知识所属模型ID（外键）
        String TABLE_ID = StaticMethod.null2String(request.getParameter("TABLE_ID"));
        // 知识模型所属分类ID（外键）
        String THEME_ID = StaticMethod.null2String(request.getParameter("THEME_ID"));

        // 读取:模型字段的定义
        KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
        List columnList = kmTableColumnMgr.getKmTableColumnsByTableId(TABLE_ID);
        request.setAttribute("KmTableColumnList", columnList);

        // 读取:知识内容详细信息
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        Map kmContentsMap = kmContentsMgr.getKmContents(ID, TABLE_ID, THEME_ID);
        request.setAttribute("KmContents", kmContentsMap);

        // 读取:知识内容历史版本信息
        List kmContentsHistory = kmContentsMgr.getKmContentsHistoryList(ID, TABLE_ID, THEME_ID);
        request.setAttribute("KmContentsHistoryList", kmContentsHistory);

        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        //String operateDeptId = sessionform.getDeptid();
        //String operateUserId = sessionform.getUserid();

        request.setAttribute("SessionUserId", sessionform.getUserid());
        request.setAttribute("SessionUserName", sessionform.getUsername());
        request.setAttribute("SessionDeptId", sessionform.getDeptid());
        request.setAttribute("SessionDeptName", sessionform.getDeptname());

//		return mapping.findForward("edit");

        return mapping.findForward("detailDraft");
    }

    /**
     * 还原回收站中所有项目
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward revivifyAll(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //模型绑定
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        //还原所有数据
        kmContentsMgr.revivifyAllKmContents();

        return mapping.findForward("backRecycle");
    }

    /**
     * 还原回收站中所选择要还原的的项目
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward revivify(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //模型绑定
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        //从页面中取得要还原数据的ID
        String themeid = request.getParameter("themeId");
        //根据ID还原数据
        kmContentsMgr.revivifyKmContents(themeid);

        return mapping.findForward("backRecycle");
    }

}