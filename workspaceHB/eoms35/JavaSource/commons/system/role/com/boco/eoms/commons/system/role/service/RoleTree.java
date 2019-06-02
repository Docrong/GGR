package com.boco.eoms.commons.system.role.service;

import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticObject;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.role.model.TawSystemRole;


public class RoleTree {
  private StaticObject obj = StaticObject.getInstance();

  public RoleTree() {
  }

  // 根据给出的参数，列出以该参数为父节点下面的角色
  public String strRoleTree(int roleId) {
    String strroletree = "";
    String allTree = "";
    boolean flag = false;
    StringBuffer strTree = new StringBuffer();
    int count = 0;
    long iDeptId = 0;
    long iParentId = 0;
    String sDeptName = "";

    if (flag) {
      strroletree = allTree;
    } else {
      if (roleId < 0) {
        roleId = 0;
      }
      try {
    	  ITawSystemRoleManager mgr = (ITawSystemRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemRoleManager");
        List roleList = mgr.getChildrenByRoleId(roleId);
        for (Iterator it = roleList.iterator(); it.hasNext();) {
        	TawSystemRole orgRole = (TawSystemRole) it.next();
          iDeptId = orgRole.getRoleId();
          iParentId = orgRole.getParentId();
          sDeptName = StaticMethod.null2String(orgRole
              .getRoleName());
          strTree.append("Tree[" + count + "]=\"" + iDeptId + "|"
              + iParentId + "|" + sDeptName + "|#" + "\";");
          count++;
        }
        strroletree = strTree.toString();
        if ((roleId == StaticVariable.ProvinceID) && (!flag))
          obj.putObject("ROLETREE", strroletree);
      } catch (Exception e) {
        BocoLog.error(this, "错误:" + e.getMessage());
      }
    }
    return strroletree;
  }

}
