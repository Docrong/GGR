package com.boco.eoms.commons.statistic.base.config.report;

import java.io.Serializable;
import java.util.List;

public class DisplayInfo implements Serializable{

	private Info infos[] = null;

	public Info[] getInfos() {
		return infos;
	}

	public void setInfos(Info[] infos) {
		this.infos = infos;
	}
	
	public void setInfosList(List infolist)
	{
		Info[] tmp = new Info[infolist.size()];
		for(int f=0;f<infolist.size();f++)
		{
			tmp[f] = (Info)infolist.get(f);
		}
		
		this.infos = tmp;
	}

}
