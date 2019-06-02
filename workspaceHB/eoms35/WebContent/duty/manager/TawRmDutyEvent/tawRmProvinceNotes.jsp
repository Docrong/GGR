<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page
	import="java.util.*,com.boco.eoms.commons.system.dept.model.TawSystemDept,com.boco.eoms.duty.model.TawRmDutyEvent"%>
<style type="text/css">
<!--

.STYLE2 {font-size: 18px; color: #CE2900; float: inherit; visibility: visible; }
.STYLE1 {font-size: 18px; color: #FBCA04; float: inherit; visibility: visible; }
.STYLE0 {font-size: 18px; color: #100150; float: inherit; visibility: visible; }
.EVENT5 {color: #CE2900; font-size: 14px; }
.EVENT4 {color: #CE2900; font-size: 14px; }
.EVENT3 {color: #CE2900; font-size: 14px; }
.EVENT2 {color: #FBCA04; font-size: 14px; }
.EVENT1 {color: #000000; font-size: 14px; }


-->
.formTable{
	width: 90%;
	border-collapse: collapse;
	font-size: 12px;
	table-layout:fixed;
}

.formTable td{
	border: 1px solid #C9DEFA;
	padding: 6px 6px;
	background-color:#FFFFFF;
}

.formTable tr.header td{
	background: #cae8ea;
	color:#006699;	
} 
td.label{
	vertical-align:top;
	background-color:#EDF5FD;
	width:15%;
}
td.content{
	vertical-align:top;
	width:35%;
}
td.max{
	width:90%;
}
td.checkColumn{
	width:10px;
}

</style>
<title>全省记录</title>
<br><br>
<html:form action="/dutyevent.do" method="post"
	styleId="tawRmdutyEventForm">
	<logic:present name="noteList">
		<table border=0 cellspacing="1" cellpadding="1" >
			<tr>
				<td colspan="3">
					<h2 align="center">
						全省记录
					</h2>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<table border=0 cellspacing="0" cellpadding="0" >
						<%
							List listlist = (List) request.getAttribute("listlist");
							List noteList = (List) request.getAttribute("noteList");
							int i = 0;
							for (int j = 0; j < noteList.size(); j++) {
								TawSystemDept tawSystemDept = (TawSystemDept) noteList.get(j);
								String str = "STYLE" + tawSystemDept.getRemark();
								String deptId = tawSystemDept.getDeptId();
								List eventlist = (List) listlist.get(j);
								int count = (i % 3);
								if (count == 0) {
						%>
						<tr>
						<td>
						&nbsp;
						</td>
						</tr>
						<tr>
							<%
							}
							%>
							<td>
								<table border=0 cellspacing="1" cellpadding="1" width="200"
									align="center" class="formTable">
									<tr>
										<td  align="center" class="label">
											<font class="<%=str%>">
											 <% out.print(tawSystemDept.getDeptName()); %>
											</font>
										</td>
									</tr>
<tr><td>
									<%
											int num = eventlist.size();
											
											for (int k = 0; k < 3; k++) {
											String flag="";
											String id ="";
											if (k < num) {
											 flag ="EVENT" +((TawRmDutyEvent) (eventlist.get(k))).getFlag();
											 id =((TawRmDutyEvent) (eventlist.get(k))).getId();
											}
									%>
									
										<p align="left">
										<a href="${app}/duty/dutyevent.do?method=getEvent&id=<%=id%>" >
										<font class="<%=flag %>">
											<%
													if (k < num) {
													out.println("<image src=../images/list-items.gif>&nbsp;&nbsp;");
													out.print(((TawRmDutyEvent) (eventlist.get(k)))
															.getEventtitle());
														} 
											%>
											</font></a>
											<%         if(k>=num) {
													out.print("&nbsp;");
														} %>
										</p><%
									}
									%>
									</td>
</tr>
									<tr>
										<td align="right">
											<a
												href="${app}/duty/dutyevent.do?method=noteDetail&deptId=<%=deptId%>">
												更多信息...</a>
										</td>
									</tr>
								</table>
							</td>
							<%
									i++;
									//if (count == 0) {
							%>

							<%
							}
							%>
						
					</table>
				</td>
			</tr>
		</table>
	</logic:present>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
