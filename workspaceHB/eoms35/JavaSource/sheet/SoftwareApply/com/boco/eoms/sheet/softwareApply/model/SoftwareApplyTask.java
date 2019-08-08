
package com.boco.eoms.sheet.softwareApply.model;

import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.task.impl.TaskImpl;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="softwareApplyTask.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="softwareApply_task"
 */
public class SoftwareApplyTask extends TaskImpl implements ITask {


    /**
     * 上一级处理部门
     */
    private String preDealDept;

    /**
     * 上一级处理人员
     */
    private String preDealUserId;

    private String correlationKey;
    private String levelId;
    private String parentLevelId;


    public String getParentLevelId() {
        return parentLevelId;
    }

    public void setParentLevelId(String parentLevelId) {
        this.parentLevelId = parentLevelId;
    }

    public String getCorrelationKey() {
        return correlationKey;
    }

    public void setCorrelationKey(String correlationKey) {
        this.correlationKey = correlationKey;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getPreDealDept() {
        return preDealDept;
    }

    public void setPreDealDept(String preDealDept) {
        this.preDealDept = preDealDept;
    }

    public String getPreDealUserId() {
        return preDealUserId;
    }

    public void setPreDealUserId(String preDealUserId) {
        this.preDealUserId = preDealUserId;
    }


}
