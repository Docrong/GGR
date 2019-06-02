package com.boco.eoms.check.dao;
import com.boco.eoms.common.util.*;
import java.util.ArrayList;
import java.util.*;
import com.boco.eoms.db.hibernate.*;
import com.boco.eoms.check.bo.*;
import com.boco.eoms.check.dao.*;
import com.boco.eoms.check.model.*;
import org.hibernate.classic.Session;
import com.boco.eoms.check.vo.*;
import com.boco.eoms.check.qo.*;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import javax.servlet.*;
import com.boco.eoms.common.dao.*;
import org.hibernate.Query;
import org.apache.struts.util.*;
public class TawCheckTargerDAO extends HibernateDAO
{
	public List getList(String sql,int[] pagePra)
	{
		List list=new ArrayList();
		try
		{
			Session s=HibernateUtil.currentSession();
		    if (pagePra[2] <= 0) {
		        pagePra[2] = count(sql);
		      }
			HibernateUtil.currentTransaction();
			Query query=s.createQuery(sql);
			query.setFirstResult(pagePra[0]);
			query.setMaxResults(pagePra[1]);
            list=query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public List getTargerList(String sql)
	{
		List list=new ArrayList();
		try
		{
			Session s=HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
			Query query=s.createQuery(sql);
            list=query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public void save(TawCheckTarger tawCheckTarger)
	{
		try
		{
			Session s=HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
            s.save(tawCheckTarger);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void updateTarger(TawCheckTarger tawCheckTarger)
	{
		try
		{
			Session s=HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
            s.update(tawCheckTarger);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void delete(TawCheckTarger tawTarger)
	{
		try
		{
			Session s=HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
            s.delete(tawTarger);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public TawCheckTarger loadTarger(String targer_id)
	{
		TawCheckTarger tawCheckTarger=new TawCheckTarger();
		try
		{
			Session s=HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
			tawCheckTarger=(TawCheckTarger)s.load(TawCheckTarger.class, targer_id);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return tawCheckTarger;
	}
	public void delTarger(TawCheckTarger tarCheckTarger)
	{
		try
		{
			Session s=HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
			s.delete(tarCheckTarger);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}