<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>



<html:form action="bureaudataSegmenthlr.do?method=searchdo">
	<input type="hidden" class="text" name="deleted" value="0">

	<table class="formTable">
		<TR>
			<td width="100%" class="label" colspan="4">${eoms:a2u('查询HLR数据')}</td>
		</tr>
		<tr>
			<TD align="right"></TD>
		</TR>
		<tr class="tr_show">
			<td class="label" width="10%">${eoms:a2u('归属HLR')}</td>
			<td width="40%">
			<select name="hlrsignalid" style="width:100%;">
				<option value=''>${eoms:a2u('---请选择---')}</option>
				<logic:present name="BureaudataHlr" scope="request">  
     				 <logic:iterate id="hlr" name="BureaudataHlr" type="com.boco.eoms.datum.model.TawBureaudataHlr">  
	              		 <option value="<%=hlr.getHlrsignalid()%>"><%=hlr.getHlrname()%>(<%=hlr.getHlrsignalid()%>)</option>
       				 </logic:iterate>
       			   </logic:present>
           </select></td>
			<td class="label" width="10%">${eoms:a2u('归属')}</td>
			<td align="left" width="40%"><select name="belong"
				style="width:100%;">
				<option value="1">${eoms:a2u('已归属')}</option>
				<option value="0">${eoms:a2u('未归属')}</option>

			</select></td>
		</tr>
		<tr class="tr_show">
			<td class="label">${eoms:a2u('起始号段(万号)')}</td>
			<td><input type="text" name="beginsegment" limit="^[0-9]*$"
				class="limitinput" maxlength="9" /></td>
			<td class="label">${eoms:a2u('终止号段(万号)')}</td>
			<td><input type="text" name="endsegment" limit="^[0-9]*$"
				class="limitinput" maxlength="9" /></td>
		</tr>
		<tr class="tr_show">
			<td colspan="1" width="15%" class="label">${eoms:a2u('地市区号')}</td>
			<td colspan="7" width="400">
			<eoms:comboBox name="cityId" id="cityId" initDicId="1010107"></eoms:comboBox>
			</td>
		</tr>



		<TR>
			<TD align="right" colspan="4"><html:submit styleClass="btn">
        ${eoms:a2u('查询')}
      </html:submit> &nbsp;</TD>
		</TR>
	</table>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
