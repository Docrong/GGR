package com.boco.eoms.filemanager.extra.fileupload;

import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.resource.Util;

import java.sql.PreparedStatement;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Random;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2003-5-23
 * Time: 17:30:52
 * To change this template use Options | File Templates.
 */
public class RecordFileInfo {
   ConnectionPool pool = ConnectionPool.getInstance();
   Connection conn = null;
    PreparedStatement pst = null;
    UploadFileInfo infoBean;
    public RecordFileInfo(UploadFileInfo infoBean){
        this.infoBean=infoBean;
        init();
    }
    private void init(){
       try {

           Date date = new Date();
           java.sql.Date curr=new java.sql.Date(date.getTime());
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = dateFormat.format(date);  //��ȡ��ǰʵ��Ǽ�ʱ�䣩
            String fileRealName=currentTime.replace(' ','-').replace(':','-');
            fileRealName+=getRandomString(8)+"."+infoBean.getFileExt();
            infoBean.setFileRealName(fileRealName);

            String sql="insert into a_com_attachfile (file_name,file_real_name,file_path,file_time) values(?,?,?,?) ";

            conn=pool.getConnection();
            conn.setAutoCommit(false);            //record upload file information
            pst=conn.prepareStatement(sql);
            pst.setString(1, infoBean.getFileName());
            pst.setString(2, fileRealName);
            pst.setString(3, infoBean.getFilePath());
            pst.setDate(4,curr);
            //pst.setString(4,currentTime);
            pst.executeUpdate();
                                           //record the project and the upload file relation
            String queryId="select file_id from a_com_attachfile where file_real_name='"+fileRealName+"'";
            ResultSet rs=conn.createStatement().executeQuery(queryId);
           int fileId=-1;
           if (rs.next())
            fileId=rs.getInt("file_id");
           infoBean.setFileId(fileId);

            sql = "insert into a_com_attachcross (type_id,file_id,project_id )  values(?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setInt(1, infoBean.getProjectTypeId());
            pst.setInt(2, fileId);
            pst.setInt(3, infoBean.getProjectId());
            pst.executeUpdate();
           conn.commit();
        } catch (Exception e) {
            System.out.println(Util.UNI2GBK(e.getMessage()));
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
    }
    }

    public String getRandomString(int len){
        if(len<=0) len=8;
         Random rad=new Random();
         double intRan=rad.nextDouble();
        String temp=Double.toString(intRan).substring(2);
        String result=temp;
       if(temp.length()<len)
            for(int j=0;j<len-temp.length();j++)
               result+=j;
        else
            result=temp.substring(0,len);
       return result;
    }
    public String getFileRealName(){
        return infoBean.getFileRealName();
    }
    public int getFileId(){
        return infoBean.getFileId();
    }
    static public void deleteFileInfo(String contextPath,int fileId){

        String fileSql="select * from a_com_attachfile where file_id="+fileId;
        String sql="delete from a_com_attachfile where file_id="+fileId;
        String sql1="delete from a_com_attachcross where file_id="+fileId;
        Connection conn=ConnectionPool.getInstance().getConnection();
        PreparedStatement pst=null;
        ResultSet rs=null;
        try{
            conn.setAutoCommit(false);            //record upload file information
            pst=conn.prepareStatement(fileSql);
            rs=pst.executeQuery();
            if(rs.next()){
                String fileName=contextPath+rs.getString("file_path")+"/"+rs.getString("file_real_name");
                fileName=fileName.replace('\\', '/');
                File file=new File(fileName);
                file.delete();
            }
            rs.close();
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            pst=conn.prepareStatement(sql1);
            pst.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            System.out.println(Util.UNI2GBK(e.toString()));
        }finally{
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    System.out.println(Util.UNI2GBK(e.getMessage()));
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }


    }
    static public FileInfo getFileInfo(int fileId){
    FileInfo info=new FileInfo();
	String sql="select * from a_com_attachfile where file_id="+fileId;
    Connection conn=ConnectionPool.getInstance().getConnection();
    try{
    ResultSet rs=conn.createStatement().executeQuery(sql);
	if(rs.next()){
        info.setFileId(rs.getInt("file_id"));
        info.setFileName(rs.getString("file_name"));
        info.setFileRealName(rs.getString("file_real_name"));
        info.setFilePath(rs.getString("file_path"));
        String fileName=info.getFileName();
        info.setFileExt(fileName.substring(fileName.lastIndexOf('.')));
	}
    }catch(Exception ex){
    System.out.println(Util.UNI2GBK(ex.toString()));
    }
        return info;
}
    static public void deletFileInfo(String fileRealName){
        String sql="delete from a_com_attachfile where file_real_name='"+fileRealName+"'";
                String sql1="delete form a_com_attachcross where file_real_name='"+fileRealName+"'";
                Connection conn=ConnectionPool.getInstance().getConnection();
                PreparedStatement pst=null;
                try{
                    conn.setAutoCommit(false);          //record upload file information
                    pst=conn.prepareStatement(sql);
                    pst.executeUpdate();
                    pst=conn.prepareStatement(sql1);
                    pst.executeUpdate();
                    conn.commit();
                }catch (SQLException e){
                    System.out.println(Util.UNI2GBK(e.toString()));
                }finally{
                        if (pst != null) {
                        try {
                            pst.close();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }


    }
}
