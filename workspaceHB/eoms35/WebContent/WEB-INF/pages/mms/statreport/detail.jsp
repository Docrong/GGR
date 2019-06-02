<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.boco.eoms.commons.mms.base.util.MMSConstants"/>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%
	String path = "/" + MMSConstants.KEEP_REPORT_FILE_PATH;
 %>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'statreportForm'});
});

</script>


<fmt:bundle basename="config/applicationResources-mms">

<table class="formTable">

	<tr>
		<td class="label">
			报表创建人
		</td>
		<td class="content">
			${statreportForm.userid}
		</td>
	</tr>
	<tr>
		<td class="label">
			创建时间
		</td>
		<td class="content">
			${statreportForm.createTime}
		</td>
	</tr>
	<tr>
		<td class="label">
			报表名称
		</td>
		<td class="content">
			${statreportForm.reportName}
		</td>
	</tr>
	<tr>
		<td class="label">
			图形报表
		</td>
		<td class="content">
			<img src="..<%=path %>${statreportForm.id}.gif">
		</td>
	</tr><imgsrc="images/1.jpg">

	<tr>
		<td class="label">
			excelUrl
		</td>
		<td class="content">

			<form method="post" action="${app}/statisticfile/download.jsp" >
				<input type="hidden" name="excelFilePath" value="<%=path %>${statreportForm.id}.xls">
				<input type="hidden" name="excelFileName" value="${statreportForm.id}.xls">			
				<input type="submit" name="fileNamesubmit" value="导出Excel" >
			</form>			
		</td>
	</tr>
	
	<!-- 
	<tr>
		<td class="label">上传文件</td>
		<td>
			上传文件程序应用示例:
		     <form action="${app}/mms/statreports.do?method=uploadFile" method="post" enctype="multipart/form-data"><br>
		     请选择要上传的文件(21312Excel文件):
   				<input type="file" name="file" size="50">
   				<input type="hidden" name="id" value="${statreportForm.id}"/>
                <input type="submit" value="提交"/>
		     </form>
		</td>
	</tr>
 	-->
 
	<tr>
		<td class="label">
			报表描述
		</td>
		<td class="content">
			${statreportForm.footInfo}
		</td>
	</tr>

</table>


</fmt:bundle>




<%@ include file="/common/footer_eoms.jsp"%>