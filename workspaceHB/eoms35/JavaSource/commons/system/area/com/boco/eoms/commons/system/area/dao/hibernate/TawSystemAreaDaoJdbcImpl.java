package com.boco.eoms.commons.system.area.dao.hibernate;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.commons.system.area.dao.TawSystemAreaDaoJdbc;
import com.boco.eoms.commons.system.area.model.util.TawSystemAreaUtil;
import com.boco.eoms.commons.system.dict.util.TawSystemDictUtil;

/**
 * panlong
 * 下午04:01:28
 */
public class TawSystemAreaDaoJdbcImpl extends BaseDaoJdbc implements
        TawSystemAreaDaoJdbc {

    /*
     * 得到最大的地域ID
     */
    public String getMaxAreaId(String parareaid, int len) {
//		String sql = " select max(areaid) as areaid from taw_system_area where areaid like '"+parareaid+"%' and length(areaid)<='"+len+"'";
        String sql = " select max(areaid) as areaid from taw_system_area where areaid like '" + parareaid + "%' and length(areaid)<='" + len + "'";
        System.out.println(sql);
        String maxareaid = "";

        maxareaid = String.valueOf(getJdbcTemplate().queryForMap(sql).get("areaid"));
        if ((maxareaid.equals("") || maxareaid.equals("null"))) {
            maxareaid = TawSystemAreaUtil.AREA_DEFAULT_STRONE;
        }
        return maxareaid;
    }

}

