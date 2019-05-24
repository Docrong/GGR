package com.boco.eoms.km.expert.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.km.expert.dao.KmExpertPhotoDao;
import com.boco.eoms.km.expert.model.KmExpertPhoto;

public class KmExpertPhotoDaoHibernate extends BaseDaoHibernate implements KmExpertPhotoDao{
	
	/**
	 * 根据userId查询用户头像信息
	 */
	public KmExpertPhoto getKmExpertPhotoByUserId(final String userId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExpertPhoto kmExpertPhoto where kmExpertPhoto.userId=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, userId);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmExpertPhoto) result.iterator().next();
				} else {
					return new KmExpertPhoto();
				}
			}
		};
		return (KmExpertPhoto) getHibernateTemplate().execute(callback);
	}

	public void saveKmExpertPhoto(final KmExpertPhoto kmExpertPhoto) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// 删除旧的头像
				String queryStr = "delete from KmExpertPhoto kmExpertPhoto where kmExpertPhoto.userId=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, kmExpertPhoto.getUserId());
				query.executeUpdate();

				// 保存新的头像
				session.save(kmExpertPhoto);
				return kmExpertPhoto;
			}
		};
		getHibernateTemplate().execute(callback);
	}

}