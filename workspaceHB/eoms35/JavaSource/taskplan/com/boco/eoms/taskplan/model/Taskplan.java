package com.boco.eoms.taskplan.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="Taskplan.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="sp_submit"
 */
public class Taskplan extends BaseObject {

    private String id;

    private String project_name;

    private String project_decompose;

    private String deptid;

    private String task_decompose;

    private String stakeholders;

    private String task_plan;

    private String task_complete;

    private String month_mark;

    private String serial_mark;

    private String insert_time;

    private String deptName;

    private String stakeholdersName;

    /**
     * @return
     * @hibernate.property length="20"
     * @eoms.show
     * @eoms.cn name="部门"
     */
    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    /**
     * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return
     * @hibernate.property length="20"
     * @eoms.show
     * @eoms.cn name="部门"
     */
    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    /**
     * @return
     * @hibernate.property length="20"
     * @eoms.show
     * @eoms.cn name="月份标记"
     */
    public String getMonth_mark() {
        return month_mark;
    }

    public void setMonth_mark(String month_mark) {
        this.month_mark = month_mark;
    }

    /**
     * @return
     * @hibernate.property length="20"
     * @eoms.show
     * @eoms.cn name="项目分解"
     */
    public String getProject_decompose() {
        return project_decompose;
    }

    public void setProject_decompose(String project_decompose) {
        this.project_decompose = project_decompose;
    }

    /**
     * @return
     * @hibernate.property length="20"
     * @eoms.show
     * @eoms.cn name="项目名称"
     */
    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    /**
     * @return
     * @hibernate.property length="20"
     * @eoms.show
     * @eoms.cn name="排序标记"
     */
    public String getSerial_mark() {
        return serial_mark;
    }

    public void setSerial_mark(String serial_mark) {
        this.serial_mark = serial_mark;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="责任人"
     */
    public String getStakeholders() {
        return stakeholders;
    }

    public void setStakeholders(String stakeholders) {
        this.stakeholders = stakeholders;
    }

    /**
     * @return
     * @hibernate.property length="1000"
     * @eoms.show
     * @eoms.cn name="责任人"
     */
    public String getTask_complete() {
        return task_complete;
    }

    public void setTask_complete(String task_complete) {
        this.task_complete = task_complete;
    }

    /**
     * @return
     * @hibernate.property length="500"
     * @eoms.show
     * @eoms.cn name="任务细分"
     */
    public String getTask_decompose() {
        return task_decompose;
    }

    public void setTask_decompose(String task_decompose) {
        this.task_decompose = task_decompose;
    }

    /**
     * @return
     * @hibernate.property length="1000"
     * @eoms.show
     * @eoms.cn name="计划"
     */
    public String getTask_plan() {
        return task_plan;
    }

    public void setTask_plan(String task_plan) {
        this.task_plan = task_plan;
    }

    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getStakeholdersName() {
        return stakeholdersName;
    }

    public void setStakeholdersName(String stakeholdersName) {
        this.stakeholdersName = stakeholdersName;
    }

}
