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
var v;
Ext.onReady(function(){

	eoms.form.disableArea("selrevdept",true);
	eoms.form.disableArea("selboth",true);
	eoms.form.disableArea("trSelectdept",false);
	eoms.form.disableArea("tr1",true);
	eoms.form.enableArea("trSelectdept",true);
	v = new eoms.form.Validation({form:"theform"});
	sendDeptTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-dept',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("所属部门")}',treeChkMode:'',treeChkType:'dept',
		showChkFldId:'sendDeptName',saveChkFldId:'sendDeptId' 
	});

	revDeptTree = new xbox({
		btnId:'userTreeBtn1',dlgId:'dlg-dept1',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("所属部门")}',treeChkMode:'',treeChkType:'dept',
		showChkFldId:'revDeptName',saveChkFldId:'revDeptId' 
	});
	
	sendDeptTree1 = new xbox({
		btnId:'userTreeBtn2',dlgId:'dlg-dept',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("所属部门")}',treeChkMode:'',treeChkType:'dept',
		showChkFldId:'sendDeptName1',saveChkFldId:'sendDeptId' 
	});

	revDeptTree1 = new xbox({
		btnId:'userTreeBtn3',dlgId:'dlg-dept1',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("所属部门")}',treeChkMode:'',treeChkType:'dept',
		showChkFldId:'revDeptName1',saveChkFldId:'revDeptId' 
	});

})

function displayTR(sel){

	//alert(sel.value);
	//alert('${eoms:a2u("")}');
	if(sel.value==0){
		eoms.form.disableArea("trSelectdept",true);
		eoms.form.enableArea("tr1");
		//document.getElementById("tr1").style.display="block";
		//document.getElementById("tr2").style.display="none";
	} else if (sel.value==1) {
		eoms.form.disableArea("tr1",true);
		eoms.form.enableArea("trSelectdept");
		//document.getElementById("tr1").style.display="none";
		//document.getElementById("tr2").style.display="block";
	}else if(sel.value=="time")
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
	
	function setStatspecial(sel){
		if(sel.value=="14,1562,1566,1568,15,16,17,18,19,20,21,22"){
			${"statspecial"}.value=1;
		}
		
		
//alert(${"statspecial"}.value);
	}
	
	function setDeptType(sel){
		if(sel.value=="senddept"){
			eoms.form.enableArea("selsenddept");
			eoms.form.disableArea("selrevdept",true);
			eoms.form.disableArea("selboth",true);
			
		}else if(sel.value=="revdept"){
			eoms.form.enableArea("selrevdept");
			eoms.form.disableArea("selsenddept",true);
			eoms.form.disableArea("selboth",true);
		}else{
			eoms.form.enableArea("selboth");
			eoms.form.disableArea("selrevdept",true);
			eoms.form.disableArea("selsenddept",true);
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
     <td noWrap class="label">${eoms:a2u("选择报表")}</td>
     <td width="80%">

            	<select name="reportIndex" onchange="displayTR(this)">
            	<%
            	System.out.println(excelConfig.getSheets().length);
								for(int i=0; i<excelConfig.getSheets().length;i++){
									Sheet sheet = excelConfig.getSheets()[i];
							%>		
                 <option value="<%=sheet.getSheetIndex() %>"><%=sheet.getSheetName()%></option> 
									
									
							<%}%>
              </select>       		
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
	            	<input type="text" name="beginTime" id="beginTime" alt="allowBlank:false,vtype:'lessThen',link:'endTime',vtext:'${eoms:a2u("开始时间不能为空或晚于结束时间")}'" readonly="readonly" onclick="popUpCalendar(this, this);" class="text"/>
	            	<bean:message bundle="statistic" key="statistic.end" />
	            	<input type="text" name="endTime" id="endTime" alt="allowBlank:false,vtype:'moreThen',link:'beginTime',vtext:'${eoms:a2u("结束时间不能为空或早于开始时间")}'" readonly="readonly" onclick="popUpCalendar(this, this);" class="text"/>
              </td>
            </tr>
            
            <tr id="tr1">
            <td noWrap class="label"><bean:message bundle="statistictask" key="statistic.city" />
            </td>
            <td width="80%">
            	<input id="statspecial" type="hidden" name="statspecial" >
        		<select name="cityDeptId" onchange="setStatspecial(this)">
        		
        		<option value="14,1562,1566,1568">${eoms:a2u("贵阳分公司")}</option>
        		<option value="15">${eoms:a2u("遵义分公司")}</option>
        		<option value="16">${eoms:a2u("安顺分公司(运行维护部）")}</option>
        		<option value="17">${eoms:a2u("黔南分公司")}</option>
        		<option value="18">${eoms:a2u("黔东南分公司")}</option>
        		<option value="19">${eoms:a2u("铜仁分公司")}</option>
        		<option value="20">${eoms:a2u("毕节分公司")}</option>
        		<option value="21">${eoms:a2u("六盘水分公司")}</option>
        		<option value="22">${eoms:a2u("黔西南分公司")}</option>
        		<option value="14,1562,1566,1568,15,16,17,18,19,20,21,22">${eoms:a2u("全部")}</option>

             	</select>
             	
	        </td>
	        
	        
          </tr>

          <!-- -->    
          <tr id="trSelectdept">
          <td noWrap class="label"><!-- bean:message bundle="statistictask" key="statistic.dept" / -->
          	<select name="selectDeptType" onchange="setDeptType(this)">
          		<option value="senddept">${eoms:a2u("只按派单部门统计")}</option>
          		<option value="revdept">${eoms:a2u("只按接单部门统计")}</option>
          		<option value="both">${eoms:a2u("两者都选")}</option>
        	</select>

          </td>
			<input type="hidden" id="sendDeptId" name="sendDeptId" >
			<input type="hidden" id="revDeptId" name="revDeptId" >
          <td id="selsenddept">
          	<input type="button" value="${eoms:a2u('派发部门')}" id="userTreeBtn" class="btn" />
          	<input type="text"  name="sendDeptName"  value="" id ="sendDeptName" class="text" readonly="readonly" alt="allowBlank:true">
          	
          </td>	
          <td id="selrevdept">
        	<input type="button" value="${eoms:a2u('受理部门')}" id="userTreeBtn1" class="btn" />
          	<input  name="revDeptName"  value="" id ="revDeptName" class="text"  readonly="readonly" alt="allowBlank:true">
            
  		  </td>
  		  <td id="selboth">
        	<input type="button" value="${eoms:a2u('派发部门')}" id="userTreeBtn2" class="btn" />
          	<input type="text"  name="sendDeptName1"  value="" id ="sendDeptName1" class="text" readonly="readonly" alt="allowBlank:true">

          	<input type="button" value="${eoms:a2u('受理部门')}" id="userTreeBtn3" class="btn" />
          	<input  name="revDeptName1"  value="" id ="revDeptName1" class="text"  readonly="readonly" alt="allowBlank:true">
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
