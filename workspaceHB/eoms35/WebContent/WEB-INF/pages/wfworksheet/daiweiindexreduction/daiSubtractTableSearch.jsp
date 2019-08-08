<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    int index1 = 0;
    int index2 = 0;
    int index3 = 0;
%>
<script type="text/javascript">
    {
        var operateType = parent.document.getElementById("operateType"); // 获取父页面的元素
        var phaseId = parent.document.getElementById("phaseId");
        phaseId.value = 'RejectTask';
        operateType.value = '102'; // 初审不通过
    }

    function changePhase(obj) {
        var selectId = obj.getAttribute('id');
        var targetInput = document.getElementById('reserveD' + selectId.substr(8));
        if (obj.value == "1030101") {//是
            targetInput.value = '核减成立';
        } else {//否
            targetInput.value = '';
        }

        var operateType = parent.document.getElementById("operateType"); // 获取父页面的元素
        var phaseId = parent.document.getElementById("phaseId");
        var selectOptions = document.getElementsByName("reserveC");
        var flag = true;
        for (var i = 0; i < selectOptions.length; i++) {
            if (selectOptions[i].value != '1030101') {//是
                flag = false;
                break;
            }
        }
        if (flag == true) {
            phaseId.value = 'HoldTask';
            operateType.value = '101'; // 通过
        } else {
            phaseId.value = 'RejectTask';
            operateType.value = '102'; // 初审不通过
        }
    }

    Ext.onReady(function () {
        var listsize = '${total}';
        var noPass = '${noPass}';
        if (parent.document.getElementById("noPass") != null) {
            parent.document.getElementById("noPass").value = noPass; // 为否记录条数
        }
        if (parent.document.getElementById("listsize") != null) {
            parent.document.getElementById("listsize").value = listsize; // 总记录条数
        }
        var mainSubTypes = document.getElementsByName("mainSubType");
        if (mainSubTypes.length > 0) {
            var mainSubType = mainSubTypes[0].value;
            parent.selectRoleId(mainSubType);
        }
    });
</script>
<c:if test="${ifShowOther == 'yes' }">

    <display:table name="datasList" cellspacing="0" cellpadding="0"
                   id="datasList" pagesize="${pageSize}" class="listTable taskList"
                   export="false" requestURI="daiweiindexreduction.do"
                   sort="list" size="total" partialList="true"
                   decorator="">

        <display:column sortable="true" headerClass="sortable" title="核减理由是否成立">
            <c:choose>
                <c:when test="${datasList.reserveC=='1030101'}">
                    <select name="reserveC" id="reserveC<%=index1++ %>" onchange="changePhase(this);" style="width:70%"
                            disabled="disabled" alt="allowBlank:false">
                        <option value="1030102" <c:if
                                test="${datasList.reserveC=='1030102'}"> selected="selected" </c:if> >否
                        </option>
                        <option value="1030101"  <c:if
                                test="${datasList.reserveC=='1030101'}"> selected="selected" disabled="disabled" </c:if> >
                            是
                        </option>

                    </select>
                </c:when>
                <c:otherwise>
                    <select name="reserveC" id="reserveC<%=index1++ %>" onchange="changePhase(this);" style="width:70%"
                            alt="allowBlank:false">
                        <option value="1030102" <c:if
                                test="${datasList.reserveC=='1030102'}"> selected="selected" </c:if> >否
                        </option>
                        <option value="1030101">是</option>

                    </select>
                </c:otherwise>

            </c:choose>
            <input type="hidden" name="infoId" id="infoId" value="${datasList.id }"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="初审状态">
            <input type="text" class="text" id="reserveD<%=index2++ %>" name="reserveD"
                   alt="allowBlank:false" value="${datasList.reserveD }"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="核减专业">
            <eoms:id2nameDB id="${datasList.subtractProfessional }" beanId="ItawSystemDictTypeDao"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="核减理由类别">
            <eoms:id2nameDB id="${datasList.subtractrCategory }" beanId="ItawSystemDictTypeDao"/>
            <input name="mainSubType" id="mainSubType" value="${datasList.subtractrCategory }" type="hidden"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="核减状态">
            ${datasList.applicationReduction }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="工单流水号">
            ${datasList.serialId }
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="工单主题">
            ${datasList.title }
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="派单时间">
            ${datasList.mainDispatchTime }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="故障分类">
            ${datasList.mainFaultClass }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="故障历时(清除时间-发生时间)">
            ${datasList.mainFaultDurat }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="处理历时（恢复时间-第一次转单时间）">
            ${datasList.mainProDurat }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="故障发生时间">
            ${datasList.mainFaultFaTime }
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="告警平台清除时间">
            ${datasList.mainFaultQiTime }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="最终处理措施（T1，T2）">
            ${datasList.mainFinalMeasures }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="T2接单时间">
            ${datasList.mainT2Time }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="T2处理时限">
            ${datasList.mainT2Limit }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="T2最终处理时间">
            ${datasList.mainT2FinalTime }
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="最终处理地市">
            ${datasList.mainFinalCtiy }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="最终处理班组">
            ${datasList.mainFinalTeam }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="最终处理区县">
            ${datasList.mainFinalCounty }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="综合代维区县">
            ${datasList.mainDaiCounty }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="最终处理班组属性">
            ${datasList.mainFinalAttributes }
        </display:column>


    </display:table>
</c:if>
<c:if test="${ifShowOther == 'no'}">
    <display:table name="datasList" cellspacing="0" cellpadding="0"
                   id="datasList" pagesize="${pageSize}" class="listTable taskList"
                   export="false" requestURI="daiweiindexreduction.do"
                   sort="list" size="total" partialList="true"
                   decorator="">

        <c:if test="${ifSend == 'no'}">
            <c:if test="${!empty datasList.reserveC}">
                <display:column sortable="true" headerClass="sortable" title="核减理由是否成立">
                    <eoms:id2nameDB id="${datasList.reserveC }" beanId="ItawSystemDictTypeDao"/>
                </display:column>

                <display:column sortable="true" headerClass="sortable" title="初审状态">
                    ${datasList.reserveD }
                </display:column>
            </c:if>
        </c:if>
        <display:column sortable="true" headerClass="sortable" title="核减专业">
            <eoms:id2nameDB id="${datasList.subtractProfessional }" beanId="ItawSystemDictTypeDao"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="核减理由类别">
            <eoms:id2nameDB id="${datasList.subtractrCategory }" beanId="ItawSystemDictTypeDao"/>
            <input name="mainSubType" id="mainSubType" value="${datasList.subtractrCategory }" type="hidden"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="核减状态">
            ${datasList.applicationReduction }
        </display:column>

        <c:if test="${ifSeeHe == 'yes'}">
            <display:column sortable="true" headerClass="sortable" title="核减状态">
                ${datasList.reserveG }
            </display:column>
        </c:if>

        <display:column sortable="true" headerClass="sortable" title="工单流水号">
            ${datasList.serialId }
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="工单主题">
            ${datasList.title }
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="派单时间">
            ${datasList.mainDispatchTime }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="故障分类">
            ${datasList.mainFaultClass }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="故障历时(清除时间-发生时间)">
            ${datasList.mainFaultDurat }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="处理历时（恢复时间-第一次转单时间）">
            ${datasList.mainProDurat }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="故障发生时间">
            ${datasList.mainFaultFaTime }
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="告警平台清除时间">
            ${datasList.mainFaultQiTime }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="最终处理措施（T1，T2）">
            ${datasList.mainFinalMeasures }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="T2接单时间">
            ${datasList.mainT2Time }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="T2处理时限">
            ${datasList.mainT2Limit }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="T2最终处理时间">
            ${datasList.mainT2FinalTime }
        </display:column>

        <display:column sortable="true" headerClass="sortable" title="最终处理地市">
            ${datasList.mainFinalCtiy }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="最终处理班组">
            ${datasList.mainFinalTeam }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="最终处理区县">
            ${datasList.mainFinalCounty }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="综合代维区县">
            ${datasList.mainDaiCounty }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="最终处理班组属性">
            ${datasList.mainFinalAttributes }
        </display:column>


    </display:table>
</c:if>

<%@ include file="/common/footer_eoms.jsp" %>
