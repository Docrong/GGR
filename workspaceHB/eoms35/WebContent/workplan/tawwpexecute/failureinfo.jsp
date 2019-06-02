<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page language="java" import ="java.util.*,java.io.*,com.boco.eoms.common.util.StaticMethod"%>

    <br><br>
    <br><br>
    <br><br>
    <br><br>
    <br><br>
    <br><br>
    <div align=center>
    <%
      if (((String)request.getAttribute("error")).equals("noduty"))
      {
    %>
    <font style="font-size:14px;color:#CC0000;"><strong><bean:message key="failureinfo.title.noduty"/></strong></font>
    <%
      }
      else if(((String)request.getAttribute("error")).equals("noexecute"))
      {
    %>
    <font style="font-size:14px;color:#CC0000;"><strong><bean:message key="failureinfo.title.noexecute"/></strong></font>
    <%
      }
    %>
    </div>

<%@ include file="/common/footer_eoms.jsp"%>