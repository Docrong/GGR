<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Array Grid Example</title>
<%@ include file="/common/meta.jsp"%>
<!-- Common Styles -->
<style type="text/css">
#detail, #search,#search-form{
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
<%@ include file="/common/extlibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${app}/scripts/ext/resources/css/ytheme-aero.css"/>
<script type="text/javascript" src="${app}/scripts/base/prototype.js"></script>
<script type="text/javascript" src="${app}/scripts/util/local.js"></script>
<script type="text/javascript" src="${app}/scripts/layout/xsearch.js"></script>

<!-- END LIBS -->

<div id="grid-panel" class="x-layout-inactive-content" style="overflow:hidden">
  <div id="search-form"></div>
  <div id="grid" style="width:100%;height:400px" ></div>
</div>

<div id="detail" class="x-layout-inactive-content">
  <div id="detail-header"></div>
  <div id="detail-form"></div>
</div>

</body>
</html>
