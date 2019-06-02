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
var treeAction='${app}/xtree.do?method=userByDept';
var userByDeptTree='${app}/xtree.do?method=userFromDept';//部门用户树
var	roleTree='${app}/xtree.do?method=roleTree'; //角色树
var subroleFromDept='${app}/xtree.do?method=subroleFromDept'; //部门角色树
var v;
Ext.onReady(function(){

	//eoms.form.disableArea("selrevdept",true);
	eoms.form.disableArea("selrevrole",true);
	eoms.form.disableArea("selrevperson",true);
	//eoms.form.disableArea("trSelectdept",true);
	
	v = new eoms.form.Validation({form:"theform"});
	
	//部门角色树
	subroleFromDept = new xbox({
		btnId:'roleTreeBtn',dlgId:'dlg-subroleFromDept',
		treeDataUrl:subroleFromDept,treeRootId:'-1',treeRootText:'${eoms:a2u("部门角色")}',treeChkMode:'',treeChkType:'subrole',
		showChkFldId:'revRoleName',saveChkFldId:'revRoleid' 
	});
	
	//用户树
	userByDeptTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',
		treeDataUrl:userByDeptTree,treeRootId:'-1',treeRootText:'${eoms:a2u("人员")}',treeChkMode:'',treeChkType:'user',
		showChkFldId:'revUserName',saveChkFldId:'revUserid' 
	});

	//处理部门树
	revDeptTree = new xbox({
		btnId:'deptTreeBtn',dlgId:'dlg-dept1',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("所属部门")}',treeChkMode:'',treeChkType:'dept',
		showChkFldId:'revDeptName',saveChkFldId:'revDeptId' 
	});
	
})

function displayTR(sel){

	//alert(sel.value);
	//alert('${eoms:a2u("")}');
	
	
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
			eoms.$("reportIndex").value=0;
			eoms.form.enableArea("selrevdept");
			eoms.form.disableArea("selrevrole",true);
			eoms.form.disableArea("selrevperson",true);
		} else if (sel.value=="revrole"){//角色
			eoms.$("reportIndex").value=1;
			eoms.form.enableArea("selrevrole");
			eoms.form.disableArea("selrevdept",true);
			eoms.form.disableArea("selrevperson",true);
		} else {//人
			eoms.$("reportIndex").value=2;
			eoms.form.enableArea("selrevperson");
			eoms.form.disableArea("selrevdept",true);
			eoms.form.disableArea("selrevrole",true);
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
			<input type="hidden" name="reportIndex"  value="0">			
   		</td> 
   </tr>
   
   
            <tr>
              <td noWrap class="label">
              <!-- <bean:message bundle="statistic" key="statistic.sendtime" /> -->
              ${eoms:a2u("派单")}
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
            
         <!-- -->    
          <tr id="trSelectdept">
          <td noWrap class="label"><!-- bean:message bundle="statistictask" key="statistic.dept" / -->
          	<select name="selectDeptType" onchange="setDeptType(this)">
          		<option value="revdept">${eoms:a2u("按处理部门统计")}</option>
          		<option value="revrole">${eoms:a2u("按处理角色统计")}</option>
          		<option value="revperson">${eoms:a2u("按处理人统计")}</option>
        	</select>

          </td>
			<input type="hidden" id="revDeptId" name="revDeptId" >
			<input type="hidden" id="revRoleid" name="revRoleid" >	
			<input type="hidden" id="revUserid" name="revUserid" >	
          <td id="selrevdept">
        	<input type="button" value="${eoms:a2u('受理部门')}" id="deptTreeBtn" class="btn" />
          	<input  name="revDeptName"  value="" id ="revDeptName" class="text"  readonly="readonly" alt="allowBlank:true">
            
  		  </td>
  		  <td id="selrevrole">
          	<input type="button" value="${eoms:a2u('受理角色')}" id="roleTreeBtn" class="btn" />
          	<input type="text"  name="revRoleName"  value="" id ="revRoleName" class="text" readonly="readonly" alt="allowBlank:true">
          	
          </td>
  		  <td id="selrevperson">
        	<input type="button" value="${eoms:a2u('受理人')}" id="userTreeBtn" class="btn" />
          	<input type="text"  name="revUserName"  value="" id ="revUserName" class="text" readonly="readonly" alt="allowBlank:true">
  		  </td>
  		  </tr>
			<tr>
				<td>
					<input id="customDescribe" type="hidden" name="customDescribe" >
				</td>
			</tr>
          </table>

  <br/>	
  <!-- buttons -->

     <html:submit styleClass="btn" property="method.save" styleId="method.save">
				<bean:message bundle="statistic" key="button.done"/>
     </html:submit>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
