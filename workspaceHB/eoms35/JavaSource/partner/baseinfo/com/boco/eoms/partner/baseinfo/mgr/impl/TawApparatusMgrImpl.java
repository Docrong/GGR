package com.boco.eoms.partner.baseinfo.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partner.baseinfo.model.TawApparatus;
import com.boco.eoms.partner.baseinfo.mgr.TawApparatusMgr;
import com.boco.eoms.partner.baseinfo.dao.TawApparatusDao;

/**
 * <p>
 * Title:仪器仪表
 * </p>
 * <p>
 * Description:仪器仪表
 * </p>
 * <p>
 * Wed Feb 04 16:31:56 CST 2009
 * </p>
 * 
 * @author fengshaohong
 * @version 3.5
 * 
 */
public class TawApparatusMgrImpl implements TawApparatusMgr {
 
	private TawApparatusDao  tawApparatusDao;
 	
	public TawApparatusDao getTawApparatusDao() {
		return this.tawApparatusDao;
	}
 	
	public void setTawApparatusDao(TawApparatusDao tawApparatusDao) {
		this.tawApparatusDao = tawApparatusDao;
	}
 	
    public List getTawApparatuss() {
    	return tawApparatusDao.getTawApparatuss();
    }
    
    public TawApparatus getTawApparatus(final String id) {
    	return tawApparatusDao.getTawApparatus(id);
    }
    
    public void saveTawApparatus(TawApparatus tawApparatus) {
    	tawApparatusDao.saveTawApparatus(tawApparatus);
    }
    
    public void removeTawApparatus(final String id) {
    	tawApparatusDao.removeTawApparatus(id);
    }
    
    public Map getTawApparatuss(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return tawApparatusDao.getTawApparatuss(curPage, pageSize, whereStr);
	}
   
	public Boolean isunique(final String apparatusId){
		return tawApparatusDao.isunique(apparatusId);
	}
	public String name2Id(final String dictName,final String parentDictId){
		return tawApparatusDao.name2Id(dictName,parentDictId);
	}
}