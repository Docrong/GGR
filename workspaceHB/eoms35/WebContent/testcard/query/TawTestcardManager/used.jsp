<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="com.boco.eoms.common.tree.WKTree"%>
<%@ page
	import="java.util.*,java.text.SimpleDateFormat,java.lang.*,org.apache.struts.util.*,java.io.*,java.sql.*,javax.sql.*"%>
 
 
<html:form method="post" action="/TawTestcardManager/usedo"
	onsubmit="true" styleId="tawOnlineManageForm">
	<script language="javascript">
 
Ext.onReady(function(){
	v = new eoms.form.Validation({form:'tawOnlineManageForm'});
		});
function isnumeric(p)
{
 if (p == "")
  return false;
 var l = p.length;
 var count=0;
 for(var i=0; i<l; i++)
 {
  var digit = p.charAt(i);
  if(digit == "." )
 {
    ++count;
    if(count>1) {
	return false; }
   }
  else if(digit < "0" || digit > "9")
  {
  return false;
  }
 }
 return true;
}
</script>
	<script language="JavaScript">
function bb(main){
if (document.tawTranfaultreportForm.city.options.length!=0) {
for (i=0;i<=document.tawTranfaultreportForm.city.options.length;i++){
if(document.tawTranfaultreportForm.city.options[i].value!=main)
{document.tawTranfaultreportForm.city.options[i].selected="true";
document.tawTranfaultreportForm.city.disabled="false";
}
}
}
}
</script>
	<html:hidden property="strutsAction" value="4" />


	<table   class="formTable">
		<caption>
			<b>${eoms:a2u("测试记录查询")}</b>
			<caption>
		<tr>
			<td noWrap class="label">
				&nbsp;&nbsp;${eoms:a2u("测试人")}
			</td>
			<td width="200">
				<input name="conner" />
			</td>
		</tr>
		<tr>
			<td noWrap class="label">
				&nbsp;&nbsp;${eoms:a2u("测试分公司")}
			</td>
			<td width="200">
			<eoms:comboBox name="leave" id="a1" sub="a2" initDicId="10401"/>
				<%--<html:select property="leave" style="width: 4.0cm;" value="">
					<html:option value="">
					</html:option>
					<html:optionsCollection name="tawTestcardTestingForm"
						property="beanCollectionDN" />
				</html:select>
			--%></td>
		</tr>
		<tr>
			<td noWrap class="label">
				&nbsp;&nbsp;${eoms:a2u("卡号")}(iccid)
			</td>
			<td width="200">
				<html:text styleClass="clstext" property="iccid" size="20" />
			</td>
		</tr>
		<tr>
			<td noWrap class="label">
				&nbsp;&nbsp;msisdn
			</td>
			<td width="200">
				<html:text styleClass="clstext" property="msisdn" size="20" />
			</td>
		</tr>
		<tr>
			<td noWrap class="label">
				&nbsp;&nbsp;${eoms:a2u("测试时间从")}
			</td>
			<td width="200">
				<input name="fromtime" id="fromtime" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="readonly"
					style="clstext" alt="vtype:'lessThen',link:'totime',vtext:'${eoms:a2u("不得晚于结束时间")}'" />
			</td>
		</tr>
		<tr>
			<td noWrap class="label">
				&nbsp;&nbsp;${eoms:a2u("到")}
			</td>
			<td width="200">
				<input name="totime"  id="totime" style="clstext" onclick="popUpCalendar(this, this,null,null,null,true,-1);"
					readonly="readonly" alt="vtype:'moreThen',link:'fromtime',vtext:'${eoms:a2u("不得早于开始时间")}'" />
			</td>
		</tr>
		<tr>
			<td colspan=2>
			 
							<INPUT type="submit" class="button" value="${eoms:a2u('查询')}" name="submit">
							&nbsp;&nbsp;
							<html:reset styleClass="button">${eoms:a2u('重置')}</html:reset>
						 
			</td>
		</tr>
	</table>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
