/**
 * @see
 * <p>功能描述：用于交接班遗留问题等功能的类。</p>
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
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

import java.io.*;

public class TawRmHangoverAction extends Action {
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
    } else if ("SHOW".equalsIgnoreCase(myaction)) {
      myforward = performShow(mapping, form, request, response);
    } else if ("SAVE".equalsIgnoreCase(myaction)) {
      myforward = performSave(mapping, form, request, response);
    } else if ("TRANS".equalsIgnoreCase(myaction)) {
      myforward = performTrans(mapping, form, request, response);
    } else {
      myforward = mapping.findForward("failure");
    }
    return myforward;
  }


  /**
   * @see 交接班遗留问题显示
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performShow(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    TawRmHangoverDAO tawRmHangoverDAO=null;
    TawRmHangover tawRmHangover=null;
    TawRmHangover tawRmHangover0=null;
    String strQuestion0=null;

    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    TawRmHangoverForm form = (TawRmHangoverForm) actionForm;
    if (form.getHangWorkserial()== 0){
      int intHangWorkserial = Integer.parseInt(request.getParameter("HANGWORKSERIAL"));
      form.setHangWorkserial(intHangWorkserial);
      int intReceiveWorkserial = Integer.parseInt(request.getParameter("RECEIVEWORKSERIAL"));
      form.setReceiveWorkserial(intReceiveWorkserial);
    }
    String strHQ=StaticMethod.PageNStrRev(request.getParameter("strHQ"));
    if(!"".equals(strHQ))
      form.setHangQuestion(strHQ);
    try{
      if (form.getHangWorkserial()!=0){
        //应该已经有值了
        tawRmHangoverDAO = new TawRmHangoverDAO(ds);
        tawRmHangover = new TawRmHangover();
        if (tawRmHangoverDAO.retrieve(form.getHangWorkserial(), 1) != null) {
          tawRmHangover = tawRmHangoverDAO.retrieve(form.getHangWorkserial(),1);
          org.apache.commons.beanutils.BeanUtils.populate(form,org.apache.commons.beanutils.BeanUtils.describe(tawRmHangover));
        }
        tawRmHangover0 = tawRmHangoverDAO.retrieve(form.getHangWorkserial(), 0);
        strQuestion0 = "";
        if (tawRmHangover0 != null) {
          strQuestion0 = StaticMethod.null2String(tawRmHangover0.getHangQuestion());
        }
        form.setHangQuestion0(strQuestion0);

      }
    }
    catch (Exception e)
    {
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmHangoverDAO=null;
      tawRmHangover=null;
      tawRmHangover0=null;
      strQuestion0=null;
    }
    return mapping.findForward("success");
  }
  /**
   * @see 交接班遗留问题保存
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performSave(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    TawRmHangoverDAO tawRmHangoverDAO=null;
    TawRmHangover tawRmHangover=null;

    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    TawRmHangoverForm form = (TawRmHangoverForm) actionForm;
    try {
      tawRmHangoverDAO = new TawRmHangoverDAO(ds);
      tawRmHangover = new TawRmHangover();
      org.apache.commons.beanutils.BeanUtils.populate(tawRmHangover, org.apache.commons.beanutils.BeanUtils.describe(form));
      int intHang_workserial = tawRmHangover.getHangWorkserial();
      if (tawRmHangoverDAO.retrieve(intHang_workserial,1) == null) {
        tawRmHangoverDAO.insert(tawRmHangover);
      }
      else {
        tawRmHangoverDAO.update(tawRmHangover);
      }
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmHangoverDAO=null;
      tawRmHangover=null;
    }
    return mapping.findForward("success");
  }
  /**
   * @see
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performTrans(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    TawRmHangoverForm form = (TawRmHangoverForm) actionForm;

    try {
      //获得系统路径和分割符
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
