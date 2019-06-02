<%@ include file="/common/taglibs.jsp"%>
<head>
  <title>文件展现页面</title>
</head>
<link rel="stylesheet" type="text/css" media="all" href="${app}/styles/default/theme.css" />
<body topmargin="0" leftmargin="0" style="background-image:none">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<ul>
<c:if test="${noteInfo != null}">
<li class="error">
	<img src="<c:url value="/images/icons/warning.gif"/>" alt="<fmt:message key="icon.warning"/>" class="icon" />
	${noteInfo}
</li>
</c:if>
</ul>
</table>

</body>
