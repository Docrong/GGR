package com.boco.eoms.km.statics.mgr.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.statics.mgr.BaseStatisticMgr;

/**
 * <p>
 * Title:知识库统计
 * </p>
 * <p>
 * Description:知识库统计
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public class BaseStatisticMgrImpl implements BaseStatisticMgr {
 
/*	//调用知识操作日统计表接口,读取所需数据
	private KmOperateDateLogMgr kmOperateDateLogMgr;

	public KmOperateDateLogMgr getKmOperateDateLogMgr() {
		return kmOperateDateLogMgr;
	}

	public void setKmOperateDateLogMgr(KmOperateDateLogMgr kmOperateDateLogMgr) {
		this.kmOperateDateLogMgr = kmOperateDateLogMgr;
	}*/

    /**
     * 根据条件分页查询知识库统计
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @return 返回知识库统计的分页列表
     */
    public Map getBaseStatistics(final Integer curPage, final Integer pageSize) {

        Map map = new HashMap();
        List result = new ArrayList();
        map.put("total", new Integer(result.size()));
        map.put("result", result);
        /*
         * 需要知识管理提供接口,取得知识库统计数据
         * map = ****************
         *
         */

        //返回结果
        return map;
    }

}