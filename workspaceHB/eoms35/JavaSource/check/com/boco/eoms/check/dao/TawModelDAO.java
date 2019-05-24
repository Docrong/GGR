package com.boco.eoms.check.dao;
import java.util.*;
import org.hibernate.*;
import org.hibernate.classic.Session;
import com.boco.eoms.check.model.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.db.hibernate.HibernateUtil;
public class TawModelDAO extends HibernateDAO {
	public void save(TawModel tawModel)
	{
		try
		{
			Session s=HibernateUtil.getCurrentSession();
			HibernateUtil.currentTransaction();
            s.save(tawModel);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void update(TawModel tawModel)
	{
		try
		{
			Session s=HibernateUtil.getCurrentSession();
			HibernateUtil.currentTransaction();
            s.update(tawModel);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void delete(TawModel tawModel)
	{
		try
		{
			Session s=HibernateUtil.getCurrentSession();
			HibernateUtil.currentTransaction();
            s.delete(tawModel);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public List getList(String hsql)
	{
		List list=null;
		try
		{
			Session s=HibernateUtil.getCurrentSession();
			HibernateUtil.currentTransaction();
            Query query=s.createQuery(hsql);
            list=query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
}
