package com.boco.eoms.sheet.commonfault.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.commonfault.model.CommonFaultAuto;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultAutoManager;
import com.jspsmart.upload.SmartUpload;



/**
 * <p>Title: Excel的导入功能</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: </p>
 * @author benweiwei
 * @version 1.0(2009-10-23)
 */
public class ExcelImportForAcknowledgeUtil {

	private  String sheetType = null; 
	private  String colseSwitch = null;
	
	private String excelFilePath = null; // 上传后Excel保存的路径 
	
	


	public String getColseSwitch() {
		return colseSwitch;
	}

	public void setColseSwitch(String colseSwitch) {
		this.colseSwitch = colseSwitch;
	}

	public String getSheetType() {
		return sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	//		将EXCEL文件上传到服务器，并返回上传文件保存的路径
	public File pushExcelFile(HttpServletRequest request, String userName,
			String deptName) {
		File newExcelFile = null;

		try {
			/*
			 * 上传项目只要足够小，就应该保留在内存里 较大的项目应该被写在硬盘的临时文件上。 非常大的上传请求应该避免。
			 * 限制项目在内存中所占的空间，限制最大的上传请求，并且设定临时文件的位置。
			 */
    
			request.setCharacterEncoding("utf-8");

			RequestContext requestContext = new ServletRequestContext(request);


			if (FileUpload.isMultipartContent(requestContext)) {

				// 附件上传后保存的路径
				String uploadPath =AccessoriesMgrLocator.getAccessoriesAttributes().getUploadPath()+"accessories/uploadfile/userfiles";
				File uploadDir = new File(uploadPath); // 文件缓冲区
				if (!uploadDir.exists()) {
					uploadDir.mkdir();
				}
 
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1 * 1024 * 1024); // 设置缓冲区大小，这里是1M
				// (最大内存占用)
				factory.setRepository(uploadDir); // 设置临时目录

				ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
				servletFileUpload.setSizeMax(100 * 1024 * 1024); // 最大请求大小，这里是100M。如果为-1表示不限制大小

				// 解析请求，结果存于一个list中
				List items = servletFileUpload.parseRequest(request);
				
				Iterator iter = items.iterator();

				// 遍历这个list访问每个单独的文件项
				while (iter.hasNext()) {
					FileItem fileItem = (FileItem) iter.next();

					// 用isFormField()函数区分上传文件和常规类型域。
					if (!fileItem.isFormField()) {
						// 获得要上传的Excel文件名称，这个文件名包括路
						String fileItemName = fileItem.getName();
  
						if (fileItemName != null && !fileItemName.equals("")) {
							File excelFile = new File(fileItemName); // 要上传的文件对象
							// 要上传的Excel文件名称
							String fileName = excelFile.getName();
							// 附件的扩展名
							String expName = fileName.substring(fileName.lastIndexOf('.'));

							if (expName.toLowerCase().equals(".xls")) {
								// 附件上传后的文件名称
								String newFileName = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss")
												+ "_" + userName + "_"+ deptName+ "_"+ fileName;

								excelFilePath = uploadPath + File.separator+ newFileName;

								// 上传后的文件对象t
								newExcelFile = new File(excelFilePath);

								fileItem.write(newExcelFile); // 写入文件
							}
						}
					} else if (fileItem.isFormField()) {
						if (fileItem.getFieldName().equals("sheetType")) {
							this.setSheetType(fileItem.getString("utf-8"));
						}
						if (fileItem.getFieldName().equals("colseSwitch")) {
							this.setColseSwitch(fileItem.getString("utf-8"));
						}
					}
				}
			}
		} catch (FileUploadException ex) {
			excelFilePath = null;
			ex.printStackTrace();
		} catch (UnsupportedEncodingException ex) {
			excelFilePath = null;
			ex.printStackTrace();
		} catch (Exception ex) {
			excelFilePath = null;
			ex.printStackTrace();
		}

		return newExcelFile;
	}
	
//	将excel的第一行转换成对应的map对象
	public List sheet2map(HSSFSheet sheet, int beginRow, int endRow,String sheetType,String colseSwitch)throws Exception{
		//String dicts = "COL_110224161358,COL_110224162654,COL_110224162945,COL_110224163050,COL_110224163022,COL_110224163538";
		
	
		ArrayList sheetList = new ArrayList();

		int numberOfRows = sheet.getPhysicalNumberOfRows();
//		System.out.println(" ********** 本次共有 " + numberOfRows + " 行需要导入 **********");
		
		ITawSystemDictTypeManager _objDictManager = (ITawSystemDictTypeManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemDictTypeManager");
		TawSystemDictType tawSystemDictType = new TawSystemDictType();
		
		// 判断这个 SHEET 中是否存在行数据
		if (numberOfRows > beginRow) {
			HSSFRow tableRow = sheet.getRow(0);// 字段名称_行
			HSSFRow colsRow = sheet.getRow(1); // 字段名称_行
			HSSFRow typeRow = sheet.getRow(2); // 字段类型_行
			HSSFRow sizeRow = sheet.getRow(3); // 字段大小_行
			HSSFRow nameRow = sheet.getRow(4); // 字段含义_行
			
			HSSFRow valueRow1 = sheet.getRow(0); // 读取第一行数据
			int numberOfCells = valueRow1.getPhysicalNumberOfCells(); //取得列数
			
			for (int i = beginRow+1; i < numberOfRows; i++) {
				String netSortOne = "";
				String netSortTwo = "";
				String netSortThree = "";
				CommonFaultAuto commonFaultAuto = new CommonFaultAuto();//新建一个自动对象
//				判断是否有表名
				HSSFRow valueRow = sheet.getRow(i); // 读取一行数据
				if ("网络告警ID".equals(tableRow.getCell(0).getStringCellValue())) {
					commonFaultAuto.setRemark1(valueRow.getCell(0).getStringCellValue());
				}else {
					break;
				}
				if ("措施".equals(tableRow.getCell(1).getStringCellValue())) {
					commonFaultAuto.setCommonFaultDesc(valueRow.getCell(1).getStringCellValue());
				}else {
					break;
				}
				if ("归档描述".equals(tableRow.getCell(2).getStringCellValue())) {
					commonFaultAuto.setRemark2(valueRow.getCell(2).getStringCellValue());
				}else {
					break;
				}
				if ("厂家设备名称".equals(tableRow.getCell(3).getStringCellValue())) {
					if ("".equals(valueRow.getCell(3).getStringCellValue()) || null == valueRow.getCell(3).getStringCellValue()) {
						commonFaultAuto.setEquipmentName(valueRow.getCell(3).getStringCellValue());
					}else {
						tawSystemDictType = _objDictManager.getDictByDictName(valueRow.getCell(3).getStringCellValue(), "1010103");
						commonFaultAuto.setEquipmentName(StaticMethod.null2String(tawSystemDictType.getDictId()));
					}
				}else {
					break;
				}
				if ("告警类型".equals(tableRow.getCell(4).getStringCellValue())) {
					commonFaultAuto.setAlarmType(valueRow.getCell(4).getStringCellValue());
				}else {
					break;
				}
				if ("网络分类(一级)".equals(tableRow.getCell(5).getStringCellValue())) {
					if ("".equals(valueRow.getCell(5).getStringCellValue()) || null == valueRow.getCell(5).getStringCellValue()) {
						commonFaultAuto.setNetSortOne(valueRow.getCell(5).getStringCellValue());
					}else {
						tawSystemDictType = _objDictManager.getDictByDictName(valueRow.getCell(5).getStringCellValue(), "1010104");
						netSortOne = StaticMethod.null2String(tawSystemDictType.getDictId());
						commonFaultAuto.setNetSortOne(netSortOne);
					}
				}else {
					break;
				}
				if ("网络分类(二级)".equals(tableRow.getCell(6).getStringCellValue())) {
					if ("".equals(valueRow.getCell(6).getStringCellValue()) || null == valueRow.getCell(6).getStringCellValue() || "".equals(netSortOne)) {
						commonFaultAuto.setNetSortTwo(valueRow.getCell(6).getStringCellValue());
					}else {
						tawSystemDictType = _objDictManager.getDictByDictName(valueRow.getCell(6).getStringCellValue(), netSortOne);
						netSortTwo = StaticMethod.null2String(tawSystemDictType.getDictId());
						commonFaultAuto.setNetSortTwo(netSortTwo);
					}
				}else {
					break;
				}
				if ("网络分类(三级)".equals(tableRow.getCell(7).getStringCellValue())) {
					if ("".equals(valueRow.getCell(7).getStringCellValue()) || null == valueRow.getCell(7).getStringCellValue() || "".equals(netSortTwo)) {
						commonFaultAuto.setNetSortThree(valueRow.getCell(7).getStringCellValue());
					}else {
						tawSystemDictType = _objDictManager.getDictByDictName(valueRow.getCell(7).getStringCellValue(), netSortTwo);
						netSortThree = StaticMethod.null2String(tawSystemDictType.getDictId());
						commonFaultAuto.setNetSortThree(netSortThree);
					}
				}else {
					break;
				}
	
				
				ICommonFaultAutoManager commonfaultAutoMgr = (ICommonFaultAutoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
				try {
					commonFaultAuto.setRuleType("autoHold");
					commonFaultAuto.setCreateDate(new Date());
					commonFaultAuto.setSheetType(sheetType);
					commonFaultAuto.setColseSwitch(colseSwitch);
					commonFaultAuto.setId(UUIDHexGenerator.getInstance().getID());
					commonfaultAutoMgr.saveObject(commonFaultAuto);
				} catch (Exception e) {
					e.printStackTrace();
					sheetList.add("保存新技术案例时发生错误，error："+e.getMessage());
				}
			}
		}
		return sheetList;
	}

//	导入excel
	public List processExcel(File excelFile, int beginRow, int endRow,String sheetType,String colseSwitch )
		throws Exception {
	
		ArrayList okList = new ArrayList();
		
		FileInputStream inputStream = new FileInputStream(excelFile.getAbsolutePath());
		
		// 创建要导入的Excel文件读入流
		POIFSFileSystem poiFileSystem = new POIFSFileSystem(inputStream);
		
		// 创建Excel工作薄对象
		HSSFWorkbook workBook = new HSSFWorkbook(poiFileSystem);
		int sheetSize = workBook.getNumberOfSheets();
		List sheetList = null;
		for (int sheetNum = 0; sheetNum < sheetSize; sheetNum++) {
			// 读取 EXCEL 文件中的一个 SHEET
			HSSFSheet sheet = workBook.getSheetAt(sheetNum);
		
			// 解析一个 Sheet， 将 Sheet 中的每一行都转换成一个 Map。
		
			sheetList = new ArrayList();
			try {
				sheetList = this.sheet2map(sheet, beginRow, endRow,sheetType,colseSwitch);
			} catch (Exception e) {
				e.printStackTrace();
				okList.add("模板错误");
			}
			if(sheetList.size()!=0){
				for (int i = 0; i < sheetList.size(); i++) {
					okList.add("对不起你的第"+(beginRow+i+1)+"行出错了,请检查数据是否正确。");
					System.out.println("对不起你的第"+(beginRow+i+1)+"行出错了");
				}				
			}	
		}
		return okList;
	}
	
}
