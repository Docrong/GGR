<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="com.boco.eoms.base.util.StaticMethod"%>
<script language="javascript">
function SubmitCheck(){
  frmReg = document.tawwpmodeladd;
  
  if(frmReg.leave.value == '0'||frmReg.leave.value == '-1'||frmReg.leave.value == ''){
 
    alert("${eoms:a2u('è¯·éæ©å­æ¾åä½')}");
   
    return false;
  }
  
  if(frmReg.theFile.value == ''){
 
    alert("${eoms:a2u('è¯·éæ©è¦å¯¼å¥çæä»¶')}");
   
    return false;
  }
  return true;
}
</script>
<form name="tawwpmodeladd" action="../TawTestcard/toload.do" method="post" enctype="multipart/form-data" onsubmit='return SubmitCheck()'>
<%
String id=StaticMethod.nullObject2String(request.getParameter("id")); 

%>

<input type="hidden" name="id" value="<%=id%>"/>
<br>
<table border="0" cellspacing="1" cellpadding="1" class="formTable" align=center>
    <tr class="td_label">
    <td colspan="1" class="label" align="center">${eoms:a2u('å­æ¾å¬å¸')}</td>
                <td colspan="2">
                    <eoms:comboBox name="leave" id="a1" sub="a2" initDicId="10401"/>
                </td>
      </tr>

  <tr class="label">
    <td class="label" align="center">${eoms:a2u(' å¯¼å¥æ¨¡ç')}</td>
    <td >
    <a href="../../testcard/manager/TawTestcard/down.jsp?fileName=jiruceshitaizhang.xls&&path=excelmodel">${eoms:a2u('ä¸è½½æµè¯å¡æ¨¡ç')}</a>&nbsp&nbsp&nbsp

    </td>
  </tr>
    <tr class="label">
      <td colspan="3" align="center" class="label">
        <b>${eoms:a2u('æµè¯å¡æ¹éå¥åº')}</b>
      </td>
    </tr>
      <tr class="tr_show">
                  <td  class="label" align="center">&nbsp;&nbsp;${eoms:a2u('å¯¼å¥æä»¶')}                
                                   </td>
                                   <td>&nbsp;&nbsp;
                  <input type="file" name="theFile" size="40" value=""  maxlength="255"/>
                                   </td>
                </tr>
          <tr class="tr_show">
                  <td colspan="3" align="center">
        <input class="button"  type="submit" value="${eoms:a2u('å¯¼å¥EXCELè¡¨æ ¼')}" name="submit" >
      </td>
                </tr>
</table>
<logic:notEmpty name="message">
			<script type="text/javascript">
			<!--
			alert('<bean:write name="message"/>');
			//-->
			</script>
		</logic:notEmpty>
		<logic:notEmpty name="falseMessage">
			<script type="text/javascript">
			<!--
			alert('<bean:write name="falseMessage"/>');
			//-->
			</script>
		</logic:notEmpty>
		

</form>



