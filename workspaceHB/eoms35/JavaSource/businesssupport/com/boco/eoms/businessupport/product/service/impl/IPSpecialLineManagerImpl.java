
package com.boco.eoms.businessupport.product.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.HibernateException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.sheet.nbproducts.model.NBProducts;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;
import com.boco.eoms.businessupport.product.dao.IIPSpecialLineDao;
import com.boco.eoms.businessupport.product.dao.IIPSpecialLineJdbc;
import com.boco.eoms.businessupport.product.service.IIPSpecialLineManager;

public class IPSpecialLineManagerImpl extends BaseManager implements IIPSpecialLineManager {
    private IIPSpecialLineDao ipspeciallineDao;
    private IIPSpecialLineJdbc ipspeciallineJdbc;
	
	public List getChildList(String parentId) {
		// TODO Auto-generated method stub
		return ipspeciallineDao.getChildList(parentId);
	}
	public String getMaxCode() {
		// TODO Auto-generated method stub
		return ipspeciallineJdbc.getMaxCode();
	}
	public IPSpecialLine getIPSpecialLine(String id) {
		// TODO Auto-generated method stub
		return ipspeciallineDao.getIPSpecialLine(id);
	}
	public List getIPSpecialLines() {
		// TODO Auto-generated method stub
		return ipspeciallineDao.getIPSpecialLines();
	}
	public Map getIPSpecialLines(Integer curPage, Integer pageSize) {
		// TODO Auto-generated method stub
		 return ipspeciallineDao.getIPSpecialLines(curPage, pageSize);
	}
	public Map getIPSpecialLines(Integer curPage, Integer pageSize, String whereStr) {
		// TODO Auto-generated method stub
		 return ipspeciallineDao.getIPSpecialLines(curPage, pageSize, whereStr);
	}
	public List getIPSpecialLinesByProCode(String proCode, String moduleName) {
		// TODO Auto-generated method stub
		String hql = " from " + moduleName + " where mainProductCode = '"+"' and templateFlag <> 1";
		return ipspeciallineDao.getIPSpecialLinesByHql(hql);
	}
	public List getIPSpecialLinesByTxtwords(Integer newDeleted, String txtwords, String moduleName) {
		// TODO Auto-generated method stub
		if(txtwords.trim().equalsIgnoreCase("")){
			String hql = " from " + moduleName + " where keyword ='"+"' and deleted = " + newDeleted +"" ;
			return ipspeciallineDao.getIPSpecialLinesByHql(hql);
		}else{
			String hql = " from " + moduleName + " where keyword like '%" + txtwords +"%'and deleted = " + newDeleted +"";
			return ipspeciallineDao.getIPSpecialLinesByHql(hql);
		}
	}
	public List getIPSpecialLinesDeleted() {
		// TODO Auto-generated method stub
		return ipspeciallineDao.getIPSpecialLinesDeleted();
	}
	public void removeIPSpecialLine(String id) {
		// TODO Auto-generated method stub
		ipspeciallineDao.removeIPSpecialLine(new String(id));
	}
	public void restoreIPSpecialLine(String id) {
		// TODO Auto-generated method stub
		ipspeciallineDao.restoreIPSpecialLine(new String(id));
	}
	public void saveIPSpecialLine(IPSpecialLine ipspecialline) {
		// TODO Auto-generated method stub
		ipspeciallineDao.saveIPSpecialLine(ipspecialline);
	}
	
	public JSONArray xGetChildNodes(String parentId) {
			JSONArray json = new JSONArray();
			List list = new ArrayList();	
			list = this.getChildList(parentId);

			for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
				NBProducts obj = (NBProducts) rowIt.next();
				JSONObject jitem = new JSONObject();
				jitem.put("id", obj.getId());
				//jitem.put("text", obj.getName());
				//jitem.put("name", obj.getName());
				jitem.put("allowChild", true);
				jitem.put("allowDelete", true);
				//if(obj.getLeaf().equals("1")){
					//jitem.put("leaf", true);
				//}
				json.put(jitem);
			}
			return json;
		}	
	
	public List getSpecialLines(String id) {
		// TODO Auto-generated method stub
		return ipspeciallineDao.getSpecialLines(id);
	}
	
	public Map getQueryIPSpecialLines(final Map queryMap, final Integer curPage, final Integer pageSize,
			final String whereStr){
		return ipspeciallineDao.getQueryIPSpecialLines(queryMap, curPage, pageSize, whereStr);
	}
	public IPSpecialLine getSpecialLineById(String id) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}
	public IIPSpecialLineDao getIpspeciallineDao() {
		return ipspeciallineDao;
	}
	public void setIpspeciallineDao(IIPSpecialLineDao ipspeciallineDao) {
		this.ipspeciallineDao = ipspeciallineDao;
	}
	public IIPSpecialLineJdbc getIpspeciallineJdbc() {
		return ipspeciallineJdbc;
	}
	public void setIpspeciallineJdbc(IIPSpecialLineJdbc ipspeciallineJdbc) {
		this.ipspeciallineJdbc = ipspeciallineJdbc;
	}
	public void saveOrUpdate(IPSpecialLine ipspecialline) throws HibernateException{
		this.getIpspeciallineDao().saveOrUpdate(ipspecialline);
	}
	/**
	 * 通过A端站点名称和Z端站点名称查找电路
	 * @param siteNameA
	 * @param siteNameZ
	 * @return
	 */
	public IPSpecialLine getSpecialLinesBySiteName(String siteNameA,String siteNameZ) throws Exception{
		List list = ipspeciallineDao.getSpecialLinesBySiteName(siteNameA, siteNameZ);
		if(list!=null && list.size()>0)
			return (IPSpecialLine)list.get(0);
		else
			return null;
	}
	/**
	 * 通过Z端业务设备端口查找电路
	 */
	public IPSpecialLine getSpecialLineByZPort(String portZBDeviceName,String portZBDevicePort) throws Exception{
		List list = ipspeciallineDao.getSpecialLineByZPort(portZBDeviceName,portZBDevicePort);
		if(list!=null&&list.size()>0)
			return (IPSpecialLine)list.get(0);
		else
			return null;
	}
}

