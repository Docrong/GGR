package com.boco.eoms.system.mappingstorage.webapp.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.EOMSAttributes;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.Sequence;
import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;


import com.boco.eoms.system.mappingstorage.mgr.MappingJdbcMgr;
import com.boco.eoms.system.mappingstorage.mgr.MappingMgr;
import com.boco.eoms.system.mappingstorage.model.Mapping;
import com.boco.eoms.system.mappingstorage.util.MappingConstants;
import com.boco.eoms.system.mappingstorage.webapp.form.MappingForm;

/**
 * <p>
 * Title:存储映射
 * </p>
 * <p>
 * Description:存储映射
 * </p>
 * <p>
 * Wed Apr 08 09:10:47 CST 2009
 * </p>
 *
 * @moudle.getAuthor() sam
 * @moudle.getVersion() 1.0
 */
public final class MappingAction extends BaseAction {

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
     * 新增存储映射
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
        return mapping.findForward("add");
    }

    /**
     * 修改存储映射
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
        MappingMgr mappingMgr = (MappingMgr) getBean("mappingMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        Mapping mappings = mappingMgr.getMapping(id);
        MappingForm mappingForm = (MappingForm) convert(mappings);
        updateFormBean(mapping, request, mappingForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存存储映射
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
        MappingMgr mappingMgr = (MappingMgr) getBean("mappingMgr");
        MappingForm mappingForm = (MappingForm) form;
        boolean isNew = (null == mappingForm.getId() || "".equals(mappingForm.getId()));
        Mapping mapping2 = (Mapping) convert(mappingForm);
        if (isNew) {
            mappingMgr.saveMapping(mapping2);
            String dbType = ApplicationContextHolder.getInstance().getHQLDialect();
            //GenTableMgr mgr = (GenTableMgr) getBean("gentable");
            MappingJdbcMgr mgr = (MappingJdbcMgr) getBean("mappingJdbcMgr");
            mgr.genTable(mappingForm.getNew_table(), dbType);

        } else {

            mappingMgr.saveMapping(mapping2);

        }

        return search(mapping, mappingForm, request, response);
    }

    /**
     * 删除存储映射
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
        MappingMgr mappingMgr = (MappingMgr) getBean("mappingMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));

        Mapping mapp = mappingMgr.getMapping(id);
        mapp.setDeleted("1");
        mappingMgr.saveMapping(mapp);

        return mapping.findForward("success");
    }

    /**
     * 分页显示存储映射列表
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
                MappingConstants.MAPPING_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        MappingMgr mappingMgr = (MappingMgr) getBean("mappingMgr");
        Map map = (Map) mappingMgr.getMappings(pageIndex, pageSize, " where mapping.deleted='0'");
        List list = (List) map.get("result");
        request.setAttribute(MappingConstants.MAPPING_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 分页显示存储映射列表，支持Atom方式接入Portal
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @throws Exception
     */
    public ActionForward insertValue(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 初始化mgr
        //InsertValueMgr mgr = (InsertValueMgr) getBean("insertValue");
        MappingJdbcMgr mgr = (MappingJdbcMgr) getBean("mappingJdbcMgr");
        String appcode = request.getParameter("appCode");
        String sheetkey = request.getParameter("sheetKey");
        String rootId = request.getParameter("rootId");
        String dictid = request.getParameter("dictId");
        String sequenceOpen = StaticMethod
                .null2String(((EOMSAttributes) ApplicationContextHolder
                        .getInstance().getBean("eomsAttributes"))
                        .getSequenceOpen());
        if ("true".equals(sequenceOpen)) {
            // 初始化队列
            ISequenceFacade sequenceFacade = SequenceLocator
                    .getSequenceFacade();
            Sequence mappingSequence = null;
            try {
                mappingSequence = sequenceFacade.getSequence("mapping");
            } catch (SequenceNotFoundException e) {
                e.printStackTrace();
            }
            // 把mgr撇队列里
            sequenceFacade.put(mgr, "insertValue", null, null, null,
                    mappingSequence);
            mappingSequence.setChanged();
            sequenceFacade.doJob(mappingSequence);
        } else {
            try {
                mgr.insertValue(appcode, sheetkey, rootId, dictid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward("success");
    }

    public String checkunique(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {

        String tablename = request.getParameter("name");

        MappingMgr mgr = (MappingMgr) getBean("mappingMgr");
        String status = mgr.checkUnique(tablename);
        JSONUtil.print(response, status);
        return null;

    }

}