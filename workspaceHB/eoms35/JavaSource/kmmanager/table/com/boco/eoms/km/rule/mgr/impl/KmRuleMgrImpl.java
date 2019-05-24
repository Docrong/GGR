package com.boco.eoms.km.rule.mgr.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.core.dataservice.database.OracleDataBase;
import com.boco.eoms.km.knowledge.dao.jdbc.KmContentsDaoJdbc;
import com.boco.eoms.km.knowledge.model.KmContents;
import com.boco.eoms.km.rule.model.KmRule;
import com.boco.eoms.km.rule.mgr.KmRuleMgr;
import com.boco.eoms.km.rule.dao.KmRuleDao;

/**
 * <p>
 * Title:规则库
 * </p>
 * <p>
 * Description:规则库
 * </p>
 * <p>
 * Fri Apr 17 16:06:45 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public class KmRuleMgrImpl implements KmRuleMgr {
 
	private KmRuleDao  kmRuleDao;
 	
	public KmRuleDao getKmRuleDao() {
		return this.kmRuleDao;
	}
 	
	public void setKmRuleDao(KmRuleDao kmRuleDao) {
		this.kmRuleDao = kmRuleDao;
	}
 	
    public List getKmRules() {
    	return kmRuleDao.getKmRules();
    }
    
    public KmRule getKmRule(final String id) {
    	return kmRuleDao.getKmRule(id);
    }
    
    public void saveKmRule(KmRule kmRule) {
    	kmRuleDao.saveKmRule(kmRule);
    }
    
    public void removeKmRule(final String id) {
    	kmRuleDao.removeKmRule(id);
    }
    
    private KmContentsDaoJdbc  kmContentsDaoJdbc;    
    
    public KmContentsDaoJdbc getKmContentsDaoJdbc() {
		return kmContentsDaoJdbc;
	}

	public void setKmContentsDaoJdbc(KmContentsDaoJdbc kmContentsDaoJdbc) {
		this.kmContentsDaoJdbc = kmContentsDaoJdbc;
	}
    
    public Map getKmRules(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmRuleDao.getKmRules(curPage, pageSize, whereStr);
	}
    public List getNextLevelKmRules(String parentNodeId) {
		return kmRuleDao.getNextLevelKmRules(parentNodeId);
	}
    public List getQueryList(String sql,List valueList,List typeList,final Integer curPage, final Integer pageSize){    	   	
//    	sql="select *　from KM_CONTENTS_1240469055468 where　CREATE_USER = ? and CREATE_DEPT != ? and CREATE_TIME>?";
//		valueList.add("admin");		
//		valueList.add("dept");
//		valueList.add("2009-01-10 00:00:00");
//		dataTypeList.add(12);
//		dataTypeList.add(12);
//		dataTypeList.add(91);
		List objectList= new ArrayList();
		List dataTypeList= new ArrayList();	
		OracleDataBase oracleDataBase=null;
//		try {
//			oracleDataBase = OracleDataBase.class.newInstance();
//		} catch (InstantiationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IllegalAccessException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 
		Object obj = null;
		for(int i=0;i<valueList.size();i++){
			Integer value=new Integer(typeList.get(i)+"");
//			try {				
//				obj = oracleDataBase.convStringToObject((String)valueList.get(i), value, "");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			dataTypeList.add(value);
			objectList.add(obj);
		}		
		List result = kmContentsDaoJdbc.list(sql, objectList, dataTypeList, curPage, pageSize);
		return result;
    	
    }
    public String checkSql(String sql){    	
    	return kmRuleDao.checkSql(sql);    	
    }
}