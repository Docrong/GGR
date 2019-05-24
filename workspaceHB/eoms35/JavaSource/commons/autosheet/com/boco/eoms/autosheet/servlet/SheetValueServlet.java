package com.boco.eoms.autosheet.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.autosheet.util.*;
import com.boco.eoms.autosheet.servletoper.*;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;

public class SheetValueServlet
    extends HttpServlet {
  //changed by songxuesong
//  static final private String CONTENT_TYPE = "text/html; charset=GBK";
  static final private String CONTENT_TYPE = "text/html; charset=GB2312";
  private String UserId = null;
  private LogMan log = LogMan.getInstance();
  //Initialize global variables
  public void init() throws ServletException {
  }

  private SheetValueOperator shValueOper = new SheetValueOperator();
  private int direction;
  private SheetUtil sheetUtil = new SheetUtil();
  //private TawValidatePrivBO tawValidatePrivBO = null;
  //Process the HTTP Get request
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
       if (StaticMethod.getDbType().equals(StaticVariable.ORACLE))
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
      //tawValidatePrivBO = new TawValidatePrivBO();
    }
    catch (Exception ee) {
      ee.printStackTrace();
    }

    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>SheetValueServlet</title></head>");
    out.println("<body>");

    String sheetID = StaticMethod.null2String(request.getParameter("sheet_id"));
    boolean flag = shValueOper.doDelete(request, response);
    if (flag) {
      response.sendRedirect(request.getContextPath() +
                            "/autosheet/editvalue.jsp?sheet_id=" + sheetID);
      return;
    }
    else {
      out.println("����ɾ����");
    }
    out.println("</body></html>");

  }

  //Process the HTTP Post request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
    if (StaticMethod.getDbType().equals(StaticVariable.ORACLE))
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
      //tawValidatePrivBO = new TawValidatePrivBO();
    }
    catch (Exception ee) {
      ee.printStackTrace();
    }

    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>SheetValueServlet</title></head>");
    out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() +
                "/autosheet/style.css\">");
    out.println("<body>");
    String flag = StaticMethod.null2String(request.getParameter("flag"), "new");
    String reFactor = StaticMethod.null2String(request.getParameter("reFactor"),
                                               "");
    String preview = StaticMethod.null2String(request.getParameter("preview"),
                                              "");

    String url = (String) request.getSession().getAttribute("returnURL");
    String sheetID = StaticMethod.null2String(request.getParameter("sheetID"));
    if (flag.equals("new")) {
      if (!UserId.equals(StaticVariable.ADMIN)) {
        try {
          /*if (tawValidatePrivBO.validatePriv(UserId,
                                             sheetUtil.OPE_AUTOSHEET_EDIT) == false) {
            response.sendRedirect(request.getContextPath() + "/nopriv.jsp");
            return; //û��Ȩ�ޣ�ת����Ȩ��ҳ��
          }*/
        }
        catch (Exception e) {
          e.printStackTrace();
        }
}

      direction = StaticMethod.getIntValue(request.getParameter("direction"), 0);
      try {
        shValueOper.doInit(request);
        if (!preview.equals("preview"))
          shValueOper.doInsert();
        if (direction == 0) {
          response.sendRedirect(request.getContextPath() +
                                "/autosheet/sheetattr.jsp?sheetID=" + sheetID +
                                "&reFactor=" + reFactor);
          return;
        }
        if (direction == 1) {
          response.sendRedirect(request.getContextPath() +
                                "/autosheet/sheetvalue.jsp?sheetID=" + sheetID +
                                "&reFactor=" + reFactor);
          return;
        }
        if (direction == 2) {
          response.sendRedirect(request.getContextPath() +
                                "/laststepservlet?sheetID=" + sheetID +
                                "&flag=new&reFactor=" + reFactor);
          return;
        }
        if (direction == 3) {
          response.sendRedirect(request.getContextPath() +
                                "/htmlservlet?action=preview&sheet_id=" +
                                sheetID);
          return;
        }

      }
      catch (Exception e) {
        log.writeLog("SheetValueServlet", e);
      }
      out.println(
          "<p>�������ʧ��:�˻���һ������<a href=\"#\" onclick=\"history.back()\">�� ��</a></p>");
      out.println("</body></html>");
    }
    else if (flag.equals("update")) {
      if (!UserId.equals(StaticVariable.ADMIN)) {
     try {
       /*if (tawValidatePrivBO.validatePriv(UserId,
                                          sheetUtil.OPE_AUTOSHEET_EDIT) == false) {
         response.sendRedirect(request.getContextPath() + "/nopriv.jsp");
         return; //û��Ȩ�ޣ�ת����Ȩ��ҳ��
       }*/
     }
     catch (Exception e) {
       e.printStackTrace();
     }
}

      try {
        shValueOper.doInit(request);
        if (shValueOper.doUpdate(request)) {
          out.println("�޸������ɹ�!");
          out.println("<p align=\"center\"><a href=\"" + url + "\">�� ��</a></p>");
        }
        else {
          out.println("�޸������ʧ��!");
          out.println(
              "<p>�������ʧ��:�˻���һ������<a href=\"#\" onclick=\"history.back()\">�� ��</a></p>");
        }
      }
      catch (Exception e) {
        log.writeLog("SheetValueServlet", e);
      }

    }
    else if (flag.equals("delete")) {
      if (!UserId.equals(StaticVariable.ADMIN)) {
     try {
       /*if (tawValidatePrivBO.validatePriv(UserId,
                                          sheetUtil.OPE_AUTOSHEET_EDIT) == false) {
         response.sendRedirect(request.getContextPath() + "/nopriv.jsp");
         return; //û��Ȩ�ޣ�ת����Ȩ��ҳ��
       }*/
     }
     catch (Exception e) {
       e.printStackTrace();
     }
}

      this.doGet(request, response);
    }
  }

  //Clean up resources
  public void destroy() {
  }
}