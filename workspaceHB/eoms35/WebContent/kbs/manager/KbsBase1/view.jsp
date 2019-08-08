<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,com.boco.eoms.common.controller.*" %>
<%@ page import="com.boco.eoms.kbs.util.*" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>

<%
    String status = StaticMethod.nullObject2String(request.getAttribute("status"), "");
    String flag = StaticMethod.nullObject2String(request.getAttribute("FLAG"), "");
    String pageFlag = StaticMethod.nullObject2String(request.getAttribute("pageFlag"), "");
    String sheetflag = StaticMethod.nullObject2String(request.getAttribute("SHEETFLAG"), "");
    String worksheetId = StaticMethod.nullObject2String(request.getAttribute("WORKSHEETID"), "");
    String sheetId = StaticMethod.nullObject2String(request.getAttribute("SHEETID"), "");
    SaveSessionBeanForm saveSessionBeanForm = (SaveSessionBeanForm) request.getSession().
            getAttribute("SaveSessionBeanForm");
    com.boco.eoms.kbs.controller.KbsBaseForm kbsform = (com.boco.eoms.kbs.controller.KbsBaseForm) request.getAttribute("kbsBaseForm");
    boolean flag1 = kbsform.getAuthor().equals(saveSessionBeanForm.getWrf_UserID()) ? true : false;
    String sheetid = kbsform.getSheetid();
    if (flag1 && sheetid.indexOf("-79-") > 0)
        flag1 = true;
    else
        flag1 = false;
%>


<SCRIPT type="text/javascript">
    function onRemove() {
        document.kbsBaseForm.action = "<%=request.getContextPath()%>/kbs/KbsBase/trash.do";
        document.kbsBaseForm.submit();
        return true;
    }

    function onCommit() {
        document.kbsBaseForm.action = "<%=request.getContextPath()%>/kbs/KbsBase/commit.do";
        document.kbsBaseForm.submit();
        return true;
    }

    function onCheckPass() {
        if (<%=flag1%>) {
            alert("����֧Ԯ�����뾭��ⲻ�ܱ�������Լ��ύ�Ĺ���!");
            return false;
        } else {
            document.kbsBaseForm.action = "<%=request.getContextPath()%>/kbs/KbsBase/pass.do";
            document.kbsBaseForm.submit();
            return true;
        }
    }

    function onCheckDeny() {
        document.kbsBaseForm.action = "<%=request.getContextPath()%>/kbs/KbsBase/deny.do";
        document.kbsBaseForm.submit();
        return true;
    }

    function onCheckStop() {
        document.kbsBaseForm.action = "<%=request.getContextPath()%>/kbs/KbsBase/stop.do";
        document.kbsBaseForm.submit();
        return true;
    }
</script>
<html>
<head>
    <title>�鿴����</title>
    <html:base/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<!--<base target="_self">-->
<body>
<center>
    <br>
    <form name="kbsBaseForm" method="post">
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" class="table_title" align="center">
                    &nbsp;&nbsp;<bean:message key="label.view"/>����&nbsp;��
                </td>
            </tr>
        </table>
        <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
            <logic:present name="kbsBaseForm" scope="request">

                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        ��������
                    </td>
                    <td width="70%" height="25">
                        <bean:write name="kbsBaseForm" property="code" scope="request"/>
                    </td>
                </tr>
                <%if (!sheetId.equals("")) {%>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        ������
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
                        ��������
                    </td>
                    <td width="70%" height="25">
                        <bean:write name="kbsBaseForm" property="name" scope="request"/>
                    </td>
                </tr>


                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        �ύ��
                    </td>
                    <td width="70%" height="25">
                        <bean:write name="kbsBaseForm" property="authorDeptName" scope="request"/>&nbsp;
                        <bean:write name="kbsBaseForm" property="authorName" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        ����
                    </td>
                    <td width="70%" height="25">
                        <bean:define id="zuozhe" name="kbsBaseForm" property="zuozhe" type="java.lang.String"/>
                        <%=StaticMethod.getGBString(StaticMethod.null2String(zuozhe, ""))%>
                    </td>

                </tr>

                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        ����ʱ��
                    </td>
                    <td width="70%" height="25">
                        <bean:write name="kbsBaseForm" property="publicTime" scope="request"/>
                    </td>
                </tr>
                <%if (flag.equals("1")) {%>

                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        Ͷ������
                    </td>
                    <td width="70%" height="25">

                        <bean:write name="kbsBaseForm" property="applyName" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">

                    <td width="30%" height="25" class="clsfth">&nbsp;
                        �û�Ʒ��
                    </td>
                    <td width="70%" height="25">

                        <bean:write name="kbsBaseForm" property="custName" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        Ͷ������
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
                        Ͷ��ԭ�����
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
                        רҵ����
                    </td>
                    <td width="70%" height="25">

                        <bean:write name="kbsBaseForm" property="specialtyName" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        ��������
                    </td>
                    <td width="70%" height="25">
                        <bean:write name="kbsBaseForm" property="faultName" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        ��������
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
                        ����ԭ�����
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
                        �������
                    </td>
                    <td width="70%" height="25" style="WORD-BREAK: break-all">

                        <% JUBB ubb = new JUBB();
                            //System.out.println(description);
                            String body = StaticFunction.htmlEncode(kbsform.getDeal());
                            body = ubb.getAll(StaticMethod.null2String(body));
                            out.println(body);%>
                    </td>
                </tr>
                <% if (!status.equalsIgnoreCase("1")) {%>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        �����
                    </td>
                    <td width="70%" height="25">
                        <bean:write name="kbsBaseForm" property="auditorDeptName" scope="request"/>&nbsp;
                        <bean:write name="kbsBaseForm" property="auditorName" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        ���ʱ��
                    </td>
                    <td width="70%" height="25">

                        <bean:write name="kbsBaseForm" property="auditTime" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        ��˽���
                    </td>
                    <td width="70%" height="25" style="WORD-BREAK: break-all">

                        <bean:write name="kbsBaseForm" property="auditGrade" scope="request"/>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        ������
                    </td>
                    <td width="70%" height="25" style="WORD-BREAK: break-all">

                        <bean:write name="kbsBaseForm" property="auditResult" scope="request"/>
                    </td>
                </tr>
                <%}%>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        ״̬
                    </td>
                    <td width="70%" height="25">
                        <%if (status.equalsIgnoreCase("0")) {%>�ݸ�<%
                    } else if (status.equalsIgnoreCase("1")) {
                    %>�����<%
                    } else if (status.equalsIgnoreCase("2")) {
                    %>ͨ��������<%
                    } else if (status.equalsIgnoreCase("3")) {
                    %>���޸�<%
                    } else if (status.equalsIgnoreCase("4")) {
                    %>������<%
                    } else if (status.equalsIgnoreCase("5")) {
                    %>ͣ��<%
                        }

                    %>
                    </td>
                </tr>
                <tr class="tr_show">
                    <td width="30%" height="25" class="clsfth">&nbsp;
                        ����
                    </td>
                    <td width="70%" height="25">
                        <eoms:attachment name="kbsBaseForm" property="attachName" scope="request" idField="attachName"
                                         appCode="kbs" viewFlag="Y"/>
                        <!-- <eoms:attachment name="kbsBaseForm" property="attachName" scope="request" idField="attachName" appCode="worksheet" viewFlag="Y"/> -->
                    </td>
                </tr>


            </logic:present>
        </table>
        <br/>
        <% if (status.equalsIgnoreCase("1")) {
            String userId = saveSessionBeanForm.getWrf_UserID();
            String userName = saveSessionBeanForm.getWrf_UserName();
            String deptName = saveSessionBeanForm.getWrf_DeptName();
            String checkTime = StaticMethod.getLocalString();

            if (!pageFlag.equalsIgnoreCase("1")) {
        %>
        <%=pageFlag%>
        <table border="0" width="95%" cellspacing="0">

            <tr>
                <td width="100%" class="table_title" align="center">
                    &nbsp;&nbsp;��˰���
                </td>
            </tr>
        </table>
        <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
            <tr class="tr_show">
                <td width="15%" height="25" class="clsfth">&nbsp;
                    �����
                </td>
                <td width="400" height="25">
                    <%=deptName%>&nbsp;&nbsp;<%=userName%>
                </td>
                <td width="15%" height="25" class="clsfth">&nbsp;
                    ���ʱ��
                </td>
                <td width="400" height="25">
                    <%=checkTime%>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="15%" height="25" class="clsfth">&nbsp;��˽���
                </td>
                <td width="650" height="25" colspan="3">
                    <select name="auditgrade">
                        <option value="����" selected="selected">����</option>
                        <option value="һ�Ƚ�">һ�Ƚ�</option>
                        <option value="���Ƚ�">���Ƚ�</option>
                        <option value="���Ƚ�">���Ƚ�</option>
                        <option value="�ĵȽ�">�ĵȽ�</option>
                    </select>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="15%" height="25" class="clsfth">&nbsp;������
                </td>
                <td width="650" height="25" colspan="3">
                    <TEXTAREA name="auditresult" rows="4" cols="110"></TEXTAREA>
                </td>
            </tr>
        </table>
        <%
                }
            }
        %>
        <table border="0" width="95%" cellspacing="0">
            <tr>
                <td width="100%" height="32" align="right">
                    <bean:define id="id" name="kbsBaseForm" property="id" type="java.lang.Integer"/>
                    <input type="hidden" name="id" value=<%=id%>>
                    <%

                        String domainType = (StaticVariable.INFORM_DOMAIN_TYPE) + "";

                        if (status.equalsIgnoreCase("0")) {
                    %>

                    <input type="button" Class="clsbtn2" value="<bean:message key="label.remove" />"
                           onclick="return onRemove();">
                    <input type="button" Class="clsbtn2" value="�ύ" onClick="onCommit();">
                    <input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>"
                           onclick="history.back()">
                    <%
                    } else if (status.equalsIgnoreCase("1")) {
                        if (!pageFlag.equalsIgnoreCase("1")) {
                    %>
                    <input type="button" Class="clsbtn2" value="���ͨ��" onClick="onCheckPass();">&nbsp;
                    <input type="button" Class="clsbtn2" value="������" onClick="onCheckDeny();">&nbsp;
                    <%}%>
                    <input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>"
                           onclick="history.back()">
                    <%
                    } else if (status.equalsIgnoreCase("2")) {%>
                    <input type="button" Class="clsbtn2" value="ͣ��" onClick="onCheckStop();">
                    <input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>"
                           onclick="history.back()">

                    <%
                    } else if (status.equalsIgnoreCase("4")) {%>
                    <input type="button" Class="clsbtn2" value="�ύ" onClick="onCommit();">
                    <input type="button" Class="clsbtn2" value="<bean:message key="label.remove"/>"
                           onclick="return onRemove();">
                    <input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>"
                           onclick="history.back()">
                    <%
                    } else if (status.equalsIgnoreCase("5")) {%>
                    <input type="button" Class="clsbtn2" value="�ύ" onClick="onCommit();">
                    <input type="button" Class="clsbtn2" value="<bean:message key="label.remove"/>"
                           onclick="return onRemove();">
                    <input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>"
                           onclick="history.back()">
                    <%
                        }
                    %>
                </td>
            </tr>
        </table>


        <logic:notPresent name="kbsBaseForm" scope="request">
            <bean:message key="error.notfound"/>
        </logic:notPresent>
    </form>
</center>
</div>

</body>

</html>
