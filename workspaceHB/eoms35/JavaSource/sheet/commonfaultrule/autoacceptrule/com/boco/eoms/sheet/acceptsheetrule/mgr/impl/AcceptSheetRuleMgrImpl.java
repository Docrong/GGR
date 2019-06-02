package com.boco.eoms.sheet.acceptsheetrule.mgr.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.util.RoleManage;
import com.boco.eoms.commons.system.role.util.RoleMapSchema;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.sheet.acceptsheetrule.model.AcceptSheetRule;
import com.boco.eoms.sheet.acceptsheetrule.mgr.AcceptSheetRuleMgr;
import com.boco.eoms.sheet.acceptsheetrule.mgr.IAutoAcceptGetUserIds;
import com.boco.eoms.sheet.acceptsheetrule.dao.AcceptSheetRuleDao;
import com.boco.eoms.sheet.acceptsheetrule.dao.hibernate.AcceptSheetRuleDaoHibernate;
import com.boco.eoms.sheet.commonfault.util.InterfaceAutoDistribute;

/**
 * <p>
 * Title:自动接单规则配置
 * </p>
 * <p>
 * Description:自动接单规则配置
 * </p>
 * <p>
 * Wed Apr 22 09:19:35 CST 2009
 * </p>
 * 
 * @author 史闯科
 * @version 3.5
 * 
 */
public class AcceptSheetRuleMgrImpl implements AcceptSheetRuleMgr {
 
	private static final String String = null;
	private AcceptSheetRuleDao  acceptSheetRuleDao;
 	
	public AcceptSheetRuleDao getAcceptSheetRuleDao() {
		return this.acceptSheetRuleDao;
	}
 	
	public void setAcceptSheetRuleDao(AcceptSheetRuleDao acceptSheetRuleDao) {
		this.acceptSheetRuleDao = acceptSheetRuleDao;
	}
 	
    public List getAcceptSheetRules() {
    	return acceptSheetRuleDao.getAcceptSheetRules();
    }
    
    public AcceptSheetRule getAcceptSheetRule(final String id) {
    	return acceptSheetRuleDao.getAcceptSheetRule(id);
    }
    
    public void saveAcceptSheetRule(AcceptSheetRule acceptSheetRule) {
    	acceptSheetRuleDao.saveAcceptSheetRule(acceptSheetRule);
    }
    
    public void removeAcceptSheetRule(final String id) {
    	acceptSheetRuleDao.removeAcceptSheetRule(id);
    }
    
    public Map getAcceptSheetRules(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return acceptSheetRuleDao.getAcceptSheetRules(curPage, pageSize, whereStr);
	}

	public String getAccepter(String processTemplateName, HashMap map, String dealPerformer)throws Exception {
		//得到所有符合条件的用户列表
		ITawSystemUserRefRoleManager userRefRoleManager = (ITawSystemUserRefRoleManager) ApplicationContextHolder
		.getInstance().getBean("itawSystemUserRefRoleManager");
        List tmpUserList = userRefRoleManager.getUserIdBySubRoleids(dealPerformer);
        //得到符合条件的值班人员userId列表
        IAutoAcceptGetUserIds autoAceepter = (IAutoAcceptGetUserIds)ApplicationContextHolder
		.getInstance().getBean("getIsUsedUserIdList");
        List userList = autoAceepter.getIsUsedUserIdList(tmpUserList);
        String dealPerformerLeader = "";
        String subRole = "";
        String toDeptId = StaticMethod.nullObject2String(map.get("toDeptId"));
		String mainNetSortOne = StaticMethod.nullObject2String(map.get("mainNetSortOne"));
		String mainNetSortTwo = StaticMethod.nullObject2String(map.get("mainNetSortTwo"));
		String mainNetSortThree = StaticMethod.nullObject2String(map.get("mainNetSortThree"));
		String mainEquipmentFactory = StaticMethod.nullObject2String(map.get("mainEquipmentFactory"));
        if(userList!=null&& userList.size()>0 ){
            //调用自动分配任务功能，得到具体的接单人员的userId
    		//dealPerformerLeader = InterfaceAutoDistribute.getAutoDistributeUser(processTemplateName,userList);
        }else{
        	AcceptSheetRuleDaoHibernate acceptSheetRuleDaoHibernate= new AcceptSheetRuleDaoHibernate();
        	dealPerformerLeader = acceptSheetRuleDaoHibernate.getDealHumanByFilter(toDeptId, mainNetSortOne, mainNetSortTwo, mainNetSortThree, mainEquipmentFactory);
        	if(dealPerformerLeader!=null && dealPerformerLeader.equals("")){
        		dealPerformerLeader = dealPerformer;
        	}
        }
		return dealPerformerLeader;
	}

	public List getUsersByCondition(String bigRole, HashMap dataMap) throws Exception {
		List list = new ArrayList();
		String deptId = StaticMethod.nullObject2String(dataMap.get("deptId"));
		String mainNetSortOne = StaticMethod.nullObject2String(dataMap.get("mainNetSortOne"));
		String mainNetSortTwo = StaticMethod.nullObject2String(dataMap.get("mainNetSortTwo"));
		String mainNetSortThree = StaticMethod.nullObject2String(dataMap.get("mainNetSortThree"));
		String mainEquipmentFactory = StaticMethod.nullObject2String(dataMap.get("mainEquipmentFactory"));
		Hashtable hash = new Hashtable();
		hash.put("deptId", deptId);
		hash.put("class1", mainNetSortOne);			
		hash.put("class2", mainNetSortTwo);			
		hash.put("class3", mainNetSortThree);			
		hash.put("class4", mainEquipmentFactory);
		List subRoleList = RoleManage.getInstance().getSubRoles(
				bigRole, hash);
		ITawSystemUserRefRoleManager userObj = ((ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager"));
		HashMap userMap = new HashMap();
		HashMap userCountmap = new HashMap();
		for(int i = 0;subRoleList!=null && i<subRoleList.size();i++){
			TawSystemSubRole subRole = (TawSystemSubRole)subRoleList.get(i);
			List userList = userObj.getUserbyroleid(subRole.getId());
			for(int j =0;userList!=null && j<userList.size();j++){
				TawSystemUser user = (TawSystemUser) userList.get(j);
				userMap.put(user.getUserid(),user);
			     int count = StaticMethod.nullObject2int(userCountmap.get(user.getUserid()),0);//为空就是0，不为空就是已经出现过
			     count=count+1;
			     userCountmap.put(user.getUserid(),count+"");			
			}
		}
		Iterator it = userCountmap.keySet().iterator();
		while(it.hasNext()){
			String userId = StaticMethod.nullObject2String(it.next());
			int count = StaticMethod.nullObject2int(userCountmap.get(userId),0);
			if(count==subRoleList.size()){
				list.add(userMap.get(userId));
			}
		}
		return list;
	}
}