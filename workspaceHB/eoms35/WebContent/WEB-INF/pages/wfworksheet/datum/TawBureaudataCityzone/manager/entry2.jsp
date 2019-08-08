<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.lang.String" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod,com.boco.eoms.datum.vo.impl.TawBureaudataCityzoneVO" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css"/>
    <STYLE>
        .limitinput {
            behavior: url(<%=request.getContextPath()%>/css/js/limitInput.htc);
        }
    </STYLE>
    <title><bean:message key="label.edit"/>对应地市</title>
    <script type="text/javascript">
        function selectTree() {
            dWinOrnaments = "status:no;scroll:no;resizable:no;dialogHeight:450px;dialogWidth:480px;";
            dWin = showModalDialog('<%=request.getContextPath()%>/css/listbox/querylistbox.jsp?sort=1&checkall=no&selectself=yes&sortnumber=1', window, dWinOrnaments);
        }

        function checkForm(form) {
            if (form.cityName.value == '') {
                alert('请输入地市名称!');
                form.cityName.focus();
                return false;
            }
            if (form.zoneNum.value == '') {
                alert('请输入区号!');
                form.zoneNum.focus();
                return false;
            }
            if (form.sort1_deptid.value == '') {
                alert('请选择负责部门!');
                form.btnSelDept.click();
                return false;
            }
            form.deptId.value = form.sort1_deptid.value;
            if (form.userId.value == '') {
                alert('请输入负责人员ID号!');
                form.userId.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body style="margin-top:20px;" onload="document.forms[0].cityName.focus();">
<html:html>
    <html:form method="post" action="/TawBureaudataCityzone/save2" onsubmit="return checkForm(this);">
        <html:hidden property="cityId" name="VO"/>
        <div align="center">
            <table cellSpacing="0" cellPadding="0" width="60%" border="0" style="margin-button:10px;">
                <TR>
                    <td width="100%" class="table_title"><bean:message key="label.edit"/>对应地市</td>
                </TR>
            </table>
            <br>
            <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
                <logic:present name="VO" scope="request">
                    <html:hidden name="VO" property="id"/>
                    <tr class="tr_show">
                        <td width="15%" class="clsfth">地市名称</td>
                        <td width="200"><html:text property="cityName" size="40" name="VO" maxlength="20"/></td>
                    </tr>
                    <tr class="tr_show">
                        <td width="15%" class="clsfth">区号</td>
                        <td width="200"><input type="text" name="zoneNum" size="40" maxlength="9" class="limitinput"
                                               limit="^[0-9]*$" value='<bean:write name="VO" property="zoneNum"/>'/>
                        </td>
                    </tr>
                    <tr class="tr_show">
                        <td class="clsfth">对应部门</td>
                        <td style="padding-bottom:10px;padding-top:10px;">
                            <div id="sort1_text" style="margin-bottom:10px;"><bean:write name="VO"
                                                                                         property="deptName"/></div>
                            <input type="button" value="选择部门" name="btnSelDept" class="clsbigbtn"
                                   onclick="selectTree();">
                            <INPUT TYPE="hidden" name="sort1_deptid" id="sort1_deptid"
                                   value='<bean:write name="VO" property="deptId"/>'/>
                            <html:hidden property="deptId" name="VO"/>
                        </td>
                    </tr>
                    <tr class="tr_show">
                        <td width="15%" class="clsfth">负责人员ID号</td>
                        <td width="200"><html:text property="userId" size="40" name="VO" maxlength="20"/><html:hidden
                                property="userSysId" name="VO"/></td>
                    </tr>
                </logic:present>
            </table>
            <table border="0" width="95%" cellspacing="0">
                <tr>
                    <td width="100%" height="32" align="right">
                        <html:submit styleClass="clsbtn2">
                            <bean:message key="label.save"/>
                        </html:submit>
                        &nbsp;
                        <html:reset styleClass="clsbtn2">
                            <bean:message key="label.reset"/>
                        </html:reset>&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </div>
    </html:form>
    <logic:messagesPresent>
        <html:messages id="error">
            <script type="text/javascript">
                alert("<bean:write name="error"/>");
            </script>
        </html:messages>
    </logic:messagesPresent>
</html:html>
</body>
</html>
