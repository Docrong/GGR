package com.boco.eoms.sparepart.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
//import com.boco.eoms.log.bo.logBO;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.common.log.BocoLog;
import org.apache.struts.action.ActionForm;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import javax.servlet.http.HttpServletResponse;
import com.boco.eoms.common.util.StaticMethod;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
import com.boco.eoms.common.util.StaticMethod;
import java.util.*;
import com.boco.eoms.sparepart.bo.TawStatBO;
import com.boco.eoms.sparepart.dao.TawStatDAO;
import com.boco.eoms.sparepart.util.TawReturnDom;
import com.boco.eoms.sparepart.bo.TawQueryBO;

public class TawStatAction extends Action{
  public TawStatAction() {
  }
  Map actionFormMap;

  private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.
      ConnectionPool.getInstance();
  private String user_id = "", user_name = "";
  List STORAGE=new ArrayList();

  /**
   * @see ���������
   */
  private void generalError(HttpServletRequest request, Exception e) {
    ActionErrors aes = new ActionErrors();
    aes.add(aes.GLOBAL_ERROR, new ActionError("error.general", e.getMessage()));
    saveErrors(request, aes);
    e.printStackTrace();
  }

  /**
   * @see ϵͳ��־����
   */
  private void insertLog(ActionMapping mapping,
                         HttpServletRequest request,
                         String ret, String name) {
    try {
      /*
      logBO logbo = new logBO(ds);
      if (ret.equals("success")) {
        boolean bool = logbo.insertLogToDbPathNew(user_id, mapping.getPath(),
                                                  StaticVariable.OPER,
                                                  request.getRemoteAddr(), name);

      }
      else if (ret.equals("failure")) {
        boolean bool = logbo.insertLogToDbPathNew(user_id, mapping.getPath(),
                                                  StaticVariable.ERROR,
                                                  request.getRemoteAddr(), name);
      }
      */
    }
    catch (Exception e) {
      BocoLog.error(this, 0, "ϵͳ��־����(����taw_log)����", e);
    }
  }

  public ActionForward execute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {
    ActionForward myforward = null;
    String myaction = mapping.getParameter();
    System.out.println(myaction);

    //session��ʱ����
    try {
      request.setCharacterEncoding("GB2312");
      TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
          request.getSession().getAttribute("sessionform");
      if (saveSessionBeanForm == null) {
        return mapping.findForward("timeout");
      }
      user_id = StaticMethod.null2String(saveSessionBeanForm.getUserid());
      user_name = StaticMethod.null2String(saveSessionBeanForm.getUsername());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    //Ȩ����֤
    try {
      /*
      TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
      if (!tawValidateBO.validPriv(user_id, mapping.getPath())) {
        return mapping.findForward("nopriv");
      }
      //20040712
      Vector v = tawValidateBO.validatePriv(user_id, 7014, 17);
      TawReturnDom TawReturnDom = new TawReturnDom(ds);
      STORAGE = TawReturnDom.getStorage(v);

      if (user_id.equalsIgnoreCase("admin")) {
        TawQueryBO tawQueryBO = new TawQueryBO(ds);
        STORAGE = tawQueryBO.getStorage();
      } else if (STORAGE.size() == 0) {
        return mapping.findForward("nopriv");
      }
      */
      //20040712
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    //
    try {
      if (isCancelled(request)) {
        return mapping.findForward("failure");
      }
      else if ("STATALL".equalsIgnoreCase(myaction)) {
        myforward = performStat(mapping, form, request, response);
      }
      else if ("STATLIST".equalsIgnoreCase(myaction)) {
        myforward = performStatlist(mapping, form, request, response);
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return myforward;
  }

  public ActionForward performStat(ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws
      Exception {
    try {
    	String typeName="";
      int partType = StaticMethod.nullObject2int(request.getParameter("partType"));//������������,����0����1����2
      request.getSession().setAttribute("partType",new Integer(partType));//����һ��ʹ��
      TawStatBO ts = new TawStatBO();
      //List stat = ts.statall();
      List stat = ts.statstorage(STORAGE,partType);
      request.setAttribute("STATALLLIST", stat);
      if(partType==0){
    	  typeName="��ά����ͳ�ƽ��";
      }else if(partType==1){
    	  typeName="��Ʒ����ͳ�ƽ��";
      }else if(partType==2){
    	  typeName="�����Ǳ�ͳ�ƽ��";
      }
      return mapping.findForward("ok");
    }
    catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public ActionForward performStatlist(ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws
      Exception {
    try {
      TawStatDAO tsd = new TawStatDAO();
//      int state = StaticMethod.nullObject2int(actionFormMap.get("state"));
      String state = StaticMethod.null2String(request.getParameter("state"));//(11,11,)
      int partType = StaticMethod.nullObject2int(request.getSession().getAttribute("partType"));//������������,����0����1����2
//      int storageid = StaticMethod.nullObject2int(actionFormMap.get("storageid"));
      int storageid = StaticMethod.nullObject2int(request.getParameter("storageid"));
      List statlist = tsd.getSparepartList(state,storageid,partType);
      request.setAttribute("statlist", statlist);
      return mapping.findForward("ok");
    }
    catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
      }

}
