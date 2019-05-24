<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<%@ page language="java" import ="com.boco.eoms.common.util.StaticMethod,java.util.*,java.lang.*"%>
 
<html>
<head>
<title> <bean:message key="TawRmRecord.exchange"/></title>
 
</head>
<body leftmargin="0" topmargin="0"> 

<%String strWorkSerial = String.valueOf(request.getAttribute("strWorkSerial"));%>
<%String strNextWorkSerial = String.valueOf(request.getAttribute("strNextWorkSerial"));%>
<%String strHQ = String.valueOf(request.getAttribute("strHQ"));%>

<html:form method="post" action="/TawRmRecord/submit">
    <html:hidden property="id" />
		<TABLE cellSpacing="0" cellPadding="0" width="600"  borderColorLight="#709FD5"  borderColorDark="#ffffff" align="center" bgColor="#f3f3f3" border="1" class="formTable">
                    <TR>
                      <TD>
                       <IFRAME ID=IFrame1  FRAMEBORDER=0 width="100%" height="130%" SCROLLING=YES SRC='../TawRmHangover/show.do?HANGWORKSERIAL=<%=strWorkSerial%>&RECEIVEWORKSERIAL=<%=strNextWorkSerial%>&strHQ=<%=strHQ%>'>
                       </IFRAME>
                      </TD>
                    </TR>
       </TABLE>
       
<TABLE cellSpacing="0" cellPadding="0" width="600"  borderColorLight="#709FD5"  borderColorDark="#ffffff" align="center" bgColor="#f3f3f3" border="1" class="formTable">
<TR> 
        <TD align="center" class="label" colSpan="6"><bean:message key="TawRmRecord.nextWork" /></TD>
</TR>
<TR>
        <TD align="center" class="label" ><bean:message key="TawRmRecord.recordName" /></TD>
        <TD align="center" class="label" ><bean:message key="TawRmRecord.recordType" /></TD>
        <TD align="center" class="label" ><bean:message key="TawRmRecord.recordTime" /></TD>
        <TD align="center" class="label" ><bean:message key="TawRmRecord.recordContant" /></TD>
        <TD align="center" class="label" ><bean:message key="TawRmRecord.isCompate" /></TD>
</TR>
<%
Vector vecPerRecords = new Vector();
Vector vecPerRecord =null;
String record_id = "";
String Workserial = "";
String Recordtime = "";
String DutyMan = "";
String DutyManName = "";
String Dutyrecord = "";
int complete_flag = 0;
String url="";
String typename="";

vecPerRecords = (Vector) request.getAttribute("vecPerRecords");
for (int i=0;i<vecPerRecords.size();i++){
vecPerRecord = new Vector();
vecPerRecord = (Vector) vecPerRecords.elementAt(i);
record_id = String.valueOf(vecPerRecord.elementAt(0));
Workserial = String.valueOf(vecPerRecord.elementAt(1));
Recordtime = String.valueOf(vecPerRecord.elementAt(2));
DutyMan = String.valueOf(vecPerRecord.elementAt(3));
DutyManName = String.valueOf(vecPerRecord.elementAt(4));
Dutyrecord = String.valueOf(vecPerRecord.elementAt(5));
complete_flag = Integer.parseInt(String.valueOf(vecPerRecord.elementAt(6)));
url = String.valueOf(vecPerRecord.elementAt(7));
typename = String.valueOf(vecPerRecord.elementAt(8));
%>
<TR>
        <TD align="center" class="label" ><%=DutyManName%>&nbsp;</TD>
        <TD align="center" class="label" ><%=typename%></TD>
        <TD align="center" class="label" ><%=Recordtime%>&nbsp;</TD>
		<TD align="center" class="label">
        <%if(url==null || "".equals(url.trim())){%>
           <textarea rows="2" cols="30" readonly><%=Dutyrecord%></textarea>
        <%}else{%>
              [<a href="<%=url%>" target="_blank"><%=Dutyrecord%></a>]
		<%}%>
        </TD>
	    <TD align="center" class="clsfth" ><%if (complete_flag==0){%>
	   <bean:message key="TawRmRecord.compltetNo" />
	    <%}else{%>
	   <bean:message key="TawRmRecord.compltetYes" />
	    <%}%></TD>
</TR>
<%
vecPerRecord =null;
}
vecPerRecords =null;
%>
</TABLE>



                <br>
		<TABLE cellSpacing="0" cellPadding="0" width="600"  borderColorLight="#709FD5"  borderColorDark="#ffffff" align="center" bgColor="#f3f3f3" border="1" class="formTable">
                    <TR>
                        <TD class="label" align="middle" valign="center" width="130" Nowrap><bean:message key="TawRmRecord.dutymanname"/></TD>
                        <TD><input name="HanderName" id="HanderName" type="text" style="WIDTH: 153px; HEIGHT: 22px; BACKGROUND-COLOR: lightgrey" size="19" readonly="" value="<%=StaticMethod.null2String(String.valueOf(request.getAttribute("DUTYMANNAME")))%>" />
                                <input name="Hander" id="Hander" type="hidden" value="<%=StaticMethod.null2String(String.valueOf(request.getAttribute("DUTYMANID")))%>" />
                        </TD>
                    </TR>
                    <TR>
                        <TD class="label"><bean:message key="TawRmRecord.handerpassword"/></TD>
                        <TD><input name="HanderPwd" id="HanderPwd" type="password" style="WIDTH: 153px; HEIGHT: 24px" maxlength="25" /></TD>
                    </TR>
                    <TR>
                        <TD class="label"><bean:message key="TawRmRecord.exflag"/></TD>
                        <TD><input type="radio" name="exFlag" value = "0"><bean:message key="TawRmRecord.alread"/>
							<input type="radio" name="exFlag" checked value = "1"><bean:message key="TawRmRecord.noread"/></TD>
                    </TR>

                    <%if (!strNextWorkSerial.equals("-1")){%>
                    <TR>
                        <TD class="label"><bean:message key="TawRmRecord.receivername"/></TD>
                        <TD><input name="ReceiverName" id="ReceiverName" type="text" style="WIDTH: 153px; HEIGHT: 22px; BACKGROUND-COLOR: lightgrey" size="18" readonly="" value="<%=StaticMethod.null2String(String.valueOf(request.getAttribute("RECEIVERMASTERNAME")))%>" />
                                <input name="Receiver" id="Receiver" type="hidden" value="<%=StaticMethod.null2String(String.valueOf(request.getAttribute("RECEIVERMASTERID")))%>" />
                        </TD>
                    </TR>
                    <TR>
                        <TD class="label"><bean:message key="TawRmRecord.receiverpassword"/></TD>
                        <TD><input name="ReceiverPwd" id="ReceiverPwd" type="password" style="WIDTH: 153px; HEIGHT: 24px" maxlength="25" /></TD>
                    </TR>
                    <%}else{%>
                    <TR>
                        <TD colspan="4" align = "center"><bean:message key="TawRmRecord.notnextworkserial"/></TD>
                    </TR>
                    <%}%>
                    <input type = "hidden" name = "boolNextWorkSerial" value = "<%=strNextWorkSerial%>">
                    <TR>
                        <TD colspan="2" align="middle" height="30">&nbsp;
                        <html:submit styleClass="button">
                        <bean:message key="TawRmRecord.exchange"/>
                        </html:submit>
                        </TD>
                    </TR>
                </TABLE>
                <br>
                <br>
		<TABLE cellSpacing="0" cellPadding="0" width="600"  borderColorLight="#709FD5"  borderColorDark="#ffffff" align="center" bgColor="#f3f3f3" border="1" class="formTable">
                   <TR>
                   	<TD class="label">
                               <bean:message key="TawRmRecord.hander"/>
                        </TD>
                   	<TD colSpan="3">
                           <html:hidden property="hander"  />
                           <input name="hander" id="hander" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="hander" scope="request"/>" />

                        </TD>
                   </TR>
                   <TR>
                   	<TD class="label"><bean:message key="TawRmRecord.dutyman"/></TD>
                   	<TD colSpan="3">
                   	<html:hidden property="dutyman" />
                   	<input name="dutyman" id="dutyman" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="dutyman" scope="request"/>" />
                   </TR>
                   <TR>
                   	<TD class="label"><bean:message key="TawRmRecord.receiver"/></TD>
                   	<TD colSpan="3">
                   	<html:hidden property="receiver" />
                   	<input name="receiver" id="receiver" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="receiver" scope="request"/>" />
                        </TD>
                   </TR>

                </TABLE>
            </TD>
        </TR>
    </TABLE>
</html:form>
</body>
</html>