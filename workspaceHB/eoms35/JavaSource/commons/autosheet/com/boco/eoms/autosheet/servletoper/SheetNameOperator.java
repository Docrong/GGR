package com.boco.eoms.autosheet.servletoper;

import javax.servlet.http.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.db.util.*;
import com.boco.eoms.autosheet.util.*;
//import com.boco.eoms.cache.CacheDataLoad;

public class SheetNameOperator {
  /************************************************************************************
   �����
   * sh_cname �?��ʾ��������
   * module_id ��ɱ�ı���
   * style �?��ʽ�ķ�� (0���ᣬ1������  2: ����)
   * isattach ��ͷβ�� (0��ֻ�б��� 1��ͷ+���� 2��ͷ+����+β 3������+β)
   * isattachment �Ƿ���(0:���� 1:��)
   * width ҳ�����ֵ����ؿ�
   * height ҳ�����ֵ����ظ�
   * columnwidth ҳ�����ֵ��п�
   * columnheight ҳ�����ֵ��п�
   * para1 �?����(0:����� 1:һά���Ա� 2:��ά��
   * para3 �Ƿ���ʱ��(0:��ʽ�� 1:��ʱ��)
   * */
  private String sh_cname = "";
  private String module_id = "";
  private String module = "";
  private String modulemk = "";
  private String ifsetpath = "";
  private String UserId = "";
  private int style, isattach, isattachment, width, height, columnwidth,
      columnheight, para1; //������ʽ��ķ��,�������
  private String params[];
  private int sheetID;
  private SheetName st = SheetName.getInstance();
  private HttpSession session;
  private RecordSet rs = new RecordSet();
  /**************************************
   ���������
   */
  private ConnStatement cstmt = new ConnStatement();
  private LogMan log = LogMan.getInstance();
//  private LogRecord LR = new LogRecord();
  /*******************
   �������
   */
  public SheetNameOperator() {
  }

  /**********��ȡ�������sh_cname,module_id,style,isattach,columnwidth,columnheight,width,height
     @param request http����
     @throws Exception
   */
  public void initParameter(HttpServletRequest request) throws Exception {
    sh_cname = request.getParameter("sh_cname");
    module_id = request.getParameter("module_id");
    module = request.getParameter("module");
    modulemk = request.getParameter("modulemk");
    ifsetpath = request.getParameter("ifsetpath");
    style = StaticMethod.getIntValue(request.getParameter("style"), 0);
    isattach = StaticMethod.getIntValue(request.getParameter("isattach"), 0);
    isattachment = StaticMethod.getIntValue(request.getParameter("isAttachment"),
                                            0);
    width = StaticMethod.getIntValue(request.getParameter("width"), 0);
    height = StaticMethod.getIntValue(request.getParameter("height"), 0);
    columnwidth = StaticMethod.getIntValue(request.getParameter("columnwidth"),
                                           0);
    columnheight = StaticMethod.getIntValue(request.getParameter("columnheight"),
                                            0);
    para1 = Integer.parseInt(request.getParameter("para1"));
    session = request.getSession();
    session.setAttribute("module_id", module_id);
    session.setAttribute("sh_cname", sh_cname);
  }

      /*******************int processData()******************************************
         �����������,д����ʱ��,����SheetNameComsInfo����,�����������¼��id
         @throws Exception
         @return int
   */
  public int dataInsert(int DeptId) throws Exception {

    try {
      //CacheDataLoad CDL = new CacheDataLoad();
      //����taw_sheetanmne
      String sql = "insert into taw_sheetname(sh_cname,module_id,style,isattach,isattachment,width,height,columnwidth,columnheight,para1,para3) " +
          "values(?,?,?,?,?,?,?,?,?,?,?)";
      cstmt.setStatementSql(sql);
      //System.out.println("name====="+sh_cname);
      cstmt.setString(1, StaticMethod.dbNStrRev(sh_cname));
      if (ifsetpath != null && ifsetpath.equals("0")) {
        cstmt.setString(2, module);
      }
      else {
        cstmt.setString(2, modulemk);
      }
      cstmt.setInt(3, style);
      cstmt.setInt(4, isattach);
      cstmt.setInt(5, isattachment);
      cstmt.setInt(6, width);
      cstmt.setInt(7, height);
      cstmt.setInt(8, columnwidth);
      cstmt.setInt(9, columnheight);
      cstmt.setInt(10, para1);
      cstmt.setInt(11, 1);
      cstmt.executeUpdate();
      cstmt.commit();
      sql = "select max(sheet_id) from taw_sheetname";
      cstmt.setStatementSql(sql);
      cstmt.executeQuery();
      if (cstmt.next()) {
        sheetID = cstmt.getInt(1);
      }

      if ( (ifsetpath != null && ifsetpath.equals("0")) ||
          (modulemk != null && modulemk.equals("27"))) {
        //����½��ı?����Ӧģ����
        if (modulemk != null && modulemk.equals("27")) {
          sql = "select max(id) from taw_rm_tree where parent_id = " + modulemk;
        }
        else if (module_id == null || module_id.equals("")) {
          sql = "select max(id) from taw_rm_tree where parent_id = " + module;
        }
        else {
          sql = "select max(id) from taw_rm_tree where parent_id = " +
              module_id;
        }
        cstmt.setStatementSql(sql);
        cstmt.executeQuery();
        int MenuId = 0;
        String parentid = "";
        if (cstmt.next()) {
          if (cstmt.getString(1) == null) {
            if (modulemk != null && modulemk.equals("27")) {
              MenuId = Integer.parseInt(modulemk + "01");
              parentid = modulemk;
            }
            else if (module_id == null || module_id.equals("")) {
              MenuId = Integer.parseInt(module + "01");
              parentid = module;
            }
            else {
              MenuId = Integer.parseInt(module_id + "01");
              parentid = module_id;
            }
          }
          else {
            MenuId = Integer.parseInt(cstmt.getString(1)) + 1;
            if (modulemk != null && modulemk.equals("27")) {
             parentid = modulemk;
           }
           else if (module_id == null || module_id.equals("")) {
             parentid = module;
           }
           else {
             parentid = module_id;
           }
          }
        }
        String url = "/htmlservlet?sheet_id=" + sheetID + "&action=list&detail=full";
        String insertSql =
            "insert into taw_rm_tree (id,parent_id,name,leaf,url,hide) values ('" +
            MenuId + "','" + parentid + "','" + StaticMethod.dbNStrRev(sh_cname) + "',1,'" + url +
            "',0)";
        System.out.println("insertSql:" + insertSql);
        cstmt.setStatementSql(insertSql);
        cstmt.executeUpdate();
        cstmt.commit();
        //CDL.initRmTreeCacheAll();
      }
      st.removeOSCache();
      SheetName.removeInstance();
      session.setAttribute("sheetID", sheetID + "");
      session.setAttribute("module_id", module_id);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      cstmt.close();
    }

    return sheetID;
  }

  /**������¼�¼**/
  public boolean doUpdate(HttpServletRequest request, String UserId, int DeptId) throws
      Exception {
    cstmt = new ConnStatement();
    boolean flag = false;
    int sheet_id = StaticMethod.getIntValue(request.getParameter("sheet_id"), 0);
//    List Data = Result(sheet_id);
//    LR.Update_SheetLog(UserId,DeptId,Data,sheet_id,0,1);
    try {
      //�滻taw_sheetanmne
      String sql = "update taw_sheetname set sh_cname=?,style=?,isattach=?," +
          "isattachment=?,width=?,height=?,columnwidth=?,columnheight=?,para1=?,para3=?" +
          " where sheet_id =?";

      cstmt.setStatementSql(sql);
      cstmt.setString(1, sh_cname);
      cstmt.setInt(2, style);
      cstmt.setInt(3, isattach);
      cstmt.setInt(4, isattachment);
      cstmt.setInt(5, width);
      cstmt.setInt(6, height);
      cstmt.setInt(7, columnwidth);
      cstmt.setInt(8, columnheight);
      cstmt.setInt(9, para1);
      cstmt.setInt(10, 1);
      cstmt.setInt(11, sheet_id);

      cstmt.executeUpdate();
      cstmt.commit();
      flag = true;
    }
    catch (Exception e) {
      try {
        cstmt.rollback();
      }
      catch (Exception ex) {}

      log.writeLog("SheetAttrOperator", e);
      throw new Exception("�޸���ݿ����ʧ��");
    }
    finally {
      cstmt.close();
    }
    return flag;
  }

  //ȡ��δ�޸�ǰ����ݣ������û���д����ݽ��бȽϣ��õ����޸ĵ������
//  public List Result(int sheet_id){
//    List Data = new ArrayList();
//    LogModel lm = new LogModel();
//    String sql_query = "select sh_cname,style,isattach,isattachment,width,height,columnwidth,columnheight,para1 where sheet_id = " + sheet_id;
//    rs.executeSql(sql_query);
//    while(rs.next()){
//      if(sh_cname != rs.getString(0)){
//        lm.setValueName("�?���");
//        lm.setOrgValue(rs.getString(0));
//        lm.setNewValue(sh_cname);
//        Data.add(lm);
//      }
//      if(style != rs.getInt(1)){
//        lm.setValueName("�?���");
//        lm.setOrgValue(TurnStyle(rs.getInt(1)));
//        lm.setNewValue(TurnStyle(style));
//        Data.add(lm);
//      }
//      if(isattach != rs.getInt(2)){
//        lm.setValueName("��ͷ�ͱ�β����");
//        lm.setOrgValue(TurnIsattach(rs.getInt(2)));
//        lm.setNewValue(TurnIsattach(isattach));
//        Data.add(lm);
//      }
//      if(isattachment != rs.getInt(3)){
//        lm.setValueName("���޸�������");
//        lm.setOrgValue(TurnIsAttchment(rs.getInt(3)));
//        lm.setNewValue(TurnIsAttchment(isattachment));
//        Data.add(lm);
//      }
//      if(width != rs.getInt(4)){
//        lm.setValueName("�?���");
//        lm.setOrgValue(rs.getString(4));
//        lm.setNewValue(String.valueOf(width));
//        Data.add(lm);
//      }
//      if(height != rs.getInt(5)){
//        lm.setValueName("�?�߶�");
//        lm.setOrgValue(rs.getString(5));
//        lm.setNewValue(String.valueOf(height));
//        Data.add(lm);
//      }
//      if(columnwidth != rs.getInt(6)){
//        lm.setValueName("�п��");
//        lm.setOrgValue(rs.getString(6));
//        lm.setNewValue(String.valueOf(columnwidth));
//        Data.add(lm);
//      }
//      if(columnheight != rs.getInt(7)){
//        lm.setValueName("�и߶�");
//        lm.setOrgValue(rs.getString(7));
//        lm.setNewValue(String.valueOf(columnheight));
//        Data.add(lm);
//      }
//      if(para1 != rs.getInt(8)){
//        lm.setValueName("�?����");
//        lm.setOrgValue(TurnPara1(rs.getInt(8)));
//        lm.setNewValue(TurnPara1(para1));
//        Data.add(lm);
//      }
//    }
//    return Data;
//  }
  //�������͵ı?���ת��Ϊ�ַ�
  public String TurnStyle(int Style) {
    String StrStyle = "";
    switch (style) {
      case 0:
        StrStyle = "����";
        break;
      case 1:
        StrStyle = "����";
        break;
      case 2:
        StrStyle = "����";
        break;
    }
    return StrStyle;
  }

  //�������͵ı�ͷ�ͱ�β����ת��Ϊ�ַ�
  public String TurnIsattach(int Isattach) {
    String StrIsattach = "";
    switch (isattach) {
      case 0:
        StrIsattach = "ֻ�б���";
        break;
      case 1:
        StrIsattach = "�б�ͷ�ͱ���";
        break;
      case 2:
        StrIsattach = "�б�ͷ������ͱ�β";
        break;
      case 3:
        StrIsattach = "�б���ͱ�β";
        break;
    }
    return StrIsattach;
  }

  //�������͵����޸�������ת��Ϊ�ַ�
  public String TurnIsAttchment(int isattachment) {
    String IsAttchment = "";
    switch (isattachment) {
      case 0:
        IsAttchment = "�и���";
        break;
      case 1:
        IsAttchment = "û�и���";
        break;
    }
    return IsAttchment;
  }

  //�������͵ı?����ת��Ϊ�ַ�
  public String TurnPara1(int para1) {
    String Para1 = "";
    switch (para1) {
      case 0:
        Para1 = "�����";
        break;
      case 1:
        Para1 = "һά���Ա�";
        break;
      case 2:
        Para1 = "��ά��";
        break;
    }
    return Para1;
  }

}
