package com.boco.eoms.km.ask.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:投票
 * </p>
 * <p>
 * Description:投票
 * </p>
 * <p>
 * Fri Aug 14 15:39:20 CST 2009
 * </p>
 *
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */
public class KmAskVoteForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 投票人
     */
    private java.lang.String voteUser;

    public void setVoteUser(java.lang.String voteUser) {
        this.voteUser = voteUser;
    }

    public java.lang.String getVoteUser() {
        return this.voteUser;
    }

    /**
     * 投票时间
     */
    private java.lang.String voteDate;

    public void setVoteDate(java.lang.String voteDate) {
        this.voteDate = voteDate;
    }

    public java.lang.String getVoteDate() {
        return this.voteDate;
    }

    /**
     * 投票部门
     */
    private java.lang.String voteDept;

    public void setVoteDept(java.lang.String voteDept) {
        this.voteDept = voteDept;
    }

    public java.lang.String getVoteDept() {
        return this.voteDept;
    }

}