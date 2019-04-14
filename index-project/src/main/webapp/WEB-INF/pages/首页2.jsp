<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>首页</title>
  <link rel="stylesheet" href="layui/css/layui.css">

  <script type="text/javascript" src="ichart/ichart.1.2.1.min.js"></script>
  <script src="resources/scripts/jquery-1.7.1.min.js"></script>
  <script src="echart/build/dist/echarts.js"></script>
  
  
  <script type="text/javascript">
  $(function(){
	  	var cpuRate=60+Math.ceil(Math.random()*10);
	  	var unUseRate=100-cpuRate;
		var data = [
		        	{name : '已使用',value : cpuRate,color:'#5674D6'},
		        	{name : '未使用',value : unUseRate,color:'#23B7E5'},
	        	];
  	
		new iChart.Pie2D({
			render : 'canvasDiv1',
			data: data,
			title : {
				text : 'CPU使用   CPU',
				height:40,
				fontsize : 15,
				color : '#545C6D'
			},
			legend : {
				enable : true
			},
			sub_option : {
				label : {
					background_color:null,
					sign:false,//设置禁用label的小图标
					padding:'0 4',
					border:{
						enable:false,
						color:'#666666'
					},
					fontsize:11,
					fontweight:600,
					color : '#4572a7'
				},
				border : {
					width : 2,
					color : '#ffffff'
				}
			},
			animation:true,
			showpercent:true,
			decimalsnum:2,
			width : 300,
			height : 200,
			radius:140
		}).draw();
	});
  $(function(){
	  	var memoryRate=50+Math.ceil(Math.random()*20);
	  	var unUseRate=100-memoryRate;
		var data = [
		        	{name : '已使用',value : memoryRate,color:'#5674D6'},
		        	{name : '未使用',value : unUseRate,color:'#23B7E5'},
	        	];
	
		new iChart.Pie2D({
			render : 'canvasDiv2',
			data: data,
			title : {
				text : '内存使用   MEMORY',
				height:40,
				fontsize : 15,
				color : '#545C6D'
			},
			legend : {
				enable : true
			},
			sub_option : {
				label : {
					background_color:null,
					sign:false,//设置禁用label的小图标
					padding:'0 4',
					border:{
						enable:false,
						color:'#666666'
					},
					fontsize:11,
					fontweight:600,
					color : '#4572a7'
				},
				border : {
					width : 2,
					color : '#ffffff'
				}
			},
			animation:true,
			showpercent:true,
			decimalsnum:2,
			width : 300,
			height : 200,
			radius:140
		}).draw();
	});
  $(function(){
	  var diskRate=60+Math.ceil(Math.random()*10);
	  var unUseRate=100-diskRate;
		var data = [
		        	{name : '已使用',value : diskRate,color:'#5674D6'},
		        	{name : '即将用完',value : unUseRate,color:'#EA735C'},
	        	];
	
		new iChart.Pie2D({
			render : 'canvasDiv3',
			data: data,
			title : {
				text : '磁盘使用   DISK',
				height:40,
				fontsize : 15,
				color : '#545C6D'
			},
			legend : {
				enable : true
			},
			sub_option : {
				label : {
					background_color:null,
					sign:false,//设置禁用label的小图标
					padding:'0 4',
					border:{
						enable:false,
						color:'#666666'
					},
					fontsize:11,
					fontweight:600,
					color : '#4572a7'
				},
				border : {
					width : 2,
					color : '#ffffff'
				}
			},
			animation:true,
			showpercent:true,
			decimalsnum:2,
			width : 300,
			height : 200,
			radius:140
		}).draw();
	});

  </script>
  
  
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header" style="background: #FFF;">
    <div class="layui-logo" style="background: #5674D6;width: 325px;"><font color="white">云化集中门户管理</font></div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      
    </ul>
    <ul class="layui-nav layui-layout-right" style="background: #BBC7EF;">
      <li class="layui-nav-item">
        <a href="javascript:;">
        <i class="layui-icon layui-icon-username" style="font-size: 20px; "></i>   
          admin
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">注销</a></li>
      <li class="layui-nav-item">&nbsp;&nbsp;&nbsp;&nbsp;</li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-gray" style="background: #545C6D;width: 325px;" >
    <div class="layui-side-scroll " style="background: #545C6D;width: 325px;">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree " style="background: #545C6D;width: 100%;"  lay-filter="test">
      </br>
      <li class="layui-nav-item layui-nav-itemed" style="width: 100%;text-align: center" >
        <img src="http://t.cn/RCzsdCq" class="layui-nav-img" style="height: 80px;width: 80px;">
      </li>
      <li class="layui-nav-item layui-nav-itemed" style="width: 100%;text-align: left" >
      	<span style="font-size: 125%">&emsp;&emsp;&emsp;职位&emsp;&emsp;<span><span style="font-size: 100%">运维人员<span>
      </li>
      <li class="layui-nav-item layui-nav-itemed" style="width: 100%;text-align: left" >
      	<span style="font-size: 125%">&emsp;&emsp;&emsp;部门&emsp;&emsp;<span><span style="font-size: 100%">亿阳--电子运维<span>
      </li>
      <hr class="layui-bg-gray">
      <br><br>
      <li style="width: 100%">
          <div style="width: 100%;height: 85px;">
    			<span style="width: 100%;display: inline-table;height: 100%">
    		<span style="width: 50%;height:100%;display:inline-block;text-align: center;">
    		<a href="####">
	    		<img style="height: 50px;width: 50px;" src="layui/icon/首页.png">
    		</a>
    		<div style="text-align: center;width: 100%">
			首页
			</div>
    		</span>
    		<span style="width: 50%;height:100%;display:inline-block;text-align: center;">
    		<a href="####">
    			<img style="height: 50px;width: 50px;" src="layui/icon/资源管理.png">
    		</a>
    		<div style="text-align: center;width: 100%">
			资源管理
			</div>
    		</span>
    			</span>
   		 </div>
      </li>
    		
      <li style="width: 100%">
          <div style="width: 100%;height: 85px;">
    			<span style="width: 100%;display: inline-table;height: 100%">
    		<span style="width: 50%;height:100%;display:inline-block;text-align: center;">
    		<a href="####">
	    		<img style="height: 50px;width: 50px;" src="layui/icon/日志管理.png">
    		</a>
    		<div style="text-align: center;width: 100%">
			日志管理
			</div>
    		</span>
    		<span style="width: 50%;height:100%;display:inline-block;text-align: center;">
    		<a href="####">
	    		<img style="height: 50px;width: 50px;" src="layui/icon/配置管理.png">
    		</a>
    		<div style="text-align: center;width: 100%">
			配置管理
			</div>
    		</span>
    			</span>
   		 </div>
      </li>	
      
      <li style="width: 100%">
          <div style="width: 100%;height: 85px;">
    			<span style="width: 100%;display: inline-table;height: 100%">
    		<span style="width: 50%;height:100%;display:inline-block;text-align: center;">
    		<a href="####">
	    		<img style="height: 50px;width: 50px;" src="layui/icon/私库管理.png">
    		</a>
    		<div style="text-align: center;width: 100%">
			私库管理
			</div>
    		</span>
    		<span style="width: 50%;height:100%;display:inline-block;text-align: center;">
    		<a href="####">
	    		<img style="height: 50px;width: 50px;" src="layui/icon/HA管理.png">
    		</a>
    		<div style="text-align: center;width: 100%">
			HA管理
			</div>
    		</span>
    			</span>
   		 </div>
      </li>	
    		
    	
      </ul>
    </div>
  </div>
  
  <div class="layui-body" style="left: 325px;">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;background-color: #EEEEEE;width: 100%;height: 100%">
    	<div style="width: 100%;height: 30%;border: 25px;background-color: white;">
    	
  <div class="layui-row layui-col-space10" style="height: 100%" >
    <div class="layui-col-md3" style="height: 100%;background-color: #EEEEEE">
    <div class="layui-row layui-col-space10" style="height: 100%;width: 100%">
      <div class="layui-col-md6" style="width: 45%;height: 45%;margin: 2.5%;background-color: #FFFFFF;text-align: center;">
      	<font color="#6C6FC0" size="9">22</font>
        <font color="#6C6FC0" size="2">台 <br></font>
        <font color="#6C6FC0" size="2">虚拟机</font>
      </div>
      <div class="layui-col-md6" style="width: 45%;height: 45%;background-color: #6C6FC0;margin: 2.5%;text-align: center;">
        <font color="white" size="9">3</font>
        <font color="white" size="2">个<br></font>
        <font color="white" size="2">节点数</font>
      </div>
      <div class="layui-col-md6" style="width: 45%;height: 45%;background-color: #23B7E5;margin: 2.5%;text-align: center;">
        <font color="white" size="9">50</font>
        <font color="white" size="2">TB<br></font>
        <font color="white" size="2">总存储</font>
      </div>
      <div class="layui-col-md6" style="width: 45%;height: 45%;margin: 2.5%;background-color: #FFFFFF;text-align: center;">
        <font color="#23B7E5" size="9">56</font>
        <font color="#23B7E5" size="2">个<br></font>
        <font color="#23B7E5" size="2">容器数</font>
      </div>
    </div>
    </div>
    
    <div class="layui-col-md9" style="height: 100%">
     <div class="layui-col-md4" id='canvasDiv1'></div>
     <div class="layui-col-md4" id='canvasDiv2'></div>
     <div class="layui-col-md4" id='canvasDiv3'></div>
    </div>
  </div>
    	</div>
    	<div style="width: 100%;height: 70%;border: 25px;background-color: #EEEEEE;padding-top: 20px">
    	<div id="main" style="background-color: white;width: 100%;height: 100%">
    		<!-- <div id="swdt" style="background-color: white;width: 100%;height: 100%;"></div> -->
    		<iframe src="index3" height="800" width="1400" ></iframe>
    	</div>
    	</div>
    </div>
  </div>
<!--     <script type="text/javascript" src="echart/main.js"></script>-->        
<!-- <script type="text/javascript" src="echart/local.js"></script>    
 -->   

    		
  
  <div class="layui-footer" style="left: 325px;">
    <%--    底部固定区域  --%>
    © boco.com.cn
  </div>
</div>
<script src="layui/layui.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
</body>
</html>
