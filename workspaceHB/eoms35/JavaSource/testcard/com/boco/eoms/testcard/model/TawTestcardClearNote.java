package com.boco.eoms.testcard.model;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: 张旭
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

public class TawTestcardClearNote {

    private String iccid;
    //---------------卡号-----------------
    private String msisdn;
    private String clearuser;
    private String cleartime;
    //---------------旧系统卡号--------------
    private String oldstate;
    //---------------经手人----------------
    private String newstate;
    //---------------借用部门--------------
    private String clearyuanyin;

    /**
     * @return claertime
     */
    public String getCleartime() {
        return cleartime;
    }

    /**
     * @param claertime 要设置的 claertime
     */
    public void setCleartime(String cleartime) {
        this.cleartime = cleartime;
    }

    /**
     * @return clearyuanyin
     */
    public String getClearyuanyin() {
        return clearyuanyin;
    }

    /**
     * @param clearyuanyin 要设置的 clearyuanyin
     */
    public void setClearyuanyin(String clearyuanyin) {
        this.clearyuanyin = clearyuanyin;
    }

    /**
     * @return iccid
     */
    public String getIccid() {
        return iccid;
    }

    /**
     * @param iccid 要设置的 iccid
     */
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    /**
     * @return newstate
     */
    public String getNewstate() {
        return newstate;
    }

    /**
     * @param newstate 要设置的 newstate
     */
    public void setNewstate(String newstate) {
        this.newstate = newstate;
    }

    /**
     * @return oldstate
     */
    public String getOldstate() {
        return oldstate;
    }

    /**
     * @param oldstate 要设置的 oldstate
     */
    public void setOldstate(String oldstate) {
        this.oldstate = oldstate;
    }

    /**
     * @return clearuser
     */
    public String getClearuser() {
        return clearuser;
    }

    /**
     * @param clearuser 要设置的 clearuser
     */
    public void setClearuser(String clearuser) {
        this.clearuser = clearuser;
    }

    /**
     * @return msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * @param msisdn 要设置的 msisdn
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }


}