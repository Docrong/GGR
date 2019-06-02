<%@ include file="/portal/common/taglibs-portal.jsp"%>
<html>
<head>
</head>
<body>
<%
String responseId = (String)request.getAttribute("responseId");
%>
<fmt:bundle basename="resourceBundle">



<form name="form_<%= responseId %>">
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left' colspan='3'>
<fmt:message key="portlet.theme.description.style"/>:
</td>
</tr>
<tr>
<td class='portlet-table-td-center'>
<c:if test="${sessionScope.portal.theme != 'theme/master1.css'}"> 
<input type='radio' name='ptTheme' value='theme/master1.css' class='portlet-form-radio'/>
</c:if>
<c:if test="${sessionScope.portal.theme == 'theme/master1.css'}"> 
<input type='radio' name='ptTheme' value='theme/master1.css' class='portlet-form-radio' checked='true' />
</c:if>
</td>
<td class='portlet-table-td-center'>
<c:if test="${sessionScope.portal.theme != 'theme/master3.css'}"> 
<input type='radio' name='ptTheme' value='theme/master3.css' />
</c:if>
<c:if test="${sessionScope.portal.theme == 'theme/master3.css'}"> 
<input type='radio' name='ptTheme' value='theme/master3.css' checked='true'/>
</c:if>
</td>
</tr>
<tr>
  <td class='portlet-table-td-center'><img src='light/images/theme1.gif' height='200' style='border: 1px solid #999' width='150' alt='' /></td>
  <td class='portlet-table-td-center'><img src='light/images/theme3.gif' height='200' style='border: 1px solid #999' width='150' alt='' /></td>
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left' colspan='3'>
<fmt:message key="portlet.theme.description.backgroudimage"/>:
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<span height='20' width='100' style='' ><fmt:message key="portlet.theme.description.defalut"/></span>
<c:if test="${sessionScope.portal.bgImage != ''}"> 
<input type='radio' name='ptBg' value='' />
</c:if>
<c:if test="${sessionScope.portal.bgImage == ''}"> 
<input type='radio' name='ptBg' value='' checked='true' />
</c:if>
</td>	
<td class='portlet-table-td-left'>
<span height='20' width='100' style='' ><fmt:message key="portlet.theme.description.noimage"/></span>
<c:if test="${sessionScope.portal.bgImage != 'no'}"> 
<input type='radio' name='ptBg' value='no' />
</c:if>
<c:if test="${sessionScope.portal.bgImage == 'no'}"> 
<input type='radio' name='ptBg' value='no' checked='true' />
</c:if>
</td>
<td class='portlet-table-td-left'>
<span height='20' width='100' style='' >
<a href="javascript:void(0);" onclick="javascript:showMoreBgImage(event,'<%= responseId %>');"><fmt:message key="portlet.theme.description.moreimage"/></a>
<c:if test="${sessionScope.portal.bgImage == 'no' || sessionScope.portal.bgImage == ''}"> 
<input type='radio' name='ptBg' value='more' />
</c:if>
<c:if test="${sessionScope.portal.bgImage != 'no' && sessionScope.portal.bgImage != ''}"> 
<input type='radio' name='ptBg' value='more' checked='true'/>
</c:if>
</span>
</td>		
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left' colspan='3'>
<fmt:message key="portlet.theme.description.headimage"/>:
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<span height='20' width='100' style='' ><fmt:message key="portlet.theme.description.defalut"/></span>
<c:if test="${sessionScope.portal.headerImage != ''}"> 
<input type='radio' name='ptHeader' value='' />
</c:if>
<c:if test="${sessionScope.portal.headerImage == ''}"> 
<input type='radio' name='ptHeader' value='' checked='true' />
</c:if>
</td>	
<td class='portlet-table-td-left'>
<span height='20' width='100' style='' ><fmt:message key="portlet.theme.description.noimage"/></span>
<c:if test="${sessionScope.portal.headerImage != 'no'}"> 
<input type='radio' name='ptHeader' value='no' />
</c:if>
<c:if test="${sessionScope.portal.headerImage == 'no'}"> 
<input type='radio' name='ptHeader' value='no' checked='true' />
</c:if>
</td>	
<td class='portlet-table-td-left'>
<span height='20' width='100' style='' >
<a href="javascript:void(0);" onclick="javascript:showMoreHeaderImage(event,'<%= responseId %>');"><fmt:message key="portlet.theme.description.moreimage"/></a>
<c:if test="${sessionScope.portal.headerImage == 'no' || sessionScope.portal.headerImage == ''}"> 
<input type='radio' name='ptHeader' value='more' />
</c:if>
<c:if test="${sessionScope.portal.headerImage != 'no' && sessionScope.portal.headerImage != ''}"> 
<input type='radio' name='ptHeader' value='more' checked='true'/>
</c:if>
</span>
</td>
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left' >
<fmt:message key="portlet.theme.description.headheight"/>:
</td>
<td class='portlet-table-td-left'>
<select name='ptHeaderHeight' size='1' class='portlet-form-select'>
<c:if test='${sessionScope.portal.headerHeight == -40}'> 
<option selected='selected' value='-40' ><fmt:message key="portlet.theme.description.defalut"/>-40</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight != -40}'> 
<option value='-40'><fmt:message key="portlet.theme.description.defalut"/>-40</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight == -30}'> 
<option selected='selected' value='-30' ><fmt:message key="portlet.theme.description.defalut"/>-30</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight != -30}'> 
<option value='-30'><fmt:message key="portlet.theme.description.defalut"/>-30</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight == -20}'> 
<option selected='selected' value='-20' ><fmt:message key="portlet.theme.description.defalut"/>-20</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight != -20}'> 
<option value='-20'><fmt:message key="portlet.theme.description.defalut"/>-20</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight == -10}'> 
<option selected='selected' value='-10' ><fmt:message key="portlet.theme.description.defalut"/>-10</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight != -10}'> 
<option value='-10'><fmt:message key="portlet.theme.description.defalut"/>-10</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight == 0}'> 
<option selected='selected' value='0' ><fmt:message key="portlet.theme.description.defalut"/></option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight != 0}'> 
<option value='0'><fmt:message key="portlet.theme.description.defalut"/></option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight == 10}'> 
<option selected='selected' value='10' ><fmt:message key="portlet.theme.description.defalut"/>+10</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight != 10}'> 
<option value='10'><fmt:message key="portlet.theme.description.defalut"/>+10</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight == 20}'> 
<option selected='selected' value='20' ><fmt:message key="portlet.theme.description.defalut"/>+20</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight != 20}'> 
<option value='20'><fmt:message key="portlet.theme.description.defalut"/>+10</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight == 30}'> 
<option selected='selected' value='30' ><fmt:message key="portlet.theme.description.defalut"/>+30</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight != 30}'> 
<option value='30'><fmt:message key="portlet.theme.description.defalut"/>+30</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight == 40}'> 
<option selected='selected' value='40' ><fmt:message key="portlet.theme.description.defalut"/>+40</option>
</c:if>
<c:if test='${sessionScope.portal.headerHeight != 40}'> 
<option value='40'><fmt:message key="portlet.theme.description.defalut"/>+40</option>
</c:if>
</select>
</td>
</tr>

<tr>
<td class='portlet-table-td-left' >
<fmt:message key="portlet.theme.description.rssfont"/>:
</td>
<td class='portlet-table-td-left'>
<select name='ptFontSize' size='1' class='portlet-form-select'>
<c:if test='${sessionScope.portal.fontSize == -4}'> 
<option selected='selected' value='-4' ><fmt:message key="portlet.theme.description.defalut"/>-4</option>
</c:if>
<c:if test='${sessionScope.portal.fontSize != -4}'> 
<option value='-4'><fmt:message key="portlet.theme.description.defalut"/>-4</option>
</c:if>
<c:if test='${sessionScope.portal.fontSize == -3}'> 
<option selected='selected' value='-3' ><fmt:message key="portlet.theme.description.defalut"/>-3</option>
</c:if>
<c:if test='${sessionScope.portal.fontSize != -3}'> 
<option value='-3'><fmt:message key="portlet.theme.description.defalut"/>-3</option>
</c:if>
<c:if test='${sessionScope.portal.fontSize == -2}'> 
<option selected='selected' value='-2' ><fmt:message key="portlet.theme.description.defalut"/>-2</option>
</c:if>
<c:if test='${sessionScope.portal.fontSize != -2}'> 
<option value='-2'><fmt:message key="portlet.theme.description.defalut"/>-2</option>
</c:if>
<c:if test='${sessionScope.portal.fontSize == -1}'> 
<option selected='selected' value='-1' ><fmt:message key="portlet.theme.description.defalut"/>-1</option>
</c:if>
<c:if test='${sessionScope.portal.fontSize != -1}'> 
<option value='-1'><fmt:message key="portlet.theme.description.defalut"/>-1</option>
</c:if>
<c:if test='${sessionScope.portal.fontSize == 0}'> 
<option selected='selected' value='0' ><fmt:message key="portlet.theme.description.defalut"/></option>
</c:if>
<c:if test='${sessionScope.portal.fontSize != 0}'> 
<option value='0'><fmt:message key="portlet.theme.description.defalut"/></option>
</c:if>
<c:if test='${sessionScope.portal.fontSize == 1}'> 
<option selected='selected' value='1' ><fmt:message key="portlet.theme.description.defalut"/>+1</option>
</c:if>
<c:if test='${sessionScope.portal.fontSize != 1}'> 
<option value='1'><fmt:message key="portlet.theme.description.defalut"/>+1</option>
</c:if>
</select>
</td>
</tr>

<tr>
<td class='portlet-table-td-right' colspan='2'>
<input name='Apply' type='button' value='<fmt:message key="portlet.theme.description.apply"/>' class='portlet-form-button'
 onclick="javascript:changeTheme('<%= responseId %>');" />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>