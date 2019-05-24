/**
 * @see
 * <p>功能描述：封装值班参数配置的业务逻辑类。</p>
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




public class TawRmSysteminfoBO extends BO{
  public TawRmSysteminfoBO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }


  /**
   * @see 根据机房ID和以","分割的交接班时间串更新该机房交接班时间
   * @param roomid
   * @param exchangetime
   * @throws SQLException
   */
  public void updateTawRmExchange(int roomid,String exchangetime) throws SQLException {
    TawRmExchangeDAO tawRmExchangeDAO=null;
    Vector exchangetime_Vector=null;
    TawRmExchange tawRmExchange=null;
    try{
      tawRmExchangeDAO = new TawRmExchangeDAO(ds);
      tawRmExchangeDAO.delete_room(roomid);
      exchangetime_Vector = StaticMethod.getVector(exchangetime,",");
      for(short i=0;i<exchangetime_Vector.size();i++){
        tawRmExchange = new TawRmExchange();
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