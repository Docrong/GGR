<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpMonthPlanVO" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Enumeration" %>

<%
    TawwpMonthPlanVO tawwpMonthPlanVO = (TawwpMonthPlanVO) request.getAttribute("mnonthplanvo");
    Hashtable principalHash = (Hashtable) request.getAttribute("principalhash");
    Enumeration tempVar = principalHash.keys();
    String userId = "";
    String userName = "";
%>

<script language="JavaScript">

    function GoBack() {
        window.history.back();
    }

    function JustifyNull1(field) {
        var Ret = true
        var str = "" + field.value
        if (str.length) {
            for (var i = 0; i < str.length; i++)
                if (str.charAt(i) != " ")
                    break
            if (i >= str.length)
                field.value = ""
        }
        if (field.value.length == 0)
            Ret = false
        return (Ret)
    }// 判断输入字段是否为空

    function SubmitCheck() {
        frmReg = document.tawwpmonthplanform;

        if (!JustifyNull1(frmReg.executetype)) {
            alert("<bean:message key="monthedit.title.warnSelExeType" />");
            return false;
        }
        //if( !JustifyNull1(frmReg.principal))
        if (frmReg.principal.value == '') {
            alert("<bean:message key="monthedit.title.warnSelExePrincipal" />");
            return false;
        }
        document.tawwpmonthplanform.button1.style.display = "none";
        return true;
    }

</script>

<!-- body begin -->

<form name="tawwpmonthplanform" method="post"
      action="../tawwpmonth/monthmodify.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>" onsubmit='return SubmitCheck()'>

    <table class="formTable">
        <caption>
            <%=tawwpMonthPlanVO.getYearFlag()%> &nbsp;<bean:message key="monthedit.title.labYear"/>&nbsp;
            <%=tawwpMonthPlanVO.getMonthFlag()%> &nbsp;<bean:message key="monthedit.title.labMonth"/>&nbsp;
            &lt;&nbsp;<%=tawwpMonthPlanVO.getName()%>&nbsp;&gt;&nbsp;<bean:message key="monthedit.title.labEdit"/>
        </caption>
        <tr>
            <td width="100" class="label">
                <bean:message key="monthedit.title.formDeptName"/>
            </td>
            <td width="500">
                <%=tawwpMonthPlanVO.getDeptName()%>
            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <bean:message key="monthedit.title.formExecuteType"/>
            </td>
            <td width="500">
                <select name="executetype" class="select">
                    <%
                        if ("".equals(tawwpMonthPlanVO.getExecuteType())) {
                    %>
                    <option value="" selected="selected"><bean:message key="monthedit.title.selTitle"/></option>
                    <%
                    } else {
                    %>
                    <option value=""><bean:message key="monthedit.title.selTitle"/></option>
                    <%
                        }
                        if ("0".equals(tawwpMonthPlanVO.getExecuteType())) {
                    %>
                    <option value="0" selected="selected"><bean:message key="monthedit.title.selMoreInOne"/></option>
                    <%
                    } else {
                    %>
                    <option value="0"><bean:message key="monthedit.title.selMoreInOne"/></option>
                    <%
                        }
                        if ("1".equals(tawwpMonthPlanVO.getExecuteType())) {
                    %>
                    <option value="1" selected="selected"><bean:message key="monthedit.title.selMoreInMore"/></option>
                    <%
                    } else {
                    %>
                    <option value="1"><bean:message key="monthedit.title.selMoreInMore"/></option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <bean:message key="monthedit.title.formPrincipal"/>
            </td>
            <td width="500">
                <%--
                <input type="text" name="principalName" size="30" styleClass="clstext" readonly="true" value="<%=tawwpMonthPlanVO.getPrincipalName()%>">
                <input type="hidden" name="principal" size="30" value="<%=tawwpMonthPlanVO.getPrincipal()%>">
                <a href="">选择执行负责人</a>
                --%>
                <select name="principal" class="select">
                    <%
                        if ("".equals(tawwpMonthPlanVO.getPrincipal())) {
                    %>
                    <option value="" selected="selected"><bean:message key="monthedit.title.selTitle"/></option>
                    <%
                        }
                    %>
                    <%
                        while (tempVar.hasMoreElements()) {
                            userId = (String) tempVar.nextElement();
                            userName = (String) principalHash.get(userId);
                            if (!"".equals(userId) && userId.equals(tawwpMonthPlanVO.getPrincipal())) {
                    %>
                    <option value="<%=userId%>" selected="selected"><%=userName%>
                    </option>
                    <%
                    } else if (!"".equals(userId)) {
                    %>
                    <option value="<%=userId%>"><%=userName%>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <bean:message key="monthedit.title.formConstituteState"/>
            </td>
            <td width="500">
                <%=tawwpMonthPlanVO.getConstituteStateName()%>
            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <bean:message key="monthedit.title.formExecuteState"/>
            </td>
            <td width="500">
                <%=tawwpMonthPlanVO.getExecuteStateName()%>
            </td>
        </tr>

        <%--
        <tr>
          <td width="100" class="label">
            系统类型
          </td>
          <td width="500">
            <%=tawwpMonthPlanVO.getSysTypeName()%>
          </td>
        </tr>
        <tr>
          <td width="100" class="label">
            网元类型
          </td>
          <td width="500">
            <%=tawwpMonthPlanVO.getNetTypeName()%>
          </td>
        </tr>
        --%>

        <tr>
            <td width="100" class="label">
                <bean:message key="monthedit.title.formNetList"/>
            </td>
            <td width="500">
                <%=tawwpMonthPlanVO.getNetName()%>
            </td>
        </tr>
    </table>
    <br>
    <input type="hidden" size=390 name="monthplanid" value="<%=tawwpMonthPlanVO.getId()%>">
    <input type="submit" value="<bean:message key="monthedit.title.btnSubmit" />" name="button1" class="submit">
    <input type="button" value="<bean:message key="monthedit.title.btnBack" />" name="B1" class="button"
           onclick="javascript:GoBack();">


</form>

<!-- body end -->
<%@ include file="/common/footer_eoms.jsp" %>



