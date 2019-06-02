<%@ page contentType="text/html; charset=GB2312" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import ="com.boco.eoms.kbs.util.BoardTree"%>
<%@ page import="java.util.*,com.boco.eoms.common.util.*"%>
<%

String context = request.getPathInfo();
BoardTree boardtree = new BoardTree();
int infoType=Integer.parseInt(request.getParameter("infoType"));
String reload=StaticMethod.nullObject2String(request.getParameter("reload"),"false");
String openClose =StaticMethod.nullObject2String(request.getParameter("openClose"),"-1");
String strTree = boardtree.strWKTree(infoType,infoType,"EOMSV2.0");

%>
<html>
<head>
<title>destroydrop &raquo; JavaScripts Tree</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">


<link rel="StyleSheet" href="../css/style.css" type="text/css">
	<script type="text/javascript" src="../kbs/inc/boardtree.js"></script>
	<script type="text/javascript">

	var Tree = new Array;
	<%=strTree%>
	</script>

</head>

<body bgcolor="#FFFFFF" text="#000000"  topmargin=0 leftmargin=0 class="clsscrollbar">

<!----------------------------------------------------------------------------------------->
<table width=189 border=0 align=center cellpadding=0 cellspacing=0  >
	<tr>
		<td height=26 background="<%=request.getContextPath()%>/images/top/button_tree.gif">
			<table width=189 cellpadding=0 cellspacing=0 border=0>
				<tr>
					<td style="padding-left:18pt;cursor:hand" width=60  onclick="window.location='?infoType=<%=infoType%>&reload=true&openClose=-1'" >
					展开树
					</td>

					<td style="padding-left:0pt;cursor:hand" onclick="window.location='?infoType=<%=infoType%>&reload=true&openClose=1'" >
					关闭树
					</td>

					<td style="padding-left:0pt;cursor:hand" onclick="window.location='?infoType=<%=infoType%>&reload=true&openClose=<%=request.getParameter("openClose")%>'">
					刷新
					</td>
				</tr>
			</table>
		</td>
	</tr>

	<tr>
		<td style="border:1px solid #000000">
			<table align=center width=187 cellpadding=0 cellspacing=0 border=0 >
        <tr>
          <td height=7 background="<%=request.getContextPath()%>/images/top/left_line.gif" colspan=2><img src="<%=request.getContextPath()%>/images/top/left_gui.gif"></td>
        </tr>
        <tr>
          <td width=7 background="<%=request.getContextPath()%>/images/top/top_right_line.gif">
          </td>
          <td><div id="tree" style="width:180;height:473;background:#E6F2FC;overflow-y:scroll">
<div id="openClose" name="openClose" value="<%=openClose%>" >
<script type="text/javascript">
//<!--
var number=<%=infoType%>
var reload=<%=reload%>
var openClose = document.all.openClose.value;

if (openClose == null)
{
	createTree(Tree,number,-7);
}
else
{
        createTree(Tree,number,openClose);
}

        if (reload!=true)
        {
          switch (number)
          {
	    case 1:
		parent.rightFrame.location= "../template/subnull.jsp?id=1";
		break
	    case 2:
		parent.rightFrame.location= "../template/subnull.jsp?id=2";
		break
            case 3:
		parent.rightFrame.location= "../template/subnull.jsp?id=3";
		break
	    default:
		parent.rightFrame.location="../template/null.jsp";
		break
          }
	}
//-->
</script>
<%//strTree%>
</div>
              <!------------------------------------------------------------------------------------------------>
            </div></td>
        </tr>
            </table>

		</td>
	</tr>
</table>
<table width="189" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="19%" align="center"><img src="../images/top/copy.gif" width="22" height="20"></td>
    <td width="81%"><font color="#CCCCCC">亿阳信通 BOCO-2004</font></td>
  </tr>
</table>
<!----------------------------------------------------------------------------------------->
</body>
</html>
