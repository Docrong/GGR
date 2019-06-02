/**
 * 
 */
package com.boco.eoms.commons.transaction.test.model;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class ApplyMain {
    /** The composite primary key value. */
    private int id;

    /** The value of the simple Name property. */
    private String name;

    /** The value of the simple remark property. */
    private String sex;

    /**
     * Default constructor.
     */
    public ApplyMain() {
    }

    /**
     * Constructor of instances given a simple primary key
     * 
     * @param id
     */
    public ApplyMain(int id) {
        setId(id);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
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
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

}
