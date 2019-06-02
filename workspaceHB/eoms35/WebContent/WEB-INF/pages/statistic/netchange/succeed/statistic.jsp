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
var v;
Ext.onReady(function(){

	//eoms.form.disableArea("selrevdept",true);
	eoms.form.disableArea("selrevrole",true);
	eoms.form.disableArea("selrevperson",true);
	//eoms.form.disableArea("trSelectdept",true);
	
	v = new eoms.form.Validation({form:"theform"});
	
	//部门角色树
	var flowRoleSubroleTree = "${app}/sheet/workflow/workflow.do?method=getAllWorkflow";
	 var cfg = {
	  btnId:'roleTreeBtn',
	  treeDataUrl:flowRoleSubroleTree,treeRootId:'-1',treeRootText:'${eoms:a2u("流程角色树")}',
	  treeChkMode:'',treeChkType:'subrole',showChkFldId:'revRoleName',saveChkFldId:'revRoleid',
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

		

	function setDeptType(sel){
		
		if(sel.value=="revdept"){//部门
			eoms.$("reportIndex").value=0;
			alert(document.all.reportIndex.value);
			eoms.form.enableArea("selrevdept");
			eoms.form.disableArea("selrevrole",true);
			eoms.form.disableArea("selrevperson",true);
		} else if (sel.value=="revrole"){//角色
		    eoms.$("reportIndex").value=1;
		    eoms.form.enableArea("selrevrole");
			eoms.form.disableArea("selrevperson",true);
			eoms.form.disableArea("selrevdept",true);
		} else {//人
			eoms.$("reportIndex").value=2;
			eoms.form.enableArea("selrevperson");
			eoms.form.disableArea("selrevdept",true);
			eoms.form.disableArea("selrevrole",true);
		}
	}
	    
	    
 
</script>
	
<html:form action="/stat.do?method=performStatistic" styleId="theform">


 <table class="formTable">
   <caption>${eoms:a2u("输入条件")}</caption>   
   <tr>
   		<td>
			<input type="hidden" name="excelConfigURL" value="<%=excelConfigURL %>">  
			<input type="hidden" name="findListForward" value="<%=findListForward %>"> 
			<input type="hidden" id="reportIndex" name ="reportIndex"  value="0">		
			<input type="hidden" name="type" value="season">		
   		</td> 
   </tr>   
            <tr>
              <td noWrap class="label">
              <!-- <bean:message bundle="statistic" key="statistic.sendtime" /> -->
              ${eoms:a2u("派单季度")}
              </td>
              
              <td  width="80%">
              	${eoms:a2u("按季度统计")}
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
             	<select name="seasonselect">
              		<option value="season_one">${eoms:a2u("第一季度")}</option>
              		<option value="season_two">${eoms:a2u("第二季度")}</option>
              		<option value="season_three">${eoms:a2u("第三季度")}</option>
              		<option value="season_four">${eoms:a2u("第四季度")}</option>
              	</select>
              </td>
              
            </tr>
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
          	<input type="text"  name="revDeptName"  value="" id ="revDeptName" class="text"  readonly="readonly" alt="allowBlank:true">
            
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