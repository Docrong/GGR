<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="com.boco.eoms.common.tree.WKTree"%>
<%@ page
	import="java.util.*,java.text.SimpleDateFormat,java.lang.*,org.apache.struts.util.*,java.io.*,java.sql.*,javax.sql.*"%>
 <script language="javascript"
	src="<%=request.getContextPath()%>/css/table_calendar.js"></script>

<html:form method="post" action="/TawTestcardManager/returnselectlist"
	onsubmit="true">
	<script language="javascript">
<!--
//以下四行用于日历显示，放在form后

//-->

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
<br>
	<table   class="formTable" align="center">
		<caption>
                 <b>${eoms:a2u("测试卡归还")}</b>
             </td>
        </caption>
		<tr>
			<td noWrap class="label">
				&nbsp;&nbsp;${eoms:a2u("经手人")}
			</td>
			<td width="380">
				<html:text property="dealer" />
			</td>
			<td noWrap class="label">
				&nbsp;&nbsp;${eoms:a2u("借出分公司")}
			</td>
			<td width="380">
			<eoms:comboBox name="leave" id="a1" sub="a2" initDicId="10401"/>
			<%--
				<html:select property="leave" style="width: 4.0cm;" value="">
					<html:optionsCollection name="tawTestcardManagerForm"
						property="beanCollectionDN" />
				</html:select>
			--%></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="label">
				&nbsp;&nbsp;${eoms:a2u("借用部门")}
			</td>
			<td width="380">
				<html:text styleClass="clstext" property="lenddept" size="20" />
			</td>
			<td noWrap class="label">
				&nbsp;&nbsp;${eoms:a2u("借用人")}
			</td>
			<td width="380">
				<html:text styleClass="clstext" property="lender" size="20" />
			</td>
		</tr>
		<tr>
			<td noWrap class="label">
				&nbsp;&nbsp;${eoms:a2u("借出时间从")}
			</td>
			<td width="380">
			<input id="startDate" name="leantime" type="text" class="text" readonly="readonly" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);"/>
			
			</td>
			<td noWrap class="label">
				&nbsp;&nbsp;${eoms:a2u("借出时间到")}
			</td>
			<td width="380">
			<input id="startDate" name="belongtime" type="text" class="text" readonly="readonly" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);"/>
				
			</td>
		</tr>
		<tr>
			<td noWrap class="label">
				&nbsp;&nbsp;${eoms:a2u("卡号")}(iccid)
			</td>
			<td width="380">
				<html:text styleClass="clstext" property="cardid" size="20" />
			</td>
			<td noWrap class="label">
				&nbsp;&nbsp;msisdn
			</td>
			<td width="380" colspan="3">
				<html:text styleClass="clstext" property="msisdn" size="20" />
			</td>
		</tr>
		<tr>
			<td colspan=4>
				 
							<INPUT type="submit" class="button" value="${eoms:a2u('查询')}" name="submit">
							&nbsp;&nbsp;
							<html:reset styleClass="button">${eoms:a2u("重置")}</html:reset>
						 
			</td>
		</tr>
	</table>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>


