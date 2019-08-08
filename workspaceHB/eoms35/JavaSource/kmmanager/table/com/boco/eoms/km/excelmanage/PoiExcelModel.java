package com.boco.eoms.km.excelmanage;

/**
 * <p>Title:excel管理定义工具</p>
 * <p>Description: excel格式信息对象</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: boco </p>
 *
 * @author 白鹏
 * @version 1.0
 */

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.File;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;

import org.apache.commons.beanutils.PropertyUtils;
import org.jdom.Element;

import com.boco.eoms.common.util.StaticMethod;


public class PoiExcelModel {

    private String excelName; //excel格式文件标识
    private String dbClass; //对应的数据封装对象

    private Hashtable headHash; //excel文件头部分信息
    private Hashtable bodyHash; //excel文件数据部分信息
    private Hashtable rowHash; //excel行的序列
    private Hashtable fontHash; //excel字体信息集合

    private int headCount = 0; //头部分信息中的单元格信息的数量
    private int bodyCount = 0; //数据部分信息中的单元格信息的数量

    private String startRow = "0"; //数据部分的开始行的位置
    private String cellCount = "0"; //数据部分的单一数据占用的行数

    private int currentRow = 0; //在生成excel时，控制当前行数
    private HSSFWorkbook wb = null; //生成excel时，excel对象
    private int sequence = 0; //生成excel时，序列的数据
    private Hashtable tempHash = null; //生成excel时，传递的临时数据

    /**
     * 初始化excel格式信息对象
     *
     * @param _excelName excel格式文件标识
     */
    public PoiExcelModel(String _excelName) {
        excelName = _excelName;
        headHash = new Hashtable();
        bodyHash = new Hashtable();
        rowHash = new Hashtable();
        fontHash = new Hashtable();
    }

    /**
     * 装载头部分信息中的单元格信息对象
     *
     * @param _poiStyle 单元格信息对象
     */
    public void setHeadInfo(PoiStyle _poiStyle) {
        headHash.put(String.valueOf(headCount++), _poiStyle);

        Object object = rowHash.get(String.valueOf(_poiStyle.getYPort()));

        Hashtable hashtable = null;

        if (object == null) {
            hashtable = new Hashtable();
        } else {
            hashtable = (Hashtable) object;
        }

        hashtable.put(String.valueOf(_poiStyle.getXPort()), _poiStyle);
        rowHash.put(String.valueOf(_poiStyle.getYPort()), hashtable);

    }

    /**
     * 获取头部分信息中的单元格信息对象
     *
     * @param _row 获取的集合中的位置
     * @return 单元格信息对象
     */
    public PoiStyle getHeadPoiStyle(int _row) {
        return (PoiStyle) headHash.get(String.valueOf(_row));
    }

    /**
     * 装载数据部分信息中的单元格信息对象
     *
     * @param _poiStyle 单元格信息对象
     */
    public void setBodyInfo(PoiStyle _poiStyle) {
        bodyHash.put(String.valueOf(bodyCount++), _poiStyle);
        Object object = rowHash.get(String.valueOf(_poiStyle.getYPort()));

        Hashtable hashtable = null;

        if (object == null) {
            hashtable = new Hashtable();
        } else {
            hashtable = (Hashtable) object;
        }

        hashtable.put(String.valueOf(_poiStyle.getXPort()), _poiStyle);
        rowHash.put(String.valueOf(_poiStyle.getYPort()), hashtable);

    }

    public void setBodyInfo(PoiStyle _poiStyle, int _row) {
        bodyHash.put(String.valueOf(bodyCount++), _poiStyle);
        Object object = rowHash.get(String.valueOf(_row));

        Hashtable hashtable = null;

        if (object == null) {
            hashtable = new Hashtable();
        } else {
            hashtable = (Hashtable) object;
        }

        hashtable.put(String.valueOf(_poiStyle.getXPort()), _poiStyle);
        rowHash.put(String.valueOf(_row), hashtable);

    }

    /**
     * 获取数据部分信息中的单元格信息对象
     *
     * @param _row 获取的集合中的位置
     * @return 单元格信息对象
     */
    public PoiStyle getBodyPoiStyle(int _row) {
        return (PoiStyle) bodyHash.get(String.valueOf(_row));
    }

    /**
     * 获取单元格的格式信息对象
     *
     * @param _row 行位置
     * @param _col 列位置
     * @return 格式信息对象
     */
    public PoiStyle getRowPoiStyle(int _row, int _col) {

        PoiStyle poiStyle = null;
        Hashtable hashtable = null;

        Object object = this.rowHash.get(String.valueOf(_row));

        if (object != null) {
            hashtable = (Hashtable) object;
            if (hashtable.get(String.valueOf(_col)) != null)
                poiStyle = (PoiStyle) hashtable.get(String.valueOf(_col));
        }
        return poiStyle;
    }

    /**
     * 获取excel格式文件标识
     *
     * @return excel格式文件标识
     */
    public String getExcelName() {
        return excelName;
    }

    /**
     * 获取头部分信息中的单元格信息的数量
     *
     * @return 单元格信息的数量
     */
    public int getHeadCount() {
        return headHash.size();
    }

    /**
     * 获取数据部分信息中的单元格信息的数量
     *
     * @return 单元格信息的数量
     */
    public int getBodyCount() {
        return this.bodyHash.size();
    }

    /**
     * 设置数据封装对象
     *
     * @param _dbClass 对象的class名称
     */
    public void setDbClass(String _dbClass) {
        this.dbClass = _dbClass;
    }

    /**
     * 获取数据封装对象
     *
     * @return 对象的class名称
     */
    public String getDbClass() {
        return this.dbClass;
    }

    /**
     * 获取数据部分的开始行的位置
     *
     * @return 当前的行位置
     */
    public String getStartRow() {
        return startRow;
    }

    /**
     * 设置数据部分的开始行的位置
     *
     * @param startRow 当前的行位置
     */
    public void setStartRow(String startRow) {
        this.startRow = startRow;
    }

    /**
     * 获取数据部分的单一数据占用的行数
     *
     * @return 占用的行数
     */
    public String getCellCount() {
        return cellCount;
    }

    /**
     * 设置数据部分的单一数据占用的行数
     *
     * @param cellCount 占用的行数
     */
    public void setCellCount(String cellCount) {
        this.cellCount = cellCount;
    }

    public String creatExcel(List _data) {
        return creatExcel(_data, null);
    }

    /**
     * 创建Excel文件
     *
     * @return 创建的Excel文件名
     */
    public String creatExcel(List _data, Hashtable _hashtable) {
        try {

            String fileName = PoiExcel.configFile + File.separator + StaticMethod.getCurrentDateTime("yyyyMMddHHmmss") +
                    ".xls";
            //modified by zyg for 创建文件夹
            if (!(new File(PoiExcel.configFile).isDirectory())) {
                new File(PoiExcel.configFile).mkdir();
            }

            FileOutputStream fos = new FileOutputStream(fileName); //创建文件

            wb = new HSSFWorkbook();

            HSSFSheet s = wb.createSheet();
            wb.setSheetName(0, "sheet1", HSSFWorkbook.ENCODING_UTF_16); //创建sheet对象

            //初始化基础信息
            this.sequence = 0;

            if (_hashtable != null) {
                this.tempHash = _hashtable;
            }

            //创建表头信息
            creatHeadInfo(s);

            //初始数据主体的开始行数
            this.currentRow = Integer.parseInt(this.getStartRow());

            //创建主体信息
            creatBodyInfo(s, _data);

            wb.write(fos); //写入数据信息
            fos.close(); //关闭文件流
            return fileName; //返回文件名
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 创建Excel文件的表头部分
     *
     * @param _sheet Excel中的sheet对象
     */
    private void creatHeadInfo(HSSFSheet _sheet) {
        HSSFRow row;
        HSSFCell cell;
        HSSFCellStyle cs;

        //循环读取头格式的各行配置信息
        for (int i = 0; i < getHeadCount(); i++) {

            PoiStyle poiStyle = getHeadPoiStyle(i); //获取当前行的格式对象

            row = _sheet.createRow(poiStyle.getYPort()); //创建一个新的row行对象

            cell = row.createCell((short) poiStyle.getXPort()); //创建一个新的cell单元格对象
            cell.setEncoding(cell.ENCODING_UTF_16); //防止中文问题。
            cell.setCellValue(poiStyle.getTitle()); //添加单元格中显示的内容

            setCellValue(cell, poiStyle, null);

            cs = getCellStyel(poiStyle); //生成该行格式的风格对象
            cell.setCellStyle(cs); //配置单元格的风格

            Region region = new Region(poiStyle.getYPort(),
                    (short) poiStyle.getXPort(),
                    poiStyle.getHigth() + poiStyle.getYPort(),
                    (short) (poiStyle.getWidth() +
                            poiStyle.getXPort()));

            _sheet.addMergedRegion(region);
        }
    }

    /**
     * 创建Excel文件的主体部分
     *
     * @param _sheet Excel中的sheet对象
     * @param _list  要生成的数据集合
     */
    private void creatBodyInfo(HSSFSheet _sheet, List _list) {

        HSSFRow row;
        HSSFCell cell;
        HSSFCellStyle cs;

        try {
            //循环显示各行数据信息
            for (int j = 0; j < _list.size(); j++) {

                Object object = _list.get(j); //获取数据对象

                //如果该数据对象为空，则结束本次循环
                if (object == null) {
                    continue;
                }

                for (int i = 0; i < getBodyCount(); i++) {

                    PoiStyle poiStyle = getBodyPoiStyle(i); //获取当前行的格式对象

                    row = _sheet.createRow(currentRow + poiStyle.getYPort()); //创建一个新的row行对象

                    cell = row.createCell((short) poiStyle.getXPort()); //创建一个新的cell单元格对象
                    cell.setEncoding(cell.ENCODING_UTF_16); //防止中文问题。

                    setCellValue(cell, poiStyle, object);

                    cs = getCellStyel(poiStyle); //生成该行格式的风格对象
                    cell.setCellStyle(cs); //配置单元格的风格

                    Region region = new Region(poiStyle.getYPort() + currentRow,
                            (short) poiStyle.getXPort(),
                            poiStyle.getHigth() + poiStyle.getYPort() +
                                    currentRow,
                            (short) (poiStyle.getWidth() +
                                    poiStyle.getXPort()));

                    _sheet.addMergedRegion(region);

                }

                currentRow = currentRow + Integer.parseInt(this.getCellCount()) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建单元格的风格对象
     *
     * @param poiStyle Excel风格信息对象
     * @return 风格对象
     */
    private HSSFCellStyle getCellStyel(PoiStyle poiStyle) {

        HSSFCellStyle cs = wb.createCellStyle(); //创建一个style

        HSSFFont littleFont = null;

        if (this.fontHash.get(poiStyle.getTitle()) == null) {

            littleFont = wb.createFont(); //创建一个Font

            littleFont.setFontName(poiStyle.getFont()); //字体
            littleFont.setFontHeightInPoints(Short.parseShort(poiStyle.getSize())); //大小
            littleFont.setColor((short) poiStyle.getColor()); //颜色

            if (poiStyle.getBold().equalsIgnoreCase("Y")) {
                littleFont.setBoldweight(littleFont.BOLDWEIGHT_BOLD); //粗体
            }

            fontHash.put(poiStyle.getTitle(), littleFont);

        } else {
            littleFont = (HSSFFont) fontHash.get(poiStyle.getTitle());
        }
        cs.setFont(littleFont); //设置字体

        cs.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
        cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //垂直举重

        return cs;

    }

    public void setCellValue(HSSFCell cell, PoiStyle poiStyle, Object object) {

        //属性分为几类
        //1 正常属性，是从object中获取的属性
        //2 以%开始的，
        try {

            String value = "";

            if (poiStyle.getProper().indexOf("%") == 0) {
                if (poiStyle.getProper().indexOf("%array1") >= 0) {

                    String proper = poiStyle.getProper().substring(poiStyle.getProper().
                            indexOf("_") + 1, poiStyle.getProper().indexOf("["));

                    String[] temp = (String[]) PropertyUtils.getSimpleProperty(object,
                            proper);
                    String arrayX = poiStyle.getProper().substring(poiStyle.getProper().
                            indexOf("[") + 1, poiStyle.getProper().indexOf("]"));
                    int x = Integer.parseInt(arrayX);

                    value = String.valueOf(temp[x]);
                } else if (poiStyle.getProper().indexOf("%array2") >= 0) {

                    String proper = poiStyle.getProper().substring(poiStyle.getProper().
                            indexOf("_") + 1, poiStyle.getProper().indexOf("["));

                    String[][] temp = (String[][]) PropertyUtils.getSimpleProperty(object,
                            proper);

                    String[] arrayXY = poiStyle.getProper().substring(poiStyle.
                            getProper().indexOf("[") + 1, poiStyle.getProper().indexOf("]")).
                            split(",");
                    int x = Integer.parseInt(arrayXY[0]);
                    int y = Integer.parseInt(arrayXY[1]);

                    value = String.valueOf(temp[x][y]);

                } else if (poiStyle.getProper().indexOf("%sequence") >= 0) {
                    value = String.valueOf(++this.sequence);
                } else if (poiStyle.getProper().indexOf("%currentdate") >= 0) {
                    value = String.valueOf(StaticMethod.getCurrentDateTime());
                }
            } else if (poiStyle.getProper().indexOf("#") == 0) {
                if (tempHash != null) {
                    value = (String) tempHash.get(poiStyle.getProper().substring(1,
                            poiStyle.getProper().length()));
                }
            } else if (poiStyle.getProper().equals("")) {
                value = poiStyle.getTitle();
            } else {
                String[] temp = poiStyle.getProper().split(",");
                for (int i = 0; i < temp.length; i++) {
                    value = value + "," +
                            PropertyUtils.getSimpleProperty(object, temp[i]).toString(); //获取数据，此处如果temp[i]属性对应值为空，就会报空指针的错 --xudongsuo
                }
                value = value.substring(1, value.length());
            }

            cell.setCellValue(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Element getPoiExcelModelXML() {
        Element element = new Element("excel");
        element.setAttribute("name", this.getExcelName());
        if (this.getDbClass() == null) {
            element.setAttribute("dbclass", "");
        } else {
            element.setAttribute("dbclass", this.getDbClass());
        }
        element.addContent(getHeadXML());
        element.addContent(getBodyXML());
        return element;
    }

    private Element getHeadXML() {
        Element element = new Element("head");
        Element rowElement = new Element("row");

        element.addContent(rowElement);

        PoiStyle poiStyle = null;

        for (int i = 0; i < headHash.size(); i++) {
            poiStyle = (PoiStyle) headHash.get(String.valueOf(i));
            rowElement.addContent(poiStyle.createPoiStyleToXML());
        }

        return element;
    }

    private Element getBodyXML() {
        Element element = new Element("body");

        element.setAttribute("startrow", this.getStartRow());
        element.setAttribute("cellcount", this.getCellCount());

        Element rowElement = new Element("row");

        element.addContent(rowElement);

        PoiStyle poiStyle = null;

        for (int i = 0; i < bodyHash.size(); i++) {
            poiStyle = (PoiStyle) bodyHash.get(String.valueOf(i));
            rowElement.addContent(poiStyle.createPoiStyleToXML());
        }

        return element;
    }


}
