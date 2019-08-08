package com.boco.eoms.duty.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.common.util.StaticMethod;

/**
 * <p>
 * Title:通用故障记录
 * </p>
 * <p>
 * Description:通用故障记录功能
 * </p>
 * <p>
 * Mon Mar 23 15:39:20 CST 2009
 * </p>
 *
 * @moudle.getAuthor() 李江红
 * @moudle.getVersion() 3.5
 */
public class FaultCommontForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    public static final String estateYes = "1"; // 已归档
    public static final String estateNo = "0"; // 未归档

    public static final String appEffectYes = "1"; // 影响业务
    public static final String appEffectNo = "0"; // 不影响业务

    public static final int ActionSearch = 1; // 查询
    public static final int ActionState = 2; // 统计
    public static final int ActionExcel = 3; // 导出Excel
    public static final int ActionAdd = 4; // 新增
    public static final int ActionEdit = 5; // 编辑
    public static final int ActionDel = 6; // 删除

    public static final String typeIdDictType = "1040402"; // 故障类别数据字典

    /**
     * 锟斤拷锟�
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 故障编号
     */
    private String sequenceNo;

    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getSequenceNo() {
        return this.sequenceNo;
    }

    /**
     * 主题
     */
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    /**
     * 故障记录人
     */
    private String greffier;

    public void setGreffier(String greffier) {
        this.greffier = greffier;
    }

    public String getGreffier() {
        return this.greffier;
    }

    /**
     * 申告人、部门
     */
    private String declarer;

    public void setDeclarer(String declarer) {
        this.declarer = declarer;
    }

    public String getDeclarer() {
        return this.declarer;
    }

    /**
     * 是否影响业务
     */
    private String isAppEffect;

    public void setIsAppEffect(String isAppEffect) {
        this.isAppEffect = isAppEffect;
    }

    public String getIsAppEffect() {
        return this.isAppEffect;
    }

    /**
     * 是否监控室第一时间发现
     */
    private String isFirstFind;

    public void setIsFirstFind(String isFirstFind) {
        this.isFirstFind = isFirstFind;
    }

    public String getIsFirstFind() {
        return this.isFirstFind;
    }

    /**
     * 故障开始时间
     */
    private String beginTime;

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getBeginTime() {
        return this.beginTime;
    }

    /**
     * 故障结束时间
     */
    private String endTime;

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    /**
     * 业务恢复时间
     */
    private String resumeTime;

    public void setResumeTime(String resumeTime) {
        this.resumeTime = resumeTime;
    }

    public String getResumeTime() {
        return this.resumeTime;
    }

    /**
     * 上报集团时间
     */
    private String reportTime;

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportTime() {
        return this.reportTime;
    }

    /**
     * 联系电话
     */
    private String linkPhone;

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getLinkPhone() {
        return this.linkPhone;
    }

    /**
     * 故障地点
     */
    private String faultAddress;

    public void setFaultAddress(String faultAddress) {
        this.faultAddress = faultAddress;
    }

    public String getFaultAddress() {
        return this.faultAddress;
    }

    /**
     * 故障现象
     */
    private String faultPhenomenon;

    public void setFaultPhenomenon(String faultPhenomenon) {
        this.faultPhenomenon = faultPhenomenon;
    }

    public String getFaultPhenomenon() {
        return StaticMethod.nullObject2String(this.faultPhenomenon).trim();
    }

    /**
     * 影响业务情况
     */
    private String appEffect;

    public void setAppEffect(String appEffect) {
        this.appEffect = appEffect;
    }

    public String getAppEffect() {
        return this.appEffect;
    }

    /**
     * 故障原因
     */
    private String faultCause;

    public void setFaultCause(String faultCause) {
        this.faultCause = faultCause;
    }

    public String getFaultCause() {
        return StaticMethod.nullObject2String(this.faultCause).trim();
    }

    /**
     * 备注
     */
    private String faultComment;

    public void setFaultComment(String faultComment) {
        this.faultComment = faultComment;
    }

    public String getFaultComment() {
        return StaticMethod.nullObject2String(this.faultComment).trim();
    }

    /**
     * 监控室处理情况
     */
    private String dealDetail;

    public void setDealDetail(String dealDetail) {
        this.dealDetail = dealDetail;
    }

    public String getDealDetail() {
        return StaticMethod.nullObject2String(this.dealDetail).trim();
    }

    /**
     * 分类id
     */
    private String typeId;

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeId() {
        return this.typeId;
    }

    /**
     * 相关工单号码
     */
    private String serialNos;

    public void setSerialNos(String serialNos) {
        this.serialNos = serialNos;
    }

    public String getSerialNos() {
        return this.serialNos;
    }

    /**
     * 上报工单号码
     */
    private String upSerialNo;

    public void setUpSerialNo(String upSerialNo) {
        this.upSerialNo = upSerialNo;
    }

    public String getUpSerialNo() {
        return this.upSerialNo;
    }

    /**
     * 报通信管理局时间
     */
    private String informOfficeTime;

    public void setInformOfficeTime(String informOfficeTime) {
        this.informOfficeTime = informOfficeTime;
    }

    public String getInformOfficeTime() {
        return this.informOfficeTime;
    }

    /**
     * 归档用户
     */
    private String issueUser;

    public void setIssueUser(String issueUser) {
        this.issueUser = issueUser;
    }

    public String getIssueUser() {
        return this.issueUser;
    }

    /**
     * 归档时间
     */
    private String issueTime;

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getIssueTime() {
        return this.issueTime;
    }

    /**
     * 录入时间
     */
    private String inputTime;

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public String getInputTime() {
        return this.inputTime;
    }

    /**
     * 录入人
     */
    private String inputUser;

    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    public String getInputUser() {
        return this.inputUser;
    }

    /**
     * 信息状态
     */
    private String estate;

    public void setEstate(String estate) {
        this.estate = estate;
    }

    public String getEstate() {
        return this.estate;
    }

    /**
     * 相关记录ID
     */
    private String recordPerId;

    public void setRecordPerId(String recordPerId) {
        this.recordPerId = recordPerId;
    }

    public String getRecordPerId() {
        return this.recordPerId;
    }

    /**
     * 机房ID
     */
    private String roomId;

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return this.roomId;
    }

    /**
     * 故障工单号
     */
    private String serialno;

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getSerialno() {
        return this.serialno;
    }

    /**
     * 故障历时时间
     */
    private int timeSlot;

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getTimeSlot() {
        return this.timeSlot;
    }

    /**
     * 业务回复时间
     */
    private int resumeTimeSlot;

    public void setResumeTimeSlot(int resumeTimeSlot) {
        this.resumeTimeSlot = resumeTimeSlot;
    }

    public int getResumeTimeSlot() {
        return this.resumeTimeSlot;
    }

    /**
     * 发生时间开始
     */
    private String fromBeginTime;

    public void setFromBeginTime(String fromBeginTime) {
        this.fromBeginTime = fromBeginTime;
    }

    public String getFromBeginTime() {
        return this.fromBeginTime;
    }

    /**
     * 发生时间结束
     */
    private String toBeginTime;

    public void setToBeginTime(String toBeginTime) {
        this.toBeginTime = toBeginTime;
    }

    public String getToBeginTime() {
        return this.toBeginTime;
    }

    /**
     * 动作标识
     */
    private int strutsAction;

    public int getStrutsAction() {
        return strutsAction;
    }

    public void setStrutsAction(int strutsAction) {
        this.strutsAction = strutsAction;
    }

    /**
     * 故障地点
     */
    private String faultAddressId;

    public String getFaultAddressId() {
        return faultAddressId;
    }

    public void setFaultAddressId(String faultAddressId) {
        this.faultAddressId = faultAddressId;
    }

}