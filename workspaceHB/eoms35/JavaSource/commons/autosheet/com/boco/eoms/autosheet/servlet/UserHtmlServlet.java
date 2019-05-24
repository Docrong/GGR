package com.boco.eoms.autosheet.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.db.util.*;
import com.boco.eoms.autosheet.util.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
//import com.boco.eoms.jbzl.bo.*;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;
import java.net.*;

public class UserHtmlServlet
    extends HttpServlet {
  //changed by songxuesong
//  static final private String CONTENT_TYPE = "text/html; charset=GBK";
 static final private String CONTENT_TYPE = "text/html; charset=GB2312";

  private int sheetID;
  private HTMLGenerator htmlG;
  private String UserId = "", Oper = "", DeptID = "", deptName = "",
      roomID = "", roomName = "";
  private Hashtable hash, hash1;
 // private HttpSession session;
  private PrintWriter out;
  private String linenum, sqlall;
  private ConnectionPool ds = ConnectionPool.getInstance();
  private SheetName shName = SheetName.getInstance();
  private String url = "";
  private SheetUtil sheetUtil = new SheetUtil();

  //Initialize global variables
  public void init() throws ServletException {
    try{
     shName.setSheetName();
    }
    catch(Exception e){
      e.printStackTrace();
    }

  }

  //Process the HTTP Post request
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {

    response.setContentType(CONTENT_TYPE);
     //if (StaticMethod.getDbType().equals(StaticVariable.ORACLE))
    request.setCharacterEncoding("GB2312");
    try {
//    	edit by wangheqi 2.7 to 3.5
	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
	      request.getSession().getAttribute("sessionform");	
      /*SaveSessionBeanForm saveSessionBeanForm =
          (SaveSessionBeanForm) request.getSession().getAttribute(
          "SaveSessionBeanForm");*/
      if (saveSessionBeanForm == null) {
        response.sendRedirect(request.getContextPath()+"/timeout.jsp");
        return;
      }
      UserId = StaticMethod.null2String(saveSessionBeanForm.getUserid());
      DeptID = StaticMethod.null2String(saveSessionBeanForm.getDeptid() +
                                        "");
      deptName = StaticMethod.null2String(saveSessionBeanForm.getDeptname());
      roomID = StaticMethod.null2String(saveSessionBeanForm.getRoomId() +
                                        "");
      roomName = StaticMethod.null2String(saveSessionBeanForm.getRoomname());
    }
    catch (Exception ee) {
      ee.printStackTrace();
    }
    out = response.getWriter();
    String sheetID = StaticMethod.null2String(request.getParameter("sheet_id"));
    String action = StaticMethod.null2String(request.getParameter("action"));
    String detail = StaticMethod.null2String(request.getParameter("detail"));
    String id = StaticMethod.null2String(request.getParameter("id"));
    String sql = StaticMethod.null2String(request.getParameter("querySQL"));

    htmlG = new HTMLGenerator(sheetID, out, request);
    if (action.equals("insert")) {
      htmlG.drawFrame();
    }else if (action.equals("preview")) {
      request.getSession().setAttribute("preview","preview");
      htmlG.drawPreViewFrame();
    }else{
    if (!id.equals("")) {
      if (action.equals("view")) {
        htmlG.drawViewFrame(action, id);
      }
      if (action.equals("edit")) {
        htmlG.drawEditFrame(action, id, sql);
      }
    }
    else
      htmlG.drawQueryForm(action,detail);

    }

  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    doGet(request, response);
  }

  //Clean up resources
  public void destroy() {
  }

}
