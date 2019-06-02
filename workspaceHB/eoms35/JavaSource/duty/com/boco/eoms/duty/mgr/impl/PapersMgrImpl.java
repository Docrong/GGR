package com.boco.eoms.duty.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.duty.model.Papers;
import com.boco.eoms.duty.model.PapersPart;
import com.boco.eoms.duty.mgr.PapersMgr;
import com.boco.eoms.duty.dao.PapersDao;

/**
 * <p>
 * Title:资料
 * </p>
 * <p>
 * Description:资料
 * </p>
 * <p>
 * Tue Apr 07 10:05:44 CST 2009
 * </p>
 * 
 * @author lixiaoming
 * @version 3.5
 * 
 */
public class PapersMgrImpl implements PapersMgr {
 
	private PapersDao  papersDao;
 	
	public PapersDao getPapersDao() {
		return this.papersDao;
	}
 	
	public void setPapersDao(PapersDao papersDao) {
		this.papersDao = papersDao;
	}
 	
    public List getPaperss() {
    	return papersDao.getPaperss();
    }
    
    public Papers getPapers(final String id) {
    	return papersDao.getPapers(id);
    }
    
    public void savePapers(Papers papers) {
    	papersDao.savePapers(papers);
    }
    public void savePapersPart(PapersPart paperspart) {
    	papersDao.savePapersPart(paperspart);
    }
    
    public void removePapers(final String id) {
    	papersDao.removePapers(id);
    }
    public void removePapersPart(final String id) {
    	papersDao.removePapersPart(id);
    }
    
    public Map getPaperss(final Integer curPage, final Integer pageSize,
			final String whereStr,String userId) {
		return papersDao.getPaperss(curPage, pageSize, whereStr,userId);
	}
    public Map searchTixing(final Integer curPage, final Integer pageSize,
			final String whereStr,String userId) {
		return papersDao.getsearchTixing(curPage, pageSize, whereStr,userId);
	}
    public Map getPerson() {
		return papersDao.getPerson();
	}
    public Map getPapersId() {
		return papersDao.getPapersId();
	}
	
}