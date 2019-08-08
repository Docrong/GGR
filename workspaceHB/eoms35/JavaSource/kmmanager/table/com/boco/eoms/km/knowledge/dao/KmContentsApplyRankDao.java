package com.boco.eoms.km.knowledge.dao;

import java.util.Map;

import com.boco.eoms.base.dao.Dao;

/**
 * <p>
 * Title:知识申请排行
 * </p>
 * <p>
 * Description:知识申请排行
 * </p>
 * <p>
 * Wed Aug 19 15:53:50 CST 2009
 * </p>
 *
 * @author wangzhiyong
 * @version 1.0
 */
public interface KmContentsApplyRankDao extends Dao {


    /**
     * 根据主键查询知识申请排行
     *
     * @param id 主键
     * @return 返回某id的知识申请排行
     */
    public Map getKmContentsApplyRankDetail(final String id, final String startDate, final String endDate);

}