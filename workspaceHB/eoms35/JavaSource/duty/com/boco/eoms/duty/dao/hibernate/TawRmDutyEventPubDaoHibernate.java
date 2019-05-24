
package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawExpertSub;
import com.boco.eoms.duty.model.TawRmArticle;
import com.boco.eoms.duty.model.TawRmDutyEvent;
import com.boco.eoms.duty.model.TawRmDutyEventPub;
import com.boco.eoms.duty.dao.ITawExpertSubDao;
import com.boco.eoms.duty.dao.ITawRmDutyEventPubDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawRmDutyEventPubDaoHibernate extends BaseDaoHibernate implements ITawRmDutyEventPubDao{

	public List getPubStatus(String pubStatus) {
		String hql = " from TawRmDutyEventPub obj where obj.pubstatus='"+pubStatus+"'";
		return getHibernateTemplate().find(hql);
	}

	public void saveTawRmDutyEventPub(TawRmDutyEventPub tawRmDutyEventPub) {
		if ((tawRmDutyEventPub.getId() == null) || (tawRmDutyEventPub.getId().equals("")))
			getHibernateTemplate().save(tawRmDutyEventPub);
		else
			getHibernateTemplate().saveOrUpdate(tawRmDutyEventPub);
	}

	public List getEventId(String eventId) {
		String hql = "from TawRmDutyEventPub where eventid='"+eventId+"'";
		return getHibernateTemplate().find(hql);
	}

	public void removeTawRmDutyEventPub(String eventId) {
		String hql = "from TawRmDutyEventPub where eventid='"+eventId+"'";
		TawRmDutyEventPub t=(TawRmDutyEventPub) getHibernateTemplate().find(hql).get(0);
		System.out.println(t.getId());
		getHibernateTemplate().delete(t);
		
	}
	

}
