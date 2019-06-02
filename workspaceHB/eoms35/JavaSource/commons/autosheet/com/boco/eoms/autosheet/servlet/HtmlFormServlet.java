package com.boco.eoms.autosheet.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
//import com.boco.eoms.jbzl.bo.*;
import com.boco.eoms.autosheet.util.*;
import com.boco.eoms.autosheet.servletoper.*;
import com.boco.eoms.db.util.*;
import java.net.*;
//import com.boco.eoms.jbzl.bo.TawValidatePrivBO;

/**
 * <p>Title: ֵ�����ǰ̨ҳ�����</p>
 * <p>Description: ֵ�����ǰ̨ҳ�����</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author ��ѩ��
 * @version 1.0
 */

public class HtmlFormServlet
    extends HttpServlet {
  //changed by songxuesong
//  static final private String CONTENT_TYPE = "text/html; charset=GBK";
  static final private String CONTENT_TYPE = "text/html; charset=GB2312";

  private HTMLGenerator gen;
  private String sheetID, action, detail,reaction;
  private SQLGenerator sqlG;
  private Hashtable hash = new Hashtable();
  private SheetValue shValue;
  private SheetAttribute shAttr;
  private SheetName shName;
  private RecordSet rs = new RecordSet();
  private PropertyFile prop = PropertyFile.getInstance();
  private CodeContentComsInfo content;
  private String querysql = "", sql;
  //add by wangheqi 2.7 to 3.5
  TawSystemUserRoleBo tawRmUserBO =TawSystemUserRoleBo.getInstance();
  //TawRmUserBO tawRmUserBO = new TawRmUserBO();
  private SheetUtil sheetUtil = new SheetUtil();
   private String UserId =null;
//private String url = "";
  //Initialize global variables
  public void init() throws ServletException {
  }

  //Process the HTTP Post request
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

      UserId = StaticMethod.null2String(saveSessionBeanForm.getUserid());

    }
    catch (Exception ee) {
      ee.printStackTrace();
    }

    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head><title>HtmlFormServlet</title></head>");
   // out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() +
    //            "/autosheet/style.css\">");
    out.println("<link rel=\"stylesheet\" href=\""+request.getContextPath()+"/css/table_style.css\" type=\"text/css\">");

    out.println("<body>");
    try {
      action = StaticMethod.null2String(request.getParameter("action"));
      sheetID = StaticMethod.null2String(request.getParameter("sheet_id"));
      querysql = StaticMethod.null2String(request.getParameter("querySQL"));
      reaction = StaticMethod.null2String(request.getParameter("reaction"));
      detail = StaticMethod.null2String(request.getParameter("detail"));
      shName = SheetName.getInstance();
      try {
        shName.setSheetName();
      }
      catch (Exception e) {
        e.printStackTrace();
      }

      shAttr = new SheetAttribute(sheetID);
      shValue = new SheetValue(sheetID);
      doQuery(response, request);
      //  url = request.getContextPath() + "/htmlservlet" +
      //      "?action=" + action + "&sheet_id=" + sheetID;

      out.println(
          "<table cellSpacing=0 cellPadding=0  border=0 width=\"80%\" align=\"center\" >");
      out.println("<tr>");
      out.println("<td align=\"right\">");
      out.println(
          "<input type=button name=button2 value=\"�� ��\" onclick=\"window.location.href='"+request.getContextPath()+"/htmlservlet?sheet_id=" + sheetID + "&action=insert&reaction=';\">");
      out.println(
          "<input type=button name=button1 value=\"�� ��\" onclick=\"window.close();\" >");
      out.println("</td>");
      out.println("</tr>");
      out.println("</table>");

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    doGet(request, response);
  }

  /**************************************************************************
   �����û���ѯ
   @return boolean
   */
  public void doQuery(HttpServletResponse response, HttpServletRequest request) throws
      IOException {

    PrintWriter out = response.getWriter();
    //   CodeContentComsInfo codeContent = new CodeContentComsInfo(
    //                       shValue.getTypeid());
    CodeContentComsInfo codeContent = null;
    String id = StaticMethod.null2String(request.getParameter("id"));

    //��ʾ��ɵĽ���
    try {
      if (querysql.equals("")) { //���sql����ͨ��doGet���ݹ�4�Ĳ���������ǲ�ѯ
        sql = produceQuerySql(request);
        querysql = URLEncoder.encode(sql);
      }
      else {
        sql = querysql; //URLDecoder.decode(querysql);
        querysql = URLEncoder.encode(querysql);
      }

      rs.execute(sql);
      //��ӡ��ͷ
      String temp = "";
      if (action.equals("view"))
        temp = "��ѯ";
      if (action.equals("edit"))
        temp = "�༭";
      if (action.equals("delete"))
        temp = "ɾ��";
      if (action.equals("list"))
        temp = "�б�";

      out.println("<center><p><b><font size=4>" + temp +
                  shName.getSh_cname(sheetID) + "</font></b></p></center>");
     //add  by  jintong
      System.out.print(rs.getCounts());
      out.println("<center><p><font size=2 color=red >����ѯ�� "+rs.getCounts()+ " ���¼</font></p></center>");
     //end
      out.println("<table cellSpacing=0 cellPadding=0  border=1 width=80% bordercolordark=\"#FFFFFF\" bordercolorlight=\"#66CCFF\" bgcolor=#F3F3F3 align=\"center\">");
      out.println("<tr>");
      out.println("<td>ִ����</td>");
      out.println("<td>ִ��ʱ��</td>");
      shValue.reset();
      while (shValue.next()) {
        if (shValue.getIsQuery().equals("1")) {
          out.println("<td>" + shAttr.getAttrName(shValue.getAttrID()) +
                      "</td>");
        }
      }
      if (action.equals("view"))
        out.println("<td>�鿴</td>");
      else if (action.equals("edit"))
        out.println("<td>�޸�</td>");
      else if (action.equals("delete"))
        out.println("<td>ɾ��</td>");
      else if (action.equals("list")) {
        if (detail.equals("view")) {
          out.println("<td>�鿴</td>");
        }
        else if (detail.equals("edit")) {
          out.println("<td>�޸�</td>");
        }
        else if (detail.equals("delete")) {
          out.println("<td>ɾ��</td>");
        }
        else if (detail.equals("full")) {
          out.println("<td>�鿴</td><td>�޸�</td><td>ɾ��</td>");


        }
      }

      out.println("</tr>");
      // ��ӡ��ݲ���

      //add by  jintong
      String user_id = "";
      try {
//      	edit by wangheqi 2.7 to 3.5
  	 	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
  	      request.getSession().getAttribute("sessionform");	
        /*SaveSessionBeanForm saveSessionBeanForm =
            (SaveSessionBeanForm) request.getSession().getAttribute(
            "SaveSessionBeanForm");*/

        user_id = StaticMethod.null2String(saveSessionBeanForm.getUserid());
      }
      catch (Exception ee) {
        ee.printStackTrace();
      }
      System.out.println(user_id);
      //end

      while (rs.next()) {
        out.println("<tr>");
        out.println("<td  width=90 >&nbsp;" + tawRmUserBO.getUsernameByUserid(rs.getString("user_id"))+
                        " </td>");
        out.println("<td  width=160 >&nbsp;" +rs.getString("systime") +
                                    " </td>");

        shValue.reset();
        while (shValue.next()) {
          if (shValue.getIsQuery().equals("1")) {
            if (shValue.getVstoretype().substring(0, 3).equals("dat")) {
              if (shValue.getVstoretype().equals("datetime")) {
                out.println("<td>&nbsp;" +
                            rs.getString(shValue.getVName()).substring(0, 19) +
                            "</td>");
              }
              else if (shValue.getVstoretype().substring(0, 4).equals("date")) {
                out.println("<td>&nbsp;" +
                            rs.getString(shValue.getVName()).substring(0, 10) +
                            "</td>");
              }
            }
            else if (shValue.getVstoretype().substring(0, 3).equals("var") ||
                     shValue.getVstoretype().substring(0, 3).equals("cha")) {
              switch (StaticMethod.getIntValue(shValue.getShowtype())) {
                case 1:

                case 3:

                case 6:
                  if (!shValue.getTypeid().equals("")) {
                    codeContent = new CodeContentComsInfo(shValue.getTypeid());

                    out.println("<td>&nbsp;" +
                                codeContent.getCodeName(StaticMethod.
                        fromBaseEncoding(rs.getString(shValue.getVName()))) +
                                "</td>");
                  }
                  break;
                case 2:

                case 4:
                  if (!shValue.getTypeid().equals("")) {
                    codeContent = new CodeContentComsInfo(shValue.getTypeid());
                    String tempstr = "<td>&nbsp;";
                    java.util.StringTokenizer st = new StringTokenizer(
                        StaticMethod.
                        fromBaseEncoding(rs.getString(shValue.getVName())), ",");

                    while (st.hasMoreElements()) {
                      tempstr +=
                          codeContent.getCodeName(st.nextElement().toString()) +
                          "<br>&nbsp;";
                      //   out.println(codeContent.getCodeName(tempstr));
                      //   out.println("<br>&nbsp;");
                    }
                    if (tempstr.length() > 16)
                      tempstr = tempstr.substring(0, tempstr.length() - 6);
                    out.println(tempstr);
                    out.println("</td>");
                  }

                  break;
                default:
                  out.println("<td>&nbsp;" + StaticMethod.
                              fromBaseEncoding(rs.getString(shValue.getVName())) +
                              "</td>");

              }

            }
            else {
              //  content = new CodeContentComsInfo(shValue.getTypeid());
              out.println("<td>&nbsp;" + rs.getString(shValue.getVName()) +
                          "</td>");
            }
          }
        }

        if (action.equals("view") || action.equals("list")) {
          if (detail.equals("view")) {


            out.println("<td><a href=htmlservlet?sheet_id=" + sheetID + "&id=" +
                       rs.getString("id") + "&action=view" + " target=\"_blank\"><img src=\""+request.getContextPath()
                       +"/images/bottom/an_xs.gif\" border=\"0\" ></a></td>");


          }
          else if (detail.equals("edit")) {
            out.println("<td><a href=htmlservlet?sheet_id=" + sheetID + "&id=" +
                        rs.getString("id") + "&action=edit" + " target=\"_blank\"><img src=\""+request.getContextPath()
                        +"/images/bottom/an_bj.gif\" border=\"0\" ></a></td>");
         //  String tem ="<img src="+request.getContextPath()+"/images/bottom/an_bj.gif\" border=\"0\" alt=\"�޸�\">";

          }
          else if (detail.equals("delete")) {
            out.println("<td><a href=useractionservlet?sheet_id=" + sheetID +
                        "&id=" + rs.getString("id") + "&action=delete"
                        + "&reaction="+reaction+"  ><img src=\""+request.getContextPath()
                       +"/images/bottom/an_sc.gif\" border=\"0\" ></a></td>");

          }
          else if (detail.equals("full")) {
            out.println("<td><a href=htmlservlet?sheet_id=" + sheetID + "&id=" +
                        rs.getString("id") + "&action=view" +
                        " target=\"_blank\"><img src=\"" +
                        request.getContextPath()
                        + "/images/bottom/an_xs.gif\" border=\"0\" ></a></td>");


            //add by jintong
            if (user_id.equals(rs.getString("user_id"))) {
              out.println("<td><a href=htmlservlet?sheet_id=" + sheetID +
                          "&id=" +
                          rs.getString("id") + "&action=edit" +
                          " target=\"_blank\"><img src=\"" +
                          request.getContextPath()
                          +
                          "/images/bottom/an_bj.gif\" border=\"0\" ></a></td>");
              out.println("<td><a href=useractionservlet?sheet_id=" + sheetID +
                          "&id=" + rs.getString("id") +
                          "&action=delete&detail=full&listid=" + id
                          + "&reaction=" + reaction + " ><img src=\"" +
                          request.getContextPath()
                          +"/images/bottom/an_sc.gif\" border=\"0\" ></a></td>");
            }
           else
           { out.println("<td>&nbsp;&nbsp;</td>");
             out.println("<td>&nbsp;&nbsp;</td>");
           }
         /////end
          }

        }
        if (detail.equals("")) {
          if (action.equals("view"))
            out.println("<td><a href=htmlservlet?sheet_id=" + sheetID + "&id=" +
                        rs.getString("id") + "&action=view" + " target=\"_blank\"><img src=\""+request.getContextPath()
                       +"/images/bottom/an_xs.gif\" border=\"0\" ></a></td>");

          if (action.equals("edit"))
            out.println("<td><a href=htmlservlet?sheet_id=" + sheetID + "&id=" +
                        rs.getString("id") + "&action=edit" + "target=\"_blank\"><img src=\""+request.getContextPath()
                       +"/images/bottom/an_bj.gif\" border=\"0\" ></a></td>");
          if (action.equals("delete"))
            out.println("<td><a href=useractionservlet?sheet_id=" + sheetID +
                        "&id=" + rs.getString("id") + "&action=delete" + "&reaction="+reaction+" ><img src=\""+request.getContextPath()
                       +"/images/bottom/an_sc.gif\" border=\"0\" ></a></td>");
        }

        out.println("</tr>");
      }
      out.println("</table>");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**************************************************************************
   �γɲ�ѯSQL���
   @return String
   */
  //changed by songxuesong 2004-06-17
  public String produceQuerySql(HttpServletRequest request) {
    String action = StaticMethod.null2String(request.getParameter("action"));
    String select = "select id,user_id,systime,";
    String from = " from " + prop.getProperty("pretable", "boco_") + sheetID;
    String where = " where ";
    String sql = "";
    if (action.equals("list")) {
      try {
        String value;
        shValue.reset();
        while (shValue.next()) {
          if (shValue.getIsQuery().equals("1")) {
            select += shValue.getVName() + ",";
          }
        }
        select = select.substring(0, select.length() - 1);
        //  select += "systime";


        ///add  by  jintong �γɲ�ѯ���
        while (shValue.next()) {
          if (!StaticMethod.null2String( (request.getParameter(shValue.getVName()))).
              equals("")) {
            //      System.out.print("12"+StaticMethod.null2String(request.getParameter(shValue.getVName()))+"12");
            where += shValue.getVName() + " like'%" +StaticMethod.
                null2String(request.getParameter(shValue.getVName())) + "%' or ";

            java.util.StringTokenizer st = new StringTokenizer(StaticMethod.
                null2String(request.getParameter(shValue.getVName())), ",");
            while (st.hasMoreElements()) {
              where += shValue.getVName() + " like'%" +
                  st.nextElement().toString() + "%' or ";
            }

          }
        }
        where = where.substring(0, where.length() - 3);
        //end

        where += " order by systime desc ";
        sql = select + from + where;
       //��ӡsql���
        System.out.print(sql);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    else {
      try {
        String value;
        shValue.reset();
        while (shValue.next()) {
          if (shValue.getIsQuery().equals("1")) {
            select += shValue.getVName() + ",";
            where += produceQueryWhere(request, shValue.getVstoretype());
          }
        }
        //  select += "systime";
        select = select.substring(0, select.length() - 1);
        where = where.substring(0, where.length() - 4);
        //   where += " order by 2 desc,systime desc";
        where += " order by systime desc";
        sql = select + from + where;
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    return sql;
  }

  /**************************************************************************
   �γɲ�ѯSQL����where �ִ�����
   @return String
   */
  public String produceQueryWhere(HttpServletRequest request, String storetype) {
    String querystr = "";
    String date1 = "", date2 = "";
    if (storetype.substring(0, 3).equals("dat")) {
      String year1 = request.getParameter(shValue.getVName() + "Year1");
      String month1 = request.getParameter(shValue.getVName() + "Month1");
      String day1 = request.getParameter(shValue.getVName() + "Day1");
      String year2 = request.getParameter(shValue.getVName() + "Year2");
      String month2 = request.getParameter(shValue.getVName() + "Month2");
      String day2 = request.getParameter(shValue.getVName() + "Day2");
      if (storetype.equals("datetime")) {
        String hour1 = request.getParameter(shValue.getVName() + "Hour1");
        String hour2 = request.getParameter(shValue.getVName() + "Hour2");
        String minute1 = request.getParameter(shValue.getVName() + "Minute1");
        String minute2 = request.getParameter(shValue.getVName() + "Minute2");
        date1 = year1 + "-" + month1 + "-" + day1 + " " + hour1 + ":" + minute1 +
            ":00";
        date2 = year2 + "-" + month2 + "-" + day2 + " " + hour2 + ":" + minute2 +
            ":59";
      }
      else {
        date1 = year1 + "-" + month1 + "-" + day1 + " 00:00:00";
        date2 = year2 + "-" + month2 + "-" + day2 + " 23:59:59";
      }
      querystr = shValue.getVName() + ">='" + date1 + " ' and "
          + shValue.getVName() + "<='" + date2 + "' and ";

    }
    else if (storetype.substring(0, 3).equals("var") ||
             shValue.getVstoretype().substring(0, 3).equals("cha")) {
      String value = (request.getParameter(shValue.getVName()));
      if (value != null) {
        value = value.trim();
        querystr = shValue.getVName() + " like '%" + value + "%'  and ";
      }
    }
    else if (storetype.substring(0, 3).equals("int") ||
             storetype.substring(0, 3).equals("flo")) {
      String value1 = request.getParameter(shValue.getVName() + "1").trim();
      String value2 = request.getParameter(shValue.getVName() + "2").trim();
      // ��ֵ���͵Ĳ�ͬ����
      //   if (!value1.equals(""))
      //     querystr = shValue.getVName() + ">= '" + value1 + "' and ";
      //   if (!value2.equals(""))
      //     querystr += shValue.getVName() + "<= '" + value2 + "' and ";
      if (!value1.equals(""))
        querystr = shValue.getVName() + ">= " + value1 + " and ";
      if (!value2.equals(""))
        querystr += shValue.getVName() + "<= " + value2 + " and ";

    }
    else {

      String value = (request.getParameter(shValue.getVName()));
      if (value == null)
        value = "";
      else
        value = value.trim();

      querystr = shValue.getVName() + "=" + value + " and ";
    }
    return querystr;
  }

  //Clean up resources
  public void destroy() {
  }

}
