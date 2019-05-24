
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>


<script type="text/javascript">	
var v;
Ext.onReady(function(){
	v = new eoms.form.Validation({form:"StSubscriptionForm"});
})
</script>

<html:form action="stSubscriptions.do?method=xquery" method="post"
	styleId="StSubscriptionForm">
	
	<table class="formTable" align="center" >
		<tr>
			<td colspan="4" align="center">
       ${eoms:a2u("定制查询页面")}
			</td>
		</tr>
		
 
		<tr>
			<td class="label" align="right">
				<eoms:label styleClass="desc" key="stSubscriptionForm.startTime" />
				<html:errors property="startTime" />
			</td>
			<td>
					<input type="text" name="startTime" id="startTime"  readonly="readonly" onclick="popUpCalendar(this, this,null,null,null,true,-1);" />
			</td>
			<td class="label" align="right">
				<eoms:label styleClass="desc" key="stSubscriptionForm.endTime" />
				<html:errors property="endTime" />
			</td>
			<td>
				<input type="text" name="endTime" id="endTime"  readonly="readonly" onclick="popUpCalendar(this, this,null,null,null,true,-1);" />
			</td>
		</tr>
	
	  <tr>
			<td class="label" align="right">
				<eoms:label styleClass="desc" key="stSubscriptionForm.statMode" />
			</td>
			<td >
				 <select name="statMode" style="width: 3.0cm;">
				 		<option value="-1">${eoms:a2u("全部")}</option>
				 	<option value="3">${eoms:a2u("自定义")}</option>
         <option value="4">${eoms:a2u("日报")}</option>
        <option value="1" >${eoms:a2u("周报")}</option>
        <option value="2" >${eoms:a2u("月报")}</option>
        <option value="6">${eoms:a2u("季报")}</option>
        <option value="5">${eoms:a2u("年报")}</option>
        </select>	
			</td>
			
				<td class="label" align="right">
          	    ${eoms:a2u('统计类别')}
          </td>
          <td >
				 <select name="item" style="width: 5.2cm;">
				 	        <option value="-1">${eoms:a2u("全部")}</option>
				 	        <option value="commonfault-onetimepass-KPI2_oracle">${eoms:a2u("故障工单_一次处理完成率统计")}</option>
                  <option value="commonfault_delay_KPI3_oracle" >${eoms:a2u("故障工单_延期解决率统计")}</option>
                  <option value="commonfault_intime_KPI1_oracle" >${eoms:a2u("故障工单_故障处理及时率统计")}</option>
        </select>	
			</td>
		</tr>	

		<tr>
			<td colspan="4" align="center">
				<html:submit styleClass="button" property="method.save"
					onclick="bCancel=false">
					<fmt:message key="button.query" />
				</html:submit>
				&nbsp;&nbsp;
				<html:reset styleClass="button" onclick="bCancel=true">
					<fmt:message key="button.reset" />
				</html:reset>
			</td>
		</tr>

		
	</table>
	
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
