<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ include file="/common/xtreelibs.jsp" %>
<%@ page import="com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager" %>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@ page import="com.boco.eoms.duty.model.TawRmLogUnite" %>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmLogUniteDetail.title"/></title>
<content tag="heading">
    <fmt:message key="tawRmLogUniteDetail.heading"/>
</content>
<%
    TawRmLogUnite tawRmLogUnite = (TawRmLogUnite) request.getAttribute("tawRmLogUnite");
    ITawSystemCptroomManager mgr = (ITawSystemCptroomManager) ApplicationContextHolder.getInstance().getBean("ItawSystemCptroomManager");
    String roomName = mgr.getTawSystemCptroomName(new Integer(tawRmLogUnite.getRoomId()));
    String roomId = request.getParameter("roomId");
    String beginTime = request.getParameter("beginTime");
    String endTime = request.getParameter("endTime");
%>

<!--对表单的自动生成的处�?-->
<html:form action="tawRmLogUnite" method="post" styleId="tawRmLogUniteForm">
    <ul>

        <html:hidden property="roomId"/>
        <html:hidden property="beginTime"/>
        <html:hidden property="endTime"/>
        <!--表示对所有的域有�? -->
        <table class="formTable">
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLogUniteForm.workerNames"/>
                </td>
                <td width="500" colspan="2">
                    <%=tawRmLogUnite.getWorkerNames() %>
                </td>
            </tr>

            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLogUniteForm.planContent"/>
                </td>
                <td width="500" colspan="2">
                    <%=tawRmLogUnite.getPlanContent() %>
                </td>
            </tr>

            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLogUniteForm.workOrder"/>
                </td>
                <td width="500" colspan="2">
                    <%=tawRmLogUnite.getWorkOrder() %>
                </td>
            </tr>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLogUniteForm.workbenchMemo"/>
                </td>
                <td width="500" colspan="2">
                    <%=tawRmLogUnite.getWorkbenchMemo() %>
                </td>
            </tr>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLogUniteForm.dispatchRecord"/>
                </td>
                <td width="500" colspan="2">
                    <%=tawRmLogUnite.getDispatchRecord() %>
                </td>
            </tr>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLogUniteForm.visitRecord"/>
                </td>
                <td width="500" colspan="2">
                    <%=tawRmLogUnite.getVisitRecord()%>
                </td>
            </tr>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLogUniteForm.reliefRecord"/>
                </td>
                <td width="500" colspan="2">
                    <%=tawRmLogUnite.getReliefRecord()%>
                </td>
            </tr>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLogUniteForm.loanRecord"/>
                </td>
                <td width="500" colspan="2">
                    <%=tawRmLogUnite.getLoanRecord()%>
                </td>
            </tr>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLogUniteForm.roomId"/>
                </td>
                <td width="500" colspan="2">
                    <%=roomName%>
                </td>
            </tr>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLogUniteForm.beginTime"/>
                </td>
                <td width="500" colspan="2">
                    <%=tawRmLogUnite.getBeginTime()%>
                </td>
            </tr>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawRmLogUniteForm.endTime"/>
                </td>
                <td width="500" colspan="2">
                    <%=tawRmLogUnite.getEndTime() %>
                </td>
            </tr>
        </table>

        <br>
        <table>
            <tr>
                <td>
                    <!-- <input type = "button" value='${eoms:a2u('返回')}' class="button"  onclick="javascript:history.back(-1);"> -->
                    <html:submit styleClass="button" property="method.searchList2">
                        <fmt:message key="button.back"/>
                    </html:submit>
                </td>
            </tr>
        </table>
    </ul>
    <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp" %>