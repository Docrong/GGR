package com.boco.eoms.sheet.base.webapp.form;

import com.boco.eoms.commons.accessories.webapp.form.TawCommonsAccessoriesForm;

public class SheetAccessoriesForm extends TawCommonsAccessoriesForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 上传部门
	 */
	protected String deptId;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	
}
