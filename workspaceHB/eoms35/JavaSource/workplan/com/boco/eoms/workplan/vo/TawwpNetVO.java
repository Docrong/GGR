package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 作业计划网元信息数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

public class TawwpNetVO {

    private String id; //标识
    private String name; //年度作业计划名称
    private String sysTypeId; //系统类别
    private String sysTypeName; //系统类别名称
    private String netTypeId; //网元类型
    private String netTypeName; //网元类型名称
    private String mynettypeid;//专业代码
    private String mynettypeName;//专业代码名称
    private String deptId; //部门标识
    private String deptName; //部门名称
    private String roomId; //机房标识
    private String roomName; //机房名称
    private String serialNo; //序列号
    private String vendor; //制造商
    private String deleted; //删除标志

    public TawwpNetVO() {
    }

    public String getId() {
        if (id == null) {
            id = "";
        }

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        if (name == null) {
            name = "";
        }

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptId() {
        if (deptId == null) {
            deptId = "";
        }

        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        if (deptName == null) {
            deptName = "";
        }

        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeleted() {
        if (deleted == null) {
            deleted = "";
        }

        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getNetTypeId() {
        if (netTypeId == null) {
            netTypeId = "";
        }

        return netTypeId;
    }

    public void setNetTypeId(String netTypeId) {
        this.netTypeId = netTypeId;
    }

    public String getNetTypeName() {
        if (netTypeName == null) {
            netTypeName = "";
        }

        return netTypeName;
    }

    public void setNetTypeName(String netTypeName) {
        this.netTypeName = netTypeName;
    }

    public String getRoomId() {
        if (roomId == null) {
            roomId = "";
        }

        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        if (roomName == null) {
            roomName = "";
        }

        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSerialNo() {
        if (serialNo == null) {
            serialNo = "";
        }

        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getSysTypeId() {
        if (sysTypeId == null) {
            sysTypeId = "";
        }

        return sysTypeId;
    }

    public void setSysTypeId(String sysTypeId) {
        this.sysTypeId = sysTypeId;
    }

    public String getSysTypeName() {
        if (sysTypeName == null) {
            sysTypeName = "";
        }

        return sysTypeName;
    }

    public void setSysTypeName(String sysTypeName) {
        this.sysTypeName = sysTypeName;
    }

    public String getVendor() {
        if (vendor == null) {
            vendor = "";
        }

        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getMynettypeid() {
        return mynettypeid;
    }

    public void setMynettypeid(String mynettypeid) {
        this.mynettypeid = mynettypeid;
    }

    public String getMynettypeName() {
        return mynettypeName;
    }

    public void setMynettypeName(String mynettypeName) {
        this.mynettypeName = mynettypeName;
    }

}
