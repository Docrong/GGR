package com.boco.eoms.km.excelmanage;

/**
 * <p>Title:excel���?�幤��</p>
 * <p>Description: ��Ԫ����Ϣ����</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: boco </p>
 * @author ����
 * @version 1.0
 */

import org.jdom.Element;
import org.jdom.Attribute;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.Region;

public class PoiStyle {

  public String title; //��Ԫ�����ƣ���ͷ������������ʾ��

  public String size; //��С

  public String font; //����

  public String bold; //�Ƿ�Ӵ�

  public int color; //��ɫ

  public int xPort; //�п�ʼλ�� ��0��ʼ

  public int yPort; //�п�ʼλ�� ��0��ʼ

  public int width; //�еĿ�� 0��ʾһ��Ԫ��

  public int higth; //�еĸ߶� 0��ʾһ���и�

  public String proper; //��Ӧ����ݶ�������ԣ��������ݲ����и����û��ֵ������ʾtitle�е���Ϣ��

  public String getBold() {
    return bold;
  }

  public void setBold(String bold) {
    this.bold = bold;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public String getFont() {
    return font;
  }

  public void setFont(String font) {
    this.font = font;
  }

  public int getHigth() {
    return higth;
  }

  public void setHigth(int higth) {
    this.higth = higth;
  }

  public String getProper() {
    return proper;
  }

  public void setProper(String proper) {
    this.proper = proper;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getTitle() {
    return title;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getXPort() {
    return xPort;
  }

  public void setXPort(int xPort) {
    this.xPort = xPort;
  }

  public int getYPort() {
    return yPort;
  }

  public void setYPort(int yPort) {
    this.yPort = yPort;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  private static String getElementAttribute(Element _element, String _attribute) {
    String value = null;

    value = _element.getAttribute(_attribute).getValue();

    return value;
  }

  private static String getElementAttribute(Element _element, String _attribute,
                                            String _returnValue) {
    String value = null;

    Attribute attribute = _element.getAttribute(_attribute);

    if (attribute != null) {
      value = attribute.getValue();
    }

    value = value == null ? _returnValue : value;

    return value;
  }

  private static int getElementAttribute(Element _element, String _attribute,
                                         int _returnValue) {
    int value = 0;
    String temp = null;

    Attribute attribute = _element.getAttribute(_attribute);

    if (attribute != null) {
      temp = attribute.getValue();
    }

    value = temp == null ? _returnValue : Integer.parseInt(temp);

    return value;
  }

  private static String getCellAttribute(HSSFCell cell) {
    String value = "";

    switch (cell.getCellType()) {
      case HSSFCell.CELL_TYPE_STRING:
        value = cell.getStringCellValue();
        break;
      case HSSFCell.CELL_TYPE_NUMERIC:
        value = String.valueOf(cell.getNumericCellValue());
        break;
      case HSSFCell.CELL_TYPE_BLANK:
        value = "";
        break;
    }

    if (value.indexOf("t(") == 0) {
      value = value.substring(2, value.length() - 1);
    }
    else if (value.indexOf("b(") == 0) {
      value = value.substring(2, value.length() - 1);
    }
    else if (value.indexOf("bp(") == 0) {
      value = value.substring(3, value.length() - 1);
    }

    return value;
  }

  private static String getCellValue(HSSFCell cell) {
    String value = "";

    switch (cell.getCellType()) {
      case HSSFCell.CELL_TYPE_STRING:
        value = cell.getStringCellValue();
        break;
      case HSSFCell.CELL_TYPE_NUMERIC:
        value = String.valueOf(cell.getNumericCellValue());
        break;
      case HSSFCell.CELL_TYPE_BLANK:
        value = "";
        break;
    }

    if (value.indexOf("t(") == 0) {
      value = value.substring(2, value.length() - 1);
      if (value.indexOf("#") != 0 && value.indexOf("%") != 0) {
        value = "";
      }
    }
    else if (value.indexOf("b(") == 0) {
      value = value.substring(2, value.length() - 1);
    }
    else if (value.indexOf("bp(") == 0) {
      value = value.substring(3, value.length() - 1);
    }

    return value;
  }

  public static PoiStyle creatPoiStyleFormXML(Element _element) {

    PoiStyle poiStyle = new PoiStyle();

    poiStyle.setTitle(getElementAttribute(_element, "title", ""));
    poiStyle.setSize(getElementAttribute(_element, "size", "10"));
    poiStyle.setFont(getElementAttribute(_element, "font", "SimSun"));
    poiStyle.setColor(getElementAttribute(_element, "color", 0));
    poiStyle.setBold(getElementAttribute(_element, "bold", "N"));
    poiStyle.setXPort(getElementAttribute(_element, "xport", 0));
    poiStyle.setYPort(getElementAttribute(_element, "yport", 0));
    poiStyle.setWidth(getElementAttribute(_element, "width", 0));
    poiStyle.setHigth(getElementAttribute(_element, "higth", 0));
    poiStyle.setProper(getElementAttribute(_element, "propert", ""));

    return poiStyle;
  }

  public static PoiStyle creatPoiStyleFormExcel(HSSFCell _cell, Region _region,
                                                HSSFWorkbook _wb) {

    PoiStyle poiStyle = new PoiStyle();

    HSSFCellStyle cellStyle = _cell.getCellStyle();
    HSSFFont littleFont = _wb.getFontAt(cellStyle.getFontIndex());

    poiStyle.setTitle(getCellAttribute(_cell));
    poiStyle.setSize(String.valueOf(littleFont.getFontHeightInPoints()));
    poiStyle.setFont(littleFont.getFontName());
    poiStyle.setColor(littleFont.getColor());
    poiStyle.setBold(littleFont.getBoldweight() == HSSFFont.BOLDWEIGHT_BOLD ?
                     "Y" : "N");

    poiStyle.setXPort(_region.getColumnFrom());
    poiStyle.setYPort(_region.getRowFrom());
    poiStyle.setWidth(_region.getColumnTo());
    poiStyle.setHigth(_region.getRowTo());
    poiStyle.setProper(getCellValue(_cell));

    return poiStyle;
  }

  public static PoiStyle creatPoiStyleFormExcel(Region _region,PoiStyle _poiStyle) {

    //_poiStyle.setXPort(_region.getColumnFrom());
    //_poiStyle.setYPort(_region.getRowFrom());
    _poiStyle.setWidth(_region.getColumnTo() - _region.getColumnFrom());
    _poiStyle.setHigth(_region.getRowTo() - _region.getRowFrom());

    return _poiStyle;
  }

  public static PoiStyle creatPoiStyleFormExcel(HSSFCell _cell,HSSFWorkbook _wb,int _xPort, int _yPort) {

    PoiStyle poiStyle = new PoiStyle();

    HSSFCellStyle cellStyle = _cell.getCellStyle();
    HSSFFont littleFont = _wb.getFontAt(cellStyle.getFontIndex());

    poiStyle.setTitle(getCellAttribute(_cell));
    poiStyle.setSize(String.valueOf(littleFont.getFontHeightInPoints()));
    poiStyle.setFont(littleFont.getFontName());
    poiStyle.setColor(littleFont.getColor());
    poiStyle.setBold(littleFont.getBoldweight() == HSSFFont.BOLDWEIGHT_BOLD ?
                     "Y" : "N");

    poiStyle.setXPort(_xPort);
    poiStyle.setYPort(_yPort);
    poiStyle.setWidth(0);
    poiStyle.setHigth(0);
    poiStyle.setProper(getCellValue(_cell));

    return poiStyle;
  }


  public Element createPoiStyleToXML() {

    Element element = new Element("col");

    element.setAttribute("title", this.getTitle());
    element.setAttribute("size", this.getSize());
    element.setAttribute("font", this.getFont());
    element.setAttribute("color", String.valueOf(this.getColor()));
    element.setAttribute("bold", this.getBold());
    element.setAttribute("xport", String.valueOf(this.getXPort()));
    element.setAttribute("yport", String.valueOf(this.getYPort()));
    element.setAttribute("width", String.valueOf(this.getWidth()));
    element.setAttribute("higth", String.valueOf(this.getHigth()));
    element.setAttribute("propert", this.getProper());

    return element;
  }

}
