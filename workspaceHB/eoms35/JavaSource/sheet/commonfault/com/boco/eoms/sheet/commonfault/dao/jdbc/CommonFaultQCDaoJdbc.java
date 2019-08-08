package com.boco.eoms.sheet.commonfault.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultQCDAO;

public class CommonFaultQCDaoJdbc extends JdbcDaoSupport implements
        ICommonFaultQCDAO {

    public Map selectObject(String sql, Integer curPage, Integer pageSize) {
        String totalSql = "select count(*) from (" + sql + ")";
        BocoLog.info(this, "计数sql语句totalSql=" + totalSql);
        int total = getJdbcTemplate().queryForInt(totalSql);
        String querySql = "";
        if (pageSize.intValue() == -1) {
            querySql = "select * from (" + sql + ")";
        } else {
            querySql = "select * from (" + sql + ") where rn>" + pageSize.intValue() * curPage.intValue() + " and rn <=" + pageSize.intValue() * (curPage.intValue() + 1);
        }
        BocoLog.info(this, "查询sql语句querySql=" + querySql);
        List result = getJdbcTemplate().queryForList(querySql);
        Map resultMap = new HashMap();
        resultMap.put("total", new Integer(total));
        resultMap.put("result", result);
        return resultMap;
    }

    public int getTotalBySql(String sql) {
        String totalSql = "select count(*) from (" + sql + ")";
        BocoLog.info(this, "计数sql语句totalSql=" + totalSql);
        int total = getJdbcTemplate().queryForInt(totalSql);
        return total;
    }

}
