<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>




<html:form action="bureaudataHlr.do?method=searchDo">
<input type="hidden" class="text"  name="deleted" value="0">

<table class="formTable">
<TR>
<td width="100%" class= "label"  colspan="2">${eoms:a2u('查询HLR数据')}</td>
</tr>
<tr>
<TD align="right">
</TD>
</TR>
  <tr class="tr_show">
      <td class= "label" width="20%">${eoms:a2u('HLR名称')}</td>
      <td width="70%">
      <input type="text" class="text"  name="hlrname">
      </td>
  </tr>
      <tr class="tr_show">
      <td class= "label" width="20%">${eoms:a2u('HLR信令点')}</td>
      <td width="70%"><input type="text" class="text"  name="hlrsignalid"></td>
  </tr>
      <tr class="tr_show">
      <td class= "label" width="20%">${eoms:a2u('HLR ID')}</td>
      <td width="70%"><input type="text" class="text"  name="hlrid"></td>
  </tr>
<TR>
	<TD align="right"  colspan="2">
      <html:submit styleClass="btn" >
        ${eoms:a2u('查询')}
      </html:submit>
&nbsp;
      </TD>
</TR>
</table>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
