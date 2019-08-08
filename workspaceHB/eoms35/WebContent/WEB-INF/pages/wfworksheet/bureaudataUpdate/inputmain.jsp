<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="java.util.ArrayList" %>
<logic:present name="BureaudataHlr" scope="request">
    <bean:define id="hlrList" name="BureaudataHlr"/>
    <%

        request.setAttribute("roleId", "4020");

        long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
        java.util.ArrayList hlrListTmp = (java.util.ArrayList) hlrList;
    %>

    <script language="javascript">
        var g_hrlname = '<span class="prettyShow" style="width:180px;"><select name="hlrSignalid"><option value="">---请选择---</option><%for (int i = 0;i<hlrListTmp.size();i++){com.boco.eoms.datum.model.TawBureaudataHlr hlr = (com.boco.eoms.datum.model.TawBureaudataHlr)hlrListTmp.get(i);%><option value="<%=hlr.getHlrsignalid()%>"><%=hlr.getHlrname()%>（<%=hlr.getHlrsignalid()%>）（<%=hlr.getHlrid()%>）</option><%}%></select></span>';

        function getUnbelongsegment() {
            var url = "bureaudataUpdate.do?method=showHlrId&sheetKey=${sheetMain.id}";
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
            } else {
                hText = '<table class="formTable">';
                for (var i = 0; i < segmentArr.length; i++) {
                    if (segmentArr[i] != '') {
                        var arrTmp = segmentArr[i].split(',');
                        var addBtnHtml = '';
                        if (arrTmp[0] != arrTmp[1]) {
                            addBtnHtml = '&nbsp;&nbsp;<input type="button" value="拆分号段" class="btn" onclick="splitSegments(this.parentNode.parentNode)"/>';
                        }
                        var addBtnHtmlT = '&nbsp;&nbsp;<input type="button" value="增加行" class="btn" onclick="splitSegment(this.parentNode.parentNode)"/>';
                        var delBtnHtml = '&nbsp;&nbsp;<input type="button" value="删除号段" class="btn" onclick="deleteSegment(this.parentNode.parentNode)"/>';
                        var all = document.getElementsByTagName("*")
                        var tr = all.segTab.insertRow(1);
                        var td = tr.insertCell();
                        td.innerHTML = '<td class= "label" width="8%">启始号段</td>';
                        tr.insertCell().innerHTML = '<td width="15%"><input type="text" size="11" maxlength="10" readonly="readonly" limit="^[0-9]*$" class="readonlyText" name="beginsegment" value="' + arrTmp[0] + '"/></td>';
                        tr.insertCell().innerHTML = '<td class="label" width="8%">终止号段</td>';
                        tr.insertCell().innerHTML = '<td width="15%"><input type="text" size="11" maxlength="10" readonly="readonly" limit="^[0-9]*$" class="readonlyText" name="endsegment" value="' + arrTmp[1] + '"/></td>';
                        tr.insertCell().innerHTML = '<td class="label" width="8%">HLR名称</td>';
                        tr.insertCell().innerHTML = '<td nowrap="nowrap">' + g_hrlname + '</td>';
                        tr.insertCell().innerHTML = '<td>' + addBtnHtmlT + addBtnHtml + delBtnHtml + '</td></tr>';
                    }
                }
            }
            showDiv.innerHTML = hText;
        }

        function initSegMap() {
            var beginsegArr = document.getElementsByName('preBeginSegment');
            var endsegArr = document.getElementsByName('preEndSegment');
            var preSegHlrIdArr = document.getElementsByName('preSegHlrId');
            for (var i = 0; i < beginsegArr.length; i++) {
                var endSegTmp = endsegArr[i].value - 0;
                for (var j = (beginsegArr[i].value - 0); j <= endSegTmp; j++) {
                    g_segMap[j] = preSegHlrIdArr[i].value.substring(0, 7);
                }
            }
        }

        function splitSegment(trObj) {
            var addBtnHtml = '&nbsp;&nbsp;<input type="button" value="增加行" class="btn" onclick="splitSegment(this.parentNode.parentNode)"/>';
            var delBtnHtml = '&nbsp;&nbsp;<input type="button" value="删除行" class="btn" onclick="deleteSegment(this.parentNode.parentNode)"/>';

            var index = 1;
            if (trObj != "") {
                index = trObj.rowIndex;
            }

            var all = document.getElementsByTagName("*")
            var tr = all.segTab.insertRow(index + 1);
            var td = tr.insertCell();
            td.innerHTML = '<td class= "label">启始号段</td>';
            tr.insertCell().innerHTML = '<td><input type="text" size="11" maxlength="10" name="beginsegment" limit="^[0-9]*$" class="limitinput"/></td>';
            tr.insertCell().innerHTML = '<td class="label">终止号段</td>';
            tr.insertCell().innerHTML = '<td><input type="text" size="11" maxlength="10" name="endsegment" limit="^[0-9]*$"  class="limitinput"/></td>';
            tr.insertCell().innerHTML = '<td class="label">HLR名称</td>';
            tr.insertCell().innerHTML = '<td>' + g_hrlname + '</td>';
            tr.insertCell().innerHTML = '<td>' + addBtnHtml + delBtnHtml + '</td>'
        }

        function splitSegments(trObj) {
            var inputArr = trObj.getElementsByTagName("input");
            var endsegValue = inputArr[1].value;
            var flagStr = (inputArr[1].readOnly) ? 'readonly="readonly" class="readonlyText"' : 'class="limitinput"';
            inputArr[1].value = '';
            inputArr[1].readOnly = false;
            inputArr[1].className = 'limitinput';
            var addBtnHtml = '&nbsp;&nbsp;<input type="button" value="增加行" class="btn" onclick="splitSegment(this.parentNode.parentNode)"/>';
            var updateBtnHtml = '&nbsp;&nbsp;<input type="button" value="拆分号段" class="btn" onclick="splitSegments(this.parentNode.parentNode)"/>';
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
            tr.insertCell().innerHTML = '<td>' + addBtnHtml + updateBtnHtml + delBtnHtml + '</td>'
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

        //显示/隐藏本部门所属号段
        function showPreSegBody() {
            var preSegBody = document.getElementById('preSegBody');
            var signSpan = document.getElementById('signSpan');
            if (preSegBody.style.display != 'none') {
                preSegBody.style.display = 'none';
                signSpan.innerText = '\u25BC';
            } else {
                preSegBody.style.display = 'block';
                signSpan.innerText = '▲';
            }
        }
    </script>


    <%@ include file="/WEB-INF/pages/wfworksheet/bureaudataUpdate/baseinputmainhtmlnew.jsp" %>
    <input type="hidden" name="${sheetPageName}processTemplateName" value="BureaudataUpdateProcess"/>
    <input type="hidden" name="${sheetPageName}sheetTemplateName" value="BureaudataUpdateProcess"/>
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
    <input type="hidden" name="${sheetPageName}beanId" value="iBureaudataUpdateMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.bureaudataUpdate.model.BureaudataUpdateMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.bureaudataUpdate.model.BureaudataUpdateLink"/>
    <br>
    <fieldset id="link1">
    <legend>
			 <span id="roleName">号段信息:
			 </span>
    </legend>
    <%
        ArrayList baseList = (ArrayList) request.getAttribute("BureaudataBasic");
        if (baseList != null && baseList.size() > 0) {
    %>
    <div id="showDiv"></div>

    <table class="formTable" id="segTab">
        <tr>
            <td>启始号段</td>
            <td><input type="text" size="11" maxlength="10" class="limitinput" limit="^[0-9]*$" name="beginsegment"/>
            </td>
            <td>终止号段</td>
            <td><input type="text" size="11" maxlength="10" class="limitinput" limit="^[0-9]*$" name="endsegment"/></td>
            <td>HLR名称</td>
            <td><span class="prettyShow">
	             <select name="hlrSignalid">
	               <option value="">---请选择---</option>
	               <logic:present name="BureaudataHlr" scope="request">
                       <logic:iterate id="hlr" name="BureaudataHlr" type="com.boco.eoms.datum.model.TawBureaudataHlr">
                           <option value="<%=hlr.getHlrsignalid()%>"><%=hlr.getHlrname()%>（<%=hlr.getHlrsignalid()%>）（<%=hlr.getHlrid()%>）</option>
                       </logic:iterate>
                   </logic:present>
	             </select>
	         </span></td>
            <td>&nbsp;&nbsp;<input type="button" value="增加行" class="btn"
                                   onclick="splitSegment(this.parentNode.parentNode)"/>
                &nbsp;&nbsp;<input type="button" value="删除行" class="btn"
                                   onclick="deleteSegment(this.parentNode.parentNode)"/>
            </td>
        </tr>
    </table>
    <%} %>
</logic:present>
<%
    ArrayList baseList = (ArrayList) request.getAttribute("BureaudataBasic");
    if (baseList != null && baseList.size() > 0) {
%>
<table class="formTable">

    <tr>
        <td colspan="5" style="font-size:13px;text-align:center;cursor:hand;"
            onclick="showPreSegBody();" title="点击折起/展开号段详情信息">本部门所负责的地市已归属的号段<span id="signSpan">▲</span></td>
    </tr>
    <tbody id="preSegBody" style="display:block;">

    <tr>
        <td class="label" style="text-align:center">启始号段</td>
        <td class="label" style="text-align:center">终止号段</td>
        <td class="label" style="text-align:center">HLR名称</td>
        <td class="label" style="text-align:center">HLR信令点</td>
        <td class="label" style="text-align:center">HLR编号</td>
    </tr>
    <%

        for (int i = 0; i < baseList.size(); i++) {
            com.boco.eoms.datum.vo.TawBureaudataSegmenthlrVO bas = (com.boco.eoms.datum.vo.TawBureaudataSegmenthlrVO) baseList.get(i);
    %>
    <tr>
        <td align="center"><%=bas.getBeginSegment() %>
        </td>
        <td align="center"><%=bas.getEndSegment() %>
        </td>
        <td align="center"><%=bas.getHlrName() %>
        </td>
        <td align="center"><%=bas.getHlrSignalId() %>
        </td>
        <td align="center"><%=bas.getHlrId() %>
        </td>
    </tr>
    <%
        }

    %>
    </tbody>
</table>
<%
} else {
%>
<span style='font-size:14px;color:red;font-weight:bold'>本部门所负责的地市无有归属的号段!</span>
<%


    }
%>
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
    initSegMap();
</script>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    if (taskName.equals("DraftTask") || taskName.equals("RejectTask")) {
%>
<script type="text/javascript">
    getUnbelongsegment();

</script>


<%
    }
%>



