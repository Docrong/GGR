<%@ include file="/common/taglibs.jsp"%>
<fmt:bundle basename="config/ApplicationResources-role">
<logic:present name="filter" scope="request">
  <logic:iterate id="roleFilter" name="filter" type="com.boco.eoms.commons.system.role.util.RoleFilter">
  	<div style="margin:10px 0"><label><bean:message key="${roleFilter.name}"/></label>
	  <div>
	  	<c:choose> 
          <c:when test="${roleFilter.multiple=='true'}">
          	<eoms:comboBox name="${roleFilter.businessName}" id="${roleFilter.businessName}" initDicId="${roleFilter.dictId}" 
	  	sub="${roleFilter.sub}" multiple="true" size="${roleFilter.size}" styleClass="select"/>
          </c:when>
          <c:otherwise>
          	<eoms:comboBox name="${roleFilter.businessName}" id="${roleFilter.businessName}" initDicId="${roleFilter.dictId}" 
	  	sub="${roleFilter.sub}" styleClass="select"/>
          </c:otherwise> 
        </c:choose>
	    <c:if test="${roleFilter.multiple=='true'}">
        </c:if>
  		
  	  </div>
	</div>
  </logic:iterate>
</logic:present>	  
</fmt:bundle>