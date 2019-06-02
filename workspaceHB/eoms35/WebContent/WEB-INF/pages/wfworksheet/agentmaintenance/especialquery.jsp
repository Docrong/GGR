<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<table class="formTable">
            <!-- 网络一级分类 -->
            <tr>	  
				  <td class="label">
				  		<bean:message bundle="sheet" key="query.status.mainNetTypeOne"/>
				  </td>
				  <td>
				  	 	<input type="hidden" name="main.mainNetSort1"/> 
				  	 	<eoms:comboBox name="mainNetSort1ChoiceExpression" id="mainNetSort1" sub="mainNetSort2" initDicId="1010104"/>
				  </td>	
			</tr>
			 <!-- 网络二级分类 -->
			<tr>			
				  <td class="label">
				  		<bean:message bundle="sheet" key="query.status.mainNetTypeTwo"/>
				  </td>
				  <td>
					  	<input type="hidden" name="main.mainNetSort2"/>
					    <eoms:comboBox name="mainNetSort2ChoiceExpression" id="mainNetSort2" sub="mainNetSort3" />
				  </td>		  
			</tr>
			<!-- 网络三级分类 -->
            <tr>
                <td  class="label">
                		<bean:message bundle="sheet" key="query.status.mainNetTypeThree"/>
                </td>
                <td>
	                   <input type="hidden" name="main.mainNetSort3"/>  
				       <eoms:comboBox name="mainNetSort3ChoiceExpression" id="mainNetSort3" />
	        	</td>  
            </tr>
			<!-- 工单类型 -->
            <tr>
                <td  class="label">
                		<bean:message bundle="sheet" key="mainForm.parentSheetName"/>
                </td>
                <td>
                	<input type="hidden" name="main.parentSheetName"/>
                  <select name="parentSheetNameChoiceExpression" >
                  	  <option value=""><bean:message bundle="sheet" key="query.status.pleaseSelect"/></option>
                      <option value="iCommonFaultMainManager">故障工单</option> 
                      <option value="iComplaintMainManager">投诉工单</option> 
                      <option value="iCommonTaskMainManager">通用任务工单</option>  
   	             </select> 
	        	</td>  
            </tr>
</table>