<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/sheet/datum/bureaudataHlr.do?method=addinit"/>'"
        value="<fmt:message key="button.add"/>"/>
</c:set>


<display:table name="bureaudataHlrList" cellspacing="0" cellpadding="0"
    id="bureaudataHlrList" pagesize="10" class="table bureaudataHlrList"
    export="true" requestURI="" sort="list" decorator="com.boco.eoms.datum.webapp.action.BureaudataHlrListDisplaytagDecoratorHelper">
   <display:column  url="/sheet/datum/bureaudataHlr.do?method=editinit"  
   paramId="id" paramProperty="id" sortable="false" headerClass="sortable"
         title="${eoms:a2u('HLR名称')}">
   	<bean:write property="hlrname" name="bureaudataHlrList"/>
       
	</display:column>
   <display:column property="hlrsignalid" sortable="false" headerClass="sortable"
         title="${eoms:a2u('HLR信令点')}"/>
         
   <display:column property="hlrid" sortable="false" headerClass="sortable"
         title="${eoms:a2u('HLR ID ')}"/>

   <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
	<display:setProperty name="export.csv" value="false"/>
  </display:table>

 
<c:out value="${buttons}" escapeXml="false"/>
<%@ include file="/common/footer_eoms.jsp"%>