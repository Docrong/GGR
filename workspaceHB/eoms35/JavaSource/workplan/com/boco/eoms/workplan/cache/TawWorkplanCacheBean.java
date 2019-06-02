package com.boco.eoms.workplan.cache;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.workplan.mgr.ITawwpNetMgr;
import com.boco.eoms.workplan.model.TawwpNet;
import com.boco.eoms.workplan.util.TawwpException;




public class TawWorkplanCacheBean {

	private final Logger logger = Logger.getLogger(this.getClass());

	public TawWorkplanCacheBean() {
		super();
	}

	/**
	 * 获取人员的信息
	 * 
	 * @return
	 */
	public Map getWorkplanUser() {
		HashMap HashMap = new HashMap();
		HashMap userMap = new HashMap();
		HashMap deptMap = new HashMap();
		HashMap dictMap = new HashMap();
		HashMap netMap = new HashMap();
		//获取用户name
		ITawSystemUserManager ITawSystemUserManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		List userList = ITawSystemUserManager.getNoDelUser();
		for (int i = 0 ;i<userList.size(); i ++){
			TawSystemUser tawSystemUser = (TawSystemUser)userList.get(i);
			userMap.put(tawSystemUser.getUserid(),tawSystemUser.getUsername());
		}
		//获取部门name
		ITawSystemDeptManager iTawSystemDeptManager = (ITawSystemDeptManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemDeptManager");
		List deptList = iTawSystemDeptManager.getworkplanDeptnames();
		for (int j = 0 ;j<deptList.size(); j ++){
			TawSystemDept tawSystemDept = (TawSystemDept)deptList.get(j);
			deptMap.put(tawSystemDept.getDeptId(),tawSystemDept.getDeptName());
		}
		//获取字典name
		ITawSystemDictTypeManager iTawSystemDictTypeManager = (ITawSystemDictTypeManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemDictTypeManager");
		List dictList = iTawSystemDictTypeManager.getworkplanDictByDictId();
		for (int a = 0 ;a<dictList.size(); a ++){
			TawSystemDictType TawSystemDictType = (TawSystemDictType)dictList.get(a);
			dictMap.put(TawSystemDictType.getDictId(),TawSystemDictType.getDictName());
		}
		//获取网元name
		ITawwpNetMgr iTawwpNetMgr = (ITawwpNetMgr) ApplicationContextHolder
		.getInstance().getBean("tawwpNetMgr");
		try {
			List netList = iTawwpNetMgr.searchWorkplanNet();
			for (int b = 0 ;b<netList.size(); b ++){
				TawwpNet TawwpNet = (TawwpNet)netList.get(b);
				netMap.put(TawwpNet.getId(),TawwpNet.getName());
			}
		} catch (TawwpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap.put("userMap",userMap);
		HashMap.put("deptMap",deptMap);
		HashMap.put("dictMap",dictMap);
		HashMap.put("netMap",netMap);
		return HashMap;
	}

	/**
	 * 添加时间，根据applicationContext-ehcache.xml配置，调用addDate时，getDate()缓冲更新
	 * 
	 */
	public void addWorkplanCache() {
		logger.debug("addDutyCache method is processed");
	}
	
}
