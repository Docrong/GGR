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

public class SheetAttrServlet
    extends HttpServlet {
  static final private String CONTENT_TYPE = "text/html; charset=GB2312";

  private int direction;
  private String flag = "new";
  private LogMan log = LogMan.getInstance();
  private SheetUtil sheetUtil = new SheetUtil();
  private String UserId = null;
  //private TawValidatePrivBO tawValidatePrivBO = null;
  //Initialize global variables
  public void init() throws ServletException {
  }

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
    out.println("<head><title>SheetAttrServlet</title></head>");
    out.println("<body>");
    SheetAttrOperator shAttrOper = new SheetAttrOperator();
    flag = StaticMethod.null2String(request.getParameter("flag"));
    String sheetID = StaticMethod.null2String(request.getParameter("sheet_id"));
    if (flag.equals("delete")) {
      if (!UserId.equals(StaticVariable.ADMIN)) {
        try {
          /*if (tawValidatePrivBO.validatePriv(UserId,
                                             sheetUtil.OPE_AUTOSHEET_DEL) == false) {
            response.sendRedirect(request.getContextPath() + "/nopriv.jsp");
            return; //û��Ȩ�ޣ�ת����Ȩ��ҳ��
          }*/
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }

      if (shAttrOper.doDelete(request, response)) {
        response.sendRedirect(request.getContextPath() +
                              "/autosheet/editattr.jsp?sheet_id=" + sheetID);
        return;
      }
      else
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
    out.println("<head><title>SheetAttrServlet</title></head>");
    out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() +
                "/autosheet/style.css\">");
    out.println("<body>");
    SheetAttrOperator shAttrOper = new SheetAttrOperator();
    flag = StaticMethod.null2String(request.getParameter("flag"));
    String reFactor = StaticMethod.null2String(request.getParameter("reFactor"),
                                               "");

    String url = (String) request.getSession().getAttribute("returnURL");
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
      String sheetID = StaticMethod.null2String(request.getParameter("sheetID"));
      try {
        shAttrOper.doInit(request);
        shAttrOper.doInsert();
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
                                "/htmlservlet?action=preview&sheet_id=" +
                                sheetID);
          return;
        }

      }
      catch (Exception e) {
        log.writeLog("SheetAttrServlet", e);
      }
      out.println(
          "<p>�������ʧ��:�˻���һ������<a href=\"#\" onclick=\"history.back()\">�� ��</a></p>");
    }
    else if (flag.equals("edit")) {
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
        shAttrOper.doInit(request);
        if (shAttrOper.doUpdate(request)) {
          out.println("�޸��������Ƴɹ�!");
          out.println("<p align=\"center\"><a href=\"" + url + "\">�� ��</a></p>");
        }
        else {
          out.println("�޸���������ʧ��!");
          out.println(
              "<p>��������޸�ʧ��:�˻���һ������<a href=\"#\" onclick=\"history.back()\">�� ��</a></p>");
        }
      }
      catch (Exception e) {
        log.writeLog("SheetAttrUpdateServlet", e);

      }

    }
    else
      this.doGet(request, response);
    out.println("</body></html>");
  }

  //Clean up resources
  public void destroy() {
  }
}