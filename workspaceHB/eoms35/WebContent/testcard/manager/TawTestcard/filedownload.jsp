<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%
String downloadFileUrl=(String)request.getParameter("url");
%>
<table align=center border=0 cellspacing="1" cellpadding="1" class="table_show">
  <tr class="tr_show">
    <td width="30%" class="td_label">
         <a href="../../../testcard/tempfiledownload/<%=downloadFileUrl%>">обть</a>
    </td>
</tr>
</table>
