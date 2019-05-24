package com.boco.eoms.km.exam.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamTestType;
import com.boco.eoms.km.exam.mgr.KmExamTestTypeMgr;
import com.boco.eoms.km.exam.dao.KmExamTestTypeDao;

/**
 * <p>
 * Title:题型信息表
 * </p>
 * <p>
 * Description:题型信息表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public class KmExamTestTypeMgrImpl implements KmExamTestTypeMgr {
 
	private KmExamTestTypeDao  kmExamTestTypeDao;
 	
	public KmExamTestTypeDao getKmExamTestTypeDao() {
		return this.kmExamTestTypeDao;
	}
 	
	public void setKmExamTestTypeDao(KmExamTestTypeDao kmExamTestTypeDao) {
		this.kmExamTestTypeDao = kmExamTestTypeDao;
	}
 	
    public List getKmExamTestTypes() {
    	return kmExamTestTypeDao.getKmExamTestTypes();
    }
    
    /**
	 * 查询某试卷下的所有类型
	 * @param testID
	 * @return
	 */
	public List getKmExamTestTypesByTestID(final String testID){
		return kmExamTestTypeDao.getKmExamTestTypesByTestID(testID);
	}
    
    public KmExamTestType getKmExamTestType(final String id) {
    	return kmExamTestTypeDao.getKmExamTestType(id);
    }
    
    public void saveKmExamTestType(KmExamTestType kmExamTestType) {
    	kmExamTestTypeDao.saveKmExamTestType(kmExamTestType);
    }
    
    public void removeKmExamTestType(final String id) {
    	kmExamTestTypeDao.removeKmExamTestType(id);
    }
    
    public Map getKmExamTestTypes(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmExamTestTypeDao.getKmExamTestTypes(curPage, pageSize, whereStr);
	}
	
}