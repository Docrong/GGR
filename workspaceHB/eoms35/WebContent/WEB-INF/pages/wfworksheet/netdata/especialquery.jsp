<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<table class="formTable">
			<!-- 所属地域 -->
            <tr>
	              <td class="label">
	                 	<bean:message bundle="sheet" key="query.status.area"/>
	              </td>
	              <td width="100%">
		               <input type="hidden" name="toDeptIdStringExpression" value="in"/>  
		               <input type="text" class="text"  readonly="readonly" name="showArea" id="showArea" beanId="tawSystemAreaDao"/>
		               <input type="hidden" name="main.toDeptId" id="toAreaId"/>
	              </td>
            </tr>
            <!-- 网络一级分类 -->
            <tr>	  
				  <td class="label">
				  		<bean:message bundle="sheet" key="query.status.mainNetTypeOne"/>
				  </td>
				  <td>
				  	 	<input type="hidden" name="main.mainNetTypeOne"/> 
				  	 	<eoms:comboBox name="mainNetTypeOneChoiceExpression" id="mainNetTypeOne" sub="mainNetTypeTwo" initDicId="1010104"/>
				  </td>	
			</tr>
			 <!-- 网络二级分类 -->
			<tr>			
				  <td class="label">
				  		<bean:message bundle="sheet" key="query.status.mainNetTypeTwo"/>
				  </td>
				  <td>
					  	<input type="hidden" name="main.mainNetTypeTwo"/>
					    <eoms:comboBox name="mainNetTypeTwoChoiceExpression" id="mainNetTypeTwo" sub="mainNetTypeThree" />
				  </td>		  
			</tr>
			<!-- 网络三级分类 -->
            <tr>
                <td  class="label">
                		<bean:message bundle="sheet" key="query.status.mainNetTypeThree"/>
                </td>
                <td>
	                   <input type="hidden" name="main.mainNetTypeThree"/>  
				       <eoms:comboBox name="mainNetTypeThreeChoiceExpression" id="mainNetTypeThree" />
	        	</td>  
            </tr>
</table>