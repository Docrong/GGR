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
				  		<bean:message bundle="accountpopedomapply" key="accountpopedomapply.netType1"/>
				  </td>
				  <td>
				  	 	<input type="hidden" name="main.netType1"/> 
				  	 	<eoms:comboBox name="netType1ChoiceExpression" id="netType1" sub="netType2" initDicId="1010104"/>
				  </td>	
			</tr>
			 <!-- 网络二级分类 -->
			<tr>			
				  <td class="label">
				  		<bean:message bundle="accountpopedomapply" key="accountpopedomapply.netType2"/>
				  </td>
				  <td>
					  	<input type="hidden" name="main.netType2"/>
					    <eoms:comboBox name="netType2ChoiceExpression" id="netType2" sub="netType3" />
				  </td>		  
			</tr>
			<!-- 网络三级分类 -->
            <tr>
                <td  class="label">
                		<bean:message bundle="accountpopedomapply" key="accountpopedomapply.netType3"/>
                </td>
                <td>
	                   <input type="hidden" name="main.netType3"/>  
				       <eoms:comboBox name="netType3ChoiceExpression" id="netType3" />
	        	</td>  
            </tr>
</table>