package com.boco.eoms.datum.webapp.form;

import java.io.Serializable;

public class TawBureaudataCityzone  implements Serializable{

	public static String REF = "TawBureaudataCityzone";
	public static String PROP_CITYID = "cityid";
	public static String PROP_ZONENUM = "zonenum";
	public static String PROP_USERSYSID = "usersysid";
	public static String PROP_DEPTID = "deptid";
	public static String PROP_USERID = "userid";
	public static String PROP_CITYNAME = "cityname";


	// constructors
	public TawBureaudataCityzone () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public TawBureaudataCityzone (java.lang.Integer cityid) {
		this.setCityid(cityid);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public TawBureaudataCityzone (
		java.lang.Integer cityid,
		java.lang.String cityname,
		java.lang.String zonenum) {

		this.setCityid(cityid);
		this.setCityname(cityname);
		this.setZonenum(zonenum);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer cityid;

	// fields
	private java.lang.String cityname;
	private java.lang.String zonenum;
	private java.lang.Integer deptid;
	private java.lang.Integer usersysid;
	private java.lang.String userid;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="cityid"
     */
	public java.lang.Integer getCityid () {
		return cityid;
	}

	/**
	 * Set the unique identifier of this class
	 * @param cityid the new ID
	 */
	public void setCityid (java.lang.Integer cityid) {
		this.cityid = cityid;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: CITYNAME
	 */
	public java.lang.String getCityname () {
		return cityname;
	}

	/**
	 * Set the value related to the column: CITYNAME
	 * @param cityname the CITYNAME value
	 */
	public void setCityname (java.lang.String cityname) {
		this.cityname = cityname;
	}



	/**
	 * Return the value associated with the column: ZONENUM
	 */
	public java.lang.String getZonenum () {
		return zonenum;
	}

	/**
	 * Set the value related to the column: ZONENUM
	 * @param zonenum the ZONENUM value
	 */
	public void setZonenum (java.lang.String zonenum) {
		this.zonenum = zonenum;
	}



	/**
	 * Return the value associated with the column: DEPTID
	 */
	public java.lang.Integer getDeptid () {
		return deptid;
	}

	/**
	 * Set the value related to the column: DEPTID
	 * @param deptid the DEPTID value
	 */
	public void setDeptid (java.lang.Integer deptid) {
		this.deptid = deptid;
	}



	/**
	 * Return the value associated with the column: USERSYSID
	 */
	public java.lang.Integer getUsersysid () {
		return usersysid;
	}

	/**
	 * Set the value related to the column: USERSYSID
	 * @param usersysid the USERSYSID value
	 */
	public void setUsersysid (java.lang.Integer usersysid) {
		this.usersysid = usersysid;
	}



	/**
	 * Return the value associated with the column: USERID
	 */
	public java.lang.String getUserid () {
		return userid;
	}

	/**
	 * Set the value related to the column: USERID
	 * @param userid the USERID value
	 */
	public void setUserid (java.lang.String userid) {
		this.userid = userid;
	}





	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getCityid()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getCityid().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}
