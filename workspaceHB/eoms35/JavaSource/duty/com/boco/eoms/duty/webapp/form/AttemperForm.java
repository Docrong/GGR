package com.boco.eoms.duty.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.common.util.StaticMethod;

/**
 * <p>
 * Title:网调信息
 * </p>
 * <p>
 * Description:网调信息管理
 * </p>
 * <p>
 * Thu Apr 02 14:11:04 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 李江红
 * @moudle.getVersion() 3.5
 * 
 */
public class AttemperForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String netDeptListDict = "1040201";
	public final static String STATUS0 = "0"; // 草稿
	public final static String STATUS1 = "1"; // 未结束
	public final static String STATUS2 = "2"; // 结束
	public final static String STATUS3 = "3"; // 已删除
	
	public final static String STRUTSACTION_ADD = "1";
	public final static String STRUTSACTION_EDIT = "2";
	public final static String STRUTSACTION_DELLIST = "4"; // 删除列表
	public final static String STRUTSACTION_VIEWLIST = "5"; // 子过程呈现列表
	public final static String STRUTSACTION_ISSUANCE ="3"; // 发布
	public final static String STRUTSACTION_QUERYLIST = "6"; // 查询列表
	public final static String STRUTSACTION_MANAGERLIST = "7"; // 管理列表
	public final static String STRUTSACTION_STATLIST = "8"; // 统计列表
	
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
	 *
	 * 创建人部门
	 *
	 */
	private String deptId;
   
	public void setDeptId(String deptId){
		this.deptId= deptId;       
	}
   
	public String getDeptId(){
		return this.deptId;
	}

	/**
	 *
	 * 创建人
	 *
	 */
	private String cruser;
   
	public void setCruser(String cruser){
		this.cruser= cruser;       
	}
   
	public String getCruser(){
		return this.cruser;
	}

	/**
	 *
	 * 创建时间
	 *
	 */
	private String crtime;
   
	public void setCrtime(String crtime){
		this.crtime= crtime;       
	}
   
	public String getCrtime(){
		return this.crtime;
	}

	/**
	 *
	 * 网调信息编号
	 *
	 */
	private String sheetId;
   
	public void setSheetId(String sheetId){
		this.sheetId= sheetId;       
	}
   
	public String getSheetId(){
		return this.sheetId;
	}

	/**
	 *
	 * 任务工单号
	 *
	 */
	private String planSheetId;
   
	public void setPlanSheetId(String planSheetId){
		this.planSheetId= planSheetId;       
	}
   
	public String getPlanSheetId(){
		return this.planSheetId;
	}

	/**
	 *
	 * 机房
	 *
	 */
	private String roomId;
   
	public void setRoomId(String roomId){
		this.roomId= roomId;       
	}
   
	public String getRoomId(){
		return this.roomId;
	}

	/**
	 *
	 * 联系人和联系方式
	 *
	 */
	private String recordUser;
   
	public void setRecordUser(String recordUser){
		this.recordUser= recordUser;       
	}
   
	public String getRecordUser(){
		return this.recordUser;
	}

	/**
	 *
	 * 发起部门
	 *
	 */
	private String recordDept;
   
	public void setRecordDept(String recordDept){
		this.recordDept= recordDept;       
	}
   
	public String getRecordDept(){
		return this.recordDept;
	}

	/**
	 *
	 * 责任人联系方式
	 *
	 */
	private String recordContace;
   
	public void setRecordContace(String recordContace){
		this.recordContace= recordContace;       
	}
   
	public String getRecordContace(){
		return this.recordContace;
	}

	/**
	 *
	 * 发起部门
	 *
	 */
	private String recordDeptName;
   
	public void setRecordDeptName(String recordDeptName){
		this.recordDeptName= recordDeptName;       
	}
   
	public String getRecordDeptName(){
		return this.recordDeptName;
	}

	/**
	 *
	 * 联系方式
	 *
	 */
	private String contact;
   
	public void setContact(String contact){
		this.contact= contact;       
	}
   
	public String getContact(){
		return this.contact;
	}

	/**
	 *
	 * 开始时间
	 *
	 */
	private String beginTime;
   
	public void setBeginTime(String beginTime){
		this.beginTime= beginTime;       
	}
   
	public String getBeginTime(){
		return this.beginTime;
	}

	/**
	 *
	 * 结束时间
	 *
	 */
	private String endTime;
   
	public void setEndTime(String endTime){
		this.endTime= endTime;       
	}
   
	public String getEndTime(){
		return this.endTime;
	}

	/**
	 *
	 * 时长
	 *
	 */
	private String timeLong;
   
	public void setTimeLong(String timeLong){
		this.timeLong= timeLong;       
	}
   
	public String getTimeLong(){
		return this.timeLong;
	}

	/**
	 *
	 * 专业名称
	 *
	 */
	private String speciality;
   
	public void setSpeciality(String speciality){
		this.speciality= speciality;       
	}
   
	public String getSpeciality(){
		return this.speciality;
	}

	/**
	 *
	 * 传输专业
	 *
	 */
	private String subSpeciality;
   
	public void setSubSpeciality(String subSpeciality){
		this.subSpeciality= subSpeciality;       
	}
   
	public String getSubSpeciality(){
		return this.subSpeciality;
	}

	/**
	 *
	 * 是否影响业务
	 *
	 */
	private String isAppEffect;
   
	public void setIsAppEffect(String isAppEffect){
		this.isAppEffect= isAppEffect;       
	}
   
	public String getIsAppEffect(){
		return this.isAppEffect;
	}

	/**
	 *
	 * 影响业务
	 *
	 */
	private String effectOperation;
   
	public void setEffectOperation(String effectOperation){
		this.effectOperation= effectOperation;       
	}
   
	public String getEffectOperation(){
		return this.effectOperation;
	}

	/**
	 *
	 * 涉及网元
	 *
	 */
	private String netNames;
   
	public void setNetNames(String netNames){
		this.netNames= netNames;       
	}
   
	public String getNetNames(){
		return this.netNames;
	}

	/**
	 *
	 * 设备所属部门
	 *
	 */
	private String netDeptList;
   
	public void setNetDeptList(String netDeptList){
		this.netDeptList= netDeptList;       
	}
   
	public String getNetDeptList(){
		return this.netDeptList;
	}

	/**
	 *
	 * 设备所属部门
	 *
	 */
	private String netDeptName;
   
	public void setNetDeptName(String netDeptName){
		this.netDeptName= netDeptName;       
	}
   
	public String getNetDeptName(){
		return this.netDeptName;
	}

	/**
	 *
	 * 调整原因
	 *
	 */
	private String adjustReason;
   
	public void setAdjustReason(String adjustReason){
		this.adjustReason= adjustReason;       
	}
   
	public String getAdjustReason(){
		return StaticMethod.nullObject2String(this.adjustReason).trim();
	}

	/**
	 *
	 * 主题
	 *
	 */
	private String title;
   
	public void setTitle(String title){
		this.title= title;       
	}
   
	public String getTitle(){
		return StaticMethod.nullObject2String(this.title).trim();
	}

	/**
	 *
	 * 网调内容
	 *
	 */
	private String content;
   
	public void setContent(String content){
		this.content= content;       
	}
   
	public String getContent(){
		return StaticMethod.nullObject2String(this.content).trim();
	}

	/**
	 *
	 * 备注
	 *
	 */
	private String remark;
   
	public void setRemark(String remark){
		this.remark= remark;       
	}
   
	public String getRemark(){
		return StaticMethod.nullObject2String(this.remark).trim();
	}

	/**
	 *
	 * 删除理由
	 *
	 */
	private String delReason;
   
	public void setDelReason(String delReason){
		this.delReason= delReason;       
	}
   
	public String getDelReason(){
		return StaticMethod.nullObject2String(this.delReason).trim();
	}

	/**
	 *
	 * 状态
	 *
	 */
	private String status;
   
	public void setStatus(String status){
		this.status= status;       
	}
   
	public String getStatus(){
		return this.status;
	}

	/**
	 *
	 * 归档时间
	 *
	 */
	private String holdTime;
   
	public void setHoldTime(String holdTime){
		this.holdTime= holdTime;       
	}
   
	public String getHoldTime(){
		return this.holdTime;
	}

	/**
	 *
	 * 结束人员
	 *
	 */
	private String finishUser;
   
	public void setFinishUser(String finishUser){
		this.finishUser= finishUser;       
	}
   
	public String getFinishUser(){
		return this.finishUser;
	}

	/**
	 *
	 * 标识
	 *
	 */
	private String serial;
   
	public void setSerial(String serial){
		this.serial= serial;       
	}
   
	public String getSerial(){
		return this.serial;
	}

	/**
	 *
	 * 是否删除
	 *
	 */
	private String deleted;
   
	public void setDeleted(String deleted){
		this.deleted= deleted;       
	}
   
	public String getDeleted(){
		return this.deleted;
	}
	
	/**
	 * 未结束数目
	 */
	private int ifSubFinish;

	public int getIfSubFinish() {
		return ifSubFinish;
	}

	public void setIfSubFinish(int ifSubFinish) {
		this.ifSubFinish = ifSubFinish;
	}
	
	/**
	 * 附件
	 */
	private String accessory;

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	
	/**
	 *  天数
	 */
	private int days;

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	
	/**
	 * 创建人名字
	 */
	private String cruserName;

	public String getCruserName() {
		return cruserName;
	}

	public void setCruserName(String cruserName) {
		this.cruserName = cruserName;
	}
	
	/**
	 * 开始时间起始
	 */
	private String fromBeginTime;
	
	/**
	 * 开始时间终止
	 */
	private String toBeginTime;

	public String getFromBeginTime() {
		return fromBeginTime;
	}

	public void setFromBeginTime(String fromBeginTime) {
		this.fromBeginTime = fromBeginTime;
	}

	public String getToBeginTime() {
		return toBeginTime;
	}

	public void setToBeginTime(String toBeginTime) {
		this.toBeginTime = toBeginTime;
	}
	
	/**
	 * 操作类型
	 */
	private String strutsAction;

	public String getStrutsAction() {
		return strutsAction;
	}

	public void setStrutsAction(String strutsAction) {
		this.strutsAction = strutsAction;
	}
	
	/**
	 * 创建时间起始
	 */
	private String fromInsertTime;

	public String getFromInsertTime() {
		return fromInsertTime;
	}

	public void setFromInsertTime(String fromInsertTime) {
		this.fromInsertTime = fromInsertTime;
	}
	
	/**
	 * 创建时间结束
	 */
	private String toInsertTime;

	public String getToInsertTime() {
		return toInsertTime;
	}

	public void setToInsertTime(String toInsertTime) {
		this.toInsertTime = toInsertTime;
	}
	
	/**
	 * 子过程内容
	 */
	private String subContent;

	public String getSubContent() {
		return subContent;
	}

	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}
	
	/**
	 * 子过程数量
	 */
	private int subNum;

	public int getSubNum() {
		return subNum;
	}

	public void setSubNum(int subNum) {
		this.subNum = subNum;
	}
	

}