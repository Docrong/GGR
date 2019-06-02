<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
String proj_id=request.getParameter("id");
%>


<html><head><title>noPrivilege</title>
    <LINK rel="stylesheet" type="text/css" href="<%=session.getAttribute("RootURL")%>/css/general.css"/>
    <STYLE>
        .LinkComponent{behavior: url("../behavior/LinkComponent.htc");}
        .hide {display: none}
        TD{font:9pt;}
        A{color:blue;}
        BUTTON{cursor:hand;}
    </STYLE>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<p align="left" >
<form name="mainForm" method="post" action="uploaddeal.jsp" enctype="multipart/form-data" >
<input type="hidden" name="projectId" value="<%=proj_id%>">
<input type="hidden" name="filepath" value="uploadimages">
<input type="hidden" name="act" value="upload">
<INPUT TYPE="FILE" NAME="FILE1" SIZE="20">
<INPUT TYPE="hidden" NAME="todo" SIZE="30" value="upload">
<input type="submit"  value="上传" onclick="">
</form>
</p>
</body>
</html>
