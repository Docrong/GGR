package com.boco.eoms.infmanage.controller;

import java.util.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import org.apache.struts.util.LabelValueBean;
import org.apache.commons.beanutils.*;
import org.apache.struts.action.*;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.infmanage.bo.*;
import com.boco.eoms.infmanage.dao.*;
import com.boco.eoms.infmanage.model.*;
import org.apache.struts.util.LabelValueBean;
//import com.boco.eoms.common.controller.SaveSessionBeanForm;

//import com.boco.eoms.jbzl.bo.TawDeptBO;
//import com.boco.eoms.jbzl.dao.TawRmUserDAO;
//import com.boco.eoms.jbzl.dao.TawDeptDAO;
//import com.boco.eoms.jbzl.model.TawDept;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;

public class TawInfAddressBookAction
    extends Action {
  private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.
      ConnectionPool.getInstance();

  private static int PAGE_LENGTH = 20;
  private String user_id = "";
  public TawInfAddressBookAction() {
  }

  public ActionForward execute(ActionMapping mapping, ActionForm form
                               , HttpServletRequest request,
                               HttpServletResponse response) {
    ActionForward myforward = null;
    String myaction = mapping.getParameter();

    if ("".equalsIgnoreCase(myaction)) {
      myforward = mapping.findForward("failure");
    }

    if ("ADD".equalsIgnoreCase(myaction)) {
      myforward = performAdd(mapping, form, request, response);
    }

    if ("SAVE".equalsIgnoreCase(myaction)) {
      myforward = performSave(mapping, form, request, response);
    }
    if ("QUERY".equalsIgnoreCase(myaction)) {
      myforward = performQuery(mapping, form, request, response);
    }
    if ("QUERYDO".equalsIgnoreCase(myaction)) {
      myforward = performQueryDo(mapping, form, request, response);
    }
    if ("VIEW".equalsIgnoreCase(myaction)) {
      myforward = performView(mapping, form, request, response);
    }
    if ("EDIT".equalsIgnoreCase(myaction)) {
      myforward = performEdit(mapping, form, request, response);
    }
    if ("EDITSAVE".equalsIgnoreCase(myaction)) {
      myforward = performEditSave(mapping, form, request, response);
    }

    if ("DEL".equalsIgnoreCase(myaction)) {
      myforward = performDel(mapping, form, request, response);
    }
    if ("DELDO".equalsIgnoreCase(myaction)) {
      myforward = performDelDo(mapping, form, request, response);
    }
    if ("ADDGROUP".equalsIgnoreCase(myaction)) {
      myforward = performAddGroup(mapping, form, request, response);
    }
    if ("ADDGROUPDO".equalsIgnoreCase(myaction)) {
      myforward = performAddGroupDo(mapping, form, request, response);
    }
    if ("MAIL".equalsIgnoreCase(myaction)) {
      myforward = performMail(mapping, form, request, response);
    }
    if ("MAILTO".equalsIgnoreCase(myaction)) {
      myforward = performMailTo(mapping, form, request, response);
    }
    if ("COUNT".equalsIgnoreCase(myaction)) {
      myforward = performCount(mapping, form, request, response);
    }
    if ("CONF".equalsIgnoreCase(myaction)) {
      myforward = performConf(mapping, form, request, response);
    }

    return myforward;
  }

  private ActionForward performAdd(ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
    TawInfAddressBookForm form = (TawInfAddressBookForm) actionForm;

    //	edit by wangheqi 2.7 to 3.5
	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     request.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 //edit end

    if (saveSessionBeanForm == null) {
      return mapping.findForward("timeout");
    }
    try {
      String sdomIds = ""; //��ǰ�û�������Ӹ�λȨ�޵Ĳ���Ids
      if (!saveSessionBeanForm.getUserid().equalsIgnoreCase(
          StaticVariable.ADMIN)) {
        String sessionUserId = saveSessionBeanForm.getUserid();
        //edit by wangheqi
        TawSystemAssignBo privBO = null;
        //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
        Vector domIds = new Vector();
        domIds = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

        if (domIds.size() <= 0) {
          return mapping.findForward("nopriv");
        }
        else {
          for (int i = 0; i < domIds.size(); i++) {
            sdomIds += domIds.get(i).toString() + ",";
          }
          sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
        }
      }

      //���sdomIds�õ���ѡ��Ĳ����б�Ͷ�Ӧ����Ա�б�
      request.setAttribute("SDOMIDS", sdomIds);
      //edit by wangheqi 2.7 to 3.5
      //TawDeptBO tawBOO = new TawDeptBO(ds);
      Integer regionId=TawSystemDeptBo.getInstance().getRegion(saveSessionBeanForm.getDeptid(),"0");

      //int regionId = tawBOO.getRegionId(saveSessionBeanForm.getDeptid());
      request.setAttribute("REGIONID", String.valueOf(regionId));

      String userId = saveSessionBeanForm.getUserid();
      form.setUserId(userId);

      TawInfAddressBookDAO tawInfAddressBookDAO = new TawInfAddressBookDAO(ds);
      Vector allGroup = tawInfAddressBookDAO.getAllGroup(userId);
      form.setGroupTypeCollection(allGroup);
      form.setCompany("");
      form.setDeptName("");
      form.setSpecialty("");
      form.setDuty("");
      form.setName("");
      form.setGroupName("");
      form.setMobile("");
      form.setOfficeTel("");
      form.setSmart("");
      form.setEmail("");
      form.setRemark("");

      Vector allRecType = new Vector(1);
      allRecType.add(new LabelValueBean("����", "����"));
      allRecType.add(new LabelValueBean("����", "����"));
      form.setRecTypeCollection(allRecType);


    }
    catch (Exception e) {
      e.printStackTrace();
      mapping.findForward("failure");
    }
    finally {
      //�ͷŶ���
    }

    return mapping.findForward("success");
  }

  /**
   * @see ����t·��Ϣ
   */
  private ActionForward performSave(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
    TawInfAddressBookForm form = (TawInfAddressBookForm) actionForm;
    String ret = "";
    String deptName = "";
    String company = "";
    String specialty = "";
    String duty = "";
    String name = "";
    int groupId = 0;
    String userId = "";
    String mobile = "";
    String officeTel = "";
    String smart = "";
    String email = "";
    String remark = "";
    String recType = "";

    try {
      TawInfAddressBookDAO tawInfAddressBookDAO = new TawInfAddressBookDAO(ds);
      TawInfAddressBook tawInfAddressBook = new TawInfAddressBook();

      org.apache.commons.beanutils.BeanUtils.populate(tawInfAddressBook,
          org.apache.commons.beanutils.BeanUtils.describe(form));

      deptName = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getDeptName().trim());
      company = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getCompany().trim());
      specialty = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getSpecialty().trim());
      duty = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getDuty().trim());
      name = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getName().trim());
      groupId = form.getGroupId();
      userId = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getUserId().trim());
      mobile = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getMobile().trim());
      officeTel = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getOfficeTel().trim());
      smart = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getSmart().trim());
      email = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getEmail().trim());
      remark = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getRemark().trim());
      recType = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getRecType().trim());

      tawInfAddressBook.setDeptName(deptName);
      tawInfAddressBook.setCompany(company);
      tawInfAddressBook.setSpecialty(specialty);
      tawInfAddressBook.setDuty(duty);
      tawInfAddressBook.setName(name);
      tawInfAddressBook.setGroupId(groupId);
      tawInfAddressBook.setUserId(userId);
      tawInfAddressBook.setMobile(mobile);
      tawInfAddressBook.setOfficeTel(officeTel);
      tawInfAddressBook.setSmart(smart);
      tawInfAddressBook.setEmail(email);
      tawInfAddressBook.setRemark(remark);
      tawInfAddressBook.setRecType(recType);

      tawInfAddressBookDAO.insert(tawInfAddressBook);
    }
    catch (Exception e) {
      generalError(request, e);
      ret = "failure";
      //return mapping.findForward("failure");
    }
    finally {
      if (ret.equals("")) {
        ret = "success";
      }
      // this.insertLog(mapping, request, ret, name);
    }

    return mapping.findForward(ret);
  }

  /**
   * @see ��ѯt·��ʼ��
   */
  private ActionForward performQuery(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
    TawInfAddressBookForm form = (TawInfAddressBookForm) actionForm;
    //	edit by wangheqi 2.7 to 3.5
	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     request.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 //edit end

    if (saveSessionBeanForm == null) {
      return mapping.findForward("timeout");
    }

    try {
      String sdomIds = ""; //��ǰ�û�������Ӹ�λȨ�޵Ĳ���Ids
      if (!saveSessionBeanForm.getUserid().equalsIgnoreCase(
          StaticVariable.ADMIN)) {
        String sessionUserId = saveSessionBeanForm.getUserid();
        //edit by wangheqi
        TawSystemAssignBo privBO = null;
        //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
        Vector domIds = new Vector();
        StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10


        if (domIds.size() <= 0) {
          return mapping.findForward("nopriv");
        }
        else {
          for (int i = 0; i < domIds.size(); i++) {
            sdomIds += domIds.get(i).toString() + ",";
          }
          sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
        }
      }

      //���sdomIds�õ���ѡ��Ĳ����б�Ͷ�Ӧ����Ա�б�
      request.setAttribute("SDOMIDS", sdomIds);
      //edit by wangehqi 2.7 to 3.5
      //TawDeptBO tawBOO = new TawDeptBO(ds);
      Integer regionId=TawSystemDeptBo.getInstance().getRegion(saveSessionBeanForm.getDeptid(), "0");

      //int regionId = tawBOO.getRegionId(saveSessionBeanForm.getUserid());
      request.setAttribute("REGIONID", String.valueOf(regionId));

      request.getSession().removeAttribute("tawInfAddressBookForm");

      TawInfAddressBookDAO tawInfAddressBookDAO = new TawInfAddressBookDAO(ds);
      String userId = saveSessionBeanForm.getUserid();
      form.setUserId(userId);

      Vector allGroup = tawInfAddressBookDAO.getAllGroup(userId);
      form.setGroupTypeCollection(allGroup);

      Vector allRecType = new Vector(1);
      allRecType.add(new LabelValueBean("ȫ��", ""));
      allRecType.add(new LabelValueBean("����", "����"));
      allRecType.add(new LabelValueBean("����", "����"));
      form.setRecTypeCollection(allRecType);
    }
    catch (Exception e) {
      generalError(request, e);
      e.printStackTrace();
      return mapping.findForward("failure");
    }

    return mapping.findForward("success");
  }

  private ActionForward performQueryDo(ActionMapping mapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
    TawInfAddressBookForm form = (TawInfAddressBookForm) actionForm;
    //	edit by wangheqi 2.7 to 3.5
	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     request.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 //edit end

    if (saveSessionBeanForm == null) {
      return mapping.findForward("timeout");
    }

    try {
      String sdomIds = "";
      if (!StaticVariable.ADMIN.equalsIgnoreCase(saveSessionBeanForm.
                                                 getUserid())) {

        String sessionUserId = saveSessionBeanForm.getUserid();
        //edit by wangheqi
        TawSystemAssignBo privBO = null;
        //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
        Vector domIds = new Vector();
        domIds = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

        //domIds = tawVPBO.validatePriv(sessionUserId,
        //                              "/TawDictPost/query");

        if (domIds.size() <= 0) {
          return mapping.findForward("nopriv");
        }
        else {
          for (int i = 0; i < domIds.size(); i++) {
            sdomIds += domIds.get(i).toString() + ",";
          }
          sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
        }

      }

      TawInfAddressBookDAO tawAPMDAO = new TawInfAddressBookDAO(ds);

      int offset;
      int length = PAGE_LENGTH;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      TawInfAddressBookBO tawInfAddressBookBO = new TawInfAddressBookBO(ds);
      form.setUserId(saveSessionBeanForm.getUserid());
      System.out.println(saveSessionBeanForm.getUserid());
      String condition = tawInfAddressBookBO.selectByConditions(form, sdomIds);
      List tawAPMs = tawInfAddressBookBO.getlist(offset, length, condition);

      String[] objKeys = {
          "TawInfAddressBook", "queryDo"};
      String objKey = CacheManager.createKey(objKeys);
      Integer size = new Integer(tawAPMDAO.getSize(
          "taw_inf_address a, taw_inf_group b ",
          condition));

      String url = request.getContextPath() + "/infmanage" +
          mapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size.intValue(), length,
                                          url);
      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("TAWAPMS", tawAPMs);
    }
    catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }
    return mapping.findForward("success");
  }

  private ActionForward performView(ActionMapping actionMapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
    //�����ж�Ȩ��,��ѯʱ�Ѿ��ж�
    TawInfAddressBookForm form = (TawInfAddressBookForm) actionForm;
    TawInfAddressBook tawInfAddressBook = new TawInfAddressBook();
    TawInfAddressBookDAO tawInfAddressBookDAO = new TawInfAddressBookDAO(ds);
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

    try {

      String sdomIds = ""; //��ǰ�û�����Ȩ�޵Ĳ���Ids
      if (!saveSessionBeanForm.getUserid().equalsIgnoreCase(
          StaticVariable.ADMIN)) {
        String sessionUserId = saveSessionBeanForm.getUserid();
        //edit by wangheqi
        TawSystemAssignBo privBO = null;
        //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
        Vector domIds = new Vector();
        domIds = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

        //domIds = tawVPBO.validatePriv(sessionUserId,
        //                              actionMapping.getPath()); // �����ϵͳ������Ա�򷵻�-10

        if (domIds.size() <= 0) {
          return actionMapping.findForward("nopriv");
        }
        else {
          for (int i = 0; i < domIds.size(); i++) {
            sdomIds += domIds.get(i).toString() + ",";
          }
          sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
        }
        boolean hasPriv = false;
        int para_DeptId = Integer.parseInt(saveSessionBeanForm.getDeptid());

        //      System.out.println("ddd==="+para_DeptId);
        int para_Id = StaticMethod.null2int(request.getParameter("id"));

        for (int i = 0; i < domIds.size(); i++) {
          if (Integer.parseInt(domIds.get(i).toString()) ==
              para_DeptId) {
            hasPriv = true;
            break;
          }
        }
        if (!hasPriv) {
          return actionMapping.findForward("nopriv");
        }

      }

      int id = StaticMethod.null2int(request.getParameter("id"));
      tawInfAddressBook = tawInfAddressBookDAO.getById(id);
      if (tawInfAddressBook == null) {
      }
      else {
        org.apache.commons.beanutils.BeanUtils.populate(form,
            org.apache.commons.beanutils.BeanUtils.describe(tawInfAddressBook));
      }

    }
    catch (Exception e) {
      actionMapping.findForward("failure");
      e.printStackTrace();
    }
    finally {
      if (tawInfAddressBook != null) {
        tawInfAddressBook = null;
      }
      if (tawInfAddressBookDAO != null) {
        tawInfAddressBookDAO = null;
      }
    }
    return actionMapping.findForward("success");
  }

  private ActionForward performEdit(ActionMapping actionMapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

    TawInfAddressBookForm form = (TawInfAddressBookForm) actionForm;
    TawInfAddressBook tawInfAddressBook = new TawInfAddressBook();
    TawInfAddressBookDAO tawInfAddressBookDAO = new TawInfAddressBookDAO(ds);
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

    try {
      String sdomIds = ""; //��ǰ�û�������Ӹ�λȨ�޵Ĳ���Ids
      if (!saveSessionBeanForm.getUserid().equalsIgnoreCase(
          StaticVariable.ADMIN)) {
        String sessionUserId = saveSessionBeanForm.getUserid();
        //edit by wangheqi
        TawSystemAssignBo privBO = null;
        //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
        Vector domIds = new Vector();
        domIds = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

        //domIds = tawVPBO.validatePriv(sessionUserId,
        //                              actionMapping.getPath()); // �����ϵͳ������Ա�򷵻�-10

        if (domIds.size() <= 0) {
          return actionMapping.findForward("nopriv");
        }
        else {
          for (int i = 0; i < domIds.size(); i++) {
            sdomIds += domIds.get(i).toString() + ",";
          }
          sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
        }
        boolean hasPriv = false;
        int para_DeptId = Integer.parseInt(saveSessionBeanForm.getDeptid());
        int para_Id = StaticMethod.null2int(request.getParameter("id"));
        if (para_Id == 0) {
          para_Id = StaticMethod.null2int( (String) request.getAttribute("id"));
        }

        for (int i = 0; i < domIds.size(); i++) {
          if (Integer.parseInt(domIds.get(i).toString()) ==
              para_DeptId) {
            hasPriv = true;
            break;
          }
        }
        if (!hasPriv) {
          return actionMapping.findForward("nopriv");
        }

      }

      //���sdomIds�õ���ѡ��Ĳ����б�Ͷ�Ӧ����Ա�б�
      request.setAttribute("SDOMIDS", sdomIds);
      //edit by wangheqi 2.7 to 3.5
      //TawDeptBO tawBOO = new TawDeptBO(ds);
      Integer regionId=TawSystemDeptBo.getInstance().getRegion(saveSessionBeanForm.getDeptid(), "0");
      //int regionId = tawBOO.getRegionId(saveSessionBeanForm.getDeptid());
      request.setAttribute("REGIONID", String.valueOf(regionId));

      int id = StaticMethod.null2int(request.getParameter("id"));
      if (id == 0) {
        id = StaticMethod.null2int( (String) request.getAttribute("id"));
      }

      tawInfAddressBook = tawInfAddressBookDAO.getById(id);
      if (tawInfAddressBook == null) {
      }
      else {
        org.apache.commons.beanutils.BeanUtils.populate(form,
            org.apache.commons.beanutils.BeanUtils.describe(tawInfAddressBook));

        String userId = saveSessionBeanForm.getUserid();
        form.setUserId(userId);

        Vector allGroup = tawInfAddressBookDAO.getAllGroup(userId);
        form.setGroupTypeCollection(allGroup);

        Vector allRecType = new Vector(1);
        allRecType.add(new LabelValueBean("����", "����"));
        allRecType.add(new LabelValueBean("����", "����"));
        form.setRecTypeCollection(allRecType);

      }

    }
    catch (Exception e) {
      actionMapping.findForward("failure");
      e.printStackTrace();
    }
    finally {
      if (tawInfAddressBook != null) {
        tawInfAddressBook = null;
      }
      if (tawInfAddressBookDAO != null) {
        tawInfAddressBookDAO = null;
      }
    }
    return actionMapping.findForward("success");
  }

  private ActionForward performDel(ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

    TawInfAddressBookForm form = (TawInfAddressBookForm) actionForm;
    TawInfAddressBook tawInfAddressBook = new TawInfAddressBook();
    TawInfAddressBookDAO tawInfAddressBookDAO = new TawInfAddressBookDAO(ds);
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

    try {
      String sdomIds = ""; //��ǰ�û�����Ȩ�޵Ĳ���Ids
      if (!saveSessionBeanForm.getUserid().equalsIgnoreCase(
          StaticVariable.ADMIN)) {
        String sessionUserId = saveSessionBeanForm.getUserid();
        //edit by wangheqi
        TawSystemAssignBo privBO = null;
        //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
        Vector domIds = new Vector();
        domIds = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

        //domIds = tawVPBO.validatePriv(sessionUserId,
        //                              actionMapping.getPath()); // �����ϵͳ������Ա�򷵻�-10

        if (domIds.size() <= 0) {
          return actionMapping.findForward("nopriv");
        }
        else {
          for (int i = 0; i < domIds.size(); i++) {
            sdomIds += domIds.get(i).toString() + ",";
          }
          sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
        }
        boolean hasPriv = false;
        int para_DeptId = Integer.parseInt(saveSessionBeanForm.getDeptid());
        int para_Id = StaticMethod.null2int(request.getParameter("id"));

        for (int i = 0; i < domIds.size(); i++) {
          if (Integer.parseInt(domIds.get(i).toString()) ==
              para_DeptId) {
            hasPriv = true;
            break;
          }
        }
        if (!hasPriv) {
          return actionMapping.findForward("nopriv");
        }

      }

      int id = StaticMethod.null2int(request.getParameter("id"));
      tawInfAddressBook = tawInfAddressBookDAO.getById(id);
      if (tawInfAddressBook == null) {
      }
      else {
        org.apache.commons.beanutils.BeanUtils.populate(form,
            org.apache.commons.beanutils.BeanUtils.describe(tawInfAddressBook));
      }

    }
    catch (Exception e) {
      actionMapping.findForward("failure");
      e.printStackTrace();
    }
    finally {
      if (tawInfAddressBook != null) {
        tawInfAddressBook = null;
      }
      if (tawInfAddressBookDAO != null) {
        tawInfAddressBookDAO = null;
      }
    }
    return actionMapping.findForward("success");
  }

  private ActionForward performDelDo(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {

    TawInfAddressBookDAO tawInfAddressBookDAO = new TawInfAddressBookDAO(ds);

    try {
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

      int id = StaticMethod.null2int(request.getParameter("id"));

      //��ݼ�¼Idɾ��
      tawInfAddressBookDAO.delete(id);
    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {
      if (tawInfAddressBookDAO != null) {
        tawInfAddressBookDAO = null;
      }
    }
    return actionMapping.findForward("success");
  }

  private ActionForward performEditSave(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
    TawInfAddressBookForm form = (TawInfAddressBookForm) actionForm;
    TawInfAddressBook tawInfAddressBook = new TawInfAddressBook();
    TawInfAddressBookDAO tawInfAddressBookDAO = new TawInfAddressBookDAO(ds);
    String ret = "";
    String deptName = "";
    String company = "";
    String specialty = "";
    String duty = "";
    String name = "";
    int groupId = 0;
    String groupName = "";
    String userId = "";
    String mobile = "";
    String officeTel = "";
    String smart = "";
    String email = "";
    String remark = "";
    String recType = "";

    try {
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

      org.apache.commons.beanutils.BeanUtils.populate(tawInfAddressBook,
          org.apache.commons.beanutils.BeanUtils.describe(form));

      tawInfAddressBookDAO.update(tawInfAddressBook);
      form.setId(0);
      form.setDeptName("");
      form.setCompany("");
      form.setSpecialty("");
      form.setDuty("");
      form.setName("");
      form.setGroupId(0);
      form.setGroupName("");
      form.setUserId("");
      form.setMobile("");
      form.setOfficeTel("");
      form.setSmart("");
      form.setEmail("");
      form.setRemark("");
      form.setRecType("");

    }
    catch (Exception e) {
      actionMapping.findForward("failure");
      e.printStackTrace();
    }
    finally {
      if (tawInfAddressBook != null) {
        tawInfAddressBook = null;
      }
      if (tawInfAddressBookDAO != null) {
        tawInfAddressBookDAO = null;
      }
      if (form != null) {
        form = null;
      }
    }

    return actionMapping.findForward("success");
  }

  private ActionForward performAddGroup(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
    TawInfAddressBookForm form = (TawInfAddressBookForm) actionForm;

    //	edit by wangheqi 2.7 to 3.5
	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     request.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 //edit end

    if (saveSessionBeanForm == null) {
      return mapping.findForward("timeout");
    }
    try {
      String sdomIds = ""; //��ǰ�û�������Ӹ�λȨ�޵Ĳ���Ids
      if (!saveSessionBeanForm.getUserid().equalsIgnoreCase(
          StaticVariable.ADMIN)) {
        String sessionUserId = saveSessionBeanForm.getUserid();
        //edit by wangheqi
        TawSystemAssignBo privBO = null;
        //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
        Vector domIds = new Vector();
        domIds = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

        //domIds = tawVPBO.validatePriv(sessionUserId, mapping.getPath()); // �����ϵͳ������Ա�򷵻�-10

        if (domIds.size() <= 0) {
          return mapping.findForward("nopriv");
        }
        else {
          for (int i = 0; i < domIds.size(); i++) {
            sdomIds += domIds.get(i).toString() + ",";
          }
          sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
        }
      }
      String type = request.getParameter("type");
      request.setAttribute("type", type);
      String id = request.getParameter("id");
      if (id != null) {
        request.setAttribute("id", id);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      mapping.findForward("failure");
    }
    finally {
      //�ͷŶ���
    }

    return mapping.findForward("success");
  }

  private ActionForward performAddGroupDo(ActionMapping mapping,
                                          ActionForm actionForm,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
    TawInfAddressBookForm form = (TawInfAddressBookForm) actionForm;
    //	edit by wangheqi 2.7 to 3.5
	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     request.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 //edit end

    if (saveSessionBeanForm == null) {
      return mapping.findForward("timeout");
    }

    String ret = "";
    String groupName = "";
    String temp = "";
    boolean flag = true;
    String type = "";
    String userId = saveSessionBeanForm.getUserid();
    try {
      TawInfAddressBookDAO tawInfAddressBookDAO = new TawInfAddressBookDAO(ds);
      groupName = request.getParameter("groupName");
      type = request.getParameter("type");
      Vector allGroupName = tawInfAddressBookDAO.getAllGroupName(userId);
      for (int i = 0; i < allGroupName.size(); i++) {
        temp = (String) allGroupName.elementAt(i);
        if (temp.equals(groupName)) {
          flag = false;
          break;
        }
      }
      if (flag) {
        tawInfAddressBookDAO.insertGroup(groupName, userId);
      }
    }
    catch (Exception e) {
      generalError(request, e);
      ret = "failure";
      //return mapping.findForward("failure");
    }
    finally {
      if ("edit".equals(type)) {
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        ret = "edit";
      }
      else if (ret.equals("")) {
        ret = "success";
      }
      // this.insertLog(mapping, request, ret, name);
    }

    return mapping.findForward(ret);
  }

  private ActionForward performMail(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
    TawInfAddressBookForm form = (TawInfAddressBookForm) actionForm;
    //	edit by wangheqi 2.7 to 3.5
	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     request.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 //edit end

    if (saveSessionBeanForm == null) {
      return mapping.findForward("timeout");
    }

    try {
      String sdomIds = ""; //��ǰ�û�������Ӹ�λȨ�޵Ĳ���Ids
      if (!saveSessionBeanForm.getUserid().equalsIgnoreCase(
          StaticVariable.ADMIN)) {
        String sessionUserId = saveSessionBeanForm.getUserid();
        //edit by wangheqi
        TawSystemAssignBo privBO = null;
        //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
        Vector domIds = new Vector();
        domIds = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

        //domIds = tawVPBO.validatePriv(sessionUserId, mapping.getPath()); // �����ϵͳ������Ա�򷵻�-10

        if (domIds.size() <= 0) {
          return mapping.findForward("nopriv");
        }
        else {
          for (int i = 0; i < domIds.size(); i++) {
            sdomIds += domIds.get(i).toString() + ",";
          }
          sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
        }
      }

      //���sdomIds�õ���ѡ��Ĳ����б�Ͷ�Ӧ����Ա�б�
      request.setAttribute("SDOMIDS", sdomIds);
      //edit by wangheqi 2.7 to 3.5
      //TawDeptBO tawBOO = new TawDeptBO(ds);
      //int regionId = tawBOO.getRegionId(saveSessionBeanForm.getDeptid());
      Integer regionId=TawSystemDeptBo.getInstance().getRegion(saveSessionBeanForm.getDeptid(), "0");
      request.setAttribute("REGIONID", String.valueOf(regionId));

      request.getSession().removeAttribute("tawInfAddressBookForm");

      TawInfAddressBookDAO tawInfAddressBookDAO = new TawInfAddressBookDAO(ds);
      String userId = saveSessionBeanForm.getUserid();
      form.setUserId(userId);

      Vector allGroup = tawInfAddressBookDAO.getAllGroup(userId);
      form.setGroupTypeCollection(allGroup);

      Vector allRecType = new Vector(1);
      allRecType.add(new LabelValueBean("ȫ��", ""));
      allRecType.add(new LabelValueBean("����", "����"));
      allRecType.add(new LabelValueBean("����", "����"));
      form.setRecTypeCollection(allRecType);

    }
    catch (Exception e) {
      generalError(request, e);
      e.printStackTrace();
      return mapping.findForward("failure");
    }

    return mapping.findForward("success");
  }

  private ActionForward performMailTo(ActionMapping mapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
    TawInfAddressBookForm form = (TawInfAddressBookForm) actionForm;
    //	edit by wangheqi 2.7 to 3.5
	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     request.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 //edit end

    if (saveSessionBeanForm == null) {
      return mapping.findForward("timeout");
    }

    try {
      String sdomIds = "";
      if (!StaticVariable.ADMIN.equalsIgnoreCase(saveSessionBeanForm.
                                                 getUserid())) {

        String sessionUserId = saveSessionBeanForm.getUserid();
        //edit by wangheqi
        TawSystemAssignBo privBO = null;
        //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
        Vector domIds = new Vector();
        domIds = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

        //domIds = tawVPBO.validatePriv(sessionUserId,
       //                               "/TawDictPost/query");

        if (domIds.size() <= 0) {
          return mapping.findForward("nopriv");
        }
        else {
          for (int i = 0; i < domIds.size(); i++) {
            sdomIds += domIds.get(i).toString() + ",";
          }
          sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
        }

      }

      TawInfAddressBookDAO tawAPMDAO = new TawInfAddressBookDAO(ds);

      int offset;
      int length = 1000;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }
      // offset = 0;
      TawInfAddressBookBO tawInfAddressBookBO = new TawInfAddressBookBO(ds);
      String condition = tawInfAddressBookBO.selectByConditions(form, sdomIds);
      List tawAPMs = tawInfAddressBookBO.getlist(offset, length, condition);

      String[] objKeys = {
          "TawInfAddressBook", "queryDo"};
      String objKey = CacheManager.createKey(objKeys);
      Integer size = new Integer(tawAPMDAO.getSize(
          "taw_inf_address a, taw_inf_group b ",
          condition));

      String url = request.getContextPath() + "/infmanage" +
          mapping.getPath() + ".do";
      String pagerHeader = Pager.generate(offset, size.intValue(), length,
                                          url);
      request.setAttribute("pagerHeader", pagerHeader);
      request.setAttribute("TAWAPMS", tawAPMs);
    }
    catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }
    return mapping.findForward("success");
  }

  private ActionForward performCount(ActionMapping mapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
    TawInfAddressBookForm form = (TawInfAddressBookForm) actionForm;
    //	edit by wangheqi 2.7 to 3.5
	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     request.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 //edit end

    if (saveSessionBeanForm == null) {
      return mapping.findForward("timeout");
    }

    try {

      int id = StaticMethod.null2int(request.getParameter("id"));
      String mail = StaticMethod.null2String(request.getParameter("mail"));

      String sdomIds = ""; //��ǰ�û�������Ӹ�λȨ�޵Ĳ���Ids
      if (!saveSessionBeanForm.getUserid().equalsIgnoreCase(
          StaticVariable.ADMIN)) {
        String sessionUserId = saveSessionBeanForm.getUserid();
        //edit by wangheqi
        TawSystemAssignBo privBO = null;
        //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
        Vector domIds = new Vector();
        domIds = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT));// �����ϵͳ������Ա�򷵻�-10

        //domIds = tawVPBO.validatePriv(sessionUserId, mapping.getPath()); // �����ϵͳ������Ա�򷵻�-10

        if (domIds.size() <= 0) {
          return mapping.findForward("nopriv");
        }
        else {
          for (int i = 0; i < domIds.size(); i++) {
            sdomIds += domIds.get(i).toString() + ",";
          }
          sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
        }
      }

      //���sdomIds�õ���ѡ��Ĳ����б�Ͷ�Ӧ����Ա�б�
      request.setAttribute("SDOMIDS", sdomIds);
      //edit by wangheqi 2.7 to 3.5
      //TawDeptBO tawBOO = new TawDeptBO(ds);
      Integer regionId=TawSystemDeptBo.getInstance().getRegion(saveSessionBeanForm.getDeptid(), "0");
      //int regionId = tawBOO.getRegionId(saveSessionBeanForm.getDeptid());
      request.setAttribute("REGIONID", String.valueOf(regionId));

      request.getSession().removeAttribute("tawInfAddressBookForm");

      TawInfAddressBookDAO tawInfAddressBookDAO = new TawInfAddressBookDAO(ds);
      tawInfAddressBookDAO.updateCount(id);
      request.setAttribute("mail", mail);

    }
    catch (Exception e) {
      generalError(request, e);
      e.printStackTrace();
      return mapping.findForward("failure");
    }

    return mapping.findForward("success");
  }

  private ActionForward performConf(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

    ArrayList list = null;
    ArrayList list2 = null;

    ArrayList temp = new ArrayList();
    //	edit by wangheqi 2.7 to 3.5
	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     request.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 //edit end

    if (saveSessionBeanForm == null) {
      return mapping.findForward("timeout");
    }

    try {
      String userId = saveSessionBeanForm.getUserid();
      TawInfAddressBookDAO tawDAO = new TawInfAddressBookDAO(ds);
      list = tawDAO.getNameMail(userId);

      if (list.size() > 1) {
        request.setAttribute("TAWOPERATES_NAME", (ArrayList) list.get(0));
        request.setAttribute("TAWOPERATES_ID", (ArrayList) list.get(1));
      }
      else {
        request.setAttribute("TAWOPERATES_NAME", temp);
        request.setAttribute("TAWOPERATES_ID", temp);
      }
      request.setAttribute("O_TAWOPERATES_NAME", temp);
      request.setAttribute("O_TAWOPERATES_ID", temp);

    }
    catch (Exception e) {
      generalError(request, e);
      return mapping.findForward("failure");
    }
    finally {
      list = null;
      list2 = null;
    }
    return mapping.findForward("success");
  }

  private void generalError(HttpServletRequest request, Exception e) {
    ActionErrors aes = new ActionErrors();
    aes.add(aes.GLOBAL_ERROR, new ActionError("error.general", e.getMessage()));
    saveErrors(request, aes);
    e.printStackTrace();
  }
}
