package com.boco.eoms.commonfaulthj.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.boco.eoms.commonfaulthj.mgr.CommonfaulthjMgr;
import com.boco.eoms.commonfaulthj.model.Commonfaulthj;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;

public class CommonfaulthjExcelImportUtil {
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
                String uploadPath = AccessoriesMgrLocator.getAccessoriesAttributes().getUploadPath() + "accessories/uploadfile/commonfaultimport";
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
                                System.out.println("文件上传的位置33333----------------newExcelFile:" + newExcelFile);


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
        //	System.out.println("文件上传的位置22222----------------newExcelFile:"+newExcelFile);
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
        List sheetList = null;
        // 读取 EXCEL 文件中的一个 SHEET
        HSSFSheet sheet = workBook.getSheetAt(0);

        // 解析一个 Sheet， 将 Sheet 中的每一行都转换成一个 Map。

        sheetList = new ArrayList();
        try {
            sheetList = this.sheet2mapone(sheet, beginRow, endRow, sheetType, colseSwitch, userId);
        } catch (Exception e) {
            e.printStackTrace();
            okList.add("模板错误");
        }
        if (sheetList != null && sheetList.size() != 0) {
            for (int i = 0; i < sheetList.size(); i++) {
                okList.add("对不起你的第" + (beginRow + i + 1) + "行出错了,请检查数据是否正确。");
                System.out.println("对不起你的第" + (beginRow + i + 1) + "行出错了");
            }
        }
        return okList;
    }

    public List sheet2mapone(HSSFSheet sheet, int beginRow, int endRow, String sheetType, String colseSwitch, String userId) throws Exception {
        try {
//			工单核减模版
            CommonfaulthjMgr commonfaulthjMgr = (CommonfaulthjMgr) ApplicationContextHolder.getInstance().getBean("commonfaulthjMgr");
            IDownLoadSheetAccessoriesService mgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
            String sql = "select distinct de.dictname,de.dictid  from taw_system_dicttype de where  de.parentdictid like '1010320%' ";
            List dictList = mgr.getSheetAccessoriesList(sql);
            Map dictMap = new HashMap();
            if (dictList != null && dictList.size() > 0) {
                for (int i = 0; i < dictList.size(); i++) {
                    Map dict = (Map) dictList.get(i);
                    dictMap.put(dict.get("dictname"), dict.get("dictid"));
                }
            }

//			ArrayList sheetList = new ArrayList();
            List trueList = new ArrayList();
            List errorList = new ArrayList();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            int numberOfRows = sheet.getPhysicalNumberOfRows();
            //		System.out.println(" ********** 本次共有 " + numberOfRows + " 行需要导入 **********");

            HSSFRow readRow1 = sheet.getRow(0);
            int col = readRow1.getPhysicalNumberOfCells();

            // 判断这个 SHEET 中是否存在行数据
            if (numberOfRows > beginRow) {
//				HSSFRow tableRow = sheet.getRow(0);// 字段名称_行

                for (int i = beginRow + 1; i < numberOfRows; i++) {
                    String falg = "";
                    Commonfaulthj message = new Commonfaulthj();//新建一个预分配对象
                    //				判断是否有表名
                    HSSFRow valueRow = sheet.getRow(i); // 读取一行数据
                    for (int j = 0; j < col; j++) {
                        HSSFCell readCell = valueRow.getCell((short) j);
                        if (j == 0) {
                            String str = "";
                            if (null == readCell || valueRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                str = "";
                            } else {
                                str = readCell.toString();
                            }
                            System.out.println("工单号 = " + str);
                            message.setSheetid(str);
                        }
                        if (j == 1) {
                            String str = "";
                            if (null == readCell || valueRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                str = "";
                            } else {
                                str = readCell.toString();
                            }
                            System.out.println("导入人 = " + str);
                            message.setCreater(str);
                        }
                        if (j == 2) {
                            String str = "";
                            if (null == readCell || valueRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                str = "";
                            } else {
                                str = readCell.toString();
                            }
                            System.out.println("核减理由 = " + str);
                            message.setRemark(str);
                        }
                        if (j == 3) {
                            String str = "";
                            if (null == readCell || valueRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                str = "";
                                falg = "核减类型不能为空！";
                            } else {
                                str = readCell.toString();
                                str = StaticMethod.nullObject2String(dictMap.get(str));
                                System.out.println("dictMap.get(str)==" + str);
                                if (str == null || "".equals(str)) {
                                    falg = "请正确填入核减类型";
                                }
                            }
                            System.out.println("核减类型 = " + str + "------falg=" + falg);
                            message.setSubtractType(str);
                        }
                    }
                    message.setSavetime(new Date());
                    //commonfaulthjMgr.saveCommonfaulthj(message);
                    if ("".equals(falg)) {
                        trueList.add(message);
                    } else {
                        falg = "核减类型不能为空，填写不对请查证";
                        errorList.add(falg);
                    }
                }
            }
            if (errorList == null || errorList.size() == 0) {
                for (int j = 0; j < trueList.size(); j++) {
                    Commonfaulthj message1 = (Commonfaulthj) trueList.get(j);
                    commonfaulthjMgr.saveCommonfaulthj(message1);
                }
            } else {
                return errorList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("保存预分配信息时发生错误，error：" + e.getMessage());
        }
        return null;
    }


}





