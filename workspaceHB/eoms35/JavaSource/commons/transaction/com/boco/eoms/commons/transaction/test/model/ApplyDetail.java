/*
 * WARNING: DO NOT EDIT THIS FILE. This is a generated file that is synchronized
 * by MyEclipse Hibernate tool integration.
 *
 * Created Tue Dec 26 17:02:52 CST 2006 by MyEclipse Hibernate Tool.
 */
package com.boco.eoms.commons.transaction.test.model;

import java.io.Serializable;

/**
 * 
 */
public class ApplyDetail implements Serializable {

    /** The composite primary key value. */
    private String id;

    /** The value of the simple Name property. */
    private String name;

    /** The value of the simple remark property. */
    private String remark;

    /**
     * Default constructor.
     */
    public ApplyDetail() {
    }

    /**
     * Constructor of instances given a simple primary key
     * 
     * @param id
     */
    public ApplyDetail(String id) {
        setId(id);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}