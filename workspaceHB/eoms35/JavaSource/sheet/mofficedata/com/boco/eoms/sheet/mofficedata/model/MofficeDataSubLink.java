package com.boco.eoms.sheet.mofficedata.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 *
 * @author weichao
 * @version 3.5
 */

public class MofficeDataSubLink implements Serializable {

    /**
     * @date 2016-3-24上午09:12:39
     * @author weichao
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 关联的link表的Id
     */
    private String parentLinkId;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 局数据工单状态
     */
    private String sheetStatus;

    /**
     * 局数据工单操作人
     */
    private String operateUserName;

    /**
     * EOMS系统工单的sheetId
     */
    private String sheetId;
    /**
     * 关联的 局数据制作的 preLinkId
     */
    private String preLinkId;


    public String getPreLinkId() {
        return preLinkId;
    }

    public void setPreLinkId(String preLinkId) {
        this.preLinkId = preLinkId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

    public String getParentLinkId() {
        return parentLinkId;
    }

    public void setParentLinkId(String parentLinkId) {
        this.parentLinkId = parentLinkId;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getSheetStatus() {
        return sheetStatus;
    }

    public void setSheetStatus(String sheetStatus) {
        this.sheetStatus = sheetStatus;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }


}