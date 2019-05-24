
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.sparepart.model.*"%>
<head>
<title>
<bean:message key="class.msg"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b><bean:message key="class.msg"/></b>
      </td>
    </tr>
</table>
<br>
<logic:present name="classMsg">
<table border="0" width="75%" cellspacing="1" class="table_show" align="center">
        <tr class="tr_show">
              <td colspan="4" class="table_title" align="center">
                ${eoms:a2u(所属专业：)}<bean:write name="tawClassMsgForm" property="department" scope="request"/>
                ${eoms:a2u(网元类型：)}<bean:write name="tawClassMsgForm" property="necode" scope="request"/>
              </td>
        </tr>
         <tr class="tr_show">
              <td width="106%" colspan="10" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/>
              <%! String key;%></td>
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
          <td align="center"><a href="../storage/editclass.do?id=<bean:write name="classMsg" property="id" scope="page"/>"><img src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0"></a></td>
          <td align="center"><img src="<%=request.getContextPath()%>/images/bottom/an_sc.gif" border="0"
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
<table border="0" width="75%" cellspacing="1">
  <tr>
    <td width="100%" height="32" align="right">
     &nbsp;&nbsp;
     <INPUT class="clsbtn2" id=button type=button value=<bean:message key="label.add"/> name=button
Onclick="window.location.href ='../storage/addclass.do?id=<bean:write name='tawClassMsgForm' property='id' scope='request'/>&deleted=4'">
     &nbsp;&nbsp;
    </td>
  </tr>
</table>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>
</html>