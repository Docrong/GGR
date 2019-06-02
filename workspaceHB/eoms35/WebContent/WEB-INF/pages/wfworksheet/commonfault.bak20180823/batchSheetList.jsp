<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- 批量操作时要显示相关的工单列表 -->
<script type="text/javascript">
	function openSee(){
		var listSeeObject = document.getElementById("listSeeObject");
		listSeeObject.style.display = "";
		var closeSee = document.getElementById("closeSee");
		closeSee.style.display = "";
	}
	function closeSee(){
		var listSeeObject = document.getElementById("listSeeObject");
		listSeeObject.style.display = "none";
		var closeSee = document.getElementById("closeSee");
		closeSee.style.display = "none";
	}
</script>

<span id="openSee">
	<a href="#"  onclick="openSee()">查看相关工单</a>
</span>
<span id="closeSee" style="display:none">
	<a href="#"  onclick="closeSee()">关闭查看</a>
</span>


<span id="listSeeObject" style="display:none">
  <table class="formTable" width="100%" cellpadding="0" cellspacing="0">
		<tr>
		   <td  class="label" nowrap="nowrap">
		       工单流水号
		   </td>
		   <td class="label content" nowrap="nowrap">
		 	  工单主题
           </td>
           <td class="label" nowrap="nowrap">
	    	  完成时限
		   </td>
		</tr>
 	<logic:iterate id="main" name="mains"  scope="request">
		<tr>
		  <td>
     		  <a href="${app}/sheet/${module}/${module}.do?method=showDetailPage&sheetKey=${main.id}" target="_blank"> <bean:write name="main" property="sheetId"/></a>
		  </td>
		  <td>
		      <bean:write name="main" property="title"/>
		  </td>
		  <td>
		     <bean:write name="main" property="sheetCompleteLimit" format="yyyy-MM-dd HH:mm:ss"/>
		  </td>       
		</tr>
	</logic:iterate>	
  </table>
</span>
<br/><br/>