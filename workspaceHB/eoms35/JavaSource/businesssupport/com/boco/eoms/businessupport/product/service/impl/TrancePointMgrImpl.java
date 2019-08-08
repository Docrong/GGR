package com.boco.eoms.businessupport.product.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.businessupport.product.model.TrancePoint;
import com.boco.eoms.businessupport.product.service.TrancePointMgr;
import com.boco.eoms.businessupport.product.dao.TrancePointDao;

/**
 * <p>
 * Title:业务接入点
 * </p>
 * <p>
 * Description:业务接入点
 * </p>
 * <p>
 * Sun May 16 14:18:55 CST 2010
 * </p>
 *
 * @author lizhigang
 * @version 3.5
 */
public class TrancePointMgrImpl implements TrancePointMgr {

    private TrancePointDao businessupportDao;

    public TrancePointDao getBusinessupportDao() {
        return this.businessupportDao;
    }

    public void setBusinessupportDao(TrancePointDao businessupportDao) {
        this.businessupportDao = businessupportDao;
    }

    public List getBusinessupports() {
        return businessupportDao.getBusinessupports();
    }

    public TrancePoint getBusinessupport(final String id) {
        return businessupportDao.getBusinessupport(id);
    }

    public void saveBusinessupport(TrancePoint businessupport) {
        businessupportDao.saveBusinessupport(businessupport);
    }

    public void removeBusinessupport(final String id) {
        businessupportDao.removeBusinessupport(id);
    }

    public Map getBusinessupports(final Integer curPage, final Integer pageSize,
                                  final String whereStr) {
        return businessupportDao.getBusinessupports(curPage, pageSize, whereStr);
    }

    public List getBusinessupportBySheetId(final String orderSheetId) {
        return businessupportDao.getBusinessupportBySheetId(orderSheetId);
    }

    public TrancePoint getTrancePointBySheetAndId(final String orderSheetId, final String portDetailAdd) {
        List list = businessupportDao.getTrancePointBySheetAndId(orderSheetId, portDetailAdd);
        if (list != null && list.size() > 0)
            return (TrancePoint) list.get(0);
        else
            return null;
    }

    /**
     * 删除定单所有接入点信息
     *
     * @param orderId
     * @throws Exception
     */
    public void removeByOrderId(String orderId) throws Exception {
        List list = this.getBusinessupportBySheetId(orderId);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                TrancePoint p = (TrancePoint) list.get(i);
                this.removeBusinessupport(p.getId());
            }
        }
    }

}