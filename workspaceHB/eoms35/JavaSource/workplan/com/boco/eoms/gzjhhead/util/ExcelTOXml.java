package com.boco.eoms.gzjhhead.util;

import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hssf.usermodel.*;

import java.io.*;

public class ExcelTOXml {
  public ExcelTOXml() {
  }

  public String getExcelData(String _excelFile,
                             int _sheetAt, int beginx, int endx, int beginy,
                             int endy) throws
      Exception {

    StringBuffer xmlString = new StringBuffer();

    POIFSFileSystem fs = null;
    HSSFWorkbook wb = null;

    HSSFSheet sheet = null;
    HSSFRow row = null;
    HSSFCell cell = null;

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
    sheet = wb.getSheetAt(_sheetAt);
    int index = 0;
    for (int i = beginy; i <=endy; i++) {
      row = sheet.getRow(i);
      for (int j = beginx; j <=endx; j++) {
        index++;
        cell = row.getCell( (short) j);
        cell.setEncoding(cell.ENCODING_UTF_16);
        if (cell.getCellType() == cell.CELL_TYPE_STRING) { 
          xmlString.append("<element value=\"" + cell.getStringCellValue() +
                           "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
                           index +
                           "\" align=\"center\" valign=\"middle\"></element>\n");
        }
        else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) { 
          xmlString.append("<element value=\"" + cell.getNumericCellValue() +
                           "\" rows=\"1\" cols=\"1\"    newline=\"0\" index=\"" +
                           index +
                           "\" align=\"center\" valign=\"middle\"></element>\n");
        }
        else if (cell.getCellType() == cell.CELL_TYPE_BLANK) { 
          xmlString.append(
              "<element value=\"\" dbname=\"one\" rows=\"1\" cols=\"1\"  x=\"" +
              j + "\" y=\"" + i + "\"");
          xmlString.append(" type=\"1\" newline=\"0\" index=\"" + index + "\"");
          xmlString.append(" align=\"center\" valign=\"middle\" formWidth=\"20\" formHeight=\"20\"></element>\n");

        }
      }

    }
    System.out.print(xmlString.toString());
    return xmlString.toString();
  }

public static void main(String args[]){
  ExcelTOXml excelTOXml = new ExcelTOXml();
  try {
      excelTOXml.getExcelData("test.xls",0,2,5,4,30);
  }
  catch (Exception ex) {
    ex.printStackTrace();
  }

}
}
