package com.boco.eoms.workplan.vo;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

public class TawwpExecuteFileVO {
    private String id; //标识
    private String fileName; //文件名
    private String fileCodeName; //文件存储名
    private String fileSize; //文件长度
    private String crtime; //创建时间
    private String cruser; //创建人

    public String getCrtime() {
        if (crtime == null) {
            crtime = "";
        }

        return crtime;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser;
    }

    public void setCrtime(String crtime) {
        this.crtime = crtime;
    }

    public String getCruser() {
        if (cruser == null) {
            cruser = "";
        }

        return cruser;
    }

    public String getFileCodeName() {
        if (fileCodeName == null) {
            fileCodeName = "";
        }

        return fileCodeName;
    }

    public void setFileCodeName(String fileCodeName) {
        this.fileCodeName = fileCodeName;
    }

    public String getFileName() {
        if (fileName == null) {
            fileName = "";
        }

        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        if (fileSize == null) {
            fileSize = "";
        }

        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getId() {
        if (id == null) {
            id = "";
        }

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
