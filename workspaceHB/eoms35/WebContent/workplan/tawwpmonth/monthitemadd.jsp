<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpAddonsTableVO" %>

<script language="javascript">

    function SubmitCheck() {

        frmReg = document.tawwpitemadd;
        if (frmReg.name.value == '') {
            alert("<bean:message key="itemadd.tawwpmodel.warnInputName" />");
            return false;
        }

        return true;
    }

</script>

<%
    Hashtable hashtable = TawwpStaticVariable.ADDONS_RANK;
    Enumeration enumeration = null;
    int count = hashtable.size();
    System.out.println("hashtable = " + hashtable.size());
    if (hashtable != null) {
        enumeration = hashtable.keys();
    }

    ArrayList addonsList = new ArrayList();
    if (request.getAttribute("addonslist") != null) {
        addonsList = (ArrayList) request.getAttribute("addonslist");
    }

    TawwpAddonsTableVO tawwpAddonsTableVO = null;
    String id = null;
%>
<form name="tawwpitemadd" method="post"
      action="../tawwpmonth/monthexecutesaveTemp.do?monthplanid=<%=request.getAttribute("monthplanid")%>"
      onsubmit='return SubmitCheck()'>
    <table class="formTable">
        <caption>作业计划临时计划新增</caption>

        <tr>
            <td class="label">
                作业项目
            </td>
            <td width="400">
                <input type="text" name="name" size="40" class="text">
            </td>
        </tr>
        <tr>
            <td class="label">
                业务类型
            </td>
            <td width="400">
                <input type="text" name="botype" size="40" class="text">
            </td>
        </tr>
        <tr>
            <td class="label">
                执行单位级别
            </td>
            <td width="400">
                <input type="text" name="executedeptlevel" size="40" class="text">
            </td>
        </tr>
        <tr>
            <td class="label">
                适用说明
            </td>
            <td width="400">
                <input type="text" name="appdesc" size="40" class="text">
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message key="itemadd.tawwpmodel.formCycle"/>
            </td>
            <td width="400">
                <select size='1' name='cycle' value='1' class="select" onchange="javascript:filter(this);">
                    <option value='1'>天</option>
                    <option value='2'>周</option>
                    <option value='3'>半月</option>
                    <option value='4'>月</option>
                    <option value='0'>其他</option>
                </select>
                <br>
                <select size='1' class="select" name="executeDay" id="executeDay" style="display:none">
                    <option value=''>请选择</option>
                    <option value='1'>周一</option>
                    <option value='2'>周二</option>
                    <option value='3'>周三</option>
                    <option value='4'>周四</option>
                    <option value='5'>周五</option>
                    <option value='6'>周六</option>
                    <option value='7'>周日</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message key="itemadd.tawwpmodel.formDefault"/>
            </td>
            <td>
                <textarea name="format" class="textarea max"></textarea>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message key="itemadd.tawwpmodel.formHoliday"/>
            </td>
            <td width="400">
                <input type=radio name='isHoliday' value='0' checked>是<input type=radio name='isHoliday' value='1'>否
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message key="itemadd.tawwpmodel.formWeekend"/>
            </td>
            <td width="400">
                <input type=radio name='isWeekend' value='0' checked>是<input type=radio name='isWeekend' value='1'>否
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message key="itemadd.tawwpmodel.formTable"/>
            </td>
            <td width="400">
                <select name='formid' value='0' class="select">
                    <option value='0'><bean:message key="itemadd.tawwpmodel.selAddTable"/></option>
                    <%
                        for (int j = 0; j < addonsList.size(); j++) {
                            tawwpAddonsTableVO = (TawwpAddonsTableVO) addonsList.get(j);
                    %>
                    <option value='<%=tawwpAddonsTableVO.getId()%>'><%=tawwpAddonsTableVO.getName()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>

        <tr>
            <td class="label">
                是否必须上传附件
            </td>
            <td width="400">
                <input type=radio name='fileFlag' value='1'>是&nbsp;&nbsp;<input type=radio name='fileFlag' value='0'
                                                                                checked>否
            </td>
        </tr>

        <tr>
            <td class="label">
                执行帮助
            </td>
            <td>
                <textarea name="remark" class="textarea max"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan='2'>
                <input type="submit" value="<bean:message key="itemadd.tawwpmodel.btnSubmit" />" name="B1"
                       Class="submit">
                <input type="button" value="<bean:message key="itemadd.tawwpmodel.btnBack" />"
                       onclick="javascript:window.history.back();" class="button">
            </td>
        </tr>
    </table>
</form>
<%@ include file="/common/footer_eoms.jsp" %>
<script type="text/javascript">
    function filter(el) {
        if (el.options[el.selectedIndex].value == 2) {
            eoms.$('executeDay').show();
        } else {
            eoms.$('executeDay').hide();
        }
    }
</script>



