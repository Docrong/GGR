package com.boco.eoms.filemanager.extra.fileupload;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2003-5-29
 * Time: 10:49:23
 * To change this template use Options | File Templates.
 */
public class FileInfo {
    private int fileId=0;
    private String fileName="";
    private String fileExt="";
    private String fileRealName="";
    private String filePath="";

    public int getFileId(){
            return fileId;
        }

    public String getFileName(){
        return fileName;
    }
    public String getFileExt(){
        return fileExt;
    }
    public String getFilePath(){
        return filePath;
    }
    public String getFileRealName(){
        return fileRealName;
    }
    public void setFileId(int fileId){
           this.fileId=fileId;
       }
    public void setFileName(String fileName){
        this.fileName=fileName;
    }
    public void setFileExt(String fileExt){
        this.fileExt=fileExt;
    }
    public void setFileRealName(String fileRealName){
        this.fileRealName=fileRealName;
    }
    public void setFilePath(String filePath){
        this.filePath=filePath;
    }


}
