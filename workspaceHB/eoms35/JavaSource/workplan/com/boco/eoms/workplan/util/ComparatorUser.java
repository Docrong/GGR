package com.boco.eoms.workplan.util;

import java.util.Comparator;

import com.boco.eoms.commons.system.user.model.TawSystemUser;

public class ComparatorUser implements Comparator{

	public int compare(Object arg0, Object arg1) {
		TawSystemUser user0 = (TawSystemUser)arg0;
		TawSystemUser user1 = (TawSystemUser)arg1;
		
		return user0.getUserid().compareTo(user1.getUserid());
	}

}
