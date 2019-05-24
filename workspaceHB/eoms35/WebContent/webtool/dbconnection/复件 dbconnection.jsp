<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Array Grid Example</title> 
<link rel="stylesheet" type="text/css" href="${app}/scripts/ext/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="${app}/scripts/ext/resources/css/ytheme-aero.css"/>
<link rel="stylesheet" type="text/css" href="${app}/styles/ext-adpter.css"/>
<!-- Common Styles -->
<style type="text/css">
#detail{
	padding:10px;
}
</style>
</head>
<body>
<!-- loading -->
<div id="loading">
<div class="loading-indicator">Loading...</div>
</div>

<!--LOADING LIBS -->
<script type="text/javascript" src="${app}/scripts/ext/adapter/yui/yui-utilities.js"></script>
<script type="text/javascript" src="${app}/scripts/ext/adapter/yui/ext-yui-adapter.js"></script>
<script type="text/javascript" src="${app}/scripts/ext/ext-all.js"></script>


<script type="text/javascript" src="${app}/webtool/dbconnection/xdbconnection.js"></script>

<!-- END LIBS -->


<div id="search" class="x-layout-inactive-content">
  <div id="search-form"></div>
</div>

<div id="detail" class="x-layout-inactive-content">
</div>

</body>
</html>
