package com.boco.eoms.common.util;


import com.ibm.network.ftp.FileInfo;
import com.ibm.network.ftp.protocol.FTPProtocol;
import java.io.File;
//import java.io.PrintStream;
import java.util.Vector;


public class BaseFtp
{

    private String sFTPServer;
    private String sFTPPort;
    private String sLogin;
    private String sPassword;
    private String sLocalPath;
    private String sLocalPubPath;
    private String sRemotePath;
    private FTPProtocol ftpClient;
    public final String ERR_NO_LOCAL_FILE = "没有设置本地文件名";
    public static int UPLOAD_RETRY_TIMES = 2;
    public final int LIST_ALL = 0;
    public final int LIST_FILES = 1;
    public final int LIST_DIRS = 2;
    private boolean debugMode;

    public BaseFtp()
    {
        sFTPServer = "";
        sFTPPort = "";
        sLogin = "";
        sPassword = "";
        sLocalPath = "";
        sLocalPubPath = "";
        sRemotePath = "/";
        ftpClient = new FTPProtocol();
        debugMode = true;
    }

    public void printOut(String _sDesc)
    {
        if(debugMode)
            System.out.println(_sDesc);
    }

    public void catchException(String _strDesc, Exception _exCaught)
    {
        if(debugMode)
        {
            System.out.println(_strDesc);
            _exCaught.printStackTrace(System.out);
        } else
        if(_exCaught.getMessage() != null)
            System.out.println(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(_strDesc)))).append(":").append(_exCaught.getMessage()))));
    }

    public boolean Connect()
        throws Exception
    {
        if(ftpClient.connect(sFTPServer, sFTPPort) == 220 && ftpClient.login(sLogin, sPassword) == 230)
        {
            ftpClient.setType("IMAGE");
            return ftpClient.isStillConnected();
        } else
        {
            return false;
        }
    }

    public void setDebugMode(boolean bDebugMode)
    {
        debugMode = bDebugMode;
    }

    public boolean Disconnect()
        throws Exception
    {
        ftpClient.disconnect();
        return true;
    }

    public boolean makeDirectory(String _sDir)
    {
        if(_sDir == null || !ftpClient.isStillConnected())
            return false;
        int nIndex = 1;
        do
        {
            if((nIndex = _sDir.indexOf(47, nIndex + 1)) == -1)
                break;
            if(ftpClient.changeDir(_sDir.substring(0, nIndex), true) != 250)
                ftpClient.makeDir(_sDir.substring(0, nIndex), true);
        } while(true);
        return ftpClient.makeDir(_sDir, true) == 257;
    }

    public boolean removeDirectory(String _sDir)
    {
        if(_sDir == null || !ftpClient.isStillConnected())
            return false;
        else
            return ftpClient.removeDir(_sDir, true) == 226;
    }

    public void setLocalPubPath(String _sLocalPubPath)
    {
        if(_sLocalPubPath != null && sLocalPubPath.length() > 0)
            sLocalPubPath = _sLocalPubPath.charAt(_sLocalPubPath.length() - 1) != File.separatorChar ? String.valueOf(_sLocalPubPath) + String.valueOf(File.separatorChar) : _sLocalPubPath;
    }

    public boolean uploadFile(String _localFile, String _remoteFile)
        throws Exception
    {
        if(_localFile == null)
            return false;
        boolean bFileUploaded = true;
        int nUploadCount = 0;
        do
        {

            if(!isConnected())
            {
                printOut(">>>>>>>>>>>>>uploadFile:正在重新连接FTP服务器.......");
                try
                {
                    if(!Connect())
                    {
                        boolean flag = false;
                        return flag;
                    }
                }
                catch(Exception ex)
                {
                    catchException("重新建立FTP连接错误!", ex);
                }
                printOut(">>>>>>>>>>>>>uploadFile:重新连接FTP服务器成功!");
            }
            try
            {
                String sRemotePath = null;
                String sRemoteFile = null;
                int nPos = _remoteFile.lastIndexOf("/");

                if(nPos > 0)
                {
                    sRemotePath = _remoteFile.substring(0, nPos + 1);
                    sRemoteFile = _remoteFile.substring(nPos + 1);
                }
                if(sRemotePath == null || sRemoteFile == null)
                {
                    boolean flag1 = false;
                    return flag1;
                }
                if(changeDir(extractFilePath(_localFile), false) && changeDir("/", true))
                {
                    changeDirs(sRemotePath);
                    bFileUploaded = ftpClient.putFile(extractFileName(_localFile), sRemoteFile) == 226;
                    ftpClient.changeDir("/", true);
                }
            }
            catch(Exception ex)
            {
              ex.printStackTrace();
                catchException(String.valueOf(String.valueOf((new StringBuffer("上传文件[")).append(_localFile).append("]到[").append(_remoteFile).append("]错误!"))), ex);
                bFileUploaded = false;
            }
            nUploadCount++;
        } while(!bFileUploaded && nUploadCount < UPLOAD_RETRY_TIMES);

        return bFileUploaded;
    }

    public void changeDirs(String _sPath)
    {
        try
        {
            int nIndex = 1;
            for(int nPrevIndex = 0; (nIndex = _sPath.indexOf(47, nIndex + 1)) != -1; nPrevIndex = nIndex + 1)
            {
                String sDirName = _sPath.substring(0, nIndex);
                if(ftpClient.changeDir(sDirName, true) != 250)
                {
                    ftpClient.makeDir(sDirName, true);
                    ftpClient.changeDir(sDirName, true);
                }
            }

        }
        catch(Exception ex)
        {
            catchException(String.valueOf(String.valueOf((new StringBuffer("更改FTP的目录[")).append(_sPath).append("]错误!"))), ex);
        }
    }

    public void uploadDirectory(String _localDir, String _remoteDir)
        throws Exception
    {
        if(!ftpClient.isStillConnected())
            return;
        try
        {
            if(_localDir != null)
                ftpClient.changeDir(_localDir, false);
            if(_remoteDir != null)
                ftpClient.changeDir(_remoteDir, true);
            ftpClient.uploadDirectoryRecursively(_localDir);
        }
        catch(Exception ex)
        {
            catchException(String.valueOf(String.valueOf((new StringBuffer("上传目录[")).append(_localDir).append("]到[").append(_remoteDir).append("]错误!"))), ex);
        }
    }

    public boolean changeDir(String _localDir, boolean bRemote)
        throws Exception
    {
        try
        {
            if(!ftpClient.isStillConnected())
            {
                boolean flag = false;
                return flag;
            } else
            {
                boolean flag1 = ftpClient.changeDir(_localDir, bRemote) == 250;
                return flag1;
            }
        }
        catch(Exception ex)
        {
            catchException("切换FTP目录错误!", ex);
        }
        return false;
    }

    public void setConnectionInfo(String _sServer, String _sPort, String _sLogin, String _sPassword)
    {
        if(_sServer != null && _sServer.length() > 0)
            sFTPServer = _sServer;
        int nPortIndex = sFTPServer.indexOf(":");
        if(nPortIndex != -1 && nPortIndex + 1 < sFTPServer.length())
            sFTPPort = sFTPServer.substring(nPortIndex + 1, sFTPServer.length());
        if(sFTPPort.length() <= 0 && _sPort != null && _sPort.length() > 0)
            sFTPPort = _sPort;
        if(_sPassword != null && _sPassword.length() > 0)
            sPassword = _sPassword;
        if(_sLogin != null && _sLogin.length() > 0)
            sLogin = _sLogin;
    }

    public void setRemotePath(String _sRemotePath)
    {
        if(_sRemotePath != null && _sRemotePath.length() > 0)
        {
            _sRemotePath.replace('\\', '/');
            sRemotePath = _sRemotePath.charAt(0) == '/' ? "" : "/";
            sRemotePath =sRemotePath + String.valueOf(_sRemotePath.charAt(_sRemotePath.length() - 1) != '/' ? ((Object) (String.valueOf(String.valueOf(_sRemotePath)).concat("/"))) : ((Object) (_sRemotePath)));
            System.out.println(" sRemotePath = " + sRemotePath);
        }
        printOut(">>>>>>>>>>>>>setRemotePath:".concat(String.valueOf(String.valueOf(sRemotePath))));
    }

    public void setLocalPath(String _sLocalPath)
    {
        if(_sLocalPath != null && _sLocalPath.length() > 0)
        {
            _sLocalPath.replace('/', File.separatorChar);
            sLocalPath = _sLocalPath.charAt(0) == File.separatorChar ? "" : File.separator;
            sLocalPath = String.valueOf(sLocalPath) + String.valueOf(_sLocalPath.charAt(_sLocalPath.length() - 1) != File.separatorChar ? ((Object) (String.valueOf(_sLocalPath) + String.valueOf(File.pathSeparator))) : ((Object) (_sLocalPath)));
        }
        printOut(">>>>>>>>>>>>>setLocalPath:".concat(String.valueOf(String.valueOf(sLocalPath))));
    }

    public boolean downloadFile(String _remoteFile, String _localFile)
        throws Exception
    {
        try
        {
            if(ftpClient == null || !ftpClient.isStillConnected())
            {
                boolean flag = false;
                return flag;
            } else
            {
                boolean flag1 = ftpClient.getFile(_remoteFile, _localFile) == 226;
                return flag1;
            }
        }
        catch(Exception ex)
        {
            catchException(String.valueOf(String.valueOf((new StringBuffer("下载文件[")).append(_remoteFile).append("]到[").append(_localFile).append("]错误!"))), ex);
        }
        return false;
    }

    public String getDateTime(String _name, boolean bRemote)
        throws Exception
    {
        if(bRemote && !ftpClient.isStillConnected())
            return "";
        try
        {
            Vector vFiles = ftpClient.fileList(bRemote);
            for(int i = 0; i < vFiles.size(); i++)
            {
                FileInfo fi = (FileInfo)vFiles.get(i);
                if(fi != null && fi.getName() == _name)
                {
                    String s = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(fi.getDate())))).append(" ").append(fi.getTime())));
                    return s;
                }
            }

        }
        catch(Exception ex)
        {
            catchException("提取FTP时间错误!", ex);
        }
        return "";
    }

    public int getFileSize(String _fileName, boolean bRemote)
        throws Exception
    {
        if(bRemote && !ftpClient.isStillConnected())
            return 0;
        try
        {
            Vector vFiles = ftpClient.fileList(bRemote);
            for(int i = 0; i < vFiles.size(); i++)
            {
                FileInfo fi = (FileInfo)vFiles.get(i);
                if(fi != null && fi.isFile() && fi.getName() == _fileName)
                {
                    int j = Integer.valueOf(fi.getSize()).intValue();
                    return j;
                }
            }

        }
        catch(Exception ex)
        {
            catchException(String.valueOf(String.valueOf((new StringBuffer("提取文件[")).append(_fileName).append("]大小错误!"))), ex);
        }
        return 0;
    }

    public boolean removeFile(String _fileName, boolean bRemote)
        throws Exception
    {
        if(bRemote && !ftpClient.isStillConnected())
            return false;
        try
        {
            if(_fileName.indexOf(File.separatorChar) != -1 && bRemote)
                _fileName = String.valueOf(sRemotePath) + String.valueOf(_fileName.substring(sLocalPubPath.length() + 1).replace(File.separatorChar, '/'));
            ftpClient.deleteFile(_fileName, bRemote);
            boolean flag = true;
            return flag;
        }
        catch(Exception ex)
        {
            catchException(String.valueOf(String.valueOf((new StringBuffer("删除文件[")).append(_fileName).append("]错误!"))), ex);
        }
        return false;
    }

    public boolean testFtpServer(String _ftpServer, String _ftpUser, String _ftpPwd)
        throws Exception
    {
        return testFtpServer(_ftpServer, _ftpUser, _ftpPwd, "/", false);
    }

    public boolean testFtpServer(String _ftpServer, String _ftpUser, String _ftpPwd, String _ftpFolder, boolean _bWrite)
        throws Exception
    {
        boolean bServerOK = false;
        setConnectionInfo(_ftpServer, "21", _ftpUser, _ftpPwd);
        setRemotePath(_ftpFolder);
        long lStartTest = System.currentTimeMillis();
        if(Connect())
        {
            if(changeDir(_ftpFolder, true))
            {
                bServerOK = true;
                if(_bWrite)
                {
                    bServerOK = makeDirectory("__test__");
                    removeDirectory("__test__");
                    if(!bServerOK)
                        throw new Exception("FTP服务器写入检测不成功(设置的目录可能不具有写入权限)!");
                }
            } else
            {
                throw new Exception("FTP服务器路径检测不成功(路径可能不存在)!");
            }
            bServerOK = Disconnect();
        } else
        {
            throw new Exception("FTP服务器连接不成功(FTP的设置可能不正确)!");
        }
        return bServerOK;
    }

    public boolean isConnected()
    {
        if(ftpClient == null)
            return false;
        try
        {
            boolean flag = ftpClient.isStillConnected();
            return flag;
        }
        catch(Exception ex)
        {
            printOut("isConnected Error!");
        }
        return false;
    }

    public String listDirs(String _ftpPath)
        throws Exception
    {
        return listDirectory(_ftpPath, 2);
    }

    public String listDirectory(String _ftpPath, int _listType)
        throws Exception
    {
        if(_ftpPath == null || !isConnected())
            return "";
        String sResult = "";
        try
        {
            if(!changeDir(_ftpPath, true))
            {
                String s = "";
                return s;
            }
            Vector vFiles = ftpClient.fileList(true);
            for(int i = 0; i < vFiles.size(); i++)
            {
                FileInfo fi = (FileInfo)vFiles.get(i);
                if(fi == null)
                    continue;
                switch(_listType)
                {
                case 2: // '\002'
                    if(!fi.isFile())
                        sResult = String.valueOf(sResult) + String.valueOf(String.valueOf(String.valueOf(fi.getName())).concat(","));
                    break;

                case 1: // '\001'
                    if(fi.isFile())
                        sResult = String.valueOf(sResult) + String.valueOf(String.valueOf(String.valueOf(fi.getName())).concat(","));
                    break;

                case 0: // '\0'
                default:
                    sResult = String.valueOf(sResult) + String.valueOf(String.valueOf(String.valueOf(fi.getName())).concat(","));
                    break;
                }
            }

            String s1 = sResult;
            return s1;
        }
        catch(Exception ex)
        {
            catchException(String.valueOf(String.valueOf((new StringBuffer("提取目录[")).append(_ftpPath).append("]错误!"))), ex);
        }
        return "";
    }

    public String listAll(String _ftpPath)
        throws Exception
    {
        return listDirectory(_ftpPath, 0);
    }

    public String listFiles(String _ftpPath)
        throws Exception
    {
        return listDirectory(_ftpPath, 1);
    }

    public  String extractFilePath(String _sFilePathName)
    {
        int nPos = _sFilePathName.lastIndexOf(File.separatorChar);
        return nPos < 0 ? "" : _sFilePathName.substring(0, nPos + 1);
    }
    public String extractFileName(String _sFilePathName)
    {
        int nPos = _sFilePathName.lastIndexOf(File.separatorChar);
        return _sFilePathName.substring(nPos + 1);
    }


    public static void main(String args[])
    {
        BaseFtp ftp = new BaseFtp();
        try
        {
            ftp.setConnectionInfo("10.175.20.108", "21", "root", "boco123");
            //ftp.setRemotePath("/opt/csm/");
            //ftp.setLocalPubPath("c:\\");
            if(ftp.Connect())
            {
                ftp.uploadFile("c:\\TawFUploadForm.txt", "/opt/jakarta-tomcat-4.1.18/webapps/eoms_new/worksheet/upload/白鹏.sql");
                ftp.Disconnect();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
