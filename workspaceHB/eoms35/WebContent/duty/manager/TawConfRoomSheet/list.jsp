<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>

<html:html>
<head>
<title>值班机房和附加表配置列表</title>
	
</head>

<body>
   <form name="buttonbar">
	 <div align="center">
	  <center>
	   <br>
		<table border="0" width="99%" cellspacing="0">
		  <tr>
		    <td width="100%" class="table_title" align="center">
		     <b>&nbsp;&nbsp;机房附加表配置列表</b></td>
		  </tr>
		</table>
		<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>

		  <tr class="td_label">
		    <td width="20%" align="center">机房名称</td>
		    <td width="20%" align="center">附加表</td>
                    <%--
		    <td width="15%" align="center">故障单否</td>
                    <td width="20%" align="center">进入遗留问题条件</td>
                    <td width="20%" align="center">进入遗留问题属性</td>
                     --%>
            <!--
                    <td width="5%" align="center">修改</td>
                 -->
                    <td width="5%" align="center">删除</td>
		  </tr>

	   <logic:iterate id="tawConfRoomSheet" name="TAWCONFROOMSHEETLIST" type="com.boco.eoms.duty.model.TawConfRoomSheet">
		  <tr class="tr_show">
		    <td  align="left"><bean:write name="tawConfRoomSheet" property="roomName" scope="page"/></td>
		    <td  align="left"><bean:write name="tawConfRoomSheet" property="sheetName" scope="page"/></td>
                    <%--
		    <td  align="left"><bean:write name="tawConfRoomSheet" property="isNotFault" scope="page"/></td>
                    <td  align="left"><bean:write name="tawConfRoomSheet" property="byAttrName" scope="page"/></td>
                    <td  align="left"><bean:write name="tawConfRoomSheet" property="toAttrName" scope="page"/></td>
                    --%>
                     <%
			java.util.HashMap map = new java.util.HashMap();
			map.put("id",String.valueOf(tawConfRoomSheet.getId()));
			map.put("deptId", String.valueOf(tawConfRoomSheet.getDeptId()));
			pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
			%>

              <!--
                   <td>
                       <html:link page="/TawConfRoomSheet/update.do" name="map" scope="page">
                       <img src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0"></html:link>
                   </td>
                 -->
                   <td>
                       <html:link page="/TawConfRoomSheet/del.do" name="map" scope="page">
                       <img src="<%=request.getContextPath()%>/images/bottom/an_sc.gif" border="0"></html:link>
                   </td>
		  </tr>
	    </logic:iterate>
		</table>
	  </center>
	</div>
	</form>
    </body>
</html:html>