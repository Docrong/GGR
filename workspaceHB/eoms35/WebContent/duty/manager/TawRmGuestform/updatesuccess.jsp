<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<head>
<title><bean:message key="TawRmGuestform.roomTable"/></title>


</head>
<body leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmGuestform/submit.do">
    <HEAD>
        <title><bean:message key="label.info"/></title>
        <META content="text/html; charset=gb2312" http-equiv="Content-Type">
    </HEAD>
    <body>
        <Center>
              <TABLE cellSpacing="0" cellPadding="0" width="500" border="0">
                  <TR>
                          <TD noWrap align="middle" colSpan="3" height="150"></TD>
                  </TR>
              </TABLE>
              <TABLE cellSpacing="0" cellPadding="0" width="560" align="center" border="0">
                  <TR>
                      <TD noWrap style="FONT-SIZE:20px;" align="center">
                              <bean:message key="label.savesuccess"/><br><br><br><br>
                      </TD>
                  </TR>
                  <TR>
                      <TD noWrap style="FONT-SIZE:14px; COLOR:red" align="center">
                     <input type="submit" value=<bean:message key="label.back"/> class="clsbtn2">
                      </TD>
                  </TR>
              </TABLE>
        </Center>
    </body>
</html:form>
</body>