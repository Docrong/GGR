<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<style type="text/css">
  	body{background-image:none;}
</style>

<script language="javascript">

function confirmSave(){
if ( confirm('${eoms:a2u("是否要保存")}') ){
	return true;
	}else{
	return false;
	}
}
</script>
<%
   List newUndoList=(List)session.getAttribute("newUndoList");
   List newFinishList=(List)session.getAttribute("newFinishList");
%>
<content tag="heading"><fmt:message key="tawRmWorkorderRecordDetail.heading"/></content>

<html:form action="tawRmWorkorderRecord" method="post" styleId="tawRmWorkorderRecordForm"> 
<ul>
	<table class="listTable" id="list-table">
		<tr height="30" class="header">
			<td nowrap="nowrap" width="20%">
				<fmt:message key="tawRmWorkorderRecordForm.orderTitle" />
			</td>
			<td nowrap="nowrap" width="20%">
				<fmt:message key="tawRmWorkorderRecordForm.workOrderId" />
			</td>
			<td nowrap="nowrap" width="20%">
				<fmt:message key="tawRmWorkorderRecordForm.replyTime" />
			</td>
			<td nowrap="nowrap" width="20%">
				<fmt:message key="tawRmWorkorderRecordForm.orderState" />
			</td>
		</tr>
		<%
		    for(int i=0;i<newUndoList.size();i++){
		    	ListOrderedMap item = (ListOrderedMap)newUndoList.get(i);
		    	String sheetId = StaticMethod.nullObject2String(item.getValue(0));
		    	String title=StaticMethod.nullObject2String(item.getValue(1));
		    	String replyTime=StaticMethod.nullObject2String(item.getValue(2));
		%>
			<tr height="30">
				<td nowrap="nowrap" width="20%">
					<a
						href="${app}/duty/tawRmWorkorderRecord.do?method=view&sheetId=<%=sheetId%>">
						<%=title %> </a>
				</td>
				<td nowrap="nowrap" width="20%">
					<%=sheetId %>
				</td>
				<td nowrap="nowrap" width="20%">
					<%=replyTime %>
				</td>
				<td nowrap="nowrap" width="20%">
					<eoms:dict key="dict-plancontent" dictId="orderState" itemId="10" beanId="id2nameXML" />	
				</td>
			</tr>
		<%
			} 
		%>
		<%
		    for(int i=0;i<newFinishList.size();i++){
		    	ListOrderedMap item = (ListOrderedMap)newFinishList.get(i);
		    	String sheetId = StaticMethod.nullObject2String(item.getValue(0));
		    	String title=StaticMethod.nullObject2String(item.getValue(1));
		    	String replyTime=StaticMethod.nullObject2String(item.getValue(2));
		%>
			<tr height="30">
				<td nowrap="nowrap" width="20%">
					<a
						href="${app}/duty/tawRmWorkorderRecord.do?method=view&sheetId=<%=sheetId%>">
						<%=title %> </a>
				</td>
				<td nowrap="nowrap" width="20%">
					<%=sheetId %>
				</td>
				<td nowrap="nowrap" width="20%">
					<%=replyTime %>
				</td>
				<td nowrap="nowrap" width="20%">
					<eoms:dict key="dict-plancontent" dictId="orderState" itemId="12" beanId="id2nameXML" />	
				</td>
			</tr>
		<%
			} 
		%>
	</table>
	
		<table border="0">
			<tr>
				<td>
				<html:submit styleClass="button" property="method.save" onclick="bCancel=false;return confirmSave()">
           			<fmt:message key="button.save"/>
       			</html:submit>
				</td>
			</tr>
		</table>
</ul>
</html:form>
<script src="${app}/scripts/util/iframe.js" type="text/javascript" />
<%@ include file="/common/footer_eoms.jsp"%>