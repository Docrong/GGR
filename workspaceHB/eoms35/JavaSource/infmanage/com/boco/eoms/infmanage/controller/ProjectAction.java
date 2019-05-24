package com.boco.eoms.infmanage.controller;

import java.util.*;
import javax.servlet.http.*;

import java.util.ArrayList;
import org.apache.commons.beanutils.*;
import org.apache.struts.action.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.infmanage.dao.*;
import com.boco.eoms.infmanage.model.*;
import com.boco.eoms.infmanage.bo.*;
//import com.boco.eoms.common.controller.SaveSessionBeanForm;
//import com.boco.eoms.jbzl.bo.TawDeptBO;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
import org.apache.struts.util.LabelValueBean;

import org.apache.struts.action.Action;

public class ProjectAction
    extends Action {
  private static int PAGE_LENGTH = 10;
  private String user_id = "";
  private String userName = "";
  private int deptId;

  private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.
      ConnectionPool.getInstance();
  private static String task_parent_id = "";
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm
                               , HttpServletRequest request,
                               HttpServletResponse response) {
    ActionForward myforward = null;
    String myaction = actionMapping.getParameter();

    //session��ʱ����
    try {

	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null)
        return actionMapping.findForward("timeout");
      user_id = saveSessionBeanForm.getUserid();
      userName = saveSessionBeanForm.getUsername();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    if ("".equalsIgnoreCase(myaction)) {
      myforward = actionMapping.findForward("failure");
    }

    if ("ADD".equalsIgnoreCase(myaction)) {
      myforward = performAdd(actionMapping, actionForm, request, response);
    }

    if ("SAVE".equalsIgnoreCase(myaction)) {
      myforward = performSave(actionMapping, actionForm, request,
                              response);
    }

    if ("QUERY".equalsIgnoreCase(myaction)) {
      myforward = performQuery(actionMapping, actionForm, request,
                               response);
    }

    if ("QUERYDONE".equalsIgnoreCase(myaction)) {
      myforward = performQueryDone(actionMapping, actionForm, request,
                                   response);
    }
    if ("QUERYTASKLIST".equalsIgnoreCase(myaction)) {
      myforward = performQueryTaskList(actionMapping, actionForm, request,
                                       response);
    }

    if ("LIST".equalsIgnoreCase(myaction)) {
      myforward = performList(actionMapping, actionForm, request,
                              response);
    }

    if ("MYLIST".equalsIgnoreCase(myaction)) {
      myforward = performMyList(actionMapping, actionForm, request,
                                response);
    }
    if ("MYLIST2".equalsIgnoreCase(myaction)) {
      myforward = performMyList2(actionMapping, actionForm, request,
                                 response);
    }

    if ("VIEW".equalsIgnoreCase(myaction)) {
      myforward = performView(actionMapping, actionForm, request,
                              response);
    }

    if ("UPDATE".equalsIgnoreCase(myaction)) {
      myforward = performUpdate(actionMapping, actionForm, request,
                                response);
    }
    if ("UPDATEDONE".equalsIgnoreCase(myaction)) {
      myforward = performUpdatedone(actionMapping, actionForm, request,
                                    response);
    }

    if ("DEL".equalsIgnoreCase(myaction)) {
      myforward = performDel(actionMapping, actionForm, request,
                             response);
    }

    if ("DELDONE".equalsIgnoreCase(myaction)) {
      myforward = performDeldone(actionMapping, actionForm, request,
                                 response);
    }

/////////// ///////////  task����
    if ("DISTRI".equalsIgnoreCase(myaction)) {
      myforward = performDistri(actionMapping, actionForm, request,
                                response);
    }

    if ("SAVETASK".equalsIgnoreCase(myaction)) {
      myforward = performSavetask(actionMapping, actionForm, request,
                                  response);
    }
    if ("TASKLIST".equalsIgnoreCase(myaction)) {
      myforward = performTasklist(actionMapping, actionForm, request,
                                  response);
    }
    if ("MYLIST1".equalsIgnoreCase(myaction)) {
      myforward = performMylist1(actionMapping, actionForm, request,
                                 response);
    }
    if ("TASKVIEW".equalsIgnoreCase(myaction)) {
      myforward = performTaskview(actionMapping, actionForm, request,
                                  response);
    }
    if ("TASKUPDATE".equalsIgnoreCase(myaction)) {
      myforward = performTaskUpdate(actionMapping, actionForm, request,
                                    response);
    }
    if ("TASKUPDATEDONE".equalsIgnoreCase(myaction)) {
      myforward = performTaskUpdateDone(actionMapping, actionForm, request,
                                        response);
    }
    if ("TASKDEL".equalsIgnoreCase(myaction)) {
      myforward = performTaskDel(actionMapping, actionForm, request,
                                 response);
    }
    if ("TASKDELDONE".equalsIgnoreCase(myaction)) {
      myforward = performTaskDelDone(actionMapping, actionForm, request,
                                     response);
    }
    if ("MYTASK".equalsIgnoreCase(myaction)) {
      myforward = performMyTask(actionMapping, actionForm, request,
                                response);
    }
    if ("MYTASK1".equalsIgnoreCase(myaction)) {
      myforward = performMyTask1(actionMapping, actionForm, request,
                                 response);
    }

    if ("UPDATETASKSIGN".equalsIgnoreCase(myaction)) {
      myforward = performUpdateTaskSign(actionMapping, actionForm, request,
                                        response);
    }
    if ("UPDATETASKSIGNDONE".equalsIgnoreCase(myaction)) {
      myforward = performUpdateTaskSignDone(actionMapping, actionForm, request,
                                            response);
    }
    if ("TRACKDONE".equalsIgnoreCase(myaction)) {
      myforward = performTrackDone(actionMapping, actionForm, request,
                                   response);
    }
    if ("QUERYTRACKDONE".equalsIgnoreCase(myaction)) {
     myforward = performQueryTrackDone(actionMapping, actionForm, request,
                                  response);
   }

    if ("PROCHECKDONE".equalsIgnoreCase(myaction)) {
      myforward = performProCheckDone(actionMapping, actionForm, request,
                                      response);
    }
    if ("TASKCHECKDONE".equalsIgnoreCase(myaction)) {
      myforward = performTaskCheckDone(actionMapping, actionForm, request,
                                       response);
    }
    if ("PROCHECKLIST".equalsIgnoreCase(myaction)) {
      myforward = performProCheckList(actionMapping, actionForm, request,
                                      response);
    }

    if ("TASKCHECKLIST".equalsIgnoreCase(myaction)) {
      myforward = performTaskCheckList(actionMapping, actionForm, request,
                                       response);
    }

    return myforward;
  }

  /**
   * ��ʾ���ҳ��
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performAdd(ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;
    //	edit by wangheqi 2.7 to 3.5
	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     request.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 //edit end

    if (saveSessionBeanForm == null) {
      return actionMapping.findForward("timeout");
    }
    int hour = Integer.parseInt(StaticMethod.getCurrentDateTime().substring(11,
        13));
    System.out.print(hour);
    try {
      request.setAttribute("userName", userName);
      form.setProject_send_user(user_id);
      form.setProject_name("");
      form.setProject_comp_time("");
      form.setProject_exec_time(StaticMethod.getCurrentDateTime());
      form.setProject_desc("");

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");

  }

  /**
   * ִ����ӵĲ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performSave(ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
    ProjectDAO projectDAO = new ProjectDAO(ds);
    ProjectForm form = (ProjectForm) actionForm;
    Project project = new Project();

    try {
      HttpSession session = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      org.apache.commons.beanutils.BeanUtils.populate(project,
          org.apache.commons.beanutils.BeanUtils.describe(form));

      projectDAO.insert(project);
    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * ��ʾ��ѯ��ҳ��
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performQuery(ActionMapping actionMapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;
    //	edit by wangheqi 2.7 to 3.5
	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     request.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 //edit end

    if (saveSessionBeanForm == null) {
      return actionMapping.findForward("timeout");
    }
    try {
      request.setAttribute("userName", userName);
      form.setProject_name("");
      form.setProject_comp_time("");
      form.setProject_exec_time(StaticMethod.getCurrentDateTime());
      form.setProject_desc("");

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");

  }

  public ActionForward performQueryDone(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;

    try {
      int length = PAGE_LENGTH;
      int offset;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      // ��ѯ
      int flag = StaticMethod.null2int(request.getParameter(
          "flag")); /////�����Ŀ�����������

      ProjectBO projectBO = new ProjectBO(ds);
      ArrayList list = (ArrayList) projectBO.getQueryList(form, offset, length,
          flag, user_id);

      int size = list.size();
      String url = request.getContextPath() + "/infmanage" +
          actionMapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size, length, url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("Project_LIST", list);
      request.setAttribute("userName", userName);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");

  }

  /**
   * ���б����ʽ��ʾ��ѯ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performList(ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;

    try {
      int length = PAGE_LENGTH;
      int offset;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      ProjectBO projectBO = new ProjectBO(ds);
      ArrayList list = (ArrayList) projectBO.getList(offset, length);
      int size = list.size();
      String url = request.getContextPath() + "/infmanage" +
          actionMapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size, length, url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("Project_LIST", list);
      request.setAttribute("userName", userName);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * ���б����ʽ��ʾ��ѯ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performMyList(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;

    try {
      int length = PAGE_LENGTH;
      int offset;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      // ��ѯ
      ProjectBO projectBO = new ProjectBO(ds);
      ArrayList list = (ArrayList) projectBO.getMyList(offset, length, user_id);
      int size = list.size();
      String url = request.getContextPath() + "/infmanage" +
          actionMapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size, length, url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("Project_LIST", list);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * ���б����ʽ��ʾ��ѯ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performMyList2(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;

    try {
      int length = PAGE_LENGTH;
      int offset;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      // ��ѯ
      int local = StaticMethod.null2int(request.getParameter(
          "local"));
      ProjectBO projectBO = new ProjectBO(ds);
      ArrayList list = (ArrayList) projectBO.getMyList2(offset, length, user_id,
          userName, 0, local);
      int size = list.size();
      String url = request.getContextPath() + "/infmanage" +
          actionMapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size, length, url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("Project_LIST", list);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * �鿴�ۺϾ����������Ϣ
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */

  public ActionForward performView(ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;
    ProjectDAO projectDAO = new ProjectDAO(ds);
    Project project = new Project();
    try {
      // �жϳ�ʱ
      HttpSession httpSession = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      //
      int pro_code = StaticMethod.null2int(request.getParameter(
          "pro_code"));
      int id = StaticMethod.null2int(request.getParameter(
          "id"));
      project = projectDAO.getById(pro_code, id);
      org.apache.commons.beanutils.BeanUtils.populate(form,
          org.apache.commons.beanutils.BeanUtils.describe(
          project));

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   *
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */

  public ActionForward performUpdate(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;
    ProjectDAO projectDAO = new ProjectDAO(ds);
    Project project = new Project();
    try {
      // �жϳ�ʱ
      HttpSession httpSession = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      int pro_code = StaticMethod.null2int(request.getParameter(
          "pro_code"));
      int id = StaticMethod.null2int(request.getParameter(
          "id"));
      project = projectDAO.getById(pro_code, id);
      org.apache.commons.beanutils.BeanUtils.populate(form,
          org.apache.commons.beanutils.BeanUtils.describe(
          project));

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * ִ���޸ĵĲ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */

  public ActionForward performUpdatedone(ActionMapping actionMapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
    ProjectDAO projectDAO = new ProjectDAO(ds);
    ProjectForm form = (ProjectForm) actionForm;
    Project project = new Project();

    try {
      HttpSession session = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      org.apache.commons.beanutils.BeanUtils.populate(project,
          org.apache.commons.beanutils.BeanUtils.describe(form));

      projectDAO.update(project);

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");

  }

  /**
   * ��ʾɾ��ҳ��
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */

  public ActionForward performDel(ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;
    ProjectDAO projectDAO = new ProjectDAO(ds);
    Project project = new Project();

    try {
      // �жϳ�ʱ
      HttpSession httpSession = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      int pro_code = StaticMethod.null2int(request.getParameter(
          "pro_code"));
      int id = StaticMethod.null2int(request.getParameter(
          "id"));
      project = projectDAO.getById(pro_code, id);

      org.apache.commons.beanutils.BeanUtils.populate(form,
          org.apache.commons.beanutils.BeanUtils.describe(
          project));

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * ִ��ɾ�����
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performDeldone(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
    ProjectDAO projectDAO = new ProjectDAO(ds);
    ProjectForm form = (ProjectForm) actionForm;
    Project project = new Project();
    try {
      HttpSession session = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      org.apache.commons.beanutils.BeanUtils.populate(project,
          org.apache.commons.beanutils.BeanUtils.describe(form));

      projectDAO.del(project);
    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  public ActionForward performDistri(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;
    ProjectDAO projectDAO = new ProjectDAO(ds);
    Project project = new Project();

    try {
      // �жϳ�ʱ
      HttpSession httpSession = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      request.setAttribute("userName", userName);

      int pro_code = StaticMethod.null2int(request.getParameter(
          "pro_code"));
      int parent_id = StaticMethod.null2int(request.getParameter(
          "parent_id"));
      project = projectDAO.getById(pro_code, parent_id);

      org.apache.commons.beanutils.BeanUtils.populate(form,
          org.apache.commons.beanutils.BeanUtils.describe(
          project));

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  public ActionForward performSavetask(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
    ProjectDAO projectDAO = new ProjectDAO(ds);
    ProjectForm form = (ProjectForm) actionForm;
    Project project = new Project();

    try {
      HttpSession session = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      org.apache.commons.beanutils.BeanUtils.populate(project,
          org.apache.commons.beanutils.BeanUtils.describe(form));

      projectDAO.inserttask(project);

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * ���б����ʽ��ʾ��ѯ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performTasklist(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;

    try {
      int length = PAGE_LENGTH;
      int offset;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      int parent_id = StaticMethod.null2int(request.getParameter(
          "parent_id"));
      if (parent_id == 0) {
        parent_id = Integer.parseInt(task_parent_id);
      }
      ProjectBO projectBO = new ProjectBO(ds);
      ArrayList list = (ArrayList) projectBO.getTasklist(offset, length,
          parent_id);
      int size = list.size();
      String url = request.getContextPath() + "/infmanage" +
          actionMapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size, length, url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("Project_TaskList", list);
      request.setAttribute("userName", userName);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * ���б����ʽ��ʾ��ѯ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performQueryTaskList(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;

    try {
      int length = PAGE_LENGTH;
      int offset;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      int parent_id = StaticMethod.null2int(request.getParameter(
          "parent_id"));
      if (parent_id == 0) {
        parent_id = Integer.parseInt(task_parent_id);
      }
      ProjectBO projectBO = new ProjectBO(ds);
      ArrayList list = (ArrayList) projectBO.getQueryTasklist(offset, length,
          parent_id);
      int size = list.size();
      String url = request.getContextPath() + "/infmanage" +
          actionMapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size, length, url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("Project_TaskList", list);
      request.setAttribute("userName", userName);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * ���б����ʽ��ʾ��ѯ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performMylist1(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;

    try {
      int length = PAGE_LENGTH;
      int offset;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      ProjectBO projectBO = new ProjectBO(ds);
      ArrayList list = (ArrayList) projectBO.getMyList(offset, length, user_id);
      int size = list.size();
      String url = request.getContextPath() + "/infmanage" +
          actionMapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size, length, url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("Project_LIST", list);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   *
   * @param actionMapping ActionMapping
   * @param actionForm ActionForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ActionForward
   */

  public ActionForward performTaskview(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;
    ProjectDAO projectDAO = new ProjectDAO(ds);
    Project project = new Project();
    try {
      // �жϳ�ʱ
      HttpSession httpSession = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      int id = StaticMethod.null2int(request.getParameter(
          "id"));
      project = projectDAO.getTaskById(id);
      org.apache.commons.beanutils.BeanUtils.populate(form,
          org.apache.commons.beanutils.BeanUtils.describe(
          project));

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   *
   * @param actionMapping ActionMapping
   * @param actionForm ActionForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ActionForward
   */
  public ActionForward performTaskUpdate(ActionMapping actionMapping,
                                         ActionForm actionForm,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;
    ProjectDAO projectDAO = new ProjectDAO(ds);
    Project project = new Project();
    try {
      // �жϳ�ʱ
      HttpSession httpSession = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }
      int id = StaticMethod.null2int(request.getParameter(
          "id"));
      project = projectDAO.getTaskById(id);
      org.apache.commons.beanutils.BeanUtils.populate(form,
          org.apache.commons.beanutils.BeanUtils.describe(
          project));

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * ִ���޸ĵĲ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */

  public ActionForward performTaskUpdateDone(ActionMapping actionMapping,
                                             ActionForm actionForm,
                                             HttpServletRequest request,
                                             HttpServletResponse response) {
    ProjectDAO projectDAO = new ProjectDAO(ds);
    ProjectForm form = (ProjectForm) actionForm;
    Project project = new Project();

    try {
      HttpSession session = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      org.apache.commons.beanutils.BeanUtils.populate(project,
          org.apache.commons.beanutils.BeanUtils.describe(form));

      projectDAO.updateTask(project);

      task_parent_id = project.getParent_id(); ////��̬

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");

  }

  /**
   * ��ʾɾ��ҳ��
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */

  public ActionForward performTaskDel(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;
    ProjectDAO projectDAO = new ProjectDAO(ds);
    Project project = new Project();

    try {
      // �жϳ�ʱ
      HttpSession httpSession = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      int id = StaticMethod.null2int(request.getParameter(
          "id"));
      project = projectDAO.getTaskById(id);

      org.apache.commons.beanutils.BeanUtils.populate(form,
          org.apache.commons.beanutils.BeanUtils.describe(
          project));

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * ִ��ɾ�����
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performTaskDelDone(ActionMapping actionMapping,
                                          ActionForm actionForm,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
    ProjectDAO projectDAO = new ProjectDAO(ds);
    ProjectForm form = (ProjectForm) actionForm;
    Project project = new Project();
    try {
      HttpSession session = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      org.apache.commons.beanutils.BeanUtils.populate(project,
          org.apache.commons.beanutils.BeanUtils.describe(form));

      projectDAO.delTask(project);
      task_parent_id = project.getParent_id(); ////��̬
    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**    * ���б����ʽ��ʾ��ѯ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performMyTask(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;

    try {
      int length = PAGE_LENGTH;
      int offset;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      ProjectBO projectBO = new ProjectBO(ds);
      ArrayList list = (ArrayList) projectBO.getMyTaskList(offset, length,
          user_id);
      int size = list.size();
      String url = request.getContextPath() + "/infmanage" +
          actionMapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size, length, url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("Project_TaskList", list);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /* *
   * @param actionMapping ActionMapping
   * @param actionForm ActionForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ActionForward
   */
  public ActionForward performUpdateTaskSign(ActionMapping actionMapping,
                                             ActionForm actionForm,
                                             HttpServletRequest request,
                                             HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;
    ProjectDAO projectDAO = new ProjectDAO(ds);
    Project project = new Project();
    try {
      // �жϳ�ʱ
      HttpSession httpSession = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }
      int id = StaticMethod.null2int(request.getParameter(
          "id"));
      project = projectDAO.getTaskById(id);
      org.apache.commons.beanutils.BeanUtils.populate(form,
          org.apache.commons.beanutils.BeanUtils.describe(
          project));

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * ִ���޸ĵĲ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */

  public ActionForward performUpdateTaskSignDone(ActionMapping actionMapping,
                                                 ActionForm actionForm,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) {
    ProjectDAO projectDAO = new ProjectDAO(ds);
    ProjectForm form = (ProjectForm) actionForm;
    Project project = new Project();

    try {
      HttpSession session = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      org.apache.commons.beanutils.BeanUtils.populate(project,
          org.apache.commons.beanutils.BeanUtils.describe(form));

      projectDAO.updateTaskSign(project);

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");

  }

  /**    * ���б����ʽ��ʾ��ѯ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performMyTask1(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;

    try {
      int length = PAGE_LENGTH;
      int offset;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }
      int local = StaticMethod.null2int(request.getParameter(
          "local"));
      ProjectBO projectBO = new ProjectBO(ds);
      ArrayList list = (ArrayList) projectBO.getMyTaskList1(offset, length,
          user_id, userName, 0, local);
      int size = list.size();
      String url = request.getContextPath() + "/infmanage" +
          actionMapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size, length, url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("Project_TaskList", list);
      request.setAttribute("userName", userName);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * ���б����ʽ��ʾ��ѯ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performTrackDone(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;

    try {
      int length = PAGE_LENGTH;
      int offset;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      int pro_id = StaticMethod.null2int(request.getParameter(
          "pro_id"));

      int task_id = StaticMethod.null2int(request.getParameter(
          "task_id"));

      // ��ѯ
      ProjectBO projectBO = new ProjectBO(ds);
      ArrayList list = (ArrayList) projectBO.getTrackList(offset, length,
          pro_id, task_id);
      int size = list.size();
      String url = request.getContextPath() + "/infmanage" +
          actionMapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size, length, url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("Project_LIST", list);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }
  /**
   * ���б����ʽ��ʾ��ѯ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performQueryTrackDone(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;

    try {
      int length = PAGE_LENGTH;
      int offset;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      int pro_id = StaticMethod.null2int(request.getParameter(
          "pro_id"));

      int task_id = StaticMethod.null2int(request.getParameter(
          "task_id"));

      // ��ѯ
      ProjectBO projectBO = new ProjectBO(ds);
      ArrayList list = (ArrayList) projectBO.getQueryTrackList(offset, length,
          pro_id, task_id);
      int size = list.size();
      String url = request.getContextPath() + "/infmanage" +
          actionMapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size, length, url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("Project_LIST", list);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }


  /**
   * ִ���޸ĵĲ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */

  public ActionForward performProCheckDone(ActionMapping actionMapping,
                                           ActionForm actionForm,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {
    ProjectDAO projectDAO = new ProjectDAO(ds);
    ProjectForm form = (ProjectForm) actionForm;
    Project project = new Project();

    try {
      HttpSession session = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      org.apache.commons.beanutils.BeanUtils.populate(project,
          org.apache.commons.beanutils.BeanUtils.describe(form));

      projectDAO.updateProCheck(project);

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");

  }

  /**
   * ִ���޸ĵĲ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */

  public ActionForward performTaskCheckDone(ActionMapping actionMapping,
                                            ActionForm actionForm,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
    ProjectDAO projectDAO = new ProjectDAO(ds);
    ProjectForm form = (ProjectForm) actionForm;
    Project project = new Project();

    try {
      HttpSession session = request.getSession();
	    //	edit by wangheqi 2.7 to 3.5
	 	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");
	      /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
	          httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 	 //edit end

      if (saveSessionBeanForm == null) {
        return actionMapping.findForward("timeout");
      }

      org.apache.commons.beanutils.BeanUtils.populate(project,
          org.apache.commons.beanutils.BeanUtils.describe(form));

      projectDAO.updateTaskCheck(project);

    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {

    }
    return actionMapping.findForward("success");

  }

  /**
   * ���б����ʽ��ʾ��ѯ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performProCheckList(ActionMapping actionMapping,
                                           ActionForm actionForm,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;

    try {
      int length = PAGE_LENGTH;
      int offset;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      // ��ѯ
      ProjectBO projectBO = new ProjectBO(ds);
      ArrayList list = (ArrayList) projectBO.getMyList2(offset, length, user_id,
          userName, 1, 0);
      int size = list.size();
      String url = request.getContextPath() + "/infmanage" +
          actionMapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size, length, url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("Project_LIST", list);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

  /**
   * ���б����ʽ��ʾ��ѯ���
   * @param actionMapping
   * @param actionForm
   * @param request
   * @param response
   * @return
   */
  public ActionForward performTaskCheckList(ActionMapping actionMapping,
                                            ActionForm actionForm,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
    ProjectForm form = (ProjectForm) actionForm;

    try {
      int length = PAGE_LENGTH;
      int offset;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      ProjectBO projectBO = new ProjectBO(ds);
      ArrayList list = (ArrayList) projectBO.getMyTaskList1(offset, length,
          user_id, userName, 1, 0);
      int size = list.size();
      String url = request.getContextPath() + "/infmanage" +
          actionMapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size, length, url);

      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("Project_TaskList", list);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {

    }
    return actionMapping.findForward("success");
  }

}
