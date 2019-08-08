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
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;
import org.apache.axiom.om.util.UUIDGenerator;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.file.mgr.KmFileTreeMgr;
import com.boco.eoms.km.table.mgr.KmTableColumnMgr;
import com.boco.eoms.km.table.mgr.KmTableGeneralMgr;
import com.boco.eoms.km.table.mgr.KmTableThemeMgr;
import com.boco.eoms.km.table.model.KmTableColumn;
import com.boco.eoms.km.table.model.KmTableGeneral;
import com.boco.eoms.km.table.model.KmTableTheme;
import com.boco.eoms.km.table.util.KmTableColumnConstants;
import com.boco.eoms.km.table.webapp.form.KmTableColumnForm;
import com.boco.eoms.km.table.webapp.form.KmTableGeneralForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:模型字段定义表
 * </p>
 * <p>
 * Description:模型字段表
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 *
 * @moudle.getAuthor() 吕卫华
 * @moudle.getVersion() 1.0
 */
public final class KmTableColumnAction extends BaseAction {

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
     * 新增模型定义
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangxb
     */
    public ActionForward addLayout(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String TABLE_ID = StaticMethod.null2String(request.getParameter("TABLE_ID"));

        // 读取：模型字段的定义列表
        KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
        List columnList = kmTableColumnMgr.getKmTableColumnsByTableId(TABLE_ID);
        request.setAttribute("KmTableColumnList", columnList);
        request.setAttribute("TABLE_ID", TABLE_ID);
        return mapping.findForward("addLayout");
    }


    /**
     * 保存模型定义
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangxb
     */
    public ActionForward saveLayout(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String result = StaticMethod.null2String(request.getParameter("result"));
        //String tableId = StaticMethod.null2String(request.getParameter("TABLE_ID"));
        JSONArray data = JSONArray.fromString(result);
//		KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
//		KmTableGeneral kmTableGeneral = kmTableGeneralMgr.getKmTableGeneral(tableId);
        KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
        for (Iterator iter = data.iterator(); iter.hasNext(); ) {
            JSONObject obj = (JSONObject) iter.next();
            String id = obj.getString("id");
            KmTableColumn kmTableColumn = kmTableColumnMgr.getKmTableColumn(id);
            int orderBy = StaticMethod.null2int(obj.getString("pos"));
            kmTableColumn.setOrderBy(new Integer(orderBy));
            kmTableColumn.setMark(obj.getString("mark"));
            kmTableColumnMgr.saveKmTableColumn(kmTableColumn);
        }
        return mapping.findForward("success1");

    }

    /**
     * 新增模型字段定义表
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
        String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
        request.setAttribute("nodeId", nodeId);

        KmTableColumnForm kmTableColumnForm = new KmTableColumnForm();
        kmTableColumnForm.setColDictType("0");
        kmTableColumnForm.setColType("0");
        kmTableColumnForm.setIsIndex("0");
        kmTableColumnForm.setIsNullable("1");
        kmTableColumnForm.setIsOpen("1");
        kmTableColumnForm.setIsUnique("0");
        kmTableColumnForm.setIsVisibl("1");
        kmTableColumnForm.setIsDeleted("1");
        updateFormBean(mapping, request, kmTableColumnForm);

        // 根据模型主键查询所有字段类型是单选字典的字段
        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        List subColList = kmTableGeneralMgr.getSubDictColumn(nodeId);
        request.setAttribute("subColList", subColList);
        return mapping.findForward("add");
    }

    /**
     * 修改模型字段定义表
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
        String id = StaticMethod.null2String(request.getParameter("nodeId"));

        KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
        KmTableColumn kmTableColumn = kmTableColumnMgr.getKmTableColumn(id);

        KmTableColumnForm kmTableColumnForm = (KmTableColumnForm) convert(kmTableColumn);
        updateFormBean(mapping, request, kmTableColumnForm);

        //为字段colDictId创建json数据
        int colDictType = kmTableColumn.getColDictType().intValue();
        String colDictId = String.valueOf(kmTableColumn.getColDictId());
        String name = "";
        if (colDictType == 1) {
            ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
            name = mgr.getDictByDictId(colDictId).getDictName();
        } else if (colDictType == 2) {
            KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
            name = kmTableThemeMgr.getKmTableThemeByNodeId(colDictId).getThemeName();
        } else if (colDictType == 3) {
            //取得文件名称
            KmFileTreeMgr fileMgr = (KmFileTreeMgr) getBean("kmFileTreeMgr");
            name = fileMgr.getKmFileTreeByNodeId(colDictId).getNodeName();
        }

        JSONObject data = new JSONObject();
        data.put("name", name);
        data.put("id", colDictId);

        JSONArray root = new JSONArray();
        root.put(data);
        request.setAttribute("data", root);

        // 根据模型主键查询所有字段类型是单选字典的字段
        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        List subColList = kmTableGeneralMgr.getSubDictColumn(kmTableColumn.getTableId());
        int subColListSize = subColList.size();
        for (int i = 0; i < subColListSize; i++) {
            KmTableColumn subCol = (KmTableColumn) subColList.get(i);
            if (subCol.getId().equals(kmTableColumn.getId())) {
                subColList.remove(i);
                i = subColListSize;
            }
        }
        request.setAttribute("subColList", subColList);

        return mapping.findForward("edit");
    }

    /**
     * 保存模型字段定义表
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
        Date createTime = StaticMethod.getLocalTime();
        TawSystemSessionForm tawSystemSessionForm = this.getUser(request);
        String tableId = StaticMethod.null2String(request.getParameter("tableId"));
        if ("".equals(tableId)) {
            tableId = StaticMethod.null2String(request.getParameter("nodeId"));
        }

        KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
        KmTableGeneralForm kmTableGeneralForm = (KmTableGeneralForm) request.getSession().getAttribute("kmTableGeneralForm");

        KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
        KmTableColumnForm kmTableColumnForm = (KmTableColumnForm) form;

        if (kmTableGeneralForm == null) {
        } else {
            request.getSession().removeAttribute("kmTableGeneralForm");
            boolean isNew0 = (null == kmTableGeneralForm.getId() || "".equals(kmTableGeneralForm.getId()));
            KmTableGeneral kmTableGeneral = (KmTableGeneral) convert(kmTableGeneralForm);
            kmTableGeneral.setCreateTime(createTime);
            kmTableGeneral.setCreateDept(tawSystemSessionForm.getDeptid());
            kmTableGeneral.setCreateUser(tawSystemSessionForm.getUserid());

            if (isNew0) {
                String tableName = kmTableGeneral.getTableName();
                if (tableName == null || tableName.equals("")) {
                    kmTableGeneral.setTableName("KM_CONTENTS_" + System.currentTimeMillis());
                }
                kmTableGeneral.setIsCreated("0");
                kmTableGeneralMgr.saveKmTableGeneral(kmTableGeneral);
            } else {
                kmTableGeneralMgr.saveKmTableGeneral(kmTableGeneral);
            }
            tableId = kmTableGeneral.getId();
        }

        boolean isNew = (null == kmTableColumnForm.getId() || "".equals(kmTableColumnForm.getId()));
        KmTableColumn kmTableColumn = (KmTableColumn) convert(kmTableColumnForm);
        kmTableColumn.setTableId(tableId);
        kmTableColumn.setCreateTime(createTime);
        kmTableColumn.setCreateDept(tawSystemSessionForm.getDeptid());
        kmTableColumn.setCreateUser(tawSystemSessionForm.getUserid());

        if (isNew) {
            long currentTimeMillis = System.currentTimeMillis();
            kmTableColumn.setColName("COL_" + currentTimeMillis);
            kmTableColumnMgr.saveKmTableColumn(kmTableColumn);
        } else {
            kmTableColumnMgr.saveKmTableColumn(kmTableColumn);
        }

        request.setAttribute("id", tableId);
        return mapping.findForward("success1");
    }

    /**
     * 删除模型字段定义表
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
        KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
        String id = StaticMethod.null2String(request.getParameter("nodeId"));

        if ("".equals(id)) {
            id = StaticMethod.null2String(request.getParameter("id"));
        }
        KmTableColumn kmTableColumn = kmTableColumnMgr.getKmTableColumn(id);
        kmTableColumnMgr.removeKmTableColumn(id);
        request.setAttribute("id", kmTableColumn.getTableId());
        return mapping.findForward("success1");
    }

    /**
     * 分页显示模型字段定义表列表
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
                KmTableColumnConstants.KMTABLECOLUMN_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
        Map map = (Map) kmTableColumnMgr.getKmTableColumns(pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute(KmTableColumnConstants.KMTABLECOLUMN_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 分页显示模型字段定义表列表，支持Atom方式接入Portal
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
            KmTableColumnMgr kmTableColumnMgr = (KmTableColumnMgr) getBean("kmTableColumnMgr");
            Map map = (Map) kmTableColumnMgr.getKmTableColumns(pageIndex, pageSize, "");
            List list = (List) map.get("result");
            KmTableColumn kmTableColumn = new KmTableColumn();

            //创建ATOM源
            Factory factory = Abdera.getNewFactory();
            Feed feed = factory.newFeed();

            // 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmTableColumn = (KmTableColumn) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmTableColumn/kmTableColumns.do?method=edit&id="
//						+ kmTableColumn.getId() + "' target='_blank'>"
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