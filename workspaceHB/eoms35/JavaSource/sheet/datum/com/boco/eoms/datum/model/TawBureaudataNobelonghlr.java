package com.boco.eoms.datum.model;

import java.io.Serializable;

public class TawBureaudataNobelonghlr implements Serializable {

    public static String REF = "TawBureaudataNobelonghlr";
    public static String PROP_BEGINSEGMENT = "beginsegment";
    public static String PROP_ENDSEGMENT = "endsegment";
    public static String PROP_CITYID = "cityid";
    public static String PROP_ID = "id";
    public static String PROP_NOTE = "note";
    public static String PROP_BELONGFLAG = "belongflag";
    public static String PROP_BUREAUNO = "bureauno";


    // constructors
    public TawBureaudataNobelonghlr() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public TawBureaudataNobelonghlr(java.lang.String id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public TawBureaudataNobelonghlr(
            java.lang.String id,
            java.lang.Integer cityid,
            java.lang.Integer beginsegment,
            java.lang.Integer endsegment,
            java.lang.String belongflag) {

        this.setId(id);
        this.setCityid(cityid);
        this.setBeginsegment(beginsegment);
        this.setEndsegment(endsegment);
        this.setBelongflag(belongflag);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.String id;

    // fields
    private java.lang.Integer cityid;
    private java.lang.Integer beginsegment;
    private java.lang.Integer endsegment;
    private java.lang.String belongflag;
    private java.lang.String bureauno;
    private java.lang.String note;


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
     * Return the value associated with the column: CITYID
     */
    public java.lang.Integer getCityid() {
        return cityid;
    }

    /**
     * Set the value related to the column: CITYID
     *
     * @param cityid the CITYID value
     */
    public void setCityid(java.lang.Integer cityid) {
        this.cityid = cityid;
    }


    /**
     * Return the value associated with the column: BEGINSEGMENT
     */
    public java.lang.Integer getBeginsegment() {
        return beginsegment;
    }

    /**
     * Set the value related to the column: BEGINSEGMENT
     *
     * @param beginsegment the BEGINSEGMENT value
     */
    public void setBeginsegment(java.lang.Integer beginsegment) {
        this.beginsegment = beginsegment;
    }


    /**
     * Return the value associated with the column: ENDSEGMENT
     */
    public java.lang.Integer getEndsegment() {
        return endsegment;
    }

    /**
     * Set the value related to the column: ENDSEGMENT
     *
     * @param endsegment the ENDSEGMENT value
     */
    public void setEndsegment(java.lang.Integer endsegment) {
        this.endsegment = endsegment;
    }


    /**
     * Return the value associated with the column: BELONGFLAG
     */
    public java.lang.String getBelongflag() {
        return belongflag;
    }

    /**
     * Set the value related to the column: BELONGFLAG
     *
     * @param belongflag the BELONGFLAG value
     */
    public void setBelongflag(java.lang.String belongflag) {
        this.belongflag = belongflag;
    }


    /**
     * Return the value associated with the column: BUREAUNO
     */
    public java.lang.String getBureauno() {
        return bureauno;
    }

    /**
     * Set the value related to the column: BUREAUNO
     *
     * @param bureauno the BUREAUNO value
     */
    public void setBureauno(java.lang.String bureauno) {
        this.bureauno = bureauno;
    }


    /**
     * Return the value associated with the column: NOTE
     */
    public java.lang.String getNote() {
        return note;
    }

    /**
     * Set the value related to the column: NOTE
     *
     * @param note the NOTE value
     */
    public void setNote(java.lang.String note) {
        this.note = note;
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
