<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp"%>

<table class="formTable">
	<tr>
		<td class="label">信息列表</td>
		<td class="content">${offlineDataInfoList.infolist }</td>
		<td class="label">在线存放设备</td>
		<td class="content">${offlineDataInfoList.storageequipment }</td>
	</tr>
	<tr>
		<td class="label">维护责任单位</td>
		<td class="content">${offlineDataInfoList.maintenance }</td>
		<td class="label">责任人</td>
		<td class="content">${offlineDataInfoList.responsible }</td>
	</tr>
	<tr>
		<td class="label">在线情况</td>
		<td class="content">${offlineDataInfoList.information }</td>
		<td class="label">信息定级</td>
		<td class="content">${offlineDataInfoList.onlinestatus }</td>
	</tr>
</table>
<%@ include file="/common/footer_eoms.jsp"%>
