<%@ include file="/common/extlibs.jsp"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!-- 查询控制 -->
<% String ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("ifReference"));
    if(ifReference.equals("")) ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifReference"));
 %>
<logic:notPresent name="ifReference" scope="request">
     <% if(!ifReference.equals("")){
           request.setAttribute("ifReference",ifReference);
           }
      %>
</logic:notPresent>
<!-- 返回控制 -->
<% String back = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("back")); %>
<logic:notPresent name="back" scope="request">
     <% if(!back.equals("")){
           request.setAttribute("back",back);
           }
      %>
</logic:notPresent>
<script type="text/javascript">
function check() {
	var templateIds = document.getElementsByName("procode");
	var nbPName = document.getElementsByName("nbPName");
	var i = 0;
	var templateId = "";
	var nName = "";
	for (i = 0 ; i < templateIds.length; i ++) {
		if (templateIds[i].checked) {
			templateId = templateIds[i].value;
			nName = nbPName[i].value;
		}
	}
	if (templateId == "") {
		alert("未选择，请确认后引用 ");
		return false;
	} else {
		opener.inputProCode(templateId,nName);
		window.close();
	}
}
function queryDeleted() {
 	var formObj = document.forms[0];
 
 	if('${ifReference}'!=null&&'${ifReference}'!='')
 	{
 	formObj.action = "ordersheets.do?method=xsearch&ifReference=true&back=true&deleted=1";
 	}else{
 	formObj.action = "ordersheets.do?method=xsearch&back=true&deleted=1";
 	}
 	formObj.submit();
 }
  function undelete() {
 
       document.location.href = "ordersheets.do?method=xquery&ifReference=${ifReference}";
 } 
function restore() {

 	var formObj = document.forms[0];
 	var id = document.getElementsByName("id");
 
 	for(var i=0;i<id.length;i++)
 	{
 	if(id[i].checked==true){	
 	var ids=id[i].checked;
    if (confirm("确认恢复所选对象吗？")) {
 	formObj.action = "ordersheets.do?method=xrestore&ifReference=${ifReference}&ordersheetid="+id[i].value;
 	formObj.submit();
 	}
 	break;
 	}
 	} 
 	if(ids != true){
 	alert("请选择要恢复的对象");
 	}
 }

</script>
<html:form action="ordersheets.do?method=xsearch" method="post" styleId="ordersheetForm">
<table height="35" width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td nowrap><content tag="heading">已删除产品目录库</content></td>
<td width="130" align="left">
  <input type="hidden" name="ifReference" id="ifReference" value="${ifReference}" />
</td>
</tr>
</table>
    
<bean:define id="url" value="ordersheets.do" />
<display:table name="ordersheetList" cellspacing="0" cellpadding="0"
    id="ordersheet" pagesize="15" class="table ordersheetList"
     export="true" requestURI="/businessupport/order/ordersheets.do?method=showDeletedDetail&ifReference=${ifReference}" sort="list" >
    <display:column media="html">
            <input type="radio" name="id" value="${ordersheet.id}"/>
			<input type="hidden" name="orderType" value="${ordersheet.orderType}"/>
    </display:column>

    <display:column property="productName" sortable="true" headerClass="sortable"
        url="/sheet/ordersheet/ordersheets.do?method=showDetail&flag=showDetail&ifReference=${ifReference}" 
        paramId="id" paramProperty="id" title="产品名称"/> 
    <display:column property="mainProductInstanceCode" sortable="true" headerClass="sortable"
        url="/sheet/ordersheet/ordersheets.do?method=showDetail" paramId="id" paramProperty="id"
         title="主产品实例编码"/>       
    <display:column  sortable="true" headerClass="orderType" title="定单类型">   
        <eoms:id2nameDB id="${ordersheet.orderType}" beanId="ItawSystemDictTypeDao" />   
    </display:column> 
    <display:column  sortable="true" headerClass="sortable"  title="紧急程度">  
      <eoms:id2nameDB id="${ordersheet.urgentDegree}" beanId="ItawSystemDictTypeDao" />
    </display:column> 
    <display:column sortable="true" headerClass="sortable" title="定单业务类型">
         <eoms:id2nameDB id="${ordersheet.orderBuisnessType}" beanId="ItawSystemDictTypeDao" />
    </display:column>     
    <display:column property="creatTime" sortable="true" headerClass="sortable"
        url="/sheet/ordersheet/ordersheets.do?method=showDetail" paramId="id" paramProperty="id"
        format="{0,date,yyyy-MM-dd HH:mm:ss}" 
        title="生成时间"/>
     <display:column property="endTime" sortable="true" headerClass="sortable"
        url="/sheet/ordersheet/ordersheets.do?method=showDetail" paramId="id" paramProperty="id"
        format="{0,date,yyyy-MM-dd HH:mm:ss}" 
        title="竣工时间"/>
     <display:column property="completeLimit" sortable="true" headerClass="sortable"
        url="/sheet/ordersheet/ordersheets.do?method=showDetail" paramId="id" paramProperty="id"
        format="{0,date,yyyy-MM-dd HH:mm:ss}" 
        title="完成期限"/> 
     <display:column property="changeTime" sortable="true" headerClass="sortable"
        url="/sheet/ordersheet/ordersheets.do?method=showDetail" paramId="id" paramProperty="id"
        format="{0,date,yyyy-MM-dd HH:mm:ss}" 
        title="变更时间"/> 
     <display:column property="cancelTime" sortable="true" headerClass="sortable"
        url="/sheet/ordersheet/ordersheets.do?method=showDetail" paramId="id" paramProperty="id"
        format="{0,date,yyyy-MM-dd HH:mm:ss}" 
        title="撤销时间"/> 
        
    <display:setProperty name="paging.banner.item_name" value="ordersheet"/>
    <display:setProperty name="paging.banner.items_name" value="ordersheets"/>
        <display:setProperty name="export.rtf" value="false"/>
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
 
</display:table>
<tr class="buttonBar bottom">
 <input type="button" onclick="restore();" value="恢复"/>
</tr>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>


