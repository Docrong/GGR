package com.boco.eoms.duty.controller;

import org.apache.struts.action.*;
import javax.servlet.http.*;

import java.util.ResourceBundle;   //defined in jdk1.4
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.commons.system.session.form.*;
import com.boco.eoms.duty.dao.TawConfRoomSheetDAO;
import com.boco.eoms.duty.dao.TawDutySheetDAO;
import com.boco.eoms.duty.model.TawDutySheet;
import com.boco.eoms.common.util.StaticMethod;


public class TawDutySheetAction extends Action {
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

  public ActionForward perform(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    /**@todo: complete the business logic here, this is just a skeleton.*/
    //throw new java.lang.UnsupportedOperationException("Method perform() not yet implemented.");
    ActionForward myForward = new ActionForward();
    String myAction = actionMapping.getParameter();

    if (isCancelled(httpServletRequest)) {
      return actionMapping.findForward("cancel");
    }

    if ("".equalsIgnoreCase(myAction)) {
      myForward = actionMapping.findForward("failure");
    }
    else if ("RECORD".equalsIgnoreCase(myAction)) {
      myForward = performRecord(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    }
    else if("DEALLINK".equalsIgnoreCase(myAction)){
      myForward = performDealLink(actionMapping, actionForm, httpServletRequest, httpServletResponse);
    }
    return myForward;
  }

  public ActionForward performRecord(ActionMapping actionMapping,
                                     ActionForm actionForm,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) {
    TawDutySheetForm form = (TawDutySheetForm)actionForm;
    try{
     //    	edit by wangheqi 2.7 to 3.5
   	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     httpServletRequest.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/      if (saveSessionBeanForm == null){
        actionMapping.findForward("timeout");
      }

      //得到本机房的所有的附加表列表
     List sheetList = new ArrayList();
     TawConfRoomSheetDAO tawConfRoomSheetDAO = new TawConfRoomSheetDAO(ds);
     sheetList = tawConfRoomSheetDAO.getSheetList(Integer.parseInt(saveSessionBeanForm.getRoomId()));
     httpServletRequest.setAttribute("SHEETLIST",sheetList);

     //得到本次值班记录所填写的附加表的信息
     int sheetId = 0;
     String sheetName = "";
     int workSerial = Integer.parseInt(saveSessionBeanForm.getWorkSerial());  //本班次的值班主记录ID
     for(int i=0;i<sheetList.size();i++){
       TawDutySheet tawDutySheet = (TawDutySheet) sheetList.get(i);
       sheetId = tawDutySheet.getSheetId();
       sheetName = tawDutySheet.getSheetName();

     }

    }catch(Exception e){
      e.printStackTrace();
    }
    return actionMapping.findForward("success");
  }

  /* 填写完成附加表后，处理和值班主记录之间的关联 */
  public ActionForward performDealLink(ActionMapping actionMapping,
                                    ActionForm actionForm,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) {
   TawDutySheetForm form = (TawDutySheetForm)actionForm;
   //返回sheet_id和 autosheet_id
   try{
     //	 edit by wangheqi 2.7 to 3.5
  	 TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
     httpServletRequest.getSession().getAttribute("sessionform");
     /*SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm)
        httpServletRequest.getSession().getAttribute("SaveSessionBeanForm");*/     if (saveSessionBeanForm == null){
       actionMapping.findForward("timeout");
     }

    String strAutoSheetId = (String)httpServletRequest.getSession().getAttribute("autosheet_id");
    if (strAutoSheetId == null || strAutoSheetId.equals("")){
      strAutoSheetId = "15";
    }

    //booc表中的记录id
    int autoSheetId = Integer.parseInt(strAutoSheetId);
    //值班主记录id
    int workSerial = Integer.parseInt(saveSessionBeanForm.getWorkSerial());
    int sheetId_qlh = StaticMethod.null2int(httpServletRequest.getParameter("sheet_id"));

    TawDutySheet tawDutySheet = new TawDutySheet();
    TawDutySheetDAO tawDutySheetDAO = new TawDutySheetDAO(ds);

    tawDutySheet.setBoco_Id(autoSheetId);
    tawDutySheet.setSheetId(sheetId_qlh);
    tawDutySheet.setWorkserial(Integer.parseInt(saveSessionBeanForm.getWorkSerial()));
    tawDutySheet.setOper_Id(saveSessionBeanForm.getUserid());
    tawDutySheet.setOper_Time(StaticMethod.getLocalString());

    tawDutySheetDAO.insert(tawDutySheet);

   }catch(Exception e){
     e.printStackTrace();
   }
   return actionMapping.findForward("success");
 }

}