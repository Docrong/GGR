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

public class TawTestcardManagerNote {
    private int id;
    private String msisdn;//---------------测试卡卡号，唯一值，20位----------------
    private String exes;//费用情况
    private String accessory;//----附件
    private String volumenum; //册号
    private String pagenum;   //页号
    private String returner;//归还人
    private String renewer;//续借人
    private String manager;//执行人
    private int cardtype;
    //---------------卡类型---------------
    private String leave;
    //---------------存放地---------------
    private String cardid;
    //---------------卡号-----------------
    private String oldid;
    //---------------旧系统卡号--------------
    private String dealer;
    //---------------经手人----------------
    private String lenddept;
    //---------------借用部门--------------
    private String relenddept;
    //---------------借用部门--------------
    private String lender;
    //---------------借用人---------------
    private String contect;
    //---------------联系方式--------------
    private String reason;
    //--------------借用原因---------------
    private String leantime;
    //--------------借出日期---------------
    private String belongtime;
    //--------------应归还日期--------------
    private String returntime;
    //------------- 归还日期－－－－－－－－
    private int renewlimit;
    //------------- 续借期限－－－－－－－－
    private String state;
    //--------------备用字段---------------
    private int deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardtype() {
        return cardtype;
    }

    public void setCardtype(int cardtype) {
        this.cardtype = cardtype;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getOldid() {
        return oldid;
    }

    public void setOldid(String oldid) {
        this.oldid = oldid;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getLenddept() {
        return lenddept;
    }

    public void setLenddept(String lenddept) {
        this.lenddept = lenddept;
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public String getContect() {
        return contect;
    }

    public void setContect(String contect) {
        this.contect = contect;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLeantime() {
        return leantime;
    }

    public void setLeantime(String leantime) {
        this.leantime = leantime;
    }

    public String getBelongtime() {
        return belongtime;
    }

    public void setBelongtime(String belongtime) {
        this.belongtime = belongtime;
    }

    public int getRenewlimit() {
        return renewlimit;
    }

    public void setRenewlimit(int renewlimit) {
        this.renewlimit = renewlimit;
    }

    public String getReturntime() {
        return returntime;
    }

    public String getAccessory() {
        return accessory;
    }

    public String getExes() {
        return exes;
    }

    public String getPagenum() {
        return pagenum;
    }

    public String getVolumenum() {
        return volumenum;
    }

    public String getRenewer() {
        return renewer;
    }

    public String getReturner() {
        return returner;
    }

    public String getState() {
        return state;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public void setExes(String exes) {
        this.exes = exes;
    }

    public void setPagenum(String pagenum) {
        this.pagenum = pagenum;
    }

    public void setVolumenum(String volumenum) {
        this.volumenum = volumenum;
    }

    public void setRenewer(String renewer) {
        this.renewer = renewer;
    }

    public void setReturner(String returner) {
        this.returner = returner;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getRelenddept() {
        return relenddept;
    }

    public void setRelenddept(String relenddept) {
        this.relenddept = relenddept;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

}