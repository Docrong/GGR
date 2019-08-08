package com.boco.eoms.sheet.commonfault.dao;

import java.util.Map;

public interface ICommonFaultQCDAO {
    /**
     * 根据sql进行分页查询
     *
     * @param sql
     * @param curPage
     * @param pageSize
     * @return
     */
    public abstract Map selectObject(String sql, final Integer curPage, final Integer pageSize);

    /**
     * 查询结果集记录条数
     *
     * @param sql
     * @return
     */
    public abstract int getTotalBySql(String sql);
}
