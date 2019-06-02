<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<!-- 返回控制 -->
<% String back = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("back")); %>
<logic:notPresent name="back" scope="request">
     <% if(!back.equals("")){
           request.setAttribute("back",back);
           }
      %>
</logic:notPresent>
<caption><div class="header center">传输专线列表</div></caption>
<html:form action="transferspeciallines.do?method=xsearch" method="post" styleId="transferspeciallineForm">
<bean:define id="url" value="transferspeciallines.do" />
<display:table name="transferspeciallineList" cellspacing="0" cellpadding="0"
    id="transferspecialline" pagesize="15" requestURI="transferspeciallines.do?method=xquery" class="table transferspeciallineList"
     export="false" sort="list" > 
     	<display:column property="cityA" headerClass="sortable" title="城市A"  url="/businessupport/product/transferspeciallines.do?method=showDetail&flag=showDetail&back=true" paramId="id" paramProperty="id"/>
		<display:column property="cityZ" headerClass="sortable" title="城市Z" />
        <display:column property="bandwidth" headerClass="sortable" title="带宽"/>
		<display:column property="amount" headerClass="sortable" title="数量" />
		
		<display:column property="portA" headerClass="sortable" title="端点A" />
		<display:column  sortable="true" headerClass="sortable"  title="端点A接口类型">  
           <eoms:id2nameDB id="${transferspecialline.portAInterfaceType}" beanId="ItawSystemDictTypeDao" />
        </display:column> 
		<display:column property="portADetailAdd" headerClass="sortable" title="端点A详细地址" />
		<display:column property="portABDeviceName" headerClass="sortable" title="端点A业务设备名称" />
		<display:column property="portABDevicePort" headerClass="sortable" title="端点A业务设备端口" />
		<display:column property="portAContactPhone" headerClass="sortable" title="端点A联系人及电话" />

        <display:column property="portZ" headerClass="sortable" title="端点Z" />
		<display:column  sortable="true" headerClass="sortable"  title="端点Z接口类型">  
           <eoms:id2nameDB id="${transferspecialline.portZInterfaceType}" beanId="ItawSystemDictTypeDao" />
        </display:column>
		<display:column property="portZDetailAdd" headerClass="sortable" title="端点Z详细地址" />
		<display:column property="portZBDeviceName" headerClass="sortable" title="端点Z业务设备名称" />
		<display:column property="portZBDevicePort" headerClass="sortable" title="端点Z业务设备端口" />
		<display:column property="portZContactPhone" headerClass="sortable" title="端点Z联系人及电话" />

        <display:column  sortable="true" headerClass="sortable"  title="是否预占">  
           <eoms:id2nameDB id="${transferspecialline.isBeforehandOccupy}" beanId="ItawSystemDictTypeDao" />
        </display:column>
</display:table>
<tr class="buttonBar bottom">
     <input type="button"  styleClass="button"
            onclick="location.href='<c:url value="/businessupport/product/transferspeciallines.do?method=xedit&type=add"/>'" 
            value="添加"/>
  <logic:present name="back" scope="request">
     <input type="button"  styleClass="button"
            onclick="location.href='<c:url value="/businessupport/product/transferspeciallines.do?method=xquery"/>'" 
            value="返回"/>
  </logic:present>

</tr>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>


