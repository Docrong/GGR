<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<center>
<table cellSpacing="0" cellPadding="0" width="85%" border="0">
       <tr>
                           <td align="right" width="100%" bgColor="#f5f5f5">
                              <div align="center">
                              <table cellSpacing="0" borderColorDark="#ffffff" cellPadding="2" width="100%" borderColorLight="#808080" border="1">
                                    <tr> <td  colspan="4" valign="middle" bgcolor="#E5EDF8" align="center">
							测 试 卡 使 用 详 细 信 息
                                              </td></tr>
<logic:present name="tawTestcardManagerForm" scope="request">

                <td noWrap width="100" class= "clsfth">卡号(iccid)</td>&nbsp
                <td width="380"  >
                      <bean:write name="tawTestcardManagerForm" property="cardid"/>
                </td>
                <td noWrap width="100" class= "clsfth">电话号码</td>
                <td width="380" >
                      <bean:write name="tawTestcardManagerForm" property="telnum"/>&nbsp
                </td>

        </tr>
        <tr>
                <td noWrap width="100" class= "clsfth">经手人</td>
                <td width="380" >
                      <bean:write name="tawTestcardManagerForm" property="dealer"/>&nbsp
                </td>
                <td noWrap width="100" class= "clsfth">使用部门</td>
                <td width="380" >
                      <bean:write name="tawTestcardManagerForm" property="lenddept"/>&nbsp
                </td>

        </tr>
        <tr>
                <td noWrap width="100" class= "clsfth">使用人</td>
                <td  width="380" >
                      <bean:write name="tawTestcardManagerForm" property="lender"/>&nbsp
                </td>
                <td noWrap width="100" class= "clsfth">联系方式</td>
                <td width="380" >
                      <bean:write name="tawTestcardManagerForm" property="contect"/>&nbsp
                </td>
        </tr>
       <tr>
               <td noWrap width="100"  class= "clsfth">
                     使用开始时间
              </td>
              <td width="380">
                      <bean:write name="tawTestcardManagerForm" property="leantime"/>&nbsp
              </td>
              <td noWrap width="100"  class= "clsfth">
                     应归还时间
              </td>
              <td width="380">
                      <bean:write name="tawTestcardManagerForm" property="belongtime"/>&nbsp
              </td>
       </tr>
       <tr>
               <td noWrap width="100"  class= "clsfth">
                     归还时间
              </td>
              <td width="380" colspan="3">
                      <bean:write name="tawTestcardManagerForm" property="returntime"/>&nbsp
              </td>
       </tr>
       <tr>
            <td noWrap width="100"  class= "clsfth">
                      用途
                </td>
                <td width="380" colspan="3">
                      <bean:write name="tawTestcardManagerForm" property="reason"/>&nbsp
                </td>
       </tr>
<!--       <tr>
            <td noWrap width="100"  class= "clsfth">
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

      <html:submit styleClass="clsbtn2" onclick="window.history.back(-1)">
        返回
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
