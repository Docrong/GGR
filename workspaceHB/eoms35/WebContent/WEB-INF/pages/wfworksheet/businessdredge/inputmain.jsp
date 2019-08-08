<%@ include file="/common/taglibs.jsp" %>
<%
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/businessdredge/baseinputmainhtmlnew.jsp" %>
<script type="text/javascript">
    function changeType(input) {
        if (input == 101101901) {
            document.forms[0].radiusValidateIPAdd.disabled = false;
        } else {
            document.forms[0].radiusValidateIPAdd.disabled = true;
            document.forms[0].radiusValidateIPAdd.value = "";
        }
    }

    changeOperate = function (input) {

        if (input == 101100101) {
            eoms.form.enableArea('GPRS');
            eoms.form.disableArea('MMS', true);
            eoms.form.disableArea('SMS', true);
            eoms.form.disableArea('TRANSFER', true);
            eoms.form.enableArea('num');
            $("UIFrame1-numDetailFile").setStyle("width:350px;height:100px");
            eoms.form.disableArea('app', true);
        } else if (input == 101100102) {
            eoms.form.enableArea('MMS');
            eoms.form.disableArea('GPRS', true);
            eoms.form.disableArea('SMS', true);
            eoms.form.disableArea('TRANSFER', true);
            eoms.form.enableArea('app');
            $("UIFrame1-appProgramme").setStyle("width:350px;height:100px");
            eoms.form.disableArea('num', true);
        } else if (input == 101100103) {
            eoms.form.enableArea('SMS');
            eoms.form.disableArea('GPRS', true);
            eoms.form.disableArea('MMS', true);
            eoms.form.disableArea('TRANSFER', true);
            eoms.form.enableArea('app');
            $("UIFrame1-appProgramme").setStyle("width:350px;height:100px");
            eoms.form.disableArea('num', true);

        } else if (input == 101100104) {
            eoms.form.enableArea('TRANSFER');
            eoms.form.disableArea('GPRS', true);
            eoms.form.disableArea('MMS', true);
            eoms.form.disableArea('SMS', true);
            eoms.form.disableArea('num', true);
            eoms.form.disableArea('app', true);

        } else {
            eoms.form.disableArea('TRANSFER', true);
            eoms.form.disableArea('GPRS', true);
            eoms.form.disableArea('MMS', true);
            eoms.form.disableArea('SMS', true);
            eoms.form.disableArea('num', true);
            eoms.form.disableArea('app', true);

        }
    }

    var frm = document.forms[0];
    var temp = frm.businesstype1 ? frm.businesstype1.value : '';
    var temp1 = frm.isRadiusValidate ? frm.isRadiusValidate.value : '';
    if (temp != '') {
        changeOperate(temp);
    }
    if (temp1 != '') {
        changeOperate(temp);
    }
    selectLimit();

    function selectLimit() {
        Ext.Ajax.request({
            method: "get",
            url: "businessdredge.do?method=newShowLimit&flowName=BusinessDredgeMainFlowProcess",
            success: function (x) {
                var o = eoms.JSONDecode(x.responseText);
                if ((o.acceptLimit == null || o.acceptLimit == "") && (o.replyLimit == null || o.replyLimit == "")) {
                    $("${sheetPageName}sheetAcceptLimit").value = "";
                    $('${sheetPageName}sheetCompleteLimit').value = "";
                } else {
                    var times =<%=localTimes%>;
                    var dt1 = new Date(times).add(Date.MINUTE, parseInt(o.acceptLimit, 10));
                    var dt2 = dt1.add(Date.MINUTE, parseInt(o.replyLimit, 10));
                    $("${sheetPageName}sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
                    $('${sheetPageName}sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
                }
            }
        });
    }
</script>
<input type="hidden" name="${sheetPageName}processTemplateName" value="BusinessDredgeMainFlowProcess"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="BusinessDredgeMainFlowProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newSheet"/>
<c:if test="${status==0}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCreateAuditHumTask"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
</c:if>
<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0"/>
<input type="hidden" name="${sheetPageName}beanId" value="iBusinessDredgeMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.businessdredge.model.BusinessDredgeMain"/>
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.businessdredge.model.BusinessDredgeLink"/>
<!-- sheet info -->
</br>

<table class="formTable">

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.sheetacceptlimit"/></td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                   id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"
                   onclick="popUpCalendar(this, this)"/>

        </td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.sheetcompletelimit"/></td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"/>
        </td>
    </tr>


    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.businesstype1"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}businesstype1" id="${sheetPageName}businesstype1"
                           defaultValue="${sheetMain.businesstype1}"
                           initDicId="1011001" alt="allowBlank:false" styleClass="select-class"
                           onchange="changeOperate(this.value);"/></td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.mainRemark"/></td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}urgentDegree" id="${sheetPageName}urgentDegree"
                           defaultValue="${sheetMain.urgentDegree}"
                           initDicId="1010102" alt="allowBlank:false" styleClass="select-class"/>
        </td>

    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.bdeptContact"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}bdeptContact" id="${sheetPageName}bdeptContact"
                   value="${sheetMain.bdeptContact}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入业务部门联系人，最大长度为100个汉字！')}'"/>
        </td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.bdeptContactPhone"/></td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}bdeptContactPhone"
                                   id="${sheetPageName}bdeptContactPhone" value="${sheetMain.bdeptContactPhone}"
                                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入业务部门联系人电话，最大长度为100个汉字！')}'"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.customNo"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}customNo" id="${sheetPageName}customNo"
                   value="${sheetMain.customNo}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入集团客户编号，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.customName"/></td>
        <td><input type="text" class="text" name="${sheetPageName}customName" id="${sheetPageName}customName"
                   value="${sheetMain.customName}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户名称，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.customContact"/></td>
        <td><input type="text" class="text" name="${sheetPageName}customContact" id="${sheetPageName}customContact"
                   value="${sheetMain.customContact}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户联系人，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.customContactPhone"/></td>
        <td><input type="text" class="text" name="${sheetPageName}customContactPhone"
                   id="${sheetPageName}customContactPhone" value="${sheetMain.customContactPhone}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户联系人电话，最大长度为100个汉字！')}'"/></td>
    </tr>


    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.cityName"/></td>
        <td><input type="text" class="text" name="${sheetPageName}cityName" id="${sheetPageName}cityName"
                   value="${sheetMain.cityName}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入所属地市，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.countyName"/></td>
        <td><input type="text" class="text" name="${sheetPageName}countyName" id="${sheetPageName}countyName"
                   value="${sheetMain.countyName}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入所属区县，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.cmanagerPhone"/></td>
        <td><input type="text" class="text" name="${sheetPageName}cmanagerPhone" id="${sheetPageName}cmanagerPhone"
                   value="${sheetMain.cmanagerPhone}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入客户经理，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.cmanagerContactPhone"/></td>
        <td><input type="text" class="text" name="${sheetPageName}cmanagerContactPhone"
                   id="${sheetPageName}cmanagerContactPhone" value="${sheetMain.cmanagerContactPhone}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入客户经理联系电话，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.customLevel"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}customLevel" id="${sheetPageName}customLevel"
                           defaultValue="${sheetMain.customLevel}"
                           initDicId="1010107" alt="allowBlank:false" styleClass="select-class"/>
        </td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.productName"/></td>
        <td><input type="text" class="text" name="${sheetPageName}productName" id="${sheetPageName}productName"
                   value="${sheetMain.productName}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入产品名称，最大长度为100个汉字！')}'"/></td>


    </tr>

    <tr>


        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.provinceName"/></td>
        <td colspan='3'><input type="text" class="text" name="${sheetPageName}provinceName"
                               id="${sheetPageName}provinceName" value="${sheetMain.provinceName}"
                               alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入所属省份，最大长度为100个汉字！')}'"/></td>
    </tr>
    <!-- GPRS -->
    <tbody id='GPRS' style="display:none">
    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.factureArea"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}factureArea" id="${sheetPageName}factureArea"
                           defaultValue="${sheetMain.factureArea}"
                           initDicId="10301" alt="allowBlank:false" styleClass="select-class"/>
        </td>
    </tr>


    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.AppServerIPAdd"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}appServerIPAdd"
                                   id="${sheetPageName}appServerIPAdd" value="${sheetMain.appServerIPAdd}"
                                   alt="allowBlank:false,maxLength:254,vtext:'${eoms:a2u('请输入企业端IP地址，最大长度为127个汉字！')}'"/>
        </td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.simHLR"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}simHLR" id="${sheetPageName}simHLR"
                                   value="${sheetMain.simHLR}"
                                   alt="allowBlank:false,maxLength:254,vtext:'${eoms:a2u('请输入SIM卡HLR制作范围，最大长度为127个汉字！')}'"/>
        </td>
    </tr>


    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.isRadiusValidate"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}isRadiusValidate" id="${sheetPageName}isRadiusValidate"
                           defaultValue="${sheetMain.isRadiusValidate}"
                           initDicId="1011019" alt="allowBlank:false" styleClass="select-class"
                           onchange="changeType(this.value);"/>
        </td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.radiusValidateIPAdd"/></td>
        <td><input type="text" class="text" name="${sheetPageName}radiusValidateIPAdd"
                   id="${sheetPageName}radiusValidateIPAdd" value="${sheetMain.radiusValidateIPAdd}"
                   alt="allowBlank:true,maxLength:254,vtext:'${eoms:a2u('请输入请提供用户断RADIUS服务器IP地址，最大长度为127个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.terminalNum"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}terminalNum" id="${sheetPageName}terminalNum"
                   value="${sheetMain.terminalNum}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入终端数量，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.APNName"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}apnName" id="${sheetPageName}apnName"
                   value="${sheetMain.apnName}"
                   alt="allowBlank:false,maxLength:64,vtext:'${eoms:a2u('请输如APN名称，最大长度为32个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.ipAddressAssign"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}ipAddressAssign" id="${sheetPageName}ipAddressAssign"
                           defaultValue="${sheetMain.ipAddressAssign}"
                           initDicId="1011008" alt="allowBlank:false" styleClass="select-class"/>
        </td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.apnRouterMode"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}apnRouterMode" id="${sheetPageName}apnRouterMode"
                           defaultValue="${sheetMain.apnRouterMode}"
                           initDicId="1011009" alt="allowBlank:false" styleClass="select-class"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.apnIPPool"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}apnIPPool" id="${sheetPageName}apnIPPool"
                   value="${sheetMain.apnIPPool}"
                   alt="allowBlank:false,maxLength:254,vtext:'${eoms:a2u('请输入APN地址池，最大长度为127个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.transferMode"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}transferMode" id="${sheetPageName}transferMode"
                           defaultValue="${sheetMain.transferMode}"
                           initDicId="1011010" alt="allowBlank:false" styleClass="select-class"/>
        </td>
    </tr>


    <tr>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.doubleGGSN"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}doubleGGSN" id="${sheetPageName}doubleGGSN"
                           defaultValue="${sheetMain.doubleGGSN}"
                           initDicId="1011017" alt="allowBlank:false" styleClass="select-class"/>
        </td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.secondIPPool"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}secondIPPool" id="${sheetPageName}secondIPPool"
                   value="${sheetMain.secondIPPool}"
                   alt="allowBlank:false,maxLength:254,vtext:'${eoms:a2u('请输入第二个APN地址池，最大长度为127个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.volumeAssessment"/>*</td>
        <td colspan="3"><textarea class="textarea max" name="${sheetPageName}volumeAssessment"
                                  id="${sheetPageName}volumeAssessment" value="${sheetMain.volumeAssessment}"
                                  alt="width:500,allowBlank:false,maxLength:254,vtext:'${eoms:a2u('请输入业务评估量，最大长度为127个汉字！')}'"/>${sheetMain.volumeAssessment}</textarea>

    </tr>


    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.numDetail"/>*</td>
        <td colspan="3"><input type="text" class="text" name="${sheetPageName}numDetail" id="${sheetPageName}numDetail"
                               value="${sheetMain.numDetail}"
                               alt="allowBlank:false,maxLength:254,vtext:'${eoms:a2u('请输入号码明细，最大长度为127个汉字！')}'"/></td>
    </tr>
    </tbody>

    <tbody id='MMS' style="display:none">
    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.bCode"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}bcode" id="${sheetPageName}bcode"
                                   value="${sheetMain.bcode}"
                                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入业务代码，最大长度为100个汉字！')}'"/>
        </td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.isConnectMISC"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}isConnectMISC" id="${sheetPageName}isConnectMISC"
                           defaultValue="${sheetMain.isConnectMISC}"
                           initDicId="1010509" alt="allowBlank:false" styleClass="select-class"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.siconnectMMSGatewayName"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}siconnectMMSGatewayName"
                   id="${sheetPageName}siconnectMMSGatewayName" value="${sheetMain.siconnectMMSGatewayName}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI接入彩信网关名称，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.SIEnterpriseCode"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}siEnterpriseCode"
                   id="${sheetPageName}siEnterpriseCode" value="${sheetMain.siEnterpriseCode}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入SI企业代码，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.SIServerCode"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}siServerCode" id="${sheetPageName}siServerCode"
                   value="${sheetMain.siServerCode}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入SI服务代码，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.SIConnectMMSGatewayID"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}siConnectMMSGatewayID"
                   id="${sheetPageName}siConnectMMSGatewayID" value="${sheetMain.siConnectMMSGatewayID}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入SI接入彩信网管ID，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.SIIPAdd"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}siIPAdd" id="${sheetPageName}siIPAdd"
                   value="${sheetMain.siIPAdd}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入SI的IP地址，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.SIUplinkUrl"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}siUplinkUrl" id="${sheetPageName}siUplinkUrl"
                   value="${sheetMain.siUplinkUrl}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入SI上行URL，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.comProtocol"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}comProtocol" id="${sheetPageName}comProtocol"
                           defaultValue="${sheetMain.comProtocol}"
                           initDicId="1011052" alt="allowBlank:false" styleClass="select-class"/>
        </td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.connectGatewayBandwidth"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}connectGatewayBandwidth"
                   id="${sheetPageName}connectGatewayBandwidth" value="${sheetMain.connectGatewayBandwidth}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入接入网管带宽，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.maxConnections"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}maxConnections" id="${sheetPageName}maxConnections"
                   value="${sheetMain.maxConnections}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入最大连接数，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.maxUnderFlow"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}maxUnderFlow" id="${sheetPageName}maxUnderFlow"
                   value="${sheetMain.maxUnderFlow}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入最大下发流量，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.maxUplinkFlow"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}maxUplinkFlow" id="${sheetPageName}maxUplinkFlow"
                   value="${sheetMain.maxUplinkFlow}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入最大上行流量，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.flowControlPriority"/></td>
        <td><input type="text" class="text" name="${sheetPageName}flowControlPriority"
                   id="${sheetPageName}flowControlPriority" value="${sheetMain.flowControlPriority}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入企业流控优先级，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.factureArea"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}factureArea" id="${sheetPageName}factureArea"
                           defaultValue="${sheetMain.factureArea}"
                           initDicId="10301" alt="allowBlank:false" styleClass="select-class"/>
        </td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.portRateIsDown"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}portRateIsDown" id="${sheetPageName}portRateIsDown"
                           defaultValue="${sheetMain.portRateIsDown}"
                           initDicId="1010509" alt="allowBlank:false" styleClass="select-class"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.enterpriseType"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}enterpriseType" id="${sheetPageName}enterpriseType"
                           defaultValue="${sheetMain.enterpriseType}"
                           initDicId="1011048" alt="allowBlank:false" styleClass="select-class"/>
        </td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.nameListSetType"/></td>
        <td>
            <eoms:comboBox name="${sheetPageName}nameListSetType" id="${sheetPageName}nameListSetType"
                           defaultValue="${sheetMain.nameListSetType}"
                           initDicId="1011027" alt="allowBlank:true" styleClass="select-class"/>
        </td>
    </tr>
    </tbody>

    <tbody id='SMS' style="display:none">

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.nameListSetType"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}nameListSetType" id="${sheetPageName}nameListSetType"
                           defaultValue="${sheetMain.nameListSetType}"
                           initDicId="1011027" alt="allowBlank:false" styleClass="select-class"/>
        </td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.SMSSigned"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}smsSigned" id="${sheetPageName}smsSigned"
                   value="${sheetMain.smsSigned}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入短信签名，最大长度为100个汉字！')}'"/>
        </td>
    </tr>


    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.enterpriseType"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}enterpriseType" id="${sheetPageName}enterpriseType"
                           defaultValue="${sheetMain.enterpriseType}"
                           initDicId="1011048" alt="allowBlank:false" styleClass="select-class"/>
        </td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.enterpriseCode"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}enterpriseCode" id="${sheetPageName}enterpriseCode"
                   value="${sheetMain.enterpriseCode}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入企业代码，最大长度为100个汉字！')}'"/>
        </td>
    </tr>


    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.serverCode"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}serverCode" id="${sheetPageName}serverCode"
                   value="${sheetMain.serverCode}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入服务代码，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.bCode"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}bcode" id="${sheetPageName}bcode"
                                   value="${sheetMain.bcode}"
                                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入业务代码，最大长度为100个汉字！')}'"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.HostIPAdd"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}hostIPAdd" id="${sheetPageName}hostIPAdd"
                   value="${sheetMain.hostIPAdd}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入主机IP地址，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.connectGatewayName"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}connectGatewayName"
                   id="${sheetPageName}connectGatewayName" value="${sheetMain.connectGatewayName}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI接入短信网关名称，最大长度为100个汉字！')}'"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.connectGatewayID"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}connectGatewayID"
                   id="${sheetPageName}connectGatewayID" value="${sheetMain.connectGatewayID}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI接入短信网关ID，最大长度为100个汉字！')}'"/>
        </td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.factureArea"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}factureArea" id="${sheetPageName}factureArea"
                           defaultValue="${sheetMain.factureArea}"
                           initDicId="10301" alt="allowBlank:false" styleClass="select-class"/>
        </td>

    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.comProtocol"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}comProtocol" id="${sheetPageName}comProtocol"
                           defaultValue="${sheetMain.comProtocol}"
                           initDicId="1011014" alt="allowBlank:false" styleClass="select-class"/>
        </td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.connectGatewayBandwidth"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}connectGatewayBandwidth"
                   id="${sheetPageName}connectGatewayBandwidth" value="${sheetMain.connectGatewayBandwidth}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入接入网管带宽，最大长度为100个汉字！')}'"/></td>

    </tr>


    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.maxConnections"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}maxConnections" id="${sheetPageName}maxConnections"
                   value="${sheetMain.maxConnections}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入最大连接数，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.maxUnderFlow"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}maxUnderFlow" id="${sheetPageName}maxUnderFlow"
                   value="${sheetMain.maxUnderFlow}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入最大下发流量，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.maxUplinkFlow"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}maxUplinkFlow" id="${sheetPageName}maxUplinkFlow"
                   value="${sheetMain.maxUplinkFlow}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入最大上行流量，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.portRateIsDown"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}portRateIsDown" id="${sheetPageName}portRateIsDown"
                           defaultValue="${sheetMain.portRateIsDown}"
                           initDicId="1010509" alt="allowBlank:false" styleClass="select-class"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.authenticationModel"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}authenticationModel" id="${sheetPageName}authenticationModel"
                           defaultValue="${sheetMain.authenticationModel}"
                           initDicId="1011021" alt="allowBlank:false" styleClass="select-class"/>

        </td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.singleWordsBit"/></td>
        <td><input type="text" class="text" name="${sheetPageName}singleWordsBit" id="${sheetPageName}singleWordsBit"
                   value="${sheetMain.singleWordsBit}" alt="allowBlank:true"/></td>
    </tr>


    <tr>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.flowControlPriority"/></td>
        <td><input type="text" class="text" name="${sheetPageName}flowControlPriority"
                   id="${sheetPageName}flowControlPriority" value="${sheetMain.flowControlPriority}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入企业流控优先级，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.betweenNetwork"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}betweenNetwork" id="${sheetPageName}betweenNetwork"
                           defaultValue="${sheetMain.betweenNetwork}"
                           initDicId="10301" alt="allowBlank:false" styleClass="select-class"/>
        </td>
    </tr>


    </tbody>

    <tbody id='TRANSFER' style="display:none">
    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.bandwidth"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}bandwidth" id="${sheetPageName}bandwidth"
                   value="${sheetMain.bandwidth}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入带宽，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.amount"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}amount" id="${sheetPageName}amount"
                   value="${sheetMain.amount}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入数量，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.cityA"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}cityA" id="${sheetPageName}cityA"
                   value="${sheetMain.cityA}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入城市A，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.cityZ"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}cityZ" id="${sheetPageName}cityZ"
                   value="${sheetMain.cityZ}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入城市Z，最大长度为100个汉字！')}'"/></td>
    </tr>


    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.portA"/>*</td>
        <td colspan="3"><input type="text" class="text" name="${sheetPageName}portA" id="${sheetPageName}portA"
                               value="${sheetMain.portA}"
                               alt="width:500,allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点A，最大长度为100个汉字！')}'"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.portAInterfaceType"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}portAInterfaceType" id="${sheetPageName}portAInterfaceType"
                           defaultValue="${sheetMain.portAInterfaceType}"
                           initDicId="1011015" alt="allowBlank:false" styleClass="select-class"/>
        </td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.portADetailAdd"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}portADetailAdd" id="${sheetPageName}portADetailAdd"
                   value="${sheetMain.portADetailAdd}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点A详细地址，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.portABDeviceName"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}portABDeviceName"
                   id="${sheetPageName}portABDeviceName" value="${sheetMain.portABDeviceName}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点A业务设备名称，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.portABDevicePort"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}portABDevicePort"
                   id="${sheetPageName}portABDevicePort" value="${sheetMain.portABDevicePort}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点A业务设备端口，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.portZ"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}portZ" id="${sheetPageName}portZ"
                   value="${sheetMain.portZ}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点Z，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.portAContactPhone"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}portAContactPhone" id="${sheetPageName}portZ"
                   value="${sheetMain.portAContactPhone}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点A联系人及电话，最大长度为100个汉字！')}'"/></td>
    </tr>


    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.portZInterfaceType"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}portZInterfaceType" id="${sheetPageName}portZInterfaceType"
                           defaultValue="${sheetMain.portZInterfaceType}"
                           initDicId="1011016" alt="allowBlank:false" styleClass="select-class"/>
        </td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.portZBDeviceName"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}portZBDeviceName"
                   id="${sheetPageName}portZBDeviceName" value="${sheetMain.portZBDeviceName}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点Z详细地址，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.portZBDevicePort"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}portZBDevicePort"
                   id="${sheetPageName}portZBDevicePort" value="${sheetMain.portZBDevicePort}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点Z业务设备端口，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.portZContactPhone"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}portZContactPhone"
                   id="${sheetPageName}portZContactPhone" value="${sheetMain.portZContactPhone}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点Z联系人及电话，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.confCRMTicketNo"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}confCRMTicketNo" id="${sheetPageName}confCRMTicketNo"
                   value="${sheetMain.confCRMTicketNo}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入资源确认的CRM侧工单号，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.transfBusiness"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}transfBusiness" id="${sheetPageName}transfBusiness"
                           defaultValue="${sheetMain.transfBusiness}"
                           initDicId="1011049" alt="allowBlank:false" styleClass="select-class"/>
        </td>

    </tr>
    </tbody>
    <tr style="display:none" id="num">
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.numDetailFile"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetMain" property="numDetailFile"
                             scope="request" idField="${sheetPageName}numDetailFile" appCode="businessdredge"/>
        </td>
    </tr>


    <tr style="display:none" id="app">
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.AppProgramme"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetMain" property="appProgramme"
                             scope="request" idField="${sheetPageName}appProgramme" appCode="businessdredge"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessdredge" key="businessdredge.bRequirementDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}brequirementDesc" id="${sheetPageName}brequirementDesc"
                      value="${sheetMain.brequirementDesc}"
                      alt="width:500,allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请输入业务需求描述，最大长度为500个汉字！')}'">${sheetMain.brequirementDesc}</textarea>
        </td>
    </tr>
</table>
<br/>
<c:if test="${status!=1}">
    <fieldset>
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
            <span id="roleName">:
			 </span>
        </legend>
        <div class="x-form-item">
            <eoms:chooser id="test"
                          category="[{id:'dealPerformer',childType:'user',limit:'none',text:'${eoms:a2u('派发')}',allowBlank:false,vtext:'${eoms:a2u('请选择任务执行人')}'},{id:'auditPerformer',childType:'user',text:'${eoms:a2u('审批')}'},{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
        </div>
    </fieldset>
</c:if>
<script type="text/javascript">
    changeOperate(document.getElementById('businesstype1').value);
</script>