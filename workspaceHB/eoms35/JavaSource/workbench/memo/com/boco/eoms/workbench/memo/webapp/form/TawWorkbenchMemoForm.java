package com.boco.eoms.workbench.memo.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 *
 * @struts.form name="tawWorkbenchMemoForm"
 */
public class TawWorkbenchMemoForm
        extends BaseForm
        implements java.io.Serializable {

    protected String id;

    protected String deleted;

    protected String title;

    protected String content;

    protected String userid;

    protected String creattime;

    protected String level;

    protected String sendflag;

    protected String sendtime;

    private String reciever;
    private String sendmanner;

    /**
     * Default empty constructor.
     */
    public TawWorkbenchMemoForm() {
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

    public String getDeleted() {
        return this.deleted;
    }

    /**
     *
     */

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getTitle() {
        return this.title;
    }

    /**
     *
     */

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    /**
     *
     */

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return this.userid;
    }

    /**
     *
     */

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCreattime() {
        return this.creattime;
    }

    /**
     *
     */

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public String getLevel() {
        return this.level;
    }

    /**
     *
     */

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSendflag() {
        return this.sendflag;
    }

    /**
     *
     */

    public void setSendflag(String sendflag) {
        this.sendflag = sendflag;
    }

    public String getSendtime() {
        return this.sendtime;
    }

    /**
     *
     */

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getReciever() {
        return this.reciever;
    }

    /**
     *
     */

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

        /* To add non XDoclet-generated methods, create a file named
           xdoclet-TawWorkbenchMemoForm.java 
           containing the additional code and place it in your metadata/web directory.
        */

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     * javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false

    }

    public String getSendmanner() {
        return sendmanner;
    }

    public void setSendmanner(String sendmanner) {
        this.sendmanner = sendmanner;
    }

}
