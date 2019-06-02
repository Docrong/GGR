<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.kbs.util.*"%>
<%
int infoType=Integer.parseInt(request.getParameter("infoType"));
String board_id=String.valueOf(request.getParameter("boardId"));
String recipients=String.valueOf(request.getAttribute("recipients"));
%>
<br><br>
<table width="80%" align="center" border="0" class="clsbkgd">
<!--
<tr >
<td colspan="5" width="100%" class="clsscd"><bean:message key="label.view"/></td>
</tr>
-->
<tr>
<td>
<logic:iterate id="tawInformation" name="TAWINFORMATIONS" type="com.boco.eoms.kbs.model.TawInformation">
<table bgcolor="#d7dae1" width="100%" cellspacing="3" border="0" cellpadding="3">
<%if (board_id.equalsIgnoreCase("106")) {%>
<tr bgcolor="#FFFFFF">
    <td nowrap width="5%" bgcolor="#d7dae1" class="clsthd2" align="right">
          发件人：
    </td>
    <td width="25%" bgcolor="#d7dae1" class="clsthd2" colspan=4>
          <bean:write name="tawInformation" property="userId" scope="page"/>
    </td>
</tr>
<tr bgcolor="#FFFFFF">
    <td nowrap width="5%" bgcolor="#d7dae1" class="clsthd2" align="right">
          收件人：
    </td>
    <td width="25%" bgcolor="#d7dae1" class="clsthd2" colspan=4>
          <%out.println(recipients);%>
    </td>
</tr>
<tr bgcolor="#FFFFFF">
    <td width="5%" bgcolor="#d7dae1" class="clsthd2" align="right">
          <bean:message key="TawInformation.dateTime"/>：
    </td>
    <td width="25%" bgcolor="#d7dae1" class="clsthd2" colspan=4>
          <bean:write name="tawInformation" property="dateTime" scope="page"/>
    </td>
</tr>
<tr>
    <td bgcolor="#d7dae1" class="clsthd2" align="right">
          <bean:message key="TawInformation.topic"/>：
    </td>
    <td colspan="4" bgcolor="#d7dae1">
          <bean:write name="tawInformation" property="topic" scope="page"/>
    </td>
</tr>
<%}else {%>
<tr bgcolor="#FFFFFF">
    <td nowrap width="10%" bgcolor="#d7dae1" class="clsthd2">
          <bean:message key="TawInformation.userId"/>
    </td>
    <td width="25%" bgcolor="#d7dae1" class="clsthd2">
          <bean:write name="tawInformation" property="userId" scope="page"/>
    </td>
    <td width="20%" bgcolor="#d7dae1" class="clsthd2">
          <bean:message key="TawInformation.dateTime"/>
    </td>
    <td width="25%" bgcolor="#d7dae1" class="clsthd2">
          <bean:write name="tawInformation" property="dateTime" scope="page"/>
    </td>
</tr>
<tr>
    <td bgcolor="#e5edf8" class="clsthd2">
          <bean:message key="TawInformation.topic"/>
    </td>
    <td colspan="4" bgcolor="#e5edf8">
          <bean:write name="tawInformation" property="topic" scope="page"/>
    </td>
</tr>
<%}%>

<tr>
    <td valign="top" colspan="5" height="200" style="word-break:break-all" bgcolor="#e5edf8">
          <%
          String img=StaticMethod.null2String(tawInformation.getImgName());
          String imgArr[]=img.split(",",0);
          if (!"".equals(img))
          {
            for (int i=0;i<imgArr.length;i++)
            {
              //out.println("<img src='../upload/" + java.net.URLEncoder.encode(imgArr[i]) + "'><br>");
              out.println("<img src='../upload/" + imgArr[i] + "'><br>");
            }
          }
          JUBB ubb=new JUBB();
          String body = StaticFunction.htmlEncode(tawInformation.getBody());
          body=ubb.getAll(StaticMethod.null2String(body));
          out.println(body);
          %>
    </td>
</tr>
<tr>
    <td bgcolor="#d7dae1" class="clsthd2" align="right">
          <bean:message key="TawInformation.attachName"/>：
    </td>
    <td colspan="4" bgcolor="#d7dae1">
     <%
     String att=StaticMethod.null2String(tawInformation.getAttachName());
     //out.println("<br><br>");
    // out.println(att);
     //out.println("<br><br>");

     String shortName=StaticFunction.getShortName(att);

     String attArr[]=att.split(",",0);
     String shortArr[]=shortName.split(",",0);
     if (!shortName.equals(""))
     {
       for (int i=0;i<attArr.length;i++)
       {
          //out.println("<a href='../upload/" + java.net.URLEncoder.encode(attArr[i]) + "'>" + shortArr[i] + "</a>");
          //out.println("<a href='../upload/" + attArr[i] + "'>" + shortArr[i] + "</a>");
           %>
          <a href="<%=request.getContextPath()%>/kbs/manager/TawInformation/download.jsp?name=<%=java.net.URLEncoder.encode(attArr[i])%>"><%=shortArr[i]%></a>
          <%
       }
     }

%>
    </td>
</tr>
</table>
</logic:iterate>


</table>
<script language="javascript">
function goAdd()
{
  window.location="add.do?boardId=<%=board_id%>&parentId=0&infoType=<%=infoType%>";
}
</script>
