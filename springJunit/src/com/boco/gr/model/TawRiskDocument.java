package com.boco.gr.model;

/**
 * �����ĵ�
 */
public class TawRiskDocument {
    private String id;//����
    private String deleted;//ɾ����ʶ��0-������1-��ɾ��
    private String riskType;//����initDicId="67801"
    private String isShow;//�Ƿ���ͼ�г���

    private String docTitle;//���ϱ���
    private String docDesc;//��������
    private String docLinkName;//���� ������taw_commons_accessories�� accessoriesname

    private String uploadTime;//�ϴ�ʱ��
    private String uploadUserId;//�ϴ���-ID
    private String uploadUserDeptid;//�ϴ��˲���-ID
    private String uploadUserPhone;//�ϴ�����ϵ��ʽ

    private String updateTime;//���һ���޸�ʱ��
    private String updateUserId;//���һ���޸���-ID
    private String updateUserDeptid;//���һ���޸��˲���-ID
    private String updateUserPhone;//���һ���޸�����ϵ��ʽ

    private String sysNo;//���


    public TawRiskDocument() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocDesc() {
        return docDesc;
    }

    public void setDocDesc(String docDesc) {
        this.docDesc = docDesc;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(String uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public String getUploadUserDeptid() {
        return uploadUserDeptid;
    }

    public void setUploadUserDeptid(String uploadUserDeptid) {
        this.uploadUserDeptid = uploadUserDeptid;
    }

    public String getDocLinkName() {
        return docLinkName;
    }

    public void setDocLinkName(String docLinkName) {
        this.docLinkName = docLinkName;
    }

    public String getRiskType() {
        if (riskType == null) {
            riskType = "";
        }
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getUploadUserPhone() {
        return uploadUserPhone;
    }

    public void setUploadUserPhone(String uploadUserPhone) {
        this.uploadUserPhone = uploadUserPhone;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserDeptid() {
        return updateUserDeptid;
    }

    public void setUpdateUserDeptid(String updateUserDeptid) {
        this.updateUserDeptid = updateUserDeptid;
    }

    public String getUpdateUserPhone() {
        return updateUserPhone;
    }

    public void setUpdateUserPhone(String updateUserPhone) {
        this.updateUserPhone = updateUserPhone;
    }

    public String getIsShow() {
        if (isShow == null) {
            isShow = "";
        }
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getSysNo() {
        return sysNo;
    }

    public void setSysNo(String sysNo) {
        this.sysNo = sysNo;
    }


}
