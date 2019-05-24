<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<table class="formTable">
		   <!-- 集团编号-->
           <tr>
              <td class="label">
                 <bean:message bundle="businessdredge" key="businessdredge.customNo" />
              </td>
              <td>
	               <input type="hidden" name="customNoStringExpression" value="like"/>
                   <input type="text" name="main.customNo" id="title" size="30" class="text"/>             
              </td>
            </tr>
		   <!-- 集团名称-->
           <tr>
              <td class="label">
                 <bean:message bundle="businessdredge" key="businessdredge.customName" />
              </td>
              <td>
	               <input type="hidden" name="customNameStringExpression" value="like"/>
                   <input type="text" name="main.customName" id="title" size="30" class="text"/>             
              </td>
            </tr>            
</table>