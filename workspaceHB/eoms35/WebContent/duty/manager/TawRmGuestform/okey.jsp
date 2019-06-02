<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<head>
<title>table</title>

</head>
<body bgcolor="#FFFFFF"  leftmargin="0" topmargin="0" class="clssclbar">
<html:form method="post" action="/TawRmGuestform/submit" >
<br>
<center>
<table width="95%" cellspacing="1" border="0" cellpadding="1">
<tr>
<td width="100%" align="center" class="table_title">
    &nbsp;&nbsp;<bean:message key="label.list"/>
	    &nbsp;<bean:message key="TawRmGuestform.tablename"/>
	</td>
</tr>
<tr>
        <td align="right" height="25">
		  <bean:write name="pagerHeader" scope="request" filter="false"/><%! String key;%>
	</td>
</tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="formTable" align=center>
<tr class="td_label">
	<td>

	<bean:message key="TawRmGuestform.id"/>
	</td>
	<td>

	<bean:message key="TawRmGuestform.inputdate"/>
	</td>
	<td>

	<bean:message key="TawRmGuestform.guestname"/>
	</td>
	<td>

	<bean:message key="TawRmGuestform.company"/>
	</td>
	<td>

	<bean:message key="TawRmGuestform.dutyman"/>
	</td>
	<td>

	<bean:message key="TawRmGuestform.starttime"/>
	</td>
	<td>
	<bean:message key="TawRmGuestform.endtime"/>
        </td>
	<td>

	<bean:message key="TawRmGuestform.flag"/>
	</td>
	<td>

          <bean:message key="label.edit"/></td>
	</tr>

		  <logic:iterate id="tawRmGuestform" name="TAWRMGUESTFORMS" type="com.boco.eoms.duty.model.TawRmGuestform">

	<tr class="tr_show">
	<td>

                  <bean:write name="tawRmGuestform" property="id" scope="page"/>

	</td>
	<td>

		<bean:write name="tawRmGuestform" property="inputdate" scope="page"/>

	</td>
	<td>

				<bean:write name="tawRmGuestform" property="guestname" scope="page"/>

	</td>
	<td>

				<bean:write name="tawRmGuestform" property="company" scope="page"/>

	</td>
	<td>

				<bean:write name="tawRmGuestform" property="dutyman" scope="page"/>

	</td>
	<td>

				<bean:write name="tawRmGuestform" property="starttime" scope="page"/>

	</td>
	<td>

				<bean:write name="tawRmGuestform" property="endtime" scope="page"/>

	</td>
	<td>

                            <%if (tawRmGuestform.getFlag() == 1){%>
                                 <bean:message key="TawRmGuestform.submit"/>
                            <%}else{%>
                                 <bean:message key="TawRmGuestform.nosubmit"/>
                            <%}%>
	</td>
	  <td align="center"> <html:link href="edit.do" paramId="id" paramName="tawRmGuestform" paramProperty="id"><img src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0"></html:link>&nbsp;
      </td>
</tr>
	  </logic:iterate>
</table>
<table width="95%" cellspacing="1" border="0" cellpadding="1">
<tr>
<td align="right" height="32">
      <html:submit>
         <bean:message key="label.cancel"/>
      </html:submit>
</td>
</tr>
</table>
</center>
</html:form>



