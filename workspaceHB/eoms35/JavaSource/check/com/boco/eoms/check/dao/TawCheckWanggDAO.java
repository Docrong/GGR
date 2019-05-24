package com.boco.eoms.check.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;


import com.boco.eoms.check.model.TawCheckGSMDATA;

import com.boco.eoms.common.dao.HibernateDAO;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.workplan.model.TawwpNet;

public class TawCheckWanggDAO extends HibernateDAO {

	/*public void save(TawCheckGSMDATA TawCheckGSMDATA) {
		try {
			Session s = HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
			s.save(TawCheckGSMDATA);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	public void saveData(TawCheckGSMDATA TawCheckGSMDATA) throws Exception {
		this.save(TawCheckGSMDATA);
	}
	public List getTransDataList(String sql) {
		List list = new ArrayList();
		try {
			Session s = HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
			Query query = s.createQuery(sql);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List getList(String sql, int[] pagePra) {
		List list = new ArrayList();
		try {
			Session s = HibernateUtil.currentSession();
			if (pagePra[2] <= 0) {
				pagePra[2] = count(sql);
			}
			HibernateUtil.currentTransaction();
			Query query = s.createQuery(sql);
			query.setFirstResult(pagePra[0]);
			query.setMaxResults(pagePra[1]);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void delTransData(TawCheckGSMDATA TawCheckGSMDATA)
	{
		try
		{
			Session s=HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
			s.delete(TawCheckGSMDATA);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
