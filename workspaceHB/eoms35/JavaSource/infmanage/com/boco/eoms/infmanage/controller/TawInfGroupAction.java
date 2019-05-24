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

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
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

public class TawInfGroupAction
    extends Action {
  private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.
      ConnectionPool.getInstance();

  private static int PAGE_LENGTH = 20;
  private String user_id = "";
  public TawInfGroupAction() {
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
    if ("QUERYDO".equalsIgnoreCase(myaction)) {
      myforward = performQueryDo(mapping, form, request, response);
    }
    if ("EDIT".equalsIgnoreCase(myaction)) {
      myforward = performEdit(mapping, form, request, response);
    }
    if ("EDITSAVE".equalsIgnoreCase(myaction)) {
      myforward = performEditSave(mapping, form, request, response);
    }
    if ("DELDO".equalsIgnoreCase(myaction)) {
      myforward = performDelDo(mapping, form, request, response);
    }
    return myforward;
  }

  private ActionForward performAdd(ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
    TawInfGroupForm form = (TawInfGroupForm) actionForm;

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
      Integer regionId=TawSystemDeptBo.getInstance().getRegion(saveSessionBeanForm.getDeptid(),"0");

      //int regionId = tawBOO.getRegionId(saveSessionBeanForm.getDeptid());
      request.setAttribute("REGIONID", String.valueOf(regionId));
      form.setGroupName("");
      String userId = saveSessionBeanForm.getUserid();
      form.setUserId(userId);

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
    TawInfGroupForm form = (TawInfGroupForm) actionForm;
    String ret = "";

    int groupId = 0;
    String groupName = "";
    String userId = "";
    String temp = "";
    boolean flag = true;

    try {
      TawInfGroupDAO tawInfGroupDAO = new TawInfGroupDAO(ds);
      TawInfGroup tawInfGroup = new TawInfGroup();

      org.apache.commons.beanutils.BeanUtils.populate(tawInfGroup,
          org.apache.commons.beanutils.BeanUtils.describe(form));

      groupId = form.getGroupId();
      groupName = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getGroupName().trim());
      userId = com.boco.eoms.common.util.StaticMethod.dbNull2String(form.getUserId().trim());

      tawInfGroup.setGroupId(groupId);
      tawInfGroup.setGroupName(groupName);
      tawInfGroup.setUserId(userId);

      Vector allGroupName = tawInfGroupDAO.getAllGroupName(userId);
      for (int i = 0; i < allGroupName.size(); i++) {
        temp = (String) allGroupName.elementAt(i);
        if (temp.equals(groupName)) {
          flag = false;
          break;
        }
      }
      if (flag) {
        tawInfGroupDAO.insert(tawInfGroup);
      }
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

  private ActionForward performQueryDo(ActionMapping mapping,
                                       ActionForm actionForm,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
    TawInfGroupForm form = (TawInfGroupForm) actionForm;
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

      TawInfGroupDAO tawAPMDAO = new TawInfGroupDAO(ds);

      int offset;
      int length = PAGE_LENGTH;
      String pageOffset = request.getParameter("pager.offset");
      if (pageOffset == null || pageOffset.equals("")) {
        offset = 0;
      }
      else {
        offset = Integer.parseInt(pageOffset);
      }

      TawInfGroupBO tawInfGroupBO = new TawInfGroupBO(ds);
      form.setUserId(saveSessionBeanForm.getUserid());
      form.setGroupName("");
      System.out.println(saveSessionBeanForm.getUserid());
      String condition = tawInfGroupBO.selectByConditions(form, sdomIds);
      List tawAPMs = tawInfGroupBO.getlist(offset, length, condition);

      String[] objKeys = {
          "TawInfGroup", "queryDo"};
      String objKey = CacheManager.createKey(objKeys);
      Integer size = new Integer(tawAPMDAO.getSize(
          "taw_inf_group ",
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

  private ActionForward performEdit(ActionMapping actionMapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

    TawInfGroupForm form = (TawInfGroupForm) actionForm;
    TawInfGroup tawInfGroup = new TawInfGroup();
    TawInfGroupDAO tawInfGroupDAO = new TawInfGroupDAO(ds);
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
      Integer regionId=TawSystemDeptBo.getInstance().getRegion(saveSessionBeanForm.getDeptid(),"0");

      //int regionId = tawBOO.getRegionId(saveSessionBeanForm.getDeptid());
      request.setAttribute("REGIONID", String.valueOf(regionId));

      int id = StaticMethod.null2int(request.getParameter("id"));
      if (id == 0) {
        id = StaticMethod.null2int( (String) request.getAttribute("id"));
      }

      tawInfGroup = tawInfGroupDAO.getById(id);
      if (tawInfGroup == null) {
      }
      else {
        org.apache.commons.beanutils.BeanUtils.populate(form,
            org.apache.commons.beanutils.BeanUtils.describe(tawInfGroup));

        String userId = saveSessionBeanForm.getUserid();
        form.setUserId(userId);
        form.setGroupId(id);

      }

    }
    catch (Exception e) {
      actionMapping.findForward("failure");
      e.printStackTrace();
    }
    finally {
      if (tawInfGroup != null) {
        tawInfGroup = null;
      }
      if (tawInfGroupDAO != null) {
        tawInfGroupDAO = null;
      }
    }
    return actionMapping.findForward("success");
  }

  private ActionForward performDelDo(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {

    TawInfGroupDAO tawInfGroupDAO = new TawInfGroupDAO(ds);

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
      tawInfGroupDAO.delete(id);
    }
    catch (Exception e) {
      e.printStackTrace();
      actionMapping.findForward("failure");
    }
    finally {
      if (tawInfGroupDAO != null) {
        tawInfGroupDAO = null;
      }
    }
    return actionMapping.findForward("success");
  }

  private ActionForward performEditSave(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
    TawInfGroupForm form = (TawInfGroupForm) actionForm;
    TawInfGroup tawInfGroup = new TawInfGroup();
    TawInfGroupDAO tawInfGroupDAO = new TawInfGroupDAO(ds);
    String ret = "";

    int groupId = 0;
    String groupName = form.getGroupName();
    String userId = form.getUserId();
    String temp = "";
    boolean flag = true;

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

      org.apache.commons.beanutils.BeanUtils.populate(tawInfGroup,
          org.apache.commons.beanutils.BeanUtils.describe(form));
      Vector allGroupName = tawInfGroupDAO.getAllGroupName(userId);
      for (int i = 0; i < allGroupName.size(); i++) {
        temp = (String) allGroupName.elementAt(i);
        if (temp.equals(groupName)) {
          flag = false;
          break;
        }
      }
      if (flag) {
        tawInfGroupDAO.update(tawInfGroup);
      }
      form.setGroupId(0);
      form.setGroupName("");
      form.setUserId("");
    }
    catch (Exception e) {
      actionMapping.findForward("failure");
      e.printStackTrace();
    }
    finally {
      if (tawInfGroup != null) {
        tawInfGroup = null;
      }
      if (tawInfGroupDAO != null) {
        tawInfGroupDAO = null;
      }
      if (form != null) {
        form = null;
      }
    }

    return actionMapping.findForward("success");
  }

  private void generalError(HttpServletRequest request, Exception e) {
    ActionErrors aes = new ActionErrors();
    aes.add(aes.GLOBAL_ERROR, new ActionError("error.general", e.getMessage()));
    saveErrors(request, aes);
    e.printStackTrace();
  }
}
