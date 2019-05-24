<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.ArrayList,java.util.List,com.boco.eoms.common.util.StaticMethod" %>
<%@ page import="com.boco.eoms.sparepart.model.*"%>
<html>
<head>
<title>
<bean:message key="class.maintenance"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center"><b><bean:message key="class.maintenance"/></b>
      </td>
    </tr>
</table>
<form method="post" action="../storage/class.do"  name="tt">
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="class.choose"/>：</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                        <select name="parentId" size="1" style="FONT-SIZE: 8pt">
                           <option value=""><bean:message key="class.choose"/></option>
<logic:iterate id="type" name="type" type="com.boco.eoms.sparepart.model.TawClassMsg">
                           <option value="<bean:write name="type" property="id" scope="page"/>"><bean:write name="type" property="cname" scope="page"/></option>
</logic:iterate>
                        </select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="class.name"/>：</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="35" value=""  maxlength="50" name="cname">
                    <font color="#FF0000">**</font>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="class.note"/>：</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" name="note" size="40"   maxlength="255" >
                  </td>
    </tr>
</table>
<table border="0" width="75%" cellspacing="0">
    <tr>
       <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.submit"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
          <input type="submit" value="<bean:message key="label.query"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
</table>
</form>
<logic:present name="classMsg">
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align="center">
        <tr class="tr_show">
              <td colspan="4" class="table_title" align="center"><bean:message key="class.msg"/></td>
        </tr>
        <tr class="td_label">
                      <td align="center"><bean:message key="class.name"/></td>
                      <td align="center"><bean:message key="class.note"/></td>
                      <td align="center"><bean:message key="label.edit"/></td>
                      <td align="center"><bean:message key="label.remove"/></td>
        </tr>
<logic:iterate id="classMsg" name="classMsg" type="com.boco.eoms.sparepart.model.TawClassMsg">
        <tr class="tr_show">
         <td><bean:write name="classMsg" property="cname" scope="page"/></td>
         <td><bean:write name="classMsg" property="note" scope="page"/></td>
         <td><p align="center"><a href="../storage/editclass.do?id=<bean:write name="classMsg" property="id" scope="page"/>"><img src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0"></a></p></td>
         <td><p align="center"><img src="<%=request.getContextPath()%>/images/bottom/an_sc.gif" border="0"
Onclick="onPublic(<bean:write name='classMsg' property='id' scope='page'/>);"></td>
        </tr>
</logic:iterate>
<script language="javascript">
function onPublic(aaa)
    {
      if( !confirm('${eoms:a2u("删除后不能恢复，您是否确认删除该类型信息？")}') ) return ;
      window.navigate("../storage/dropclass.do?id="+aaa);
    }
</script>
</table>
</logic:present>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>
</html>


