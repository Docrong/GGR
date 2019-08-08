package com.boco.eoms.interfaces.log.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
public class InterfaceLog implements Serializable {

    /**
     * nullable persistent field
     */
    private String id;

    /**
     * nullable persistent field
     */
    private int fromSystem;

    /**
     * nullable persistent field
     */
    private String sheetId;

    /**
     * nullable persistent field
     */
    private String sheetType;

    /**
     * nullable persistent field
     */
    private String serSupplier;

    /**
     * nullable persistent field
     */
    private String serCaller;

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
    private String result;

    /**
     * nullable persistent field
     */
    private String sourceData;

    /**
     * full constructor
     */
    public InterfaceLog(int fromSystem, String sheetId, String sheetType, String serSupplier, String serCaller, String serMethod, Timestamp callTime, String result, String sourceData) {
        this.fromSystem = fromSystem;
        this.sheetId = sheetId;
        this.sheetType = sheetType;
        this.serSupplier = serSupplier;
        this.serCaller = serCaller;
        this.serMethod = serMethod;
        this.callTime = callTime;
        this.result = result;
        this.sourceData = sourceData;
    }

    /**
     * default constructor
     */
    public InterfaceLog() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFromSystem() {
        return this.fromSystem;
    }

    public void setFromSystem(int fromSystem) {
        this.fromSystem = fromSystem;
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

    public String getSerSupplier() {
        return this.serSupplier;
    }

    public void setSerSupplier(String serSupplier) {
        this.serSupplier = serSupplier;
    }

    public String getSerCaller() {
        return this.serCaller;
    }

    public void setSerCaller(String serCaller) {
        this.serCaller = serCaller;
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

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSourceData() {
        return this.sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }

}
