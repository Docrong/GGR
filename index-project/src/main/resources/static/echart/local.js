//datadec
	var str="{normal:{label:{show:true,textStyle:{align:'center',verticalAlign:'middle'}},color:'#fff',borderWidth: 2,borderColor: '#EEEEEE'}}";
	
	var jsondec=eval('('+str+')');
//	console.log(jsondec);

// 路径配置
require.config({
	paths : {
		echarts : 'echart/build/dist'
	}
});

// 使用
require([ 'echarts', 'echarts/chart/tree' // 使用柱状图就加载bar模块，按需加载
], function(ec) {
	var myCharts = ec.init(document.getElementById('swdt'));
	var arr1 = [ {
		"name" : "电子运维系统\n(EOMS)",
		"children" : "",
	} ];
	arr1[0].symbol='rectangle';
	arr1[0].symbolSize= [80, 60];
	arr1[0].smooth=true;
	arr1[0].itemStyle=jsondec;
	arr1[0].isExpand=false;
	console.log(arr1[0]);
	var arr2 = [ {
		"name" : "故障工单",
		"children" : ""
	}, {
		"name" : "家宽投诉工单",
		"children" : ""
	}, {
		name : "通用任务工单",
		children : ""
	},{
		name : "平台功能",
		children :""
	} ];
	var arr3 = [ {
		"name" : "我",
		"children" : ""
	} ];
	option = {
		title : {
			text : 'test'
		},
		            	toolbox: {
		            	show : true,
		            	feature : {
		            	mark : {show: true},
		            	dataView : {show: true, readOnly: false},
		            	restore : {show: true},
		            	saveAsImage : {show: true}
		            	}
		            	},
		series : [ {
			name:'树图',
            type:'tree',
            orient: 'horizontal',  // vertical horizontal
            rootLocation: {x: 100, y: '60%'}, // 根节点位置  {x: 'center',y: 10}
            nodePadding: 20,
            symbol: 'circle',
            symbolSize: 40,
            itemStyle: {
                normal: {
                    label: {
                        show: true,
                        position: 'inside',
                        textStyle: {
                            color: '#cc9999',
                            fontSize: 15,
                            fontWeight:  'bolder'
                        }
                    },
                    lineStyle: {
                        color: '#000',
                        width: 1,
                        type: 'broken' // 'curve'|'broken'|'solid'|'dotted'|'dashed'
                    }
                },
                emphasis: {
                    label: {
                        show: true
                    }
                }
            },
			data : arr1
		} ]
	};
	myCharts.setOption(option);
	myCharts.on('click', function(params) {
		var aname = params.name;
		//判断点击的节点名称 
		if (aname == "电子运维系统\n(EOMS)") {
			//判断节点下的children是不是空 空展开即加入数据，不为空关闭
			if (params.data.children == "") {
				arr1[0].children = arr2;
			} else {
				arr1[0].children = "";
			}
		} else if (aname == "爸爸") {

			if (params.data.children == "") {
				arr2[0].children = arr3;
			} else {
				arr2[0].children = "";
			}
		}
		myCharts.setOption(option, true);
	})

});
