package com.boco.eoms.sparepart.bo;

import java.sql.*;
import java.util.*;

import com.boco.eoms.common.bo.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.db.util.*;
import com.boco.eoms.sparepart.controller.*;
import com.boco.eoms.sparepart.dao.*;
import com.boco.eoms.sparepart.model.*;
import com.boco.eoms.sparepart.util.*;
import java.lang.reflect.*;
import com.boco.eoms.sparepart.controller.TawBorrowForm;


public class TawBorrowBO extends BO {
  public TawBorrowBO(ConnectionPool ds) {
    super(ds);
  }
  public TawBorrowBO(ConnectionPool ds, String str) {
    super(ds, str);
  }

  /**
 * @see ���뱸����Ϣ,ͨ��j����������ķ������ǵ�id
 * @param form ҳ���BEAN
 * @return ��ӱ�����Ϣ�ɹ�
 */
public String insertPart(TawBorrowForm form) {
  TawBorrowDAO dao = new TawBorrowDAO(ds);
  TawPartDAO PartDAO = new TawPartDAO(ds);
  String department = form.getDepartment();
  String subdept=form.getSubdept();
  String necode = form.getNecode();
  String objecttype = form.getObjecttype();
  HashMap map = null;
  String position = form.getPosition();

  try {
    map = PartDAO.getPartId();
  }
  catch (Exception e) {

  }
  String id = getTreeThreeId(department,subdept, necode, objecttype);
  String[] idArray = getIdArray(id);
  if (idArray != null) {
    if (map.get(form.getSerialno()) == null) {
      dao.insertPart(form.getManagecode(), form.getSupplier(),
                     form.getEname(),
                     form.getOperator(), form.getNote(), form.getStorageid(),
                     form.getSerialno(), form.getProductcode(), idArray,
                     position,form.getContract());
      dao = null;
      return StaticPart.APP_PART_OK;
    }
    else {
      return  "该序列号在数据库中已存在，请检查！";
    }
  }
  else {
    return StaticPart.APP_PART_FAILURE;
  }
}

public String getTreeThreeId(String department,String subDept, String necode,
                             String objecttype) {
  TawTreeDao dao = new TawTreeDao(ds);
  String _3id = dao.getTreeThreeId(department,subDept, necode, objecttype);
  dao = null;
  return _3id;
}

public String[] getIdArray(String id) {
  if (!id.equals("")) {
    return id.split("_");
  }
  else {
    return null;
  }
}

/**
 * @see ��parentId�õ�����ص�������Ϣ
 * @param parentId �Թ�j���ֵ
 * @return ������Ϣ�б�
 */
public List getClassMsg(int parentId) {
  String sql = " where parent_id=" + parentId;
  List list = null;
  TawClassMsgDAO dao = new TawClassMsgDAO(ds);

  list = dao.getClassMsg(sql);
  dao = null;
  return list;
}

/**
 * @see condition ��ѯͳ�Ƶ����
 * @param form
 * @return
 */
public String getCondition(TawBorrowForm form){
  int temp =0 ; 
    convert(form);
    String condition="";
    if(!form.getStorageid().equals("")){
      if(temp==0){
        condition="where storageid='"+form.getStorageid()+"'";
        temp=1;
      }else{
        condition = condition + " and storageid='" + form.getStorageid() + "'";
      }
    }
    if(!form.getNettype().equals("")){
      if(temp==0){
        condition="where cname='"+form.getNettype()+"'";
        temp=1;
      }else{
        condition = condition + " and cname='" + form.getNettype() + "'";
      }
    }
    
    if(!form.getNecode().equals("")){
      if(temp==0){
        condition="where necname='"+form.getNecode()+"'";
        temp=1;
      }else{
        condition = condition + " and necname='" + form.getNecode() + "'";
      }
    }
    if(form.getObjecttypename()!=null&&!form.getObjecttypename().equals("")&&!form.getObjecttypename().equals("全选")){
      if(temp==0){
        condition="where objecttypename='"+form.getObjecttypename()+"'";
        temp=1;
      }else{
        condition = condition + " and objecttypename='" + form.getObjecttypename() +
            "'";
      }
    }
    if(!form.getSupplier().equals("")){
      if(temp==0){
        condition="where supplier='"+form.getSupplier()+"'";
        temp=1;
      }else{
        condition = condition + " and supplier=" + form.getSupplier();
      }
    }
    if(!form.getSerialno().equals("")){
      if(temp==0){
        condition="where serialno='"+form.getSerialno()+"'";
        temp=1;
      }else{
        condition = condition + " and serialno = '" + form.getSerialno() + " '";
      }
    }
    if(!form.getContract().equals("")){
      if(temp==0){
        condition="where contract='"+form.getContract()+"'";
        temp=1;
      }else{
        condition = condition + " and contract = '" + form.getContract() + " '";
      }
    }
    return condition;
}

  public void convert(Object beans){
    //ת��
        try {
          MyBeanUtils.copyPropertiesFromPageToDB(beans);
        }
        catch (InvocationTargetException ex) {
        }
        catch (IllegalAccessException ex) {
        }
}

}
