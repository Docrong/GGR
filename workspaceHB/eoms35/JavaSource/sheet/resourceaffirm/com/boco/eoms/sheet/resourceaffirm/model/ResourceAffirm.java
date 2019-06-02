package com.boco.eoms.sheet.resourceaffirm.model;

public class ResourceAffirm {

	/**
	* @header value="资源确认流程"
	* @generatortype value="uuid.hex"
	* @phaseid draft="DraftHumTask" hold="HoldHumTask"
	* @flowid value="031"
	* @sheetid value="01"
	* 
	*/ 
	private String id;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="紧急程度"
	 * @main
	 * @dicttype value="000"
	 */
	private String urgentDegree;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="集团客户业务分类"
	 * @main
	 * @dicttype value="000"
	 */
	private String businesstype1;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="业务部门联系人"
	 * @main
	 * @dicttype value="000"
	 */
	private String bdeptContact;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="业务部门联系人电话"
	 * @main
	 * @texttype
	 */
	private String bdeptContactPhone;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="集团客户编号"
	 * @main
	 * @texttype
	 */
	private String customNo;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="集团客户名称"
	 * @main
	 * @texttype
	 */
	private String customName;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="集团客户联系人"
	 * @main
	 * @texttype
	 */
	private String customContact;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="集团客户联系电话"
	 * @main
	 * @texttype
	 */
	private String customContactPhone;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="集团客户联系地址"
	 * @main
	 * @texttype
	 */
	private String customContactAdd;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="集团客户联系邮编"
	 * @main
	 * @texttype
	 */
	private String customContactPost;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="集团客户行业"
	 * @main
	 * @texttype
	 */
	private String customTrade;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="客户联系邮箱"
	 * @main
	 * @texttype
	 */
	private String customContactEmail;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="客户级别"
	 * @main
	 * @texttype
	 */
	private String customLevel;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="业务需求描述"
	 * @main
	 * @textarea
	 */
	private String bRequirementDesc;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="城市A"
	 * @main
	 * @texttype
	 */
	private String cityA;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="城市Z"
	 * @main
	 * @texttype
	 */
	private String cityZ;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="带宽"
	 * @main
	 * @texttype
	 */
	private String bandwidth;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="数量"
	 * @main
	 * @texttype
	 */
	private String Number;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="端点A"
	 * @main
	 * @texttype
	 */
	private String portA;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="端点A接口类型"
	 * @main
	 * @texttype
	 */
	private String portAInterfaceType;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="端点A详细地址"
	 * @main
	 * @texttype
	 */
	private String portADetailAdd;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="端点A业务设备名称"
	 * @main
	 * @texttype
	 */
	private String portABDeviceName;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="端点A业务设备端口"
	 * @main
	 * @texttype
	 */
	private String portABDevicePort;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="端点A联系人及电话"
	 * @main
	 * @texttype
	 */
	private String portAContactPhone;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="端点Z"
	 * @main
	 * @texttype
	 */
	private String portZ;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="端点Z接口类型"
	 * @main
	 * @texttype
	 */
	private String portZInterfaceType;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="端点Z详细地址"
	 * @main
	 * @texttype
	 */
	private String portZBDeviceName;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="端点Z业务设备端口"
	 * @main
	 * @texttype
	 */
	private String portZBDevicePort;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="端点Z联系人及电话"
	 * @main
	 * @texttype
	 */
	private String portZContactPhone;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="是否预占"
	 * @main
	 * @dicttype value="000"
	 */
	private String isBeforehandOccupy;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="网络部门联系人"
	 * @link
	 * @texttype
	 */
	private String ndeptContact;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="网络部门联系人电话"
	 * @link
	 * @texttype
	 */
	private String ndeptContactPhone;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="处理结果"
	 * @link
	 * @texttype
	 */
	private String dealResult;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="处理说明"
	 * @link
	 * @texttype
	 */
	private String dealDesc;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="网络资源能力确认"
	 * @link
	 * @texttype
	 */
	private String netResCapacity;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="预计完成天数"
	 * @link
	 * @texttype
	 */
	private String expectFinishdays;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="传输专线电路代号"
	 * @link
	 * @texttype
	 */
	private String circuitCode;
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="客户端工程能力确认"
	 * @link
	 * @texttype
	 */
	private String clientPgmCapacity;
	
}
