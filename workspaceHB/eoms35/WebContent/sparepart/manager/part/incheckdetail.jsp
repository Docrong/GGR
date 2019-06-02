<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
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
${eoms:a2u('������ϸ��Ϣ')}
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<br>
<table border="0" width="80%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u('������ϸ��Ϣ')}</b>
      </td>
    </tr>
</table>
<form action="../part/incheckOK.do" method="post" name="tawSparepartForm">
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
		<tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('�ֹ�˾��')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getDeptName()%>" readonly="readonly"/>
                  </td>
     
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('�ֿ⣺')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getStorage()%>" readonly="readonly"/>
                  </td>
   
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.department"/>${eoms:a2u('��')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getNettype()%>" readonly="readonly"/>
                  </td>
   </tr>

		<tr class="tr_show">  
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('��Ԫ���ͣ�')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getNecode()%>" readonly="readonly"/>
                  </td>
   
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('���ƣ�')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getObjecttype()%>" readonly="readonly"/>
                  </td>
    
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('Сרҵ��')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getSubdept()%>" readonly="readonly"/>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.supplier"/>${eoms:a2u('��')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getSupplier()%>" readonly="readonly"/>
                  </td>
     
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('�汾�ţ�')}</td>
                  <td   height="25">
                    <input type="text" name="version" value="<%=part.getVersion()%>" readonly="readonly">
                  </td>
  
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('���������꣺')}</p>
                  </td>
                  <td   height="25">
                    <input type="text" readonly="readonly" value="<%=part.getManagecode()%>" name="managecode">
					</td>
      </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="sparepart.serialno"/>${eoms:a2u('��')}</td>
                  <td   height="25">
                    <input type="text" name="serialno" value="<%=part.getSerialno()%>" readonly="readonly">
                  </td>
    
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('��λ��')}</td>
                  <td   height="25">
                    <input readonly="readonly" value="<%=part.getUnits()%>">
                  </td>
  
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('״̬��')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getState()%>" readonly="readonly"/>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('����״̬��')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getProform()%>" readonly="readonly"/>
                  </td>
     
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('�Ƿ��ޣ�')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getWarrantyName()%>" readonly="readonly"/>
                  </td>
   
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('�Ƿ�ͣ����')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getStopproductName()%>" readonly="readonly"/>
                  </td>
     </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('��������/��ͬ��')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getContract()%>" readonly="readonly"/>
                  </td>
  
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('������')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getMoney()%>" readonly="readonly"/>
                  </td>
   
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('λ�ã�')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getPosition()%>" readonly="readonly"/>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('����������˾��')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getCompany()%>" readonly="readonly"/>
                  </td>
  
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('����ͺţ�')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getObjtype()%>" readonly="readonly"/>
                  </td>
   
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('�������ң�')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getFixe()%>" readonly="readonly"/>
                  </td>
    </tr>	    
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.note"/>${eoms:a2u('��')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getNote()%>" readonly="readonly"/>
                  </td>
				  <td  class="clsfth" height="25"><p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('���ʱ�䣺')}</p>
				  </td>
				  <td  class="clsfth" height="25"> <input value="<%=StaticMethod.null2String(part.getIntime())%>" readonly="readonly"/>
				  </td>
				    <td   height="25"><p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('��Ҫ����������')}</p>
				  </td>
				  <td   height="25"> <input value="<%=StaticMethod.null2String(part.getDescribe())%>" readonly="readonly"/>
				  </td>
    </tr>
    </table>
	 <HR>
	<table border="0" width="80%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u('������Ϣ')}</b>
      </td>
    </tr>
	</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
   <tr class="td_label" align="center">
     <td nowrap="nowrap">${eoms:a2u('������')}</td>
     <td nowrap="nowrap"><bean:message key="storage"/></td>
     <td nowrap="nowrap">${eoms:a2u('����ʱ��')}</td>
     <td nowrap="nowrap">${eoms:a2u('�������')}</td>
     <td nowrap="nowrap">${eoms:a2u('��������')}</td>
     <td nowrap="nowrap">${eoms:a2u('����״̬')}</td>
     <td nowrap="nowrap">${eoms:a2u('ԭ��˵��')}</td>
     <%--<td nowrap="nowrap">��������</td>
     <td nowrap="nowrap">�������к�</td>
	  <td nowrap="nowrap">��������</td>--%>
    </tr>
	 <tr class="td_label" align="center">
       <td nowrap="nowrap"><%=order.getProposer()%></td>
       <td nowrap="nowrap"><%=order.getStoragename()%></td>
       <td nowrap="nowrap"><%=order.getStartdate()%></td>
       <td nowrap="nowrap"><%=order.getSheetid()%></td>
       <td nowrap="nowrap"><%=order.getOrderType()%></td>
       <td nowrap="nowrap"><%=order.getOrderState()%></td>
       <td nowrap="nowrap"><%=order.getReason()%></td>
       <%--<td nowrap="nowrap">
         <%=order.getEname()%>
       </td>
       <td nowrap="nowrap"><%=order.getSerialno()%></td>
       <td nowrap="nowrap"><%=order.getFixe()%></td>--%>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
	 <tr class="td_label" align="center">
     <td width="13%">
	  ${eoms:a2u('������')}
	  </td>
	  <td height="25">
      <input type="text"  rows="2" name="advices" style="width:100%" value="" title="${eoms:a2u('������')}"/>
      </td>
    </tr>
</table>	                  
<table border="0" width="80%" cellspacing="0">
    <tr>
      <td height="32" align="right">
          <input type="submit" value="${eoms:a2u('�ύ����')}" name="submit1"  class="clsbtn2">
      </td>
      <td height="32" align="right">
			 <input type="submit" value="${eoms:a2u('��˲�ͨ��')}" name="submit2" onclick="return submitform()"  class="clsbtn2">
      </td>		
    </tr>
</table>
</form>
</div>
</body>
</html>
<script language="javascript">
function submitform()

{
	  	  	     document.forms[0].action = "../part/incheckNO.do";			  
              document.forms[0].submit();
}
</script>