 package com.boco.eoms.commons.statistic.base.excelutil.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.contrib.HSSFCellUtil;
import org.apache.poi.hssf.util.Region;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.boco.eoms.commons.statistic.base.excelutil.mgr.DyTableInfos;
import com.boco.eoms.commons.statistic.base.excelutil.mgr.impl.DyTableInfo;
import com.boco.eoms.commons.statistic.base.util.Constants;

/**
 * Excel操作公共方法，支持复制行，复制区域等高级操作。
 * 
 * 转载自http://blog.csdn.net/hecal/archive/2008/12/16/3530944.aspx
 * 
 * lizhenyou在此基础上修改
 *
 */
public class CommonExcel {
    private boolean bOpened;
    private HSSFWorkbook objWorkbook;
    private HSSFSheet objSheet;
    private int[] rc = new int[2];

    /**
     * 操作Excel基本工具类
     */
    public CommonExcel() {
        bOpened = false;
        objWorkbook = null;
        objSheet = null;
    }
    
    /**
     * 操作Excel基本工具类
     * @param objWorkbook excel对象
     * @param objSheet  excel中sheet对象
     */
    public CommonExcel(HSSFWorkbook objWorkbook,HSSFSheet objSheet) {
        this.bOpened = true;
        this.objWorkbook = objWorkbook;
        this.objSheet = objSheet;
    }

    /**
     * 获取excel对象
     * @return
     */
    public HSSFWorkbook getWorkBook() {
     return objWorkbook;
    }
    /**
     * Excel Open
     * @param tempFileName excel绝对路径:  
     */
    public void open(String tempFileName) throws Exception {
        if (bOpened == true) {
            throw new Exception("Excel is opening!");
        } else {
            FileInputStream fs = new FileInputStream(tempFileName);
            objWorkbook = new HSSFWorkbook(fs);
            objSheet = objWorkbook.getSheetAt(0);
            
            bOpened = true;
        }
    }
    
    /**
     * Excel Open
     * @param tempFileName Excel Stream:  
     */
    public void open(InputStream is) throws Exception {
        if (bOpened == true) {
            throw new Exception("Excel is opening!");
        } else {
            objWorkbook = new HSSFWorkbook(is);
            objSheet = objWorkbook.getSheetAt(0);
            
            bOpened = true;
        }
    }


    /**
     * save file
     * @param fileName excel绝对路径: 
     */
    public void save(String fileName) throws Exception {
        if (bOpened == true) {
            FileOutputStream fs = new FileOutputStream(fileName);
            objWorkbook.write(fs);
            fs.flush();
            fs.close();
        } else {
            throw new Exception("Excel is not managed!");
        }
    }
    
    /**
     * save outputStream 把Excel保存到Strram中
     * @param outputStream Excel Stream 
     */
    public void save(OutputStream outputStream) throws Exception {
        if (bOpened == true) {
            BufferedOutputStream out = new BufferedOutputStream(outputStream);
            objWorkbook.write(out);
            out.flush();
            out.close();
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    /**
     * closed Excel
     *
     */
    public void close() throws Exception {
        if (bOpened == true) {
            objSheet = null;
            objWorkbook = null;
            bOpened = false;
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    /**
     * 设置使用的sheet
     * @param index  sheet 序号
     */
    public void setEditSheet(int index) throws Exception {
        if (bOpened == true) {
            objSheet = objWorkbook.getSheetAt(index);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    /**
     *  设置使用的sheet
     * @param sheetName sheet名称:  
     */
    public void setEditSheet(String sheetName) throws Exception {
        if (bOpened == true) {
            objSheet = objWorkbook.getSheet(sheetName);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    /**
     * 删除sheet
     * @param index sheet 序号:  
     */
    public void deleteSheet(int index) throws Exception {
        if (bOpened == true) {
            objWorkbook.removeSheetAt(index);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    /**
     * 删除sheet
     * @param index sheet名称:  
     */
    public void deleteSheet(String sheetName) throws Exception {
        if (bOpened == true) {
            this.deleteSheet(objWorkbook.getSheetIndex(sheetName));
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    /**
     * 获取单元格列的名称（"ABCDEFGHIJKLMNOPQRSTUVWXYZ"）
     * @param row 行号
     * @param col 列号
     * @return 

     */
    public String getCellName(int row, int col) {
        String str1 = "";
        String str2 = "";
        String str = "";
        String list = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        if ((col + 1) > 26) {
            int con1 = (col) / 26;
            str1 = list.substring(con1 - 1, con1);

            int con2 = (col + 1) - (26 * con1);
            str2 = list.substring(con2 - 1, con2);
        } else {
            str2 = list.substring(col, col + 1);
        }

        row++;
        str = str1 + str2 + row;

        return str;
    }

    /**
     * 获取单元格的行号与列号保存在rowcol数组中
     * @param cellName 单元格列名称:  
     * @param rowcol 1.row 行号，2.col 列号  
     */
    public void getCellRowCol(String cellName, int[] rowcol) {
        String list = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        if (list.indexOf(cellName.substring(1, 2).toUpperCase()) < 0) {
            rowcol[1] = list.indexOf(cellName.substring(0, 1).toUpperCase());
            rowcol[0] = Integer.parseInt(cellName.substring(1)) - 1;
        } else {
            int con1 = list.indexOf(cellName.substring(0, 1).toUpperCase());
            int con2 = list.indexOf(cellName.substring(1, 2).toUpperCase());
            rowcol[1] = ((con1 + 1) * 26) + con2;
            rowcol[0] = Integer.parseInt(cellName.substring(2)) - 1;
        }
    }

    /**
     * 设置单元格的value
     * @param row 行号  
     * @param col 列号  
     * @param value 字符串类型的值
     */
    public void setCellValue(int row, int col, String value)
        throws Exception {
        if (bOpened == true) {
            HSSFRow objRow = objSheet.getRow(row);
            HSSFCell objCell = objRow.getCell((short) col);
            objCell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
            objCell.setCellValue(value);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }
    
    /**
     * 设置单元格的value
     * @param row 行号  
     * @param col 列号  
     * @param value double类型的值
     */
    public void setCellValue(int row, int col, double value)
        throws Exception {
        if (bOpened == true) {
            HSSFRow objRow = objSheet.getRow(row);
            HSSFCell objCell = objRow.getCell((short) col);
            objCell.setCellValue(value);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    /**
     * 设置数字类型单元格的value
     * @param row 行号  
     * @param col 列号  
     * @param value 数字类型字符串的值
     */
    public void setCellNumberValue(int row, int col, String value)
        throws Exception {
        if (value == null || "".equals(value)) {
         return;
        } else {
            try {
             double tmpValue = new BigDecimal(value).doubleValue();
             setCellValue(row, col,  tmpValue);
            } catch (Exception ex) {
             setCellValue(row, col,  value);
            }
        }
    }
    
    /**
     *  
     * @param row (i/ ):  
     * @param col (i/ ):  
     * @param value (i/ ):  
     */
    public void setCellNumberValue(String cellName, String value)
        throws Exception {
        if (bOpened == true) {
            this.getCellRowCol(cellName, rc);
        } else {
            throw new Exception("Excel is not managed!");
        }
        
        if (value == null || "".equals(value)) {
         return;
        } else {
            try {
             double tmpValue = new BigDecimal(value).doubleValue();
             setCellValue(rc[0], rc[1],  tmpValue);
            } catch (Exception ex) {
             setCellValue(rc[0], rc[1],  value);
            }
        }
    }
    
    /**
     *  
     * @param sheetName (i/ ):  
     */
    public void setSheetSelected(String sheetName)
        throws Exception {
        if (bOpened == true) {
         objSheet = objWorkbook.getSheet(sheetName);
            objSheet.setSelected(true);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }
    
    /**
     *  
     * @param cellName (i/ ):  
     * @param value (i/ ):  
     */
    public void setCellValue(String cellName, String value)
        throws Exception {
        if (bOpened == true) {
            this.getCellRowCol(cellName, rc);
            this.setCellValue(rc[0], rc[1], value);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }
    
    /**
     *  
     * @param cellName (i/ ):  
     * @param value (i/ ):  
     */
    public void setCellValue(String cellName, double value)
        throws Exception {
        if (bOpened == true) {
            this.getCellRowCol(cellName, rc);
            this.setCellValue(rc[0], rc[1], value);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    private HSSFCellStyle cloneCellStyle(HSSFWorkbook workbook ,HSSFCellStyle objStyle1) {
        HSSFCellStyle objStyle2 = workbook.createCellStyle();
        objStyle2.setAlignment(objStyle1.getAlignment());
        objStyle2.setBorderBottom(objStyle1.getBorderBottom());
        objStyle2.setBorderLeft(objStyle1.getBorderLeft());
        objStyle2.setBorderRight(objStyle1.getBorderRight());
        objStyle2.setBorderTop(objStyle1.getBorderTop());
        objStyle2.setBottomBorderColor(objStyle1.getBottomBorderColor());
        objStyle2.setDataFormat(objStyle1.getDataFormat());
        objStyle2.setFillBackgroundColor(objStyle1.getFillBackgroundColor());
        objStyle2.setFillForegroundColor(objStyle1.getFillForegroundColor());
        objStyle2.setFillPattern(objStyle1.getFillPattern());
        objStyle2.setFont(workbook.getFontAt(objStyle1.getFontIndex()));
        objStyle2.setHidden(objStyle1.getHidden());
        objStyle2.setIndention(objStyle1.getIndention());
        objStyle2.setLeftBorderColor(objStyle1.getLeftBorderColor());
        objStyle2.setLocked(objStyle1.getLocked());
        objStyle2.setRightBorderColor(objStyle1.getRightBorderColor());
        objStyle2.setRotation(objStyle1.getRotation());
        objStyle2.setTopBorderColor(objStyle1.getTopBorderColor());
        objStyle2.setVerticalAlignment(objStyle1.getVerticalAlignment());
        objStyle2.setWrapText(objStyle1.getWrapText());

        return objStyle2;
    }
    
    private HSSFCellStyle cloneCellStyle(HSSFCellStyle objStyle1) {
        HSSFCellStyle objStyle2 = objWorkbook.createCellStyle();
        objStyle2.setAlignment(objStyle1.getAlignment());
        objStyle2.setBorderBottom(objStyle1.getBorderBottom());
        objStyle2.setBorderLeft(objStyle1.getBorderLeft());
        objStyle2.setBorderRight(objStyle1.getBorderRight());
        objStyle2.setBorderTop(objStyle1.getBorderTop());
        objStyle2.setBottomBorderColor(objStyle1.getBottomBorderColor());
        objStyle2.setDataFormat(objStyle1.getDataFormat());
        objStyle2.setFillBackgroundColor(objStyle1.getFillBackgroundColor());
        objStyle2.setFillForegroundColor(objStyle1.getFillForegroundColor());
        objStyle2.setFillPattern(objStyle1.getFillPattern());
        objStyle2.setFont(objWorkbook.getFontAt(objStyle1.getFontIndex()));
        objStyle2.setHidden(objStyle1.getHidden());
        objStyle2.setIndention(objStyle1.getIndention());
        objStyle2.setLeftBorderColor(objStyle1.getLeftBorderColor());
        objStyle2.setLocked(objStyle1.getLocked());
        objStyle2.setRightBorderColor(objStyle1.getRightBorderColor());
        objStyle2.setRotation(objStyle1.getRotation());
        objStyle2.setTopBorderColor(objStyle1.getTopBorderColor());
        objStyle2.setVerticalAlignment(objStyle1.getVerticalAlignment());
        objStyle2.setWrapText(objStyle1.getWrapText());

        return objStyle2;
    }

    /**
     *  
     * @param row (i/ ):  
     * @param col (i/ ):  
     * @param clrIndex (i/ ):  
     */
    public void setCellColor(int row, int col, int clrIndex)
        throws Exception {
        if (bOpened == true) {
            HSSFRow objRow = objSheet.getRow(row);
            HSSFCell objCell = objRow.getCell((short) col);
            HSSFCellStyle objStyle = objCell.getCellStyle();//cloneCellStyle(objCell.getCellStyle());
            objStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            objStyle.setFillForegroundColor((short) clrIndex);
            objCell.setCellStyle(objStyle);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }  

    
    /**
     *  
     * @param row (i/ ):  
     * @param col (i/ ):  
     * @param clrIndex (i/ ):  
     */
    public void setFontColor(int row, int col, int clrIndex,int fontSize)
        throws Exception {
        if (bOpened == true) {
            HSSFRow objRow = objSheet.getRow(row);
            HSSFCell objCell = objRow.getCell((short) col);
            HSSFCellStyle objStyle = objCell.getCellStyle();//cloneCellStyle(objCell.getCellStyle());
            HSSFFont data_font = objWorkbook.createFont();
            data_font.setColor((short) clrIndex);
            data_font.setFontHeightInPoints((short) fontSize);
            data_font.setFontName("MS PGothic");
            objStyle.setFont(data_font);
            objCell.setCellStyle(objStyle);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }    

    /**
     *  
     * @param cellName (i/ ):  
     * @param clrIndex (i/ ):  
     */
    public void setCellColor(String cellName, int clrIndex)
        throws Exception {
        if (bOpened == true) {
            this.getCellRowCol(cellName, rc);
            this.setCellColor(rc[0], rc[1], clrIndex);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }
    
    /**
     *  
     * @param cellName (i/ ):  
     * @param clrIndex (i/ ):  
     */
    public void setWidthColumn(int col, int width)
        throws Exception {
        if (bOpened == true) {            
         objSheet.setColumnWidth((short)col,(short)width);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }
    
    /**
     *  
     * @param cellName (i/ ):  
     * @param clrIndex (i/ ):  
     */
    public void setAutoWidthColumn(int col)
        throws Exception {
        if (bOpened == true) {            
         objSheet.autoSizeColumn((short)col);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }
    
    /**
     *  
     * @param cellName (i/ ):  
     * @param clrIndex (i/ ):  
     */
    public void setFontColor(String cellName, int clrIndex,int fontSize)
        throws Exception {
        if (bOpened == true) {
            this.getCellRowCol(cellName, rc);
            this.setFontColor(rc[0], rc[1], clrIndex, fontSize);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }    

    /**
     * 
     * @param row (i/ ):  
     * @param col (i/ ): 
     * @param formula (i/ ):  
     */
    public void setCellFormula(int row, int col, String formula)
        throws Exception {
        if (bOpened == true) {
            HSSFRow objRow = objSheet.getRow(row);
            HSSFCell objCell = objRow.getCell((short) col);
            objCell.setCellFormula(formula);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    /**
     *  
     * @param cellName (i/ ):  
     * @param formula (i/ ):  
     */
    public void setCellFormula(String cellName, String formula)
        throws Exception {
        if (bOpened == true) {
            this.getCellRowCol(cellName, rc);
            this.setCellFormula(rc[0], rc[1], formula);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    /**
     *  
     * @param row (i/ ):  
     * @param col (i/ ):  
     * @param link (i/ ):  
     */
    public void addCellLink(int row, int col, String link)
        throws Exception {
        if (bOpened == true) {
            HSSFRow objRow = objSheet.getRow(row);
            HSSFCell objCell = objRow.getCell((short) col);
            objCell.setCellFormula("HYPERLINK(\"" + link + "\",\"" +
                objCell.getStringCellValue() + "\")");
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    /**
     *  
     * @param cellName (i/ ):  
     * @param link (i/ ):  
     */
    public void addCellLink(String cellName, String link)
        throws Exception {
        if (bOpened == true) {
            this.getCellRowCol(cellName, rc);
            this.addCellLink(rc[0], rc[1], link);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }
    
    public void copySheet(HSSFWorkbook originWorkBook, String pSourceSheetName,HSSFWorkbook targetWorkBook, String pTargetSheetName) { 
     HSSFRow sourceRow = null; 
     HSSFRow targetRow = null; 
     HSSFCell sourceCell = null; 
     HSSFCell targetCell = null; 
     HSSFSheet sourceSheet = null; 
     HSSFSheet targetSheet = null; 
     Region region = null; 
     int pStartRow = 0;
     int pEndRow = 0; 
     int pPosition = 0;

     sourceSheet = originWorkBook.getSheet(pSourceSheetName); 
     targetSheet = targetWorkBook.getSheet(pTargetSheetName); 
     pStartRow = sourceSheet.getFirstRowNum();
     pEndRow = sourceSheet.getLastRowNum();
     
     if ((pStartRow == -1) || (pEndRow == -1)) { 
         return; 
        } 
     
     for (int i = 0; i < sourceSheet.getNumMergedRegions(); i++) { 
      region = sourceSheet.getMergedRegionAt(i); 
      if ((region.getRowFrom() >= pStartRow) && (region.getRowTo() <= pEndRow)) { 
       int targetRowFrom = region.getRowFrom() - pStartRow + pPosition; 
       int targetRowTo = region.getRowTo() - pStartRow + pPosition; 
       region.setRowFrom(targetRowFrom); 
       region.setRowTo(targetRowTo); 
       targetSheet.addMergedRegion(region); 
      } 
     } 
 
     for (int i = 0; i <= pEndRow; i++) { 
       sourceRow = sourceSheet.getRow(i); 
      if (sourceRow == null) { 
       continue; 
      } 
      targetRow = targetSheet.createRow(i - pStartRow + pPosition); 
      targetRow.setHeight(sourceRow.getHeight()); 
      for (short j = sourceRow.getFirstCellNum(); j < sourceRow.getLastCellNum(); j++) { //sourceRow.getPhysicalNumberOfCells()
       sourceCell = sourceRow.getCell(j); 
       if (sourceCell == null) { 
        continue; 
       } 
       targetCell = targetRow.createCell(j); 
       targetCell.setEncoding(sourceCell.getEncoding()); 
       targetCell.setCellStyle(sourceCell.getCellStyle());
       if(sourceCell.getHyperlink() != null)
       {
       	HSSFHyperlink hhl2 = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
       	hhl2.setAddress(sourceCell.getHyperlink().getAddress());
       	targetCell.setHyperlink(hhl2);
       }
       
       //复制列宽 add by lizhenyou
       targetSheet.setColumnWidth(targetCell.getCellNum(), sourceSheet.getColumnWidth(sourceCell.getCellNum()));
       
       int cType = sourceCell.getCellType(); 
       targetCell.setCellType(cType); 
       switch (cType) { 
        case HSSFCell.CELL_TYPE_BOOLEAN: 
        targetCell.setCellValue(sourceCell.getBooleanCellValue()); 
        break; 
        case HSSFCell.CELL_TYPE_ERROR: 
        targetCell.setCellErrorValue(sourceCell.getErrorCellValue()); 
        break; 
        case HSSFCell.CELL_TYPE_FORMULA: 
        targetCell.setCellFormula(sourceCell.getCellFormula()); 
        break; 
        case HSSFCell.CELL_TYPE_NUMERIC: 
        targetCell.setCellValue(sourceCell.getNumericCellValue()); 
        break; 
        case HSSFCell.CELL_TYPE_STRING: 
        targetCell.setCellValue(sourceCell.getStringCellValue()); 
        break; 
       } 
      }
     } 
    }
    
    /**
     *  
     * @param originSheetIndex (i/ ):  
     * @param originLeftTopRow (i/ ):  
     * @param originLeftTopCol (i/ ):  
     * @param originRightBottomRow (i/ ):  
     * @param originRightBottomCol (i/ ):  
     * @param targetSheetIndex (i/ ):  
     * @param targetLeftTopRow (i/ ):  
     * @param targetLeftTopCol (i/ ):  
     */
    public void copyRange(int originSheetIndex, int originLeftTopRow,
        int originLeftTopCol, int originRightBottomRow,
        int originRightBottomCol, int targetSheetIndex, int targetLeftTopRow,
        int targetLeftTopCol) throws Exception {
        if (bOpened == true) {
            HSSFSheet sheet1 = objWorkbook.getSheetAt(originSheetIndex);
            HSSFSheet sheet2 = objWorkbook.getSheetAt(targetSheetIndex);

            for (int i = 0; i <= (originRightBottomRow - originLeftTopRow);
                    i++) {
                HSSFRow row1 = sheet1.getRow(originLeftTopRow + i);

                if (row1 == null) {
                    continue;
                }

                HSSFRow row2 = sheet2.getRow(targetLeftTopRow + i);

                if (row2 == null) {
                    row2 = sheet2.createRow(targetLeftTopRow + i);
                    row2.setHeight(row1.getHeight());
                }

                for (int j = 0; j <= (originRightBottomCol - originLeftTopCol);
                        j++) {
                    HSSFCell cell1 = row1.getCell((short) (originLeftTopCol +
                            j));

                    if (cell1 == null) {
                        continue;
                    }

                    HSSFCell cell2 = row2.getCell((short) (targetLeftTopCol +
                            j));
                    
                    if (cell2 == null) {
                        cell2 = row2.createCell((short) (targetLeftTopCol + j));
                        //sheet2.setColumnWidth((short) (targetLeftTopCol + j),sheet1.getColumnWidth((short) (targetLeftTopCol + j)));
                    }

                    cell2.setEncoding(cell1.getEncoding());
                    cell2.setCellStyle(cell1.getCellStyle());
                    if(cell1.getHyperlink() != null)
                    {
                    	HSSFHyperlink hhl2 = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
                    	hhl2.setAddress(cell1.getHyperlink().getAddress());
                    	cell2.setHyperlink(hhl2);
                    }
                    //复制列宽
                    sheet2.setColumnWidth(cell2.getCellNum(), sheet1.getColumnWidth(cell1.getCellNum()));

                    int cellType = cell1.getCellType();

                    if (cellType == HSSFCell.CELL_TYPE_STRING) 
                    {
//                        cell2.setCellValue(cell1.getStringCellValue());
                    	String str = cell1.getStringCellValue();
                    	HSSFRichTextString  hrts = new HSSFRichTextString(str);
                        cell2.setCellValue(hrts);
                    } 
                    else if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                        cell2.setCellValue(cell1.getNumericCellValue());
                    }
                }
            }

            for (int i = 0; i < sheet1.getNumMergedRegions(); i++) {
                Region region1 = sheet1.getMergedRegionAt(i);
                int LeftTopRow = region1.getRowFrom();
                int LeftTopCol = region1.getColumnFrom();
                int RightBottomRow = region1.getRowTo();
                int RightBottomCol = region1.getColumnTo();

                if ((LeftTopRow >= originLeftTopRow) &&
                        (LeftTopCol >= originLeftTopCol) &&
                        (RightBottomRow <= originRightBottomRow) &&
                        (RightBottomCol <= originRightBottomCol)) {
                    LeftTopRow = targetLeftTopRow +
                        (LeftTopRow - originLeftTopRow);
                    LeftTopCol = targetLeftTopCol +
                        (LeftTopCol - originLeftTopCol);
                    RightBottomRow = targetLeftTopRow +
                        (RightBottomRow - originLeftTopRow);
                    RightBottomCol = targetLeftTopCol +
                        (RightBottomCol - originLeftTopCol);
                    sheet2.addMergedRegion(new Region((short) LeftTopRow,
                            (short) LeftTopCol, (short) RightBottomRow,
                            (short) RightBottomCol));
                }
            }
        } else {
            throw new Exception("Excel is not managed!");
        }
    }
    
    //=====================
    /**
     * 复制报表表头
     * @param originSheetIndex (i/ ):  
     * @param originLeftTopRow (i/ ):  
     * @param originLeftTopCol (i/ ):  
     * @param originRightBottomRow (i/ ):  
     * @param originRightBottomCol (i/ ):  
     * @param targetSheetIndex (i/ ):  
     * @param targetLeftTopRow (i/ ):  
     * @param targetLeftTopCol (i/ ):  
     */
    private void copyHeadRange(int originSheetIndex, int originLeftTopRow,
        int originLeftTopCol, int originRightBottomRow,
        int originRightBottomCol, int targetSheetIndex, int targetLeftTopRow,
        int targetLeftTopCol,String[] headNames) throws Exception {
        if (bOpened == true) {
        	int headNamesIndex = 0;
            HSSFSheet sheet1 = objWorkbook.getSheetAt(originSheetIndex);
            HSSFSheet sheet2 = objWorkbook.getSheetAt(targetSheetIndex);

            for (int i = 0; i <= (originRightBottomRow - originLeftTopRow);
                    i++) {
                HSSFRow row1 = sheet1.getRow(originLeftTopRow + i);

                if (row1 == null) {
                    continue;
                }

                HSSFRow row2 = sheet2.getRow(targetLeftTopRow + i);

                if (row2 == null) {
                    row2 = sheet2.createRow(targetLeftTopRow + i);
                    row2.setHeight(row1.getHeight());
                }

                for (int j = 0; j <= (originRightBottomCol - originLeftTopCol);
                        j++) {
                    HSSFCell cell1 = row1.getCell((short) (originLeftTopCol +
                            j));

                    if (cell1 == null) {
                        continue;
                    }

                    HSSFCell cell2 = row2.getCell((short) (targetLeftTopCol +
                            j));
                    
                    if (cell2 == null) {
                        cell2 = row2.createCell((short) (targetLeftTopCol + j));
                        //sheet2.setColumnWidth((short) (targetLeftTopCol + j),sheet1.getColumnWidth((short) (targetLeftTopCol + j)));
                    }

                    cell2.setEncoding(cell1.getEncoding());
                    cell2.setCellStyle(cell1.getCellStyle());
                    if(cell1.getHyperlink() != null)
                    {
                    	HSSFHyperlink hhl2 = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
                    	hhl2.setAddress(cell1.getHyperlink().getAddress());
                    	cell2.setHyperlink(hhl2);
                    }
                    
                    //复制列宽
                    sheet2.setColumnWidth(cell2.getCellNum(), sheet1.getColumnWidth(cell1.getCellNum()));

                    int cellType = cell1.getCellType();

                    if (cellType == HSSFCell.CELL_TYPE_STRING) 
                    {
//                        cell2.setCellValue(cell1.getStringCellValue());
                    	String str = cell1.getStringCellValue();
                    	if(Constants.$COLUM$.equalsIgnoreCase(str))
                    	{
                    		str = headNames[headNamesIndex];
                    		headNamesIndex++;
                    	}
                    	HSSFRichTextString  hrts = new HSSFRichTextString(str);
                        cell2.setCellValue(hrts);
                    } 
                    else if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                        cell2.setCellValue(cell1.getNumericCellValue());
                    }
                }
            }
            
            for (int i = 0; i < sheet1.getNumMergedRegions(); i++) {
                Region region1 = sheet1.getMergedRegionAt(i);
                int LeftTopRow = region1.getRowFrom();
                int LeftTopCol = region1.getColumnFrom();
                int RightBottomRow = region1.getRowTo();
                int RightBottomCol = region1.getColumnTo();

                if ((LeftTopRow >= originLeftTopRow) &&
                        (LeftTopCol >= originLeftTopCol) &&
                        (RightBottomRow <= originRightBottomRow) &&
                        (RightBottomCol <= originRightBottomCol)) {
                    LeftTopRow = targetLeftTopRow +
                        (LeftTopRow - originLeftTopRow);
                    LeftTopCol = targetLeftTopCol +
                        (LeftTopCol - originLeftTopCol);
                    RightBottomRow = targetLeftTopRow +
                        (RightBottomRow - originLeftTopRow);
                    RightBottomCol = targetLeftTopCol +
                        (RightBottomCol - originLeftTopCol);
                    sheet2.addMergedRegion(new Region((short) LeftTopRow,
                            (short) LeftTopCol, (short) RightBottomRow,
                            (short) RightBottomCol));
                }
            }
        } else {
            throw new Exception("Excel is not managed!");
        }
    }
    
    /**
     * 复制报表表体
     * @param originSheetIndex (i/ ):  
     * @param originLeftTopRow (i/ ):  
     * @param originLeftTopCol (i/ ):  
     * @param originRightBottomRow (i/ ):  
     * @param originRightBottomCol (i/ ):  
     * @param targetSheetIndex (i/ ):  
     * @param targetLeftTopRow (i/ ):  
     * @param targetLeftTopCol (i/ ):  
     */
    private void copyBodyRange(int originSheetIndex, int originLeftTopRow,
        int originLeftTopCol, int originRightBottomRow,
        int originRightBottomCol, int targetSheetIndex, int targetLeftTopRow,
        int targetLeftTopCol,String[] bodyNames) throws Exception {
        if (bOpened == true) {
        	int bodyNamesIndex = 0;
            HSSFSheet sheet1 = objWorkbook.getSheetAt(originSheetIndex);
            HSSFSheet sheet2 = objWorkbook.getSheetAt(targetSheetIndex);

            for (int i = 0; i <= (originRightBottomRow - originLeftTopRow);
                    i++) {
                HSSFRow row1 = sheet1.getRow(originLeftTopRow + i);

                if (row1 == null) {
                    continue;
                }

                HSSFRow row2 = sheet2.getRow(targetLeftTopRow + i);

                if (row2 == null) {
                    row2 = sheet2.createRow(targetLeftTopRow + i);
                    row2.setHeight(row1.getHeight());
                }

                for (int j = 0; j <= (originRightBottomCol - originLeftTopCol);
                        j++) {
                    HSSFCell cell1 = row1.getCell((short) (originLeftTopCol +
                            j));

                    if (cell1 == null) {
                        continue;
                    }

                    HSSFCell cell2 = row2.getCell((short) (targetLeftTopCol +
                            j));
                    
                    if (cell2 == null) {
                        cell2 = row2.createCell((short) (targetLeftTopCol + j));
                        //sheet2.setColumnWidth((short) (targetLeftTopCol + j),sheet1.getColumnWidth((short) (targetLeftTopCol + j)));
                    }

                    cell2.setEncoding(cell1.getEncoding());
                    cell2.setCellStyle(cell1.getCellStyle());
                    if(cell1.getHyperlink() != null)
                    {
                    	HSSFHyperlink hhl2 = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
                    	hhl2.setAddress(cell1.getHyperlink().getAddress());
                    	cell2.setHyperlink(hhl2);
                    }
                    //复制列宽
                    sheet2.setColumnWidth(cell2.getCellNum(),sheet1.getColumnWidth(cell1.getCellNum()) );

                    int cellType = cell1.getCellType();

                    if (cellType == HSSFCell.CELL_TYPE_STRING) 
                    {
//                        cell2.setCellValue(cell1.getStringCellValue());
                    	String str = cell1.getStringCellValue();
                    	if(str != null && str.startsWith(Constants.$DATA$) && str.indexOf(Constants.$STATCOLUM_X$) != -1)
                    	{
                    		str = str.replace(Constants.$STATCOLUM_X$, bodyNames[bodyNamesIndex]);
                    		bodyNamesIndex++;
                    	}
                    	HSSFRichTextString  hrts = new HSSFRichTextString(str);
                        cell2.setCellValue(hrts);
                    } 
                    else if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                        cell2.setCellValue(cell1.getNumericCellValue());
                    }
                }
            }

            for (int i = 0; i < sheet1.getNumMergedRegions(); i++) {
                Region region1 = sheet1.getMergedRegionAt(i);
                int LeftTopRow = region1.getRowFrom();
                int LeftTopCol = region1.getColumnFrom();
                int RightBottomRow = region1.getRowTo();
                int RightBottomCol = region1.getColumnTo();

                if ((LeftTopRow >= originLeftTopRow) &&
                        (LeftTopCol >= originLeftTopCol) &&
                        (RightBottomRow <= originRightBottomRow) &&
                        (RightBottomCol <= originRightBottomCol)) {
                    LeftTopRow = targetLeftTopRow +
                        (LeftTopRow - originLeftTopRow);
                    LeftTopCol = targetLeftTopCol +
                        (LeftTopCol - originLeftTopCol);
                    RightBottomRow = targetLeftTopRow +
                        (RightBottomRow - originLeftTopRow);
                    RightBottomCol = targetLeftTopCol +
                        (RightBottomCol - originLeftTopCol);
                    sheet2.addMergedRegion(new Region((short) LeftTopRow,
                            (short) LeftTopCol, (short) RightBottomRow,
                            (short) RightBottomCol));
                }
            }
        } else {
            throw new Exception("Excel is not managed!");
        }
    }
    
    /**
     * 复制报表表体
     * @param originSheetIndex (i/ ):  
     * @param originLeftTopRow (i/ ):  
     * @param originLeftTopCol (i/ ):  
     * @param originRightBottomRow (i/ ):  
     * @param originRightBottomCol (i/ ):  
     * @param targetSheetIndex (i/ ):  
     * @param targetLeftTopRow (i/ ):  
     * @param targetLeftTopCol (i/ ):  
     */
    private void copyFootRange(int originSheetIndex, int originLeftTopRow,
        int originLeftTopCol, int originRightBottomRow,
        int originRightBottomCol, int targetSheetIndex, int targetLeftTopRow,
        int targetLeftTopCol,String[] footNames) throws Exception {
        if (bOpened == true) {
        	int footNamesIndex = 0;
            HSSFSheet sheet1 = objWorkbook.getSheetAt(originSheetIndex);
            HSSFSheet sheet2 = objWorkbook.getSheetAt(targetSheetIndex);

            for (int i = 0; i <= (originRightBottomRow - originLeftTopRow);
                    i++) {
                HSSFRow row1 = sheet1.getRow(originLeftTopRow + i);

                if (row1 == null) {
                    continue;
                }

                HSSFRow row2 = sheet2.getRow(targetLeftTopRow + i);

                if (row2 == null) {
                    row2 = sheet2.createRow(targetLeftTopRow + i);
                    row2.setHeight(row1.getHeight());
                }

                for (int j = 0; j <= (originRightBottomCol - originLeftTopCol);
                        j++) {
                    HSSFCell cell1 = row1.getCell((short) (originLeftTopCol +
                            j));

                    if (cell1 == null) {
                        continue;
                    }

                    HSSFCell cell2 = row2.getCell((short) (targetLeftTopCol +
                            j));
                    
                    if (cell2 == null) {
                        cell2 = row2.createCell((short) (targetLeftTopCol + j));
                        //sheet2.setColumnWidth((short) (targetLeftTopCol + j),sheet1.getColumnWidth((short) (targetLeftTopCol + j)));
                    }

                    cell2.setEncoding(cell1.getEncoding());
                    cell2.setCellStyle(cell1.getCellStyle());
                    if(cell1.getHyperlink() != null)
                    {
                    	HSSFHyperlink hhl2 = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
                    	hhl2.setAddress(cell1.getHyperlink().getAddress());
                    	cell2.setHyperlink(hhl2);
                    }
                    
                    //复制列宽 add by lizhenyou
                    sheet2.setColumnWidth(cell2.getCellNum(), sheet1.getColumnWidth(cell1.getCellNum()));

                    int cellType = cell1.getCellType();
                    if (cellType == HSSFCell.CELL_TYPE_STRING) 
                    {
//                        cell2.setCellValue(cell1.getStringCellValue());
                    	String str = cell1.getStringCellValue();
                    	if(str != null && str.startsWith(Constants.$TOTAL$) && str.indexOf(Constants.$STATCOLUM_X$) != -1)
                    	{
                    		str = str.replace(Constants.$STATCOLUM_X$, footNames[footNamesIndex]);
                    		footNamesIndex++;
                    	}
                    	HSSFRichTextString  hrts = new HSSFRichTextString(str);
                        cell2.setCellValue(hrts);
                    }
                    else if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                        cell2.setCellValue(cell1.getNumericCellValue());
                    }
                }
            }

            for (int i = 0; i < sheet1.getNumMergedRegions(); i++) {
                Region region1 = sheet1.getMergedRegionAt(i);
                int LeftTopRow = region1.getRowFrom();
                int LeftTopCol = region1.getColumnFrom();
                int RightBottomRow = region1.getRowTo();
                int RightBottomCol = region1.getColumnTo();

                if ((LeftTopRow >= originLeftTopRow) &&
                        (LeftTopCol >= originLeftTopCol) &&
                        (RightBottomRow <= originRightBottomRow) &&
                        (RightBottomCol <= originRightBottomCol)) {
                    LeftTopRow = targetLeftTopRow +
                        (LeftTopRow - originLeftTopRow);
                    LeftTopCol = targetLeftTopCol +
                        (LeftTopCol - originLeftTopCol);
                    RightBottomRow = targetLeftTopRow +
                        (RightBottomRow - originLeftTopRow);
                    RightBottomCol = targetLeftTopCol +
                        (RightBottomCol - originLeftTopCol);
                    sheet2.addMergedRegion(new Region((short) LeftTopRow,
                            (short) LeftTopCol, (short) RightBottomRow,
                            (short) RightBottomCol));
                }
            }
        } else {
            throw new Exception("Excel is not managed!");
        }
    }
    
    //====================

    /**
     *  
     * @param originSheetName (i/ ):  
     * @param originLeftTopCellName (i/ ):  
     * @param originRightBottomCellName (i/ ):  
     * @param targetSheetName (i/ ):  
     * @param targetLeftTopCellName (i/ ):  
     */
    public void copyRange(String originSheetName, String originLeftTopCellName,
        String originRightBottomCellName, String targetSheetName,
        String targetLeftTopCellName) throws Exception {
        if (bOpened == true) {
            int originSheetIndex;
            int originLeftTopRow;
            int originLeftTopCol;
            int originRightBottomRow;
            int originRightBottomCol;
            int targetSheetIndex;
            int targetLeftTopRow;
            int targetLeftTopCol;
            originSheetIndex = objWorkbook.getSheetIndex(originSheetName);
            this.getCellRowCol(originLeftTopCellName, rc);
            originLeftTopRow = rc[0];
            originLeftTopCol = rc[1];
            this.getCellRowCol(originRightBottomCellName, rc);
            originRightBottomRow = rc[0];
            originRightBottomCol = rc[1];
            targetSheetIndex = objWorkbook.getSheetIndex(targetSheetName);
            this.getCellRowCol(targetLeftTopCellName, rc);
            targetLeftTopRow = rc[0];
            targetLeftTopCol = rc[1];
            this.copyRange(originSheetIndex, originLeftTopRow,
                originLeftTopCol, originRightBottomRow, originRightBottomCol,
                targetSheetIndex, targetLeftTopRow, targetLeftTopCol);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    /**
     *  
     * @param SheetName (i/ ):  
     */
    public void addSheet(String SheetName) throws Exception {
        if (bOpened == true) {
            objWorkbook.createSheet(SheetName);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    /**
     *  
     * @param leftTopRow (i/ ):  
     * @param leftTopCol (i/ ):  
     * @param rightBottomRow (i/ ):  
     * @param rightBottomCol (i/ ):  
     */
    public void setMergeRegion(int leftTopRow, int leftTopCol,
        int rightBottomRow, int rightBottomCol) throws Exception {
        if (bOpened == true) {
            objSheet.addMergedRegion(new Region((short) leftTopRow,
                    (short) leftTopCol, (short) rightBottomRow,
                    (short) rightBottomCol));
        } else {
            throw new Exception("Excel is not managed!");
        }
    }

    /**
     *  
     * @param leftTopCellName (i/ ):  
     * @param rightBottomCellName (i/ ):  
     */
    public void setMergeRegion(String leftTopCellName,
        String rightBottomCellName) throws Exception {
        if (bOpened == true) {
            int lr = 0;
            int lc = 0;
            this.getCellRowCol(leftTopCellName, rc);
            lr = rc[0];
            lc = rc[1];
            this.getCellRowCol(rightBottomCellName, rc);
            this.setMergeRegion(lr, lc, rc[0], rc[1]);
        } else {
            throw new Exception("Excel is not managed!");
        }
    }
    
    /**
     * 解析动态列配置信息
     * @param xml
     * @return
     * @throws Exception
     */
	private static DyColum getConfigInfoDycolum(String xml) throws Exception
	{
		Document document = new SAXReader().read(new ByteArrayInputStream(xml.getBytes()));
		Element root = document.getRootElement();
		DyColum dc = null;
		List rootList = root.elements();
		for(int i = 0; i < rootList.size(); i++)
		{
			Element config = ((Element)(rootList.get(i)));
			if(config.getName().equalsIgnoreCase(Constants.DYCOLUM))
			{	
				String start_row = config.attributeValue("start_row");;
				String end_row = config.attributeValue("end_row");;
				String front_start = config.attributeValue("front_start");
				String front_end = config.attributeValue("front_end");
				String dynamic_start = config.attributeValue("dynamic_start");
				String dynamic_end = config.attributeValue("dynamic_end");
				String back_start = config.attributeValue("back_start");
				String back_end = config.attributeValue("back_end");
				String head_unite_start = config.attributeValue("head_unite_start");
				String head_unite_end = config.attributeValue("head_unite_end");
				String foot_unite_start = config.attributeValue("foot_unite_start");
				String foot_unite_end = config.attributeValue("foot_unite_end");
				String cls = config.attributeValue("cls");
				dc = new DyColum(start_row,end_row,front_start,front_end,dynamic_start,dynamic_end
						,back_start,back_end,
						head_unite_start,head_unite_end,foot_unite_start,foot_unite_end,
						cls);
				break;
			}
		}
		
		return dc;
	}
	
    /**
     * 解析动态列配置信息
     * @param xml
     * @return
     * @throws Exception
     */
	private static Table getConfigInfoTable(String xml) throws Exception
	{
		Document document = new SAXReader().read(new ByteArrayInputStream(xml.getBytes()));
		Element root = document.getRootElement();
		Table table = null;
		List rootList = root.elements();
		for(int i = 0; i < rootList.size(); i++)
		{
			Element config = ((Element)(rootList.get(i)));
			if(config.getName().equalsIgnoreCase(Constants.CONFIGTABLE))
			{	
				String width = config.attributeValue("width");;
				String head_start = config.attributeValue("head_start");;
				String head_end = config.attributeValue("head_end");
				String body_start = config.attributeValue("body_start");
				String body_end = config.attributeValue("body_end");
				String foot_start = config.attributeValue("foot_start");
				String foot_end = config.attributeValue("foot_end");
				table = new Table(width,head_start,head_end,body_start,
						body_end,foot_start,foot_end);
				break;
			}
		}
		
		return table;
	}
	
	/**
	 * 根据动态列的信息，调整table标签的width属性，设置解析Excel表格的宽度
	 * @param xml
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private String setConfigInfo(String xml,String value) throws Exception
	{
		Document document = new SAXReader().read(new ByteArrayInputStream(xml.getBytes()));
		Element root = document.getRootElement();
		List rootList = root.elements();
		for(int i = 0; i < rootList.size(); i++)
		{
			Element config = ((Element)(rootList.get(i)));
			if(config.getName().equalsIgnoreCase(Constants.CONFIGTABLE))
			{	
				config.attribute(Constants.CONFIGWIDTH).setValue(value);
				break;
			}
		}
		
		xml = document.asXML();
		
		return xml;
	}
	
	/**
	 * 动态列Excel处理
	 * @param excelPath excel绝对路径
	 * @param sheetIndex excel文件sheet的索引
	 * @param dyColumSelectids DyTableInfo中的id 
	 * @return HSSFWorkbook 返回处理后的Excel工作薄
	 * @throws Exception
	 */
	public HSSFWorkbook dyColumConver(HSSFWorkbook hwb,HSSFSheet sheet ,String[] dyColumSelectids) throws Exception
	{
		DyColum dc = getDyColumForExcel(sheet);
		if(dc == null )//不需要处理excel。
		{
			return hwb;
		}
		DyTableInfos dyTableInfos = (DyTableInfos)Class.forName(dc.cls).newInstance();//new SampleDyTableInfo();
    	
    	DyTableInfo[] dtis = new DyTableInfo[dyColumSelectids.length];
    	for(int i=0;i<dtis.length;i++)
    	{
    		dtis[i] = dyTableInfos.getDyTableInfoById(dyColumSelectids[i]);
    	}
    		
		return dyColumConver(hwb,sheet,dtis);
	}
	
	/**
	 * 动态列Excel处理，
	 * 注意这个是处理动态列的操作，主要操作excel，其中合并拆分单元格比较复杂，
	 * 如果遇到样式计算错误可以是用一下代码把流写到文件中，以方便查出问题所在
	 * FileOutputStream fos= new FileOutputStream("E:/dycolum_test.xls");
	 * wb.write(fos);
	 * fos.close();
	 * @param excelPath excel绝对路径
	 * @param sheetIndex excel文件sheet的索引
	 * @param selectid DyTableInfo中的id 
	 * @return HSSFWorkbook 返回处理后的Excel工作薄
	 * @throws Exception
	 */
	public HSSFWorkbook dyColumConver(HSSFWorkbook hwb,HSSFSheet sheet ,DyTableInfo[] dtis) throws Exception
	{
		CommonExcel ce = new CommonExcel(hwb,sheet);
		int sheetIndex = hwb.getSheetIndex(sheet);
		
//		ce.open(excelPath);
		
    	//读取模板配置信息获取第一行的配置信息
    	//HSSFSheet sheet = hfsheet;//ce.objWorkbook.getSheetAt(sheetIndex);
        HSSFRow fistRow = sheet.getRow(0);
        if(fistRow == null)
        {
            return ce.objWorkbook;
        }
        HSSFCell fistCell;
        if(( fistCell=fistRow.getCell((short)0)) == null)
        {
                throw new Exception("没有定义配置信息");
        }
        String configInfoXml = fistCell.getRichStringCellValue().getString();
        DyColum dc = getConfigInfoDycolum(configInfoXml);
        if(dc == null)//没有配置动态列
        {
        	return ce.objWorkbook;
        }
        Table table = getConfigInfoTable(configInfoXml);
    	// 建立一个临时的sheet
    	String tmpSheetName = "tmpSheetName";
    	if(ce.objWorkbook.getSheetIndex(tmpSheetName) != -1)
    	{
    		ce.deleteSheet(tmpSheetName);
    	}
    	int maxSheetIndex = ce.objWorkbook.getNumberOfSheets();
    	ce.objWorkbook.createSheet(tmpSheetName);
    	ce.objWorkbook.setSheetOrder(tmpSheetName, maxSheetIndex);
    	ce.objWorkbook.setSelectedTab(maxSheetIndex);
    	
    	//拷贝源sheet的都有配置信息到tmpSheetName的sheet中 并且不合并单元格
    	ce.copySheet(ce.objWorkbook,  ce.objWorkbook.getSheetName(sheetIndex)
    			, ce.objWorkbook, tmpSheetName);
//    	ce.copyRange(sheetIndex, 0, 0, 0, 1, maxSheetIndex, 0, 0);
    	
    	//写文件
//    	FileOutputStream fos= new FileOutputStream("E:/dycolum_test.xls");
//    	ce.objWorkbook.write(fos);
//    	fos.close();
    	
    	//前部静态列，不做处理
    	int startR = dc.start_row-1;//3;
    	int startC = dc.front_start_colum-1;//0;
    	int endR = dc.end_row-1;//6;
    	int endC = dc.front_end_colum-1;//2;
    	ce.copyRange(sheetIndex, startR, startC, endR, endC, maxSheetIndex, startR, startC);
    	
    	int dColum = dc.dynamic_end_colum-dc.dynamic_start_colum+1;//2; //动态列模板的列数
    	startC = dc.dynamic_start_colum-1;//3;
    	endC = dc.dynamic_end_colum-1;//4;
    	int sc = startC;
    	for(int i=0;i<dtis.length;i++)//根据输入条件确定循环的次数，动态的生成
    	{
    		//复制表头部分 table.head_start为-1的时候为没有表头部分，这里所说的表头是横向的表从左到右 
    		if(table.head_start>0 && table.head_end>0)
    		{
    			String[] headNames = dtis[i].getHeadNames();//{"第一列","及时数","超时数"};//表头文字信息
        		int headSR = startR>table.head_start-1 ? startR:table.head_start;
        		int headER = table.head_end-1;
        		ce.copyHeadRange(sheetIndex, headSR, startC, headER, endC, maxSheetIndex, headSR, sc,headNames);
    		}
    		//复制表身部分
    		if(table.body_start>0 && table.body_end>0)
    		{
    			String[] bodyNames = dtis[i].getBodyNames();//{"stat1","stat2","stat3"};//表体配置信息
        		int bodySR = table.body_start-1;
        		int bodyER = table.body_end-1;
        		ce.copyBodyRange(sheetIndex, bodySR, startC, bodyER, endC, maxSheetIndex, bodySR, sc,bodyNames);
    		}
    		
    		//复制表尾部分
    		if(table.foot_start>0 && table.foot_end>0)
    		{
    			String[] footNames = dtis[i].getFootNames(); //{"stat1","stat2","stat3"};//表尾配置信息
        		int footSR = table.foot_start-1;
        		int footER = endR>table.foot_end-1 ? endR:table.foot_end-1;
        		ce.copyFootRange(sheetIndex, footSR, startC, footER, endC, maxSheetIndex, footSR, sc,footNames);
    		}
        	sc += dColum;
    	}
//    	FileOutputStream fos1= new FileOutputStream("E:/dycolum_test1.xls");
//    	ce.objWorkbook.write(fos1);
//    	fos1.close();
    	
    	//后部静态列
    	if(dc.back_start_colum>-1 && dc.back_end_colum>-1)
    	{
    		startC = dc.back_start_colum-1;//5;
        	endC = dc.back_end_colum-1;//6;
    		ce.copyRange(sheetIndex, startR, startC, endR, endC, maxSheetIndex, startR, sc);
    		sc=sc+endC-startC+1;
    	}
    	
//    	FileOutputStream fos2= new FileOutputStream("E:/dycolum_test2.xls");
//    	ce.objWorkbook.write(fos2);
//    	fos2.close();
    	
    	//设置模板配置信息，修改table标签的width属性
    	int tableWidth = sc;
    	String config = setConfigInfo(configInfoXml,String.valueOf(tableWidth));
    	ce.objWorkbook.getSheetAt(maxSheetIndex).getRow(0).getCell(0).setCellValue(new HSSFRichTextString(config));
    	
    	//修改表头表尾信息行的单元格，合并为与整个
    	//合并表头的信息行
    	if(dc.head_unite_start>0 && dc.head_unite_end > 0)
    	{
        	//合并信息-表头信息
//    		ce.copyRange(sheetIndex, dc.head_unite_start-1,0, dc.head_unite_end-1, tableWidth-1, maxSheetIndex, dc.head_unite_start-1, 0);
    		
    		for(int i=dc.head_unite_start ; i<dc.head_unite_end+1;i++)
    		{
        		short LeftTopRow = (short)(i-1);
            	short LeftTopCol = (short)0;
            	short RightBottomRow = (short)(i-1);
            	short RightBottomCol = (short)(tableWidth -1);
            	HSSFSheet sh = ce.objWorkbook.getSheetAt(maxSheetIndex);
        		Region region = new Region((short) LeftTopRow,(short) LeftTopCol, 
    					(short) RightBottomRow,(short) RightBottomCol);
        		HSSFCell hc = sh.getRow(LeftTopRow).getCell(LeftTopCol);
        		HSSFCellStyle cs = ce.cloneCellStyle(ce.objWorkbook,hc.getCellStyle());
        		setRegionStyle(sh, region , cs);//设置合并后单元格的边框
            	ce.objWorkbook.getSheetAt(maxSheetIndex).addMergedRegion(new Region(
            						(short) LeftTopRow,(short) LeftTopCol, 
            						(short) RightBottomRow,(short) RightBottomCol));
    		}
    		
//    		short LeftTopRow = (short)(dc.head_unite_start-1);
//        	short LeftTopCol = (short)0;
//        	short RightBottomRow = (short)(dc.head_unite_end-1);
//        	short RightBottomCol = (short)(tableWidth -1);
//        	HSSFSheet sh = ce.objWorkbook.getSheetAt(maxSheetIndex);
//    		Region region = new Region((short) LeftTopRow,(short) LeftTopCol, 
//					(short) RightBottomRow,(short) RightBottomCol);
//    		HSSFCell hc = sh.getRow(LeftTopRow).getCell(LeftTopCol);
//    		HSSFCellStyle cs = ce.cloneCellStyle(ce.objWorkbook,hc.getCellStyle());
//    		setRegionStyle(sh, region , cs);//设置合并后单元格的边框
//        	ce.objWorkbook.getSheetAt(maxSheetIndex).addMergedRegion(new Region(
//        						(short) LeftTopRow,(short) LeftTopCol, 
//        						(short) RightBottomRow,(short) RightBottomCol));
    	}
    	
    	//写文件
//    	FileOutputStream fos= new FileOutputStream("E:/dycolum_test22.xls");
//    	ce.objWorkbook.write(fos);
//    	fos.close();
    	
    	//合并表尾的信息行
    	if(dc.foot_unite_start>0 && dc.foot_unite_end>0)
    	{
    		//合并信息-表尾信息
//    		ce.copyRange(sheetIndex, dc.foot_unite_start-1,0, dc.foot_unite_end-1, tableWidth-1, maxSheetIndex, dc.foot_unite_start-1, 0);
    		
    		for(int i=dc.foot_unite_start ; i<dc.foot_unite_end+1;i++)
    		{
    			short LeftTopRow = (short)(i-1);
        		short LeftTopCol = (short)0;
        		short RightBottomRow = (short)(i-1);
        		short RightBottomCol = (short)(tableWidth -1);
        		HSSFSheet sh = ce.objWorkbook.getSheetAt(maxSheetIndex);
        		Region region = new Region((short) LeftTopRow,(short) LeftTopCol, 
    					(short) RightBottomRow,(short) RightBottomCol);
        		HSSFCell hc = sh.getRow(LeftTopRow).getCell(LeftTopCol);
        		HSSFCellStyle cs = ce.cloneCellStyle(ce.objWorkbook,hc.getCellStyle());
        		setRegionStyle(sh, region , cs);//设置合并后单元格的边框
            	ce.objWorkbook.getSheetAt(maxSheetIndex).addMergedRegion(region);
    		}
    		
//    		short LeftTopRow = (short)(dc.foot_unite_start-1);
//    		short LeftTopCol = (short)0;
//    		short RightBottomRow = (short)(dc.foot_unite_end-1);
//    		short RightBottomCol = (short)(tableWidth -1);
//    		HSSFSheet sh = ce.objWorkbook.getSheetAt(maxSheetIndex);
//    		Region region = new Region((short) LeftTopRow,(short) LeftTopCol, 
//					(short) RightBottomRow,(short) RightBottomCol);
//    		HSSFCell hc = sh.getRow(LeftTopRow).getCell(LeftTopCol);
//    		HSSFCellStyle cs = ce.cloneCellStyle(ce.objWorkbook,hc.getCellStyle());
//    		setRegionStyle(sh, region , cs);//设置合并后单元格的边框
//        	ce.objWorkbook.getSheetAt(maxSheetIndex).addMergedRegion(region);
    	}
    	
    	//删除原有的sheet，建立新的sheet，最普通的sheet没有合并和拆分
    	String OsheetName = ce.objWorkbook.getSheetName(sheetIndex);
    	ce.objWorkbook.removeSheetAt(sheetIndex);
    	ce.objWorkbook.createSheet(OsheetName);
    	ce.objWorkbook.setSheetOrder(OsheetName, sheetIndex);
    	ce.objWorkbook.setSelectedTab(sheetIndex);
    	
    	//完成动态列操作的sheet拷贝到原来sheet中
    	ce.copySheet(ce.objWorkbook,  tmpSheetName, ce.objWorkbook, ce.objWorkbook.getSheetName(sheetIndex));
    	
    	//删除临时建立的sheet，保持原有的excel配置
//    	ce.deleteSheet(tmpSheetName);
    	
    	return ce.objWorkbook;
	}
	
	public DyColum getDyColumForExcel(HSSFSheet objSheet) throws Exception
	{
		//读取模板配置信息获取第一行的配置信息
    	//HSSFSheet sheet = hfsheet;//ce.objWorkbook.getSheetAt(sheetIndex);
        HSSFRow fistRow = objSheet.getRow(0);
        if(fistRow == null)
        {
        	 throw new Exception("没有定义配置信息");
        }
        HSSFCell fistCell;
        if(( fistCell=fistRow.getCell((short)0)) == null)
        {
             throw new Exception("没有定义配置信息");
        }
        String configInfoXml = fistCell.getRichStringCellValue().getString();
        DyColum dc = getConfigInfoDycolum(configInfoXml);
        return dc;
	}
	
	/**
	 * 动态列Excel处理
	 * @param excelPath excel绝对路径
	 * @param sheetIndex excel文件sheet的索引
	 * @param dyColumSelectids DyTableInfo中的id 
	 * @return HSSFWorkbook 返回处理后的Excel工作薄
	 * @throws Exception
	 */
	public HSSFWorkbook dyColumConver(String excelPath,int sheetIndex,String[] dyColumSelectids) throws Exception
	{
		//打开Excel文件
		FileInputStream fs = new FileInputStream(excelPath);
		HSSFWorkbook hwb = new HSSFWorkbook(fs);
        HSSFSheet objSheet = hwb.getSheetAt(0);
        fs.close();
        
        return dyColumConver(hwb,objSheet,dyColumSelectids);
	}
	
	/**
	 * http://blog.csdn.net/xieyf_0413/archive/2007/12/14/1936892.aspx
	 * 提供由于合并单元格后没有边框，该方法可以解决。
	 * @param sheet
	 * @param region
	 * @param cs
	 */
    public void setRegionStyle(HSSFSheet sheet, Region region , HSSFCellStyle cs) {
        int toprowNum = region.getRowFrom();
        for (int i = region.getRowFrom(); i <= region.getRowTo(); i ++) {
            HSSFRow row = HSSFCellUtil.getRow(i, sheet);
            for (int j = region.getColumnFrom(); j <= region.getColumnTo(); j++) {
                HSSFCell cell = HSSFCellUtil.getCell(row, (short)j);
                cell.setCellStyle(cs);
            }
        }
 }
    
    public static void main(String[] args) throws Exception
    {
    	CommonExcel ce = new CommonExcel();
    	String excelPath = "E:\\dycolumn_test.xls";
    	int sheetindex = 0;
    	
    	String[] dyColumSelectids = {"1220","1222","1221"};
    	HSSFWorkbook hwb = ce.dyColumConver(excelPath,sheetindex,dyColumSelectids);
    	
    	//保存修改过的excel文件
    	FileOutputStream fs = new FileOutputStream(excelPath);
    	hwb.write(fs);
    	fs.close();
    	
    	/*//合并单元格
    	//ce.setMergeRegion(leftTopRow, leftTopCol, rightBottomRow, rightBottomCol);
    	ce.open("D:\\poi\\filename.xls");
    	ce.setMergeRegion(17, 0, 18, 0);
    	ce.setMergeRegion(17, 1, 18, 1);
    	ce.setMergeRegion(19, 0, 21, 0);
    	ce.setMergeRegion(22, 0, 23, 0);
    	ce.setMergeRegion(22, 1, 23, 1);
    	ce.save("D:\\poi\\filename2.xls");
    	*/
    	
    	//复制一个区域
    	//ce.copyRange(originSheetIndex, originLeftTopRow, originLeftTopCol, originRightBottomRow, originRightBottomCol, targetSheetIndex, targetLeftTopRow, targetLeftTopCol)
    	//ce.copyRange(originSheetName, originLeftTopCellName, originRightBottomCellName, targetSheetName, targetLeftTopCellName)
    	//ce.copySheet(originWorkBook, pSourceSheetName, targetWorkBook, pTargetSheetName);
    	
    	//删除临时sheet 不知道为什么 必须要从新打开才能删除保存，如果不这么做就会破坏Excel数据导致Excel不能打开或者是打开报错
//    	CommonExcel ce2 = new CommonExcel();
//    	ce2.open("E:\\statistic-config-excel-commonfault-std_oracle.xls");
//    	ce2.deleteSheet(tmpSheetName);
//    	ce2.save("E:\\statistic-config-excel-commonfault-std_oracle.xls");
    }
}

/**
 * 动态列信息
 * @author 李振友
 *
 */
class DyColum {
	public int start_row; //动态部分起始行号
	public int end_row;//动态部分结束行号
	public int front_start_colum;//前静态部分起始列号
	public int front_end_colum;//前静态部分结束列号
	public int dynamic_start_colum;//动态部分起始列号
	public int dynamic_end_colum;//动态部分结束列号
	public int back_start_colum;//后静态部分起始列号
	public int back_end_colum;//后静态部分结束列号
	
	public int head_unite_start;//表头部分动态列计算完毕后合并列开始行
	public int head_unite_end;//表头部分动态列计算完毕后合并列结束行
	public int foot_unite_start;//表尾部分动态列计算完毕后合并列开始行
	public int foot_unite_end;//表尾部分动态列计算完毕后合并列结束行
	
	public String cls;
	
	public DyColum() {}
	
	public DyColum(String start_row ,String end_row,String front_start_colum,String front_end_colum,
			String dynamic_start_colum,String dynamic_end_colum,
			String back_start_colum,String back_end_colum,
			String head_unite_start,String head_unite_end,String foot_unite_start,String foot_unite_end,
			String cls) 
	{
		this.start_row = Integer.parseInt(start_row);
		this.end_row = Integer.parseInt(end_row);
		this.front_start_colum = Integer.parseInt(front_start_colum);
		this.front_end_colum = Integer.parseInt(front_end_colum);
		this.dynamic_start_colum = Integer.parseInt(dynamic_start_colum);
		this.dynamic_end_colum = Integer.parseInt(dynamic_end_colum);
		this.back_start_colum = Integer.parseInt(back_start_colum);
		this.back_end_colum = Integer.parseInt(back_end_colum);
		this.head_unite_start = Integer.parseInt(head_unite_start);
		this.head_unite_end = Integer.parseInt(head_unite_end);
		this.foot_unite_start = Integer.parseInt(foot_unite_start);
		this.foot_unite_end = Integer.parseInt(foot_unite_end);
		this.cls=cls;
	}
}

class Table{
//	 <table width="10" head_start="3" head_end="5" body_start="6" body_end="7" foot_start="12" foot_end="19"/>
	public int width;
	public int head_start;
	public int head_end;
	public int body_start;
	public int body_end;
	public int foot_start;
	public int foot_end;
	
	public Table()
	{}
	
	public Table(String width,String head_start,String head_end,
			String body_start,String body_end,String foot_start,String foot_end)
	{
		this.width = Integer.parseInt(width);
		this.head_start = Integer.parseInt(head_start);
		this.head_end = Integer.parseInt(head_end);
		this.body_start = Integer.parseInt(body_start);
		this.body_end = Integer.parseInt(body_end);
		this.foot_start = Integer.parseInt(foot_start);
		this.foot_end = Integer.parseInt(foot_end);
	}
}
