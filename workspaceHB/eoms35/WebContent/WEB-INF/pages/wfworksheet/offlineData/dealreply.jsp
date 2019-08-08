<%@ include file="/common/taglibs.jsp" %>
<style type="text/css">
    .x-form-column {
        width: 320px
    }
</style>
<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
    function selfx() {
        var put = document.getElementsByName("${sheetPageName}deal");
        for (i = 0; i < put.length; i++) {
            put[i].checked = (put[i].checked) ? false : true;
        }
    }

    function selfcheckall() {
        var put1 = document.getElementsByName("${sheetPageName}checkall");
        var put2 = document.getElementsByName("${sheetPageName}checkbackall");
        if (put1[0].checked == true) {
            put2[0].checked = false;
        } else {
            put2[0].checked = true;
        }
    }

    function selfcheckbackall() {
        var put1 = document.getElementsByName("${sheetPageName}checkall");
        var put2 = document.getElementsByName("${sheetPageName}checkbackall");
        if (put2[0].checked == true) {
            put1[0].checked = false;
        } else {
            put1[0].checked = true;
        }
    }

    function check() {
        var put1 = document.getElementsByName("${sheetPageName}checkall");
        var put2 = document.getElementsByName("${sheetPageName}checkbackall");
        if (put1[0].checked == true) {
            put1[0].checked = false;
        }
        if (put2[0].checked == true) {
            put2[0].checked = false;
        }

    }

    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'theform'});
        v.custom = function () {
            var checkArray = document.getElementsByName('${sheetPageName}deal');
            var _toid = document.getElementsByName('${sheetPageName}toid');
            var _toids = ",";
            var i = 0;
            for (var c0 = 0; c0 < checkArray.length; c0++) {
                if (checkArray[c0].checked) {
                    i = 1;
                    _toids = _toids + _toid[c0].value + ",";
                }
            }
            if (i == 1) {
                $('${sheetPageName}taskIds').value = _toids.substring(1, _toids.length - 1);
            } else {
                alert("请选择需要处理的对象");
                return false;
            }

            return true;
        };
    });
</script>


<html:form action="/offlineData.do?method=batchPerformDeal" styleId="theform">
    <%@ include file="/WEB-INF/pages/wfworksheet/offlineData/baseinputlinkhtmlnew.jsp" %>
    <input type="hidden" name="${sheetPageName}beanId" value="iOfflineDataMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.offlineData.model.OfflineDataMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.offlineData.model.OfflineDataLink"/>
    <input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName"
           value="OfflineDataMainFlowProcess"/>
    <input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate"/>
    <input type="hidden" name="${sheetPageName}taskIds" id="${sheetPageName}taskIds" value=""/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}"/>
    <input type="hidden" name="${sheetPageName}toOrgRoleId" id="${sheetPageName}toOrgRoleId"
           value="${toTask.taskOwner}"/>
    <table class="formTable">
        <tr>
            <td class="label"><bean:message bundle="sheet" key="phase.pleaseSelect"/></br>
                <input type="checkbox" name="${sheetPageName}checkall" id="${sheetPageName}checkall"
                       value="${toTask.taskOwner}"
                       onclick="selfcheckall();eoms.util.checkAll(this, '${sheetPageName}deal')">
                全选</br>
                <input type="checkbox" name="${sheetPageName}checkbackall" id="${sheetPageName}checkbackall"
                       value="${toTask.taskOwner}" onclick="selfcheckbackall();selfx()">
                反选

            </td>
            <td colspan="3">
                <table class="listTable">
                    <logic:present name="mapList" scope="request">
                        <tr>
                            <td class="label">
                                上一步处理人
                            </td>
                            <td class="label">
                                上一步处理部门
                            </td>
                            <td class="label">
                                完成情况
                            </td>
                        </tr>
                        <logic:iterate id="toTask" name="mapList" type="java.util.HashMap">
                            <tr>
                                <td>
                                    <input type="checkbox" name="${sheetPageName}deal" id="${sheetPageName}deal"
                                           value="${toTask.operateUserId}" onclick="check()">
                                    <eoms:id2nameDB id="${toTask.operateUserId}"
                                                    beanId="tawSystemDeptDao"/><eoms:id2nameDB
                                        id="${toTask.operateUserId}" beanId="tawSystemUserDao"/>&nbsp;
                                    <input type="hidden" name="${sheetPageName}toid" id="${sheetPageName}toid"
                                           value="${toTask.id}"/>
                                </td>
                                <td>
                                    <eoms:id2nameDB id="${toTask.operateDeptId}"
                                                    beanId="tawSystemDeptDao"/><eoms:id2nameDB
                                        id="${toTask.operateDeptId}" beanId="tawSystemUserDao"/>&nbsp;
                                </td>
                                <td>
                                    <bean:write name="toTask" property="linkTaskComplete" scope="page"/>
                                </td>
                            </tr>
                        </logic:iterate>
                    </logic:present>
                </table>
            </td>
        </tr>
        <c:if test="${operateType=='211'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
            <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
                   value="true"/>
        </c:if>
        <c:if test="${operateType=='212'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
        </c:if>
        <tr>
            <td class="label"><bean:message bundle="offlineData" key="offlineData.advice"/>*</td>
            <td colspan="3">
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete"
                                id="${sheetPageName}linkTaskComplete"
                                alt="width:500,allowBlank:false,maxLength:2000,vtext:'请输入建议，最多输入1000汉字'">${sheetLink.linkTaskComplete}</textarea>
            </td>
        </tr>
    </table>
    <div class="x-form-item">
        <div class="form-btns">
            <html:submit styleClass="btn" property="method.save" styleId="method.save">
                <fmt:message key="button.done"/>
            </html:submit></div>
    </div>

</html:form>

          