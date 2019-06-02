/*
 * Created on 2008-1-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.test;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Test extends ConsoleTestCase{
	public void TestInsertRole(){
		try{
	    	TawSystemUserDao dao = (TawSystemUserDao)getBean("tawSystemUserDao");
//	    	TawSystemUser user = dao.getUserByuserid("zhangwei");

	    	for(int i=0;i<100;i++){
	    		TawSystemUser u = new TawSystemUser();
	    		u.setDeptid("10101");
	    		u.setUserid("user"+(i+1));
	    		u.setUsername("user"+(i+1));
	    		u.setPassword("1111");
	    		dao.saveTawSystemUser(u);
	    		
	    		TawSystemUserRefRoleDao urdao = (TawSystemUserRefRoleDao)getBean("tawSystemUserRefRoleDao");
	    		TawSystemUserRefRole ur1 = new TawSystemUserRefRole();
	    		ur1.setUserid("user"+(i+1));
	    		ur1.setSubRoleid("8a15978f1636f049011637137ff5000d");
	    		urdao.saveTawSystemUserRefRole(ur1);
	    		
	    		TawSystemUserRefRole ur2 = new TawSystemUserRefRole();
	    		ur2.setUserid("user"+(i+1));
	    		ur2.setSubRoleid("8a15978f1636f049011636f8d2100005");
	    		urdao.saveTawSystemUserRefRole(ur2);
	    		
	    		TawSystemUserRefRole ur3 = new TawSystemUserRefRole();
	    		ur3.setUserid("user"+(i+1));
	    		ur3.setSubRoleid("8a15978f1636f049011636f87b070004");
	    		urdao.saveTawSystemUserRefRole(ur3);
	    		
	    		TawSystemUserRefRole ur4 = new TawSystemUserRefRole();
	    		ur4.setUserid("user"+(i+1));
	    		ur4.setSubRoleid("8a8092ea16a4aa940116ae41be7d00b6");
	    		urdao.saveTawSystemUserRefRole(ur4);

	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	public void TestSplit(){
		String str = "test中dd文dsaf中男大3443n中国43中国人";
		System.out.println(str.substring(0,4));
		System.out.println(str.substring(4,str.length()));
		System.out.println(str.substring(5,str.length()));
	}
	
	public void test(){
		ITawSystemUserRefRoleManager userMgr = 
			(ITawSystemUserRefRoleManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");    
		List userList = userMgr.getUserbyroleid("8aa082d81bed4aa6011bed85a395003d");
		Map map = userMgr.getGroupUserbyroleid("8aa082d81bed4aa6011bed85a395003d");
		JSONArray userArray = new JSONArray();
		
		int userListSize = userList.size();
		for(int j=0;j<userListSize;j++){
    		TawSystemUser user = (TawSystemUser)userList.get(j);
    		JSONObject useritem = new JSONObject();
    		String userId = user.getUserid();
    		String userName = user.getUsername();
    		useritem.put("id",userId);
    		useritem.put("name",userName);
    		useritem.put("text",userName);
    		
    		useritem.put("groupType",map.get(userId));
    		Object object=map.get(userId);
    		String groupType=StaticMethod.nullObject2String(map.get(userId),RoleConstants.groupType_common);
    		if(groupType.equals(RoleConstants.groupType_leader)){
    		  
    		}
    		
    		useritem.put("iconCls","user");
    		useritem.put("nodeType","user");
    		useritem.put("leaf",true);
    		 
    		userArray.put(useritem);
    	}
	}
}
