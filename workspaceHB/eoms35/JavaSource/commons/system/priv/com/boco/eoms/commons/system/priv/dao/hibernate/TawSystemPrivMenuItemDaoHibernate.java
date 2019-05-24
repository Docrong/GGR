package com.boco.eoms.commons.system.priv.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenuItem;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuItemDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSystemPrivMenuItemDaoHibernate extends BaseDaoHibernate
		implements TawSystemPrivMenuItemDao {

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuItemDao#getTawSystemPrivMenuItems(com.boco.eoms.commons.system.priv.model.TawSystemPrivMenuItem)
	 */
	public List getTawSystemPrivMenuItems(
			final TawSystemPrivMenuItem tawSystemPrivMenuItem) {
		return getHibernateTemplate().find("from TawSystemPrivMenuItem");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawSystemPrivMenuItem ==
		 * null) { return getHibernateTemplate().find("from
		 * TawSystemPrivMenuItem"); } else { // filter on properties set in the
		 * tawSystemPrivMenuItem HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawSystemPrivMenuItem).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawSystemPrivMenuItem.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuItemDao#getTawSystemPrivMenuItem(String
	 *      id)
	 */
	public TawSystemPrivMenuItem getTawSystemPrivMenuItem(final String id) {
		TawSystemPrivMenuItem tawSystemPrivMenuItem = (TawSystemPrivMenuItem) getHibernateTemplate()
				.get(TawSystemPrivMenuItem.class, id);
		if (tawSystemPrivMenuItem == null) {
			throw new ObjectRetrievalFailureException(
					TawSystemPrivMenuItem.class, id);
		}

		return tawSystemPrivMenuItem;
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuItemDao#saveTawSystemPrivMenuItem(TawSystemPrivMenuItem
	 *      tawSystemPrivMenuItem)
	 */
	public void saveTawSystemPrivMenuItem(
			final TawSystemPrivMenuItem tawSystemPrivMenuItem) {
		if ((tawSystemPrivMenuItem.getId() == null)
				|| (tawSystemPrivMenuItem.getId().equals("")))
			getHibernateTemplate().save(tawSystemPrivMenuItem);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemPrivMenuItem);
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuItemDao#removeTawSystemPrivMenuItem(String
	 *      id)
	 */
	public void removeTawSystemPrivMenuItem(final String id) {
		getHibernateTemplate().delete(getTawSystemPrivMenuItem(id));
	}

	/**
	 * 閿熸枻鎷烽敓鑺傚嚖鎷烽〉閿熸枻鎷风ず curPage 閿熸枻鎷峰墠椤甸敓鏂ゆ嫹 pageSize 姣忛〉閿熸枻鎷风ず閿熸枻鎷� whereStr
	 * where閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鎴掞紝閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�"where"閿熸枻鎷峰ご,閿熸枻鎷烽敓鏂ゆ嫹涓洪敓鏂ゆ嫹
	 */
	public Map getTawSystemPrivMenuItems(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the tawSystemPrivMenuItem
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemPrivMenuItem";
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

	public Map getTawSystemPrivMenuItems(final Integer curPage,
			final Integer pageSize) {
		return this.getTawSystemPrivMenuItems(curPage, pageSize, null);
	}

	// //////////////////////////////////////////////////////////////////////////
	// // 鐎圭偟骞嘔TawSystemPrivMenuCommonDao閹恒儱褰涚�规矮绠熼柈銊ュ瀻
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.ITawSystemPrivMenuCommonDao#getAllMenus()
	 */
	public List getAllMenus() {
		return getHibernateTemplate().find("from TawSystemPrivMenu");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.ITawSystemPrivMenuCommonDao#getFirLevelMenus(java.lang.String)
	 */
	public List getFirLevelMenus(String privid) {
		String _strSQLClause = "from TawSystemPrivMenuItem where parentcode='"
				+ privid + "'";
		return getHibernateTemplate().find(_strSQLClause);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.ITawSystemPrivMenuCommonDao#getMenuNameEx(java.lang.String)
	 */
	public Object getMenuName(String privid) {
		String _strSQLClause = "from TawSystemPrivMenu where privid='" + privid
				+ "'";
		return getHibernateTemplate().find(_strSQLClause).get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.ITawSystemPrivMenuCommonDao#getNextLevelMenus(java.lang.String,
	 *      java.lang.String)
	 */
	public List getNextLevelMenus(String privid, String code) {
		String hql = "select mentitem from TawSystemPrivMenuItem mentitem , TawSystemPrivOperation operation where mentitem.menuid='"
				+ privid + "' and mentitem.parentcode='" + code + "'" + " and mentitem.parentcode = operation.parentcode and mentitem.code = operation.code order by operation.orderby" ;
		List list = new ArrayList();
		list = getHibernateTemplate().find(hql);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.ITawSystemPrivMenuCommonDao#getSelfMenus(java.lang.String)
	 */
	public List getSelfMenus(String _strOwnerId) {
		String _strSQLClause = "from TawSystemPrivMenu where ownerId='"
				+ _strOwnerId + "'";
		return getHibernateTemplate().find(_strSQLClause);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuItemDao#getSubMenuItems(int
	 *      flag, java.util.List, java.util.List)
	 */
	public List getSubMenuItems(List _objTotals, List _objExists) {
		if (_objExists == null) {
			return _objTotals;
		}

		List _objReturn = new ArrayList();

		int iTotalCount = _objTotals.size();
		int iExistsCount = _objExists.size();

		for (int i = 0; i < iTotalCount; i++) {
			TawSystemPrivUserAssign _objMenuItem1 = null;
			String _strItemId = "";
			_objMenuItem1 = (TawSystemPrivUserAssign) _objTotals.get(i);
			_strItemId = _objMenuItem1.getCurrentprivid();

			boolean _bIsExists = false;
			for (int j = 0; j < iExistsCount; j++) {
				TawSystemPrivMenuItem _objMenuItem2 = (TawSystemPrivMenuItem) _objExists
						.get(j);
				if (_objMenuItem2.getCode().equals(_strItemId)) {
					_bIsExists = true;
					break;
				}
			}

			if (!_bIsExists) {
				_objReturn.add(_objMenuItem1);
			}
		}

		if (_objReturn.size() <= 0) {
			return null;
		}
		return _objReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuItemDao#getValidSubMenuItems(java.lang.String,
	 *      java.lang.String)
	 */
	public List getValidSubMenuItems(String _strUserId, String _strParentItemId) {
		String _strSQLClause = "from TawSystemPrivUserAssign"
				+ " where userid='" + _strUserId + "' and parentprivid='"
				+ _strParentItemId + "'";
		return getHibernateTemplate().find(_strSQLClause);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuItemDao#saveParentMenuItems(java.lang.String,
	 *      java.lang.String)
	 */
	public void saveParentMenuItems(String privid, String code) {

		// 閸掋倖鏌囬幍锟界憰浣瑰絻閸忋儳娈戦懞鍌滃仯閸︺劌鍩楃�规俺褰嶉崡鏇熸煙濡楀牅鑵戦弰顖氭儊鐎涙ê婀�
		if (checkIsExists(privid, code)) {
			return;
		}

		// 閹笛嗩攽閹绘帒鍙�
		String _strSQLClause = "from TawSystemPrivOperation where code='"
				+ code + "'";
		List _objRtn = getHibernateTemplate().find(_strSQLClause);
		TawSystemPrivOperation _objOpt = (TawSystemPrivOperation) _objRtn
				.get(0);
		TawSystemPrivMenuItem _objMenuItem = new TawSystemPrivMenuItem();

		String _strIsApp = _objOpt.getIsApp();
		if (StaticVariable.AREA_DEFAULT_STRONE.equals(_strIsApp)) {
			_objMenuItem.setIsLeaf(StaticVariable.AREA_DEFAULT_STRONE);
			_objMenuItem.setIsApp(StaticVariable.AREA_DEFAULT_STRONE);
		} else {
			_objMenuItem.setIsLeaf(StaticVariable.AREA_DEFAULT_STRZERO);
			_objMenuItem.setIsApp(StaticVariable.AREA_DEFAULT_STRZERO);
		}
		_objMenuItem.setIsHide(StaticVariable.AREA_DEFAULT_STRZERO);
		_objMenuItem.setMenuid(privid);
		_objMenuItem.setCode(_objOpt.getCode());
		if (!StaticVariable.AREA_DEFAULT_PARENTVAL.equals(_objOpt
				.getParentcode())) {
			_objMenuItem.setParentcode(_objOpt.getParentcode());
		} else {
			_objMenuItem.setParentcode(privid);
		}
		_objMenuItem.setRemark("");
		saveTawSystemPrivMenuItem(_objMenuItem);

		if (!StaticVariable.AREA_DEFAULT_PARENTVAL.equals(_objOpt
				.getParentcode())) {
			saveParentMenuItems(privid, _objOpt.getParentcode());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuItemDao#checkIsExists(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean checkIsExists(String privid, String code) {
		String _strSQLClause = "from TawSystemPrivMenuItem where code='" + code
				+ "' and menuid='" + privid + "'";

		List _objRtn = getHibernateTemplate().find(_strSQLClause);
		if (_objRtn.size() == 0 || _objRtn.get(0) == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 閺嶈宓丳RIVID閸滃瓔ODE閺屻儴顕楅弻鎰綅閸楁洝顔囪ぐ锟�
	 * 
	 * @param privid
	 * @param code
	 * @return
	 */
	public TawSystemPrivMenuItem getMenuItemByPrividAndCode(String privid,
			String code) {
		String hql = "from TawSystemPrivMenuItem mentitem where mentitem.menuid='"
				+ privid + "' and mentitem.code='" + code + "'";

		return (TawSystemPrivMenuItem) getHibernateTemplate().find(hql).get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuItemDao#listTawSystemPrivMenuItems(java.lang.String)
	 */
	public List listTawSystemPrivMenuItems(String menuId) {
		return (List) getHibernateTemplate().find(
				"from TawSystemPrivMenuItem where menuid='" + menuId + "'");
	}

}
