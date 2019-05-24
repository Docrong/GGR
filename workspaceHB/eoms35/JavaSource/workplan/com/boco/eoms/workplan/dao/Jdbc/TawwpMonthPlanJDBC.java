package com.boco.eoms.workplan.dao.Jdbc;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.workplan.dao.ITawwpMmonthPlanJdbc;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
 

public class TawwpMonthPlanJDBC extends BaseDaoJdbc implements ITawwpMmonthPlanJdbc{
	private TawwpMonthPlanJDBC() {

	}

	 

	public String getNewMonthplanId(String _yearplanid,String _netId,String _monthFlag) 
		  {
		    String id = "";
		    String sql = "";
		    if(_netId!=null){
		    	sql = "SELECT id FROM taw_wp_monthplan WHERE year_plan_id ='"+_yearplanid+"' AND monthflag ='"+_monthFlag+"' and netid ='"+_netId+"'";
		    }else{
		    	sql = "SELECT id FROM taw_wp_monthplan WHERE year_plan_id ='"+_yearplanid+"' AND monthflag ='"+_monthFlag+"' and netid is null ";	
		    }
			List list = getJdbcTemplate().queryForList(sql.toString());
			for (Iterator it = list.iterator(); it.hasNext();) {
				Map mapObj = (Map) it.next();
				id = StaticMethod.nullObject2String(mapObj.get("id"));
			}
			return id;

	}
}
