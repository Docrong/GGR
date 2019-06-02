/**
 * @see
 * <p>功能描述：用于排班子表增、删、改、查等功能的类。</p>
 * @author 赵川
 * @version 2.0
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

public class TawRmAssignSubAction extends Action {
  private com.boco.eoms.db.util.ConnectionPool ds=com.boco.eoms.db.util.ConnectionPool.getInstance();
  private static int PAGE_LENGTH = 20;
  //整合调整关于国际化
  static {
    ResourceBundle prop = ResourceBundle.getBundle(
        "resources.application_zh_CN");
    try {
      PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
    }
    catch (Exception e) {
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
   * @see 排班子表的列表
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    try {

      TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);

      int offset;
      int length = PAGE_LENGTH;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      } else {
        offset = Integer.parseInt(pageOffset);
      }

      List tawRmAssignSubs = tawRmAssignSubDAO.list(offset, length);

      String[] objKeys = {"TawRmAssignSub", "list"};
      String objKey = CacheManager.createKey(objKeys);
      Integer size = (Integer)SizeCacheManager.getCache(objKey);
      if(size == null) {
        size = new Integer(tawRmAssignSubDAO.getSize("taw_rm_assign_sub", ""));
        SizeCacheManager.putCache(size, objKey, 0);
      }

      String url = request.getContextPath()+"/"+mapping.getPath()+".do";
      String pagerHeader = Pager.generate(offset, size.intValue(), length, url);
      request.setAttribute("pagerHeader", pagerHeader);

      request.setAttribute("TAWRMASSIGNSUBS", tawRmAssignSubs);
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }

    return mapping.findForward("success");
  }

  /**
   * @see 值班子表的查看
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performView(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    TawRmAssignSubForm form = (TawRmAssignSubForm) actionForm;
    try {

      TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);

      int id = Integer.parseInt(request.getParameter("id"));
      TawRmAssignSub tawRmAssignSub = tawRmAssignSubDAO.retrieve(id);
      if (tawRmAssignSub == null) {
        ActionErrors aes = new ActionErrors();
        aes.add(aes.GLOBAL_ERROR, new ActionError("error.object.notfound", "TawRmAssignSub"));
        saveErrors(request, aes);
      } else {
        org.apache.commons.beanutils.BeanUtils.populate(form, org.apache.commons.beanutils.BeanUtils.describe(tawRmAssignSub));
      }
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }
    return mapping.findForward("success");
  }

  /**
   * @see 值班子表的保存
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performSave(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    TawRmAssignSubForm form = (TawRmAssignSubForm) actionForm;

    try {

      TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);

      TawRmAssignSub tawRmAssignSub = new TawRmAssignSub();
      org.apache.commons.beanutils.BeanUtils.populate(tawRmAssignSub, org.apache.commons.beanutils.BeanUtils.describe(form));
      int strutsAction = form.getStrutsAction();
      if (strutsAction == TawRmAssignSubForm.ADD) {
        int id = tawRmAssignSub.getId();
        if (tawRmAssignSubDAO.retrieve(id) == null) {
          tawRmAssignSubDAO.insert(tawRmAssignSub);
        } else {
          sqlDuplicateError(request, "TawRmAssignSub");
          return mapping.findForward("failure");
        }
      } else if (strutsAction == TawRmAssignSubForm.EDIT) {
        tawRmAssignSubDAO.update(tawRmAssignSub);
      }
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }
    return mapping.findForward("success");
  }

  /**
   * @see 值班子表的编辑
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performEdit(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    TawRmAssignSubForm form = (TawRmAssignSubForm) actionForm;
    form.setStrutsAction(TawRmAssignSubForm.EDIT);
    try {

      TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);

      int id = Integer.parseInt(request.getParameter("id"));

      TawRmAssignSub tawRmAssignSub = tawRmAssignSubDAO.retrieve(id);
      org.apache.commons.beanutils.BeanUtils.populate(form, org.apache.commons.beanutils.BeanUtils.describe(tawRmAssignSub));
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }
    return mapping.findForward("success");
  }

  /**
   * @see 值班子表新增
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performAdd(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    TawRmAssignSubForm form = (TawRmAssignSubForm) actionForm;
    form.setStrutsAction(TawRmAssignSubForm.ADD);
    return mapping.findForward("success");
  }

  /**
   * @see 值班子表删除
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performRemove(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    return performView(mapping, actionForm, request, response);
  }

  private ActionForward performTrash(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    TawRmAssignSubForm form = (TawRmAssignSubForm) actionForm;
    try {

      TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);

      int id = Integer.parseInt(request.getParameter("id"));
      tawRmAssignSubDAO.delete(id);
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
