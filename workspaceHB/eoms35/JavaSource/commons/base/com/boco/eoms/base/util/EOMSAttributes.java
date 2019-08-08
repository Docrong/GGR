package com.boco.eoms.base.util;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-19 11:35:37
 * </p>
 *
 * @author 鏇查潤娉�
 * @version 1.0
 */
public class EOMSAttributes {
    /**
     * 浜嬩緥
     */
    private String sample;

    /**
     * 鐧婚檰鏂瑰紡锛屽彲涓簊so锛宎cegi
     */
    private String loginType;

    /**
     * 榛樿姣忛〉鏄剧ず鏉℃暟
     */
    private Integer pageSize;

    /**
     * cas server 鍦板潃
     */
    private String casUrl;

    /**
     * eoms鍦板潃
     */
    private String eomsUrl;

    /**
     * 鏁版嵁搴撶被鍨�
     */
    private String dbType;

    /**
     * 鐗堟湰鏄惁鍙戝竷
     */
    private String release;

    /**
     * 闃熷垪鏄惁鍚敤
     */
    private String sequenceOpen;

    /**
     * 鑷畾涔夊浘鐗囪祫婧愭枃浠跺湴鍧�
     */
    private String propertiesFilePath;

    /**
     * wap宸ュ崟url
     */
    private String wapSheetUrl;

    /**
     * 鐗堟湰鏃ュ織鎵撳嵃璺緞
     */
    private String versionPath;

    /**
     * 澶氳彍鍗昳p鏄惁鍚敤 on涓哄惎鐢� off涓虹鐢�
     */
    private String menu_ipOpen;

    public String getMenu_ipOpen() {
        return menu_ipOpen;
    }

    public void setMenu_ipOpen(String menu_ipOpen) {
        this.menu_ipOpen = menu_ipOpen;
    }

    public String getSequenceOpen() {
        return sequenceOpen;
    }

    public void setSequenceOpen(String sequenceOpen) {
        this.sequenceOpen = sequenceOpen;
    }

    /**
     * @return the release
     */
    public String getRelease() {
        return release;
    }

    /**
     * @param release the release to set
     */
    public void setRelease(String release) {
        this.release = release;
    }

    /**
     * @return the sample
     */
    public String getSample() {
        return sample;
    }

    /**
     * @param sample the sample to set
     */
    public void setSample(String sample) {
        this.sample = sample;
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCasUrl() {
        return casUrl;
    }

    public void setCasUrl(String casUrl) {
        this.casUrl = casUrl;
    }

    public String getEomsUrl() {
        return eomsUrl;
    }

    public void setEomsUrl(String eomsUrl) {
        this.eomsUrl = eomsUrl;
    }

    /**
     * @return the loginType
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * @param loginType the loginType to set
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    /**
     * @return the dbType
     */
    public String getDbType() {
        return dbType;
    }

    /**
     * @param dbType the dbType to set
     */
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getPropertiesFilePath() {
        return propertiesFilePath;
    }

    public void setPropertiesFilePath(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;
    }

    public String getWapSheetUrl() {
        return wapSheetUrl;
    }

    public void setWapSheetUrl(String wapSheetUrl) {
        this.wapSheetUrl = wapSheetUrl;
    }

    /**
     * @return the versionPath
     */
    public String getVersionPath() {
        return versionPath;
    }

    /**
     * @param versionPath the versionPath to set
     */
    public void setVersionPath(String versionPath) {
        this.versionPath = versionPath;
    }

    /**
     * 娉ㄥ唽鐨処P鏄犲皠灞炴��
     */
    private Map register;

    /**
     * 娉ㄥ唽鐨勬帴鍙ｈ皟鐢ㄨ繃婊�
     */
    private List interfaceList;

    public List getInterfaceList() {
        return interfaceList;
    }

    public void setInterfaceList(List interfaceList) {
        this.interfaceList = interfaceList;
    }

    public Map getRegister() {
        return register;
    }

    public void setRegister(Map register) {
        this.register = register;
    }

}
