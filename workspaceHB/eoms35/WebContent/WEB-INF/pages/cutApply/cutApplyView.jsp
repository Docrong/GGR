<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<html:form action="/cutApplys.do?method=save" styleId="cutApplyForm"
           method="post">

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
                        ${cutApplyForm.context}
                </td>
            </tr>

            <tr>
                <td class="label">
                    割接所属地州
                </td>
                <td class="content">
                    <eoms:id2nameDB id="${cutApplyForm.areaId}"
                                    beanId="tawSystemDictTypeDao"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    割接开始时间
                </td>
                <td class="content">
                        ${cutApplyForm.cutStartTime}
                </td>
            </tr>

            <tr>
                <td class="label">
                    割接结束时间
                </td>
                <td class="content">
                        ${cutApplyForm.cutEndTime}
                </td>
            </tr>

            <tr>
                <td class="label">
                    割接现场负责人
                </td>
                <td class="content">
                        ${cutApplyForm.manager}
                </td>
            </tr>

            <tr>
                <td class="label">
                    联系电话
                </td>
                <td class="content">
                        ${cutApplyForm.phone}
                </td>
            </tr>

            <tr>
                <td class="label">
                    申请割接工单号或者公文号
                </td>
                <td class="content">
                        ${cutApplyForm.fileId}
                </td>
            </tr>

            <tr>
                <td class="label">
                    是否影响业务
                </td>
                <td class="content">
                    <eoms:id2nameDB id="${cutApplyForm.isAffect}"
                                    beanId="tawSystemDictTypeDao"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    影响开始时间
                </td>
                <td class="content">
                        ${cutApplyForm.affectStartTime}
                </td>
            </tr>
            <tr>
                <td class="label">
                    影响结束时间
                </td>
                <td class="content">
                        ${cutApplyForm.affectEndTime}
                </td>
            </tr>
            <tr>
                <td class="label">
                    影响业务描述
                </td>
                <td class="content">
                        ${cutApplyForm.affectInfo}
                </td>
            </tr>
            <tr>
                <td class="label">
                    备注
                </td>
                <td class="content">
                        ${cutApplyForm.remark}
                </td>
            </tr>
            <tr>
                <td align="center" colspan="4">
                    <html:button styleClass="button" property="button.close"
                                 onclick="window.close()">
                        关闭
                    </html:button>
                </td>
            </tr>
        </table>
    </fmt:bundle>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>
