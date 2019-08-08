package com.boco.eoms.commonfaulthj.util;

import java.io.*;


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
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.accessories.util.AccessoriesUtil;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;

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


    public File pushExcelFile1(HttpServletRequest request, String userName,
                               String deptName) {
        List list = new ArrayList();
        File tempFile = null;
        File currentFile = null;
        Part part;
        Integer maxSize = new Integer(100);

        try {
            // 确定这个请求确实来自于文件上传
            if (request.getContentType() != null
                    && request.getContentType().indexOf("multipart/form-data") >= 0) {
                // 获取最大文件长度（单位:M）

                MultipartParser mp = new MultipartParser(request, maxSize.intValue() * 1024 * 1024, true, true, "UTF-8");
                // 获取文件保存路径
                String uploadPath = AccessoriesMgrLocator.getAccessoriesAttributes().getUploadPath() + "accessories/uploadfile/maintaincommandimport";

                AccessoriesUtil.createFile(uploadPath, "/");
                File file = new File(uploadPath);
                if (!file.exists()) {
                    file.mkdir();
                }
                while ((part = mp.readNextPart()) != null) {

                    if (part.isFile()) {
                        FilePart filePart = (FilePart) part;
                        String fileCnName = filePart.getFileName();
                        if (fileCnName != null) {
                            filePart.writeTo(file);
                            String fileName = StaticMethod
                                    .getCurrentDateTime("yyyyMMddHHmmss");
                            currentFile = new File(file, fileCnName);
                            fileName = fileName
                                    + fileCnName.substring(fileCnName
                                    .lastIndexOf("."));
                            tempFile = new File(file, fileName);
                            if (currentFile.isFile()) {
                                currentFile.renameTo(tempFile);
                            }
                        }// if (fileName != null)
                    }// if(part.isFile())
                }// while

            }
        } catch (Exception lEx) {
            lEx.printStackTrace();
            list.add("上传文件不能超过" + maxSize.intValue() + "M");
            //throw new AccessoriesException("文件上传错误");
        }
        return tempFile;
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
                String uploadPath = AccessoriesMgrLocator.getAccessoriesAttributes().getUploadPath() + "accessories/uploadfile/maintaincommandimport";
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
                System.out.println("lyglyglyg=" + items.size());
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
                                        + "_" + userName.trim() + "_" + deptName.trim() + "_" + fileName.trim();

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
    public List processExcel(File excelFile, int beginRow, int endRow, String sheetType, String colseSwitch, String userId, String flag)
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

            sheetList = this.sheet2mapone(sheet, beginRow, endRow, sheetType, colseSwitch, userId, flag);

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
        return okList;
    }

    public List sheet2mapone(HSSFSheet sheet, int beginRow, int endRow, String sheetType, String colseSwitch, String userId, String flag) throws Exception {

        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String today = dateFormat.format(new Date());
//		IMaintainCommandMainManager iMaintainCommandMainManager=(IMaintainCommandMainManager)ApplicationContextHolder.getInstance().getBean("iMaintainCommandMainManager");
        ArrayList sheetList = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            System.out.println(" ********** 本次共有 " + numberOfRows + " 行需要导入 **********");

            HSSFRow readRow1 = sheet.getRow(0);
            //得到列的个数
            int col = readRow1.getPhysicalNumberOfCells();

            // 判断这个 SHEET 中是否存在行数据
            if (numberOfRows > beginRow && col > 5) {
                HSSFRow tableRow = sheet.getRow(0);// 字段名称_行

                for (int i = beginRow + 1; i < numberOfRows; i++) {
                    //				判断是否有表名
                    HSSFRow valueRow = sheet.getRow(i); // 读取一行数据
                    String str0 = "";//网元名称
                    String str1 = "";//告警ID
                    String str2 = "";//网元ID
                    String str3 = "";//地市
                    String str4 = "";//匹配班组
                    String str5 = "";//抄送班组

                    if (null == valueRow.getCell((short) 0) || valueRow.getCell((short) 0).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                        str0 = "";
                    } else {
                        //得到这行数据的第一个格子
                        str0 = valueRow.getCell((short) 0).toString();
                    }
                    if (null == valueRow.getCell((short) 1) || valueRow.getCell((short) 1).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                        str1 = "";
                    } else {
                        //得到这行数据的第一个格子
                        str1 = valueRow.getCell((short) 1).toString();
                    }

                    if (null == valueRow.getCell((short) 2) || valueRow.getCell((short) 2).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                        str2 = "";
                    } else {
//						得到这行数据的第二个格子
                        str2 = valueRow.getCell((short) 2).toString();
                    }
                    if (null == valueRow.getCell((short) 3) || valueRow.getCell((short) 3).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                        str3 = "";
                    } else {
//						得到这行数据的第3个格子
                        str3 = valueRow.getCell((short) 3).toString();
                    }
                    if (null == valueRow.getCell((short) 4) || valueRow.getCell((short) 4).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                        str4 = "";
                    } else {
//						得到这行数据的第4个格子
                        str4 = valueRow.getCell((short) 4).toString();
                    }
                    if (null == valueRow.getCell((short) 5) || valueRow.getCell((short) 5).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                        str5 = "";
                    } else {
//						得到这行数据的第5个格子
                        str5 = valueRow.getCell((short) 5).toString();
                    }


                    if ("".equals(str0) && "".equals(str1) && "".equals(str2) && "".equals(str3) && "".equals(str4) && "".equals(str5)) {
                        //表示已经到最后一排了
                    } else {
                        Map map = new HashMap();
                        map.put("str0", str0);
                        map.put("str1", str1);
                        map.put("str2", str2);
                        map.put("str3", str3);
                        map.put("str4", str4);
                        map.put("str5", str5);
                        map.put("createuserid", userId);
                        map.put("flag", flag);
                        saveOrdeleted(map);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            sheetList.add("保存预分配信息时发生错误，error：" + e.getMessage());
        }
        return sheetList;
    }


//	public void getfile(HttpServletRequest request){
//		MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;   
//        File file = wrapper.getFile("imgFile");   
//        String fileName = wrapper.getFilesystemName("imgFile");
//        //检查文件大小  
////        if(file.length() > maxSize){  
////            String temStr= "上传文件大小超过限制。";  
////            this.writeResponse(response, temStr);  
////            return;  
////        }  
//        //检查扩展名  
//        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();  
////        if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){  
////            String temStr= "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。";  
////            this.writeResponse(response, temStr);  
////            return;  
////        }  
//  
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
//        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;  
//  
//        try {    
//            InputStream in = new FileInputStream(file);    
//            File uploadFile = new File(savePath, newFileName);    
//            OutputStream out = new FileOutputStream(uploadFile);    
//            byte[] buffer = new byte[1024 * 1024];    
//            int length;    
//            while ((length = in.read(buffer)) > 0) {    
//                out.write(buffer, 0, length);
//            }    
//    
//            in.close();    
//            out.close();    
//        } catch (FileNotFoundException ex) {    
//            ex.printStackTrace();    
//        } catch (IOException ex) {    
//            ex.printStackTrace();    
//        }
//	}

    /**
     * NETNAME        VARCHAR2(1000),--网元名称
     * ALARMID        VARCHAR2(2000),--告警Id
     * NETID        VARCHAR2(1000),--网元ID
     * city        VARCHAR2(1000),--地市
     * SUBROLEID      VARCHAR2(100),--匹配班组
     * ccsubroleid VARCHAR2(100), --抄送班组
     * createtime DATE, --保存时间
     * createuserid VARCHAR2(100) --保存人
     *
     * @param map
     * @return
     * @throws Exception
     */
    public String saveOrdeleted(Map map) throws Exception {
        IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        String netName = StaticMethod.nullObject2String(map.get("str0"));
        String alarmId = StaticMethod.nullObject2String(map.get("str1"));
        String netId = StaticMethod.nullObject2String(map.get("str2"));
        String city = StaticMethod.nullObject2String(map.get("str3"));
        String subroleId = StaticMethod.nullObject2String(map.get("str4"));
        String ccsubroleId = StaticMethod.nullObject2String(map.get("str5"));
        String createuserid = StaticMethod.nullObject2String(map.get("createuserid"));
        String flag = StaticMethod.nullObject2String(map.get("flag"));
        String saveOrdeletedSql = "";
        if ("1".equals(flag) || "2".equals(flag)) {//保存 和 修改
            String infoFlag = getInfo(netName, alarmId);
            if ("0".equals(infoFlag)) {//表示表中已经有数据了，那么修改这条数据
                saveOrdeletedSql = "update COMMONFAULT_NEW_ROLE set netId = '" + netId + "',city= '" + city + "',subroleId='" + subroleId + "',ccsubroleId='" + ccsubroleId + "',createtime=sysdate,createuserid='" + createuserid + "' WHERE netname = '" + netName + "' and AlarmId = '" + alarmId + "' ";
            } else {
                saveOrdeletedSql = "insert into COMMONFAULT_NEW_ROLE values('" + netName + "','" + alarmId + "','" + netId + "','" + city + "','" + subroleId + "','" + ccsubroleId + "',sysdate,'" + createuserid + "')";
            }
        } else if ("3".equals(flag)) {//删除
            saveOrdeletedSql = "delete from COMMONFAULT_NEW_ROLE where netname = '" + netName + "' and AlarmId = '" + alarmId + "'";
        }
        if (!"".equals(saveOrdeletedSql)) {
            sqlMgr.executeProcedure(saveOrdeletedSql);
        }
        return null;
    }

    public String getInfo(String netName, String alarmId) throws Exception {
        String infoSql = "SELECT * FROM COMMONFAULT_NEW_ROLE t WHERE netname = '" + netName + "' and AlarmId = '" + alarmId + "'";
        IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        List infoList = sqlMgr.getSheetAccessoriesList(infoSql);
        String infoFlag = "1";
        if (infoList != null && infoList.size() > 0) {
            infoFlag = "0";
        }
        return infoFlag;
    }

}





