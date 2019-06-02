<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html:html>
<head>
<title><bean:message key="label.list"/></title>
<html:base/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<form name="form1">
  <body>
	<br>
	  <table border="0" width="100%" cellspacing="0" align="center">
		<tr>
		  <td width="100%" align="center" class="table_title">
			<b>
			  &nbsp;&nbsp;<bean:message key="label.list"/>
			</b>
		  </td>
		</tr>
	  </table>
      <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
		<tr class="tr_show">
		 <td width="100%" colspan="10" height="25" align="center"><bean:write name="pagerHeader" scope="request" filter="false"/><%! String key;%></td>
        </tr>
        <tr class="tr_show">
          <td width="20%"  height="25" class="clsfth"  align="center">工单流水号</td>
	    <td width="20%" height="25" class="clsfth" align="center">录入人</td>

           <td width="5%" height="25" class="clsfth" align="center">录入时间</td>

	   <td width="15%" height="25" class="clsfth" align="center">主题</td>

          <td width="5%" height="25" align="center"><font color="#cc0000">查看</font></td>

          <td width="5%" height="25" align="center"><font color="#cc0000">修改</font></td>

          <td width="5%" height="25" align="center"><font color="#cc0000">删除</font></td>
		</tr>
<%
int listNum=0;
%>
		<logic:iterate id="collsheet" name="Collsheet_LIST" type="com.boco.eoms.infmanage.model.Collsheet">
		<tr class="tr_show">
		  <td width="20%" height="25" class="clsfth" align="center">

                    <bean:write name="collsheet" property="region_code" scope="page"/></td>

		  <td width="5%" height="25" class="clsfth" align="center"><bean:write name="collsheet" property="achieve_person" scope="page"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:write name="collsheet" property="achieve_time" scope="page"/></td>

		  <td width="15%" height="25" class="clsfth" align="center"><bean:write name="collsheet" property="key_word" scope="page"/></td>
		  <%
			java.util.HashMap map = new java.util.HashMap();

			map.put("id",String.valueOf(collsheet.getId()));  //id
			pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
          %>

           <td width="5%" height="25" align="center"><font color="#cc0000"><html:link page="/Collsheet/view.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0" alt="显示"></html:link>&nbsp;</font></td>

           <td width="5%" height="25" align="center"><font color="#cc0000"><html:link page="/Collsheet/update.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0" alt="编辑"></html:link>&nbsp;</font></td>

           <td width="5%" height="25" align="center"><font color="#cc0000"><html:link page="/Collsheet/del.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_sc.gif" border="0" alt="删除"></html:link>&nbsp;</font></td>
     	</tr>
		</logic:iterate>
    　</table>
      <table border="0" width="100%" cellspacing="0">
		  <tr>
		    <td width="100%" colspan="10" height="32" align="right">
		  <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="clsbtn2"/>
		    </td>
		  </tr>
      </table>
  </body>

</form>
</html:html>
