<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*,javax.sql.*,java.lang.*"%>
<%@ page import ="com.boco.eoms.common.controller.*,com.boco.eoms.common.util.*"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.testcard.model.TawStat"%>
<%@ page import = "java.util.*"%>

 
<%
List list=(List)request.getAttribute("STATALLLIST");
%>
<form name="form">
  <table cellpadding="0" cellspacing="0" width="95%">
  <tr>
  <td width="100%" align="center" class="table_title">
${eoms:a2u('分公司存储测试卡统计')}
  </td>
  </tr>
</table>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="formTable" align="center">
   <tr class="td_label">
     <td>${eoms:a2u('序号')}</td>
     <td>${eoms:a2u('存储分公司')}</td>
     <td>${eoms:a2u('可用数')}</td>
     <td>${eoms:a2u('停机数')}</td>
     <td>${eoms:a2u('借出数')}</td>
     <td>${eoms:a2u('报废数')}</td>
     <td>${eoms:a2u('遗失数')}</td>
     <td>${eoms:a2u('总数')}</td>
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
        <a href="../TawTestcard/statlist.do?state=0&leave=<%=st.getLeave()%>">
          <%=st.getNaturalnum()%>
        </a>
      </td>
      <td>
        <a href="../TawTestcard/statlist.do?state=1&leave=<%=st.getLeave()%>">
          <%=st.getPausenum()%>
        </a>
      </td>
      <td>
        <a href="../TawTestcard/statlist.do?state=3&leave=<%=st.getLeave()%>">
          <%=st.getLoannum()%>
        </a>
      </td>
      <td>
        <a href="../TawTestcard/statlist.do?state=5&leave=<%=st.getLeave()%>">
          <%=st.getScrapnum()%>
        </a>
      </td>
      <td>
        <a href="../TawTestcard/statlist.do?state=2&leave=<%=st.getLeave()%>">
          <%=st.getLosenum()%>
        </a>
        </td>
      <td><%=st.getSummation()%></td>
    </tr>
      <%
    }
    %>
  </table>
  <table cellpadding="0" cellspacing="0" width="95%">
</table>
</form>
<%@ include file="/common/footer_eoms.jsp"%>
