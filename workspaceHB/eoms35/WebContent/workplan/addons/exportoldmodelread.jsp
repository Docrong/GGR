<%@ page language="java" pageEncoding="UTF-8" %>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<%@ page import="java.io.FileOutputStream"%>

<%@ page import="org.jdom.Element"%>
<%@ page import="org.jdom.output.XMLOutputter"%>
<%@ page import="org.jdom.Attribute"%>
<%@ page import="org.jdom.Document"%>

<%@ page import="com.boco.eoms.db.util.RecordSet"%>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<%@ page import="com.boco.eoms.workplan.util.TawwpUtil"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>

<%@ page import="com.boco.eoms.workplan.vo.TawwpAddonsOldDataVO"%>
<html>
<head>
<title>
导出历史模板
</title>
</head>
<body bgcolor="#ffffff">
<h1>
<%
/**
 * 导出全部都显示（时间）的历史模版数据到
 * WEB-INF/originXML/old/[model_id]/[sheet_id]_read.xml
 * 等同于com.boco.eoms.workplan.bo.TawwpAddonsExportOldDataBO
 * 中的静态方法：addonsExportOldModelReadBO()
 * Author:zhyg
 */
 	String[] elementAttribute = TawwpStaticVariable.elementAttribute;

      	String[] showTypeValue = TawwpStaticVariable.showTypeValue;

    //导入模版
    RecordSet rtSheet = new RecordSet();
    RecordSet rtAttr = new RecordSet();
    RecordSet rtValue = new RecordSet();
    RecordSet rtAttrVO = new RecordSet();

    //表信息
    String sheetID = "";
    String sheetName = "";
    String sheetModel = "";

    //获得
    String url="";
    //结构设计

    //获得所有表的信息
    String sql = "select * from taw_sheetname";
    rtSheet.execute(sql);
    int sheetNum = 0; //sheet的数量
    int eleNum = 0;
    sheetNum = rtSheet.getCounts();
    while (rtSheet.next()) {

      sheetName = TawwpUtil.parseChinese(rtSheet.getString("sh_cname"));
      sheetID = rtSheet.getString("sheet_id");
      sheetModel = rtSheet.getString("module_id");

      List sheetTable = new ArrayList();
      //List sheetTable=null;
      TawwpAddonsOldDataVO tawwpAddonsOldDataVO = null;
      //获得taw_sheetattr的数量
      sql = "select count(*) as totle from taw_sheetattr where sheet_id='" + sheetID + "'";
      rtAttr.execute(sql);
      rtAttr.next();
      eleNum = Integer.parseInt(rtAttr.getString("totle"));

      //获得taw_sheetvalue的数量
      sql = "select count(*) as totle from taw_sheetvalue where sheet_id='" + sheetID + "'";
      rtValue.execute(sql);
      rtValue.next();
      eleNum = eleNum + Integer.parseInt(rtValue.getString("totle"));

      for (int i = 1; i <= eleNum; i++) {

        sql = "select * from taw_sheetattr where sheet_id='" + sheetID +
            "' and index1='" + i + "'";

        rtAttrVO.execute(sql);
        //如果存在该记录
        if (rtAttrVO.next()) {
          tawwpAddonsOldDataVO = new TawwpAddonsOldDataVO();
          tawwpAddonsOldDataVO.setAorv("a");
          tawwpAddonsOldDataVO.setShowtype(6);
          tawwpAddonsOldDataVO.setValue(StaticMethod.strFromDBToPage(rtAttrVO.getString("attr_name")));
          tawwpAddonsOldDataVO.setIsattach(rtAttrVO.getString("isattach"));
          tawwpAddonsOldDataVO.setIndex1(rtAttrVO.getString("index1"));
          tawwpAddonsOldDataVO.setNewline(rtAttrVO.getString("newline"));
          tawwpAddonsOldDataVO.setAlign(rtAttrVO.getString("align"));
          tawwpAddonsOldDataVO.setValign(rtAttrVO.getString("valign"));
          tawwpAddonsOldDataVO.setColspan(rtAttrVO.getString("colspan"));
          tawwpAddonsOldDataVO.setRowspan(rtAttrVO.getString("rowspan"));
          tawwpAddonsOldDataVO.setStarttime(rtAttrVO.getString("starttime"));
          tawwpAddonsOldDataVO.setEndtime(rtAttrVO.getString("endtime"));

          sheetTable.add(tawwpAddonsOldDataVO);

        }
        else { //如果不存在
          sql = "select * from taw_sheetvalue where sheet_id='" + sheetID +
              "' and index1='" + i + "'";
          rtValue.execute(sql);
          if (rtValue.next()) {

            tawwpAddonsOldDataVO = new TawwpAddonsOldDataVO();
            tawwpAddonsOldDataVO.setAorv("v");
            tawwpAddonsOldDataVO.setShowtype(rtValue.getInt("showtype"));
            tawwpAddonsOldDataVO.setValue(StaticMethod.strFromDBToPage(rtValue.getString("defaultval")));
            tawwpAddonsOldDataVO.setIsattach(rtValue.getString("isattach"));
            tawwpAddonsOldDataVO.setIsattach(rtValue.getString("isattach"));
            tawwpAddonsOldDataVO.setIndex1(rtValue.getString("index1"));
            tawwpAddonsOldDataVO.setNewline(rtValue.getString("newline"));
            tawwpAddonsOldDataVO.setAlign(rtValue.getString("align"));
            tawwpAddonsOldDataVO.setValign(rtValue.getString("valign"));
            tawwpAddonsOldDataVO.setColspan(rtValue.getString("colspan"));
            tawwpAddonsOldDataVO.setRowspan(rtValue.getString("rowspan"));
            tawwpAddonsOldDataVO.setStarttime(rtValue.getString("starttime"));
            tawwpAddonsOldDataVO.setEndtime(rtValue.getString("endtime"));
            sheetTable.add(tawwpAddonsOldDataVO);
          }
        }

      }

      //存数据到模版中地址

     Element titleElement = new Element("title");

      titleElement.setAttribute(new Attribute("value", sheetName));
      titleElement.setAttribute(new Attribute("name", sheetID));
      titleElement.setAttribute(new Attribute("type", "1"));

      Document myDocument = new Document(titleElement);

      Element headElement = new Element("head");
      Element headElesElement = new Element("elements");

      Element bodyElement = new Element("body");
      Element bodyElesElement = new Element("elements");
      Element bodyEmtermElement = new Element("emterm");

      String newline="1";

      for (int i = 0; i < sheetTable.size(); i++) { //获取头元素数做循环
        Element bodyEleEmtermElement = new Element("element");

        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[0],
                        ((TawwpAddonsOldDataVO) sheetTable.get(i)).getValue()));
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[1],
                        showTypeValue[((TawwpAddonsOldDataVO) sheetTable.get(i)).getShowtype()]));

        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[2],
                        ((TawwpAddonsOldDataVO) sheetTable.get(i)).getAorv()+i));

        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[3],
                        newline));
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[4],
                        ((TawwpAddonsOldDataVO) sheetTable.get(i)).getRowspan()));
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[5],
                        ((TawwpAddonsOldDataVO) sheetTable.get(i)).getColspan()));
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[6],
                        ((TawwpAddonsOldDataVO) sheetTable.get(i)).getAlign()));
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[7],
                        ((TawwpAddonsOldDataVO) sheetTable.get(i)).getValign()));
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[8],
                        "1"));
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[9],
                        "1"));
         bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[10],
                        "null"));
        //显示
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[11],
                        "00:00:00"));
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[12],
                        "23:59:59"));

        /*
        //原表
        if(!((TawwpAddonsOldDataVO)sheetTable.get(i)).getStarttime().equals("")){
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[11],
                        ((TawwpAddonsOldDataVO) sheetTable.get(i)).getStarttime()+":00"));
          }else{
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[11],
                        "00:00:00"));
            }
        if(!((TawwpAddonsOldDataVO)sheetTable.get(i)).getEndtime().equals("")){
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[12],
                        ((TawwpAddonsOldDataVO)sheetTable.get(i)).getEndtime()+":00"));
          }else{
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[12],
                        "23:59:59"));
            }
	*/
        bodyEleEmtermElement.setAttribute(new Attribute(elementAttribute[
                13],
                ""));
        newline=((TawwpAddonsOldDataVO)sheetTable.get(i)).getNewline();

        bodyEmtermElement.addContent(bodyEleEmtermElement);
      }
      //搭构head区域
      headElement.addContent(headElesElement);

      bodyElesElement.addContent(bodyEmtermElement);
      bodyElement.addContent(bodyElesElement);

      myDocument.getRootElement().addContent(headElement);
      myDocument.getRootElement().addContent(bodyElement);
      String indent = " ";
      boolean newLines = true;
      XMLOutputter outp = new XMLOutputter(indent, newLines, "gb2312");

      TawwpUtil.mkDir(TawwpStaticVariable.rootDir +
                      TawwpStaticVariable.rootOriginXMLDir + "old");
      outp.output(myDocument,
                  new FileOutputStream(
          TawwpStaticVariable.rootDir + TawwpStaticVariable.rootOriginXMLDir +
          "old/" + sheetID + "_read"   + ".xml"));
      url = TawwpStaticVariable.rootOriginXMLDir + "old/" +
          sheetID + ".xml";
      System.out.println("新的只读模板:"+sheetID + "_read"+ ".xml");

      //存数据到模版中地址结束
    }
    rtValue.givebackConnection();
    rtAttr.givebackConnection();
    rtSheet.givebackConnection();
    System.out.println("模版总数sheetNum=" + sheetNum);

%>
</h1>
</body>
</html>
