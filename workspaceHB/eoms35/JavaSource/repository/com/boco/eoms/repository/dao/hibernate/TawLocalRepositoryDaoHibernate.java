package com.boco.eoms.repository.dao.hibernate;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.repository.dao.TawLocalRepositoryDao;
import com.boco.eoms.repository.model.TawLocalRepository;
import com.boco.eoms.repository.model.TawLocalRepositoryUp;

/**
 * <p>
 * Title:tawLocalRepository dao的hibernate实现
 * </p>
 * <p>
 * Description:tawLocalRepository
 * </p>
 * <p>
 * Fri Oct 30 09:14:26 CST 2009
 * </p>
 * 
 * @author 李锋
 * @version 1.0
 * 
 */
public class TawLocalRepositoryDaoHibernate extends BaseDaoHibernate implements TawLocalRepositoryDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.repository.TawLocalRepositoryDao#getTawLocalRepositorys()
	 *      
	 */
	public List getTawLocalRepositorys() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawLocalRepository tawLocalRepository where 1=1 ";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.repository.TawLocalRepositoryDao#getTawLocalRepository(java.lang.String)
	 *
	 */
	public TawLocalRepository getTawLocalRepository(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawLocalRepository tawLocalRepository where tawLocalRepository.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (TawLocalRepository) result.iterator().next();
				} else {
					return new TawLocalRepository();
				}
			}
		};
		return (TawLocalRepository) getHibernateTemplate().execute(callback);
	}
	
	/**
	 *
	 * @see com.boco.eoms.repository.TawLocalRepositoryDao#getTawLocalRepository(java.lang.String)
	 *
	 */
	public TawLocalRepository getTawLocalRepositorybyname(final String name) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawLocalRepository tawLocalRepository where tawLocalRepository.net=:net";
				Query query = session.createQuery(queryStr);
				query.setString("net", name);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (TawLocalRepository) result.iterator().next();
				} else {
					return new TawLocalRepository();
				}
			}
		};
		return (TawLocalRepository) getHibernateTemplate().execute(callback);
	}
	
	public TawLocalRepositoryUp getTawLocalRepositorybySheetkey(final String sheetkey) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawLocalRepositoryUp t where t.sheetkey=:sheetkey";
				Query query = session.createQuery(queryStr);
				query.setString("sheetkey", sheetkey);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (TawLocalRepositoryUp) result.iterator().next();
				} else {
					return new TawLocalRepositoryUp();
				}
			}
		};
		return (TawLocalRepositoryUp) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.repository.TawLocalRepositoryDao#saveTawLocalRepositorys(com.boco.eoms.repository.TawLocalRepository)
	 *      
	 */
	public void saveTawLocalRepository(final TawLocalRepository tawLocalRepository) {
		if ((tawLocalRepository.getId() == null) || (tawLocalRepository.getId().equals("")))
			getHibernateTemplate().save(tawLocalRepository);
		else
			getHibernateTemplate().saveOrUpdate(tawLocalRepository);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.repository.TawLocalRepositoryDao#saveTawLocalRepositorys(com.boco.eoms.repository.TawLocalRepository)
	 *      
	 */
	public void updateTawLocalRepository(final TawLocalRepository tawLocalRepository) {

		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {

				String id=tawLocalRepository.getHardwareRepository();
				String hardwareRepository=tawLocalRepository.getHardwareRepository();
				String softwareRepository=tawLocalRepository.getSoftwareRepository();
				String patch=tawLocalRepository.getPatch();
				String queryStr = "update TawLocalRepository tawLocalRepository set tawLocalRepository.hardwareRepository = :hardwareRepository  where tawLocalRepository.id = :id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				return new Integer(query.executeUpdate());
			}
		};
		getHibernateTemplate().execute(callback);
	}

	/**
	 * 
	 * @see com.boco.eoms.repository.TawLocalRepositoryDao#saveTawLocalRepositorys(com.boco.eoms.repository.TawLocalRepository)
	 *      
	 */
	public void saveTawLocalRepositoryUp(final TawLocalRepositoryUp tawLocalRepositoryUp) {

			getHibernateTemplate().save(tawLocalRepositoryUp);
		
	}
	/**
	 * 
	 * @see com.boco.eoms.repository.TawLocalRepositoryDao#removeTawLocalRepositorys(java.lang.String)
	 *      
	 */
    public void removeTawLocalRepository(final String id) {
		getHibernateTemplate().delete(getTawLocalRepository(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		TawLocalRepository tawLocalRepository = this.getTawLocalRepository(id);
		if(tawLocalRepository==null){
			return "";
		}
		//TODO 请修改代码
		return "";
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.repository.TawLocalRepositoryDao#getTawLocalRepositorys(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getTawLocalRepositorys(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawLocalRepository tawLocalRepository where 1=1 ";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	
	
	/**
	 * 
	 * @see com.boco.eoms.repository.TawLocalRepositoryDao#getTawLocalRepositorys(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getTawLocalRepositorysHistory(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				
				String queryStr = "select getpatchsbyrid(id) as patchs,getpatchsbysheetkey(id) as sheetkeys," +
						"u.repository_id as repositoryId,u.before_hardware_repository as beforeHardwareRepository,u.before_software_repository as beforeSoftwareRepository," +
						"u.software_repository as softwareRepository,u.hardware_repository as hardwareRepository,u.up_num as upNum  from " +
						"taw_repository tawLocalRepository,(select distinct " +
						"t.repository_id,t.before_hardware_repository,t.before_software_repository,t.software_repository,t.hardware_repository,t.up_num from taw_repository_upgrade t) u " +
						"where tawLocalRepository.id = u.repository_id";
//				String queryStr = "select u from TawLocalRepositoryUp u,TawLocalRepository tawLocalRepository where u.repositoryId = tawLocalRepository.id ";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;

				Query query = session.createSQLQuery(queryStr)
//					.addEntity(TawLocalRepository.class)
					.addScalar("patchs",Hibernate.STRING)
					.addScalar("sheetkeys",Hibernate.STRING)
					.addScalar("repositoryId",Hibernate.STRING)
					.addScalar("beforeHardwareRepository",Hibernate.STRING)
					.addScalar("beforeSoftwareRepository",Hibernate.STRING)
					.addScalar("softwareRepository",Hibernate.STRING)
					.addScalar("hardwareRepository",Hibernate.STRING)
					.addScalar("upNum",Hibernate.STRING);
				int total =  query.list().size();
				
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
}