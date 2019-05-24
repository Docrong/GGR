package com.boco.eoms.commons.statistic.base.config.report;

import java.io.Serializable;
import java.util.List;

public class Datas implements Serializable{

	private Data datas[] = null;

	public Data[] getDatas() {
		return datas;
	}

	public void setDatas(Data[] datas) {
		this.datas = datas;
	}
	
	public void setDatasList(List datalist)
	{
		Data[] tmp = new Data[datalist.size()];
		for(int f=0;f<datalist.size();f++)
		{
			tmp[f] = (Data)datalist.get(f);
		}
		
		this.datas = tmp;
	}
}
