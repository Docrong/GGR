<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<style>
#tabs {
	width:90%;
}
#tabs .x-tabs-item-body {
	display:none;
	padding:10px;
}
</style>
<script type="text/javascript">
var Tabs = {
    init : function(){
        var tabs = new Ext.TabPanel('tabs');
        tabs.addTab('form','已删除用户查询');
        tabs.addTab('info', '说明帮助');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>

<div id="tabs">
<div id="form" class="tab-content">
	
	
	
<fmt:bundle  basename="config/ApplicationResources">	
  <html:form action="tawSystemUsers.do?method=xsearchdel" method="post"
	   styleId="TawSystemUserForm">
	
	     <fmt:message key="tawSystemUserForm.username"/><fmt:message key="button.query" />:<input type="text" name="username" id="username" class="text">
	        <html:submit styleClass="button" property="method.save">
						<fmt:message key="button.query" />
				 </html:submit>
	</html:form>




<display:table name="tawSystemUserList" cellspacing="0" cellpadding="0"
    id="tawSystemUserList" pagesize="25" class="table tawSystemUserList"
    export="false" requestURI="" sort="list"
    decorator="com.boco.eoms.commons.system.user.util.UserDisplaytagDecorator">
    	
    
           <display:column  titleKey="button.comeback">
						<html:link
							href="${app}/sysuser/tawSystemUsers.do?method=resume"
							paramId="id" paramProperty="id" paramName="tawSystemUserList">
							<fmt:message key="button.comeback" />
						</html:link>
					</display:column>
    
	 <display:column property="username" sortable="true" headerClass="sortable"
       
         titleKey="tawSystemUserForm.username"/>
 
   <display:column property="deptname" sortable="true" headerClass="sortable"
       
         titleKey="tawSystemUserForm.deptname"/>

    
    <display:column property="cptroomname" sortable="true" headerClass="sortable"
        
         titleKey="tawSystemUserForm.cptroomname"/>

   
    <display:column property="email" sortable="true" headerClass="sortable"
       
         titleKey="tawSystemUserForm.email"/>

    <display:column property="familyaddress" sortable="true" headerClass="sortable"
        
         titleKey="tawSystemUserForm.familyaddress"/>

    <display:column property="fax" sortable="true" headerClass="sortable"
       
         titleKey="tawSystemUserForm.fax"/>

    <display:column property="mobile" sortable="true" headerClass="sortable"
       
         titleKey="tawSystemUserForm.mobile"/>
         
    <display:column property="phone" sortable="true" headerClass="sortable"
        
         titleKey="tawSystemUserForm.phone"/>
         
    <display:column property="operuserid" sortable="true" headerClass="sortable"
       
         titleKey="tawSystemUserForm.operuserid"/>

    <display:column property="sex" sortable="true" headerClass="sortable"
        
         titleKey="tawSystemUserForm.sex"/>

    <display:column property="savetime" sortable="true" headerClass="sortable"
      
         titleKey="tawSystemUserForm.savetime" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column property="updatetime" sortable="true" headerClass="sortable"
        
         titleKey="tawSystemUserForm.updatetime"/>
         
   <display:column property="operremotip" sortable="true" headerClass="sortable"
       
         titleKey="tawSystemUserForm.operremotip"/>
    <display:column property="remark" sortable="true" headerClass="sortable"
       
         titleKey="tawSystemUserForm.remark"/>
         
  
    <display:setProperty name="paging.banner.item_name" value="tawSystemUser"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemUsers"/>
</display:table>
</div>


<div id="info">
  	<dl>
  		<dt>功能说明</dt>
        <dd>对已经删除用户查询，并可以恢复到未删除状态，并且保留其权限</dd>
		
    </dl>
  </div>
</div>


</fmt:bundle>

<c:out value="${buttons}" escapeXml="false"/>



<%@ include file="/common/footer_eoms.jsp"%>