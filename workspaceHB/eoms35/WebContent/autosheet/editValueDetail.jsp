<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.boco.eoms.db.util.*" %>
<%@ page import="com.boco.eoms.common.util.*" %>
<%@ page import="com.boco.eoms.autosheet.util.*" %>

<html>
<head>
    <title>�޸������</title>
    <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    <script language="javascript" src="<%=request.getContextPath()%>/autosheet/control.js"></script>
</head>
<%

    String sheet_id = request.getParameter("sheet_id");
    String value_id = request.getParameter("value_id");
    SheetValue shValue = new SheetValue(sheet_id);
    CodeCommon comm = new CodeCommon();
    CodeTypeComsInfo codetype = CodeTypeComsInfo.getInstance();
    codetype.removeOSCache();
    codetype = CodeTypeComsInfo.getInstance();
    String url = (String) session.getAttribute("returnURL");
%>
<script LANGUAGE=javascript>

    function goto() {
        document.addEForm.action = "<%=url%>";
        document.addEForm.submit();
    }
</script>
<body bgcolor="#FFFFFF" text="#000000" onLoad="initEditValue()">
<div align="center">
    <p><b><font size="5">�޸������</font></b></p>
    <form name="addEForm" method="post" action="<%=request.getContextPath()%>/sheetvalueservlet"
          onsubmit="javascript: return checkValueInput();">
        <table cellspacing=0 cellpadding=0 border=1 width="760" bordercolordark="#FFFFFF" bordercolorlight="#66CCFF"
               bgcolor=#F3F3F3 align="center">
            <tr>
                <td width="15%" nowrap>
                    <div align="right">�ֶ���</div>
                </td>
                <td width="18%">
                    <input type="hidden" name="value_id" value="<%out.print(value_id);%>">
                    <input type="hidden" name="isedit" value="1">
                    <input type="text" name="vName" size="10" maxlength="200"
                           value="<%out.print(shValue.getVName(value_id));%>" onchange="isVariable(this)">
                </td>
                <td width="15%" nowrap>
                    <div align="right">��������</div>
                    <input type=hidden name=sheet_id value=<%=sheet_id%>>
                </td>
                <td width="18%">
                    <select name="isattach">
                        <%

                            if (shValue.getIsattach(value_id).equals("0"))
                                out.println("<option value=\"0\" selected>����</option>");
                            else
                                out.println("<option value=\"0\" >����</option>");

      /*     if(shValue.getIsattach(value_id).equals("1"))
            out.println("<option value=\"1\" selected>��ͷ</option>");
           else
             out.println("<option value=\"1\" >��ͷ</option>");

           if(shValue.getIsattach(value_id).equals("2"))
            out.println("<option value=\"2\" selected>��β</option>");
           else
             out.println("<option value=\"2\" >��β</option>");*/
                        %>
                    </select>
                </td>
                <td width="15%" nowrap>
                    <div align="right">˳���</div>
                </td>
                <td width="18%">
                    <input type="text" name="index" size="10" maxlength="10"
                           value="<%out.print(shValue.getIndex(value_id));%>">
                </td>
            </tr>
            <tr>
                <td width="15%" nowrap>
                    <div align="right">����Ƿ���</div>
                </td>
                <td width="18%">
                    <%
                        out.println("<input type=\"checkbox\" name=\"newLine\" value=\"1\"");
                        if (shValue.getNewLine(value_id).equals("1"))
                            out.print(" checked");
                        out.print(">");
                    %>
                </td>
                <td width="15%" nowrap>
                    <div align="right">�Ƿ�Ϊ��ѯ��</div>
                </td>
                <td width="18%">
                    <%
                        String isquery = "";
                        if (shValue.getIsQuery(value_id).equals("1"))
                            isquery = " checked";
                    %>
                    <input type="checkbox" name="isQuery" value="1" <%out.println(isquery);%>>
                </td>
                <td width="15%" nowrap>
                    <div align="right">��������</div>
                </td>
                <td width="18%">
                    <select name="attrID">
                        <%
                            SheetAttribute shAttribute = new SheetAttribute(sheet_id);
                            String temp = shValue.getAttrID(value_id);
                        %>
                        <option value="<%=shValue.getAttrID(value_id)%>"
                        "selected"><%=shAttribute.getAttrName(shValue.getAttrID(value_id))%></option>
                        <%
                            shAttribute.reset();
                            shValue.reset();
                            while (shAttribute.next()) {
                                if (!temp.equals(shAttribute.getAttrID()))
                                    out.println("<option value=" + shAttribute.getAttrID() + ">" + shAttribute.getAttrName() + "</option>");
                            }
                        %>

                    </select>
                </td>
            </tr>
            <tr>
                <td width="15%" nowrap>
                    <div align="right">��Ԫ����</div>
                </td>
                <td width="18%">
                    <input type="text" name="width" size="10" maxlength="10"
                           value="<%out.print(shValue.getWidth(value_id));%>"> ����
                </td>
                <td width="15%" nowrap>
                    <div align="right">��Ԫ��߶�</div>
                </td>
                <td width="18%">
                    <input type="text" name="height" size="10" maxlength="10"
                           value="<%out.print(shValue.getHeight(value_id));%>"> ����
                </td>
                <td width="15%" nowrap>
                    <div align="right">���п�</div>
                </td>
                <td width="18%">
                    <input type="text" name="colspan" size="10" maxlength="10"
                           value="<%out.print(shValue.getColspan(value_id));%>"><font color="red">**</font>
                </td>
            </tr>
            <tr>
                <td nowrap width="15%">
                    <div align="right">���п�</div>
                </td>
                <td width="18%">
                    <input type="text" name="rowspan" size="10" maxlength="10"
                           value="<%out.print(shValue.getRowspan(value_id));%>"><font color="red">**</font>
                </td>
                <td nowrap width="15%">
                    <div align="right">��Ԫ������</div>
                </td>
                <td width="18%">
                    <select name="align">
                        <%

                            if (shValue.getAlign(value_id).trim().equals("left"))
                                out.println("<option value=\"left\" selected>����</option>");
                            else
                                out.println("<option value=\"left\" >����</option>");

                            if (shValue.getAlign(value_id).trim().equals("center"))
                                out.println("<option value=\"center\" selected>����</option>");
                            else
                                out.println("<option value=\"center\" >����</option>");

                            if (shValue.getAlign(value_id).trim().equals("right"))
                                out.println("<option value=\"right\" selected>����</option>");
                            else
                                out.println("<option value=\"right\" >����</option>");
                        %>
                    </select>
                </td>
                <td nowrap width="15%">
                    <div align="right">��Ԫ���ݶ���</div>
                </td>
                <td width="18%">
                    <select name="valign">
                        <%
                            if (shValue.getValign(value_id).equals("middle"))
                                out.println("<option value=\"middle\" selected>����</option>");
                            else
                                out.println("<option value=\"middle\" >����</option>");

                            if (shValue.getValign(value_id).equals("top"))
                                out.println("<option value=\"top\" selected>����</option>");
                            else
                                out.println("<option value=\"top\" >����</option>");

                            if (shValue.getValign(value_id).equals("bottom"))
                                out.println("<option value=\"bottom\" selected>����</option>");
                            else
                                out.println("<option value=\"bottom\" >����</option>");
                        %>

                    </select>
                </td>
            </tr>
            <tr>
                <td width="15%" nowrap>
                    <div align="right">��Ԫ���Ƿ���</div>
                </td>
                <td width="18%">
                    <%
                        String nowrap = "";
                        if (shValue.getNowrap(value_id).equals(""))
                            nowrap = "checked";

                    %>
                    <input type="checkbox" name="nowrap" value="" <%out.print(nowrap);%> >
                </td>
                <td width="15%" nowrap>
                    <div align="right">������ɫ<font size=-2 color=red><b>��˫��ѡ��</b></font></div>
                </td>
                <td width="18%">
                    <input type="text" name="color" size="10" maxlength="10"
                           value="<%out.print(shValue.getColor(value_id));%>" ondblclick="openColorWin()">
                </td>
                <td width="15%" height="18" nowrap>
                    <div align="right">��������</div>
                </td>
                <td width="18%" height="18">
                    <select name="vStoreType" onchange=" vStoreTypeChange()">

                        <%

                            if (shValue.getVstoretype(value_id).equals("varchar(255)"))
                                out.println("<option value=\"varchar(255)\" selected>String</option>");
                            else
                                out.println("<option value=\"varchar(255)\" >String</option>");
                            if (StaticMethod.getDbType().equals(StaticVariable.ORACLE)) {
                                if (shValue.getVstoretype(value_id).equals("varchar2(4000)"))
                                    out.println("<option value=\"varchar2(4000)\" selected>LongString</option>");
                                else
                                    out.println("<option value=\"varchar2(4000)\" >LongString</option>");
                            } else if (StaticMethod.getDbType().equals(StaticVariable.INFORMIX)) {
                                if (shValue.getVstoretype(value_id).equals("char(4000)"))
                                    out.println("<option value=\"char(4000)\" selected>LongString</option>");
                                else
                                    out.println("<option value=\"char(4000)\" >LongString</option>");
                            }

                            if (shValue.getVstoretype(value_id).equals("date"))
                                out.println("<option value=\"date\" selected>Date</option>");
                            else
                                out.println("<option value=\"date\" >Date</option>");

                            if (shValue.getVstoretype(value_id).equals("datetime"))
                                out.println("<option value=\"datetime\" selected>DateTime</option>");
                            else
                                out.println("<option value=\"datetime\" >DateTime</option>");

                            if (shValue.getVstoretype(value_id).equals("integer"))
                                out.println("<option value=\"integer\" selected>Integer</option>");
                            else
                                out.println("<option value=\"integer\" >Integer</option>");

                            if (shValue.getVstoretype(value_id).equals("float"))
                                out.println("<option value=\"float\" selected>Float</option>");
                            else
                                out.println("<option value=\"float\" >Float</option>");

                            //  if(shValue.getVstoretype(value_id).equals("text"))
                            //    out.println("<option value=\"text\" selected>Text</option>");
                            //   else
                            //     out.println("<option value=\"text\" >Text</option>");
                        %>

                    </select>
                </td>
            </tr>
            <tr>
                <td width="15%" nowrap>
                    <div align="right">���������</div>
                </td>
                <td width="18%">

                    <select name="showtype" onchange="showtypeChange()">

                        <%
                            if (shValue.getShowtype(value_id).equals("0"))
                                out.println("<option value=\"0\" selected>TextField</option>");
                            else
                                out.println("<option value=\"0\" >TextField</option>");

                            if (shValue.getShowtype(value_id).equals("1"))
                                out.println("<option value=\"1\" selected>RadioBox</option>");
                            else
                                out.println("<option value=\"1\" >RadioBox</option>");

                            if (shValue.getShowtype(value_id).equals("2"))
                                out.println("<option value=\"2\" selected>CheckBox</option>");
                            else
                                out.println("<option value=\"2\" >CheckBox</option>");

                            if (shValue.getShowtype(value_id).equals("3"))
                                out.println("<option value=\"3\" selected>Select</option>");
                            else
                                out.println("<option value=\"3\" >Select</option>");

                            if (shValue.getShowtype(value_id).equals("4"))
                                out.println("<option value=\"4\" selected>MulSelect</option>");
                            else
                                out.println("<option value=\"4\" >MulSelect</option>");

                            if (shValue.getShowtype(value_id).equals("5"))
                                out.println("<option value=\"5\" selected>TextArea</option>");
                            else
                                out.println("<option value=\"5\" >TextArea</option>");

                            //  if(shValue.getShowtype(value_id).equals("6"))
                            //     out.println("<option value=\"6\" selected>MenuBox</option>");
                            //     else
                            //      out.println("<option value=\"6\" >MenuBox</option>");
                            if (shValue.getShowtype(value_id).equals("7"))
                                out.println("<option value=\"7\" selected>UserName</option>");
                            else
                                out.println("<option value=\"7\" >UserName</option>");
                        %>

                    </select>
                </td>
                <td width="15%" nowrap>
                    <div align="right">�����</div>
                </td>
                <td width="18%">
                    <input type="text" name="formWidth" size="10" maxlength="10"
                           value="<%out.print(shValue.getFormWidth(value_id));%>"> �ַ�
                </td>

                <td width="15%" nowrap>
                    <div align="right" id="str">���봮���</div>
                </td>
                <td width="18%">
                    <input type="text" name="formHeight" size="10" maxlength="10"
                           value="<%out.print(shValue.getFormHeight(value_id));%>"
                    > �ַ�<font color="red">**</font>
                </td>
            </tr>
            <tr>
                <td width="15%" nowrap>
                    <div align="right">�����������</div>
                </td>
                <td width="18%">

                    <select name="typeID">
                        <%
                            codetype.reset();
                            while (codetype.next()) {
                        %>
                        <option value="<%=codetype.getTypeID()%>"
                                <%
                                    if (codetype.getTypeID().equals(shValue.getTypeid(value_id))) {
                                        out.println(" selected");
                                    }
                                %>><%=codetype.getTypeName()%>
                        </option>
                        <%
                            }
                        %>
                        <option value="0" <% if (shValue.getTypeid(value_id).equals("0"))
                            out.println(" selected");%>>��
                        </option>
                    </select>

                </td>
                <td width="15%" height="18" nowrap>
                    <div align="right">ϵͳĬ��ֵ</div>
                </td>
                <td width="18%" height="18">

                    <input type="text" name="defaultval" size="10" maxlength="10"
                           value="<%out.print(shValue.getDefaultVal(value_id));%>">

                </td>
                </td>
                <td width="15%" height="18" nowrap>
                    <div align="right">��������</div>
                </td>
                <td width="18%" height="18">
                    <select name="vCtrl">
                        <% if (shValue.getVCtrl(value_id).equals("1"))
                            out.println("<option value=\"1\" selected>�����</option>");
                        else
                            out.println("<option value=\"1\" >�����</option>");

                            if (shValue.getVCtrl(value_id).equals("0"))
                                out.println("<option value=\"0\" selected>�ǿ�</option>");
                            else
                                out.println("<option value=\"0\" >�ǿ�</option>");
                        %>
                    </select>
                </td>
            </tr>
        </table>
        <table cellSpacing=0 cellPadding=0 border=0 width="760">
            <td width="15%" align="right">
                <input type="hidden" name="flag" value="update">
                <input type="Submit" name="Submit" value="�� ��">
                <input type="button" name="button1" value="�� ��" onclick="goto();">
            </td>
        </table>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
    </form>
</div>
</body>
</html>
