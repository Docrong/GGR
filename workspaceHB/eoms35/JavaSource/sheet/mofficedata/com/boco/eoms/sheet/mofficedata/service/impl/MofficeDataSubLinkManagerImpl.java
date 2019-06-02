package com.boco.eoms.sheet.mofficedata.service.impl;

import java.util.List;

import com.boco.eoms.sheet.mofficedata.dao.IMofficeDataSubLinkDAO;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataSubLink;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataSubLinkManager;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 * 
 * @author weichao
 * @version 3.5
 * 
 */

public class MofficeDataSubLinkManagerImpl implements IMofficeDataSubLinkManager {

	private IMofficeDataSubLinkDAO subLinkDao;
	private MofficeDataSubLink subLinkObject;
	
	public MofficeDataSubLink getSubLinkObject() {
		return subLinkObject;
	}

	public void setSubLinkObject(MofficeDataSubLink subLinkObject) {
		this.subLinkObject = subLinkObject;
	}

	public IMofficeDataSubLinkDAO getSubLinkDao() {
		return subLinkDao;
	}

	public void setSubLinkDao(IMofficeDataSubLinkDAO subLinkDao) {
		this.subLinkDao = subLinkDao;
	}

	public void saveOrUpdate(MofficeDataSubLink obj) throws Exception {
		subLinkDao.saveOrUpdate(obj);

	}

	/**
	 * 更新其他subLink，使之与link表的记录关联起来
	 */
	public void updateOthers(String liId, String preLinkId) {
		String hql = "from MofficeDataSubLink where preLinkId='"+preLinkId+"'";
		List list = subLinkDao.getSubLinksByHql(hql);
		if(null!=list){
			for(int i=0;i<list.size();i++){
				MofficeDataSubLink sublink = (MofficeDataSubLink)list.get(i);
				sublink.setParentLinkId(liId);
				subLinkDao.saveOrUpdate(sublink);
			}
		}
	}

	public List getSubLinks(String parentLinkId) throws Exception {
		String hql = "from MofficeDataSubLink where parentLinkId='"+parentLinkId+"' order by operateTime asc";
		return subLinkDao.getSubLinksByHql(hql);
	}

}