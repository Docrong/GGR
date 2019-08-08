<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.commons.db.bocopool.RecordSet" %>
<%@page import="java.util.List" %>
<%@page import="com.boco.eoms.common.util.StaticMethod" %>

<%
    String module = request.getParameter("module");
    //add by jintong
    String user_id = "";
    try {
        //edit by wangheqi 2.7 to 3.5
        TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm)
                request.getSession().getAttribute("sessionform");
        /*SaveSessionBeanForm saveSessionBeanForm =
            (SaveSessionBeanForm) request.getSession().getAttribute(
            "SaveSessionBeanForm");*/

        user_id = StaticMethod.null2String(saveSessionBeanForm.getUserid());
        //System.out.println(user_id + ",,,,,,");
    } catch (Exception ee) {
        ee.printStackTrace();
    }
    //????????????
    if (!user_id.equals("admin")) {%>
<script language="javascript">alert("<fmt:message key="autosheet.nopopedom"/>");
history.back()
</script>

<%
    }
    ////   end
%>

<head>
    <title>?????????????????</title>
    <meta http-equiv="Content-Type" content="text/html;">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    <script language="javascript">
        function isNull(varStr) {
            var varstr = varStr;
            if (varstr == "" || varstr == null) {
                alert("<fmt:message key="autosheet.xinghao"/>" + varStr + "<fmt:message key="autosheet.notnull"/>");
                return false;
            }
            return true;
        }

        function isNumber(inputVal) {

            inputStr = inputVal;
            if (inputStr.length == 1) {
                if ((inputStr.charAt(0) < '0') || (inputStr.charAt(0) > '9')) {
                    alert("<fmt:message key="autosheet.shuruxiang"/>:" + inputStr + "<fmt:message key="autosheet.isnumber"/>");
                    return false;
                }
            } else if (inputStr.length > 1) {
                if ((inputStr.charAt(0) < '0') || (inputStr.charAt(0) > '9')) {
                    alert("<fmt:message key="autosheet.shuruxiang"/>:" + inputStr + "<fmt:message key="autosheet.isnumber"/>");
                    return false;
                }
                for (n = 1; n < inputStr.length; n++) {
                    if ((inputStr.charAt(n) != '.')) {
                        if ((inputStr.charAt(n) > '9') || (inputStr.charAt(n) < '0')) {
                            alert("<fmt:message key="autosheet.shuruxiang"/>:" + inputStr + "<fmt:message key="autosheet.isnumber"/>");
                            return false;
                        }
                    }
                }

            }
            return true;
        }

        function checkInput() {
            var sh_cname = document.addEForm.sh_cname.value;
            var module_id = document.addEForm.module_id.value;
            var width = document.addEForm.width.value;
            var height = document.addEForm.height.value;
            if (isNull(sh_cname) && isNumber(width) && isNumber(height)) {
                return true;
            } else {
                return false;
            }
        }

        function goto() {
            document.addEForm.action = "./index.jsp";
            document.addEForm.submit();
        }

        var s = ["s1", "s2"];

        function setup() {
            for (i = 0; i < s.length - 1; i++)
                document.getElementById(s[i]).onchange = new Function("change(" + (i + 1) + ")");
            change(0);
        }

        function selectTr0() {
            document.all.lj.style.display = "inline"
            document.all.mk.style.display = "none";
        }

        function selectTr1() {
            document.all.lj.style.display = "none";
            document.all.mk.style.display = "inline";
        }

    </script>
</head>
<%
    session.setAttribute("index", "0");
%>
<p align="center"><b><font size="5"><fmt:message key="autosheet.bddz1"/></font></b></p>


<form name="addEForm" method="post" action="<%=request.getContextPath()%>/sheetnameservlet"
      onsubmit="javascript: return checkInput();">
    <div align="center">
        <center>
            <br>
            <table cellSpacing=0 cellPadding=0 border=1 width="760" bordercolordark="#FFFFFF" bordercolorlight="#66CCFF"
                   bgcolor=#F3F3F3 align="center">
                <tr>
                    <td>
                        <div align="right"><fmt:message key="autosheet.formcnname"/>
                        </div>
                    </td>
                    <td>
                        <input type="text" name="sh_cname" size="20" maxlength="200"><font color="red">**</font>
    </div>
    </td>
    <td>
        <div align="right"><fmt:message key="autosheet.formtype"/></div>
    </td>
    <td>
        <select name="para1" readonly>
            <option value="0"><fmt:message key="autosheet.cgb"/></option>
            <!--<option value="2">?????</option>
            <option value="3">???</option>-->
        </select>
        </div>
    </td>

    </tr>

    <tr>
        <td>
            <div align="right"><fmt:message key="autosheet.style"/></div>
        </td>
        <td>
            <select name="style">
                <option value="0"><fmt:message key="autosheet.hxxs"/></option>
                <!-- <option value="1">????</option>
                 <option value="2">????</option>-->
            </select>
            </div>
        </td>
        <td>
            <div align="right"><fmt:message key="autosheet.btbwkz"/>

            </div>
        </td>
        <td>
            <select name="isattach">
                <option value="0"><fmt:message key="autosheet.bt"/></option>
                <!--<option value="0">????</option>
                <option value="1">?+??</option>
                <option value="2">?+??+?</option>
                <option value="3">??+?</option>-->
            </select>
            </div>
        </td>
    </tr>
    <tr>
        <td width="23%">
            <div align="right"><fmt:message key="autosheet.width"/>
            </div>
        </td>
        <td width="23%">
            <input type="text" name="width" size="10" maxlength="10" value="600">
            </div>
        </td>
        <td width="27%">
            <div align="right"><fmt:message key="autosheet.height"/>
            </div>
        </td>
        <td width="27%">
            <input type="text" name="height" size="10" maxlength="10" value="800">
            </div>
        </td>
    </tr>
    <!--
     <tr>
        <td   width="25%">
          <div  align="right">????
          </div>
        </td>
      <td width="25%">
            <input type="text" name="columnwidth" style="BACKGROUND-COLOR: lightgrey" readonly="true" size="10" maxlength="10">
          </div>
        </td>
        <td width="25%">
          <div align="right">????
          </div>
        </td>
        <td width="25%">
            <input type="text" name="columnheight" style="BACKGROUND-COLOR: lightgrey" readonly="true" size="10" maxlength="10">
          </div>
        </td>
      </tr>-->
    <tr>
        <td width="23%">
            <div align="right"><fmt:message key="autosheet.zdfblj"/></font>
            </div>
        </td>
        <td width="23%">
            <fmt:message key="autosheet.yes"/><input type="radio" name="ifsetpath" value="0" onClick="selectTr0()"
                                                     checked="true">
            <fmt:message key="autosheet.no"/><input type="radio" name="ifsetpath" value="1" onClick="selectTr1()">
        </td>
        <td width="23%" nowrap>
            <div align="right"><fmt:message key="autosheet.isAttachment"/></div>
        </td>
        <td width="27%" nowrap>
            <input type="checkbox" name="isAttachment" value="1">
        </td>
    </tr>
    <tr id="lj">
        <td width="23%">
            <div align="right"><fmt:message key="autosheet.path"/></font>
            </div>
        </td>
        <td width="27%" colspan="3">
            <select name="module" id="s1" onchange="this.form.action='';this.form.submit();">
                <% RecordSet rs = new RecordSet();
                    String sql = "select app_id,app_name from taw_application where app_id <> 13 and app_id<>18 order by app_id";
                    rs.execute(sql);
                    while (rs.next()) {
                        if (module == null) {
                            System.out.println(module);
                %>
                <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%>
                </option>
                <%
                } else {
                %>
                <option value="<%=rs.getString(1)%>"<%
                    if (module.equals(rs.getString(1))) {
                        out.print("selected");
                    }
                %>><%=rs.getString(2)%>
                </option>
                <%
                        }
                    }
                %>
            </select>

            <select name="module_id" id="s2">
                <option></option>
                <%
                    com.boco.eoms.autosheet.servletoper.SheetModelSelect allTree = new com.boco.eoms.autosheet.servletoper.SheetModelSelect();
                    if (module == null) {
                        module = "11";
                    }
                    List alltree = allTree.Tree(module);
                    String[] Module = null;
                    for (int i = 0; i < alltree.size(); i++) {
                        Module = alltree.get(i).toString().split("_");
                %>
                <option value="<%=Module[0]%>"><%=Module[2]%>
                </option>
                <%
                    }
                %>
            </select>
            </div>
        </td>
    </tr>
    <tr id="mk" style="display:none">
        <td width="23%">
            <div align="right"><fmt:message key="autosheet.selectmodule"/></font>
            </div>
        </td>
        <td width="27%" colspan="3">
            <select name="modulemk">
                <% RecordSet rs1 = new RecordSet();
                    String sql1 = "select app_id,app_name from taw_application where app_id <> 13 and app_id<>18 order by app_id";
                    rs1.execute(sql1);
                    while (rs1.next()) {
                        if (module == null) {
                %>
                <option value="<%=rs1.getString(1)%>"><%=rs1.getString(2)%>
                </option>
                <%
                } else {
                %>
                <option value="<%=rs1.getString(1)%>"<%
                    if (module.equals(rs1.getString(1))) {
                        out.print("selected");
                    }
                %>><%=rs1.getString(2)%>
                </option>
                <%
                        }
                    }
                %>
            </select>
            </div>
        </td>
    </tr>
    </table>
    <table cellspacing=0 cellpadding=0 border=0 width="760">
        <td colspan="4">
            <div align="left">
                <fmt:message key="autosheet.zy1"/><br>
                &nbsp;&nbsp;&nbsp;<fmt:message key="autosheet.zy2"/>
            </div>
        </td>
        <td colspan="4">
            <div align="right">
                <input type="hidden" name="flag" value="new">
                <input type="Submit" name="nextstep" value="<fmt:message key="autosheet.next"/>">
                <input type="button" name="button1" value="<fmt:message key="autosheet.editform"/>" onclick="goto();">
            </div>
        </td>
    </table>
    </center>
    </div>
</form>
<%@ include file="/common/footer_eoms.jsp" %>
