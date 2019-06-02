package com.boco.eoms.check.dao;
import com.boco.eoms.common.util.*;
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
public class TawCheckSchedulerDAO extends HibernateDAO {

	public List getDATA(String hsql)
	{
		List list=new ArrayList();
		try
		{
			Session s=HibernateUtil.currentSession();
			HibernateUtil.currentTransaction();
			Query query=s.createQuery(hsql);
			list=query.list();
		}catch(Exception e)
		{e.printStackTrace();}
		return list;
	}
	public void saveCDMAScore(TawCheckCDMASCORE tawCheckCDMASOCRE)
	{
		try
		{
		      Session s = HibernateUtil.currentSession();
		      HibernateUtil.currentTransaction();
		      s.save(tawCheckCDMASOCRE);
		      HibernateUtil.commitTransaction();//提交事务
		}catch(Exception e)
		{e.printStackTrace();}
	}
	public void saveGSMScore(TawCheckGSMSCORE tawCheckGSMSOCRE)
	{
		try
		{
		      Session s = HibernateUtil.currentSession();
		      HibernateUtil.currentTransaction();
		      s.save(tawCheckGSMSOCRE);
		      HibernateUtil.commitTransaction();//提交事务
		}catch(Exception e)
		{e.printStackTrace();}
	}
	public void saveDutyScore(TawCheckDutyScore tawCheckDutyScore)
	{
		try
		{
		      Session s = HibernateUtil.currentSession();
		      HibernateUtil.currentTransaction();
		      s.save(tawCheckDutyScore);
		      HibernateUtil.commitTransaction();//提交事务
		}catch(Exception e)
		{e.printStackTrace();}
	}
	public List getList(String sql) {
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
	public void saveTransScore(TawCheckTransSCORE tawCheckTransSOCRE)
	{
		try
		{
		      Session s = HibernateUtil.currentSession();
		      HibernateUtil.currentTransaction();
		      s.save(tawCheckTransSOCRE);
		      HibernateUtil.commitTransaction();//提交事务
		}catch(Exception e)
		{e.printStackTrace();}
	}
	public void saveDATAScore(TawCheckDATASCORE tawCheckDATASOCRE)
	{
		try
		{
		      Session s = HibernateUtil.currentSession();
		      HibernateUtil.currentTransaction();
		      s.save(tawCheckDATASOCRE);
		      HibernateUtil.commitTransaction();//提交事务
		}catch(Exception e)
		{e.printStackTrace();}
	}
}
