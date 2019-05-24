<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
 
<form  method="post" action="../TawTestcardManager/scraplist.do" onsubmit="true">
<input type="hidden" name="cardTypeNum" value="0" />
<input type="hidden" name="operation" value="1" />
<center>
<br>
<table class="formTable">
      <caption>
                                <b>${eoms:a2u("测试卡报废")}</b>
      </caption>
	<tr>
  	<td noWrap width="100" class= "label">${eoms:a2u("卡类型")}</td>
  			 <td width="100" align="left">
                 		 <html:select property="cardType" style="width: 3.0cm;" value="7">
                              <html:option value="1">${eoms:a2u("国际来访卡")}</html:option>
                              <html:option value="2">${eoms:a2u("省际来访卡")}</html:option>
                              <html:option value="5">${eoms:a2u("省内来访卡")}</html:option>
                              <html:option value="4">${eoms:a2u("本地测试卡")}</html:option>
                              <html:option value="7">${eoms:a2u("所有测试卡")}</html:option>
                      </html:select>
        	</td>
	</tr>
  <tr>
          <td class="label">
            ${eoms:a2u("测试卡套餐类型")}
          </td>
          <td>
            <html:select property="cardpackage" style="width: 4.0cm;" value="">
              <html:optionsCollection name="tawTestcardForm" property="beCollep"/>
            </html:select>
          </td>
  </tr>
  <tr>
         <td noWrap width="100"  class= "label">
                      ${eoms:a2u("存放公司")}
         </td>
                <td width="100">
                <eoms:comboBox name="leave" id="a1" sub="a2" initDicId="10401"/>
                <%--
                    <html:select property="leave" style="width: 4.0cm;" value="">
                          <html:optionsCollection name="tawTestcardForm" property="beanCollectionDN"/>
                    </html:select>
                --%></td>
  </tr>
  <tr>
         <td noWrap width="100"  class= "label">
                     ${eoms:a2u("当前状态")}
              </td>
              <td width="380">
                    <html:select property="state" style="width: 4.0cm;" value="">
                    <html:optionsCollection name="tawTestcardForm" property="beanCollection"/>
                    </html:select>
              </td>
   </tr>
   <tr>
              <td noWrap width="100"  class= "label">
                    ${eoms:a2u("卡号")}(iccid)
              </td>
              <td width="380">
                     <input type="text" name="iccid" size="20" value="" class="clstext">
              </td>
    </tr>
    <tr>
              <td noWrap width="100"  class= "label">
                   IMSI
              </td>
              <td width="380">
                     <input type="text" name="imsi" size="20" value="" class="clstext">
              </td>
    </tr>
    <tr>
              <td noWrap width="100"  class= "label">
                   msisdn
              </td>
              <td width="380">
                     <input type="text" name="msisdn" size="20" value="" class="clstext">
              </td>
    </tr>
    <tr   valign="middle">
      <td colspan="2">
        <INPUT type="submit" class="button"  value="${eoms:a2u('查询')}"  name="submit">&nbsp;&nbsp;
        <html:reset styleClass="button">${eoms:a2u("重置")}</html:reset>
      </td>
    </tr>
</table>
</center>
</form>
<%@ include file="/common/footer_eoms.jsp"%>
