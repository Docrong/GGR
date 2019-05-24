
package com.boco.eoms.commons.statistic.base.mgr.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ognl.OgnlException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.config.model.SummaryDefine;
import com.boco.eoms.commons.statistic.base.excelutil.mgr.impl.DyTableInfo;
import com.boco.eoms.commons.statistic.base.excelutil.util.CommonExcel;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;
import com.boco.eoms.commons.statistic.base.util.Constants;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;
import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil.SumarrayUniteConfig;

public class ExportExcel {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	private static final String tmpSheetName = "tmpSheet";
	
	/**
	 * 合并汇总字段开关，false:不合并 true:合并
	 */
//	private boolean sumArrayUnite = false;
	
	/**
	 * 导出Excel
	 * 
	 * @param inFilePath
	 *            Excel路径
	 * @param sourceSheetName
	 *            excel的sheet
	 * @param rslist
	 *            结果集
	 * @param map
	 *            info标签map
	 * @return ExcelStream
	 * @throws Exception
	 */
	public OutputStream ResultExportExcel(String inFilePath,
			String sourceSheetName, List rslist, Map map, KpiDefine kpiDefine) throws Exception {
		// Map configMap = initMap();
		logger.debug("inFilePath : " + inFilePath +"," +"sourceSheetName : " + sourceSheetName);
		OutputStream outStream = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(inFilePath);
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook wb = new HSSFWorkbook(fs);

			// 输出流
			outStream = ResultExportExcel(wb,sourceSheetName,rslist,map,kpiDefine);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if(fis != null)
			{
				fis.close();
			}
		}

		return outStream;
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param inFilePath
	 *            Excel路径
	 * @param sourceSheetName
	 *            excel的sheet
	 * @param rslist
	 *            结果集
	 * @param map
	 *            info标签map
	 * @return ExcelStream
	 * @throws Exception
	 */
	public OutputStream ResultExportExcel(String inFilePath,
			String sourceSheetName, List rslist, Map map, KpiDefine kpiDefine,String[] dyColumSelectids,int sheetIndex) throws Exception {
		// Map configMap = initMap();
		logger.debug("inFilePath : " + inFilePath +"," +"sourceSheetName : " + sourceSheetName);
		OutputStream outStream = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(inFilePath);
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook wb = new HSSFWorkbook(fs);

			//add by lizhenyou 处理动态列excel
			if(dyColumSelectids != null && dyColumSelectids.length != 0)
			{
				HSSFSheet sheet = wb.getSheetAt(sheetIndex);
				CommonExcel ce1 = new CommonExcel();
				wb = ce1.dyColumConver(wb,sheet,dyColumSelectids);
				//写入文件查看修改后的excel是否正确是否正确
//				FileOutputStream fos= new FileOutputStream("E:/dycolum_eportexcel_test.xls");
//				wb.write(fos);
//				fos.close();
			}
			
			// 输出流
			outStream = ResultExportExcel(wb,sourceSheetName,rslist,map,kpiDefine);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if(fis != null)
			{
				fis.close();
			}
		}

		return outStream;
	}
	
	
	//=============
	/**
	 * 导出Excel
	 * 
	 * @param inFilePath
	 *            Excel路径
	 * @param sourceSheetName
	 *            excel的sheet
	 * @param rslist
	 *            结果集
	 * @param map
	 *            info标签map
	 * @return ExcelStream
	 * @throws Exception
	 */
	public OutputStream ResultExportExcel(HSSFWorkbook wb,
			String sourceSheetName, List rslist, Map map, KpiDefine kpiDefine) throws Exception {
		// Map configMap = initMap();
		OutputStream outStream = null;
		//FileInputStream fis = null;
		try {
			//fis = new FileInputStream(inFilePath);
			//POIFSFileSystem fs = new POIFSFileSystem(fis);
			//HSSFWorkbook wb = new HSSFWorkbook(fs);

			// 建立一个临时的sheet
			wb.createSheet(tmpSheetName);
			wb.setSheetOrder(tmpSheetName, 0);
			wb.setSelectedTab(0);
			
			// 获取第一行的配置信息
			HSSFSheet sheet = wb.getSheet(sourceSheetName);
			HSSFRow fistRow = sheet.getRow(0);
			if (fistRow == null) {
				return null;
			}
			HSSFCell fistCell;
			if ((fistCell = fistRow.getCell(0)) == null) {
				throw new Exception("没有定义配置信息");
			}
			
			String configInfoXml = fistCell.getRichStringCellValue()
					.getString();
		    int showmaxrow = ExcelConverterUtil.getConfigInfoShowMaxRow(configInfoXml);
			List configInfoTables = ExcelConverterUtil.getCongfigInfo(configInfoXml);
			Map cogfigMap = (Map) configInfoTables.get(0);
			
			SumarrayUniteConfig suc = null;
            try
            {
            	suc = ExcelConverterUtil.getConfigInfoSumarrayunite(configInfoXml);
            	if(suc != null)
            	{
            		int body_start = Integer.parseInt(String.valueOf(((Map)configInfoTables.get(0)).get(Constants.CONFIGBODY_START)));
                	int body_send = Integer.parseInt(String.valueOf(((Map)configInfoTables.get(0)).get(Constants.CONFIGBODY_END)));
            		suc.uniterows = body_send - body_start + 1;
            	}
            }
            catch(Exception e)
            {
                System.err.println("sumarrayunite 设置错误");
            }

			// 处理表头
			int pStartRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGHEAD_START)));
			int copyCount = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGHEAD_END)))- pStartRow + 1;
			handleTableHead(wb, sourceSheetName, tmpSheetName, pStartRow,copyCount, pStartRow, map);

//			writeExcelToFile(wb,"E:/outFile1.xls");
			
			// 处理表体
			pStartRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGBODY_START)));
			copyCount = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGBODY_END)))- pStartRow + 1;
			int bodyEndPoisition = handleTableBody(wb, sourceSheetName,	
					tmpSheetName, pStartRow, copyCount, pStartRow, 
					rslist,kpiDefine,suc,showmaxrow);
//			writeExcelToFile(wb,"E:/outFile2.xls");

			//记录合并单元格起始行，只有表体数据需要合并
			int unitStartRow = pStartRow - 1;
			
			// 处理表尾
			pStartRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGFOOT_START)));
			copyCount = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGFOOT_END)))- pStartRow + 1;
			if (pStartRow > 0 && copyCount >= 0) {
				handleTableFoot(wb, sourceSheetName, tmpSheetName, pStartRow,copyCount, bodyEndPoisition, map,rslist);
			}
//			writeExcelToFile(wb,"E:/outFile3.xls");
			
			//处理合并单元格
			if(suc!= null && suc.unite && rslist.size() > 0)
			{
				ExcelConverter ec = new ExcelConverter();
				//计算单元格合并的信息
	            String[] cols = suc.unitecols;//{"s1","s2","s3"};//配置文件中获取
	            int uniteCol = suc.uniterows;//1;//配置文件 body_start="6" body_end="6" ： 算法：body_end-body_start+1
	            int startCol = suc.startCol;//开始合并列号
	            int endCol = cols.length;
	            List unitlist = new ArrayList();//需要合并的单元格信息
				ec.unitResult(unitlist,0,rslist.size()-1,0,rslist,endCol,cols);
				
				//根据合并信息处理单元格（javaObject）
				CommonExcel ce = new CommonExcel(wb,wb.getSheet(tmpSheetName));
	            for(int i=0;i<unitlist.size();i++)
	            {
	            	SuarryUnit su = (SuarryUnit)unitlist.get(i);
//	            	System.err.println(su.toString());
	            	su.handleSuarryUnit(uniteCol);
	            	int column = su.column + startCol - 1;
	            	int leftTopRow = su.beginrow + unitStartRow;
	            	int leftTopCol = column;
	            	int rightBottomRow = su.endrow + unitStartRow;
	            	int rightBottomCol = column;
	            	
	            	ce.setMergeRegion(leftTopRow, leftTopCol, rightBottomRow, rightBottomCol);
	            }
			}
//			writeExcelToFile(wb,"E:/outFile2.xls");
			
			// 输出流
			outStream = outExcelStream(wb, sourceSheetName);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
//			if(fis != null)
//			{
//				fis.close();
//			}
		}

		return outStream;
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param inFilePath
	 *            Excel路径
	 * @param sourceSheetName
	 *            excel的sheet
	 * @param rslist
	 *            结果集
	 * @param map
	 *            info标签map
	 * @return ExcelStream
	 * @throws Exception
	 */
	public OutputStream ResultExportExcel(HSSFWorkbook wb,
			String sourceSheetName, List rslist, Map map, KpiDefine kpiDefine,DyTableInfo[] dtis) throws Exception {
		// Map configMap = initMap();
		OutputStream outStream = null;
		//FileInputStream fis = null;
		try {
			//fis = new FileInputStream(inFilePath);
			//POIFSFileSystem fs = new POIFSFileSystem(fis);
			//HSSFWorkbook wb = new HSSFWorkbook(fs);

			// 建立一个临时的sheet
			wb.createSheet(tmpSheetName);
			wb.setSheetOrder(tmpSheetName, 0);
			wb.setSelectedTab(0);
			
			// 获取第一行的配置信息
			HSSFSheet sheet = wb.getSheet(sourceSheetName);
			HSSFRow fistRow = sheet.getRow(0);
			if (fistRow == null) {
				return null;
			}
			HSSFCell fistCell;
			if ((fistCell = fistRow.getCell(0)) == null) {
				throw new Exception("没有定义配置信息");
			}
			
			
			String configInfoXml = fistCell.getRichStringCellValue()
					.getString();
		    int showmaxrow = ExcelConverterUtil.getConfigInfoShowMaxRow(configInfoXml);
			List configInfoTables = ExcelConverterUtil.getCongfigInfo(configInfoXml);
			Map cogfigMap = (Map) configInfoTables.get(0);
			
			SumarrayUniteConfig suc = null;
            try
            {
            	suc = ExcelConverterUtil.getConfigInfoSumarrayunite(configInfoXml);
            	if(suc != null)
            	{
            		int body_start = Integer.parseInt(String.valueOf(((Map)configInfoTables.get(0)).get(Constants.CONFIGBODY_START)));
                	int body_send = Integer.parseInt(String.valueOf(((Map)configInfoTables.get(0)).get(Constants.CONFIGBODY_END)));
            		suc.uniterows = body_send - body_start + 1;
            	}
            }
            catch(Exception e)
            {
                System.err.println("sumarrayunite 设置错误");
            }

			// 处理表头
			int pStartRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGHEAD_START)));
			int copyCount = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGHEAD_END)))- pStartRow + 1;
			handleTableHead(wb, sourceSheetName, tmpSheetName, pStartRow,copyCount, pStartRow, map);

//			writeExcelToFile(wb,"E:/outFile1.xls");
			
			// 处理表体
			pStartRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGBODY_START)));
			copyCount = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGBODY_END)))- pStartRow + 1;
			int bodyEndPoisition = handleTableBody(wb, sourceSheetName,	
					tmpSheetName, pStartRow, copyCount, pStartRow, 
					rslist,kpiDefine,suc,showmaxrow);
//			writeExcelToFile(wb,"E:/outFile2.xls");

			//记录合并单元格起始行，只有表体数据需要合并
			int unitStartRow = pStartRow - 1;
			
			// 处理表尾
			pStartRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGFOOT_START)));
			copyCount = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGFOOT_END)))- pStartRow + 1;
			if (pStartRow > 0 && copyCount >= 0) {
				handleTableFoot(wb, sourceSheetName, tmpSheetName, pStartRow,copyCount, bodyEndPoisition, map,rslist);
			}
//			writeExcelToFile(wb,"E:/outFile3.xls");
			
			//处理合并单元格
			if(suc!= null && suc.unite && rslist.size() > 0)
			{
				ExcelConverter ec = new ExcelConverter();
				//计算单元格合并的信息
	            String[] cols = suc.unitecols;//{"s1","s2","s3"};//配置文件中获取
	            int uniteCol = suc.uniterows;//1;//配置文件 body_start="6" body_end="6" ： 算法：body_end-body_start+1
	            int startCol = suc.startCol;//开始合并列号
	            int endCol = cols.length;
	            List unitlist = new ArrayList();//需要合并的单元格信息
				ec.unitResult(unitlist,0,rslist.size()-1,0,rslist,endCol,cols);
				
				//根据合并信息处理单元格（javaObject）
				CommonExcel ce = new CommonExcel(wb,wb.getSheet(tmpSheetName));
	            for(int i=0;i<unitlist.size();i++)
	            {
	            	SuarryUnit su = (SuarryUnit)unitlist.get(i);
	            	su.handleSuarryUnit(uniteCol);
	            	int column = su.column + startCol - 1;
	        		int beginrow = su.beginrow;
	        		int endrow = su.endrow;
	            	int leftTopRow = beginrow + unitStartRow;
	            	int leftTopCol = column;
	            	int rightBottomRow = endrow + unitStartRow - 1;
	            	int rightBottomCol = column;
	            	
	            	ce.setMergeRegion(leftTopRow, leftTopCol, rightBottomRow, rightBottomCol);
	            }
			}
//			writeExcelToFile(wb,"E:/outFile2.xls");
			
			// 输出流
			outStream = outExcelStream(wb, sourceSheetName);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
//			if(fis != null)
//			{
//				fis.close();
//			}
		}

		return outStream;
	}
	//========
	
	/**
	 * 导出Excel
	 * 
	 * @param inFilePath
	 *            Excel路径
	 * @param sourceSheetName
	 *            excel的sheet
	 * @param rslist
	 *            结果集
	 * @param map
	 *            info标签map
	 * @return ExcelStream
	 * @throws Exception
	 */
	public OutputStream ResultExportExcel(String inFilePath,
			String sourceSheetName, List rslist, Map map) throws Exception {
		if(map == null)
		{
			map = new HashMap();
		}
		// Map configMap = initMap();
		logger.debug("inFilePath : " + inFilePath +"," +"sourceSheetName : " + sourceSheetName);
		OutputStream outStream = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(inFilePath);
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook wb = new HSSFWorkbook(fs);

			// 建立一个临时的sheet
			wb.createSheet(tmpSheetName);
			wb.setSheetOrder(tmpSheetName, 0);
			wb.setSelectedTab(0);

			// 获取第一行的配置信息
			HSSFSheet sheet = wb.getSheet(sourceSheetName);
			HSSFRow fistRow = sheet.getRow(0);
			if (fistRow == null) {
				return null;
			}
			HSSFCell fistCell;
			if ((fistCell = fistRow.getCell(0)) == null) {
				throw new Exception("没有定义配置信息");
			}
			String configInfoXml = fistCell.getRichStringCellValue()
					.getString();
			
			
		    
		    int showmaxrow = ExcelConverterUtil.getConfigInfoShowMaxRow(configInfoXml);

			List configInfoTables = ExcelConverterUtil.getCongfigInfo(configInfoXml);
			Map cogfigMap = (Map) configInfoTables.get(0);
			
			SumarrayUniteConfig suc = null;
            try
            {
            	suc = ExcelConverterUtil.getConfigInfoSumarrayunite(configInfoXml);
            	if(suc != null)
            	{
            		int body_start = Integer.parseInt(String.valueOf(((Map)configInfoTables.get(0)).get(Constants.CONFIGBODY_START)));
                	int body_send = Integer.parseInt(String.valueOf(((Map)configInfoTables.get(0)).get(Constants.CONFIGBODY_END)));
            		suc.uniterows = body_send - body_start + 1;
            	}
            }
            catch(Exception e)
            {
                System.err.println("sumarrayunite 设置错误");
            }

			// 处理表头
			int pStartRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGHEAD_START)));
			int copyCount = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGHEAD_END)))- pStartRow + 1;
			handleTableHead(wb, sourceSheetName, tmpSheetName, pStartRow,copyCount, pStartRow, map);

//			writeExcelToFile(wb,"E:/outFile1.xls");
			
			// 处理表体
			pStartRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGBODY_START)));
			copyCount = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGBODY_END)))- pStartRow + 1;
			int bodyEndPoisition = handleTableBody(wb, sourceSheetName,	
					tmpSheetName, pStartRow, copyCount, pStartRow, 
					rslist,suc,showmaxrow);
//			writeExcelToFile(wb,"E:/outFile2.xls");
			
			//记录合并单元格起始行，只有表体数据需要合并
			int unitStartRow = pStartRow - 1;
			
			// 处理表尾
			pStartRow = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGFOOT_START)));
			copyCount = Integer.parseInt(String.valueOf(cogfigMap.get(Constants.CONFIGFOOT_END)))- pStartRow + 1;
			if (pStartRow > 0 && copyCount >= 0) {
				handleTableFoot(wb, sourceSheetName, tmpSheetName, pStartRow,copyCount, bodyEndPoisition, map,rslist);
			}
//			writeExcelToFile(wb,"E:/outFile3.xls");
			
			//处理合并单元格
			if(suc!= null && suc.unite)
			{
				ExcelConverter ec = new ExcelConverter();
				//计算单元格合并的信息
	            String[] cols = suc.unitecols;//{"s1","s2","s3"};//配置文件中获取
	            int uniteCol = suc.uniterows;//1;//配置文件 body_start="6" body_end="6" ： 算法：body_end-body_start+1
	            int startCol = suc.startCol-1;//开始合并列号
	            int endCol = cols.length+startCol-1;
	            List unitlist = new ArrayList();//需要合并的单元格信息
				ec.unitResult(unitlist,0,rslist.size()-1,0,rslist,endCol,cols);
				
				//根据合并信息处理单元格（javaObject）
				CommonExcel ce = new CommonExcel(wb,wb.getSheet(tmpSheetName));
	            for(int i=0;i<unitlist.size();i++)
	            {
	            	SuarryUnit su = (SuarryUnit)unitlist.get(i);
	            	su.handleSuarryUnit(uniteCol);
	            	int column = su.column + startCol;
	        		int beginrow = su.beginrow;
	        		int endrow = su.endrow;
	            	int leftTopRow = beginrow + unitStartRow;
	            	int leftTopCol = column;
	            	int rightBottomRow = endrow + unitStartRow;
	            	int rightBottomCol = column;
	            	
	            	ce.setMergeRegion(leftTopRow, leftTopCol, rightBottomRow, rightBottomCol);
	            }
			}
//			writeExcelToFile(wb,"E:/outFile2.xls");
			
			// 输出流
			outStream = outExcelStream(wb, sourceSheetName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if(fis != null)
			{
				fis.close();
			}
		}

		return outStream;
	}

	/**
	 * 处理表头
	 * @param wb
	 * @param pSourceSheetName
	 * @param pTargetSheetName
	 * @param pStartRow
	 * @param copyCount
	 * @param pPosition
	 * @param object
	 */
	private void handleTableHead(HSSFWorkbook wb, String pSourceSheetName,
			String pTargetSheetName, int pStartRow, int copyCount,
			int pPosition, Object object) {
		// 处复制表头部分
		copyRows(wb, pSourceSheetName, pTargetSheetName, pStartRow, copyCount,
				pPosition);
		handleInfo(wb, pTargetSheetName, pStartRow, copyCount, object);
		logger.debug("导出Excel处理表头结束");
	}
	
	/**
	 * 处理表体
	 * @param wb
	 * @param pSourceSheetName
	 * @param pTargetSheetName
	 * @param pStartRow
	 * @param copyCount
	 * @param pPosition
	 * @param rsList
	 * @return
	 * @throws Exception
	 */
	private int handleTableBody(HSSFWorkbook wb, String pSourceSheetName,
			String pTargetSheetName, int pStartRow, int copyCount,
			int pPosition, List rsList, KpiDefine kpiDefine,SumarrayUniteConfig suc,int showmaxrow) throws Exception {
		// 复制表体，处理表体
		int position = pStartRow;
		
		//设置最大显示行数
        int size = showmaxrow == -1 || showmaxrow > rsList.size()
        	?rsList.size():showmaxrow;
        	
    	String unitecols = "";//记录所有的合并汇总字段
        int startcol = -1;
        	
		for (int rsIndex = 0; rsIndex < size; rsIndex++) 
		{
			// 复制表体
			copyRows(wb, pSourceSheetName, pTargetSheetName, pStartRow,copyCount, position);

			for (int p = position - 1; p < position + copyCount - 1; p++) 
			{
				HSSFRow row = wb.getSheet(pTargetSheetName).getRow(p);
				if (row != null) {
					for (int l = row.getFirstCellNum(); l < row
							.getLastCellNum(); l++) {
						HSSFCell cell = row.getCell(l);
						if (cell != null) 
						{
							String cellString = ExcelConverterUtil.getCellText(cell);
							// 设置序号
							if (cellString.startsWith(Constants.$SN$)) 
							{
								cell.setCellValue(rsIndex + 1);
							}
							else if (cellString.startsWith(Constants.$DATA$)) 
							{
								String str = cellString.substring(Constants.$DATA$.length());
								// 调用Model得到需要显示的内容
								String fieldData = StaticMethod.null2String(
										(String) StatUtil.getExpressionResult((Map) (rsList.get(rsIndex)),str, "0"), "0");
								ExcelConverterUtil.setCellText(cell, fieldData);
							}
							else if (cellString.startsWith(Constants.$SUMMARY$)) 
							{
								String summaryID = cellString.substring(Constants.$SUMMARY$.length());
								SummaryDefine summaryDefine = kpiDefine.getSummaryDefineByID(summaryID);
								
								String fieldData = StaticMethod.null2String(
										(String) StatUtil.getExpressionResult((Map) (rsList.get(rsIndex)),summaryID, ""), "");
								
								//记录合并列的开始位置
								if(startcol == -1)
                            	{
                            		startcol = l +1;
                            	}
								//记录所有需要可能合并列
								if(rsIndex == 0)
								{
									unitecols = unitecols + summaryID + ",";
								}
								
//								把id 转换为 name 显示.
								if (summaryDefine != null
										&& !summaryDefine.getId2nameService().equals(""))
								{
									//fieldData = StatUtil.id2Name(fieldData, summaryDefine.getId2nameService());
									fieldData = StatUtil.id2NameDisply(summaryDefine,fieldData);
								}
								
								ExcelConverterUtil.setCellText(cell, fieldData);
							}
						}
					}
				}
			}

			position = position + copyCount;
		}
		
		if(!"".equalsIgnoreCase(unitecols) && suc != null)
        {
			suc.unitecols = unitecols.split(",");
        	suc.startCol = startcol;
        }

		logger.debug("/n导出Excel处理表体结束");
		
		return position;
	}
	
	/**
	 * 处理表体
	 * @param wb
	 * @param pSourceSheetName
	 * @param pTargetSheetName
	 * @param pStartRow
	 * @param copyCount
	 * @param pPosition
	 * @param rsList
	 * @return
	 * @throws Exception
	 */
	private int handleTableBody(HSSFWorkbook wb, String pSourceSheetName,
			String pTargetSheetName, int pStartRow, int copyCount,
			int pPosition, List rsList, SumarrayUniteConfig suc,int showmaxrow) throws Exception {
		// 复制表体，处理表体
		int position = pStartRow;
		
		//设置最大显示行数
        int size = showmaxrow == -1 || showmaxrow > rsList.size()
        	?rsList.size():showmaxrow;
		
        String unitecols = "";//记录所有的合并汇总字段
        int startcol = -1;
        
		for (int rsIndex = 0; rsIndex < size; rsIndex++) 
		{
			// 复制表体
			copyRows(wb, pSourceSheetName, pTargetSheetName, pStartRow,copyCount, position);
			for (int p = position - 1; p < position + copyCount - 1; p++) 
			{
				HSSFRow row = wb.getSheet(pTargetSheetName).getRow(p);
				if (row != null) {
					for (int l = row.getFirstCellNum(); l < row
							.getLastCellNum(); l++) {
						HSSFCell cell = row.getCell(l);
						if (cell != null) 
						{
							String cellString = ExcelConverterUtil.getCellText(cell);
							// 设置序号
							if (cellString.startsWith(Constants.$SN$)) 
							{
								cell.setCellValue(rsIndex + 1);
							}
							else if (cellString.startsWith(Constants.$DATA$)) 
							{
								String str = cellString.substring(Constants.$DATA$.length());
								// 调用Model得到需要显示的内容
								String fieldData = StaticMethod.null2String(
										(String) StatUtil.getExpressionResult((Map) (rsList.get(rsIndex)),str, "0"), "0");
								ExcelConverterUtil.setCellText(cell, fieldData);
							}
							else if (cellString.startsWith(Constants.$SUMMARY$)) 
							{
								String summaryID = cellString.substring(Constants.$SUMMARY$.length());
								//SummaryDefine summaryDefine = kpiDefine.getSummaryDefineByID(summaryID);
								
								String fieldData = StaticMethod.null2String(
										(String) StatUtil.getExpressionResult((Map) (rsList.get(rsIndex)),summaryID, ""), "");
								
								//记录合并列的开始位置
								if(startcol == -1)
                            	{
                            		startcol = l +1;
                            	}
								//记录所有需要可能合并列
								if(rsIndex == 0)
								{
									unitecols = unitecols + summaryID + ",";
								}
								
//								把id 转换为 name 显示.
								//if (summaryDefine != null
								//		&& !summaryDefine.getId2nameService().equals(""))
								//{
									//fieldData = StatUtil.id2Name(fieldData, summaryDefine.getId2nameService());
									//fieldData = StatUtil.id2NameDisply(summaryDefine,fieldData);
								//}
								
								ExcelConverterUtil.setCellText(cell, fieldData);
							}
						}
					}
				}
			}

			position = position + copyCount;
		}
		
		if(!"".equalsIgnoreCase(unitecols) && suc != null)
        {
			suc.unitecols = unitecols.split(",");
        	suc.startCol = startcol;
        }
		
		logger.debug("/n导出Excel处理表体结束");
		
		return position;
	}

	/**
	 * 处理表尾
	 * @param wb
	 * @param pSourceSheetName
	 * @param pTargetSheetName
	 * @param pStartRow
	 * @param copyCount
	 * @param pPosition
	 * @param object
	 * @param rsList
	 */
	private void handleTableFoot(HSSFWorkbook wb, String pSourceSheetName,
			String pTargetSheetName, int pStartRow, int copyCount,
			int pPosition, Object object ,List rsList)
	{
		handleTableFoot(wb, pSourceSheetName,pTargetSheetName, pStartRow, copyCount,pPosition, object);
		handleTotal(wb, pTargetSheetName,pPosition, copyCount, rsList);
		logger.debug("导出Excel处理表尾结束");
	}
	/**
	 * 处理表尾
	 * 
	 * @param wb
	 * @param pSourceSheetName
	 * @param pTargetSheetName
	 * @param pStartRow
	 * @param copyCount
	 * @param pPosition
	 */
	private void handleTableFoot(HSSFWorkbook wb, String pSourceSheetName,
			String pTargetSheetName, int pStartRow, int copyCount,
			int pPosition, Object object) {
		copyRows(wb, pSourceSheetName, pTargetSheetName, pStartRow, copyCount,
				pPosition);
		handleInfo(wb, pTargetSheetName, pPosition, copyCount, object);
	}

	/**
	 * 处理$info$标签
	 * @param wb
	 * @param pTargetSheetName
	 * @param startrow
	 * @param copyCount
	 * @param object
	 */
	private void handleInfo(HSSFWorkbook wb, String pTargetSheetName,int pStartRow, int copyCount, Object object) 
	{
		Map map = (Map) object;
		pStartRow = pStartRow - 1;
		for (int p = pStartRow; p < pStartRow + copyCount; p++) 
		{
			HSSFRow row = wb.getSheet(pTargetSheetName).getRow(p);
			if (row != null) 
			{
				for (int l = row.getFirstCellNum(); l < row.getLastCellNum(); l++) 
				{
					HSSFCell cell = row.getCell(l);
					if (cell != null)
					{
						String cellString = cell.getRichStringCellValue().getString();//cell.getStringCellValue();//
						
						if (cellString.indexOf(Constants.$INFO$) != -1) 
						{
							cell.setCellValue(new HSSFRichTextString(ExcelConverterUtil.rep(map, cellString)));// 设置为需要的值
						}
					}
				}
			}
		}
	}
	
	/**
	 * 处理$total$标签
	 * @param wb
	 * @param pTargetSheetName
	 * @param startrow
	 * @param copyCount
	 * @param reList
	 */
	private void handleTotal(HSSFWorkbook wb, String pTargetSheetName,int startrow, int copyCount, List reList)
	{
		List reListClone = (List)StatUtil.CloneObject(reList);
		startrow = startrow - 1;
 		for (int p = startrow; p < startrow + copyCount; p++) 
		{
			HSSFRow row = wb.getSheet(pTargetSheetName).getRow(p);
			if (row != null)
			{
//				保存当前行单元格的所有数据到Map
        		Map totalMap = new HashMap();
				for (int l = row.getFirstCellNum(); l < row.getLastCellNum(); l++) 
				{
					HSSFCell cell = row.getCell(l);
					if (cell != null) 
					{
						String cellString = cell.getRichStringCellValue().getString();
						cellString = getCellTOTALString(cellString,reListClone,totalMap);
						cell.setCellValue(new HSSFRichTextString(cellString));
					}
				}
			}
		}
	}
	
	/**
	 * 获得$total$标签计算后的字符串
	 * @param cellString
	 * @param reListClone
	 * @return
	 */
	private String getCellTOTALString(String cellString,List reListClone,Map totalMap)
	{
		if(cellString.startsWith(Constants.$TOTAL$))
		{
			String str = cellString.substring(Constants.$TOTAL$.length());
			String type ="";//默认为int类型
			String expressionString = "#sumA=0"+type+",#root.{#sumA=#sumA+#this."+str+"},#sumA";
			Object object = null;
			try {
				for(int k=0;k<reListClone.size();k++)
				{
					Map map = (Map)reListClone.get(k);
					map = ExcelConverterUtil.mapValueStringToType(map,"Integer");
				}
				
				object = StatUtil.getOgnlValue(null, expressionString, reListClone);new Integer(10);
			} catch (OgnlException e) {
				logger.info("请查看$total$标签配置是否正确");
				e.printStackTrace();
			}
			cellString = String.valueOf(object);
			
			totalMap.put(str, cellString);
		}
		
		else if(cellString.startsWith(Constants.$DATA$))
        {
                String str = cellString.substring(Constants.$DATA$.length());
                //调用Model得到需要显示的内容
                
                System.out.println(totalMap);
                for(int k=0;k<reListClone.size();k++)
                {
                        Map map = (Map)reListClone.get(k);
                        map = ExcelConverterUtil.mapValueStringToType(map,"Integer");
                }
                String fieldData = StaticMethod.null2String((String)
                                StatUtil.getExpressionResult(totalMap,str,"0")
                                ,"0");

                cellString = fieldData;
        }
		
		return cellString;
	}
	
	/**
	 * 生成ExcelStream
	 * @param wb
	 * @param sourceSheetName
	 * @return
	 * @throws IOException
	 */
	private OutputStream outExcelStream(HSSFWorkbook wb, String sourceSheetName) throws IOException {
		ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
				
		wb.write(byteOutStream);
		byteOutStream.flush();
		byteOutStream.close();
		
		//删除与本次修改无关的sheet
		ByteArrayInputStream bis = new ByteArrayInputStream(byteOutStream.toByteArray());
		POIFSFileSystem fs = new POIFSFileSystem(bis);
		HSSFWorkbook tmp = new HSSFWorkbook(fs);
		int num = tmp.getNumberOfSheets();
		while(num != 1)
		{
			tmp.removeSheetAt(1);
			num--;
		}
		
//		合并汇总字段
//		sumArrayUnite = true;
//		if(sumArrayUnite)
//		{
//			
//		}
		
		tmp.setSheetName(0, sourceSheetName);
		ByteArrayOutputStream retBbyteOutStream = new ByteArrayOutputStream();
		tmp.write(retBbyteOutStream);
		retBbyteOutStream.flush();
		retBbyteOutStream.close();
		bis.close();

		return retBbyteOutStream;
	}
	
//	public int parseSheetAndModfiy(HSSFSheet sheet,int startRow ,int endRow ,int width)
//	{
//		//处理字典字段，根据字典字段修改配置Excel样式
//		int retLength = 0;
//		for(int i = startRow; i<endRow ;i++)
//		{
//			HSSFRow hssfRow = sheet.getRow(i + startRow);
//			
//			if(hssfRow == null)
//			{
//				continue;
//			}
//			
//			int cellNum = hssfRow.getLastCellNum() - hssfRow.getFirstCellNum() + 1;
////			if(cellNum > width)
////			{
////				cellNum = width;
////			}
//			
//			for(int j = 0; j<cellNum;j++)
//			{
//				HSSFCell hssfCell = hssfRow.getCell(j);
//				
//				if(hssfCell == null)
//				{
//					continue;
//				}
//				String cellString = ExcelConverterUtil.getCellText(hssfCell);
//				if(cellString.startsWith(Constants.DICTIONARYID))
//				{
//					String idString = cellString.substring(Constants.DICTIONARYID.length());
//					Map dictionary = (Map)dictionaryById.get(idString);
//					retLength += dictionary.size();
//					hssfRow.removeCell(hssfCell);
//					
//				}
//			}
//		}
//		
//    	//写入文件查看 测试用E:/测试使用New.xls
//		
//		return retLength;
//	}
//	
//	/**
//	 * 复制列
//	 * @param wb
//	 * @param pSourceSheetName
//	 * @param pTargetSheetName
//	 * @param pStartColumn
//	 * @param copyCount
//	 * @param pPosition
//	 */
//	public void copyColumn (HSSFWorkbook wb, String pSourceSheetName,
//			String pTargetSheetName, int pStartColumn, int copyCount, int pPosition)
//	{
//		HSSFRow sourceRow = null;
//		HSSFRow targetRow = null;
//		HSSFCell sourceCell = null;
//		HSSFCell targetCell = null;
//		HSSFSheet sourceSheet = null;
//		HSSFSheet targetSheet = null;
//		
//		
//	}

	/**
	 * 复制行
	 * 
	 * @param wb
	 *            HSSFWorkbook
	 * @param pSourceSheetName
	 *            源sheetName
	 * @param pTargetSheetName
	 *            目标sheetName
	 * @param pStartRow
	 *            拷贝起始行
	 * @param copyCount
	 *            拷贝多少行
	 * @param pPosition
	 *            拷贝到目标sheet的其始位置
	 */
	public void copyRows(HSSFWorkbook wb, String pSourceSheetName,
			String pTargetSheetName, int pStartRow, int copyCount, int pPosition) {
		HSSFRow sourceRow = null;
		HSSFRow targetRow = null;
		HSSFCell sourceCell = null;
		HSSFCell targetCell = null;
		HSSFSheet sourceSheet = null;
		HSSFSheet targetSheet = null;
		Region region = null;
		int cType;
		int i;
		short j;
		int targetRowFrom;
		int targetRowTo;

		// 这样就从1开始索引了
		pStartRow = pStartRow - 1;
		pPosition = pPosition - 1;
		//copyCount = copyCount - 1;

		if (pStartRow < 0) {
			System.out.println("Start Row[" + pStartRow + "] must > 0 !");
			return;
		}
		if (copyCount < 0) {
			System.out.println("Copy Count[" + copyCount + "] must > 0 !");
			return;
		}

		sourceSheet = wb.getSheet(pSourceSheetName);
		targetSheet = wb.getSheet(pTargetSheetName);

		// 拷贝合并的单元格
		for (i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
			region = sourceSheet.getMergedRegionAt(i);
			if ((region.getRowFrom() >= pStartRow)
					&& (region.getRowTo() <= pStartRow + copyCount)) {
				targetRowFrom = region.getRowFrom() - pStartRow + pPosition;
				targetRowTo = region.getRowTo() - pStartRow + pPosition;
				region.setRowFrom(targetRowFrom);
				region.setRowTo(targetRowTo);
				targetSheet.addMergedRegion(region);
			}
		}

		// 设置列宽
		HSSFRow row = null;
		int colNum = -1; // 得到表总列数
		for (int ii = sourceSheet.getFirstRowNum(); ii <= sourceSheet
				.getLastRowNum(); ii++) {
			row = sourceSheet.getRow(ii);
			if (row != null) {
				colNum = (colNum < (row.getLastCellNum() - row
						.getFirstCellNum())) ? (row.getLastCellNum() - row
						.getFirstCellNum()) : colNum;
			}
		}

		for (i = pStartRow; i < pStartRow + copyCount; i++) {
			sourceRow = sourceSheet.getRow(i);
			if (sourceRow != null) {
				if (sourceRow.getLastCellNum() <= 0)
					continue;
				for (j = sourceRow.getLastCellNum(); j >= 0; j--) {
					targetSheet
							.setColumnWidth(j, sourceSheet.getColumnWidth(j));
					targetSheet.setColumnHidden(j, false);

					//System.out.println(sourceSheet.getColumnWidth(j));
				}
				break;
			}
		}

		// 拷贝行并填充数据
		for (i = pStartRow; i < pStartRow + copyCount; i++) {
			sourceRow = sourceSheet.getRow(i);
			if (sourceRow == null) {
				continue;
			}
			targetRow = targetSheet.createRow(i - pStartRow + pPosition);
			targetRow.setHeight(sourceRow.getHeight());
			targetRow.setZeroHeight(false);
			for (j = sourceRow.getFirstCellNum(); j <= colNum; j++) {
				sourceCell = sourceRow.getCell(j);
				if (sourceCell == null) {
					continue;
				}

				targetCell = targetRow.createCell(j);
				targetCell.setEncoding(sourceCell.getEncoding());

				targetCell.setCellStyle(sourceCell.getCellStyle());
				cType = sourceCell.getCellType();
				targetCell.setCellType(cType);

				switch (cType) {
				case HSSFCell.CELL_TYPE_BOOLEAN:
					targetCell.setCellValue(sourceCell.getBooleanCellValue());
//					System.out.println("--------TYPE_BOOLEAN:"+ targetCell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_ERROR:
					targetCell
							.setCellErrorValue(sourceCell.getErrorCellValue());
//					System.out.println("--------TYPE_ERROR:"+ targetCell.getErrorCellValue());
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					// parseFormula这个函数的用途在后面说明
					targetCell.setCellFormula(sourceCell.getCellFormula());
//					System.out.println("--------TYPE_FORMULA:"+ targetCell.getCellFormula());
					break;

				case HSSFCell.CELL_TYPE_NUMERIC:
					targetCell.setCellValue(sourceCell.getNumericCellValue());
//					System.out.println("--------TYPE_NUMERIC:"+ targetCell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					targetCell.setCellValue(sourceCell.getRichStringCellValue());
//					System.out.println("--------TYPE_STRING:" + i+ targetCell.getRichStringCellValue());
					break;
				}
			}
		}
	}
	

	public static void main(String[] args) throws Exception {

		ExportExcel ex = new ExportExcel();
		ByteArrayOutputStream outStream = (ByteArrayOutputStream) ex.ResultExportExcel
		("E:/statistic-config-excel-commonfault-std_oracle.xls","按受理部门", initRsList(), initMap());
		
		FileOutputStream fileOut = new FileOutputStream("E:/outFile.xls");
		fileOut.write(outStream.toByteArray());
		fileOut.flush();
		fileOut.close();
	}
	
	//写Excel文件
	private void writeExcelToFile(HSSFWorkbook wb,String filePath)
	{
		FileOutputStream fileOut1 = null;
		try {
			fileOut1 = new FileOutputStream(filePath);
			wb.write(fileOut1);
			fileOut1.flush();
			fileOut1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String parseFormula(String pPOIFormula) {
		final String cstReplaceString = "ATTR(semiVolatile)"; //$NON-NLS-1$
		StringBuffer result = null;
		int index;
		result = new StringBuffer();
		index = pPOIFormula.indexOf(cstReplaceString);
		if (index >= 0) {
			result.append(pPOIFormula.substring(0, index));
			result.append(pPOIFormula.substring(index
					+ cstReplaceString.length()));
		} else {
			result.append(pPOIFormula);
		}
		return result.toString();
	}

	public static List initRsList() {
		Map d1 = new HashMap();
		d1.put("s1", "14");
		d1.put("s2", "14");
		d1.put("s3", "15");
		d1.put("f1", "01402");
		d1.put("f2", "80");
		d1.put("f3", "70");
		d1.put("f4", "80");
		d1.put("f5", "86");
		d1.put("f6", "14");
		d1.put("f7", "18");
		d1.put("f8", "123");
		Map d2 = new HashMap();
		d2.put("s1", "14");
		d2.put("s2", "14");
		d2.put("s3", "15");
		d2.put("f1", "01402");
		d2.put("f2", "8");
		d2.put("f3", "144");
		d2.put("f4", "12");
		d2.put("f5", "89");
		d2.put("f6", "144");
		d2.put("f7", "18");
		d2.put("f8", "29");
		Map d3 = new HashMap();
		d3.put("s1", "14");
		d3.put("s2", "15");
		d3.put("s3", "14");
		d3.put("f1", "01402");
		d3.put("f2", "80");
		d3.put("f3", "90");
		d3.put("f4", "10");
		d3.put("f5", "86");
		d3.put("f6", "9");
		d3.put("f7", "18");
		d3.put("f8", "29");
		Map d4 = new HashMap();
		d4.put("s1", "13");
		d4.put("s2", "14");
		d4.put("s3", "14");
		d4.put("f1", "01403");
		d4.put("f2", "20");
		d4.put("f3", "90");
		d4.put("f4", "30");
		d4.put("f5", "68");
		d4.put("f6", "19");
		d4.put("f7", "18");
		d4.put("f8", "29");
		Map d5 = new HashMap();
		d5.put("s1", "14");
		d5.put("s2", "14");
		d5.put("s3", "14");
		d5.put("f1", "01404");
		d5.put("f2", "80");
		d5.put("f3", "90");
		d5.put("f4", "330");
		d5.put("f5", "70");
		d5.put("f6", "29");
		d5.put("f7", "18");
		d5.put("f8", "29");
		Map d6 = new HashMap();
		d6.put("s1", "14");
		d6.put("s2", "14");
		d6.put("s3", "14");
		d6.put("f1", "01404");
		d6.put("f2", "33");
		d6.put("f3", "79");
		d6.put("f4", "30");
		d6.put("f5", "18");
		d6.put("f6", "29");
		d6.put("f7", "77");
		d6.put("f8", "29");
		List listResult = new ArrayList();
		listResult.add(d1);
		listResult.add(d2);
		listResult.add(d3);
		listResult.add(d4);
		listResult.add(d5);
		listResult.add(d6);

		return listResult;
	}
	
	public static Map initMap()
	{
		Map map = new HashMap();
		map.put("lasttime", "2009-8-9 22:50:00");
		map.put("begintime", "2001-8-6 22:10:00");
		map.put("endtime", "2005-5-3 22:00:00");
		return map;
	}
	public static Map dictionaryById = initDictionaryById();
	
	public static Map dictionary = initDictionary();
	
	public static Map initDictionaryById()
	{
		Map dctionaryById = new HashMap();
		dctionaryById.put("check", initDictionary());
		return dctionaryById;
	}
	
	/**
	 * 初始化字典
	 */
	public static Map initDictionary()
	{
		Map dictionaryMap = new HashMap();
		dictionaryMap.put("1", "极简单");
		dictionaryMap.put("2", "简单");
		dictionaryMap.put("3", "中等");
		dictionaryMap.put("4", "难");
		dictionaryMap.put("5", "极难");
		
		return dictionaryMap;
	}
}
