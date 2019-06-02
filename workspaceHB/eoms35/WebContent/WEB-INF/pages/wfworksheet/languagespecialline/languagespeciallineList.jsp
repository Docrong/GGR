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
function query() {
 	var formObj = document.forms[0];
 
 	if('${ifReference}'!=null&&'${ifReference}'!='')
 	{
 	formObj.action = "LanguageSpecialLines.do?method=xsearch&ifReference=true&back=true&deleted=0";
 	}else{
 	formObj.action = "LanguageSpecialLines.do?method=xsearch&back=true&deleted=0";
 	}
 	formObj.submit();
 }
 function deleted() { 
       document.location.href = "LanguageSpecialLines.do?method=xqueryDeleted&ifReference=${ifReference}";
 }
function onkeydownEvt(evt) { 
	evt=evt?evt:(window.event?window.event:null); 
      if(evt.keyCode==13) {  
		query(); 
		return false; 
	} 
} 
</script>

<caption><div class="header center">服务存量信息</div></caption>
<html:form action="LanguageSpecialLines.do?method=xsearch" method="post" styleId="LanguageSpecialLineForm">
<bean:define id="url" value="LanguageSpecialLines.do" />
<display:table name="LanguageSpecialLineList" cellspacing="0" cellpadding="0"
    id="LanguageSpecialLine" pagesize="15" class="table LanguageSpecialLineList"
     export="true" requestURI="/businessupport/order/LanguageSpecialLines.do?method=showDetail" sort="list" > 
    <display:column property="groupCustomIP" headerClass="sortable" title="集团用户对端Ip" />
		<display:column property="radiusAddress" headerClass="sortable" title="radius地址" />
		<display:column property="groupCustomIPDetail" headerClass="sortable" title="集团用户IP地址端需求" />
		<display:column property="apnName" headerClass="sortable" title="APN名称" />

    <display:setProperty name="paging.banner.item_name" value="LanguageSpecialLine"/>
    <display:setProperty name="paging.banner.items_name" value="LanguageSpecialLines"/>
        <display:setProperty name="export.rtf" value="false"/>
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
 
</display:table>
<tr class="buttonBar bottom">
     <input type="button"  styleClass="button"
            onclick="location.href='<c:url value="/businessupport/product/LanguageSpecialLines.do?method=xedit&type=add&ifReference=${ifReference}"/>'" 
            value="添加"/>
  <logic:present name="back" scope="request">
     <input type="button"  styleClass="button"
            onclick="location.href='<c:url value="/businessupport/product/LanguageSpecialLines.do?method=xquery&ifReference=${ifReference}"/>'" 
            value="返回"/>
  </logic:present>
</tr>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>


