package com.boco.eoms.workplan.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.workplan.dao.ITawwpNetDao;
import com.boco.eoms.workplan.model.TawwpNet;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 1:09:09 AM
 * </p>
 * 
 * @author 鏇查潤娉�
 * @version 3.5.1
 * 
 */
public class TawwpNetDaoHibernate extends BaseDaoHibernate implements
		ITawwpNetDao {
	/**
	 * 淇濆瓨缃戝厓
	 * 
	 * @param _tawwpNet
	 * TawwpNet 缃戝厓绫� @ 鎿嶄綔寮傚父
	 */
	public void saveNet(TawwpNet _tawwpNet) {
		// this.save(_tawwpNet);
		this.getHibernateTemplate().save(_tawwpNet);
	}

	/**
	 * 鍒犻櫎缃戝厓
	 * 
	 * @param _tawwpNet
	 * TawwpNet 缃戝厓绫� @ 鎿嶄綔寮傚父
	 */
	public void deleteNet(TawwpNet _tawwpNet) {
		// this.delete(_tawwpNet);
		this.getHibernateTemplate().delete(_tawwpNet);
	}

	/**
	 * 淇敼缃戝厓
	 * 
	 * @param _tawwpNet
	 * TawwpNet 缃戝厓绫� @ 鎿嶄綔寮傚父
	 */
	public void updateNet(TawwpNet _tawwpNet) {
		// this.update(_tawwpNet);
		this.getHibernateTemplate().update(_tawwpNet);
	}

	/**
	 * 鏌ヨ缃戝厓淇℃伅
	 * 
	 * @param _id
	 * String 缃戝厓鏍囪瘑 @ 鎿嶄綔寮傚父
	 * @return TawwpNet 缃戝厓绫�
	 */
	public TawwpNet loadNet(String _id) {
		if (_id.equals("0")) {
			return new TawwpNet("鏃犵綉鍏�", "", "", "", "", "", "", "", "0","");
		} else {
			// return (TawwpNet) this.load(_id, TawwpNet.class);
			return (TawwpNet) this.getHibernateTemplate().get(TawwpNet.class,
					_id);
		}

	}

	/**
	 * 鏌ヨ鎵�鏈夌綉鍏冧俊鎭� @ 鎿嶄綔寮傚父
	 * @return List 缃戝厓绫诲垪琛�
	 */
	public List ListNet() {
		// Session s = HibernateUtil.currentSession();
		// String hSql = "";
		String hSql = "from TawwpNet as tawwpnet where tawwpnet.deleted = '0' ";
		//
		// Query query = s.createQuery(hSql);
		// query.setCacheable(true);
		// return query.list();
		// TODO query.setCacheable(true);
		return this.getHibernateTemplate().find(hSql);
	}

	/**
	 * 鏌ヨ鎵�鏈夌綉鍏冧俊鎭�
	 * 
	 * @param _netIdList
	 * String 缃戝厓鏍囪瘑瀛楃涓� @ 鎿嶄綔寮傚父
	 * @return List 缃戝厓绫诲垪琛�
	 */
	public List ListNet(String _netIdList) {
		// Session s = HibernateUtil.currentSession();
		// String hSql = "";
		String hSql = "from TawwpNet as tawwpnet where tawwpnet.deleted = '0' and id in ('"
				+ _netIdList + "')";

		// Query query = s.createQuery(hSql);
		// query.setCacheable(true);
		// return query.list();
		return this.getHibernateTemplate().find(hSql);
	}

	/**
	 * 鑾峰彇鎵�鏈夌綉鍏冧俊鎭紝鎸夌綉鍏冪被鍨嬨�侀儴闂ㄨ繘琛屾煡璇�
	 * 
	 * @param _netTypeId
	 *            String 缃戝厓鏍囪瘑
	 * @param _deptId
	 * String 閮ㄩ棬 @ 鎿嶄綔寮傚父
	 * @return List 缃戝厓绫诲垪琛�
	 */
	public List ListNetByNetDetp(String _netTypeId, String _deptId) {
		// Session s = HibernateUtil.currentSession();
		// String hSql = "";
		String hSql = "from TawwpNet as tawwpnet where tawwpnet.deptId='"
				+ _deptId + "' and tawwpnet.netTypeId = '" + _netTypeId + "' "
				+ "and tawwpnet.deleted = '0'";

		// Query query = s.createQuery(hSql);
		// query.setCacheable(true);
		// return query.list();
		return this.getHibernateTemplate().find(hSql);
	}

	/**
	 * 鏌ヨ鎵�鏈夌綉鍏冧俊鎭紝鍒嗛〉
	 * 
	 * @param _pagePra
	 * int[] 鍒嗛〉淇℃伅 @ 鎿嶄綔寮傚父
	 * @return List 缃戝厓绫诲垪琛�
	 */
	public List ListNet(int[] _pagePra) {
		// Session s = HibernateUtil.currentSession();
		// Criteria criteria = s.createCriteria(TawwpNet.class);
		// criteria.add(Expression.eq("deleted", "0"));
		//
		// criteria.addOrder(Order.asc("name"));
		// criteria.addOrder(Order.asc("netTypeId"));
		//
		// String hsql = "from TawwpNet as tawwpnet where tawwpnet.deleted='0'";
		//
		// if (_pagePra[2] <= 0) {
		// _pagePra[2] = count(hsql);
		// }
		//
		// // 鍒嗛〉澶勭悊
		// criteria.setFirstResult(_pagePra[0]);
		// criteria.setMaxResults(_pagePra[1]);
		// return criteria.list();

		DetachedCriteria criteria = DetachedCriteria.forClass(TawwpNet.class);
		criteria.add(Expression.eq("deleted", "0"));

		criteria.addOrder(Order.asc("name"));
		criteria.addOrder(Order.asc("netTypeId"));

		String hsql = "from TawwpNet as tawwpnet where tawwpnet.deleted='0'";

		if (_pagePra[2] <= 0) {
			_pagePra[2] = count(hsql);
		}

		return getHibernateTemplate().findByCriteria(criteria, _pagePra[0],
				_pagePra[1]);

	}

	/**
	 * 缁勫悎鏌ヨ缃戝厓淇℃伅
	 * 
	 * @param _map
	 * Map 鏌ヨ鏉′欢 @ 鎿嶄綔寮傚父
	 * @return List 缃戝厓淇℃伅鍒楄〃
	 */
	public List searchNet(Map _map) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(TawwpNet.class);
		String condition = null;

		Set set = _map.keySet();
		Iterator iterator = set.iterator();

		while (iterator.hasNext()) {
			condition = (String) iterator.next();
			if (condition != null&&!condition.equals("netReportType")) {
				if (condition.equals("name")) {
					criteria.add(Expression
							.like(condition, _map.get(condition)));
				}
				
				else if(condition.equals("deptId")){
					List list = (List)_map.get(condition);
					if(list.size()>0){
						criteria.add(Expression
								.in(condition, list));// edit by gonyufeng   list
					}
				}
				else {
					criteria.add(Expression.eq(condition, _map.get(condition)));
				}
			}
		}
		return getHibernateTemplate().findByCriteria(criteria);
	}

	
	/**
	 * 缁勫悎鏌ヨ缃戝厓淇℃伅
	 * 
	 * @param _map
	 * Map 鏌ヨ鏉′欢 @ 鎿嶄綔寮傚父
	 * @return List 缃戝厓淇℃伅鍒楄〃
	 */
	public List searchNet(Map _map,String flag) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(TawwpNet.class);
		String condition = null;

		Set set = _map.keySet();
		Iterator iterator = set.iterator();

		while (iterator.hasNext()) {
			condition = (String) iterator.next();
			if (condition != null&&!condition.equals("netReportType")) {
				if (condition.equals("name")) {
					criteria.add(Expression
							.like(condition, _map.get(condition)));
				}
				
				else if(condition.equals("deptId")){
					List listDept = (List)_map.get(condition);
					if(listDept.size()>0){
						criteria.add(Expression
								.in(condition, (List)_map.get(condition)));// edit by gonyufeng   list
					}
				}
				else {
					criteria.add(Expression.eq(condition, _map.get(condition)));
				}
			}
		}
		return getHibernateTemplate().findByCriteria(criteria);

	}
	/**
	 * 鑾峰彇缃戝厓鍚嶇О瀛楃涓�
	 * 
	 * @param _netIdList
	 * String 缃戝厓缂栧彿瀛楃涓� @ 鎿嶄綔寮傚父
	 * @return String 缃戝厓鍚嶇О瀛楃涓�
	 */
	public String getNetNameList(String _netIdList) {
		// Session s = HibernateUtil.currentSession();
		// Criteria criteria = s.createCriteria(TawwpNet.class);
		// List list = null;
		// String netNameList = "";
		// TawwpNet tawwpNet = null;
		//
		// String[] netIdArray = _netIdList.split(",");
		// criteria.add(Expression.in("id", netIdArray)); // 鏌ヨ鍖呭惈缃戝厓缂栧彿寰楃綉鍏冨璞�
		//
		// list = criteria.list();
		//
		// // 寰幆澶勭悊缃戝厓鍚嶇О淇℃伅
		// if (list.size() > 0) {
		// for (int i = 0; i < list.size(); i++) {
		// tawwpNet = (TawwpNet) list.get(i);
		// netNameList = netNameList + "," + tawwpNet.getName();
		// }
		// netNameList = netNameList.substring(1, netNameList.length());
		// }
		// return netNameList;
		//		

		DetachedCriteria criteria = DetachedCriteria.forClass(TawwpNet.class);

		List list = null;
		String netNameList = "";
		TawwpNet tawwpNet = null;

		String[] netIdArray = _netIdList.split(",");
		criteria.add(Expression.in("id", netIdArray)); // 鏌ヨ鍖呭惈缃戝厓缂栧彿寰楃綉鍏冨璞�

		list = getHibernateTemplate().findByCriteria(criteria);

		// 寰幆澶勭悊缃戝厓鍚嶇О淇℃伅
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				tawwpNet = (TawwpNet) list.get(i);
				netNameList = netNameList + "," + tawwpNet.getName();
			}
			netNameList = netNameList.substring(1, netNameList.length());
		}

		return netNameList;

	}

	/**
	 * 鑾峰彇缃戝厓鐨勭敓浜у巶鍟� @ 鎿嶄綔寮傚父
	 * @return List 鍘傚晢淇℃伅鍒楄〃
	 */
	public List listVerdor() {
//		Session s = HibernateUtil.currentSession();
//		Iterator iterator = null;
		Object[] verdon = null;
		List list = new ArrayList();

//		iterator = s
//				.createQuery(
//						"select tawwpnet.vendor,count(tawwpnet.id) from TawwpNet as tawwpnet where tawwpnet.vendor<>'' group by tawwpnet.vendor ")
//				.list().iterator();

		List l = this
				.getHibernateTemplate()
				.find(
						"select tawwpnet.vendor,count(tawwpnet.id) from TawwpNet as tawwpnet where tawwpnet.vendor<>'' group by tawwpnet.vendor ");
		if (l != null) {
			Iterator iterator = l.iterator();
			while (iterator.hasNext()) {
				verdon = (Object[]) iterator.next();
				list.add(StaticMethod.null2String((String) verdon[0]));
			}
		}

		return list;
	}
	 public int count(String hsql) throws HibernateException {
		    
		    int amount = 0;
		    int sql_distinct = hsql.indexOf("distinct");
		    int sql_index = hsql.indexOf("from");
		    int sql_orderby = hsql.indexOf("order by");

		    String countStr = "";
		    if (sql_distinct > 0)
		      countStr = "select count(" + hsql.substring(sql_distinct, sql_index)+") ";
		    else
		      countStr = "select count(*) ";

		    if (sql_orderby > 0)
		      countStr +=  hsql.substring(sql_index, sql_orderby);
		    else
		      countStr +=  hsql.substring(sql_index);
            List list=this.getHibernateTemplate().find(countStr); 
		    if (!list.isEmpty()) {
		      amount = ( (Integer) list.get(0)).intValue();
		    }
		    else {
		      amount = 0;
		    }
		    return amount;
   }

	public String loadNetBySerial(String _netSerial) {
		String id = "";
		String hSql = "select id from TawwpNet as tawwpnet where tawwpnet.serialNo ='" + _netSerial
				+ "'";
		List list=this.getHibernateTemplate().find(hSql);
		if(list!=null&&list.size()!=0){
			id =(String)list.get(0);
		}
		return id;
	}

	public List ListNetByDept(String _deptId) {
		String hSql = "";
		hSql = "from TawwpNet as tawwpnet where tawwpnet.deptId in('" + _deptId
				+ "') and tawwpnet.deleted = '0'";
		return this.getHibernateTemplate().find(hSql);
	}
	public List searchWorkplanNet() {
		String hSql = "";
		hSql = "from TawwpNet as tawwpnet where  tawwpnet.deleted = '0'";
		return this.getHibernateTemplate().find(hSql);
	}

	public List ListNetNoExecute(String time) {
		    String hSql = "";
		    String statTime = time + " 00:00:00";
		    String endTime = time + " 23:59:59";
		    hSql = "select distinct tawwpexecutecontent.tawwpMonthPlan from TawwpExecuteContent as tawwpexecutecontent " +
		    	   "where tawwpexecutecontent.startDate>='"+statTime+"' and tawwpexecutecontent.endDate<='"+endTime+
		    	   "' and tawwpexecutecontent.formDataId is null and tawwpexecutecontent.formId <>'0'";

		    return this.getHibernateTemplate().find(hSql);
	}

	/**
	 * 鑾峰彇缃戝厓鐨勭被鍨嬪強瀵瑰簲搴忓垪鍙风殑鏂规硶
	 * 
	 * @throws Exception
	 * @return List
	 */
	public List listNetType(Map _map) throws Exception {
		
		String netType = null;
		String netTypetemp = null;
		String serial = null;
		List list = new ArrayList();
		List resultList = new ArrayList();
		String condition = null;
		String queryCondition = "";
		Set set = _map.keySet();
		Iterator iterator = set.iterator();
		Iterator iteratortemp = set.iterator();
		while (iterator.hasNext()) {
			condition = (String) iterator.next();
			if (condition.equals("netReportType")) {
			} else {
				queryCondition = queryCondition + " and " + condition + " = '"
						+ _map.get(condition) + "'";
				
			}
		}
		queryCondition=queryCondition.substring(4);
		// 閬嶅巻缃戝厓绫诲瀷锛堜娇鐢╣roup by閬垮厤浜嗛噸澶嶏級
		iteratortemp = this.getHibernateTemplate().find(
				"select  tawwpnet.netTypeId  from TawwpNet as tawwpnet where "
						+ queryCondition + "  group by tawwpnet.netTypeId ")
				.iterator();
		while (iteratortemp.hasNext()) {
			list.clear();
			netTypetemp = (String) iteratortemp.next();
            // 涓撲笟浠ｇ爜锛堣幏寰楁煇缃戝厓绫诲瀷涓嬬殑鎵�鏈変笓涓氫唬鐮侊級锛堜娇鐢╣roup by閬垮厤浜嗛噸澶嶏級
			iterator = this.getHibernateTemplate().find(
					"select  tawwpnet.mynettypeid  from TawwpNet as tawwpnet where "
							+ queryCondition + " and tawwpnet.netTypeId="
							+ netTypetemp + "  group by tawwpnet.mynettypeid ")
					.iterator();
			while (iterator.hasNext()) {
				netType = (String) iterator.next();
				list.add(StaticMethod.dbNull2String(netType));
			}
			String sysType = null;
			String netTypeId = null;
			for (int i = 0; i < list.size(); i++) {
				Map map = new HashMap();
				List serialnoList = new ArrayList();
				String nettypeid = (String) list.get(i);
				// 浣跨敤缃戝厓绫诲瀷鍜屼笓涓氫唬鐮佹潵绾︽潫鏌ヨ鍥哄畾鑼冨洿鍐呯殑缃戝厓
				iterator = this.getHibernateTemplate().find(
						" from TawwpNet as tawwpnet  where " + queryCondition+ "and mynettypeid="
								+ nettypeid + " and tawwpnet.netTypeId=" + netTypetemp)
						.iterator();
				while (iterator.hasNext()) {
					TawwpNet net = (TawwpNet) iterator.next();
					sysType = net.getSysTypeId();
					netTypeId = net.getNetTypeId();
					serial = net.getSerialNo();
					serialnoList.add(StaticMethod.dbNull2String(serial));
				}
				map.put("netTypeId", netTypeId);
				map.put("sysType", sysType);
				map.put("mynettypeid", nettypeid);
				map.put("list", serialnoList);
				resultList.add(map);
			}

		}
		return resultList;
	}

	public List ListNetByYearReport() throws Exception {
		String hSql = "from TawwpNet as tawwpnet where  tawwpnet.reportFlag != '-1' and tawwpnet.deleted = '0'";	
		return this.getHibernateTemplate().find(hSql);
	}

	public List ListNetNewUpdate() throws Exception {
		String hSql = "from TawwpNet as tawwpnet where  tawwpnet.reportFlag = '0' and tawwpnet.deleted = '0'";
		return this.getHibernateTemplate().find(hSql);
	}

	public List searchListNet(String _id) throws Exception {
	    String hSql = "from TawwpNet as tawwpnet where tawwpnet.id ='" + _id + "'";
	    return this.getHibernateTemplate().find(hSql);
	}	

	 public List searchNetpage(Map _map,int[] _pagePra,String sql) throws Exception{
		 DetachedCriteria criteria = DetachedCriteria.forClass(TawwpNet.class);
		 criteria.add(Expression.eq("deleted", "0"));
			String condition = null;

			Set set = _map.keySet();
			Iterator iterator = set.iterator();

			while (iterator.hasNext()) {
				condition = (String) iterator.next();
				if (condition != null&&!condition.equals("netReportType")) {
					if (condition.equals("name")) {
						criteria.add(Expression
								.like(condition, _map.get(condition)));
					} else {
						criteria.add(Expression.eq(condition, _map.get(condition)));
					}
				}
			}
			String hsql = "from TawwpNet as tawwpnet where tawwpnet.deleted='0'"+sql;
			

			if (_pagePra[2] <= 0) {
				_pagePra[2] = count(hsql);
			}
		 
		
		 return getHibernateTemplate().findByCriteria(criteria, _pagePra[0],
					_pagePra[1]);
	 }

	public List listidbymynettypie(String mynettypeid) throws Exception {
		// TODO Auto-generated method stub
		String hsql="from TawwpNet as tawwpnet where tawwpnet.deleted='0' and tawwpnet.netTypeId='"+mynettypeid+"'";
		return getHibernateTemplate().find(hsql);
	}
}
