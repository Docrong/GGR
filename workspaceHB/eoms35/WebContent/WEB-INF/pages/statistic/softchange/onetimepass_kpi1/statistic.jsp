<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%@page import="com.boco.eoms.commons.statistic.base.config.excel.* ,java.util.*"%>
<%
	Excel excelConfig =(Excel) request.getAttribute("excelConfig");
	if (excelConfig == null) throw new Exception("读取配置统计文件失败!");
	
	String excelConfigURL = String.valueOf(request.getAttribute("excelConfigURL"));
	String findListForward = String.valueOf(request.getAttribute("findListForward"));
	Calendar cld = Calendar.getInstance();
	int year = cld.get(Calendar.YEAR);
	int mondth = cld.get(Calendar.MONTH) + 1;
%>
	
<html:form action="/stat.do?method=performStatistic" styleId="theform">


 <table class="formTable">
   <caption>${eoms:a2u("输入条件")}</caption>   
   <tr>
   		<td>
			<input type="hidden" name="excelConfigURL" value="<%=excelConfigURL %>">  
			<input type="hidden" name="findListForward" value="<%=findListForward %>"> 
			<input type="hidden" id="reportIndex" name ="reportIndex" value="0">		
			<input type="hidden" name="type" value="season">		
   		</td> 
   </tr>   
            <tr>
              <td noWrap class="label">
              <!-- <bean:message bundle="statistic" key="statistic.sendtime" /> -->
              ${eoms:a2u("派单季度")}
              </td>
              
              <td width="80%">
              	${eoms:a2u("按季度统计")}
              	<select name="beginyear">
	              	<%
	              		for(int i=2001; i<= year+1;i++)
	              		{
	              			String select = "";
	              			if(year == i)
	              			{
	              				select = "Selected";
	              			}
	              	 %>
	              		<option value="<%=i%>" <%=select %>><%= i%></option>
	              	<%}%>
	              </select>
	            ${eoms:a2u("年")}
             	<select name="seasonselect">
              		<option value="season_one">${eoms:a2u("第一季度")}</option>
              		<option value="season_two">${eoms:a2u("第二季度")}</option>
              		<option value="season_three">${eoms:a2u("第三季度")}</option>
              		<option value="season_four">${eoms:a2u("第四季度")}</option>
              	</select>
              </td>
            </tr>
            
			<tr>
				<td>
					<input id="customDescribe" type="hidden" name="customDescribe" >
				</td>
			</tr>
          </table>

  <br/>	
  <!-- buttons -->

     <html:submit styleClass="btn" property="method.save" styleId="method.save">
				<bean:message bundle="statistic" key="button.done"/>
     </html:submit>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
