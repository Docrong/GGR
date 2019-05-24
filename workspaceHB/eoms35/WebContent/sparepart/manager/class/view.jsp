
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<title>
${eoms:a2u('备件属性')}备件属性
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b>${eoms:a2u('备件属性')}备件属性</b>
      </td>
    </tr>
</table><table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align="center">
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
         <td><a href="../storage/editclass.do?id=<bean:write name="classMsg" property="id" scope="page"/>"><img src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0"></a></td>
         <td><img src="<%=request.getContextPath()%>/images/bottom/an_sc.gif" border="0"
                 Onclick="onPublic(<bean:write name='classMsg' property='id' scope='page'/>);"></td>
        </tr>
          </logic:iterate>
        <tr class="tr_show">
         <form action="../class/add.do" method="post" name="tawTreeForm">
            <html:hidden property="parentId" name="tawClassMsgForm" />
           <td height="32" align="right" colspan="4">
              <input  align="right" type="submit" value="<bean:message key="label.add"/>" name="submit" class="clsbtn2">
                 &nbsp;&nbsp;
           </td>
         </form>
        </tr>
<script language="javascript">
function onPublic(aaa)
    {
      if( !confirm('${eoms:a2u("删除后不能恢复，您是否确认删除该类型信息？")}') ) return ;
      window.navigate("../storage/dropclass.do?id="+aaa);
    }
</script>
</table>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>
</html>