/**
 * @author 赵川
 * @version 2.0
 * @see <p>功能描述：用于机房交接班时间信息的增、删、改、查等功能的类。</p>
 */


package com.boco.eoms.duty.controller;

import javax.sql.*;
import java.util.*;

import org.apache.commons.logging.LogFactory;


import javax.servlet.http.*;
import javax.servlet.*;

import org.apache.struts.action.*;
import org.apache.struts.util.*;

import com.boco.eoms.duty.model.*;
import com.boco.eoms.duty.dao.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

public class TawRmExchangeAction extends Action {
    private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
    private static int PAGE_LENGTH = 20;

    //整合调整关于国际化
    static {
        ResourceBundle prop = ResourceBundle.getBundle(
                "resources.application_zh_CN");
        try {
            PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
        } catch (Exception e) {
        }
    }

    /*
      static {
        ResourceBundle prop = ResourceBundle.getBundle("resources.application");
        try {
          PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
        } catch (Exception e) {
        }
      }
    */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        ActionForward myforward = null;
        String myaction = mapping.getParameter();

        if (isCancelled(request)) {
            return mapping.findForward("cancel");
        }
        if ("".equalsIgnoreCase(myaction)) {
            myforward = mapping.findForward("failure");
        } else if ("VIEW".equalsIgnoreCase(myaction)) {
            myforward = performView(mapping, form, request, response);
        } else if ("EDIT".equalsIgnoreCase(myaction)) {
            myforward = performEdit(mapping, form, request, response);
        } else if ("ADD".equalsIgnoreCase(myaction)) {
            myforward = performAdd(mapping, form, request, response);
        } else if ("SAVE".equalsIgnoreCase(myaction)) {
            myforward = performSave(mapping, form, request, response);
        } else if ("REMOVE".equalsIgnoreCase(myaction)) {
            myforward = performRemove(mapping, form, request, response);
        } else if ("TRASH".equalsIgnoreCase(myaction)) {
            myforward = performTrash(mapping, form, request, response);
        } else if ("LIST".equalsIgnoreCase(myaction)) {
            myforward = performList(mapping, form, request, response);
        } else {
            myforward = mapping.findForward("failure");
        }
        return myforward;
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 交接班时间列表
     */
    private ActionForward performList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
        try {

            TawRmExchangeDAO tawRmExchangeDAO = new TawRmExchangeDAO(ds);

            int offset;
            int length = PAGE_LENGTH;
            String pageOffset = request.getParameter("pager.offset");
            if (pageOffset == null || pageOffset.equals("")) {
                offset = 0;
            } else {
                offset = Integer.parseInt(pageOffset);
            }

            List tawRmExchanges = tawRmExchangeDAO.list(offset, length);

            String[] objKeys = {"TawRmExchange", "list"};
            String objKey = CacheManager.createKey(objKeys);
            Integer size = (Integer) SizeCacheManager.getCache(objKey);
            if (size == null) {
                size = new Integer(tawRmExchangeDAO.getSize("taw_rm_exchange", ""));
                SizeCacheManager.putCache(size, objKey, 0);
            }

            String url = request.getContextPath() + "/" + mapping.getPath() + ".do";
            String pagerHeader = Pager.generate(offset, size.intValue(), length, url);
            request.setAttribute("pagerHeader", pagerHeader);

            request.setAttribute("TAWRMEXCHANGES", tawRmExchanges);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        }

        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 交接班时间查看
     */
    private ActionForward performView(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
        TawRmExchangeForm form = (TawRmExchangeForm) actionForm;
        try {

            TawRmExchangeDAO tawRmExchangeDAO = new TawRmExchangeDAO(ds);

            int roomId = Integer.parseInt(request.getParameter("roomId"));
            short id = Short.parseShort(request.getParameter("id"));
            TawRmExchange tawRmExchange = tawRmExchangeDAO.retrieve(roomId, id);
            if (tawRmExchange == null) {
                ActionErrors aes = new ActionErrors();
                aes.add(aes.GLOBAL_ERROR, new ActionError("error.object.notfound", "TawRmExchange"));
                saveErrors(request, aes);
            } else {
                org.apache.commons.beanutils.BeanUtils.populate(form, org.apache.commons.beanutils.BeanUtils.describe(tawRmExchange));
            }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 交接班时间保存
     */
    private ActionForward performSave(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

        //	edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
                request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmExchangeForm form = (TawRmExchangeForm) actionForm;

        try {

            TawRmExchangeDAO tawRmExchangeDAO = new TawRmExchangeDAO(ds);

            TawRmExchange tawRmExchange = new TawRmExchange();
            org.apache.commons.beanutils.BeanUtils.populate(tawRmExchange, org.apache.commons.beanutils.BeanUtils.describe(form));
            int strutsAction = form.getStrutsAction();
            if (strutsAction == TawRmExchangeForm.ADD) {
                int roomId = tawRmExchange.getRoomId();
                short id = tawRmExchange.getId();
                if (tawRmExchangeDAO.retrieve(roomId, id) == null) {
                    tawRmExchangeDAO.insert(tawRmExchange);
                } else {
                    sqlDuplicateError(request, "TawRmExchange");
                    return mapping.findForward("failure");
                }
            } else if (strutsAction == TawRmExchangeForm.EDIT) {
                tawRmExchangeDAO.update(tawRmExchange);
            }
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 交接班时间编辑
     */
    private ActionForward performEdit(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

        //	edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
                request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
        if (saveSessionBeanForm == null) {
            return mapping.findForward("timeout");
        }

        TawRmExchangeForm form = (TawRmExchangeForm) actionForm;
        form.setStrutsAction(TawRmExchangeForm.EDIT);
        try {
            TawRmExchangeDAO tawRmExchangeDAO = new TawRmExchangeDAO(ds);

            int roomId = Integer.parseInt(request.getParameter("roomId"));
            short id = Short.parseShort(request.getParameter("id"));

            TawRmExchange tawRmExchange = tawRmExchangeDAO.retrieve(roomId, id);
            org.apache.commons.beanutils.BeanUtils.populate(form, org.apache.commons.beanutils.BeanUtils.describe(tawRmExchange));
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 交接班时间新增
     */
    private ActionForward performAdd(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
        TawRmExchangeForm form = (TawRmExchangeForm) actionForm;
        form.setStrutsAction(TawRmExchangeForm.ADD);
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 交接班时间删除
     */
    private ActionForward performRemove(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
        return performView(mapping, actionForm, request, response);
    }

    /**
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @see 交接班时间删除
     */
    private ActionForward performTrash(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
        TawRmExchangeForm form = (TawRmExchangeForm) actionForm;
        try {
            TawRmExchangeDAO tawRmExchangeDAO = new TawRmExchangeDAO(ds);

            int roomId = Integer.parseInt(request.getParameter("roomId"));
            short id = Short.parseShort(request.getParameter("id"));
            tawRmExchangeDAO.delete(roomId, id);
        } catch (Exception e) {
            generalError(request, e);
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }


    private void sqlDuplicateError(HttpServletRequest request, String objName) {
        ActionErrors aes = new ActionErrors();
        aes.add(aes.GLOBAL_ERROR, new ActionError("errors.database.duplicate", objName));
        saveErrors(request, aes);
    }

    private void generalError(HttpServletRequest request, Exception e) {
        ActionErrors aes = new ActionErrors();
        aes.add(aes.GLOBAL_ERROR, new ActionError("error.general", e.getMessage()));
        saveErrors(request, aes);
        e.printStackTrace();
    }
}
