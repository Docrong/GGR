<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.TawClassMsg,com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>
 
<%
 TawClassMsgTree wk_tree = new TawClassMsgTree();
 String strTree = wk_tree.dong("2");
%>
<SCRIPT LANGUAGE = JavaScript>
<!--
//** Power by Fason(2004-3-11)
//** Email:fason_pfx@hotmail.com

var s=["s1","s2","s3"];
var opt0 = ['${eoms:a2u("全选")}','${eoms:a2u("全选")}','${eoms:a2u("全选")}'];

var dsy = new Dsy();
<%=strTree%>

function setup()
{
	for(i=0;i<s.length-1;i++)
		document.getElementById(s[i]).onchange=new Function("change(dsy,"+(i+1)+",s,opt0)");
	change(dsy,0,s,opt0);
}
//-->
</script> 
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b><bean:message key="storage.stat"/></b>
      </td>
    </tr>
</table>
<form action="../query/statview.do" method="post" name="frm" onsubmit="checkdata()">
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.department"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <select name="nettype" id="s1" style="width: 4.4cm;"></select>
                  </td>
    </tr>
	 <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('小专业：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <select name="subdept" id="s2" style="width: 4.4cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.necode"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="necode" id="s3" style="width: 4.4cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.objectname"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="22" value=""  maxlength="50" name="objecttype">${eoms:a2u('(维护信息)')}
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="storage.name"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="storage" size="1"  style="width: 4.4cm;">
                            <option value=""><bean:message key="label.selall"/></option>
            <logic:iterate id="storage" name="storage" type="com.boco.eoms.sparepart.model.TawStorage">
                           <option value="<bean:write name="storage" property="id" scope="page"/>"><bean:write name="storage" property="storagename" scope="page"/></option>
            </logic:iterate>
                        </select>
                     </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.state"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="state" size="1"  style="width: 4.4cm;">
                            <option value=""><bean:message key="label.selall"/></option>
            <logic:iterate id="state" name="state" type="com.boco.eoms.sparepart.model.TawClassMsg">
                           <option value="<bean:write name="state" property="id" scope="page"/>"><bean:write name="state" property="cname" scope="page"/></option>
            </logic:iterate>
                        </select>
                     </td>
    </tr>
     <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.timein"/>${eoms:a2u('：')}</p>
                  </td>
                   <td width="70%" height="25" colspan="2">
                    <app:SelectDate name="timein" formName="frm" value="<%=StaticMethod.getLocalString() %>"/>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.timeend"/>${eoms:a2u('：')}</p>
                  </td>
                   <td width="70%" height="25" colspan="2">
                    <app:SelectDate name="timeend" formName="frm" value="<%=StaticMethod.getLocalString() %>"/>
                  </td>
    </tr>
</table>
<script language="javascript">
function checkdata() {

        if ( document.frm.nettype.value == '${eoms:a2u("全选")}' ) {
                document.frm.nettype.value="";
        }
        if ( document.frm.necode.value == '${eoms:a2u("全选")}' ) {
                 document.frm.necode.value="";
        }
		 if ( document.frm.subdept.value == '${eoms:a2u("全选")}' ) {
                 document.frm.subdept.value="";
        }
        if ( document.frm.objectname.value == '${eoms:a2u("备件名称")}' ) {
                  document.frm.objectname.value="";
        }
}
</script>
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.query"/>" name="statterm" class="clsbtn2">
          &nbsp;&nbsp;
          &nbsp;&nbsp;
      </td>
    </tr>
</table>
</form>
<%@ include file="/common/footer_eoms.jsp"%>
