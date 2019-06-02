package com.boco.eoms.system.mappingstorage.dao.hibernate;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.system.mappingstorage.dao.MappingDao;
import com.boco.eoms.system.mappingstorage.mgr.MappingMgr;

import com.boco.eoms.system.mappingstorage.model.Mapping;
import com.boco.eoms.system.mappingstorage.webapp.form.MappingForm;

/**
 * <p>
 * Title:存储映射 dao的hibernate实现
 * </p>
 * <p>
 * Description:存储映射
 * </p>
 * <p>
 * Wed Apr 08 09:10:47 CST 2009
 * </p>
 * 
 * @author sam
 * @version 1.0
 * 
 */
public class MappingDaoHibernate extends BaseDaoHibernate implements MappingDao,
		ID2NameDAO {	
    /**
	 * 
	 * @see com.boco.eoms.system.mappingstorage.MappingDao#getMappings()
	 *      
	 */
	public List getMappings() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Mapping mapping";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.system.mappingstorage.MappingDao#getMapping(java.lang.String)
	 *
	 */
	public Mapping getMapping(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Mapping mapping where mapping.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (Mapping) result.iterator().next();
				} else {
					return new Mapping();
				}
			}
		};
		return (Mapping) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.system.mappingstorage.MappingDao#saveMappings(com.boco.eoms.system.mappingstorage.Mapping)
	 *      
	 */
	public void saveMapping(final Mapping mapping) {
		if ((mapping.getId() == null) || (mapping.getId().equals("")))
			getHibernateTemplate().save(mapping);
		else
			getHibernateTemplate().saveOrUpdate(mapping);
	}

	/**
	 * 
	 * @see com.boco.eoms.system.mappingstorage.MappingDao#removeMappings(java.lang.String)
	 *      
	 */
	
    public void removeMapping(final String id) {
		getHibernateTemplate().delete(getMapping(id));
    	
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	
	
 	/**
	 * 
	 * @see com.boco.eoms.system.mappingstorage.MappingDao#getMappings(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getMappings(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Mapping mapping";
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

	public void genTable(String tableName){
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("create table "+tableName);
		sql.append("(app_code varchar2(32)," +
				"sheetKey varchar2(32),rootId varchar2(32),dictId varchar2(200),context varchar2(200) )");
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.execute();
			pstmt.close();
			conn.commit();
		} catch (SQLException e) {
			try {
				pstmt.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		
	}

	public void updateMapping(Mapping mapping) {
		// TODO Auto-generated method stub
		
	}


	public String insertValue(String appcode, 
		String sheetKey, String rootId,String dict) throws SQLException, Exception {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
		String sql1="select new_table from mappingstorage where app_code='"+appcode+"'";
		conn = ds.getConnection();
		pstmt=conn.prepareStatement(sql1);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()){
		String tablename=rs.getString("new_table");
		String [] strArray = dict.split(",");
	           for (int i = 0, j = strArray.length; i < j; i ++) {

				    StringBuffer sql=new StringBuffer();
			        sql.append("insert into " + tablename+"(app_code, sheetKey,rootId,dictId)");
					sql.append(" values('" + appcode + "', '" + sheetKey + "','"+ rootId+"','"+strArray[i]+"')");
					pstmt = conn.prepareStatement(sql.toString());
					pstmt.execute();
		}
		
	    conn.commit();
		pstmt.close();
		conn.close();
		
	}
		return UUIDHexGenerator.getInstance().getID();
	}
	
//	得到dictid的list
	private static int len=7;
	public String dictIdToName(String appcode,String sheetkey){
		 String tablename = (String) getHibernateTemplate().find("select new_table from Mapping where app_code='"+appcode+"'").get(0);
		 
			com.boco.eoms.db.util.BocoConnection conn = null;
			PreparedStatement pstmt = null;
			com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool.
			                 getInstance();
			String sql1="select dictid from " + tablename + " where sheetkey='"+sheetkey+"'";
			conn = ds.getConnection();
			List list=new ArrayList();
				try {
					pstmt=conn.prepareStatement(sql1);
					ResultSet rs = pstmt.executeQuery();
					
					
					while(rs.next()){
						list.add((String)rs.getString("dictid"));
						}
					
					
					    pstmt.execute();
						conn.commit();
						pstmt.close();
						conn.close();
						
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				String newStr = "";
				String newStrr="";
				String display="";

				
				for (int i = 0, j = list.size(); i < j; i ++) {
					
					newStr = dictIdToNamee((String) list.get(i));
					newStrr =newStr.substring(0,newStr.length()-1);
					
					display +=newStrr+",";
				}
				
				
				
				
				
		 return display;	
	    }
	
     public String dictIdToNamee(String id) {
		
		
		String beanid="ItawSystemDictTypeDao";
		ID2NameService id2nameservice=(ID2NameService)ApplicationContextHolder.
		getInstance().getBean("id2nameService");
		
		
		int length = id.length();
		if (length == len) {
			return id2nameservice.id2Name(id, beanid) + "-";
		} else if (length < len + 2) {
			return "";
		}else {
			String newStr = id.substring(0, id.lastIndexOf(id.substring(length - 2)));
			return dictIdToNamee(newStr) + id2nameservice.id2Name(id, beanid) + "-";
		}
	}

	

	public String id2Name(String id) throws DictDAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String checkUnique(String tableName) {
		List list = new ArrayList();
		String sql="select app_code from Mapping where new_table= '" +tableName+ "'";
		list = getHibernateTemplate().find(sql);
		String flag = "true";
		if(list.size()>0){
			flag="false";
		}
		
		return flag;
	}

	


	
	
}