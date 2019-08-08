<%@ include file="../../../common/taglibs.jsp" %>
<%@ include file="../../../common/header_eoms_form.jsp" %>
<%@ page language="java" import="java.util.*" %>

<head>
    <style type="text/css">

        select.menuBox2 {
            width: 300px;
        }

    </style>
    <SCRIPT LANGUAGE=javascript>
        function checkform() {
            var workfrom = "";
            var workto = "";
            var lengthfrom = document.forms[0].applyfrom.length;
            var recever = document.forms[0].recever.value;

            if (lengthfrom == 0) {
                alert("${eoms:a2u('您不能申请')}");
                return false;
            } else {

                for (i = 0; i < lengthfrom; i++) {
                    workfrom = workfrom + "@" + document.forms[0].applyfrom.options[i].value;
                }
                document.forms[0].action = "${app}/duty/tawRmReplace.do?method=xsave&workapplyfrom=" + workfrom + "&recever=" + recever;
                document.forms[0].submit();
            }


        }

        function add_from(obj) {
            var selected_spr_text_from = "";

            var selected_spr_value_from = obj.value;


            var sel_sprlen = document.forms[0].applyfrom.options.length - 1;

            var exist_flag = 1;
            var j = 0;
            for (j = 0; j <= sel_sprlen; j++) {
                if (document.forms[0].applyfrom.options[j].value == selected_spr_value_from) {
                    exist_flag = 0;
                    break;
                }
            }

            if (exist_flag) {
                if (sel_sprlen >= 0) {
                    var i = 0;
                    var k = 0;
                    for (j = 0; j <= sel_sprlen; j++) {
                        if (selected_spr_value_from > document.forms[0].applyfrom.options[j].value) {
                            i = j;
                            k = 1;
                        }
                    }

                    if (k == 0) {
                        i--;
                    }
                    for (j = sel_sprlen; j > i; j--) {
                        selected_spr_text_from = document.forms[0].applyfrom.options[j].text;
                        var test1 = new Option(selected_spr_text_from);
                        test1.value = document.forms[0].applyfrom.options[j].value;
                        document.forms[0].applyfrom.options[j + 1] = test1;
                    }
                    var test1 = new Option(selected_spr_value_from);
                    test1.value = selected_spr_value_from;
                    document.all.applyfrom.options[i + 1] = test1;

                } else {
                    var test1 = new Option(selected_spr_value_from);
                    test1.value = selected_spr_value_from;
                    document.all.applyfrom.options[0] = test1;
                }

            } else {

                var object = document.forms[0].applyfrom;
                for (var i = 0; i < object.length; i++) {
                    var valueform = object.options[i].value;
                    if (valueform == selected_spr_value_from) {
                        document.forms[0].applyfrom.options[i] = null;
                    }
                }
            }

        }

    </SCRIPT>
</head>
<body leftmargin="0" topmargin="0">

<form method="post" action="save_batchApply.do">
    <input type="hidden" name="workapplyfrom" value=""/>
    <center>
        <br>
            <%
		String recever =(String)request.getAttribute("receiver");
		Vector vector_from = new Vector();
		Vector getWorkserial_from = new Vector();
		Vector getStarttime_from = new Vector();
		Vector getDutyman_from = new Vector();
		Vector getUsername_from = new Vector();
		vector_from = (Vector)request.getAttribute("VECTORFROM");
		getWorkserial_from = (Vector)vector_from.elementAt(0);
		getStarttime_from = (Vector)vector_from.elementAt(1);
		getDutyman_from = (Vector)vector_from.elementAt(2);
		getUsername_from = (Vector)vector_from.elementAt(3);
		int size = getWorkserial_from.size()*2;
		%>
        <table cellpadding="0" cellspacing="0" border="0" width="700">
            <tr>
                <input type="hidden" name="recever" value="<%=recever %>"/>
                <td width="100%" align="center" class="table_title">

                    &nbsp;${eoms:a2u('替班管理')}
                    <%
                        String saveflag = String.valueOf(request.getParameter("SAVEFLAG"));
                        if (saveflag.trim().equals("true")) {
                    %>
                    <font color="red"><bean:message key="TawRmChangeduty.savesuccess"/></font>
                    <%
                        }
                    %>

                </td>
            </tr>

            <tr>
                <td>

                    <table cellpadding="1" cellspacing="1" border="0" width="100%">
                        <tr align="center" valign="middle">
                            <td width="45%" valign="top">
                                <table cellpadding="1" cellspacing="1" border="0" width="100%" class="listTable">
                                    <tr class="tr_show">
                                        <td colspan=3 align="center" class="label"><b>
                                            &nbsp;${eoms:a2u('申请人已有班次')}</b>
                                        </td>
                                    </tr>
                                    <tr class="td_label">
                                        <td class="label"><bean:message key="TawRmChangeduty.select"/></td>
                                        <td class="label"><bean:message key="TawRmChangeduty.starttime"/></td>
                                        <td class="label"><bean:message key="TawRmChangeduty.dutyman"/></td>
                                    </tr>

                                    <%
                                        if (getWorkserial_from.size() > 0) {
                                            for (int i = 0; i < getWorkserial_from.size(); i++) {
                                    %>
                                    <tr class="tr_show">
                                        <td>
                                            <input type="checkbox" name="radio_from"
                                                   value="<%="from,"+String.valueOf(getWorkserial_from.elementAt(i))+","+String.valueOf(getDutyman_from.elementAt(i))+","+String.valueOf(getStarttime_from.elementAt(i))%>"
                                                   onclick="javascript:add_from(this)" ;>
                                        </td>
                                        <td>
                                            <%=String.valueOf(getStarttime_from.elementAt(i))%>
                                        </td>
                                        <td>
                                            <%=String.valueOf(getUsername_from.elementAt(i))%>
                                        </td>
                                    </tr>
                                    <%}%>
                                    <%} else {%>
                                    <tr class="tr_show">
                                        <td colspan=5>
                                            &nbsp;${eoms:a2u('此时间申请人没有班次。不能替班')}</b>
                                        </td>
                                    </tr>
                                    <%}%>
                                </table>
                            </td>
                            <td width="10%">>></td>
                            <td width="45%" valign="top" class="tr_show">
                                <table cellpadding="1" cellspacing="1" border="0" width="100%" class="listTable">
                                    <tr class="tr_show">
                                        <td colspan=3 align="center" class="label"><b>
                                            &nbsp;${eoms:a2u('希望替班班次')}</b>
                                        </td>
                                    </tr>
                                    <tr class="tr_show">
                                    </tr>
                                    <tr class="td_label">
                                        <td colspan=3 align="center" class="label"><b>
                                            <select name="applyfrom" size="<%=size%>" class="menuBox2">
                                            </select>
                                            </tb>
                                    </tr>
                                </table>

                            </td>


                        <tr align="center" valign="middle">

                            <table cellpadding="0" cellspacing="0" border="0" width="700" class="listTable">
                                <tr>

                                </tr>
                                <%if (getWorkserial_from.size() > 0) {%>
                                <tr>
                                    <td class="label"><b><bean:message key="TawRmChangeduty.applyreson"/></b>
                                    </td>
                                    <td>
                                        <textarea rows="2" name="reason" cols="80"></textarea>
                                    </td>

                                </tr>
                                <tr>
                                    <td class="label"><b>${eoms:a2u('备注')}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
                                    </td>
                                    <td>
                                        <textarea rows="2" name="remark" cols="80"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td height="32" align="left" class="label"><input type="button" class="button"
                                                                                      onclick="return checkform();"
                                                                                      value="<bean:message key="TawRmChangeduty.apply"/>">
                                    </td>
                                    <td class="label">&nbsp;</td>
                                </tr>
                                <%} else {%>
                                <tr class="table_title">
                                    <td> &nbsp;${eoms:a2u('无法申请替班')}</b> </td>
                                </tr>
                                <%}%>
                            </table>

                        </tr>
                    </table>

                        <%
int roomId = Integer.parseInt(String.valueOf(request.getAttribute("roomId")).trim());
String user_id = String.valueOf(request.getAttribute("USERID")).trim();
String time_from = String.valueOf(request.getAttribute("TIMEFROM")).trim();
%>
                    <input type="hidden" name="roomId" value="<%=roomId%>">
                    <input type="hidden" name="user_id" value="<%=user_id%>">
                    <input type="hidden" name="time_from" value="<%=time_from%>">

</form>
</body>
<%@ include file="/common/footer_eoms.jsp" %>
