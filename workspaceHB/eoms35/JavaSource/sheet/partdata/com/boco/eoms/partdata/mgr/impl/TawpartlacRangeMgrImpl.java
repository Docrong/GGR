package com.boco.eoms.partdata.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partdata.model.TawpartlacRange;
import com.boco.eoms.partdata.mgr.TawpartlacRangeMgr;
import com.boco.eoms.partdata.dao.TawpartlacRangeDao;

/**
 * <p>
 * Title:LAC码号地市分配
 * </p>
 * <p>
 * Description:LAC码号地市分配
 * </p>
 * <p>
 * Mon Jul 12 09:21:06 CST 2010
 * </p>
 *
 * @author fengshaohong
 * @version 3.6
 */
public class TawpartlacRangeMgrImpl implements TawpartlacRangeMgr {

    private TawpartlacRangeDao tawpartlacRangeDao;

    public TawpartlacRangeDao getTawpartlacRangeDao() {
        return this.tawpartlacRangeDao;
    }

    public void setTawpartlacRangeDao(TawpartlacRangeDao tawpartlacRangeDao) {
        this.tawpartlacRangeDao = tawpartlacRangeDao;
    }

    public List getTawpartlacRanges() {
        return tawpartlacRangeDao.getTawpartlacRanges();
    }

    public TawpartlacRange getTawpartlacRange(final String id) {
        return tawpartlacRangeDao.getTawpartlacRange(id);
    }

    public void saveTawpartlacRange(TawpartlacRange tawpartlacRange) {
        tawpartlacRangeDao.saveTawpartlacRange(tawpartlacRange);
    }

    public void removeTawpartlacRange(final String id) {
        tawpartlacRangeDao.removeTawpartlacRange(id);
    }

    public Map getTawpartlacRanges(final Integer curPage, final Integer pageSize,
                                   final String whereStr) {
        return tawpartlacRangeDao.getTawpartlacRanges(curPage, pageSize, whereStr);
    }

    public boolean isavailable(String start, String end) {

        return tawpartlacRangeDao.isavailable(Integer.parseInt(start), Integer.parseInt(end));
    }

    public boolean isavailablenotself(String start, String end, String id) {
        return tawpartlacRangeDao.isavailablenotself(Integer.parseInt(start), Integer.parseInt(end), id);
    }

    public List getTawpartlacRangebyL1L2(String l1l2) {
        return tawpartlacRangeDao.getTawpartlacRangebyL1L2(l1l2);
    }
}