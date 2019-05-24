package com.boco.eoms.km.excelmanage;

/**
 * <p>Title: wϰ</p>
 * <p>Description: wϰ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.util.Region;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

public class PoiReadExcel {

  public void ReadExcel() {

    POIFSFileSystem fs = null;
    HSSFWorkbook wb = null;
    HSSFSheet sheet = null;
    HSSFHeader header = null;
    HSSFFooter footer = null;
    HSSFRow row = null;
    HSSFCell cell = null;

    Region region = null;

    Iterator iteratorRow = null;
    Iterator iteratorCell = null;
    int cellLastInt = 0;
    int regionInt = 0;

    try {
      fs = new POIFSFileSystem(new FileInputStream("D:\\test.xls"));
      wb = new HSSFWorkbook(fs);
    }
    catch (java.io.FileNotFoundException e) {
      System.out.println("�ļ�û���ҵ�");
      System.exit(0);
    }
    catch (java.io.IOException e) {
      System.out.println("��ȡ�ļ�����");
      System.exit(0);
    }

    int sheetCount = wb.getNumberOfSheets(); //sheet����

    for (int i = 0; i < sheetCount; i++) {
      sheet = wb.getSheetAt(i);

      iteratorRow = sheet.rowIterator();

      while (iteratorRow.hasNext()) {
        row = (HSSFRow) iteratorRow.next();
        pl("�кţ�" + row.getRowNum());

        cellLastInt = row.getLastCellNum();

        for (int j = 0; j < cellLastInt; j++) {

          cell = row.getCell( (short) j);

          if (cell != null) {
            p("�кţ�" + cell.getCellNum());
            switch (cell.getCellType()) {
              case HSSFCell.CELL_TYPE_STRING:
                pl(cell.getStringCellValue());
                break;
              case HSSFCell.CELL_TYPE_NUMERIC:
                pl(cell.getNumericCellValue());
                break;
              case HSSFCell.CELL_TYPE_BLANK:
                pl("��");
                break;
            }
          }
        }
      }

      regionInt = sheet.getNumMergedRegions();
System.out.println("regionInt = " + regionInt);
      for(int k = 0; k < regionInt; k++){
        region = sheet.getMergedRegionAt(k);

        pl("��ʽ" + k + "(" + region.getRowFrom() + "," + region.getRowTo() +
           "," +
           region.getColumnFrom() + "," + region.getColumnTo() + ")");

        if(region.getRowFrom() !=  region.getRowTo() || region.getColumnFrom() !=  region.getColumnTo()){


        }
      }

    }

    try {
      FileOutputStream fileOut = new FileOutputStream("D:\\test.xls");
      wb.write(fileOut);
      fileOut.close();
    }
    catch (java.io.FileNotFoundException e) {
      System.out.println("�ļ�û���ҵ�");
      System.exit(0);
    }
    catch (java.io.IOException e) {
      System.out.println("�����ļ�����");
      System.exit(0);
    }
  }

  public void pl(String _info) {
    System.out.println(_info);
  }

  public void pl(int _info) {
    System.out.println(_info);
  }

  public void pl(double _info) {
    System.out.println(_info);
  }

  public void p(String _info) {
    System.out.print(_info);
  }

  public void p(int _info) {
    System.out.print(_info);
  }

  public void p(double _info) {
    System.out.print(_info);
  }


  public static void main(String[] args) {
    PoiReadExcel poiReadExcel = new PoiReadExcel();
    poiReadExcel.ReadExcel();
  }
}
