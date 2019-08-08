package com.boco.eoms.partdata.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.partdata.mgr.TawpartLacBscMgr;
import com.boco.eoms.partdata.model.TawpartLacBsc;
import com.boco.eoms.partdata.util.TawpartLacBscConstants;
import com.boco.eoms.partdata.webapp.form.TawpartLacBscForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:LAC的BSC分配
 * </p>
 * <p>
 * Description:LAC的BSC分配
 * </p>
 * <p>
 * Mon Jul 12 17:27:34 CST 2010
 * </p>
 *
 * @moudle.getAuthor() fengshaohong
 * @moudle.getVersion() 3.6
 */
public final class TawpartLacBscAction extends BaseAction {

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
     * 新增LAC的BSC分配
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
     * 修改LAC的BSC分配
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
        TawpartLacBscMgr tawpartLacBscMgr = (TawpartLacBscMgr) getBean("tawpartLacBscMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        TawpartLacBsc tawpartLacBsc = tawpartLacBscMgr.getTawpartLacBsc(id);
        TawpartLacBscForm tawpartLacBscForm = (TawpartLacBscForm) convert(tawpartLacBsc);
        updateFormBean(mapping, request, tawpartLacBscForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存LAC的BSC分配
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
        TawpartLacBscMgr tawpartLacBscMgr = (TawpartLacBscMgr) getBean("tawpartLacBscMgr");
        TawpartLacBscForm tawpartLacBscForm = (TawpartLacBscForm) form;
        boolean isNew = (null == tawpartLacBscForm.getId() || "".equals(tawpartLacBscForm.getId()));
        TawpartLacBsc tawpartLacBsc = (TawpartLacBsc) convert(tawpartLacBscForm);
        if (isNew) {
            tawpartLacBscMgr.saveTawpartLacBsc(tawpartLacBsc);
        } else {
            tawpartLacBscMgr.saveTawpartLacBsc(tawpartLacBsc);
        }
        return search(mapping, tawpartLacBscForm, request, response);
    }

    /**
     * 删除LAC的BSC分配
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
        TawpartLacBscMgr tawpartLacBscMgr = (TawpartLacBscMgr) getBean("tawpartLacBscMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        tawpartLacBscMgr.removeTawpartLacBsc(id);
        return search(mapping, form, request, response);
    }

    /**
     * 分页显示LAC的BSC分配列表
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
                TawpartLacBscConstants.TAWPARTLACBSC_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        TawpartLacBscMgr tawpartLacBscMgr = (TawpartLacBscMgr) getBean("tawpartLacBscMgr");
        Map map = (Map) tawpartLacBscMgr.getTawpartLacBscs(pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute(TawpartLacBscConstants.TAWPARTLACBSC_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    public ActionForward assignSave(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        TawpartLacBscMgr tawpartLacBscMgr = (TawpartLacBscMgr) getBean("tawpartLacBscMgr");
        String tenStart = request.getParameter("tenStart");
        String tenEnd = request.getParameter("tenEnd");
        int start = Integer.parseInt(tenStart);
        int end = Integer.parseInt(tenEnd);
        String areaId = request.getParameter("areaId");
        String areaName = request.getParameter("areaName");
        String rangeId = request.getParameter("rangeId");
        Map tawpartLacBscmap = tawpartLacBscMgr.getTawpartLacBscList(rangeId);
        String lac = "";
        String bsc = "";
        String mscName = "";
        for (int i = start; i <= end; i++) {
            bsc = request.getParameter("bsc" + i);
            lac = request.getParameter("lac" + i);
            mscName = request.getParameter("mscName" + i);
            TawpartLacBsc tawpartLacBsc = new TawpartLacBsc();
            if (tawpartLacBscmap.containsKey(lac)) {
                tawpartLacBsc = (TawpartLacBsc) tawpartLacBscmap.get(lac);
            }
            tawpartLacBsc.setAreaId(areaId);
            tawpartLacBsc.setAreaName(areaName);
            tawpartLacBsc.setBsc(bsc);
            tawpartLacBsc.setLac(lac);
            tawpartLacBsc.setMscName(mscName);
            tawpartLacBsc.setCreateTime(StaticMethod.getLocalString());
            tawpartLacBsc.setCreator(sessionform.getUsername());
            tawpartLacBsc.setPartRangeId(rangeId);
            tawpartLacBscMgr.saveTawpartLacBsc(tawpartLacBsc);
        }
        return mapping.findForward("success");
    }
}