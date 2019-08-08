<%@ page language="java" import="java.util.*,com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    String assessStatus = request.getAttribute("assessStatus").toString();
    String specialType = request.getAttribute("specialType").toString();
    String realAssessor = request.getAttribute("realAssessor").toString();
    String assessAttitude = request.getAttribute("assessAttitude").toString();
    String size = request.getAttribute("size").toString();
    int listSize = Integer.parseInt(size);
%>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
        colorRows('note-table');
        //init Form validation and styles
        //valider({form:'tawSupplierkpiTemplateAssessForm',vbtn:'method.save'});
    })

    function inquire() {
        var flag = document.forms[0].flag.value;
        if (flag == 1) {
            alert("${eoms:a2u('尚有被驳回的评估指标未处理！')}");
        } else {
            var specialType = document.forms[0].specialType.value;
            var url = '<c:url value="/supplierkpi/tawSupplierkpiTemplateAssesss.do?method=isInquireForUpdate"/>';
            pars = "&specialType=" + specialType;
            var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars, onComplete: call});
        }
    }

    function call(originalRequest) {
        var str = originalRequest.responseText;
        if (1 == str) {
            var answer = window.confirm("${eoms:a2u('目前已有供应商定制该专业的评估指标，是否将评估指标的变化同步更新到定制关系中去？同步将在审核通过后执行。')}");
            if (answer) {
                document.forms[0].isUpdate.value = "1";
            }
        }
        tawSupplierkpiTemplateAssessForm.submit();
    }

    function setFlag() {
        document.forms[0].flag.value = "1";
    }
</script>

<table cellspacing="0" cellpadding="0" border="0" id="note-table" align="right">
    <tr height="10">
        <td width="10" style="BACKGROUND-COLOR:aqua">
        </td>
        <td width="100">
            ${eoms:a2u('：新增指标')}
        </td>
        <td width="10" style="BACKGROUND-COLOR:gray">
        </td>
        <td width="100">
            ${eoms:a2u('：删除指标')}
        </td>
        <td width="10" style="BACKGROUND-COLOR:red">
        </td>
        <td width="100">
            ${eoms:a2u('：驳回指标')}
        </td>
    </tr>
</table>

<fmt:bundle basename="config/ApplicationResources-supplierkpi">
    <div class="list-title">
        <bean:write name="tawSupplierkpiTemplateForm" property="serviceType" scope="request"/>
        -
        <bean:write name="tawSupplierkpiTemplateForm" property="specialType" scope="request"/>
    </div>
</fmt:bundle>
<html:form action="saveTawSupplierkpiTemplateAssess" method="post" styleId="tawSupplierkpiTemplateAssessForm">
    <html:hidden property="assessStatus" value="<%=assessStatus%>"/>
    <html:hidden property="specialType" value="<%=specialType%>"/>
    <html:hidden property="isUpdate" value="0"/>
    <P>
            <html:hidden property="flag" value="0"/>
    <div class="list">
        <table cellspacing="0" cellpadding="0" border="0" id="list-table">
            <tr height="30">
                <td width="20%">
                    <eoms:label styleClass="desc" key="tawSupplierkpiTemplateForm.serviceType"/>
                </td>
                <td width="30%">
                    <B><bean:write name="tawSupplierkpiTemplateForm" property="serviceType" scope="request"/></B>
                </td>
                <td width="20%">
                    <eoms:label styleClass="desc" key="tawSupplierkpiTemplateForm.specialType"/>
                </td>
                <td width="30%">
                    <B><bean:write name="tawSupplierkpiTemplateForm" property="specialType" scope="request"/></B>
                </td>
            </tr>
            <tr height="30">
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiTemplateForm.creator"/>
                </td>
                <td>
                    <B><bean:write name="tawSupplierkpiTemplateForm" property="creator" scope="request"/></B>
                </td>
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiTemplateForm.assessStatus"/>
                </td>
                <td>
                    <B><bean:write name="tawSupplierkpiTemplateForm" property="templateName" scope="request"/></B>
                </td>
            </tr>
            <%if ("3".equals(assessStatus)) {%>
            <tr height="30">
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiTemplateAssessForm.realAssessor"/>
                </td>
                <td colspan="3">
                    <B><%=realAssessor%>
                    </B>
                </td>
            </tr>
            <tr height="30">
                <td>
                        ${eoms:a2u('审核时间')}
                </td>
                <td colspan="3">
                    <B><bean:write name="tawSupplierkpiTemplateForm" property="createTime" scope="request"/></B>
                </td>
            </tr>
            <tr height="60">
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiTemplateAssessForm.assessAttitude"/>
                </td>
                <td colspan="3">
                    <B><%=assessAttitude%>
                    </B>
                </td>
            </tr>
            <%} else if ("4".equals(assessStatus)) {%>
            <tr height="30">
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiTemplateAssessForm.realAssessor"/>
                </td>
                <td colspan="3">
                    <B><%=realAssessor%>
                    </B>
                </td>
            </tr>
            <tr height="30">
                <td>
                        ${eoms:a2u('审核时间')}
                </td>
                <td colspan="3">
                    <B><bean:write name="tawSupplierkpiTemplateForm" property="createTime" scope="request"/></B>
                </td>
            </tr>
            <tr height="60">
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiTemplateAssessForm.assessAttitude"/>
                </td>
                <td colspan="3">
                    <font color="red"><B><%=assessAttitude%>
                    </B></font>
                </td>
            </tr>
            <%}%>
            <%if (listSize > 0) {%>
            <%if ("1".equals(assessStatus) || ("4".equals(assessStatus))) {%>
            <tr height="30">
                <td colspan="4">
                    <input type="button" class="btn" value="${eoms:a2u('送审')}" onclick="inquire()"/>
                </td>
            </tr>
            <%}%>
            <%} else {%>
            <tr height="30">
                <td colspan="4">
                    <font color="red">${eoms:a2u('该专业目前没有评估指标,请到"评估指标管理"页面添加.')}</font>
                </td>
            </tr>
            <%}%>
        </table>
    </div>
    <P>
        <!-- kpi列表-->
    <div class="list-title">
            ${eoms:a2u(' 评估指标列表')}
    </div>
    <div class="list">
        <table cellspacing="0" cellpadding="0" border="0" id="kpi-list-table">
            <tr class="header" height="30">
                <td width="50%">
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.kpiName"/>
                </td>
                <td width="10%">
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.dataSource"/>
                </td>
                <td width="10%">
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.dataType"/>
                </td>
                <td width="10%">
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.statictsCycle"/>
                </td>
                <!--td width="10%">
			<eoms:label styleClass="desc" key="tawSupplierkpiItemForm.writeManner"/>
		</td-->
                <td width="10%">
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.unit"/>
                </td>
                <td width="10%">
                    <eoms:label styleClass="desc" key="tawSupplierkpiItemForm.isImpersonality"/>
                </td>
            </tr>
            <logic:iterate id="it" name="it">
                <!-- assessStatus==4代表审核驳回（红色） -->
                <logic:match name="it" property="assessStatus" value="4">
                    <tr height="30" style="BACKGROUND-COLOR:red">
                    <script language="javascript">
                        setFlag();
                    </script>
                </logic:match>
                <logic:notMatch name="it" property="assessStatus" value="4">
                    <!-- assessStatus==5代表审核后新增（绿色） -->
                    <logic:match name="it" property="assessStatus" value="5">
                        <tr height="30" style="BACKGROUND-COLOR:aqua">
                    </logic:match>
                    <logic:notMatch name="it" property="assessStatus" value="5">
                        <!-- assessStatus==6代表审核后删除（灰色） -->
                        <logic:match name="it" property="assessStatus" value="6">
                            <tr height="30" style="BACKGROUND-COLOR:gray">
                        </logic:match>
                        <logic:notMatch name="it" property="assessStatus" value="6">
                            <tr height="30">
                        </logic:notMatch>
                    </logic:notMatch>
                </logic:notMatch>
                <td>
                    <bean:define id="dictId" name="it" property="itemType"/>
                    <c:choose>
                        <c:when test="${it.assessStatus == 6}">
                            <a href="editTawSupplierkpiItem.do?method=view&dictId=<%=dictId%>&fromTemplateView=1&itemId=${it.id}">
                                <bean:write name="it" property="kpiName"/>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="editTawSupplierkpiItem.do?method=update&dictId=<%=dictId%>&fromTemplateView=1">
                                <bean:write name="it" property="kpiName"/>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <eoms:dict key="dict-supplierkpi" dictId="dataSource" itemId="${it.dataSource}"
                               beanId="id2nameXML"/>
                </td>
                <td>
                    <eoms:dict key="dict-supplierkpi" dictId="dataType" itemId="${it.dataType}" beanId="id2nameXML"/>
                </td>
                <td>
                    <eoms:dict key="dict-supplierkpi" dictId="statictsCycle" itemId="${it.statictsCycle}"
                               beanId="id2nameXML"/>
                </td>
                <!--td>
                <bean:write name="it" property="id2writeManner"/>
                </td-->
                <td>
                    <eoms:dict key="dict-supplierkpi" dictId="unit" itemId="${it.unit}" beanId="id2nameXML"/>
                </td>
                <td>
                    <eoms:dict key="dict-supplierkpi" dictId="isImpersonality" itemId="${it.isImpersonality}"
                               beanId="id2nameXML"/>
                </td>
                </tr>
            </logic:iterate>
        </table>
    </div>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>