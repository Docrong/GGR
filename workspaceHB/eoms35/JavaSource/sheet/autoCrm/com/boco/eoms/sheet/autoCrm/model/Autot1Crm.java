package com.boco.eoms.sheet.autoCrm.model;

import java.io.Serializable;

public class Autot1Crm implements Serializable {

	public static String REF = "Autot1Crm";
	public static String PROP_REMARK1 = "remark1";
	public static String PROP_CREATEDATE = "createdate";
	public static String PROP_REMARK2 = "remark2";
	public static String PROP_ID = "id";
	public static String PROP_AUTOACCEPTROLET1 = "autoacceptrolet1";
	public static String PROP_DEALROLET2 = "dealrolet2";
	public static String PROP_TEMPLATENAME = "templatename";
	public static String PROP_FAULTSITE = "faultsite";
	public static String PROP_COLSESWITCH = "colseswitch";


	// constructors
	public Autot1Crm () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public Autot1Crm (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String faultsite;
	private java.lang.String colseswitch;
	private java.lang.String autoacceptrolet1;
	private java.lang.String dealrolet2;
	private java.lang.String remark1;
	private java.lang.String remark2;
	private java.util.Date createdate;
	private java.lang.String templatename;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="id"
     */
	public java.lang.String getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: FAULTSITE
	 */
	public java.lang.String getFaultsite () {
		return faultsite;
	}

	/**
	 * Set the value related to the column: FAULTSITE
	 * @param faultsite the FAULTSITE value
	 */
	public void setFaultsite (java.lang.String faultsite) {
		this.faultsite = faultsite;
	}



	/**
	 * Return the value associated with the column: COLSESWITCH
	 */
	public java.lang.String getColseswitch () {
		return colseswitch;
	}

	/**
	 * Set the value related to the column: COLSESWITCH
	 * @param colseswitch the COLSESWITCH value
	 */
	public void setColseswitch (java.lang.String colseswitch) {
		this.colseswitch = colseswitch;
	}



	/**
	 * Return the value associated with the column: AUTOACCEPTROLET1
	 */
	public java.lang.String getAutoacceptrolet1 () {
		return autoacceptrolet1;
	}

	/**
	 * Set the value related to the column: AUTOACCEPTROLET1
	 * @param autoacceptrolet1 the AUTOACCEPTROLET1 value
	 */
	public void setAutoacceptrolet1 (java.lang.String autoacceptrolet1) {
		this.autoacceptrolet1 = autoacceptrolet1;
	}



	/**
	 * Return the value associated with the column: DEALROLET2
	 */
	public java.lang.String getDealrolet2 () {
		return dealrolet2;
	}

	/**
	 * Set the value related to the column: DEALROLET2
	 * @param dealrolet2 the DEALROLET2 value
	 */
	public void setDealrolet2 (java.lang.String dealrolet2) {
		this.dealrolet2 = dealrolet2;
	}



	/**
	 * Return the value associated with the column: REMARK1
	 */
	public java.lang.String getRemark1 () {
		return remark1;
	}

	/**
	 * Set the value related to the column: REMARK1
	 * @param remark1 the REMARK1 value
	 */
	public void setRemark1 (java.lang.String remark1) {
		this.remark1 = remark1;
	}



	/**
	 * Return the value associated with the column: REMARK2
	 */
	public java.lang.String getRemark2 () {
		return remark2;
	}

	/**
	 * Set the value related to the column: REMARK2
	 * @param remark2 the REMARK2 value
	 */
	public void setRemark2 (java.lang.String remark2) {
		this.remark2 = remark2;
	}



	/**
	 * Return the value associated with the column: CREATEDATE
	 */
	public java.util.Date getCreatedate () {
		return createdate;
	}

	/**
	 * Set the value related to the column: CREATEDATE
	 * @param createdate the CREATEDATE value
	 */
	public void setCreatedate (java.util.Date createdate) {
		this.createdate = createdate;
	}



	/**
	 * Return the value associated with the column: TEMPLATENAME
	 */
	public java.lang.String getTemplatename () {
		return templatename;
	}

	/**
	 * Set the value related to the column: TEMPLATENAME
	 * @param templatename the TEMPLATENAME value
	 */
	public void setTemplatename (java.lang.String templatename) {
		this.templatename = templatename;
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}
