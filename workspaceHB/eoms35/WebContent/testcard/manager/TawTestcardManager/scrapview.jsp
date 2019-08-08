<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ include file="/common/xtreelibs.jsp" %>
<center>
    <table cellSpacing="0" cellPadding="0" width="85%" border="0">
        <tr>
            <td align="right" width="100%" bgColor="#f5f5f5">
                <div align="center">
                    <table cellSpacing="0" borderColorDark="#ffffff" cellPadding="2" width="40%"
                           borderColorLight="#808080" border="1">
                        <tr>
                            <td colspan="4" valign="middle" bgcolor="#E5EDF8" align="center">
                                �� �� �� �� �� �� ϸ �� Ϣ
                            </td>
                        </tr>
                        <logic:present name="tawTestcardManagerForm" scope="request">

                        <td noWrap width="100" class="clsfth">����(iccid)</td>
                        &nbsp
                        <td width="380">
                            <bean:write name="tawTestcardManagerForm" property="cardid"/>
                        </td>
                        </tr>
                        <tr>
                            <td noWrap width="100" class="clsfth">MSISDN</td>
                            <td width="380">
                                <bean:write name="tawTestcardManagerForm" property="msisdn"/>&nbsp
                            </td>

                        </tr>
                        <tr>
                            <td noWrap width="100" class="clsfth">������</td>
                            <td width="380">
                                <bean:write name="tawTestcardManagerForm" property="dealer"/>&nbsp
                            </td>
                        </tr>
                        <tr>
                            <td noWrap width="100" class="clsfth">���ϲ���</td>
                            <td width="380">
                                <bean:write name="tawTestcardManagerForm" property="leave"/>&nbsp
                            </td>

                        </tr>
                        <tr>
                            <td noWrap width="100" class="clsfth">��ϵ��ʽ</td>
                            <td colspan=3 width="380">
                                <bean:write name="tawTestcardManagerForm" property="contect"/>&nbsp
                            </td>
                        </tr>
                        <tr>
                            <td noWrap width="100" class="clsfth">
                                ����ʱ��
                            </td>
                            <td width="380">
                                <bean:write name="tawTestcardManagerForm" property="leantime"/>&nbsp
                            </td>
                        </tr>
                        <tr>
                            <td noWrap width="100" class="clsfth">
                                ��ע
                            </td>
                            <td width="380" colspan="3">
                                <bean:write name="tawTestcardManagerForm" property="reason"/>&nbsp
                            </td>
                        </tr>
                        <!--       <tr>
            <td noWrap width="100"  class= "clsfth">
                      ������
                </td>
                <td width="380" colspan="3">
                      <bean:write name="tawTestcardManagerForm" property="accessory"/>&nbsp
                </td>
       </tr>
-->
                        </logic:present>

                        <td colspan=4>
                            <table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0"
                                   width="100%">
                                <tr align="right" valign="middle">
                                    <td>

                                        <html:submit styleClass="clsbtn2" onclick="window.history.back(-1)">
                                            ����
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
