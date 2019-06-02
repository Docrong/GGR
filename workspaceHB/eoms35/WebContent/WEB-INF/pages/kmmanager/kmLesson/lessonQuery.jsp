<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">

function checkTime(){
	var ss=document.getElementById("starttime");
	var tt=document.getElementById("endtime");

	if((ss.value!="")&&(tt.value=="")){
		alert("请输入您要查询的结束时间...");
		return false;
	}
	
	if((ss.value=="")&&(tt.value!="")){
		alert("请输入您要查询的开始时间...");
		return false;
	}
	return true;
}
</script>

<form enctype="multipart/form-data" name="CreatlessonForm" id="CreatlessonForm" method="post"
			action="${app}/kmmanager/creatlessons.do?method=search&flag=yes" styleId="CreatlessonForm"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<table class="formTable">	
	<caption>
		<div class="header center"><fmt:message key="creatlesson.title.search"/>
		<img src="${app}/images/icons/search.gif"/>
		</div>
	</caption>
</table>	
<table class="formTable">
	<!-- 创建时间 -->
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.startTime"/> 
		</td>
		<td class="content" colspan="2">
			<input type="text" size="20" readonly="readonly" class="text" 
			          name="starttime"
			          id="starttime" 
			          onclick="popUpCalendar(this,this,null,null,null,true,-1);"
			          alt="allowBlank:false,vtext:'请选择开始时间...'" value=""/>
		</td>
	</tr>
	<tr>
		<td class="label">	          
			<fmt:message key="creatlesson.endTime"/>
		</td>
		<td class="content" colspan="2">	 
			<input type="text" size="20" readonly="readonly" class="text" 
			          name="endtime" 
			          id="endtime" 
			          onclick="popUpCalendar(this,this,null,null,null,true,-1);"
			          alt="allowBlank:false,vtext:'请选择结束时间...'" value=""/>
		</td>
	</tr>
	
	
    <!-- 文件名 --> 	
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.subjectName" />
		</td>
		<td class="content" colspan="2">								
			<input type="text" id="subjectName" name="subjectName" class="text" maxlength="20" value="" alt="allowBlank:true"/>
		</td>
	</tr>
	
	<!-- 主题 -->
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.theme" />			
		</td>
		<td class="content" colspan="2">
			<input type="text"   name="theme" id="theme" value="" maxlength="20" class="text" alt="allowBlank:true"/>
		</td>
	</tr>
	

   <!-- 业务类别 -->
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.businessType" />			
		</td>
		<td class="content" colspan="2">
			<input type="text"   name="businessType" id="businessType" value="" maxlength="10" class="text" alt="allowBlank:true"/>
		</td>
	</tr>
	
	  <!-- 编号-->
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.id" />
			
		</td>
		<td class="content" colspan="2">
		<input type="text"   name="id" id="id" value="" maxlength="10" class="text" alt="allowBlank:true"/>
		<!--   <input type="text"   name="expand" id="expand" value="" maxlength="10" class="text" alt="allowBlank:true" /> -->  
		</td>
	</tr>
	
</table>
<br>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="creatlesson.search"/>" onclick="return checkTime();"/>
			<img src="${app}/images/icons/search.gif"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" class="btn" value="<fmt:message key="creatlesson.rest"/>" />
			<img src="${app}/images/icons/refresh.gif"/>
		</td>
	</tr>
</table>
</fmt:bundle>
<br>
</form>

<%@ include file="/common/footer_eoms.jsp"%>