<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<center>
<%
	String formId=StaticMethod.nullObject2String(request.getAttribute("formId"));
	String taskId=StaticMethod.nullObject2String(request.getAttribute("taskId"));
%>
<table cellSpacing="0" cellPadding="0" width="85%" border="0" class="formTable">
       <tr>
                           <td align="right" width="100%" bgColor="#f5f5f5">
                              <div align="center">
   <table cellSpacing="0"  cellPadding="2" width="100%" class="formTable" border="1">
                                    <tr> <td  colspan="4" valign="middle" bgcolor="#E5EDF8" align="center">
							申请单 详细信息
                                              </td>
                             </tr>
<logic:present name="tawTestcardApplyForm" scope="request">
        <tr>
                <td noWrap width="100" class= "label">卡类型</td>
                <td colspan = 3 width="380" align="center">
                      <bean:write name="tawTestcardApplyForm" property="cardtypename"/> &nbsp
        	</td>
        </tr>
		<tr>
            <td noWrap width="100"  class= "label">存放分公司</td>
                <td width="380">
                    <bean:write name="tawTestcardApplyForm" property="leaveidname"/> &nbsp
                </td>
          <td noWrap width="100"  class= "label">
            测试卡套餐类型
          </td>
          <td width="380">
                  <bean:write name="tawTestcardApplyForm" property="cardpackagename" />&nbsp
          </td>
       </tr>
				<tr>
					<td noWrap bgcolor="#E5EDF8" width="80" class="label">
						申请单主题
					</td>
					<td width="380" colspan="3">
                       <bean:write  name="tawTestcardApplyForm" property="formName" />&nbsp
					</td>
				</tr>

 				<tr>
					<td noWrap bgcolor="#E5EDF8" width="80" class="label">
						审批者
					</td>
					<td width="380" colspan="3">
                       <bean:write  name="tawTestcardApplyForm" property="auditOrgName" />&nbsp
					</td>
				</tr>
       <tr>
            <td noWrap width="100"  class= "label">
            申请原因
                </td>
                <td width="380" colspan="3">
                       <bean:write  name="tawTestcardApplyForm" property="applyreason" />&nbsp
                </td>      </tr>
</logic:present>
<table border="0" width="95%" cellspacing="1" class="listTable">
<tr>
	<td width="100%" colspan="14" bgcolor="#E5EDF8" align="center" height="25">
		<b>
			已选择的测试卡 列表&nbsp; 
 		</b> 
	</td>
</tr>
<tr bgcolor="#FFFFFF">
       <td nowrap class="label"  align="center" height="25">
          存放公司
        </td>
        <td nowrap class="label"  align="center" height="25">
          手机号码
        </td>
</tr>
   <logic:iterate id="tawTestcard" name="tawTestcard" type="com.boco.eoms.testcard.model.TawTestcard">
<tr bgcolor="#FFFFFF">
     <td nowrap bgcolor="#E5EDF8" align="center">
                    <bean:write name="tawTestcard" property="leavename" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" >
                    <bean:write name="tawTestcard" property="phoneNumber" scope="page"/>
    </td>

</tr>
</logic:iterate>
</table>
<html:form  method="post" action="/TawTestcardApply/audit" styleId="tawTestcardApplyForm">
<script language="JavaScript">
function beforesubmit(flag){
	if(document.forms[0].auditInfo.value==""){
		alert("审批意见");
		return false;
	}
	if(flag == 11){
		document.forms[0].auditflag.value = "11";
	}
	if(flag == 12){
		document.forms[0].auditflag.value = "12";
	}
	return true;
}
</script>
<table cellSpacing="0" cellPadding="0" width="85%" border="0">
				<tr>
					<td noWrap bgcolor="#E5EDF8" width="80" class="label">
						审核意见
					</td>
					<td width="380" colspan="3">
						<html:textarea property="auditInfo" rows="4" cols="88" title="审核意见" />
					</td>
				</tr>
				<html:hidden property="formId" value="<%=formId%>"/>
				<html:hidden property="taskId" value="<%=taskId%>"/>
				<html:hidden property="auditflag"/>
</table>
<table cellSpacing="0" cellPadding="0" width="85%" border="0">
	<tr align="left" valign="middle">
		<td>
      	<html:submit onclick="return beforesubmit(12);">
        	审批通过
      	</html:submit>
      	<html:submit onclick="return beforesubmit(11);">
        	驳回
      	</html:submit>
      	<html:submit onclick="window.history.back(-1)">
        	返回
      	</html:submit>

		</td>
	</tr>
</table>
</html:form>
</table>
</table>
</center>
