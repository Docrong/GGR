
package com.boco.eoms.businessupport.product.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import net.sf.json.JSONArray;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.businessupport.product.dao.ITransferSpecialLineDao;
import com.boco.eoms.businessupport.product.dao.ITransferSpecialLineJdbc;
import com.boco.eoms.businessupport.product.service.ITransferSpecialLineManager;

public class TransferSpecialLineManagerImpl extends BaseManager implements ITransferSpecialLineManager {
    private ITransferSpecialLineDao transferspeciallineDao;
    private ITransferSpecialLineJdbc transferspeciallineJdbc;

    public List getChildList(String parentId) {
        // TODO Auto-generated method stub
        return transferspeciallineDao.getChildList(parentId);
    }

    public String getMaxCode() {
        // TODO Auto-generated method stub
        return transferspeciallineJdbc.getMaxCode();
    }

    public TransferSpecialLine getTransferSpecialLine(String id) {
        // TODO Auto-generated method stub
        return transferspeciallineDao.getTransferSpecialLine(id);
    }

    public List getTransferSpecialLines() {
        // TODO Auto-generated method stub
        return transferspeciallineDao.getTransferSpecialLines();
    }

    public Map getTransferSpecialLines(Integer curPage, Integer pageSize) {
        // TODO Auto-generated method stub
        return transferspeciallineDao.getTransferSpecialLines(curPage, pageSize);
    }

    public Map getTransferSpecialLines(Integer curPage, Integer pageSize, String whereStr) {
        // TODO Auto-generated method stub
        return transferspeciallineDao.getTransferSpecialLines(curPage, pageSize, whereStr);
    }

    public List getTransferSpecialLinesByProCode(String proCode, String moduleName) {
        // TODO Auto-generated method stub
        String hql = " from " + moduleName + " where mainProductCode = '" + "' and templateFlag <> 1";
        return transferspeciallineDao.getTransferSpecialLinesByHql(hql);
    }

    public List getTransferSpecialLinesByTxtwords(Integer newDeleted, String txtwords, String moduleName) {
        // TODO Auto-generated method stub
        if (txtwords.trim().equalsIgnoreCase("")) {
            String hql = " from " + moduleName + " where keyword ='" + "' and deleted = " + newDeleted + "";
            return transferspeciallineDao.getTransferSpecialLinesByHql(hql);
        } else {
            String hql = " from " + moduleName + " where keyword like '%" + txtwords + "%'and deleted = " + newDeleted + "";
            return transferspeciallineDao.getTransferSpecialLinesByHql(hql);
        }
    }

    public List getTransferSpecialLinesDeleted() {
        // TODO Auto-generated method stub
        return transferspeciallineDao.getTransferSpecialLinesDeleted();
    }

    public void removeTransferSpecialLine(String id) {
        // TODO Auto-generated method stub
        transferspeciallineDao.removeTransferSpecialLine(new String(id));
    }

    public void restoreTransferSpecialLine(String id) {
        // TODO Auto-generated method stub
        transferspeciallineDao.restoreTransferSpecialLine(new String(id));
    }

    public void saveTransferSpecialLine(TransferSpecialLine transferspecialline) {
        // TODO Auto-generated method stub
        transferspeciallineDao.saveTransferSpecialLine(transferspecialline);
    }

    public List getSpecialLines(String id) {
        // TODO Auto-generated method stub
        return transferspeciallineDao.getSpecialLines(id);
    }

    public Map getQueryTransferSpecialLines(final Map queryMap, final Integer curPage, final Integer pageSize,
                                            final String whereStr) {
        return transferspeciallineDao.getQueryTransferSpecialLines(queryMap, curPage, pageSize, whereStr);
    }

    public TransferSpecialLine getSpecialLineById(String id) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public ITransferSpecialLineDao getTransferspeciallineDao() {
        return transferspeciallineDao;
    }

    public void setTransferspeciallineDao(ITransferSpecialLineDao transferspeciallineDao) {
        this.transferspeciallineDao = transferspeciallineDao;
    }

    public ITransferSpecialLineJdbc getTransferspeciallineJdbc() {
        return transferspeciallineJdbc;
    }

    public void setTransferspeciallineJdbc(ITransferSpecialLineJdbc transferspeciallineJdbc) {
        this.transferspeciallineJdbc = transferspeciallineJdbc;
    }

    public void saveOrUpdate(TransferSpecialLine transferspecialline) throws HibernateException {
        this.getTransferspeciallineDao().saveOrUpdate(transferspecialline);
    }

    public JSONArray xGetChildNodes(String parentId) {
        // TODO Auto-generated method stub
        return null;
    }

    //	根据电路名称得到对象
    public TransferSpecialLine getTransferByCircuitName(String circuitName) throws HibernateException {
        return transferspeciallineDao.getTransferByCircuitName(circuitName);
    }

    /**
     * 通过A端站点名称和Z端站点名称查找电路
     *
     * @param siteNameA
     * @param siteNameZ
     * @return
     */
    public TransferSpecialLine getSpecialLinesBySiteName(String siteNameA, String siteNameZ) throws Exception {
        List list = transferspeciallineDao.getSpecialLinesBySiteName(siteNameA, siteNameZ);
        if (list != null && list.size() > 0)
            return (TransferSpecialLine) list.get(0);
        else
            return null;
    }

    /**
     * 通过Z端业务设备端口查找电路
     */
    public TransferSpecialLine getSpecialLineByZPort(String portZBDeviceName, String portZBDevicePort) throws Exception {
        List list = transferspeciallineDao.getSpecialLineByZPort(portZBDeviceName, portZBDevicePort);
        if (list != null && list.size() > 0)
            return (TransferSpecialLine) list.get(0);
        else
            return null;
    }
}
