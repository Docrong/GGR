<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" import ="java.util.*,java.io.*,com.boco.eoms.common.util.StaticMethod"%>

    <br><br>
    <br><br>
    <br><br>
    <br><br>
    <br><br>
    <br><br>
    <div align=center>
    <%
      if (((String)request.getAttribute("error")).equals("nocopy"))
      {
    %>
    <font style="font-size:14px;color:#CC0000;"><strong><bean:message key="failureinfo.tawwpmonth.noUsablePlan" /></strong></font>
    <input class="button" type="button" name="back" value="<bean:message key="failureinfo.tawwpmonth.btnBack" />" onclick=javascript:history.back(); >
    <%
      }else if(((String)request.getAttribute("error")).equals("1")){
    %>
    <font style="font-size:14px;color:#CC0000;"><strong><bean:message key="failureinfo.tawwpmonth.cantConstitute" /></strong></font>
    <%
      }
    %>
    </div>
<%@ include file="/common/footer_eoms.jsp"%>
