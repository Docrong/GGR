/**
 * @see
 * <p>功能描述：用于出入机房登记等功能的类。</p>
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
import com.boco.eoms.duty.bo.*;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
//import com.boco.eoms.jbzl.model.TawApparatusroom;
//import com.boco.eoms.jbzl.dao.TawApparatusroomDAO;
import java.sql.*;
//import com.boco.eoms.log.bo.logBO;



public class TawRmGuestformAction
    extends Action {
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
    }
    catch (Exception e) {
    }
  }
*/
  public ActionForward execute(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {
    ActionForward myforward = null;
    String myaction = mapping.getParameter();

    if (isCancelled(request)) {
      return mapping.findForward("cancel");
    }
    if ("".equalsIgnoreCase(myaction)) {
      myforward = mapping.findForward("failure");
    }
    else if ("VIEW".equalsIgnoreCase(myaction)) {
      myforward = performView(mapping, form, request, response);
    }
    else if ("EDIT".equalsIgnoreCase(myaction)) {
      myforward = performEdit(mapping, form, request, response);
    }
    else if ("ADD".equalsIgnoreCase(myaction)) {
      myforward = performAdd(mapping, form, request, response);
    }
    else if ("SAVE".equalsIgnoreCase(myaction)) {
      myforward = performSave(mapping, form, request, response);
    }
    else if ("REMOVE".equalsIgnoreCase(myaction)) {
      myforward = performRemove(mapping, form, request, response);
    }
    else if ("TRASH".equalsIgnoreCase(myaction)) {
      myforward = performTrash(mapping, form, request, response);
    }
    else if ("LIST".equalsIgnoreCase(myaction)) {
      myforward = performList(mapping, form, request, response);
    }
    else if ("SEARCH".equalsIgnoreCase(myaction)) { //
      myforward = performSearch(mapping, form, request, response);
    }
    else if ("QUERY".equalsIgnoreCase(myaction)) { //
      myforward = performQuery(mapping, form, request, response);
    }
    else if ("SUBMIT".equalsIgnoreCase(myaction)) { //
      myforward = performSubmit(mapping, form, request, response);
    }
    else if ("OK".equalsIgnoreCase(myaction)) { //
      myforward = performOK(mapping, form, request, response);
    } else if ("QUERYDELETE".equalsIgnoreCase(myaction)) {
      myforward = performQuerydelete(mapping, form, request, response);
    } else if ("SEARCHDELETE".equalsIgnoreCase(myaction)) {
      myforward = performSearchdelete(mapping, form, request, response);
    } else if ("VIEWDELETE".equalsIgnoreCase(myaction)) {
      myforward = performViewdelete(mapping, form, request, response);
    } else if ("DODELETE".equalsIgnoreCase(myaction)) {
      myforward = performDodelete(mapping, form, request, response);
    }
    else {
      myforward = mapping.findForward("failure");
    }
    return myforward;
  }

  /**
   * @see 出入机房登记列表
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
    TawRmGuestformDAO tawRmGuestformDAO=null;
    String pageOffset=null;
    List tawRmGuestforms=null;
    String objKey=null;
    String url=null;
    String pagerHeader=null;

    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    try {

      tawRmGuestformDAO = new TawRmGuestformDAO(ds);

      int offset;
      int length = PAGE_LENGTH;
      pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      } else {
        offset = Integer.parseInt(pageOffset);
      }

      tawRmGuestforms = tawRmGuestformDAO.list(offset, length);

      String[] objKeys = {"TawRmGuestform", "list"};
      objKey = CacheManager.createKey(objKeys);
      Integer size = (Integer)SizeCacheManager.getCache(objKey);
      if(size == null) {
        size = new Integer(tawRmGuestformDAO.getSize("taw_rm_guestform", ""));
        SizeCacheManager.putCache(size, objKey, 0);
      }

      url = request.getContextPath()+"/"+mapping.getPath()+".do";
      pagerHeader = Pager.generate(offset, size.intValue(), length, url);
      request.setAttribute("pagerHeader", pagerHeader);

      request.setAttribute("TAWRMGUESTFORMS", tawRmGuestforms);
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmGuestformDAO=null;
      pageOffset=null;
      tawRmGuestforms=null;
      objKey=null;
      url=null;
      pagerHeader=null;
    }
    return mapping.findForward("success");
  }
  /**
   * @see 出入机房登记确认列表
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performOK(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    TawRmGuestformDAO tawRmGuestformDAO=null;
    String pageOffset=null;
    String starttime=null;
    String endtime=null;
    String paramCondition=null;
    String strCondition=null;
    String url=null;
    String pagerHeader=null;

    //判断超时
    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    try {

      tawRmGuestformDAO = new TawRmGuestformDAO(ds);

      int offset;
      int length = PAGE_LENGTH;
      pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      } else {
        offset = Integer.parseInt(pageOffset);
      }
      String time_fromyear = request.getParameter("starttimeyear");
      String time_frommonth = request.getParameter("starttimemonth");
      String time_fromday = request.getParameter("starttimeday");
      starttime = time_fromyear+"-"+time_frommonth+"-"+time_fromday;
      String time_toyear = request.getParameter("endtimeyear");
      String time_tomonth = request.getParameter("endtimemonth");
      String time_today = request.getParameter("endtimeday");
      endtime = time_toyear+"-"+time_tomonth+"-"+time_today;
     // starttime = request.getParameter("starttime");
      //endtime = request.getParameter("endtime");
      paramCondition = "starttime="+starttime+"&endtime="+endtime+"&flag="+String.valueOf(request.getParameter("flag"));
      strCondition = " where inputdate >= '" + starttime + "' and inputdate <= '" + endtime +"' ";
      int flag = Integer.parseInt(request.getParameter("flag"));
      if (flag != -1) {
        strCondition = strCondition + " and flag = " + flag ;
      }

      int roomId = Integer.parseInt(saveSessionBeanForm.getRoomId());
      if (roomId > 0) {
        strCondition = strCondition + " and room_id = " + roomId ;
      }
      strCondition = strCondition + " ";
      List tawRmGuestforms = tawRmGuestformDAO.search(offset, length, strCondition);
      int size = 0;
      try {
        size = tawRmGuestformDAO.getSize("taw_rm_guestform", strCondition);
      }
      catch (SQLException ex) {
      }
      url = request.getContextPath()+"/duty"+mapping.getPath()+".do";
      pagerHeader = Pager.generate(offset, size, length, url,paramCondition);
      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("TAWRMGUESTFORMS", tawRmGuestforms);
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmGuestformDAO=null;
      pageOffset=null;
      starttime=null;
      endtime=null;
      paramCondition=null;
      strCondition=null;
      url=null;
      pagerHeader=null;
    }
    return mapping.findForward("success");
  }


  /**
   * @see 出入机房登记查询列表
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performSearch(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    TawRmGuestformDAO tawRmGuestformDAO=null;
    String pageOffset=null;
    String starttime=null;
    String endtime=null;
    String paramCondition=null;
    String strCondition=null;
    String url=null;
    String pagerHeader=null;
    List tawRmGuestforms=null;

    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    try {

      tawRmGuestformDAO = new TawRmGuestformDAO(ds);

      int offset;
      int length = PAGE_LENGTH;
      pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      } else {
        offset = Integer.parseInt(pageOffset);
      }
      String time_fromyear = request.getParameter("starttimeyear");
      String time_frommonth = request.getParameter("starttimemonth");
      String time_fromday = request.getParameter("starttimeday");
      starttime = time_fromyear+"-"+time_frommonth+"-"+time_fromday;
      String time_toyear = request.getParameter("endtimeyear");
      String time_tomonth = request.getParameter("endtimemonth");
      String time_today = request.getParameter("endtimeday");
      endtime = time_toyear+"-"+time_tomonth+"-"+time_today;
      //starttime = request.getParameter("starttime");
      //endtime = request.getParameter("endtime");
      paramCondition = "starttime="+starttime+"&endtime="+endtime+"&roomId="+String.valueOf(request.getParameter("roomId"))+"&flag="+String.valueOf(request.getParameter("flag"));
      strCondition = " where inputdate >= '" + starttime +"' and inputdate <= '" + endtime + "' ";
      int flag = Integer.parseInt(request.getParameter("flag"));
      if (flag != -1) {
        strCondition = strCondition + " and flag = " + flag ;
      }

      int roomId = Integer.parseInt(request.getParameter("roomId"));
      if (roomId > 0) {
        strCondition = strCondition + " and room_id = " + roomId ;
      }
      strCondition = strCondition + " ";
      tawRmGuestforms = tawRmGuestformDAO.search(offset, length, strCondition);
      int  size = tawRmGuestformDAO.getSize("taw_rm_guestform", strCondition);
      url = request.getContextPath()+"/duty"+mapping.getPath()+".do";
      pagerHeader = Pager.generate(offset, size, length, url,paramCondition);
      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("TAWRMGUESTFORMS", tawRmGuestforms);
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmGuestformDAO=null;
      pageOffset=null;
      starttime=null;
      endtime=null;
      paramCondition=null;
      strCondition=null;
      url=null;
      pagerHeader=null;
      tawRmGuestforms=null;
    }
    return mapping.findForward("success");
  }

  /**
   * @see 出入机房查询条件
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performQuery(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    Vector SelectRoom=null;
    Vector SelectRoomName=null;
    TawRmAssignworkBO tawRmAssignworkBO=null;
    //edit by wangheqi
    TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
    TawSystemCptroom tawApparatusroom = null;
    
    //TawApparatusroomDAO tawApparatusroomDAO=null;
    //TawApparatusroom tawApparatusroom=null;
    String strSelectRoomName=null;

    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    try {
      SelectRoom = new Vector();
      SelectRoomName = new Vector();
      if(saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)){
        tawRmAssignworkBO = new TawRmAssignworkBO(ds);
        SelectRoom = tawRmAssignworkBO.getRoomSelect();
      }
      else{
    	//edit by wangheqi
    	TawSystemAssignBo privBO = null;    	  
        //TawValidatePrivBO tawValidatePrivBO = new TawValidatePrivBO(ds);
        //SelectRoom = tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(), 2012, 2);
        SelectRoom = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));
      }
      //zc 1-6 增加功能，满足本机房人员可以进行查询操作的要求
      if (SelectRoom.size()==0){
        String roomId = String.valueOf(saveSessionBeanForm.getRoomId());
        if (!roomId.equals("0")){
          SelectRoom.add(roomId);
        }
      }
      //
      if (SelectRoom.size()>0){
        //tawApparatusroomDAO = new TawApparatusroomDAO(ds);
        //tawApparatusroom = new TawApparatusroom();
        //strSelectRoomName = "";

        for (int i = 0; i < SelectRoom.size(); i++) {
          tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(String.valueOf(SelectRoom.elementAt(i))),0);
          if (tawApparatusroom != null) {
            strSelectRoomName = StaticMethod.null2String(tawApparatusroom.getRoomname());
            SelectRoomName.add(strSelectRoomName);
          }
          else {
            SelectRoomName.add(" ");
          }
        }
      }
      else{
        return mapping.findForward("nopriv");
      }
      request.setAttribute("SelectRoom",SelectRoom);
      request.setAttribute("SelectRoomName",SelectRoomName);

    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      SelectRoom=null;
      SelectRoomName=null;
      tawRmAssignworkBO=null;
      cptroomBO = null;
      //tawApparatusroomDAO=null;
      tawApparatusroom=null;
      strSelectRoomName=null;
    }
    return mapping.findForward("success");
  }


  /**
   * @see 确认出入机房登记
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performSubmit(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    //logBO logbo=null;

    //判断超时
    //saveSessionBeanForm =(SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }
    //判断是否值班
    if (Integer.parseInt(saveSessionBeanForm.getWorkSerial()) == 0)
    {
    //  return mapping.findForward("notonduty"); add by gongyufeng 
    }

    try {

      TawRmGuestformForm form = (TawRmGuestformForm) actionForm;
      //form.setStrutsAction(TawRmGuestformForm.ADD);
      //logbo = new logBO(ds);
      //boolean bool = logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),"确认出入机房记录", StaticVariable.OPER,request.getRemoteAddr());
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      //logbo=null;
  }
    return mapping.findForward("success");
  }

  /**
   * @see 出入机房查询
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
    TawRmGuestformDAO tawRmGuestformDAO=null;
    TawRmGuestform tawRmGuestform=null;
    //logBO logbo=null;

    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    TawRmGuestformForm form = (TawRmGuestformForm) actionForm;
    try {
      tawRmGuestformDAO = new TawRmGuestformDAO(ds);

      int id = Integer.parseInt(request.getParameter("id"));
      //System.out.println("id="+id);
      tawRmGuestform = tawRmGuestformDAO.retrieve(id);
      //System.out.println("tawRmGuestform="+tawRmGuestform);
      if (tawRmGuestform == null) {
        ActionErrors aes = new ActionErrors();
        aes.add(aes.GLOBAL_ERROR, new ActionError("error.object.notfound", "TawRmGuestform"));
        saveErrors(request, aes);
      } else {
        org.apache.commons.beanutils.BeanUtils.populate(form, org.apache.commons.beanutils.BeanUtils.describe(tawRmGuestform));
      }
      //logbo = new logBO(ds);
      //boolean bool = logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),"查询出入机房记录",StaticVariable.OPER,request.getRemoteAddr());
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmGuestformDAO=null;
      tawRmGuestform=null;
      //logbo=null;
    }
    return mapping.findForward("success");
  }

  /**
   * @see 出入机房登记保存
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
    TawRmGuestformDAO tawRmGuestformDAO=null;
    TawRmGuestform tawRmGuestform=null;
    //logBO logbo=null;

    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    TawRmGuestformForm form = (TawRmGuestformForm) actionForm;

    try {

      tawRmGuestformDAO = new TawRmGuestformDAO(ds);

      tawRmGuestform = new TawRmGuestform();
      org.apache.commons.beanutils.BeanUtils.populate(tawRmGuestform, org.apache.commons.beanutils.BeanUtils.describe(form));

      int strutsAction = form.getStrutsAction();
      if (strutsAction == TawRmGuestformForm.ADD) {
        tawRmGuestformDAO.insert(tawRmGuestform);
      } else if (strutsAction == TawRmGuestformForm.EDIT) {
        tawRmGuestformDAO.update(tawRmGuestform);
      }
      //logbo = new logBO(ds);
      //boolean bool = logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),"新增出入机房记录",StaticVariable.OPER,request.getRemoteAddr());
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmGuestformDAO=null;
      tawRmGuestform=null;
      //logbo=null;
    }
    return mapping.findForward("success");
  }
  /**
   * @see 出入机房登记编辑
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
    TawRmGuestformDAO tawRmGuestformDAO=null;
    TawRmGuestform tawRmGuestform=null;

    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    TawRmGuestformForm form = (TawRmGuestformForm) actionForm;
    form.setStrutsAction(TawRmGuestformForm.EDIT);
    try {
      tawRmGuestformDAO = new TawRmGuestformDAO(ds);
      int id = Integer.parseInt(request.getParameter("id"));
      tawRmGuestform = tawRmGuestformDAO.retrieve(id);
      org.apache.commons.beanutils.BeanUtils.populate(form, org.apache.commons.beanutils.BeanUtils.describe(tawRmGuestform));
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmGuestformDAO=null;
      tawRmGuestform=null;
    }
    return mapping.findForward("success");
  }

  /**
   * @see 新增出入机房登记
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performAdd(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    //判断超时
    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }
    //判断是否值班
    //add by wangheqi test
    //saveSessionBeanForm.setWorkSerial("1");
    if (Integer.parseInt(saveSessionBeanForm.getWorkSerial()) == 0)
    {
      //return mapping.findForward("notonduty");  // add by gongyufeng
    }
    TawRmGuestformForm form = (TawRmGuestformForm) actionForm;
    form.setStrutsAction(TawRmGuestformForm.ADD);
    saveSessionBeanForm=null;
    return mapping.findForward("success");
  }
  private ActionForward performRemove(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    return performView(mapping, actionForm, request, response);
  }

  /**
   * @see 出入机房登记删除
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
private ActionForward performQuerydelete(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
    //	edit by wangheqi 2.7 to 3.5
 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
      request.getSession().getAttribute("sessionform");
    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    Vector SelectRoom=null;
    Vector SelectRoomName=null;
    TawRmAssignworkBO tawRmAssignworkBO=null;
    //edit by wangheqi
    TawSystemAssignBo privBO = null;
    
    //TawValidatePrivBO tawValidatePrivBO=null;
    //edit by wangheqi
    TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
    TawSystemCptroom tawApparatusroom = null;
   
    //TawApparatusroomDAO tawApparatusroomDAO=null;
    //TawApparatusroom tawApparatusroom=null;
    String strSelectRoomName=null;

    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    try {
      SelectRoom = new Vector();
      SelectRoomName = new Vector();
      if(saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)){
        tawRmAssignworkBO = new TawRmAssignworkBO(ds);
        SelectRoom = tawRmAssignworkBO.getRoomSelect();
      }
      else{
        //tawValidatePrivBO = new TawValidatePrivBO(ds);
        //SelectRoom = tawValidatePrivBO.validatePriv(saveSessionBeanForm.getWrf_UserID(), 2014, 2);
        SelectRoom = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));
      }
      if (SelectRoom.size()>0){
        //tawApparatusroomDAO = new TawApparatusroomDAO(ds);
        //tawApparatusroom = new TawApparatusroom();
        strSelectRoomName = "";

        for (int i = 0; i < SelectRoom.size(); i++) {
          tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(String.valueOf(SelectRoom.elementAt(i))),0);
          if (tawApparatusroom != null) {
            strSelectRoomName = StaticMethod.null2String(tawApparatusroom.getRoomname());
            SelectRoomName.add(strSelectRoomName);
          }
          else {
            SelectRoomName.add(" ");
          }
        }
      }
      else{
        return mapping.findForward("nopriv");
      }
      request.setAttribute("SelectRoom",SelectRoom);
      request.setAttribute("SelectRoomName",SelectRoomName);
    } catch (Exception e) {
    generalError(request, e);
    return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      SelectRoom=null;
      SelectRoomName=null;
      tawRmAssignworkBO=null;
      privBO = null;
      //tawValidatePrivBO=null;
      cptroomBO = null;
      //tawApparatusroomDAO=null;
      tawApparatusroom=null;
      strSelectRoomName=null;
    }
    return mapping.findForward("success");
  }


  /**
   * @see 出入机房登记删除的查询
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
 private ActionForward performSearchdelete(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
   TawRmGuestformDAO tawRmGuestformDAO=null;
   String pageOffset=null;
   String starttime=null;
   String endtime=null;
   String paramCondition=null;
   String strCondition=null;
   List tawRmGuestforms=null;
   String url=null;
   String pagerHeader=null;

   //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
   if (saveSessionBeanForm==null)
   {
     return mapping.findForward("timeout");
   }

   try {
     tawRmGuestformDAO = new TawRmGuestformDAO(ds);
     int offset;
     int length = PAGE_LENGTH;
     pageOffset = request.getParameter("pager.offset");
     if (pageOffset == null || pageOffset.equals("")) {
       offset = 0;
     } else {
       offset = Integer.parseInt(pageOffset);
     }
     String time_fromyear = request.getParameter("starttimeyear");
     String time_frommonth = request.getParameter("starttimemonth");
     String time_fromday = request.getParameter("starttimeday");
     starttime = time_fromyear+"-"+time_frommonth+"-"+time_fromday;
     String time_toyear = request.getParameter("endtimeyear");
     String time_tomonth = request.getParameter("endtimemonth");
     String time_today = request.getParameter("endtimeday");
     endtime = time_toyear+"-"+time_tomonth+"-"+time_today;
    // starttime = request.getParameter("starttime");
    // endtime = request.getParameter("endtime");
     paramCondition = "starttime="+starttime+"&endtime="+endtime+"&roomId="+String.valueOf(request.getParameter("roomId"))+"&flag="+String.valueOf(request.getParameter("flag"));
     strCondition = " where inputdate >= '" + starttime +
        "' and inputdate <= '" + endtime + "' ";
     int flag = Integer.parseInt(request.getParameter("flag"));
     if (flag != -1) {
       strCondition = strCondition + " and flag = " + flag ;
     }

     int roomId = Integer.parseInt(request.getParameter("roomId"));
     if (roomId > 0) {
       strCondition = strCondition + " and room_id = " + roomId ;
     }
     strCondition = strCondition + " ";
     tawRmGuestforms = tawRmGuestformDAO.search(offset, length, strCondition);
     int  size = tawRmGuestformDAO.getSize("taw_rm_guestform", strCondition);
     url = request.getContextPath()+"/duty"+mapping.getPath()+".do";
     pagerHeader = Pager.generate(offset, size, length, url,paramCondition);
     request.setAttribute("pagerHeader", pagerHeader);
     request.setAttribute("TAWRMGUESTFORMS", tawRmGuestforms);
   } catch (Exception e) {
     generalError(request, e);
     return mapping.findForward("failure");
   }finally{
     saveSessionBeanForm=null;
     tawRmGuestformDAO=null;
     pageOffset=null;
     starttime=null;
     endtime=null;
     paramCondition=null;
     strCondition=null;
     tawRmGuestforms=null;
     url=null;
     pagerHeader=null;
   }
   return mapping.findForward("success");
  }

  /**
   * @see 出入机房登记删除前的显示
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performViewdelete(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    TawRmGuestformDAO tawRmGuestformDAO=null;
    TawRmGuestform tawRmGuestform=null;
    //logBO logbo=null;
    //判断超时

    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    TawRmGuestformForm form = (TawRmGuestformForm) actionForm;
    try {

      tawRmGuestformDAO = new TawRmGuestformDAO(ds);

      int id = Integer.parseInt(request.getParameter("id"));
      tawRmGuestform = tawRmGuestformDAO.retrieve(id);
      if (tawRmGuestform == null) {
        ActionErrors aes = new ActionErrors();
        aes.add(aes.GLOBAL_ERROR, new ActionError("error.object.notfound", "TawRmGuestform"));
        saveErrors(request, aes);
      } else {
        org.apache.commons.beanutils.BeanUtils.populate(form, org.apache.commons.beanutils.BeanUtils.describe(tawRmGuestform));
      }
      //logbo = new logBO(ds);
      //boolean bool = logbo.insertLogToDB(saveSessionBeanForm.getWrf_UserID(),"查询出入机房记录",StaticVariable.OPER,request.getRemoteAddr());
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmGuestformDAO=null;
      tawRmGuestform=null;
      //logbo=null;
    }
    return mapping.findForward("success");
  }

  /**
   * @see 出入机房登记删除
   * @param mapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  private ActionForward performDodelete(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
	    //	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    TawRmGuestformDAO tawRmGuestformDAO=null;

    //判断超时
    //saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");
    if (saveSessionBeanForm==null)
    {
      return mapping.findForward("timeout");
    }

    TawRmGuestformForm form = (TawRmGuestformForm) actionForm;
    try {
      int id = Integer.parseInt(request.getParameter("id"));
      tawRmGuestformDAO = new TawRmGuestformDAO(ds);
      tawRmGuestformDAO.delete(id);
    } catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }finally{
      saveSessionBeanForm=null;
      tawRmGuestformDAO=null;
    }
      return mapping.findForward("success");
  }




  private ActionForward performTrash(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
    TawRmGuestformForm form = (TawRmGuestformForm) actionForm;
    try {

      TawRmGuestformDAO tawRmGuestformDAO = new TawRmGuestformDAO(ds);

      int id = Integer.parseInt(request.getParameter("id"));
      tawRmGuestformDAO.delete(id);
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
