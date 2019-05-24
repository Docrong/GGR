package com.boco.eoms.check.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.boco.eoms.check.model.TawCheckModel;
import com.boco.eoms.check.model.TawCheckTarger;
import com.boco.eoms.common.dao.HibernateDAO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.hibernate.HibernateUtil;

public class TawCheckModelDAO extends HibernateDAO {

	public void save(TawCheckModel tawCheckModel) {
		try {
			Session s = HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
			s.save(tawCheckModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List getTargerList(String sql) {
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
	public TawCheckModel loadTarger(String id)
	{
		TawCheckModel tawCheckModel=new TawCheckModel();
		try
		{
			Session s=HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
			tawCheckModel=(TawCheckModel)s.load(TawCheckModel.class, id);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return tawCheckModel;
	}
	public void delModel(TawCheckModel tawCheckModel)
	{
		try
		{
			Session s=HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
			s.delete(tawCheckModel);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
