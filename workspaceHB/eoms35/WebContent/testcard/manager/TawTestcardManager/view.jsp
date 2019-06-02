<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<center>
<table cellSpacing="0" cellPadding="0" width="85%" border="0">
       <tr>
                           <td align="right" width="100%" >
                              <div align="center">
                              <table cellSpacing="0" borderColorDark="#ffffff" cellPadding="2" width="100%" class="formTable" border="1">
                                    <tr> <td  colspan="4" valign="middle" bgcolor="#E5EDF8" align="center">
							${eoms:a2u("测 试 卡 借 出 详 细 信 息")}
                                              </td></tr>
<logic:present name="tawTestcardManagerForm" scope="request">

                <td class="label" width="100" class= "clsfth">${eoms:a2u("卡号(iccid)")}</td>&nbsp
                <td width="380"  >
                      <bean:write name="tawTestcardManagerForm" property="cardid"/>
                </td>
                <td class="label" width="100" class= "clsfth">${eoms:a2u("msisdn")}</td>
                <td width="380" >
                      <bean:write name="tawTestcardManagerForm" property="msisdn"/>&nbsp
                </td>

        </tr>
        <tr>
                <td class="label" width="100" class= "clsfth">${eoms:a2u("经手人")}</td>
                <td width="380" >
                      <bean:write name="tawTestcardManagerForm" property="dealer"/>&nbsp
                </td>
                <td class="label" width="100" class= "clsfth">${eoms:a2u("借用部门")}</td>
                <td width="380" >
                      <bean:write name="tawTestcardManagerForm" property="lenddept"/>&nbsp
                </td>

        </tr>
        <tr>
                <td class="label" width="100" class= "clsfth">${eoms:a2u("借用人")}</td>
                <td  width="380" >
                      <bean:write name="tawTestcardManagerForm" property="lender"/>&nbsp
                </td>
                <td class="label" width="100" class= "clsfth">${eoms:a2u("联系方式")}</td>
                <td width="380" >
                      <bean:write name="tawTestcardManagerForm" property="contect"/>&nbsp
                </td>
        </tr>
       <tr>
               <td class="label" width="100"  class= "clsfth">
                     ${eoms:a2u("借用时间")}
              </td>
              <td width="380">
                      <bean:write name="tawTestcardManagerForm" property="leantime"/>&nbsp
              </td>
              <td class="label" width="100"  class= "clsfth">
                     ${eoms:a2u("应归还时间")}
              </td>
              <td width="380">
                      <bean:write name="tawTestcardManagerForm" property="belongtime"/>&nbsp
              </td>
       </tr>
       <tr>
               <td class="label" width="100"  class= "clsfth">
                     ${eoms:a2u("归还时间")}
              </td>
              <td width="380">
                      <bean:write name="tawTestcardManagerForm" property="returntime"/>&nbsp
              </td>
              <td class="label" width="100"  class= "clsfth">
                     ${eoms:a2u("续借期限")}
              </td>
              <td width="380">
                      <bean:write name="tawTestcardManagerForm" property="renewlimit"/>&nbsp
              </td>
       </tr>
       <tr>
            <td class="label" width="100"  class= "clsfth">
                      ${eoms:a2u("用途")}
                </td>
                <td width="380" colspan="3">
                      <bean:write name="tawTestcardManagerForm" property="reason"/>&nbsp
                </td>
       </tr>
<!--       <tr>
            <td class="label" width="100"  class= "clsfth">
                      附件：
                </td>
                <td width="380" colspan="3">
                      <bean:write name="tawTestcardManagerForm" property="accessory"/>&nbsp
                </td>
       </tr>
-->
</logic:present>

                                           <td colspan=4>
				                 <table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
					              <tr align="right" valign="middle">
						        <td>

      <html:submit styleClass="button" onclick="window.history.back(-1)">
        ${eoms:a2u("返回")}
      </html:submit>

						        </td>
					               </tr>


          </td>
	</tr>
</table>
</center>
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
