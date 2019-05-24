package com.boco.eoms.commons.log.model.castor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TawCommonLogFileDeployMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String siteName;

	private List eomsbocolog = new ArrayList();

	public List getEomsbocolog() {
		return eomsbocolog;
	}

	public void setEomsbocolog(List eomsbocolog) {
		this.eomsbocolog = eomsbocolog;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}
