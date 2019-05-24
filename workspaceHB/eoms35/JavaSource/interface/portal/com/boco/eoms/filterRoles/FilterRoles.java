package com.boco.eoms.filterRoles;

import java.util.Hashtable;
import java.util.List;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.role.util.RoleManage;
import com.boco.eoms.util.InterfaceUtil;

public class FilterRoles {
	 public String isAlive() {
		    //BocoLog.trace(this, 35, "收到握手信号");
		    System.out.println("收到握手信号");
		    String isAliveResult = "";

		    return isAliveResult;
		  }
	public  String subRoles(String roleId, String deptId,
						String type1, String type2,String type3,String type4) throws Exception {
		Hashtable hash = new Hashtable();
		String subRoles="";
		StaticMethod staticMethod =new StaticMethod();
	
		try {
		if(!"-1".equals(deptId)&&deptId!=null && !"0".equals(deptId)){
			hash.put("deptId", deptId);	
		}	
		
		if(!"-1".equals(type1)){
					hash.put("class1", staticMethod.null2String(type1));
		}
		if("0".equals(type1)){
			hash.put("class1","");	
		}
		if(!"-1".equals(type2)){
			hash.put("class2", staticMethod.null2String(type2));	
		}
		if("0".equals(type2)){
			hash.put("class2","");	
		}
		if(!"-1".equals(type3)){
			hash.put("class3", staticMethod.null2String(type3));	
		}
		if("0".equals(type3)){
			hash.put("class3","");	
		}
		if(!"-1".equals(type4)){
			hash.put("class4", staticMethod.null2String(type4));	
		}
		if("0".equals(type4)){
			hash.put("class","");	
		}
		InterfaceUtil interfaceUtil =new InterfaceUtil();
		subRoles=interfaceUtil.getFilterRoles(RoleManage.getInstance().getSubRoles(roleId, hash));
		}
		catch (Exception e) {
			e.printStackTrace();
			subRoles="-1";
		}
		
		return subRoles;
	}


}
