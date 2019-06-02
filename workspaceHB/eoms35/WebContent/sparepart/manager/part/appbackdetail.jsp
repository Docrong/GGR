<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.*,com.boco.eoms.common.util.StaticMethod"%>
<html>
<%
  TawPart part=(TawPart)request.getAttribute("sparepart");
  TawOrder order=(TawOrder)request.getAttribute("order");
  String orderpart_id=(String)request.getAttribute("orderpart_id");
  System.out.println(part.getId());
  System.out.println(order.getId());
  System.out.println(orderpart_id);
  request.getSession().setAttribute("sparepart_id", part.getId());
  request.getSession().setAttribute("order_id", order.getId());
  request.getSession().setAttribute("orderpart_id", orderpart_id);
%>
<head>
<title>
${eoms:a2u('备件详细信息')}
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<br>
<table border="0" width="80%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u('备件详细信息')}</b>
      </td>
    </tr>
</table>
<form action="../part/appbackcheck.do" method="post" name="tawSparepartForm">
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
		<tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('分公司：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getDeptName()%>" readonly="readonly"/>
                  </td>
     
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('仓库：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getStorage()%>" readonly="readonly"/>
                  </td>
   
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.department"/>${eoms:a2u('：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getNettype()%>" readonly="readonly"/>
                  </td>
   </tr>

		<tr class="tr_show">  
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.necode"/>${eoms:a2u('：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getNecode()%>" readonly="readonly"/>
                  </td>
   
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('备件类型：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getObjecttype()%>" readonly="readonly"/>
                  </td>
    
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('备件名称：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getObjectname()%>" readonly="readonly"/>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.supplier"/>${eoms:a2u('：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getSupplier()%>" readonly="readonly"/>
                  </td>
     
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('备件版本号：')}</td>
                  <td   height="25">
                    <input type="text" name="productcode" value="<%=part.getProductcode()%>" readonly="readonly">
                  </td>
  
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('实物编码：')}</p>
                  </td>
                  <td   height="25">
                    <input type="text" readonly="readonly" value="<%=part.getManagecode()%>" name="managecode">
					</td>
      </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="sparepart.serialno"/>${eoms:a2u('：')}</td>
                  <td   height="25">
                    <input type="text" name="serialno" value="<%=part.getSerialno()%>" readonly="readonly">
                  </td>
    
                  <td class="clsfth" width="30%" height="25"><bean:message key="sparepart.operator"/>${eoms:a2u('：')}</td>
                  <td   height="25">
                    <input readonly="readonly" value="<%=part.getOperator()%>">
                  </td>
  
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('状态：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getState()%>" readonly="readonly"/>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('备件性能：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getProform()%>" readonly="readonly"/>
                  </td>
     
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('是否保修：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getWarrantyName()%>" readonly="readonly"/>
                  </td>
   
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('是否停产：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getStopproductName()%>" readonly="readonly"/>
                  </td>
     </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('所属工程/合同：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getContract()%>" readonly="readonly"/>
                  </td>
  
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('订购金额：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getMoney()%>" readonly="readonly"/>
                  </td>
   
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('位置：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getPosition()%>" readonly="readonly"/>
                  </td>
    </tr>
    
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.note"/>：</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getNote()%>" readonly="readonly"/>
                  </td>
				  <td  class="clsfth" height="25"><p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('入库时间：')}</p>
				  </td>
				  <td  class="clsfth" height="25"> <input value="<%=StaticMethod.null2String(part.getIntime())%>" readonly="readonly"/>
				  </td>
				    <td   height="25"><p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('出库时间：')}</p>
				  </td>
				  <td   height="25"> <input value="<%=StaticMethod.null2String(part.getEtime())%>" readonly="readonly"/>
				  </td>
    </tr>
    </table>
	 <HR>
	<table border="0" width="80%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u('工单信息')}</b>
      </td>
    </tr>
	</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
   <tr class="td_label" align="center">
     <td nowrap="nowrap">${eoms:a2u('申请人')}</td>
     <td nowrap="nowrap"><bean:message key="storage"/></td>
     <td nowrap="nowrap">${eoms:a2u('申请时间')}</td>
     <td nowrap="nowrap">${eoms:a2u('工单编号')}</td>
     <td nowrap="nowrap">${eoms:a2u('工单类型')}</td>
     <td nowrap="nowrap">${eoms:a2u('工单状态')}</td>
     <td nowrap="nowrap">${eoms:a2u('原因说明')}</td>
     <td nowrap="nowrap">${eoms:a2u('备件名称')}</td>
     <td nowrap="nowrap">${eoms:a2u('备件序列号')}</td>
	  <td nowrap="nowrap">${eoms:a2u('备件厂商')}</td>
    </tr>
	 <tr class="td_label" align="center">
       <td nowrap="nowrap"><%=order.getProposer()%></td>
       <td nowrap="nowrap"><%=order.getStoragename()%></td>
       <td nowrap="nowrap"><%=order.getStartdate()%></td>
       <td nowrap="nowrap"><%=order.getSheetid()%></td>
       <td nowrap="nowrap"><%=order.getOrderType()%></td>
       <td nowrap="nowrap"><%=order.getOrderState()%></td>
       <td nowrap="nowrap"><%=order.getReason()%></td>
       <td nowrap="nowrap">
         <%=order.getEname()%>
       </td>
       <td nowrap="nowrap"><%=order.getSerialno()%></td>
       <td nowrap="nowrap"><%=order.getFixe()%></td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
	 <tr class="td_label" align="center">
     <td width="13%">
	  ${eoms:a2u('驳回意见')}
	  </td>
	  <td height="25">
      <%=order.getAdvices()%>
      </td>
    </tr>
</table>	                  
<table border="0" width="80%" cellspacing="0">
    <tr>
      <td height="32" align="right">
          <input type="submit" value="${eoms:a2u('确认驳回申请')}" name="submit1"  class="clsbtn2">
      </td>
      <td height="32" align="right" style="display:none">
			 <input type="submit" value="${eoms:a2u('删除该申请')}" name="submit2" onclick="return submitform()"  class="clsbtn2">
      </td>		
    </tr>
</table>
</form>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
<script language="javascript">
function submitform()

{
	  	  	     document.forms[0].action = "../part/incheckNO.do";			  
              document.forms[0].submit();
}
</script>