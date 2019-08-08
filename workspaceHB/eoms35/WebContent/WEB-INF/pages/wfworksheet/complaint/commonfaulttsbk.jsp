<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    <%@page import="java.util.*" %>
    <%@page import="com.boco.eoms.base.util.StaticMethod" %>
    <%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

    function initPage() {
        v = new eoms.form.Validation({form: 'theform'});
    }

    Ext.onReady(function () {

    });

    function test() {
        var sheetAccessories = document.getElementById("sheetAccessories").value;
        if (sheetAccessories == null || sheetAccessories == '') {

            alert("请先上传附件后在保存！");
            return false;
        } else {
            var len = sheetAccessories.length;
            if (len > 25) {
                alert("只能上传一个附件！");
                return false;
            }

        }
    }
</script>


<div id="sheetform">
    <html:form action="/complaint.do?method=saveAccessories" styleId="theform">

        <table class="listTable">
            <%
                List retList = (List) request.getAttribute("retList");
                if (retList != null && retList.size() > 0) {
                    for (int i = 0; i < retList.size(); i++) {
                        String id = StaticMethod.nullObject2String(((Map) retList.get(i)).get("id"));
                        String accessoriesCnName = StaticMethod.nullObject2String(((Map) retList.get(i)).get("accessoriesCnName"));

            %>
            <tr>
                <td class="label">投诉百科附件下载</td>
                <td colspan="3">
                    <a href="${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=<%=id%>"><%=accessoriesCnName %>
                    </a>
                </td>
            </tr>
            <%
                    }
                } %>
            <tr>
                <td class="label">上传投诉百科附件</td>
                <td colspan="3">
                    <eoms:attachment name="sheetMain" property="sheetAccessories" scope="request"
                                     idField="sheetAccessories" appCode="complaint"/>
                </td>
            </tr>
        </table>
        <div class="form-btns">
            <html:submit styleClass="btn" property="method.save" styleId="method.save"
                         onclick="javascript:return test()">
                保存
            </html:submit>
        </div>
    </html:form>

</div>
<%@ include file="/common/footer_eoms.jsp" %>