
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.TawClassMsg,com.boco.eoms.common.util.StaticMethod"%>
<html>
<head>
<title>
<bean:message key="order.query"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<br>
<form action="../query/orderview.do" method="post" name="tt">
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b><bean:message key="order.query"/></b>
      </td>
    </tr>
</table>
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
     <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="storage.name"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                           <select name="storage" size="1"  style="width: 4.4cm;">
                             <option value=""><bean:message key="label.selall"/></option>
                             <logic:iterate id="storage" name="storage" type="com.boco.eoms.sparepart.model.TawStorage">
                             <option value="<bean:write name="storage" property="id" scope="page"/>"><bean:write name="storage" property="storagename" scope="page"/></option>
                             </logic:iterate>
                          </select>
                  </td>
     </tr>
     <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="order.state"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                     <select name="state" size="1" style="width: 4.4cm;">
                     <option value=""><bean:message key="label.selall"/></option>
           <%--          <option value="0">费单</option>--%>
                         <option value="1">${eoms:a2u('：')}待审核</option>
                         <option value="2">${eoms:a2u('：')}审核通过</option>
						 <option value="3">${eoms:a2u('：')}待审批</option>
						 <option value="4">${eoms:a2u('：')}驳回</option>
                     </select>
                  </td>
     </tr>
     <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="order.type"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                     <select name="type" size="1"  style="width: 4.4cm;">
                       <option value=""><bean:message key="label.selall"/></option>

                           <option value="1">${eoms:a2u('新件入库')}</option>         
                           <option value="2">${eoms:a2u('维修入库')}</option>           
                           <option value="3">${eoms:a2u('其他入库')}</option>           
                           <option value="7">${eoms:a2u('维护出库')}</option>
                           <option value="8">${eoms:a2u('维修出库')}</option>
                           <option value="9">${eoms:a2u('扩容出库')}</option>            
                           <option value="10">${eoms:a2u('报废出库')}</option>           
                           <option value="11">${eoms:a2u('其他出库')}</option>	
						   <option value="35">${eoms:a2u('借用出库')}</option>
						   <option value="36">${eoms:a2u('检测出库单')}</option>
						   <option value="31">${eoms:a2u('仪器仪表新件入库')}</option>
						   <option value="32">${eoms:a2u('仪器仪表归还入库')}</option>													
                     </select>
                  </td>
     </tr>
      <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="order.operater"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="22"  maxlength="50" name="operator">
                  </td>
     </tr>
     <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="order.proposer"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" name="proposer" size="22"  maxlength="255" >
                  </td>
     </tr>
     <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.timein"/>${eoms:a2u('：')}</p>
                  </td>
                   <td width="70%" height="25" colspan="2">
                    <app:SelectDate name="timein" formName="tt" value="<%=StaticMethod.getLocalString() %>"/>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.timeend"/>${eoms:a2u('：')}</p>
                  </td>
                   <td width="70%" height="25" colspan="2">
                    <app:SelectDate name="timeend" formName="tt" value="<%=StaticMethod.getLocalString() %>"/>
                  </td>
    </tr>
</table>
<table border="0" width="75%" cellspacing="0">
    <tr>
     <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.query"/>" name="order" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
  </table>
</form>
</div>
</body>
</html>
