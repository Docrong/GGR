package com.boco.eoms.sheet.tool.limit.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.sheet.expert.model.TawSheetExpert;
import com.boco.eoms.sheet.tool.limit.dao.ISheetLimitDAO;
import com.boco.eoms.sheet.tool.limit.model.SheetLimit;


public class SheetLimitDAOImpl extends BaseDaoHibernate implements ISheetLimitDAO {

    /**
     * 保存
     *
     * @param Proxy the object to be saved
     */
    public void saveSheetLimit(final SheetLimit sheetLimit) {
        if ((sheetLimit.getId() == null) || sheetLimit.getId().equals("")) {
            getHibernateTemplate().save(sheetLimit);
        } else {

            getHibernateTemplate().saveOrUpdate(sheetLimit);
        }
    }

    /**
     * 删除
     *
     * @param Proxy the object to be removed
     */
    public void removeSheetLimit(final Integer Id) {
        getHibernateTemplate().delete(getSheetLimit(Id));
    }

    /**
     * 获取
     */
    public SheetLimit getSheetLimit(final Integer Id) {
        SheetLimit tempLimit = (SheetLimit) getHibernateTemplate().get(SheetLimit.class, Id);
        if (tempLimit == null) {
            throw new ObjectRetrievalFailureException(SheetLimit.class, Id);
        }
        return tempLimit;
    }

    /**
     * 查询
     *
     * @param SheetLimit the object to be saved
     */
    public List getSheetLimitList(final SheetLimit sheetLimit) {
        return getHibernateTemplate().find("from SheetLimit sl where sl.moudleId = 'stepLimit'");
    }

    /**
     * 查询
     *
     * @param SheetLimit the object to be saved
     */
    public List getDealLimitList(final SheetLimit sheetLimit) {
        return getHibernateTemplate().find("from SheetLimit where moudleId = 'dealLimit'");
    }

    /**
     * 根据专业查询列表
     *
     * @param SheetLimit the object to be saved
     */
    public SheetLimit getSheetLimitBySpecial(String special1, String special2) {

        String hql = " from SheetLimit sl where sl.specialty1='" + special1 + "' and sl.specialty2 = '" + special2 + "'";
        SheetLimit sheetLimit = new SheetLimit();
        if (getHibernateTemplate().find(hql).size() > 0) {
            sheetLimit = (SheetLimit) getHibernateTemplate().find(hql).get(0);
        }
        return sheetLimit;
    }

    /**
     * 根据专业查询列表
     *
     * @param SheetLimit the object to be saved
     */
    public SheetLimit getSheetLimitBySpecial(String special1, String special2, String special3, String special4) {

        String hql = " from SheetLimit sl where (sl.specialty1='" + special1 + "'or sl.specialty1 is null or sl.specialty1='')  and (sl.specialty2 = '" + special2 + "'or sl.specialty2 is null or sl.specialty2='') and (sl.specialty3 = '" + special3 + "'or sl.specialty3 is null or sl.specialty3 ='') and (sl.specialty4 = '" + special4 + "'or sl.specialty4 is null or sl.specialty4='') and sl.moudleId = 'stepLimit'";
        SheetLimit sheetLimit = new SheetLimit();
        List list = getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                sheetLimit = (SheetLimit) list.get(i);
            }
        }
        return sheetLimit;
    }

    /**
     * 根据专业查询列表
     *
     * @param SheetLimit the object to be saved
     */
    public SheetLimit getSheetLimitBySpecial(String special) {

        String hql = " from SheetLimit sl where sl.moudleId = 'dealLimit' and sl.specialty2 = '" + special + "'";
        SheetLimit sheetLimit = new SheetLimit();
        if (getHibernateTemplate().find(hql).size() > 0) {
            sheetLimit = (SheetLimit) getHibernateTemplate().find(hql).get(0);
        }
        return sheetLimit;
    }

    /**
     * 根据网络三级分类和故障处理响应级别查询时限
     * special4 网络三级分类
     */
    public SheetLimit getSheetLimitByLastSpecial(String special4, String special2) {
        String hql = " from SheetLimit sl where sl.specialty2='" + special2 + "' and sl.specialty4 = '" + special4 + "'";
        SheetLimit sheetLimit = null;
        if (getHibernateTemplate().find(hql).size() > 0) {
            sheetLimit = (SheetLimit) getHibernateTemplate().find(hql).get(0);
        }
        return sheetLimit;
    }

}
