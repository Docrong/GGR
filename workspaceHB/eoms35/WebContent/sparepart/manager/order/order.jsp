
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<title>
<bean:write name="msg" scope="request"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<br>
<form action="../part/term.do" method="post" name="TawOrderForm" onsubmit="return checkdata()">
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b><bean:write name="msg" scope="request"/></b>
      </td>
    </tr>
</table>
<html:hidden property="orderType" name="tawOrderForm" />
<table border="0" width="75%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
        ${eoms:a2u('您当前操作的仓库是：')}<b>&nbsp;&nbsp;<bean:write name="storage" scope="session"/></b>
      </td>
    </tr>
</table>
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="order.operater"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" readonly="readonly" value="<bean:write name="userId" scope="request"/>"size="35"  maxlength="50" name="operater">
                   <font color="#FF0000">**</font></td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="order.proposer"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" name="proposer"size="35"  maxlength="255" >
                   <font color="#FF0000">**</font></td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="order.prop_dept"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" name="prop_dept" size="35"  maxlength="255" >
                  <font color="#FF0000">**</font></td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="order.prop_tel"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" name="prop_tel" size="35"  maxlength="255" >
                  <font color="#FF0000">**</font></td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('工单号：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" name="sheetid" size="35"  maxlength="255" >
                  <font color="#FF0000">**</font></td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('借出依据：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" name="reason" size="35"  maxlength="255" >
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('使用站点：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" name="station" size="35"  maxlength="255" >
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('设备厂商：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" name="fixe" size="35"  maxlength="255" >
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('版本号：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" name="version" size="35"  maxlength="255" >
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('序列号：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" name="serialno" size="35"  maxlength="255" >
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('实物名称：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" name="ename" size="35"  maxlength="255" >
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('设备类型：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" name="objtype" size="35"  maxlength="255" >
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="order.note"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" name="note" size="35"  maxlength="255" >
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
function checkdata() {

        if ( document.TawOrderForm.operater.value == "" ) {
                alert('${eoms:a2u("请输入经手人！")}');
                document.TawOrderForm.operater.focus();
                return false;
        }

        if ( document.TawOrderForm.proposer.value == "" ) {
                alert('${eoms:a2u("请输入申请人！")}');
                document.TawOrderForm.proposer.focus();
                return false;
        }
        if ( document.TawOrderForm.proposer.value == document.TawOrderForm.operater.value ) {
                alert('${eoms:a2u("经手人与申请人不应相同！")}');
                document.TawOrderForm.proposer.focus();
                return false;
        }
        if ( document.TawOrderForm.prop_dept.value == "" ) {
                alert('${eoms:a2u("请输入申请部门！")}');
                document.TawOrderForm.prop_dept.focus();
                return false;
        }

        if ( document.TawOrderForm.prop_tel.value == "" ) {
                alert('${eoms:a2u("请输入部门电话！")}');
                document.TawOrderForm.prop_tel.focus();
                return false;
        }
        /*
        if ( document.TawOrderForm.flditimelong.value == "" )
        {
             document.TawOrderForm.flditimelong.value="120";
        }
        else
        {
           if(!IsNumber(document.TawOrderForm.flditimelong.value))
           {
              alert("保修时长必须为数字!");
              document.form1.flditimelong.focus();
              return false;
           }
        }
        */
       return true;
}
</script>

