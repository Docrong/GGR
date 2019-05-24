<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<html:form  method="post" action="/TawTestcard/trash.do" >
<html:hidden property="iccid"/>
<center>
<table cellSpacing="0" cellPadding="0" width="85%" border="0">
       <tr>
       		<td align="right" width="100%" bgColor="#f5f5f5">
                <div align="center">
                <table cellSpacing="0" borderColorDark="#ffffff" cellPadding="2" width="100%" borderColorLight="#808080" border="1">
                <tr> <td  colspan="4" valign="middle" bgcolor="#E5EDF8" align="center">
                æµ è¯ å¡ å  é¤ ç¡® è®¤
                </td></tr>
<logic:present name="tawTestcardForm" scope="request">
        <tr>
                <td noWrap width="100" class= "clsfth">å¡ç±»å</td>
                <td colspan = 3 width="380" align="center">
                      <bean:write name="tawTestcardForm" property="cardType"/> &nbsp
       	        </td>
        </tr>
       <tr>
                <td noWrap width="100" class= "clsfth">å½å±å°</td>
                <td colspan = 3 width="380" align="center">
                       <bean:write name="tawTestcardForm" property="fromCanton" /> &nbsp;&nbsp
                     <bean:write name="tawTestcardForm" property="fromCountry" /> &nbsp;&nbsp

                      <bean:write name="tawTestcardForm" property="fromOpe"/> &nbsp

        	</td>
        </tr>
       <tr>
                <td noWrap width="100" class= "clsfth">å½å±å°</td>
                <td colspan = 3 width="380" align="center">
                       <bean:write name="tawTestcardForm" property="fromCrit" /> &nbsp;&nbsp

                      <bean:write name="tawTestcardForm" property="fromCity"/> &nbsp

        	</td>
        </tr>
       <tr>
                <td noWrap width="100" class= "clsfth">æè®¿å°</td>
                <td colspan = 3 width="380" align="center">
                      <bean:write name="tawTestcardForm" property="toCanton"/> &nbsp;&nbsp

                      <bean:write name="tawTestcardForm" property="toCountry"/> &nbsp;&nbsp


                      <bean:write name="tawTestcardForm" property="toOpe"/> &nbsp

                      </html:select>
        	</td>
        </tr>
       <tr>
                <td noWrap width="100" class= "clsfth">æè®¿å°</td>
                <td colspan = 3 width="380" align="center">
                      <bean:write name="tawTestcardForm" property="toCrit"/> &nbsp;&nbsp

                     <bean:write name="tawTestcardForm" property="toCity" /> &nbsp


        	</td>
        </tr>

       <tr>
                <td noWrap width="100"  class= "clsfth">
                      å­æ¾ä»åº
                </td>
                <td width="380">
                    <bean:write name="tawTestcardForm" property="leave"/> &nbsp
                </td>
                <td noWrap width="100"  class= "clsfth">
                      å¡å·(iccid)
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm"  property="iccid" /> &nbsp
                </td>
       </tr>
       <tr>
               <td noWrap width="100"  class= "clsfth">
                     msisdn
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm"  property="msisdn" /> &nbsp
                </td>
                <td noWrap width="100"  class= "clsfth">
                      çµè¯å·ç 
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="telnum" /> &nbsp
                </td>
       </tr>
       <tr>
               <td noWrap width="100"  class= "clsfth">
                      ä¸ªäººè¯å«ç 1
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="pin1" /> &nbsp
                </td>
                <td noWrap width="100"  class= "clsfth">
                      ä¸ªäººè¯å«ç 2
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="pin2" /> &nbsp
                </td>
       </tr>
       <tr>
               <td noWrap width="100"  class= "clsfth">
                      è§£éç 1
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="puk1" /> &nbsp
                </td>
                <td noWrap width="100"  class= "clsfth">
                      è§£éç 2
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="puk2" /> &nbsp
                </td>
       </tr>
       <tr>
               <td noWrap width="100"  class= "clsfth">
                      å¼æºå¯ç 
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="password" /> &nbsp
                </td>
                <td noWrap width="100"  class= "clsfth">
                     æ§ç³»ç»ç¼å·
               </td>
               <td width="380">
                    <bean:write name="tawTestcardForm" property="oldNo"/> &nbsp
               </td>
       </tr>
       <tr>
               <td noWrap width="100"  class= "clsfth">
                     å¼å§æ¶é´
              </td>
              <td width="380">
                     <bean:write name="tawTestcardForm" property="begintime"/> &nbsp
              </td>
              <td noWrap width="100"  class= "clsfth">
                     æ³¨éæ¶é´
              </td>
              <td width="380">
                    <bean:write name="tawTestcardForm" property="endtime"/> &nbsp
              </td>
       </tr>
       <tr>
               <td noWrap width="100"  class= "clsfth">
                     å¥åºæ¶é´
              </td>
              <td width="380">
                     <bean:write name="tawTestcardForm" property="intime" /> &nbsp
              </td>
              <td noWrap width="100"  class= "clsfth">
                     å½åç¶æ
              </td>
              <td width="380">
                    <bean:write name="tawTestcardForm" property="state" /> &nbsp
              </td>
       </tr>
       <tr>
            <td noWrap width="100"  class= "clsfth">
                      æä¾å
                </td>
                <td width="380" colspan="3">
                       <bean:write  name="tawTestcardForm" property="offer" /> &nbsp
                </td>
       </tr>

       <tr>
            <td noWrap width="100"  class= "clsfth">
                      å¼éä¸å¡
                </td>
                <td width="380" colspan="3">
                       <bean:write  name="tawTestcardForm" property="operation" /> &nbsp
                </td>
       </tr>
</logic:present>

                                           <td colspan=4>
				                 <table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
					              <tr align="right" valign="middle">
						        <td>

      <html:submit styleClass="clsbtn2">
        å é¤
      </html:submit>
      <INPUT type="button" class="clsbtn2"  value="è¿å" onclick="window.history.back(-1)"/>

						        </td>
					               </tr>


          </td>
	</tr>
</table>
</center>
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

</html:html>
