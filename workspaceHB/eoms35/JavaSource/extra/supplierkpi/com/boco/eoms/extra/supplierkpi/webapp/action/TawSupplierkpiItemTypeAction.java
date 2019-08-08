package com.boco.eoms.extra.supplierkpi.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiDictManager;
import com.boco.eoms.extra.supplierkpi.util.TawSupplierkpiDictUtil;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiItemTypeForm;

public final class TawSupplierkpiItemTypeAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("search");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSupplierkpiItemTypeForm tawSupplierkpiItemTypeForm = (TawSupplierkpiItemTypeForm) form;
        String parentDictId = StaticMethod.null2String(request
                .getParameter("parentDictId"));

        ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
        TawSupplierkpiDict dict = new TawSupplierkpiDict();
        dict = dictMgr.getDictByDictId(parentDictId);
        String dictName = dict.getDictName();
        String dictRemark = dict.getDictRemark();

        tawSupplierkpiItemTypeForm.setDictName(dictName);
        tawSupplierkpiItemTypeForm.setDictRemark(dictRemark);
        updateFormBean(mapping, request, tawSupplierkpiItemTypeForm);
        request.setAttribute("oper", "update");
        request.setAttribute("parentDictId", parentDictId);
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String parentDictId = request.getParameter("parentDictId");
        String dictName = request.getParameter("dictName");
        String dictRemark = request.getParameter("dictRemark");
        String oper = request.getParameter("oper");

        TawSupplierkpiDict tawSupplierkpiDict = new TawSupplierkpiDict();

        ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
        if ("update".equals(oper)) {
            tawSupplierkpiDict = dictMgr.getDictByDictId(parentDictId);
            tawSupplierkpiDict.setDictName(dictName);
            tawSupplierkpiDict.setDictRemark(dictRemark);
            dictMgr.saveTawSupplierkpiDict(tawSupplierkpiDict);
        } else {
            tawSupplierkpiDict.setDictName(dictName);
            tawSupplierkpiDict.setDictRemark(dictRemark);
            tawSupplierkpiDict.setParentDictId(parentDictId);
            tawSupplierkpiDict
                    .setNodeType(TawSupplierkpiDictUtil.NODETYPE_ITEMTYPE);

            String newdictid = dictMgr.getMaxDictid(parentDictId);
            TawSupplierkpiDict dict = new TawSupplierkpiDict();
            dict = dictMgr.getDictByDictId(parentDictId);
            if (tawSupplierkpiDict.getDictId() == null
                    || tawSupplierkpiDict.getDictId().equals("")) {
                tawSupplierkpiDict.setDictId(newdictid);
            }
            dict.setLeaf("0");
            tawSupplierkpiDict.setLeaf("1");
            dictMgr.saveTawSupplierkpiDict(dict);
        }
        dictMgr.saveTawSupplierkpiDict(tawSupplierkpiDict);
        return mapping.findForward("success");
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("list");
    }

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }
}
