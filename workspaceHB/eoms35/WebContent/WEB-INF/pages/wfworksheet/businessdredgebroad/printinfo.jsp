<%@ include file="/common/taglibs.jsp"%>

<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.businessdredgebroad.model.BusinessDredgebroadMain" %>
<%@page import="com.boco.eoms.sheet.businessdredgebroad.task.impl.BusinessDredgebroadTaskImpl" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.service.ILinkService" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getAttribute("taskStatus"));

if(taskStatus.equals("")){
taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
 System.out.println("taskStatus taskStatus taskStatus="+taskStatus);
}
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
BusinessDredgebroadTaskImpl task = new BusinessDredgebroadTaskImpl();

String operaterType = "";
if(request.getAttribute("task")!=null){
  task = (BusinessDredgebroadTaskImpl)request.getAttribute("task"); 
  if(task.getTaskStatus().equals("2") || taskStatus.equals("8")){
  	String tat = task.getTaskStatus();
   }else{
		 taskStatus = task.getTaskStatus();
  }
 operaterType = task.getOperateType();
}

String myPreLinkId = (String)request.getAttribute("preLinkId");
ILinkService  linkManager =(ILinkService )ApplicationContextHolder.getInstance().getBean("iBusinessDredgebroadLinkManager");
BaseLink  myLink = null;
if(myPreLinkId != null && !"".equals(myPreLinkId)){
 myLink = linkManager.getSingleLinkPO(myPreLinkId);
}
request.setAttribute("operaterType",operaterType);
BusinessDredgebroadMain basemain = (BusinessDredgebroadMain)request.getAttribute("sheetMain");
String businesstype1=basemain.getBusinesstype1();
String sendUserId = basemain.getSendUserId();
request.setAttribute("taskStatus", taskStatus);
String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
String ifInvokeUrgentFault = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvokeUrgentFault"));
System.out.println("taskname is ================"+taskName+"======operaterType====="+operaterType);
//add by yangna
String zxType=com.boco.eoms.base.util.StaticMethod.nullObject2String(basemain.getZxType());
System.out.println("zxType===="+zxType);
//end by yangna
%>

<style>
		/*处理下方用户签名*/
		div.qb_cls_sign
		{
			margin-top:22px;
		}
		td
		{
			border:solid 1px #000000 ;
		}
</style>

<center><caption>${eoms:a2u('施工单')}</caption></center>
  
<table class="formTable" style="border: solid 1px #000000;" cellspacing="0" cellpadding="0"  rules=cols rules="rows" rules="all">

	<tr>
		<td class="label"><bean:message bundle="sheet" key="mainForm.title"/></td>
		<td colspan="3"><bean:write name="sheetMain" property="title" scope="request"/></td>
		<td class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.mainRemark"/></td>
		<td><eoms:id2nameDB id="${sheetMain.urgentDegree}" beanId="ItawSystemDictTypeDao"/></td>
	</tr>

	<tr>
		<td class="label"><bean:message bundle="sheet" key="mainForm.sheetId"/></td>
		<td><bean:write name="sheetMain" property="sheetId" scope="request"/></td>
		<td class="label"><bean:message bundle="sheet" key="mainForm.sendTime"/></td>
		<td><bean:write name="sheetMain" property="sendTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/></td>
		<td class="label">${eoms:a2u('CRM侧工单号')}</td>
		<td><bean:write name="sheetMain" property="parentCorrelation" scope="request"/></td>
	</tr>

	<tr>
		<td class="label"><bean:message bundle="sheet" key="mainForm.sendUserName"/></td>
		<td >
			<eoms:id2nameDB id="${sheetMain.sendUserId}" beanId="tawSystemUserDao"/>		  			  
		</td>
		<td class="label">
			<bean:message bundle="sheet" key="mainForm.sendContact"/>
		</td>
		<td>
			<bean:write name="sheetMain" property="sendContact" scope="request"/>
		</td>
		<td class="label"><bean:message bundle="sheet" key="mainForm.sendDeptName"/></td>			
		<td ><eoms:id2nameDB id="${sheetMain.sendDeptId}" beanId="tawSystemDeptDao"/></td>
	</tr>
	
	<tr>
		<td class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.businesstype1"/> </td>
		<td ><eoms:id2nameDB id="${sheetMain.businesstype1}" beanId="ItawSystemDictTypeDao"/></td>
		<td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.provinceName"/></td>
		<td  ><bean:write name="sheetMain" property="provinceName" scope="request"/></td>
		<td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.cityName"/></td>
		<td  ><bean:write name="sheetMain" property="cityName" scope="request"/></td>
	</tr>
	<tr>
		<td  class="label">${eoms:a2u('业务序号')}</td>
		<td><bean:write  name="sheetMain"  property="radiusValidateIPAdd"  scope="request"/></td>
		<td  class="label">${eoms:a2u('所属地区')}</td>
		<td><bean:write  name="sheetMain"  property="simHLR"  scope="request"/></td>
		<td  class="label">${eoms:a2u('所属区县')}</td>
		<td><bean:write  name="sheetMain"  property="isRadiusValidate"  scope="request"/></td>
	</tr>

	<tr>
		<td  class="label">${eoms:a2u('受理来源')}</td>
		<td ><eoms:id2nameDB id="${sheetMain.terminalNum}" beanId="ItawSystemDictTypeDao"/></td>
		<td  class="label">${eoms:a2u('客户联系人')}</td>
		<td><bean:write  name="sheetMain"  property="factureArea"  scope="request"/></td>
		<td  class="label">${eoms:a2u('客户联系人电话')}</td>
		<td><bean:write  name="sheetMain"  property="appServerIPAdd"  scope="request"/></td>
	 </tr>

	<tr>
		<td  class="label">${eoms:a2u('营业分局')}</td>
		<td><bean:write  name="sheetMain"  property="apnName"  scope="request"/></td>
		<td  class="label">${eoms:a2u('营业员名称/工号')}</td>
		<td><bean:write  name="sheetMain"  property="ipAddressAssign"  scope="request"/></td>
		<td  class="label">${eoms:a2u('用户属性')}</td>
		<td ><eoms:id2nameDB id="${sheetMain.apnRouterMode}" beanId="ItawSystemDictTypeDao"/></td>
	</tr>   

	<tr>
		 <td  class="label">${eoms:a2u('联系人姓名')}</td>
		<td><bean:write  name="sheetMain"  property="apnIPPool"  scope="request"/></td>
		<td  class="label">${eoms:a2u('联系人性别')}</td>
		<td><bean:write  name="sheetMain"  property="transferMode"  scope="request"/></td>
		<td  class="label">${eoms:a2u('联系方式1')}</td>
		<td><bean:write  name="sheetMain"  property="doubleGGSN"  scope="request"/></td>
	</tr>  
	<tr>
		<td  class="label">${eoms:a2u('联系方式2')}</td>
		<td><bean:write  name="sheetMain"  property="secondIPPool"  scope="request"/></td>
		<td  class="label">${eoms:a2u('要求联系时间')}</td>
		<td><bean:write  name="sheetMain"  property="factureTime"  scope="request"/></td>
		<td  class="label">${eoms:a2u('预约装机时间')}</td>
		<td><bean:write  name="sheetMain"  property="siEngineerPhone"  scope="request"/></td>
	</tr> 
	<tr>
		<td  class="label">${eoms:a2u('第二联系人姓名')}</td>
		<td><bean:write  name="sheetMain"  property="numDetail"  scope="request"/></td>
		<td  class="label">${eoms:a2u('第二联系人联系方式')}</td>
		<td><bean:write  name="sheetMain"  property="bcode"  scope="request"/></td>
		<td  class="label">${eoms:a2u('订购套餐类型')}</td>
		<td ><eoms:id2nameDB id="${sheetMain.isConnectMISC}" beanId="ItawSystemDictTypeDao"/></td>
	</tr>
	<tr>
		<td  class="label">${eoms:a2u('接入类型')}</td>
		<td ><eoms:id2nameDB id="${sheetMain.siconnectMMSGatewayName}" beanId="ItawSystemDictTypeDao"/></td>
		<td  class="label">${eoms:a2u('带宽')}</td>
		<td ><eoms:id2nameDB id="${sheetMain.siEnterpriseCode}" beanId="ItawSystemDictTypeDao"/></td>
		<td  class="label">${eoms:a2u('IP地址分配方式')}</td>
		<td ><eoms:id2nameDB id="${sheetMain.siIPAdd}" beanId="ItawSystemDictTypeDao"/></td>
	</tr> 
	<tr>
		<td  class="label">${eoms:a2u('最大下发流量')}</td>
		<td><bean:write  name="sheetMain"  property="siServerCode"  scope="request"/></td>
		<td  class="label">${eoms:a2u('最大上行流量')}</td>
		<td><bean:write  name="sheetMain"  property="siConnectMMSGatewayID"  scope="request"/></td>
		<td  class="label">${eoms:a2u('所属小区名称')}</td>
		<td><bean:write  name="sheetMain"  property="siUplinkUrl"  scope="request"/></td>
	</tr>  
	<tr>
		<td class="label">${eoms:a2u('新安装地址编码')}</td>
		<td><bean:write  name="sheetMain"  property="connectGatewayBandwidth"  scope="request"/></td>
		<td class="label">${eoms:a2u('新安装详细地址')}</td>
		<td colspan="3"><bean:write  name="sheetMain"  property="comProtocol"  scope="request"/></td>
	</tr>    
	<tr>
		<td class="label">${eoms:a2u('所属小区2（旧）')}</td>
		<td><bean:write  name="sheetMain"  property="maxConnections"  scope="request"/></td>
		<td class="label">${eoms:a2u('原安装安装地址')}</td>
		<td colspan="3"><bean:write  name="sheetMain"  property="maxUnderFlow"  scope="request"/></td>
	</tr> 
	<tr>
		<td  class="label">${eoms:a2u('原安装安装编码')}</td>
		<td><bean:write  name="sheetMain"  property="maxUplinkFlow"  scope="request"/></td>
		<td  class="label">${eoms:a2u('用户账号')}</td>
		<td><bean:write  name="sheetMain"  property="flowControlPriority"  scope="request"/></td>
		<td  class="label">${eoms:a2u('账号密码')}</td>
		<td><bean:write  name="sheetMain"  property="portRateIsDown"  scope="request"/></td>
	</tr>
	<tr>
		<td  class="label">${eoms:a2u('设备编码')}</td>
		<td><bean:write  name="sheetMain"  property="smsSigned"  scope="request"/></td>
		<td  class="label">${eoms:a2u('设备名称')}</td>
		<td><bean:write  name="sheetMain"  property="hostIPAdd"  scope="request"/></td>
		<td  class="label">${eoms:a2u('设备端口')}</td>
		<td><bean:write  name="sheetMain"  property="connectGatewayName"  scope="request"/></td>
	</tr>
	<tr>
		<td  class="label">${eoms:a2u('设备地址')}</td>
		<td><bean:write  name="sheetMain"  property="connectGatewayID"  scope="request"/></td>
		<td  class="label">${eoms:a2u('发票信息')}</td>
		<td><bean:write  name="sheetMain"  property="siEngineerName"  scope="request"/></td>
		<td  class="label">${eoms:a2u('赠品信息')}</td>
		<td><bean:write  name="sheetMain"  property="cityA"  scope="request"/></td>
	</tr>
	<tr>
		<td  class="label">${eoms:a2u('终端信息')}</td>
		<td style="border:solid 1px #000000 ;"><bean:write  name="sheetMain"  property="cityZ"  scope="request"/></td>
		<td  class="label">${eoms:a2u('用户类型')}</td>
		<td><bean:write  name="sheetMain"  property="bandwidth"  scope="request"/></td>
		<td  class="label">${eoms:a2u('用户要求')}</td>
		<td><bean:write  name="sheetMain"  property="amount"  scope="request"/></td>
	</tr>
	<tr>
		<td class="label">${eoms:a2u('施工内容')}</td>
		<td colspan='5'><bean:write name="sheetMain" property="volumeAssessment" scope="request"/></td>
	</tr>
	<tr>
		<td class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.bRequirementDesc"/></td>
		<td colspan="5"><pre><bean:write name="sheetMain" property="brequirementDesc" scope="request"/></pre></td>
	</tr>
	<tr>
		<td colspan="2" rowspan="5">
			<div>${eoms:a2u('用户签名')}</div>
			<div align="right" class="qb_cls_sign">${eoms:a2u('年')}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${eoms:a2u('月')}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${eoms:a2u('日')}</div>
		</td>
		<td colspan="4">${eoms:a2u('施工说明：立杆')}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${eoms:a2u('，皮线')}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${eoms:a2u('米，电缆')}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${eoms:a2u('米')}</td>
	</tr>
	<tr>
		<td colspan="4" rowspan="4">${eoms:a2u('备注：')}</td>
	</tr>

</table>


