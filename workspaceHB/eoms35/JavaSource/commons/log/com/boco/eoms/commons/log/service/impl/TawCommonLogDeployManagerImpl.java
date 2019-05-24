package com.boco.eoms.commons.log.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.log.model.TawCommonLogDeploy;
import com.boco.eoms.commons.log.dao.TawCommonLogDeployDao;

import com.boco.eoms.commons.log.service.TawCommonLogDeployManager;

public class TawCommonLogDeployManagerImpl extends BaseManager implements
		TawCommonLogDeployManager {
	private TawCommonLogDeployDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawCommonLogDeployDao(TawCommonLogDeployDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.log.service.TawCommonLogDeployManager#getTawCommonLogDeploys(com.boco.eoms.commons.log.model.TawCommonLogDeploy)
	 */
	public List getTawCommonLogDeploys(
			final TawCommonLogDeploy tawCommonLogDeploy) {
		return dao.getTawCommonLogDeploys(tawCommonLogDeploy);
	}

	/**
	 * @see com.boco.eoms.commons.log.service.TawCommonLogDeployManager#getTawCommonLogDeploy(String
	 *      id)
	 */
	public TawCommonLogDeploy getTawCommonLogDeploy(final String id) {
		return dao.getTawCommonLogDeploy(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.log.service.TawCommonLogDeployManager#saveTawCommonLogDeploy(TawCommonLogDeploy
	 *      tawCommonLogDeploy)
	 */
	public void saveTawCommonLogDeploy(TawCommonLogDeploy tawCommonLogDeploy) {
		dao.saveTawCommonLogDeploy(tawCommonLogDeploy);
	}

	/**
	 * @see com.boco.eoms.commons.log.service.TawCommonLogDeployManager#removeTawCommonLogDeploy(String
	 *      id)
	 */
	public void removeTawCommonLogDeploy(final String id) {
		dao.removeTawCommonLogDeploy(new String(id));
	}

	/**
	 * 根据级别ID查询信息
	 */
	public TawCommonLogDeploy getDeployByOperID(String operlevelID) {
		// TODO Auto-generated method stub
		TawCommonLogDeploy logbocodeploy;

		logbocodeploy = dao.getDeployByOperID(operlevelID);
		return logbocodeploy;
	}
	
	/**
	 * 查询某用户的配置
	 * @param userid
	 * @param modelid
	 * @return
	 */
	public List getDeoloyByuseridandmodelid(String userid,String modelid){
		
		List list = new ArrayList();
		list = dao.getDeoloyByuseridandmodelid(userid, modelid);
		return list;
	}
	/**
	 * 查询某用户的配置
	 * @param userid
	 * @param modelid
	 * @return
	 */
	public List getDeoloyByuseridormodelid(String userid,String modelid){
		
		List list = new ArrayList();
		list = dao.getDeoloyByuseridormodelid(userid, modelid);
		return list;
	}
}
