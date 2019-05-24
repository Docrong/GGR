package com.boco.eoms.sheet.netownershipwireless.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
import com.boco.eoms.sheet.netownershipwireless.dao.INetOwnershipwirelessDAO;
import com.boco.eoms.sheet.netownershipwireless.model.NetOwnershipwireless;

public class NetOwnershipwirelessDAOHibernate extends BaseSheetDaoHibernate implements INetOwnershipwirelessDAO {

	public void deleteObjectById(String id) throws Exception {
		NetOwnershipwireless common = this.getObjectById(id);
		common.setDeleted(Integer.valueOf("1"));
		this.saveOrUpdate(common);
	}

	public List getObjectByCondition(String condition) throws Exception {
		String hql = "from  NetOwnershipwireless where deleted='0' "+condition;
		return getHibernateTemplate().find(hql);
	}

	public NetOwnershipwireless getObjectById(String id) throws Exception {
		String hql = "from NetOwnershipwireless where deleted='0' and id='"+id+"'";
		NetOwnershipwireless common = new NetOwnershipwireless();
		List list = getHibernateTemplate().find(hql);
		if(list.size()>0){
			common =(NetOwnershipwireless) list.get(0);
		}
		return common;
	}

	public void saveOrUpdate(NetOwnershipwireless common) throws Exception {
		if("".equals(common.getId())||null==common.getId()){
			getHibernateTemplate().save(common);
		}else{
			getHibernateTemplate().saveOrUpdate(common);
		}
	}

	public List getQueryByCondition(final String hsql, final Integer curPage,final Integer pageSize, int[] aTotal, String queryType)
			throws HibernateException {
		if (aTotal[0] <= 0) {
			aTotal[0] = ((Integer)count(hsql)).intValue();
			if (aTotal[0] <= 0) {
				return null;
			}
		}
		if (queryType != null && queryType.equals("number")) {
			return null;
		}
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)throws HibernateException {
				Query query = session.createQuery(hsql);
				// 分页查询条
				if(pageSize.intValue()!=-1){
				query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				}
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	public Integer count(String hsql) throws HibernateException {
		int sql_distinct = hsql.indexOf("distinct");
		int sql_index = hsql.indexOf("from");
		int sql_orderby = hsql.indexOf("order by");
		String countStrTmp;
		if (sql_distinct > 0) {
			int sql_comma = (hsql.substring(sql_distinct, sql_index)).indexOf(",");
			if (sql_comma > 0) {
				countStrTmp = "select count("+ hsql.substring(sql_distinct, sql_distinct+ sql_comma) + ") ";
			} else {
				countStrTmp = "select count("+ hsql.substring(sql_distinct, sql_index) + ") ";
			}
		}
		else {
			countStrTmp = "select count(*) ";
		}
		if (sql_orderby > 0)
			countStrTmp += hsql.substring(sql_index, sql_orderby);
		else
			countStrTmp += hsql.substring(sql_index);
		
		final String countStr = countStrTmp;
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)throws HibernateException {
				Integer amount;
				Query query = session.createQuery(countStr);
				if (!query.list().isEmpty()) {
					amount = (Integer) query.list().get(0);
				} else
					amount = new Integer(0);
				return amount;
			}
		};
		return (Integer) getHibernateTemplate().execute(callback);

	}

	public boolean ifNetExist(String netId,String netName) throws Exception {
		//false为不存在，true为存在
		boolean flag = false;
		String hql = "from NetOwnershipwireless where deleted='0' and netId = '"+netId+"' and netName='"+netName+"'";
		List list =  getHibernateTemplate().find(hql);
		if(list.size()>0){
			NetOwnershipwireless net = (NetOwnershipwireless)list.get(0);
			if(net.getDeleted().equals("1")){
				flag = false;
			}else{
				flag = true;
			}
		}else{
			flag = false;
		}
		return flag;
	}

	public List getAreabycountyId(String condition) throws Exception {
		String hql = "from NetOwnershipAreawireless a where 1=1 "+condition;
		List list = getHibernateTemplate().find(hql);
		if(list.size()>0){
			return list;
		}else{
			return new ArrayList();
		}
	}

	public List getTuifuAlarmBynetId(String condition) throws Exception {
		String hql = "from NetOwnershipAlarmwireless a where 1=1 "+condition;
		List list = getHibernateTemplate().find(hql);
		if(list.size()>0){
			return list;
		}else{
			return new ArrayList();
		}
	}
	
	public List getNetType() throws HibernateException {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "select distinct nettype from CommonFault_Net_Team_wx where deleted='0' and nettype is not null";
				Query query = session.createSQLQuery(queryStr);
				ArrayList queryList = new ArrayList();
				queryList = (ArrayList)query.list();
				return queryList;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	public String getSubroleId(final String cityid) throws HibernateException {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String subroleid = "";
				String queryStr = "select subroleid from cityid_to_subrole where cityid='" + cityid +"'";
				Query query = session.createSQLQuery(queryStr);
				ArrayList queryList = new ArrayList();
				queryList = (ArrayList)query.list();
				if (queryList.size()>0) {
					subroleid = (String)queryList.get(0);
				}
				return subroleid;
			}
		};
		return (String) getHibernateTemplate().execute(callback);
	}
	
	public List ifNoneedAutotran(final String neid) throws HibernateException {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "select * from noneedautotran where netid='" + neid +"'";
				Query query = session.createSQLQuery(queryStr);
				ArrayList queryList = new ArrayList();
				queryList = (ArrayList)query.list();
				return queryList;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
	
	public NetOwnershipwireless getNetOwnershipByNetId(String netId) throws Exception{
		NetOwnershipwireless netOwnershipwireless=null;
		String hql = "from NetOwnershipwireless where deleted='0' and netId = '"+netId+"'";
		List list =  getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			netOwnershipwireless=(NetOwnershipwireless)list.get(0);
		}
		return netOwnershipwireless;
	}
	
	public String getPerformanceAlarm(final String toDeptId,final String mainAlarmId) throws HibernateException {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String subroleid = "";
				String queryStr = "select subroleid from performancealarm where todeptid='" + toDeptId +"' and alarmid='" + mainAlarmId + "'";
				Query query = session.createSQLQuery(queryStr);
				ArrayList queryList = new ArrayList();
				queryList = (ArrayList)query.list();
				if (queryList.size()>0) {
					subroleid = (String)queryList.get(0);
				}
				return subroleid;
			}
		};
		return (String) getHibernateTemplate().execute(callback);
	}
	
	public NetOwnershipwireless getNetOwnershipByNetName(String netName) throws Exception{
		NetOwnershipwireless netOwnershipwireless=null;
		String hql = "from NetOwnershipwireless where deleted='0' and netName = '"+netName+"'";
		List list =  getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			netOwnershipwireless=(NetOwnershipwireless)list.get(0);
		}
		return netOwnershipwireless;
	}
	
	public String getSubroleIdByEquipmentName(final String s) throws Exception {
		HibernateCallback callback = new HibernateCallback() {

			public Object doInHibernate(Session session)
				throws HibernateException
			{
				String subroleid = "";
				String queryStr = "select teamroleid from commonfault_net_team where deleted = 0 and netname='" + s + "'";
				Query query = session.createSQLQuery(queryStr);
				ArrayList queryList = new ArrayList();
				queryList = (ArrayList)query.list();
				if (queryList.size() > 0)
					subroleid = (String)queryList.get(0);
				return subroleid;
			}

		};
		return (String)getHibernateTemplate().execute(callback);
	}
	
}
