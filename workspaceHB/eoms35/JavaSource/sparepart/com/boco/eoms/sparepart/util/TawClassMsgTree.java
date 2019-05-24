package com.boco.eoms.sparepart.util;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;

import com.boco.eoms.commons.system.dept.service.impl.TawSystemDeptManagerImpl;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticObject;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.common.log.BocoLog;

public class TawClassMsgTree{

    ConnectionPool pool=ConnectionPool.getInstance();
    Connection con=null;
    private StaticObject obj=StaticObject.getInstance();

    public TawClassMsgTree(){
    }

    /**
     * @see ��ݸ��Ĳ����г��Ըò���Ϊ���ڵ�����Ĳ���
     * @param parent_dept
     * @return
     */
    public String strWKTree(String parent_dept){

        String strwktree="";
        String allTree="";
        boolean flag=false;

        if(StaticMethod.null2int(parent_dept)==StaticVariable.ProvinceID){
            allTree=StaticMethod.nullObject2String(obj.getObject(
                  "TawClassMsgTree"));
            if((allTree.equals(""))||(allTree==null)){
                flag=false;
            }
            else{
                flag=true;
            }
        }

        if(flag){
            strwktree=allTree;
        }
        else{
            if(StaticMethod.null2int(parent_dept)<0){
                parent_dept="0";
            }
            try{
                strwktree=treeNew(parent_dept);

                if((StaticMethod.null2int(parent_dept)==StaticVariable.ProvinceID)&&(!flag)){
                    obj.putObject("TawClassMsgTree",strwktree);
                }
            }
            catch(Exception e){
                BocoLog.error(this,0,"����",e);
            }
        }
        return strwktree;
    }

    /**
     *
     * @param deptId
     * @return
     * @throws java.lang.Exception
     */
    private String treeNew(String deptId) throws Exception{

        PreparedStatement ps=null;
        ResultSet rs=null;
        String id="";
        StringBuffer strTree=new StringBuffer();
        int count=0;
        int iDeptId=0;
        int iParentId=0;
        String sDeptName="";
        try{
            con=pool.getConnection();
        	TawSystemDeptBo deptBO = TawSystemDeptBo.getInstance();
            TawSystemDept TawSystemDept = deptBO.getDeptinfobydeptid(deptId,"0");
            
            id=TawSystemDept.getLinkid();
            String sql=
                  "SELECT id,parent_id,cname FROM taw_sp_classmsg where deleted!=4 order by id";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();

            while(rs.next()){
                iDeptId=rs.getInt(1);
                iParentId=rs.getInt(2);
                sDeptName=StaticMethod.dbNull2String(rs.getString(3));
                strTree.append("Tree["+count+"]=\""+iDeptId+"|"+
                               iParentId+"|"+sDeptName+"|#"+"\";");
                count++;
            }

        }
        catch(Exception e){
            BocoLog.error(this,0,"����",e);
        }
        finally{
            try{
                if(ps!=null){
                    ps.close();
                }
                if(rs!=null){
                    rs.close();
                }
                ps=null;
                rs=null;
                if(con!=null){
                    con.close();
                }
            }
            catch(SQLException e){
                BocoLog.error(this,0,"����",e);
            }
        }

        return strTree.toString();
    }

    public ArrayList dongNew() throws Exception{

        PreparedStatement ps=null;
        ArrayList list=new ArrayList();
        ResultSet rs=null;
        StringBuffer strTree=new StringBuffer();
        int id=0;
        int parent_id=0;
        String cname="";
        try{
            con=pool.getConnection();
            String sql=
                  "SELECT id,parent_id,cname FROM taw_sp_classmsg where id>1 order by id";
            ps=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);
            rs=ps.executeQuery();

            String aa="";
            while(rs.next()){
                id=rs.getInt(1);
                parent_id=rs.getInt(2);
                cname=StaticMethod.dbNull2String(rs.getString(3));
                aa=id+"_"+parent_id+"_"+cname;
                list.add(aa);
            }
        }
        catch(Exception e){
            BocoLog.error(this,0,"����",e);
        }
        finally{
            try{
                if(ps!=null){
                    ps.close();
                }
                if(rs!=null){
                    rs.close();
                }
                ps=null;
                rs=null;
                if(con!=null){
                    con.close();
                }
            }
            catch(SQLException e){
                BocoLog.error(this,0,"����",e);
            }
        }
        return list;
    }

    public String dong(String fid) throws Exception{
        ArrayList bb=this.dongNew();
        int arrayInt=bb.size();

        String dataStr="";
        String dataStr1="";
        String dataStr2="";
        String dataStr3="";
        int m=0;
        for(int i=0;i<arrayInt;i++){
            String open=bb.get(i).toString();
            int c=open.length();
            int s=open.indexOf("_");
            int e=open.lastIndexOf("_");
            String ss=open.substring(0,s);
            String ms=open.substring(s+1,e);
            String es=open.substring(e+1,c);

            if(ms.equals(fid)){
                dataStr1=dataStr1+",'"+es+"'";
                String dataStr22="";
                int n=0;
                for(int j=0;j<arrayInt;j++){
                    String open2=bb.get(j).toString();
                    int c2=open2.length();
                    int s2=open2.indexOf("_");
                    int e2=open2.lastIndexOf("_");
                    String ss2=open2.substring(0,s2);
                    String ms2=open2.substring(s2+1,e2);
                    String es2=open2.substring(e2+1,c2);

                    if(ms2.equals(ss)){
                        dataStr22=dataStr22+",'"+es2+"'";
                        /*
                                                 String dataStr33="";
                                                 for(int k=0;k<arrayInt;k++){
                            String open3=bb.get(k).toString();
                            int c3=open3.length();
                            int s3=open3.indexOf("_");
                            int e3=open3.lastIndexOf("_");
                            String ss3=open3.substring(0,s3);
                            String ms3=open3.substring(s3+1,e3);
                            String es3=open3.substring(e3+1,c3);
                            if(ms3.equals(ss2)){
                                dataStr33=dataStr33+",'"+es3+"'";
                            }
                                                 }
                                                 if(dataStr33.length()>1){
                               dataStr33=dataStr33.substring(1,dataStr33.length());
                            dataStr3=dataStr3+"dsy.add('0_"+m+"_"+n+"',["+
                                  dataStr33+"]);";
                                                 }
                                                 n++;
                         */
                    }
                }
                if(dataStr22.length()>1){
                    dataStr22=dataStr22.substring(1,dataStr22.length());
                    dataStr2=dataStr2+"dsy.add('0_"+m+"',["+dataStr22+"]);";
                }
                m++;
            }
        }
        dataStr1=dataStr1.substring(1,dataStr1.length());
        dataStr1="dsy.add('0',["+dataStr1+"]);";

        return dataStr=dataStr1+dataStr2+dataStr3;
    }

}
