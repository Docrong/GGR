<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%
    request.setAttribute("roleId", "249");

    long localTimes = com.boco.eoms.base.util.StaticMethod
            .getLocalTime().getTime();
%>
<%@ include
        file="/WEB-INF/pages/wfworksheet/softwareApply/baseinputmainhtmlnew.jsp" %>
<input type="hidden" name="${sheetPageName}processTemplateName"
       value="SoftwareApplyProcess"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName"
       value="SoftwareApplyProcess"/>
<input type="hidden" name="${sheetPageName}operateName"
       value="newWorkSheet"/>
<c:if test="${status!=1}">
    <input type="hidden" name="${sheetPageName}phaseId"
           id="${sheetPageName}phaseId" value="AuditingTask"/>
    <input type="hidden" id="${sheetPageName}operateType"
           name="${sheetPageName}operateType" value="${operateType}"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="${sheetPageName}phaseId"
           id="${sheetPageName}phaseId" value="Over"/>
</c:if>
<input type="hidden" name="${sheetPageName}beanId"
       value="iSoftwareApplyMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.softwareApply.model.SoftwareApplyMain"/>
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.softwareApply.model.SoftwareApplyLink"/>
<br>
<!-- sheet info -->
<table class="formTable">
    <tr>
        <td class="label"><bean:message bundle="sheet"
                                        key="mainForm.acceptLimit"/>*
        </td>
        <td class="content"><input type="text" class="text"
                                   name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                                   id="${sheetPageName}sheetAcceptLimit"
                                   value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                                   onclick="popUpCalendar(this, this)"
                                   alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

        </td>
        <td class="label"><bean:message bundle="sheet"
                                        key="mainForm.completeLimit"/>*
        </td>
        <td class="content"><input type="text" class="text"
                                   name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                                   id="${sheetPageName}sheetCompleteLimit"
                                   value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                                   onclick="popUpCalendar(this, this)"
                                   alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>

        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainmanufacturer"/>*
        </td>
        <td>
            <div id="factoryview" class="hide"></div>
            <script type="text/javascript">
                //viewer
                var factoryViewer = new Ext.JsonView("factoryview",
                    '<div class="viewlistitem-{nodeType}">{name}</div>',
                    {
                        emptyText: '<div>没有选择项目</div>'
                    }
                );

                //area tree
                var factoryTreeAction = '${app}/xtree.do?method=dict';
                factoryTree = new xbox({

                    btnId: '${sheetPageName}showFactory',

                    treeDataUrl: factoryTreeAction,
                    treeRootId: '1010103',
                    treeRootText: '设备厂家',
                    treeChkMode: '',
                    treeChkType: 'dict',
                    showChkFldId: '${sheetPageName}showFactory',
                    saveChkFldId: '${sheetPageName}mainmanufacturer',
                    viewer: factoryViewer
                });
            </script>
            <textarea class="textarea max" readonly="true"
                      name="${sheetPageName}showFactory" style="height:50px"
                      id="${sheetPageName}showFactory"
                      alt="allowBlank:false,maxLength:250,vtext:'请填入设备厂家，最多输入125个汉字'"><c:forTokens
                    items="${sheetMain.mainmanufacturer}" delims=","
                    var="mainmanufacturer" varStatus="status">
                <eoms:id2nameDB id="${mainmanufacturer}"
                                beanId="ItawSystemDictTypeDao"/>
                <c:choose>
                    <c:when test="${status.last}"></c:when>
                    <c:otherwise>,</c:otherwise>
                </c:choose>
            </c:forTokens></textarea> <input type="hidden" name="${sheetPageName}mainmanufacturer"
                                             id="${sheetPageName}mainmanufacturer"
                                             value="${sheetMain.mainmanufacturer}"/></td>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainnetsystem"/></td>
        <td><eoms:comboBox name="mainnetsystem" id="mainnetsystem"
                           initDicId="1011502" alt="allowBlank:false"></eoms:comboBox></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainsoftwaretype"/></td>
        <td><eoms:comboBox name="mainsoftwaretype" id="mainsoftwaretype"
                           initDicId="1011503" alt="allowBlank:false" defaultValue="101150301"
                           onchange="onTdChange(this)"></eoms:comboBox></td>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainisnetwork"/></td>
        <td><eoms:comboBox name="mainisnetwork" id="mainisnetwork"
                           initDicId="1011504" alt="allowBlank:false"></eoms:comboBox></td>
    </tr>
    <tr id="mainSoftwareversionLabel" style="display: ">
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainoldedition"/></td>
        <td><input type="text" class="text" name="mainoldedition"
                   id="mainoldedition" value=""/></td>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainNewedition"/></td>
        <td><input type="text" class="text" name="mainNewedition"
                   id="mainNewedition" value=""/></td>
    </tr>
    <tr id="mainSoftwareNoLabel" style="display: none;">
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainmendedition"/></td>
        <td><input type="text" class="text" name="mainmendedition"
                   id="mainmendedition" value=""/></td>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainnewmend"/></td>
        <td><input type="text" class="text" name="mainnewmend"
                   id="mainnewmend" value=""/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.maintesttime"/></td>
        <td><input type="text" class="text" name="maintesttime"
                   id="maintesttime" readonly="readonly"
                   value="${eoms:date2String(sheetMain.sendTime)}"
                   onclick="popUpCalendar(this, this)" alt="allowBlank:false"/></td>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.maintestadd"/></td>
        <td><input type="text" class="text" name="maintestadd"
                   id="maintestadd" value="" alt="allowBlank:false"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainstartconfertime"/></td>
        <td><input type="text" class="text" name="mainstartconfertime"
                   id="mainstartconfertime"
                   value="${eoms:date2String(sheetMain.sendTime)}"
                   onclick="popUpCalendar(this, this)" alt="allowBlank:false"/></td>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainendconfertime"/></td>
        <td><input type="text" class="text" name="mainendconfertime"
                   id="mainendconfertime"
                   value="${eoms:date2String(sheetMain.sendTime)}"
                   onclick="popUpCalendar(this, this)" alt="allowBlank:false"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainistest"/></td>
        <td><eoms:comboBox name="mainistest" id="mainistest"
                           initDicId="10301" alt="allowBlank:false"></eoms:comboBox></td>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.maininstancy"/></td>
        <td><eoms:comboBox name="maininstancy" id="maininstancy"
                           initDicId="10302" alt="allowBlank:false"></eoms:comboBox></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainisimbark"/></td>
        <td><eoms:comboBox name="mainisimbark" id="mainisimbark"
                           initDicId="10301" alt="allowBlank:false"
                           onchange="ifimbark(this.value)"></eoms:comboBox></td>
    </tr>
    <tr id="imbark" style="display:none">
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainintendingtime"/></td>
        <td><input type="text" class="text" name="mainintendingtime"
                   id="mainintendingtime"
                   value="${eoms:date2String(sheetMain.sendTime)}"
                   onclick="popUpCalendar(this, this)" alt="allowBlank:false"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainishardware"/></td>
        <td><eoms:comboBox name="mainishardware" id="mainishardware"
                           initDicId="10301" alt="allowBlank:false"></eoms:comboBox></td>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainissecurity"/></td>
        <td><eoms:comboBox name="mainissecurity" id="mainissecurity"
                           initDicId="10301" alt="allowBlank:false"></eoms:comboBox></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainisnetchange"/></td>
        <td><eoms:comboBox name="mainisnetchange" id="mainisnetchange"
                           initDicId="10301" alt="allowBlank:false"></eoms:comboBox></td>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainnetcriterion"/></td>
        <td><eoms:comboBox name="mainnetcriterion" id="mainnetcriterion"
                           initDicId="10301" alt="allowBlank:false"></eoms:comboBox></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainsystemtext"/></td>
        <td colspan="3"><textarea name="mainsystemtext"
                                  id="mainsystemtext" class="textarea max"
                                  alt="allowBlank:ture,width:500,maxLength:1000,vtext:'最多输入1000汉字'"></textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.maincompatibility"/></td>
        <td colspan="3"><textarea name="maincompatibility"
                                  id="maincompatibility" class="textarea max"
                                  alt="allowBlank:ture,width:500,maxLength:1000,vtext:'最多输入1000汉字'"></textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.maintext"/></td>
        <td colspan="3"><textarea name="maintext" id="maintext"
                                  class="textarea max"
                                  alt="allowBlank:ture,width:500,maxLength:1000,vtext:'最多输入1000汉字'"></textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainsoftwarechargeinfo"/></td>
        <td colspan="3"><textarea name="mainsoftwarechargeinfo"
                                  id="mainsoftwarechargeinfo" class="textarea max"
                                  alt="allowBlank:ture,width:500,maxLength:1000,vtext:'最多输入1000汉字'"></textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.maintestuser"/></td>
        <td colspan="3"><textarea name="maintestuser" id="maintestuser"
                                  class="textarea max"
                                  alt="allowBlank:ture,width:500,maxLength:1000,vtext:'最多输入1000汉字'"></textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="softwareApply"
                                        key="softwareApply.mainaccessoriesinfo"/></td>
        <td colspan="3"><textarea name="mainaccessoriesinfo"
                                  id="mainaccessoriesinfo" class="textarea max"
                                  alt="allowBlank:ture,width:500,maxLength:1000,vtext:'最多输入1000汉字'"></textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet"
                                        key="tawSheetAccessForm.access"/></td>
        <td colspan="3"><eoms:attachment name="tawSheetAccess"
                                         property="accesss" scope="request" idField="accesss"
                                         appCode="toolaccess" viewFlag="Y"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet"
                                        key="mainForm.accessories"/></td>
        <td colspan="3"><eoms:attachment name="sheetMain"
                                         property="sheetAccessories" scope="request"
                                         idField="${sheetPageName}sheetAccessories" appCode="commontask"/></td>
    </tr>
</table>
<c:if test="${status!=1}">
    <fieldset id="link1">
        <legend><bean:message
                bundle="sheet" key="mainForm.toOperateRoleName"/> <span
                id="roleName">:号段审核人 </span></legend>
        <div class="x-form-item"><eoms:chooser id="test"
                                               config="{returnJSON:true,showLeader:true}"
                                               category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                                               panels="[{text:'部门与人员',dataUrl:'/xtree.do?method=userFromDept'},{text:'个性化部门树',dataUrl:'/sheet/userdefinegroup/userdefinegroup.do?method=showTree&flowId=1'}]"
                                               data="${sendUserAndRoles}"/></div>
    </fieldset>
</c:if>
<script type="text/javascript">
    function ifimbark(pretreatment) {
        if (pretreatment == '1030101') {
            document.getElementById('imbark').style.display = '';
        } else {
            document.getElementById('imbark').style.display = 'none';
        }
    }

    function onTdChange(tx) {
        var value = tx.value;

        if (value == '101150301') {
            document.getElementById('mainSoftwareNoLabel').style.display = 'none';
            document.getElementById('mainSoftwareversionLabel').style.display = '';
        } else {
            document.getElementById('mainSoftwareNoLabel').style.display = '';
            document.getElementById('mainSoftwareversionLabel').style.display = 'none';
        }
    }
</script>
