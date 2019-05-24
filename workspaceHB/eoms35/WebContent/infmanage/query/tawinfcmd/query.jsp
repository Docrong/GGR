<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
<head>
<title>≤È—Ø√¸¡Óø‚◊ ¡œ</title>
<html:base/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<html:form action="/TawInfCmd/list" method="post">
<body>
    <br>
	  <table border="0" width="100%" cellspacing="0" align="center">
	    <tr>
		  <td width="100%" align="center" class="table_title">
            <b>
              <bean:message key="label.query"/>&nbsp;<bean:message key="TawInfCmd.Name"/>
	  	    </b>
		  </td>
		</tr>
	  </table>
	  <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
	    <tr class="tr_show">
          <td width="30%" height="25" class="clsfth">&nbsp;
	  	    &nbsp<bean:message key="TawInfCmd.Select_swich"/>
		  </td>
          <td width="70%" height="25">
		    <html:select property="cmdSwich">
			  <html:optionsCollection name="tawInfCmdForm" property="collectionSwich"/>
			</html:select>
		  </td>
		</tr>
		<tr class="tr_show">
		  <td width="30%" height="25" class="clsfth">&nbsp;
			&nbsp<bean:message key="TawInfCmd.Cmd_id"/>
		  </td>
          <td width="70%" height="25">
			<html:text styleClass="clstext" property="cmdId" size="20"/>
		  </td>
		</tr>
		<tr class="tr_show">
          <td width="30%" height="25" class="clsfth">&nbsp;
			&nbsp<bean:message key="TawInfCmd.Cmd_name"/>
		  </td>
          <td width="70%" height="25">
			<html:text styleClass="clstext" property="cmdName" size="20"/>
		  </td>
		</tr>
	  	<tr class="tr_show">
		  <td width="30%" height="25" class="clsfth">&nbsp;
			&nbsp<bean:message key="TawInfCmd.Cmd_param"/>
		  </td>
		  <td width="70%" height="25">
			<html:text styleClass="clstext" property="cmdParam" size="20"/>
          </td>
		</tr>
		<tr class="tr_show">
		  <td width="30%" height="25" class="clsfth">&nbsp;
			&nbsp<bean:message key="TawInfCmd.Param_scope"/>
		  </td>
          <td width="70%" height="25">
			<html:text styleClass="clstext" property="paramScope" size="20"/>
		  </td>
		</tr>
	  </table>
      <table border="0" width="100%" cellspacing="0" align="center">
		<tr>
          <td width="100%" colspan="2" height="32" align="right">
			<input Class="clsbtn2" type="button" name="toadd" value="<bean:message key="label.add"/>" onClick="toAdd()">
			&nbsp;
			<input  Class="clsbtn2" type="button" name="tosubmit" value="<bean:message key="label.query"/>" onClick="toSubmit()">
			&nbsp;
          </td>
		</tr>
      </table>
  </body>
</html:form>
</html:html>

<script language="javascript">
  function toSubmit(){
    window.document.tawInfCmdForm.submit();
  }

  function toAdd(){
	window.location.href="add.do";
  }
</script>

