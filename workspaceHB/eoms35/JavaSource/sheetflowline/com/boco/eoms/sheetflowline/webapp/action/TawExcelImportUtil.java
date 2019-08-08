package com.boco.eoms.sheetflowline.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;

import com.boco.eoms.sheetflowline.mgr.IPreAllocatedMgr;
import com.boco.eoms.sheetflowline.model.PreAllocated;

public class TawExcelImportUtil {
    private String sheetType = null;
    private String colseSwitch = null;

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

    //	将EXCEL文件上传到服务器，并返回上传文件保存的路径
    public File pushExcelFile(HttpServletRequest request, String userName,
                              String deptName) {
        File newExcelFile = null;
        String excelFilePath = "";
        try {
            /*
             * 上传项目只要足够小，就应该保留在内存里 较大的项目应该被写在硬盘的临时文件上。 非常大的上传请求应该避免。
             * 限制项目在内存中所占的空间，限制最大的上传请求，并且设定临时文件的位置。
             */

            request.setCharacterEncoding("utf-8");

            RequestContext requestContext = new ServletRequestContext(request);


            if (FileUpload.isMultipartContent(requestContext)) {

                // 附件上传后保存的路径
                String uploadPath = AccessoriesMgrLocator.getAccessoriesAttributes().getUploadPath() + "accessories/uploadfile/userfiles";
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
                                        + "_" + userName + "_" + deptName + "_" + fileName;

                                excelFilePath = uploadPath + File.separator + newFileName;

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

    //	导入excel
    public List processExcel(File excelFile, int beginRow, int endRow, String sheetType, String colseSwitch, String userId)
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
                sheetList = this.sheet2map(sheet, beginRow, endRow, sheetType, colseSwitch, userId);
            } catch (Exception e) {
                e.printStackTrace();
                okList.add("模板错误");
            }
            if (sheetList.size() != 0) {
                for (int i = 0; i < sheetList.size(); i++) {
                    okList.add("对不起你的第" + (beginRow + i + 1) + "行出错了,请检查数据是否正确。");
                    System.out.println("对不起你的第" + (beginRow + i + 1) + "行出错了");
                }
            }
        }
        return okList;
    }

    public List sheet2map(HSSFSheet sheet, int beginRow, int endRow, String sheetType, String colseSwitch, String userId) throws Exception {

        IPreAllocatedMgr preAllocateMgr = (IPreAllocatedMgr) ApplicationContextHolder.getInstance().getBean("preAllocatedMgr");

        ArrayList sheetList = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            //		System.out.println(" ********** 本次共有 " + numberOfRows + " 行需要导入 **********");

            // 判断这个 SHEET 中是否存在行数据
            if (numberOfRows > beginRow) {
                HSSFRow tableRow = sheet.getRow(0);// 字段名称_行

                for (int i = beginRow + 1; i < numberOfRows; i++) {
                    PreAllocated message = new PreAllocated();//新建一个预分配对象
                    //				判断是否有表名
                    HSSFRow valueRow = sheet.getRow(i); // 读取一行数据
                    if ("开始时间".equals(tableRow.getCell(0).getStringCellValue())) {
                        String startTime = valueRow.getCell(0).getStringCellValue();
                        Date startDate = sdf.parse(startTime);
                        message.setStartTime(startDate);
                    } else {
                        break;
                    }
                    if ("结束时间".equals(tableRow.getCell(1).getStringCellValue())) {
                        String endTime = valueRow.getCell(1).getStringCellValue();
                        Date endDate = sdf.parse(endTime);
                        message.setStartTime(endDate);
                    } else {
                        break;
                    }
                    if ("值班长".equals(tableRow.getCell(2).getStringCellValue())) {
                        message.setDutyLeader(valueRow.getCell(2).getStringCellValue());
                    } else {
                        break;
                    }
                    if ("人员".equals(tableRow.getCell(3).getStringCellValue())) {
                        message.setDutyUserId(valueRow.getCell(3).getStringCellValue());
                    } else {
                        break;
                    }
                    if ("网络分类一级".equals(tableRow.getCell(4).getStringCellValue())) {
                        message.setNetTypeOne(valueRow.getCell(4).getStringCellValue());
                    } else {
                        break;
                    }
                    if ("网络分类二级".equals(tableRow.getCell(5).getStringCellValue())) {
                        message.setNetTypeTwo(valueRow.getCell(5).getStringCellValue());
                    } else {
                        break;
                    }
                    if ("网络分类三级".equals(tableRow.getCell(6).getStringCellValue())) {
                        message.setNetTypeThree(valueRow.getCell(6).getStringCellValue());
                    } else {
                        break;
                    }
                    if ("厂商".equals(tableRow.getCell(7).getStringCellValue())) {
                        message.setVendor(valueRow.getCell(7).getStringCellValue());
                    } else {
                        break;
                    }
                    if ("故障响应级别".equals(tableRow.getCell(8).getStringCellValue())) {
                        message.setFaultResponseLevel(valueRow.getCell(8).getStringCellValue());
                    } else {
                        break;
                    }
                    if ("分配数量".equals(tableRow.getCell(9).getStringCellValue())) {
                        message.setCount((int) valueRow.getCell(9).getNumericCellValue());
                    } else {
                        break;
                    }
                    if ("开关".equals(tableRow.getCell(10).getStringCellValue())) {
                        message.setCount((int) valueRow.getCell(10).getNumericCellValue());
                    } else {
                        break;
                    }
                    message.setCreateTime(sdf.parse(StaticMethod.getCurrentDateTime()));
                    message.setCreateUser(userId);
                    preAllocateMgr.saveObject(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            sheetList.add("保存预分配信息时发生错误，error：" + e.getMessage());
        }
        return sheetList;
    }

}





