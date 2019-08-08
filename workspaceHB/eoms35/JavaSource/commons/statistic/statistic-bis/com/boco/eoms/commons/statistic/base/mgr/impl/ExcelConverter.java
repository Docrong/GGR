package com.boco.eoms.commons.statistic.base.mgr.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;

import com.boco.eoms.commons.statistic.base.config.excel.Excel;
import com.boco.eoms.commons.statistic.base.config.excel.Sheet;
import com.boco.eoms.commons.statistic.base.config.excel.Table;
import com.boco.eoms.commons.statistic.base.config.excel.TableBody;
import com.boco.eoms.commons.statistic.base.config.excel.TableCell;
import com.boco.eoms.commons.statistic.base.config.excel.TableCellFont;
import com.boco.eoms.commons.statistic.base.config.excel.TableCellLink;
import com.boco.eoms.commons.statistic.base.config.excel.TableColWidth;
import com.boco.eoms.commons.statistic.base.config.excel.TableContent;
import com.boco.eoms.commons.statistic.base.config.excel.TableFoot;
import com.boco.eoms.commons.statistic.base.config.excel.TableHead;
import com.boco.eoms.commons.statistic.base.config.excel.TableRow;
import com.boco.eoms.commons.statistic.base.config.model.KpiConfig;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.config.model.SummaryDefine;
import com.boco.eoms.commons.statistic.base.excelutil.util.CommonExcel;
import com.boco.eoms.commons.statistic.base.exception.Excel2XmlException;
import com.boco.eoms.commons.statistic.base.reference.ParseXmlService;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;
import com.boco.eoms.commons.statistic.base.util.Constants;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;
import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil.SumarrayUniteConfig;

/**
 * @author lizhenyou
 * <p>
 * 转换Excel文件为Excel对象
 * http://hi.baidu.com/amess/blog/item/f9b110170ef8fd014a90a7ac.html castor使用
 */
public class ExcelConverter {

    protected Logger logger = Logger.getLogger(this.getClass());

    /**
     * 合并汇总字段开关，false:不合并 true:合并
     */
    //private boolean sumArrayUnite = true;

    /**
     * 文件名称
     */
//        private String fileName = null;

    /**
     * Excel工作薄
     */
//        private HSSFWorkbook wb = null;

    /**
     * Excel对象
     */
//        private Excel excel = null;

    /**
     * Castor 的 Mapping路径
     */
    private String excelToHtmlMappingPath = null;

    /**
     * 解析 Excel文件
     *
     * @param fileName 文件名称
     * @throws Excel2XmlException
     */
    private Excel parseExcel(String fileName, String[] dyColumSelectids, int sheetIndex) throws Exception {
        FileInputStream fis = null;
        Excel excel = null;
        try {
            fis = new FileInputStream(fileName);
            excel = this.parseExcel(fis, fileName, dyColumSelectids, sheetIndex);
        } catch (Exception e) {
            throw e;
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

        return excel;
    }

    /**
     * 解析 Excel文件
     *
     * @param fileName 文件名称
     * @throws Excel2XmlException
     */
    private Excel parseExcel(String fileName) throws Exception {
        FileInputStream fis = null;
        Excel excel = null;
        try {
            fis = new FileInputStream(fileName);
            excel = this.parseExcel(fis, fileName);
        } catch (Exception e) {
            throw e;
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

        return excel;
    }

    /**
     * 解析Excel Stream
     *
     * @param in Stream
     * @throws Excel2XmlException
     */
    private Excel parseExcel(InputStream in, String fileName) throws Exception {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(in);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            return parseExcel(wb, fileName);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 解析Excel Stream
     *
     * @param in Stream
     * @throws Excel2XmlException
     */
    private Excel parseExcel(InputStream in, String fileName, String[] dyColumSelectids, int sheetIndex) throws Exception {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(in);
            HSSFWorkbook wb = new HSSFWorkbook(fs);

            //对动态的Excel wb 进行处理
//                        String[] dyColumSelectids = {"1220","1222"};
//                        int sheetIndex = 0;
            if (dyColumSelectids != null && dyColumSelectids.length != 0) {
                CommonExcel ce = new CommonExcel();
                HSSFSheet sheet = wb.getSheetAt(sheetIndex);
                wb = ce.dyColumConver(wb, sheet, dyColumSelectids);
                //写入文件查看修改后的excel是否正确是否正确
//                        	FileOutputStream fos= new FileOutputStream("E:/dycolum_test.xls");
//                        	wb.write(fos);
//                        	fos.close();
            }

            return parseExcel(wb, fileName);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 解析Excel Stream
     *
     * @param wb HSSFWorkbook
     * @throws Excel2XmlException
     */
    public Excel parseExcel(HSSFWorkbook wb, String fileName) throws Exception {
        try {
            int iSheets = wb.getNumberOfSheets(); //得到Excel中sheet的总数
            if (iSheets < 1) return null;

            Excel excel = new Excel();
            Sheet[] sheets = new Sheet[iSheets];
            excel.setSheets(sheets);
            excel.setFileName(fileName);

            //遍厉所有的sheet
            for (int i = 0; i < iSheets; i++) {
                sheets[i] = new Sheet();
                sheets[i].setSheetIndex(i);
                sheets[i].setSheetName(wb.getSheetName(i));
                HSSFSheet hssfSheet = wb.getSheetAt(i);
                this.parseSheet(hssfSheet, i, excel, wb);
            }

            return excel;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 解析Sheet
     *
     * @param sheet       sheet
     * @param iSheetIndex 索引
     * @throws Excel2XmlException
     */
    private void parseSheet(HSSFSheet sheet, int iSheetIndex, Excel excel, HSSFWorkbook wb) throws Exception {
        try {

            //获取第一行的配置信息
            HSSFRow fistRow = sheet.getRow(0);
            if (fistRow == null) {
                return;
            }
            HSSFCell fistCell;
            if ((fistCell = fistRow.getCell((short) 0)) == null) {
                throw new Exception("没有定义配置信息");
            }
            String configInfoXml = fistCell.getRichStringCellValue().getString();

            String quaryfilename = ExcelConverterUtil.getConfigInfoQueryfilepath(configInfoXml);
            String queryName = ExcelConverterUtil.getCongfigInfoQueryName(configInfoXml);

            //table配置
            List configInfoTables = ExcelConverterUtil.getCongfigInfo(configInfoXml);

            //合并汇总字段配置
            SumarrayUniteConfig suc = null;
            try {
                suc = ExcelConverterUtil.getConfigInfoSumarrayunite(configInfoXml);
                if (suc != null) {
                    int body_start = Integer.parseInt(String.valueOf(((Map) configInfoTables.get(0)).get(Constants.CONFIGBODY_START)));
                    int body_send = Integer.parseInt(String.valueOf(((Map) configInfoTables.get(0)).get(Constants.CONFIGBODY_END)));
                    suc.uniterows = body_send - body_start + 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("sumarrayunite 设置错误");
            }

            Sheet currentSheet = excel.getSheets()[iSheetIndex];
            currentSheet.setQueryName(queryName);
            currentSheet.setQueryFileName(quaryfilename);

            //获取最大显示行
            int showmaxrow = ExcelConverterUtil.getConfigInfoShowMaxRow(configInfoXml);
            currentSheet.setShowmaxrow(showmaxrow);

            //图形报表配置
            HSSFCell secondCell = null;
            if ((secondCell = fistRow.getCell(1)) != null) {
                String graphicsConfigInfoXml = secondCell.getRichStringCellValue().getString();
                currentSheet.setGraphicConfig(graphicsConfigInfoXml);
            }

            Table[] tables = new Table[configInfoTables.size()];
            currentSheet.setTables(tables);
            for (int i = 0; i < configInfoTables.size(); i++) {
                tables[i] = new Table();

                Map cogfigMap = (Map) configInfoTables.get(i);

                parseTableHaed(sheet, tables[i], cogfigMap, wb);

                parseTableBody(sheet, tables[i], cogfigMap, suc, showmaxrow, wb);

                parseTableFoot(sheet, tables[i], cogfigMap, wb);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    /**
     * 解析表头
     *
     * @param table
     * @param cogfigMap
     */
    private void parseTableHaed(HSSFSheet sheet, Table table, Map cogfigMap, HSSFWorkbook wb) {
        TableHead tableHead = new TableHead();
        table.setTableHead(tableHead);

        //需要处理的起始行和结束行
        int startRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGHEAD_START))) - 1;
        int endRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGHEAD_END))) - 1;
        int width = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGWIDTH)));
        table.setHead_start(startRow);
        table.setHead_end(endRow);
        parseTableContent(sheet, table, cogfigMap, tableHead, startRow, endRow, width, wb);
    }

    /**
     * 解析表体,并处理结果集中的数据
     *
     * @param table
     * @param cogfigMap
     */
    private void parseTableBody(HSSFSheet sheet, Table table, Map cogfigMap, SumarrayUniteConfig SumArrayUnite, int showmaxrow, HSSFWorkbook wb) {
        TableBody tableBody = new TableBody();
        table.setTableBody(tableBody);
        tableBody.setSumArrayUnite(SumArrayUnite);
        tableBody.setShowmaxrow(showmaxrow);

        //需要处理的起始行和结束行
        int startRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGBODY_START))) - 1;
        int endRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGBODY_END))) - 1;
        int width = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGWIDTH)));
        table.setBody_start(startRow);
        table.setBody_end(endRow);
        table.setWidth(width);
        parseTableContent(sheet, table, cogfigMap, tableBody, startRow, endRow, width, wb);
    }

    /**
     * 解析表尾
     *
     * @param table
     * @param cogfigMap
     */
    private void parseTableFoot(HSSFSheet sheet, Table table, Map cogfigMap, HSSFWorkbook wb) {
        TableFoot tableFoot = new TableFoot();
        table.setTableFoot(tableFoot);

        int startRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGFOOT_START))) - 1;
        int endRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGFOOT_END))) - 1;
        int width = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGWIDTH)));
        table.setFoot_start(startRow);
        table.setFoot_end(endRow);
        parseTableContent(sheet, table, cogfigMap, tableFoot, startRow, endRow, width, wb);
    }

    /**
     * 解析TableContent
     *
     * @param sheet        HSSFSheet
     * @param table        Table
     * @param cogfigMap    配置信息 从Excel的第1行得到
     * @param tableContent 表头,表体,表尾
     */
    private void parseTableContent(HSSFSheet sheet, Table table, Map cogfigMap, TableContent tableContent
            , int startRow, int endRow, int width, HSSFWorkbook wb) {
        //add by lizhenyou
        //width += parseSheetAndModfiy(sheet,startRow,endRow,width);
        //add end

        if (startRow <= 0 || endRow <= 0) {
            return;
        }

        Region mergedRegion = null;
        TableRow[] tableRows = new TableRow[endRow - startRow + 1];
        tableContent.setTableRows(tableRows);

//		得到每列的宽度 和总宽度
        float totalWidth = 0;
        TableColWidth[] tableColWidth = new TableColWidth[width];
        int k = 0;
        for (int w = 0; w < width; w++) {
            tableColWidth[w] = new TableColWidth();

            tableColWidth[w].setColwidth(sheet.getColumnWidth((short) w));

            if (tableColWidth[w].getColwidth() == 8) {
                tableColWidth[w].setColwidth(Constants.DEFAULT_COL_WIDTH);
            }
            k = (int) Math.round((double) tableColWidth[w].getColwidth() / 256 * 6);
            if (k > (double) tableColWidth[w].getColwidth() / 256 * 6) {
                tableColWidth[w].setColwidth(k + 1);
            } else {
                tableColWidth[w].setColwidth(k);
            }

            totalWidth += tableColWidth[w].getColwidth();
        }
        table.setTableColWidth(tableColWidth);
        table.setStyle("width:" + totalWidth + "pt");

        String unitecols = "";//记录所有的合并汇总字段
        int startcol = -1;

        for (int p = 0; p < tableRows.length; p++) {
            HSSFRow hssfRow = sheet.getRow(p + startRow);
            if (hssfRow == null) {
                continue;
            }

            tableRows[p] = new TableRow();
            tableRows[p].setCellHeight(hssfRow.getHeightInPoints());

            int cellNum = hssfRow.getLastCellNum() - hssfRow.getFirstCellNum() + 1;
            if (cellNum > width) {
                cellNum = width;
            }

            List tmpList = new ArrayList();
            for (int j = 0; j < cellNum; j++) {
                TableCell tableCell = new TableCell();
                TableCellFont tableCellFont = new TableCellFont();
                tableCell.setTableCellFont(tableCellFont);
                HSSFCell hssfCell = hssfRow.getCell(j);
                if (hssfCell == null) {
                    continue;
                }
                mergedRegion = ExcelConverterUtil.getMergedRegion(sheet, p + startRow, j);

                //处理合并单元格
                if (mergedRegion != null) {
                    if (ExcelConverterUtil.isFirstCellInRegion(mergedRegion, p + startRow, j)) {
                        if (mergedRegion.getColumnTo() - mergedRegion.getColumnFrom() > 0) {
                            tableCell.setColSpan("" + (mergedRegion.getColumnTo() - mergedRegion.getColumnFrom() + 1));
                        }
                        if (mergedRegion.getRowTo() - mergedRegion.getRowFrom() > 0) {
                            tableCell.setRowSpan("" + (mergedRegion.getRowTo() - mergedRegion.getRowFrom() + 1));
                        }
                    } else {
                        continue;
                    }
                }

                //设置单元格的属性
                tableCellFont.setStyle(ExcelConverterUtil.getCellBold(hssfCell, wb));
                tableCellFont.setColor(ExcelConverterUtil.getCellFontHexRGB(hssfCell, wb));
                tableCellFont.setFace(ExcelConverterUtil.getCellFontName(hssfCell, wb));
                tableCellFont.setSize(ExcelConverterUtil.getCellFontSize(hssfCell, wb));
                tableCell.setVAlign(ExcelConverterUtil.getCellVAlign(hssfCell));
                tableCell.setHAlign(ExcelConverterUtil.getCellAlign(hssfCell));
//				tableCell.setCellHeight(hssfRow.getHeightInPoints());
//				tableCell.setCellWidth((int)Math.round( sheet.getColumnWidth((short)j)/256*6));
                if (hssfCell.getHyperlink() != null) {
                    TableCellLink tableCellLink = new TableCellLink();
                    tableCellLink.setLink(hssfCell.getHyperlink().getAddress());
                    tableCellLink.setShowText(ExcelConverterUtil.getCellText(hssfCell));
                    tableCell.setTableCellLink(tableCellLink);
                } else {
                    tableCellFont.setShowText(ExcelConverterUtil.getCellText(hssfCell));
                }

                tmpList.add(tableCell);

                //记录所有汇总字段
                String strhssfCell = ExcelConverterUtil.getCellText(hssfCell);
                if (strhssfCell != null && strhssfCell.startsWith(Constants.$SUMMARY$)) {
                    if (startcol == -1) {
                        startcol = j + 1;
                    }
                    String summaryID = ExcelConverterUtil.getCellText(hssfCell).substring(Constants.$SUMMARY$.length());
                    unitecols = unitecols + summaryID + ",";
                }
            }

            TableCell[] tableCells = new TableCell[tmpList.size()];
            for (int q = 0; q < tmpList.size(); q++) {
                tableCells[q] = (TableCell) tmpList.get(q);
            }
            tableRows[p].setTableCells(tableCells);
        }

        if (!"".equalsIgnoreCase(unitecols) && tableContent.getSumArrayUnite() != null) {
            tableContent.getSumArrayUnite().unitecols = unitecols.split(",");
            tableContent.getSumArrayUnite().startCol = startcol;
        }
    }

    /**
     * 处理$total$标签
     *
     * @param tableContent
     * @param reLis
     */
    protected void handleTotal(TableContent tableContent, List reList) {
        List reListClone = (List) StatUtil.CloneObject(reList);
        if (tableContent == null || tableContent.getTableRows() == null || reList == null) {
            return;
        }

        for (int i = 0; i < tableContent.getTableRows().length; i++) {
            //保存当前行单元格的所有数据到Map
            Map totalMap = new HashMap();
            TableCell[] tableCells = tableContent.getTableRows()[i].getTableCells();
            for (int j = 0; j < tableCells.length; j++) {
                TableCellFont tableCellFont = tableCells[j].getTableCellFont();
                TableCellLink tableCellLink = tableCells[j].getTableCellLink();
                String tableCellString = getTableCellString(tableCellFont, tableCellLink);
                if (tableCellString.startsWith(Constants.$TOTAL$)) {
                    String str = tableCellString.substring(Constants.$TOTAL$.length());
                    String type = "";//默认为int类型
                    String expressionString = "#sumA=0" + type + ",#root.{#sumA=#sumA+#this." + str + "},#sumA";
                    Object object = new Integer(0);
                    try {
                        //把所有list中的map中的 value都转换为int类型，之后才能相加
                        for (int k = 0; k < reListClone.size(); k++) {
                            Map map = (Map) reListClone.get(k);
                            map = ExcelConverterUtil.mapValueStringToType(map, "Integer");
                        }

                        object = StatUtil.getOgnlValue(null, expressionString, reListClone);
                    } catch (Exception e) {
                        logger.info("请查看$total$标签配置是否正确");
                        e.printStackTrace();
//                                        		object = new Integer(0);
                    }
                    tableCellString = String.valueOf(object);

                    totalMap.put(str, tableCellString);
                } else if (tableCellString.startsWith(Constants.$DATA$)) {
                    String str = tableCellString.substring(Constants.$DATA$.length());
                    //调用Model得到需要显示的内容

                    System.out.println(totalMap);
                    for (int k = 0; k < reListClone.size(); k++) {
                        Map map = (Map) reListClone.get(k);
                        map = ExcelConverterUtil.mapValueStringToType(map, "Integer");
                    }
                    String fieldData = StaticMethod.null2String((String)
                                    StatUtil.getExpressionResult(totalMap, str, "0")
                            , "0");

                    tableCellString = fieldData;
                }

                if (tableCellFont != null) {
                    tableCellFont.setShowText(tableCellString);
                }

                if (tableCellLink != null) {
                    tableCellLink.setShowText(tableCellString);
                }
            }
        }
    }

    /**
     * 处理info标签
     *
     * @param tableContent
     * @param object
     */
    protected void handleInfo(TableContent tableContent, Object object) {

        if (object == null || tableContent == null || tableContent.getTableRows() == null) {
            return;
        }

        Map map = (Map) object;

        //模拟数据
//		map = new HashMap();
//		map.put("beginTime", "dsad");
//		map.put("endTime", "F10120102301");

        for (int i = 0; i < tableContent.getTableRows().length; i++) {
            TableCell[] tableCells = tableContent.getTableRows()[i].getTableCells();

            for (int j = 0; j < tableCells.length; j++) {
                TableCellFont tableCellFont = tableCells[j].getTableCellFont();
                TableCellLink tableCellLink = tableCells[j].getTableCellLink();

                setTableCellFont(tableCellFont, map);
                setTableCellLink(tableCellLink, map);
            }
        }
    }

    private String getTableCellString(TableCellFont tableCellFont, TableCellLink tableCellLink) {
        String reString = null;
        if (tableCellFont != null) {
            reString = tableCellFont.getShowText();
        }

        if (tableCellLink != null) {
            if (tableCellLink != null) {
                reString = tableCellLink.getShowText();
            }
        }
        return reString;
    }

    private void setTableCellFont(TableCellFont tableCellFont, Map map) {
        String tableCellFontStr = getTableCellString(tableCellFont, null);

        if (tableCellFontStr != null) {
            tableCellFontStr = ExcelConverterUtil.rep(map, tableCellFontStr);
            tableCellFont.setShowText(tableCellFontStr);
        }
    }

    private void setTableCellLink(TableCellLink tableCellLink, Map map) {
        String tableCellLinkStr = getTableCellString(null, tableCellLink);

        if (tableCellLinkStr != null) {
            tableCellLinkStr = ExcelConverterUtil.rep(map, tableCellLinkStr);
            tableCellLink.setShowText(tableCellLinkStr);
        }
    }

    /**
     * 处理结果集
     *
     * @param tableContent
     * @param rsList
     * @throws Exception
     */
    private void handleResult(TableContent tableContent, List rsList, String dataUrl) throws Exception {
        handleResult(tableContent, rsList, dataUrl, null);
    }

    private void handleResult(TableContent tableContent, List rsList, String dataUrl, KpiDefine kpiDefine) throws Exception {
        if (rsList == null) {
            return;
        }

        //需要复制的Row
        TableRow[] tableRows = tableContent.getTableRows();

        //总的行数
        List RowsList = new ArrayList();

        //设置最大显示行数
        int size = tableContent.getShowmaxrow() == -1 || tableContent.getShowmaxrow() > rsList.size()
                ? rsList.size() : tableContent.getShowmaxrow();

        int currentTableCellAllowRows = 0;//当前单元格允许合并的行数

        for (int rsIndex = 0; rsIndex < size; rsIndex++) {
//                        boolean sumArrayUniteflg = true;
            String summary = "";//记录每个记录集的汇总字段
            if (kpiDefine != null) {
                for (int iSummary = 0; iSummary < kpiDefine.getSummaryDefines().length; iSummary++) {
                    SummaryDefine summaryDefine = kpiDefine.getSummaryDefines()[iSummary];
                    if (summary.length() > 0)
                        summary += "&";
                    String fieldData = StaticMethod.null2String((String)
                            StatUtil.getExpressionResult((Map) (rsList.get(rsIndex)), summaryDefine.getId(), ""), "");
                    String summaryValue = fieldData;
                    if (!summaryDefine.isEnable())
                        summaryValue = Constants.$DISENABLE$;
                    summary += summaryDefine.getId() + "=" + summaryValue;//把汇总字段拼成字符串
                }
            }


            TableRow[] tmp = (TableRow[]) StatUtil.CloneObject(tableRows);
            for (int i = 0; i < tmp.length; i++) {
                TableRow tableRow = tmp[i];
                TableCell[] tableCells = tableRow.getTableCells();

                List tableCellList = new ArrayList();

                for (int j = 0; j < tableCells.length; j++) {
                    tableCellList.add(tableCells[j]);
                    TableCellFont tableCellFont = tableCells[j].getTableCellFont();
                    TableCellLink tableCellLink = tableCells[j].getTableCellLink();
                    String tableCellFontStr = null;
                    String tableCellLinkStr = null;

                    if (tableCellFont != null) {
                        tableCellFontStr = tableCellFont.getShowText();
                    }

                    if (tableCellLink != null) {
                        tableCellLinkStr = tableCellLink.getShowText();
                    }

                    if (ExcelConverterUtil.isTableCellFontHasString(tableCellFont))//Cell中没有超链接
                    {
//						设置序号
                        if (tableCellFontStr.startsWith(Constants.$SN$)) {
                            tableCellFont.setShowText(String.valueOf(rsIndex + 1));
                        } else if (tableCellFontStr.startsWith(Constants.$DATA$)) {
                            String str = tableCellFontStr.substring(Constants.$DATA$.length());
                            //调用Model得到需要显示的内容
                            String fieldData = StaticMethod.null2String((String)
                                            StatUtil.getExpressionResult((Map) (rsList.get(rsIndex)), str, "0")
                                    , "0");

                            tableCellFont.setShowText(fieldData);
                        } else if (tableCellFontStr.startsWith(Constants.$SUMMARY$))//汇总
                        {
                            String summaryID = tableCellFontStr.substring(Constants.$SUMMARY$.length());

                            SummaryDefine summaryDefine = kpiDefine != null ? kpiDefine.getSummaryDefineByID(summaryID) : null;

                            //调用Model得到需要显示的内容
                            String fieldData = StaticMethod.null2String((String)
                                            StatUtil.getExpressionResult((Map) (rsList.get(rsIndex)), summaryID, "")
                                    , "0");
                            if (summary != "")
                                summary += "&";
                            String summaryValue = fieldData;
                            if (summaryDefine != null && !summaryDefine.isEnable())
                                summaryValue = Constants.$DISENABLE$;

                            //如果需要是String类型需要把summaryValue 转换为'summaryValue',在拼成url字符串
                            if (summaryDefine != null && Constants.MARKFLG.equalsIgnoreCase(summaryDefine.getMarkflg())) {
                                summaryValue = "'" + summaryValue + "'";
                            }

                            summary += summaryID + "=" + summaryValue;//把汇总字段拼成字符串

                            //把id 转换为 name 显示.
                            if (summaryDefine != null
                                    && !summaryDefine.getId2nameService().equals("")) {
                                String showText = "";
                                showText = StatUtil.id2NameDisply(summaryDefine, fieldData);

                                tableCellFont.setShowText(showText);
                            } else
                                tableCellFont.setShowText(fieldData);
                        }
                    }

                    if (ExcelConverterUtil.isTableCellLinkHasString(tableCellLink)) //Cell中有超链接
                    {
                        //设置序号
                        if (tableCellLinkStr.startsWith(Constants.$SN$)) {
                            tableCellLink.setShowText(String.valueOf(rsIndex + 1));
                        } else if (tableCellLinkStr.startsWith(Constants.$DATA$)) {
                            String str = tableCellLinkStr.substring(Constants.$DATA$.length());
                            //调用Model得到需要显示的内容
                            String fieldData = StaticMethod.null2String((String)
                                            StatUtil.getExpressionResult((Map) (rsList.get(rsIndex)), str, "0")
                                    , "0");

                            if ("0".equalsIgnoreCase(fieldData)) //如果统计结果为0的情况，不生成超链接
                            {
                                tableCellFont.setShowText(fieldData);
//                                                        	tableCellLink.setLink(null);
                                tableCells[j].setTableCellLink(null);
                            } else {
                                tableCellLink.setShowText(fieldData);
                                tableCellLink.setLink(dataUrl + "&" + summary + "&" + "fieldId=" + str);
                            }
                        } else if (tableCellLinkStr.startsWith(Constants.$SUMMARY$))//汇总
                        {
                            String summaryID = tableCellLinkStr.substring(Constants.$SUMMARY$.length());
                            SummaryDefine summaryDefine = kpiDefine != null ? kpiDefine.getSummaryDefineByID(summaryID) : null;
                            //调用Model得到需要显示的内容
                            String fieldData = StaticMethod.null2String((String)
                                            StatUtil.getExpressionResult((Map) (rsList.get(rsIndex)), summaryID, "")
                                    , "0");
                            if (summaryDefine != null
                                    && !summaryDefine.getId2nameService().equals("")) {
                                String showText = "";
                                showText = StatUtil.id2NameDisply(summaryDefine, fieldData);

                                tableCellFont.setShowText(showText);
                            } else
                                tableCellFont.setShowText(fieldData);
                        }
                    }
                }

                tableRow.setTableCells(tableCellList);

                RowsList.add(tableRow);
            }
        }

        tableContent.setTableRows(RowsList);

        logger.debug("/n结果集添加到表体完成");
    }

    /**
     * 处理结果集，把需要合并的列号，begin行号，end行号 保存到list中
     *
     * @param unitlist
     * @param begin
     * @param end
     * @param col
     * @param rsList
     * @param colCount
     * @param cols
     */
    public void unitResult(List unitlist, int begin, int end, int col, List rsList, final int colCount, String[] cols) {
        if (rsList == null || rsList.size() == 0) {
            return;
        }

        int thisBegin = begin;
        String key = cols[col];
        String thisData = StaticMethod.null2String((String)
                        StatUtil.getExpressionResult((Map) (rsList.get(thisBegin)), key, "")
                , "");

        for (int i = begin; i <= end; i++) {
            if (i == end) {
//        			找到合并结束行
                //TODO 开始合并
                //do some method
//					System.err.println(col+" : "+thisBegin+" , " +i);
                unitlist.add(new SuarryUnit(col, thisBegin, i));
                //合并完，继续处理

                //处理下一列

                if (col < colCount - 1) {
                    unitResult(unitlist, thisBegin, end, col + 1, rsList, colCount, cols);
                }
            } else {
                String nextData = StaticMethod.null2String((String)
                                StatUtil.getExpressionResult((Map) (rsList.get(i + 1)), cols[col], "0")
                        , "0");
                if (!nextData.equals(thisData)) {

                    //找到合并结束行

                    //处理下一列

                    //TODO 开始合并
                    //do some method
//						System.err.println(col+" : "+thisBegin+" , " +i);
                    unitlist.add(new SuarryUnit(col, thisBegin, i));
                    //合并完，继续处理
                    if (col < colCount - 1) {
                        unitResult(unitlist, thisBegin, i, col + 1, rsList, colCount, cols);
                    }
                    thisData = nextData;
                    thisBegin = i + 1;
                }
            }
        }

    }

    /**
     * //计算并合并的单元格
     *
     * @param suc              合并的配置信息
     * @param reList           结果集
     * @param tableContentBody 表体
     * @return
     */
    protected void uniteSumArrayCell(SumarrayUniteConfig suc, List reList, TableContent tableContentBody) {
        //计算单元格合并的信息
        String[] cols = suc.unitecols;//{"s1","s2","s3"};//配置文件中获取
        int uniteCol = suc.uniterows;//1;//配置文件 body_start="6" body_end="6" ： 算法：body_end-body_start+1
        int startCol = suc.startCol;//开始合并列号
        int endCol = cols.length;
        List unitlist = new ArrayList();//需要合并的单元格信息
        unitResult(unitlist, 0, reList.size() - 1, 0, reList, endCol, cols);

        //根据合并信息处理单元格（javaObject）
        for (int i = 0; i < unitlist.size(); i++) {
            SuarryUnit su = (SuarryUnit) unitlist.get(i);
            //为不同的显示形式处理合并的行数
            su.handleSuarryUnit(uniteCol);
//            	System.err.println(su);
            tableContentBody.unitCell(su, startCol, uniteCol);
        }

//          删除tableRow中的null单元格
        TableRow[] tableRows = tableContentBody.getTableRows();
        for (int j = 0; j < tableRows.length; j++) {
            TableCell[] tableCell = tableRows[j].getTableCells();
            List list = new ArrayList();
            for (int k = 0; k < tableCell.length; k++) {
                if (tableCell[k] != null) {
                    list.add(tableCell[k]);
                }
            }
            tableRows[j].setTableCells(list);
        }
    }

    //===============================public interface==================================================

    /**
     * 解析Excel并返回 Excel的XML表示形式
     *
     * @param reportData
     * @param reList
     * @param info
     * @return
     * @throws Exception
     */
    public OutputStream parseSheet2Html(Sheet reportData, List reList, Object info, String dataUrl, KpiDefine kpiDefine) throws Exception {
        //parse 表体，对配置Excel进行处理,把reList增加到配置Excel中
        TableContent tableContentBody = reportData.getTables()[0].getTableBody();

        if (reList == null) {
            reportData.getTables()[0].setTableBody(null);
        }

        handleResult(tableContentBody, reList, dataUrl, kpiDefine);

//              ============合并汇总字段
        SumarrayUniteConfig suc = tableContentBody.getSumArrayUnite();
        if (suc != null && suc.unite && suc.unitecols != null) {
            this.logger.info("合并汇总字段");
            uniteSumArrayCell(suc, reList, tableContentBody);
        }
        //=============

        //parse 表头
        TableContent tableContentHaed = reportData.getTables()[0].getTableHead();
        handleInfo(tableContentHaed, info);
        logger.debug("/n处理表头结束");

        //parse 表尾
        TableContent tableContentFoot = reportData.getTables()[0].getTableFoot();
        handleInfo(tableContentFoot, info);
        handleTotal(tableContentFoot, reList);
        logger.debug("/n处理表尾结束");

        //利用castor影射为HTMLStream
        Mapping mapping = new Mapping(); //用于加载mapping.xml
        mapping.loadMapping(StaticMethod.getFilePathForUrl(excelToHtmlMappingPath));

        OutputStream HTMLStream = new ByteArrayOutputStream();

        OutputStreamWriter osw = new OutputStreamWriter(HTMLStream, "UTF-8");
        Marshaller tmpM = new Marshaller(osw);
        tmpM.setMapping(mapping);
        tmpM.marshal(reportData);
        osw.close();
        HTMLStream.close();

        return HTMLStream;
    }

    /**
     * 得到Excel的配置信息
     *
     * @param inFilePath
     * @return
     */
    public Excel parseExcelToConfig(String inFilePath) {
        Excel excel = null;
        try {
            excel = parseExcel(inFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return excel;
    }

    /**
     * 得到Excel的配置信息
     *
     * @param inFilePath
     * @return
     */
    public Excel parseExcelToConfig(String inFilePath, String[] dyColumSelectids, int sheetIndex) {
        Excel excel = null;
        try {
            excel = parseExcel(inFilePath, dyColumSelectids, sheetIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return excel;
    }

    public String getExcelToHtmlMappingPath() {
        return excelToHtmlMappingPath;
    }

    public void setExcelToHtmlMappingPath(String excelToHtmlMappingPath) {
        this.excelToHtmlMappingPath = excelToHtmlMappingPath;
    }

    /**
     * 测试,解析,处理数据,生成XML
     *
     * @param args
     * @throws Excel2XmlException
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        List test = new ArrayList();
        String[] a = {"a", "aa", "fb"}; //0
        String[] a1 = {"a", "aa", "bb"}; //1
        String[] a2 = {"a", "aa", "bb"}; //2
        String[] a3 = {"b", "aa", "bb"}; //3
        String[] a4 = {"b", "aa", "gb"}; //4
        String[] a5 = {"c", "aa", "bb"}; //5
        test.add(a);
        test.add(a1);
        test.add(a2);
        test.add(a3);
        test.add(a4);
        test.add(a5);
        handleSpan(0, test.size() - 1, 0, test, 3);

        if (false) {
            return;
        }

        String kpiconfigPath = "E:/statistic-config-query-commonfault_T_resolve_KPI4_oracle.xml";
        KpiConfig kpiConfig = (KpiConfig) ParseXmlService.create().xml2object(
                KpiConfig.class, kpiconfigPath);

        String name = "commonfault_T_resolve_byuser";
        KpiDefine kpiDefine = kpiConfig.getConfigByKpiDefineName(name);

        ExcelConverter ex = new ExcelConverter();

        Excel excel = ex.parseExcel("E:/statistic-config-excel-commonfault_T_resolve_KPI4_oracle.xls");
        System.err.println("我配置文件处理结束" + excel);

        //处理结果集,添加到ex.excel中
        int sheetIndex = 0;
        List rslist = ex.initList();

        Map info = new HashMap();
        Sheet sheet = excel.getSheets()[sheetIndex];

        OutputStream outstream = ex.parseSheet2Htmltest(sheet, rslist, info, "", kpiDefine);

        ExcelConverterUtil.writeFile((ByteArrayOutputStream) outstream, "E:/test.html");


        //输出默认的mapping 可以照这默认的改成自己下需要的 xml格式
//		MappingTool mt = new MappingTool();
//        mt.addClass(Excel.class);
//        mt.write(new OutputStreamWriter(System.out));

//            FileOutputStream f = new FileOutputStream("E:/test.html");
//            OutputStreamWriter osw = new OutputStreamWriter(f, "UTF-8");
//            Marshaller tmpM = new Marshaller(osw);
//            tmpM.setMapping(mapping);
//            tmpM.marshal(excel);
//            osw.close();
//            f.flush();
//            f.close();

        System.err.println("生成XML完毕");
    }

    private static int handleSpan(int begin, int end, int col, List list, final int colCount) {
        int thisBegin = begin;
//        	int thisEnd =begin;
        String thisData = ((String[]) list.get(thisBegin))[col];

        for (int i = begin; i <= end; i++) {
            if (i == end) {
                //找到合并结束行
                //TODO 开始合并
                //do some method
                System.out.println(col + " : " + thisBegin + " , " + i);
                //合并完，继续处理

                //处理下一列

                if (col < colCount - 1) {
                    handleSpan(thisBegin, end, col + 1, list, colCount);
                }
            } else {
                String nextData = ((String[]) list.get(i + 1))[col];
                if (!nextData.equals(thisData)) {

                    //找到合并结束行

                    //处理下一列

                    //TODO 开始合并
                    //do some method
                    System.out.println(col + " : " + thisBegin + " , " + i);
                    //合并完，继续处理
                    if (col < colCount - 1) {
                        handleSpan(thisBegin, i, col + 1, list, colCount);
                    }
                    thisData = nextData;
                    thisBegin = i + 1;
                }
            }
        }
        return 1;
    }

    private OutputStream parseSheet2Htmltest(Sheet reportData, List reList, Object info, String dataUrl, KpiDefine kpiDefine) throws Exception {
        //parse 表体，对配置Excel进行处理,把reList增加到配置Excel中
        TableContent tableContentBody = reportData.getTables()[0].getTableBody();

        if (reList == null) {
            reportData.getTables()[0].setTableBody(null);
        }

        handleResult(tableContentBody, reList, dataUrl, kpiDefine);

        //============
        SumarrayUniteConfig suc = tableContentBody.getSumArrayUnite();
        if (suc.unite) {
            uniteSumArrayCell(suc, reList, tableContentBody);
        }
        //=============

        //parse 表头
        TableContent tableContentHaed = reportData.getTables()[0].getTableHead();
        handleInfo(tableContentHaed, info);
        logger.debug("/n处理表头结束");

        //parse 表尾
        TableContent tableContentFoot = reportData.getTables()[0].getTableFoot();
        handleInfo(tableContentFoot, info);
        handleTotal(tableContentFoot, reList);
        logger.debug("/n处理表尾结束");

        //利用castor影射为HTMLStream
        Mapping mapping = new Mapping(); //用于加载mapping.xml
        mapping.loadMapping("E:/statExcelToHtmlMappingV35.xml");

        OutputStream HTMLStream = new ByteArrayOutputStream();

        OutputStreamWriter osw = new OutputStreamWriter(HTMLStream, "UTF-8");
        Marshaller tmpM = new Marshaller(osw);
        tmpM.setMapping(mapping);
        tmpM.marshal(reportData);
        osw.close();
        HTMLStream.close();

        return HTMLStream;
    }

    //排好序的结果集
    private List initList() {
        Map d1 = new HashMap();
        d1.put("s1", "01402");
        d1.put("s2", "80");
        d1.put("s3", "56");
        d1.put("f4", "80");
        d1.put("f5", "86");
        d1.put("f6", "14");
        Map d2 = new HashMap();
        d2.put("s1", "01402");
        d2.put("s2", "80");
        d2.put("s3", "70");
        d2.put("f4", "12");
        d2.put("f5", "89");
        d2.put("f6", "144");
        Map d3 = new HashMap();
        d3.put("s1", "01402");
        d3.put("s2", "80");
        d3.put("s3", "70");
        d3.put("f4", "10");
        d3.put("f5", "86");
        d3.put("f6", "9");
        Map d4 = new HashMap();
        d4.put("s1", "01403");
        d4.put("s2", "80");
        d4.put("s3", "70");
        d4.put("f4", "30");
        d4.put("f5", "68");
        d4.put("f6", "19");
        Map d5 = new HashMap();
        d5.put("s1", "01404");
        d5.put("s2", "80");
        d5.put("s3", "90");
        d5.put("f4", "330");
        d5.put("f5", "70");
        d5.put("f6", "29");
        Map d6 = new HashMap();
        d6.put("s1", "01404");
        d6.put("s2", "33");
        d6.put("s3", "90");
        d6.put("f4", "30");
        d6.put("f5", "18");
        d6.put("f6", "29");

        Map d7 = new HashMap();
        d7.put("s1", "01408");
        d7.put("s2", "33");
        d7.put("s3", "79");
        d7.put("f4", "30");
        d7.put("f5", "18");
        d7.put("f6", "29");

        Map d8 = new HashMap();
        d8.put("s1", "01404");
        d8.put("s2", "33");
        d8.put("s3", "79");
        d8.put("f4", "30");
        d8.put("f5", "18");
        d8.put("f6", "29");

        Map d9 = new HashMap();
        d9.put("s1", "01404");
        d9.put("s2", "33");
        d9.put("s3", "79");
        d9.put("f4", "30");
        d9.put("f5", "18");
        d9.put("f6", "29");

        List listResult = new ArrayList();
        listResult.add(d1);
        listResult.add(d2);
        listResult.add(d3);
        listResult.add(d4);
        listResult.add(d5);
        listResult.add(d6);
        listResult.add(d7);
        listResult.add(d8);
        listResult.add(d9);

        return listResult;
    }

}
