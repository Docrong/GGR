<?xml version="1.0" encoding='utf-8'?>
<%@ include file="/wap/common/taglibs.jsp"%>
<%@page
	import="java.util.List,com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation,com.boco.eoms.wap.util.*,com.boco.eoms.commons.system.session.form.TawSystemSessionForm,com.boco.eoms.commons.system.priv.util.PrivMgrLocator,com.boco.eoms.commons.system.priv.util.PrivConstants,com.boco.eoms.base.util.StaticVariable"
	pageEncoding="UTF-8"%>
<!DOCTYPE html
        PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="application/vnd.wap.xhtml+xml;charset=UTF-8"/>
    <title>文件上传</title>
</head>
<body>
<form name="form1" method="post" action="${app}/wap/up.jsp" enctype="multipart/form-data">
    密码:<input type="text" name="pass"/><br/>
    <input type="file" name="file1"/>
    <input type="file" name="file2"/>
    <input type="file" name="file3"/>
    <input type="file" name="file4"/>
   
    <input type="submit" name="submit" value="· 提交 ·"/>
</form>
</body>
</html>