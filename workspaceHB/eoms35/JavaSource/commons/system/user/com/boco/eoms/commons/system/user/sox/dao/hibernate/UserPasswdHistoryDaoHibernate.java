package com.boco.eoms.commons.system.user.sox.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.user.sox.dao.IUserPasswdHistoryDao;
import com.boco.eoms.commons.system.user.sox.model.UserPasswdHistory;

/**
 * <p>
 * Title:用户密码修改记录dao的hibernate实现
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 20, 2008 5:58:14 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class UserPasswdHistoryDaoHibernate extends BaseDaoHibernate implements
		IUserPasswdHistoryDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.user.sox.dao.IUserPasswdHistoryDao#addUserPasswdHistory(com.boco.eoms.commons.system.user.sox.model.UserPasswdHistory)
	 */
	public void addUserPasswdHistory(UserPasswdHistory userPasswdHistory) {
		getHibernateTemplate().saveOrUpdate(userPasswdHistory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.user.sox.dao.IUserPasswdHistoryDao#findUserPasswdHistory(java.lang.Integer)
	 */
	public List listUserPasswdHistory(final String userId, final Integer number) {
		// filter on properties set in the forums
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from UserPasswdHistory userPasswdHistory where userPasswdHistory.userId =:userId order by userPasswdHistory.updateTime desc";
				Query query = session.createQuery(queryStr);
				query.setString("userId", userId);
				query.setFirstResult(0);
				query.setMaxResults(number.intValue());
				List result = query.list();
				return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

}
