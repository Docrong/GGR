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
var	userTreeAction='${app}/xtree.do?method=dept';//部门树
var userByDeptTree='${app}/xtree.do?method=userFromDept';//部门用户树
 
var	areaTree='${app}/xtree.do?method=areaTree'; //地域树

var v;
Ext.onReady(function(){
	v = new eoms.form.Validation({form:"theform"});

	//选择地域对话框
	areaTree = new xbox({
		btnId:'areaTreeBtn',dlgId:'dlg-area',
		treeDataUrl:areaTree,treeRootId:'-1',treeRootText:'${eoms:a2u("区域")}',treeChkMode:'',treeChkType:'area',
		showChkFldId:'areaName',saveChkFldId:'areaid' 
	});
	//角色
	var flowRoleSubroleTree = "${app}/sheet/workflow/workflow.do?method=getAllWorkflow";
	 var cfg = {
	  btnId:'subroleFromDeptBtn',
	  treeDataUrl:flowRoleSubroleTree,treeRootId:'-1',treeRootText:'${eoms:a2u("流程角色树")}',
	  treeChkMode:'',treeChkType:'subrole',showChkFldId:'subroleFromDeptName',saveChkFldId:'subroleFromDeptid',
	  baseAttrs:{
	   loader : new Ext.tree.TreeLoader({
	    dataUrl:eoms.appPath+'/xtree.do?method=flowRoleSubrole',
	    listeners:{
	     beforeload:function(loader,node,callback){
	      loader.baseParams['nodeType'] = node.attributes.nodeType || '';
	     }
	    }
	      })
	     }
	 }
	 var frTree = new xbox(cfg);
	 frTree.defaultTree.loader.applyLoader = false;
	 
	 //人员
	 userByDeptTree = new xbox({
		btnId:'userByDeptTreeBtn',dlgId:'dlg-user',
		treeDataUrl:userByDeptTree,treeRootId:'-1',treeRootText:'${eoms:a2u("人员")}',treeChkMode:'',treeChkType:'user',
		showChkFldId:'userByDeptName',saveChkFldId:'userByDeptid' 
	});
})
function displayTR(sel){

	//alert(sel.value);
	//alert('${eoms:a2u("")}');
	if(sel.value=="time")
	{
		eoms.form.enableArea("td2");
		eoms.form.disableArea("td1",true);
		eoms.form.disableArea("td3",true);
	}else if(sel.value=="month")
	{
		eoms.form.enableArea("td1");
		eoms.form.disableArea("td2",true);
		eoms.form.disableArea("td3",true);
	}else if(sel.value=="season")
	{
		eoms.form.enableArea("td3");
		eoms.form.disableArea("td1",true);
		eoms.form.disableArea("td2",true);
	}
	
	else if(sel.value==1)
	{
		eoms.form.disableArea("operateusertd",true);
		eoms.form.enableArea("operateroletd");
	}
	else if(sel.value==0)
	{
		eoms.form.disableArea("operateroletd",true);
		eoms.form.enableArea("operateusertd");
	}
	
	return;
}

	function validateCheck(data)
	{
		alert(data.getElementById("beginTime").value);
		alert(data.getElementById("endTime").value);
	}	
	
		
//alert(${"statspecial"}.value);

 
</script>
	
<html:form action="/stat.do?method=performStatistic" styleId="theform">


 <table class="formTable" >
   <caption>${eoms:a2u("输入条件")}</caption>   
   <tr>
   		<td>
			<input type="hidden" name="excelConfigURL" value="<%=excelConfigURL %>">  
					
   		</td> 
   </tr>
   
            <tr>
              <td noWrap class="label">
              <!-- <bean:message bundle="statistic" key="statistic.sendtime" /> -->
              ${eoms:a2u("派单")}
              <select name="type" onchange="displayTR(this)">
              	<option value="time">${eoms:a2u("时间")}</option>
              	<option value="month">${eoms:a2u("年月")}</option>
              	<option value="season">${eoms:a2u("季度")}</option>
              </select>
              </td>
              
              <td id="td1" style="display:none;">
              	${eoms:a2u("按月统计")}
             	 <select name="beginyear">
	              	<%
	              		for(int i=2001; i<= year+1;i++)
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
              
              <td id="td3" style="display:none;">
              
              <select name="beginyear">
	              	<%
	              		for(int i=2001; i<= year+1;i++)
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
	              
              	<select  name="seasonselect">
              		<option value="season_one">${eoms:a2u("第一季度")}</option>
              		<option value="season_two">${eoms:a2u("第二季度")}</option>
              		<option value="season_three">${eoms:a2u("第三季度")}</option>
              		<option value="season_four">${eoms:a2u("第四季度")}</option>
              	</select>
              </td>
              
            </tr>
           	
             <td noWrap class="label">${eoms:a2u("选择报表")} 
    		 
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
              
  		  <td id=operateusertd><!-- alt="allowBlank:false" -->
  		  	<input type="button" value="${eoms:a2u('部门人员选择')}" id="userByDeptTreeBtn" class="btn" />
  		  	<input type="text"  name="userByDeptName"  value="" id ="userByDeptName" class="text" />
  		  	<input type="hidden" id="userByDeptid" value="" name="userByDeptid" />
  		  </td>
  		  
  		  <td id=operateroletd  style="display:none;">
  		  	 <!-- 
  		  	 <input type="button" value="${eoms:a2u('角色选择')}" id="roleTreeBtn" class="btn" />
  		 	 <input type="text"  name="roleName"  value="" id ="roleName" class="text" />
  		 	 <input type="hidden" id="roleid" name="roleid" />
  		 	  -->
  		 	  
  		 	 <input type="button" value="${eoms:a2u('部门角色选择')}" id="subroleFromDeptBtn" class="btn" />
  		 	 <input type="text"  name="subroleFromDeptName"  value="" id ="subroleFromDeptName" class="text" />
  		 	 <input type="hidden" id="subroleFromDeptid" value="" name="subroleFromDeptid" />
  		  </td>
  		  
  		  </tr>
			<tr>
				
				<td noWrap class="label">${eoms:a2u("选择地域")}</td>
				
				<td>
              		<input type="button" value="${eoms:a2u('地域选择')}" id="areaTreeBtn" class="btn" />
          			<input type="text"  name="areaName"  value="" id ="areaName" class="text" readonly="readonly" />
					<input type="hidden" id="areaid" value="" name="areaid" />
				</td>
				
			</tr>
	
      <tr>
			  
			   <td class="label">${eoms:a2u("投诉分类一级")}</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}complainttype1" id="${sheetPageName}complainttype1" 
			  	      sub="${sheetPageName}complainttype2" initDicId="1010601" defaultValue="${sheetMain.complainttype1}"  alt="allowBlank:ture" />
			  </td>
			</tr>
     <tr>
			  
			   <td class="label">${eoms:a2u("投诉分类二级")}</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}complainttype2" id="${sheetPageName}complainttype2" 
			  	      sub="${sheetPageName}complainttype" initDicId="${sheetMain.complainttype1}" defaultValue="${sheetMain.complainttype2}" alt="allowBlank:ture"/>
			  </td>
			</tr>
			
			
			 <tr>
			  
			 <td class="label">${eoms:a2u("投诉分类三级")}</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}complainttype" id="${sheetPageName}complainttype" 
			  	      sub="${sheetPageName}complainttype4" initDicId="${sheetMain.complainttype2}" defaultValue="${sheetMain.complainttype}" alt="allowBlank:true" />
			  </td>
			</tr>
			 <tr>
			  
			 <td class="label">${eoms:a2u("投诉分类四级")}</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}complainttype4" id="${sheetPageName}complainttype4" 
			  	      sub="${sheetPageName}complainttype5" initDicId="${sheetMain.complainttype}" defaultValue="${sheetMain.complainttype4}" alt="allowBlank:true" />
			  </td>
			</tr>    		
			 <tr>
			  
			 <td class="label">${eoms:a2u("投诉分类五级")}</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}complainttype5" id="${sheetPageName}complainttype5" 
			  	      sub="${sheetPageName}complainttype6" initDicId="${sheetMain.complainttype4}" defaultValue="${sheetMain.complainttype5}" alt="allowBlank:true" />
			  </td>
			</tr>    		
			 <tr>
			  
			 <td class="label">${eoms:a2u("投诉分类六级")}</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}complainttype6" id="${sheetPageName}complainttype6" 
			  	      sub="${sheetPageName}complainttype7" initDicId="${sheetMain.complainttype5}" defaultValue="${sheetMain.complainttype6}" alt="allowBlank:true" />
			  </td>
			</tr>    		
			
			 <tr>
			  
			 <td class="label">${eoms:a2u("投诉分类七级")}</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}complainttype7" id="${sheetPageName}complainttype7" 
			  	      sub="" initDicId="${sheetMain.complainttype6}" defaultValue="${sheetMain.complainttype7}" alt="allowBlank:true" />
			  </td>
			</tr>    		
			
			<tr>
				<td>
				<input type="hidden" name="findListForward" value="<%=findListForward %>">  	
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
