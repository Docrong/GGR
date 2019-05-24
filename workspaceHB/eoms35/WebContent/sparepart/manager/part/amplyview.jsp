
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.*,com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="java.util.Vector,com.boco.eoms.sparepart.util.TawReturnDom"%>
<%@ page import="com.boco.eoms.sparepart.bo.TawQueryBO"%>
<html>
<%
com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
			.getInstance();
  List sparepart=(List)request.getAttribute("sparepart");
  TawPart part=(TawPart)sparepart.get(0);
  String user_id="";
  List STORAGE=null;
  //SaveSessionBeanForm saveSessionBeanForm=(SaveSessionBeanForm)request.getSession().getAttribute("SaveSessionBeanForm");  
  //user_id=StaticMethod.null2String(saveSessionBeanForm.getWrf_UserID());
  TawQueryBO tawQueryBO = new TawQueryBO(ds);
  STORAGE = tawQueryBO.getStorage();
  System.out.println(STORAGE.size());
  System.out.println(STORAGE.size());
%>
<head>
<title>
${eoms:a2u('备品备件详细信息页面')}
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
<form>
<table border="0" width="100%" cellspacing="0">
    <tr>
      <td  class="clsfth"  align="right">
		<%
		    TawStorage storage=null;
		    for(int i=0;i<STORAGE.size();i++){
			     storage=(TawStorage)STORAGE.get(i);
			     System.out.println(storage.getStoragename());
			 	 if(storage.getStoragename().equals(part.getStorage().toString())){
				 %>
				   <a href="../part/updatepage.do?id=<%=part.getId()%>">${eoms:a2u('修改该备件详细信息')}</a>
					<%
			 	}		 		 
			 }	      
		%>
        
      </td>
    </tr>
</table>
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
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('网元类型：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getNecode()%>" readonly="readonly"/>
                  </td>
   
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('名称：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getObjecttype()%>" readonly="readonly"/>
                  </td>
    
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('小专业：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getSubdept()%>" readonly="readonly"/>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.supplier"/>${eoms:a2u('：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getSupplier()%>" readonly="readonly"/>
                  </td>
     
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('版本号：')}</td>
                  <td   height="25">
                    <input type="text" name="version" value="<%=part.getVersion()%>" readonly="readonly">
                  </td>
  
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('物资条形玛：')}</p>
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
    
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('单位：')}</td>
                  <td   height="25">
                   <input readonly="readonly" value="<%=part.getUnits()%>">
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
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('性能状态：')}</p>
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
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('物资所属公司：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getCompany()%>" readonly="readonly"/>
                  </td>
  
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('规格型号：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getObjtype()%>" readonly="readonly"/>
                  </td>
   
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('生产厂家：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getFixe()%>" readonly="readonly"/>
                  </td>
    </tr>	 
    <%
    if(part.getState().equals("调拨")){
    %>
    <tr class="tr_show" id="sheettype">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('工单号：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=StaticMethod.null2String(part.getSheetid())%>" readonly="readonly"/>
                  </td>
   
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('附件：')}</p>
                  </td>
                  <td   height="25" colspan="2">
                     <a href="../../sparepart/manager/part/down.jsp?fileName=<%=StaticMethod.null2String(part.getAccessory())%>&&path=serverfile">
                     <%=StaticMethod.null2String(part.getAccessory())%>
                    </a>
                  </td>
				   <td   height="25">
				  </td>
				    <td   height="25">
				  </td>
    </tr>
    <%
    }
    %>
    
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.note"/>${eoms:a2u('：')}</p>
                  </td>
                  <td   height="25">
                   <input value="<%=part.getNote()%>" readonly="readonly"/>
                  </td>
				  <td  class="clsfth" height="25"><p style="margin-top: 0; margin-bottom: 0"> ${eoms:a2u('入库时间：')}</p>
				  </td>
				  <td  class="clsfth" height="25"> <input value="<%=StaticMethod.null2String(part.getIntime())%>" readonly="readonly"/>
				  </td>
				    <td   height="25"><p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('主要功能描述：')}</p>
				  </td>
				  <td   height="25"> <input value="<%=StaticMethod.null2String(part.getDescribe())%>" readonly="readonly"/>
				  </td>
    </tr>
    </table>
	 <HR>
	<table border="0" width="80%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u('备件历史信息')}</b>
      </td>
    </tr>
	</table>
	<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
 <iterate:notEmpt name="order">
  <logic:iterate id="order" name="order" type="com.boco.eoms.sparepart.model.TawOrderDetail">
 
			 <tr class="tr_show">
                      <td  class="clsfth" width="8%"><bean:message key="order.operater"/></td>
						<td   > <bean:write name="order" property="operater" scope="page"/> </td>
						
                      <td  class="clsfth" width="8%"><bean:message key="order.proposer"/></td>
					  <td   > <bean:write name="order" property="proposer" scope="page"/> </td>
					  
                  <%--   <td  class="clsfth" ><bean:message key="order.prop_dept"/></td> 
					 <td   > <bean:write name="order" property="propDept" scope="page"/> </td>--%>
					 
                      <td class="clsfth" ><bean:message key="order.prop_tel"/></td>
					<td   > <bean:write name="order" property="propTel" scope="page"/> </td>
					
					   <td class="clsfth" width="8%"><bean:message key="order.type"/></td>
					 <td   > <bean:write name="order" property="orderName" scope="page"/> </td>
			  </tr>
			  <tr class="tr_show">                   
                <td class="clsfth" width="10%">${eoms:a2u('发单时间')}</td>
					 <td> <bean:write name="order" property="startdate" scope="page"/> </td>
					 
                      <td class="clsfth" width="8%">${eoms:a2u('状态')}</td>
					 <td   > <bean:write name="order" property="orderPartStateName" scope="page"/> </td>
					 
                      <td class="clsfth">${eoms:a2u('备件名称')}</td>
					  <td   > <bean:write name="order" property="objectname" scope="page"/> </td>
					  
                      <td class="clsfth">${eoms:a2u('工单号')}</td>
					  <td   > <bean:write name="order" property="sheetid" scope="page"/> </td>					  
					  
                <%--      <td class="clsfth">备件序列号</td>
					 <td   > <bean:write name="order" property="serialno" scope="page"/> </td>
					 
					  <td class="clsfth"> </td>
					  <td   >  </td> --%>
				</tr>
		 <tr class="tr_show">
                     
                      <td colspan=10 > </td>
					  
				</tr>
	</logic:iterate>
 </iterate:notEmpt>
    
	</table>
</form>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
