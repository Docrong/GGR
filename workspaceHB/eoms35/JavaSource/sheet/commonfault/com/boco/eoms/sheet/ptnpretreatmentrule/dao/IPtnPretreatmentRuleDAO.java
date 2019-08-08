package com.boco.eoms.sheet.ptnpretreatmentrule.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;

public interface IPtnPretreatmentRuleDAO extends Dao {

    /**
     * 分页查询
     *
     * @param pageSize
     * @param pageIndex
     * @param condition
     * @return
     */
    public abstract Map getRuleListByCondition(final Integer pageSize, final Integer pageIndex, final String condition);

    /**
     * 保存或更新
     */
    public abstract void saveObject(Object obj);

    /**
     * 根据条件查询
     *
     * @param condition
     * @return
     */
    public abstract List getListByCondition(String condition);

}
