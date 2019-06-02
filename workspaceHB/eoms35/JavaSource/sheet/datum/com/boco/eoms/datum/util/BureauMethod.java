package com.boco.eoms.datum.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.PropertyFile;
import com.boco.eoms.datum.dao.hibernate.TawBureaudataBasicDaoHibernate;
import com.boco.eoms.datum.dao.hibernate.TawBureaudataCityzoneHibernate;
import com.boco.eoms.datum.dao.hibernate.TawBureaudataHlrDaoHibernate;
import com.boco.eoms.datum.model.TawBureaudataBasic;
import com.boco.eoms.datum.model.TawBureaudataCityzone;
import com.boco.eoms.datum.model.TawBureaudataHlr;
import com.boco.eoms.datum.service.ITawBureaudataBasicDAOManager;
import com.boco.eoms.datum.service.ITawBureaudataCityzoneManager;
import com.boco.eoms.datum.service.ITawBureaudataHlrDAOManager;

import java.io.*;
import com.boco.RW_Excel.excel.Workbook;
import com.boco.RW_Excel.excel.Sheet;
import com.boco.RW_Excel.excel.Cell;
import com.boco.RW_Excel.excel.read.biff.BiffException;
import java.util.*;

public class BureauMethod {
  static PropertyFile PROP = PropertyFile.getInstance();
  public BureauMethod() {
  }

  /**
   * Added by Matao 2007-11-09
   * 添加了条数的限制，最大条数为500
   * @param ips InputStream
   * @param sheetId int
   * @return String
   */
  public String isStandard(InputStream ips, int sheetId) {
    String resultStr = "";
    if (ips != null) {
      int maxListSize = 50000;
      Workbook workBook = null;
      Cell[] row = null;
      try {
        workBook = Workbook.getWorkbook(ips);
        int sheetNum = 0; //默认只处理第一个工作薄
        Sheet sheet = workBook.getSheet(sheetNum);
        int size = sheet.getRows();
        System.out.println("size:" + size);
        if (sheetId == 1) { //未确定HLR归属的号段
          size -= 3;
          row = sheet.getRow(2);
          if (!(row[3].getContents().matches("\\d\\d\\d\\d"))) {
            resultStr = "《未确定HLR归属的号段》模版文件中格式不对";
          }
        } else if (sheetId == 2) { //变更批复表
          row = sheet.getRow(2);
          size -= 3;
          if ((row[4].getContents().matches("\\d\\d\\d\\d")) || !(row[5].getContents().matches("\\d\\d\\d\\d"))) {
            resultStr = "《变更批复表》模版文件中格式不对";
          }
        } else if (sheetId == 3) { //现有HLR和号段的对应
          row = sheet.getRow(2);
          size -=2;
          if (row.length < 8 || row[1].getContents().trim().equals("") || row[2].getContents().trim().equals("") || row[5].getContents().trim().equals("") || row[7].getContents().trim().equals("") ) {
            resultStr = "《现有HLR和号段的对应关系》模版文件中格式不对";
          }
        } else if (sheetId == 4) { //HLR列表
          row = sheet.getRow(0);
          size -= 1;
          if (row.length != 3) {
            resultStr = "《HLR列表》模版文件中格式不对";
          }
        }
        if(size > maxListSize){
            resultStr = "模版文件中记录的条数不能大于"+ maxListSize +"，如果大于"+ maxListSize +"，请分批导入!";
          }
      } catch (Exception ex) {
        resultStr = "所上传的文件无法正确读取，请检查是否是Excel模版文件!";
        ex.printStackTrace();
      }
    }
    return resultStr;
  }


  /**
   * Added by Matao 2007-11-09
   * 检查城市是否正确时，先把所有的城市信息PO以城市名称为Key放入到Map中，然后用城市区号从Map中取出城市信息PO
   * @param ips InputStream
   * @param bureauno String
   * @throws Exception 如果出现区号不匹配或号码段数据不准确的情况就会抛出异常
   * @return List 如果在读取一切顺利就把分析出来的数据放入到List中
   */
  public List getNobelonghlr(InputStream ips, String bureauId, Set citySetOutput) throws Exception {
    ArrayList list = new ArrayList();
    TawBureaudataCityzone cityPO = null;
    String cellcontents[] = null;
    bureauId = bureauId + ";";
    if(citySetOutput == null){
      citySetOutput = new HashSet();
    }
    if (ips != null) { //文件存在
      Workbook workBook = Workbook.getWorkbook(ips);
      int sheetNum = 0; //默认只处理第一个工作薄
      Sheet sheet = workBook.getSheet(sheetNum);
      int rows = sheet.getRows(); //总行数
      Cell[] celltitle = null;
      Cell[] cell = null;
      int endcol = 0; //表头中非数字列的位置
      //开始开始处理表头,取到表头的第一个非数字列的列号
      celltitle = sheet.getRow(2);
      for (int i = 3; i < celltitle.length; i++) {
        if (!(celltitle[i].getContents()).matches("\\d\\d\\d\\d")) {
          break;
        }
        endcol = i;
      }
      ITawBureaudataCityzoneManager mgr = (ITawBureaudataCityzoneManager) ApplicationContextHolder
		.getInstance().getBean("iTawBureaudataCityzoneManager");
      ITawBureaudataBasicDAOManager base = (ITawBureaudataBasicDAOManager) ApplicationContextHolder
		.getInstance().getBean("iTawBureaudataBasicDAOManager");
      Map cityMap = mgr.getAllCityIntoMapKeyZoneNum();
      Set bdBasicSet = base.getAllBureaudataBasic();
      //开始处理数据,从第四行开始处理数据，处理数据的列的有效范围为：1~endcol
      for (int i = 3; i < rows; i++) {
        cell = sheet.getRow(i); //
        if(cell[0].getContents().indexOf("湖北")<0){
          continue;
        }
        cityPO = (TawBureaudataCityzone)cityMap.get(new Integer(cell[2].getContents()).toString());
        citySetOutput.add(cityPO);
        if (cityPO == null) { //没有找到相应的城市
          String errorInfo = "在模版文件中单元格<b>" + StaticMethod.getExcelPosition(i+1,3) + "</b>的城市区号[" + cell[2].getContents() + "]不存在，请检查";
          throw new Exception(errorInfo);
        } else {
          for (int j = 3; j <= endcol; j++) {
              if (!cell[j].getContents().equals("")) {
                //取到开始和结束的号段
                cellcontents = (cell[j].getContents()).split(",");
                for (int k = 0; k < cellcontents.length; k++) {
                  //对形如：767-779进行细分
                  String[] strArrTmp = cellcontents[k].split("-");
                  int beginSegment = 0; int endSegment = 0;
                  try {
                    beginSegment = Integer.parseInt(celltitle[j].getContents() + strArrTmp[0]);
                    endSegment = 0;
                    if (strArrTmp.length < 2) {
                      endSegment = beginSegment;
                    } else {
                      endSegment = Integer.parseInt(celltitle[j].getContents() + strArrTmp[1]);
                    }
                  } catch (Exception ex) {
                    ex.printStackTrace();
                    String errorInfo = "在模版文件中单元格<b>" + StaticMethod.getExcelPosition(i+1,j+1) + "</b>的数据[" + cell[j].getContents() + "]错误，请检查";
                    throw new Exception(errorInfo);
                  }
                  if(beginSegment > endSegment){
                    int dataTmp = beginSegment;
                    beginSegment = endSegment;
                    endSegment = dataTmp;
                  }
                  for(int segTmp = beginSegment; segTmp <= endSegment; segTmp++){
                    if(bdBasicSet.contains(new Integer(segTmp))){
                      String errorInfo = "号段值:<b>" + segTmp + "</b>已存在,与模版文件中单元格<b>" + StaticMethod.getExcelPosition(i+1,j+1) + "</b>的数据[" + cell[j].getContents() + "]重复，请检查!";
                      throw new Exception(errorInfo);
                    }
                    TawBureaudataBasic nobelongPO = new TawBureaudataBasic();
                    nobelongPO.setPrezonenum(new Integer(cityPO.getZonenum()));
                    nobelongPO.setZonenum(new Integer(cityPO.getZonenum()));
                    nobelongPO.setBelongflag(new Integer(0));
                    nobelongPO.setSegmentid(new Integer(segTmp));
                    nobelongPO.setNewbureauid(bureauId);
                    list.add(nobelongPO);
                  }
                }
              }
          }
        }
      }
      workBook.close();
    }
    return list;
  }

  /**
   * Added by Matao 2007-11-09
   * 从《BOSS帐户号码省内调整批复表.xls》和《智能网帐户号码省内调整批复表.xls》文件中取出数据放到List中
   * 检查城市区号是否正确时，先把所有的城市信息PO以城市名称为Key放入到Map中，然后用城市名称从Map中取出城市信息PO
   * @param ips InputStream
   * @param bureauno String
   * @throws Exception 如果出现区号不匹配或号码段数据不准确的情况就会抛出异常
   * @return List 如果在读取一切顺利就把分析出来的数据放入到List中
   */
  public List getApprove(InputStream ips, String bureauId, Set citySetOutput) throws Exception,
      BiffException {
    ArrayList list = new ArrayList();
    TawBureaudataCityzone preCityPO = null;
   TawBureaudataCityzone cityPO = null;
   String cellcontents[] = null;
   bureauId = bureauId + ";";
   if(citySetOutput == null){
     citySetOutput = new HashSet();
   }
   if (ips != null) { //文件存在
     Workbook workBook = Workbook.getWorkbook(ips);
     int sheetNum = 0; //默认只处理第一个工作薄
     Sheet sheet = workBook.getSheet(sheetNum);
     int rows = sheet.getRows(); //总行数
     Cell[] celltitle = null;
     Cell[] cell = null;
     int endcol = 0; //表头中非数字列的位置
     //开始开始处理表头,取到表头的第一个非数字列的列号
     celltitle = sheet.getRow(2);
     for (int i = 5; i < celltitle.length; i++) {
       if (!(celltitle[i].getContents()).matches("\\d\\d\\d\\d")) {
         break;
       }
       endcol = i;
     }
     ITawBureaudataCityzoneManager mgr = (ITawBureaudataCityzoneManager) ApplicationContextHolder
		.getInstance().getBean("iTawBureaudataCityzoneManager");
     Map cityMap = mgr.getAllCityIntoMapKeyZoneNum();
     //开始处理数据,从第四行开始处理数据，处理数据的列的有效范围为：1~endcol
     for (int i = 3; i < rows; i++) {
       cell = sheet.getRow(i); //
       if(cell[0].getContents().indexOf("湖北")<0){
         continue;
       }
       preCityPO = (TawBureaudataCityzone)cityMap.get(cell[2].getContents().toString());
       cityPO = (TawBureaudataCityzone)cityMap.get(cell[4].getContents().toString());
       citySetOutput.add(cityPO);
       if (preCityPO == null) { //没有找到相应的城市
         String errorInfo = "在模版文件中<b>" + StaticMethod.getExcelPosition(i+1,3) + "</b>的城市区号[" + cell[2].getContents() + "]不存在，请检查";
         throw new Exception(errorInfo);
       } else if (cityPO == null) { //没有找到相应的城市
         String errorInfo = "在模版文件中<b>" + StaticMethod.getExcelPosition(i+1,5) + "</b>的城市区号[" + cell[4].getContents() + "]不存在，请检查";
         throw new Exception(errorInfo);
       } else {
         for (int j = 5; j <= endcol; j++) {
           try {
             if (!cell[j].getContents().equalsIgnoreCase("")) {
               //取到开始和结束的号段
               cellcontents = (cell[j].getContents()).split(",");
               for (int k = 0; k < cellcontents.length; k++) {
                 //对形如：767-779进行细分
                 String[] strArrTmp = cellcontents[k].split("-");
                 int beginSegment = Integer.parseInt(celltitle[j].getContents() + strArrTmp[0]);
                 int endSegment = 0;
                 if(strArrTmp.length < 2){
                   endSegment = beginSegment;
                 }else{
                   endSegment = Integer.parseInt(celltitle[j].getContents() + strArrTmp[1]);
                 }
                 for(int segTmp = beginSegment; segTmp <= endSegment; segTmp++){
                   TawBureaudataBasic nobelongPO = new TawBureaudataBasic();
                   nobelongPO.setPrezonenum(new Integer(cityPO.getZonenum()));
                   nobelongPO.setZonenum(new Integer(cityPO.getZonenum()));
                   nobelongPO.setBelongflag(new Integer(0));
                   nobelongPO.setSegmentid(new Integer(segTmp));
                   nobelongPO.setNewbureauid(bureauId);
                   list.add(nobelongPO);
                 }
               }
             }
           } catch (Exception ex) {
             ex.printStackTrace();
             String errorInfo = "在模版文件中<b>" + StaticMethod.getExcelPosition(i+1,j+1) + "</b>的数据[" + cell[j].getContents() + "]错误，请检查";
             throw new Exception(errorInfo);
           }
         }
       }
     }
     workBook.close();
   }
   return list;
  }

  /**
   * Added by Matao 2007-11-09
   * 从《全省号段归属汇总表.xls》文件中取出数据放到List中
   * 检查城市区号是否正确时，先把所有的城市信息PO以城市名称为Key放入到Map中，然后用城市名称从Map中取出城市信息PO
   * @param ips InputStream
   * @param bureauno String
   * @throws Exception 如果出现区号不匹配或号码段数据不准确的情况就会抛出异常
   * @return List 如果在读取一切顺利就把分析出来的数据放入到List中
   */
  public List getBureaudataHLR(InputStream ips) throws Exception,
      BiffException {
    ArrayList list = new ArrayList();
    if (ips != null) { //文件存在
      Workbook workBook = Workbook.getWorkbook(ips);
      int sheetNum = 0; //默认只处理第一个工作薄
      Sheet sheet = workBook.getSheet(sheetNum);
      int rows = sheet.getRows(); //总行数
      Cell[] cell = null;
      
      ITawBureaudataCityzoneManager mgr = (ITawBureaudataCityzoneManager) ApplicationContextHolder
		.getInstance().getBean("iTawBureaudataCityzoneManager");
      ITawBureaudataHlrDAOManager hlr = (ITawBureaudataHlrDAOManager) ApplicationContextHolder
		.getInstance().getBean("iTawBureaudataHlrDAOManager");
      Map cityMap = mgr.getAllCityIntoMapKeyCityName();
      Map hlrMap = hlr.getAllHLRIntoMapKeySignalId();
      //开始处理数据,从第四行开始处理数据，处理数据的列的有效范围为：1~endcol
      for (int i = 2; i < rows; i++) {
        cell = sheet.getRow(i); //
        String cityName = StaticMethod.null2String(cell[1].getContents());
        String hlrSignalId = StaticMethod.null2String(cell[5].getContents());
        if(cityName.equals("") || hlrSignalId.equals("")){
          continue;
        }
        TawBureaudataCityzone cityPO = (TawBureaudataCityzone)cityMap.get(cityName);
        TawBureaudataHlr hlrPo = (TawBureaudataHlr)hlrMap.get(hlrSignalId.replace('.','-'));
        if (cityPO == null) { //没有找到相应的城市
          String errorInfo = "在模版文件中<b>" + StaticMethod.getExcelPosition(i+1,2) + "</b>的城市[" + cityName + "]不存在，请检查";
          throw new Exception(errorInfo);
        }else if (hlrPo == null) { //没有找到相应的HLR信息
          String errorInfo = "在模版文件中<b>" + StaticMethod.getExcelPosition(i+1,6) + "</b>的HLR[" + hlrSignalId + "]不存在，请检查";
          throw new Exception(errorInfo);
        } else {
          try {
            int segment = Integer.parseInt(cell[7].getContents().substring(2));
            String remark = StaticMethod.null2String((cell.length >8)?cell[8].getContents()+";":"");
            
            TawBureaudataBasic nobelongPO = new TawBureaudataBasic();
            nobelongPO.setSegmentid(new Integer(segment));
            nobelongPO.setBelongflag(new Integer(1));
            nobelongPO.setNewbureauid(remark);
            nobelongPO.setZonenum(new Integer(cityPO.getZonenum()));
            nobelongPO.setHlrsignalid(hlrPo.getHlrsignalid());
            list.add(nobelongPO);
          } catch (Exception ex) {
            ex.printStackTrace();
            String errorInfo = "在模版文件中<b>" + StaticMethod.getExcelPosition(i+1,8) + "</b>的号段[" + cell[7].getContents() + "]格式不正确，请检查";
            throw new Exception(errorInfo);
          }
        }
      }
      workBook.close();
    }
    return list;
  }

}
