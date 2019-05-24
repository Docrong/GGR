package com.boco.eoms.datum.model;

import java.io.Serializable;

public class TawBureaudataHlr implements Serializable {

	public static String REF = "TawBureaudataHlr";
	public static String PROP_REMARK = "remark";
	public static String PROP_HLRNAME = "hlrname";
	public static String PROP_HLRID = "hlrid";
	public static String PROP_HLRSIGNALID = "hlrsignalid";
	public static String PROP_BUREAUID = "bureauid";
	public static String PROP_DELETED = "deleted";
	public static String PROP_BELONGCITYID = "belongcityid";
	public static String PROP_ID = "id";

	// constructors
	public TawBureaudataHlr () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public TawBureaudataHlr (java.lang.String hlrid) {
		this.setHlrid(hlrid);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public TawBureaudataHlr (
		java.lang.String hlrid,
		java.lang.String hlrsignalid) {

		this.setHlrid(hlrid);
		this.setHlrsignalid(hlrsignalid);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id; 
	private java.lang.String hlrid;

	// fields
	private java.lang.String hlrname;
	private java.lang.String hlrsignalid;
	private java.lang.Integer belongcityid;
	private java.lang.Integer bureauid;
	private java.lang.String remark;
	private java.lang.Integer deleted;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="HLRID"
     */
	public java.lang.String getHlrid () {
		return hlrid;
	}

	/**
	 * Set the unique identifier of this class
	 * @param hlrid the new ID
	 */
	public void setHlrid (java.lang.String hlrid) {
		this.hlrid = hlrid;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: HLRNAME
	 */
	public java.lang.String getHlrname () {
		return hlrname;
	}

	/**
	 * Set the value related to the column: HLRNAME
	 * @param hlrname the HLRNAME value
	 */
	public void setHlrname (java.lang.String hlrname) {
		this.hlrname = hlrname;
	}



	/**
	 * Return the value associated with the column: HLRSIGNALID
	 */
	public java.lang.String getHlrsignalid () {
		return hlrsignalid;
	}

	/**
	 * Set the value related to the column: HLRSIGNALID
	 * @param hlrsignalid the HLRSIGNALID value
	 */
	public void setHlrsignalid (java.lang.String hlrsignalid) {
		this.hlrsignalid = hlrsignalid;
	}



	/**
	 * Return the value associated with the column: BELONGCITYID
	 */
	public java.lang.Integer getBelongcityid () {
		return belongcityid;
	}

	/**
	 * Set the value related to the column: BELONGCITYID
	 * @param belongcityid the BELONGCITYID value
	 */
	public void setBelongcityid (java.lang.Integer belongcityid) {
		this.belongcityid = belongcityid;
	}



	/**
	 * Return the value associated with the column: BUREAUID
	 */
	public java.lang.Integer getBureauid () {
		return bureauid;
	}

	/**
	 * Set the value related to the column: BUREAUID
	 * @param bureauid the BUREAUID value
	 */
	public void setBureauid (java.lang.Integer bureauid) {
		this.bureauid = bureauid;
	}



	/**
	 * Return the value associated with the column: REMARK
	 */
	public java.lang.String getRemark () {
		return remark;
	}

	/**
	 * Set the value related to the column: REMARK
	 * @param remark the REMARK value
	 */
	public void setRemark (java.lang.String remark) {
		this.remark = remark;
	}



	/**
	 * Return the value associated with the column: DELETED
	 */
	public java.lang.Integer getDeleted () {
		return deleted;
	}

	/**
	 * Set the value related to the column: DELETED
	 * @param deleted the DELETED value
	 */
	public void setDeleted (java.lang.Integer deleted) {
		this.deleted = deleted;
	}





	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getHlrid()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getHlrid().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	/**
	 * 关联工单的id
	 */
	private String sheetKey; 
	public String getSheetKey() {
		return sheetKey;
	}
	public void setSheetKey(String sheetKey) {
		this.sheetKey = sheetKey;
	}
	
	/**
	 * 资源是否被占用  ‘0’表示未被占用，‘1’表示已经被占用，‘2’表示工单中已经被占用但是占用工单还没有归档
	 */
	private   String isOccupation ;

	public String getIsOccupation() {
		return isOccupation;
	}

	public void setIsOccupation(String isOccupation) {
		this.isOccupation = isOccupation;
	}
	
	
}
