<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,com.boco.eoms.common.controller.*" %>
<%@ page import="com.boco.eoms.kbs.util.*" %>
<%
    String flag = StaticMethod.nullObject2String(request.getAttribute("FLAG"), "");
    String sheetflag = StaticMethod.nullObject2String(request.getAttribute("SHEETFLAG"), "");
    String worksheetId = StaticMethod.nullObject2String(request.getAttribute("WORKSHEETID"), "");
    String sheetId = StaticMethod.nullObject2String(request.getAttribute("SHEETID"), "");
    com.boco.eoms.kbs.controller.KbsBaseForm kbsform = (com.boco.eoms.kbs.controller.KbsBaseForm) request.getAttribute("kbsBaseForm");
    String mapPath = StaticMethod.nullObject2String(request.getAttribute("MAPPATH"), "");
%>
<script LANGUAGE=javascript>
    function onRemove() {
        document.form1.action = "trash.do";
        document.form1.submit();
        return true;
    }
</script>
<html>

<head>
    <title>删除案例</title>
    <html:base/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<!--<base target="_self">-->
<body>
<center>
    <br>
    <html:form method="POST" action="/KbsBase/trash">
        <input type="hidden" name="mapPath" id="mapPath" value="<%=mapPath%>">
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" class="table_title" align="center">&nbsp;&nbsp;
                    <bean:message key="label.remove"/>案例
                </td>
            </tr>
        </table>
        <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
            <logic:present name="kbsBaseForm" scope="request">
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        案例编码
                    </td>
                    <td width="70%" height="25">
                        <bean:write name="kbsBaseForm" property="code" scope="request"/>
                    </td>
                </tr>
                <%if (!sheetId.equals("")) {%>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        工单号
                    </td>
                    <td width="70%" height="25">
                        <%if (sheetflag.equals("0")) {%>
                        <a href="/EOMS_J2EE/newworksheet/Faultsheet/detail.do?id=<%=worksheetId.toString()%>"><%=sheetId.toString()%>
                        </a>
                        <%} else {%>
                        <a href="/EOMS_J2EE/newworksheet/Applysheet/detail.do?id=<%=worksheetId.toString()%>"><%=sheetId.toString()%>
                        </a>
                        <%}%>
                    </td>
                </tr>
                <%}%>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        案例主题
                    </td>
                    <td width="70%" height="25">
                        <bean:write name="kbsBaseForm" property="name" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        提交人
                    </td>
                    <td width="70%" height="25">
                        <bean:write name="kbsBaseForm" property="authorDeptName" scope="request"/>&nbsp;
                        <bean:write name="kbsBaseForm" property="authorName" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        作者
                    </td>
                    <td width="70%" height="25">
                        <bean:define id="zuozhe" name="kbsBaseForm" property="zuozhe" type="java.lang.String"/>
                        <%=StaticMethod.getGBString(StaticMethod.null2String(zuozhe, ""))%>

                    </td>
                </tr>

                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        生成时间
                    </td>
                    <td width="70%" height="25">
                        <bean:write name="kbsBaseForm" property="publicTime" scope="request"/>
                    </td>
                </tr>

                <%if (flag != null && flag.equals("1")) {%>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        投诉类型
                    </td>
                    <td width="70%" height="25">

                        <bean:write name="kbsBaseForm" property="applyName" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">

                    <td width="30%" height="25" class="clsfth">&nbsp;
                        用户品牌
                    </td>
                    <td width="70%" height="25">

                        <bean:write name="kbsBaseForm" property="custName" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        投诉描述
                    </td>
                    <td width="70%" height="25" style="WORD-BREAK: break-all">
                        <% JUBB ubb1 = new JUBB();
                            //System.out.println(description);
                            String body1 = StaticFunction.htmlEncode(kbsform.getDescription());
                            body1 = ubb1.getAll(StaticMethod.null2String(body1));
                            out.println(body1);%>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        投诉原因分析
                    </td>
                    <td width="70%" height="25" style="WORD-BREAK: break-all">

                        <% JUBB ubb2 = new JUBB();
                            //System.out.println(description);
                            String body2 = StaticFunction.htmlEncode(kbsform.getCause());
                            body2 = ubb2.getAll(StaticMethod.null2String(body2));
                            out.println(body2);%>
                    </td>
                </tr>
                <%} else {%>

                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        专业类型
                    </td>
                    <td width="70%" height="25">

                        <bean:write name="kbsBaseForm" property="specialtyName" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        故障类型
                    </td>
                    <td width="70%" height="25">
                        <bean:write name="kbsBaseForm" property="faultName" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        故障描述
                    </td>
                    <td width="70%" height="25" style="WORD-BREAK: break-all">
                        <% JUBB ubb3 = new JUBB();
                            //System.out.println(description);
                            String body3 = StaticFunction.htmlEncode(kbsform.getDescription());
                            body3 = ubb3.getAll(StaticMethod.null2String(body3));
                            out.println(body3);%>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        故障原因分析
                    </td>
                    <td width="70%" height="25" style="WORD-BREAK: break-all">

                        <% JUBB ubb4 = new JUBB();
                            //System.out.println(description);
                            String body4 = StaticFunction.htmlEncode(kbsform.getCause());
                            body4 = ubb4.getAll(StaticMethod.null2String(body4));
                            out.println(body4);%>
                    </td>
                </tr>
                <%}%>

                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        处理过程
                    </td>
                    <td width="70%" height="25" style="WORD-BREAK: break-all">

                        <% JUBB ubb = new JUBB();
                            //System.out.println(description);
                            String body = StaticFunction.htmlEncode(kbsform.getDeal());
                            body = ubb.getAll(StaticMethod.null2String(body));
                            out.println(body);%>
                    </td>
                </tr>

                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        审核人
                    </td>
                    <td width="70%" height="25">
                        <bean:write name="kbsBaseForm" property="auditorDeptName" scope="request"/>&nbsp;
                        <bean:write name="kbsBaseForm" property="auditorName" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        审核时间
                    </td>
                    <td width="70%" height="25">
                        <bean:write name="kbsBaseForm" property="auditTime" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        附件
                    </td>
                    <td width="70%" height="25">
                        <eoms:attachment name="kbsBaseForm" property="attachName" scope="request" idField="attachName"
                                         appCode="kbs" viewFlag="Y"/>
                    </td>
                </tr>
            </logic:present>
        </table>

        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" height="32" align="right">
                    <bean:define id="id" name="kbsBaseForm" property="id" type="java.lang.Integer"/>
                    <input type=hidden name="id" value=<%=id%>>
                    <html:submit property="button1" onclick="bCancel=false;" styleClass="clsbtn2">
                        <bean:message key="label.remove"/>
                    </html:submit>&nbsp;
                    <input type="button" class="clsbtn2" value="<bean:message key="label.cancel"/>" name="button"
                           onclick="history.back()">
                    &nbsp;&nbsp;
                </td>
            </tr>
        </table>
    </html:form>
</center>
</div>
</body>
</html>
