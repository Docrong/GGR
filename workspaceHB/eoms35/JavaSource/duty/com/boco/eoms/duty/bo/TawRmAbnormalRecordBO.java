/**
 * @see
 * <p>功能描述：封装机房用户对应关系的业务逻辑类。</p>
 * <p>使用举例：首先实例化该类，然后通过实例化该类的对象，调用相应方法。</p>
 * @author 赵川
 * @version 1.0
 */

 package com.boco.eoms.duty.bo;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.*;
import org.apache.struts.util.*;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.duty.controller.*;
import com.boco.eoms.duty.dao.*;
import com.boco.eoms.common.util.*;




public class TawRmAbnormalRecordBO extends BO{
  public TawRmAbnormalRecordBO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }

  public void insertAbnormalRecord(int type){
 	  TawRmRecordDAO tawRmRecordDAO=null;
    try{
      TawRmAbnormalRecordDAO dao = new TawRmAbnormalRecordDAO(ds);
      dao.insert(type);
    }
    catch (SQLException e) {
    }finally{
      tawRmRecordDAO=null;
    }
  }
  
  public void insertAbnormalRecord(int type,String tawRoomDutyCheckId,String addonsTableIDS){
 	  TawRmRecordDAO tawRmRecordDAO=null;
    try{
      TawRmAbnormalRecordDAO dao = new TawRmAbnormalRecordDAO(ds);
      dao.insert(type, tawRoomDutyCheckId, addonsTableIDS);
    }
    catch (SQLException e) {
    }finally{
      tawRmRecordDAO=null;
    }
  }
  
  public List findAbnormalRecordByType(int type,int roomid,int offset,int length){
  	TawRmRecordDAO tawRmRecordDAO=null;
  	List list=null;
    try{
      TawRmAbnormalRecordDAO dao = new TawRmAbnormalRecordDAO(ds);
      list=dao.getAbnormalRecordListByType(type,roomid,offset,length);
    }
    catch (SQLException e) {
    }finally{
      tawRmRecordDAO=null;
    }
    return list;
  }
  
  public List findAbnormalRecordByType(int type,int roomid){
	  	TawRmRecordDAO tawRmRecordDAO=null;
	  	List list=null;
	    try{
	      TawRmAbnormalRecordDAO dao = new TawRmAbnormalRecordDAO(ds);
	      list=dao.getAbnormalRecordListByType(type,roomid);
	    }
	    catch (SQLException e) {
	    }finally{
	      tawRmRecordDAO=null;
	    }
	    return list;
	  }
  
}