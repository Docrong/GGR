<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ page language="java" import="com.boco.eoms.common.util.StaticMethod" %>

<%@ page
        import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.duty.controller.TawRmRecordForm,com.boco.eoms.duty.model.TawRmDutyfile,com.boco.eoms.common.util.StaticMethod,com.boco.eoms.duty.util.StaticDutycheck,com.boco.eoms.duty.model.TawRmRecord" %>
<SCRIPT language=javascript>
    function CheckDutyRecord(oSrc, args) {
        args.IsValid = (window.Form1.Dutyrecord.value.length <= 2000);
    }

    function CheckNotes(oSrc, args) {
        args.IsValid = (window.Form1.Notes.value.length <= 120);
    }

    function goto_transmit() {
        document.tawRmRecordForm.action = "<%=request.getContextPath()%>/duty/TawRmRecord/audit.do";
        document.tawRmRecordForm.submit();
    }
</SCRIPT>
<%
    String roomName = request.getAttribute("roomName").toString();
    List cutoversheetlist = (List) request
            .getAttribute("cutoversheetlist");
    List faultreportsheetlist = (List) request
            .getAttribute("faultreportsheetlist");
    List faultsheetlist = (List) request.getAttribute("faultsheetlist");
    List monthPlanVOList = (List) request.getAttribute("monthPlanVOList");
%>
<%
    String setStrutsAction = StaticMethod.nullObject2String(request.getAttribute("setStrutsAction"));
    System.out.println("setStrutsAction2:" + setStrutsAction);
    int statuss = 0;
    if (!setStrutsAction.equals(""))
        statuss = 1;
    System.out.println("statuss:" + statuss);

    String dutycheck = request.getAttribute("dutycheck").toString();
%>
<html>
<head>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmRecord/dodelete">
    <CENTER>
        <br>
        <table border="0" width="95%" cellspacing="1" cellpadding="1" class="listTable" align=center>
            <TBODY>
            <html:hidden property="id"/>
            <TR class="tr_show">
                <TD noWrap rowSpan="1" align="center" class="label">&nbsp;
                    <bean:message key="TawRmRecord.baseinfo"/></TD>
                <TD class="label"><bean:message key="TawRmSysteminfo.roomName"/></TD>
                <TD colSpan="3">
                    <INPUT id="RoomName" style="BACKGROUND-COLOR: lightgrey" readOnly maxLength=150
                           value="<%=roomName%>" name="RoomName">
            <TR class="tr_show">
                <TD class="label">
                    <bean:message key="TawRmRecord.hander"/>
                </TD>
                <TD colSpan="3">
                    <html:hidden property="hander"/>
                    <input name="hander" id="hander" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly=""
                           size="58" value=
                            <bean:write name="tawRmRecordForm" property="hander" scope="request"/>/>

                </TD>
            </TR>
            <TR class="tr_show">
                <TD class="label"><bean:message key="TawRmRecord.dutyman"/></TD>
                <TD colSpan="3">
                        <html:hidden property="dutyman"/>
                    <input name="dutyman" id="dutyman" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly=""
                           size="58" value=
                            <bean:write name="tawRmRecordForm" property="dutyman" scope="request"/>/>
            </TR>
            <TR class="tr_show">
                <TD class="label"><bean:message key="TawRmRecord.receiver"/></TD>
                <TD colSpan="3">
                    <html:hidden property="receiver"/>
                    <input name="receiver" id="receiver" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly=""
                           size="58" value=
                            <bean:write name="tawRmRecordForm" property="receiver" scope="request"/>/>
                </TD>
            </TR>
            <TR class="tr_show">
                <TD class="label"><bean:message key="TawRmRecord.receivertime"/></TD>
                <TD>
                    <input name="starttime" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" size="18" value=
                            <bean:write name="tawRmRecordForm" property="starttime" scope="request"/>/>
                </TD>
                <TD class="label"><bean:message key="TawRmRecord.flag"/></TD>
                <TD colspan="3">
                    <html:hidden property="flag"/>
                    <c:choose>
                        <c:when test="${requestScope['tawRmRecordForm'].flag == 0}">
                            <input name="flag" id="flag" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext"
                                   readOnly="" size="12" value='<bean:message key="TawRmRecord.compltetNo" />'/>
                        </c:when>
                        <c:when test="${requestScope['tawRmRecordForm'].flag == 1}">
                            <input name="flag" id="flag" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext"
                                   readOnly="" size="12" value='<bean:message key="TawRmRecord.compltetYes" />'/>
                        </c:when>
                    </c:choose>
                </TD>
            <TR class="tr_show">
                <TD noWrap rowSpan="2" align="center" class="label"><bean:message key="TawRmRecord.circumstance"/></TD>
                <TD class="label"><bean:message key="TawRmRecord.weather"/></TD>
                <TD><html:hidden property="weather"/><input name="tempStr" id="tempStr" type="text"
                                                            style="BACKGROUND-COLOR: lightgrey" class="clstext"
                                                            readOnly="" size="20" value=
                        <bean:write name="tawRmRecordForm" property="weather"
                                    scope="request"/>/>**
                </TD>
                <TD class="label"><bean:message key="TawRmRecord.temperature"/></TD>
                <TD><html:hidden property="temperature"/><input name="tempStr" id="tempStr" type="text"
                                                                style="BACKGROUND-COLOR: lightgrey" class="clstext"
                                                                readOnly="" size="5" value=
                        <bean:write name="tawRmRecordForm"
                                    property="temperature"
                                    scope="request"/>/>&nbsp;C
                </TD>
                <TD class="label"><bean:message key="TawRmRecord.wet"/></TD>
                <TD><html:hidden property="wet"/><input name="tempStr" id="tempStr" type="text"
                                                        style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly=""
                                                        size="5"
                                                        value="<bean:write name="tawRmRecordForm" property="wet" scope="request"/>"/>&nbsp;%
                </TD>

            </TR>
            <TR class="tr_show">
                <TD class="label"><bean:message key="TawRmRecord.clean"/></TD>
                <TD><html:hidden property="clean"/><input name="tempStr" id="tempStr" type="text"
                                                          style="BACKGROUND-COLOR: lightgrey" class="clstext"
                                                          readOnly="" size="8"
                                                          value="<bean:write name="tawRmRecordForm" property="clean" scope="request"/>"/>**
                </TD>
                <html:hidden property="clean1"/>
                <TD class="label"><bean:message key="TawRmRecord.conditioner"/></TD>
                <TD colspan="3"><html:hidden property="conditioner"/><input name="tempStr" id="tempStr" type="text"
                                                                            style="BACKGROUND-COLOR: lightgrey"
                                                                            class="clstext" readOnly="" size="8"
                                                                            value="<bean:write name="tawRmRecordForm" property="conditioner" scope="request"/>"/>**
                </TD>
            </TR>

            <TR class="tr_show">
                <TD noWrap align="center" class="label"><bean:message key="TawRmHangover.prehangquestion"/></TD>
                <TD colSpan="8">
                    <TEXTAREA id="hangQuestion0" name="hangQuestion0" rows=4 cols=80 style="BACKGROUND-COLOR: lightgrey"
                              readOnly=""><%=String.valueOf(request.getAttribute("strHangQuestion"))%></TEXTAREA>
                </TD>
            </TR>

            <tr class="tr_show">
                <td class="label">
                    <bean:message key="TawRmRecord.remark1"/>

                </td>
                <td colspan="6">
                    <%for (int i = 1; i <= StaticDutycheck.dutycheckcount; i++) {%>
                    <font title="<bean:write name='<%=StaticDutycheck.getDutycheckId(i)%>'/>"
                          color='<%out.print(dutycheck.lastIndexOf(StaticDutycheck.getDutycheckId(i))!=-1?"black":"red");%>'><%=StaticDutycheck.getDutycheckName(StaticDutycheck.getDutycheckId(i))%>
                    </font>&nbsp;
                    <%}%>
                </td>
            </tr>

            <TR class="tr_show">
                <TD noWrap align="center" class="label">
                    <bean:message key="TawRmRecord.accessories"/></TD>
                <%Vector vecDutyFile = (Vector) request.getAttribute("vecDutyFile");%>
                <TD colSpan=6>&nbsp;
                    <%
                        for (int i = 0; i < vecDutyFile.size(); i++) {
                            TawRmDutyfile tawRmDutyfile = (TawRmDutyfile) vecDutyFile.elementAt(i);
                            if (StaticMethod.getUploadType().equals("UpE")) {
                                out.print("<a href='../upload/" + java.net.URLEncoder.encode(tawRmDutyfile.getEncodename()) + "'>" + tawRmDutyfile.getFilename() + "</a>");
                            } else {
                                out.print("<a href='../upload/" + tawRmDutyfile.getEncodename() + "'>" + tawRmDutyfile.getFilename() + "</a>");
                            }
                            out.print("&nbsp;");
                        }
                    %>
                </TD>
            </TR>


            <TR class="tr_show">
                <TD noWrap align="center" class="label"><bean:message key="TawRmRecord.personrecord"/></TD>
                <TD colSpan=6>&nbsp;
                    <SPAN id=spanSubReocrd>
        <logic:iterate id="tawRmRecordSub" name="TAWRMRECORD_DUTYMAN" type="com.boco.eoms.duty.model.TawRmRecordSub">
            <html:link href="../TawRmRecordSub/view.do" target="blank" paramId="id" paramName="tawRmRecordSub"
                       paramProperty="id">
                <bean:write name="tawRmRecordSub" property="dutyman" scope="page"/>
            </html:link>
        </logic:iterate>&nbsp;
      </SPAN></TD>
            </TR>
            <TR class="tr_show">
                <TD noWrap align="center" class="label"><bean:message key="TawRmRecord.workrecord"/></TD>
                <TD noWrap align=left colSpan=6><TEXTAREA id="dutyrecord" name="dutyrecord" rows=6 cols=80
                                                          style="BACKGROUND-COLOR: lightgrey" readOnly=""><bean:write
                        name="tawRmRecordForm" property="dutyrecord" scope="request"/></TEXTAREA>
                    <SPAN id=Customvalidator1 style="DISPLAY: none; COLOR: red"
                          clientvalidationfunction="CheckDutyRecord"
                          evaluationfunction="CustomValidatorEvaluateIsValid" display="Dynamic"
                          errormessage="<BR>"
                          controltovalidate="Dutyrecord"><BR></SPAN></TD>
            </TR>
            <TR class="tr_show">
                <TD noWrap align="center" class="label"><bean:message key="TawRmRecord.notes"/></TD>
                <TD colSpan=6><TEXTAREA id=notes name=notes rows=4 cols=80 style="BACKGROUND-COLOR: lightgrey"
                                        readOnly=""><bean:write name="tawRmRecordForm" property="notes"
                                                                scope="request"/></TEXTAREA>
                    <SPAN id=Customvalidator2 style="DISPLAY: none; COLOR: red"
                          clientvalidationfunction="CheckNotes"
                          evaluationfunction="CustomValidatorEvaluateIsValid" display="Dynamic"
                          errormessage="<BR>" controltovalidate="Notes"><BR></SPAN>
                </TD>
            </TR>
            <TR>


                        <%if(statuss ==1){%>
            <TR class="tr_show">
                <TD noWrap align="center" class="label"><bean:message key="TawRmRecord.auContent"/></TD>
                <TD colSpan=6>
      <textarea id="notes" name="notes" rows=4 cols=80 style="BACKGROUND-COLOR: lightgrey" readonly type="_moz">
      <bean:write name="tawRmRecordForm" property="auContent" scope="request"/>
      </textarea>
                </TD>
            </TR>
            <%}%>

            <%
                if (statuss != 1) {
            %>
            <TR class="tr_show">
                <TD noWrap align="center" class="label"><bean:message key="TawRmRecord.auContent"/></TD>
                <TD colSpan=6>
                    <logic:equal name="AUDITPRIV" value="1">
                        <textarea id="auContent" name="auContent" rows=4 cols=80></textarea>
                    </logic:equal>
                    <logic:notEqual name="AUDITPRIV" value="1">
        <textarea id="notes" name="notes" rows=4 cols=80 style="BACKGROUND-COLOR: lightgrey" readonly type="_moz">
          <bean:write name="tawRmRecordForm" property="auContent" scope="request"/>
        </textarea>
                    </logic:notEqual>
                </TD>
                <%}%>
            </TR>

            <!-- add by sunshengtai start -->
            <tr class="tr_show">
                <td class="label" rowspan="4" valign="middle">
                    <bean:message key="TawRmRecordSub.watchSummary"/>
                </td>
                <td class="label">
                    <bean:message key="TawRmRecordSub.importantNetFault"/>
                </td>
                <td colspan="2">
			<textarea id="importantnetfault" name="importantnetfault" rows="3" cols="71" readonly="readonly"
                      style="BACKGROUND-COLOR: lightgrey"><bean:write name="tawRmRecordForm"
                                                                      property="importantnetfault" scope="request"/>
			</textarea>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <bean:message key="TawRmRecordSub.importantSocietyDisaster"/>
                </td>
                <td colspan="2">
			<textarea id="importantsocietydisaster" name="importantsocietydisaster" rows="3" cols="71"
                      readonly="readonly" style="BACKGROUND-COLOR: lightgrey"><bean:write name="tawRmRecordForm"
                                                                                          property="importantsocietydisaster"
                                                                                          scope="request"/>
			</textarea>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <bean:message key="TawRmRecordSub.needCorrespond"/>
                </td>
                <td colspan="2">
			<textarea id="needcorrespond" name="needcorrespond" rows="3" cols="71" readonly="readonly"
                      style="BACKGROUND-COLOR: lightgrey"><bean:write name="tawRmRecordForm" property="needcorrespond"
                                                                      scope="request"/>
			</textarea>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <bean:message key="TawRmRecordSub.handOverProceeding"/>
                </td>
                <td colspan="2">
			<textarea id="handoverproceeding" name="handoverproceeding" rows="3" cols="71" readonly="readonly"
                      style="BACKGROUND-COLOR: lightgrey"><bean:write name="tawRmRecordForm"
                                                                      property="handoverproceeding" scope="request"/>
			</textarea>
                </td>
            </tr>
            <!-- add by sunshengtai end -->

            <!--TR class="tr_show">
					<TD  class="label" colSpan="7" align="center">
						${eoms:a2u('值班期间处理的工单及作业计划')}
					</TD></TR>
 <TR class="tr_show">
					<TD  class="label">
						${eoms:a2u('故障')}
					</TD>
					<TD  colSpan="6" class="label">
					<%
					if(faultsheetlist!=null&&faultsheetlist.size()>0){
					for(int i=0;i<faultsheetlist.size();i++){
					TawRmRecord tawRmRecord =new TawRmRecord();
					tawRmRecord=(TawRmRecord)faultsheetlist.get(i);
					String url=tawRmRecord.getSheetUrl();
									%>
					
					[<%=url%>]<br>
										<%}}%></TD>
					</TR>
					<TR class="tr_show">
					<TD  class="label">
						${eoms:a2u('作业计划记录')}
					</TD>
					<TD  colSpan="6" class="label">
					<%
					if(monthPlanVOList!=null&&monthPlanVOList.size()>0){
					for(int i=0;i<monthPlanVOList.size();i++){
					TawRmRecord tawRmRecord =new TawRmRecord();
					tawRmRecord=(TawRmRecord)monthPlanVOList.get(i);
					String url=tawRmRecord.getSheetUrl();
									%>
					
					[<%=url%>]<br>
										<%}}%>
					</TD>
					</TR>
					<TR class="tr_show">
					<TD  class="label">
					${eoms:a2u('重大故障上报')}
					</TD>
					<TD  colSpan="6" class="label">
					<%
					if(faultreportsheetlist!=null&&faultreportsheetlist.size()>0){
					for(int i=0;i<faultreportsheetlist.size();i++){
					TawRmRecord tawRmRecord =new TawRmRecord();
					tawRmRecord=(TawRmRecord)faultsheetlist.get(i);
					String url=tawRmRecord.getSheetUrl();
									%>
					
					[<%=url%>]<br>
										<%}}%></TD>
					</TR>
					<TR class="tr_show">
					<TD  class="label">
						${eoms:a2u('割接')}
					</TD>
					<TD  colSpan="6" class="label">
					<%
					if(cutoversheetlist!=null&&cutoversheetlist.size()>0){
					for(int i=0;i<faultsheetlist.size();i++){
					TawRmRecord tawRmRecord =new TawRmRecord();
					tawRmRecord=(TawRmRecord)faultsheetlist.get(i);
					String url=tawRmRecord.getSheetUrl();
									%>
					
					[<%=url%>]<br>
										<%}}%></TD>
					</TR-->
            </TBODY>
        </TABLE>

        <logic:equal name="AUDITPRIV" value="1">
        <%if (statuss != 1) {%>
        <TR class="tr_show">
            <table border="0" width="95%" cellspacing="1" cellpadding="1" align="center">
                <TR>
                    <TD align="center" colSpan="6">
                        <input type="button" Class="clsbtn2" name="button"
                               value='<bean:message key="TawRmRecord.reject"/>' onclick="goto_transmit();">&nbsp;&nbsp;&nbsp;
                    </TD>
                </TR>
            </table>
                    <%}%>
            </logic:equal>

            <br>
    </CENTER>
</html:form>
</body>
</html>
