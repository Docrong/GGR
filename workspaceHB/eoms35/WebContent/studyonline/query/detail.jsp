<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,java.io.*"%>

<html>
<head>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<title>
做题明细
</title>

<script language="JavaScript" >
function view(image){
        var win;
        win=window.open("/EOMS_J2EE/studyonline/manage/view.jsp?fileName="+image,"图片显示","height=350,width=350,left=0,top=350,resizable=yes,scrollbars=yes,status=no");
}
</script>

</head>
<body>
<table cellpadding="0" cellspacing="0" width="95%">
  <tr>
      <td align="center" class="table_title">
        做题明细
      </td>
  </tr>
</table>

<center>
<div id="divTable" style="position: relative; align: center; top: 5px;width:  100%; height:  440; z-index: 1; overflow: auto; overflow-x: hidden">
<table cellpadding="1" cellspacing="1" width="95%" border="0" class="table_show">
<logic:iterate id="detailqo" name="ContentList" type="com.boco.eoms.studyonline.qo.detailQO">
<%
  String[] options = detailqo.getOptions().split(";");
  //System.out.println(options[0]);
%>
 <tr class="tr_show">
   <td width="85%" bgcolor="#BEDEF8">
     <bean:write name="detailqo" property="title" scope="page"/>             <!--标题-->
     <logic:notEmpty name="detailqo" property="image">                       <!--图片-->
          <img src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0" onclick="view('<%=detailqo.getImage()%>')">
     </logic:notEmpty>
     (
     <bean:write name="detailqo" property="answer"/>                         <!--答案-->
     <logic:equal name="detailqo" property="right" value="2">                <!--对错-->
       <font color="red">对</font>
     </logic:equal>
     <logic:equal name="detailqo" property="right" value="0">                <!--对错-->
       错
     </logic:equal>
     )
   </td>
   <logic:notEmpty name="detailqo" property="submitTime">
   <td width="15%"  bgcolor="#BEDEF8" align="center">
     <bean:write name="detailqo" property="submitTime"/>
   </td>
   </logic:notEmpty>
 </tr>

<%
  for(int i = 0; i < options.length; i++ ){
    String comment = options[i].toString();
    String opt = comment.substring(0,1);
%>
  <tr align="center" class="tr_show">
    <td colspan="2" align="left">
      <%=comment%>
    </td>
  </tr>
<%
  }
%>
</logic:iterate>
</table>
</div>
<br>
  <input type="button" value="返回" styleClass="clsbtn2" onclick="history.back()">
</center>

</body>
</html>
