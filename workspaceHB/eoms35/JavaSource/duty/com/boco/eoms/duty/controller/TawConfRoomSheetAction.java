package com.boco.eoms.duty.controller;

import org.apache.struts.action.*;
import javax.servlet.http.*;

import java.util.ResourceBundle;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.util.Pager;
import com.boco.eoms.common.util.StaticVariable;
//import com.boco.eoms.common.util.StaticMethod;

import com.boco.eoms.duty.controller.TawConfRoomSheetForm;
//import com.boco.eoms.common.controller.SaveSessionBeanForm;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
//import com.boco.eoms.jbzl.dao.TawApparatusroomDAO;
import com.boco.eoms.duty.dao.TawAutoSheetDAO;
import com.boco.eoms.duty.dao.TawConfRoomSheetDAO;
import com.boco.eoms.duty.model.TawConfRoomSheet;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;


import java.util.Vector;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts.util.LabelValueBean;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
//import com.boco.eoms.jbzl.model.TawApparatusroom;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.base.util.StaticMethod;

public class TawConfRoomSheetAction extends Action {
  //数据连接
 private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
 private static int PAGE_LENGTH = 10;

 static{
   ResourceBundle prop = ResourceBundle.getBundle("resources.application_zh_CN");
   try {
     PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
   }
   catch (Exception e) {

   }
 }

 public ActionForward perform(ActionMapping actionMapping,
                              ActionForm actionForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) {
   /**@todo: complete the business logic here, this is just a skeleton.*/
   ActionForward myForward = new ActionForward();
   String myAction = actionMapping.getParameter();

   if (isCancelled(httpServletRequest)) {
     return actionMapping.findForward("cancel");
   }
   if ("".equalsIgnoreCase(myAction)) {
     return actionMapping.findForward("failure");
   }

   if ("CONFIGRS".equalsIgnoreCase(myAction)) {
     myForward = performConfigRS(actionMapping, actionForm, httpServletRequest,
                                 httpServletResponse);
   }
   else if ("CONFIGRSDONE".equalsIgnoreCase(myAction)) {
     myForward = performConfigRSDone(actionMapping, actionForm,
                                     httpServletRequest, httpServletResponse);
   } else if ("CONFIGRSLIST".equalsIgnoreCase(myAction)) {
     myForward = performConfigRSList(actionMapping, actionForm,
                                     httpServletRequest, httpServletResponse);
   }
   else if ("UPDATE".equalsIgnoreCase(myAction)) {
     myForward = performUpdate(actionMapping, actionForm,
                               httpServletRequest, httpServletResponse);
   }
   else if ("DEL".equalsIgnoreCase(myAction)) {
     myForward = performDel(actionMapping, actionForm,
                            httpServletRequest, httpServletResponse);
   }
   else if ("DELDONE".equalsIgnoreCase(myAction)) {
     myForward = performDelDone(actionMapping, actionForm,
                                httpServletRequest, httpServletResponse);
   }
   else if ("UPDATEDONE".equalsIgnoreCase(myAction)) {
     myForward = performUpdateDone(actionMapping, actionForm,
                                   httpServletRequest, httpServletResponse);
   }
   return myForward;
  }

   public ActionForward performConfigRS(ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse
                                        httpServletResponse) {

     TawConfRoomSheetForm tawConfRoomSheetForm = (TawConfRoomSheetForm) actionForm;
     Vector SelectRoom = null;
     Vector SelectRoomName = null;
     //edit by wangheqi
     TawSystemAssignBo privBO = null;
     //TawValidatePrivBO tawValidatePrivBO = null;
     //edit by wangheqi
     TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
     TawSystemCptroom tawApparatusroom = null;
     
     //TawApparatusroomDAO tawApparatusroomDAO = null;
     TawRmAssignworkBO tawRmAssignworkBO=null;
     //TawApparatusroom tawApparatusroom = null;
     String strSelectRoomName = null;

     try{
         //判断超时
    	 //edit by wangheqi 2.7 to 3.5
    	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
         httpServletRequest.getSession().getAttribute("sessionform");
         /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
             httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
         if (saveSessionBeanForm == null){
           return actionMapping.findForward("timeout");
         }

         //判断权限
         String sdomIds = ""; //当前用户具有增加岗位权限的部门Ids
         String sessionUserId = saveSessionBeanForm.getUserid();
         //String sessionUserId = saveSessionBeanForm.getWrf_UserID();
         /*
         if (!sessionUserId.equalsIgnoreCase(StaticVariable.ADMIN)) {
           TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
           Vector domIds = new Vector();
           domIds = tawVPBO.validatePriv(sessionUserId, actionMapping.getPath()); // 如果是系统管理人员则返回-10
           if (domIds.size() <= 0) {
             return actionMapping.findForward("nopriv");
           }
           else {
             for (int i = 0; i < domIds.size(); i++) {
               sdomIds += domIds.get(i).toString() + ",";
             }
             sdomIds = sdomIds.substring(0, (sdomIds.length() - 1));
           }

         }
         */
         SelectRoom = new Vector();
         SelectRoomName = new Vector();
         if(saveSessionBeanForm.getUserid().equals(StaticVariable.ADMIN)){
           tawRmAssignworkBO = new TawRmAssignworkBO(ds);
           SelectRoom = tawRmAssignworkBO.getRoomSelect();
         }
         else{
            privBO = TawSystemAssignBo.getInstance();
           //tawValidatePrivBO = new TawValidatePrivBO(ds);
            SelectRoom = StaticMethod.list2vector(privBO.getPermissions(saveSessionBeanForm.getUserid(),com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));
           //SelectRoom = tawValidatePrivBO.validatePriv(saveSessionBeanForm.getUseriD(),actionMapping.getPath());
         }
         if (SelectRoom.size() > 0) {
        	 
           //tawApparatusroomDAO = new TawApparatusroomDAO(ds);
           tawApparatusroom = null;
           strSelectRoomName = "";
           Vector removeEle=new Vector();
           for (int i = 0; i < SelectRoom.size(); i++) {
        	 tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(
                       String.valueOf(SelectRoom.elementAt(i))),0);  
             //tawApparatusroom = tawApparatusroomDAO.retrieve(Integer.parseInt(
             //    String.valueOf(SelectRoom.elementAt(i))));
             if (tawApparatusroom != null) {
               strSelectRoomName = StaticMethod.null2String(tawApparatusroom.
                   getRoomname());
               SelectRoomName.add(strSelectRoomName);
             }
             else {
               removeEle.add(SelectRoom.elementAt(i));
             }
           }
           SelectRoom.removeAll(removeEle);
         }
         else{
             return actionMapping.findForward("nopriv");
         }
         httpServletRequest.setAttribute("SelectRoom",SelectRoom);
         httpServletRequest.setAttribute("SelectRoomName",SelectRoomName);

        //机房和附加表的对应
        TawAutoSheetDAO tawAutoSheetDAO = new TawAutoSheetDAO(ds);

        Vector roomSelect = new Vector();
        //List roomSelect = new ArrayList();
        Vector sheetSelect = new Vector();
        //roomSelect = tawApparatusRoomDAO.getRoomNameSelectV(sdomIds,0);

        sheetSelect = tawAutoSheetDAO.getAutoSheetSelect(11);   //值班的module_id=11;
        //tawConfRoomSheetForm.setCollRoomSelect(roomSelect);
        tawConfRoomSheetForm.setCollSheetSelect(sheetSelect);

        //是否故障表
        Vector collIsNotFault = new Vector();
        collIsNotFault.add(new LabelValueBean("- 选择 -",""));
        collIsNotFault.add(new LabelValueBean("是","是"));
        collIsNotFault.add(new LabelValueBean("不是","不是"));
        tawConfRoomSheetForm.setCollIsNotFault(collIsNotFault);


     }catch(Exception e){
       e.printStackTrace();
     }
      return actionMapping.findForward("success");
   }

   public ActionForward performConfigRSDone(ActionMapping actionMapping,
                                            ActionForm actionForm,
                                            HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse){

     TawConfRoomSheetForm form = (TawConfRoomSheetForm) actionForm;
     try{
        //edit by wangheqi 2.7 to 3.5
    	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
         httpServletRequest.getSession().getAttribute("sessionform");
         /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
             httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
        if (saveSessionBeanForm == null){
          return actionMapping.findForward("timeout");
        }

       TawConfRoomSheetDAO tawConfRoomSheetDAO = new TawConfRoomSheetDAO(ds);
       TawConfRoomSheet tawConfRoomSheet = new TawConfRoomSheet();
       org.apache.commons.beanutils.BeanUtils.populate(
          tawConfRoomSheet,org.apache.commons.beanutils.BeanUtils.describe(form));
       if(tawConfRoomSheetDAO.verifyExist(form.getRoomId(),form.getSheetId())){
          return actionMapping.findForward("configdup");
       }else{
         //insert into
        // form.setDeptId(saveSessionBeanForm.getWrf_DeptID());
         tawConfRoomSheetDAO.insert(tawConfRoomSheet);
       }

     }catch(Exception e){
       e.printStackTrace();
     }
     return actionMapping.findForward("success");
   }

  public ActionForward performConfigRSList(ActionMapping actionMapping,
                                           ActionForm actionForm,
                                           HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse){

    TawConfRoomSheetForm form = (TawConfRoomSheetForm) actionForm;
    try{
       TawConfRoomSheetDAO tawConfRoomSheetDAO = new TawConfRoomSheetDAO(ds);
       List searchList = new ArrayList();
       searchList = tawConfRoomSheetDAO.getConfigList();

       httpServletRequest.setAttribute("TAWCONFROOMSHEETLIST",searchList);
    }catch(Exception e){
      e.printStackTrace();
    }
    return actionMapping.findForward("success");
  }

  /* 根据权限配置*/
  public ActionForward performUpdate(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) {

    TawConfRoomSheetForm form = (TawConfRoomSheetForm) actionForm;
    //  edit by wangheqi 2.7 to 3.5
	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
    httpServletRequest.getSession().getAttribute("sessionform");
    /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
    if (saveSessionBeanForm == null){
         return actionMapping.findForward("timeout");
    }
   try{
     //判断权限
     String sessionUserId = saveSessionBeanForm.getUserid();
     int paraDeptId = StaticMethod.null2int(httpServletRequest.getParameter(
         "deptId"));
     int paraId = StaticMethod.null2int(httpServletRequest.getParameter("id"));

     String sdomIds = ""; //当前用户具有删除权限的部门

     if (!sessionUserId.equalsIgnoreCase(StaticVariable.ADMIN)) {
    	 //edit by wangheqi
    	 TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
       //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
       Vector domIds = new Vector();
       //edit by wangheqi
       domIds = StaticMethod.list2vector(privBO.getPermissions(sessionUserId,com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER, com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));
       //domIds = tawVPBO.validatePriv(sessionUserId, actionMapping.getPath());

       if (domIds.size() <= 0) {
         return actionMapping.findForward("nopriv");
       }

       boolean hasPriv = false;
       for (int i = 0; i < domIds.size(); i++) {
         if (Integer.parseInt(domIds.get(i).toString()) == paraDeptId) {
           hasPriv = true;
           break;
         }
       }
       if (!hasPriv) {
         return actionMapping.findForward("nopriv");
       }

       TawConfRoomSheetDAO tawConfRoomSheetDAO = new TawConfRoomSheetDAO(ds);
       TawConfRoomSheet tawConfRoomSheet = new TawConfRoomSheet();

       tawConfRoomSheet = tawConfRoomSheetDAO.retrieve(paraId);
       org.apache.commons.beanutils.BeanUtils.populate(form,
           org.apache.commons.beanutils.BeanUtils.describe(tawConfRoomSheet));

     }
   }catch(Exception e){
     e.printStackTrace();
   }
    return actionMapping.findForward("success");
  }


  public ActionForward performUpdateDone(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) {

     TawConfRoomSheetForm form = (TawConfRoomSheetForm) actionForm;
     //   edit by wangheqi 2.7 to 3.5
	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     httpServletRequest.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
	 if (saveSessionBeanForm == null){
          return actionMapping.findForward("timeout");
     }
    try{
      //判断权限
      String sessionUserId = saveSessionBeanForm.getUserid();
      String sdomIds = ""; //当前用户具有删除权限的部门

      if (!sessionUserId.equalsIgnoreCase(StaticVariable.ADMIN)) {
    	 //edit by wangheqi
    	 TawSystemAssignBo privBO = null;    	 
        //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
        Vector domIds = new Vector();
        domIds = StaticMethod.list2vector(privBO.getPermissions(sessionUserId,com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));
        //domIds = tawVPBO.validatePriv(sessionUserId,"/TawConfRoomSheet/update");

        if (domIds.size() <= 0) {
          return actionMapping.findForward("nopriv");
        }

        boolean hasPriv = false;
        for (int i = 0; i < domIds.size(); i++) {
          if (Integer.parseInt(domIds.get(i).toString()) == form.getDeptId()) {
            hasPriv = true;
            break;
          }
        }
        if (!hasPriv) {
          return actionMapping.findForward("nopriv");
        }


        TawConfRoomSheetDAO tawConfRoomSheetDAO = new TawConfRoomSheetDAO(ds);
        TawConfRoomSheet tawConfRoomSheet = new TawConfRoomSheet();

        org.apache.commons.beanutils.BeanUtils.populate(tawConfRoomSheet,
            org.apache.commons.beanutils.BeanUtils.describe(form));
        tawConfRoomSheetDAO.update(tawConfRoomSheet);
    }
    }catch(Exception e){
      e.printStackTrace();
    }
     return actionMapping.findForward("success");
   }

  public ActionForward performDel(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) {
       TawConfRoomSheetForm form = (TawConfRoomSheetForm) actionForm;
       //     edit by wangheqi 2.7 to 3.5
  	   TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
       httpServletRequest.getSession().getAttribute("sessionform");
       /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
           httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/
        if (saveSessionBeanForm == null){
             return actionMapping.findForward("timeout");
        }
       try{
         //判断权限
         String sessionUserId = saveSessionBeanForm.getUserid();
         int paraDeptId = StaticMethod.null2int(httpServletRequest.getParameter(
             "deptId"));
         int paraId = StaticMethod.null2int(httpServletRequest.getParameter("id"));

         String sdomIds = ""; //当前用户具有删除权限的部门

         if (!sessionUserId.equalsIgnoreCase(StaticVariable.ADMIN)) {
        	 //edit by wangheqi
        	 TawSystemAssignBo privBO = null;    	 
        	
           //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
           Vector domIds = new Vector();
           domIds = StaticMethod.list2vector(privBO.getPermissions(sessionUserId,com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER, com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));
           //domIds = tawVPBO.validatePriv(sessionUserId, actionMapping.getPath());

           if (domIds.size() <= 0) {
             return actionMapping.findForward("nopriv");
           }

           boolean hasPriv = false;
           for (int i = 0; i < domIds.size(); i++) {
             if (Integer.parseInt(domIds.get(i).toString()) == paraDeptId) {
               hasPriv = true;
               break;
             }
           }
           if (!hasPriv) {
             return actionMapping.findForward("nopriv");
           }
         }

         TawConfRoomSheetDAO tawConfRoomSheetDAO = new TawConfRoomSheetDAO(ds);
          TawConfRoomSheet tawConfRoomSheet = new TawConfRoomSheet();
          tawConfRoomSheet = tawConfRoomSheetDAO.retrieve(paraId);

          org.apache.commons.beanutils.BeanUtils.populate(form,
              org.apache.commons.beanutils.BeanUtils.describe(tawConfRoomSheet));

       }catch(Exception e){
         e.printStackTrace();
       }

    return actionMapping.findForward("success");
  }


  public ActionForward performDelDone(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) {
       TawConfRoomSheetForm form = (TawConfRoomSheetForm) actionForm;
     //     edit by wangheqi 2.7 to 3.5
  	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     httpServletRequest.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
         httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/        
  	 if (saveSessionBeanForm == null){
             return actionMapping.findForward("timeout");
        }
       try{
         //判断权限
         String sessionUserId = saveSessionBeanForm.getUserid();
         String sdomIds = ""; //当前用户具有删除权限的部门

         if (!sessionUserId.equalsIgnoreCase(StaticVariable.ADMIN)) {
           //        	edit by wangheqi
        	TawSystemAssignBo privBO = null;    	
           //TawValidatePrivBO tawVPBO = new TawValidatePrivBO(ds);
           Vector domIds = new Vector();
           domIds = StaticMethod.list2vector(privBO.getPermissions(sessionUserId,com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER, com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_CPTROOM));
           //domIds = tawVPBO.validatePriv(sessionUserId, "/TawConfRoomSheet/del");

           if (domIds.size() <= 0) {
             return actionMapping.findForward("nopriv");
           }

           boolean hasPriv = false;
           for (int i = 0; i < domIds.size(); i++) {
             if (Integer.parseInt(domIds.get(i).toString()) == form.getDeptId()) {
               hasPriv = true;
               break;
             }
           }
           if (!hasPriv) {
             return actionMapping.findForward("nopriv");
           }
         }

         TawConfRoomSheetDAO tawConfRoomSheetDAO = new TawConfRoomSheetDAO(ds);
         tawConfRoomSheetDAO.delete(form.getId());

       }catch(Exception e){
         e.printStackTrace();
       }

    return actionMapping.findForward("success");
  }

}
