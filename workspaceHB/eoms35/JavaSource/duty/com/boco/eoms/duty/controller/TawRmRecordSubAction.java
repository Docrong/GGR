/**
 * @see
 * <p>功能描述：用于值班子记录管理等功能的类。</p>
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
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;

//import com.boco.eoms.jbzl.dao.TawApparatusroomDAO;
//import com.boco.eoms.jbzl.dao.TawRmUserDAO;
//import com.boco.eoms.jbzl.model.TawApparatusroom;
//import com.boco.eoms.jbzl.model.TawRmUser;

public class TawRmRecordSubAction extends Action {
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
   * @see 列表值班子记录
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performList(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    try {

      TawRmRecordSubDAO tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);

      int offset;
      int length = PAGE_LENGTH;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      } else {
        offset = Integer.parseInt(pageOffset);
      }

      List tawRmRecordSubs = tawRmRecordSubDAO.list(offset, length);

      String[] objKeys = {"TawRmRecordSub", "list"};
      String objKey = CacheManager.createKey(objKeys);
      Integer size = (Integer)SizeCacheManager.getCache(objKey);
      if(size == null) {
        size = new Integer(tawRmRecordSubDAO.getSize("taw_rm_record_sub", ""));
        SizeCacheManager.putCache(size, objKey, 0);
      }

      String url = request.getContextPath()+"/"+mapping.getPath()+".do";
      String pagerHeader = Pager.generate(offset, size.intValue(), length, url);
      request.setAttribute("pagerHeader", pagerHeader);

      request.setAttribute("TAWRMRECORDSUBS", tawRmRecordSubs);
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }

    return mapping.findForward("success");
  }
  /**
   * @see 查看值班子记录
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performView(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    TawRmRecordSubDAO tawRmRecordSubDAO=null;
    TawRmRecordSub tawRmRecordSub=null;
    //TawRmUserDAO tawRmUserDAO=null;
    String strDutyman=null;
    //edit by wangheqi
    TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
    TawSystemCptroom tawApparatusroom = null;
    
    //TawApparatusroomDAO tawApparatusroomDAO=null;
    //TawApparatusroom tawApparatusroom=null;
    String strRoomname=null;
    int Workflag=0;

    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }


    TawRmRecordSubForm form = (TawRmRecordSubForm) actionForm;
    try {

      tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);

      int id = Integer.parseInt(request.getParameter("id"));
      tawRmRecordSub = tawRmRecordSubDAO.retrieve(id);

      if (tawRmRecordSub == null) {
        ActionErrors aes = new ActionErrors();
        aes.add(aes.GLOBAL_ERROR, new ActionError("error.object.notfound", "TawRmRecordSub"));
        saveErrors(request, aes);
      } else {
        org.apache.commons.beanutils.BeanUtils.populate(form, org.apache.commons.beanutils.BeanUtils.describe(tawRmRecordSub));
        form.setStarttime(tawRmRecordSub.getStarttime().toString());
        form.setEndtime(tawRmRecordSub.getEndtime().toString());
        form.setStarttimeDefined(tawRmRecordSub.getStarttimeDefined().toString());
        form.setEndtimeDefined(tawRmRecordSub.getEndtimeDefined().toString());
//      edit by wangheqi 2.7to3.5
    	TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
    	//edit end
        //tawRmUserDAO = new TawRmUserDAO(ds);
    	strDutyman = StaticMethod.null2String(userbo.getUsernameByUserid(tawRmRecordSub.getDutyman()));
        //strDutyman = StaticMethod.null2String(tawRmUserDAO.getUserName(tawRmRecordSub.getDutyman()));
        request.setAttribute("DUTYMAN",strDutyman);
        //tawApparatusroomDAO = new TawApparatusroomDAO(ds);
        tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(tawRmRecordSub.getRoomId()),0);
        strRoomname = StaticMethod.null2String(tawApparatusroom.getRoomname());
        request.setAttribute("ROOMNAME",strRoomname);
        Workflag = Integer.parseInt(String.valueOf(tawRmRecordSub.getWorkflag()));
        String WorkFlagStr = "";
        switch (Workflag){
        case 0 : WorkFlagStr = "正常";
                   break;
        case 1 : WorkFlagStr = "迟到";
                   break;
        case 2 : WorkFlagStr = "早退";
                   break;
        case 3 : WorkFlagStr = "迟到、早退";
                   break;
        default :  WorkFlagStr = "正常";
                   break;
        }
        request.setAttribute("WORKFALG",WorkFlagStr);
        String Status = String.valueOf(tawRmRecordSub.getStatus());
        
        String StatusStr = "";
        int StatusInt = Integer.parseInt(Status);
        switch (StatusInt){
        case 0 : StatusStr = "可修改";
                   break;
        case 1 : StatusStr = "不可改";
                   break;
        default :  StatusStr = "可修改";
                   break;
        }
        request.setAttribute("STATUS",StatusStr);
        request.setAttribute("dutycheck", tawRmRecordSub.getDutycheck());
      }
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmRecordSubDAO=null;
      tawRmRecordSub=null;
      //userbo = null;
      //tawRmUserDAO=null;
      strDutyman=null;
      cptroomBO = null;
      //tawApparatusroomDAO=null;
      tawApparatusroom=null;
      strRoomname=null;
      Workflag=0;
    }
    return mapping.findForward("success");
  }
  /**
   * @see 保存值班子记录
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
    TawRmRecordSubDAO tawRmRecordSubDAO=null;
    TawRmRecordSub tawRmRecordSub=null;

    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }


    TawRmRecordSubForm form = (TawRmRecordSubForm) actionForm;

    try {

      tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);

      tawRmRecordSub = new TawRmRecordSub();
      org.apache.commons.beanutils.BeanUtils.populate(tawRmRecordSub, org.apache.commons.beanutils.BeanUtils.describe(form));

      tawRmRecordSub.setStarttime(form.getStarttime());
      tawRmRecordSub.setEndtime(form.getEndtime());
      tawRmRecordSub.setStarttimeDefined(form.getStarttimeDefined());
      tawRmRecordSub.setEndtimeDefined(form.getEndtimeDefined());
      int strutsAction = form.getStrutsAction();
      if (strutsAction == TawRmRecordSubForm.ADD) {
        int id = tawRmRecordSub.getId();
        if (tawRmRecordSubDAO.retrieve(id) == null) {
          tawRmRecordSubDAO.insert(tawRmRecordSub);
        } else {
          sqlDuplicateError(request, "TawRmRecordSub");
          return mapping.findForward("failure");
        }
      } else if (strutsAction == TawRmRecordSubForm.EDIT) {
        tawRmRecordSubDAO.update(tawRmRecordSub);
      }
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmRecordSubDAO=null;
      tawRmRecordSub=null;
    }
    return mapping.findForward("success");
  }
  /**
   * @see 编辑值班子记录
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performEdit(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    TawRmRecordSubDAO tawRmRecordSubDAO=null;
    TawRmRecordSub tawRmRecordSub=null;

    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    TawRmRecordSubForm form = (TawRmRecordSubForm) actionForm;
    form.setStrutsAction(TawRmRecordSubForm.EDIT);
    try {

      tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);

      int id = Integer.parseInt(request.getParameter("id"));

      tawRmRecordSub = tawRmRecordSubDAO.retrieve(id);
      org.apache.commons.beanutils.BeanUtils.populate(form, org.apache.commons.beanutils.BeanUtils.describe(tawRmRecordSub));
      form.setStarttime(tawRmRecordSub.getStarttime().toString());
      form.setEndtime(tawRmRecordSub.getEndtime().toString());
      form.setStarttimeDefined(tawRmRecordSub.getStarttimeDefined().toString());
      form.setEndtimeDefined(tawRmRecordSub.getEndtimeDefined().toString());
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmRecordSubDAO=null;
      tawRmRecordSub=null;
    }
    return mapping.findForward("success");
  }

  private ActionForward performAdd(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    TawRmRecordSubForm form = (TawRmRecordSubForm) actionForm;
    form.setStrutsAction(TawRmRecordSubForm.ADD);
    return mapping.findForward("success");
  }
  private ActionForward performRemove(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    return performView(mapping, actionForm, request, response);
  }

  private ActionForward performTrash(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    TawRmRecordSubForm form = (TawRmRecordSubForm) actionForm;
    try {

      TawRmRecordSubDAO tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);

      int id = Integer.parseInt(request.getParameter("id"));
      tawRmRecordSubDAO.delete(id);
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
