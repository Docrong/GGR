package com.boco.eoms.commons.log.model.castor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TawCommonLogFileInfoMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List logfileinfo = new ArrayList();

	public List getLogfileinfo() {
		return logfileinfo;
	}

	public void setLogfileinfo(List logfileinfo) {
		this.logfileinfo = logfileinfo;
	}

}
