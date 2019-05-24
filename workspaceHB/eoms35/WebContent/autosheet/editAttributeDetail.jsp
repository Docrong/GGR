<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.boco.eoms.db.util.*"%>
<%@ page import="com.boco.eoms.autosheet.util.*"%>
<%@ page import="com.boco.eoms.common.util.*"%>
<%
String sheet_id=request.getParameter("sheet_id");
String attr_id=request.getParameter("attr_id");


SheetAttribute shAttribute=new SheetAttribute(sheet_id);
String url=(String)session.getAttribute("returnURL");

%>

<html>
<head>
<title>修改表单输入框名称</title>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script language="javascript" src="<%=request.getContextPath()%>/autosheet/control.js"></script>
<script language="javascript">
  function checkInput(){

     var attrName=document.addEForm.attrName.value;
     var color   =document.addEForm.color.value;
     var colspan=document.addEForm.colspan.value;
     var rowspan=document.addEForm.rowspan.value;
     var width=document.addEForm.width.value;
     var height=document.addEForm.height.value;
     var index=document.addEForm.index.value;

    if(isNull(attrName)&&isNull(colspan)&&isNull(rowspan)&&isNull(index)&&isNumber(colspan)&&isNumber(index)&&isNumber(rowspan)&&isNumber(width)&&isNumber(height)&&isColor(color)){
      return true;
    }else{
      return false;
    }
  }

function goto()
{
      document.addEForm.action="<%=url%>";
      document.addEForm.submit();
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
<div align="center">
  <p><b><font size="5">修改表单输入框名称</font></b></p>

   <form name="addEForm" method="post" action="<%=request.getContextPath()%>/sheetattrservlet"  onsubmit="javascript: return checkInput();">
    <table cellspacing=0 cellpadding=0  border=1 width="779" bordercolordark="#FFFFFF" bordercolorlight="#66CCFF" bgcolor=#F3F3F3 align="center">
      <tr>
        <td width="12%" nowrap align="right">输入框名称</td>
        <td width="13%" nowrap>
          <input type="text" name="attrName" size="10" maxlength="200" value="<%out.print(shAttribute.getAttrName(attr_id));%>"><font color="red">**</font>
          <input type="hidden" name="attr_id" value="<%=attr_id%>">
        </td>
        <td width="12%" nowrap align="right">所属部分</td>
        <td width="13%" nowrap>
          <select name="isattach">

<%
   if(shAttribute.getIsattach(attr_id).equals("0"))
            out.println("<option value=\"0\" selected>表体</option>");
           else
             out.println("<option value=\"0\" >表体</option>");

         /*  if(shAttribute.getIsattach(attr_id).equals("1"))
            out.println("<option value=\"1\" selected>表头</option>");
           else
             out.println("<option value=\"1\" >表头</option>");

           if(shAttribute.getIsattach(attr_id).equals("2"))
            out.println("<option value=\"2\" selected>表尾</option>");
           else
             out.println("<option value=\"2\" >表尾</option>");*/
%>
          </select>
        </td>
        <td width="12%" nowrap align="right">顺序号</td>
        <td width="13%" nowrap>
          <input type="text" name="index" size="10" maxlength="10" value="<%=(shAttribute.getIndex(attr_id))%>"><font color="red">**</font>
        </td>
        <td width="12%" nowrap align="right"> 表格是否换行 </td>
        <td width="13%" nowrap>
          <%
          out.println("<input type=\"checkbox\" name=\"newLine\" value=\"1\"");
          if(shAttribute.getNewLine(attr_id).equals("1"))
            out.print(" checked");
          out.print(">");
          %>

        </td>
      </tr>

      <tr>
        <td width="12%" nowrap align="right">横对齐</td>
        <td width="13%" nowrap>

          <select name="align">
        <%
          if(shAttribute.getAlign(attr_id).trim().equals("left"))
            out.println("<option value=\"left\" selected>居左</option>");
           else
             out.println("<option value=\"left\" >居左</option>");

           if(shAttribute.getAlign(attr_id).trim().equals("center"))
            out.println("<option value=\"center\" selected>居中</option>");
           else
             out.println("<option value=\"center\" >居中</option>");

           if(shAttribute.getAlign(attr_id).trim().equals("right"))
            out.println("<option value=\"right\" selected>居右</option>");
           else
             out.println("<option value=\"right\" >居右</option>");
        %>
          </select>
        </td>
        <td width="12%" nowrap align="right">纵对齐</td>
        <td width="13%" nowrap>
          <select name="valign">

<%
           if(shAttribute.getValign(attr_id).trim().equals("middle"))
            out.println("<option value=\"middle\" selected>居中</option>");
           else
             out.println("<option value=\"middle\" >居中</option>");

           if(shAttribute.getValign(attr_id).trim().equals("top"))
            out.println("<option value=\"top\" selected>向上</option>");
           else
             out.println("<option value=\"top\" >向上</option>");

           if(shAttribute.getValign(attr_id).trim().equals("bottom"))
            out.println("<option value=\"bottom\" selected>向下</option>");
           else
             out.println("<option value=\"bottom\" >向下</option>");
%>

          </select>
        </td>
        <td width="12%" nowrap align="right">跨列宽</td>
        <td width="13%" nowrap>
          <input type="text" name="colspan" size="10" maxlength="10" value="<%=shAttribute.getColspan(attr_id)%>"><font color="red">**</font>
        </td>
        <td width="12%" nowrap align="right">跨行宽 </td>
        <td width="13%" nowrap>
          <input type="text" name="rowspan" size="10" maxlength="10" value="<%=shAttribute.getRowspan(attr_id)%>"><font color="red">**</font>
        </td>
      </tr>
      <tr>
        <td width="12%" nowrap align="right">单元格宽度</td>
        <td width="13%" nowrap>
          <input type="text" name="width" size="10" maxlength="10" value="<%=shAttribute.getWidth(attr_id)%>"> 象素
        </td>
        <td width="12%" nowrap align="right">单元格高度</td>
        <td width="13%" nowrap>
          <input type="text" name="height" size="10" maxlength="10" value="<%=shAttribute.getHeight(attr_id)%>"> 象素
        </td>

        <td width="12%" nowrap align="right">
          <div align="right">文字颜色<font size=-2 color=red><b>（双击选择）</b></font></div>
        </td>
        <td width="13%">
          <input type="text" name="color" size="10" maxlength="10"  value="<%=shAttribute.getColor(attr_id)%>" ondblclick="openColorWin()">
        </td>
        <td width="12%" nowrap align="right">单元格是否换行</td>
        <td width="13%" nowrap>
        <%
          String nowrapstr="";
          if(shAttribute.getNowrap(attr_id).equals(""))
          nowrapstr=" checked";
        %>
          <input type="checkbox" name="nowrap" value="" <%out.print(nowrapstr);%>>
        </td>
      </tr>

    </table>
<table cellSpacing=0 cellPadding=0  border=0 width="760" >
  <td colspan="8" align="right">
          <div >
            <input type="hidden" name="flag" value="edit">
            <input type="Submit" name="Submit" value="修 改">
            <input type="reset" name="reset" value="取 消">
            <input type="button" name="button1" value="返 回"  onclick="goto();">
          </div>
        </td>
</table>
   </form>
</div>
</body>
</html>
