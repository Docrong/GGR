<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%@page import="com.boco.eoms.commons.statistic.base.config.excel.* ,java.util.*"%>
<%
	Excel excelConfig =(Excel) request.getAttribute("excelConfig");
	if (excelConfig == null) throw new Exception("读取配置统计文件失败!");
	
	String excelConfigURL = String.valueOf(request.getAttribute("excelConfigURL"));
	String findListForward = String.valueOf(request.getAttribute("findListForward"));
	Calendar cld = Calendar.getInstance();
	int year = cld.get(Calendar.YEAR);
	int mondth = cld.get(Calendar.MONTH) + 1;
%>
<script type="text/javascript">
var	userTreeAction='${app}/xtree.do?method=dept';
var userByDeptTree='${app}/xtree.do?method=userFromDept';//部门用户树

var v;
Ext.onReady(function(){
eoms.form.disableArea("selrevdept",true);
eoms.form.disableArea("seloper",true);
v = new eoms.form.Validation({form:"theform"});
	
//用户树
	userByDeptTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',
		treeDataUrl:userByDeptTree,treeRootId:'-1',treeRootText:'${eoms:a2u("人员")}',treeChkMode:'',treeChkType:'user',
		showChkFldId:'revUserName',saveChkFldId:'revUserId' 
	});

	//处理部门树
	revDeptTree = new xbox({
		btnId:'deptTreeBtn',dlgId:'dlg-dept1',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("部门")}',treeChkMode:'',treeChkType:'dept',
		showChkFldId:'revDeptName',saveChkFldId:'revDeptId' 
	});
	
})

function displayTR(sel){

	if(sel.value=="time")
	{
		eoms.form.disableArea("td1",true);
		eoms.form.enableArea("td2");
	}else if(sel.value=="month")
	{
		eoms.form.disableArea("td2",true);
		eoms.form.enableArea("td1");
	}
	return;
}
	
	function validateCheck(data)
	{
		alert(data.getElementById("beginTime").value);
		alert(data.getElementById("endTime").value);
	}

		

	
	function setDeptType(sel){
		if(sel.value=="revdept"){//部门
			eoms.$("reportIndex").value=1;
			eoms.form.enableArea("selrevdept");
			eoms.form.enableArea("seloper");
		  eoms.form.disableArea("selrevperson",true);
		  
		}else {//人
			eoms.$("reportIndex").value=0;
			eoms.form.enableArea("selrevperson");
			eoms.form.disableArea("selrevdept",true);
			eoms.form.disableArea("seloper",true);
	  }
	}
 
</script>
	
<html:form action="/stat.do?method=performStatistic" styleId="theform">


 <table class="formTable" >
   <caption>${eoms:a2u("输入条件")}</caption>   
   <tr>
   		<td>
			<input type="hidden" name="excelConfigURL" value="<%=excelConfigURL %>">  
			<input type="hidden" name="findListForward" value="<%=findListForward %>"> 
				
   		</td> 
   </tr>
   
   
            <tr>
              <td noWrap class="label">
              ${eoms:a2u("登陆")}
              <select name="type" onchange="displayTR(this)">
              	<option value="time">${eoms:a2u("时间")}</option>
              	<option value="month">${eoms:a2u("年月")}</option>
              </select>
              </td>
              
              <td id="td1" style="display:none;">
              	${eoms:a2u("按月统计")}
             	 <select name="beginyear">
	              	<%
	              		for(int i=2008; i<= year+1;i++)
	              		{
	              			String select = "";
	              			if(year == i)
	              			{
	              				select = "Selected";
	              			}
	              	 %>
	              		<option value="<%=i%>" <%=select %>><%= i%></option>
	              	<%}%>
	              </select>
	              ${eoms:a2u("年")}
	              
	              <select name="beginmonth">
	              	<%
	              		for(int i=1; i<=12;i++)
	              		{
	              		
	              			String value = String.valueOf(i);
		              		if(i<10)
		              		{
		              			value = "0" + String.valueOf(i);
		              		}
		              		
		              		String select = "";
	              			if(mondth == i)
	              			{
	              				select = "Selected";
	              			}
	              	 %>
	              		<option value="<%=value%>" <%=select %>><%= value%></option>
	              	<%}%>
	              </select>
	              ${eoms:a2u("月")}
              </td>
              
              <td width="80%" id="td2">
              	<bean:message bundle="statistic" key="statistic.begin" />
	            	<input type="text" name="beginTime" id="beginTime" alt="allowBlank:false,vtype:'lessThen',link:'endTime',vtext:'${eoms:a2u("开始时间不能为空或晚于结束时间")}'" readonly="readonly" onclick="popUpCalendar(this, this,null,null,null,true,-1);" class="text"/>
	            	<bean:message bundle="statistic" key="statistic.end" />
	            	<input type="text" name="endTime" id="endTime" alt="allowBlank:false,vtype:'moreThen',link:'beginTime',vtext:'${eoms:a2u("结束时间不能为空或早于开始时间")}'" readonly="readonly" onclick="popUpCalendar(this, this,null,null,null,true,-1);" class="text"/>
              </td>
            </tr>
            
         
          <tr id="trSelectdept">
          <td noWrap class="label">
          	<select name="selectDeptType" onchange="setDeptType(this)">
          	   <option value="revperson">${eoms:a2u("按人")}</option>
          		<option value="revdept">${eoms:a2u("按部门")}</option>
          		
        	</select>

          </td>
			<input type="hidden" id="revDeptId" name="revDeptId" >
			<input type="hidden" id="revUserid" name="revUserId" >	
          <td id="selrevdept">
        	<input type="button" value="${eoms:a2u('受理部门')}" id="deptTreeBtn" class="btn" />
          	<input  name="revDeptName"  value="" id ="revDeptName" class="text"  readonly="readonly" alt="allowBlank:true">
            
  		  </td>
  		 
  		  <td id="selrevperson">
        	<input type="button" value="${eoms:a2u('受理人')}" id="userTreeBtn" class="btn" />
          	<input type="text"  name="revUserName"  value="" id ="revUserName" class="text" readonly="readonly" alt="allowBlank:true">
  		  </td>
  		  </tr>
  		  
  		  
  	      <tr id="seloper">
              <td noWrap class="label">
              ${eoms:a2u("功能模块")}
             </td>	
             <td>
              <select name="opername" >
               <option value="">${eoms:a2u("==请选择==")}</option>
              	<option value="${eoms:a2u("防汛管理")}">${eoms:a2u("防汛管理")}</option>
              	<option value="${eoms:a2u("个人工作台")}">${eoms:a2u("个人工作台")}</option>
               	<option value="${eoms:a2u("组织管理")}">${eoms:a2u("组织管理")}</option>
              	<option value="${eoms:a2u("信息发布")}">${eoms:a2u("信息发布")}</option>
              	<option value="${eoms:a2u("资料管理")}">${eoms:a2u("资料管理")}</option>
              	<option value="${eoms:a2u("作业计划")}">${eoms:a2u("作业计划")}</option> 
               	<option value="${eoms:a2u("评估管理")}">${eoms:a2u("评估管理")}</option>
              	<option value="${eoms:a2u("值班管理")}">${eoms:a2u("值班管理")}</option>
              	<option value="${eoms:a2u("流程管理")}">${eoms:a2u("流程管理")}</option>
         	
              </select>
              </td>	  
  		  	<tr>
				<td>
						<input type="hidden" id="reportIndex" name ="reportIndex" value="0">	
				</td>
			</tr>
          </table>

  <br/>	
 
     <html:submit styleClass="btn" property="method.save" styleId="method.save">
				<bean:message bundle="statistic" key="button.done"/>
     </html:submit>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
