package com.boco.eoms.sheet.autotransmitrule.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.autotransmitrule.model.AutoTransmitRule;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:29:53
 * </p>
 *
 * @author zhangying
 * @version 1.0
 */
public interface IAutoTransmitRuleDAO extends Dao {
    /**
     * 保存AutoTransmitRule
     *
     * @param AutoTransmitRule
     */
    public void saveAutoTransmitRule(AutoTransmitRule commonfaultRule) throws HibernateException;

    /**
     * 删除AutoTransmitRule
     *
     * @param id
     */
    public void removeAutoTransmitRule(String id) throws HibernateException;

    /**
     * 根据id查询commonfaultrule记录
     *
     * @param id
     * @return AutoTransmitRule
     */
    public AutoTransmitRule getAutoTransmitRule(String id) throws HibernateException;

    /**
     * 得到所有的记录
     */
    public HashMap getAllAutoTransmitRule(Integer pageIndex, Integer pageSize) throws HibernateException;
}
