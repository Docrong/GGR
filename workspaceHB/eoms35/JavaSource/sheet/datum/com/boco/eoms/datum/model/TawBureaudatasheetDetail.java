package com.boco.eoms.datum.model;

import java.io.Serializable;

public class TawBureaudatasheetDetail implements Serializable {

    public static String REF = "TawBureaudatasheetDetail";
    public static String PROP_PREHLRSIGNALID = "prehlrsignalid";
    public static String PROP_ID = "id";
    public static String PROP_HLRSIGNALID = "hlrsignalid";
    public static String PROP_SEGMENTID = "segmentid";
    public static String PROP_SHEETID = "sheetid";


    // constructors
    public TawBureaudatasheetDetail() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public TawBureaudatasheetDetail(java.lang.String id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public TawBureaudatasheetDetail(
            java.lang.String id,
            java.lang.String sheetid,
            java.lang.Integer segmentid) {

        this.setId(id);
        this.setSheetid(sheetid);
        this.setSegmentid(segmentid);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.String id;

    // fields
    private java.lang.String sheetid;
    private java.lang.Integer segmentid;
    private java.lang.String hlrsignalid;
    private java.lang.String prehlrsignalid;


    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="sequence"
     * column="id"
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param id the new ID
     */
    public void setId(java.lang.String id) {
        this.id = id;
        this.hashCode = Integer.MIN_VALUE;
    }


    /**
     * Return the value associated with the column: SHEETID
     */
    public java.lang.String getSheetid() {
        return sheetid;
    }

    /**
     * Set the value related to the column: SHEETID
     *
     * @param sheetid the SHEETID value
     */
    public void setSheetid(java.lang.String sheetid) {
        this.sheetid = sheetid;
    }


    /**
     * Return the value associated with the column: SEGMENTID
     */
    public java.lang.Integer getSegmentid() {
        return segmentid;
    }

    /**
     * Set the value related to the column: SEGMENTID
     *
     * @param segmentid the SEGMENTID value
     */
    public void setSegmentid(java.lang.Integer segmentid) {
        this.segmentid = segmentid;
    }


    /**
     * Return the value associated with the column: HLRSIGNALID
     */
    public java.lang.String getHlrsignalid() {
        return hlrsignalid;
    }

    /**
     * Set the value related to the column: HLRSIGNALID
     *
     * @param hlrsignalid the HLRSIGNALID value
     */
    public void setHlrsignalid(java.lang.String hlrsignalid) {
        this.hlrsignalid = hlrsignalid;
    }


    /**
     * Return the value associated with the column: PREHLRSIGNALID
     */
    public java.lang.String getPrehlrsignalid() {
        return prehlrsignalid;
    }

    /**
     * Set the value related to the column: PREHLRSIGNALID
     *
     * @param prehlrsignalid the PREHLRSIGNALID value
     */
    public void setPrehlrsignalid(java.lang.String prehlrsignalid) {
        this.prehlrsignalid = prehlrsignalid;
    }


    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            if (null == this.getId()) return super.hashCode();
            else {
                String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }


    public String toString() {
        return super.toString();
    }


}
