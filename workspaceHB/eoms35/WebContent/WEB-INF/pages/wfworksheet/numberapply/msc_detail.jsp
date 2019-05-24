<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp"%>

<table class="formTable">
	<tr>
		<td class="label">网元类型(模板类型)</td>
		<td class="content">${numberApplyMscid.netType}</td>
		<td class="label">网元名称*</td>
		<td class="content">${numberApplyMscid.netName}</td>
	</tr>
	<tr>
		<td class="label">网元代号*</td>
		<td class="content">${numberApplyMscid.netId}</td>
		<td class="label">网元属性*</td>
		<td class="content">${numberApplyMscid.netProp}</td>
	</tr>
	<tr>
		<td class="label">相连网元</td>
		<td class="content">${numberApplyMscid.connNet}</td>
		<td class="label">建设地点*</td>
		<td class="content">${numberApplyMscid.buildAddress}</td>
	</tr>
	<tr>
		<td class="label">供应商*</td>
		<td class="content">
			<eoms:id2nameDB id="${numberApplyMscid.supplier}" beanId="ItawSystemDictTypeDao"/>
		</td>
		<td class="label">设备架构</td>
		<td class="content">${numberApplyMscid.equipmentArc}</td>
	</tr>
	<tr>
		<td class="label">信令点编码(24位)</td>
		<td class="content">
			<c:if test="${empty numberApplyMscid.commandCode24  }">
				等待管理员分配
			</c:if>
			<c:if test="${!empty numberApplyMscid.commandCode24 }">
				${numberApplyMscid.commandCode24 }
			</c:if>
		</td>
		<td class="label">MSCID</td>
		<td class="content">
			<c:if test="${empty numberApplyMscid.mscId  }">
				等待管理员分配
			</c:if>
			<c:if test="${!empty numberApplyMscid.mscId }">
				${numberApplyMscid.mscId }
			</c:if>
		</td>
	</tr>
	<tr>
		<td class="label">信令点编码(14位)</td>
		<td class="content">
			<c:if test="${empty numberApplyMscid.commandCode14  }">
				等待管理员分配
			</c:if>
			<c:if test="${!empty numberApplyMscid.commandCode14 }">
				${numberApplyMscid.commandCode14 }
			</c:if>
		</td>
		<td class="label">硬件平台</td>
		<td class="content">${numberApplyMscid.hardwareFlatRoof}</td>
	</tr>
	<tr>
		<td class="label">软件版本</td>
		<td class="content">${numberApplyMscid.softwareVersion}</td>
		<td class="label">容量（万）</td>
		<td class="content">${numberApplyMscid.capability}</td>
	</tr>
	<tr>
		<td class="label">信令链路数（2MB）</td>
		<td class="content">${numberApplyMscid.commondLink}</td>
		<td class="label">E1端口总数（承载窄带)</td>
		<td class="content">${numberApplyMscid.portCount}</td>
	</tr>
	<tr>
		<td class="label">IP接口总数（FE/GE）</td>
		<td class="content">${numberApplyMscid.iptotalNum}</td>
		<td class="label">呼叫处理能力(CAPS)</td>
		<td class="content">${numberApplyMscid.caps}</td>
	</tr>
	<tr>
		<td class="label">最大源信令点数量</td>
		<td class="content">${numberApplyMscid.maxSourceNum}</td>
		<td class="label">最大目的信令点数量</td>
		<td class="content">${numberApplyMscid.maxTargetNum}</td>
	</tr>
	<tr>
		<td class="label">覆盖地区</td>
		<td class="content">${numberApplyMscid.coverArea}</td>
		<td class="label">覆盖地区长途区号</td>
		<td class="content">${numberApplyMscid.areaNumber}</td>
	</tr>
	<tr>
		<td class="label">城市</td>
		<td class="content">${numberApplyMscid.city}</td>
		<td class="label">覆盖地区范围</td>
		<td class="content">${numberApplyMscid.coverAreaRange}</td>
	</tr>
	<tr>
		<td class="label">端口数</td>
		<td class="content">${numberApplyMscid.portNum}</td>
		<td class="label">设备所在本地网名称</td>
		<td class="content">${numberApplyMscid.deviceName}</td>
	</tr>
	<tr>
		<td class="label">归属区域</td>
		<td class="content">${numberApplyMscid.attachArea}</td>
		<td class="label">批复文件号</td>
		<td class="content">${numberApplyMscid.fileNumber}</td>
	</tr>
</table>
<%@ include file="/common/footer_eoms.jsp"%>
