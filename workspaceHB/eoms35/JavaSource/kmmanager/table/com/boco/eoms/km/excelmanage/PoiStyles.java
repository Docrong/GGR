package com.boco.eoms.km.excelmanage;

/**
 * <p>Title: wϰ</p>
 * <p>Description: wϰ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.util.Hashtable;

import java.util.Properties;
import org.jdom.Element;
import java.util.List;

import com.boco.eoms.common.properties.*;

public class PoiStyles {

  private Hashtable excelHash;

  private int rowCount;

  private PoiStyles poiStyles = null;

  public void init(String _fileName) {

    excelHash = new Hashtable();

    XMLProperties properties = null;

    properties = new XMLProperties(_fileName);

    Element element = properties.getElement();
    Element headelement = null;
    Element bodyelement = null;
    Element cellelement = null;

    List list = element.getChildren(); //��ȡ����Excel�ĸ�ʽ����Ԫ��
    List headList = null;
    List bodyList = null;
    List cellList = null;

    PoiStyle poiStyle = null;

    //�����Excel�ĸ�ʽ����ϸ����
    for (int i = 0; i < list.size(); i++) {
      element = (Element) list.get(i);

      PoiExcelModel poiExcelModel = null;

      if (!element.getChild("name").getText().trim().equals("")) {
        poiExcelModel = new PoiExcelModel(element.getChild("name").getText().
                                          trim());
        excelHash.put(element.getChild("name").getText().trim(), poiExcelModel);
      }

      //��ȡ��ݷ�װ�����
      if (!element.getChild("dbclass").getText().trim().equals("")) {
        poiExcelModel.setDbClass(element.getChild("dbclass").getText().trim());
      }

      //����ͷ��ʽ��Ϣ
      headList = element.getChild("head").getChildren("row");

      for (int j = 0; j < headList.size(); j++) {
        headelement = (Element) headList.get(j);

        cellList = headelement.getChildren("col");

        for (int k = 0; k < cellList.size(); k++) {

          cellelement = (Element) cellList.get(k);
          poiStyle = creatPoiStyle(cellelement);
          poiExcelModel.setHeadInfo(poiStyle);
        }
      }

      //��ȡ���������Ϣ�ĳ�ʼ����
      String startRow = element.getChild("body").getChild("startrow").getText().trim();
      poiExcelModel.setStartRow(startRow);

      //��ȡ���������Ϣ��һ�����ռ������
      String cellCount = element.getChild("body").getChild("cellcount").getText().trim();
      poiExcelModel.setCellCount(cellCount);

      //���������ʽ��Ϣ
      bodyList = element.getChild("body").getChildren("row");

      for (int j = 0; j < bodyList.size(); j++) {
        bodyelement = (Element) bodyList.get(j);

        cellList = bodyelement.getChildren("col");

        for (int k = 0; k < cellList.size(); k++) {

          cellelement = (Element) cellList.get(k);
          poiStyle = creatPoiStyle(cellelement);
          poiExcelModel.setBodyInfo(poiStyle);
        }

      }

      excelHash.put(poiExcelModel.getExcelName(), poiExcelModel);
    }

  }

  private PoiStyle creatPoiStyle(Element _element) {

    PoiStyle poiStyle = new PoiStyle();
    Element perporElement = null;

    perporElement = _element.getChild("title");
    poiStyle.setTitle(perporElement == null ? "" : perporElement.getText());

    perporElement = _element.getChild("size");
    poiStyle.setSize(perporElement == null ? "" : perporElement.getText());

    perporElement = _element.getChild("font");
    poiStyle.setFont(perporElement == null ? "" : perporElement.getText());

    perporElement = _element.getChild("color");
    poiStyle.setColor(perporElement == null ? 0 :
                      Integer.parseInt(perporElement.getText()));

    perporElement = _element.getChild("bold");
    poiStyle.setBold(perporElement == null ? "" : perporElement.getText());

    perporElement = _element.getChild("xport");
    poiStyle.setXPort(perporElement == null ? 0 :
                         Integer.parseInt(perporElement.getText()));

    perporElement = _element.getChild("yport");
    poiStyle.setYPort(perporElement == null ? 0 :
                         Integer.parseInt(perporElement.getText()));


    perporElement = _element.getChild("width");
    poiStyle.setWidth(perporElement == null ? 0 :
                         Integer.parseInt(perporElement.getText()));

    perporElement = _element.getChild("higth");
    poiStyle.setHigth(perporElement == null ? 0 :
                         Integer.parseInt(perporElement.getText()));

    perporElement = _element.getChild("propert");
    poiStyle.setProper(perporElement == null ? "" : perporElement.getText());

    return poiStyle;
  }

  public PoiExcelModel getPoiExcelModel(String _excelName) {
    PoiExcelModel poiExcelModel = (PoiExcelModel) excelHash.get(_excelName);
    return poiExcelModel;
  }

}


