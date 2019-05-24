package com.boco.eoms.datum.dao.hibernate;

import java.util.*;

import org.hibernate.HibernateException;

import com.boco.eoms.datum.dao.ITawBureaudataCityzoneDao;
import com.boco.eoms.datum.model.TawBureaudataCityzone;
import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;

public class TawBureaudataCityzoneHibernate  extends BaseSheetDaoHibernate implements ITawBureaudataCityzoneDao {

	public Map getAllCityIntoMapKeyZoneNum() throws HibernateException{
		Map map = new HashMap();
		String sql = "from TawBureaudataCityzone";
		List list = this.getHibernateTemplate().find(sql);
		for(int i = 0;i<list.size();i++){
			TawBureaudataCityzone obj = (TawBureaudataCityzone) list.get(i);
			map.put(obj.getZonenum(), obj);
		}
		return map;
	}

	public Map getAllCityIntoMapKeyCityName() throws HibernateException{
		Map map = new HashMap();
		String sql = "from TawBureaudataCityzone";
		List list = this.getHibernateTemplate().find(sql);
		for(int i = 0;i<list.size();i++){
			TawBureaudataCityzone obj = (TawBureaudataCityzone) list.get(i);
			map.put(obj.getCityname(), obj);
		}
		return map;
	}

	public List loadList(String hql) throws HibernateException {
		return getHibernateTemplate().find(hql);
	}

	public void saveOrUpdate(Object o) throws HibernateException {
		super.saveObject(o);
		
	}

}
