<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<html:form action="/cutApplys.do?method=save" styleId="cutApplyForm" method="post">
    <script type="text/javascript">
        Ext.onReady(function () {
            v = new eoms.form.Validation({form: 'cutApplyForm'});
        });

    </script>
    <fmt:bundle basename="config/applicationResource-cutapply">
        <table class="formTable">
            <td colspan="2" align="center">
                <h2>
                    干线割接管理
                </h2>
            </td>

            <tr>
                <td class="label">
                    割接申请人
                </td>
                <td class="content">
                    <eoms:id2nameDB id="${cutApplyForm.userId}"
                                    beanId="tawSystemUserDao"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    割接申请人部门
                </td>
                <td class="content">
                    <eoms:id2nameDB id="${cutApplyForm.deptId}"
                                    beanId="tawSystemDeptDao"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    割接内容
                </td>
                <td class="content">
                    <html:textarea property="context" styleId="context"
                                   styleClass="text medium"
                                   alt="allowBlank:false,vtext:'请输入割接内容',maxLength:255" value="${cutApplyForm.context}"
                                   rows="3" cols="40"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    割接所属地州
                </td>
                <td class="content">
                    <eoms:comboBox name="areaId" id="areaId" initDicId="11601" alt="allowBlank:false,vtext:'请选择割接所属地州'"
                                   styleClass="select-class" defaultValue="${cutApplyForm.areaId}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    割接开始时间
                </td>
                <td class="content">
                    <html:text property="cutStartTime" styleId="cutStartTime"
                               styleClass="text medium"
                               value="${cutApplyForm.cutStartTime}" readonly="true"
                               alt="allowBlank:false,vtype:'lessThen',link:'cutEndTime',vtext:'不得晚于割接结束时间'"
                               onclick="popUpCalendar(this, this,null,null,null,true,-1);"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    割接结束时间
                </td>
                <td class="content">
                    <html:text property="cutEndTime" styleId="cutEndTime"
                               styleClass="text medium"
                               value="${cutApplyForm.cutEndTime}" readonly="true"
                               alt="allowBlank:false,vtype:'moreThen',link:'cutStartTime',vtext:'不得早于割接开始时间'"
                               onclick="popUpCalendar(this, this,null,null,null,true,-1);"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    割接现场负责人
                </td>
                <td class="content">
                    <html:text property="manager" styleId="manager"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:'请输入割接现场负责人',maxLength:32" value="${cutApplyForm.manager}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    联系电话
                </td>
                <td class="content">
                    <html:text property="phone" styleId="phone"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:'请输入联系电话',maxLength:32" value="${cutApplyForm.phone}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    申请割接工单号或者公文号
                </td>
                <td class="content">
                    <html:text property="fileId" styleId="fileId"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:'请输入申请割接工单号或者公文号',maxLength:32"
                               value="${cutApplyForm.fileId}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    是否影响业务
                </td>
                <td class="content">
                    <eoms:comboBox name="isAffect" id="isAffect" initDicId="11602"
                                   alt="allowBlank:false,vtext:'请选择是否影响业务'"
                                   styleClass="select-class" defaultValue="${cutApplyForm.isAffect}"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    影响开始时间
                </td>
                <td class="content">
                    <html:text property="affectStartTime" styleId="affectStartTime"
                               styleClass="text medium"
                               value="${cutApplyForm.affectStartTime}" readonly="true"
                               alt="vtype:'lessThen',link:'affectEndTime',vtext:'不得晚于影响结束时间'"
                               onclick="popUpCalendar(this, this,null,null,null,true,-1);"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    影响结束时间
                </td>
                <td class="content">
                    <html:text property="affectEndTime" styleId="affectEndTime"
                               styleClass="text medium"
                               value="${cutApplyForm.affectEndTime}" readonly="true"
                               alt="vtype:'moreThen',link:'affectStartTime',vtext:'不得早于影响开始时间'"
                               onclick="popUpCalendar(this, this,null,null,null,true,-1);"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    影响业务描述
                </td>
                <td class="content">
                    <html:textarea property="affectInfo" styleId="affectInfo"
                                   styleClass="text medium"
                                   alt="maxLength:255" value="${cutApplyForm.affectInfo}" rows="3" cols="40"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    备注
                </td>
                <td class="content">
                    <html:textarea property="remark" styleId="remark"
                                   styleClass="text medium"
                                   alt="maxLength:255" value="${cutApplyForm.remark}" rows="3" cols="40"/>
                </td>
            </tr>

        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
                <c:if test="${not empty cutApplyForm.id}">
                    <input type="button" class="btn" value="<fmt:message key="button.delete"/>"
                           onclick="javascript:if(confirm('确认删除本条记录?')){
                                   var url='${app}/cutapply/cutApplys.do?method=remove&id=${cutApplyForm.id}';
                                   location.href=url}"/>
                </c:if>
            </td>
        </tr>
    </table>
    <html:hidden property="id" value="${cutApplyForm.id}"/>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>