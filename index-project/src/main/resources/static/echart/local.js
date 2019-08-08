//datadec
var str = "{normal:{label:{show:true,textStyle:{align:'center',verticalAlign:'middle'}},color:'#fff',borderWidth: 2,borderColor: '#EEEEEE'}}";

var jsondec = eval('(' + str + ')');
//	console.log(jsondec);

// 路径配置
require.config({
    paths: {
        echarts: 'echart/build/dist'
    }
});

// 使用
require(['echarts', 'echarts/chart/tree' // 使用柱状图就加载bar模块，按需加载
], function (ec) {
    var myCharts = ec.init(document.getElementById('swdt'));
    var arr1 = [{
        "name": "电子运维系统\n(EOMS)",
        "children": "",
    }];
//	arr1[0].symbol='rectangle';
//	arr1[0].symbolSize= [80, 30];
//	arr1[0].smooth=true;
//	arr1[0].itemStyle=jsondec;
//	arr1[0].isExpand=false;
    console.log(arr1[0]);
    var arr2 = [{
        "name": "故障工单",
        "children": ""
    }, {
        "name": "家宽投诉工单",
        "children": ""
    }, {
        name: "通用任务工单",
        children: ""
    }, {
        name: "平台功能",
        children: ""
    }];

    for (var i = 0; i < arr2.length; i++) {
//		arr2[i].symbol='rectangle';
//		arr2[i].symbolSize= [80, 30];
//		arr2[i].smooth=true;
//		arr2[i].itemStyle=jsondec;
//		arr2[i].isExpand=false;
    }

    var arr3 = [{
        "name": "接口",
        "children": ""
    }, {
        name: "处理",
        children: ""
    }, {
        name: "流程",
        children: ""
    }, {
        name: "查询",
        children: ""
    }, {
        name: "统计",
        children: ""
    }, {
        name: "视图",
        children: ""
    }, {
        name: "定时任务",
        children: ""
    }];

    for (var i = 0; i < arr3.length; i++) {
//		arr3[i].symbol='rectangle';
//		arr3[i].symbolSize= [80, 30];
//		arr3[i].smooth=true;
//		arr3[i].itemStyle=jsondec;
//		arr3[i].isExpand=false;
    }

    option = {
        title: {
            text: 'test'
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [{
            name: '树图',
            type: 'tree',
            orient: 'horizontal',  // vertical horizontal
            rootLocation: {x: 100, y: '60%'}, // 根节点位置  {x: 'center',y: 10}
            nodePadding: 30,
            layerPadding: 100,
            hoverable: false,
            symbol: 'circle',
            symbolSize: 20,
            roam: true,
            itemStyle: {
                color: '#FFFFFF',
                normal: {
                    label: {
                        show: true,
                        position: 'inside',
                        textStyle: {
                            color: '#cc9999',
                            fontSize: 15,
                            fontWeight: 'bolder'
                        }
                    },
                    lineStyle: {
                        color: '#000',
                        width: 1,
                        type: 'broken' //折线 'curve'|'broken'|'solid'|'dotted'|'dashed'
                    }
                },
                emphasis: {
                    label: {
                        show: false
                    }
                }
            },
            data: arr1
        }]
    };
    myCharts.setOption(option);
    myCharts.on('click', function (params) {
        console.log(params);
        var aname = params.name;
        //判断点击的节点名称 
        if (aname == "电子运维系统\n(EOMS)") {
            //判断节点下的children是不是空 空展开即加入数据，不为空关闭
            if (params.data.children == "") {
                arr1[0].children = arr2;
            } else {
                arr1[0].children = "";
            }
        } else if (aname == "故障工单") {

            if (params.data.children == "") {
                arr2[0].children = arr3;
            } else {
                arr2[0].children = "";
            }
        } else if (aname == '家宽投诉工单') {

        } else if (aname == '通用任务工单') {

        } else if (aname == '平台功能') {

        } else if (aname == '2') {

        }
        myCharts.setOption(option, true);
    })

});
