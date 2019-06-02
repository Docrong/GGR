/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.datum.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.datum.dao.ITawBureaudataBasicDAO;
import com.boco.eoms.datum.dao.ITawBureaudataHlrDAO;
import com.boco.eoms.datum.model.TawBureaudataHlr;
import com.boco.eoms.datum.service.ITawBureaudataBasicDAOManager;
import com.boco.eoms.datum.service.ITawBureaudataHlrDAOManager;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawBureaudataHlrDAOManagerImpl  implements ITawBureaudataHlrDAOManager {
	
	private ITawBureaudataHlrDAO bureaudataHlrDAO;
	
	

	public ITawBureaudataHlrDAO getbureaudataHlrDAO() {
		return bureaudataHlrDAO;
	}

	public void setbureaudataHlrDAO(ITawBureaudataHlrDAO bureaudataHlrDAO) {
		this.bureaudataHlrDAO = bureaudataHlrDAO;
	}

	public Object getObject(Class clazz, Serializable id) throws HibernateException {
		return bureaudataHlrDAO.getObject(clazz, id);
	}

	public List getObjectsByCondtion(Integer curPage, Integer pageSize, int[] aTotal, Map condition, String queryNumber) throws HibernateException {
		
		String hql = " from TawBureaudataHlr ";
		String countHql = "select count(*) from TawBureaudataHlr ";
		String where = (String)condition.get("where");
		hql = hql + where;
		countHql = countHql + where;
		return bureaudataHlrDAO.getObjectsByCondtion(curPage, pageSize, aTotal, hql, countHql,queryNumber);
	}

	public void removeObject(Class clazz, Serializable id) throws HibernateException {
		bureaudataHlrDAO.removeObject(clazz, id);
		
	}

	public void saveObject(Object o) throws HibernateException {
		bureaudataHlrDAO.saveObject(o);
		
	}
	public List getBureaudatedept()throws HibernateException{
		String hql ="from TawBureaudataHlr where deleted=0 order by hlrname";
		return bureaudataHlrDAO.loadList(hql);
	}
	
	public Map getAllHLRIntoMapKeySignalId() throws HibernateException{
		
		return bureaudataHlrDAO.getAllHLRIntoMapKeySignalId();
	}
	public void saveOrUpdate(Object o)throws HibernateException{
		bureaudataHlrDAO.saveOrUpdate(o);
	}
	
	
	public List getHlrList(Object o) throws HibernateException{
		TawBureaudataHlr obj = (TawBureaudataHlr) o;
		String hql = "from TawBureaudataHlr where  hlrsignalid like '%"+obj.getHlrsignalid()+"%' and  hlrname like '%"+obj.getHlrname()+"%' and  hlrid like '%"+obj.getHlrid()+"%'";
		if(obj.getDeleted()!=null){
			hql+=" and deleted="+obj.getDeleted()+" ";
		}
		return bureaudataHlrDAO.loadList(hql);
		
	}
	
	
	public Object getObject(String id)throws HibernateException{
		String hql = "from TawBureaudataHlr where id='"+id+"'";
		List list  = bureaudataHlrDAO.loadList(hql);
		if(list!=null&&list.size()>0){
			TawBureaudataHlr obj = (TawBureaudataHlr) list.get(0);
			return obj;
		}
		return null;
	}
	
	
	public Object getHlrsignalidObject(String hlrsignalid)throws HibernateException{
		String hql = "from TawBureaudataHlr where hlrsignalid='"+hlrsignalid+"'";
		List list  = bureaudataHlrDAO.loadList(hql);
		if(list!=null&&list.size()>0){
			TawBureaudataHlr obj = (TawBureaudataHlr) list.get(0);
			return obj;
		}
		return null;
	}
}
