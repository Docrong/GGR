<%@ include file="../../../common/taglibs.jsp" %>
<%@ include file="../../../common/header_eoms_form.jsp" %>
<%@ page
        import="java.util.*,java.lang.*,org.apache.struts.util.*,com.boco.eoms.common.controller.*,com.boco.eoms.duty.model.*,com.boco.eoms.duty.util.StaticDutycheck" %>
<%
    String roomName = request.getAttribute("roomName").toString();
    String roomId = (String) request.getAttribute("roomId");
    String workserial = (String) request.getAttribute("workserial");
    String dutyType = (String) request.getAttribute("dutyType");
%>
<html>
<head>

    <title>
        <%--<bean:message key="TawRmRecord.dutyrecord" />
    --%>
    </title>

    <SCRIPT LANGUAGE=javascript>
        <!--
        function fun_save() {
            document.all.tawRmRecordForm.action = "../TawRmRecord/saveper.do";
            document.all.tawRmRecordForm.submit;
        }

        function fun_delete(record_id) {
            document.all.tawRmRecordForm.action = "../TawRmRecord/deleteper.do?record_id=" + record_id;
            document.all.tawRmRecordForm.submit;
        }

        function fun_update(record_id) {
            document.all.tawRmRecordForm.action = "../TawRmRecord/updateper.do?record_id=" + record_id;
            document.all.tawRmRecordForm.submit;
        }

        function fun_refresh() {
            document.all.tawRmRecordForm.action = "../TawRmRecord/record.do";
            document.all.tawRmRecordForm.submit;
        }

        //-->
    </SCRIPT>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="clssclbar" onload="tawRmRecordForm.Dutyrecord.focus();">
<html:form method="post" action="/TawRmRecord/record">
    <html:hidden property="strutsAction" value="1"/>
    <html:hidden property="id"/>
    <table width="100%" height="100%">
        <tr>
            <td align="center" valign="top">
                <TABLE cellSpacing="0" cellPadding="0" width="95%"
                       borderColorLight="#709FD5" borderColorDark="#ffffff"
                       align="center" bgColor="#f3f3f3" border="1" class="listTable">
                    <TR>

                        <TD align="center" class="label" colSpan="6">
                            <bean:message key="TawRmRecord.dutyrecord7"/>
                        </TD>
                    </TR>
                    <TR>
                        <TD colSpan="6" class="label">
                            <IFRAME ID=IFrame2 FRAMEBORDER=0 width="100%" height=100
                                    SCROLLING=YES SRC='../../duty/TawRmRecord/search7days.do'>
                            </IFRAME>
                        </TD>
                    </TR>
                </table>

                <TABLE cellSpacing="0" cellPadding="0" width="95%"
                       borderColorLight="#709FD5" borderColorDark="#ffffff"
                       align="center" bgColor="#f3f3f3" border="1" class="listTable">
                    <TR>

                        <TD align="center" class="label" colSpan="6">
                            <bean:message key="TawRmRecord.fineRecord"/>
                        </TD>
                    </TR>
                    <TR>
                        <TD colSpan="6" class="clsfth">
                            <IFRAME ID=IFrame1 FRAMEBORDER=0 width="100%" height=520
                                    SCROLLING=YES
                                    SRC='../../duty/TawRmRecord/recordmain.do?WORKSERIAL=<bean:write name="tawRmRecordForm" property="id"/>'>
                            </IFRAME>
                        </TD>
                    </TR>

                    <c:choose>
                        <c:when test="${requestScope['tawRmRecordForm'].sheetCount >0 }">
                            <TR>
                                <TD align="center" class="label" colSpan="6">
                                    <bean:message key="TawRmRecord.selftable"/>
                                </TD>
                            </TR>
                            <tr class="tr_show">
                                <td width="10%" class="label">
                                    <bean:message key="TawRmRecord.writeDuty"/>
                                </td>
                                <td align="center" colspan="6">
                                    <!-- EOMS_J2EE/htmlservlet?sheet_id=9&action=insert&reaction=/TawMemo/sentlist.do-->
                                    <logic:iterate id="tawRmAddonsTable" name="SHEETLIST"
                                                   type="com.boco.eoms.duty.model.TawRmAddonsTable">
                                        <%
                                            String sheetId = String.valueOf(tawRmAddonsTable
                                                    .getId());
                                            StaticDutycheck check = new StaticDutycheck();
                                            String url = check.getUrl(sheetId, workserial);
                                        %>

                                        [ <a
                                            href='<%=request.getContextPath()%>/duty/manager/ntko/showDetail.jsp?id=<%=sheetId%>&url=<%=url%>&path=<%=tawRmAddonsTable.getUrl()%>'><bean:write
                                            name="tawRmAddonsTable" property="name" scope="page"/> </a>]
                                    </logic:iterate>
                                </td>
                            </tr>
                        </c:when>
                    </c:choose>

                    <c:choose>

                        <c:when test="${requestScope['tawRmRecordForm'].realCount >0 }">

                            <tr class="tr_show">
                                <td width="10%" class="label">
                                    <bean:message key="TawRmRecord.lookDuty"/>
                                </td>
                                <td align="center" colspan="6" class="label">
                                    <!-- htmlformservlet?sheet_id=9&action=list&id=1,2,3-->
                                    <logic:iterate id="tawDutySheet" name="FILLEDSHEETLIST"
                                                   type="com.boco.eoms.duty.model.TawDutySheet">
                                        <%
                                            String sheetId = String.valueOf(tawDutySheet
                                                    .getSheetId());
                                            String bocoIds = String.valueOf(tawDutySheet
                                                    .getStrBocoId());
                                            String path = String.valueOf(tawDutySheet.getUrl());
                                        %>

                                        [ <a
                                            href='<%=request.getContextPath()%>/duty/manager/ntko/showDetail.jsp?id=<%=sheetId%>&url=<%=path%>&path=<%=path%>'><bean:write
                                            name="tawDutySheet" property="name" scope="page"/> </a>]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </logic:iterate>
                                </td>
                            </tr>
                        </c:when>

                    </c:choose>

                    <c:choose>
                        <c:when test="${requestScope['tawRmRecordForm'].cycleCount >0 }">
                            <TR>
                                <TD align="center" class="label" colSpan="6">
                                    <bean:message key="TawRmRecord.cycletable"/>
                                </TD>
                            </TR>
                            <%
                                int sell = Integer.parseInt((String) request
                                        .getAttribute("sell")) + 1;
                                for (int i = 0; i < sell; i++) {
                                    int iterator = i + 1;
                            %>
                            <tr class="tr_show">
                                <td width="10%" class="label">
                                    <bean:message key="label.di"/>
                                    <%=i + 1%>
                                    <bean:message key="label.cycle"/>

                                </td>
                                <td align="center" colspan="6">
                                    <!-- htmlformservlet?sheet_id=9&action=list&id=1,2,3-->
                                    <logic:iterate id="tawRmCycleTable" name="CYCLELIST"
                                                   type="com.boco.eoms.duty.model.TawRmCycleTable">
                                        <% int flag = 0;
                                            String id = String.valueOf(tawRmCycleTable
                                                    .getId());
                                            StaticDutycheck check = new StaticDutycheck();
                                            String url = check.getCycleUrl(id, workserial,
                                                    iterator + "");
                                            String path = String.valueOf(tawRmCycleTable
                                                    .getUrl());
                                            if (url != "") {
                                                path = url;
                                                flag = 1;
                                            }
                                        %>

                                        <% if (flag == 0) {%><font color="red">[</font><%} else { %>[<%} %><a
                                            href='<%=request.getContextPath()%>/duty/manager/TawRmCycleTable/ntko/showDetail.jsp?id=<%=id%>&url=<%=url%>&sell=<%=sell%>&iterator=<%=iterator%>&path=<%=path%>'><bean:write
                                            name="tawRmCycleTable" property="name"
                                            scope="page"/> </a> <% if (flag == 0) {%><font
                                            color="red">]</font><%} else { %>]<%} %>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </logic:iterate>
                                </td>
                            </tr>
                            <%
                                }
                            %>

                        </c:when>
                    </c:choose>

                    <TR>
                        <TD align="center" class="label" colSpan="6">
                            <bean:message key="TawRmRecord.dutyrecord"/>

                        </TD>
                    </TR>
                    <TR>
                        <TD align="center" class="label">
                            <bean:message key="TawRmRecord.recordName"/>

                        </TD>
                        <TD align="center" class="label">
                            <bean:message key="TawRmRecord.recordType"/>

                        </TD>
                        <TD align="center" class="label">
                            <bean:message key="TawRmRecord.recordTime"/>

                        </TD>
                        <TD align="center" class="label">
                            <bean:message key="TawRmRecord.recordContant"/>

                        </TD>
                        <TD align="center" class="label">
                            <bean:message key="TawRmRecord.isCompate"/>

                        </TD>
                        <TD align="center" class="label">
                            <bean:message key="TawRmRecord.operation"/>

                        </TD>
                    </TR>


                    <%
                        Vector vecPerRecords = new Vector();
                        Vector vecPerRecord = null;
                        String record_id = "";
                        String Workserial = "";
                        String Recordtime = "";
                        String DutyMan = "";
                        String DutyManName = "";
                        String Dutyrecord = "";
                        String url = "";
                        String typename = "";
                        int complete_flag = 0;
                        vecPerRecords = (Vector) request.getAttribute("vecPerRecords");
                        for (int i = 0; i < vecPerRecords.size(); i++) {
                            vecPerRecord = new Vector();
                            vecPerRecord = (Vector) vecPerRecords.elementAt(i);
                            record_id = String.valueOf(vecPerRecord.elementAt(0));
                            Workserial = String.valueOf(vecPerRecord.elementAt(1));
                            Recordtime = String.valueOf(vecPerRecord.elementAt(2));
                            DutyMan = String.valueOf(vecPerRecord.elementAt(3));
                            DutyManName = String.valueOf(vecPerRecord.elementAt(4));
                            Dutyrecord = String.valueOf(vecPerRecord.elementAt(5));
                            complete_flag = Integer.parseInt(String
                                    .valueOf(vecPerRecord.elementAt(6)));
                            url = String.valueOf(vecPerRecord.elementAt(7));
                            url = "../" + "../" + "../" + url;
                            //System.out.println("sss"+url);
                            typename = String.valueOf(vecPerRecord.elementAt(8));
                    %>
                    <TR>
                        <TD align="center">
                            <%=DutyManName%>
                        </TD>
                        <TD align="center">
                            <%=typename%>
                        </TD>
                        <TD align="center">
                            <%=Recordtime%>
                        </TD>
                        <TD align="center">
                                <%--<%
                                            if (url == null || "".equals(url.trim())
                                            || "null".equals(url.trim())) {
                                %>
                                --%><textarea rows="2" name="Dutyrecord<%=record_id%>" cols="30"
                                              type="_moz"><%=Dutyrecord%>
									</textarea>

                        </TD>
                        <TD align="center">
                            <select id='complete_flag' name='complete_flag<%=record_id%>'>
                                <option value='0' <%if (complete_flag == 0) {%> selected <%}%>>
                                    <bean:message key="TawRmRecord.compltetNo"/>

                                </option>
                                <option value='1' <%if (complete_flag == 1) {%> selected <%}%>>
                                    <bean:message key="TawRmRecord.compltetYes"/>

                                </option>
                            </select>
                        </TD>
                        <TD align="center" class="label">
                            <input type=submit Class="button" name="load"
                                   value='<bean:message key="label.editas" />'
                                   onclick="javascript:fun_update(<%=record_id%>);">
                            &nbsp;
                            <input type=submit Class="button" name="load"
                                   value='<bean:message key="label.delete" />'
                                   onclick="javascript:fun_delete(<%=record_id%>);">
                            &nbsp;
                        </TD>
                            <%--<%
                            } else {
                            %>
                            <textarea rows="3"  cols="30" type="_moz" readonly><%=Dutyrecord%>
                            </textarea>


                            <TD align="center" class="clsfth">
                                <%
                                if (complete_flag == 0) {
                                %>
                                <bean:message key="TawRmRecord.compltetNo" />
                                <%
                                } else {
                                %>
                                <bean:message key="TawRmRecord.compltetYes" />
                                <%
                                }
                                %>

                            </TD>
                            <TD align="center" class="clsfth">
                                <input type=submit Class="clsbtn2" name="load"
                                    value='<bean:message key="label.edit" />'
                                    onclick="javascript:fun_update(<%=record_id%>);" disabled>
                                &nbsp;
                                <input type=submit Class="clsbtn2" name="load"
                                    value='<bean:message key="label.delete" />'
                                    onclick="javascript:fun_delete(<%=record_id%>);" disabled>
                                &nbsp;
                            </TD>
                            <%
                            }
                            %>

                        --%></TR>
                    <%
                            vecPerRecord = null;
                        }
                        vecPerRecords = null;
                    %>
                    <TR>
                        <TD align="center" colSpan="6">
                            <textarea rows="3" name="Dutyrecord" cols="100"></textarea>
                        </TD>
                        <input type="hidden" name="typename" value="<%=dutyType %>">
                    </TR>

                </TABLE>

                <table border="0" width="95%" cellspacing="1" cellpadding="1"
                       align=center>
                    <TR>
                        <TD align="center" colSpan="6">
                            <input type=submit Class="button" name="load"
                                   value='<bean:message key="label.add" />'
                                   onclick="javascript:fun_save();">
                            &nbsp;&nbsp;
                            <input type=submit Class="button" name="load"
                                   value='<bean:message key="label.rush" />'
                                   onclick="javascript:fun_refresh();">
                        </TD>
                    </TR>
                </TABLE>

            </td>
        </tr>
    </table>

</html:form>
</body>
</html>
