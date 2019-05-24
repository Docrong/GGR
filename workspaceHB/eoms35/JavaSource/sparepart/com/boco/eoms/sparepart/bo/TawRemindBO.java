package com.boco.eoms.sparepart.bo;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.dao.TawClassMsgDAO;
import com.boco.eoms.sparepart.controller.TawRemindForm;
import com.boco.eoms.sparepart.model.TawRemind;
import com.boco.eoms.sparepart.dao.TawRemindDAO;
import com.boco.eoms.sparepart.dao.TawOrderDAO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.sparepart.dao.TawCommonDAO;
import com.boco.eoms.sparepart.model.PartRemind;
import com.boco.eoms.sparepart.util.*;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawRemindBO extends BO {

    public TawRemindBO(){
    }

    public TawRemindBO(ConnectionPool ds){
        super(ds);
    }

    public TawRemindBO(ConnectionPool ds,String str){
        super(ds,str);
    }

    /**
     * @see 根据department,necode,objectname，返回备件名称的id
     * @param department  专业属性
     * @param necode   网元类型
     * @param objectname  备件名称
     * @return 备件名称的id 唯一
     */
    public String getClassId(String department,String necode,String objectname){
        String id="";
        TawClassMsgDAO tawClassMsgDAO=new TawClassMsgDAO(ds);
        id=tawClassMsgDAO.getClassMsgId(department,necode,objectname);
        if(!id.equals("")){
            int i=id.length();
            int s=id.indexOf("_");
            int e=id.lastIndexOf("_");
            String ss=id.substring(0,s);
            String ms=id.substring(s+1,e);
            String es=id.substring(e+1,i);
            id=ss;
        }
        return id;
    }

    /**
     * @see 插入库存设置的数据
     * @param tawRemindForm
     * @return 成功的信息
     */
    public String insertRemindPart(TawRemindForm tawRemindForm){
        TawRemind tawRemind=new TawRemind();
        tawRemind.setStorageid(tawRemindForm.getStorageid());
        tawRemind.setObject(tawRemindForm.getObjectname());
        tawRemind.setType(tawRemindForm.getType());
        tawRemind.setUpperlimit(tawRemindForm.getUpperlimit());
        tawRemind.setLowerlimit(tawRemindForm.getLowerlimit());

        TawRemindDAO tawRemindDAO=new TawRemindDAO(ds);

        tawRemindDAO.insertRemind(tawRemind);
        return StaticPart.SET_PART_OK;

    }

    /**
     * @see 插入单据设置的数据
     * @param tawRemindForm
     * @return  成功信息
     */
    public String insertRemindOrder(TawRemindForm tawRemindForm){
        TawRemind tawRemind=new TawRemind();
        tawRemind.setStorageid(tawRemindForm.getStorageid());
        tawRemind.setType(tawRemindForm.getType());
        tawRemind.setLimitdate(tawRemindForm.getLimitdate());
        TawRemindDAO tawRemindDAO=new TawRemindDAO(ds);
        tawRemindDAO.insertRemind(tawRemind);

        return StaticPart.SET_ORDER_OK;
    }

    /**
     * @see 根据仓库的id返回各类单据的设置信息列表
     * @param storageid  仓库id
     * @return 单据设置信息列表
     */
    public List getOrderRemind(String storageId){
        String sql=" where type!=10 and storageid="+storageId;
        TawRemindDAO tawRemindDAO=new TawRemindDAO(ds);
        List list=tawRemindDAO.getTawRemind(sql);

        return list;
    }

    /**
     * @see 根据单据设置信息与单据信息的比较，获取超过限期的单据信息
     * @param orderRemind 单据设置信息列表
     * @return 超过限期的单据信息列表
     */
    public List getOrderRemindMsg(String storageId){
        ArrayList list=new ArrayList();
        TawRemind tawRemind=new TawRemind();
        TawOrderDAO tawOrderDAO=new TawOrderDAO(ds);

        List orderRemind=getOrderRemind(storageId);
        for(int i=0;i<orderRemind.size();i++){
            tawRemind=(TawRemind)orderRemind.get(i);
            if(!tawRemind.getLimitdate().equals("")){
                List tawOrder=null;
                String nowDate=StaticMethod.getDateString(Integer.parseInt(
                      tawRemind.getLimitdate()));
                String sql=" where storageid="+tawRemind.getStorageid()+
                      " and type="+
                      tawRemind.getType()+" and "+nowDate+">startdate";
                try{
                    tawOrder=tawOrderDAO.getTawOrder(sql);
                } catch(SQLException ex){
                }
                list.add(tawOrder);
            }
        }
        return list;
    }

    /**
     * @see 校验是否有单据超过限期
     * @param storageId 仓库id
     * @return 返回true有单据超过限期；false,无单据超过限期
     */
    public boolean checkOrderRemind(String storageId){
        boolean hor=false;

        List orderRemind=getOrderRemindMsg(storageId);
        if((orderRemind!=null)&&(orderRemind.size()>0)){
            hor=true;
        }
        return hor;
    }

    /**
     * @see 根据仓库id返回超过限期的单据信息
     * @param storageId 仓库id
     * @return 超过限期的单据信息
     */
    public List OrderRemind(String storageId){
        List orderRemind=getOrderRemindMsg(storageId);

        return orderRemind;
    }

    /**
     * @see 根据仓库id获得库存备件信息和设置信息
     * @param storageId 仓库id
     * @return 库存备件信息和设置信息
     */
    public List getPartRemind(String storageId){
        TawCommonDAO tawCommonDAO=new TawCommonDAO(ds);
        List list=tawCommonDAO.getPartRemind(storageId);

        return list;
    }

    /**
     * @see 在库存备件信息和设置信息中，找出超出备件上限，下限的类型备件信息
     * @param partRemind 库存备件信息和设置信息
     * @return 有超出设置的类型备件信息，某一类型的备件
     */
    public List getPartRemindMsg(String storageId){
        List partRemind=getPartRemind(storageId);
        ArrayList list=new ArrayList();
        for(int i=0;i<partRemind.size();i++){
            PartRemind aa=(PartRemind)partRemind.get(i);
            int upper=Integer.parseInt(aa.getUpperlimit());
            int lower=Integer.parseInt(aa.getLowerlimit());
            int now=Integer.parseInt(aa.getNowdata());
            if(now-upper>0){
                aa.setSendMsg(StaticPart.PART_MANY);
            }
            if(now-lower<0){
                aa.setSendMsg(StaticPart.PART_ABSENT);
            }
            if(aa.getSendMsg()!=""||!aa.getSendMsg().equals(null)){
                list.add(aa);
            }
        }
        return list;
    }

    /**
     * @see 判断是否有超出限制的备件信息
     * @param storageId 仓库id
     * @return true 有超出设置的备件信息；否则，无超出设置的备件信息
     */
    public boolean checkPartRemind(String storageId){
        boolean hpr=false;

        List partRemind=getPartRemindMsg(storageId);
        if((partRemind!=null)&&(partRemind.size()>0)){
            hpr=true;
        }
        return hpr;
    }

}
