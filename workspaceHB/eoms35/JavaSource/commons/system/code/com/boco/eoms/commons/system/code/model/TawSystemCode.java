package com.boco.eoms.commons.system.code.model;


import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * <a href="BackUp.java.html"><i>View Source</i></a>
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_code"
 */
public class TawSystemCode extends BaseObject{
	private String id ;
	
	private String name ;
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getLeaf() {
		// TODO Auto-generated method stub
		return null;
	}
}

