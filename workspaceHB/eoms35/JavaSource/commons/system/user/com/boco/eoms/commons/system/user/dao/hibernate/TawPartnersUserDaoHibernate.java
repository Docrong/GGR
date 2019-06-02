package com.boco.eoms.commons.system.user.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.user.dao.TawPartnersUserDao;
import com.boco.eoms.commons.system.user.model.TawPartnersUser;

public class TawPartnersUserDaoHibernate extends BaseDaoHibernate implements TawPartnersUserDao{
	/**
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserDao#saveTawPartnersUser(TawPartnersUser
	 *      tawPartnersUser)
	 *      2008-11-11 liujinlong
	 */
	public void saveTawPartnersUser(TawPartnersUser tawPartnersUser){
		if ((tawPartnersUser.getId() == null)
				|| (tawPartnersUser.getId().equals("")))
			getHibernateTemplate().save(tawPartnersUser);
		else
			getHibernateTemplate().saveOrUpdate(tawPartnersUser);
	}
	/**
	 * 删除tawPartnersUser
	 * 
	 * @param tawPartnersUser
	 * 2008-11-12 liujinlong
	 */
	public void removeTawPartnersUser(TawPartnersUser tawPartnersUser) {
		getHibernateTemplate().delete(tawPartnersUser);
	}
	/**
	 * 根据userid得到代维人员的信息
	 * 
	 * @param userid
	 * @return
	 * 2008-11-12 liujinlong
	 */
	public TawPartnersUser getPartnersUserByUserId(String userid){
		TawPartnersUser tawPartnersUser = new TawPartnersUser();
		List list = new ArrayList();
		if(userid!=null){
			String hql = " from TawPartnersUser tawPartnersUser where tawPartnersUser.userid='"+ userid + "'";
			list = (List) getHibernateTemplate().find(hql);
			if(list!=null&&list.size()>0){
				tawPartnersUser = (TawPartnersUser)list.get(0);
			}
		}
		return tawPartnersUser;
	}

}
