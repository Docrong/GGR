<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<!-- 删除统计工具产生的垃圾文件，页面 -->
	 <head>
	   <title>Stat Manager Page</title>
	 </head>
	 
	 <table align="center" valign="center">
	 	<tr align="center" valign="center">
	 		<td align="center" valign="center">	 
	 				<form method="post" action="${app}/statisticfile/StatSet.jsp" >
				   		<select name="StatDeleteValue">
				   			<option value="StatDeleteExcel">StatDeleteExcel</option>
				   			<option value="StatDeleteXml">StatDeleteXml</option>
				   		</select>
				   		<input type="submit" name="fileNamesubmit" value="deleteExcel" >
				   	</form>
	 		</td>
	 	</tr>
	 </table>
   
<%@ include file="/common/footer_eoms.jsp"%>