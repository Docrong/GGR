/*
 * Created on 2007-9-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.accessories.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpLoginException;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.EOMSAttributes;
import com.boco.eoms.base.util.FileDownLoad;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesDao;
import com.boco.eoms.commons.accessories.exception.AccessoriesConfigException;
import com.boco.eoms.commons.accessories.exception.AccessoriesException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesConfigManager;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.accessories.util.AccessoriesUtil;
import com.boco.eoms.commons.loging.BocoLog;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;
import com.boco.eoms.commons.util.xml.XmlManage;

/**
 * @author IBM_USER
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TawCommonsAccessoriesManagerCOSImpl extends BaseManager implements
        ITawCommonsAccessoriesManager {
    private TawCommonsAccessoriesDao dao;

    private ITawCommonsAccessoriesConfigManager configManager;

    private String filePath;

    FtpClient fc = new FtpClient();

    /**
     * @return Returns the dao.
     */
    public TawCommonsAccessoriesDao getDao() {
        return dao;
    }

    /**
     * @param dao The dao to set.
     */
    public void setDao(TawCommonsAccessoriesDao dao) {
        this.dao = dao;
    }

    /**
     * @return Returns the configManager.
     */
    public ITawCommonsAccessoriesConfigManager getConfigManager() {
        return configManager;
    }

    /**
     * @param configManager The configManager to set.
     */
    public void setConfigManager(
            ITawCommonsAccessoriesConfigManager configManager) {
        this.configManager = configManager;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager#getTawCommonsAccessoriess()
     */
    public List getTawCommonsAccessoriess() {
        // TODO Auto-generated method stub
        return dao.getTawCommonsAccessoriess();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager#getTawCommonsAccessories(java.lang.String)
     */
    public TawCommonsAccessories getTawCommonsAccessories(final String id) {
        return dao.getTawCommonsAccessories(new String(id));
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager#saveTawCommonsAccessories(com.boco.eoms.commons.accessories.model.TawCommonsAccessories)
     */
    public void saveTawCommonsAccessories(
            TawCommonsAccessories tawCommonsAccessories) {
        dao.saveTawCommonsAccessories(tawCommonsAccessories);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager#removeTawCommonsAccessories(java.lang.String)
     */
    public void removeTawCommonsAccessories(final String id) {
        dao.removeTawCommonsAccessories(new String(id));
    }

    /**
     * 文件上传
     *
     * @param request
     * @param appCode
     * @return
     * @throws FileUploadException
     * @throws IOException
     * @throws AccessoriesException
     * @author 秦敏
     */
    public List saveFile(HttpServletRequest request, String appCode,
                         String accesspriesFileNames) throws AccessoriesException {
        // TODO Auto-generated method stub
        List list = new ArrayList();
        File tempFile = null;
        File currentFile = null;
        Part part;
        accesspriesFileNames = StaticMethod
                .nullObject2String(accesspriesFileNames);

        try {
            // 确定这个请求确实来自于文件上传
            if (request.getContentType() != null
                    && request.getContentType().indexOf("multipart/form-data") >= 0) {
                // 获取最大文件长度（单位:M）
                TawCommonsAccessoriesConfig config = this.configManager
                        .getAccessoriesConfigByAppcode(appCode);
                Integer maxSize = config.getMaxSize();

                MultipartParser mp = new MultipartParser(request, maxSize
                        .intValue() * 1024 * 1024, true, true, "UTF-8");
                // 获取文件保存路径
                String rootFilePath = AccessoriesMgrLocator
                        .getAccessoriesAttributes().getUploadPath();
                String path = this.filePath.substring(this.filePath
                        .indexOf(":") + 1, this.filePath.length())
                        + config.getPath();
                String filePath = rootFilePath + path;

                AccessoriesUtil.createFile(filePath, "/");
                File file = new File(filePath);
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
                            // 附件信息入库
                            TawCommonsAccessories accessories = new TawCommonsAccessories();
                            accessories.setAccessoriesCnName(fileCnName);
                            accessories.setAccessoriesName(fileName);
                            accessories.setAccessoriesPath(path);
                            accessories.setAccessoriesSize(tempFile.length());
                            accessories.setAccessoriesUploadTime(StaticMethod
                                    .getLocalTime());

                            accessories.setAppCode(config.getAppCode());
                            this.dao.saveTawCommonsAccessories(accessories);

                            accesspriesFileNames = accesspriesFileNames + ",'"
                                    + fileName + "'";
                        }// if (fileName != null)
                    }// if(part.isFile())
                }// while

            }
            if (!accesspriesFileNames.equals("")) {
                if (accesspriesFileNames.indexOf(",") == 0) {
                    accesspriesFileNames = accesspriesFileNames.substring(1);
                }
                list = dao.getAllFileByName(accesspriesFileNames);
            }
        } catch (Exception lEx) {
            lEx.printStackTrace();
            BocoLog.error(this, "文件上传错误");
            throw new AccessoriesException("文件上传错误");
        }
        return list;
    }

    /**
     * 根据文件名称查询文件信息
     *
     * @param fileNames 文件名称，用","分割
     * @return TawCommonsAccessories对象的List数组
     * @author 秦敏
     */
    public List getAllFileById(String fileIds) throws AccessoriesException {
        List list = dao.getAllFileByName(fileIds);
        return list;
    }

    /**
     * 根据文件名称查询文件存放路径。
     *
     * @param fileNames 文件名称，用","分割
     * @return String[] 路径数组
     */
    public String[] getFilePathByName(String fileNames)
            throws AccessoriesException {
        List list = dao.getAllFileByName(fileNames);
        String[] filePaths = new String[list.size()];
        TawCommonsAccessories accessories = null;
        for (int i = 0; i < list.size(); i++) {
            accessories = (TawCommonsAccessories) list.get(i);
            String filePath = accessories.getAccessoriesPath() + "\\"
                    + accessories.getAccessoriesName();
            filePaths[i] = filePath;
        }
        return filePaths;
    }

    /**
     * @return Returns the filePath.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath The filePath to set.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesConfigManager#getFilePath(java.lang.String)
     */
    public String getFilePath(String appId) throws AccessoriesConfigException {
        TawCommonsAccessoriesConfig config = configManager
                .getAccessoriesConfigByAppcode(appId);
        // 获取文件保存路径
        String rootFilePath = AccessoriesMgrLocator.getAccessoriesAttributes()
                .getUploadPath();
        String path = this.filePath.substring(this.filePath.indexOf(":") + 1,
                this.filePath.length())
                + config.getPath();
        return rootFilePath + path + "/";
    }

    public String getUrlById(String id) {
        // String rootFilePath =
        // AccessoriesMgrLocator.getAccessoriesAttributes()
        // .getUploadPath();
        // TawCommonsAccessories accessories = getTawCommonsAccessories(id);
        // String path = "";
        // if (null != accessories) {
        // if (null != accessories.getAccessoriesPath()
        // && !"".equals(accessories.getAccessoriesPath())) {
        // String accessoriesPath = accessories.getAccessoriesPath();
        // path = accessoriesPath.substring(
        // accessoriesPath.indexOf(":") + 1, accessoriesPath
        // .length());
        // }
        // }
        // return rootFilePath + path + File.separator +
        // accessories.getAccessoriesName();
        return ((EOMSAttributes) ApplicationContextHolder.getInstance()
                .getBean("eomsAttributes")).getEomsUrl()
                + "/accessories/tawCommonsAccessoriesConfigs.do?method=download&id="
                + id;
    }

    /**
     * 外系统附件下载接口(http&FTP)
     *
     * @param code          本系统对应的附件类型
     * @param cnName        附件实际名
     * @param strRemoteAddr 附件存放地址
     * @param downType      附件下载类型（http，ftp）
     * @return
     * @author 赵东亮
     */
    public String downFromOtherSystem(String cnName, String strRemoteAddr,
                                      String code) {
        String fileName = "";
        URL url = null;
        String errFtp = "";
        try {
            // 根据code获取附件的配置信息
            TawCommonsAccessoriesConfig tc = configManager
                    .getAccessoriesConfigByAppcode(code);
            // 获取服务器上的文件存储路径（物理路径）
            String rootFilePath = AccessoriesMgrLocator
                    .getAccessoriesAttributes().getUploadPath();
            String path = this.filePath.substring(
                    this.filePath.indexOf(":") + 1, this.filePath.length())
                    + tc.getPath();
            String physicalPath = rootFilePath + "/" + path + "/";
            // 根据传送过来的url获取url中的文件名
            if (strRemoteAddr.indexOf("&amp;") >= 0) {
                strRemoteAddr = strRemoteAddr.replace("&amp;", "&");
            }
            url = new URL(strRemoteAddr);
            String realFileName = (new File(url.getFile())).getName();
            // 生成系统的实际文件名
            String sysFilename = "";
            System.out.println("@@@@@@@@@@@@@@@@@@@@cnName = " + cnName);
            if (cnName.equals("")) {
                sysFilename = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss")
                        + this.randomKey(4) + realFileName.substring(realFileName.indexOf("."));
            } else {
                //			if(cnName.indexOf(".")>=0){
                sysFilename = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss")
                        + this.randomKey(4) + cnName.substring(cnName.indexOf("."));
                //			}else{
                //				sysFilename = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss")
                //				+ this.randomKey(4) + realFileName.substring(realFileName.indexOf("."));
                //			}
            }
            System.out.println("@@@@@@@@@@@@@@@@@@@@sysFilename = " + sysFilename);
			/*String sysFilename = StaticMethod
					.getCurrentDateTime("yyyyMMddHHmmss")
					+ realFileName.substring(realFileName.indexOf("."));*/
            // 下载文件
            String downType = strRemoteAddr.substring(0, 1);
            if (downType.equals("h")) {
                Thread downThread = new Thread(new FileDownLoad(strRemoteAddr,
                        physicalPath), sysFilename);
                downThread.start();
            } else if (downType.equals("f")) {
                String ftpserver = StaticMethod.nullObject2String(XmlManage
                        .getFile("/config/accessoriesServer.xml").getProperty(
                                "ftp.ip"));
                String serverPath = strRemoteAddr.substring(strRemoteAddr
                        .indexOf(ftpserver)
                        + ftpserver.length(), strRemoteAddr.length());
                errFtp = this.downloadFileByFtp(sysFilename, physicalPath,
                        serverPath);
                if (!errFtp.equals("")) {
                    BocoLog.error(this, errFtp);
                    System.out.println(errFtp);
                }
            }
            // 附件信息入库
            TawCommonsAccessories accessories = new TawCommonsAccessories();
            if (!cnName.equals("")) {
                accessories.setAccessoriesCnName(cnName);
            } else {
                accessories.setAccessoriesCnName(realFileName);
            }
            accessories.setAccessoriesEnName(sysFilename);
            accessories.setAccessoriesName(sysFilename);
            // 获取附件的相对路径
            String fileEnd = this.filePath.substring(this.filePath
                    .indexOf(":") + 1, this.filePath.length())
                    + tc.getPath();
            accessories.setAccessoriesPath(fileEnd);
            //accessories.setAccessoriesPath(path);
            // 获取文件的大小
            File file = new File(physicalPath);
            File systemFile = new File(file, sysFilename);

            accessories.setAccessoriesSize(systemFile.length());
            accessories.setAccessoriesUploadTime(StaticMethod.getLocalTime());
            accessories.setAppCode(code);
            this.dao.saveTawCommonsAccessories(accessories);
            fileName = sysFilename;
        } catch (Exception e) {
            e.printStackTrace();
            BocoLog.error(this, "外部接口调用错误");
        }
        return fileName;
    }

    /**
     * FTP附件下载
     *
     * @param fileName   文件名
     * @param filepath   本系统文件的存储路径
     * @param serverPath 服务器上文件的存储路径
     * @return
     * @author 赵东亮
     */
    public String downloadFileByFtp(String fileName, String filepath,
                                    String serverPath) {
        String retMessage = "";
        String ftpserver = "";
        String userLogin = "";
        String pwdLogin = "";
        try {
            ftpserver = StaticMethod.nullObject2String(XmlManage.getFile(
                    "/config/accessoriesServer.xml").getProperty("ftp.ip"));
            userLogin = StaticMethod.nullObject2String(XmlManage.getFile(
                    "/config/accessoriesServer.xml")
                    .getProperty("ftp.username"));
            pwdLogin = StaticMethod.nullObject2String(XmlManage.getFile(
                    "/config/accessoriesServer.xml")
                    .getProperty("ftp.password"));
            retMessage = this
                    .connectToFtpServer(ftpserver, userLogin, pwdLogin);
            if (!retMessage.equals("")) {
                return "下载时文件：" + fileName + "时无法和FTP服务器连接!" + retMessage;
            }
            TelnetInputStream is = fc.get(serverPath);
            File file_out = new File(filepath + "/" + fileName);
            FileOutputStream os = new FileOutputStream(file_out);
            byte[] bytes = new byte[1024];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
            }
            is.close();
            os.close();
            fc.closeServer();
        } catch (IOException e) {
            retMessage = "下载文件：" + fileName + "时发生文件读写错误：" + e.getMessage();
            System.out.println("获取文件时发生错误：" + e.getMessage());
        }
        return retMessage;
    }

    /**
     * FTP附件上传
     *
     * @param fileName   文件名
     * @param filepath   本系统文件的存储路径
     * @param serverPath 服务器上文件的存储路径
     * @return
     * @author 赵东亮
     */
    public void uploadFileByFtp(String fileName, String filepath,
                                String serverPath) {
        String retMessage = "";
        String ftpserver = "";
        String userLogin = "";
        String pwdLogin = "";
        String ceshipot1 = "";
        String ceshipot2 = "";
        String ceshipot3 = "";
        String ceshipot4 = "";
        try {
            ftpserver = StaticMethod.nullObject2String(XmlManage.getFile(
                    "/config/accessoriesServer.xml").getProperty("ftp.ip"));
            userLogin = StaticMethod.nullObject2String(XmlManage.getFile(
                    "/config/accessoriesServer.xml")
                    .getProperty("ftp.username"));
            pwdLogin = StaticMethod.nullObject2String(XmlManage.getFile(
                    "/config/accessoriesServer.xml")
                    .getProperty("ftp.password"));
            retMessage = this
                    .connectToFtpServer(ftpserver, userLogin, pwdLogin);
            if (!retMessage.equals("")) {
                System.out.println("上传文件：" + fileName + "时无法和FTP服务器连接");
            }
            ceshipot1 = "@@@@@@@@open serverfile";
            TelnetOutputStream os = fc.put(serverPath + fileName);
            ceshipot2 = "@@@@@@@@open localfile";
            File file_in = new File(filepath + fileName);
            ceshipot3 = "@@@@@@@@open file";
            FileInputStream is = new FileInputStream(file_in);
            ceshipot4 = "@@@@@@@@open readfile";
            byte[] bytes = new byte[1024];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
            }
            is.close();
            os.close();
            fc.closeServer();
        } catch (IOException e) {
            retMessage = "上传文件：" + fileName + "时发生文件读写错误：" + e.getMessage();
            System.out.println("上传文件：" + fileName + "时发生文件读写错误："
                    + e.getMessage());
            System.out.println("@@@@@@ceshipot1 " + ceshipot1);
            System.out.println("@@@@@@ceshipot2 " + ceshipot2);
            System.out.println("@@@@@@ceshipot3 " + ceshipot3);
            System.out.println("@@@@@@ceshipot4 " + ceshipot4);
        }
    }

    /*
     * 用于连接到FTP服务器上 @return String 若失败则返回失败信息，成功返回空字符串
     */
    private String connectToFtpServer(String ftpserver, String userLogin,
                                      String pwdLogin) {
        if ((ftpserver == null) || (ftpserver.equals("")))
            return "FTP服务器名设置不正确!";
        try {
            fc.openServer(ftpserver);
            fc.login(userLogin, pwdLogin);
            fc.binary();
        } catch (FtpLoginException e) {
            return "没有与FTP服务器连接的权限,或用户名密码设置不正确!";
        } catch (IOException e) {
            return "与FTP服务器连接失败!";
        } catch (SecurityException e) {
            return "没有权限与FTP服务器连接";
        }
        return "";
    }

    /**
     * 本系统附件提供给外系统接口
     *
     * @param uploadType 附件上传类型
     * @param id         附件的ID
     * @return
     * @author 赵东亮
     */
    public TawCommonsAccessories getSystemToOther(String id, String uploadType) {
        TawCommonsAccessories Accessories = new TawCommonsAccessories();
        try {
            List list = dao.getFileByName(id);
            // 获取本系统的物理路径
            TawCommonsAccessories commonsAccessories = (TawCommonsAccessories) list
                    .get(0);
            String rootFilePath = AccessoriesMgrLocator
                    .getAccessoriesAttributes().getUploadPath();
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@rootFilePath = "
                    + rootFilePath);
            String physicalPath = rootFilePath
                    + commonsAccessories.getAccessoriesPath() + "/";
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@physicalPath = "
                    + physicalPath);
            String temp[] = commonsAccessories.getAccessoriesName().split(",");
            String contentPath = "";
            if (uploadType.equals("ftp")) {
                System.out.println("-----ph------ftp------------");
                contentPath = "ftp://ngsf:Ngsf!#$2@10.30.244.103:21/upload/";
//				contentPath = StaticMethod.nullObject2String(XmlManage.getFile("/config/accessoriesServer.xml").getProperty("rootpath.ftp"));

                String url = contentPath + "/"
                        + commonsAccessories.getAccessoriesName() + "/";
                for (int k = 0; k < temp.length; k++) {
                    this.uploadFileByFtp(temp[k], physicalPath, contentPath);
                    Accessories.setAccessoriesCnName(commonsAccessories
                            .getAccessoriesCnName());
                    Accessories.setAccessoriesSize(commonsAccessories
                            .getAccessoriesSize());
                    Accessories.setAccessoriesPath(url);
                }
            } else if (uploadType.equals("http")) {
                System.out.println("-----ph------http------------");
                contentPath = "ftp://ngsf:Ngsf!#$2@10.30.244.103:21/upload/";
//				contentPath = StaticMethod.nullObject2String(XmlManage.getFile("/config/accessoriesServer.xml").getProperty("rootpath.http"));

                String url = contentPath + commonsAccessories.getAccessoriesPath() + "/";
                for (int j = 0; j < temp.length; j++) {
                    if (temp[j].indexOf(id) >= 0) {

                        url = url + temp[j];
                        Accessories.setAccessoriesCnName(commonsAccessories
                                .getAccessoriesCnName());
                        Accessories.setAccessoriesSize(commonsAccessories
                                .getAccessoriesSize());
                        Accessories.setAccessoriesPath(url);
                        //break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            BocoLog.error(this, "外部接口调用错误");
        }
        return Accessories;
    }


    public TawCommonsAccessories getTawCommonsAccessoriesByName(String id) {
        List list = dao.getFileByName(id);
        TawCommonsAccessories accessories = new TawCommonsAccessories();
        if (list.size() > 0) {
            accessories = (TawCommonsAccessories) list.get(0);
        }
        return accessories;
    }

    //生成随机数，防止循环调用附件下载接口时出现系统文件名重复的问题，传进来的sLen是几位就生成几位的随机数
    public String randomKey(int sLen) {
        String base;
        String temp;
        int i;
        int p;

        base = "1234567890";
        temp = "";
        for (i = 0; i < sLen; i++) {
            p = (int) (Math.random() * 10);
            temp += base.substring(p, p + 1);
        }
        return (temp);
    }

    public List getTawCommonsAccessoriesByMonth(String beginyear, String beginmonth) {
        // TODO Auto-generated method stub
        return null;
    }
}
