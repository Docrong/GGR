package com.boco.eoms.partdata.mgr.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.partdata.model.TawpartLacBsc;
import com.boco.eoms.partdata.mgr.TawpartLacBscMgr;
import com.boco.eoms.partdata.dao.TawpartLacBscDao;

/**
 * <p>
 * Title:LAC的BSC分配
 * </p>
 * <p>
 * Description:LAC的BSC分配
 * </p>
 * <p>
 * Mon Jul 12 17:27:34 CST 2010
 * </p>
 *
 * @author fengshaohong
 * @version 3.6
 */
public class TawpartLacBscMgrImpl implements TawpartLacBscMgr {

    private TawpartLacBscDao tawpartLacBscDao;

    public TawpartLacBscDao getTawpartLacBscDao() {
        return this.tawpartLacBscDao;
    }

    public void setTawpartLacBscDao(TawpartLacBscDao tawpartLacBscDao) {
        this.tawpartLacBscDao = tawpartLacBscDao;
    }

    public List getTawpartLacBscs() {
        return tawpartLacBscDao.getTawpartLacBscs();
    }

    public TawpartLacBsc getTawpartLacBsc(final String id) {
        return tawpartLacBscDao.getTawpartLacBsc(id);
    }

    public void saveTawpartLacBsc(TawpartLacBsc tawpartLacBsc) {
        tawpartLacBscDao.saveTawpartLacBsc(tawpartLacBsc);
    }

    public void removeTawpartLacBsc(final String id) {
        tawpartLacBscDao.removeTawpartLacBsc(id);
    }

    public Map getTawpartLacBscs(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        return tawpartLacBscDao.getTawpartLacBscs(curPage, pageSize, whereStr);
    }

    public Map getTawpartLacBscList(final String rangeId) {
        List list = tawpartLacBscDao.getTawpartLacBscList(rangeId);
        Map map = new HashMap();
        String lac = "";
        for (int i = 0; i < list.size(); i++) {
            TawpartLacBsc tawpartLacBsc = new TawpartLacBsc();
            tawpartLacBsc = (TawpartLacBsc) list.get(i);
            lac = tawpartLacBsc.getLac();
            map.put(lac, tawpartLacBsc);
        }

        return map;
    }

    public void removeTawpartLacBscbyRangeid(String rangeId) {
        tawpartLacBscDao.removeTawpartLacBscbyRangeid(rangeId);
    }
}