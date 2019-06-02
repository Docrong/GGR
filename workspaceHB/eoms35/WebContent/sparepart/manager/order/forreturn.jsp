
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.TawClassMsg,com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>
<html>
<head>
<%
 String partTypeStr =(String)request.getAttribute("StringTree");
 String storageStr =(String)request.getAttribute("StorageTree");
%>
<title>
${eoms:a2u('备件查询')}
</title>
<style>
body,select
{
	font-size:9pt;
	font-family:Verdana;
}
select {background-color:#F0F0F0;}
</style>
<script language="JavaScript" src="<%=request.getContextPath()%>/css/area.js"></script>


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif" ">
<div align="center">
<br>
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u('备件查询')}</b>
      </td>
    </tr>
</table>
<form action="../part/return.do" method="post" name="frm" >
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  	
 <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.supplier"/>：</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="supplier" size="1" style="width: 4.4cm;">
                            <option value=""><bean:message key="label.selall"/></option>
<logic:iterate id="supplier" name="supplier" type="com.boco.eoms.sparepart.model.TawClassMsg">
                           <option value="<bean:write name="supplier" property="id" scope="page"/>"><bean:write name="supplier" property="cname" scope="page"/></option>
</logic:iterate>
                        </select>
                     </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.objectname"/>：</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="22"  maxlength="50" name="objectname">
                    </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.serialno"/>：</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="22"  maxlength="50" name="serialno">
                    </td>
    </tr>
    
    

</table>


<table border="0" width="75%" cellspacing="0">
    <tr>
      <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.query"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
          &nbsp;&nbsp;
      </td>
    </tr>
</table>
</form>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
