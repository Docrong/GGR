package com.boco.eoms.sparepart.bo;

import java.util.List;
import java.sql.*;
import java.io.*;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.controller.TawOrderForm;
import com.boco.eoms.sparepart.dao.TawOrderDAO;
import com.boco.eoms.sparepart.dao.TawSparepartDAO;
import com.boco.eoms.sparepart.dao.TawQueryDAO;
import com.boco.eoms.sparepart.dao.TawTempPartDAO;
import com.boco.eoms.sparepart.model.TawOrder;
import com.boco.eoms.sparepart.model.TawOrderPart;
import com.boco.eoms.sparepart.dao.TawOrderPartDAO;
import com.boco.eoms.sparepart.util.*;
import com.boco.eoms.sparepart.dao.*;
import org.apache.struts.upload.*;
import org.apache.struts.action.*;

/**
 * <p>Title: EOMS</p>
 * <p>Description: 单据的业务层</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawOrderBO extends BO{

    public TawOrderBO(ConnectionPool ds){
        super(ds);
    }

    public TawOrderBO(ConnectionPool ds,String str){
        super(ds,str);
    }

    /**
     * @see 插入单据表数据
     * @param TawOrder
     * @return orderId单据的id的主键
     */
    public int insertOrder(TawOrderForm form){
        TawOrderDAO dao=new TawOrderDAO(ds);
        TawOrder tawOrder=new TawOrder();
        tawOrder.setOperater(form.getOperaterId());
        tawOrder.setNote(form.getNote());
        tawOrder.setPropDept(form.getProp_dept());
        tawOrder.setProposer(form.getProposerId());
        tawOrder.setPropTel(form.getProp_tel());
        tawOrder.setType(Integer.parseInt(form.getOrderType()));
        tawOrder.setStorageid(Integer.parseInt(form.getStorageid()));
        tawOrder.setAccessory("");
        tawOrder.setSheetid(form.getSheetid());
        //add by wqw 20070703
        tawOrder.setReason(form.getReason());
        tawOrder.setEname(form.getEname());
        tawOrder.setObjtype(form.getObjtype());
        tawOrder.setFixe(form.getFixe());
        tawOrder.setVersion(form.getVersion());
        tawOrder.setSerialno(form.getSerialno());
        tawOrder.setStation(form.getStation());

        int orderId=dao.insertOrder(tawOrder);
        return orderId;
    }
    /**
     * @see 插入单据表数据和单据备件关联信息表.
     * @param TawOrder
     * @return orderId单据的id的主键
     */
    public int insertOrderandPart(TawOrderForm form){
        TawOrderDAO dao=new TawOrderDAO(ds);
        TawOrder tawOrder=new TawOrder();
        tawOrder.setOperater(form.getOperater());
        tawOrder.setNote(form.getNote());
        tawOrder.setPropDept(form.getProp_dept());
        tawOrder.setProposer(form.getProposer());
        tawOrder.setPropTel(form.getProp_tel());
        tawOrder.setType(Integer.parseInt(form.getOrderType()));
        tawOrder.setStorageid(Integer.parseInt(form.getStorageid()));
        tawOrder.setAccessory("");
        tawOrder.setSheetid(form.getSheetid());
        //add by wqw 20070703
        tawOrder.setReason(form.getReason());
        tawOrder.setEname(form.getEname());
        tawOrder.setObjtype(form.getObjtype());
        tawOrder.setFixe(form.getFixe());
        tawOrder.setVersion(form.getVersion());
        tawOrder.setSerialno(form.getSerialno());
        tawOrder.setStation(form.getStation());

        int orderId=dao.insertOrder(tawOrder);
        TawOrderPartDAO daopart=new TawOrderPartDAO(ds);
        daopart.insertOrderPart(String.valueOf(orderId), form.getSparepart_id(), "", "");//插入业务单据与备件关联表
        return orderId;
    }
    /**
     * @see 获取单据的列表
     * @param sql 条件
     * @return  单据列表
     */
    public List getTawOrder(String sql){
        TawOrderDAO dao=new TawOrderDAO(ds);
        List list=null;
        try{
            list=dao.getTawOrder(sql);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }
    /**
     * @see 根据单据编号获取一个单据的信息
     * @param order_id 条件
     * @return  单据列表
     * dww
     */
    public TawOrder getTawOrder(int order_id){
        TawOrderDAO dao=new TawOrderDAO(ds);
        List list=null;
        String sql="where id="+order_id;
        TawOrder tawOrder=new TawOrder();
        try{
            list=dao.getTawOrder(sql);
            tawOrder=(TawOrder)list.get(0);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return tawOrder;
    }    

    /**
     * @see 根据orderId得到taw_sp_order_part的所有备件的id和的字符串
     * @param orderId 单据id
     * @return 备件列表
     */
    public List getSparepart(int orderId){
        String StrId=Integer.toString(orderId);
        String sql=" where state=1 and order_id='"+StrId+"'";
        TawOrderPartDAO tawOrderPartDAO=new TawOrderPartDAO(ds);
        String SumId=tawOrderPartDAO.getSparePartSumId(sql);

        String SQL=" where id in ("+SumId+")";
        TawPartDAO tawPartDAO=new TawPartDAO(ds);
        List list=null;
        try{
            list=tawPartDAO.getSparepart(SQL);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }
    /**
     * @see 根据state得到taw_sp_order_part的所有备件和单据关联的数据
     * @param state 单据的状态 1,待审核 2,审批通过 3,待审核
     * @return 备件列表
     * dww
     */
    public List getOrderPart(String  state){
        String sql=" where state='"+state+"'";
        TawOrderPartDAO tawOrderPartDAO=new TawOrderPartDAO(ds);
        
        List list=null;
        try{
            list=tawOrderPartDAO.getOrderPart(sql);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }
    /**
     * @see 临时id时候先delete。
     * 在操作备件之前先删除垃圾数据，一些没有完成操作的数据。
     * @param user_id 用户id
     * @param order_id 单据id 主键
     */
    public void deleteTempId(String user_id,String order_id){
        String sql=
              " where user_id='"+user_id+"' and order_id='"+order_id+"'";
        TawTempPartDAO dao=new TawTempPartDAO(ds);
        dao.deleteTempPart(sql);
    }
    /**
     * 检查上传的文件是否重复
     */
    public boolean check(FormFile File,String FilePath){
        boolean flag = true;
        File theFile = new File(FilePath);
        String Filename[] = theFile.list();
        for(int i=0;i<Filename.length;i++){
          String aa = Filename[i];
          String bb = File.getFileName();
          if(aa.equals(bb)){
            flag = false;
            break;
          }
        }
        return flag;
     }
     public void updateOrder(String sql){
       TawOrderDAO dao=new TawOrderDAO(ds);
       dao.updateOrder(sql);
     }
   }
