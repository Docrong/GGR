package com.boco.eoms.sparepart.bo;

import java.util.List;
import java.util.ArrayList;

import java.sql.*;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.controller.TawStorageForm;
import com.boco.eoms.sparepart.dao.TawStorageDAO;
import com.boco.eoms.sparepart.model.TawStorage;
import com.boco.eoms.sparepart.dao.TawCommonDAO;
import com.boco.eoms.sparepart.util.*;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawStorageBO extends BO{

    public TawStorageBO(ConnectionPool ds){
        super(ds);
    }

    public TawStorageBO(ConnectionPool ds,String str){
        super(ds,str);
    }

    /**
     * @��form��ƴ��sql�����
     * @param tawStorageForm
     * @return
     */
    public TawStorage getStorage(String id){
        TawStorageDAO dao=new TawStorageDAO(ds);
        TawStorage storage=dao.getList(id);
        return storage;
    }

    public String getStorageName(String id){
        TawStorage storage=getStorage(id);
        return storage.getStoragename();
    }

    public String getStorageIdByName(String storageName){
      TawStorageDAO dao=new TawStorageDAO(ds);
      String storageId = "";
      try {
        storageId = Integer.toString(dao.getStorageIdByName(storageName));
      }
      catch (SQLException ex) {
        ex.printStackTrace();
      }

      return storageId;
    }

    public List getStorageList(){
        List list=new ArrayList();
        TawStorageDAO dao=new TawStorageDAO(ds);
        try{
            list=dao.getList();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * @deprecated ɾ��ֿ�ͬ�������򣬼���ɾ��ֿ��ͬʱɾ��ֿ��ڵ������еļ�¼
     * @param tawStorageForm
     * @return
     */
    public String deleteStorage(String storageId){
        String deleteok="";
        TawStorageDAO dao=new TawStorageDAO(ds);
        int id=Integer.parseInt(storageId);
        try{
            dao.deleteStroage(id);
            dao.deleteThirdDom(id);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            dao=null;
            deleteok=StaticPart.STOTAGE_DELETE_OK;
        }
        return deleteok;
    }

    /**
     * @deprecated ���ֿ��Ƿ�����
     * @return �з����
     */
    public boolean checkStorageName(String storagename){
        boolean flag=false;
        TawCommonDAO dao=new TawCommonDAO(ds);

        flag=dao.checkName("taw_sp_storage","storagename",storagename);

        return flag;
    }

    /**
     * �޸Ĳֿ�ͬ��������
     */
    public String updateStorage(int id,String name,String note,String deptId){

        try{
            TawStorageDAO dao=new TawStorageDAO(ds);
            dao.updateStroage(name,note,id,deptId);
            dao.updateThirdDom(id,name);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return StaticPart.STOTAGE_UPDATE_OK;
    }

    public String insertStorage(String name,String note,String deptId){
        try{
            TawStorageDAO dao=new TawStorageDAO(ds);
            //����ֿ�
            int id=dao.insert(name,note,deptId);
            //���������
            dao.insertThirdDom(id,name);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return StaticPart.STOTAGE_CREATE_OK;
    }

    /***/
    public List getDept(String deptid) throws Exception {
    	TawSystemDeptBo deptBO = TawSystemDeptBo.getInstance();
    	List dept = deptBO.getNextLevecDepts(deptid,"0");

    	return dept;
    }

    public String getDeptName(String storage) throws Exception {
    	TawStorageDAO dao=new TawStorageDAO(ds);
    	TawStorage tawStorage = dao.getTawStorage(storage);
    	TawSystemDeptBo deptBO = TawSystemDeptBo.getInstance();
    	TawSystemDept dept = deptBO.getDeptinfobydeptid(tawStorage.getDeptid(),"0");
    	return dept.getDeptName();
    }

    public List getStorageListByDeptId(String deptId) throws Exception {
      List list=new ArrayList();
      TawStorageDAO dao=new TawStorageDAO(ds);
      try{
          list=dao.getStorageListByDeptId(deptId);
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      return list;
    }

    public String getStorageTreeStr() throws Exception {
      String str="",str1="", str2="";
      TawSystemDept tawDept = null;
      TawStorage tawStorage = null;
      List deptList = getDept("1");
      List storageList = null;
      if(deptList!=null&&deptList.size()>0){   //
	      for(int i=0;i<deptList.size();i++) {
	        tawDept = (TawSystemDept)deptList.get(i);
	        storageList = getStorageListByDeptId(tawDept.getDeptId());
	        str1 = str1 + ",'" + tawDept.getDeptName() + "'";
	        str2 = "";
	        if(storageList!=null&&storageList.size()>0){  //
		        for(int j=0;j<storageList.size();j++) {
		          tawStorage = (TawStorage)storageList.get(j);
		          str2 = str2 + ",'" + tawStorage.getStoragename() + "'";
		        }
		        str2 = "dsyStorageTree.add('0_"+Integer.toString(i)+"',["+str2.substring(1,str2.length())+"]);";
	        }
	        str = str + str2;
	      }
	      str1 = "dsyStorageTree.add('0',["+str1.substring(1,str1.length())+"]);";
      }
      str = str1 + str;
      return str;
    }

}
