<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userByDeptid = String.valueOf(request.getAttribute("subscriberId"));
%>

<script type="text/javascript">

var userByDeptTree='${app}/xtree.do?method=userFromDept';//部门用户树

var v;
Ext.onReady(function(){
	v = new eoms.form.Validation({form:"theform"});
	//部门用户树
	userByDeptTree = new xbox({
		btnId:'userByDeptTreeBtn',dlgId:'dlg-user',
		treeDataUrl:userByDeptTree,treeRootId:'-1',treeRootText:'人员',treeChkMode:'',treeChkType:'user',
		showChkFldId:'userByDeptName',saveChkFldId:'userByDeptid' 
	});
})
</script>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
	   <title><bean:message key="custom.title"/></title>
	 </head>
  
  <body>
	 <html:form action="/stat.do?method=showAlreadySatatistFileInfo" styleId="theform">
	 <table class="formTable">
	 	<tr>
	 		<td noWrap class="label">	 
	 			<bean:message key="custom.reportType"/>
	 		</td>
	 		
	 		<td>	
				<select name="seachReportType">
		   			<option value="yearReport">年报表</option>
		   			<option value="seasonReport">季报表</option>
		   			<option value="monthReport">月报表</option>
		   			<option value="weekReport">周报表</option>
		   			<option value="dailyReport">日报表</option>
		   			<option value="customReport">自定义报表</option>
		   			<option value="">所有</option>
		   		</select>
	 		</td>
	 	</tr>
	 	
	 	<tr>
	 		<td>	 
	 			<bean:message key="custom.time"/>
	 		</td>
	 		
	 		<td>
              	<bean:message bundle="statistic" key="statistic.begin" />
            		<input type="text" name="beginTime" id="beginTime" alt="allowBlank:true,vtype:'lessThen',link:'endTime',vtext:'<bean:message key="custom.beginTime1"/>´'" readonly="readonly" onclick="popUpCalendar(this, this,null,null,null,true,-1);" class="text"/>
            	<bean:message bundle="statistic" key="statistic.end" />
            		<input type="text" name="endTime" id="endTime" alt="allowBlank:true,vtype:'moreThen',link:'beginTime',vtext:'<bean:message key="custom.beginTime2"/>'" readonly="readonly" onclick="popUpCalendar(this, this,null,null,null,true,-1);" class="text"/>
            </td>
	 	</tr>
	 	
	 	<%
	 		//只有超级用户才可以查阅其他人订阅的报表
	 		
	 		if("admin".equalsIgnoreCase(userByDeptid))
	 		{
	 	%>		
 			<tr>
 				<td>
 					部门人员选择
 				</td>
 			
		 		<td><!-- alt="allowBlank:false" -->
		  		  	<input type="button" value="部门人员选择" id="userByDeptTreeBtn" class="btn" /><!-- 部门人员选择 -->
		  		  	<input type="text"  name="userByDeptName"  value="" id ="userByDeptName" class="text" />
		  		  	<input type="hidden" id="userByDeptid" value="" name="userByDeptid" />
	  		  	</td>
		 	</tr>
	 	<%		
	 		}
	 		else
	 		{
	 	 %>
	 	 	<input type="hidden" name="userByDeptid" value="<%=userByDeptid %>"/>
	 	 <%
			}	 	 
	 	  %>
	 	 
	 	 
	 	 <tr>
	 		<td>	 
	 			经过审核：
	 		</td>
	 		
	 		<td>	
				<select name="checked">
					<option value=""> 所有</option>
		   			<option value="Y">是</option>
		   			<option value="N">否</option>
		   		</select>
	 		</td>
	 	</tr>
	 	
	 	<tr>
	 		<td>	 
	 			阅读：
	 		</td>
	 		
	 		<td>	
				<select name="readedState">
					<option value=""> 所有</option>
		   			<option value="Y">已阅读</option>
		   			<option value="N">未阅读</option>
		   		</select>
	 		</td>
	 	</tr>
	 	
	 	
	 	
	 	 <tr>
	 		<td>	 
	 			 报表名称：
	 		</td>
	 		
	 		<td>	
				<input type="text" id="statName" name="statName"/>
	 		</td>
	 	</tr>
	 	
	 	<tr>
	 		<td colspan="2">	
				<!-- buttons -->
			     <html:submit styleClass="btn" property="method.save" styleId="method.save">
					<bean:message bundle="statistic" key="button.done"/>
			     </html:submit>
	 		</td>
	 	</tr>
	 </table>
	 </html:form>
   
<%@ include file="/common/footer_eoms.jsp"%>
    
  </body>
</html>
