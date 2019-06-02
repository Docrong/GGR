<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<script type="text/javascript"  src="${app}/scripts/kmmanager/kmIndex.js"></script>
<link rel="stylesheet" type="text/css" href="kmIndex.css" />
</head>
<body>
<div id="container">
	<div id="leftPart">
		<div class="leftContent">
			<div class="leftTitle">知识管理</div>
			<div class="dataList" id="contextTree"></div>
		</div>
		<div class="leftContent">
			<div class="leftTitle">文件管理</div>
			<div class="dataList" id="fileTree"></div>
		</div>
		<div class="leftContent">
			<div class="leftTitle">课程管理</div>
			<div class="dataList" id="lessonTree"></div>
		</div>
	</div>
	<div id="middlePart">
		<!-- 搜索部分 -->
		<div class="search">
			<div class="leftBorder"></div>
			<div class="topBorder"></div>
			<div class="rightBorder"></div>
			<div class="bottomBorder"></div>
			<div id="searchContent">
				搜索：<select name="actionName" id="actionName" class="select"  style="font-size:11px;padding-top:0px;border:1px solid #D4D0C8;">
						<option value="search.do">知识案例库</option>
						<option value="sdoc.do">文档案例库</option>
					</select>
					<input type="text" class="text" name="actionValue" id="actionValue" style="height:20px;padding-top:0px;border:1px solid #D4D0C8;"/>
					<span class="searchHander"  onClick="sb()">搜索</span>
			</div>
		</div>
		
		<!-- 图像部分 -->
		<div id="pictureList" onmouseover="scanExpertOver();" onmouseout="scanExpertOut()">
			<div id="pictureContainer"></div>
		</div>
		<!-- 数据1列表 -->
		<div class="dataDetailList">
			<div class="dataDetailTitle">
				<div class="leftBorder"></div>
				<div class="topBorder"></div>
				<div class="rightBorder"></div>
				<div class="bottomBorder"></div>
				<div class="dataDetailTitleContent">
					<span class="span1" style="text-align:center;">所属类别</span>|<span class="span2" style="text-align:center;">文档主题</span>|<span class="span3">创建时间</span>|<span class="span4">创建人</span>
				</div>
			</div>
			<div class="dataDetailContent" id="dataDetailContent"></div>
		</div>
		
		<!-- 数据2列表 -->
		<div class="dataDetailList">
			<div class="dataDetailTitle">
				<div class="leftBorder"></div>
				<div class="topBorder"></div>
				<div class="rightBorder"></div>
				<div class="bottomBorder"></div>
				<div class="dataDetailTitleContent">
					<span class="span1" style="text-align:center;">所属类别</span>|<span class="span2" style="text-align:center;">文档主题</span>|<span class="span3">创建时间</span>|<span class="span4">创建人</span>
				</div>
			</div>
			<div class="dataDetailContent" id="dataDetailFile"></div>
		</div>
		
	</div>
	<div id="rightPart">
		<!-- 登陆用户头像 -->
		<div id="loginUser">
			<div class="userLeftTop"></div>
			<div class="userRightTop"></div>
			<div class="userRightBottom"></div>
			<div class="userLeftBottom"></div>
			<div class="userLeftBorder"></div>
			<div class="userTopBorder"></div>
			<div class="userRightBorder"></div>
			<div class="userBottomBorder"></div>
			<div class="userContent">
				<img id="expertPhoto" src="kmIndexImg/Sleesangyun.jpg" width="96" height="120"/>
				<span>
					<div>${sessionScope.sessionform.username}</div><div id="knowledgeScore"></div><div id="expertScore"></div>
				</span>
			</div>
		</div>
		
		<!-- 排行 -->
		<div class="orderList">
			<div class="orderTitle">知识库知识量排行</div>
			<div class="orderContent">
				<div style="margin-left:2px;">
				  <span id="tab1" class="orderTabOver"    onmouseover="switchTab('${app}','',1);" onmouseout="out(this);">总</span><span id="tab2" class="orderTabDefault" onmouseover="switchTab('${app}','',2);" onmouseout="out(this);">年</span><span id="tab3" class="orderTabDefault" onmouseover="switchTab('${app}','',3);" onmouseout="out(this);">季</span><span id="tab4" class="orderTabDefault" onmouseover="switchTab('${app}','',4);" onmouseout="out(this);">月</span><span id="tab5" class="orderTabDefault" onmouseover="switchTab('${app}','',5);"  onmouseout="out(this);">周</span></div>
				<div id="order1" class="orderContentDetail"></div>
				<div id="order2" class="orderContentDetail" style="display:none"></div>
				<div id="order3" class="orderContentDetail" style="display:none"></div>
				<div id="order4" class="orderContentDetail" style="display:none"></div>
				<div id="order5" class="orderContentDetail" style="display:none"></div>
			</div>
			
			<div class="orderTitle">知识阅读热度排行</div>
			<div class="orderContent">
				<div style="margin-left:2px;">
				  <span id="readcountTab1" class="orderTabOver"    onmouseover="switchTab('${app}','readcount',1);" onmouseout="out(this);">总</span><span id="readcountTab2" class="orderTabDefault" onmouseover="switchTab('${app}','readcount',2);" onmouseout="out(this);">年</span><span id="readcountTab3" class="orderTabDefault" onmouseover="switchTab('${app}','readcount',3);" onmouseout="out(this);">季</span><span id="readcountTab4" class="orderTabDefault" onmouseover="switchTab('${app}','readcount',4);" onmouseout="out(this);">月</span><span id="readcountTab5" class="orderTabDefault" onmouseover="switchTab('${app}','readcount',5);" onmouseout="out(this);">周</span></div>
				<div id="readcountOrder1" class="orderContentDetail"></div>
				<div id="readcountOrder2" class="orderContentDetail" style="display:none"></div>
				<div id="readcountOrder3" class="orderContentDetail" style="display:none"></div>
				<div id="readcountOrder4" class="orderContentDetail" style="display:none"></div>
				<div id="readcountOrder5" class="orderContentDetail" style="display:none"></div>
			</div>

			<div class="orderTitle">员工知识积分排行</div>
			<div class="orderContent">
				<div style="margin-left:2px;">
				  <span id="scoreTab1" class="orderTabOver"    onmouseover="switchTab('${app}','score',1);" onmouseout="out(this);">总</span><span id="scoreTab2" class="orderTabDefault" onmouseover="switchTab('${app}','score',2);" onmouseout="out(this);">年</span><span id="scoreTab3" class="orderTabDefault" onmouseover="switchTab('${app}','score',3);" onmouseout="out(this);">季</span><span id="scoreTab4" class="orderTabDefault" onmouseover="switchTab('${app}','score',4);" onmouseout="out(this);">月</span><span id="scoreTab5" class="orderTabDefault" onmouseover="switchTab('${app}','score',5);" onmouseout="out(this);">周</span></div>
				<div id="scoreOrder1" class="orderContentDetail"></div>
				<div id="scoreOrder2" class="orderContentDetail" style="display:none"></div>
				<div id="scoreOrder3" class="orderContentDetail" style="display:none"></div>
				<div id="scoreOrder4" class="orderContentDetail" style="display:none"></div>
				<div id="scoreOrder5" class="orderContentDetail" style="display:none"></div>
			</div>
<!--  
			<div class="orderTitle">知识阅读热度排行</div>
			<div class="orderContent">
				<div style="margin-left:2px;">
				  <span id="contributeTab1" class="orderTabOver"    onmouseover="switchTab('${app}','contribute',1);" onmouseout="out(this);">总</span><span id="contributeTab2" class="orderTabDefault" onmouseover="switchTab('${app}','contribute',2);" onmouseout="out(this);">年</span><span id="contributeTab3" class="orderTabDefault" onmouseover="switchTab('${app}','contribute',3);" onmouseout="out(this);">季</span><span id="contributeTab4" class="orderTabDefault" onmouseover="switchTab('${app}','contribute',4);" onmouseout="out(this);">月</span><span id="contributeTab5" class="orderTabDefault" onmouseover="switchTab('${app}','contribute',5);" onmouseout="out(this);">周</span></div>
				<div id="contributeOrder1" class="orderContentDetail"></div>
				<div id="contributeOrder2" class="orderContentDetail" style="display:none"></div>
				<div id="contributeOrder3" class="orderContentDetail" style="display:none"></div>
				<div id="contributeOrder4" class="orderContentDetail" style="display:none"></div>
				<div id="contributeOrder5" class="orderContentDetail" style="display:none"></div>
			</div>
-->			
		</div>
	</div>
	
</div>

<script type="text/javascript">
function window.onload() {
    var url = "${app}";
	var clientWidth = document.getElementById("pictureList").clientWidth;
	//页面右边当前用户信息
	showUser(url);
	//页面右边的排行
	switchTab(url, "", 1);
	switchTab(url, "readcount", 1);
	switchTab(url, "score", 1);
	//switchTab(url, "contribute", 1);	
	//页面中间的值班专家
	showOnDutyExperts(url);	
	//页面中间的内容
	showContent(url, 1);
	showContent(url, 2);
	//页面左边菜单树
	showNavigationTree(url, 1);
	showNavigationTree(url, 2);
	showNavigationTree(url, 3);
}
</script>
</body>
</html>
