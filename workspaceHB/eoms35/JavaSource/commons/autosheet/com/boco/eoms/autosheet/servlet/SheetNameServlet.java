package com.boco.eoms.autosheet.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.autosheet.util.*;
import com.boco.eoms.autosheet.servletoper.*;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
  
public class SheetNameServlet
    extends HttpServlet {
  //changed by songxuesong
//  static final private String CONTENT_TYPE = "text/html; charset=GBK";
  static final private String CONTENT_TYPE = "text/html; charset=GB2312";
  private SheetNameOperator sno = new SheetNameOperator();
  int sheetID;
  private String flag="new";
  private LogMan log = LogMan.getInstance();
  private String UserId =null;
  private SheetUtil sheetUtil = new SheetUtil();
  //private TawValidatePrivBO tawValidatePrivBO = null;
  private int DeptId=0;
  //Initialize global variables
  public void init() throws ServletException {
  }

  //Process the HTTP Get request
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
  }

  //Process the HTTP Put request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
    request.setCharacterEncoding("UTF-8");

    try {
//    	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");	
      /*SaveSessionBeanForm saveSessionBeanForm =
          (SaveSessionBeanForm) request.getSession().getAttribute(
          "SaveSessionBeanForm");*/
      if (saveSessionBeanForm == null) {
        response.sendRedirect(request.getContextPath() + "/timeout.jsp");
        return;
      }
      UserId = StaticMethod.null2String(saveSessionBeanForm.getUserid());
      DeptId = Integer.parseInt(saveSessionBeanForm.getDeptid());
     // tawValidatePrivBO = new TawValidatePrivBO();

    }
    catch (Exception ee) {
      ee.printStackTrace();
    }

    String sh_cname = request.getParameter("sh_cname");
    PrintWriter out = response.getWriter();

   out.println("<html>");
   out.println("<head><title>SheetNameServlet</title></head>");
   out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() +
               "/autosheet/style.css\">");
   out.println("<body>");
   flag=StaticMethod.null2String(request.getParameter("flag"));

   String url=(String)request.getSession().getAttribute("returnURL");
     if(flag.equals("new")){
       if (!UserId.equals(StaticVariable.ADMIN)) {
    try{
      /*if (tawValidatePrivBO.validatePriv(UserId,
                                         sheetUtil.OPE_AUTOSHEET_ADD) == false) {
        response.sendRedirect(request.getContextPath() + "/nopriv.jsp");
        return; //û��Ȩ�ޣ�ת����Ȩ��ҳ��
      }*/
    }catch(Exception e){
      e.printStackTrace();
    }
  }

       try {
         sno.initParameter(request);
         sheetID = sno.dataInsert(DeptId);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
       if (sheetID <= 0) {
         out.println(
             "<p>�������ʧ��:�˻���һ������<a href=\"#\" onClick=\"history.back()\">�� ��</a></p>");

       }
       else {

         response.sendRedirect(request.getContextPath() +
                               "/autosheet/sheetattr.jsp?sheetID=" + sheetID);
         return;
       }
     }
     else if(flag.equals("edit")){
       if (!UserId.equals(StaticVariable.ADMIN)) {
   try{
     /*if (tawValidatePrivBO.validatePriv(UserId,
                                        sheetUtil.OPE_AUTOSHEET_EDIT) == false) {
       response.sendRedirect(request.getContextPath() + "/nopriv.jsp");
       return; //û��Ȩ�ޣ�ת����Ȩ��ҳ��
     }*/
   }catch(Exception e){
     e.printStackTrace();
   }
 }

      try{
      sno.initParameter(request);
      if(sno.doUpdate(request,UserId,DeptId)){
        out.println("�޸ı?�����Գɹ�!");
        out.println("<p align=\"center\"><a href=\"" + url + "\">�� ��</a></p>");
      }else{
       out.println("�޸ı?������ʧ��!");
       out.println("<p>��������޸�ʧ��:�˻���һ������<a href=\"#\" onclick=\"history.back()\">�� ��</a></p>");
      }
    }catch(Exception e){
      log.writeLog("SheetNameUpdateServlet",e);

    }

    }else
      this.doGet(request,response);
    out.println("</body></html>");

  }

  //Clean up resources
  public void destroy() {
  }
}