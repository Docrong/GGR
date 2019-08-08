package com.boco.eoms.sheet.limit.dao.hibernate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.sheet.expert.model.TawSheetExpert;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.limit.dao.ISheetDealLimitDAO;
import com.boco.eoms.sheet.limit.model.LevelLimit;
import com.boco.eoms.sheet.limit.model.StepLimit;


public class SheetDealLimitDAOImpl extends BaseDaoHibernate implements ISheetDealLimitDAO {

    /**
     * 保存
     *
     * @param Proxy the object to be saved
     */
    public void saveStepLimit(final StepLimit stepLimit) throws Exception {
        if ((stepLimit.getId() == null) || stepLimit.getId().equals("")) {
            String id = UUIDHexGenerator.getInstance().getID();
            stepLimit.setId(id);
            getHibernateTemplate().save(stepLimit);
        } else {
            getHibernateTemplate().saveOrUpdate(stepLimit);
        }
    }

    public void saveLevelLimit(LevelLimit levelLimit) throws Exception {
        String id = levelLimit.getId();
        if (id == null || id.equals("")) {
            levelLimit.setId(UUIDHexGenerator.getInstance().getID());
        }
        getHibernateTemplate().saveOrUpdate(levelLimit);
    }

    /**
     * 删除
     *
     * @param Proxy the object to be removed
     */
    public void removeStepLimit(final String Id) {
        getHibernateTemplate().delete(getStepLimit(Id));
    }

    public List getStepLimitByLevel(final String levelId) {
        String hql = " from StepLimit stepLimit where levelId='" + levelId + "' order by stepLimit.stepId asc";
        return getHibernateTemplate().find(hql);
    }

    public void removeLevelLimit(final String Id) {
        getHibernateTemplate().delete(getLevelLimit(Id));
    }

    /**
     * 获取
     */
    public StepLimit getStepLimit(final String Id) {
        StepLimit tempLimit = (StepLimit) getHibernateTemplate().get(StepLimit.class, Id);
        if (tempLimit == null) {
            throw new ObjectRetrievalFailureException(StepLimit.class, Id);
        }
        return tempLimit;
    }

    /**
     * 获取
     */
    public LevelLimit getLevelLimit(final String Id) {
        LevelLimit tempLimit = (LevelLimit) getHibernateTemplate().get(LevelLimit.class, Id);
        if (tempLimit == null) {
            throw new ObjectRetrievalFailureException(LevelLimit.class, Id);
        }
        return tempLimit;
    }

    public List getLevelLimitByCondition(String condition) {

        String hql = " from LevelLimit levelLimit where " + condition + " order by levelLimit.specialty1 desc";

        return getHibernateTemplate().find(hql);
    }

    /**
     * 查询
     *
     * @param SheetLimit the object to be saved
     */
    public List getStepLimitList(String flowName) {
        return getHibernateTemplate().find("from StepLimit stepLimit where stepLimit.levelId in (select id from LevelLimit levelLimit where flowName='" + flowName + "')");
    }

    /**
     * 查询
     *
     * @param SheetLimit the object to be saved
     */
    public List getLevelLimitList(String flowName) {
        return getHibernateTemplate().find("from LevelLimit levelLimit where levelLimit.flowName = '" + flowName + "'");
    }

    /**
     * 根据特定的条件查询Overtime记录
     *
     * @param condition
     * @return List
     */
    public List getStepLimitByCondition(HashMap condition) throws HibernateException {
        Iterator it = condition.keySet().iterator();
        StringBuffer sb = new StringBuffer();
        while (it.hasNext()) {
            String column = (String) it.next();
            String value = (String) condition.get(column);
            sb.append(" stepLimit." + column + value + " and");
        }
        String wherestr = sb.toString().substring(0, sb.length() - 4);
        String hql = " from StepLimit stepLimit where " + wherestr + " order by stepLimit.specialty2 asc,stepLimit.specialty3 desc,stepLimit.specialty4 desc ";

        return getHibernateTemplate().find(hql);
    }
}
