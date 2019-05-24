<%@ include file="/common/extlibs.jsp"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
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
		alert("${eoms:a2u('未选择，请确认后引用')} ");
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
 	formObj.action = "nbproductss.do?method=xsearch&ifReference=true&back=true&deleted=1";
 	}else{
 	formObj.action = "nbproductss.do?method=xsearch&back=true&deleted=1";
 	}
 	formObj.submit();
 }
  function undelete() {
 
       document.location.href = "nbproductss.do?method=xquery&ifReference=${ifReference}";
 } 
function restore() {

 	var formObj = document.forms[0];
 	var id = document.getElementsByName("id");
 
 	for(var i=0;i<id.length;i++)
 	{
 	if(id[i].checked==true){	
 	var ids=id[i].checked;
    if (confirm("${eoms:a2u('确认恢复所选对象吗？')}")) {
 	formObj.action = "nbproductss.do?method=xrestore&ifReference=${ifReference}&nbproductsid="+id[i].value;
 	formObj.submit();
 	}
 	break;
 	}
 	} 
 	if(ids != true){
 	alert("${eoms:a2u('请选择要恢复的对象')}");
 	}
 }

</script>
<html:form action="nbproductss.do?method=xsearch" method="post" styleId="nbproductsForm">
<table height="35" width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td nowrap><content tag="heading">${eoms:a2u('已删除产品目录库')}</content></td>
<td nowrap><content tag="heading"><a href="#"  onclick="undelete()">${eoms:a2u('进入新业务产品目录库')}</a></content></td>
<td width="380"></td>
<td nowrap width="100" align="middle" style="vertical-align: middle">${eoms:a2u('请输入产品关键字')}</td>
<td width="130" align="left">
  <input type="text" id="txtwords" name="txtwords" class="headinpout2" maxlength="20" onkeydown=""/>
  <input type="hidden" name="${sheetPageName}txtwords" id="${sheetPageName}txtwords" value="${sheetPageName}txtwords" />
  <input type="hidden" name="ifReference" id="ifReference" value="${ifReference}" />
</td>

<td>
  <input id="btnSearch" type="button" align="right" name= "button" onclick="queryDeleted()" value="<fmt:message key="button.query"/>"/>
</td>
</tr>
</table>
    
<bean:define id="url" value="nbproductss.do" />
<display:table name="nbproductsList" cellspacing="0" cellpadding="0"
    id="nbproducts" pagesize="15" class="table nbproductsList"
     export="true" requestURI="/sheet/nBProducts/nbproductss.do?method=showDeletedDetail&ifReference=${ifReference}" sort="list" >

    <display:column media="html">
            <input type="radio" name="id" value="${nbproducts.id}"/>
			<input type="hidden" name="procode" value="${nbproducts.procode}"/>
			<input type="hidden" name="nbPName" value="${nbproducts.nbPName}"/>
    </display:column>

    <display:column property="procode" sortable="true" headerClass="sortable"
        url="/sheet/nBProducts/nbproductss.do?method=showDeletedDetail&flag=showDetail&ifReference=${ifReference}" paramId="id" paramProperty="id"
         title="${eoms:a2u('产品编号')}"/>

    <display:column property="nbPName" sortable="true" headerClass="sortable"
        url="/sheet/nBProducts/nbproductss.do?method=showDeletedDetail" paramId="id" paramProperty="id"
         title="${eoms:a2u('产品名称')}"/>
         
    <display:column property="businessSort" sortable="true" headerClass="sortable"
        url="/sheet/nBProducts/nbproductss.do?method=showDeletedDetail" paramId="id" paramProperty="id"
         title="${eoms:a2u('业务分类')}"/>
         
    <display:column property="keyword" sortable="true" headerClass="sortable"
        url="/sheet/nBProducts/nbproductss.do?method=showDeletedDetail" paramId="id" paramProperty="id"
         title="${eoms:a2u('产品关键字')}"/>

    <display:column sortable="true" headerClass="sortable"
         title="${eoms:a2u('记录人')}">
         <eoms:id2nameDB id="${nbproducts.recorder}" beanId="tawSystemUserDao" />
    </display:column>
      
    <display:column property="recordTime" sortable="true" headerClass="sortable"
        url="/sheet/nBProducts/nbproductss.do?method=showDeletedDetail" paramId="id" paramProperty="id"
        format="{0,date,yyyy-MM-dd HH:mm:ss}" 
         title="${eoms:a2u('记录时间')}"/> 
    <display:setProperty name="paging.banner.item_name" value="nbproducts"/>
    <display:setProperty name="paging.banner.items_name" value="nbproductss"/>
        <display:setProperty name="export.rtf" value="false"/>
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
 
</display:table>
<tr class="buttonBar bottom">
 <input type="button" onclick="restore();" value="${eoms:a2u('恢复')}"/>
</tr>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>


