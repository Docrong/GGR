<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    var frmReg;
    Ext.onReady(function () {
        frmReg = new eoms.form.Validation({form: 'newFormPage'});
    });

    function returnlist() {
        var sheetKey = window.parent.document.getElementById("sheetKey").value;
        var frame1 = window.parent.document.getElementById("frame14");
        frame1.style.display = "block";
        frame1.src = "${app}/sheet/numberapply/numberapply.do?method=performManual&sheetKey=" + sheetKey + "&actionForword=commond14";
    }

</script>
<logic:notEmpty name="TawPartFourteenSignal">
    14位信令点
    <form name="newFormPage" method="POST" id="newFormPage" action="numberapply.do?method=showQuicQuery">
        <table class="listTable taskList">
            <input type="hidden" name="actionForword" id="actionForword" value="commond14"/>
            <input type="hidden" name="modelname" id="modelname" value="TawPartFourteenSignal"/>
            <tr>
                <td class="label">14位信令点</td>
                <td>
                    <input type="text" name="signalvalue" id="signalvalue" value=""/>
                </td>
                <td class="label">信令编号</td>
                <td>
                    <input type="text" name="signalnum" id="signalnum" value=""/>
                </td>
                <td class="label">用户名id</td>
                <td>
                    <input type="text" name="userid" id="userid" value=""/>
                </td>
                <td>
                    <input type="submit" class="submit" value="查询"/>
                </td>
                <td>
                    <input type="button" class="button" value="返回列表" onclick="returnlist()"/>
                </td>
            </tr>
        </table>
    </form>
    <display:table name="TawPartFourteenSignal" cellspacing="0" cellpadding="0"
                   id="TawPartFourteenSignal" pagesize="${pageSize}" class="listTable taskList"
                   export="false" sort="list" size="TawPartFourteenSignalsize" partialList="true"
                   requestURI="numberapply.do">
        <display:column headerClass="sortable" title="">
            <input type="radio" name="radio14" id="radio14" value="${TawPartFourteenSignal.id}"/>
            <input type="hidden" name="radio14Value" id="radio14Value" value="${TawPartFourteenSignal.signalvalue}"/>

        </display:column>
        <display:column property="signalvalue" sortable="true" headerClass="sortable" title="14位信令点"/>
        <display:column property="signalnum" sortable="true" headerClass="sortable" title="信令编号"/>
        <display:column property="userid" sortable="true" headerClass="sortable" title="用户名id"/>
        <display:column property="updatedate" sortable="true" headerClass="sortable" title="更新时间"/>

    </display:table>
</logic:notEmpty>

<c:if test="${TawPartFourteenSignalsize==0 }">
    14位信令点，没有相关记录！
    <input type="button" class="button" value="返回列表" onclick="returnlist()"/>
</c:if>


<%@ include file="/common/footer_eoms.jsp" %>
