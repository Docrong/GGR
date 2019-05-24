package com.boco.eoms.autosheet.util;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.jsp.JspWriter;
import java.io.PrintWriter;
import com.boco.eoms.db.util.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
//import com.boco.eoms.jbzl.bo.*;
import com.boco.eoms.autosheet.util.*;
import java.util.*;
import java.net.*;
import java.text.*;

import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;


/*****************************************************************
 ����ָ��sheetID�ı?��html����
 *****************************************************************/
public class HTMLGenerator {
  private SheetName shName = SheetName.getInstance();
  private SheetUtil sheetUtil =new SheetUtil();
  private SheetAttribute shAttr;
  private SheetValue shValue;
  private CodeContentComsInfo codeContent;
  private String sheetID, reaction;
  private int id;
  String user_id ="";
  private PrintWriter out;
  private HttpServletRequest request;
  private CheckContraint checkCon;

  private final DecimalFormat NUMBER_FORMAT = new DecimalFormat("00");
  TawSystemUserRoleBo tawRmUserBO = TawSystemUserRoleBo.getInstance();
  //TawRmUserBO tawRmUserBO = new TawRmUserBO();


  /**
   *@params sheetID sheetID
   *@params out out
   * @see��ݸ���sheetID��ʼ�������������
   */
  public HTMLGenerator(String sheetID, PrintWriter out,
                       HttpServletRequest request) {
    this.sheetID = sheetID;
    this.out = out;
    this.request = request;

    reaction = StaticMethod.null2String(request.getParameter("reaction"));
    shAttr = new SheetAttribute(sheetID);
    shValue = new SheetValue(sheetID);
    headCounts = shAttr.getHeadItemCounts() + shValue.getHeadItemCounts();
    bodyCounts = shAttr.getBodyItemCounts() + shValue.getBodyItemCounts();
    tailCounts = shAttr.getTailItemCounts() + shValue.getTailItemCounts();
    checkCon = new CheckContraint(sheetID, out, request);

    try {
//    	edit by wangheqi 2.7 to 3.5
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


    try {
      shName.setSheetName();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * @params keyid keyid
   * @params out out
   *@see ��ݸ���keyid��ʼ�������������
   */

  public static final int HEAD = 1;
  public static final int BODY = 0;
  public static final int TAIL = 2;
  private int headCounts, bodyCounts, tailCounts;
  /*******************************************************************
   ����һ���û�������
   *******************************************************************/
  public void drawFrame() {
    String title = shName.getSh_cname(sheetID);
    out.println("<%@ taglib uri='/WEB-INF/app.tld' prefix='eoms' %>");
    out.println("<html><title>" + title + "</title>");
   // out.println(" <link rel=\"stylesheet\" href=\"" + request.getContextPath() +
   //             "/autosheet/style.css\" type=\"text/css\">");
   out.println("<link rel=\"stylesheet\" href=\""+request.getContextPath()+"/css/table_style.css\" type=\"text/css\">");

    checkCon.checkConstraint();
    out.println("<body><p align=center><font  size=\"4\"><b>��д" + title +
                "</b></font></p>");
    out.println("<form id=form1 name=form1 action=\"" + request.getContextPath() + "/useractionservlet\" method=post   onsubmit=\"javascript: return checkInput();\">");

    drawPortion(HEAD, 1, this.headCounts, "insert", ""); //����ͷ

    drawPortion(BODY, this.headCounts + 1, this.headCounts + this.bodyCounts,
                "insert", ""); //������
    drawPortion(TAIL, this.headCounts + this.bodyCounts + 1,
                this.headCounts + this.bodyCounts + this.tailCounts, "insert",
                ""); //����β

    out.println("<table cellSpacing=0 cellPadding=0  border=0 width=\"80%\" align=\"center\" >");
    out.println("<tr>");
    out.println("<td align=right>");
    out.println("<input type=hidden name=sheet_id value=" + sheetID + ">");
    out.println("<input type=hidden name=\"reaction\" value=\"" + reaction +"\">");
    out.println("<input type=hidden name=\"action\" value=\"insert\">");
    out.println("<input type=submit name=submit value=\"确定\"><input type=reset name=reset value=\"重置\">"
           +"<input type=button name=button1 value=\"页面关闭\" onclick=\"window.close();\" >");
    out.println("</td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("</form></body></html>");
  }

  /*******************************************************************
  ����һ���û�Ԥ�1��
  *******************************************************************/

  public void drawPreViewFrame() {
   String title = shName.getSh_cname(sheetID);

   out.println("<html><title>" + title + "</title>");
  // out.println(" <link rel=\"stylesheet\" href=\"" + request.getContextPath() +
  //             "/autosheet/style.css\" type=\"text/css\">");
  out.println("<link rel=\"stylesheet\" href=\""+request.getContextPath()+"/css/table_style.css\" type=\"text/css\">");

   checkCon.checkConstraint();
   out.println("<body><p align=center><font  size=\"4\"><b>Ԥ��" + title +
               "</b></font></p>");
   out.println("<table cellSpacing=0 cellPadding=0  border=0 width=\"100%\" bgcolor=\"#FFFFFF\" align=\"left\">");
//    out.println("<form id=form1 name=form1 action=\"" + request.getContextPath() +"/useractionservlet\" method=\"post\">");
   out.println("<form id=form1 name=form1 action=\"" + request.getContextPath() + "/useractionservlet\" method=post   onsubmit=\"javascript: return checkInput();\">");

   out.println("<tr><td>");
   drawPortion(HEAD, 1, this.headCounts, "insert", ""); //����ͷ
   out.println("</td></td>");
   out.println("<tr><td>");
   drawPortion(BODY, this.headCounts + 1, this.headCounts + this.bodyCounts,
               "insert", ""); //������

   out.println("</td></tr>");
   out.println("<tr><td>");
   drawPortion(TAIL, this.headCounts + this.bodyCounts + 1,
               this.headCounts + this.bodyCounts + this.tailCounts, "insert",
               ""); //����β
   out.println("</td></tr>");
   out.println("<tr><td align=center>");
   out.println("<input type=hidden name=sheet_id value=" + sheetID + ">");
   out.println("<input type=hidden name=\"reaction\" value=\"" + reaction +
               "\">");
   out.println("<input type=hidden name=\"action\" value=\"insert\">");
   out.println("<input type=hidden name=\"preView\" value=\"preView\">");
   out.println("</td></tr>");
   out.println(
          "<p align=center><a href=\"#\" onclick=\"history.back()\">�� ��</a></p>");
   out.println("</table>");
   out.println("</form>");
 }


  /*********************************************************************************
   ͨ��sheetID,action��ɲ�ѯ����
   @param action ������
   *********************************************************************************/
  public void drawQueryForm(String action,String detail) {
    /**javascript���벿��,��֤����������������*/
    checkCon.checkConsQuery();
    String temp = "";
    if (action.equals("view")) {
      temp = "��ѯ";
    }
    if (action.equals("edit")) {
      temp = "�༭";
    }
    if (action.equals("delete")) {
      temp = "ɾ��";
    }
  //  out.println(" <link rel=\"stylesheet\" href=\"" + request.getContextPath() +
  //              "/autosheet/style.css\" type=\"text/css\">");
  out.println("<link rel=\"stylesheet\" href=\""+request.getContextPath()+"/css/table_style.css\" type=\"text/css\">");

    out.println("<center><font size=4><b>" + temp + " " + shName.getSh_cname(sheetID) +
                "</b></font></center>");
    out.println("<form id=form1 name=form1 action=\"" + request.getContextPath() +
        "/htmlformservlet\" method=post onsubmit=\"javascript: return checkInput();\">");
    out.println("<table cellSpacing=0 cellPadding=0  border=1 width=\"55%\" bordercolordark=\"#FFFFFF\" bordercolorlight=\"#66CCFF\" bgcolor=\"#F3F3F3\" align=\"center\">");
    while (shValue.next()) {
      if (shValue.getIsQuery().equals("1")) {
        out.println("<tr><td align=right nowrap >" +
                    shAttr.getAttrName(shValue.getAttrID()) +
                    "</td><td align=left>");
        drawQueryObject();
        out.println("</td></tr>");
      }
    }
    out.println("</table>");
    out.println("<table cellSpacing=0 cellPadding=0  border=0 width=\"55%\" align=\"center\" >");
    out.println("<tr>");
    out.println("<td align=\"right\">");
    out.println("<input type=hidden name=sheet_id value=" + sheetID + ">");
    out.println("<input type=hidden name=action value=" + action + ">");
    out.println("<input type=hidden name=detail value=" + detail + ">");

out.println("<input type=submit name=submit value=\"�� ѯ\">"+
          "<input type=button name=button2 value=\"�� ��\" onclick=\"window.location.href='"+request.getContextPath()+"/htmlservlet?sheet_id=" + sheetID + "&action=insert&reaction=';\">"
                +"<input type=button name=button1 value=\"�� ��\" onclick=\"window.close();\" >");
    out.println("</td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("</form>");
  }

  /******************************************************************
   ����һ���û��鿴���
   @param action ������Ϊ(view,edit)
   @param id �����
   *******************************************************************/
  public void drawViewFrame(String action, String id) {
    out.println(" <%@ taglib uri=\"/WEB-INF/app.tld\" prefix=\"eoms\" %>");
    String title = shName.getSh_cname(sheetID);

    String userName = tawRmUserBO.getUsernameByUserid(sheetUtil.getUT(sheetID,id)[0]);
    String systime = sheetUtil.getUT(sheetID,id)[1];
    out.println("<html><head><title>" + title + "</title>");
  //  out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() +
  //              "/autosheet/style.css\" type=\"text/css\">");
  out.println("<link rel=\"stylesheet\" href=\""+request.getContextPath()+"/css/table_style.css\" type=\"text/css\">");

    out.println("</head><body><p align=center><b><font  size=\"4\"><a href=\"javascript:window.print();\">" +
                title + "</a></font></b></p>");
    out.println("<table cellSpacing=0 cellPadding=0  border=0 width=\"80%\" align=\"center\">");
    out.println("<tr>");
    out.println("<td align=\"right\">");
    out.println("ִ���ˣ�"+userName+" ִ��ʱ�䣺"+systime);
    out.println("</td>");
    out.println("</tr>");
    out.println("</table>");

    out.println("<table cellSpacing=0 cellPadding=0  border=0 width=\"80%\" align=\"center\">");
    out.println("<tr>");

    out.println("</td>");
    out.println("</tr>");
    out.println("</table>");

    drawPortion(HEAD, 1, this.headCounts, action, id); //����ͷ
    drawPortion(BODY, this.headCounts + 1, this.headCounts + this.bodyCounts,
                action, id); //������
    drawPortion(TAIL, this.headCounts + this.bodyCounts + 1,
                this.headCounts + this.bodyCounts + this.tailCounts, action, id); //����β

    out.println("<table cellSpacing=0 cellPadding=0  border=0 width=\"80%\" align=\"center\">");
    out.println("<tr>");
    out.println("<td align=\"right\">");
    out.println("<input type=button name=button1 value=\"�� ��\" onclick=\"window.close();\" >");
    out.println("</td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("</body></html>");
  }

  /******************************************************************
   ����һ���û��༭���
   @param action ������Ϊ(edit)
   @param id �����
   *******************************************************************/
  public void drawEditFrame(String action, String id, String sql) {
    sql = URLEncoder.encode(sql);
    String url = request.getContextPath() + "/htmlformservlet?sheet_id=" +
        sheetID + "&action=edit&querySQL=" + sql;
    String title = shName.getSh_cname(sheetID);
    String userName = tawRmUserBO.getUsernameByUserid(sheetUtil.getUT(sheetID,id)[0]);
    String systime = sheetUtil.getUT(sheetID,id)[1];
    out.println("<html><head><title>�༭ " + title + "</title>");
  //  out.println(" <link rel=\"stylesheet\" href=\"" + request.getContextPath() +
  //              "/autosheet/style.css\" type=\"text/css\">");
    out.println("<link rel=\"stylesheet\" href=\""+request.getContextPath()+"/css/table_style.css\" type=\"text/css\">");
    out.println("</head><body><p align=center><b><font  size=\"4\"><a href=\"javascript:window.print();\">" +
                title + "</a></font></b></p>");
    out.println("<form id=form1 name=form1 action=\"" + request.getContextPath() +
                "/useractionservlet\" method=post >");
    out.println("<table cellSpacing=0 cellPadding=0  border=0 width=\"80%\" align=\"center\">");
    out.println("<tr>");
    out.println("<td align=\"right\">");
    out.println("ִ����Ա��"+userName+" ִ��ʱ�䣺"+systime);
    out.println("</td>");
    out.println("</tr>");
    out.println("</table>");


    drawPortion(HEAD, 1, this.headCounts, action, id); //����ͷ
    drawPortion(BODY, this.headCounts + 1, this.headCounts + this.bodyCounts,
                action, id); //������

    drawPortion(TAIL, this.headCounts + this.bodyCounts + 1,
                this.headCounts + this.bodyCounts + this.tailCounts, action, id); //����β
 //   out.println("</td></tr>");
  //   out.println("</table>");
    out.println("<table cellSpacing=0 cellPadding=0  border=0 width=\"80%\" align=\"center\" >");
    out.println("<tr>");
    out.println("<td align=\"right\"><input type=hidden name=sheet_id value=" +sheetID + ">");
    out.println("<input type=hidden name=id value=" + id + ">");
    out.println("<input type=hidden name=action value=" + action + ">");
    out.println("<input type=hidden name=querySQL value=" + sql + ">");
    out.println("<input type=submit name=submit value=\"ȷ ��\"><input type=reset name=reset value=\"�� ��\">"
       +"<input type=button name=button1 value=\"�� ��\" onclick=\"window.close();\" >");
    out.println("</td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("</form></body></html>");
  }

  /******************************************************************
    ����һ���û�������
    @param action ������Ϊ
    @param id �����
    @throws Exception
   *******************************************************************/
  public void drawtFrame(String action, String id, String sqlall) throws
      Exception {

    String title = shName.getSh_cname(sheetID);
    //String subtitle=shName.getSubtitle(keyID.getSubsheetID(keyid+""));

    out.println("<html><head><title>" + title + "</title>");
   // out.println(" <link rel=\"stylesheet\" href=\"" + request.getContextPath() +
  //              "/autosheet/style.css\" type=\"text/css\">");
    out.println("<link rel=\"stylesheet\" href=\""+request.getContextPath()+"/css/table_style.css\" type=\"text/css\">");

    //javascript���벿��,��֤����������������
    checkCon.checkConstraint();
    out.println("</head><body><p align=center><b><font  size=\"4\"><a href=\"javascript:window.print();\">" +
                title + "</a></font></b></p>");
    out.println("<form id=form1 name=form1 action=\"" + request.getContextPath() + "/useractionservlet\" method=post   onsubmit=\"javascript: return checkInput();\">");
    out.println("<table cellSpacing=0 cellPadding=0  border=0 width=\"100%\" bgcolor=#FFFFFF align=\"left\">");
    out.println("<tr><td>");
    drawPortion(HEAD, 1, this.headCounts, action, id); //����ͷ
    out.println("</td></td>");
    out.println("<tr><td>");
    drawPortion(BODY, this.headCounts + 1, this.headCounts + this.bodyCounts,
                action, id); //������

    out.println("</td></tr>");
    out.println("<tr><td>");
    drawPortion(TAIL, this.headCounts + this.bodyCounts + 1,
                this.headCounts + this.bodyCounts + this.tailCounts, action, id); //����β
    out.println("</td></tr>");
    if (!action.equals("show")) {
      out.println("<tr><td>");
      out.println(
          "<table cellSpacing=0 cellPadding=0  border=0 width=\"80%\" align=\"center\">");
      out.println("<tr><td align=right>");
      out.println("<input type=hidden name=sheet_id value=" + sheetID + ">");
      out.println("<input type=hidden name=id value=" + id + ">");
      out.println("<input type=hidden name=action value=" + action + ">");
      out.println("<input type=submit name=submit value=\"ȷ ��\"><input type=reset name=reset value=\"�� ��\">"
          +"<input type=button name=button1 value=\"�� ��\" onclick=\"window.close();\" >");
      out.println("</td></tr>");
      out.println("</table>");
      out.println("</td></tr>");
      out.println("<tr><td>");
      out.println("<center>ע:���Ǻ�Ϊ������!��Ҫ�����ֵ��.</center>");
      out.println("</td></tr>");
    }
    out.println("</table>");
    out.println("</form>");
  }

  /****
   *@param portion �?�Ĳ�λ BODYΪ����,HEADΪ��ͷ,TAILΪ��β
   *@param count ˳��ţ�ָʾӦ�ô�ӡ�ĸ�Ԫ��
   *@param action insert Ϊ���룬editΪ�༭��show Ϊ��ʾ
   *@param id ����ʾ��¼�����
   *@param initcount initcount
   */
  private void drawPortion(int portion, int initcount, int count, String action,
                           String id) {

    Hashtable hash = new Hashtable();
    String attach_id = "";

    //�?��һ��������������
    int Acolumns = 0;
    //�?��һ�����������
    int Vcolumns = 0;

    // int coo =shValue.getVColumns();
    if (!id.equals("")) {
      hash = this.getValue(id);
      attach_id = hash.get("attach_id").toString();
    }
    //edit by wangheqi 2.7 to 3.5
    String inserthtml = "<td><tr><tr><td nowrap  align=\"center\" class=\"clsthd\">����</td><td width=\"480\" colspan = 3>"
        //+ "<IFRAME ID=IFrame1  FRAMEBORDER=0 width='100%' height=80 "
        //+ "SCROLLING=NO SRC='" + request.getContextPath() +
        + "<eoms:attachment idList='' idField='" + attach_id + "' appCode='" + 1001 + "'/>"
        + "</td></tr></tr></td>";
    /*String inserthtml = "<td><tr><tr><td nowrap  align=\"center\" class=\"clsthd\">����</td><td width=\"480\" colspan = 3>"
        + "<IFRAME ID=IFrame1  FRAMEBORDER=0 width='100%' height=80 "
        + "SCROLLING=NO SRC='" + request.getContextPath() +
        "/attachment/do_upload.jsp?app=autosheet"
        + "&idfile=attach_id"+ "&filelist=" + attach_id
        +"'></IFRAME><input type='hidden' name='attach_id'>"
        + "</td></tr></tr></td>";    
    String viewhtml = "<td><tr><tr><td nowrap  align=\"center\" class=\"clsthd\">����</td><td width=\"480\" colspan = 3>"
        + "<IFRAME ID=IFrame1  FRAMEBORDER=0 width='100%' height=100 "
        + "SCROLLING=NO SRC='" + request.getContextPath() +
        "/attachment/do_view.jsp?app=autosheet"
        + "&idfile=" + "filelist" + "&filelist=" + attach_id + "'></IFRAME>"
        + "</td></tr></tr></td>";*/
    String viewhtml = "<td><tr><tr><td nowrap  align=\"center\" class=\"clsthd\">����</td><td width=\"480\" colspan = 3>"
        + "<eoms:attachment name='BASEWORKSHEET' property='accessories'" +  
          " scope='request' idField='accessories' appCode='1001'   viewFlag='Y'/> "
        + "</td></tr></tr></td>";

    if (portion == 0) {
      out.println("<table cellSpacing=0 cellPadding=0  border=1 width=\"80%\" bordercolordark=\"#FFFFFF\" bordercolorlight=\"#66CCFF\" bgcolor=\"#F3F3F3\" align=\"center\">");
    }
    else if ( (portion == 1) || (portion == 2)) {
      out.println(
          "<table cellSpacing=0 cellPadding=0  border=0 width=\"80%\" align=\"center\">");
    }
    out.println("<tr>");
    shAttr.reset();
    shValue.reset();

    //�ֱ����?��һ���������ƺ�����������  songxuesong
    for (int i = initcount; i <= count; i++) {
      if (!shAttr.getAttrID(portion + "", i + "").equals("-1")) {
        Acolumns++;
        if (shAttr.getNewLine().equals("1"))
          break;
      }
      else if (!shValue.getValueID(portion + "", i + "").equals("-1")) {
        Vcolumns++;
        if (shValue.getNewLine().equals("1"))
          break;
      }
    }

    int tempint = Acolumns + 3 * Vcolumns;
    if (tempint == 0)
      tempint = 1;
    int unit = 100 / tempint;

    shAttr.reset();
    shValue.reset();
    for (int i = initcount; i <= count; i++) {
      if (!shAttr.getAttrID(portion + "", i + "").equals("-1")) {
        out.println("<td colspan=\"" + shAttr.getColspan() + "\" rowspan=\"" +
                    shAttr.getRowspan() + "\" align=\"" + shAttr.getAlign() +
                    "\" width=\"" + unit + "%\" ");
        out.println(" align=\"" + shAttr.getAlign() + "\" valign=" +
                    shAttr.getValign());
        if (StaticMethod.getIntValue(shAttr.getWidth(), 0) > 0) {
          out.println(" width=" + shAttr.getWidth());
        }
        if (StaticMethod.getIntValue(shAttr.getHeight(), 0) > 0) {
          out.println(" height=" + shAttr.getHeight());
        }
        if (!StaticMethod.null2String(shAttr.getNowrap()).equals("")) {
          out.println(" " + shAttr.getNowrap());
        }
        out.println(">");
        if (!StaticMethod.null2String(shAttr.getColor()).trim().equals("")) {
          out.println("<font color=" + shAttr.getColor() + ">");
        }
        out.println(shAttr.getAttrName());
        if (!shAttr.getColor().equals("")) {
          out.println("</font>");
        }
        out.println("</td>");
        if (shAttr.getNewLine().equals("1")) {
          out.println("</tr><tr>");
        }
      }
      else if (!shValue.getValueID(portion + "", i + "").equals("-1")) {
        out.println("<td colspan=\"" + shValue.getColspan() + "\" rowspan=\"" +
                    shValue.getRowspan() + "\"");
        out.println(" align=\"" + shValue.getAlign() + "\" valign=" +
                    shValue.getValign() + " width=\"" + 3 * unit + "%\" ");
        if (StaticMethod.getIntValue(shValue.getWidth(), 0) > 0) {
          out.println(" width=" + shValue.getWidth());
        }
        if (StaticMethod.getIntValue(shValue.getHeight(), 0) > 0) {
          out.println(" height=" + shValue.getHeight());
        }
        out.println(" nowrap >");
        if (!StaticMethod.null2String(shValue.getColor()).trim().equals("")) {
          out.println("<font color=" + shValue.getColor() + ">");
        }
        if (action.equals("insert")) {
          drawFormObject();
        }
        if (action.equals("view")) {

          this.drawViewObject(hash);
        }
        if (action.equals("edit")) {
          drawFormObject(hash);
        }
        if (shValue.getVCtrl().equals("0") && !action.equals("view")) {
          out.println("<font color=\"red\">*</font>");
        }
        out.println("</td>");
        if (shValue.getNewLine().equals("1")) {
          out.println("</tr><tr>");
        }
      }
    }
   out.println("</tr>");
    //��������
    if (portion == BODY) {
      if (shName.getIsattachment().equals("1")) {
        if (action.equals("insert")||action.equals("edit"))
          out.println(inserthtml);
        else if (action.equals("view"))
          out.println(viewhtml);

      }
    }
    out.println("</table>");
  }

  /***************��ӡ����,����ʾ��ʼֵ(insert)*****************************************************/
  private void drawFormObject() {
    switch (StaticMethod.getIntValue(shValue.getShowtype())) {
      case 0:
        if (shValue.getVstoretype().substring(0, 3).equals("dat")) {
          drawEditStoreType(shValue.getVstoretype(), "", "1");
        }
        else {
          out.println("<input type=\"text\" name=\"" + shValue.getVName() +
                      "\" value =\""+ shValue.getDefaultVal()  +"\" size=\"" +
                      shValue.getFormWidth() + "\" maxlength=\"" + shValue.getFormHeight() + "\">");
        }
        break;
      case 1:
        if (!shValue.getTypeid().equals("")) {

          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {
            out.println("<input type=\"radio\" name=\"" + shValue.getVName() +
                        "\" value=\"" +
                        codeContent.getRecordID() + "\"><label>" +
                        codeContent.getCodeName() + "</label>");
          }
        }

        break;
      case 2:
        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {
            out.println("<input type=\"checkbox\" name=\"" + shValue.getVName() +
                        "\" value=\"" +
                        codeContent.getRecordID() + "\"><label>" +
                        codeContent.getCodeName() + "</label>");
          }
        }

        break;
      case 3:

        //       System.out.println("typeid="+shValue.getTypeid());
        out.println("<select name=\"" + shValue.getVName() + "\">");
        out.println("<option value=0>��</option>");
        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {
            out.println("<option value=" + codeContent.getRecordID() + ">" +
                        codeContent.getCodeName() + "</option>");
          }
        }
        out.println("</select>");
        break;
      case 4:
        out.println("<select name=\"" + shValue.getVName() +
                    "\" multiple size=" +
                    StaticMethod.getIntValue(shValue.getWidth(), 5) + ">");
        out.println("<option value=0>��</option>");
        if (!shValue.getTypeid().equals("")) {
          //System.out.println("getTypeid="+shValue.getTypeid());
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {
            out.println("<option value=" + codeContent.getRecordID() + ">" +
                        codeContent.getCodeName() + "</option>");
          }
        }
        out.println("</select>");
        break;
      case 5:
        out.println("<textarea name=\"" + shValue.getVName() + "\"  cols=\"" + shValue.getFormWidth() +
                    "\" rows=\"" + shValue.getFormHeight() + "\">"
                    + shValue.getDefaultVal()+"</textarea>");
        break;

      case 6:
        out.println("<select name=" + shValue.getVName() + ">");
        out.println("<option value=0>��</option>");
        if (!shValue.getTypeid().equals("")) {
          System.out.println("getTypeid=" + shValue.getTypeid());
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {
            out.println("<option value=" + codeContent.getRecordID() + ">" +
                        codeContent.getCodeName() + "</option>");
          }
        }
        out.println("</select>");
        break;
       case 7:
         out.println("<input type=hidden name=" + shValue.getVName() + " value="+user_id+">");
         out.println(tawRmUserBO.getUsernameByUserid(user_id));

      break;
    }
  }

  /***************��ӡ����,��ʾ��ʼֵ(view)****************************************************
   * @params hash hash
   * */
  private void drawViewObject(Hashtable hash) {
    String tempstr = "&nbsp;";
    //out.println("&nbsp;");
    switch (StaticMethod.getIntValue(shValue.getShowtype())) {
      case 0:
        tempstr += hash.get(shValue.getVName()).toString();
        out.println(tempstr);
        break;
      case 1:

      case 3:
        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          tempstr += codeContent.getCodeName( (String) hash.get(shValue.
              getVName()));
          out.println(tempstr);
        }
        break;

      case 2:

      case 4:
        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());

          java.util.StringTokenizer st = new StringTokenizer(hash.get(shValue.
              getVName()).toString(), ",");
          //  out.println("<td>");
          while (st.hasMoreElements()) {
            tempstr += codeContent.getCodeName(st.nextElement().toString()) +
                "<br>&nbsp;";
          }
          if (tempstr.length() > 16)
            tempstr = tempstr.substring(0, tempstr.length() - 6);
          out.println(tempstr);
          //    out.println("</td>");
        }

        break;
      case 5:
        String temp = (String) hash.get(shValue.getVName());
        temp = temp.replace('^', '\r');
        temp = StaticMethod.StringReplace(temp, " ", "");
        tempstr += temp;
        out.println(tempstr);
        break;

      case 7:
        if (!shValue.getTypeid().equals("")) {
           tempstr +=tawRmUserBO.getUsernameByUserid( hash.get(shValue.getVName()).toString());
           out.println(tempstr);
         }
         break;
    }
  }

  /***************��ӡ����,��ʾ��ʼֵ(edit)****************************************************
   * @params hash hash
   * */
  private void drawFormObject(Hashtable hash) {
    switch (StaticMethod.getIntValue(shValue.getShowtype())) {
      case 0:
        if (shValue.getVstoretype().substring(0, 3).equals("dat")) {
          drawEditStoreType(shValue.getVstoretype(),
                            hash.get(shValue.getVName()).toString(), "1");
        }
        else {
          out.println("<input type=\"text\" name=\"" + shValue.getVName() +
                      "\" size=\"" + shValue.getFormWidth() +
                      "\" maxlength=\"" + shValue.getFormHeight() +
                      "\" value=\"" + hash.get(shValue.getVName()) + "\")>");
        }
        break;
      case 1:
        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {
            out.println("<input type=\"radio\" name=\"" + shValue.getVName() +
                        "\" value=\"" +
                        codeContent.getRecordID() + "\" ");
            // if (hash.get(shValue.getVName()).equals(codeContent.getRecordID())) {
            String vName = hash.get(shValue.getVName()).toString();
            if (vName.equals(codeContent.getRecordID())) {
              out.println(" checked");
            }

            out.println("><label>" + codeContent.getCodeName() + "</label>");
          }
        }

        break;
      case 2:
        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {
            out.println("<input type=\"checkbox\" name=\"" + shValue.getVName() +
                        "\" value=\"" +
                        codeContent.getRecordID() + "\" ");
            //  if (hash.get(shValue.getVName()).equals(codeContent.getRecordID())) {
            String vName = hash.get(shValue.getVName()).toString();
            if (vName.indexOf("," + codeContent.getRecordID() + ",") != -1 ||
                vName.equals(codeContent.getRecordID())) {
              out.println(" checked");
            }

            out.println("><label>" + codeContent.getCodeName() + "</label>");
          }
        }

        break;
      case 3:
        out.println("<select name=\"" + shValue.getVName() + "\">");
        out.println("<option value=0>��</option>");

        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          // out.println("<option value=1 selected >��</option>");
          while (codeContent.next()) {
            //   if (hash.get(shValue.getVName()).equals(codeContent.getRecordID()) )
            String vName = hash.get(shValue.getVName()).toString();
            if (vName.equals(codeContent.getRecordID()))

              out.println("<option value=\"" + codeContent.getRecordID() +
                          "\" selected >" + codeContent.getCodeName() +
                          "</option>");
            else
              out.println("<option value=" + codeContent.getRecordID() + ">" +
                          codeContent.getCodeName() + "</option>");

          }
        }
        out.println("</select>");
        break;
      case 4:
        out.println("<select name=\"" + shValue.getVName() +
                    "\" multiple size=" +
                    StaticMethod.getIntValue(shValue.getWidth(), 5) + ">");
        out.println("<option value=0>��</option>");
        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {

            //song   if (hash.get(shValue.getVName()).equals(codeContent.getRecordID()))
            String vName = hash.get(shValue.getVName()).toString();
            if (vName.indexOf("," + codeContent.getRecordID() + ",") != -1 ||
                vName.equals(codeContent.getRecordID())) {

              out.println("<option value=\"" + codeContent.getRecordID() +
                          "\" selected >" + codeContent.getCodeName() +
                          "</option>");
            }
            else
              out.println("<option value=" + codeContent.getRecordID() + ">" +
                          codeContent.getCodeName() + "</option>");
          }
        }
        out.println("</select>");
        break;
      case 5:
        String temp = (String) hash.get(shValue.getVName());
        temp = temp.replace('^', '\r');
        temp = StaticMethod.StringReplace(temp, " ", "");
        out.println("<textarea name=\"" + shValue.getVName() + "\" cols=\"" +
                    shValue.getFormWidth() +
                    "\" rows=\"" + shValue.getFormHeight() + "\">" + temp +
                    "</textarea>");
        break;
      case 6:
        out.println("<select name=" + shValue.getVName() + ">");

        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          out.println("<option value=" + (String) hash.get(shValue.getVName()) +
                      ">" +
                      codeContent.getCodeName( (String) hash.get(shValue.
              getVName())) +
                      "</option>");

          //codeContent=new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {
            out.println("<option value=" + codeContent.getRecordID() + ">" +
                        codeContent.getCodeName() + "</option>");
          }
        }
        out.println("</select>");
        break;

      case 7:
        if (!shValue.getTypeid().equals("")) {
      //  String tempstr = hash.get(shValue.getVName()).toString();
    //    out.println("<input type=hidden name=" + shValue.getVName() + " value="+tempstr+">");
     //   out.println(tawRmUserBO.getUserName(tempstr));
      out.println("<input type=hidden name=" + shValue.getVName() + " value="+user_id+">");
      out.println(tawRmUserBO.getUsernameByUserid(user_id));
      }
      break;
    }
  }

  /***************��ӡ��ѯ����,����ʾ��ʼֵ*****************************************************/
  private void drawQueryObject() {
    switch (StaticMethod.getIntValue(shValue.getShowtype())) {
      case 0:
        if (shValue.getVstoretype().substring(0, 3).equals("dat")) {
          out.println("��");
          drawEditStoreType(shValue.getVstoretype(), "", "1");
          out.println("<br>��");
          drawEditStoreType(shValue.getVstoretype(), "", "2");
        }
        else {
          drawEditStoreType(shValue.getVstoretype(), "", "1");
        }
        break;
      case 1:
        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {
            out.println("<input type=\"radio\" name=\"" + shValue.getVName() +
                        "\" value=\"" +
                        codeContent.getRecordID() + "\"><label>" +
                        codeContent.getCodeName() + "</label>");
          }
        }
        break;
      case 2:
        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {
            out.println("<input type=\"checkbox\" name=\"" + shValue.getVName() +
                        "\" value=\"" +
                        codeContent.getRecordID() + "\"><label>" +
                        codeContent.getCodeName() + "</label>");
          }
        }
        break;
      case 3:
        out.println("<select name=\"" + shValue.getVName() + "\">");
        out.println("<option value=\"\">ȫ��</option>");
        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {
            out.println("<option value=" + codeContent.getRecordID() + ">" +
                        codeContent.getCodeName() + "</option>");
          }
        }
        out.println("</select>");
        break;
      case 4:
        out.println("<select name=\"" + shValue.getVName() +
                    "\" multiple size=" +
                    StaticMethod.getIntValue(shValue.getWidth(), 5) + ">");
        out.println("<option value=\"\">ȫ��</option>");

        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {
            out.println("<option value=" + codeContent.getRecordID() + ">" +
                        codeContent.getCodeName() + "</option>");
          }
        }
        out.println("</select>");
        break;
      case 5:
        out.println("<textarea name=\"" + shValue.getVName() + "\" cols=\"" +
                    shValue.getFormWidth() +
                    "\" rows=\"" + shValue.getFormHeight() + "\"></textarea>");
        break;
      case 6:
        out.println("<select name=" + shValue.getVName() + ">");
        out.println("<option value=\"\">ȫ��</option>");
        if (!shValue.getTypeid().equals("")) {
          codeContent = new CodeContentComsInfo(shValue.getTypeid());
          while (codeContent.next()) {
            out.println("<option value=" + codeContent.getRecordID() + ">" +
                        codeContent.getCodeName() + "</option>");
          }
        }
        out.println("</select>");
        break;
    }
  }

  /************�����ֵ�Ĵ洢���ʹ�ӡ�����ʽ*************************************************
   * @params storetype storetype
   * */
  public void drawStoreType(String storetype) {
    int CurrentYear = -1, CurrentMonth = -1, CurrentDay = -1, CurrentHour = -1,
        CurrentMinute = -1;
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DATE, -0);
    CurrentYear = c.get(Calendar.YEAR);
    CurrentMonth = c.get(Calendar.MONTH) + 1;
    CurrentDay = c.get(Calendar.DATE);
    CurrentHour = c.get(Calendar.HOUR_OF_DAY);
    System.out.println("CurrentHour:" + CurrentHour);
    CurrentMinute = c.get(Calendar.MINUTE);
    if (storetype.substring(0, 3).equals("dat")) { //��ӡ�ֶ�Ϊ����ʱ�����͵���ʾ��ʽ
      out.println("��<select id=Year1 name=" + shValue.getVName() + "Year1>"); //��ӡ��ݸ�ʽ
      for (int i = -1; i <= 4; i++) {
        out.println(" <option value=" + (CurrentYear - i));
        if (CurrentYear == CurrentYear - i) {
          out.println(" SELECTED");
        }
        out.println(">");
        out.println( (CurrentYear - i) + "</option>");
      }
      out.println("</select>");
      out.println("��");
      out.println("<select id=Month1 name=" + shValue.getVName() + "Month1>"); //��ӡ�·ݸ�ʽ
      for (int i = 1; i <= 12; i++) {
        out.println("<option value=" + NUMBER_FORMAT.format(new Integer(i)));
        if (CurrentMonth == i) {
          out.println("SELECTED");
        }
        out.println(">");
        out.println(i);
        out.println(" </option>");
      }
      out.println("</select>��");
      out.println("<select id=Day1 name=" + shValue.getVName() + "Day1>"); //��ӡ�µĵڼ����ʽ
      for (int i = 1; i <= 31; i++) {
        out.println("<option value=" + NUMBER_FORMAT.format(new Integer(i)));
        if (CurrentDay == i) {
          out.println("SELECTED");
        }
        out.println(">");
        out.println(i + "</option>");
      }
      out.println("</select>��");
      if (storetype.equals("datetime")) {
        out.println("<select id=Hour1 name=" + shValue.getVName() + "Hour1>"); //��ӡ��ĵڼ�Сʱ��ʽ
        for (int i = 0; i <= 23; i++) {
          out.println("<option value=" + NUMBER_FORMAT.format(new Integer(i)));
          if (CurrentHour == i) {
            out.println("SELECTED");
          }
          out.println(">");
          out.println(i + "</option>");
        }
        out.println("</select>ʱ");
        out.println("<select id=Minute1 name=" + shValue.getVName() +
                    "Minute1>"); //��ӡ���Ӹ�ʽ
        for (int i = 0; i <= 59; i++) {
          out.println("<option value=" + NUMBER_FORMAT.format(new Integer(i)));
          if (CurrentMinute == i) {
            out.println("SELECTED");
          }
          out.println(">");
          out.println(i + "</option>");
        }
        out.println("</select>��");
      }
      out.println("<br>");
      out.println("��<select id=Year1 name=" + shValue.getVName() + "Year2>"); //��ӡ��ݸ�ʽ
      for (int i = -1; i <= 4; i++) {
        out.println(" <option value=" + (CurrentYear - i));
        if (CurrentYear == CurrentYear - i) {
          out.println(" SELECTED");
        }
        out.println(">");
        out.println( (CurrentYear - i) + "</option>");
      }
      out.println("</select>");
      out.println("��");
      out.println("<select id=Month1 name=" + shValue.getVName() + "Month2>"); //��ӡ�·ݸ�ʽ
      for (int i = 1; i <= 12; i++) {
        out.println("<option value=" + NUMBER_FORMAT.format(new Integer(i)));
        if (CurrentMonth == i) {
          out.println("SELECTED");
        }
        out.println(">");
        out.println(i);
        out.println(" </option>");
      }
      out.println("</select>��");
      out.println("<select id=Day1 name=" + shValue.getVName() + "Day2>"); //��ӡ�µĵڼ����ʽ
      for (int i = 1; i <= 31; i++) {
        out.println("<option value=" + NUMBER_FORMAT.format(new Integer(i)));
        if (CurrentDay == i) {
          out.println("SELECTED");
        }
        out.println(">");
        out.println(i + "</option>");
      }
      out.println("</select>��");
      if (storetype.equals("datetime")) {
        out.println("<select id=Hour2 name=" + shValue.getVName() + "Hour2>"); //��ӡ��ĵڼ�Сʱ��ʽ
        for (int i = 1; i <= 23; i++) {
          out.println("<option value=" + NUMBER_FORMAT.format(new Integer(i)));
          if (CurrentHour == i) {
            out.println("SELECTED");
          }
          out.println(">");
          out.println(i + "</option>");
        }
        out.println("</select>ʱ");
        out.println("<select id=Minute2 name=" + shValue.getVName() +
                    "Minute2>"); //��ӡ���Ӹ�ʽ
        for (int i = 1; i <= 59; i++) {
          out.println("<option value=" + NUMBER_FORMAT.format(new Integer(i)));
          if (CurrentMinute == i) {
            out.println("SELECTED");
          }
          out.println(">");
          out.println(i + "</option>");
        }
        out.println("</select>��");
      }
      out.println("<br>");
      //��ӡ�ֶ�Ϊ�ִ����͵���ʾ��ʽ
    }
    else if (storetype.substring(0, 3).equals("var") ||
             shValue.getVstoretype().substring(0, 3).equals("cha")) {
      out.println("<input type=\"text\" name=\"" + shValue.getVName() +
                  "\" size=\"" + shValue.getFormWidth() +
                  "\" maxlength=\"" + shValue.getFormHeight() + "\">");
      //��ӡ�ֶ�Ϊ��ֵ���͵���ʾ��ʽ
    }
    else if (storetype.substring(0, 3).equals("int") ||
             storetype.substring(0, 3).equals("flo")) {
      out.println(" >= <input type=text name=" + shValue.getVName() + 1 +
                  "> <= <input type=text name=" + shValue.getVName() + 2 + ">");
    }

  }

  /************�����ֵ�Ĵ洢���ʹ�ӡ�����ʽ*********************************************
   * @param storetype :�洢�ֶ�����
   * @param datetimestr :ʱ��ֵ,Ϊ��ʱ��ʾ¼���������,�ǿ���storetypeʱ������ʱ��ʾ�޸�����
   * @param paranum :�������ڱ��ı��,������1������2
   * *********************************************************************************/
  private void drawEditStoreType(String storetype, String datetimestr,
                                 String paranum) {
    if (storetype.substring(0, 3).equals("dat")) { //��ӡ�ֶ�Ϊ����ʱ�����͵���ʾ��ʽ
      int inityear = -1, initmonth = -1, initday = -1, inithour = -1,
          initminute = -1;
      int CurrentYear = -1, CurrentMonth = -1, CurrentDay = -1,
          CurrentHour = -1, CurrentMinute = -1;
      Calendar c = Calendar.getInstance();
      c.add(Calendar.DATE, -0);
      CurrentYear = c.get(Calendar.YEAR);
      CurrentMonth = c.get(Calendar.MONTH) + 1;
      CurrentDay = c.get(Calendar.DATE);
      CurrentHour = c.get(Calendar.HOUR_OF_DAY);
      CurrentMinute = c.get(Calendar.MINUTE);
      if (!datetimestr.equals("")) {

        inityear = StaticMethod.getIntValue(datetimestr.substring(0, 4));
        initmonth = StaticMethod.getIntValue(datetimestr.substring(5, 7));
        initday = StaticMethod.getIntValue(datetimestr.substring(8, 10));
        if (datetimestr.length() > 10) {
          inithour = StaticMethod.getIntValue(datetimestr.substring(11, 13));
          initminute = StaticMethod.getIntValue(datetimestr.substring(14, 16));
        }
      }
      out.println("<select id=Year" + paranum + " name=" + shValue.getVName() +
                  "Year" + paranum + ">"); //��ӡ��ݸ�ʽ
      for (int i = -1; i <= 4; i++) {
        out.println(" <option value=" + (CurrentYear - i));
        if (CurrentYear == CurrentYear - i) {
          out.println(" SELECTED");
        }
        out.println(">");
        out.println( (CurrentYear - i) + "</option>");
      }
      if (inityear != -1) {
        out.println("<option value=" +
                    NUMBER_FORMAT.format(new Integer(inityear)) + " selected>" +
                    inityear +
                    "</option>");
      }
      out.println("</select>");
      out.println("��");
      out.println("<select id=Month" + paranum + " name=" + shValue.getVName() +
                  "Month" + paranum + ">"); //��ӡ�·ݸ�ʽ
      for (int i = 1; i <= 12; i++) {
        out.println("<option value=" + NUMBER_FORMAT.format(new Integer(i)));
        if (CurrentMonth == i) {
          out.println("SELECTED");
        }
        out.println(">");
        out.println(i);
        out.println(" </option>");
      }
      if (initmonth != -1) {
        out.println("<option value=" +
                    NUMBER_FORMAT.format(new Integer(initmonth)) + " selected>" +
                    initmonth +
                    "</option>");
      }
      out.println("</select>��");
      out.println("<select id=Day" + paranum + " name=" + shValue.getVName() +
                  "Day" + paranum + ">"); //��ӡ�µĵڼ����ʽ
      for (int i = 1; i <= 31; i++) {
        out.println("<option value=" + NUMBER_FORMAT.format(new Integer(i)));
        if (CurrentDay == i) {
          out.println("SELECTED");
        }
        out.println(">");
        out.println(i + "</option>");
      }
      if (initday != -1) {
        out.println("<option value=" + NUMBER_FORMAT.format(new Integer(initday)) +
                    " selected>" + initday +
                    "</option>");
      }
      out.println("</select>��");
      boolean aaa = storetype.equals("datetime");
      if (aaa) {
        out.println("<select id=Hour" + paranum + " name=" + shValue.getVName() +
                    "Hour" + paranum + ">"); //��ӡ��ĵڼ�Сʱ��ʽ
        for (int i = 0; i <= 23; i++) {
          out.println("<option value=" + NUMBER_FORMAT.format(new Integer(i)));
          if (CurrentHour == i) {
            out.println("SELECTED");
          }
          out.println(">");
          out.println(i + "</option>");
        }
        if (inithour != -1) {
          out.println("<option value=" +
                      NUMBER_FORMAT.format(new Integer(inithour)) +
                      " selected>" + inithour +
                      "</option>");
        }
        out.println("</select>ʱ");
        out.println("<select id=Minute" + paranum + " name=" + shValue.getVName() +
                    "Minute" + paranum + ">"); //��ӡ���Ӹ�ʽ
        for (int i = 0; i <= 59; i++) {
          out.println("<option value=" + NUMBER_FORMAT.format(new Integer(i)));
          if (CurrentMinute == i) {
            out.println("SELECTED");
          }
          out.println(">");
          out.println(i + "</option>");
        }
        if (initminute != -1) {
          out.println("<option value=" +
                      NUMBER_FORMAT.format(new Integer(initminute)) +
                      " selected>" + initminute +
                      "</option>");
        }
        out.println("</select>��");
      }

      //��ӡ�ֶ�Ϊ�ִ����͵���ʾ��ʽ
    }
    else if (storetype.substring(0, 3).equals("var") ||
             shValue.getVstoretype().substring(0, 3).equals("cha")) {
      out.println("<input type=\"text\" name=\"" + shValue.getVName() +
                  "\" size=\"" + shValue.getFormWidth() +
                  "\" maxlength=\"" + shValue.getFormHeight() + "\">");
      //��ӡ�ֶ�Ϊ��ֵ���͵���ʾ��ʽ
    }
    else if (storetype.substring(0, 3).equals("int") ||
             storetype.substring(0, 3).equals("flo")) {
      out.println(">=<input type=text name=" + shValue.getVName() + 1 +
                  "><=<input type=text name=" + shValue.getVName() + 2 + ">");
    }
  }

  /********************************************************************
   ͨ��id�������ڱ�ļ�¼
   @param id ���
   @return Hashtable
   *******************************************************************/

  private Hashtable getValue(String id) {
    RecordSet rs = new RecordSet();
    PropertyFile prop = PropertyFile.getInstance();
    String sql = "select * from " +
        StaticMethod.null2String(prop.getProperty("pretable"), "boco_") +
        shName.getSheetID() + " where id=" + id;
    System.out.println("sql=" + sql);
    rs.execute(sql);
    shValue.reset();
    Hashtable hash = new Hashtable();
    try {
      if (rs.next()) {
        while (shValue.next()) {
          if (shValue.getVstoretype().substring(0, 3).equals("dat")) {
            if (shValue.getVstoretype().equals("datetime")) {
              hash.put(shValue.getVName(),
                       StaticMethod.fromBaseEncoding(rs.
                  getString(shValue.getVName())).substring(0, 19));
            }

            else if (shValue.getVstoretype().substring(0,
                4).equals("date")) {
              hash.put(shValue.getVName(),
                       StaticMethod.fromBaseEncoding(rs.
                  getString(shValue.getVName())).substring(0, 10));
            }

          }
          else {
            hash.put(shValue.getVName(),
                     StaticMethod.fromBaseEncoding(rs.
                getString(shValue.getVName())));
          }
        }
        hash.put("attach_id",
                 StaticMethod.fromBaseEncoding(rs.
                                               getString("attach_id")));

      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return hash;
  }

}
