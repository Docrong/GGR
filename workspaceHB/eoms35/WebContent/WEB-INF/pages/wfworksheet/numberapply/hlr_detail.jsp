<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp"%>

<table class="formTable">
	<tr>
		<td class="label">网元名称*</td>
		<td class="content">${numberApplyHlrid.netName }</td>
		<td class="label">网元代号*</td>
		<td class="content">${numberApplyHlrid.netId }</td>
	</tr>
	<tr>
		<td class="label">网元属性*</td>
		<td class="content">${numberApplyHlrid.netProp }</td>
		<td class="label">建设地点*</td>
		<td class="content">${numberApplyHlrid.buildAddress }</td>
	</tr>
	<tr>
		<td class="label">供应商*</td>
		<td class="content">
		<eoms:id2nameDB id="${numberApplyHlrid.supplier}" beanId="ItawSystemDictTypeDao"/>
		</td>
		<td class="label">信令点编码(24位)</td>
		<td class="content">
			<c:if test="${empty numberApplyHlrid.commandCode24  }">
				等待管理员分配
			</c:if>
			<c:if test="${!empty numberApplyHlrid.commandCode24}">
				${numberApplyHlrid.commandCode24 }
			</c:if>
	</tr>
	<tr>
		<td class="label">LLRID</td>
		<td class="content">
			<c:if test="${empty numberApplyHlrid.hlrId  }">
				等待管理员分配
			</c:if>
			<c:if test="${!empty numberApplyHlrid.hlrId }">
				${numberApplyHlrid.hlrId }
			</c:if>
		<td class="label">信令点编码(14位)</td>
		<td class="content">
			<c:if test="${empty numberApplyHlrid.commandCode14  }">
				等待管理员分配
			</c:if>
			<c:if test="${!empty numberApplyHlrid.commandCode14 }">
				${numberApplyHlrid.commandCode14 }
			</c:if>
	</tr>
	<tr>
		<td class="label">硬件平台</td>
		<td class="content">${numberApplyHlrid.hardwareFlatRoof }</td>
		<td class="label">软件版本</td>
		<td class="content">${numberApplyHlrid.softwareVersion }</td>
	</tr>
	<tr>
		<td class="label">容量（万）</td>
		<td class="content">${numberApplyHlrid.capability }</td>
		<td class="label">信令链路数（2MB）</td>
		<td class="content">${numberApplyHlrid.commondLink }</td>
	</tr>
	<tr>
		<td class="label">E1端口总数（承载窄带)</td>
		<td class="content">${numberApplyHlrid.portCount }</td>
		<td class="label">覆盖地区</td>
		<td class="content">${numberApplyHlrid.coverArea }</td>
	</tr>
	<tr>
		<td class="label">覆盖地区长途区号</td>
		<td class="content">${numberApplyHlrid.areaNumber }</td>
		<td class="label">城市</td>
		<td class="content">${numberApplyHlrid.city }</td>
	</tr>
	<tr>
		<td class="label">设备所在本地网名称</td>
		<td class="content">${numberApplyHlrid.deviceName }</td>
		<td class="label">归属区域</td>
		<td class="content">${numberApplyHlrid.attachArea }</td>
	</tr>
	<tr>
		<td class="label">批复文件号</td>
		<td class="content" colspan="3" >${numberApplyHlrid.fileNumber }</td>
	</tr>
</table>
<%@ include file="/common/footer_eoms.jsp"%>
