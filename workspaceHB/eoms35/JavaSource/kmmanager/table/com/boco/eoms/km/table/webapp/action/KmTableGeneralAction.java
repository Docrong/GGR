package com.boco.eoms.km.table.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
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

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.km.table.mgr.KmTableGeneralMgr;
import com.boco.eoms.km.table.model.KmTableColumn;
import com.boco.eoms.km.table.model.KmTableGeneral;
import com.boco.eoms.km.table.util.KmTableGeneralConstants;
import com.boco.eoms.km.table.util.KmTableUtil;
import com.boco.eoms.km.table.webapp.form.KmTableGeneralForm;

/**
 * <p>
 * Title:模块信息表
 * </p>
 * <p>
 * Description:模块信息表
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 *
 * @moudle.getAuthor() 吕卫华
 * @moudle.getVersion() 1.0
 */
public final class KmTableGeneralAction extends BaseAction {

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
     * 模型分类表树页面
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
        request.getSession().setAttribute("kmTableGeneralForm", form);
        request.setAttribute("tableChname", request.getParameter("tableChname"));
        request.setAttribute("kmTableColumnForm.tableId", request.getParameter("id"));
        request.setAttribute("tableId", request.getParameter("id"));
        return mapping.findForward("tree");

    }

    /**
     * 模型分类表树页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward createModel(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));

        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        KmTableGeneral kmTableGeneral = kmTableGeneralMgr.getKmTableGeneral(nodeId);

        KmTableGeneralForm kmTableGeneralForm = (KmTableGeneralForm) request.getSession().getAttribute("kmTableGeneralForm");
        if (kmTableGeneralForm != null) {
            if (null == kmTableGeneral.getId() || "".equals(kmTableGeneral.getId())) {
                Date createTime = StaticMethod.getLocalTime();
                TawSystemSessionForm tawSystemSessionForm = this.getUser(request);
                kmTableGeneral = (KmTableGeneral) convert(kmTableGeneralForm);
                kmTableGeneral.setCreateTime(createTime);
                kmTableGeneral.setCreateDept(tawSystemSessionForm.getDeptid());
                kmTableGeneral.setCreateUser(tawSystemSessionForm.getUserid());
                String tableName = kmTableGeneral.getTableName();
                if (tableName == null || tableName.equals("")) {
                    kmTableGeneral.setTableName("KM_CONTENTS_" + System.currentTimeMillis());
                }
                kmTableGeneral.setIsCreated("0");
                kmTableGeneralMgr.saveKmTableGeneral(kmTableGeneral);
            } else {
                kmTableGeneral.setOrderBy(kmTableGeneralForm.getOrderBy());
                kmTableGeneral.setIsOpen(kmTableGeneralForm.getIsOpen());
                kmTableGeneral.setRemark(kmTableGeneralForm.getRemark());
            }
        }

        // 数据库类型
        if (kmTableGeneral != null && !kmTableGeneral.getTableName().equals("")) {
            String type = ApplicationContextHolder.getInstance().getHQLDialect();
            if ("org.hibernate.dialect.OracleDialect".equals(type)) {//oracle
                if (kmTableGeneralMgr.HasTable(kmTableGeneral.getTableName().toUpperCase())) {
                    kmTableGeneralMgr.modifyModelForOracle(kmTableGeneral);
                } else {
                    kmTableGeneralMgr.createModelForOracle(kmTableGeneral);
                }
            } else if ("org.hibernate.dialect.InformixDialect".equals(type)) {//informix
                if (kmTableGeneralMgr.HasTable(kmTableGeneral.getTableName().toLowerCase())) {
                    kmTableGeneralMgr.modifyModelForInformix(kmTableGeneral);
                } else {
                    kmTableGeneralMgr.createModelForInformix(kmTableGeneral);
                }
            }
        }

        kmTableGeneral.setIsCreated("1");
        kmTableGeneralMgr.saveKmTableGeneral(kmTableGeneral);

        return mapping.findForward("success");
        // return search(mapping, form, request, response);
    }

    /**
     * 生成模型分类表树JSON数据
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
        if ("1".equals(nodeId)) {
            nodeId = StaticMethod.null2String(request.getParameter("tableId"));
        }
        JSONArray jsonRoot = new JSONArray();
        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        // 取下级节点
        List list = kmTableGeneralMgr.getNextLevelKmTableGenerals(nodeId);
        for (Iterator nodeIter = list.iterator(); nodeIter.hasNext(); ) {
            KmTableColumn kmTableColumn = (KmTableColumn) nodeIter.next();
            JSONObject jitem = new JSONObject();
            jitem.put("id", kmTableColumn.getId());
            //设置节点名称
            if (kmTableColumn.getOrderBy() != null && kmTableColumn.getOrderBy().intValue() < 10) {
                jitem.put("text", "[0" + kmTableColumn.getOrderBy() + "]" + kmTableColumn.getColChname());
            } else {
                jitem.put("text", "[" + kmTableColumn.getOrderBy() + "]" + kmTableColumn.getColChname());
            }
            // 设置右键菜单
            jitem.put("allowChild", false);
            jitem.put("allowEdit", true);
            jitem.put("allowDelete", true);
            // 设置左键点击可触发action
            jitem.put("allowClick", true);
            // 设置是否为叶子节点
            boolean leafFlag = true;
            // if (kmTableGeneralMgr.isHasNextLevel(kmTableGeneral.getNodeId()))
            // {
            // leafFlag = false;
            // }
            jitem.put("leaf", leafFlag);
            // TODO 设置鼠标悬浮提示
            // jitem.put("qtip", your tips here);
            jsonRoot.put(jitem);
        }
        JSONUtil.print(response, jsonRoot.toString());
        return null;
    }

    /**
     * 获取模型分类树(单选)
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
        // 获取节点id
        String nodeId = request.getParameter("node");
        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        // 取下级节点
        List list = kmTableGeneralMgr.getNextLevelKmTableGenerals(nodeId);
        // json根（树根节点）
        JSONArray root = new JSONArray();
        // 遍历子版块
        if (null != list) {
            for (Iterator nodeIter = list.iterator(); nodeIter.hasNext(); ) {
                KmTableGeneral forum = (KmTableGeneral) nodeIter.next();
                JSONObject item = new JSONObject();
                item.put("id", forum.getId()); // 添加节点ID
                item.put("text", forum.getTableChname()); // 添加节点名称
                item.put(UIConstants.JSON_NODETYPE, "folder");
                // item.put("allowChild", true); // 设置右键菜单
                // item.put("allowEdit", true);
                // item.put("allowDelete", true);

                item.put("iconCls", "folder");
                item.put("qtip", forum.getTableChname());
                // item.put("allowNewThread", true);
                // item.put("allowListThreads", true);
                // item.put("allowListUnreadThreads", true);
                // item.put(UIConstants.JSON_NODETYPE,
                // InfopubConstants.NODETYPE_INFOPUB_FORUMS);

                // 设置是否为叶子节点
                boolean leafFlag = true;
                // if (kmTableGeneralMgr.isHasNextLevel(forum.getNodeId())) {
                // leafFlag = false;
                // }
                item.put("leaf", leafFlag);

                // 设置叶子节点可以被选择
                if (leafFlag) {
                    item.put("checked", false);
                }
                root.put(item);
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(root.toString());
    }

    /**
     * 新增模块信息表
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
        // request.setAttribute("KmTableTheme", tableTheme);
        KmTableGeneralForm kmTableGeneralForm = new KmTableGeneralForm();
        kmTableGeneralForm.setIsVisibl("1");
        kmTableGeneralForm.setIsOpen("1");
        kmTableGeneralForm.setIsAudit("0");
        updateFormBean(mapping, request, kmTableGeneralForm);
        return mapping.findForward("edit");
    }

    /**
     * 下一步，转向模型字段定义新增
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward next(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // request.setAttribute("kmTableGeneralForm",form);
        request.getSession().setAttribute("kmTableGeneralForm", form);
        // return mapping.findForward("next");
        return new ActionForward("/kmTableColumns.do?method=add", false);
    }

    /**
     * 修改模块信息表
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
        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        KmTableGeneral kmTableGeneral = kmTableGeneralMgr.getKmTableGeneral(id);
        KmTableGeneralForm kmTableGeneralForm = (KmTableGeneralForm) convert(kmTableGeneral);
        updateFormBean(mapping, request, kmTableGeneralForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存模块信息表
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
        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        KmTableGeneralForm kmTableGeneralForm = (KmTableGeneralForm) form;
        boolean isNew = (null == kmTableGeneralForm.getId() || ""
                .equals(kmTableGeneralForm.getId()));
        KmTableGeneral kmTableGeneral = (KmTableGeneral) convert(kmTableGeneralForm);

        Date createTime = StaticMethod.getLocalTime();
        kmTableGeneral.setCreateTime(createTime);
        // kmTableGeneral.setThemeId("10000");

        TawSystemSessionForm tawSystemSessionForm = this.getUser(request);
        kmTableGeneral.setCreateDept(tawSystemSessionForm.getDeptid());
        kmTableGeneral.setCreateUser(tawSystemSessionForm.getUserid());

        if (isNew) {
            kmTableGeneralMgr.saveKmTableGeneral(kmTableGeneral);
        } else {
            kmTableGeneralMgr.saveKmTableGeneral(kmTableGeneral);
        }
        return search(mapping, kmTableGeneralForm, request, response);
    }

    /**
     * 根据模型id删除模型定义、模型字段，并解除与模型绑定的模型分类
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
        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        //根据模型id删除模型定义、模型字段，并解除与模型绑定的模型分类
        kmTableGeneralMgr.removeKmTableGeneral(id);
        return search(mapping, form, request, response);
    }

    /**
     * 分页显示模块信息表列表
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
                KmTableGeneralConstants.KMTABLEGENERAL_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        Map map = (Map) kmTableGeneralMgr.getKmTableGenerals(pageIndex, pageSize);
        List list = (List) map.get("result");
        request.setAttribute(KmTableGeneralConstants.KMTABLEGENERAL_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 根据查询条件分页显示模块信息表列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchX(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmTableGeneralConstants.KMTABLEGENERAL_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmTableGeneralForm kmTableGeneralForm = (KmTableGeneralForm) form;
        String whereStr = KmTableUtil.getQueryCondition(kmTableGeneralForm);
        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        Map map = (Map) kmTableGeneralMgr.getKmTableGenerals(pageIndex,
                pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(KmTableGeneralConstants.KMTABLEGENERAL_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("kmTableGeneralForm", kmTableGeneralForm);
        return mapping.findForward("list");
    }

    /**
     * 分页显示模块信息表列表，支持Atom方式接入Portal
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
            KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
            Map map = (Map) kmTableGeneralMgr.getKmTableGenerals(pageIndex,
                    pageSize, "");
            List list = (List) map.get("result");
            KmTableGeneral kmTableGeneral = new KmTableGeneral();

            // 创建ATOM源
            Factory factory = Abdera.getNewFactory();
            Feed feed = factory.newFeed();

            // 分页
            // for (int i = 0; i < list.size(); i++) {
            // kmTableGeneral = (KmTableGeneral) list.get(i);
            //
            // // TODO 请按照下面的实例给entry赋值
            // Entry entry = feed.insertEntry();
            // entry.setTitle("<a href='" + request.getScheme() + "://"
            // + request.getServerName() + ":"
            // + request.getServerPort()
            // + request.getContextPath()
            // + "/kmTableGeneral/kmTableGenerals.do?method=edit&id="
            // + kmTableGeneral.getId() + "' target='_blank'>"
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
}