
package com.boco.eoms.businessupport.product.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;
import com.boco.eoms.businessupport.product.dao.IGprsSpecialLineDao;
import com.boco.eoms.businessupport.product.dao.IGprsSpecialLineJdbc;
import com.boco.eoms.businessupport.product.service.IGprsSpecialLineManager;

public class GprsSpecialLineManagerImpl extends BaseManager implements IGprsSpecialLineManager {
    private IGprsSpecialLineDao gprsspeciallineDao;
    private IGprsSpecialLineJdbc gprsspeciallineJdbc;
	
	public IGprsSpecialLineDao getGprsspeciallineDao() {
		return gprsspeciallineDao;
	}
	public void setGprsspeciallineDao(IGprsSpecialLineDao gprsspeciallineDao) {
		this.gprsspeciallineDao = gprsspeciallineDao;
	}
	public IGprsSpecialLineJdbc getGprsspeciallineJdbc() {
		return gprsspeciallineJdbc;
	}
	public void setGprsspeciallineJdbc(IGprsSpecialLineJdbc gprsspeciallineJdbc) {
		this.gprsspeciallineJdbc = gprsspeciallineJdbc;
	}
	public List getChildList(String parentId) {
		// TODO Auto-generated method stub
		return gprsspeciallineDao.getChildList(parentId);
	}
	public String getMaxCode() {
		// TODO Auto-generated method stub
		return gprsspeciallineJdbc.getMaxCode();
	}
	public GprsSpecialLine getGprsSpecialLine(String id) {
		// TODO Auto-generated method stub
		return gprsspeciallineDao.getGprsSpecialLine(id);
	}
	public List getGprsSpecialLines() {
		// TODO Auto-generated method stub
		return gprsspeciallineDao.getGprsSpecialLines();
	}
	public Map getGprsSpecialLines(Integer curPage, Integer pageSize) {
		// TODO Auto-generated method stub
		 return gprsspeciallineDao.getGprsSpecialLines(curPage, pageSize);
	}
	public Map getGprsSpecialLines(Integer curPage, Integer pageSize, String whereStr) {
		// TODO Auto-generated method stub
		 return gprsspeciallineDao.getGprsSpecialLines(curPage, pageSize, whereStr);
	}
	public List getGprsSpecialLinesByProCode(String proCode, String moduleName) {
		// TODO Auto-generated method stub
		String hql = " from " + moduleName + " where mainProductCode = '"+"' and templateFlag <> 1";
		return gprsspeciallineDao.getGprsSpecialLinesByHql(hql);
	}
	public List getGprsSpecialLinesByTxtwords(Integer newDeleted, String txtwords, String moduleName) {
		// TODO Auto-generated method stub
		if(txtwords.trim().equalsIgnoreCase("")){
			String hql = " from " + moduleName + " where keyword ='"+"' and deleted = " + newDeleted +"" ;
			return gprsspeciallineDao.getGprsSpecialLinesByHql(hql);
		}else{
			String hql = " from " + moduleName + " where keyword like '%" + txtwords +"%'and deleted = " + newDeleted +"";
			return gprsspeciallineDao.getGprsSpecialLinesByHql(hql);
		}
	}
	public List getGprsSpecialLinesDeleted() {
		// TODO Auto-generated method stub
		return gprsspeciallineDao.getGprsSpecialLinesDeleted();
	}
	public void removeGprsSpecialLine(String id) {
		// TODO Auto-generated method stub
		gprsspeciallineDao.removeGprsSpecialLine(new String(id));
	}
	public void restoreGprsSpecialLine(String id) {
		// TODO Auto-generated method stub
		gprsspeciallineDao.restoreGprsSpecialLine(new String(id));
	}
	public void saveGprsSpecialLine(GprsSpecialLine gprsspecialline) {
		// TODO Auto-generated method stub
		gprsspeciallineDao.saveGprsSpecialLine(gprsspecialline);
	}
	public List getSpecialLines(String id) {
		// TODO Auto-generated method stub
		return gprsspeciallineDao.getSpecialLines(id);
	}
	
	public Map getQueryGprsSpecialLines(final Map queryMap, final Integer curPage, final Integer pageSize,
			final String whereStr){
		return gprsspeciallineDao.getQueryGprsSpecialLines(queryMap, curPage, pageSize, whereStr);
	}
	public GprsSpecialLine getSpecialLineById(String id) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}
	public void saveOrUpdate(GprsSpecialLine gprsspecialline) throws HibernateException{
		this.getGprsspeciallineDao().saveOrUpdate(gprsspecialline);
	}
	/**
	 * 通过A端站点名称和Z端站点名称查找电路
	 * @param siteNameA
	 * @param siteNameZ
	 * @return
	 */
	public GprsSpecialLine getSpecialLinesBySiteName(String siteNameA,String siteNameZ) throws Exception{
		List list = this.getGprsspeciallineDao().getSpecialLinesBySiteName(siteNameA, siteNameZ);
		if(list!=null && list.size()>0)
			return (GprsSpecialLine)list.get(0);
		else
			return null;
	}
	/**
	 * 通过Z端业务设备端口查找电路
	 */
	public GprsSpecialLine getSpecialLineByZPort(String portZBDeviceName,String portZBDevicePort) throws Exception{
		List list = getGprsspeciallineDao().getSpecialLineByZPort(portZBDeviceName,portZBDevicePort);
		if(list!=null&&list.size()>0)
			return (GprsSpecialLine)list.get(0);
		else
			return null;
	}
}

