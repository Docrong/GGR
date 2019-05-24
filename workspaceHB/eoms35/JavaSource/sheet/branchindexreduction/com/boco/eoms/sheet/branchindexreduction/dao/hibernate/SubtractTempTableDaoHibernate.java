package com.boco.eoms.sheet.branchindexreduction.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.branchindexreduction.dao.ISubtractTempTableDao;
import com.boco.eoms.sheet.branchindexreduction.model.SubtractTempTable;


/**
 * @author wangmingming
 *
 * 2017-8-4
 */

public class SubtractTempTableDaoHibernate extends BaseDaoHibernate implements ISubtractTempTableDao {

	public List getSubtractTempTablesByCondition(final String condition) {
		// TODO Auto-generated method stub
		HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException {
           	 
            String queryStr = "from SubtractTempTable subtractTempTable ";
            if(condition != null && condition.length() > 0){
            	queryStr += condition;
    		}
            queryStr += " order by id desc ";
            Query query = session.createQuery(queryStr);
            return query.list();
        }
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	public void removeSubtractTempTable(String id) {
		// TODO Auto-generated method stub
		SubtractTempTable temp =  (SubtractTempTable)this.getObject(SubtractTempTable.class, id);
		getHibernateTemplate().delete(temp);
	}

	public void saveSubtractTempTable(SubtractTempTable subtractTable) {
		// TODO Auto-generated method stub
		if ((subtractTable.getId() == null) || (subtractTable.getId().equals(""))){
            getHibernateTemplate().save(subtractTable);
		}else{
            getHibernateTemplate().saveOrUpdate(subtractTable);
		}
	}

	public List getInvalidTables(final String serialKey,final String sheetId) {
		// TODO Auto-generated method stub
		HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException {
            	String querySql = "select a.id,a.rowIndex,a.serialKey,a.serialId,a.isValid,a.remark from substract a" +
            			" left join SUBTRACTCONTENTSINGLE_TABLE b on a.serialId = b.serialId where a.serialKey = :serialKey and b.id is null ";
            	SQLQuery sqlQuery = session.createSQLQuery(querySql);
            	sqlQuery.setParameter("serialKey",serialKey);
            	sqlQuery.addEntity(SubtractTempTable.class);
            	return sqlQuery.list();
        }
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	public List getRepeatedTables(final String serialKey) {
		// TODO Auto-generated method stub
		HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException {
            	String querySql = "select a.id,a.rowIndex,a.serialKey,a.serialId,a.isValid,a.remark from substract a where a.serialId in "
            		+ "(select serialId from substract where serialKey =:serialKey group by serialId having(count(1))>1) and a.serialKey = :serialKey";
            	SQLQuery sqlQuery = session.createSQLQuery(querySql);
            	sqlQuery.setParameter("serialKey",serialKey);
            	sqlQuery.addEntity(SubtractTempTable.class);
            	return sqlQuery.list();
        }
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	public List getUnCompletedTables(final String serialKey) {
		// TODO Auto-generated method stub
		HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException {
            	String querySql = "select a.id,a.rowIndex,a.serialKey,a.serialId,a.isValid,a.remark from substract a where a.serialKey = :serialKey and (a.isValid is null or a.isValid = '请选择')  ";
            	SQLQuery sqlQuery = session.createSQLQuery(querySql);
            	sqlQuery.setParameter("serialKey",serialKey);
            	sqlQuery.addEntity(SubtractTempTable.class);
            	return sqlQuery.list();
        }
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	public List getOmitTables(final String serialKey,final String sheetId) {
		// TODO Auto-generated method stub
		HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException {
            	String querySql = "select b.id,b.serialId from SUBTRACTCONTENTSINGLE_TABLE b "
            		+" left join substract a on a.serialId = b.serialId and  a.serialKey = :serialKey where a.id is null and b.reserveB=:sheetId";
            	SQLQuery sqlQuery = session.createSQLQuery(querySql);
            	sqlQuery.setParameter("serialKey",serialKey);
            	sqlQuery.setParameter("sheetId",sheetId);
            	return sqlQuery.list();
        }
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	public void delBySerialKey(final String serialKey) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					HibernateException {
				String sql = "delete from substract where serialKey=:serialKey"; 
				SQLQuery query = session.createSQLQuery(sql);
				query.setString("serialKey", serialKey);
				query.executeUpdate();
				return null;
				}
			});
	}

	public void delForInvalidTables(final String serialKey,final String sheetId) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					HibernateException {
				String sql = "delete from substract where serialKey=:serialKey and serialId not in(select serialId from SUBTRACTCONTENTSINGLE_TABLE where reserveB = :sheetId)"; 
				SQLQuery query = session.createSQLQuery(sql);
				query.setString("serialKey", serialKey);
				query.setParameter("sheetId",sheetId);
				query.executeUpdate();
				return null;
				}
			});
	}

	public void delForRepeatTables(final String serialKey) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					HibernateException {
				String sql = " delete from substract " 
						+ "where serialId in(select serialId from substract where serialKey = :serialKey group by serialId having(count(1)>1)) " 
						+ " and rowIndex not in(select max(rowIndex) from substract where serialKey = :serialKey group by serialId having(count(1))>1)";
				SQLQuery query = session.createSQLQuery(sql);
				query.setString("serialKey", serialKey);
				query.executeUpdate();
				return null;
				}
			});
	}

	public void updateForUncompletedTables(final String serialKey) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					HibernateException {
				String sql = "update substract set isValid = '否' where serialKey=:serialKey and (isValid is null or isValid='请选择' or remark is null )";
				SQLQuery query = session.createSQLQuery(sql);
				query.setString("serialKey", serialKey);
				query.executeUpdate();
				return null;
				}
			});
	}

	public void removeBySerialKey(final String serialKey) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					HibernateException {
				String sql = "delete from substract where serialKey = :serialKey";
				SQLQuery query = session.createSQLQuery(sql);
				query.setString("serialKey", serialKey);
				query.executeUpdate();
				return null;
				}
			});
	}

	public void updateForOmitTables(final String serialKey,final String sheetId) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					HibernateException {
				String sql = "update SUBTRACTCONTENTSINGLE_TABLE main set main.reserveC = '否' where main.serialId in(select temp.serialId from " +
						"SUBTRACTCONTENTSINGLE_TABLE main left join substract temp on temp.serialId = main.serialId and temp.serialKey=:serialKey" +
						" where temp.id is null and main.reserveB = :sheetId)";
				SQLQuery query = session.createSQLQuery(sql);
				query.setString("serialKey", serialKey);
				query.setString("sheetId", sheetId);
				query.executeUpdate();
				return null;
				}
			});
	}

	public void updateMainTable(final String serialKey) {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					HibernateException {
				String sql = "update SUBTRACTCONTENTSINGLE_TABLE main,substract temp " 
						+ "set main.reserveD = temp.remark,main.reserveC = temp.isValid " 
						+ "where main.serialId = temp.serialId and temp.serialKey=:serialKey";
				SQLQuery query = session.createSQLQuery(sql);
				query.setString("serialKey", serialKey);
				query.executeUpdate();
				return null;
				}
			});
	}
}