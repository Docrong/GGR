<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%
    List list = (List) request.getAttribute("listYear");
    String year = StaticMethod.nullObject2String(request.getAttribute("Year"));
    String month = StaticMethod.nullObject2String(request.getAttribute("Month"));
    String roomId = StaticMethod.nullObject2String(request.getAttribute("roomId"));
%>

<html xmlns:BOCO="BOCO Inter-Telecom">
<html>
	<head>
		<title>new</title>
<script language=javascript>
function selectedoption(Id,value){
	document.getElementById(Id).value=value;
}
function init()
{
    selectedoption("reportYear","<%=year%>");
    selectedoption("reportMonth","<%=month%>");
}

</script>
	</head>

	<body onload="init();">
		<center>
			<table cellSpacing="0" cellPadding="0" width="85%" border="0">
				<tr>
					<td class="table_title" align="center">
						<b>${eoms:a2u('值班月报')}&nbsp;${eoms:a2u('选择条件')}</b>
					</TD>
				</tr>
			</table>

			<!---正文开始------------------------------------------------------------------------------------------>
			<html:form action="/TawRmRecord/monthReport.do">
 				<input type="hidden" name="roomId" value="<%=roomId %>" >
				<table border="0" width="500" cellspacing="1" cellpadding="1" class="formTable" align=center>

					<tr class="tr_show">
						<td class="label">
							${eoms:a2u('选择时间')}
						</td>
						<td>
							${eoms:a2u('年')}
							<select name="reportYear">
								<%for(int i=0;i<list.size();i++){
									String temp = StaticMethod.nullObject2String(list.get(i));
								%>
									<option value="<%=temp%>"><%=temp%></option>
								<%}%>
							</select>
							${eoms:a2u('月')}
							<select name="reportMonth">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</select>
						</td>

					</tr>



				</table>

				<table cellpadding="0" cellspacing="0" border="0" width="500" align="center">
					<tr>
						<td height="32" align="left">
							<html:submit styleClass="button">
								${eoms:a2u('生成月报')}
							</html:submit>
							&nbsp;
						</td>
					</tr>
				</table>
			</html:form>
			<!---正文结束------------------------------------------------------------------------------------------>
		</center>
	</body>
</html>
<%@ include file="/common/footer_eoms.jsp"%>
