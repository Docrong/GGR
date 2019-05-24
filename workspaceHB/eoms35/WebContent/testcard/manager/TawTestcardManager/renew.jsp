<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import=" com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%
          TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
          String deptName=saveSessionBeanForm.getDeptname();   
          
 String idlist =(String)request.getAttribute("idlist");
%> 
<html:form  method="post" action="/TawTestcardManager/renewupdate" >
<div align="center">
<table border="0" width="70%" cellspacing="1" class="formTable">
    <tr><td colspan="4" valign="middle" bgcolor="#E5EDF8" align="center">${eoms:a2u("测 试 卡 续 借")}</td></tr>
	<logic:present name="tawTestcardManagerForm" scope="request">
	<!--<html:hidden property="id"/>
                <td noWrap width="100" class= "clsfth">卡号(iccid)</td>&nbsp
                <td width="380">
                      <bean:write name="tawTestcardManagerForm" property="cardid"/>
                </td>
                <td noWrap width="100" class= "clsfth">电话号码</td>
                <td width="380" >
                      <bean:write name="tawTestcardManagerForm" property="msisdn"/>&nbsp
                </td>

        </tr>
        <tr>
                <td noWrap width="100" class= "clsfth">借出人</td>
                <td width="380">
                      <bean:write name="tawTestcardManagerForm" property="dealer"/>&nbsp
                </td>
                 <td noWrap width="100"  class= "clsfth">
                     借用时间
              </td>
              <td width="170" nowrap="nowrap">
                      <bean:write name="tawTestcardManagerForm" property="leantime"/>&nbsp
              </td>                
        </tr>
        <tr>
                <td noWrap width="100" class= "clsfth">借用人</td>
                <td  width="380" >
                      <bean:write name="tawTestcardManagerForm" property="lender"/>&nbsp
                </td>            
              <td noWrap width="100"  class= "clsfth">
                     应归还时间
              </td>
              <td width="170" nowrap="nowrap">
                      <bean:write name="tawTestcardManagerForm" property="belongtime"/>&nbsp
              </td>
        </tr>
       
              <td noWrap width="100" class= "clsfth">借用部门</td>
                <td width="380" >
                      <bean:write name="tawTestcardManagerForm" property="lenddept"/>&nbsp
                </td>-->
       <tr>
            <td nowrap class="label" align="center">${eoms:a2u("续借人")}</td>
            <td nowrap   ><html:text property="renewer" readonly="false"/></td>
            <td nowrap class="label" align="center">${eoms:a2u("续借部门")}</td>
            <td nowrap   >
                <html:text styleClass="clstext" name="tawTestcardManagerForm" property="relenddept"/>&nbsp
            </td>
       </tr>
       <tr>
            <td nowrap class="label" align="center">${eoms:a2u("续借期限(天)")}</td>
            <td colspan="3" nowrap   >
                <html:text styleClass="clstext" name="tawTestcardManagerForm" property="renewlimit" value=""/>&nbsp
            </td>

       </tr>
       <tr>
            <td nowrap class="label" align="center">${eoms:a2u("续借说明")}</td>
            <td colspan="3" nowrap   >
                  <html:text size="70" property="reason" name="tawTestcardManagerForm" styleClass="clstext"></html:text>&nbsp
                  <INPUT TYPE="hidden" name="idlist" id="idlist" value="<%=idlist%>">
            </td>
       </tr>
      </logic:present>
       <tr >
                  <td colspan=4 nowrap bgcolor="#E5EDF8" align="center"><html:submit styleClass="clsbtn2" >${eoms:a2u("保存")}</html:submit></td>
 </tr>
</table>
</div>
</html:form>
<logic:messagesPresent>
                  <html:messages id="error">
	<script type="text/javascript">
		<!--
                    alert("<bean:write name="error"/>");
		-->
	</script>
                  </html:messages>
</logic:messagesPresent>
</body>
