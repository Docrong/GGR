package com.boco.eoms.km.expert.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.dao.KmExpertGroupDao;
import com.boco.eoms.km.expert.mgr.KmExpertGroupMgr;
import com.boco.eoms.km.expert.model.KmExpertGroup;
import com.boco.eoms.km.expert.model.KmExpertGroupUser;

public class KmExpertGroupMgrImpl implements KmExpertGroupMgr {

	private KmExpertGroupDao kmExpertGroupDao;

	public KmExpertGroupDao getKmExpertGroupDao() {
		return kmExpertGroupDao;
	}

	public KmExpertGroup getKmExpertGroup(String id) {
		return kmExpertGroupDao.getKmExpertGroup(id);
	}
	
	public KmExpertGroup getKmExpertGroupAndGroupMember(String id) {
		//查询基本信息
		KmExpertGroup kmExpertGroup = kmExpertGroupDao.getKmExpertGroup(id);

		// 查询所有组员信息
		List groupMember = kmExpertGroupDao.getKmExpertGroupUsersByGroupId(id);
		int size = groupMember.size();
		boolean first = true;
		for(int i=0; i<size; i++){
			KmExpertGroupUser kmExpertGroupUser = (KmExpertGroupUser)groupMember.get(i);
			String member = kmExpertGroupUser.getUserId(); //组员
			if(kmExpertGroupUser.getIsLeader().equals("0")){
				if(first){
					kmExpertGroup.setGroupMember(member);
					first=false;
				}
				else{
					kmExpertGroup.setGroupMember(kmExpertGroup.getGroupMember() + ',' + member);
				}				
			}
		}
		return kmExpertGroup;
	}

	public List getKmExpertGroupUsersByGroupId(String groupId) {
		return kmExpertGroupDao.getKmExpertGroupUsersByGroupId(groupId);
	}

	public void setKmExpertGroupDao(KmExpertGroupDao kmExpertGroupDao) {
		this.kmExpertGroupDao = kmExpertGroupDao;
	}

	public void saveKmExpertGroup(KmExpertGroup kmExpertGroup) {
		kmExpertGroupDao.saveKmExpertGroup(kmExpertGroup);
	}

	public Map getKmExpertGroups(Integer curPage, Integer pageSize,
			String whereStr) {
		return kmExpertGroupDao.getKmExpertGroups(curPage, pageSize, whereStr);
	}

	public void removeKmExpertGroup(String id) {
		kmExpertGroupDao.removeKmExpertGroup(id);		
	}

}
