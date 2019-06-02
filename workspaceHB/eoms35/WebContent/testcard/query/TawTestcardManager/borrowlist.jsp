<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<html> 
<%
String condition="";
%>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">
<div align="center">
<center>
<table border="0" width="95%" cellspacing="1" class="formTable">
	<tr>
		<td width="100%" colspan="14" bgcolor="#E5EDF8" align="center" height="25">
 		<b align="center" class="table_title">${eoms:a2u("请选择要查询的测试卡 列表")}&nbsp;&nbsp;<b>
 		<%--<bean:message key="label.list"/>
		--%></td>
	</tr>
	<tr bgcolor="#FFFFFF">
    		<td width="100%" colspan="14" height="25" bgcolor="#EEECED" align="center">
      			<bean:write name="pagerHeader" scope="request" filter="false"/>
      			<%! String key;%>
    		</td>
        </tr>
	<tr bgcolor="#FFFFFF"><b>
       		<td nowrap class="label" align="center" height="25">
          		${eoms:a2u("卡号(iccid)")}
                </td>
        	<td nowrap class="label"  align="center" height="25">
           		${eoms:a2u("msisdn")}
        	</td>
          <td nowrap class="label"  align="center" height="25">
          		${eoms:a2u("经手人")}
        	</td>
        	<td nowrap class="label"  align="center" height="25">
          		${eoms:a2u("借用部门")}
        	</td>
        	<td nowrap class="label"  align="center" height="25">
          		${eoms:a2u("借用人")}
        	</td>
       		<td nowrap class="label"  align="center" height="25">
          		${eoms:a2u("借用时间")}
        	</td>
        	<td nowrap class="label"  align="center" height="25">
          		${eoms:a2u("应归还时间")}
       		</td>
<!--        	<td nowrap bgcolor="#E5EDF8" align="center" height="25">
           		${eoms:a2u("归还时间")}
        	</td>-->
          <td nowrap class="label"  align="center" height="25">
           ${eoms:a2u("状态")}
        </td>
        	<td nowrap class="label" align="center" height="25">
           		${eoms:a2u("备注")}
        	</td>
        	<td nowrap class="label"  align="center" height="25">
          		${eoms:a2u("显示")}
        	</td>
<!--         	<td nowrap bgcolor="#E5EDF8" align="center" height="25">
          		${eoms:a2u("续借")}
        	</td>  -->
	</tr>
   	<logic:iterate id="tawTestcardManager" name="tawTestcardManager" type="com.boco.eoms.testcard.model.TawTestcardManager">
		<tr bgcolor="#FFFFFF">
     			<td nowrap  " align="center" >
                    		<bean:write name="tawTestcardManager" property="cardid" scope="page"/>
    			</td>
    			<td nowrap  align="center" >
                    		<bean:write name="tawTestcardManager" property="msisdn" scope="page"/>
    			</td>
          <td nowrap   align="center" >
                    		<bean:write name="tawTestcardManager" property="dealer" scope="page"/>
    			</td>
    			<td nowrap   align="center">
                    		<bean:write name="tawTestcardManager" property="lenddept" scope="page"/>
    			</td>
    			<td nowrap   align="center" >
                    		<bean:write name="tawTestcardManager" property="lender" scope="page"/>
    			</td>
     			<td nowrap   align="center" >
                    		<bean:write name="tawTestcardManager" property="leantime" scope="page"/>
    			</td>
     			<td nowrap   align="center" >
         			<bean:write name="tawTestcardManager" property="belongtime" scope="page"/>

    			</td>
<!--     			<td nowrap bgcolor="#E5EDF8" align="center" >
         			<bean:write name="tawTestcardManager" property="returntime" scope="page"/>
    			</td>-->
          <td nowrap   align="center" >
         <bean:write name="tawTestcardManager" property="state" scope="page"/>

          </td>
     			<td nowrap   align="center" >
         			<bean:write name="tawTestcardManager" property="reason" scope="page"/>
    			</td>

    			<td nowrap   align="center" > <html:link href="../TawTestcardManager/returnborrownote.do" paramId="id" paramName="tawTestcardManager" paramProperty="id">	${eoms:a2u("显示")}</html:link></td>

<%--<!--           <td nowrap bgcolor="#E5EDF8" align="center" >
           <%if(!("使用").equals(tawTestcardManager.getState())){%>
            <html:link href="../TawTestcardManager/renew.do" paramId="id" paramName="tawTestcardManager" paramProperty="id">续借</html:link>
          <%}%>
          </td>  -->--%>
        </tr>
    	</logic:iterate>
      </table>
      </body>
</div>
</html>


