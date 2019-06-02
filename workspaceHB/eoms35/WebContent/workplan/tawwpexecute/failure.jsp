<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page language="java" import ="java.util.*,java.io.*,com.boco.eoms.common.util.StaticMethod"%>

<br>
<br><br>
<b><bean:message key="failure.title.heading" /></b>
<div align=center>
  <font color=red><bean:message key="failure.title.warn" />
  <input class="button" type="button" name="back" value="<bean:message key="failure.title.back" />" onclick=javascript:history.back(); ><bean:message key="failure.title.reset" /></font>
</div>

<%@ include file="/common/footer_eoms.jsp"%>