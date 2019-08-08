package com.boco.eoms.km.statics.mgr;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.statics.model.KmOperateScore;

public interface GeneralStatisticMgr {

    /**
     * 根据条件查询知识按知识库统计表
     *
     * @return 返回知识条件查询知识按知识库统计表的列表
     */
    public Map getGeneralStatistics(final String startDate, final String endDate);

}
