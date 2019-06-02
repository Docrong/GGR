<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
<div id="helpPanel" style="padding:10px;" class="x-layout-active-content">
	<dl>
	 	<dt>功能说明</dt>
		<dd>专家管理主要分为添加、查询、修改、删除专家信息，主要包括专家的基本信息、教育背景、工作经验、培训管理、证书管理和技术交流表彰等等</dd>
		<dt>添加专家信息</dt>
		<dd>进入到运维骨干页面，点击【新增专家】，添加完专家基本信息后，才能添加教育背景、工作经验、培训管理、证书管理和技术交流表彰等信息</dd>
		<dt>查询专家信息</dt>
		<dd>进入到运维骨干页面，点击【专家列表】，选择专家所在的部门和专业后，点击【查询】  </dd>
		<dt>修改专家信息</dt>
		<dd>进入到运维骨干页面，点击【专家列表】，查找到要修改信息的专家，点击【修改】图标，进入修改基本信息界面，选择要修改的界面，点击【修改】图标，修改相关信息后，点击【保存】</dd>
	    <dt>删除专家信息</dt>
		<dd>进入到运维骨干页面，点击【专家列表】，查找到要删除信息的专家，点击【删除】图标</dt>
	</dl>
</div>

<%@ include file="/common/footer_eoms.jsp"%>