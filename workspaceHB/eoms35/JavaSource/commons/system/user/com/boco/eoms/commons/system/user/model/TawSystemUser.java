package com.boco.eoms.commons.system.user.model;

import java.io.Serializable;
import java.util.Date;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="TawSystemUser.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_user"
 */
public class TawSystemUser extends BaseObject implements Serializable,
        UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public boolean equals(Object o) {
        if (o instanceof TawSystemUser) {
            TawSystemUser tawSystemUser = (TawSystemUser) o;
            if (this.id != null || this.id.equals(tawSystemUser.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private String id;

    private String operuserid;

    private String userid;

    private String username;

    private String deptid;

    private String deptname;

    private String phone;

    private String email;

    private String mobile;

    private String familyaddress;

    private String sex;

    private String fax;

    private String userdegree;

    private String cptroomid;

    private String cptroomname;

    private Date savetime;

    private String updatetime;

    private String operremotip;

    private String remark;

    private String deleted;

    private String isPartners;//判断是否属于代维人员

    protected boolean credentialsExpired;

    protected boolean enabled;

    protected boolean accountExpired;

    protected boolean accountLocked;

    protected String password; // required

    protected String isfullemploy;

    protected String isrest;

    protected String portalrolename;

    protected String userstatus;

//	protected String centerId;

//	protected String centerName;


    private String attribute1;

    private String attribute2;

    /**
     * 密码连续输入错误次数
     */
    protected Integer failCount;

    public String getPortalrolename() {
        return portalrolename;
    }

    public void setPortalrolename(String portalrolename) {
        this.portalrolename = portalrolename;
    }

    /**
     * @return
     * @hibernate.property length="2"
     * @eoms.show
     * @eoms.cn name="全职或者兼职"
     */
    public String getIsfullemploy() {
        return isfullemploy;
    }

    public void setIsfullemploy(String isfullemploy) {
        this.isfullemploy = isfullemploy;
    }

    /**
     * @return
     * @hibernate.property length="2"
     * @eoms.show
     * @eoms.cn name="是否休假"
     */
    public String getIsrest() {
        return isrest;
    }

    public void setIsrest(String isrest) {
        this.isrest = isrest;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="密码"
     */
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="机房ID"
     */
    public String getCptroomid() {
        return cptroomid;
    }

    public void setCptroomid(String cptroomid) {
        this.cptroomid = cptroomid;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="机房名称"
     */
    public String getCptroomname() {
        return cptroomname;
    }

    public void setCptroomname(String cptroomname) {
        this.cptroomname = cptroomname;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="部门ID"
     */
    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="部门NAME"
     */
    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="EMAIL"
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="家庭住址"
     */
    public String getFamilyaddress() {
        return familyaddress;
    }

    public void setFamilyaddress(String familyaddress) {
        this.familyaddress = familyaddress;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="传真"
     */
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="手机号"
     */
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="增加用户的用户ID"
     */
    public String getOperuserid() {
        return operuserid;
    }

    public void setOperuserid(String operuserid) {
        this.operuserid = operuserid;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="家庭电话"
     */
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="备注"
     */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return
     * @hibernate.property length="20"
     * @eoms.show
     * @eoms.cn name="男女"
     */
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="用户权限"
     */
    public String getUserdegree() {
        return userdegree;
    }

    public void setUserdegree(String userdegree) {
        this.userdegree = userdegree;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="用户ID"
     */
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="用户姓名"
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="操作的远程ID地址"
     */
    public String getOperremotip() {
        return operremotip;
    }

    public void setOperremotip(String operremotip) {
        this.operremotip = operremotip;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="保存时间"
     */
    public Date getSavetime() {
        return savetime;
    }

    public void setSavetime(Date savetime) {
        this.savetime = savetime;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="修改时间"
     */
    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public GrantedAuthority[] getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @hibernate.property column="account_expired" not-null="true"
     * type="yes_no"
     */
    public boolean isAccountExpired() {
        return accountExpired;
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#isAccountNonExpired()
     */
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    /**
     * @hibernate.property column="account_locked" not-null="true" type="yes_no"
     */
    public boolean isAccountLocked() {
        return accountLocked;
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#isAccountNonLocked()
     */
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    /**
     * @hibernate.property column="credentials_expired" not-null="true"
     * type="yes_no"
     */
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#isCredentialsNonExpired()
     */
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    /**
     * @hibernate.property column="account_enabled" type="yes_no"
     */
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     * @eoms.cn name="删除标志"
     */
    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    /**
     * @return
     * @hibernate.property length="2"
     * @eoms.show
     * @eoms.cn name="在线状态"
     */
    public String getUserstatus() {
        return userstatus;
    }

    /**
     * @param userstatus The userstatus to set.
     */
    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }

    /**
     * @return the failCount
     */
    public Integer getFailCount() {
        return failCount;
    }

    /**
     * @param failCount the failCount to set
     */
    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public String getIsPartners() {
        return isPartners;
    }

    public void setIsPartners(String isPartners) {
        this.isPartners = isPartners;
    }

/*	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}*/


    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

}
