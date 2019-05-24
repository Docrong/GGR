/*
 * Created on 2008-1-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.statistic.base.config.model;

import java.io.Serializable;

/**
 * @author liuxy
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class KpiConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4328988288760795604L;
	private String title;
	private int estModuleId;
	private KpiDefine kpiDefines[];

	/**
	 * @return Returns the kpiDefines.
	 */
	public KpiDefine[] getKpiDefines() {
		return kpiDefines;
	}

	/**
	 * @param kpiDefines
	 *            The kpiDefines to set.
	 */
	public void setKpiDefines(KpiDefine[] kpiDefines) {
		this.kpiDefines = kpiDefines;
	}

	public KpiDefine getConfigByKpiDefineName(String kpiDefineName)
			throws Exception {
		KpiDefine kpiDefine = null;
		boolean isDefined = false;
		for (int i = 0; i < kpiDefines.length; i++) {
			if (kpiDefines[i].getName().equals(kpiDefineName)) {
				if (isDefined == true) {
					throw new Exception("重复定义KpiDefineName!");
				}
				isDefined = true;
				kpiDefine = kpiDefines[i];
			}
		}
		if (isDefined == false) {
			throw new Exception("没有定义KpiDefineName!");
		}

		return kpiDefine;
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public int getEstModuleId() {
		return estModuleId;
	}

	public void setEstModuleId(int estModuleId) {
		this.estModuleId = estModuleId;
	}
}
