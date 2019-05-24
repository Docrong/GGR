<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%
String id= StaticMethod.nullObject2String(request.getAttribute("id"));
%>
<style type='text/css'>
.gr_list_title{border-collapse:collapse; width:100%; height:27px;}
.gr_list_l{ background:url(${app}/images/title_left_bg.png) no-repeat; width:150px; color:#fff; font-size:14px; text-align:center; }
.gr_list_r{ background:url(${app}/images/title_right_bg.png) no-repeat; width:10px; }
.gr_list_c{ background:url(${app}/images/title_m_bg.png) repeat-x;}
</style>
<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
<script type="text/javascript" src="${app}/scripts/Sheet.js"></script>
<script type="text/javascript">

</script>
<!-- Sheet Tabs Start -->
<div id="sheet-detail-page">
  <!-- Sheet Detail Tab Start -->
  <div id="sheetinfo">
	<br/>
          <%if(!"".equals(id)){ %>
		 	<table class="gr_list_title">
			     <tr>
			      <td class="gr_list_l">百科接口信息</td>
			      <td class="gr_list_c">&nbsp;</td>
			      <td class="gr_list_r"></td>
			    </tr>
			  </table>
			  <table id="sheet" class="formTable" > 
			     <tr>
			       <td class="label"><!-- 网管告警流水号 -->
					百科ID
				   </td>
				   <td class="content">${id}</td>
				   <td class="label"><!-- 网管告警ID -->
					百科标题
				   </td>
				   <td class="content">${title}</td>
				 </tr>
			     <tr>
			       <td class="label"><!-- 网管告警流水号 -->
					告警ID
				   </td>
				   <td class="content">${alarmId}</td>
				   <td class="label"><!-- 网管告警ID -->
					告警专业
				   </td>
				   <td class="content">${alarmSpecialty}</td>
				 </tr>
				 <tr>
			       <td class="label"><!-- 网管告警流水号 -->
					设备厂家
				   </td>
				   <td class="content">${equipmentFactory}</td>
				   <td class="label"><!-- 网管告警ID -->
					设备类型
				   </td>
				   <td class="content">${equipmentType}</td>
				 </tr>
				 <tr>
			       <td class="label"><!-- 网管告警流水号 -->
					告警名称
				   </td>
				   <td class="content">${alarmName}</td>
				   <td class="label"><!-- 网管告警ID -->
					中文名称
				   </td>
				   <td class="content">${alarmNameCn}</td>
				 </tr>
				 <tr>
			       <td class="label"><!-- 网管告警流水号 -->
					告警级别
				   </td>
				   <td class="content">${alarmLevel}</td>
				   <td class="label"><!-- 网管告警ID -->
					告警解释
				   </td>
				   <td class="content">${alarmExplanation}</td>
				 </tr>
				 <tr>
			       <td class="label"><!-- 网管告警流水号 -->
					业务影响
				   </td>
				   <td class="content">${busiEffect}</td>
				   <td class="label"><!-- 网管告警ID -->
					其他内容
				   </td>
				   <td class="content">${otherContent}</td>
				 </tr>
				 <tr>
			       <td class="label"><!-- 网管告警流水号 -->
					处理建议
				   </td>
				   <td class="content">${handOpinion}</td>
				   <td class="label"><!-- 网管告警ID -->
					参考文档
				   </td>
				   <td class="content">${referDoc}</td>
				 </tr>
				 </table>
		<br/>
                <%} else { %>
    	            该告警ID没有对应的百科词条。
                <%} %>
  </div>
</div>