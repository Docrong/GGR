/*
 * Copyright HBT Co,.Ltd.(2000-2002). Allrights Reserved
 * User: lizhengyou
 * Date: May 21, 2002
 * Time: 3:39:33 PM
 *
 * $Id
*/
// Source File Name:   UploadBean.java

package com.boco.eoms.filemanager.extra.fileupload;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

// Referenced classes of package javazoom.upload:
//            UploadException, UploadFile, UploadParameters, Archiver,
//            UploadListener, MultipartFormDataRequest

public class UploadBean {

    public UploadBean() {
        _$15600 = 0;
        _$15611 = null;
        _$15621 = 100;
        _$15630 = 0xa00000L;
        _$15642 = null;
        _$15649 = null;
        _$15657 = null;
        _$15697 = null;
        _$15725 = null;
        _$15733 = null;
        _$15725 = new Vector();
        _$15697 = new Vector();
        _$15611 = new Vector();
        SEPARATOR = System.getProperty("file.separator").charAt(0);
        _$15733 = new Vector();
    }

    public void addUploadListener(UploadListener listener) {
        if (_$15697 != null)
            _$15697.addElement(listener);
    }

    public void setStoremodel(int storeid)
            throws UploadException {
        if (storeid == 0 || storeid == 1 || storeid == 2 || storeid == 3 || storeid == 4 || storeid == 5 || storeid == 6)
            _$15600 = storeid;
        else
            throw new UploadException(UploadException.UNKNOWNSTOREMODEL);
    }

    public Connection getDatabasestore() {
        return _$15657;
    }

    public String getZipfilestore() {
        return _$15649;
    }

    public String getFolderstore() {
        return _$15642;
    }

    public Vector getMemorystore() {
        return _$15733;
    }

    public void setMemorystore()
            throws UploadException {
        setStoremodel(0);
    }

    public void setFolderstore(String serverfolder)
            throws UploadException {
        setStoremodel(1);
        _$15642 = serverfolder;
        _$15642 = _$15642.replace('\\', '/').replace('/', SEPARATOR);
        java.io.File dir = new java.io.File(String.valueOf(_$15642) + String.valueOf(SEPARATOR));
        if (dir.exists()) {
            if (!dir.canWrite())
                throw new UploadException(UploadException.FOLDERISREADONLY);
        } else if (!dir.mkdir())
            throw new UploadException(UploadException.CANNOTCREATEFOLDER);
    }

    public void setZipfilestore(String file)
            throws IOException, UploadException {
        setStoremodel(3);
        file = file.replace('\\', '/').replace('/', SEPARATOR);
        java.io.File f = new java.io.File(file);
        _$15649 = file;
        if (!f.exists()) {
            FileOutputStream fos = new FileOutputStream(file);
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.putNextEntry(new ZipEntry("EmptyFile"));
            zos.closeEntry();
            zos.close();
            fos.close();
        }
    }

    public void setDatabasestore(String driver, String URL, Properties credentials)
            throws Exception {
        setStoremodel(2);
        Class.forName(driver);
        Connection c = DriverManager.getConnection(URL, credentials);
        _$15657 = c;
    }

    public void setDatabasestore(Connection jdbcconnection)
            throws UploadException {
        setStoremodel(2);
        _$15657 = jdbcconnection;
    }

    public String getBlacklist() {
        StringBuffer sb = new StringBuffer();
        for (int k = 0; k < _$15611.size(); k++) {
            if (k > 0)
                sb.append(",");
            String el = (String) _$15611.elementAt(k);
            sb.append(el);
        }

        return sb.toString();
    }

    public void setBlacklist(String blacklist) {
        _$15611 = new Vector();
        String token;
        for (StringTokenizer st = new StringTokenizer(blacklist, ","); st.hasMoreElements(); _$15611.addElement(token))
            token = (String) st.nextElement();

    }

    public void setMaxfiles(int max) {
        _$15621 = max;
    }

    public int getMaxfiles() {
        return _$15621;
    }

    public long getFilesizelimit() {
        return _$15630;
    }

    public void setFilesizelimit(long max) {
        _$15630 = max;
    }

    private boolean _$16070(String f) {
        boolean match = false;
        int j = 0;
        do {
            if (j >= _$15611.size())
                break;
            String deny = (String) _$15611.elementAt(j);
            String denyName = "";
            String denyExt = "";
            int ind = deny.indexOf(".");
            if (ind != -1) {
                denyName = deny.substring(0, ind);
                denyExt = deny.substring(ind + 1, deny.length());
            }
            String fName = "";
            String fExt = "";
            ind = f.indexOf(".");
            if (ind != -1) {
                fName = f.substring(0, ind);
                fExt = f.substring(ind + 1, f.length());
            }
            if (denyName.equals("*") && denyExt.equalsIgnoreCase(fExt)) {
                match = true;
                break;
            }
            if (denyExt.equals("*") && denyName.equalsIgnoreCase(fName)) {
                match = true;
                break;
            }
            if (deny.equalsIgnoreCase(f)) {
                match = true;
                break;
            }
            j++;
        } while (true);
        return match;
    }

    public String store(MultipartFormDataRequest mrequest, String field)
            throws IOException, UploadException {
        String returnStr = "" ;
        String fileindex = mrequest.getParameter("req_docu");
        if (_$15600 == 1 && _$15642 != null) {
            Hashtable files = mrequest.getFiles();
            UploadFile file = (UploadFile) files.get(field);
            if (file.getFileName() != null && !file.getFileName().equals("") && file.getFileSize() >= (long) 0) {
                if (file.getFileSize() > _$15630)
                    throw new UploadException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(UploadException.UPLOADFILESIZELIMITREACHED)))).append(":").append(file.getFileName()))));
                java.io.File folder = new java.io.File(String.valueOf(getFolderstore()) + String.valueOf(SEPARATOR));
                java.io.File folderfiles[] = folder.listFiles();
                int amount = folderfiles.length;
                boolean found = false;
                for (int i = 0; i < amount; i++)
                    if (folderfiles[i].getName().equals(file.getFileName()))
                        found = true;

                if (amount < _$15621) {
                    if (_$16070(file.getFileName()))
                        throw new UploadException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(UploadException.UPLOADFILENAMEDENIED)))).append(":").append(file.getFileName()))));
                    FileOutputStream fos = null;
                    if (!found)
                        fos = new FileOutputStream(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(getFolderstore())))).append(SEPARATOR).append(file.getFileName()))));
                    else {
                        String file_name = file.getFileName() ;
                        String result = "" ;
                        returnStr = "_"+String.valueOf(System.currentTimeMillis()) ;
//                        int dot_idx = file_name.indexOf(".");
                        int dot_idx = file_name.lastIndexOf(".");
                        if(dot_idx>0) {
                            String file_src= file_name.substring(0,dot_idx);
                            String file_ext= file_name.substring(dot_idx);
                            result = file_src+returnStr+file_ext;
                        }
                        fos = new FileOutputStream(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(getFolderstore())))).append(SEPARATOR).append(result))));
//                        .append(".").append(System.currentTimeMillis())
                    }
                    fos.write(file.getData());
                    fos.close();
                    UploadParameters up = new UploadParameters(file.getFileName(), file.getFileSize(), file.getContentType(), 1, _$15642);
                    _$15725.addElement(up);
                    _$16164(up);
                } else {
                    throw new UploadException(UploadException.UPLOADLIMITREACHED);
                }
            }
        } else if (_$15600 == 3 && _$15649 != null) {
            Hashtable files = mrequest.getFiles();
            UploadFile file = (UploadFile) files.get(field);
            if (file.getFileName() != null && !file.getFileName().equals("") && file.getFileSize() >= (long) 0) {
                if (file.getFileSize() > _$15630)
                    throw new UploadException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(UploadException.UPLOADFILESIZELIMITREACHED)))).append(":").append(file.getFileName()))));
                if (_$16070(file.getFileName()))
                    throw new UploadException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(UploadException.UPLOADFILENAMEDENIED)))).append(":").append(file.getFileName()))));
                Archiver zip = Archiver.getInstance();
                if (zip.append(_$15649, file, _$15621)) {
                    String filename = file.getFileName();
                    byte[] dbbyte1 = filename.getBytes("iso-8859-1");
                    filename = new String(dbbyte1);
                    UploadParameters up = new UploadParameters(filename, file.getFileSize(), file.getContentType(), 3, _$15649);
                    _$15725.addElement(up);
                    _$16164(up);
                }
            }
        } else if (_$15600 == 0 && _$15733 != null) {
            Hashtable files = mrequest.getFiles();
            UploadFile file = (UploadFile) files.get(field);
            if (file.getFileName() != null && !file.getFileName().equals("") && file.getFileSize() >= (long) 0) {
                if (file.getFileSize() > _$15630)
                    throw new UploadException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(UploadException.UPLOADFILESIZELIMITREACHED)))).append(":").append(file.getFileName()))));
                if (_$16070(file.getFileName()))
                    throw new UploadException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(UploadException.UPLOADFILENAMEDENIED)))).append(":").append(file.getFileName()))));
                if (_$15733.size() >= _$15621)
                    throw new UploadException(UploadException.UPLOADLIMITREACHED);
                _$15733.addElement(file);
                UploadParameters up = new UploadParameters(file.getFileName(), file.getFileSize(), file.getContentType(), 0, "memory");
                _$15725.addElement(up);
                _$16164(up);
            }
        } else if (_$15600 == 2 && _$15657 != null) {
            Hashtable files = mrequest.getFiles();
            UploadFile file = (UploadFile) files.get(field);
            if (file.getFileName() != null && !file.getFileName().equals("") && file.getFileSize() >= (long) 0) {
                if (file.getFileSize() > _$15630)
                    throw new UploadException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(UploadException.UPLOADFILESIZELIMITREACHED)))).append(":").append(file.getFileName()))));
                if (_$16070(file.getFileName()))
                    throw new UploadException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(UploadException.UPLOADFILENAMEDENIED)))).append(":").append(file.getFileName()))));
                try {
                    String count = "SELECT COUNT(*) FROM ".concat(String.valueOf(String.valueOf(SQLUPLOADTABLE)));
                    Statement s = _$15657.createStatement();
                    ResultSet r = s.executeQuery(count);
                    int size;
                    for (size = 0; r.next(); size = r.getInt(1)) ;
                    r.close();
                    s.close();
                    if (size >= _$15621)
                        throw new UploadException(UploadException.UPLOADLIMITREACHED);
                    String sql = String.valueOf(String.valueOf((new StringBuffer("INSERT INTO ")).append(SQLUPLOADTABLE).append(" (").append(SQLUPLOADID).append(",DOCUMENT_ID").append(",").append(SQLUPLOADFILENAME).append(",").append(SQLUPLOADFILE).append(") VALUES (?,?,?,?)")));
                    PreparedStatement ps = _$15657.prepareStatement(sql);
                    ps.setLong(1, System.currentTimeMillis());
                    ps.setString(2, fileindex);
                    ps.setString(3, file.getFileName());
                    ps.setBytes(4, file.getData());

                    int modified = ps.executeUpdate();
                    ps.close();
                } catch (Exception e) {
                    throw new UploadException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(UploadException.DATABASESTOREERROR)))).append(":").append(e.getMessage()))));
                }
                UploadParameters up = new UploadParameters(file.getFileName(), file.getFileSize(), file.getContentType(), 2, "database");
                _$15725.addElement(up);
                _$16164(up);
            }
        }
        return returnStr ;
    }

    public String store(MultipartFormDataRequest mrequest)
            throws IOException, UploadException {
        String returnStr = "" ;
        Hashtable files = mrequest.getFiles();
        String field;
        for (Enumeration e = files.keys(); e.hasMoreElements(); returnStr = store(mrequest, field))
            field = (String) e.nextElement();
        return returnStr ;
    }

    public Vector getHistory() {
        return _$15725;
    }

    public void resetStore()
            throws IOException, UploadException {
        if (_$15600 == 1 && _$15642 != null) {
            java.io.File dir = new java.io.File(String.valueOf(_$15642) + String.valueOf(SEPARATOR));
            if (dir.exists()) {
                if (!dir.canWrite())
                    throw new UploadException(UploadException.FOLDERISREADONLY);
                java.io.File files[] = dir.listFiles();
                if (files != null) {
                    for (int h = 0; h < files.length; h++)
                        if (!files[h].delete())
                            throw new UploadException(UploadException.CANNOTDELETESTORE);

                }
                setFolderstore(_$15642);
                _$15725.removeAllElements();
            }
        } else if (_$15600 == 3 && _$15649 != null) {
            java.io.File dir = new java.io.File(_$15649);
            if (!dir.delete())
                throw new UploadException(UploadException.CANNOTDELETESTORE);
            setZipfilestore(_$15649);
            _$15725.removeAllElements();
        } else if (_$15600 == 0 && _$15733 != null) {
            _$15733.removeAllElements();
            _$15725.removeAllElements();
        } else if (_$15600 == 2 && _$15657 != null) {
            try {
                String delete = "DELETE FROM ".concat(String.valueOf(String.valueOf(SQLUPLOADTABLE)));
                Statement s = _$15657.createStatement();
                int deleted = s.executeUpdate(delete);
                s.close();
            } catch (Exception e) {
                throw new UploadException(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(UploadException.DATABASESTOREERROR)))).append(":").append(e.getMessage()))));
            }
            _$15725.removeAllElements();
        }
    }

    private void _$16164(UploadParameters up) {
        for (int i = 0; i < _$15697.size(); i++) {
            UploadListener ul = (UploadListener) _$15697.elementAt(i);
            ul.fileUploaded(up);
        }

    }

    public final void initialize(PageContext pageContext)
            throws ServletException {
        m_application = pageContext.getServletContext();
        m_request = (HttpServletRequest) pageContext.getRequest();
        m_response = (HttpServletResponse) pageContext.getResponse();
    }

    public void downloadField(ResultSet rs, String columnName, String contentType, String destFileName)
            throws SQLException, IOException, ServletException {
        if (rs == null)
            throw new IllegalArgumentException("The RecordSet cannot be null (1045).");
        if (columnName == null)
            throw new IllegalArgumentException("The columnName cannot be null (1050).");
        if (columnName.length() == 0)
            throw new IllegalArgumentException("The columnName cannot be empty (1055).");
        byte b[] = rs.getBytes(columnName);
        if (contentType == null)
            m_response.setContentType("application/x-msdownload");
        else if (contentType.length() == 0)
            m_response.setContentType("application/x-msdownload");
        else
            m_response.setContentType(contentType);
        m_response.setContentLength(b.length);
        if (destFileName == null)
            m_response.setHeader("Content-Disposition", "attachment;");
        else if (destFileName.length() == 0)
            m_response.setHeader("Content-Disposition", "attachment;");
        else
            m_response.setHeader("Content-Disposition", "attachment; filename=".concat(String.valueOf(destFileName)));
        m_response.getOutputStream().write(b, 0, b.length);
    }

    public static int MAXUPLOADEDFILES = 100;
    public static long MAXUPLOADEDFILESIZE = 0xa00000L;
    public static char SEPARATOR = '/';
    public static int MEMORYSTORE = 0;
    public static int FOLDERSTORE = 1;
    public static int DATABASESTORE = 2;
    public static int ZIPFILESTORE = 3;
    public static int TARGZIPFILESTORE = 4;
    public static int SERIALIZEDFILESTORE = 5;
    public static int XMLFILESTORE = 6;
    public static String EMPTYENTRY = "EmptyFile";
    public static String SQLUPLOADTABLE = "UPLOADS";
    public static String SQLUPLOADID = "UPLOADID";
    public static String SQLUPLOADFILENAME = "FILENAME";
    public static String SQLUPLOADFILE = "BINARYFILE";
    private int _$15600;
    private Vector _$15611;
    private int _$15621;
    private long _$15630;
    private String _$15642;
    private String _$15649;
    private Connection _$15657;
    private Vector _$15697;
    private Vector _$15725;
    private Vector _$15733;
    protected HttpServletResponse m_response;
    protected HttpServletRequest m_request;
    protected ServletContext m_application;

    static {
        MAXUPLOADEDFILES = 100;
        MAXUPLOADEDFILESIZE = 0xa00000L;
        MEMORYSTORE = 0;
        FOLDERSTORE = 1;
        DATABASESTORE = 2;
        ZIPFILESTORE = 3;
        TARGZIPFILESTORE = 4;
        SERIALIZEDFILESTORE = 5;
        XMLFILESTORE = 6;
        EMPTYENTRY = "EmptyFile";
    }
}
