package com.boco.eoms.testcard.controller;

import org.apache.struts.action.Action;
import java.util.ResourceBundle;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.testcard.bo.TawTestcardTestingBO;
import com.boco.eoms.testcard.dao.TawTestcardTestingDAO;
import java.util.List;
import com.boco.eoms.common.controller.SizeCacheManager;
import com.boco.eoms.testcard.model.TawTestcardTesting;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.util.Pager;

public class TawTestcardTestingAction extends Action {
  private com.boco.eoms.db.util.ConnectionPool ds=com.boco.eoms.db.util.ConnectionPool.getInstance();
  /**
  * @see ÿҳ��ʾ�ļ�¼����
  */
  private static int PAGE_LENGTH = 22;
  static ResourceBundle prop = null;
  private String user_id = "";
  static {
    prop = ResourceBundle.getBundle("resources.application_zh_CN");
    try {
      PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
    } catch (Exception e) {
    }
  }

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
  ActionForward myforward = null;
  String myaction = mapping.getParameter();

//session��ʱ����
try {
   	TawSystemSessionForm saveSessionBeanForm =
        (TawSystemSessionForm) request.getSession().getAttribute(
        "sessionform");  if (saveSessionBeanForm == null)
    return mapping.findForward("timeout");
  user_id = StaticMethod.null2String(saveSessionBeanForm.getUserid());
}
catch (Exception ee) {
  ee.printStackTrace();
}

//Ȩ����֤
try {
/*  TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds);
  if (!tawValidateBO.validPriv(user_id, mapping.getPath()))
    return mapping.findForward("nopriv");*/
}
catch (Exception ee) {
  ee.printStackTrace();
}
if ("".equalsIgnoreCase(myaction)) {
  myforward = mapping.findForward("failure");
}else if ("SEARCHTESTED".equalsIgnoreCase(myaction)) {
  myforward = performSearchTested(mapping, form, request, response);
}else {
  myforward = mapping.findForward("failure");
}
return myforward;
}

  /**
* @see ��ѯ���Կ�--����
*/
private ActionForward performSearchTested(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
  TawTestcardTestingForm form=(TawTestcardTestingForm)actionForm;
  int offset;
  int length = PAGE_LENGTH;
  String pageOffset = request.getParameter("pager.offset");

//  SaveSessionBeanForm saveSessionBeanForm=null;
//  saveSessionBeanForm = (SaveSessionBeanForm)request.getSession().getAttribute("sessionform");
//  if (saveSessionBeanForm == null) {
//    return mapping.findForward("timeout");
//  }
  TawTestcardTestingBO bo = new TawTestcardTestingBO(ds);
  String condition ="";
  if (request.getParameter("submit") != null) {
    condition = bo.getTestedCondition(form);
    request.getSession().removeAttribute("condition");
    request.getSession().setAttribute("condition", condition);
  }else{
    condition = (String) request.getSession().getAttribute("condition");
  }
  List tawTestcardTesting = null ;

  try{
    TawTestcardTestingDAO dao=new TawTestcardTestingDAO(ds);
    if (pageOffset == null || pageOffset.equals("")) {
      offset = 0;
    } else {
      offset = Integer.parseInt(pageOffset);
    }
      tawTestcardTesting=dao.getTestedResult(offset,length,condition);
    String[] objKeys = {"tawTestcardTesting", "list"};
    String objKey = CacheManager.createKey(objKeys);
    Integer size = (Integer)SizeCacheManager.getCache(objKey); 
    if((size == null)||(request.getParameter("submit") != null)) {
      size =new Integer( dao.getLengh(condition));
      SizeCacheManager.putCache(size, objKey, 0);
    }
    String url = request.getContextPath()+"/testcard"+mapping.getPath()+".do";
    String pagerHeader = Pager.generate(offset, size.intValue(), length, url);
    request.setAttribute("pagerHeader", pagerHeader);
    request.setAttribute("tawTestcardTesting", tawTestcardTesting);

  }catch(Exception e)
  {
    e.printStackTrace();
  }
  return mapping.findForward("success");
}


}
