package com.boco.eoms.commons.statistic.base.dao;

import java.util.List;

public interface IStatDetailDAO {

    public List getListDetail(String sql, String className, int pageIndex,
                              int pageSize) throws Exception;

}
