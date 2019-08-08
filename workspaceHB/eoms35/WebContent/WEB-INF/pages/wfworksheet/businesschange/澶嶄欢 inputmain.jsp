<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/WEB-INF/pages/wfworksheet/businesschange/baseinputmainhtmlnew.jsp" %>

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
            $("UIFrame1-numDetail").setStyle("width:350px;height:100px");
            eoms.form.disableArea('MMS', true);
            eoms.form.disableArea('SMS', true);
            eoms.form.disableArea('TRANSFER', true);
        } else if (input == 101100102) {
            eoms.form.enableArea('MMS');
            $("UIFrame1-AppProgramme").setStyle("width:350px;height:100px");
            eoms.form.disableArea('GPRS', true);
            eoms.form.disableArea('SMS', true);
            eoms.form.disableArea('TRANSFER', true);

        } else if (input == 101100103) {
            eoms.form.enableArea('SMS');
            $("UIFrame1-sheetAccessories").setStyle("width:350px;height:100px");
            eoms.form.disableArea('GPRS', true);
            eoms.form.disableArea('MMS', true);
            eoms.form.disableArea('TRANSFER', true);

        } else if (input == 101100104) {
            eoms.form.enableArea('TRANSFER');
            eoms.form.disableArea('GPRS', true);
            eoms.form.disableArea('MMS', true);
            eoms.form.disableArea('SMS', true);

        } else {
            eoms.form.disableArea('TRANSFER', true);
            eoms.form.disableArea('GPRS', true);
            eoms.form.disableArea('MMS', true);
            eoms.form.disableArea('SMS', true);

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


    //changeOperate($('${sheetPageName}mainSendContentType').value);
</script>
<!-- base info -->


<input type="hidden" name="${sheetPageName}processTemplateName" value="BusinessChangeMainFlowProcess"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="BusinessChangeProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newSheet"/>
<c:if test="${status==0}">
    <input type="hidden" id="${sheetPageName}phaseId" name="${sheetPageName}phaseId" value="TaskCreateAuditHumTask"/>
    <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType"
           value="${sheetPageName}operateType"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" id="${sheetPageName}phaseId" name="${sheetPageName}phaseId" value=""/>
</c:if>
<input type="hidden" name="${sheetPageName}beanId" value="iBusinessChangeMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.businesschange.model.BusinessChangeMain"/>
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.businesschange.model.BusinessChangeLink"/>
<!-- sheet info -->
</br>
<table class="formTable">

    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.sheetAcceptLimit"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                   id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"
                   onclick="popUpCalendar(this, this)"/>

        </td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.sheetCompleteLimit"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"/>

        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.businesstype1"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}businesstype1" id="${sheetPageName}businesstype1"
                           onchange="changeOperate(this.value);"
                           initDicId="1011001" alt="allowBlank:false" defaultValue="${sheetMain.businesstype1}"
                           styleClass="select-class"/>
        </td>


        <td class="label"><bean:message bundle="businesschange" key="businesschange.urgentDegree"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}urgentDegree" id="${sheetPageName}urgentDegree"
                           initDicId="10302" alt="allowBlank:false" defaultValue="${sheetMain.urgentDegree}"
                           styleClass="select-class"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.bdeptContact"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}bdeptContact" value="${sheetMain.bdeptContact}"
                   id="${sheetPageName}bdeptContact"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入业务部门联系人，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businesschange" key="businesschange.bdeptContactPhone"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}bdeptContactPhone"
                   value="${sheetMain.bdeptContactPhone}" id="${sheetPageName}bdeptContactPhone"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入业务部门联系人电话，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.customNo"/></td>
        <td><input type="text" class="text" name="${sheetPageName}customNo" id="${sheetPageName}customNo"
                   value="${sheetMain.customNo}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户编号，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businesschange" key="businesschange.customName"/></td>
        <td><input type="text" class="text" name="${sheetPageName}customName" id="${sheetPageName}customName"
                   value="${sheetMain.customName}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入业务集团客户名称，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.customContact"/></td>
        <td><input type="text" class="text" name="${sheetPageName}customContact" id="${sheetPageName}customContact"
                   value="${sheetMain.customContact}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户联系人，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businesschange" key="businesschange.customContactPhone"/></td>
        <td><input type="text" class="text" name="${sheetPageName}customContactPhone"
                   id="${sheetPageName}customContactPhone" value="${sheetMain.customContactPhone}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户联系人电话，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.customContactAdd"/></td>
        <td><input type="text" class="text" name="${sheetPageName}customContactAdd"
                   id="${sheetPageName}customContactAdd" value="${sheetMain.customContactAdd}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户联系人地址，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businesschange" key="businesschange.customContactPost"/></td>
        <td><input type="text" class="text" name="${sheetPageName}customContactPost"
                   id="${sheetPageName}customContactPost" value="${sheetMain.customContactPost}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户联系邮编，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.customTrade"/></td>
        <td><input type="text" class="text" name="${sheetPageName}customTrade" id="${sheetPageName}customTrade"
                   value="${sheetMain.customTrade}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入集团客户行业，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businesschange" key="businesschange.customContactEmail"/></td>
        <td><input type="text" class="text" name="${sheetPageName}customContactEmail"
                   id="${sheetPageName}customContactEmail" value="${sheetMain.customContactEmail}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入客户联系邮箱，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.customLevel"/></td>
        <td colspan='3'><input type="text" class="text" name="${sheetPageName}customLevel"
                               id="${sheetPageName}customLevel" value="${sheetMain.customLevel}"
                               alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入客户级别，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tbody id='GPRS' style="display:none">
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.factureArea"/>*</td>
        <td>
            <div id="areaview" class="hide"></div>
            <script type="text/javascript">
                //viewer
                var areaViewer = new Ext.JsonView("areaview",
                    '<div class="viewlistitem-{nodeType}">{name}</div>',
                    {
                        emptyText: '<div>${eoms:a2u("没有选择项目")}</div>'
                    }
                );
                var data = "[{id:'${sheetMain.factureArea}',name:'<eoms:id2nameDB id='${sheetMain.factureArea}' beanId='tawSystemAreaDao'/>',nodeType:'area'}]";
                areaViewer.jsonData = eoms.JSONDecode(data);
                areaViewer.refresh();

                //area tree
                var deptTreeAction = '${app}/xtree.do?method=areaTree';
                deptetree = new xbox({

                    btnId: '${sheetPageName}showDept1',
                    dlgId: 'dlg3',

                    treeDataUrl: deptTreeAction,
                    treeRootId: '-1',
                    treeRootText: '${eoms:a2u('地市')}',
                    treeChkMode: 'single',
                    treeChkType: 'area',
                    showChkFldId: '${sheetPageName}showDept1',
                    saveChkFldId: '${sheetPageName}factureArea1',
                    viewer: areaViewer
                });
            </script>
            <input type="text" class="text" readonly="readonly" name="${sheetPageName}showDept1"
                   id="${sheetPageName}showDept1" alt="allowBlank:false,vtext:'${eoms:a2u('请选择地域名称')}'"
                   value="<eoms:id2nameDB id='${sheetMain.factureArea}' beanId='tawSystemAreaDao'/>"/>
            <input type="hidden" name="${sheetPageName}factureArea" id="${sheetPageName}factureArea1"
                   value="${sheetMain.factureArea}"/>
        </td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.APNName"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}apNName" id="${sheetPageName}apNName"
                   value="${sheetMain.apNName}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入APN名称，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.ipAddressAssign"/>*</td>
        <td class="content"><eoms:comboBox name="${sheetPageName}ipAddressAssign" id="${sheetPageName}ipAddressAssign"
                                           initDicId="1011008" alt="allowBlank:false" styleClass="select-class"
                                           defaultValue="${sheetMain.ipAddressAssign}"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.apnRouterMode"/>*</td>
        <td class="content"><eoms:comboBox name="${sheetPageName}apnRouterMode" id="${sheetPageName}apnRouterMode"
                                           initDicId="1011009" alt="allowBlank:false" styleClass="select-class"
                                           defaultValue="${sheetMain.apnRouterMode}"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.apnIPPool"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}apnIPPool"
                                   id="${sheetPageName}apnIPPool"
                                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入APN的IP地址池，最大长度为100个汉字！')}'"
                                   value="${sheetMain.apnIPPool}"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.transferMode"/>*</td>
        <td class="content"><eoms:comboBox name="${sheetPageName}transferMode" id="${sheetPageName}transferMode"
                                           initDicId="1011010" alt="allowBlank:false" styleClass="select-class"
                                           defaultValue="${sheetMain.transferMode}"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.AppServerIPAdd"/></td>
        <td><input type="text" class="text" name="${sheetPageName}appServerIPAdd" id="${sheetPageName}appServerIPAdd"
                   value="${sheetMain.appServerIPAdd}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入企业端IP地址，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.doubleGGSN"/>*</td>
        <td class="content"><eoms:comboBox name="${sheetPageName}doubleGGSN" id="${sheetPageName}doubleGGSN"
                                           initDicId="1011017" alt="allowBlank:false" styleClass="select-class"
                                           defaultValue="${sheetMain.doubleGGSN}"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.secondIPPool"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}secondIPPool"
                                   id="${sheetPageName}secondIPPool"
                                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入第二APN地址，最大长度为100个汉字！')}'"
                                   value="${sheetMain.secondIPPool}"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.isRadiusValidate"/></td>
        <td><eoms:comboBox name="${sheetPageName}isRadiusValidate" id="${sheetPageName}isRadiusValidate"
                           initDicId="1011019" alt="allowBlank:false" defaultValue="${sheetMain.isRadiusValidate}"
                           onchange="changeType(this.value);" styleClass="select-class"/></td>

    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.radiusValidateIPAdd"/></td>
        <td><input type="text" class="text" name="${sheetPageName}radiusValidateIPAdd"
                   id="${sheetPageName}radiusValidateIPAdd" value="${sheetMain.radiusValidateIPAdd}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入如用户端进行RADIUS验证，请提供用户端RADIUS服务器IP地址，最大长度为100个汉字！')}'"/>
        </td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.simHLR"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}simHLR" id="${sheetPageName}simHLR"
                                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入SIM卡HLR制作范围，最大长度为100个汉字！')}'"
                                   value="${sheetMain.simHLR}"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.terminalNum"/></td>
        <td colspan='3'><input type="text" class="text" name="${sheetPageName}terminalNum"
                               id="${sheetPageName}terminalNum" value="${sheetMain.terminalNum}"
                               alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入终端数量，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.numDetail"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetMain" property="numDetail"
                             scope="request" idField="${sheetPageName}numDetail" appCode="businesschangesheet"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.volumeAssessment"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}volumeAssessment" id="${sheetPageName}volumeAssessment" class="textarea max"
                      alt="allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入业务量评估，最大长度为1000个汉字！')}'">${sheetMain.volumeAssessment}</textarea>
        </td>
    </tr>
    </tbody>

    <tbody id='MMS' style="display:none">
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.bCode"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}bcode" id="${sheetPageName}bcode"
                   value="${sheetMain.bcode}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入业务代码，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.SIName"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}siName" id="${sheetPageName}siName"
                   value="${sheetMain.siName}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI名称，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.SIEnterpriseCode"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}siEnterpriseCode"
                   id="${sheetPageName}siEnterpriseCode" value="${sheetMain.siEnterpriseCode}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI企业代码，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.SIServerCode"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}siServerCode" id="${sheetPageName}siServerCode"
                   value="${sheetMain.siServerCode}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI名称，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.SIConnectMMSGatewayID"/></td>
        <td><input type="text" class="text" name="${sheetPageName}siConnectMMSGatewayID"
                   id="${sheetPageName}siConnectMMSGatewayID" value="${sheetMain.siConnectMMSGatewayID}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI接入彩信网关ID，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.SIIPAdd"/></td>
        <td><input type="text" class="text" name="${sheetPageName}siIPAdd" id="${sheetPageName}siIPAdd"
                   value="${sheetMain.siIPAdd}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI的IP地址，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.SIUplinkUrl"/></td>
        <td><input type="text" class="text" name="${sheetPageName}siUplinkUrl" id="${sheetPageName}siUplinkUrl"
                   value="${sheetMain.siUplinkUrl}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI上行URL，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businesschange" key="businesschange.isConnectMISC"/></td>
        <td><input type="text" class="text" name="${sheetPageName}isConnectMISC" id="${sheetPageName}isConnectMISC"
                   value="${sheetMain.isConnectMISC}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入是否接入MISC，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.factureArea"/>*</td>
        <td colspan='3'>
            <div id="areaview2" class="hide"></div>
            <script type="text/javascript">
                //viewer
                var areaViewer = new Ext.JsonView("areaview2",
                    '<div class="viewlistitem-{nodeType}">{name}</div>',
                    {
                        emptyText: '<div>${eoms:a2u("没有选择项目")}</div>'
                    }
                );
                var data = "[{id:'${sheetMain.factureArea}',name:'<eoms:id2nameDB id='${sheetMain.factureArea}' beanId='tawSystemAreaDao'/>',nodeType:'area'}]";
                areaViewer.jsonData = eoms.JSONDecode(data);
                areaViewer.refresh();

                //area tree
                var deptTreeAction = '${app}/xtree.do?method=areaTree';
                deptetree = new xbox({

                    btnId: '${sheetPageName}showDept2',
                    dlgId: 'dlg3',

                    treeDataUrl: deptTreeAction,
                    treeRootId: '-1',
                    treeRootText: '${eoms:a2u('地市')}',
                    treeChkMode: 'single',
                    treeChkType: 'area',
                    showChkFldId: '${sheetPageName}showDept2',
                    saveChkFldId: '${sheetPageName}factureArea2',
                    viewer: areaViewer
                });
            </script>
            <input type="text" class="text" readonly="readonly" name="${sheetPageName}showDept2"
                   id="${sheetPageName}showDept2" alt="allowBlank:false,vtext:'${eoms:a2u('请选择地域名称')}'"
                   value="<eoms:id2nameDB id='${sheetMain.factureArea}' beanId='tawSystemAreaDao'/>"/>
            <input type="hidden" name="${sheetPageName}factureArea" id="${sheetPageName}factureArea2"
                   value="${sheetMain.factureArea}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.comProtocol"/>*</td>
        <td><eoms:comboBox name="${sheetPageName}comProtocol" id="${sheetPageName}comProtocol"
                           initDicId="1011014" alt="allowBlank:false" defaultValue="${sheetMain.comProtocol}"
                           styleClass="select-class"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.connectGatewayBandwidth"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}connectGatewayBandwidth"
                   id="${sheetPageName}connectGatewayBandwidth" value="${sheetMain.connectGatewayBandwidth}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入接入网关带宽，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.maxConnections"/></td>
        <td><input type="text" class="text" name="${sheetPageName}maxConnections" id="${sheetPageName}maxConnections"
                   value="${sheetMain.maxConnections}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入最大连接数，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.maxUnderFlow"/></td>
        <td><input type="text" class="text" name="${sheetPageName}maxUnderFlow" id="${sheetPageName}maxUnderFlow"
                   value="${sheetMain.maxUnderFlow}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入最大下发流量，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.maxUplinkFlow"/></td>
        <td><input type="text" class="text" name="${sheetPageName}maxUplinkFlow" id="${sheetPageName}maxUplinkFlow"
                   value="${sheetMain.maxUplinkFlow}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入最大上行流量，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.portRateIsDown"/>*</td>
        <td><eoms:comboBox name="${sheetPageName}portRateIsDown" id="${sheetPageName}portRateIsDown"
                           initDicId="1011017" defaultValue="${sheetMain.portRateIsDown}" alt="allowBlank:false"
                           styleClass="select-class"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.flowControlPriority"/></td>
        <td colspan='3'><input type="text" class="text" name="${sheetPageName}flowControlPriority"
                               id="${sheetPageName}flowControlPriority" value="${sheetMain.flowControlPriority}"
                               alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入企业流控优先级，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.AppProgramme"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetMain" property="appProgramme"
                             scope="request" idField="${sheetPageName}appProgramme" appCode="businesschangesheet"/></td>
    </tr>
    </tbody>
    <tbody id='SMS' style="display:none">
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.nameListSetType"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}nameListSetType" id="${sheetPageName}nameListSetType"
                   value="${sheetMain.nameListSetType}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入黑白名单设置方式，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.SMSSigned"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}smSSigned" id="${sheetPageName}smSSigned"
                   value="${sheetMain.smSSigned}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入短信签名，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.enterpriseCode"/></td>
        <td><input type="text" class="text" name="${sheetPageName}enterpriseCode" id="${sheetPageName}enterpriseCode"
                   value="${sheetMain.enterpriseCode}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入企业代码，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.bCode"/></td>
        <td><input type="text" class="text" name="${sheetPageName}bcode" id="${sheetPageName}bcode"
                   value="${sheetMain.bcode}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入业务代码，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.serverCode"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}serverCode" id="${sheetPageName}serverCode"
                   value="${sheetMain.serverCode}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入服务代码，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.factureArea"/>*</td>
        <td>
            <div id="areaview3" class="hide"></div>
            <script type="text/javascript">
                //viewer
                var areaViewer = new Ext.JsonView("areaview3",
                    '<div class="viewlistitem-{nodeType}">{name}</div>',
                    {
                        emptyText: '<div>${eoms:a2u("没有选择项目")}</div>'
                    }
                );
                var data = "[{id:'${sheetMain.factureArea}',name:'<eoms:id2nameDB id='${sheetMain.factureArea}' beanId='tawSystemAreaDao'/>',nodeType:'area'}]";
                areaViewer.jsonData = eoms.JSONDecode(data);
                areaViewer.refresh();

                //area tree
                var deptTreeAction = '${app}/xtree.do?method=areaTree';
                deptetree = new xbox({

                    btnId: '${sheetPageName}showDept3',
                    dlgId: 'dlg3',

                    treeDataUrl: deptTreeAction,
                    treeRootId: '-1',
                    treeRootText: '${eoms:a2u('地市')}',
                    treeChkMode: 'single',
                    treeChkType: 'area',
                    showChkFldId: '${sheetPageName}showDept3',
                    saveChkFldId: '${sheetPageName}factureArea3',
                    viewer: areaViewer
                });
            </script>
            <input type="text" class="text" readonly="readonly" name="${sheetPageName}showDept3"
                   id="${sheetPageName}showDept3" alt="allowBlank:false,vtext:'${eoms:a2u('请选择地域名称')}'"
                   value="<eoms:id2nameDB id='${sheetMain.factureArea}' beanId='tawSystemAreaDao'/>"/>
            <input type="hidden" name="${sheetPageName}factureArea" id="${sheetPageName}factureArea3"
                   value="${sheetMain.factureArea}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.connectPoint"/></td>
        <td><input type="text" class="text" name="${sheetPageName}connectPoint" id="${sheetPageName}connectPoint"
                   value="${sheetMain.connectPoint}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入接入点，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.HostIPAdd"/></td>
        <td><input type="text" class="text" name="${sheetPageName}hostIPAdd" id="${sheetPageName}hostIPAdd"
                   value="${sheetMain.hostIPAdd}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入主机IP地址，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.connectGateway"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}connectGateway" id="${sheetPageName}connectGateway"
                   value="${sheetMain.connectGateway}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入接入网关，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.gatewayCode"/></td>
        <td><input type="text" class="text" name="${sheetPageName}gatewayCode" id="${sheetPageName}gatewayCode"
                   value="${sheetMain.gatewayCode}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入接入网关代码，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.comProtocol"/>*</td>
        <td><eoms:comboBox name="${sheetPageName}comProtocol" id="${sheetPageName}comProtocol"
                           initDicId="1011014" alt="allowBlank:false" defaultValue="${sheetMain.comProtocol}"
                           styleClass="select-class"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.connectGatewayBandwidth"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}connectGatewayBandwidth"
                   id="${sheetPageName}connectGatewayBandwidth" value="${sheetMain.connectGatewayBandwidth}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网关代码，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.maxConnections"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}maxConnections" id="${sheetPageName}maxConnections"
                   value="${sheetMain.maxConnections}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入最大连接数，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.maxUnderFlow"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}maxUnderFlow" id="${sheetPageName}maxUnderFlow"
                   value="${sheetMain.maxUnderFlow}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入最大下发流量，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.maxUplinkFlow"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}maxUplinkFlow" id="${sheetPageName}maxUplinkFlow"
                   value="${sheetMain.maxUplinkFlow}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入最大上行流量，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.portRateIsDown"/>*</td>
        <td><eoms:comboBox name="${sheetPageName}portRateIsDown" id="${sheetPageName}portRateIsDown"
                           initDicId="1011017" defaultValue="${sheetMain.portRateIsDown}" alt="allowBlank:false"
                           styleClass="select-class"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.flowControlPriority"/></td>
        <td colspan='3'><input type="text" class="text" name="${sheetPageName}flowControlPriority"
                               id="${sheetPageName}flowControlPriority" value="${sheetMain.flowControlPriority}"
                               alt="allowBlank:true"/>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.AppProgramme"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetMain" property="sheetAccessories"
                             scope="request" idField="${sheetPageName}sheetAccessories"
                             appCode="businesschangesheet"/></td>
    </tr>
    </tbody>
    <tbody id='TRANSFER' style="display:none">
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.cityA"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}cityA" id="${sheetPageName}cityA"
                   value="${sheetMain.cityA}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入城市A，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.cityZ"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}cityZ" id="${sheetPageName}cityZ"
                   value="${sheetMain.cityZ}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入城市Z，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.bandwidth"/></td>
        <td><input type="text" class="text" name="${sheetPageName}bandwidth" id="${sheetPageName}bandwidth"
                   value="${sheetMain.bandwidth}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入带宽，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businesschange" key="businesschange.amount"/></td>
        <td><input type="text" class="text" name="${sheetPageName}amount" id="${sheetPageName}amount"
                   value="${sheetMain.amount}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入数量，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.portA"/></td>
        <td><input type="text" class="text" name="${sheetPageName}portA" id="${sheetPageName}portA"
                   value="${sheetMain.portA}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点A，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.portAInterfaceType"/>*</td>
        <td><eoms:comboBox name="${sheetPageName}portAInterfaceType" id="${sheetPageName}portAInterfaceType"
                           initDicId="1011015" defaultValue="${sheetMain.portAInterfaceType}" alt="allowBlank:false"
                           styleClass="select-class"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.portADetailAdd"/></td>
        <td><input type="text" class="text" name="${sheetPageName}portADetailAdd" id="${sheetPageName}portADetailAdd"
                   value="${sheetMain.portADetailAdd}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入端点A详细地址，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.portABDeviceName"/></td>
        <td><input type="text" class="text" name="${sheetPageName}portABDeviceName"
                   id="${sheetPageName}portABDeviceName" value="${sheetMain.portABDeviceName}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入端点A业务设备名称，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.portABDevicePort"/></td>
        <td><input type="text" class="text" name="${sheetPageName}portABDevicePort"
                   id="${sheetPageName}portABDevicePort" value="${sheetMain.portABDevicePort}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入端点A业务设备端口，最大长度为100个汉字！')}'"/></td>

        <td class="label"><bean:message bundle="businesschange" key="businesschange.portAContactPhone"/></td>
        <td><input type="text" class="text" name="${sheetPageName}portAContactPhone"
                   id="${sheetPageName}portAContactPhone" value="${sheetMain.portAContactPhone}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入端点A联系人及电话，最大长度为100个汉字！')}'"/></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.portZ"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}portZ" id="${sheetPageName}portZ"
                   value="${sheetMain.portZ}"
                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入端点Z，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.portZInterfaceType"/>*</td>
        <td><eoms:comboBox name="${sheetPageName}portZInterfaceType" id="${sheetPageName}portZInterfaceType"
                           initDicId="1011016" defaultValue="${sheetMain.portZInterfaceType}" alt="allowBlank:false"
                           styleClass="select-class"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.portZBDeviceName"/></td>
        <td><input type="text" class="text" name="${sheetPageName}portZBDeviceName"
                   id="${sheetPageName}portZBDeviceName" value="${sheetMain.portZBDeviceName}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入端点Z详细地址，最大长度为100个汉字！')}'"/></td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.portZBDevicePort"/></td>
        <td><input type="text" class="text" name="${sheetPageName}portZBDevicePort"
                   id="${sheetPageName}portZBDevicePort" value="${sheetMain.portZBDevicePort}"
                   alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入端点Z业务设备端口，最大长度为100个汉字！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.portZContactPhone"/></td>
        <td colspan="3"><input type="text" class="text" name="${sheetPageName}portZContactPhone"
                               id="${sheetPageName}portZContactPhone" value="${sheetMain.portZContactPhone}"
                               alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('请输入端点Z联系人及电话，最大长度为100个汉字！')}'"/>
        </td>
    </tr>

    </tbody>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.bRequirementDesc"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}brequirementDesc" id="${sheetPageName}brequirementDesc" class="textarea max"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入业务需求描述，最大长度为1000个汉字！')}'">${sheetMain.brequirementDesc}</textarea>
        </td>
    </tr>
</table>
<c:if test="${status!=1}">
    <fieldset>
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
            <span id="roleName"></span>
        </legend>
        <eoms:chooser id="test"
                      category="[{id:'dealPerformer',text:'${eoms:a2u('派发')}',childType:'user',allowBlank:false,vtext:'${eoms:a2u('请选择派发人')}',limit:'none'},{id:'copyPerformer',text:'${eoms:a2u('抄送')}',childType:'user',limit:'none'},{id:'auditPerformer',text:'${eoms:a2u('审核')}',childType:'user'}]"
                      data="${sendUserAndRoles}"/>

    </fieldset>
</c:if>
