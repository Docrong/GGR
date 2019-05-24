package com.boco.eoms.sheet.tool.access.dao.hibernate;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.commons.system.area.model.util.TawSystemAreaUtil;
import com.boco.eoms.sheet.tool.access.dao.ITawSheetAccessDaoJdbc;

public class TawSheetAccessJdbcImpl extends BaseDaoJdbc implements
		ITawSheetAccessDaoJdbc {

	public String getMaxAccessId(String parareaid, int len) {
		String sql = " select max(accessid) as accessid from sheet_tool_access where accessid like '"+parareaid+"%' and length(accessid)<='"+len+"'";
		String maxareaid = "";
		
		maxareaid = String.valueOf(getJdbcTemplate().queryForMap(sql).get("accessid"));
		if ( (maxareaid.equals("") || maxareaid.equals("null")) ) {
			maxareaid = TawSystemAreaUtil.AREA_DEFAULT_STRONE;
		}
		return maxareaid;
	}

	
	
}
