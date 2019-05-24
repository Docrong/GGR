<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,com.boco.eoms.common.controller.*"%>
<%@ page import="com.boco.eoms.kbs.util.*"%>
<%String mapPath =StaticMethod.nullObject2String(request.getAttribute("MAPPATH"),"");
System.out.println(mapPath);
%>
<html>
<head>
<title>案例列表</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script language="JavaScript">
<!--
//-->
</script>
</head>
<!--<base target="_self">-->
<body>
<center>
<br>
<form name="form1">
  <table border="0" width="95%" cellspacing="0">
  <tr>
    <td width="100%" class="table_title" align="center">
    &nbsp;&nbsp;案例列表</td>
  </tr>

</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
   <tr class="tr_show">
    <td width="106%" colspan="10" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/>
    <%! String key;%></td>
  </tr>

  <tr class="td_label">
    <td width="14%" height="25">案例编号</td>
    <td width="14%" height="25">案例主题</td>
    <td width="10%" height="25">作者</td>
    <td width="9%" height="20">提交人</td>
    <td width="9%" height="25">提交人部门</td>
    <td width="14%" height="30">生成时间</td>
    <td width="5%" height="25" >状态</td>
    <td width="7%" height="25">查看</td>
    <td width="7%" height="25">修改</td>
    <td width="12%" height="25">删除</td>
  </tr>
   <logic:iterate id="kbsBase" name="KBSBASES" type="com.boco.eoms.kbs.model.KbsBase">

  <tr class="tr_show">
    <td width="15%" height="25" align="center"><bean:write name="kbsBase" property="code" scope="page"/></td>
    <td width="15%" height="25" align="center"><bean:write name="kbsBase" property="name" scope="page"/></td>
    <td width="7%" height="25" align="center"><bean:write name="kbsBase" property="zuozhe" scope="page"/></td>
    <td width="10%" height="25" align="center"><bean:write name="kbsBase" property="authorName" scope="page"/></td>
    <td width="10%" height="25" align="center"><bean:write name="kbsBase" property="authorDeptName" scope="page"/></td>
    <td width="15%" height="25" align="center"><bean:write name="kbsBase" property="publicTime" scope="page"/></td>
    <td width="5%" height="25" align="center">
       <bean:define id="status" name="kbsBase" property="status" type="java.lang.Integer"/>
       <% String Status=String.valueOf(status);
        if (Status.equalsIgnoreCase("0")){%>
        草稿<%
     }
else if (Status.equalsIgnoreCase("1"))
{
%>待审核<%
}
else if (Status.equalsIgnoreCase("2"))
{
%>通过并启用<%
}
else if (Status.equalsIgnoreCase("3"))
{
%>待修改<%
}
else if (Status.equalsIgnoreCase("4"))
{
%>不采用<%
}
else if (Status.equalsIgnoreCase("5"))
{
%>停用<%
}

%>
    </td>
    <td width="5%" height="25" align="center">

    <%
       java.util.HashMap map = new java.util.HashMap();
       map.put("id", String.valueOf(kbsBase.getId()));
	   map.put("mapPath", mapPath);
       pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
    %>

    <html:link page="/KbsBase/view.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0" alt="查看">
    </html:link>&nbsp;
    </td>
    <td width="5%" height="25" align="center">
    <html:link page="/KbsBase/edit.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0" alt="修改">
    </html:link>&nbsp;
    </td>
    <td width="12%" height="25" align="center">
    <html:link page="/KbsBase/remove.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_sc.gif" border="0" alt="删除">
    </html:link>
    </td>
  </tr>

</logic:iterate>
</table>

</form>
  </center>
</div>

</body>
</html>
