<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.businessimplement.model.BusinessImplementMain" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    BusinessImplementMain basemain = (BusinessImplementMain) request.getAttribute("sheetMain");
    System.out.println("@@@@v=" + basemain.getMainCircuitSheetId() + "id=" + basemain.getId());
//根据类型判断
    String mainSpecifyType = basemain.getMainSpecifyType();
    System.out.println("@askName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("@operateType----------" + operateType);
    System.out.println("@mainSpecifyType----------" + mainSpecifyType);
    String operateUserId = "";
    BaseLink bl = (BaseLink) request.getAttribute("preLink");
    IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean("BusinessImplement");
    if (bl != null) {
        String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
        if (!prelinkid.equals("")) {
            BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
            operateUserId = base.getOperateUserId();
        }
    }
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
    String userName = sessionform.getUserid();
    String pwd = sessionform.getPassword();
%>
<script type="text/javascript">

    function show(ifm) {
        obj = document.all[ifm];
        display = obj.style.display;
        obj.style.display = "block";
    }

    function hide(ifm) {
        obj = document.all[ifm];
        display = obj.style.display;
        obj.style.display = "none";
    }


    changeOperate = function (input) {

        if (input == 101230501) {
            eoms.form.enableArea('MSTP');
            eoms.form.disableArea('PON', true);

        } else if (input == 101230502) {
            eoms.form.enableArea('PON');
            eoms.form.disableArea('MSTP', true);
        }


    }

    var frm = document.forms[0];
    var temp = frm.linkDesinType ? frm.linkDesinType.value : '';
    if (temp != '') {
        changeOperate(temp);
    }
    //不成功时填写
    changeOperate2 = function (input) {

        if (input == 101230302) {
            eoms.form.enableArea('NoSuccess');


        } else {

            eoms.form.disableArea('NoSuccess', true);
        }


    }


    var temp2 = frm.linkDesinResourt ? frm.linkDesinResourt.value : '';
    if (temp2 != '') {
        changeOperate2(temp2);
    }

    //处理时限不能超过工单完成时限
    var dtnow = new Date();
    var str = '${sheetMain.sheetCompleteLimit}';
    str = str.replace(/-/g, "/");
    str = str.substring(0, str.length - 2);
    var dt2 = new Date(str);
    if (dt2 > dtnow) {
        document.getElementById("tmpCompleteLimit").value = '${sheetMain.sheetCompleteLimit}';
    } else {
        document.getElementById("tmpCompleteLimit").value = '';
    }

    function popupCircuidPage() {
        var userName = "<%=userName%>";
        var urls = "http://10.26.106.210:9900/webattemp/raptor/subsystem/attempX/irms/index.jsp?sheetId=${sheetMain.mainCircuitSheetId}&userName=" + userName;
        //alert(urls);
        window.open(urls);
    }

    function popupProjectPage() {
        var userName = "<%=userName%>";
        var urls = "http://10.26.106.210:9900/webattemp/raptor/subsystem/attempX/irms/index.jsp?sheetId=${sheetMain.mainCircuitSheetId}&userName=" + userName;

        window.open(urls);
    }
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/businessimplement/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""
           alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
    <input type="hidden" name="${sheetPageName}beanId" value="iBusinessImplementMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.businessimplement.model.BusinessImplementMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.businessimplement.model.BusinessImplementLink"/>
    <input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
    <c:if test="${taskName != 'HoldTask' }">
        <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
    </c:if>

    <c:choose>
        <c:when test="${task.subTaskFlag == 'true'}">
            <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
                   value="true"/>
        </c:when>
    </c:choose>

    <tr>
        <td class="label">
            设计方案
        </td>
        <td colspan=3>
            <eoms:attachment name="orderSheet" property="accessories"
                             scope="request" idField="accessories" appCode="resourceconfirm"
                             viewFlag="Y"/>
        </td>
    </tr>
    <tr>
            <%if(operateType.equals("4")){ %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
        <c:choose>
        <c:when test="${taskName=='ImplementDealTask'}">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
        <c:when test="${taskName=='ProjectDealTask'}">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ImplementDealTask"/>
        </c:when>
        <c:when test="${taskName=='CityNetTask'}">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ProjectDealTask"/>
        </c:when>
        <c:when test="${taskName=='ApnTask'}">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ProjectDealTask"/>
        </c:when>
        <c:when test="${taskName=='TrasitionTask'}">
                <%if(mainSpecifyType.equals("101230101") || mainSpecifyType.equals("101230102")){ %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="CityNetTask"/>
                <%} %>
                <%if(mainSpecifyType.equals("101230103")){ %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ProjectDealTask"/>
                <%} %>
        </c:when>
        <c:when test="${taskName=='BusinessTestTask'}">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ApnTask"/>
        </c:when>
        <c:when test="${taskName=='BusinessTestTask'}">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TrasitionTask"/>
        </c:when>
        </c:choose>
    <tr>
        <td class="label">
            备注说明*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:500,vtext:'请最多输入1000字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%} %>

    <%if (taskName.equals("ImplementDealTask") && (operateType.equals("91") || operateType.equals("11"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ProjectDealTask"/>

    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkArugmentlevel"/>*
        </td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkArugmentlevel" id="${sheetPageName}linkArugmentlevel"
                           initDicId="1010102" styleClass="select-class" alt="allowBlank:false"
                           defaultValue="${sheetLink.linkArugmentlevel}"/>
        </td>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkNeedFinishTime"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}linkNeedFinishTime" readonly="readonly"
                   value="${eoms:date2String(sheetLink.linkNeedFinishTime)}" id="${sheetPageName}linkNeedFinishTime"/>
        </td>
    </tr>

    <tr>
        <td class="label"> 处理意见</td>
        <td class="content" colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkSenderOpinition"
                            id="${sheetPageName}linkSenderOpinition"
                            alt="allowBlank:true,maxLength:200,vtext:'处理意见，最多输入100汉字'">${sheetLink.linkSenderOpinition}</textarea>
        </td>
    </tr>

    <%} else if (taskName.equals("ProjectDealTask") && (operateType.equals("93") || operateType.equals("11"))) { %>

    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <%if (mainSpecifyType.equals("101230102") || mainSpecifyType.equals("101230103")) {%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value=""/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FDDITask"/>
    <%if (mainSpecifyType.equals("101230102") || mainSpecifyType.equals("101230103")) { %>
    <tr>
        <td class="label">
            施工资料导入
        </td>
        <td colspan="3">
            <a onclick="popupCircuidPage()" style="cursor:pointer;">施工资料</a>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkDoResourt"/>*
        </td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkDoResourt" id="${sheetPageName}linkDoResourt" initDicId="1012303"
                           styleClass="select-class" alt="allowBlank:false"
                           defaultValue="${sheetLink.linkArugmentlevel}"/>

        </td>
    </tr>

    <tr>
        <td class="label">
            建设条件是否具备*
        </td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkDevType" id="${sheetPageName}linkDevType" initDicId="10301"
                           styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkDevType}"
                           onchange="HaveCondition101230102(this.value);"/>
        </td>
    </tr>
    <%} %>


    <%} else if (mainSpecifyType.equals("101230101")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
    <input type="hidden" name="${sheetPageName}extendKey2" id="${sheetPageName}extendKey2" value=""/>
    <input type="hidden" name="${sheetPageName}toMorePhaseId" id="${sheetPageName}toMorePhaseId" value=""/>
    <tr>
        <td class="label">
            施工资料导入
        </td>
        <td colspan="3">
            <a onclick="popupCircuidPage()" style="cursor:pointer;">施工资料</a>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkDoResourt"/>*
        </td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkDoResourt" id="${sheetPageName}linkDoResourt" initDicId="1012303"
                           styleClass="select-class" alt="allowBlank:false"
                           defaultValue="${sheetLink.linkArugmentlevel}"/>

        </td>
    </tr>


    <td class="label">
        建设条件是否具备*
    </td>
    <td colspan="3">
        <eoms:comboBox name="${sheetPageName}linkDevType" id="${sheetPageName}linkDevType" initDicId="10301"
                       styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkDevType}"
                       onchange="HaveCondition(this.value);"/>
    </td>
    </tr>


    <%} %>


    <tr>
        <td class="label">处理意见</td>
        <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkSenderOpinition"
                            id="${sheetPageName}linkSenderOpinition"
                            alt="allowBlank:true,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkSenderOpinition}</textarea>
        </td>
    </tr>


    <%} else if (taskName.equals("FDDITask")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <%if (operateType.equals("930") || operateType.equals("11")) { %>

    <input type="hidden" name="${sheetPageName}extendKey2" id="${sheetPageName}extendKey2" value="BusinessTestTask"/>
    <input type="hidden" name="${sheetPageName}toMorePhaseId" id="${sheetPageName}toMorePhaseId" value=""/>

    <%if (mainSpecifyType.equals("101230102")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="CityNetTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="960"/>


    <tr>
        <td class="label">处理意见</td>
        <td colspan="3">
									      <textarea class="textarea max" name="${sheetPageName}linkSenderOpinition"
                                                    id="${sheetPageName}linkSenderOpinition"
                                                    alt="allowBlank:true,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkSenderOpinition}</textarea>
        </td>
    </tr>
    <%} %>
    <%if (mainSpecifyType.equals("101230103")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TrasitionTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="961"/>
    <tr>
        <td class="label">处理意见</td>
        <td colspan="3">
									      <textarea class="textarea max" name="${sheetPageName}linkSenderOpinition"
                                                    id="${sheetPageName}linkSenderOpinition"
                                                    alt="allowBlank:true,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkSenderOpinition}</textarea>
        </td>
    </tr>
    <%} %>
    <%} %>
    <%} else if (taskName.equals("CityNetTask") && (operateType.equals("92") || operateType.equals("11"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TrasitionTask"/>
    <!--
			 <tr>
				<td class="label">
				     <bean:message bundle="businessimplement" key="businessimplement.linkDesinResourt"/>*      
		           </td>
		           <td colspan="3"> 
		     	     <eoms:comboBox   name="${sheetPageName}linkDesinResourt" id="${sheetPageName}linkDesinResourt"  initDicId="1012303" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkDesinResourt}" />
		           </td>
	       </tr> 
	       -->
    <tr>
        <td class="label"> 处理结果</td>
        <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkFailureResson"
                            id="${sheetPageName}linkFailureResson"
                            alt="allowBlank:true,maxLength:200,vtext:'处理结果，最多输入100汉字'">${sheetLink.linkFailureResson}</textarea>
        </td>
    </tr>
    <!-- 七个环节开始反馈 -->
    <%} else if (taskName.equals("CityNetTask") && (operateType.equals("741") || operateType.equals("11"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusinessTestTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkDesinResourt"/>*
        </td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkDesinResourt" id="${sheetPageName}linkDesinResourt"
                           initDicId="1012303" styleClass="select-class" alt="allowBlank:false"
                           defaultValue="${sheetLink.linkDesinResourt}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessimplement" key="businessimplement.linkFailureResson"/> *</td>
        <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkFailureResson"
                            id="${sheetPageName}linkFailureResson"
                            alt="allowBlank:false,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkFailureResson}</textarea>
        </td>
    </tr>
    <%} else if (taskName.equals("HLRTask") && (operateType.equals("748") || operateType.equals("11"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusinessTestTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkDesinResourt"/>*
        </td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkDesinResourt" id="${sheetPageName}linkDesinResourt"
                           initDicId="1012303" styleClass="select-class" alt="allowBlank:false"
                           defaultValue="${sheetLink.linkDesinResourt}"/>
        </td>
    </tr>
    <!-- 	       	 <tr>
			  	<td class="label"> <bean:message bundle="businessimplement" key="businessimplement.linkFailureResson"/> * </td>
			    <td  colspan="3">
			      <textarea class="textarea max"  name="${sheetPageName}linkFailureResson" id="${sheetPageName}linkFailureResson" 
			      		alt="allowBlank:false,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkFailureResson}</textarea>
			    </td> 
			  </tr>	 -->


    <%} else if (taskName.equals("ApnTask") && (operateType.equals("742") || operateType.equals("11"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusinessTestTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkDesinResourt"/> *
        </td>
        <td colspan="3"> <!-- onchange="changeOperate2(this.value);"
		           </td> -->
            <eoms:comboBox name="${sheetPageName}linkDesinResourt" id="${sheetPageName}linkDesinResourt"
                           initDicId="1012303" styleClass="select-class" alt="allowBlank:false"
                           defaultValue="${sheetLink.linkDesinResourt}"/>
        </td>
    </tr>
    <!--  <tr>
				   <td class="label">		     
		             <bean:message bundle="businessimplement" key="businessimplement.linkGroupUserAddressZone"/>*
		           </td>
		           <td  colspan="3"> 
		             <input type="text"  class="text" name="${sheetPageName}linkGroupUserAddressZone" id="${sheetPageName}linkGroupUserAddressZone"  alt="allowBlank:false" value="${sheetLink.linkGroupUserAddressZone}"/>
		          </td>
	       </tr> 
	       	     不成功时填写
	      <tbody id='NoSuccess' style="display:none">   
	       	 <tr>
			  	<td class="label"> <bean:message bundle="businessimplement" key="businessimplement.linkFailureResson"/>*</td>
			    <td  colspan="3">
			      <textarea class="textarea max"  name="${sheetPageName}linkFailureResson" id="${sheetPageName}linkFailureResson" 
			      		alt="allowBlank:false,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkFailureResson}</textarea>
			    </td> 
			  </tr>	
		</tbody>  -->
    <%} else if (taskName.equals("TrasitionTask") && (operateType.equals("743") || operateType.equals("11"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusinessTestTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <tr>
        <td class="label">
            电路设计
        </td>
        <td colspan="3">
            <a onclick="popupCircuidPage()" style="cursor:pointer;">点击进行传输电路设计</a>
        </td>
    </tr>
    <tr>
        <td class="label">处理意见</td>
        <td class="content" colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkFailureResson"
                            id="${sheetPageName}linkFailureResson"
                            alt="allowBlank:true,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkFailureResson}</textarea>
        </td>
    </tr>
    <%} else if (taskName.equals("PONConfigTask") && (operateType.equals("744") || operateType.equals("11"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusinessTestTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkTrasitionEleNumber"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkTrasitionEleNumber"
                   id="${sheetPageName}linkTrasitionEleNumber" alt="allowBlank:false"
                   value="${sheetLink.linkTrasitionEleNumber}"/>
        </td>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkGroupUserAddressZone"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkGroupUserAddressZone"
                   id="${sheetPageName}linkGroupUserAddressZone" alt="allowBlank:false"
                   value="${sheetLink.linkGroupUserAddressZone}"/>
        </td>
    </tr>
    <%} else if (taskName.equals("GGSNTask") && (operateType.equals("745") || operateType.equals("11"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusinessTestTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkDealResourt"/>*
        </td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkDealResourt" id="${sheetPageName}linkDealResourt"
                           initDicId="1012306" styleClass="select-class" alt="allowBlank:false"
                           defaultValue="${sheetLink.linkDealResourt}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessimplement" key="businessimplement.linkDealDescriptin"/> *</td>
        <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkDealDescriptin"
                            id="${sheetPageName}linkDealDescriptin"
                            alt="allowBlank:false,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkDealDescriptin}</textarea>
        </td>
    </tr>
    <!--
	      	       <tr>
		           <td class="label">
				     <bean:message bundle="businessimplement" key="businessimplement.linkTrasitionEleNumber"/>*   
		           </td>
		           <td class="content"> 
		     	     <input type="text"  class="text" name="${sheetPageName}linkTrasitionEleNumber" id="${sheetPageName}linkTrasitionEleNumber"  alt="allowBlank:false" value="${sheetLink.linkTrasitionEleNumber}"/>
		           </td>
				   <td class="label">		     
		             <bean:message bundle="businessimplement" key="businessimplement.linkGroupUserAddressZone"/>*
		           </td>
		           <td class="content"> 
		             <input type="text"  class="text" name="${sheetPageName}linkGroupUserAddressZone" id="${sheetPageName}linkGroupUserAddressZone"  alt="allowBlank:false" value="${sheetLink.linkGroupUserAddressZone}"/>
		          </td>
	       </tr>  -->
    <%} else if (taskName.equals("HLRTask") && (operateType.equals("748") || operateType.equals("11"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusinessTestTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkDealResourt"/>*
        </td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkDealResourt" id="${sheetPageName}linkDealResourt"
                           initDicId="1012306" styleClass="select-class" alt="allowBlank:false"
                           defaultValue="${sheetLink.linkDealResourt}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessimplement" key="businessimplement.linkDealDescriptin"/> *</td>
        <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkDealDescriptin"
                            id="${sheetPageName}linkDealDescriptin"
                            alt="allowBlank:false,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkDealDescriptin}</textarea>
        </td>
    </tr>


    <%} else if (taskName.equals("ClientTask") && (operateType.equals("747") || operateType.equals("11"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusinessTestTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkTrasitionEleNumber"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkTrasitionEleNumber"
                   id="${sheetPageName}linkTrasitionEleNumber" alt="allowBlank:false"
                   value="${sheetLink.linkTrasitionEleNumber}"/>
        </td>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkGroupUserAddressZone"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkGroupUserAddressZone"
                   id="${sheetPageName}linkGroupUserAddressZone" alt="allowBlank:false"
                   value="${sheetLink.linkGroupUserAddressZone}"/>
        </td>
    </tr>
    <!-- 一派七个环节反馈结束 -->
    <%} else if (taskName.equals("BusinessTestTask") && (operateType.equals("75") || operateType.equals("11"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DredgeAffirmTask"/>
    <tr>
        <td class="label">处理意见*</td>
        <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkDealDescriptin"
                            id="${sheetPageName}linkDealDescriptin"
                            alt="allowBlank:false,maxLength:200,vtext:'处理意见，最多输入100汉字'">${sheetLink.linkDealDescriptin}</textarea>
        </td>
    </tr>

    <%} else if (taskName.equals("ApnTask") && (operateType.equals("11") || operateType.equals("95"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusinessTestTask"/>
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkDesinResourt"/> *
        </td>
        <td class="content" colspan="3">
            <eoms:comboBox name="${sheetPageName}linkDesinResourt" id="${sheetPageName}linkDesinResourt"
                           initDicId="1012303" styleClass="select-class" alt="allowBlank:false"
                           defaultValue="${sheetLink.linkDesinResourt}" onchange="changeOperate2(this.value);"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkGroupUserAddressZone"/>*
        </td>
        <td class="content" colspan="3">
            <input type="text" class="text" name="${sheetPageName}linkGroupUserAddressZone"
                   id="${sheetPageName}linkGroupUserAddressZone" alt="allowBlank:false"
                   value="${sheetLink.linkGroupUserAddressZone}"/>
        </td>
    </tr>
    <!-- 不成功时填写 -->
    <tbody id='NoSuccess' style="display:none">
    <tr>
        <td class="label"><bean:message bundle="businessimplement" key="businessimplement.linkFailureResson"/>*</td>
        <td class="content" colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkFailureResson"
                            id="${sheetPageName}linkFailureResson"
                            alt="allowBlank:false,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkFailureResson}</textarea>
        </td>
    </tr>
    </tbody>
    <%} else if (taskName.equals("ModifyDesignTask") && (operateType.equals("11") || operateType.equals("72"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditDesignTask"/>
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkGroupUserAddressZone"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkGroupUserAddressZone"
                   id="${sheetPageName}linkGroupUserAddressZone" alt="allowBlank:false"
                   value="${sheetLink.linkGroupUserAddressZone}"/>
        </td>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkGroupUserAddressZone"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkGroupUserAddressZone"
                   id="${sheetPageName}linkGroupUserAddressZone" alt="allowBlank:false"
                   value="${sheetLink.linkGroupUserAddressZone}"/>
        </td>
    </tr>
    <%} else if (taskName.equals("AuditDesignTask") && (operateType.equals("11") || operateType.equals("73"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ProjectDealTask"/>
    <tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkGroupUserAddressZone"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkGroupUserAddressZone"
                   id="${sheetPageName}linkGroupUserAddressZone" alt="allowBlank:false"
                   value="${sheetLink.linkGroupUserAddressZone}"/>
        </td>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkGroupUserAddressZone"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkGroupUserAddressZone"
                   id="${sheetPageName}linkGroupUserAddressZone" alt="allowBlank:false"
                   value="${sheetLink.linkGroupUserAddressZone}"/>
        </td>
    </tr>
    <%} else if (taskName.equals("DredgeAffirmTask") && (operateType.equals("76") || operateType.equals("11"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
    <input type="hidden" name="${sheetPageName}dealPerformer" value="${sheetMain.sendRoleId}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" value="subrole"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" value="${sheetMain.sendUserId}"/>

    <tr>

    <tr>
        <td class="label">
            处理结果*
        </td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}mainNumber" id="${sheetPageName}mainNumber" initDicId="1010401"
                           styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetMain.mainNumber}"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            网络部门联系人*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}mainInterfaceAConnPerson"
                   id="${sheetPageName}mainInterfaceAConnPerson" alt="allowBlank:false"
                   value="${sheetMain.mainInterfaceAConnPerson}"/>
        </td>
        <td class="label">
            网络部门联系人电话*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}mainAConnPersonPhone"
                   id="${sheetPageName}mainAConnPersonPhone" alt="allowBlank:false"
                   value="${sheetMain.mainAConnPersonPhone}"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            工程部门联系人*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}mainBusinessConnPerson"
                   id="${sheetPageName}mainBusinessConnPerson" alt="allowBlank:false"
                   value="${sheetMain.mainBusinessConnPerson}"/>
        </td>
        <td class="label">
            工程部门联系人电话*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}mainBusinessConnPersonPhone"
                   id="${sheetPageName}mainBusinessConnPersonPhone" alt="allowBlank:false"
                   value="${sheetMain.mainBusinessConnPersonPhone}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessimplement" key="businessimplement.linkDealDescriptin"/> *</td>
        <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkDealDescriptin"
                            id="${sheetPageName}linkDealDescriptin"
                            alt="allowBlank:false,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkDealDescriptin}</textarea>
        </td>
    </tr>

    <%} else if (taskName.equals("TrasitionTask") && (operateType.equals("11") || operateType.equals("97"))) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusinessTestTask"/>
    <%if (mainSpecifyType.equals("101230101")) { %>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <%} %>
    <tr>
        <td class="label">
            电路设计
        </td>
        <td colspan="3">
            <a onclick="popupCircuidPage()" style="cursor:pointer;">点击进行传输电路设计</a>
        </td>
    </tr>
    <tr>
        <td class="label">处理意见</td>
        <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkFailureResson"
                            id="${sheetPageName}linkFailureResson"
                            alt="allowBlank:true,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkFailureResson}</textarea>
        </td>
    </tr>

    <%} else if (taskName.equals("HoldTask")) {%>
    <%if (operateType.equals("18")) { %>

    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
    <%
        String parentProcessName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName"));
        System.out.println("@@parentProcessName11" + parentProcessName);

        if (parentProcessName.equals("iBusinessImplementYYMainManager")) {
            System.out.println("@@parentProcessName==iBusinessImplementYYManager");
    %>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="backToBusinessImplementYY"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="BusinessImplementYY"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="OpenTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%
    } else if (parentProcessName.equals("iBusinessImplementSmsMainManager")) {
        System.out.println("@@parentProcessName==iBusinessImplementSmsMainManager");
    %>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="callProcess"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="BusinessImplementSms"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="TranferTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} else {%>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <%}%>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
        <td colspan='3'>
            <eoms:comboBox name="${sheetPageName}holdStatisfied"
                           id="${sheetPageName}holdStatisfied"
                           defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}"
                           initDicId="10303" styleClass="select" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
        <td colspan="3">
			      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
                            alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetMain.endResult}</textarea>
        </td>
    </tr>
    <%}%>
    <%if (operateType.equals("17")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="17"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ImplementDealTask"/>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td class="content" colspan="3">
					        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                                      alt="allowBlank:false,width:500,vtext:'请最多输入1000字'"
                                      alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%}%>

    <%}%>
    <%if (!operateType.equals("61") && !operateType.equals("18") && !operateType.equals("4") && !taskName.equals("OneAuditTask") && !taskName.equals("TwoAuditTask") && !taskName.equals("ProvinceTask")) { %>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="businessimplement"/>
        </td>
    </tr>
    <%}%>
    <%
        if (taskName.equals("ImplementDealTask") || taskName.equals("ProjectDealTask") ||
                taskName.equals("ModifyDesignTask") || taskName.equals("AuditDesignTask") || taskName.equals("CityNetTask") ||
                taskName.equals("ApnTask") || taskName.equals("TrasitionTask") || taskName.equals("PONConfigTask") ||
                taskName.equals("GGSNTask") || taskName.equals("FDDITask") || taskName.equals("ClientTask") ||
                taskName.equals("BusinessTestTask") || taskName.equals("DredgeAffirmTask")) {
    %>
    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <%
            }
        }
    %>


    <% if (taskName.equals("cc")) {%>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
            <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                      alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'"
                      alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>
</table>
<%if (taskName.equals("ImplementDealTask") && operateType.equals("91")) { %>

<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName1">:线缆施工
			 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test1" type="role" roleId="917" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<%} %>
<%if (taskName.equals("ModifyDesignTask") && operateType.equals("72")) { %>

<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName1">:方案审核人员
			 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test1" type="role" roleId="4022" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<%} %>
<%if (taskName.equals("AuditDesignTask") && operateType.equals("73")) { %>

<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName1">:线缆施工
			 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test1" type="role" roleId="917" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<%} %>
<%if (taskName.equals("ProjectDealTask") && operateType.equals("93")) { %>
<%if (mainSpecifyType.equals("101230101")) { %>
<tbody id='HaveCondition' style="display:none">
<fieldset id="fieldset1">
    <legend id="legend1">
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        城域网配置人员
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test1" type="role" roleId="4023" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'supplier1Performer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<fieldset id="fieldset2">
    <legend id="legend2">
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        APN人员
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test2" type="role" roleId="4024" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'supplier2Performer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>


<fieldset id="fieldset5">
    <legend id="legend5">
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        GGSN人员
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test5" type="role" roleId="4025" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'supplier5Performer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<fieldset id="fieldset15">
    <legend id="legend5">
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        HLR数据制作人员
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test15" type="role" roleId="6015" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'supplier6Performer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<fieldset id="fieldset8">
    <legend id="legend8">
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        网络测试人员
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test8" type="role" roleId="4026" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'gatherDealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>

</tbody>

<tbody id='NoHaveCondition' style="display:none">
<fieldset id="fieldset9">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        方案制作人员
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test9" type="role" roleId="4021" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
</tbody>


<%} %>

<%if (mainSpecifyType.equals("101230102") || mainSpecifyType.equals("101230103")) { %>

<tbody id='NoHaveCondition' style="display:none">
<fieldset id="fieldset9">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        方案制作人员
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test9" type="role" roleId="4021" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
</tbody>

<tbody id='HaveCondition' style="display:none">
<fieldset id="fieldset11">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName1">:光纤调度人员
			 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test11" type="role" roleId="6014" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
</tbody>
<%} %>


<%} %>
<%if (taskName.equals("FDDITask") && operateType.equals("930")) { %>


<%if (mainSpecifyType.equals("101230102")) { %>
<tbody id='HaveCondition' style="display:none">
<fieldset id="fieldset1">
    <legend id="legend1">
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        城域网配置人员
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test1" type="role" roleId="4023" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<fieldset id="fieldset8">
    <legend id="legend8">
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        网络测试人员
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test8" type="role" roleId="4026" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'gatherDealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
</tbody>
<%} %>
<%if (mainSpecifyType.equals("101230103")) { %>
<tbody id='HaveCondition' style="display:none">
<fieldset id="fieldset3">
    <legend id="legend3">
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        电路调度人员
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test3" type="role" roleId="918" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<fieldset id="fieldset8">
    <legend id="legend8">
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        网络测试人员
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test8" type="role" roleId="4026" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'gatherDealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
</tbody>
<%} %>

<%} %>
<%if (taskName.equals("ApnTask") && operateType.equals("95")) { %>

<%} %>
<%if (taskName.equals("CityNetTask") && operateType.equals("92")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName1">:数据班人员
			 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test1" type="role" roleId="918" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<%} %>

<%if (taskName.equals("BusinessTestTask") && operateType.equals("75")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName1">:开通确认人员
			 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test1" type="role" roleId="4027" flowId="320" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<%} %>
<%if (taskName.equals("DredgeAffirmTask") && operateType.equals("76")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName1">:建单人
			 </span>
    </legend>
    <div class="x-form-item">
    </div>
</fieldset>
<%} %>
<!-- 反馈七个派单树结束 -->
<%if (taskName.equals("HoldTask")) { %>
<% if (operateType.equals("17")) { %>
<fieldset id="link4">
    <legend>
        <bean:message bundle="businessimplement" key="role.toOrgName"/>
        <span id="roleName">:参数验证实施组
			 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<%} %>
<%} %>

<%if (taskName.equals("ApnTask") && (operateType.equals("11") || operateType.equals("95"))) { %>
<script type="text/javascript">
    changeOperate2(document.getElementById('linkDesinResourt').value);
</script>
<%} %>
<script type="text/javascript">

    var var1 = "<%=mainSpecifyType %>";

    var frm = document.forms[0];
    var temp1 = frm.linkDevType ? frm.linkDevType.value : '';
    if (var1 == '101230101') {
        HaveCondition(temp1);
    }
    if (var1 == '101230102') {
        HaveCondition101230102(temp1);
    }

    if (var1 == '101230103') {
//HaveCondition3(temp1);
    }


    function HaveCondition(input) {
        var frm = document.forms[0];
        var temp1 = frm.linkDevType ? frm.linkDevType.value : '';
        if ('<%=taskName%>' == 'ProjectDealTask' && '<%=operateType%>' == '93') {
            if ('<%=operateType%>' != '11') {
                if (input == "1030101") {
                    //建设条件具备
                    eoms.form.enableArea('HaveCondition');
                    eoms.form.disableArea('NoHaveCondition', true);
                    $('${sheetPageName}phaseId').value = '';
                    $('${sheetPageName}operateType').value = '711';
                    $('${sheetPageName}extendKey2').value = 'BusinessTestTask';
                    $('${sheetPageName}toMorePhaseId').value = 'CityNetTask,ApnTask,GGSNTask,HLRTask';
                    chooser_test1.enable();
                    chooser_test2.enable();

                    chooser_test15.enable();
                    chooser_test5.enable();
                    chooser_test8.enable();


                    chooser_test9.disable();
                    show("fieldset1");
                    show("fieldset2");

                    show("fieldset15");
                    show("fieldset5");
                    show("fieldset8");


                    hide("fieldset9");

                } else if (input == "1030102") {
                    //建设条件不具备
                    eoms.form.enableArea('NoHaveCondition');
                    eoms.form.disableArea('HaveCondition', true);
                    $('${sheetPageName}phaseId').value = 'ModifyDesignTask';
                    $('${sheetPageName}operateType').value = '712';
                    $('${sheetPageName}extendKey2').value = '';
                    $('${sheetPageName}toMorePhaseId').value = '';
                    chooser_test1.disable();
                    chooser_test2.disable();

                    chooser_test15.disable();
                    chooser_test5.disable();
                    chooser_test8.disable();


                    chooser_test9.enable();
                    hide("fieldset1");
                    hide("fieldset2");

                    hide("fieldset15");
                    hide("fieldset5");
                    hide("fieldset8");


                    show("fieldset9");
                } else {
                    eoms.form.disableArea('NoHaveCondition', true);
                    eoms.form.disableArea('HaveCondition', true);
                    $('${sheetPageName}phaseId').value = '';
                    $('${sheetPageName}operateType').value = '';
                    $('${sheetPageName}extendKey2').value = '';
                    $('${sheetPageName}toMorePhaseId').value = '';
                    chooser_test1.disable();
                    chooser_test2.disable();
                    chooser_test15.disable();
                    chooser_test5.disable();
                    chooser_test8.disable();


                    chooser_test9.disable();
                    hide("fieldset1");
                    hide("fieldset2");
                    hide("fieldset15");
                    hide("fieldset5");
                    hide("fieldset8");


                    hide("fieldset9");
                }
            }
        }
    }


    function HaveCondition101230102(input) {
        var frm = document.forms[0];
        var temp1 = frm.linkDevType ? frm.linkDevType.value : '';
        if ('<%=taskName%>' == 'ProjectDealTask' && '<%=operateType%>' == '93') {

            if ('<%=operateType%>' != '11') {

                if (input == "1030101") {
                    //建设条件具备
                    eoms.form.enableArea('HaveCondition');
                    eoms.form.disableArea('NoHaveCondition', true);
                    $('${sheetPageName}phaseId').value = 'FDDITask';
                    $('${sheetPageName}operateType').value = '777';

                    chooser_test9.disable();
                    chooser_test11.enable();

                    hide("fieldset9");
                    show("fieldset11");

                } else if (input == "1030102") {
                    //建设条件不具备
                    eoms.form.enableArea('NoHaveCondition');
                    eoms.form.disableArea('HaveCondition', true);
                    $('${sheetPageName}phaseId').value = 'ModifyDesignTask';
                    $('${sheetPageName}operateType').value = '712';

                    chooser_test9.enable();
                    chooser_test11.disable();
                    show("fieldset9");
                    hide("fieldset11");
                } else {

                    eoms.form.enableArea('NoHaveCondition');
                    eoms.form.disableArea('HaveCondition', true);
                    $('${sheetPageName}phaseId').value = '';
                    $('${sheetPageName}operateType').value = '';
                    chooser_test9.disable();
                    chooser_test11.disable();
                    hide("fieldset9");
                    hide("fieldset11");
                }
            }
        }
    }

    function HaveCondition3(input) {
        var frm = document.forms[0];
        var temp1 = frm.linkDevType ? frm.linkDevType.value : '';
        if ('<%=taskName%>' == 'ProjectDealTask' && '<%=operateType%>' == '93') {

            if ('<%=operateType%>' != '11') {

                if (input == "1030101") {
                    //建设条件具备
                    eoms.form.enableArea('HaveCondition');
                    eoms.form.disableArea('NoHaveCondition', true);
                    $('${sheetPageName}phaseId').value = 'TrasitionTask';
                    $('${sheetPageName}operateType').value = '711';
                    $('${sheetPageName}extendKey2').value = '';
                    $('${sheetPageName}toMorePhaseId').value = '';
                    chooser_test3.enable();
                    chooser_test8.enable();

                    chooser_test9.disable();
                    show("fieldset3");
                    show("fieldset8");

                    hide("fieldset9");

                } else if (input == "1030102") {
                    //建设条件不具备
                    eoms.form.enableArea('NoHaveCondition');
                    eoms.form.disableArea('HaveCondition', true);
                    $('${sheetPageName}phaseId').value = 'ModifyDesignTask';
                    $('${sheetPageName}operateType').value = '712';
                    $('${sheetPageName}extendKey2').value = '';
                    $('${sheetPageName}toMorePhaseId').value = '';
                    chooser_test3.disable();
                    chooser_test8.disable();

                    chooser_test9.enable();
                    hide("fieldset3");
                    hide("fieldset8");
                    show("fieldset9");
                } else {

                    eoms.form.enableArea('NoHaveCondition');
                    eoms.form.disableArea('HaveCondition', true);
                    $('${sheetPageName}phaseId').value = '';
                    $('${sheetPageName}operateType').value = '';
                    $('${sheetPageName}extendKey2').value = '';
                    $('${sheetPageName}toMorePhaseId').value = '';
                    chooser_test3.disable();
                    chooser_test8.disable();

                    chooser_test9.disable();
                    hide("fieldset3");
                    hide("fieldset8");
                    hide("fieldset9");
                }
            }
        }
    }


</script>
