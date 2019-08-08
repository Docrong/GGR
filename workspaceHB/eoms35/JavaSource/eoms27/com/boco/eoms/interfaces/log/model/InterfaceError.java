package com.boco.eoms.interfaces.log.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.sql.Timestamp;

import com.boco.eoms.common.oo.DataObject;

/**
 * @author Hibernate CodeGenerator
 */
public class InterfaceError implements Serializable, DataObject {

    /**
     * identifier field
     */
    private String id;

    /**
     * nullable persistent field
     */
    private String sheetId;

    private String sheetType;
    /**
     * nullable persistent field
     */
    private String serMethod;

    /**
     * nullable persistent field
     */
    private Timestamp callTime;

    /**
     * nullable persistent field
     */
    private String sourceData;

    /**
     * full constructor
     */
    public InterfaceError(String sheetId, String sheetType, String serMethod, Timestamp callTime, String sourceData) {
        this.sheetId = sheetId;
        this.sheetType = sheetType;
        this.serMethod = serMethod;
        this.callTime = callTime;
        this.sourceData = sourceData;
    }

    /**
     * default constructor
     */
    public InterfaceError() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSheetId() {
        return this.sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getSheetType() {
        return this.sheetType;
    }

    public void setSheetType(String sheetType) {
        this.sheetType = sheetType;
    }

    public String getSerMethod() {
        return this.serMethod;
    }

    public void setSerMethod(String serMethod) {
        this.serMethod = serMethod;
    }

    public Timestamp getCallTime() {
        return this.callTime;
    }

    public void setCallTime(Timestamp callTime) {
        this.callTime = callTime;
    }

    public String getSourceData() {
        return this.sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof InterfaceError)) return false;
        InterfaceError castOther = (InterfaceError) other;
        return new EqualsBuilder()
                .append(this.getId(), castOther.getId())
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

}
