
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*,javax.sql.*,java.lang.*"%>
<%@ page import ="com.boco.eoms.common.controller.*,com.boco.eoms.common.util.*"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
 <%@ page import ="com.boco.eoms.sparepart.util.StaticPartMethod"%>
<%@ page import ="com.boco.eoms.sparepart.model.TawStat"%>
<%@ page import = "java.util.*"%>

 <%
List list=(List)request.getAttribute("STATALLLIST");
String typeName=(String)request.getAttribute("typeName");
%>
 
 
<form name="form">
  <table cellpadding="0" cellspacing="0" width="95%">
  <tr>
  <td width="100%" align="center" class="table_title">
       <%=StaticMethod.null2String(typeName)%>
  </td>
  </tr>
</table>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align="center">
   <tr class="td_label">
      <td>${eoms:a2u('序号')}</td>
     <td>${eoms:a2u('仓库名')}</td>
     <td>${eoms:a2u('库存数')}</td>
     <td>${eoms:a2u('库外数')}</td>
     <td>${eoms:a2u('报废数')}</td>
     <td>${eoms:a2u('仓库总数')}</td>
    </tr>
    <%
    int i=0;
    for(int j=0;j<list.size();j++){
      TawStat st =(TawStat)list.get(j);
      i=i+1;
    %>
    <tr class="tr_show">
      <td><%=i%></td>
      <td>
          <%=st.getStorage()%>
      </td>
      <td>
        <a href="../query/statlist.do?state=<%=StaticPartMethod.stateIN%>&storageid=<%=st.getStorageid()%>">
          <%=st.getInnum()%>
        </a>
      </td>
      <td>
        <a href="../query/statlistfault.do?state=<%=StaticPartMethod.stateOUT%>&storageid=<%=st.getStorageid()%>">
          <%=st.getOutnum()%>
        </a>
      </td>
      <td>
        <a href="../query/statlist.do?state=<%=StaticPartMethod.stateNO%>&storageid=<%=st.getStorageid()%>">
          <%=st.getRejectnum()%>
        </a>
        </td>
      <td><%=st.getAllnum()%></td>
    </tr>
      <%
    }
    %>
  </table>
  <table cellpadding="0" cellspacing="0" width="95%">
</table>
</form>
<%@ include file="/common/footer_eoms.jsp"%>
