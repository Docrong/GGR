/**
 * @see
 * <p>锟斤拷锟斤拷锟斤拷锟斤拷锟阶帮拷锟斤拷锟?锟斤拷锟揭碉拷锟斤拷呒锟斤拷唷?/p>
 * <p>使锟矫撅拷锟斤拷锟斤拷锟斤拷实锟斤拷锟斤拷啵伙拷锟酵拷锟绞碉拷锟斤拷锟斤拷亩锟斤拷螅锟斤拷锟斤拷锟接︼拷锟斤拷锟斤拷锟?/p>
 * @author 锟斤拷强
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

import com.boco.eoms.common.bo.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.duty.controller.TawApparatusroomForm;
import com.boco.eoms.duty.dao.TawApparatusroomDAO;
import com.boco.eoms.duty.model.TawApparatusroom;



public class TawApparatusroomBO extends BO{
   //zhengheceshi
  /**
   * @see   锟斤拷锟届方锟斤拷
   * @param ds  DataSource 锟斤拷锟皆达拷锟斤拷锟絊truts锟斤拷锟斤拷锟斤拷峁╋拷锟?
   */
 public TawApparatusroomBO(com.boco.eoms.db.util.ConnectionPool  ds) {
   super(ds);
 }

 /**
  * @see   锟斤拷莶锟斤拷锟斤拷询锟斤拷息锟斤拷锟斤拷锟截凤拷锟斤拷锟斤拷锟侥伙拷锟斤拷息锟斤拷锟较ｏ拷
  * @param offset  int  锟接革拷锟斤拷锟铰硷拷锟斤拷锟绞?
  * @param length  int  每页锟侥硷拷录锟斤拷锟斤拷
  * @param tawAPMform  TawApparatusroomForm  锟斤拷锟窖拷锟较拷锟紽orm
  * @return List锟斤拷锟斤拷值锟斤拷
  * @throws SQLException
  */

 public List getlist(int offset,int length,String con) throws SQLException {
   TawApparatusroom  taw = null;
   List tawAPMs = null;
   TawApparatusroomDAO tawAPMDAO = null;
//   TawDeptDAO tawDeptDAO = null;
//   TawRmUserDAO tawRUDAO = null;

   try{
     tawAPMDAO = new TawApparatusroomDAO(ds);
     tawAPMs =
      tawAPMDAO.selectByCondition(offset,length,con,StaticVariable.UNDELETED);

//     tawDeptDAO  = new TawDeptDAO(ds);
//     tawRUDAO  = new TawRmUserDAO(ds);
//     for(int i=0;i<tawAPMs.size();i++){
//       taw =(TawApparatusroom)tawAPMs.get(i);
//       int j =taw.getDeptId();
//       if(j > 0){
//         taw.setDeptName(tawDeptDAO.getDeptName(j, StaticVariable.UNDELETED));
//       }
//       String userId = taw.getManager();
//       if(!userId.equals("")){
//         taw.setManName(tawRUDAO.getUserName(userId));
//       }
//       String userIdT = taw.getTempManager();
//       if(!userIdT.equals("")){
//         taw.setTempName(tawRUDAO.getUserName(userIdT));
//       }
//     }
   }
   catch(SQLException e){
   }
   finally{
      taw = null;
//      tawAPMDAO = null;
//      tawDeptDAO = null;
//      tawRUDAO = null;
   }

   return tawAPMs;
 }


 /**
  * @see   锟斤拷锟斤拷锟斤拷锟窖拷锟较⑵达拷锟絊QL锟斤拷锟斤拷械锟?WHERE ..."锟斤拷询锟斤拷锟斤拷锟?
  * @param tawAPM  TawApparatusroomForm  锟斤拷锟窖拷锟较拷锟紽orm
  * @return String锟斤拷锟斤拷值锟斤拷
  * @throws SQLException
  */

 public String selectByConditions(TawApparatusroomForm tawAPM,String domIds)
     throws SQLException{

   String condition = "";
   Vector vTemp = new Vector();
   String roomName = tawAPM.getRoomName();
   String manager = tawAPM.getManager();
   int dept_id = tawAPM.getDeptId();
   String address = tawAPM.getAddress();

   try{
     condition = "  WHERE  a.deleted=" + StaticVariable.UNDELETED;

     if (dept_id > 0){
       vTemp.add("  a.dept_id=" + dept_id);
     }

     if (!roomName.trim().equals("")){
       vTemp.add(" a.room_name like  " + "\'%" + roomName + "%\'  ");
     }

     if (!manager.equals("")){
       vTemp.add("  a.manager = " + "\'" + manager + "\'  ");
     }

     if (!address.trim().equals("")){
       vTemp.add("  a.address like  " + "\'%" + address + "%\'  ");
     }

     int j = vTemp.size();
     if (j > 0) {
       condition += " and ";
       for (int i = 0; i < j; i++) {
         condition += vTemp.get(i).toString() + "   and ";
       }
       condition = condition.substring(0, (condition.length() - 5));
     }
     else if (!domIds.equals("")) {
       condition += " and  a.dept_id in (" + domIds + ")  ";
     }
   }
   catch(Exception e){
   }
   finally{
     vTemp = null;
     roomName = null;
     manager = null;
     address = null;
   }

   return condition;
 }

// public TawApparatusroom getRetrieve(int id) throws SQLException {
//
//   TawApparatusroomDAO tawAprDAO = new TawApparatusroomDAO(ds);
//   TawApparatusroom tawApr = new TawApparatusroom();
//   tawApr = tawAprDAO.retrieve(id);
//
//   TawRmUserDAO tawRUDAO = new TawRmUserDAO(ds);
//   TawDeptDAO taw = new TawDeptDAO(ds);
//
//   int deptid = tawApr.getDeptId();
//   if(deptid > 0){
//     tawApr.setDeptName(taw.getDeptName(deptid, StaticVariable.UNDELETED));
//   }
//
//   String userId = StaticMethod.null2String(tawApr.getManager());
//   String tep = StaticMethod.null2String(tawApr.getTempManager());
//
//   tawApr.setManager(StaticMethod.null2String(tawRUDAO.getUserName(userId)));
//   tawApr.setTempManager(StaticMethod.null2String(tawRUDAO.getUserName(tep)));
//
//   return tawApr;
// }

// public TawApparatusroom retrieve(int id) throws SQLException {
//
//   TawApparatusroomDAO tawAprDAO = new TawApparatusroomDAO(ds);
//   TawApparatusroom tawApr = new TawApparatusroom();
//   tawApr = tawAprDAO.retrieve(id);
//
//   TawRmUserDAO tawRUDAO = new TawRmUserDAO(ds);
//   TawDeptDAO taw = new TawDeptDAO(ds);
//
//   int deptid = tawApr.getDeptId();
//   if(deptid > 0){
//     tawApr.setDeptName(taw.getDeptName(deptid, StaticVariable.UNDELETED));
//   }
//
//
//   return tawApr;
// }

// public List getChildDeptSel(int deptId) throws SQLException{
//   List list = new ArrayList();
//   TawDeptDAO tawDeptDAO = new TawDeptDAO(ds);
//   String ids = tawDeptDAO.getChildDept(deptId,StaticVariable.UNDELETED);
//   if(ids.equals("")){
//     ids = StaticVariable.defaultnull;
//   }
//
//   TawApparatusroomDAO tawAppDAO = new TawApparatusroomDAO(ds);
//   list = tawAppDAO.getRoomNameSelect(ids,StaticVariable.UNDELETED);
//
//   return list;
// }

// public String getChildDeptSelString(int deptId) throws SQLException{
//   String list = "";
//   TawDeptDAO tawDeptDAO = new TawDeptDAO(ds);
//   String ids = tawDeptDAO.getChildDept(deptId,StaticVariable.UNDELETED);
//   if(ids.equals("")){
//     ids = StaticVariable.defaultnull;
//   }
//
//   TawApparatusroomDAO tawAppDAO = new TawApparatusroomDAO(ds);
//   list = tawAppDAO.getRoomNameSelString(ids,StaticVariable.UNDELETED);
//
//   return list;
// }

}
