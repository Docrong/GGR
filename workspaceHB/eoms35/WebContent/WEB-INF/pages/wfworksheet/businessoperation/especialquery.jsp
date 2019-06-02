<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.boco.eoms.sheet.base.util.SheetAttributes" %>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<% 

SheetAttributes sheetAttributes=(SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes");


%>
<table class="formTable">
<tr> 
	              <td class="label">
	                 	<bean:message bundle="businessoperation" key="businessoperation.mainProductCode"/>
	              </td>
	              <td >
                    <input type="hidden" name="mainProductCodeStringExpression" value="like"/>
                     <input type="hidden" name="main.mainProductCode" id="mainProductCode" size="30" class="text" value=""/> 
                     
                     <input type="text" name="mainProductCode1" id="mainProductCode1" style="width:60px" size="30" maxlength="4" class="text" value="<%=sheetAttributes.getRegionId()%>"/>
                     -p-
                     <input type="text" name="mainProductCode2" id="mainProductCode2" style="width:60px" size="30" maxlength="4"  class="text"/>
                     -
                     <input type="text" name="mainProductCode3" id="mainProductCode3" style="width:60px" size="30" maxlength="4" class="text"/>   
                     
                     

	              </td>

            </tr>
</table>