package com.boco.eoms.km.expert.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:基本信息
 * </p>
 * <p>
 * Description:专家基本信息
 * </p>
 * <p>
 * Mon Jun 15 19:14:24 CST 2009
 * </p>
 *
 * @author zhangxb
 * @version 1.0
 */
public class KmExpertBasic extends BaseObject {

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
     * 创建人
     */
    private String createUser;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 创建时间
     */
    private java.util.Date createTime;

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 专家姓名
     */
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    /**
     * 所属部门
     */
    private String deptId;

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptId() {
        return this.deptId;
    }

    /**
     * 联系电话
     */
    private String phone;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    /**
     * 专家级别
     */
    private String expertLevel;

    public void setExpertLevel(String expertLevel) {
        this.expertLevel = expertLevel;
    }

    public String getExpertLevel() {
        return this.expertLevel;
    }

    /**
     * 专家类型
     */
    private String expertClass;

    public String getExpertClass() {
        return expertClass;
    }

    public void setExpertClass(String expertClass) {
        this.expertClass = expertClass;
    }

    /**
     * 所属专业
     */
    private String specialty;

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return this.specialty;
    }

    /**
     * 所属地域
     */
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 专家特长
     */
    private String intro;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 电子邮件
     */
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    /**
     * 手机
     */
    private String mobile;

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }

    /**
     * 职位
     */
    private String position;

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    /**
     * 性别
     */
    private int sex;

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSex() {
        return this.sex;
    }

    /**
     * 备注
     */
    private String notes;

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return this.notes;
    }

    /**
     * 员工编号
     */
    private String expertNumber;

    public void setExpertNumber(String expertNumber) {
        this.expertNumber = expertNumber;
    }

    public String getExpertNumber() {
        return this.expertNumber;
    }

    /**
     * 户口所在
     */
    private String homeSpace;

    public void setHomeSpace(String homeSpace) {
        this.homeSpace = homeSpace;
    }

    public String getHomeSpace() {
        return this.homeSpace;
    }

    /**
     * 参加本单位时间
     */
    private java.util.Date privateJobDate;

    public void setPrivateJobDate(java.util.Date privateJobDate) {
        this.privateJobDate = privateJobDate;
    }

    public java.util.Date getPrivateJobDate() {
        return this.privateJobDate;
    }

    /**
     * 所在公司
     */
    private String company;

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return this.company;
    }

    /**
     * 民族
     */
    private String nation;

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNation() {
        return this.nation;
    }

    /**
     * 出生日期
     */
    private java.util.Date birthday;

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    public java.util.Date getBirthday() {
        return this.birthday;
    }

    /**
     * 政治面貌
     */
    private String polity;

    public void setPolity(String polity) {
        this.polity = polity;
    }

    public String getPolity() {
        return this.polity;
    }

    /**
     * 最高学历
     */
    private String education;

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducation() {
        return this.education;
    }

    /**
     * 身份证号
     */
    private String idCard;

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCard() {
        return this.idCard;
    }

    /**
     * 参加工作时间
     */
    private java.util.Date joinJobDate;

    public void setJoinJobDate(java.util.Date joinJobDate) {
        this.joinJobDate = joinJobDate;
    }

    public java.util.Date getJoinJobDate() {
        return this.joinJobDate;
    }

    /**
     * 是否在线
     */
    private int isOnline;

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public int getIsOnline() {
        return this.isOnline;
    }

    /**
     * 相片
     */
    private String imgPath;

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgPath() {
        return this.imgPath;
    }

    /**
     * 临时相片
     */
    private String imgPathTemp;

    public void setImgPathTemp(String imgPathTemp) {
        this.imgPathTemp = imgPathTemp;
    }

    public String getImgPathTemp() {
        return this.imgPathTemp;
    }

    /**
     * 专家组级别
     */
    private int teamLeader;

    public void setTeamLeader(int teamLeader) {
        this.teamLeader = teamLeader;
    }

    public int getTeamLeader() {
        return this.teamLeader;
    }

    public boolean equals(Object o) {
        if (o instanceof KmExpertBasic) {
            KmExpertBasic kmExpertBasic = (KmExpertBasic) o;
            if (this.id != null || this.id.equals(kmExpertBasic.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}