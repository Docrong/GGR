<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.boco.eoms.db.util.*" %>
<%@ page import="com.boco.eoms.autosheet.util.*" %>
<%@ page import="com.boco.eoms.common.util.*" %>
<%
    String sheet_id = request.getParameter("sheet_id");
    SheetName shName = SheetName.getInstance();
    try {
        shName.setSheetName();

    } catch (Exception e) {
        e.printStackTrace();
    }
    String url = (String) session.getAttribute("returnURL");
%>
<html>
<head>
    <title>编辑表单基本属性</title>
    <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    <script language="javascript" src="<%=request.getContextPath()%>/autosheet/control.js"></script>
    <script language="javascript">

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
            document.addEForm.action = "<%=url%>";
            document.addEForm.submit();
        }
    </script>
</head>
<%
    session.setAttribute("index", "0");

%>
<body bgcolor="#FFFFFF" text="#000000">

<p align="center"><b><font size="5">编辑表单基本属性</font></b></p>


<form name="addEForm" method="post" action="<%=request.getContextPath()%>/sheetnameservlet"
      onsubmit="javascript: return checkInput();">
    <div align="center">
        <center>
            <br>
            <table cellSpacing=0 cellPadding=0 border=1 width="760" bordercolordark="#FFFFFF" bordercolorlight="#66CCFF"
                   bgcolor=#F3F3F3 align="center">
                <tr>
                    <td>
                        <div align="right">表单显示的中文名
                        </div>
                    </td>
                    <td>
                        <input type="text" name="sh_cname" size="20" maxlength="200"
                               value="<%out.print(shName.getSh_cname(sheet_id));%>"><font color="red">**</font>
    </div>
    </td>
    <td>
        <div align="right">表单类型
        </div>
    </td>
    <td>


        <select name="para1" readonly>
            <%
                if (shName.getPara1(sheet_id).trim().equals("0"))
                    out.println("<option value=\"0\" selected>常规表</option>");
                else
                    out.println("<option value=\"0\" >常规表</option>");

        /*   if(shName.getPara1(sheet_id).trim().equals("2"))
            out.println("<option value=\"2\" selected>一维线型表</option>");
           else
             out.println("<option value=\"2\" >一维线型表</option>");

           if(shName.getPara1(sheet_id).trim().equals("3"))
            out.println("<option value=\"3\" selected>二维表</option>");
           else
             out.println("<option value=\"3\" >二维表</option>");*/
            %>
        </select>
        </div>
    </td>

    </tr>

    <tr>
        <td>
            <div align="right">表单样式
            </div>
        </td>
        <td>

            <select name="style">
                <%
                    if (shName.getStyle(sheet_id).trim().equals("0"))
                        out.println("<option value=\"0\" selected>横向显示</option>");
                    else
                        out.println("<option value=\"0\" >横向显示</option>");

        /*   if(shName.getStyle(sheet_id).trim().equals("1"))
            out.println("<option value=\"1\" selected>纵向显示</option>");
           else
             out.println("<option value=\"1\" >纵向显示</option>");

           if(shName.getStyle(sheet_id).trim().equals("2"))
            out.println("<option value=\"2\" selected>矩阵显示</option>");
           else
             out.println("<option value=\"2\" >矩阵显示</option>");*/
                %>
            </select>
            </div>
        </td>
        <td>
            <div align="right">表头表尾控制

            </div>
        </td>
        <td>
            <select name="isattach">
                <%
                    if (shName.getIsattach(sheet_id).trim().equals("0"))
                        out.println("<option value=\"0\" selected>表体</option>");
                    else
                        out.println("<option value=\"0\" >表体</option>");

        /*   if(shName.getIsattach(sheet_id).trim().equals("1"))
            out.println("<option value=\"1\" selected>头+表体</option>");
           else
             out.println("<option value=\"1\" >头+表体</option>");

           if(shName.getIsattach(sheet_id).trim().equals("2"))
            out.println("<option value=\"2\" selected>头+表体+尾</option>");
           else
             out.println("<option value=\"2\" >头+表体+尾</option>");

           if(shName.getIsattach(sheet_id).trim().equals("3"))
            out.println("<option value=\"3\" selected>表体+尾</option>");
           else
             out.println("<option value=\"3\" >表体+尾</option>");*/
                %>
            </select>

            </div>
        </td>
    </tr>
    <tr>
        <td width="23%">
            <div align="right">表单象素宽
            </div>
        </td>
        <td width="23%">
            <input type="text" name="width" size="10" maxlength="10" value="<%out.print(shName.getWidth(sheet_id));%>">
            </div>
        </td>
        <td width="27%">
            <div align="right">表单象素高
            </div>
        </td>
        <td width="27%">
            <input type="text" name="height" size="10" maxlength="10"
                   value="<%out.print(shName.getHeight(sheet_id));%>">
            </div>
        </td>
    </tr>
    <!--
     <tr>
        <td   width="25%">
          <div  align="right">表体行数
          </div>
        </td>
      <td width="25%">
            <input type="text" name="columnwidth" style="BACKGROUND-COLOR: lightgrey" readonly="true" size="10" maxlength="10">
          </div>
        </td>
        <td width="25%">
          <div align="right">表体列数
          </div>
        </td>
        <td width="25%">
            <input type="text" name="columnheight" style="BACKGROUND-COLOR: lightgrey" readonly="true" size="10" maxlength="10">
          </div>
        </td>
      </tr>-->
    <tr>
        <td width="23%">
            <div align="right">所属模块</font>
            </div>
        </td>
        <td width="27%">
            <select name="module_id" disabled=true>
                <% RecordSet rs = new RecordSet();
                    String sql = "select app_id,app_name from taw_application where app_id in(11,15) order by app_id";
                    rs.execute(sql);
                    while (rs.next()) {
                        if (shName.getmodule_id(sheet_id).trim().equals(rs.getString(1)))
                            out.println("<option value=\"" + rs.getString(1) + "\" selected >" + rs.getString(2) + "</option>");
                        else
                            out.println("<option value=\"" + rs.getString(1) + "\" >" + rs.getString(2) + "</option>");
                    }%>

            </select>
            </div>
        </td>
        <td width="23%" nowrap>
            <div align="right">是否包含附件</div>
        </td>
        <td width="27%" nowrap>
            <%
                String isAttachment = "";
                if (shName.getIsattachment(sheet_id).equals("1"))
                    isAttachment = " checked";
            %>
            <input type="checkbox" name="isAttachment" value="1" <%out.print(isAttachment);%>>
        </td>
    </tr>
    </table>
    <table cellSpacing=0 cellPadding=0 border=0 width="760">
        <td colspan="4" align="right">
            <div>
                <input type="hidden" name="flag" value="edit">
                <input type="hidden" name="sheet_id" value="<%=sheet_id%>">
                <input type="Submit" name="Submit" value="修 改">
                <input type="reset" name="reset" value="取 消">
                <input type="button" name="button1" value="返 回" onclick="goto();">
            </div>
        </td>
    </table>
    </center>
    </div>
</form>
</body>
</html>
