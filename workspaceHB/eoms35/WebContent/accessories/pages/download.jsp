<%@page contentType="application/x-download;charset=gb2312"%>
<%@page
	import="com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager,com.boco.eoms.base.util.StaticMethod,com.boco.eoms.commons.accessories.model.TawCommonsAccessories,com.boco.eoms.base.util.ApplicationContextHolder,java.net.URLEncoder,java.lang.IllegalArgumentException"%>
<head>
	<title>文件下载处理页面</title>
</head>
<%
		try {
		String id = StaticMethod.nullObject2String(request
		.getParameter("id"));
		ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager) ApplicationContextHolder
		.getInstance().getBean("ItawCommonsAccessoriesManager");
		TawCommonsAccessories accessories = (TawCommonsAccessories) mgr
		.getTawCommonsAccessories(id);
		String filePath=mgr.getFilePath(id);
		if (accessories != null) {
			String fileCnName = accessories.getAccessoriesCnName();
			String fileName = accessories.getAccessoriesName();
			String path = accessories.getAccessoriesPath();
			path = filePath+ fileName;
			fileCnName = URLEncoder.encode(fileCnName, "UTF-8");
			response.reset();
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
			"attachment;filename=" + fileCnName);
			javax.servlet.RequestDispatcher dispatcher = application
			.getRequestDispatcher(path);
			if (dispatcher != null) {
		dispatcher.forward(request, response);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
