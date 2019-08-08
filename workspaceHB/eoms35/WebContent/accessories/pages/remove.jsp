<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*,
                 java.io.File,
                 com.boco.eoms.commons.accessories.service.*,
                 com.boco.eoms.commons.accessories.model.*,
                 com.boco.eoms.base.util.StaticMethod,
                 com.boco.eoms.base.util.ApplicationContextHolder" %>


<head>
    <title>删除文件处理页面</title>
</head>

<%

    String appId = StaticMethod.nullObject2String(request.getParameter("appId"));
    String filelist = StaticMethod.nullObject2String(request.getParameter("filelist"));
    String removeIdList = StaticMethod.nullObject2String(request.getParameter("removeid"));
    String idField = StaticMethod.nullObject2String(request.getParameter("idField"));
    ITawCommonsAccessoriesManager mgr =
            (ITawCommonsAccessoriesManager) ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
    System.out.println(removeIdList);
    List list = mgr.getAllFileById(removeIdList);
    for (int i = 0; i < list.size(); i++) {
        System.out.println("12323124");
        TawCommonsAccessories accessories = (TawCommonsAccessories) list.get(i);
        mgr.removeTawCommonsAccessories(accessories.getId());
        java.io.File file = new java.io.File(accessories.getAccessoriesPath() + "/" + accessories.getAccessoriesName());
        System.out.println(accessories.getAccessoriesPath() + "/" + accessories.getAccessoriesName());
        file.delete();
    }
    response.sendRedirect("upload.jsp?appId=" + appId + "&filelist=" + filelist + "&idField=" + idField);
%>



