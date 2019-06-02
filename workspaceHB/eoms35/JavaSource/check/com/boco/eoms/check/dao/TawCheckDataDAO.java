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
import com.boco.eoms.check.model.TawCheckTransData;
import com.boco.eoms.common.dao.HibernateDAO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.hibernate.HibernateUtil;

public class TawCheckDataDAO extends HibernateDAO {

	public void save(Object tawCheckTransData) {
		try {
			Session s = HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
			s.save(tawCheckTransData);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public TawCheckTransData loadTarger(String id)
	{
		TawCheckTransData tawCheckTransData=new TawCheckTransData();
		try
		{
			Session s=HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
			tawCheckTransData=(TawCheckTransData)s.load(TawCheckTransData.class, id);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return tawCheckTransData;
	}
	public void delTransData(TawCheckTransData tawCheckTransData)
	{
		try
		{
			Session s=HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
			s.delete(tawCheckTransData);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void delScoreData(String scoreYear,String scoreMonth,String className,String sheetName){
		try
		{
			String hsql = "from "+className+" as tawcheckdata where " +
	        " tawcheckdata.score_year = '" + scoreYear + "' and tawcheckdata.score_month='"+scoreMonth+"'";
			if(sheetName!=null&&!sheetName.equals("")){
				hsql+=" and modelflag='"+sheetName+"'";
			}
			delete(hsql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
