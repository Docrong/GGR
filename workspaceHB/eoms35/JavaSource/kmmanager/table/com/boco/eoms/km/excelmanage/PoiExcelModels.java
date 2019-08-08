package com.boco.eoms.km.excelmanage;

/**
 * <p>Title:excel管理定义工具</p>
 * <p>Description: excel格式信息对象管理容器</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: boco </p>
 *
 * @author 白鹏
 * @version 1.0
 */

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.jdom.Element;
import com.boco.eoms.common.properties.XMLProperties;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.util.Region;


public class PoiExcelModels {

    private Hashtable excelHash = null; //excel格式信息的集合

    private String configFile = "config.xml";

    public PoiExcelModels(String _configFile) {
        configFile = _configFile;
        init();
    }

    public PoiExcelModels() {
        init();
    }

    public PoiExcelModel getPoiExcelModel(String _excelName) {
        if (excelHash == null) {
            init();
        }

        PoiExcelModel poiExcelModel = (PoiExcelModel) excelHash.get(_excelName);

        return poiExcelModel;

    }

    public void init(String _configFile) {
        configFile = _configFile;
        init();
    }

    public void init() {

        excelHash = new Hashtable();

        XMLProperties properties = null;

        properties = new XMLProperties(configFile);
        // System.out.println("configFile="+configFile);
        Element element = properties.getElement();
        Element headelement = null;
        Element bodyelement = null;
        Element cellelement = null;

        List list = element.getChildren(); //获取所有Excel的格式定义元素
        List headList = null;
        List bodyList = null;
        List cellList = null;

        PoiStyle poiStyle = null;

        //解析各Excel的格式的详细内容
        System.out.println("list.size=" + list.size());
        for (int i = 0; i < list.size(); i++) {
            element = (Element) list.get(i);

            PoiExcelModel poiExcelModel = null;

            if (!element.getAttribute("name").getValue().trim().equals("")) {
                poiExcelModel = new PoiExcelModel(element.getAttribute("name").getValue().
                        trim());
                excelHash.put(element.getAttribute("name").getValue().trim(),
                        poiExcelModel);
            }

            //获取数据封装类名称
            if (!element.getAttribute("dbclass").getValue().trim().equals("")) {
                poiExcelModel.setDbClass(element.getAttribute("dbclass").getValue().
                        trim());
            }

            //分析头格式信息
            headList = element.getChild("head").getChildren("row");

            for (int j = 0; j < headList.size(); j++) {
                headelement = (Element) headList.get(j);

                cellList = headelement.getChildren("col");

                for (int k = 0; k < cellList.size(); k++) {

                    cellelement = (Element) cellList.get(k);
                    poiStyle = PoiStyle.creatPoiStyleFormXML(cellelement);
                    poiExcelModel.setHeadInfo(poiStyle);

                }
            }

            //获取数据主体信息的初始行数
            String startRow = element.getChild("body").getAttribute("startrow").
                    getValue().trim();
            poiExcelModel.setStartRow(startRow);

            //获取数据主体信息单一数据所占的行数
            String cellCount = element.getChild("body").getAttribute("cellcount").
                    getValue().trim();
            poiExcelModel.setCellCount(cellCount);

            //分析主体格式信息
            bodyList = element.getChild("body").getChildren("row");

            for (int j = 0; j < bodyList.size(); j++) {
                bodyelement = (Element) bodyList.get(j);

                cellList = bodyelement.getChildren("col");

                for (int k = 0; k < cellList.size(); k++) {

                    cellelement = (Element) cellList.get(k);
                    poiStyle = PoiStyle.creatPoiStyleFormXML(cellelement);
                    poiExcelModel.setBodyInfo(poiStyle);

                }
            }

            excelHash.put(poiExcelModel.getExcelName(), poiExcelModel);
        }
    }

    public void getPoiExcelModelFormExcel() {

        XMLProperties properties = null;
        properties = new XMLProperties(configFile);
        Element element = properties.getElement();

        POIFSFileSystem fs = null;
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
        HSSFRow row = null;
        HSSFCell cell = null;

        Region region = null;

        PoiStyle poiStyle = null;
        PoiExcelModel poiExcelModel = null;

        Iterator iteratorRow = null;
        Iterator iteratorCell = null;
        int cellLastInt = 0;
        int regionInt = 0;

        try {
            fs = new POIFSFileSystem(new FileInputStream(
                    "D:\\test.xls"));
            wb = new HSSFWorkbook(fs);
        } catch (java.io.FileNotFoundException e) {
            System.out.println("文件没有找到");
            System.exit(0);
        } catch (java.io.IOException e) {
            System.out.println("读取文件错误");
            System.exit(0);
        }

        int sheetCount = wb.getNumberOfSheets(); //sheet的数量

        for (int i = 0; i < sheetCount; i++) {
            sheet = wb.getSheetAt(i);

            if (wb.getSheetName(i).indexOf("(") >= 0) {
                poiExcelModel = new PoiExcelModel(wb.getSheetName(i).substring(0,
                        wb.getSheetName(i).indexOf("(")));
                String temp = wb.getSheetName(i).substring(wb.getSheetName(i).indexOf(
                        "(") + 1, wb.getSheetName(i).length() - 1);
                String[] a = temp.split(",");

                poiExcelModel.setStartRow(a[0]);
                poiExcelModel.setCellCount(a[1]);
            } else {
                continue;
            }

            regionInt = sheet.getNumMergedRegions();

            iteratorRow = sheet.rowIterator();

            while (iteratorRow.hasNext()) {
                row = (HSSFRow) iteratorRow.next();

                cellLastInt = row.getLastCellNum();

                for (int j = 0; j < cellLastInt; j++) {

                    cell = row.getCell((short) j);

                    if (cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK) {

                        if (cell.getStringCellValue().indexOf("t(") == 0) {
                            poiStyle = PoiStyle.creatPoiStyleFormExcel(cell, wb, j,
                                    row.getRowNum());
                            poiExcelModel.setHeadInfo(poiStyle);
                        } else {
                            poiStyle = PoiStyle.creatPoiStyleFormExcel(cell, wb, j,
                                    row.getRowNum() - Integer.parseInt(poiExcelModel.getStartRow()));
                            poiExcelModel.setBodyInfo(poiStyle, row.getRowNum());
                        }
                    }
                }
            }

            for (int k = 0; k < regionInt; k++) {
                region = sheet.getMergedRegionAt(k);

                poiStyle = poiExcelModel.getRowPoiStyle(region.getRowFrom(),
                        region.getColumnFrom());
                PoiStyle.creatPoiStyleFormExcel(region, poiStyle);
                region = null;
            }

            excelHash.put(poiExcelModel.getExcelName(), poiExcelModel);
        }
    }

    public void creatPoiXML(PoiExcelModel _poiExcelModel) {
        XMLProperties properties = null;
        properties = new XMLProperties(configFile);

        Element element = properties.getElement();

        element.addContent(_poiExcelModel.getPoiExcelModelXML());

        properties.saveProperties();
    }

    public static String combineExcel(List _pathFile, String _modelExcel, String _combineExcel) {

        POIFSFileSystem fs = null;
        POIFSFileSystem fsTemp = null;
        HSSFWorkbook wb = null;
        HSSFWorkbook wbTemp = null;
        HSSFSheet sheet = null;
        HSSFSheet sheetTemp = null;
        HSSFRow row = null;
        HSSFRow rowTemp = null;
        HSSFCell cell = null;
        HSSFCell cellTemp = null;
        Iterator iteratorRow = null;
        FileOutputStream fos = null;

        int startPort = 0;
        int tempPort = 0;
        int currentPort = 0;
        try {
            fs = new POIFSFileSystem(new FileInputStream(_modelExcel));
            fos = new FileOutputStream(_combineExcel);

            wb = new HSSFWorkbook(fs);
        } catch (java.io.FileNotFoundException e) {
//e.pritStackTrace();
            System.out.println("文件没有找到");
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.out.println("读取文件错误");
        }

        sheet = wb.getSheetAt(0);

        startPort = sheet.getPhysicalNumberOfRows();
        currentPort = startPort;


        tempPort = sheet.getPhysicalNumberOfRows();

        try {
            for (int i = 0; i < _pathFile.size(); i++) {
                fsTemp = new POIFSFileSystem(new FileInputStream((String.valueOf(
                        _pathFile.get(i)))));
                wbTemp = new HSSFWorkbook(fsTemp);

                sheetTemp = wbTemp.getSheetAt(0);
                tempPort = sheetTemp.getPhysicalNumberOfRows() - 1;
                System.out.println("tempPort = " + tempPort);
                for (int j = startPort; j <= tempPort; j++) {
                    rowTemp = sheetTemp.getRow(j);
                    row = sheet.createRow(currentPort++);
                    for (int k = 0; k < rowTemp.getPhysicalNumberOfCells(); k++) {
                        cell = row.createCell((short) k);
                        cellTemp = rowTemp.getCell((short) k);

                        switch (cellTemp.getCellType()) {
                            case HSSFCell.CELL_TYPE_STRING:
                                cell.setCellValue(cellTemp.getStringCellValue());
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                cell.setCellValue(cellTemp.getNumericCellValue());
                                break;
                        }
                    }
                }
            }
            wb.write(fos); //写入数据信息
            fs.writeFilesystem(fos);
            fos.close(); //关闭文件流
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件没有找到");
            System.exit(0);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.out.println("读取文件错误");
            System.exit(0);
        }

        return null;
    }

    public static void main(String[] args) {
        List _dbList = new java.util.ArrayList();
        for (int j = 0; j < 100; j++) {
            PoiDBModel poiDBModel = new PoiDBModel();

            poiDBModel.setDeptName("部门" + j);
            poiDBModel.setDayExec(j);
            poiDBModel.setDayPlan(j);
            poiDBModel.setWeekExec(j);
            poiDBModel.setWeekPlan(j);
            poiDBModel.setHalfExec(j);
            poiDBModel.setHalfPlan(j);
            poiDBModel.setMonthExec(j);
            poiDBModel.setMonthPlan(j);
            poiDBModel.setThreeExec(j);
            poiDBModel.setThreePlan(j);
            poiDBModel.setHalfyearExec(j);
            poiDBModel.setHalfyearPlan(j);
            poiDBModel.setYearExec(j);
            poiDBModel.setYearPlan(j);

            _dbList.add(poiDBModel);
        }

        //PoiExcelModels poiExcelModels = new PoiExcelModels();
        // poiExcelModels.getPoiExcelModelFormExcel();
        //PoiExcelModel poiExcelModel = poiExcelModels.getPoiExcelModel("test");
        //poiExcelModels.creatPoiXML(poiExcelModel);

        //System.out.println(poiExcelModel.creatExcel(_dbList));
        List temp = new ArrayList();
        temp.add("D:\\testexcel\\testexcel_01.xls");
        temp.add("D:\\testexcel\\testexcel_02.xls");
        PoiExcelModels.combineExcel(temp, "D:\\testexcel\\testexcel.xls", "D:\\testexcel\\testexcel_com.xls");
    }

}
