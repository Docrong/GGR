
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.ArrayList,java.util.List,com.boco.eoms.common.util.StaticMethod" %>
<%@ page import="com.boco.eoms.sparepart.model.*"%>
<html>
<head>
<title>
<bean:message key="remind.setorder"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b><bean:message key="remind.setorder"/></b>
      </td>
    </tr>
</table>
<br>
<form name="frm" action="../stock/overorder.do" method="post" onsubmit="return checkdata()">
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="storage.name"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="storageid" size="1" style="width: 6.8cm;">
                            <option value=""><bean:message key="label.choose"/></option>
            <logic:iterate id="storage" name="storage" type="com.boco.eoms.sparepart.model.TawStorage">
                           <option value="<bean:write name="storage" property="id" scope="page"/>"><bean:write name="storage" property="storagename" scope="page"/></option>
            </logic:iterate>
                        </select>
                     </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="order.type"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <select name="type" size="1" style="width: 6.8cm;">
                           <option value=""><bean:message key="label.choose"/></option>
                           <option value="1"><bean:message key="load.limit"/></option>
                           <option value="3"><bean:message key="service.limit"/></option>
                           <option value="4"><bean:message key="fault.limit"/></option>
                        </select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="order.limitdate"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" name="limitdate"size="35" value=""  maxlength="255" ><font color="#FF0000">**</font>${eoms:a2u('（天数）')}
                  </td>
    </tr>
</table>
<table border="0" width="75%" cellspacing="0">
    <tr>
       <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.submit"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
          <input type="reset" value="<bean:message key="label.reset"/>" name="reset" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
  </table>
</form>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
<script language="javascript">
function IsDigit(cCheck)
	{
	return (('0'<=cCheck) && (cCheck<='9'));
	}

function IsNumber(str)
{
	for (var nIndex=0; nIndex<str.length; nIndex++)
		{
		cCheck = str.charAt(nIndex);
		if (!(IsDigit(cCheck) || cCheck=='.'))
			{
			return false;
			}
		}
   return true;
}
function checkdata() {
        if ( document.frm.storageid.value == "" ) {
                alert('${eoms:a2u("请选择仓库！")}');
                document.frm.storageid.focus();
                return false;
        }
        if ( document.frm.type.value == "" ) {
                alert('${eoms:a2u("请选择单据类型！")}');
                document.frm.type.focus();
                return false;
        }
        if ( document.frm.limitdate.value == "" ) {
                alert('${eoms:a2u("请单据限制的天数！")}');
                document.frm.limitdate.focus();
                return false;
        }else{
           if(!IsNumber(document.frm.limitdate.value))
           {
              alert('${eoms:a2u("保修时长必须为数字!")}');
              document.frm.limitdate.focus();
              return false;
           }
        }

       return true;
}
</script>



