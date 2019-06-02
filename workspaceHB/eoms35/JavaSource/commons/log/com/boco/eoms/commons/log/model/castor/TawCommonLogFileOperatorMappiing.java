package com.boco.eoms.commons.log.model.castor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author panlong
 * @Mar 23, 2007 3:39:46 AM
 */
public class TawCommonLogFileOperatorMappiing implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List bocologcontents = new ArrayList();

	private String siteName;

	public List getBocologcontents() {
		return bocologcontents;
	}

	public void setBocologcontents(List bocologcontents) {
		this.bocologcontents = bocologcontents;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
}
