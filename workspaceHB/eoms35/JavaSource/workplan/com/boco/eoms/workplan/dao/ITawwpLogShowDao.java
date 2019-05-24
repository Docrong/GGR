package com.boco.eoms.workplan.dao;

import java.util.List;
import com.boco.eoms.workplan.model.TawwpNewLog;

public interface ITawwpLogShowDao {

	public List listTable();

	public List listTable(String sheetId, String timeStart, String timeEnd); 

	public TawwpNewLog getOne(String id);

}
