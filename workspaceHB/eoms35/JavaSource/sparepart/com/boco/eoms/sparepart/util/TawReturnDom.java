package com.boco.eoms.sparepart.util;

import java.util.*;

import java.sql.SQLException;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.dao.TawStorageDAO;

public class TawReturnDom
      extends BO{

    public TawReturnDom(ConnectionPool ds){
        super(ds);
    }

    public TawReturnDom(ConnectionPool ds,String str){
        super(ds,str);
    }

    public List getStorage(Vector v){
        List list=new ArrayList();
        String sunId=" and (taw_sp_storage.id=0";
        for(int i=0;i<v.size();i++){
            sunId=sunId+" or taw_sp_storage.id="+v.get(i).toString();
        }
        sunId = sunId + ")";
        TawStorageDAO dao=new TawStorageDAO(ds);
        try{
            list=dao.getRoleList(sunId);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }
}
