
<jsp:directive.page
	import="com.boco.eoms.base.util.ApplicationContextHolder" />
<%
			com.mchange.v2.c3p0.ComboPooledDataSource cpds = (com.mchange.v2.c3p0.ComboPooledDataSource) ApplicationContextHolder
			.getInstance().getBean("dataSource");
%>

<table>
	<tr>
		<td>
			num_connections
		</td>
		<td>
			<%= cpds.getNumConnections()%>
		</td>
	</tr>
	<tr>
		<td>
			num_busy_connections
		</td>
		<td>
			<%= cpds.getNumBusyConnections()%>
		</td>
	</tr>
	
	<tr>
		<td>
			num_idle_connections
		</td>
		<td>
			<%= cpds.getNumIdleConnections()%>
		</td>
	</tr>
</table>
