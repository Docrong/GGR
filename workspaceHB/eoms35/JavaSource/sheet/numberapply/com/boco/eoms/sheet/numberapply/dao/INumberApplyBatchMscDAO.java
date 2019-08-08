package com.boco.eoms.sheet.numberapply.dao;

import java.util.List;

public interface INumberApplyBatchMscDAO {

    /**
     * 批量插入
     *
     * @param columnValue
     * @throws Exception
     */
    public List batchInsert(final String mainid, final List columnValue) throws Exception;

    /**
     * 批量更新
     *
     * @param columnValue
     * @throws Exception
     */
    public void batchPreUpdate(final String mainid) throws Exception;

}
