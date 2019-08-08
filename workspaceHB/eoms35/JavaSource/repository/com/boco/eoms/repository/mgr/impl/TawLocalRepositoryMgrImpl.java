package com.boco.eoms.repository.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.repository.dao.TawLocalRepositoryDao;
import com.boco.eoms.repository.mgr.TawLocalRepositoryMgr;
import com.boco.eoms.repository.model.TawLocalRepository;
import com.boco.eoms.repository.model.TawLocalRepositoryUp;

/**
 * <p>
 * Title:tawLocalRepository
 * </p>
 * <p>
 * Description:tawLocalRepository
 * </p>
 * <p>
 * Fri Oct 30 09:14:26 CST 2009
 * </p>
 *
 * @author 李锋
 * @version 1.0
 */
public class TawLocalRepositoryMgrImpl implements TawLocalRepositoryMgr {

    private TawLocalRepositoryDao tawLocalRepositoryDao;

    public TawLocalRepositoryDao getTawLocalRepositoryDao() {
        return this.tawLocalRepositoryDao;
    }

    public void setTawLocalRepositoryDao(TawLocalRepositoryDao tawLocalRepositoryDao) {
        this.tawLocalRepositoryDao = tawLocalRepositoryDao;
    }

    public List getTawLocalRepositorys() {
        return tawLocalRepositoryDao.getTawLocalRepositorys();
    }

    public TawLocalRepository getTawLocalRepository(final String id) {
        return tawLocalRepositoryDao.getTawLocalRepository(id);
    }

    public TawLocalRepository getTawLocalRepositorybyname(final String name) {
        return tawLocalRepositoryDao.getTawLocalRepositorybyname(name);
    }

    public void saveTawLocalRepository(TawLocalRepository tawLocalRepository) {
        tawLocalRepositoryDao.saveTawLocalRepository(tawLocalRepository);
    }

    public void updateTawLocalRepository(TawLocalRepository tawLocalRepository) {
        tawLocalRepositoryDao.updateTawLocalRepository(tawLocalRepository);
    }

    public void saveTawLocalRepositoryUp(TawLocalRepositoryUp tawLocalRepositoryUp) {
        tawLocalRepositoryDao.saveTawLocalRepositoryUp(tawLocalRepositoryUp);
    }

    public void removeTawLocalRepository(final String id) {
        tawLocalRepositoryDao.removeTawLocalRepository(id);
    }

    public Map getTawLocalRepositorys(final Integer curPage, final Integer pageSize,
                                      final String whereStr) {
        return tawLocalRepositoryDao.getTawLocalRepositorys(curPage, pageSize, whereStr);
    }

    public Map getTawLocalRepositorysHistory(final Integer curPage, final Integer pageSize,
                                             final String whereStr) {
        return tawLocalRepositoryDao.getTawLocalRepositorysHistory(curPage, pageSize, whereStr);
    }

    public TawLocalRepositoryUp getTawLocalRepositorybysheetkey(String sheetkey) {
        // TODO Auto-generated method stub
        return tawLocalRepositoryDao.getTawLocalRepositorybySheetkey(sheetkey);
    }

}