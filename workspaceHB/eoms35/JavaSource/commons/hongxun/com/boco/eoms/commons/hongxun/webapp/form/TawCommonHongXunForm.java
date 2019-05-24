package com.boco.eoms.commons.hongxun.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawCommonHongXunForm extends BaseForm implements
java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String com_name;

	private String xiaozu_name;

	private String name;

	private String tel;

	private String zhize;

	private String remark;

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getXiaozu_name() {
		return xiaozu_name;
	}

	public void setXiaozu_name(String xiaozu_name) {
		this.xiaozu_name = xiaozu_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getZhize() {
		return zhize;
	}

	public void setZhize(String zhize) {
		this.zhize = zhize;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
