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
<script type="text/javascript" src="${app}/webtool/dbconnection/layout.js"></script>
<link rel="stylesheet" type="text/css" href="forms.css" />

<!-- END LIBS -->



<div id="search" class="x-layout-inactive-content">
  <div id="search-form"></div>
</div>

<div style="width:340px;">
    <div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>
    <div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc">
        <h3>Fieldsets, labels right and complex fields</h3>
        <div id="detail">

        </div>
    </div></div></div>
    <div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>
</div>

   <div id="hello-dlg" style="visibility:hidden;position:absolute;top:0px;">
    <div class="x-dlg-hd">Welcome</div>
    <div class="x-dlg-bd">
        <!-- Auto create tab 1 -->
        <div class="x-dlg-tab" title="Hello:">
            <!-- Nested "inner-tab" to safely add padding -->
            <div class="inner-tab">
                 Welcom to Eoms...<br><br><br>
            </div>
        </div> 
        </div>
    </div>
</body>
</html>
