package com.boco.eoms.km.excelmanage;

/**
 * <p>Title: Excel�ļ����POI APIʹ��wϰ</p>
 * <p>Description: Excel�ļ�������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.util.List;
import java.io.File;

public class PoiExcel {

  public static PoiExcelModels poiExcelModels = null;

  public static String configFile = null;

  public static void init(String _configFile){
    poiExcelModels = new PoiExcelModels(_configFile + File.separator + "excelconfig.xml");
    setConfig(_configFile);
  }
//LIQUAN ADD
  public PoiExcel(String _configFile){
        poiExcelModels = new PoiExcelModels(_configFile + File.separator + "excelconfig.xml");
        setConfig(_configFile);
      }
   //END

  public static String getPoiExcel(String _poiStr,List _dbList) {
    PoiExcelModel poiExcelModel = poiExcelModels.getPoiExcelModel(_poiStr);
    return poiExcelModel.creatExcel(_dbList);
  }

  public static void setConfig(String _configFile){
    String temp = null;
    temp = _configFile.substring(0,_configFile.lastIndexOf("/"));
    temp = temp.substring(0,temp.lastIndexOf("/")) + File.separator + "excelfile";

    configFile = temp;
  }
}
