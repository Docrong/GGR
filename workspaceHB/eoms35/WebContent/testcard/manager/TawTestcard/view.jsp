
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<center>
<table cellSpacing="0" cellPadding="0" width="85%" border="0" class="formTable">
       <tr>
                           <td align="right" width="100%" bgColor="#f5f5f5">
                              <div align="center">
                              <table cellSpacing="0"  cellPadding="2" width="100%" class="formTable" border="1">
                                    <tr> <td  colspan="4" valign="middle" bgcolor="#E5EDF8" align="center">
							${eoms:a2u("测 试 卡 详 细 信 息")}
                                              </td></tr>
<logic:present name="tawTestcardForm" scope="request">
        <tr>
                <td noWrap width="100" class= "label">${eoms:a2u("卡类型")}</td>
                <td colspan = 3 width="380" align="center">
                      <bean:write name="tawTestcardForm" property="cardType"/>
        	</td>
        </tr>
       <tr>
                <td noWrap width="100" class= "label">${eoms:a2u("归属地")}</td>
                <td colspan = 3 width="380" align="center">
                  ${eoms:a2u("国家:")}<bean:write name="tawTestcardForm" property="fromCountry" /> &nbsp;&nbsp
                  ${eoms:a2u("省份:")}<bean:write name="tawTestcardForm" property="fromCrit" /> &nbsp;&nbsp
                  ${eoms:a2u("地市:")}<bean:write name="tawTestcardForm" property="fromCity"/> &nbsp;&nbsp
                  ${eoms:a2u("运营商:")}<bean:write name="tawTestcardForm" property="fromOpe"/> &nbsp
        	</td>
        </tr>
       <tr>
                <td noWrap width="100" class= "label">${eoms:a2u("拜访地")}</td>
                <td colspan = 3 width="380" align="center">
                  ${eoms:a2u("国家:")}<bean:write name="tawTestcardForm" property="toCountry" /> &nbsp;&nbsp
                  ${eoms:a2u("省份:")}<bean:write name="tawTestcardForm" property="toCrit" /> &nbsp;&nbsp
                  ${eoms:a2u("地市:")}<bean:write name="tawTestcardForm" property="toCity"/> &nbsp;&nbsp
                  ${eoms:a2u("运营商:")}<bean:write name="tawTestcardForm" property="toOpe"/> &nbsp
        	</td>
        </tr><%--
        <tr>
          <td class="clsfth">
            册号
          </td>
          <td>
            <bean:write name="tawTestcardForm" property="volumenum"/>&nbsp
          </td>
          <td class="clsfth">
            页号
          </td>
          <td>
            <bean:write name="tawTestcardForm" property="pagenum"/>&nbsp
          </td>
        </tr>
        --%><tr>
            <td noWrap width="100"  class= "label">${eoms:a2u("存放分公司")}</td>
                <td width="380">
                    <bean:write name="tawTestcardForm" property="leave"/> &nbsp
                </td>
          <td class="clsfth">
            ${eoms:a2u("测试卡套餐类型")}
          </td>
          <td width="380">
                  <bean:write name="tawTestcardForm" property="cardpackage" />&nbsp
          </td>
       </tr>
        <tr>
          <td noWrap width="100"  class= "label">
                      ${eoms:a2u("卡号(iccid)")}
          </td>
          <td width="380">
                       <bean:write name="tawTestcardForm"  property="iccid" /> &nbsp
          </td>
          <td noWrap width="100"  class= "label">
                ${eoms:a2u("单卡编号")}
               </td>
               <td width="380">
                    <bean:write name="tawTestcardForm" property="oldNo"/> &nbsp
               </td>
          <%--<td noWrap class= "label">
                      电话号码
                </td>
                <td>
                       <bean:write name="tawTestcardForm" property="telnum" /> &nbsp
                </td>
          
        --%></tr>
        <tr>
               <td noWrap width="100"  class= "label">
                      msisdn
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm"  property="msisdn" /> &nbsp
                </td>
                <td nowrap="nowrap" class= "label">
            ${eoms:a2u("IMSI")}
          </td>
          <td>
        		<bean:write  name="tawTestcardForm" property="imsi" />&nbsp
          </td>
       </tr>
       <tr>
               <td noWrap width="100"  class= "label">
                     ${eoms:a2u("个人识别码")}
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="pin1" /> &nbsp
                </td>
                <td noWrap width="100"  class= "label">
                      ${eoms:a2u("解锁码")}
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="puk1" /> &nbsp
                </td>
       </tr>
       <tr>
                <td noWrap width="100"  class= "label">
                      ${eoms:a2u("开机密码")}
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="password" /> &nbsp
                </td>  
                <td noWrap width="100"  class= "label">
             
               </td>
               <td width="380" colspan="3">
                  
               </td>
                                
       </tr>
  <!--     <tr>
               <td noWrap width="100"  class= "label">
                      个人识别码1
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="pin1" /> &nbsp
                </td>
                <td noWrap width="100"  class= "label">
                      个人识别码2
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="pin2" /> &nbsp
                </td>
       </tr>
        <tr>
               <td noWrap width="100"  class= "label">
                      解锁码1
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="puk1" /> &nbsp
                </td>
                <td noWrap width="100"  class= "label">
                      解锁码2
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="puk2" /> &nbsp
                </td>
       </tr> 
       <tr>
                <td noWrap width="100"  class= "label">
                      开机密码
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="password" /> &nbsp
                </td>  
                <td noWrap width="100"  class= "label">
                单卡编号
               </td>
               <td width="380" colspan="3">
                    <bean:write name="tawTestcardForm" property="oldNo"/> &nbsp
               </td>
                                
       </tr> -->
       <tr>
              <td noWrap width="100"  class= "label">
                    ${eoms:a2u("状态")}
              </td>
              <td width="380">
                    <bean:write name="tawTestcardForm" property="state" /> &nbsp
              </td>
                         <td class="clsfth">
             ${eoms:a2u("费用情况")}
           </td>
           <td>
             <bean:write name="tawTestcardForm" property="exes"/>&nbsp
           </td>
       </tr>
       <tr>
            <td noWrap width="100"  class= "label">${eoms:a2u("归属HLR厂商")} </td>
                <td width="380">
                        <bean:write name="tawTestcardForm" property="offer" />&nbsp
                </td>
            <td noWrap width="100"  class= "label">
            ${eoms:a2u("归属HLR GT")}
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="msgcenterno" />&nbsp
                </td>
       </tr><%--
       <tr>
               <td noWrap width="100"  class= "label">
                     开始时间
              </td>
              <td width="380">
                     <bean:write name="tawTestcardForm" property="begintime"/> &nbsp
              </td>
              <td noWrap width="100"  class= "label">
                     注销时间
              </td>
              <td width="380">
                    <bean:write name="tawTestcardForm" property="endtime"/> &nbsp
              </td>
       </tr>
        <tr>

           <td  noWrap width="100"  class= "label">
             最后测试时间
                </td>
                <td width="380" >
                       <bean:write name="tawTestcardForm" property="lasttesttime" />&nbsp
                </td>
       </tr>
               <tr>
            <td noWrap width="100"  class= "label">
             测试结果
                </td>
                <td width="380">
                       <bean:write name="tawTestcardForm" property="testresult" />&nbsp
                </td>
            <td  noWrap width="100"  class= "label">
             处理结果
                </td>
                <td width="380" >
                      <bean:write name="tawTestcardForm" property="dealresult" />&nbsp
                </td>
       </tr>
       --%><tr>
           <td  noWrap width="100"  class= "label">
                      ${eoms:a2u("入库人")}
                </td>
                <td width="380" >
                         <bean:write name="tawTestcardForm" property="adder" />&nbsp
                </td>
           <td noWrap width="100"  class= "label">
                    ${eoms:a2u("入库时间")}
              </td>
              <td width="380">
                     <bean:write name="tawTestcardForm" property="intime" /> &nbsp
              </td>
        </tr>
       <tr>
            <td  noWrap width="100"  class= "label">
             ${eoms:a2u("存放位置")}
                </td>
                <td width="380" colspan="3" >
                      <bean:write name="tawTestcardForm" property="position" />&nbsp
                </td>
       </tr>
       <tr>
            <td noWrap width="100"  class= "label">
            ${eoms:a2u("备注")}
                </td>
                <td width="380" colspan="3">
                       <bean:write  name="tawTestcardForm" property="operation" />&nbsp
                </td>      </tr>
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

