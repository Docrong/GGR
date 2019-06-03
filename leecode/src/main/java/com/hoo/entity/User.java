package com.hoo.entity;

import java.io.Serializable;

/**
 * * <b>function:</b>User Entity * @author hoojo * @createDate Dec 16, 2010
 * 10:20:02 PM * @file User.java * @package com.hoo.entity * @project
 * AxisWebService * @blog http://blog.csdn.net/IBM_hoojo * @email hoojo_@126.com
 * * @version 1.0
 */
public class User implements Serializable {
	private static final long serialVersionUID = 677484458789332877L;
	private int id;
	private String name;
	private String email;
	private String address;
//Constractor //getter、setter @Override public String toString() { return this.id + "#" + this.name + "#" + this.email + "#" + this.address; } }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}