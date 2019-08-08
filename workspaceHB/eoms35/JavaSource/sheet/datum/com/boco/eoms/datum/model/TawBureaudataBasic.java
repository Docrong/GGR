package com.boco.eoms.datum.model;

import java.io.Serializable;

public class TawBureaudataBasic implements Serializable {

    public static String REF = "TawBureaudataBasic";
    public static String PROP_PREHLRSIGNALID = "prehlrsignalid";
    public static String PROP_NEWBUREAUID = "newbureauid";
    public static String PROP_ZONENUM = "zonenum";
    public static String PROP_ADJUSTBUREAUID = "adjustbureauid";
    public static String PROP_BELONGFLAG = "belongflag";
    public static String PROP_PREZONENUM = "prezonenum";
    public static String PROP_SEGMENTID = "segmentid";
    public static String PROP_BUREADATASHEETID = "bureadatasheetid";
    public static String PROP_HLRSIGNALID = "hlrsignalid";
    public static String PROP_SHEETID = "sheetid";


    // constructors
    public TawBureaudataBasic() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public TawBureaudataBasic(java.lang.Integer segmentid) {
        this.setSegmentid(segmentid);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Integer segmentid;

    // fields
    private java.lang.Integer prezonenum;
    private java.lang.Integer zonenum;
    private java.lang.Integer belongflag;
    private java.lang.String prehlrsignalid;
    private java.lang.String hlrsignalid;
    private java.lang.String newbureauid;
    private java.lang.String adjustbureauid;
    private java.lang.String bureadatasheetid;
    private java.lang.String sheetid;


    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="sequence"
     * column="SEGMENTID"
     */
    public java.lang.Integer getSegmentid() {
        return segmentid;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param segmentid the new ID
     */
    public void setSegmentid(java.lang.Integer segmentid) {
        this.segmentid = segmentid;
        this.hashCode = Integer.MIN_VALUE;
    }


    /**
     * Return the value associated with the column: PREZONENUM
     */
    public java.lang.Integer getPrezonenum() {
        return prezonenum;
    }

    /**
     * Set the value related to the column: PREZONENUM
     *
     * @param prezonenum the PREZONENUM value
     */
    public void setPrezonenum(java.lang.Integer prezonenum) {
        this.prezonenum = prezonenum;
    }


    /**
     * Return the value associated with the column: ZONENUM
     */
    public java.lang.Integer getZonenum() {
        return zonenum;
    }

    /**
     * Set the value related to the column: ZONENUM
     *
     * @param zonenum the ZONENUM value
     */
    public void setZonenum(java.lang.Integer zonenum) {
        this.zonenum = zonenum;
    }


    /**
     * Return the value associated with the column: BELONGFLAG
     */
    public java.lang.Integer getBelongflag() {
        return belongflag;
    }

    /**
     * Set the value related to the column: BELONGFLAG
     *
     * @param belongflag the BELONGFLAG value
     */
    public void setBelongflag(java.lang.Integer belongflag) {
        this.belongflag = belongflag;
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
     * Return the value associated with the column: NEWBUREAUID
     */
    public java.lang.String getNewbureauid() {
        return newbureauid;
    }

    /**
     * Set the value related to the column: NEWBUREAUID
     *
     * @param newbureauid the NEWBUREAUID value
     */
    public void setNewbureauid(java.lang.String newbureauid) {
        this.newbureauid = newbureauid;
    }


    /**
     * Return the value associated with the column: ADJUSTBUREAUID
     */
    public java.lang.String getAdjustbureauid() {
        return adjustbureauid;
    }

    /**
     * Set the value related to the column: ADJUSTBUREAUID
     *
     * @param adjustbureauid the ADJUSTBUREAUID value
     */
    public void setAdjustbureauid(java.lang.String adjustbureauid) {
        this.adjustbureauid = adjustbureauid;
    }


    /**
     * Return the value associated with the column: BUREADATASHEETID
     */
    public java.lang.String getBureadatasheetid() {
        return bureadatasheetid;
    }

    /**
     * Set the value related to the column: BUREADATASHEETID
     *
     * @param bureadatasheetid the BUREADATASHEETID value
     */
    public void setBureadatasheetid(java.lang.String bureadatasheetid) {
        this.bureadatasheetid = bureadatasheetid;
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


    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            if (null == this.getSegmentid()) return super.hashCode();
            else {
                String hashStr = this.getClass().getName() + ":" + this.getSegmentid().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }


    public String toString() {
        return super.toString();
    }


}
