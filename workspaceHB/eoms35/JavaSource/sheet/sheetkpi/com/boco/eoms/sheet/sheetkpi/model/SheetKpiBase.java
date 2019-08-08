/**
 *
 */
package com.boco.eoms.sheet.sheetkpi.model;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class SheetKpiBase implements Serializable {
    private String cname;
    private String ename;
    private String url;
    private String keyid;


    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
