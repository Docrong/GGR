<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>

<html:html>
<head>
<title>资料类别列表</title>
<html:base/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<html:form action="/TawInfSort/add" method="post">
  <body>
	<br>
	  <table border="0" width="50%" cellspacing="0" align="center">
		<tr>
		  <td width="100%" align="center" class="table_title">
			<b>
			  &nbsp;&nbsp;<bean:message key="TawInfSort.Name"/><bean:message key="label.list"/>
			</b>
		  </td>
		</tr>
	  </table>
      <table border="0" width="50%" cellspacing="1" cellpadding="1" class="table_show" align=center>
		<tr class="tr_show">
		    <td width="100%" colspan="10" height="25" align="center"><bean:write name="pagerHeader" scope="request" filter="false"/><%! String key;%></td>
        </tr>
        <tr class="tr_show">
		  <td width="30%" height="25" class="clsfth" align="center"><bean:message key="TawInfSort.inf_sort_name"/></td>

          <td width="10%" height="25" align="center"><font color="#cc0000">修改</font></td>

          <td width="10%" height="25" align="center"><font color="#cc0000">删除</font></td>
		</tr>

		<logic:iterate id="tawInfSort" name="TAW_INF_SORT_LIST" type="com.boco.eoms.infmanage.model.TawInfSort">
		<tr class="tr_show">
		  <td width="30%" height="25" class="clsfth" align="center"><bean:write name="tawInfSort" property="infSortName" scope="page"/></td>
		  <%
			java.util.HashMap map = new java.util.HashMap();

			map.put("sortId",String.valueOf(tawInfSort.getInfSortId()));  //资料类别编号
			pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
          %>
           <td width="10%" height="25" align="center"><font color="#cc0000"><html:link page="/TawInfSort/update.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0" alt="编辑"></html:link>&nbsp;</font></td>

           <td width="10%" height="25" align="center"><font color="#cc0000"><html:link page="/TawInfSort/del.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_sc.gif" border="0" alt="删除"></html:link>&nbsp;</font></td>
     	</tr>
		</logic:iterate>
    　</table>
      <table border="0" width="75%" cellspacing="0">
		  <tr>
		    <td width="100%" colspan="10" height="32" align="right">
              <input type="button" value="<bean:message key="label.add"/>" onclick="toSubmit()" class="clsbtn2"/>
              &nbsp;
              <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="clsbtn2"/>
			  &nbsp;
		    </td>
		  </tr>
      </table>
  </body>
</html:form>
</html:html>

<script language="javascript">
  function toSubmit()
  {
    window.document.tawInfSortForm.submit();
  }
</script>
