package com.boco.eoms.km.knowledge.mgr.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.dao.KmContentsMainDao;
import com.boco.eoms.km.knowledge.mgr.KmContentsMainMgr;
import com.boco.eoms.km.knowledge.webapp.form.KmContentsForm;

/**
 * <p>
 * Title:首页知识排行
 * </p>
 * <p>
 * Description:首页知识排行
 * </p>
 * <p>
 * Wed Aug 19 15:53:50 CST 2009
 * </p>
 * 
 * @author wangzhiyong
 * @version 1.0
 * 
 */
public class KmContentsMainMgrImpl implements KmContentsMainMgr {
 
	private KmContentsMainDao  kmContentsMainDao;
 	
	public KmContentsMainDao getKmContentsMainDao() {
		return kmContentsMainDao;
	}

	public void setKmContentsMainDao(KmContentsMainDao kmContentsMainDao) {
		this.kmContentsMainDao = kmContentsMainDao;
	}
 	
    

	public List getKmContentsMain(final int count,final String type) {
		List list = kmContentsMainDao.getKmContentsMain(count,type);
		List result = new ArrayList();
		int listSize =  list.size();
		for(int i=0; i<listSize;i++){
			Object[] objs = (Object[])list.get(i);
			KmContentsForm kmContents = new KmContentsForm();
			kmContents.setTableId((String)objs[0]);
			kmContents.setContentTitle((String)objs[1]);
			kmContents.setCreateTime((String)objs[2]);
			kmContents.setCreateUser((String)objs[3]);
			kmContents.setCreateDept((String)objs[4]);
			kmContents.setId((String)objs[5]);
			kmContents.setThemeId((String)objs[6]);
			result.add(kmContents);
		}
		
		
		return result;
	}

	public Map getKmContentsMains(Integer curPage, Integer pageSize,
			String whereStr,String type) {

		Map map = kmContentsMainDao.getKmContentsMain(curPage, pageSize, whereStr,type);
		List list = (List)map.get("result");
		
		List result = new ArrayList();
		int listSize =  list.size();
		for(int i=0; i<listSize;i++){
			Object[] objs = (Object[])list.get(i);
			KmContentsForm kmContents = new KmContentsForm();
			kmContents.setTableId(((String)objs[0]).trim());
			kmContents.setContentTitle((String)objs[1]);
			kmContents.setCreateTime((String)objs[2]);
			kmContents.setCreateUser((String)objs[3]);
			kmContents.setCreateDept((String)objs[4]);
			kmContents.setId((String)objs[5]);
			kmContents.setThemeId((String)objs[6]);
			result.add(kmContents);
		}
		
		map.put("result", result);
		return map;
	}

}