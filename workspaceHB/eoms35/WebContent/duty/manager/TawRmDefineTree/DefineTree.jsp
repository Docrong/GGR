<%@ page contentType="text/html; charset=gb2312"%>
<html>
<head>
<%
String roomId=request.getParameter("roomId");
%>
<script language="javascript">
function onPreview(action)
{
 window.open("../../TawRmDefineTree/selectplan.do?roomId=<%=roomId%>&Action="+action,"","width=350,height=120,left=200,top=150,scrollbars=yes");
}
</script>
<title>≈‰÷√÷µ∞‡◊˜“µ</title>

</head>
<body  leftmargin="0" topmargin="0" >
<form method="post" action="">
<table width="100%" height="100%">
<TR>
        <TD align="left" class="clsfth"  height="5%">
        &nbsp;&nbsp;<input type="button" value="‘§  ¿¿" onclick="javascript:onPreview('View');" class="clsbtn2">
        </TD>
</TR>
<TR>
        <TD class="clsfth">
        <IFRAME ID=IFrame1  FRAMEBORDER=0 width="100%" height="95%" SCROLLING=YES SRC='../../TawRmDefineTree/DesignTree.do?roomId=<%=roomId%>'>
        </IFRAME>
        </TD>
</TR>
</table>
</form>
</body>
</html>
