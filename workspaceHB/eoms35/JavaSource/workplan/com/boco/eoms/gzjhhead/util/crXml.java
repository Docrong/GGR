package com.boco.eoms.gzjhhead.util;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hssf.usermodel.*;

import java.io.*;


public class crXml {
  public crXml() {
  }

  public String getExcelData(String _excelFile,
                             int _sheetAt, int beginx, int endx, int beginy,
                             int endy,String xmlFileName,int _index) throws
    Exception {



    StringBuffer xmlString = new StringBuffer(); //执行内容对应的excel数据存放id
    POIFSFileSystem fs = null;
    HSSFWorkbook wb = null;

    //读取文件信息
    HSSFSheet sheet = null;
    HSSFRow row = null;
    HSSFCell cell = null;

//获取excel文件
    try {
      fs = new POIFSFileSystem(new FileInputStream(_excelFile));
      wb = new HSSFWorkbook(fs);
    }
    catch (java.io.FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("文件没有找到");
    }
    catch (java.io.IOException e) {
      e.printStackTrace();
      System.out.println("读取文件错误");
    }
    //得到excel中页片对象
    sheet = wb.getSheetAt(_sheetAt);
    int index = _index;
    int dbname = 0;
    xmlString.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
    xmlString.append("\n");
    for (int i = beginy; i <= endy; i++) {
      row = sheet.getRow(i);
      for (int j = beginx; j <= endx; j++) {
//        if(i==4&&j==0){
//          index++;
//          xmlString.append("<element value=\"" + "需要改写"+
//          "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
//          index +
//          "\" align=\"center\" valign=\"middle\"></element>\n");
//          index++;
//        cell = row.getCell( (short) j);
//        if (cell != null) {
//          cell.setEncoding(cell.ENCODING_UTF_16);
//          if (cell.getCellType() == cell.CELL_TYPE_STRING) { //字符型数据
//            xmlString.append("<element value=\"" + cell.getStringCellValue() +
//                "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
//                index +
//                "\" align=\"center\" valign=\"middle\"></element>\n");
//          }
//          else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) { //数字型数据
//            xmlString.append("<element value=\"" + cell.getNumericCellValue() +
//                "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
//                index +
//                "\" align=\"center\" valign=\"middle\"></element>\n");
//          }
//          else if (cell.getCellType() == cell.CELL_TYPE_BLANK) { //空
//            dbname++;
//            xmlString.append(
//                "<element value=\"\" dbname=\"column_" + dbname +
//                "\" rows=\"1\" cols=\"1\"  x=\"" +
//                j + "\" y=\"" + i + "\"");
//            xmlString.append(" type=\"1\" newline=\"0\" index=\"" + index +
//                             "\"");
//            xmlString.append(" align=\"center\" valign=\"middle\" formWidth=\"20\" formHeight=\"20\"></element>\n");
//
//            }
//          }
//
//
//        }
//
//        if(i==9&&j==0){
//          index++;
//          xmlString.append("<element value=\"" + "需要改写"+
//          "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
//          index +
//          "\" align=\"center\" valign=\"middle\"></element>\n");
//
//          index++;
//        cell = row.getCell( (short) j);
//        if (cell != null) {
//          cell.setEncoding(cell.ENCODING_UTF_16);
//          if (cell.getCellType() == cell.CELL_TYPE_STRING) { //字符型数据
//            xmlString.append("<element value=\"" + cell.getStringCellValue() +
//                "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
//                index +
//                "\" align=\"center\" valign=\"middle\"></element>\n");
//          }
//          else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) { //数字型数据
//            xmlString.append("<element value=\"" + cell.getNumericCellValue() +
//                "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
//                index +
//                "\" align=\"center\" valign=\"middle\"></element>\n");
//          }
//          else if (cell.getCellType() == cell.CELL_TYPE_BLANK) { //空
//            dbname++;
//            xmlString.append(
//                "<element value=\"\" dbname=\"column_" + dbname +
//                "\" rows=\"1\" cols=\"1\"  x=\"" +
//                j + "\" y=\"" + i + "\"");
//            xmlString.append(" type=\"1\" newline=\"0\" index=\"" + index +
//                             "\"");
//            xmlString.append(" align=\"center\" valign=\"middle\" formWidth=\"20\" formHeight=\"20\"></element>\n");
//
//            }
//          }
//
//
//        }
//
//        if(i==13&&j==0){
//          index++;
//          xmlString.append("<element value=\"" + "需要改写"+
//          "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
//          index +
//          "\" align=\"center\" valign=\"middle\"></element>\n");
//
//          index++;
//        cell = row.getCell( (short) j);
//        if (cell != null) {
//          cell.setEncoding(cell.ENCODING_UTF_16);
//          if (cell.getCellType() == cell.CELL_TYPE_STRING) { //字符型数据
//            xmlString.append("<element value=\"" + cell.getStringCellValue() +
//                "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
//                index +
//                "\" align=\"center\" valign=\"middle\"></element>\n");
//          }
//          else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) { //数字型数据
//            xmlString.append("<element value=\"" + cell.getNumericCellValue() +
//                "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
//                index +
//                "\" align=\"center\" valign=\"middle\"></element>\n");
//          }
//          else if (cell.getCellType() == cell.CELL_TYPE_BLANK) { //空
//            dbname++;
//            xmlString.append(
//                "<element value=\"\" dbname=\"column_" + dbname +
//                "\" rows=\"1\" cols=\"1\"  x=\"" +
//                j + "\" y=\"" + i + "\"");
//            xmlString.append(" type=\"1\" newline=\"0\" index=\"" + index +
//                             "\"");
//            xmlString.append(" align=\"center\" valign=\"middle\" formWidth=\"20\" formHeight=\"20\"></element>\n");
//
//            }
//          }
//
//
//        }
//
//
//        if(i==19&&j==0){
//          index++;
//          xmlString.append("<element value=\"" + "需要改写"+
//          "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
//          index +
//          "\" align=\"center\" valign=\"middle\"></element>\n");
//
//          index++;
//        cell = row.getCell( (short) j);
//        if (cell != null) {
//          cell.setEncoding(cell.ENCODING_UTF_16);
//          if (cell.getCellType() == cell.CELL_TYPE_STRING) { //字符型数据
//            xmlString.append("<element value=\"" + cell.getStringCellValue() +
//                "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
//                index +
//                "\" align=\"center\" valign=\"middle\"></element>\n");
//          }
//          else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) { //数字型数据
//            xmlString.append("<element value=\"" + cell.getNumericCellValue() +
//                "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
//                index +
//                "\" align=\"center\" valign=\"middle\"></element>\n");
//          }
//          else if (cell.getCellType() == cell.CELL_TYPE_BLANK) { //空
//            dbname++;
//            xmlString.append(
//                "<element value=\"\" dbname=\"column_" + dbname +
//                "\" rows=\"1\" cols=\"1\"  x=\"" +
//                j + "\" y=\"" + i + "\"");
//            xmlString.append(" type=\"1\" newline=\"0\" index=\"" + index +
//                             "\"");
//            xmlString.append(" align=\"center\" valign=\"middle\" formWidth=\"20\" formHeight=\"20\"></element>\n");
//
//            }
//          }
//
//
//        }

        if(i==21&&j==0){
          index++;
          xmlString.append("<element value=\"" + "需要改写"+
          "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
          index +
          "\" align=\"center\" valign=\"middle\"></element>\n");

          index++;
        cell = row.getCell( (short) j);
        if (cell != null) {
          cell.setEncoding(cell.ENCODING_UTF_16);
          if (cell.getCellType() == cell.CELL_TYPE_STRING) { //字符型数据
            xmlString.append("<element value=\"" + cell.getStringCellValue() +
                "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
                index +
                "\" align=\"center\" valign=\"middle\"></element>\n");
          }
          else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) { //数字型数据
            xmlString.append("<element value=\"" + cell.getNumericCellValue() +
                "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
                index +
                "\" align=\"center\" valign=\"middle\"></element>\n");
          }
          else if (cell.getCellType() == cell.CELL_TYPE_BLANK) { //空
            dbname++;
            xmlString.append(
                "<element value=\"\" dbname=\"column_" + dbname +
                "\" rows=\"1\" cols=\"1\"  x=\"" +
                j + "\" y=\"" + i + "\"");
            xmlString.append(" type=\"1\" newline=\"0\" index=\"" + index +
                             "\"");
            xmlString.append(" align=\"center\" valign=\"middle\" formWidth=\"20\" formHeight=\"20\"></element>\n");

            }
          }
        }

        else{
        index++;
        cell = row.getCell( (short) j);
        if (cell != null) {
          cell.setEncoding(cell.ENCODING_UTF_16);
          if (cell.getCellType() == cell.CELL_TYPE_STRING) { //字符型数据
            xmlString.append("<element value=\"" + cell.getStringCellValue() +
                "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
                index +
                "\" align=\"center\" valign=\"middle\"></element>\n");
          }
          else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) { //数字型数据
            xmlString.append("<element value=\"" + cell.getNumericCellValue() +
                "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
                index +
                "\" align=\"center\" valign=\"middle\"></element>\n");
          }
          else if (cell.getCellType() == cell.CELL_TYPE_BLANK) { //空
            dbname++;
            xmlString.append(
                "<element value=\"\" dbname=\"column_" + dbname +
                "\" rows=\"1\" cols=\"1\"  x=\"" +
                j + "\" y=\"" + i + "\"");
            xmlString.append(" type=\"1\" newline=\"0\" index=\"" + index +
                             "\"");
            xmlString.append(" align=\"center\" valign=\"middle\" formWidth=\"20\" formHeight=\"20\"></element>\n");

            }
          }
        }
      }
    }

    DataOutputStream output = new DataOutputStream
    (new FileOutputStream(new File(xmlFileName)));

    output.writeBytes(new String(xmlString.toString().getBytes("GB2312"),
                                  "ISO8859-1"));
    output.close();

    System.out.print(xmlString.toString());
    return xmlString.toString();
  }

  public static void main(String args[]){
  crXml crxml = new crXml();
  try {
      crxml.getExcelData("test.xls",0,0,5,4,30,"sbyxztjcjlb.xml",12);
  }
  catch (Exception ex) {
    ex.printStackTrace();
  }

}


}
