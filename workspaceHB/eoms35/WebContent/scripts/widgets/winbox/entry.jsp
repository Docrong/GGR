<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<br/><br/>
<div id="container">
	<div class="viewer-box"></div>
	<input type='hidden' name="userId">
	<input type='hidden' name="deptId">
	<div class="data" style="display:none"></div>
</div>
<input type="button" class="button" value="选择部门" 
	onclick="window.showModalDialog('winbox.jsp',document.getElementById('container'),'scroll=0;resizable=0;dialogWidth=300px;dialogHeight=350px');"/>
<%@ include file="/common/footer_eoms.jsp"%>
