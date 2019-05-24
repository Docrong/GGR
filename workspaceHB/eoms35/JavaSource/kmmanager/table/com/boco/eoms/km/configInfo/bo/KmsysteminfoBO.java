/**
 * @see
 * <p>功能描述：封装值班参数配置的业务逻辑类。</p>
 * <p>使用举例：首先实例化该类，然后通过实例化该类的对象，调用相应方法。</p>
 * @author 赵川
 * @version 1.0
 */

 package com.boco.eoms.km.configInfo.bo;

import java.sql.*;
import java.util.*;


import com.boco.eoms.common.bo.BO;
import com.boco.eoms.km.configInfo.model.*;
import com.boco.eoms.km.configInfo.dao.*;
import com.boco.eoms.common.util.*;




public class KmsysteminfoBO extends BO{
  public KmsysteminfoBO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }


  /**
   * @see 根据机房ID和以","分割的交接班时间串更新该机房交接班时间
   * @param roomid
   * @param exchangetime
   * @throws SQLException
   */
  public void updateTawRmExchange(int roomid,String exchangetime) throws SQLException {
    KmexchangeDAO tawRmExchangeDAO=null;
    Vector exchangetime_Vector=null;
    Kmexchange tawRmExchange=null;
    try{
      tawRmExchangeDAO = new KmexchangeDAO(ds);
      tawRmExchangeDAO.delete_room(roomid);
      exchangetime_Vector = StaticMethod.getVector(exchangetime,",");
      for(short i=0;i<exchangetime_Vector.size();i++){
        tawRmExchange = new Kmexchange();
        tawRmExchange.setExchangetime(exchangetime_Vector.get(i).toString());
        tawRmExchange.setId(i);
        tawRmExchange.setRoomId(roomid);
        tawRmExchangeDAO.insert(tawRmExchange);
      }
    }catch (SQLException e){
    }
    finally{
      //null
      tawRmExchangeDAO=null;
      exchangetime_Vector=null;
      tawRmExchange=null;
    }
  }

}