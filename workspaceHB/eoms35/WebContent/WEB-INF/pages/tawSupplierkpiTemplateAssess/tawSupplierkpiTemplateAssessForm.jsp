<%@ page language="java" import="java.util.*,com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    String specialType = request.getAttribute("specialType").toString();
    String serviceType = request.getAttribute("serviceType").toString();
    String assessStatus = request.getAttribute("assessStatus").toString();
%>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
        colorRows('kpi-list-table');
        colorRows('note-table');
        //init Form validation and styles
        //valider({form:'tawSupplierkpiTemplateAssessForm',vbtn:'method.save'});
    })

    function agree() {
        document.forms[0].opertype.value = "1";
    }

    function reject() {
        document.forms[0].opertype.value = "2";
    }
</script>

<!--table cellspacing="0" cellpadding="0" border="0" id="note-table" align="right">
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
</table-->

<fmt:bundle basename="config/ApplicationResources-supplierkpi">
    <div class="list-title">
        <eoms:id2nameDB id="<%=serviceType%>" beanId="tawSupplierkpiDictDao"/>
        -
        <eoms:id2nameDB id="<%=specialType%>" beanId="tawSupplierkpiDictDao"/>
    </div>
</fmt:bundle>

<html:form action="saveTawSupplierkpiTemplateAssess" method="post" styleId="tawSupplierkpiTemplateAssessForm">
    <html:hidden property="specialType" value="<%=specialType%>"/>
    <html:hidden property="opertype" value=""/>
    <div class="list">
        <table cellspacing="0" cellpadding="0" border="0" id="list-table">
            <tr height="30">
                <td width="20%">
                    <eoms:label styleClass="desc" key="tawSupplierkpiTemplateForm.serviceType"/>
                </td>
                <td width="30%">
                    <B><eoms:id2nameDB id="<%=serviceType%>" beanId="tawSupplierkpiDictDao"/></B>
                </td>
                <td width="20%">
                    <eoms:label styleClass="desc" key="tawSupplierkpiTemplateForm.specialType"/>
                </td>
                <td width="30%">
                    <B><eoms:id2nameDB id="<%=specialType%>" beanId="tawSupplierkpiDictDao"/></B>
                </td>
            </tr>
            <tr height="30">
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiTemplateAssessForm.assessRole"/>
                </td>
                <td>
                    <B><bean:write name="tawSupplierkpiTemplateAssessForm" property="assessRole" scope="request"/></B>
                </td>
                <td>
                        ${eoms:a2u('送审时间')}
                </td>
                <td>
                    <B><bean:write name="tawSupplierkpiTemplateAssessForm" property="assessTime" scope="request"/></B>
                </td>
            </tr>
            <tr height="30">
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiTemplateAssessForm.assessStatus"/>
                </td>
                <td colspan="3">
                    <B><%=assessStatus%>
                    </B>
                </td>
            </tr>
            <tr height="60">
                <td>
                    <eoms:label styleClass="desc" key="tawSupplierkpiTemplateAssessForm.assessAttitude"/>
                </td>
                <td colspan="3">
                    <html:textarea property="assessAttitude" styleId="assessAttitude" styleClass="textarea small"
                                   style="width:88%" value=""/>
                </td>
            </tr>
            <tr height="30">
                <td colspan="4">
                    <input type="submit" class="btn" value="${eoms:a2u('通过')}" onClick="agree()"/>
                    <input type="submit" class="btn" value="${eoms:a2u('驳回')}" onClick="reject()"/>
                </td>
            </tr>
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
                <tr height="30">
                    <td>
                        <bean:define id="dictId" name="it" property="itemType"/>
                        <input type="checkbox" name="checkbox" value="<bean:write name="it" property="id"/>"/>
                        <a href="editTawSupplierkpiItem.do?method=view&dictId=<%=dictId%>&fromTemplateAss=1">
                            <bean:write name="it" property="kpiName"/>
                        </a>
                    </td>
                    <td>
                        <eoms:dict key="dict-supplierkpi" dictId="dataSource" itemId="${it.dataSource}"
                                   beanId="id2nameXML"/>
                    </td>
                    <td>
                        <eoms:dict key="dict-supplierkpi" dictId="dataType" itemId="${it.dataType}"
                                   beanId="id2nameXML"/>
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