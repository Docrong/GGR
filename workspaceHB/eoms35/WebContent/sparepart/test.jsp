
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
 <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.department"/>£º</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <select name="department" id="s1" style="width: 6.8cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.necode"/>£º</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="necode" id="s2" style="width: 6.8cm;"></select>
                  </td>
    </tr>
     <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('?????')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="objecttype" id="s3" style="width: 6.8cm;"></select>
                  </td>
    </tr>