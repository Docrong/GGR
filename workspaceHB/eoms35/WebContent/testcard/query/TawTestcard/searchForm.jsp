<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%
	String id = StaticMethod.nullObject2String(request.getAttribute("formId"));
	String leaveid = StaticMethod.nullObject2String(request.getAttribute("leaveid"));
%>    
    <html:form  method="post" action="/TawTestcardApply/searchdo"  styleId="tawTestcardApplyForm">
      <table  class="formTable">
       <tr>
					<td noWrap width="80" class="label">
						卡类型
					</td>
					<td colspan=3 width="80%" align="left">
						<input type="radio" name="cardtype" value="2"/>
						省际来访卡&nbsp;
						<input type="radio" name="cardtype" value="3"/>
						省际出访卡&nbsp;
						<input type="radio" name="cardtype" value="5"/>
						省内来访卡&nbsp;
						<input type="radio" name="cardtype" value="6"/>
						省内出访卡&nbsp;
						<input type="radio" name="cardtype" value="4"/>
						本地测试卡&nbsp;
						<input type="radio" name="cardtype" value="1"/>
						国际来访卡&nbsp;
						<input type="radio" name="cardtype" value="0"/>
						国际出访卡&nbsp;
					</td>
				</tr>
       <tr>
					<td class="label">
						套餐类型
					</td>
					<td>
						<html:select property="cardpackage" style="width: 4.0cm;" value=""
							onchange="showbutton(this.value);" title="套餐类型">
							<html:optionsCollection name="tawTestcardApplyForm"
								property="beCollep" />
						</html:select>
					</td>
    </tr>
			<tr class="tr_show">
				<td class="clsfth">
					申请单状态
				</td>
				<td colspan=3>
					<html:select property="status" style="width: 4cm;">
						<option value="9">
							草稿
						</option>
						<option value="10">
							待审核
						</option>
						<option value="11">
							驳回
						</option>
						<option value="12">
							审核通过
						</option>
					</html:select>
			</tr>
     <tr class="tr_show">
                  <td class="clsfth">
                    开始时间
                  </td>
                   <td width="70%" height="25" colspan="2">
                     <html:text styleClass="clstext" property="beginTime" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="readonly" size="21" value="<%=StaticMethod.getLocalString()%>"/>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth">
                    结束时间
                  </td>
                   <td width="70%" height="25" colspan="2">
                     <html:text styleClass="clstext" property="endTime" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="readonly" size="21" value="<%=StaticMethod.getLocalString()%>"/>
                  </td>
    </tr>
       <tr>
              <td noWrap width="100"  class= "label">
                   申请单名称
              </td>
              <td width="380">
                     <html:text styleClass="clstext" property="phoneNumber" size="20" value=""/>
              </td>
    </tr>
	<tr>
			<td class="label">
					存放公司
			</td>
			<td colspan="3">
				<eoms:comboBox name="leaveid" id="a1" sub="a2" initDicId="10401"/>
			</td>
	</tr>
	<tr>
					<td noWrap width="80" class="label">
						申请原因
					</td>
					<td width="380" colspan="3">
						<html:textarea property="applyreason" rows="4" cols="88" title="申请原因" />
					</td>
				</tr>
	<html:hidden property="id" value="<%=id%>"/>
    <tr>
         <td colspan="2" >
           <html:submit styleClass="button">
		   查询
		   </html:submit>
      		<html:reset styleClass="button">
       		重置
      		</html:reset>
      </td>
	</tr>
			</table>
</html:form>
 <script type="text/javascript">
window.onload = function(){
	document.forms[0].leaveid.value = <%=leaveid%>;
}
</script>
      
<%@ include file="/common/footer_eoms.jsp"%>
