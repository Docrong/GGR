<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" import ="java.util.*,java.io.*,com.boco.eoms.common.util.StaticMethod"%>

    <%
      if(((String)request.getAttribute("error")).equals("1")){
    %>
    <font style="font-size:14px;color:#CC0000;"><strong><bean:message key="failure.tawwpyear.notSet" /></strong></font>
    <%
      }else{
    %>
    <font style="font-size:14px;color:#CC0000;"><strong><bean:message key="failure.tawwpyear.reset" /></strong></font>
    <input class="button" type=button name="back" value="<bean:message key="failure.title.back" />" onclick=javascript:history.back(); >
    <%
      }
    %>
<%@ include file="/common/footer_eoms.jsp"%>
