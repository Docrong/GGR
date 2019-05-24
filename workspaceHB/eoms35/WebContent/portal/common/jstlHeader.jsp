<%@ include file="/portal/common/taglibs-portal.jsp"%>
<%@ page import="org.light.portal.util.DateFormatter" import="java.util.Date" import="javax.servlet.jsp.jstl.core.Config" import="java.util.Locale" %>
<table border="0" cellpadding="0" cellspacing="0">
<tbody>
<tr>
<td class="logo"></td>

<td class="statusBar"><table border="0" cellpadding="3" cellspacing="0">
<tbody>
<tr>
<td class="statusBar">
<authz:authorize ifNotGranted="ROLE_USER">
<span class="menuItemSeparater1"><img src="<%= request.getContextPath() %>/portal/light/images/spacer.gif" style="border: 0px" width="1" height="4" /></span>
<a href="<%= request.getContextPath() %>/index.jsp">Home</a>
<span class="menuItemSeparater1"><img src="<%= request.getContextPath() %>/portal/light/images/spacer.gif" style="border: 0px" width="1" height="4" /></span>
<a href="mailto:liujianmin@gmail.com">Contact Us</a>
<span class="menuItemSeparater1"><img src="<%= request.getContextPath() %>/portal/light/images/spacer.gif" style="border: 0px" width="1" height="4" /></span>
<a href="javascript:void(0);">Help</a>
<span class="menuItemSeparater1"><img src="<%= request.getContextPath() %>/portal/light/images/spacer.gif" style="border: 0px" width="1" height="4" /></span>
<a href="javascript:void(0)" onclick="Light.switchPortal()">My Portal</a>
<span class="menuItemSeparater1"><img src="<%= request.getContextPath() %>/portal/light/images/spacer.gif" style="border: 0px" width="1" height="4" /></span>
</authz:authorize>

</td>
</tr>
</tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0">
<tbody>
<tr>
<td class="statusBar">Welcome&nbsp;Guest

	<a href="javascript:void(0);"><img src="<%= request.getContextPath() %>/portal/light/images/logout.jpg"
	        style= "border: 0px;-moz-opacity:0.5;filter:alpha(opacity=50);"
		  	onmouseover= "this.style.MozOpacity=1;this.filters.alpha.opacity=100"
            onmouseout= "this.style.MozOpacity=0.5;this.filters.alpha.opacity=50"/></a></td>
</tr>
</tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0">
<tbody>
<tr>
<td class="statusBar"><%= DateFormatter.format(new Date(),(Locale) Config.get(request.getSession(),Config.FMT_LOCALE),"EEEE, MMMM dd, yyyy HH:mm") %></td>
</tr>
</tbody>
</table>

</td>
</tr>
</tbody>
</table>
