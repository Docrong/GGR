package com.boco.eoms.common.tree;

import java.sql.*;
import java.util.ArrayList;
import java.io.*;

import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticObject;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;

public class WKTree {

  ConnectionPool pool = ConnectionPool.getInstance();
  Connection con = null;
  private StaticObject obj = StaticObject.getInstance();

  public WKTree() {
  }

  //根据给出的参数，列出以该参数为父节点下面的部门
  public String strWKTree(int parent_dept) {

    String strwktree = "";
    String allTree = "";
    boolean flag = false;
 
      try {
        strwktree = treeNew(parent_dept, StaticVariable.UNDELETED);

        if ( (parent_dept == StaticVariable.ProvinceID) && (!flag))
          obj.putObject("WKTREE", strwktree);
      }
      catch (Exception e) {
        BocoLog.error(this, 0, "错误", e);
      }
    //}
    return strwktree;
  }
  public String strWKTree(String parent_dept) {

	    String strwktree = "";
	    String allTree = "";
	    boolean flag = false;
	 
	      try {
	        strwktree = treeNew(parent_dept, StaticVariable.UNDELETED);

	        if ( (parent_dept.equals(StaticVariable.ProvinceID+"")) && (!flag))
	          obj.putObject("WKTREE", strwktree);
	      }
	      catch (Exception e) {
	        BocoLog.error(this, 0, "错误", e);
	      }
	    //}
	    return strwktree;
	  }
  private String treeNew(int deptId, int undel) throws Exception {

    PreparedStatement ps = null;
    ResultSet rs = null;
    String id = "";
    StringBuffer strTree = new StringBuffer();
    int count = 0;
    int iDeptId = 0;
    int iParentId = 0;
    String sDeptName = "";
    try {
      /*con = pool.getConnection();
      TawDeptDAO tawDeptDAO = new TawDeptDAO(pool);
      id = tawDeptDAO.getId(deptId);

      String sql = "SELECT dept_id,parent_dept,dept_name,id FROM taw_dept " +
          "WHERE deleted=" + undel + " and id like \'" + id + "%\' order by id";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();

      while (rs.next()) {
        iDeptId = rs.getInt(1);
        iParentId = rs.getInt(2);
        sDeptName = StaticMethod.dbNull2String(rs.getString(3));
        strTree.append("Tree[" + count + "]=\"" + iDeptId + "|" +
                       iParentId + "|" + sDeptName + "|#" + "\";");
        count++;
      }
*/
    	//EOMS35中取子部门 begin“”
    	 
    	TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
    	ArrayList deptlist =(ArrayList)deptbo.getALLSondept(String.valueOf(deptId), String.valueOf(undel));
    	if( deptlist != null && deptlist.size()>0){
    		for( int i=0;i<deptlist.size();i++){
    		TawSystemDept sysdept = (TawSystemDept)deptlist.get(i);
    		strTree.append("Tree[" + count + "]=\"" + sysdept.getDeptId() + "|" +
    				sysdept.getParentDeptid() + "|" + sysdept.getDeptName() + "|#" + "\";");
    	 
    		count++;
    		}
    	}
    	//EOMS35取子部门结束
    }
    catch (Exception e) {
      BocoLog.error(this, 0, "取部门树图错误", e);
    }
 /*   finally {
      try {
        if (ps != null)
          ps.close();
        if (rs != null)
          rs.close();
        ps = null;
        rs = null;
        if (con != null)
          con.close();
      }
      catch (SQLException e) {
        BocoLog.error(this, 0, "错误", e);
      }
    }*/

    return strTree.toString();
  }
  /**
	 * 查询所有的子部门 值班用
	 * 
	 * @param deptid
	 * @param delid
	 * @return
	 */
  private String treeNew(String deptId, int undel) throws Exception {
 
	    StringBuffer strTree = new StringBuffer();
	    int count = 0;
	    
	    try {
	      
	    	TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
	    	ArrayList deptlist =(ArrayList)deptbo.getALLdept(String.valueOf(deptId), String.valueOf(undel));
	    	if( deptlist != null && deptlist.size()>0){
	    		for( int i=0;i<deptlist.size();i++){
	    		TawSystemDept sysdept = (TawSystemDept)deptlist.get(i);
	    		strTree.append("Tree[" + count + "]=\"" + sysdept.getDeptId() + "|" +
	    				sysdept.getParentDeptid() + "|" + sysdept.getDeptName() + "|#" + "\";");
	    	 
	    		count++;
	    		}
	    	}
	    	//EOMS35取子部门结束
	    }
	    catch (Exception e) {
	      BocoLog.error(this, 0, "取部门树图错误", e);
	    }
 

	    return strTree.toString();
	  }

}
