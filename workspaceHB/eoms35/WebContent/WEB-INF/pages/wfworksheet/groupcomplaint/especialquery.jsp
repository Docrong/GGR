<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<table class="formTable">
            <!-- 投诉一级分类 -->
            <tr>	  
				  <td class="label">
				  		<bean:message bundle="group" key="groupcomplaint.complaintType1"/>
				  </td>
				  <td>
				  	 	<input type="hidden" name="main.complaintType1"/> 
				  	 	<eoms:comboBox name="complaintType1ChoiceExpression" id="complaintType1" sub="complaintType2" initDicId="1010601"/>
				  </td>	
			</tr> 
            <!-- 投诉二级分类 -->
            <tr>	  
				  <td class="label">
				  		<bean:message bundle="group" key="groupcomplaint.complaintType2"/>
				  </td>
				  <td>
				  	 	<input type="hidden" name="main.complaintType2"/> 
				  	 	<eoms:comboBox name="complaintType2ChoiceExpression" id="complaintType2" sub="complaintType"/>
				  </td>	
			</tr> 			
            <!-- 投诉三级分类 -->
            <tr>	  
				  <td class="label">
				  		<bean:message bundle="group" key="groupcomplaint.complaintType"/>
				  </td>
				  <td>
				  	 	<input type="hidden" name="main.complaintType"/> 
				  	 	<eoms:comboBox name="complaintTypeChoiceExpression" id="complaintType" sub="complaintType4"/>
				  </td>	
			</tr>  
            <!-- 投诉四级分类 -->
            <tr>	  
				  <td class="label">
				  		<bean:message bundle="group" key="groupcomplaint.complaintType4"/>
				  </td>
				  <td>
				  	 	<input type="hidden" name="main.complaintType4"/> 
				  	 	<eoms:comboBox name="complaintType4ChoiceExpression" id="complaintType4" sub="complaintType5"/>
				  </td>	
			</tr> 
            <!-- 投诉五级分类 -->
            <tr>	  
				  <td class="label">
				  		<bean:message bundle="group" key="groupcomplaint.complaintType5"/>
				  </td>
				  <td>
				  	 	<input type="hidden" name="main.complaintType5"/> 
				  	 	<eoms:comboBox name="complaintType5ChoiceExpression" id="complaintType5" sub="complaintType6"/>
				  </td>	
			</tr> 
            <!-- 投诉六级分类 -->
            <tr>	  
				  <td class="label">
				  		<bean:message bundle="group" key="groupcomplaint.complaintType6"/>
				  </td>
				  <td>
				  	 	<input type="hidden" name="main.complaintType6"/> 
				  	 	<eoms:comboBox name="complaintType6ChoiceExpression" id="complaintType6" sub="complaintType7"/>
				  </td>	
			</tr> 
            <!-- 投诉七级分类 -->
            <tr>	  
				  <td class="label">
				  		<bean:message bundle="group" key="groupcomplaint.complaintType7"/>
				  </td>
				  <td>
				  	 	<input type="hidden" name="main.complaintType7"/> 
				  	 	<eoms:comboBox name="complaintType7ChoiceExpression" id="complaintType7"/>
				  </td>	
			</tr> 
</table>