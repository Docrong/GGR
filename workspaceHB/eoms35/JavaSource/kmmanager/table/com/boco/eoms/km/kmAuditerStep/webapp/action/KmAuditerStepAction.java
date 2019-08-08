package com.boco.eoms.km.kmAuditerStep.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.core.crimson.util.XmlUtil;
import com.boco.eoms.km.file.mgr.KmFileMgr;
import com.boco.eoms.km.file.model.KmFile;
import com.boco.eoms.km.file.util.FileConstants;
import com.boco.eoms.km.file.webapp.form.KmFileForm;
import com.boco.eoms.km.kmAuditer.mgr.KmAuditerMgr;
import com.boco.eoms.km.kmAuditer.model.KmAuditer;
import com.boco.eoms.km.kmAuditerStep.mgr.KmAuditerStepMgr;
import com.boco.eoms.km.kmAuditerStep.model.KmAuditerStep;
import com.boco.eoms.km.kmAuditerStep.util.KmAuditerStepConstants;
import com.boco.eoms.km.kmAuditerStep.webapp.form.KmAuditerStepForm;
import com.boco.eoms.km.knowledge.mgr.KmContentsMgr;
import com.boco.eoms.km.knowledge.mgr.KmContentsOpinionMgr;
import com.boco.eoms.km.knowledge.model.KmContents;
import com.boco.eoms.km.knowledge.util.KmContentsConstants;
import com.boco.eoms.km.log.mgr.KmOperateDateLogMgr;
import com.boco.eoms.km.log.mgr.KmOperateLogMgr;
import com.boco.eoms.km.log.util.KmOperateDefine;
import com.boco.eoms.km.log.util.KmOperateLogConstants;
import com.boco.eoms.km.servlet.context.RequestContext;
import com.boco.eoms.km.table.mgr.KmTableColumnMgr;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;

/**
 * <p>
 * Title:知识管理审核步骤
 * </p>
 * <p>
 * Description:知识管理审核步骤
 * </p>
 * <p>
 * Mon May 04 14:42:01 CST 2009
 * </p>
 *
 * @moudle.getAuthor() 戴志刚
 * @moudle.getVersion() 1.0
 */
public final class KmAuditerStepAction extends BaseAction {

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
     * 新增知识管理审核步骤
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
     * 修改知识管理审核步骤
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
        KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        KmAuditerStep kmAuditerStep = kmAuditerStepMgr.getKmAuditerStep(id);
        KmAuditerStepForm kmAuditerStepForm = (KmAuditerStepForm) convert(kmAuditerStep);
        updateFormBean(mapping, request, kmAuditerStepForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存知识管理审核步骤
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
        KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
        KmAuditerStepForm kmAuditerStepForm = (KmAuditerStepForm) form;
        boolean isNew = (null == kmAuditerStepForm.getId() || "".equals(kmAuditerStepForm.getId()));
        KmAuditerStep kmAuditerStep = (KmAuditerStep) convert(kmAuditerStepForm);
        if (isNew) {
            kmAuditerStepMgr.saveKmAuditerStep(kmAuditerStep);
        } else {
            kmAuditerStepMgr.saveKmAuditerStep(kmAuditerStep);
        }
        return search(mapping, kmAuditerStepForm, request, response);
    }

    /**
     * 删除知识管理审核步骤
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
        KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        kmAuditerStepMgr.removeKmAuditerStep(id);
        return search(mapping, form, request, response);
    }

    /**
     * 分页显示知识管理审核步骤列表
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
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmAuditerStepConstants.KMAUDITERSTEP_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
        Map map = (Map) kmAuditerStepMgr.getKmAuditerSteps(pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute(KmAuditerStepConstants.KMAUDITERSTEP_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }


    /**
     * 分页显示知识管理待审核步骤列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getContentUnAuditList(ActionMapping mapping, ActionForm form,
                                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmAuditerStepConstants.KMAUDITERSTEP_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        ITawSystemUserRefRoleManager userRefRoleMgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
        List subRoleList = userRefRoleMgr.getSubRoleByUserId(userId, RoleConstants.ROLETYPE_SUBROLE);
        Map map = kmAuditerStepMgr.getContentUnAuditList(pageIndex, pageSize, userId, subRoleList);

        List list = (List) map.get("result");
        List stepList = new ArrayList();
        List ContentsList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            ContentsList.add((KmContents) obj[0]);
            stepList.add((KmAuditerStep) obj[1]);
        }
        request.setAttribute(KmContentsConstants.KMCONTENTS_LIST, ContentsList);
        request.setAttribute(KmAuditerStepConstants.KMAUDITERSTEP_LIST, stepList);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("contentUnAuditList");
    }


    /**
     * 分页显示知识管理待审核步骤列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getFileUnAuditList(ActionMapping mapping, ActionForm form,
                                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmAuditerStepConstants.KMAUDITERSTEP_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        ITawSystemUserRefRoleManager userRefRoleMgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
        List subRoleList = userRefRoleMgr.getSubRoleByUserId(userId, RoleConstants.ROLETYPE_SUBROLE);
        Map map = kmAuditerStepMgr.getFileUnAuditList(pageIndex, pageSize, userId, subRoleList);

        List list = (List) map.get("result");
        List stepList = new ArrayList();
        List fileList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            fileList.add((KmFile) obj[0]);
            stepList.add((KmAuditerStep) obj[1]);
        }
        request.setAttribute("kmFileList", fileList);
        request.setAttribute(KmAuditerStepConstants.KMAUDITERSTEP_LIST, stepList);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("fileUnAuditList");
    }
    /**
     * 分页显示知识管理审核步骤列表，支持Atom方式接入Portal
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception

    public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws Exception {
    try {
    // --------------用于分页，得到当前页号-------------
    final Integer pageIndex = new Integer(request
    .getParameter("pageIndex"));
    final Integer pageSize = new Integer(request
    .getParameter("pageSize"));
    KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
    Map map = (Map) kmAuditerStepMgr.getKmAuditerSteps(pageIndex, pageSize, "");
    List list = (List) map.get("result");
    KmAuditerStep kmAuditerStep = new KmAuditerStep();

    //创建ATOM源
    Factory factory = Abdera.getNewFactory();
    Feed feed = factory.newFeed();

    // 分页
    for (int i = 0; i < list.size(); i++) {
    kmAuditerStep = (KmAuditerStep) list.get(i);

    // TODO 请按照下面的实例给entry赋值
    Entry entry = feed.insertEntry();
    entry.setTitle("<a href='" + request.getScheme() + "://"
    + request.getServerName() + ":"
    + request.getServerPort()
    + request.getContextPath()
    + "/kmAuditerStep/kmAuditerSteps.do?method=edit&id="
    + kmAuditerStep.getId() + "' target='_blank'>"
    + display name for list + "</a>");
    entry.setSummary(summary);
    entry.setContent(content);
    entry.setLanguage(language);
    entry.setText(text);
    entry.setRights(tights);

    // 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
    entry.setUpdated(new java.util.Date());
    entry.setPublished(new java.util.Date());
    entry.setEdited(new java.util.Date());

    // 为person的name属性赋值，entry.addAuthor可以随意赋值
    Person person = entry.addAuthor(userId);
    person.setName(userName);
    }

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
     */

    /**
     * 知识管理审核页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward contentAudit(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        String kmId = StaticMethod.null2String(request.getParameter("id"));

        KmContents kmContents = kmContentsMgr.getKmContents(kmId);
        // 知识ID（主键）
        String ID = kmContents.getContentId();
        // 知识所属模型ID（外键）
        String TABLE_ID = kmContents.getTableId();
        // 知识模型所属分类ID（外键）
        String THEME_ID = kmContents.getThemeId();

        // 读取:知识内容详细信息

        Map kmContentsMap = kmContentsMgr.getKmContents(ID, TABLE_ID, THEME_ID);
        request.setAttribute("KmContents", kmContentsMap);

        // 读取:模型字段的定义
        KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
        List columnList = kmTableColumnMgr.getKmTableColumnsByTableId(TABLE_ID);
        request.setAttribute("KmTableColumnList", columnList);

        // 查询该知识的知识评论信息
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
        String whereStr = " where kmContentsOpinion.contentId = '" + contentId
                + "'";
        Map map = (Map) kmContentsOpinionMgr.getKmContentsOpinions(pageIndex,
                pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(KmContentsConstants.KMCONTENTSOPINION_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
        KmAuditer kmAuditer = kmAuditerMgr.getKmAuditerByTheme(THEME_ID);
        if (kmAuditer != null) {
            if (kmAuditer.getMasterAudit().equals("1030101")) {
                request.setAttribute("master", kmAuditer.getMasterId());
            }
            request.setAttribute("roleId", kmAuditer.getRoleId());
            request.setAttribute("kmId", contentId);
        }
        return mapping.findForward("contentAudit");
    }

    /**
     * 知识管理附件审核页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward fileAudit(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        KmFile file = fileMgr.getFile(id);
        // 若无此贴子则转向失败页面，不记录查看记录历史
        if (file == null || file.getId() == null) {
            ActionMessages messages = new ActionMessages();
            messages.add("", new ActionMessage("文件ID不存在", false));
            this.saveMessages(request, messages);
            return mapping.findForward("fail");
        }

        String nodeId = file.getNodeId();
        KmFileForm fileForm = (KmFileForm) convert(file);
        request.setAttribute("kmFileForm", fileForm);

        KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
        KmAuditer kmAuditer = kmAuditerMgr.getKmAuditerByNodeid(nodeId);
        if (kmAuditer != null) {
            if (kmAuditer.getMasterAudit().equals("1030101")) {
                request.setAttribute("master", kmAuditer.getMasterId());
            }
            request.setAttribute("roleId", kmAuditer.getRoleId());
            request.setAttribute("kmId", id);
        }
        return mapping.findForward("fileAudit");
    }

    /**
     * 知识管理内容审核
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward contentAuditDo(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();

        String auditResult = StaticMethod.null2String(request.getParameter("auditResult")); //审核结果
        String contentState = StaticMethod.null2String(request.getParameter("contentState"));
        String state = StaticMethod.null2String(request.getParameter("state"));
        String nextStep = StaticMethod.null2String(request.getParameter("nextStep"));
        String date = StaticMethod.getLocalString();

        String ID = StaticMethod.null2String(request.getParameter("TableInfo/ID"));
        String THEME_ID = StaticMethod.null2String(request.getParameter("TableInfo/THEME_ID"));
        String TABLE_ID = StaticMethod.null2String(request.getParameter("TableInfo/TABLE_ID"));
        String CONTENT_TITLE = StaticMethod.null2String(request.getParameter("TableInfo/CONTENT_TITLE"));
        String CONTENT_KEYS = StaticMethod.null2String(request.getParameter("TableInfo/CONTENT_KEYS"));

        // 处理：页面数据格式转换
        RequestContext reqContext = new RequestContext();
        reqContext.setEntityValue2("TableInfo/ID", ID);
        reqContext.setEntityValue2("TableInfo/THEME_ID", THEME_ID);
        reqContext.setEntityValue2("TableInfo/TABLE_ID", TABLE_ID);
        reqContext.setEntityValue2("TableInfo/CONTENT_TITLE", CONTENT_TITLE);
        reqContext.setEntityValue2("TableInfo/CONTENT_KEYS", CONTENT_KEYS);

        KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
        KmAuditer kmAuditer = kmAuditerMgr.getKmAuditerByTheme(THEME_ID);
        if (auditResult.equals("0")) {
            reqContext.setEntityValue2("TableInfo/CONTENT_STATUS", "1");
            if (nextStep.equals("1030102")) {//如果不需要向下流转则直接通过有效
                state = "2";
            }
        } else {
            if (contentState.equals("5")) {
                reqContext.setEntityValue2("TableInfo/CONTENT_STATUS", "6");
            } else if (contentState.equals("6") && kmAuditer.getMasterAudit().equals("1030101")) {
                reqContext.setEntityValue2("TableInfo/CONTENT_STATUS", "7");
            } else {
                reqContext.setEntityValue2("TableInfo/CONTENT_STATUS", "2");
                state = "2";
            }
            if (nextStep.equals("1030102")) {//如果不需要向下流转则直接通过有效
                reqContext.setEntityValue2("TableInfo/CONTENT_STATUS", "2");
                state = "2";
            }
        }

        // 执行：修改知识条目
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        kmContentsMgr.updateKmContentsForAudit(reqContext);

        // 查询：根据条件取知识管理审核步骤列表
        String whereStr = "kmId='" + ID + "' order by createTime desc";
        KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
        List list = kmAuditerStepMgr.getKmAuditerSteps(whereStr);
        for (int i = 0; i < list.size(); i++) {
            KmAuditerStep kmAuditerStep = (KmAuditerStep) list.get(0);
            kmAuditerStep.setOperateTime(date);
            kmAuditerStep.setState("2");
            kmAuditerStepMgr.saveKmAuditerStep(kmAuditerStep);
            kmAuditerStep = null;
        }

        Integer operateId = null;
        if (auditResult.equals("1")) {
            operateId = KmOperateDefine.KM_OPERATE_NAME_CONTENTS_AUDIT_OK;
        } else {
            operateId = KmOperateDefine.KM_OPERATE_NAME_CONTENTS_AUDIT_BACK;
        }

        String toOrgId = StaticMethod.nullObject2String(request.getParameter("toOrgId"));
        String toOrgType = StaticMethod.nullObject2String(request.getParameter("toOrgType"));
        String[] toOrgIds = toOrgId.split(",");
        String[] toOrgTypes = toOrgType.split(",");

        if (auditResult.equals("0") || nextStep.equals("1030102")) {
            KmAuditerStep kmAuditerStepNew = new KmAuditerStep();
            kmAuditerStepNew.setCreateTime(date);
            if (state.equals("2")) {
                kmAuditerStepNew.setOperateTime(date);
            }
            kmAuditerStepNew.setKmId(ID);
            kmAuditerStepNew.setOperateType("user");//类型为个人
            kmAuditerStepNew.setOperateId(StaticMethod.nullObject2String(operateUserId));
            kmAuditerStepNew.setToOrgType("user");
            kmAuditerStepNew.setToOrgId(StaticMethod.nullObject2String(request.getParameter("createUser")));//类型为个人
            kmAuditerStepNew.setAuditResult(auditResult);
            kmAuditerStepNew.setRemark(StaticMethod.nullObject2String(request.getParameter("remark")));
            kmAuditerStepNew.setState(state);
            kmAuditerStepMgr.saveKmAuditerStep(kmAuditerStepNew);

            // 记录:操作记录表-知识审核操作
            KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
            kmOperateLogMgr.saveKmOperateLog(THEME_ID, TABLE_ID, ID,
                    operateId,
                    operateUserId, operateDeptId, operateUserId);
            // 记录：日操作记录表-知识审核操作
            KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
            kmOperateDateLogMgr.saveKmOperateDateLog(new Date(),
                    operateId,
                    operateUserId, operateDeptId);
            return mapping.findForward("contentUnAudit");
        }

        if (!nextStep.equals("1030102")) {
            for (int i = 0; i < toOrgIds.length; i++) {
                KmAuditerStep kmAuditerStepNew = new KmAuditerStep();
                kmAuditerStepNew.setCreateTime(date);
                if (state.equals("2")) {
                    kmAuditerStepNew.setOperateTime(date);
                }
                kmAuditerStepNew.setKmId(ID);
                kmAuditerStepNew.setOperateType("user");//类型为个人
                kmAuditerStepNew.setOperateId(StaticMethod.nullObject2String(operateUserId));
                kmAuditerStepNew.setToOrgId(toOrgIds[i]);//类型为个人
                kmAuditerStepNew.setToOrgType(toOrgTypes[i]);
                kmAuditerStepNew.setAuditResult(StaticMethod.nullObject2String(request.getParameter("auditResult")));
                kmAuditerStepNew.setRemark(StaticMethod.nullObject2String(request.getParameter("remark")));
                kmAuditerStepNew.setState(state);
                kmAuditerStepMgr.saveKmAuditerStep(kmAuditerStepNew);

                // 记录:操作记录表-知识审核操作
                KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
                kmOperateLogMgr.saveKmOperateLog(THEME_ID, TABLE_ID, ID,
                        operateId,
                        operateUserId, operateDeptId, operateUserId);
                // 记录：日操作记录表-知识审核操作
                KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
                kmOperateDateLogMgr.saveKmOperateDateLog(new Date(),
                        operateId,
                        operateUserId, operateDeptId);
            }
        }
        //增加提醒短信功能
        if (state.equals("1")) {
            String cruser = "";
            for (int i = 0; i < toOrgIds.length; i++) {
                if (!cruser.equals("")) {
                    cruser = cruser + "#";
                }
                if (toOrgTypes[i].equals("user")) {
                    cruser = cruser + "1," + toOrgIds[i];
                } else if (toOrgTypes[i].equals("subrole")) {
                    cruser = cruser + "3," + toOrgIds[i];
                }

            }
            kmAuditerStepMgr.sendSMS(cruser, "content", ID);

        }
        return mapping.findForward("contentUnAudit");
    }

    /**
     * 知识管理附件审核
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward fileAuditDo(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 读取：当前操作用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateDeptId = sessionform.getDeptid();
        String operateUserId = sessionform.getUserid();

        String auditResult = StaticMethod.null2String(request.getParameter("auditResult"));
        String fileState = StaticMethod.null2String(request.getParameter("fileState"));
        String state = StaticMethod.null2String(request.getParameter("state"));
        String nextStep = StaticMethod.null2String(request.getParameter("nextStep"));
        String date = StaticMethod.getLocalString();
        String ID = StaticMethod.null2String(request.getParameter("id"));

        KmFileMgr fileMgr = (KmFileMgr) getBean("kmFileMgr");
        KmFile file = fileMgr.getFile(ID);

        String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));

        KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
        KmAuditer kmAuditer = kmAuditerMgr.getKmAuditerByNodeid(nodeId);
        if (auditResult.equals("0")) {
            file.setState("1");
            if (nextStep.equals("1030102")) {//如果不需要向下流转则直接通过有效
                state = "2";
            }
        } else {
            if (fileState.equals("5")) {
                file.setState("6");
            } else if (fileState.equals("6") && kmAuditer.getMasterAudit().equals("1030101")) {
                file.setState("7");
            } else {
                file.setState("2");
                state = "2";
            }
            if (nextStep.equals("1030102")) {//如果不需要向下流转则直接通过有效
                file.setState("2");
                state = "2";
            }
        }
        // 执行：修改知识条目
        fileMgr.saveFile(file);

        // 查询：根据条件取知识管理审核步骤列表
        String whereStr = "kmId = '" + ID + "' order by createTime desc";
        KmAuditerStepMgr kmAuditerStepMgr = (KmAuditerStepMgr) getBean("kmAuditerStepMgr");
        List list = kmAuditerStepMgr.getKmAuditerSteps(whereStr);
        for (int i = 0; i < list.size(); i++) {
            KmAuditerStep kmAuditerStep = (KmAuditerStep) list.get(0);
            kmAuditerStep.setOperateTime(date);
            kmAuditerStep.setState("2");
            kmAuditerStepMgr.saveKmAuditerStep(kmAuditerStep);
            kmAuditerStep = null;
        }

        Integer operateId = null;
        if (auditResult.equals("1")) {
            operateId = KmOperateDefine.KM_OPERATE_NAME_CONTENTS_AUDIT_OK;
        } else {
            operateId = KmOperateDefine.KM_OPERATE_NAME_CONTENTS_AUDIT_BACK;
        }

        String toOrgId = StaticMethod.nullObject2String(request.getParameter("toOrgId"));
        String toOrgType = StaticMethod.nullObject2String(request.getParameter("toOrgType"));
        String[] toOrgIds = toOrgId.split(",");
        String[] toOrgTypes = toOrgType.split(",");

        if (auditResult.equals("0") || nextStep.equals("1030102")) {
            KmAuditerStep kmAuditerStepNew = new KmAuditerStep();
            kmAuditerStepNew.setCreateTime(date);
            if (state.equals("2")) {
                kmAuditerStepNew.setOperateTime(date);
            }
            kmAuditerStepNew.setKmId(ID);
            kmAuditerStepNew.setOperateType("user");//类型为个人
            kmAuditerStepNew.setOperateId(StaticMethod.nullObject2String(operateUserId));
            kmAuditerStepNew.setToOrgId(StaticMethod.nullObject2String(request.getParameter("createUser")));//类型为个人
            kmAuditerStepNew.setToOrgType("user");
            kmAuditerStepNew.setAuditResult(StaticMethod.nullObject2String(request.getParameter("auditResult")));
            kmAuditerStepNew.setRemark(StaticMethod.nullObject2String(request.getParameter("remark")));
            kmAuditerStepNew.setState(state);
            kmAuditerStepMgr.saveKmAuditerStep(kmAuditerStepNew);

            // 记录:操作记录表-附件审核操作
            KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
            kmOperateLogMgr.saveKmOperateLog(file.getNodeId(), "KMFILE", file.getId(),
                    operateId,
                    operateUserId, operateDeptId, operateUserId);
            // 记录：日操作记录表-附件审核操作
            KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
            kmOperateDateLogMgr.saveKmOperateDateLog(new Date(),
                    operateId,
                    operateUserId, operateDeptId);

            return mapping.findForward("fileUnAudit");
        }
        if (!nextStep.equals("1030102")) {
            for (int i = 0; i < toOrgIds.length; i++) {
                KmAuditerStep kmAuditerStepNew = new KmAuditerStep();
                kmAuditerStepNew.setCreateTime(date);
                if (state.equals("2")) {
                    kmAuditerStepNew.setOperateTime(date);
                }
                kmAuditerStepNew.setKmId(ID);
                kmAuditerStepNew.setOperateType("user");//类型为个人
                kmAuditerStepNew.setOperateId(StaticMethod.nullObject2String(operateUserId));
                kmAuditerStepNew.setToOrgId(toOrgIds[i]);//类型为个人
                kmAuditerStepNew.setToOrgType(toOrgTypes[i]);
                kmAuditerStepNew.setAuditResult(StaticMethod.nullObject2String(request.getParameter("auditResult")));
                kmAuditerStepNew.setRemark(StaticMethod.nullObject2String(request.getParameter("remark")));
                kmAuditerStepNew.setState(state);
                kmAuditerStepMgr.saveKmAuditerStep(kmAuditerStepNew);

                // 记录:操作记录表-附件审核操作
                KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
                kmOperateLogMgr.saveKmOperateLog(file.getNodeId(), "KMFILE", file.getId(),
                        operateId,
                        operateUserId, operateDeptId, operateUserId);
                // 记录：日操作记录表-附件审核操作
                KmOperateDateLogMgr kmOperateDateLogMgr = (KmOperateDateLogMgr) getBean("kmOperateDateLogMgr");
                kmOperateDateLogMgr.saveKmOperateDateLog(new Date(),
                        operateId,
                        operateUserId, operateDeptId);
            }
        }
        //增加提醒短信功能
        if (state.equals("1")) {
            String cruser = "";
            for (int i = 0; i < toOrgIds.length; i++) {
                if (!cruser.equals("")) {
                    cruser = cruser + "#";
                }
                if (toOrgTypes[i].equals("user")) {
                    cruser = cruser + "1," + toOrgIds[i];
                } else if (toOrgTypes[i].equals("subrole")) {
                    cruser = cruser + "3," + toOrgIds[i];
                }

            }
            kmAuditerStepMgr.sendSMS(cruser, "file", file.getId());

        }
        return mapping.findForward("fileUnAudit");
    }
}