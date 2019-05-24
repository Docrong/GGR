 package com.boco.eoms.workplan.controller;

/**
 * <p>Title: 月度作业计划Action类</p>
 * <p>Description: 负责页面数据的显示、组织及获取等</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import javax.servlet.http.*;
import org.apache.struts.action.*;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Hashtable;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
 
import com.boco.eoms.workplan.bo.TawwpUtilBO;
import com.boco.eoms.workplan.mgr.ITawwpLogMgr;
import com.boco.eoms.workplan.mgr.ITawwpStubUserMgr;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpStubUserVO; 

public class TawwpStubUserAction
    extends BaseAction {

  //获取属性文件
  static {
    ResourceBundle prop = ResourceBundle.getBundle(
        "resources.application_zh_CN");
  }

  /**
   * 执行控制方法，以跳转到实际Action
   * @param mapping ActionMapping 集合
   * @param form ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */  
  public ActionForward execute(ActionMapping mapping, ActionForm form
                               , HttpServletRequest request,
                               HttpServletResponse response) {

    ActionForward myforward = null;

    //获取请求的action属性
    String myaction = mapping.getParameter();

    //session超时处理
    try {
      /*TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
          getSession().getAttribute(
          "sessionform");
      if (TawSystemSessionForm == null) {
        return mapping.findForward("timeout");
      }*/
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    //根据用户请求页面请求，进行页面跳转
    if (isCancelled(request)) {
      return mapping.findForward("cancel"); //无效的请求，转向错误页面
    }
    else if ("".equalsIgnoreCase(myaction)) {
      myforward = mapping.findForward("failure"); //条件为空，转向空页
    }
    else if ("CRUSERLIST".equalsIgnoreCase(myaction)) {
      myforward = performCruserList(mapping, form, request, response); //显示申请人提交的申请信息列表页面
    }
    else if ("STUBUSERLIST".equalsIgnoreCase(myaction)) {
      myforward = performStubUserList(mapping, form, request, response); //显示当前用户为代理人的代理信息列表页面
    }
    else if ("CHECKLIST".equalsIgnoreCase(myaction)) {
      myforward = performCheckList(mapping, form, request, response); //显示需要当前用户进行审批的代理信息列表页面
    }
    else if ("STUBUSERADD".equalsIgnoreCase(myaction)) {
      myforward = performStubUserAdd(mapping, form, request, response); //显示代理信息新增
    }
    else if ("STUBUSERSAVE".equalsIgnoreCase(myaction)) {
      myforward = performStubUserSave(mapping, form, request, response); //保存新增的代理信息
    }
    else if ("STUBUSEREDIT".equalsIgnoreCase(myaction)) {
      myforward = performStubUserEdit(mapping, form, request, response); //显示代理信息编辑
    }
    else if ("STUBUSERMODIFY".equalsIgnoreCase(myaction)) {
      myforward = performStubUserModify(mapping, form, request, response); //保存编辑后的代理信息
    }
    else if ("STUBUSERDEL".equalsIgnoreCase(myaction)) {
      myforward = performStubUserDel(mapping, form, request, response); //代理信息删除
    }
    else if ("STUBUSERVIEW".equalsIgnoreCase(myaction)) {
      myforward = performStubUserView(mapping, form, request, response); //浏览代理信息
    }
    else if ("CHECKVIEW".equalsIgnoreCase(myaction)) {
      myforward = performCheckView(mapping, form, request, response); //显示待审批代理信息的详细内容
    }
    else if ("STUBUSERREFER".equalsIgnoreCase(myaction)) {
      myforward = performStubUserRefer(mapping, form, request, response); //代理信息提交审批
    }
    else if ("PASS".equalsIgnoreCase(myaction)) {
      myforward = performPass(mapping, form, request, response); //通过代理信息审批
    }
    else if ("REJECT".equalsIgnoreCase(myaction)) {
      myforward = performReject(mapping, form, request, response); //驳回审批信息
    }
    else {
      myforward = mapping.findForward("failure"); //无效的请求，转向错误页面
    }
    return myforward;
  }

  /**
   * 显示申请人提交的申请信息列表页面Action
   * @param mapping ActionMapping 集合
   * @param actionForm ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */
  private ActionForward performCruserList(ActionMapping mapping
                                          , ActionForm actionForm,
                                          HttpServletRequest request
                                          , HttpServletResponse response) {

    //获取当前用户的session中的信息
    TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
        getSession().getAttribute("sessionform");

    String userId = TawSystemSessionForm.getUserid(); //当前用户登录名

    //初始化数据
    ITawwpStubUserMgr tawwpStubUserMgr=(ITawwpStubUserMgr)getBean("tawwpStubUserMgr"); 

    List stubUserVOList = null;

    try {
      //获取代理人信息VO对象集合
      stubUserVOList = tawwpStubUserMgr.listStubUserByCruser(userId);
      //为页面显示准备数据
      request.setAttribute("stubuservolist", stubUserVOList);

      //转向申请人提交的申请信息列表页面
      return mapping.findForward("success");
    }
    catch (Exception e) {
      generalError(request, e); //将错误信息加入到消息队列、写入日志
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure"); //转向错误页面
    }
  }

  /**
   * 显示当前用户为代理人的代理信息列表页面Action
   * @param mapping ActionMapping 集合
   * @param actionForm ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */
  private ActionForward performStubUserList(ActionMapping mapping
                                            , ActionForm actionForm,
                                            HttpServletRequest request
                                            , HttpServletResponse response) {

    //获取当前用户的session中的信息
    TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
        getSession().getAttribute("sessionform");

    String userId = TawSystemSessionForm.getUserid(); //当前用户登录名
    String deptId = TawSystemSessionForm.getDeptid();

    //初始化数据
    ITawwpStubUserMgr tawwpStubUserMgr=(ITawwpStubUserMgr)getBean("tawwpStubUserMgr");
    String checkuser = request.getParameter("checkuser");
    String stubtime = request.getParameter("stubtime");
    String state = request.getParameter("state");
    List stubUserVOList = null;
    Hashtable userHash = null;
    Hashtable stateHash = null;
    String stateType[] = null;
   
    TawwpUtilBO tawwpUtilBO = new TawwpUtilBO();
    try {
    	
      userHash = tawwpUtilBO.getUserByDept(deptId);
      stateType = TawwpStubUserVO.STATETPYE;
      stateHash = new Hashtable();
      for(int i = 0;i<stateType.length;i++){
    	  stateHash.put(String.valueOf(i),String.valueOf(stateType[i]));
      }
    
         
      //获取代理人信息VO对象集合
      stubUserVOList = tawwpStubUserMgr.listStubUserByStubuser(userId,stubtime,checkuser,state);
      //为页面显示准备数据
      request.setAttribute("stubuservolist", stubUserVOList);
      request.setAttribute("userhash", userHash);
      request.setAttribute("STATETYPE",stateHash);

      //转向当前用户为代理人的代理信息列表页面
      return mapping.findForward("success");
    }
    catch (Exception e) {
      generalError(request, e); //将错误信息加入到消息队列、写入日志
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure"); //转向错误页面
    }
  }

  /**
   * 显示需要当前用户进行审批的代理信息列表页面Action
   * @param mapping ActionMapping 集合
   * @param actionForm ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */
  private ActionForward performCheckList(ActionMapping mapping
                                         , ActionForm actionForm,
                                         HttpServletRequest request
                                         , HttpServletResponse response) {

    //获取当前用户的session中的信息
    TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
        getSession().getAttribute("sessionform");

    String userId = TawSystemSessionForm.getUserid(); //当前用户登录名

    //初始化数据
    ITawwpStubUserMgr tawwpStubUserMgr=(ITawwpStubUserMgr)getBean("tawwpStubUserMgr");

    List stubUserVOList = null;

    try {
      //获取代理人信息VO对象集合
      stubUserVOList = tawwpStubUserMgr.listStubUserByCheckuser(userId);
      //为页面显示准备数据
      request.setAttribute("stubuservolist", stubUserVOList);

      //转向需要当前用户进行审批的代理信息列表页面
      return mapping.findForward("success");
    }
    catch (Exception e) {
      generalError(request, e); //将错误信息加入到消息队列、写入日志
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure"); //转向错误页面
    }
  }

  /**
   * 显示代理信息新增页面Action
   * @param mapping ActionMapping 集合
   * @param actionForm ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */
  private ActionForward performStubUserAdd(ActionMapping mapping
                                           , ActionForm actionForm,
                                           HttpServletRequest request
                                           , HttpServletResponse response) {

    //获取当前用户的session中的信息
    TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
        getSession().getAttribute("sessionform");

    String currUser = TawSystemSessionForm.getUserid(); //当前用户登录名
    String userName = TawSystemSessionForm.getUsername(); //当前用户姓名
    String deptId = TawSystemSessionForm.getDeptid(); //部门编号

    //初始化数据
    TawwpUtilBO tawwpUtilBO = new TawwpUtilBO();
    Hashtable userHash = null;

    try {
      //获取部门中人员对象集合
      userHash = tawwpUtilBO.getUserByDept(deptId);

      //为页面显示准备数据
      request.setAttribute("username", userName);
      request.setAttribute("userhash", userHash);
      request.setAttribute("curruser", currUser);

      //转向代理信息新增页面
      return mapping.findForward("success");
    }
    catch (Exception e) {
      generalError(request, e); //将错误信息加入到消息队列、写入日志
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure"); //转向错误页面
    }
  }

  /**
   * 代理信息新增保存Action
   * @param mapping ActionMapping 集合
   * @param actionForm ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */
  private ActionForward performStubUserSave(ActionMapping mapping
                                            , ActionForm actionForm,
                                            HttpServletRequest request
                                            , HttpServletResponse response) {

    //获取当前用户的session中的信息
    TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
        getSession().getAttribute("sessionform");

    String userId = TawSystemSessionForm.getUserid(); //当前用户登录名

    //获取页面传递过来的参数
    String stubUser = TawwpUtil.getRequestValue(request, "stubuser", ""); //代理人
    String startDate = TawwpUtil.getRequestValue(request, "startdate", ""); //代理开始时间
    String endDate = TawwpUtil.getRequestValue(request, "enddate", ""); //代理结束时间
    String content = TawwpUtil.getRequestValue(request, "content", ""); //描述
    String checkUser = TawwpUtil.getRequestValue(request, "checkuser", ""); //审批人

    //初始化数据
    ITawwpStubUserMgr tawwpStubUserMgr=(ITawwpStubUserMgr)getBean("tawwpStubUserMgr");

    try {

      //保存代理信息
      tawwpStubUserMgr.addStubUser(userId, stubUser, startDate, endDate,
                                  checkUser, content);
      //日志
      ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");

      tawwpLogMgr.addLog(userId, "referStubUser", "");

      //转向申请人提交的申请信息列表页面
      return mapping.findForward("success");
    }
    catch (Exception e) {
      generalError(request, e); //将错误信息加入到消息队列、写入日志
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure"); //转向错误页面
    }
  }

  /**
   * 显示代理信息编辑Action
   * @param mapping ActionMapping 集合
   * @param actionForm ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */
  private ActionForward performStubUserEdit(ActionMapping mapping
                                            , ActionForm actionForm,
                                            HttpServletRequest request
                                            , HttpServletResponse response) {

    //获取当前用户的session中的信息
    TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
        getSession().getAttribute("sessionform");

    String userId = TawSystemSessionForm.getUserid(); //当前用户登录名
    String deptId = TawSystemSessionForm.getDeptid(); //当前用户登录名

    //获取页面传递过来的参数
    String stubUserId = TawwpUtil.getRequestValue(request, "stubuserid", ""); //代理信息编号

    //初始化数据
    ITawwpStubUserMgr tawwpStubUserMgr=(ITawwpStubUserMgr)getBean("tawwpStubUserMgr");
    TawwpStubUserVO tawwpStubUserVO = null;
    TawwpUtilBO tawwpUtilBO = new TawwpUtilBO();
    Hashtable userHash = null;

    try {

      //获取部门中人员对象集合
      userHash = tawwpUtilBO.getUserByDept(String.valueOf(deptId));

      //获取代理人信息VO对象
      tawwpStubUserVO = tawwpStubUserMgr.editView(stubUserId);

      //为页面显示准备数据
      request.setAttribute("tawwpstubuservo", tawwpStubUserVO);
      request.setAttribute("userhash", userHash);
      request.setAttribute("curruser", userId);

      //转向代理信息编辑页面
      return mapping.findForward("success");
    }
    catch (Exception e) {
      generalError(request, e); //将错误信息加入到消息队列、写入日志
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure"); //转向错误页面
    }
  }

  /**
   * 代理信息编辑保存Action
   * @param mapping ActionMapping 集合
   * @param actionForm ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */
  private ActionForward performStubUserModify(ActionMapping mapping
                                              , ActionForm actionForm,
                                              HttpServletRequest request
                                              , HttpServletResponse response) {

    //获取页面传递过来的参数
    String stubUser = TawwpUtil.getRequestValue(request, "stubuser", ""); //代理人
    String startDate = TawwpUtil.getRequestValue(request, "startdate", ""); //代理开始时间
    String endDate = TawwpUtil.getRequestValue(request, "enddate", ""); //代理结束时间
    String content = TawwpUtil.getRequestValue(request, "content", ""); //描述
    String checkUser = TawwpUtil.getRequestValue(request, "checkuser", ""); //审批人
    String stubUserId = TawwpUtil.getRequestValue(request, "stubuserid", ""); //代理信息编号

    //初始化数据
    ITawwpStubUserMgr tawwpStubUserMgr=(ITawwpStubUserMgr)getBean("tawwpStubUserMgr");

    try {

      //保存代理信息
      tawwpStubUserMgr.editStubUser(stubUserId, stubUser, startDate, endDate,
                                   checkUser, content);
      //日志
      ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
      //获取当前用户的session中的信息
      TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
          getSession().getAttribute("sessionform");
      String userId = TawSystemSessionForm.getUserid(); //当前用户名
      tawwpLogMgr.addLog(userId, "editStubUser", "");

      //转向申请人提交的申请信息列表页面
      return mapping.findForward("success");
    }
    catch (Exception e) {
      generalError(request, e); //将错误信息加入到消息队列、写入日志
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure"); //转向错误页面
    }
  }

  /**
   * 代理信息删除Action
   * @param mapping ActionMapping 集合
   * @param actionForm ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */
  private ActionForward performStubUserDel(ActionMapping mapping
                                           , ActionForm actionForm,
                                           HttpServletRequest request
                                           , HttpServletResponse response) {

    //获取页面传递过来的参数
    String stubUserId = TawwpUtil.getRequestValue(request, "stubuserid", ""); //代理信息编号

    //初始化数据
    ITawwpStubUserMgr tawwpStubUserMgr=(ITawwpStubUserMgr)getBean("tawwpStubUserMgr");

    try {

      //删除代理人信息
      tawwpStubUserMgr.removeStubUser(stubUserId);
      //日志
      ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
      //获取当前用户的session中的信息
      TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
          getSession().getAttribute("sessionform");
      String userId = TawSystemSessionForm.getUserid(); //当前用户名
      tawwpLogMgr.addLog(userId, "removeStubUser", "");

      //转向申请人提交的申请信息列表页面
      return mapping.findForward("success");
    }
    catch (Exception e) {
      generalError(request, e); //将错误信息加入到消息队列、写入日志
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure"); //转向错误页面
    }
  }

  /**
   * 浏览代理信息Action
   * @param mapping ActionMapping 集合
   * @param actionForm ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */
  private ActionForward performStubUserView(ActionMapping mapping
                                            , ActionForm actionForm,
                                            HttpServletRequest request
                                            , HttpServletResponse response) {

    //获取当前用户的session中的信息
    TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
        getSession().getAttribute("sessionform");

    String userId = TawSystemSessionForm.getUserid(); //当前用户登录名

    //获取页面传递过来的参数
    String stubUserId = TawwpUtil.getRequestValue(request, "stubuserid", ""); //代理信息编号

    //初始化数据
    ITawwpStubUserMgr tawwpStubUserMgr=(ITawwpStubUserMgr)getBean("tawwpStubUserMgr");
    TawwpStubUserVO tawwpStubUserVO = null;

    try {
      //获取代理人信息VO对象
      tawwpStubUserVO = tawwpStubUserMgr.editView(stubUserId);

      //为页面显示准备数据
      request.setAttribute("tawwpstubuservo", tawwpStubUserVO);

      //转向代理信息浏览页面
      return mapping.findForward("success");
    }
    catch (Exception e) {
      generalError(request, e); //将错误信息加入到消息队列、写入日志
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure"); //转向错误页面
    }
  }

  /**
   * 浏览待审批代理信息Action
   * @param mapping ActionMapping 集合
   * @param actionForm ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */
  private ActionForward performCheckView(ActionMapping mapping
                                         , ActionForm actionForm,
                                         HttpServletRequest request
                                         , HttpServletResponse response) {

    //获取当前用户的session中的信息
    TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
        getSession().getAttribute("sessionform");

    String userId = TawSystemSessionForm.getUserid(); //当前用户登录名

    //获取页面传递过来的参数
    String stubUserId = TawwpUtil.getRequestValue(request, "stubuserid", ""); //代理信息编号

    //初始化数据
    ITawwpStubUserMgr tawwpStubUserMgr=(ITawwpStubUserMgr)getBean("tawwpStubUserMgr");
    TawwpStubUserVO tawwpStubUserVO = null;

    try {
      //获取代理人信息VO对象
      tawwpStubUserVO = tawwpStubUserMgr.editView(stubUserId);

      //为页面显示准备数据
      request.setAttribute("tawwpstubuservo", tawwpStubUserVO);

      //转向待审批代理信息浏览页面
      return mapping.findForward("success");
    }
    catch (Exception e) {
      generalError(request, e); //将错误信息加入到消息队列、写入日志
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure"); //转向错误页面
    }
  }

  /**
   * 代理信息审批通过Action
   * @param mapping ActionMapping 集合
   * @param actionForm ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */
  private ActionForward performPass(ActionMapping mapping
                                    , ActionForm actionForm,
                                    HttpServletRequest request
                                    , HttpServletResponse response) {

    //获取页面传递过来的参数
    String stubUserId = TawwpUtil.getRequestValue(request, "stubuserid", ""); //代理信息编号

    //初始化数据
    ITawwpStubUserMgr tawwpStubUserMgr=(ITawwpStubUserMgr)getBean("tawwpStubUserMgr");

    try {

      //代理信息审批通过
      tawwpStubUserMgr.passStubUser(stubUserId);
      //日志
      ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
      //获取当前用户的session中的信息
      TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
          getSession().getAttribute("sessionform");
      String userId = TawSystemSessionForm.getUserid(); //当前用户名
      tawwpLogMgr.addLog(userId, "passStubUser", "");

      //转向待审批代理信息列表页面
      return mapping.findForward("success");
    }
    catch (Exception e) {
      generalError(request, e); //将错误信息加入到消息队列、写入日志
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure"); //转向错误页面
    }
  }

  /**
   * 代理信息审批驳回Action
   * @param mapping ActionMapping 集合
   * @param actionForm ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */
  private ActionForward performReject(ActionMapping mapping
                                      , ActionForm actionForm,
                                      HttpServletRequest request
                                      , HttpServletResponse response) {

    //获取页面传递过来的参数
    String stubUserId = TawwpUtil.getRequestValue(request, "stubuserid", ""); //代理信息编号

    //初始化数据
    ITawwpStubUserMgr tawwpStubUserMgr=(ITawwpStubUserMgr)getBean("tawwpStubUserMgr");

    try {

      //代理信息审批驳回
      tawwpStubUserMgr.rejectStubUser(stubUserId);
      //日志
      ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
      //获取当前用户的session中的信息
      TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
          getSession().getAttribute("sessionform");
      String userId = TawSystemSessionForm.getUserid(); //当前用户名
      tawwpLogMgr.addLog(userId, "rejectStubUser", "");

      //转向待审批代理信息列表页面
      return mapping.findForward("success");
    }
    catch (Exception e) {
      generalError(request, e); //将错误信息加入到消息队列、写入日志
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure"); //转向错误页面
    }
  }

  /**
   * 代理信息提交审批Action
   * @param mapping ActionMapping 集合
   * @param actionForm ActionForm 数据模型
   * @param request HttpServletRequest 请求
   * @param response HttpServletResponse 应答
   * @return ActionForward 转向对象
   */
  private ActionForward performStubUserRefer(ActionMapping mapping
                                             , ActionForm actionForm,
                                             HttpServletRequest request
                                             , HttpServletResponse response) {

    //获取页面传递过来的参数
    String stubUserId = TawwpUtil.getRequestValue(request, "stubuserid", ""); //代理信息编号

    //初始化数据
    ITawwpStubUserMgr tawwpStubUserMgr=(ITawwpStubUserMgr)getBean("tawwpStubUserMgr");

    try {

      //代理信息提交审批
      tawwpStubUserMgr.referStubUser(stubUserId);
      //日志
      ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) getBean("tawwpLogMgr");
      //获取当前用户的session中的信息
      TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.
          getSession().getAttribute("sessionform");
      String userId = TawSystemSessionForm.getUserid(); //当前用户名
      tawwpLogMgr.addLog(userId, "referStubUser", "");

      //转向申请人提交的申请信息列表页面
      return mapping.findForward("success");
    }
    catch (Exception e) {
      generalError(request, e); //将错误信息加入到消息队列、写入日志
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		"submit.title.failure"));
		saveMessages(request, messages);
      return mapping.findForward("failure"); //转向错误页面
    }
  }

  /**
   * 将错误信息加入到消息队列、写入日志
   * @param request HttpServletRequest 要进行处理的request
   * @param e Exception 异常
   */
  private void generalError(HttpServletRequest request, Exception e) {
    ActionMessages aes = new ActionMessages();
    aes.add("EOMS_WORKPLAN_ERROR",
            new ActionMessage("error.general", e.getMessage())); //将错误信息加入到消息队列中
    saveMessages(request, aes); //保存消息队列
    BocoLog.error(this, 2, "[WORK_PLAN_STUBUSER] Error -", e); //将错误信息写入日志
  }

}
