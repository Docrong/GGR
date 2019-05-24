package com.boco.eoms.autosheet.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.db.util.*;
import com.boco.eoms.autosheet.util.*;
import java.net.*;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;

public class UserActionServlet
    extends HttpServlet {
  //changed by songxuesong
//  static final private String CONTENT_TYPE = "text/html; charset=GBK";
  static final private String CONTENT_TYPE = "text/html; charset=GB2312";

  private SheetName shName = SheetName.getInstance();


  private SheetValue shValue;
  private SheetAttribute shAttr;
  private int id, linenum;
  private String para1, action, sheetID,attach_id;
  private SQLGenerator sqlG;
  private RecordSet rs;
  private ConnStatement cstmt = new ConnStatement();
  private Hashtable hash = new Hashtable();
  private PropertyFile prop = PropertyFile.getInstance();
  private String[] valueArr, valueSingle;
  private String url = "", reaction = "",querysql = "";
  private String ip,UserId =null;
  private SheetUtil sheetUtil = new SheetUtil();

  public void init() throws ServletException {
    try{
         shName.setSheetName();
        }
        catch(Exception e){
          e.printStackTrace();
        }

  }

  //Process the HTTP Post request
  /****�����޸ĺ�¼���¼*********/
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
   //  if (StaticMethod.getDbType().equals(StaticVariable.ORACLE))
     request.setCharacterEncoding("GB2312");
    PrintWriter out = response.getWriter();
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

    }
    catch (Exception ee) {
      ee.printStackTrace();
    }


  //  session =request.getSession();
    ip = request.getRemoteAddr();

    String id = StaticMethod.null2String(request.getParameter("id"));

    out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() +
                "/autosheet/style.css\">");
    this.initParameter(request);
    String  title = getTitle(action, sheetID);
    out.println("<html>");
    out.println("<head><title>" + title + "</title></head>");
    out.println("<body>");
    if (doUpdateSql(action,request,response)) {
      out.println("<center><br><p>" + title + "����ɹ�.</p></center>");
    }
    else {
      out.println("<center><br><p>" + title + "����ʧ��.</p></center>");
    }
    if(action.equals("insert"))
      {url = request.getContextPath() + "/htmlservlet?sheet_id=" + sheetID +
        "&action=" + action;
      out.print("<p align=\"center\"><a href=\"" + url + "\">������д</a></p>");
      out.print("<p align=\"center\"><a href=\"" + url + "\">�� ��</a></p>");}

    else   { url = request.getContextPath() + "/htmlservlet?sheet_id=" + sheetID +
          "&action=" + action +"&id=" + id+"&querySQL=" + querysql;
    out.println("<p align=\"center\"><a href=\"" + url + "\">�� ��</a></p>");}

    out.println("</body></html>");
  }

  /**����ɾ���¼************************************************************/
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
    if (StaticMethod.getDbType().equals(StaticVariable.ORACLE))
      request.setCharacterEncoding("GB2312");
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

    }
    catch (Exception ee) {
      ee.printStackTrace();
    }

    String listid = StaticMethod.null2String(request.getParameter("listid"));
    String detail = StaticMethod.null2String(request.getParameter("detail"));
    PrintWriter out = response.getWriter();
    String title = "";
    try {
      this.initParameter(request);
      title = getTitle(action, sheetID);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() +
                "/autosheet/style.css\">");

    //out.println(title);
    if (action.equals("delete")) {
      String sql = "delete from " +
          StaticMethod.null2String(prop.getProperty("pretable"), "boco_") +
          sheetID +
          " where id=" + id;
      try {
        cstmt.setStatementSql(sql);
        cstmt.executeUpdate();
        cstmt.commit();
      }
      catch (Exception e) {}
      finally {
        cstmt.close();
      }
      if (!reaction.equals("")) {
        try {
          if (reaction.indexOf("?") == -1)
            reaction += "?id=" + id;
          else
            reaction += "&id="+id;
          response.sendRedirect(request.getContextPath() + reaction);
          return;
        }
        catch (IOException ioe) {
          ioe.printStackTrace();
        }

       }


      out.println("<center><p></p><p>�ɹ�" + title + ".</p>");
    }
    else {
      out.println("<p>" + title + "ʧ��.</p>");
    }

    if (detail.equals("full")) {
      url = request.getContextPath() + "/htmlformservlet?sheet_id=" + sheetID +
          "&action=list&detail=full&id=" + listid;
      response.sendRedirect(url);
      return;
    }

    else {
      url = request.getContextPath() + "/htmlformservlet?sheet_id=" + sheetID +
          "&action=" + action +
          "&querySQL=" + URLEncoder.encode(querysql);
      out.println("<p align=\"center\"><a href=\"" + url + "\">�� ��</a></p>");
    }

  }

  //Clean up resources
  public void destroy() {

  }

  /********��ʼ������************************************************************
   @param request httpservletrequest
       *****************************************************************************/
  private void initParameter(HttpServletRequest request) {
    hash.clear();
    Enumeration enu = request.getParameterNames();
    String paraname;
    while (enu.hasMoreElements()) {
      paraname = (String) enu.nextElement();
      if (paraname.equals("sheet_id")) {
        sheetID = StaticMethod.null2String(request.getParameter(paraname));
      }
      else if (paraname.equals("action")) {
        action = StaticMethod.null2String(request.getParameter(paraname));
      }
      else if (paraname.equals("attach_id")) {
        attach_id = StaticMethod.null2String(request.getParameter(paraname));
      }
      else if (paraname.equals("id")) {
        id = StaticMethod.getIntValue(request.getParameter(paraname));
      }
      else if (paraname.equals("querySQL")) {
        querysql = StaticMethod.null2String(request.getParameter(paraname));
      }
      else if (paraname.equals("reaction")) {
        reaction = StaticMethod.null2String(request.getParameter(paraname));
        reaction =StaticMethod.StringReplace(reaction,"~@$","&");
        reaction =StaticMethod.StringReplace(reaction,"^@$","%");
        reaction =StaticMethod.StringReplace(reaction,"*@$","#");

    }
      else {
       String paravalue[]= request.getParameterValues(paraname);
       String tempStr =",";
       int lengh =paravalue.length;
       if(lengh ==1)
            hash.put(paraname,StaticMethod.null2String(paravalue[0]));
       else if(lengh >1){
         for (int i = 0; i < lengh; i++) {
           tempStr += paravalue[i] + ",";
         }
         hash.put(paraname, StaticMethod.null2String(tempStr));
       }

    //  hash.put(paraname,
      //           StaticMethod.null2String(request.getParameter(paraname)));
      }
    }
    sqlG = new SQLGenerator(sheetID);
    shValue = new SheetValue(sheetID);
    shAttr = new SheetAttribute(sheetID);
  }

      /*****************************************************************************
         �����û�����
         @return boolean
       ****************************************************************************/
  public boolean doInsert() {
    boolean bool = false;
    String sql = sqlG.generateInsertSQL();
    try {
      cstmt.setStatementSql(sql);
      int i = 1;
      String value;
      while (shValue.next()) {
        value = (String) hash.get(shValue.getVName());
        if (shValue.getVstoretype().equals("text")) {
          char[] parachars = value.toCharArray();
          java.io.CharArrayReader reader = new CharArrayReader(parachars);
          cstmt.setCharacterStream(i, reader, parachars.length);
        }
        else {
          cstmt.setString(i,StaticMethod.dbNStrRev(value));
        }
        i++;
      }
      cstmt.executeUpdate();
      cstmt.commit();
      bool = true;
    }
    catch (Exception e) {

      try {
        cstmt.rollback();
      }
      catch (Exception ex) {}
      e.printStackTrace();
    }
    finally {
      cstmt.close();
    }
    return bool;
  }

  /**************************************************************************
   �����û��޸�
   @return boolean
   */
  public boolean doUpdateSql(String action,HttpServletRequest request,HttpServletResponse response) {
    boolean bool = false;
    String sql = "";

    String single_id=null;
    if (action.equals("insert")) {
      sql = sqlG.generateInsertSQL();
    }
    else if (action.equals("edit")) {
      sql = sqlG.generateUpdateSQL();
    }
    try {
      cstmt.setStatementSql(sql);
      int i = 1;
      String value;
      while (shValue.next()) {
        value = (String) hash.get(shValue.getVName());
        if (shValue.getVstoretype().equals("text")) {
          char[] parachars = value.toCharArray();
          java.io.CharArrayReader reader = new CharArrayReader(parachars);
          cstmt.setCharacterStream(i, reader, parachars.length);
          i++;
        }
        else if (shValue.getVstoretype().substring(0, 3).equals("dat")) {
          String year = (String) hash.get(shValue.getVName() + "Year1");
          String month = (String) hash.get(shValue.getVName() + "Month1");
          String day = (String) hash.get(shValue.getVName() + "Day1");
          value = year + "-" + month + "-" + day;
          if (shValue.getVstoretype().equals(
              "datetime")) {
            String hour = (String) hash.get(shValue.getVName() + "Hour1");
            String minute = (String) hash.get(shValue.getVName() + "Minute1");
            value += " " + hour + ":" + minute + ":00";
          }
          cstmt.setString(i, StaticMethod.dbNStrRev(value));
          i++;
        }
        else {
          cstmt.setString(i, StaticMethod.dbNStrRev(value));
          i++;
        }
      }
      if (id > 0 & action.equals("edit")) {
        cstmt.setInt(i, id);
      }
      else if (action.equals("insert")) {
        String currenttime = StaticMethod.getCurrentDateTime();
        single_id = ip + currenttime;
        cstmt.setString(i, single_id);
        cstmt.setString(i+1, attach_id);
        cstmt.setString(i+2, UserId);
        cstmt.setString(i+3, currenttime);
      }
      cstmt.executeUpdate();
      cstmt.commit();
      if (action.equals("insert")) {
        int autosheet_id =0;
       sql ="select id from boco_"+sheetID+" where single_id ='"+single_id+"'";
      // cstmt.setString(1, single_id);
       cstmt.setStatementSql(sql);
       cstmt.executeQuery();
       cstmt.commit();
       if(cstmt.next()){
         autosheet_id=cstmt.getInt(1);
       }
       request.getSession().setAttribute("autosheet_id",autosheet_id+"");
       if(!reaction.equals("")){
         try{

        response.sendRedirect(request.getContextPath() + reaction);
        return true;
      }
      catch(IOException ioe){
        ioe.printStackTrace();
      }

       }

    }
      bool = true;
    }
    catch (SQLException ex) {
      ex.printStackTrace();

    }
    finally {
      cstmt.close();
    }
    return bool;
  }

  /**��ɱ���*/
  private String getTitle(String action, String sheetID) {
    String subtitle = "";
    String title = "";
    title = shName.getSh_cname(sheetID);

    if (action.equals("insert")) {
      title = "����" + title + "��Ϣ";
    }
    else if (action.equals("delete")) {
      title = "ɾ��" + title + "��Ϣ";
    }
    else if (action.equals("update")) {
      title = "�޸�" + title + "��Ϣ";
    }
    return title;
  }
}
