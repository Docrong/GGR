<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<script type="text/javascript">
    function ConfirmDel() {
        var msg = "确认删除该记录?";
        if (confirm(msg) == true) {
            return true;
        } else {
            return false;
        }
    };

    function newPage() {
        document.forms[0].action = "cutApplys.do?method=addAdmin";
        document.forms[0].submit();
    }
</script>
<form action="cutApplys.do?method=addAdmin" id="fr" name="fr"
      method="post">
    <fmt:bundle basename="config/applicationResource-cutapply">

        <content tag="heading">
            <center>
                <H2>
                    干线割接管理-管理员角色列表
                </H2>
            </center>
        </content>
        <display:table name="cutApplyAdminList" cellspacing="0"
                       cellpadding="0" id="cutApplyAdminList"
                       class="table cutApplyAdminList" pagesize="12" sort="external"
                       requestURI="${app}/cutapply/cutApplys.do?method=adminList"
                       size="resultSize">

            <logic:present name="cutApplyAdminList" property="id">
                <display:column title="管理员姓名">
                    <eoms:id2nameDB id="${cutApplyAdminList.userId}"
                                    beanId="tawSystemUserDao"/>
                </display:column>
                <display:column title="管理员ID">
                    ${cutApplyAdminList.userId}
                </display:column>
                <display:column title="所在部门名称">
                    <eoms:id2nameDB id="${cutApplyAdminList.deptId}"
                                    beanId="tawSystemDeptDao"/>
                </display:column>

                <display:column headerClass="sortable" title="删除">
                    <html:link href="${app}/cutapply/cutApplys.do?method=removeAdmin"
                               paramId="id" paramProperty="id" paramName="cutApplyAdminList"
                               onclick="javascript:return ConfirmDel();">
                        <bean:message key="button.delete"/>
                    </html:link>
                </display:column>

            </logic:present>
        </display:table>
        <br/>
        <table width="80%">
            <tr>
                <input type="button" style="margin-right: 5px" onclick="newPage()"
                       value="<bean:message key="button.add"/>"/>
            </tr>
        </table>
    </fmt:bundle>
</form>

<%@ include file="/common/footer_eoms.jsp" %>
