<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@page
	import="com.boco.eoms.commons.statistic.base.config.excel.*,java.util.*"%>
<%
	Excel excelConfig = (Excel) request.getAttribute("excelConfig");
	if (excelConfig == null)
		throw new Exception("读取配置统计文件失败!");

	String excelConfigURL = String.valueOf(request
			.getAttribute("excelConfigURL"));
	String findListForward = String.valueOf(request
			.getAttribute("findListForward"));
	Calendar cld = Calendar.getInstance();
	int year = cld.get(Calendar.YEAR);
	int mondth = cld.get(Calendar.MONTH) + 1;
%>

<script language="JavaScript" type="text/JavaScript"
	src="${app}/scripts/module/partner/ajax.js"></script>

<html:form action="/apparatusStat.do?method=performStatistic"
	styleId="theform">

	<table class="formTable middle" align="left">
		<caption>
			输入条件
		</caption>
		<tr>
			<td colspan="2">
				<input type="hidden" name="excelConfigURL"
					value="<%=excelConfigURL%>">
				<input type="hidden" name="findListForward"
					value="<%=findListForward%>">
			</td>
		</tr>

		<tr>
			<td noWrap class="label">
				地市名称
			</td>
			<td>
				<select name="area_id" id="area_id" size="1" onchange="changeDep();">
					<%
						List listId = (List) request.getAttribute("listId");
						List listName = (List) request.getAttribute("listName");

						String nodeId = (String) request.getAttribute("user_deptId");
						if (nodeId == null) {
							nodeId = " ";
						}
						for (int i = 0; i < listId.size(); i++) {
							if (nodeId.equals(listId.get(i))) {
					%>
					<option value="<%=listId.get(i)%>" selected>
						<%=listName.get(i)%>
					</option>
					<%
					} else {
					%>
					<option value="<%=listId.get(i)%>">
						<%=listName.get(i)%>
					</option>
					<%
						}
						}
					%>
				</select>
				<input type="hidden" name="areaName" id="areaName" value="">
			</td>
		</tr>
		<tr>
			<td noWrap class="label">
				合作伙伴名称
			</td>
			<td>
				<select name="dept_id" id="dept_id" size="1">
				</select>
				<input type="hidden" id="deptName" name="deptName" value="">
			</td>
		</tr>
			<input type="hidden" name="reportIndex" value="0">
			
		<tr>
			<td align="left" colspan="2">
				<html:submit styleClass="btn" property="method.save"
					styleId="method.save" >
					<bean:message bundle="statistic" key="button.done" />
				</html:submit>
			</td>
		</tr>
	</table>
	<br />
</html:form>
<script type="text/javascript">

var id = document.getElementById("area_id").value;
			 var url="<%=request.getContextPath()%>/statistic/notflow/partner/baseinfo/apparatusStat.do?method=changeDep2&id="+id;
			 var fieldName = "dept_id";
			 var deptId ="";
			 changeList(url,fieldName,deptId);
function changeDep()
		{    
			 var id = document.getElementById("area_id").value;
			 var url="<%=request.getContextPath()%>/statistic/notflow/partner/baseinfo/apparatusStat.do?method=changeDep2&id="+id;
			 var fieldName = "dept_id";
			 changeList(url,fieldName,"");
		}
  </script>

<%@ include file="/common/footer_eoms.jsp"%>
