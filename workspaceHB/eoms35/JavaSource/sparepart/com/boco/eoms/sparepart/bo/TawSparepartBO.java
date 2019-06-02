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
import com.boco.eoms.common.util.StaticMethod;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawSparepartBO
    extends BO {

  public TawSparepartBO(ConnectionPool ds) {
    super(ds);
  }

  public TawSparepartBO(ConnectionPool ds, String str) {
    super(ds, str);
  }

  /**
   * @see 由parentId得到其相关的类型信息
   * @param parentId 自关联主键值
   * @return 类型信息列表
   */
  public List getClassMsg(int parentId) {
    String sql = " where parent_id=" + parentId+" order by id";
    List list = null;
    TawClassMsgDAO dao = new TawClassMsgDAO(ds);

    list = dao.getClassMsg(sql);
    dao = null;
    return list;
  }

  /**
   * @see 插入备件信息,通过联动的三个中文返回他们的id
   * @param form 页面的BEAN
   * @return 添加备件信息成功
   */
  public String insertPart(TawSparepartForm form) {
    TawSparepartDAO dao = new TawSparepartDAO(ds);
    TawPartDAO PartDAO = new TawPartDAO(ds);
    String department = form.getDepartment();
    String subDept=form.getSubDept();
    String necode = form.getNecode();
    String objecttype = form.getObjecttype();
    HashMap map = null;
    int proform = Integer.parseInt(form.getProform());
    int warrantyFlag = Integer.parseInt(form.getWarrantyFlag());
    int stopproductFlag = Integer.parseInt(form.getStopproductFlag());
    String position = form.getPosition();
    int state = Integer.parseInt(form.getState());

    try {
      map = PartDAO.getPartId();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    String id = getTreeThreeId(department,subDept, necode, objecttype);
    String[] idArray = getIdArray(id);
    String file="";
    if(!StaticMethod.null2String(form.getAccessory()).equals("")){
      file = "/sparepart/serverfile/" + form.getTheFile().getFileName();
    }
    if (idArray != null) {
      if (map.get(form.getSerialno()) == null) {
        dao.insertPart(form.getManagecode(), form.getSupplier(),
                       form.getEname(),
                       form.getOperator(), form.getNote(), form.getStorageid(),
                       form.getSerialno(), form.getProductcode(), form.getVersion(),idArray,
                       state, proform, position,form.getContract(),form.getMoney(),form.getSheetid(),
                       warrantyFlag, stopproductFlag,form.getAccessory(),file,form.getProposer(),
                       form.getCompany(),form.getObjtype(),form.getRepair_endtime(),form.getRepairtime(),form.getFixe(),form.getUnits());       
        dao = null;
        return StaticPart.APP_PART_OK;
      }
      else {
        return "该序列号在数据库中已存在，请检查！";
      }
    }
    else {
      return StaticPart.APP_PART_FAILURE;
    }
  }
  /**
   * @see 通过实物条形码得到备件id  dww 修改为入库申请专用 070803
   * @param managecode
   * @return 添加备件信息成功 sparepart_id
   */
  public int getSparepartIDbyManagecode(String managecode) {
     TawSparepartDAO dao = new TawSparepartDAO(ds);
     int id=dao.getSparepartIDbyManagecode(managecode);
	return id;
  }
  /**
   * @see 插入备件信息,通过联动的三个中文返回他们的id  dww 修改为入库申请专用 070803
   * @param form 页面的BEAN
   * @return 添加备件信息成功 sparepart_id
   */
  public String insertPart_away(TawSparepartForm form) {
    TawSparepartDAO dao = new TawSparepartDAO(ds);
    TawPartDAO PartDAO = new TawPartDAO(ds);
    String department = form.getDepartment();
    String subDept=form.getSubDept();
    String necode = form.getNecode();
    String objecttype = form.getObjecttype();
    HashMap map = null;
    int proform = Integer.parseInt(form.getProform());
    int warrantyFlag = Integer.parseInt(form.getWarrantyFlag());
    int stopproductFlag = Integer.parseInt(form.getStopproductFlag());
    String position = form.getPosition();
    int state = Integer.parseInt(form.getState());

    try {
      //map = PartDAO.getPartId();
      map = PartDAO.getPartId_formanagecode();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    String id = getTreeThreeId(department,subDept, necode, objecttype);
    String[] idArray = getIdArray(id);
    String file="";
    if(!StaticMethod.null2String(form.getAccessory()).equals("")){
      file = "/sparepart/serverfile/" + form.getTheFile().getFileName();
    }
    if (idArray != null) {
      if (map.get(form.getManagecode()) == null) {
        dao.insertPart_away(form.getManagecode(), form.getSupplier(),
                       form.getEname(),
                       form.getOperator(), form.getNote(), form.getStorageid(),
                       form.getSerialno(), form.getProductcode(), form.getVersion(),idArray,
                       state, proform, position,form.getContract(),form.getMoney(),form.getSheetid(),
                       warrantyFlag, stopproductFlag,form.getAccessory(),file,form.getProposer(),
                       form.getCompany(),form.getObjtype(),form.getRepair_endtime(),form.getRepairtime(),
                       form.getFixe(),form.getDeleted(),form.getUnits(),form.getPartType(),form.getDescribe());       
        dao = null;
        return StaticPart.APP_PART_OK;
      }
      else {
        return "该实物编码在数据库中已存在，请检查！";
      }
    }
    else {
      return StaticPart.APP_PART_FAILURE;
    }
  }  

  public String insertBorrowInSparePart(String SumId, String storageid) {
    TawSparepartDAO dao = new TawSparepartDAO(ds);
    TawPart part=null;

    String sql=" where id in (" + SumId + ")";
    List sparepart=getSparepart(sql);
    for (int i=0;i<sparepart.size();i++){
      part = (TawPart)sparepart.get(i);
      part.setStorageid(Integer.parseInt(storageid));
      part.setBorrowState(2); //1本库,2借入,3借出
      part.setStateid(11); //11库中可用
      dao.insertPart1(part);
    }

    dao = null;
    return StaticPart.APP_PART_OK;
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
   * @see sql of term
   * @param form
   * @return  sql的条件字符串
   */
  public String getViewSql(TawSparepartForm form) {
    String sql = " where storageid=" + form.getStorageid();
    //5为待修，满足维修出库的条件

//    if (form.getOrderType().equals("5")) {
//      sql = sql + " and stateid in (11,12,19)";
//    }
    //
    if (form.getPartType() == 1) {//备品备件
        sql = sql + " and parttype='" + form.getPartType() + "'";
        String type = form.getOrderType();
        if (type.equals("6") || type.equals("7") || type.equals("8") || type.equals("9")|| type.equals("10")|| type.equals("11")) {//出库单
          sql = sql + " and stateid in(11,12,19)";//新件入库,维修入库,其他入库.
        }
        if (type.equals("2")) {//维修入库单
          sql = sql + " and stateid=13";//13是维修出库状态.
        }
      }else if(form.getPartType() == 2){//仪器仪表
          sql = sql + " and parttype='" + form.getPartType() + "'";
          String type = form.getOrderType();
          if (type.equals("35") || type.equals("36")) {//仪器仪表出库单
            sql = sql + " and stateid in(11,12,19)";//新件入库,维修入库,其他入库,归还入库
          }
          if (type.equals("32")) {//归还入库单
            sql = sql + " and stateid=20 or stateid=21";//20是借用出库状态.21检测出库状态
          } 
      }else{//物资管理
          sql = sql + " and parttype='" + form.getPartType() + "'";
          String type = form.getOrderType();
          if (type.equals("26") || type.equals("27") || type.equals("28") || type.equals("29")|| type.equals("30")|| type.equals("11")) {//出库单
            sql = sql + " and stateid in(11,12,19)";//新件入库,维修入库,其他入库.
          }
          if (type.equals("22")) {//维修入库单
            sql = sql + " and stateid=13";//13是维修出库状态.
          }
      }
    if (form.getNettype() != null&&!form.getNettype().equals("")) {
      sql = sql + " and nettype='" + form.getNettype() + "'";
    }
    if (form.getNecode() != null&&!form.getNecode().equals("")) {
      sql = sql + " and necode='" + form.getNecode() + "'";
    }
    if (form.getObjecttype() != null&&!form.getObjecttype().equals("")) {
      sql = sql + " and objecttype='" + form.getObjecttype() + "'";
    }
    if (form.getSupplier() !=null && !form.getSupplier().equals("")) {
      sql = sql + " and supplierid=" + form.getSupplier();
    }
    /*
    if (!form.getOperator().equals("")) {
      sql = sql + " and operator='" + form.getOperator() + "'";
    }
    */
   if (form.getSerialno() !=null && !form.getSerialno().equals("")) {
      sql = sql + " and serialno like '%" + form.getSerialno() + "%'";
    }
    if (form.getContract() !=null && !form.getContract().equals("")) {
      sql = sql + " and contract like '%" + form.getContract() + "%'";
    }
    if (form.getObjectname() !=null && !form.getObjectname().equals("")) {
      sql = sql + " and objectname like '%" + form.getObjectname() + "%'";
    }
    if(form.getState() !=null && !form.getState().equals("")){
    	sql=sql+" and stateid like '"+form.getState()+"'";
    }
//    if (!form.getTimein().equals("")){
//      sql = sql + " and intime between '"+ form.getTimein() + "'";
//    }
//    if (!form.getTimeend().equals("")){
//      sql = sql + " and '" + form.getTimeend() + "'";
//    }
   
    	
    System.out.print(sql);
    return sql;
  }

  /**
   * @see 取得查询的sql条件字符串
   * @param form 页面BEAN
   * @return sql的where字符串
   */
  public String getUpdateSql(TawSparepartForm form) {
    String sql = " where storageid=" + form.getStorageid();
    if (form.getNettype() != null&&!form.getNettype().equals("")) {
      System.out.println(form.getNettype());
      sql = sql + " and nettype='" + form.getNettype() + "'";
    }
    if (form.getNecode() != null&&!form.getNecode().equals("")) {
      sql = sql + " and necode='" + form.getNecode() + "'";
    }
    if (form.getObjectname() != null&&!form.getObjectname().equals("")) {
      sql = sql + " and objecttype='" + form.getObjectname() + "'";

    }
    if (!form.getSupplier().equals("")) {
      sql = sql + " and supplierid=" + form.getSupplier();
    }
    if(form.getSubDept()!=null&&!form.getSubDept().equals("")){
    	 sql = sql + " and SUBDEPT='" + form.getSubDept()+"'";
    	
    }
//    if (!form.getEname().equals("")) {
//      sql = sql + " and objectname='" + form.getEname() + "'";
//    }
    if (!form.getContract().equals("")) {
      sql = sql + " and contract='" + form.getContract() + "'";
    }
    if (!form.getOperator().equals("")) {
      sql = sql + " and operator='" + form.getOperator() + "'";
    }
    if (!form.getSerialno().equals("")) {
      sql = sql + " and serialno='" + form.getSerialno() + "'";
    }
//    if (!form.getManagecode().equals("")) {
//      sql = sql + " and managecode='" + form.getManagecode() + "'";
//    }
//    if(!form.getTimein().equals("")){
//      sql = sql + " and intime between '" + form.getTimein()+"'";
//    }
//    if (!form.getTimeend().equals("")){
//      sql = sql + " and '" + form.getTimeend() +"'";
//    }

    return sql;
  }

  /**
   * @see  获得临时的part列表
   * @param sql 条件
   * @return 在临时表中的已经选择的sparepart
   */
  public List getTemppart(String sql) {
    String SQL = " where id in(" + sql + ")";
    List list = null;
    TawPartDAO tawPartDAO = new TawPartDAO(ds);
    try {
      list = tawPartDAO.getSparepart(SQL);
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      list = null;
    }
    return list;
  }


  /**
   * @see 获得被选中的备件id,用于备件操作，支持多选，使用数据库实现的
   * @param aa 备件的id数组，选中的不畏空
   * @param orderId
   * @param userId
   * @return 临时选中的备件id字符串和
   * @see插入已经选择的备件 id ,返回已经选择id的集合
   */
  public String getChoosedId(String[] aa, String userId, String orderId) {
    TawTempPartDAO dao = new TawTempPartDAO(ds);
    //20040712
    if (aa != null) {
      for (int i = 0; i < aa.length; i++) {
        String id = aa[i].toString();
        dao.insertTempPart(id, userId, orderId);
      }
    }
    String sql =
        " where user_id='" + userId + "' and order_id='" + orderId + "'";
//          " where user_id='" + userId + "'";
    String SumId = "";
    try {
      SumId = dao.getTempPartId(sql);
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
    return SumId;
  }

  /**
   * @see 插入单据备件关系表信息，是从临时表中获得的信息，通过orderId检索
   * @param userId
   * @param orderId
   */
  public void insertOrderPart(String userId, String orderId) {
    TawTempPartDAO dao = new TawTempPartDAO(ds);
    TawOrderDAO order = new TawOrderDAO(ds);
    TawOrderPartDAO tawOrderPartDAO = new TawOrderPartDAO(ds);

    List TempPart = null;
    try {
      TempPart = dao.getTempPart(userId, orderId);
      for (int i = 0; i < TempPart.size(); i++) {
        TawTempPart tawTempPart = (TawTempPart) TempPart.get(i);
        tawOrderPartDAO.insertOrderPart(tawTempPart.getOrderId(),
                                        tawTempPart.getSparepartId(),
                                        tawTempPart.getOrgSerialNo(),
                                        tawTempPart.getNewSerialNo());
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
    //删除临时表中的信息
    String sql = " where order_id='" + orderId + "'";
    dao.deleteTempPart(sql);
    //state=1 代表正常单，数据完整单。0代表不完整的数据，缺少备件信息。
    String update = " set state=1 where id=" + orderId;
    order.updateOrder(update);
  }

  /**
   * @see 通过借出单的OderPart得到借入单的OrderPart
   * @param userId
   * @param orderId
   */
  public void insertBorrowInOrderPart(String userId, String borrowOutOrderId,
                                      String borrowInOrderId, String storageId) {
    TawTempPartDAO dao = new TawTempPartDAO(ds);
    TawOrderDAO order = new TawOrderDAO(ds);
    TawOrderPartDAO tawOrderPartDAO = new TawOrderPartDAO(ds);

    List orderPartList = null;
    try {
      orderPartList = tawOrderPartDAO.getOrderPart(" where order_id="+borrowOutOrderId);
      for (int i = 0; i < orderPartList.size(); i++) {
        TawOrderPart orderPart = (TawOrderPart) orderPartList.get(i);
        String borrowInSparepartId = getOtherStorageSparepartId(orderPart.getSparepartId(),
            storageId);
        tawOrderPartDAO.insertOrderPart(borrowInOrderId,
                                        borrowInSparepartId,
                                        orderPart.getOrgSerialNo(),
                                        orderPart.getNewSerialNo());
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }

    String update = " set state=1 where id=" + borrowInOrderId;
    order.updateOrder(update);
  }

  /**
   * @see 根据单据的类型，改变备件的状态。
   * @param form  页面的BEAN
   * @return orderover，成功的信息
   */
  public String updateSparePart(String type, String SumId) {
    int state = getTypeToState(type);
    TawSparepartDAO dao = new TawSparepartDAO(ds);

    String update = " set state=" + state +", outtime='"+StaticMethod.getLocalString().substring(0,10) +"' where id in (" + SumId + ")";
    dao.updateSparePart(update);

    return StaticPart.ORDER_OVER;
  }

  /**
   * @param orderType  单据类型的数字代码
   * @return  单据操作后的备件状态
   */
  public int getTypeToState(String orderType) {
    int state = 0;
    switch (Integer.parseInt(orderType)) {
      case 6:
        state = 14;
        break;
      case 2:
        state = 16;
        break;
      case 3:
        state = 13;
        break;
      case 4:
        state = 12;
        break;
      case 5:
        state = 15;
        break;
      case 1:
        state = 11;
        break;
      case 7:
      state = 16;
      break;
      default:
        state = 11;
    }
    return state;
  }

  /**
   * @see 得到备件信息
   * @param sql
   * @return 备件列表
   */
  public List getSparepart(String sql) {
    TawPartDAO tawPartDAO = new TawPartDAO(ds);
    List list = null;
    try {
      System.out.println(sql);
      list = tawPartDAO.getSparepart(sql);
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
    tawPartDAO = null;
    return list;
  }
  /**
   * @see 通过备件ID得到一个备件信息
   * @param sql
   * @return 备件列表
   */
  public TawSparepart getSparepart_a(int sparepart_id) {
	  TawSparepartDAO sparepartDAO = new TawSparepartDAO(ds);
	  TawSparepart sparepartPart=new TawSparepart();
      List list=null;
      sparepartPart = sparepartDAO.getSparepart(sparepart_id);
      sparepartDAO = null;
      return sparepartPart;
  }  

  public String getOtherStorageSparepartId(String sparepartId, String storageId) {
    List list = null;
    String sql = " where storageid = '" + storageId + "'" +
        " and org_serial_no in (select org_serial_no from taw_part " +
        " where storageid = '" + storageId + "' and id=" + sparepartId + ")";

    String otherStorageSparepartId = "";
    TawPart tawPart = null;

    list = getSparepart(sql);
    if (list.size()>0){
      tawPart = (TawPart)list.get(0);
      otherStorageSparepartId = Integer.toString(tawPart.getId());
    }
    return otherStorageSparepartId ;
  }

  /**
   * @see 备件修改的功能实现
   * @see updateTawSparePart
   * @param form
   */
  public String updateTawSparePart(TawSparepartForm form) {
    TawSparepartDAO dao = new TawSparepartDAO(ds);
    TawPartDAO PartDAO = new TawPartDAO(ds);
    HashMap map = null;
    //获取连动的三个id
    String id = getTreeThreeId(form.getDepartment(),form.getSubDept(), form.getNecode(),
                               form.getObjectname());
    if (id == "") {
      return StaticPart.NO_PART_NAME;
    }

      String[] Id = getIdArray(id);

        TawPart tawPart = new TawPart();
        tawPart.setNettype(Id[0]);
        tawPart.setSubdept(Id[1]);
        tawPart.setNecode(Id[2]);
        tawPart.setObjecttype(Id[3]);
        tawPart.setId(Integer.parseInt(form.getId()));
        tawPart.setObjectname(form.getEname());
        tawPart.setSupplier(form.getSupplier());
        tawPart.setProductcode(form.getProductcode());
        tawPart.setManagecode(form.getManagecode());
        tawPart.setSerialno(form.getSerialno());
        tawPart.setOperator(form.getOperator());
        tawPart.setNote(form.getNote());
        tawPart.setContract(form.getContract());
        tawPart.setMoney(form.getMoney());
        tawPart.setPosition(form.getPosition());
        tawPart.setProformFlag(Integer.parseInt(form.getProform()));
        tawPart.setWarrantyFlag(Integer.parseInt(form.getWarrantyFlag()));
        tawPart.setStopproductFlag(Integer.parseInt(form.getStopproductFlag()));
        tawPart.setObjtype(form.getObjtype());//新增０７１０２２
        tawPart.setVersion(form.getVersion());//新增０７１０２２
        tawPart.setCompany(form.getCompany());//新增０７１０２２
        tawPart.setFixe(form.getFixe());//新增０７１０２２
        tawPart.setDescribe(form.getDescribe());//新增０７１０２２描述
        dao.updatePart(tawPart);
        dao = null;
        return StaticPart.UPDATE_OVER;
  }

  /**
   * @see 归还备件的业务处理，支持原件返回和损坏两种操作。
   * @see 双层的业务逻辑是通过两个数组实现的。
   * @see 在这里要改变三个表的状态，备件表，单据表，单据备件关联表。
   * @param aa 原件返回的备件id数组
   * @param bb 损坏的备件id数组
   * @param orderId 单据
   */
  public void updateReturn(String[] aa, String[] bb, String storageId) {
    String sparepartId = "", newSerialNo = "", orderId = "", overgay = "", str = "";
    String[] strs;

    TawSparepartDAO dao = new TawSparepartDAO(ds);
    TawOrderPartDAO tawOrderPartDAO = new TawOrderPartDAO(ds);
    TawOrderDAO tawOrderDAO = new TawOrderDAO(ds);

    if (aa != null) {
//      for (int i = 0; i < aa.length; i++) {
//        str = aa[i].toString();
//        strs = str.split(",");
//        sparepartId = strs[0];
//        newSerialNo = strs[2];
//        orderId = strs[3];
//        overgay = strs[4];
//
//        String borrowOutSparepartId = getOtherStorageSparepartId(sparepartId,storageId);
//        String sql = " set state=11,borrow_state=1,serialno='"+newSerialNo+"' where id in (" +borrowOutSparepartId+')';
//        dao.updateSparePart(sql);
//        deletePart(sparepartId);
//        //单中已经处理的备件
//        String update = " set state=2 where order_id='" + orderId +
//           "' and sparepart_id in (" +sparepartId+")";
//        tawOrderPartDAO.updateOrderPart(update);
//        
//      } add by lq20070320
    	  for (int i = 0; i < aa.length; i++) {
    		str = aa[i].toString();
            strs = str.split(",");
            sparepartId = strs[0];
            orderId = strs[3];
    	      }
    	      String sql = " set state=11 where id in (" +
    	      sparepartId + ")";
    	      dao.updateSparePart(sql);
    	      //单中已经处理的备件 
    	      String update = " set state=2 where order_id='" + orderId +
            "' and sparepart_id in (" +sparepartId+")";
            tawOrderPartDAO.updateOrderPart(update);
    }

//    if (bb != null) {
//      for (int i = 0; i < bb.length; i++) {
//        str = bb[i].toString();
//        strs = str.split(",");
//        sparepartId = strs[0];
//        newSerialNo = strs[2];
//        orderId = strs[3];
//        overgay = strs[4];
//
//        sparepartId = bb[i].toString();
//        String borrowOutSparepartId = getOtherStorageSparepartId(sparepartId,storageId);
//        String sql = " set state=15,borrow_state=1 where id in (" +borrowOutSparepartId+')';
//        dao.updateSparePart(sql);
////        deletePart(sparepartId);
//        //单中已经处理的备件
//        String update = " set state=2 where order_id='" + orderId +
//            "' and sparepart_id in (" +sparepartId+")";
//        tawOrderPartDAO.updateOrderPart(update);
//      }
//    } add by lq 20070320
    
    
    if (bb != null) {
        for (int i = 0; i < bb.length; i++) {
        	str = bb[i].toString();
            strs = str.split(",");
            sparepartId = strs[0];
            newSerialNo = strs[2];
            orderId = strs[3];
            overgay = strs[4];
        }
        String SQL = " set state=12 where id in (" +
        sparepartId + ")";
        dao.updateSparePart(SQL);

        String update = " set state=2,mangle=1 where order_id='" + orderId +
            "' and sparepart_id in (" +
            sparepartId+ ")";
        tawOrderPartDAO.updateOrderPart(update);
      }
    
    
    //state 已经处理,当order_part的state全为1时候，执行updateorder
    String updateorder = " set state=2,overdate='" +
        StaticMethod.getCurrentDateTime().substring(0, 10) + "' where id=" +
        orderId;
    if (!tawOrderPartDAO.checkPartState(orderId)) {
      tawOrderDAO.updateOrder(updateorder);
    }
  }
  
  /**add by wqw 
   * @see 归还备件的业务处理，支持原件返回和损坏两种操作。
   * @see 双层的业务逻辑是通过两个数组实现的。
   * @see 在这里要改变三个表的状态，备件表，单据表，单据备件关联表。
   * @param aa 原件返回的备件id数组
   * @param bb 损坏的备件id数组
   * @param orderId 单据
   */
  public void updateReturn(String[] aa, String[] bb, String storageId,String overtime) {
    String sparepartId = "", newSerialNo = "", orderId = "", overgay = "", str = "";
    String[] strs;

    TawSparepartDAO dao = new TawSparepartDAO(ds);
    TawOrderPartDAO tawOrderPartDAO = new TawOrderPartDAO(ds);
    TawOrderDAO tawOrderDAO = new TawOrderDAO(ds);

    if (aa != null) {

    	  for (int i = 0; i < aa.length; i++) {
    		str = aa[i].toString();
            strs = str.split(",");
            sparepartId = strs[0];
            orderId = strs[3];
    	      }
    	      String sql = " set state=11 where id in (" +
    	      sparepartId + ")";
    	      dao.updateSparePart(sql);
    	      //单中已经处理的备件 
    	      String update = " set state=2 where order_id='" + orderId +
            "' and sparepart_id in (" +sparepartId+")";
            tawOrderPartDAO.updateOrderPart(update);
    }    
    
    if (bb != null) {
        for (int i = 0; i < bb.length; i++) {
        	str = bb[i].toString();
            strs = str.split(",");
            sparepartId = strs[0];
            newSerialNo = strs[2];
            orderId = strs[3];
            overgay = strs[4];
        }
        String SQL = " set state=12 where id in (" +
        sparepartId + ")";
        dao.updateSparePart(SQL);

        String update = " set state=2,mangle=1 where order_id='" + orderId +
            "' and sparepart_id in (" +
            sparepartId+ ")";
        tawOrderPartDAO.updateOrderPart(update);
      }
    
    
    //state 已经处理,当order_part的state全为1时候，执行updateorder
    String updateorder = " set state=2,overdate='" +
        overtime + "' where id=" +
        orderId;
    if (!tawOrderPartDAO.checkPartState(orderId)) {
      tawOrderDAO.updateOrder(updateorder);
    }
  }
  /**
   * @see 维修回库的业务操作，支持原件返回和添加新件。
   * @see 通过两个数组实现。
   * @see 改变三个表的状态，备件表，单据表，单据备件关联表。
   * @param aa
   * @param bb
   * @param orderId
   */
  public void updateService(String[] repairFlags) {
    String sparepartId = "";
    String serialNo = "",orderId="", charge="";
    String str = "";
    String[] strs;

    TawOrderPartDAO tawOrderPartDAO = new TawOrderPartDAO(ds);
    TawSparepartDAO dao = new TawSparepartDAO(ds);

    if (repairFlags != null) {
      for (int i = 0; i < repairFlags.length; i++) {
        str = repairFlags[i].toString();
        strs = str.split(",");
        sparepartId = strs[0];
        orderId = strs[1];
        serialNo = strs[3];
        charge = strs[4];

        String SQL = " set state=11, serialno='"+serialNo+"' where id in (" +
          sparepartId + ")";
        dao.updateSparePart(SQL);
        //state=2为处理过的单据备件信息，mangle=2为换新件
        String update = " set state=2, mangle=2 where order_id='" + orderId +
          "' and sparepart_id in (" + sparepartId + ")";
        tawOrderPartDAO.updateOrderPart(update);

        String sql = " set charge='" + charge + "' where order_id=" + orderId +
            " and sparepart_id=" + sparepartId;
        tawOrderPartDAO.updateOrderPart(sql);

        //state 已经处理,当order_part的state全为1时候，执行updateorder
        String updateorder = " set state=2,overdate='" +
            StaticMethod.getCurrentDateTime().substring(0, 10) + "' where id=" +
            orderId;
        TawOrderDAO tawOrderDAO = new TawOrderDAO(ds);
        if (!tawOrderPartDAO.checkPartState(orderId)) {
          tawOrderDAO.updateOrder(updateorder);
        }


      }
    }
  }

  /**
   * @see 备件删除
   * @param id
   * @return
   */
  public String deletePart(String id) {
    String condition = " where id=" + id;
    TawSparepartDAO dao = new TawSparepartDAO(ds);
    dao.deleteSparePart(condition);
    return StaticPart.PART_DELETE_OK;
  }

  public void insertCharge(String[] charge, String orderId) {
    TawOrderPartDAO tawOrderPartDAO = new TawOrderPartDAO(ds);
    System.out.println("orderId=" + orderId);
    if (charge != null) {
      for (int i = 0; i < charge.length; i++) {
        String str = charge[i].toString();
        int a = str.indexOf("_");
        int b = str.length();
        if (b > 1 + a) {
          String id = str.substring(0, a);
          String rate = str.substring(a + 1, b);
          String sql = " set charge='" + rate + "' where order_id=" + orderId +
              " and sparepart_id=" + id;
          tawOrderPartDAO.updateOrderPart(sql);
        }
      }
    }
  }

  public String updateBack(String[] back, String orderId) {
    String backId = "";
    TawOrderPartDAO tawOrderPartDAO = new TawOrderPartDAO(ds);
    TawSparepartDAO dao = new TawSparepartDAO(ds);
    if (back != null) {
      for (int i = 0; i < back.length; i++) {
        backId = backId + "," + back[i].toString();
      }
    }
    String condition = " set state=11 where id in(" + backId + ")";

    try {
      tawOrderPartDAO.updateOrderPart(backId, orderId);
      dao.updateSparePart(condition);
      backId = StaticPart.BACK_PART_OK;
    }
    catch (Exception ex) {
      backId = StaticPart.BACK_PART_NO;
      ex.printStackTrace();
    }

    return backId;
  }

  //将已经选中要进行转库操作的备件ID，连同执行该操作的用户ID存放到数据库中，同时返回已经选中的备件的ID
  public String getRemoveChoosedId(String[] aa, String userId) {
    TawTempPartDAO dao = new TawTempPartDAO(ds);
    if (aa != null) {
      for (int i = 0; i < aa.length; i++) {
        String id = aa[i].toString();
        if (id != "") {
          dao.insertRemovePart(id, userId);
        }
      }
    }
    String sql =
        " where user_id='" + userId + "'";
    String SumId = "";
    try {
      SumId = dao.getRemoveTempPartId(sql);
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
    return SumId;
  }

  //改变备件所属的仓库
  public String updateRemovePart(String SumId, String storageid_id) {
    TawSparepartDAO dao = new TawSparepartDAO(ds);

    String update = " set storageid=" + storageid_id + " where id in (" + SumId +
        ")";
    dao.updateSparePart(update);

    return StaticPart.REMOVE_OVER;
  }

  //拼写SQL的条件语句
  public String getRemoveViewSql(TawSparepartForm form) {
    String sql = " where storageid=" + form.getStorageid();
    if (form.getNettype() != null&&!form.getNettype().equals("")) {
      sql = sql + " and nettype='" + form.getNettype() + "'";
    }
    if (form.getNecode() != null&&!form.getNecode().equals("")) {
      sql = sql + " and necode='" + form.getNecode() + "'";
    }
    if (form.getObjecttype() != null&&!form.getObjecttype().equals("")) {
      sql = sql + " and objecttype='" + form.getObjecttype() + "'";
    }
    if (!form.getSupplier().equals("")) {
      sql = sql + " and supplierid=" + form.getSupplier();
    }
    /*
    if (!form.getOperator().equals("")) {
      sql = sql + " and operator='" + form.getOperator() + "'";
    }
    */
    if (!form.getSerialno().equals("")) {
      sql = sql + " and serialno='" + form.getSerialno() + "'";
    }
    if (!form.getObjectname().equals("")) {
        sql = sql + " and objectname like '%" + form.getObjectname() + "%'";
      }
      if(!form.getState().equals("")){
      	sql=sql+" and stateid like '"+form.getState()+"'";
      }
    /*
    if (!form.getManagecode().equals("")) {
      sql = sql + " and managecode='" + form.getManagecode() + "'";
    }
    */
    return sql;
  }

  /**
   * @see 临时id时候先delete。
   * 在操作备件之前先删除垃圾数据，一些没有完成操作的数据。
   * @param user_id 用户id
   * @param order_id 单据id 主键
   */
  public void deleteRemoveTempId(String user_id) {
    String sql =
        " where user_id='" + user_id + "'";
    TawTempPartDAO dao = new TawTempPartDAO(ds);
    dao.deleteRemoveTempPart(sql);
  }

  //用没有入库的坏备件来替换库中同样型号的好备件
  public void updatePart(String WellPart, String BadPart, String storageid) {
    String sql = "set serialno = '" + BadPart + "',state=12 where serialno = '"
        + WellPart + "' and storageid = " + storageid;
    System.out.println(sql);
    TawSparepartDAO dao = new TawSparepartDAO(ds);
    dao.updateSparePart(sql);
  }

  public void updatePart(String WellPart, String BadPart) {
    String sql = "set serialno = '" + BadPart + "',state=12 where serialno = '"
        + WellPart;
    System.out.println(sql);
    TawSparepartDAO dao = new TawSparepartDAO(ds);
    dao.updateSparePart(sql);
  }

  //检查库中是否存在该备件信息
  public boolean checkPart(String WellPart) {
    boolean flag = false;
    List check = new ArrayList();
    TawPartDAO dao = new TawPartDAO(ds);
    String condition = " where serialno = '" + WellPart + "'";
    check = dao.getPartList(condition, 0, 15);
    if (check.size() > 0) {
      flag = true;
    }
    return flag;
  }

  //更改备件统计次数
  public void updateNum(String orderType, String SumId) {
    TawSparepartDAO dao = new TawSparepartDAO(ds);
    String id[] = SumId.split(",");
    for (int i = 0; i < id.length; i++) {
      if (orderType.equals("6")) {
        int LoanNum = dao.getLoanNum(id[i]) + 1;
        String sql = "set loannum = " + LoanNum + " where id = " + id[i];
        dao.updateSparePart(sql);
      }
      if (orderType.equals("8")) {
        int RepairNum = dao.getRepairNum(id[i]) + 1;
        String sql = "set repairnum = " + RepairNum + " where id = " + id[i]; ;
        dao.updateSparePart(sql);
      }
    }
  }
  /*得到getPositionList的值*/  
  public List getPositionList(String storage) {
	    TawSparepartDAO dao = new TawSparepartDAO(ds);
	    List list=new ArrayList();
	    try {
			list=dao.getPositionList(storage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return list;  
  }
  /*得到getobjTypeList的值*/  
  public List getobjTypeList(String storage)  {
	    TawSparepartDAO dao = new TawSparepartDAO(ds);
	    List list=new ArrayList();
	    try {
			list=dao.getobjTypeList(storage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return list; 
}  

}
