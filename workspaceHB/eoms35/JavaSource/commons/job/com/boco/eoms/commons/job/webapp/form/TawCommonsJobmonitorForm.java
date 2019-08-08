package com.boco.eoms.commons.job.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with
 * XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 *
 * @struts.form name="tawCommonsJobmonitorForm"
 */
public class TawCommonsJobmonitorForm extends BaseForm implements
        java.io.Serializable {
    /**
     * ID
     */
    protected String id;
    /**
     * 任务类型ID
     */
    protected String jobSortId;
    /**
     * 任务订阅ID
     */
    protected String jobSubId;
    /**
     * 任务最大允许执行时间
     */
    protected String maxExecuteTime;
    /**
     * 任务执行结束时间
     */
    protected String executeEndTime;
    /**
     * 任务开始执行时间
     */
    protected String executeStartTime;
    /**
     * 状态
     */
    protected String status;

    /**
     * Default empty constructor.
     */
    public TawCommonsJobmonitorForm() {
    }

    public String getId() {
        return this.id;
    }

    /**
     *
     */

    public void setId(String id) {
        this.id = id;
    }

    public String getJobSortId() {
        return this.jobSortId;
    }

    /**
     *
     */

    public void setJobSortId(String jobSortId) {
        this.jobSortId = jobSortId;
    }

    public String getJobSubId() {
        return this.jobSubId;
    }

    /**
     *
     */

    public void setJobSubId(String jobSubId) {
        this.jobSubId = jobSubId;
    }

    public String getMaxExecuteTime() {
        return this.maxExecuteTime;
    }

    /**
     *
     */

    public void setMaxExecuteTime(String maxExecuteTime) {
        this.maxExecuteTime = maxExecuteTime;
    }

    public String getExecuteEndTime() {
        return this.executeEndTime;
    }

    /**
     *
     */

    public void setExecuteEndTime(String executeEndTime) {
        this.executeEndTime = executeEndTime;
    }

    public String getExecuteStartTime() {
        return this.executeStartTime;
    }

    /**
     *
     */

    public void setExecuteStartTime(String executeStartTime) {
        this.executeStartTime = executeStartTime;
    }

    public String getStatus() {
        return this.status;
    }

    /**
     *
     */

    public void setStatus(String status) {
        this.status = status;
    }

    /*
     * To add non XDoclet-generated methods, create a file named
     * xdoclet-TawCommonsJobmonitorForm.java containing the additional code and
     * place it in your metadata/web directory.
     */

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     * javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false

    }

}
