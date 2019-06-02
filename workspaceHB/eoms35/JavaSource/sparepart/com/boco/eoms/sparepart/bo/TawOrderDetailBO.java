package com.boco.eoms.sparepart.bo;


import java.util.List;
import java.sql.*;
import java.io.*;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.controller.TawOrderForm;
import com.boco.eoms.sparepart.dao.TawOrderDetailDAO;
import com.boco.eoms.sparepart.dao.TawSparepartDAO;
import com.boco.eoms.sparepart.dao.TawQueryDAO;
import com.boco.eoms.sparepart.dao.TawTempPartDAO;
import com.boco.eoms.sparepart.model.TawOrderDetail;
import com.boco.eoms.sparepart.dao.TawOrderPartDAO;
import com.boco.eoms.sparepart.util.*;
import com.boco.eoms.sparepart.dao.*;
import org.apache.struts.upload.*;
import org.apache.struts.action.*;

public class TawOrderDetailBO  extends BO{

  public TawOrderDetailBO(ConnectionPool ds){
      super(ds);
  }

  public TawOrderDetailBO(ConnectionPool ds,String str){
      super(ds,str);
  }

  public List getTawOrderDetail(String sql){
      TawOrderDetailDAO dao=new TawOrderDetailDAO(ds);
      List list=null;
      try{
          list=dao.getTawOrderDetail(sql);
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      return list;
  }

  public List getTawOrderDetail(String SQL,int offset,int length){
      TawOrderDetailDAO dao=new TawOrderDetailDAO(ds);
      List list=null;
      try{
          list=dao.getTawOrderDetail(SQL,offset,length);
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      return list;
  }

  public List getTawOrderDetailByStorageId(String storageId){
      TawOrderDetailDAO dao=new TawOrderDetailDAO(ds);
      List list=null;
      try{
          String sql = " where storage_id="+storageId;
          list=dao.getTawOrderDetail(sql);
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      return list;
  }

  public List getTawOrderDetailByStorageId(String storageId, int orderType){
      TawOrderDetailDAO dao=new TawOrderDetailDAO(ds);
      List list=null;
      try{
          String sql = " where storage_id="+storageId+
              " and order_type="+Integer.toString(orderType);
          list=dao.getTawOrderDetail(sql);
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      return list;
  }

  public List getTawOrderDetailByStorageId(String storageId, int orderType, int orderPartState){
      TawOrderDetailDAO dao=new TawOrderDetailDAO(ds);
      List list=null;
      try{
          String sql = " where storageid="+storageId+
              " and order_type="+Integer.toString(orderType)+
              " and order_part_state="+Integer.toString(orderPartState);
          list=dao.getTawOrderDetail(sql);
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      return list;
  }
  
  public List getTawOrderDetailByStorageId(String storageId,int orderType,int orderPartState,String supplier,String serialno,String objectname){
      TawOrderDetailDAO dao=new TawOrderDetailDAO(ds);
      List list=null;
      try{
          String sql = " where storageid="+storageId+
              " and order_type="+Integer.toString(orderType)+
              " and order_part_state="+Integer.toString(orderPartState);
          if(supplier!=null&&!supplier.equals(""))
          {
        	  sql=sql+" and supplier='"+supplier+"'";
          }
          if(serialno!=null&&!serialno.equals(""))
          {
        	  sql=sql+" and serialno='"+serialno+"'";
          }
          if(objectname!=null&&!objectname.equals(""))
          {
        	  sql=sql+" and objectname like '%"+objectname+"%'";
          }
          list=dao.getTawOrderDetail(sql);
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      return list;
  }
  
  public List getTawOrderDetailBySerialNo(String serialNo){
      TawOrderDetailDAO dao=new TawOrderDetailDAO(ds);
      List list=null;
      try{
        String sql =
            " where (serial_no like '%"+serialNo+"%' "+
                " or org_serial_no like '%"+serialNo+"%' "+
                " or new_serial_no like '%"+serialNo+"%') ";
        
          list=dao.getTawOrderDetail(sql);
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      return list;
  }
  public List getTawOrderDetailBySparepartId(String sparenpartId){
      TawOrderDetailDAO dao=new TawOrderDetailDAO(ds);
      List list=null;
      try{
        String sql =
            " where  SPAREPART_ID='"+sparenpartId+"' ";
          list=dao.getTawOrderDetail(sql);
      }
      catch(SQLException ex){
          ex.printStackTrace();
      }
      return list;
  }


}
