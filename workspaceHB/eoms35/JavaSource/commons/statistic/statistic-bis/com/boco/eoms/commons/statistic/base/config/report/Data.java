package com.boco.eoms.commons.statistic.base.config.report;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable{
	
	private Field fields[] = null;

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}
	
	public void setFieldsList(List fieldlist)
	{
		Field[] tmp = new Field[fieldlist.size()];
		for(int f=0;f<fieldlist.size();f++)
		{
			tmp[f] = (Field)fieldlist.get(f);
		}
		
		this.fields = tmp;
	}
}
