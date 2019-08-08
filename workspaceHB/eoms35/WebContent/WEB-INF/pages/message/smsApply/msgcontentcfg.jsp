<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<%@page import="java.util.*" %>
<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
<html:form action="/smsApplys.do?method=msgContentConfigure" method="post" styleId="">
    <table class="formTable">
        <caption>工单短信内容配置</caption>
        <tr>
            <td class="label">
                流程名：
            </td>
            <td>
                <eoms:comboBox name="flowName" id="flowName"
                               initDicId="1017702" alt="allowBlank:false" defaultValue="${flowId}"
                               styleClass="select-class"/>
            </td>
            <td class="label">
                提醒类型：
            </td>
            <td>
                <eoms:comboBox name="noticeType" id="noticeType"
                               initDicId="1017701" alt="allowBlank:false" defaultValue="${noticeType}"
                               styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <input type="submit" value="查询" class="submit"/>
            </td>
        </tr>
    </table>
</html:form>
<%
    String source = (String) request.getAttribute("source");
    if (null != source) {%>
<html:form action="/smsApplys.do?method=saveMsgContent" method="post" styleId="">
    <table>
        <%
            List list = (List) request.getAttribute("list");
            for (int i = 0; i < list.size(); i++) {
                List subList = (List) list.get(i);
                if (i % 5 == 0) {
        %>
        <tr>
            <%}%>
            <td width="200">
                <input type="checkbox" name="<%=subList.get(0)%>" id="<%=subList.get(0)%>" value="<%=subList.get(0)%>"
                       onclick="changeContent(this)"><%=subList.get(1)%>
            </td>
            <%if (i % 5 == 4) { %>
        </tr>
        <%} %>
        <%}%>
    </table>
    <br>
    <textarea class="textarea max" name="chContent" id="chContent" size="200" width="200"
              onmousedown=>${content}</textarea>
    <input type="submit" value="保存内容" class="submit"/>
    <input type="hidden" name="workflowName" id="workflowName" value="${workflowName}"/>
    <input type="hidden" name="flowDictName" id="flowDictName" value="${flowDictName}"/>
    <input type="hidden" name="serviceId" id="serviceId" value="${serviceId}"/>
    <input type="hidden" name="type" id="type" value="${type}"/>
</html:form>
<%} %>

<script type="text/javascript">
    function changeContent(thisobj) {
        var id = thisobj.id;
        var value = thisobj.value;
        var oldContent = document.getElementById("chContent").innerHTML;
        if (thisobj.checked) {
            document.getElementById("chContent").innerHTML = oldContent + "[" + value + "]";
        } else {
            var newValue = "[" + value + "]";
            if (oldContent.indexOf(newValue) > -1) {
                //alert("0--"+oldContent.indexOf(newValue));
                removeContent(newValue, oldContent.indexOf(newValue), oldContent);
            }
        }
    }

    function removeContent(newValue, isexist, oldContent) {
        var newContent = oldContent.substring(0, isexist);
        var subcon = oldContent.substring((isexist + newValue.length), oldContent.length);
        document.getElementById("chContent").innerHTML = newContent + subcon;
        //alert("1--"+oldContent.indexOf(newValue));
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>