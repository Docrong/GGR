<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
    <html:form action="/smsApplys.do?method=xinitApply" method="post" styleId="smsApplyForm"> 
    	<table>
    		<tr>
    			<td>
    				<bean:message key='smsApply.servicelist'/>
    			</td>
    			<td>
	    			<html:select property="serviceId" >
		    				<html:options collection="applys" property="serviceId" labelProperty="name" />
	    			</html:select>    				
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    					<logic:notEmpty name="applys">
    						<input type="submit" value='<bean:message key='smsApply.diyservice'/>'>
    					</logic:notEmpty>
    					
    					<logic:empty name="applys">
    						<input type="submit" disabled="disabled" value='<bean:message key='smsApply.diyservice'/>'>
    					</logic:empty>
    				
    			</td>
    		</tr>
    	</table>    	
    </html:form>
<%@ include file="/common/footer_eoms.jsp"%>