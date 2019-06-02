package com.boco.eoms.filemanager.extra.fileupload;


/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2003-5-23
 * Time: 17:26:32
 * To change this template use Options | File Templates.
 */
public class UploadFileInfo extends FileInfo{
    public UploadFileInfo(){
    }
    private int projectTypeId=0;
    private int projectId=0;


    public int getProjectTypeId(){
        return projectTypeId;
    }
    public int getProjectId(){
        return projectId;
    }

    public void setProjectTypeId(int projectTypeId){
        this.projectTypeId=projectTypeId;
    }
    public void setProjectId(int projectId){
        this.projectId=projectId;
    }


}
