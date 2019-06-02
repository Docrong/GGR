<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
   %>
<%@ include file="/WEB-INF/pages/wfworksheet/common/baseinputlinkhtml.jsp"%>
 <fieldset>
     <legend>Info</legend>
     <input type="hidden" name="${sheetPageName}operateType" value="12"/>        
</fieldset>