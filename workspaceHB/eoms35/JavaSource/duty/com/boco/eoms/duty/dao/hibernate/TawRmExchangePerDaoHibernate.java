
package com.boco.eoms.duty.dao.hibernate;

  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawRmAssignSub;
import com.boco.eoms.duty.model.TawRmExchangePer;
import com.boco.eoms.duty.model.TawRmGuestform;
import com.boco.eoms.duty.dao.ITawRmExchangePerDao;


public class TawRmExchangePerDaoHibernate extends BaseDaoHibernate implements ITawRmExchangePerDao{

	com.boco.eoms.db.util.ConnectionPool ds;
	TawRmExchangePer tawRmExchangePer=new TawRmExchangePer();

	
	public List getTawRmExchangePer(String workserial) {
		String hql = " from TawRmExchangePer where  workserial='"+workserial+"'";
		return getHibernateTemplate().find(hql);
	}
	public void saveTawRmExchangePer(TawRmExchangePer tawRmExchangePer) {
		
		if ((tawRmExchangePer.getId() == null) || (tawRmExchangePer.getId().equals("")))
			getHibernateTemplate().save(tawRmExchangePer);
		else
			getHibernateTemplate().save(tawRmExchangePer);
		
	}


}