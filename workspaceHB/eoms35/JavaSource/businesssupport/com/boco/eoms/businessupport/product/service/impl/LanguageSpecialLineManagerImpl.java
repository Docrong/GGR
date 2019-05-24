
package com.boco.eoms.businessupport.product.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.businessupport.product.model.LanguageSpecialLine;
import com.boco.eoms.businessupport.product.dao.ILanguageSpecialLineDao;
import com.boco.eoms.businessupport.product.dao.ILanguageSpecialLineJdbc;
import com.boco.eoms.businessupport.product.service.ILanguageSpecialLineManager;

public class LanguageSpecialLineManagerImpl extends BaseManager implements ILanguageSpecialLineManager {
    private ILanguageSpecialLineDao languagespeciallineDao;
    private ILanguageSpecialLineJdbc languagespeciallineJdbc;
	
	public ILanguageSpecialLineDao getLanguagespeciallineDao() {
		return languagespeciallineDao;
	}
	public void setLanguagespeciallineDao(ILanguageSpecialLineDao languagespeciallineDao) {
		this.languagespeciallineDao = languagespeciallineDao;
	}
	public ILanguageSpecialLineJdbc getLanguagespeciallineJdbc() {
		return languagespeciallineJdbc;
	}
	public void setLanguagespeciallineJdbc(ILanguageSpecialLineJdbc languagespeciallineJdbc) {
		this.languagespeciallineJdbc = languagespeciallineJdbc;
	}
	public List getChildList(String parentId) {
		// TODO Auto-generated method stub
		return languagespeciallineDao.getChildList(parentId);
	}
	public String getMaxCode() {
		// TODO Auto-generated method stub
		return languagespeciallineJdbc.getMaxCode();
	}
	public LanguageSpecialLine getLanguageSpecialLine(String id) {
		// TODO Auto-generated method stub
		return languagespeciallineDao.getLanguageSpecialLine(id);
	}
	public List getLanguageSpecialLines() {
		// TODO Auto-generated method stub
		return languagespeciallineDao.getLanguageSpecialLines();
	}
	public Map getLanguageSpecialLines(Integer curPage, Integer pageSize) {
		// TODO Auto-generated method stub
		 return languagespeciallineDao.getLanguageSpecialLines(curPage, pageSize);
	}
	public Map getLanguageSpecialLines(Integer curPage, Integer pageSize, String whereStr) {
		// TODO Auto-generated method stub
		 return languagespeciallineDao.getLanguageSpecialLines(curPage, pageSize, whereStr);
	}
	public List getLanguageSpecialLinesByProCode(String proCode, String moduleName) {
		// TODO Auto-generated method stub
		String hql = " from " + moduleName + " where mainProductCode = '"+"' and templateFlag <> 1";
		return languagespeciallineDao.getLanguageSpecialLinesByHql(hql);
	}
	public List getLanguageSpecialLinesByTxtwords(Integer newDeleted, String txtwords, String moduleName) {
		// TODO Auto-generated method stub
		if(txtwords.trim().equalsIgnoreCase("")){
			String hql = " from " + moduleName + " where keyword ='"+"' and deleted = " + newDeleted +"" ;
			return languagespeciallineDao.getLanguageSpecialLinesByHql(hql);
		}else{
			String hql = " from " + moduleName + " where keyword like '%" + txtwords +"%'and deleted = " + newDeleted +"";
			return languagespeciallineDao.getLanguageSpecialLinesByHql(hql);
		}
	}
	public List getLanguageSpecialLinesDeleted() {
		// TODO Auto-generated method stub
		return languagespeciallineDao.getLanguageSpecialLinesDeleted();
	}
	public void removeLanguageSpecialLine(String id) {
		// TODO Auto-generated method stub
		languagespeciallineDao.removeLanguageSpecialLine(new String(id));
	}
	public void restoreLanguageSpecialLine(String id) {
		// TODO Auto-generated method stub
		languagespeciallineDao.restoreLanguageSpecialLine(new String(id));
	}
	public void saveLanguageSpecialLine(LanguageSpecialLine languagespecialline) {
		// TODO Auto-generated method stub
		languagespeciallineDao.saveLanguageSpecialLine(languagespecialline);
	}
	public List getSpecialLines(String id) {
		// TODO Auto-generated method stub
		return languagespeciallineDao.getSpecialLines(id);
	}
	
	public Map getQueryLanguageSpecialLines(final Map queryMap, final Integer curPage, final Integer pageSize,
			final String whereStr){
		return languagespeciallineDao.getQueryLanguageSpecialLines(queryMap, curPage, pageSize, whereStr);
	}
	public LanguageSpecialLine getSpecialLineById(String id) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}
	public void saveOrUpdate(LanguageSpecialLine languagespecialline) throws HibernateException{
		this.getLanguagespeciallineDao().saveOrUpdate(languagespecialline);
	}
	/**
	 * 通过A端站点名称和Z端站点名称查找电路
	 * @param siteNameA
	 * @param siteNameZ
	 * @return
	 */
	public LanguageSpecialLine getSpecialLinesBySiteName(String siteNameA,String siteNameZ) throws Exception{
		List list = this.getLanguagespeciallineDao().getSpecialLinesBySiteName(siteNameA, siteNameZ);
		if(list!=null && list.size()>0)
			return (LanguageSpecialLine)list.get(0);
		else
			return null;
	}
	/**
	 * 通过Z端业务设备端口查找电路
	 */
	public LanguageSpecialLine getSpecialLineByZPort(String portZBDeviceName,String portZBDevicePort) throws Exception{
		List list = getLanguagespeciallineDao().getSpecialLineByZPort(portZBDeviceName,portZBDevicePort);
		if(list!=null&&list.size()>0)
			return (LanguageSpecialLine)list.get(0);
		else
			return null;
	}
}

