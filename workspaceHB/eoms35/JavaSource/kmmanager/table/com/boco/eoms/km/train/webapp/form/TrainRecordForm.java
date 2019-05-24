package com.boco.eoms.km.train.webapp.form;

import org.apache.struts.upload.FormFile;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:培训需求
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 16:11:13 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 * 
 */
public class TrainRecordForm extends BaseForm implements java.io.Serializable {

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
	 * 培训名字
	 */
	private String trainName;
	
	/**
	 * 培训内容
	 */
	private String trainContent;
	
	/**
	 * 培训地点
	 */
	private String trainAddress;
	
	/**
	 * 培训天数
	 */
	private String trainTime;
	
	/**
	 * 所属部门
	 */
	private String trainDept;
	
	/**
	 * 文件编号
	 */
	private String trainDocument;
	
	/**
	 * 厂家
	 */
	private String trainVender;
	
	/**
	 * 专业
	 */
	private String trainSpeciality;
	
	/**
	 * 设备类型
	 */
	private String trainType;
	
	/**
	 * 培训人
	 */
	private String trainUser;
	
	/**
	 * 培训时间
	 */
	private String trainDate;
	
	/**
	 *  中心
	 */
	private String trainCenter;
	
	/**
	 *  组织单位
	 */
	private String trainUnit;
	
	/**
	 * 性别
	 */
	private String trainUserSex;
	
	/**
	 * 附件名字
	 */
	private String trainFile;
	
	private FormFile file;
	
	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getTrainContent() {
		return trainContent;
	}

	public void setTrainContent(String trainContent) {
		this.trainContent = trainContent;
	}

	public String getTrainAddress() {
		return trainAddress;
	}

	public void setTrainAddress(String trainAddress) {
		this.trainAddress = trainAddress;
	}

	public String getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(String trainTime) {
		this.trainTime = trainTime;
	}

	public String getTrainDept() {
		return trainDept;
	}

	public void setTrainDept(String trainDept) {
		this.trainDept = trainDept;
	}

	public String getTrainDocument() {
		return trainDocument;
	}

	public void setTrainDocument(String trainDocument) {
		this.trainDocument = trainDocument;
	}

	public String getTrainVender() {
		return trainVender;
	}

	public void setTrainVender(String trainVender) {
		this.trainVender = trainVender;
	}

	public String getTrainSpeciality() {
		return trainSpeciality;
	}

	public void setTrainSpeciality(String trainSpeciality) {
		this.trainSpeciality = trainSpeciality;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public String getTrainUser() {
		return trainUser;
	}

	public void setTrainUser(String trainUser) {
		this.trainUser = trainUser;
	}

	public String getTrainDate() {
		return trainDate;
	}

	public void setTrainDate(String trainDate) {
		this.trainDate = trainDate;
	}

	public String getTrainCenter() {
		return trainCenter;
	}

	public void setTrainCenter(String trainCenter) {
		this.trainCenter = trainCenter;
	}

	public String getTrainUnit() {
		return trainUnit;
	}

	public void setTrainUnit(String trainUnit) {
		this.trainUnit = trainUnit;
	}

	public String getTrainUserSex() {
		return trainUserSex;
	}

	public void setTrainUserSex(String trainUserSex) {
		this.trainUserSex = trainUserSex;
	}

	public String getTrainFile() {
		return trainFile;
	}

	public void setTrainFile(String trainFile) {
		this.trainFile = trainFile;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}
}