package com.boco.eoms.km.core.dataservice.sql;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.core.dataservice.map.EntityMap;

public class DeletedSQLBuilder {

	private EntityMap inModel;
	private Map inValue;
	

	public DeletedSQLBuilder(final EntityMap tableModel, final Map valueMap) {
		this.inModel = tableModel;
		this.inValue = valueMap;
	}

	public String buildSQL(List value, List type) throws Exception {
		return null;//
	}

}
