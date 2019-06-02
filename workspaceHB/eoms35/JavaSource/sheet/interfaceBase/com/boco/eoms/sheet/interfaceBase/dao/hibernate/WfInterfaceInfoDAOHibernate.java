package com.boco.eoms.sheet.interfaceBase.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.model.WfInterfaceInfo;
import com.boco.eoms.sheet.interfaceBase.dao.IWfInterfaceInfoDAO;

/**
 * @author
 * 
 */
public class WfInterfaceInfoDAOHibernate extends BaseDaoHibernate implements
		IWfInterfaceInfoDAO {

	public WfInterfaceInfo loadWfInterfaceInfo(String id) throws Exception {
		WfInterfaceInfo info = (WfInterfaceInfo) getHibernateTemplate().get(
				WfInterfaceInfo.class, id);
		return info;
	}

	/**
	 * 信息发送完后更改表中发送状态
	 * 
	 * @param id
	 * @param isSended
	 * @throws HibernateException
	 */
	public void updateSendStatusWfInterfaceInfo(String id, String isSended)
			throws HibernateException {
		WfInterfaceInfo info = (WfInterfaceInfo) getHibernateTemplate().get(
				WfInterfaceInfo.class, id);
		info.setIsSended(isSended);
		info.setSendTime(new Date());
		getHibernateTemplate().save(info);
	}

	/**
	 * 获取所有未成功发送的信息
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public List getAllNotSendInfo() throws HibernateException {
		String sql = "from WfInterfaceInfo as info where info.isSended <> '"+InterfaceConstants.IS_SENDED+"'";
		List list = getHibernateTemplate().find(sql);
		return list;
	}

	public void saveOrUpdateWfInterfaceInfo(WfInterfaceInfo info)
			throws HibernateException { 
		getHibernateTemplate().saveOrUpdate(info);
	}

	public void updateInfoForSend(String sheetKey, String interfaceType, String methodType) throws HibernateException {
		String sql = "from WfInterfaceInfo as info where info.sheetKey='"+sheetKey+"' and info.isSended = '"+InterfaceConstants.IS_UNREADY+"' and info.interfaceType='"+interfaceType+"' and info.methodType='"+methodType+"'";
		List list = getHibernateTemplate().find(sql);
		if(list.size()>0){
			WfInterfaceInfo info = (WfInterfaceInfo)list.get(0);
			info.setIsSended(InterfaceConstants.UN_SEND);
			this.saveOrUpdateWfInterfaceInfo(info);
		}
	}

}
