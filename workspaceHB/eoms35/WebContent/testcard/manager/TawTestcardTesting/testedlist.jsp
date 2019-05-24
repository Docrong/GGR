<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
  <table border="0" width="95%" cellspacing="1" class="formTable">
  <tr>
<td width="100%" colspan="14" bgcolor="#E5EDF8" align="center" height="25">
<b>
${eoms:a2u("测试卡测试记录列表")}
 </b>
</td>
</tr>
  <tr bgcolor="#FFFFFF">
    <td width="100%" colspan="14" height="25" class="label" align="center">
      <bean:write name="pagerHeader" scope="request" filter="false"/>
    <%! String key;%>
    </td>
    </tr>
  <tr bgcolor="#FFFFFF">
       <td nowrap class="label" align="center" height="10">
          ${eoms:a2u("卡号(iccid)")}
       </td>
       <td nowrap="nowrap" class="label" align="center">
         ${eoms:a2u("MSISDN")}
       </td>
       <td nowrap="nowrap" class="label" align="center">
         ${eoms:a2u("测试人")}
       </td>
       <td nowrap="nowrap" class="label" align="center">
         ${eoms:a2u("测试部门")}
       </td>
       <td nowrap="nowrap" class="label" align="center">
         ${eoms:a2u("测试时间")}
       </td>
       <td nowrap="nowrap" class="label" align="center">
         ${eoms:a2u("测试结果")}
       </td>
       <td nowrap="nowrap" class="label" align="center">
         ${eoms:a2u("附件列表")}
       </td>
  </tr>
  <logic:iterate id="tawTestcardTesting" name="tawTestcardTesting" type="com.boco.eoms.testcard.model.TawTestcardTesting">
    <tr>
      <td nowrap="nowrap" align="center" bgcolor="#E5EDF8"><bean:write name="tawTestcardTesting" property="iccid" scope="page"/>      </td>
      <td nowrap="nowrap" align="center" bgcolor="#E5EDF8"><bean:write name="tawTestcardTesting" property="msisdn" scope="page"/>      </td>
      <td nowrap="nowrap" align="center" bgcolor="#E5EDF8"><bean:write name="tawTestcardTesting" property="conner" scope="page"/>      </td>
      <td nowrap="nowrap" align="center" bgcolor="#E5EDF8"><bean:write name="tawTestcardTesting" property="leave" scope="page"/>      </td>
      <td nowrap="nowrap" align="center" bgcolor="#E5EDF8"><bean:write name="tawTestcardTesting" property="testtime" scope="page"/>      </td>
      <td nowrap="nowrap" align="center" bgcolor="#E5EDF8"><bean:write name="tawTestcardTesting" property="outcome" scope="page"/>      </td>
      <td >
      <eoms:attachment name="tawTestcardTesting" property="accessories" 
           scope="request" idField="accessories" appCode="duty" 
           viewFlag="Y"/> 
        </td>
    </tr>
  </logic:iterate>
  </table>
