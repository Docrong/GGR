package com.boco.eoms.km.expert.webapp.form;

import com.boco.eoms.base.api.SysMgrs;
import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.system.dict.service.ID2NameService;

/**
 * <p>
 * Title:专家团队
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
public class KmExpertGroupForm extends BaseForm implements java.io.Serializable {

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
    private String createTime;

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 团队名称
     */
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 所属专业
     */
    private String specialty;

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    /**
     * 组长
     */
    private String groupLeader;

    public String getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(String groupLeader) {
        this.groupLeader = groupLeader;
    }

    /**
     * 组员
     */
    private String groupMember;

    public String getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(String groupMember) {
        this.groupMember = groupMember;
    }

    public String getGroupMemberName() {
        if (groupMember == null || groupMember.length() == 0) {
            return "";
        }

        String memberName = "";
        String[] member = groupMember.split(",");
        int length = member.length;
        boolean first = true;
        ID2NameService id2NameService = SysMgrs.getDictMgrs().getID2NameMgr();
        for (int i = 0; i < length; i++) {
            if (first) {
                memberName = id2NameService.id2Name(member[i], "tawSystemUserDao");
                first = false;
            } else {
                memberName = memberName + ',' + id2NameService.id2Name(member[i], "tawSystemUserDao");
            }
        }
        return memberName;
    }

    public boolean equals(Object o) {
        if (o instanceof KmExpertGroupForm) {
            KmExpertGroupForm kmExpertBasic = (KmExpertGroupForm) o;
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