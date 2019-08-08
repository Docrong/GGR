<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="java.util.ArrayList" %>
<logic:present name="BureaudataHlr" scope="request">
    <bean:define id="hlrList" name="BureaudataHlr"/>
    <%

        request.setAttribute("roleId", "249");

        long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
        java.util.ArrayList hlrListTmp = (java.util.ArrayList) hlrList;
    %>
    <%@ include file="/WEB-INF/pages/wfworksheet/bureaudataSave/baseinputmainhtmlnew.jsp" %>
    <input type="hidden" name="${sheetPageName}processTemplateName" value="BureaudataSaveProcesses"/>
    <input type="hidden" name="${sheetPageName}sheetTemplateName" value="BureaudataSaveProcesses"/>
    <input type="hidden" name="${sheetPageName}operateName" value="newWorksheet"/>
    <c:if test="${status!=1}">
        <input type="hidden" name="${sheetPageName}taskName" id="${sheetPageName}taskName" value="${taskName }"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Assessor"/>
        <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType"
               value="${operateType}"/>
    </c:if>
    <c:if test="${status==1}">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iBureaudataSaveMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.bureaudataSave.model.BureaudataSaveMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.bureaudataSave.model.BureaudataSaveLink"/>
    <br>
    <fieldset id="link1">
        <legend>
			 <span id="roleName">号段信息：
			 </span>
        </legend>
        <div id="showDiv"></div>
    </fieldset>
    <c:if test="${status!=1}">
        <fieldset id="link1">
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
                <span id="roleName">:号段审核人
			 </span>
            </legend>
            <div class="x-form-item">
                <div id="test">
                    <div class="viewer-list" id="test-chooser-show">
                        <div>派发:
                            <div style="display: inline;" class="viewlistitem-dept">黄芬</div>
                        </div>
                    </div>
                    <input type="hidden" name="dealPerformer" id="ext-gen135" value="huangfen">
                    <input type="hidden" name="dealPerformerType" id="ext-gen136" value="user">
                    <input type="hidden" name="dealPerformerLeader" id="ext-gen137" value="huangfen">
                </div>
            </div>
        </fieldset>
    </c:if>

    <script type="text/javascript">
        var g_hrlname = '<span class="prettyShow" style="width:180px;"><select name="hlrSignalid"><option value="">---请选择---</option><%for (int i = 0;i<hlrListTmp.size();i++){com.boco.eoms.datum.model.TawBureaudataHlr hlr = (com.boco.eoms.datum.model.TawBureaudataHlr)hlrListTmp.get(i);%><option value="<%=hlr.getHlrsignalid()%>"><%=hlr.getHlrname()%>（<%=hlr.getHlrsignalid()%>）（<%=hlr.getHlrid()%>）</option><%}%></select></span>';

        function getUnbelongsegment() {
            var url = "bureaudataSave.do?method=showHlrId&sheetKey=${sheetMain.id}";
            Ext.onReady(function () {
                Ext.Ajax.request({
                    form: "",
                    method: "post",
                    url: url,
                    success: function (v, c) {
                        showUnbelongsegment(v);
                    }
                });
            });


        }

        function showUnbelongsegment(http_response) {
            var returnValue = http_response.responseText;
            var segmentArr = returnValue.split('|');
            var showDivshowDiv = $('showDiv');
            var hText = '';
            if (returnValue == '' || segmentArr[0].split(',').length != 2) {
                hText = "<span style='font-size:14px;color:red;font-weight:bold'>本部门所负责的地市无未归属的号段!</span>";
            } else {
                hText = '<table class="formTable" id="segTab">';
                for (var i = 0; i < segmentArr.length; i++) {
                    if (segmentArr[i] != '') {
                        var arrTmp = segmentArr[i].split(',');
                        var addBtnHtml = '';
                        if (arrTmp[0] != arrTmp[1]) {
                            addBtnHtml = '&nbsp;&nbsp;<input type="button" value="拆分号段" class="btn" onclick="splitSegment(this.parentNode.parentNode)"/>';
                        }
                        var delBtnHtml = '&nbsp;&nbsp;<input type="button" value="删除号段" class="btn" onclick="deleteSegment(this.parentNode.parentNode)"/>';
                        hText = hText + '<tr class="tr_show">' +
                            '<td class= "label" width="8%">启始号段</td>' +
                            '<td width="15%"><input type="text" size="11" maxlength="10" readonly="readonly" limit="^[0-9]*$" class="readonlyText" name="beginsegment" value="' + arrTmp[0] + '"/></td>' +
                            '<td class="label" width="8%">终止号段</td>' +
                            '<td width="15%"><input type="text" size="11" maxlength="10" readonly="readonly" limit="^[0-9]*$" class="readonlyText" name="endsegment" value="' + arrTmp[1] + '"/></td>' +
                            '<td class="label" width="8%">HLR名称</td>' +
                            '<td nowrap="nowrap">' + g_hrlname + '</td>' +
                            '<td>' + addBtnHtml + delBtnHtml + '</td></tr>';
                    }
                }
                hText = hText + '</table>';
            }
            showDiv.innerHTML = hText;
        }

        function splitSegment(trObj) {
            var inputArr = trObj.getElementsByTagName("input");
            var endsegValue = inputArr[1].value;
            var flagStr = (inputArr[1].readOnly) ? 'readonly="readonly" class="readonlyText"' : 'class="limitinput"';
            inputArr[1].value = '';
            inputArr[1].readOnly = false;
            inputArr[1].className = 'limitinput';
            var addBtnHtml = '&nbsp;&nbsp;<input type="button" value="拆分号段" class="btn" onclick="splitSegment(this.parentNode.parentNode)"/>';
            var delBtnHtml = '&nbsp;&nbsp;<input type="button" value="删除号段" class="btn" onclick="deleteSegment(this.parentNode.parentNode)"/>';

            var index = trObj.rowIndex;
            var all = document.getElementsByTagName("*")
            var tr = all.segTab.insertRow(index + 1);
            var td = tr.insertCell();
            td.innerHTML = '<td class= "label">启始号段</td>';
            tr.insertCell().innerHTML = '<td><input type="text" size="11" maxlength="10" name="beginsegment" limit="^[0-9]*$" class="limitinput"/></td>';
            tr.insertCell().innerHTML = '<td class="label">终止号段</td>';
            tr.insertCell().innerHTML = '<td><input type="text" size="11" maxlength="10" name="endsegment" limit="^[0-9]*$"' + flagStr + ' value="' + endsegValue + '"/></td>';
            tr.insertCell().innerHTML = '<td class="label">HLR名称</td>';
            tr.insertCell().innerHTML = '<td>' + g_hrlname + '</td>';
            tr.insertCell().innerHTML = '<td>' + addBtnHtml + delBtnHtml + '</td>'
        }

        function deleteSegment(trObj) {
            var inputArr = trObj.getElementsByTagName("input");
            if ((!inputArr[0].readOnly) && inputArr[1].readOnly) {
                var preInputArr = trObj.previousSibling.getElementsByTagName("input");
                preInputArr[1].value = inputArr[1].value;
                preInputArr[1].readOnly = true;
                preInputArr[1].mergeAttributes(inputArr[1], false);
            } else if (inputArr[0].readOnly && (!inputArr[1].readOnly)) {
                var preInputArr = trObj.nextSibling.getElementsByTagName("input");
                preInputArr[0].value = inputArr[0].value;
                preInputArr[0].readOnly = true;
                preInputArr[0].mergeAttributes(inputArr[0], false);
            }
            trObj.parentNode.removeChild(trObj);
        }

        function checkSegment() {
            var beginsegArr = document.getElementsByName('beginsegment');
            var endsegArr = document.getElementsByName('endsegment');
            var hlrIdArr = document.getElementsByName('hlrSignalid');
            if (beginsegArr.length == 0) {
                alert("无未归属的号段，无法申请工单！");
                return false;
            }
            var endSegTmp = 0;
            for (var i = 0; i < beginsegArr.length; i++) {
                if (beginsegArr[i].value == '') {
                    alert('启始号段不能为空，请输入！');
                    beginsegArr[i].focus();
                    return false;
                }
                if (endsegArr[i].value == '') {
                    alert('终止号段不能为空，请输入！');
                    endsegArr[i].focus();
                    return false;
                }
                if (hlrIdArr[i].value == '') {
                    alert('请选择HLR信令点！');
                    hlrIdArr[i].focus();
                    return false;
                }
                if ((beginsegArr[i].value - 0) > (endsegArr[i].value - 0)) {
                    alert('启始号段不能大于终止号段，请重新输入！');
                    if (beginsegArr[i].readOnly) {
                        endsegArr[i].select();
                        endsegArr[i].focus();
                    } else {
                        beginsegArr[i].select();
                        beginsegArr[i].focus();
                    }
                    return false;
                }
                if (!beginsegArr[i].readOnly) {
                    if ((beginsegArr[i].value - endSegTmp) != 1) {
                        alert('启始号段<' + beginsegArr[i].value + '>未与上一终止号段<' + endSegTmp + '>连续，请重新输入！');
                        beginsegArr[i].select();
                        beginsegArr[i].focus();
                        return false;
                    }
                }
                if (!endsegArr[i].readOnly) {
                    endSegTmp = endsegArr[i].value - 0;
                }
            }
            return true;
        }


        getUnbelongsegment();
    </script>
</logic:present>