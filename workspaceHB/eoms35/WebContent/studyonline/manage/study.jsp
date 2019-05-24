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
在线学习
</title>

<script language="JavaScript" >

function checkItem(form)
{
   for (var i=0;i<form.elements.length;i++) {
     var e = form.elements[i];
     if (e.type=='checkbox'){
	  if (e.checked){
            if( form.options.value == "" || form.options.value == null )
              form.options.value = e.name;
            else
              form.options.value = form.options.value + ";" + e.name;
	  }
     }
   }
   //alert("option=" + form.options.value);
   if ( form.options.value == "" || form.options.value == null ){
     alert("选择答案不能为空");
     return false;
   }
}
function view(image){
        var win;
        win=window.open("/EOMS_J2EE/studyonline/manage/view.jsp?fileName="+image,"图片显示","height=350,width=350,left=0,top=350,resizable=yes,scrollbars=yes,status=no");
}
</script>

<!--定义正确数和总体数，求正确率时使用-->
<bean:define id="right" name="Usercontent" property="right" type="java.lang.Integer" />
<bean:define id="total" name="Usercontent" property="total" type="java.lang.Integer" />

<%
  String typeSel = StaticMethod.null2String(request.getParameter("typeSel"));
  String showTop = "TOP10排名 ";
  int topId = 1;
%>

<logic:iterate id="topTen" name="TopTen" type="com.boco.eoms.studyonline.model.OnlineTopTen">
  <% showTop += topId++ + "." + topTen.getUserId() + ":" + topTen.getSum() + "分 "; %>
</logic:iterate>

</head>
<body>
<html:form action="/StudySubmit" onsubmit="checkItem(this)" method="post">
<html:hidden property="options" />
<table cellpadding="0" cellspacing="0" width="95%">
  <tr>
      <td colspan=5 align="center" class="table_title">
        <marquee scrollamount=3><%=showTop%></marquee>
      </td>
  </tr>
  <tr>
    <td align="center">
      您的积分：<bean:write name="Usercontent" property="mark"/>
    </td>
    <td align="center">
      正确数：<bean:write name="Usercontent" property="right"/>
    </td>
    <td align="center">
      总题数：<bean:write name="Usercontent" property="total"/>
    </td>
    <td align="center">
      正确率：
      <%=( 0 == total.intValue() ? 0 : Math.round((float)right.intValue() / total.intValue() * 10000) / 100.0)%>%
    </td>
  </tr>
</table>

<br>
<br>

<logic:iterate id="SubjectObj" name="OnlineWarehouse" type="com.boco.eoms.studyonline.model.OnlineWarehouse">
<%
  String[] options = SubjectObj.getOptions().split(";");
  //System.out.println(options[0]);
%>
<table cellpadding="1" cellspacing="1" width="95%" align="center" border="0" class="table_show">
 <tr align="center" class="tr_show">
   <td width="10%" class="td_label">标题：</td>
   <td width="90%" class="td_label">
     <bean:write name="SubjectObj" property="title" scope="page"/>              <!--标题-->
     <logic:notEmpty name="SubjectObj" property="image">                   <!--图片-->
          <img src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0" onclick="view('<%=SubjectObj.getImage()%>')">
      </logic:notEmpty>
   </td>
 </tr>

 <html:hidden property="typeSel" value="<%=typeSel%>" />

<%
  for(int i = 0; i < options.length; i++ ){
    String comment = options[i].toString();
    String opt = comment.substring(0,1);
%>
  <tr align="center" class="tr_show">
    <td width="10%">
      <input name="<%=SubjectObj.getSubId()%><%=opt%>" type="checkbox">
    </td>
    <td width="90%" align="left">
      <%=comment%>
    </td>
  </tr>
<%
  }
%>
  <tr align="center" class="tr_show">
    <td colspan="2">
      <html:submit styleClass="clsbtn2">确定</html:submit>
    </td>
  </tr>
</table>
</logic:iterate>
</html:form>
</body>
</html>
