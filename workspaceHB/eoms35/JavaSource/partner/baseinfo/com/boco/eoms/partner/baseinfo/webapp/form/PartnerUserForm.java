package com.boco.eoms.partner.baseinfo.webapp.form;

import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:��f��Ϣ
 * </p>
 * <p>
 * Description:��f��Ϣ
 * </p>
 * <p>
 * Tue Feb 10 17:33:14 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() liujinlong
 * @moudle.getVersion() 3.5
 * 
 */
public class PartnerUserForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * ���
	 */
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * 人员姓名�Ա����
	 *
	 */
	private java.lang.String name;
   
	public void setName(java.lang.String name){
		this.name= name;       
	}
   
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *
	 * 厂商（部门）ID
	 *
	 */
	private java.lang.String deptId;
   
	public void setDeptId(java.lang.String deptId){
		this.deptId= deptId;       
	}
   
	public java.lang.String getDeptId(){
		return this.deptId;
	}

	/**
	 *
	 * 厂商名����˾
	 *
	 */
	private java.lang.String deptName;
   
	public void setDeptName(java.lang.String deptName){
		this.deptName= deptName;       
	}
   
	public java.lang.String getDeptName(){
		return this.deptName;
	}
	/**
	 * 地市nodeId
	 */
	private String areaId;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	/**
	 * 所属地市
	 */
	private String areaName ;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	/**
	 * 身份证号码
	 */
	private String personCardNo;

	public String getPersonCardNo() {
		return personCardNo;
	}

	public void setPersonCardNo(String personCardNo) {
		this.personCardNo = personCardNo;
	}
	/**
	 *
	 * ������з�服务地市范围
	 *
	 */
	private java.lang.String areaNames;
   
	public void setAreaNames(java.lang.String areaNames){
		this.areaNames= areaNames;       
	}
   
	public java.lang.String getAreaNames(){
		return this.areaNames;
	}

	/**
	 *
	 * 用户ID
	 *
	 */
	private java.lang.String userId;
   
	public void setUserId(java.lang.String userId){
		this.userId= userId;       
	}
   
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *
	 * 性别�Ա�
	 *
	 */
	private java.lang.String sex;
   
	public void setSex(java.lang.String sex){
		this.sex= sex;       
	}
   
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *
	 * ������Ƭ
	 *
	 */
	private java.lang.String photo;
   
	public void setPhoto(java.lang.String photo){
		this.photo= photo;       
	}
   
	public java.lang.String getPhoto(){
		return this.photo;
	}

	/**
	 *
	 * ��������
	 *
	 */
	private java.lang.String birthdey;
   
	public void setBirthdey(java.lang.String birthdey){
		this.birthdey= birthdey;       
	}
   
	public java.lang.String getBirthdey(){
		return this.birthdey;
	}

	/**
	 *
	 * ѧ�����
	 *
	 */
	private java.lang.String diploma;
   
	public void setDiploma(java.lang.String diploma){
		this.diploma= diploma;       
	}
   
	public java.lang.String getDiploma(){
		return this.diploma;
	}

	/**
	 *
	 * �μӹ���ʱ��
	 *
	 */
	private java.lang.String workTime;
   
	public void setWorkTime(java.lang.String workTime){
		this.workTime= workTime;       
	}
   
	public java.lang.String getWorkTime(){
		return this.workTime;
	}

	/**
	 *
	 * �μӱ���λ����ʱ��
	 *
	 */
	private java.lang.String deptWorkTime;
   
	public void setDeptWorkTime(java.lang.String deptWorkTime){
		this.deptWorkTime= deptWorkTime;       
	}
   
	public java.lang.String getDeptWorkTime(){
		return this.deptWorkTime;
	}

	/**
	 *
	 * ��֤��������
	 *
	 */
	private java.lang.String licenseType;
   
	public void setLicenseType(java.lang.String licenseType){
		this.licenseType= licenseType;       
	}
   
	public java.lang.String getLicenseType(){
		return this.licenseType;
	}

	/**
	 *
	 * ��֤���
	 *
	 */
	private java.lang.String licenseNo;
   
	public void setLicenseNo(java.lang.String licenseNo){
		this.licenseNo= licenseNo;       
	}
   
	public java.lang.String getLicenseNo(){
		return this.licenseNo;
	}

	/**
	 *
	 * ��λ��Ϣ
	 *
	 */
	private java.lang.String post;
   
	public void setPost(java.lang.String post){
		this.post= post;       
	}
   
	public java.lang.String getPost(){
		return this.post;
	}

	/**
	 *
	 * �ڸ�״̬
	 *
	 */
	private java.lang.String postState;
   
	public void setPostState(java.lang.String postState){
		this.postState= postState;       
	}
   
	public java.lang.String getPostState(){
		return this.postState;
	}

	/**
	 *
	 * jϵ�绰
	 *
	 */
	private java.lang.String phone;
   
	public void setPhone(java.lang.String phone){
		this.phone= phone;       
	}
   
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *
	 * �����ʼ�
	 *
	 */
	private java.lang.String email;
   
	public void setEmail(java.lang.String email){
		this.email= email;       
	}
   
	public java.lang.String getEmail(){
		return this.email;
	}
	/**
	 * 对应树节点nodeId
	 */
	private String treeNodeId;
	
	public String getTreeNodeId() {
		return treeNodeId;
	}

	public void setTreeNodeId(String treeNodeId) {
		this.treeNodeId = treeNodeId;
	}
	
    private FormFile accessoryName; //批量录入文件
    
	public FormFile getAccessoryName() {
		return accessoryName;
	}
	public void setAccessoryName(FormFile accessoryName) {
		this.accessoryName = accessoryName;
	}

}