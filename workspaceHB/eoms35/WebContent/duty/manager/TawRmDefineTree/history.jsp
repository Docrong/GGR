<%@ page contentType="text/html; charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="java.util.*"%>
<HTML>
<HEAD>
<LINK  href="../css/style.css" type="text/css" rel="stylesheet">
<SCRIPT src="../js/scripts.js"></SCRIPT>
<SCRIPT src="../js/navigate.js"></SCRIPT>
<SCRIPT src="../js/checkselect.js"></SCRIPT>
<SCRIPT language=JavaScript>
<!--
function DispReport (OpenURL) {
 selectdocument();
 document.getElementById("TaskReport").src = OpenURL;
}
function goHistory()
{
form1.action="addDefRecord.do";
form1.submit();
}
// -->
</SCRIPT>
<title>查看历史</title>
<META content="MSHTML 6.00.2800.1106" name=GENERATOR></HEAD>
<BODY text=#000000 bgColor=#ffffff leftMargin=0 topMargin=0  onload=clearnodocumenttext();setTableColor(table1,1);>
<FORM name="form1" method="post">
<STYLE type="text/css">
.mainview {
	Z-INDEX: 1; BACKGROUND: url(/images/viewbg.jpg) fixed no-repeat right bottom; LEFT: 4px; OVERFLOW: auto; ; WIDTH: expression(document.body.clientWidth-8); POSITION: absolute; TOP: 30px; HEIGHT: 99%
}
</STYLE>
<SCRIPT language=JavaScript src="../js/tigra_tables.js"></SCRIPT>
<TABLE height="102%" cellSpacing=0 cellPadding=0 width="100%" align=center
border=0>
  <TBODY>
  <TR>
    <TD colSpan=3 height=28>
      <TABLE class=p9 cellSpacing=0 cellPadding=0 width="100%"
      background="../images/main-top_02.gif border=0">
        <TBODY>
        <TR>
          <TD width=11><IMG height=33 src="../images/main-top_01.gif"
            width=14></TD>
          <TD vAlign=center noWrap><IMG height=0 width=200><BR>&nbsp; <A
            title=退出 href="javascript:goHistory();"><IMG
            src="../images/reply.gif" align=absMiddle border=0>退出</A> 　　</TD>
          <TD align=right width=158><IMG height=33
            src="../images/main-top_03.gif" width=158></TD></TR>
		</TBODY>
	  </TABLE>
	</TD>
   </TR>
  <TR>
    <TD width=1 bgColor=#efefef><IMG width=1></TD>
    <TD vAlign=top width="100%">
      <DIV id=Layer1>
      <TABLE height="100%" cellSpacing=0 width="101%" align=center border=1>
        <TBODY>
        <TR>
          <TD vAlign=top width="15%">
            <TABLE id=table1 style="FONT-SIZE: 9pt" cellSpacing=1 cellPadding=0
            width="99%" align=center border=0>
              <TBODY>
              <TR class=shitu_headcolor>
                <TD noWrap width="100%">月份</TD></TR>
              <%
                Vector monthVect=(Vector)request.getAttribute("monthVect");
                if(monthVect!=null && monthVect.size() >0)
                 {
                 for(int i=0;i<monthVect.size() ;i++)
                 {
              %>
              <TR
              onclick='DispReport("addDefRecord.do?roomId=<%=request.getAttribute("roomId")%>&Action=View&History=true&month=<%=monthVect.elementAt(i)%>")'
              align=left>
                <TD><%=monthVect.elementAt(i)%></TD></TR>
               <%
                 }
                 }
               %>
              </TBODY>
			 </TABLE>
            <SCRIPT language=JavaScript> tigra_tables("table1", 1); </SCRIPT>
          </TD>
          <TD width="85%">
		    <IFRAME id=TaskReport src="about:blank"  frameBorder=0 width="100%" scrolling=yes  height="100%"></IFRAME>
		  </TD>
		 </TR>
		 </TBODY>
		 </TABLE>
		 </DIV>
		 </TD>
    <TD width=1 bgColor=#efefef><IMG width=1></TD>
	</TR>
  <TR>
    <TD colSpan=3 height=18>
	<IMG height=18 src="../images/main-buttom.gif" width="100%"></TD>
  </TR>
  </TBODY>
  </TABLE>
  </FORM>
  </BODY>
  </HTML>
