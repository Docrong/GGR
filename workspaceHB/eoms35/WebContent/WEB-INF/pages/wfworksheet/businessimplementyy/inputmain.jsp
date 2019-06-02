<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.sheet.base.util.UUIDHexGenerator" %> 
<%
 request.setAttribute("roleId","950"); 
long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
String orderId = UUIDHexGenerator.getInstance().getID();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/businessimplementyy/baseinputmainhtmlnew.jsp"%>
 <script type="text/javascript">
     	//selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "businessimplementyy.do?method=newShowLimit&flowName=BusinessImplementYYProcess",
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	   // $("${sheetPageName}sheetAcceptLimit").value = "";
        	   // $('${sheetPageName}sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	       	var dt1 = new Date().add(Date.MINUTE,parseInt(o.acceptLimit,10));
	       	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	$("${sheetPageName}sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('${sheetPageName}sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }

   
   </script>
   <!-- 流程互调增加 -->
   
   <input type="hidden" name="${sheetPageName}sheetTemplateName" value="BusinessImplementYYProcess" />
	<input type="hidden" name="${sheetPageName}processTemplateName" value="BusinessImplementYYProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet" />
	<c:if test="${status!=1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
       <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${sheetPageName}operateType" />
       <input type="hidden" name="${sheetPageName}gatherPhaseId" id="${sheetPageName}gatherPhaseId" value="HoldTask" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iBusinessImplementYYMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYLink"/>
    <input type="hidden" name="${sheetPageName}orderId" value="<%=orderId%>"/>
    <br>
    <fieldset>
	 <legend>客户相关信息</legend> 
  		<table class="formTable">		     
		     <tr>
		     <td class="label">客户名称*</td>
		     <td class="content">
		         <input type="text"  class="text" name="${sheetPageName}customName" id="${sheetPageName}customName" alt="allowBlank:false" />  
		     </td>
		     <td class="label">客户地址*</td>
		     <td class="content">
		         <input type="text"  class="text" name="${sheetPageName}customAdd" id="${sheetPageName}customAdd" alt="allowBlank:false" />  
		     </td>
			 </tr>
		   <tr>
		     <td class="label">客户联系人</td>
		     <td class="content">  
		         <input type="text"  class="text" name="${sheetPageName}customContact" id="${sheetPageName}customContact" alt="allowBlank:false" />
		     </td>
		     <td class="label">客户联系人电话</td>
		     <td class="content">
		         <input type="text"  class="text" name="${sheetPageName}customContactPhone" id="${sheetPageName}customContactPhone" alt="allowBlank:false" />
		     </td>
		   </tr> 
		   <tr> 
			     <td class="label">集团客户联系人邮箱</td>
			     <td class="content">
			         <input type="text"  class="text" name="${sheetPageName}customConnMain" id="${sheetPageName}customConnMain" alt="allowBlank:true" />  
			     </td>
			     <td class="label">集团客户编号*</td>
			     <td class="content">
			         <input type="text"  class="text" name="${sheetPageName}groupCustomNo" id="${sheetPageName}groupCustomNo" alt="allowBlank:true" />  
			     </td>
		    </tr>  
  
		</table>
	</fieldset>
<fieldset id="link1" >
<legend>CMCC客户经理信息</legend>
	<table class="formTable">
		 <br>
			   <tr>
		     <td class="label">客户经理姓名*</td>
		     <td class="content">
		         <input type="text"  class="text" name="${sheetPageName}cmanagerPhone" id="${sheetPageName}cmanagerPhone" alt="allowBlank:false" />
		     </td>
		     <td class="label">业务所属区域*</td>
		     <td class="content">
		         <input type="text"  class="text" name="${sheetPageName}businesBelongCity" id="${sheetPageName}businesBelongCity" alt="allowBlank:false" />
		     </td>
		   </tr>
		   <!-- old end-->
		   <tr>
		     <td class="label">客户经理联系邮箱*</td>
		     <td class="content" > 
		         <input type="text"  class="text" name="${sheetPageName}customManagerMail" id="${sheetPageName}customManagerMail" alt="allowBlank:false" />
		     </td>
		     <td class="label">客户经理电话*</td>
		     <td class="content">
		         <input type="text"  class="text" name="${sheetPageName}projectManagerPhone" id="${sheetPageName}projectManagerPhone" alt="allowBlank:false" />
		     </td>
		   </tr>   
		</table>
</fieldset>
<fieldset id="link2" >
<legend>合同号信息</legend>
	<table class="formTable">
		 <br>
			   <tr>
		     <td class="label">合同号</td>
		     <td class="content">
		         <input type="text"  class="text" name="${sheetPageName}productSN" id="${sheetPageName}productSN" alt="allowBlank:true" />
		     </td>
		     <td class="label">签订日期</td>
		         <td class="content">
			        <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
					 id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
					 alt="allowBlank:true" onclick="popUpCalendar(this, this)" /> 
		  			  
			    </td>	
		   </tr> 
		   <!-- 
		   		<td class="label">拟生效时间</td>
		 <td class="content">
		     <input type="text" class="text" name="planOenDate" readonly="readonly"  styleId="planOenDate" 
					 id="planOenDate" value="${eoms:date2String(languagespeciallineForm.planOenDate)}" 
					 alt="allowBlank:true" onclick="popUpCalendar(this, this)" /> 
		  			  
			  </td> -->
		</table>
</fieldset>
<fieldset id="link3" >
<legend>技术方案信息</legend>
	<table class="formTable">
		 <br>
		<tr>
	      <td class="label">技术方案描述</td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}needDesc" class="textarea max" id="${sheetPageName}needDesc" 
		        alt="allowBlank:true,width:200,vtext:'请最多输入100字'" alt="width:'200px'"></textarea>
		  </td>
		</tr>
		
		
		<tr>
			<td colspan="4">
			
				<input type="button" class="btn" value="添加产品" onclick="javascript:openwin()"> 
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<iframe id="frame" src="" width="100%" height="300px" style="display:none"></iframe>
			</td>
		</tr>	
		</table>
</fieldset>
	<c:if test="${status!=1}">
	<fieldset id="link1" >
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName">:定单审核人
			 </span>
	      </legend>
	        <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="951" flowId="270" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sendUserAndRoles}" />			   
			 </div>
	</fieldset>
	</c:if>
<script type="text/javascript">
refreshFrame();
function openwin(){

		
		var urls = "${app}/businessupport/product/languagespeciallines.do?method=xedit&type=add&sheetType=businessImplement&ifReference=&orderId=<%=orderId%>";
		
		window.showModalDialog(urls,"","dialogWidth=1200px;dialogHeight=100px;help:no;resizable:yes;status:no;dialogWidth=1024px;dialogHeight=700px");
		//window.open(urls,"newwindow","dialogWidth=1200px;dialogHeight=100px;help:no;resizable:yes;status:no;dialogWidth=1024px;dialogHeight=700px");
		obj.style.display="block";
		refreshFrame();
	}
	function refreshFrame(){
		var mainSpecifyType = "yuyin";
		obj   =   document.all["frame"];
		if(obj.style.display=="none")
			return false;

	 	obj.src = "${app}/businessupport/order/ordersheets.do?method=getSpecialLinesByType&orderId=<%=orderId%>&specialtyType="+mainSpecifyType+"&taskName=${taskName}&taskStatus=${taskStatus}"; 
		
	}
</script>

	