package com.boco.eoms.businessupport.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="OrderSheet.java.do"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="OrderSheet"
 */
public class OrderSheet implements Serializable {

    //	定单表主键id
    private String id;
    //产品规格表id						//使用productID 预留
    private String pruductId;
    //定单类型							//预留
    private String orderType;
    //资源确认的CRM侧工单号
    private String mainProductInstanceCode;
    //定单编号
    private String orderNumber;
    //紧急程度
    private String urgentDegree;
    //生成时间
    private Date creatTime;
    //竣工时间
    private Date endTime;
    //完成期限
    private Date completeLimit;
    //相关定单id(预留1)
    private String p_order_id;
    //状态
    private String status;
    //竣工标识
    private String isCompleted;
    //专线类型 GPRS、互联网、传输
    private String orderBuisnessType;
    //定单业务表标识
    private String orderBuisnessMark;            //预留
    //变更时间
    private Date changeTime;
    //撤销时间
    private Date cancelTime;
    //业务唯一标识(预留2)
    private String businessCode;
    //主题
    private String title;
    //集团编号
    private String customNo;        //使用groupCustomNo，该字段预留
    //客户名称
    private String customName;
    //客户经理联系电话
    private String cmanagerContactPhone;
    //集团客户联系人
    private String customContact;
    //集团客户联系电话
    private String customContactPhone;
    //产品名称
    private String productName;
    //集团级别
    private String customLevel;
    //所属省份
    private String provinceName;
    //所属地市
    private String cityName;
    //所属区县
    private String countyName;

    //集客部联系人联系电话
    private String bdeptContactPhone;
    //电路类别
    private String isNonLocal;
    //产品类别
    private String productKind;
    //产品编号
    private String productID;
    //产品序列号
    private String productSN;
    //集团业务联系人
    private String groupDealContact;
    //集团业务联系人电话
    private String groupDealContactPhone;
    //集团技术联系人
    private String groupTechContact;
    //客户经理
    private String cmanagerPhone;
    //集团技术联系人电话
    private String groupTechContactPhone;

    private String crmSpecialLineNo;
    //业务需求描述
    private String brequirementDesc;
    //传输承载的业务
    private String transfBusiness;
    //资源id用来保存接口传过来的资源的id
    private String resourceId;
    //删除标识
    private Integer deleted;

    private String specialtyType;    //预留

    //add by liufuyuan 陕西
//	申请信息
    private String needDesc;                // 需求描述
//	客户及客户经理信息 
//	客户经理工号

    private String bdeptContact;
    private String costomBelongSpecify;     // 客户所属行业			使用groupCustomBusinesType，预留
    private String customAdd;               //客户地址
    private String clientConnPerson;        //客户联系人
    private String clientConnPersonPhone;   //客户联系电话
    private String customConnMail;          //客户联系邮箱
    private String customManagerDept;       //客户经理部门
    private String customManagerMail;       //客户经理联系邮箱
    private String projectManagerName;      //项目经理姓名
    private String projectManagerPhone;      //项目经理电话
    //	ng
    private String groupCustomBusinessScope;     // 集团客户业务范围
    //	本地网
    private String businesBelongCity;          //业务所属地市
    //	归档意见
    private String isOutPutPreResouce;        //是否释放预占资源				//无用，作为预留字段
    //	集团客户相关信息
    private String groupCustomBusinessType;     //集团客户业务分类
    private String groupCustomNo;              //集团客户编号
    private String groupCustomAdd;             //集团客户联系地址
    private String groupCustomMail;             //集团客户联系邮编
    private String groupCustomBusinesType;             //集团客户行业
    private String customConnMain;             //客户联系邮箱
    //	数据专线
    private String projectName;     //项目名称
    //	点对点
    private String stationNumber;            //站点数量
    private String eleNumber;            //电路数量

    private String remark;                    //备注
    private String netResCapacity;        //回复 网络资源能力确认
    private String clientPgmCapacity;        //回复 客户端工程能力确认
    private String expectFinishdays;        //回复 预计完成天数
    private String circuitCode;        //回复 传输专线电路代号

    //	GPRS专线
    private String businessInfoGPRS;     //业务信息
    //   审核人姓名
    private String checkPerson;
    //  审核人部门
    private String checkDepmt;
    //  审核人电话
    private String checkPhone;
    //  审核人邮箱
    private String checkMail;
    //  审核人意见
    private String checkComments;
    //  审核时间
    private Date checkTime;
    //  拟生效时间
    private Date planExecuteTime;

    private String accessories;    //附件


    public String getBdeptContact() {
        return bdeptContact;
    }

    public void setBdeptContact(String bdeptContact) {
        this.bdeptContact = bdeptContact;
    }

    public String getBdeptContactPhone() {
        return bdeptContactPhone;
    }

    public void setBdeptContactPhone(String bdeptContactPhone) {
        this.bdeptContactPhone = bdeptContactPhone;
    }

    public String getBrequirementDesc() {
        return brequirementDesc;
    }

    public void setBrequirementDesc(String brequirementDesc) {
        this.brequirementDesc = brequirementDesc;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCmanagerContactPhone() {
        return cmanagerContactPhone;
    }

    public void setCmanagerContactPhone(String cmanagerContactPhone) {
        this.cmanagerContactPhone = cmanagerContactPhone;
    }

    public String getCmanagerPhone() {
        return cmanagerPhone;
    }

    public void setCmanagerPhone(String cmanagerPhone) {
        this.cmanagerPhone = cmanagerPhone;
    }

    public Date getCompleteLimit() {
        return completeLimit;
    }

    public void setCompleteLimit(Date completeLimit) {
        this.completeLimit = completeLimit;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getCrmSpecialLineNo() {
        return crmSpecialLineNo;
    }

    public void setCrmSpecialLineNo(String crmSpecialLineNo) {
        this.crmSpecialLineNo = crmSpecialLineNo;
    }

    public String getCustomContact() {
        return customContact;
    }

    public void setCustomContact(String customContact) {
        this.customContact = customContact;
    }

    public String getCustomContactPhone() {
        return customContactPhone;
    }

    public void setCustomContactPhone(String customContactPhone) {
        this.customContactPhone = customContactPhone;
    }

    public String getCustomLevel() {
        return customLevel;
    }

    public void setCustomLevel(String customLevel) {
        this.customLevel = customLevel;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomNo() {
        return customNo;
    }

    public void setCustomNo(String customNo) {
        this.customNo = customNo;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getGroupDealContact() {
        return groupDealContact;
    }

    public void setGroupDealContact(String groupDealContact) {
        this.groupDealContact = groupDealContact;
    }

    public String getGroupDealContactPhone() {
        return groupDealContactPhone;
    }

    public void setGroupDealContactPhone(String groupDealContactPhone) {
        this.groupDealContactPhone = groupDealContactPhone;
    }

    public String getGroupTechContact() {
        return groupTechContact;
    }

    public void setGroupTechContact(String groupTechContact) {
        this.groupTechContact = groupTechContact;
    }

    public String getGroupTechContactPhone() {
        return groupTechContactPhone;
    }

    public void setGroupTechContactPhone(String groupTechContactPhone) {
        this.groupTechContactPhone = groupTechContactPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getIsNonLocal() {
        return isNonLocal;
    }

    public void setIsNonLocal(String isNonLocal) {
        this.isNonLocal = isNonLocal;
    }

    public String getMainProductInstanceCode() {
        return mainProductInstanceCode;
    }

    public void setMainProductInstanceCode(String mainProductInstanceCode) {
        this.mainProductInstanceCode = mainProductInstanceCode;
    }

    public String getOrderBuisnessMark() {
        return orderBuisnessMark;
    }

    public void setOrderBuisnessMark(String orderBuisnessMark) {
        this.orderBuisnessMark = orderBuisnessMark;
    }

    public String getOrderBuisnessType() {
        return orderBuisnessType;
    }

    public void setOrderBuisnessType(String orderBuisnessType) {
        this.orderBuisnessType = orderBuisnessType;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getP_order_id() {
        return p_order_id;
    }

    public void setP_order_id(String p_order_id) {
        this.p_order_id = p_order_id;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductKind() {
        return productKind;
    }

    public void setProductKind(String productKind) {
        this.productKind = productKind;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSN() {
        return productSN;
    }

    public void setProductSN(String productSN) {
        this.productSN = productSN;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getPruductId() {
        return pruductId;
    }

    public void setPruductId(String pruductId) {
        this.pruductId = pruductId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTransfBusiness() {
        return transfBusiness;
    }

    public void setTransfBusiness(String transfBusiness) {
        this.transfBusiness = transfBusiness;
    }

    public String getUrgentDegree() {
        return urgentDegree;
    }

    public void setUrgentDegree(String urgentDegree) {
        this.urgentDegree = urgentDegree;
    }

    public String getSpecialtyType() {
        return specialtyType;
    }

    public void setSpecialtyType(String specialtyType) {
        this.specialtyType = specialtyType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getBusinesBelongCity() {
        return businesBelongCity;
    }

    public void setBusinesBelongCity(String businesBelongCity) {
        this.businesBelongCity = businesBelongCity;
    }

    public String getBusinessInfoGPRS() {
        return businessInfoGPRS;
    }

    public void setBusinessInfoGPRS(String businessInfoGPRS) {
        this.businessInfoGPRS = businessInfoGPRS;
    }

    public String getCircuitCode() {
        return circuitCode;
    }

    public void setCircuitCode(String circuitCode) {
        this.circuitCode = circuitCode;
    }

    public String getClientConnPerson() {
        return clientConnPerson;
    }

    public void setClientConnPerson(String clientConnPerson) {
        this.clientConnPerson = clientConnPerson;
    }

    public String getClientConnPersonPhone() {
        return clientConnPersonPhone;
    }

    public void setClientConnPersonPhone(String clientConnPersonPhone) {
        this.clientConnPersonPhone = clientConnPersonPhone;
    }

    public String getClientPgmCapacity() {
        return clientPgmCapacity;
    }

    public void setClientPgmCapacity(String clientPgmCapacity) {
        this.clientPgmCapacity = clientPgmCapacity;
    }

    public String getCostomBelongSpecify() {
        return costomBelongSpecify;
    }

    public void setCostomBelongSpecify(String costomBelongSpecify) {
        this.costomBelongSpecify = costomBelongSpecify;
    }

    public String getCustomAdd() {
        return customAdd;
    }

    public void setCustomAdd(String customAdd) {
        this.customAdd = customAdd;
    }

    public String getCustomConnMail() {
        return customConnMail;
    }

    public void setCustomConnMail(String customConnMail) {
        this.customConnMail = customConnMail;
    }

    public String getCustomConnMain() {
        return customConnMain;
    }

    public void setCustomConnMain(String customConnMain) {
        this.customConnMain = customConnMain;
    }

    public String getCustomManagerDept() {
        return customManagerDept;
    }

    public void setCustomManagerDept(String customManagerDept) {
        this.customManagerDept = customManagerDept;
    }

    public String getCustomManagerMail() {
        return customManagerMail;
    }

    public void setCustomManagerMail(String customManagerMail) {
        this.customManagerMail = customManagerMail;
    }

    public String getExpectFinishdays() {
        return expectFinishdays;
    }

    public void setExpectFinishdays(String expectFinishdays) {
        this.expectFinishdays = expectFinishdays;
    }

    public String getGroupCustomAdd() {
        return groupCustomAdd;
    }

    public void setGroupCustomAdd(String groupCustomAdd) {
        this.groupCustomAdd = groupCustomAdd;
    }

    public String getGroupCustomBusinessScope() {
        return groupCustomBusinessScope;
    }

    public void setGroupCustomBusinessScope(String groupCustomBusinessScope) {
        this.groupCustomBusinessScope = groupCustomBusinessScope;
    }

    public String getGroupCustomBusinessType() {
        return groupCustomBusinessType;
    }

    public void setGroupCustomBusinessType(String groupCustomBusinessType) {
        this.groupCustomBusinessType = groupCustomBusinessType;
    }

    public String getGroupCustomBusinesType() {
        return groupCustomBusinesType;
    }

    public void setGroupCustomBusinesType(String groupCustomBusinesType) {
        this.groupCustomBusinesType = groupCustomBusinesType;
    }

    public String getGroupCustomMail() {
        return groupCustomMail;
    }

    public void setGroupCustomMail(String groupCustomMail) {
        this.groupCustomMail = groupCustomMail;
    }

    public String getGroupCustomNo() {
        return groupCustomNo;
    }

    public void setGroupCustomNo(String groupCustomNo) {
        this.groupCustomNo = groupCustomNo;
    }

    public String getIsOutPutPreResouce() {
        return isOutPutPreResouce;
    }

    public void setIsOutPutPreResouce(String isOutPutPreResouce) {
        this.isOutPutPreResouce = isOutPutPreResouce;
    }

    public String getNeedDesc() {
        return needDesc;
    }

    public void setNeedDesc(String needDesc) {
        this.needDesc = needDesc;
    }

    public String getNetResCapacity() {
        return netResCapacity;
    }

    public void setNetResCapacity(String netResCapacity) {
        this.netResCapacity = netResCapacity;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getProjectManagerPhone() {
        return projectManagerPhone;
    }

    public void setProjectManagerPhone(String projectManagerPhone) {
        this.projectManagerPhone = projectManagerPhone;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getEleNumber() {
        return eleNumber;
    }

    public void setEleNumber(String eleNumber) {
        this.eleNumber = eleNumber;
    }

    public String getCheckComments() {
        return checkComments;
    }

    public void setCheckComments(String checkComments) {
        this.checkComments = checkComments;
    }

    public String getCheckDepmt() {
        return checkDepmt;
    }

    public void setCheckDepmt(String checkDepmt) {
        this.checkDepmt = checkDepmt;
    }

    public String getCheckMail() {
        return checkMail;
    }

    public void setCheckMail(String checkMail) {
        this.checkMail = checkMail;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public String getCheckPhone() {
        return checkPhone;
    }

    public void setCheckPhone(String checkPhone) {
        this.checkPhone = checkPhone;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getPlanExecuteTime() {
        return planExecuteTime;
    }

    public void setPlanExecuteTime(Date planExecuteTime) {
        this.planExecuteTime = planExecuteTime;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }
}
