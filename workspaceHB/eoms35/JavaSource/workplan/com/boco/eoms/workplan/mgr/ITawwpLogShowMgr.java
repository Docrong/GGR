package com.boco.eoms.workplan.mgr;
import java.util.List;

import com.boco.eoms.workplan.model.TawwpNewLog;

public interface ITawwpLogShowMgr {

		public List listTable();
		
		public List listTableSearch(String sheetId, String timeStart, String timeEnd) ;
		
		public TawwpNewLog getOne(String id);

}
