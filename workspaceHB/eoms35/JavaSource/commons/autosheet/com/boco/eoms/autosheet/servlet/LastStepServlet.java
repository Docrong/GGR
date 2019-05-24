package com.boco.eoms.autosheet.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.autosheet.util.*;
import com.boco.eoms.db.util.*;
import com.boco.eoms.autosheet.servletoper.SheetOperator;
//import com.boco.eoms.log.bo.logBO;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;

public class LastStepServlet
    extends HttpServlet {
  //changed by songxuesong
//  static final private String CONTENT_TYPE = "text/html; charset=GBK";
  static final private String CONTENT_TYPE = "text/html; charset=GB2312";
  private SheetValue shValue;
  private int domain_id, app_id;
  private SheetName shName;
  private PropertyFile prop = PropertyFile.getInstance();
  private ConnectionPool ds = ConnectionPool.getInstance();
  private ConnStatement cst = new ConnStatement();
  // private RecordSet rs = new RecordSet();
  //private logBO log;
  private HTMLGenerator gen;
  private SheetUtil sheetUtil = new SheetUtil();
  private String UserId =null;
  //private TawValidatePrivBO tawValidatePrivBO = null;
  //Initialize global variables
  public void init() throws ServletException {
  }

  //Process the HTTP Get request
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    if (StaticMethod.getDbType().equals(StaticVariable.ORACLE))
      request.setCharacterEncoding("UTF-8");
    response.setContentType(CONTENT_TYPE);
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
     //tawValidatePrivBO = new TawValidatePrivBO();

    }
    catch (Exception ee) {
      ee.printStackTrace();
    }

    String sheetID = request.getParameter("sheetID");
    String flag = "";
    String reFactor = "";
    PrintWriter out = response.getWriter();
    out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() +
                "/autosheet/style.css\">");
    flag = StaticMethod.null2String(request.getParameter("flag"), "");
    reFactor = StaticMethod.null2String(request.getParameter("reFactor"), "");
    System.out.println("flag=" + flag);
    /*ɾ��?*/
    if (flag.equals("delete")) {
      //�жϵ�ǰ�û�Ȩ��
     if (!UserId.equals(StaticVariable.ADMIN)) {
       try{
         /*if (tawValidatePrivBO.validatePriv(UserId,
                                            sheetUtil.OPE_AUTOSHEET_DEL) == false) {
           response.sendRedirect(request.getContextPath() + "/nopriv.jsp");
           return; //û��Ȩ�ޣ�ת����Ȩ��ҳ��
         }*/
       }catch(Exception e){
         e.printStackTrace();
       }
     }

      if (sheetOperator.dropSheet(sheetID)) {
        out.println("<p align=center>ɾ��?�ɹ�!<a href=" + request.getContextPath() +
                    "/autosheet/index.jsp>�� ��</a></p>");
      }
      else {
        out.println("<p align=center>ɾ��?ʧ��!<a href=" + request.getContextPath() +
                    "/autosheet/index.jsp>�� ��</a></p>");
      }
    }
    /*��b�?*/
    if (flag.equals("new")) {
      //�жϵ�ǰ�û�Ȩ��
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

      if (!reFactor.equals("")) {
        if (sheetOperator.reNewSheet(sheetID)) {
          out.println("<p align=center>��ӳɹ�!<a href=" + request.getContextPath() +
                      "/autosheet/index.jsp>�� ��</a></p>");
        }
        else {
          out.println("<p align=center>���ʧ��!<a href=" + request.getContextPath() +
                      "/autosheet/index.jsp>�� ��</a></p>");
        }

      }
      else {
        if (sheetOperator.createSheet(request)) {
          out.println("<p align=center>����ɹ�!<a href=" + request.getContextPath() +
                      "/autosheet/index.jsp>�� ��</a></p>");
        }
        else {
          out.println("<p align=center>����ʧ��!<a href=" + request.getContextPath() +
                      "/autosheet/index.jsp>�� ��</a></p>");
        }
      }
    }
    /*�ؽ��?*/
    if (flag.equals("renew")) {
      if (!UserId.equals(StaticVariable.ADMIN)) {
    try{
      /*if (tawValidatePrivBO.validatePriv(UserId,
                                         sheetUtil.OPE_AUTOSHEET_RENEW) == false) {
        response.sendRedirect(request.getContextPath() + "/nopriv.jsp");
        return; //û��Ȩ�ޣ�ת����Ȩ��ҳ��
      }*/
    }catch(Exception e){
      e.printStackTrace();
    }
  }

      if (!sheetOperator.reNewSheet(sheetID)) {
        out.println("<p align=center>ɾ��?ʧ��!<a href=" +
                    request.getContextPath() +
                    "/autosheet/index.jsp>�� ��</a></p>");
      }
      else {
        out.println("<p align=center>�ؽ��?�ɹ�!<a href=" + request.getContextPath() +
                    "/autosheet/index.jsp>�� ��</a></p>");
      }
    }
    /*�����?*/
    if (flag.equals("publish")) {
      if (sheetOperator.publishSheet(sheetID)) {
        out.println("<p align=center>�����?�ɹ�!<a href=" + request.getContextPath() +
                    "/autosheet/index.jsp>�� ��</a></p>");
      }
    }
  }

  //Process the HTTP Post request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    doGet(request, response);
  }

  public void destroy() {
  }

  private SheetOperator sheetOperator = new SheetOperator(ds);
}