        //data
        var data=[
        {
                name: '电子运维系统\n(EOMS)',
                value: '电子运维系统',
                symbol: 'rectangle',
                symbolSize: [80, 60],
                smooth:true,
                //barBorderRadius: [5, 5, 10, 10],
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            textStyle:{
                                align:'center',
                                verticalAlign:'middle'
                            }
                        },
                        color:'#fff',
                        borderWidth: 2,
                        borderColor: '#EEEEEE'
                    }
                },
                children: [
                    {
                        name: '故障工单',
                        value: '故障工单',
                        symbol: 'rectangle',
                        symbolSize: [80,40],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle:{
                                        align:'center',
                                        verticalAlign:'middle'
                                    }
                                },
                                color:'#fff',
                                borderWidth: 2,
                                borderColor: '#EEEEEE'
                            }
                        },
                        isExpand: false,
                        children: [
                            {
                                name: '接口',
                                symbol: 'rectangle',
                                symbolSize: [80,40],
                                value: '接口',
                                itemStyle: {
                                    normal: {
                                        label: {
                                            show: true,
                                            textStyle:{
                                                align:'center',
                                                verticalAlign:'middle'
                                            }
                                        },
                                        color:'#fff',
                                        borderWidth: 2,
                                        borderColor: '#EEEEEE'
                                    }
                                },
                                children:[
                                	{
                                		 name: '接口2',
                                         symbol: 'rectangle',
                                         symbolSize: [80,40],
                                         value: '接口2',
                                         itemStyle: {
                                             normal: {
                                                 label: {
                                                     show: true,
                                                     textStyle:{
                                                         align:'center',
                                                         verticalAlign:'middle'
                                                     }
                                                 },
                                                 color:'#fff',
                                                 borderWidth: 2,
                                                 borderColor: '#EEEEEE'
                                             }
                                         },
                                	},{
                               		 name: '接口2',
                                     symbol: 'rectangle',
                                     symbolSize: [80,40],
                                     value: '接口2',
                                     itemStyle: {
                                         normal: {
                                             label: {
                                                 show: true,
                                                 textStyle:{
                                                     align:'center',
                                                     verticalAlign:'middle'
                                                 }
                                             },
                                             color:'#fff',
                                             borderWidth: 2,
                                             borderColor: '#EEEEEE'
                                         }
                                     },
                            	},
                                		
                                ]
                            },
                            {
                                name: '处理',
                                value: '处理',
                                symbol: 'rectangle',
                                symbolSize: [80,40],
                                itemStyle: {
                                    normal: {
                                        label: {
                                            show: true,
                                            textStyle:{
                                                align:'center',
                                                verticalAlign:'middle'
                                            }
                                        },
                                        color:'#fff',
                                        borderWidth: 2,
                                        borderColor: '#EEEEEE'
                                    }
                                },
                                children:[
                                	{
                                		 name: '接口2',
                                         symbol: 'rectangle',
                                         symbolSize: [80,40],
                                         value: '接口2',
                                         itemStyle: {
                                             normal: {
                                                 label: {
                                                     show: true,
                                                     textStyle:{
                                                         align:'center',
                                                         verticalAlign:'middle'
                                                     }
                                                 },
                                                 color:'#fff',
                                                 borderWidth: 2,
                                                 borderColor: '#EEEEEE'
                                             }
                                         },
                                	},{
                               		 name: '接口2',
                                     symbol: 'rectangle',
                                     symbolSize: [80,40],
                                     value: '接口2',
                                     itemStyle: {
                                         normal: {
                                             label: {
                                                 show: true,
                                                 textStyle:{
                                                     align:'center',
                                                     verticalAlign:'middle'
                                                 }
                                             },
                                             color:'#fff',
                                             borderWidth: 2,
                                             borderColor: '#EEEEEE'
                                         }
                                     },
                            	},
                                		
                                ]
                            },
                            {
                                name: '流程',
                                value: '流程',
                                symbol: 'rectangle',
                                symbolSize: [80,40],
                                itemStyle: {
                                    normal: {
                                        label: {
                                            show: true,
                                            textStyle:{
                                                align:'center',
                                                verticalAlign:'middle'
                                            }
                                        },
                                        color:'#fff',
                                        borderWidth: 2,
                                        borderColor: '#EEEEEE'
                                    }
                                },
                                children:[
                                	{
                               		 name: '接口2',
                                        symbol: 'rectangle',
                                        symbolSize: [80,40],
                                        value: '接口2',
                                        itemStyle: {
                                            normal: {
                                                label: {
                                                    show: true,
                                                    textStyle:{
                                                        align:'center',
                                                        verticalAlign:'middle'
                                                    }
                                                },
                                                color:'#fff',
                                                borderWidth: 2,
                                                borderColor: '#EEEEEE'
                                            }
                                        },
                               	},{
                              		 name: '接口2',
                                    symbol: 'rectangle',
                                    symbolSize: [80,40],
                                    value: '接口2',
                                    itemStyle: {
                                        normal: {
                                            label: {
                                                show: true,
                                                textStyle:{
                                                    align:'center',
                                                    verticalAlign:'middle'
                                                }
                                            },
                                            color:'#fff',
                                            borderWidth: 2,
                                            borderColor: '#EEEEEE'
                                        }
                                    },
                           	},
                               		
                               ]
                            }
                        ]
                    },
                    {
                        name: '家宽投诉工单',
                        symbol: 'rectangle',
                        symbolSize: [80, 40],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle:{
                                        align:'center',
                                        verticalAlign:'middle'
                                    }
                                },
                                color:'#fff',
                                borderWidth: 2,
                                borderColor: '#EEEEEE'
                            }
                        },
                        value: '家宽投诉工单',
                    },
                    {
                        name: '通用任务工单',
                        symbol: 'rectangle',
                        symbolSize: [80, 40],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle:{
                                        align:'center',
                                        verticalAlign:'middle'
                                    }
                                },
                                color:'#fff',
                                borderWidth: 2,
                                borderColor: '#EEEEEE'
                            }
                        },
                        value: '通用任务工单',
                    },
                    {
                        name: '平台功能',
                        symbol: 'rectangle',
                        symbolSize: [80, 40],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle:{
                                        align:'center',
                                        verticalAlign:'middle'
                                    }
                                },
                                color:'#fff',
                                borderWidth: 2,
                                borderColor: '#EEEEEE'
                            }
                        },
                        value: '平台功能'
                    }
                ]
            }
        ];
        //
        console.log(data);
        // 路径配置
        require.config({
            paths: {
                echarts: 'echart/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/tree' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
            	var myCharts = ec.init(document.getElementById('swdt'));
            	var arr1=[{"name":"爷爷","children":""}] ;
            	var arr2=[{"name":"爸爸","children":""},{"name":"叔叔","children":""}];
            	var arr3=[{"name":"我","children":""}] ; 
            	option = {
            	title : {
            	text: '专项救治'
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
            	series : [
            	{
            	name:'专项救治',
            	type:'tree',
            	orient: 'horizontal',  // vertical horizontal
            	rootLocation: {x: 100,y: 230}, // 根节点位置  {x: 100, y: 'center'}
            	nodePadding: 8,
            	layerPadding: 200,
            	hoverable: false,
            	roam: true,
            	symbolSize: 6,
            	itemStyle: {
            	normal: {
            	color: '#4883b4',
            	label: {
            	show: true,
            	position: 'right',
            	formatter: "{b}",
            	textStyle: {
            	color: '#000',  
            	fontSize: 5
            	}
            	},
            	lineStyle: {
            	color: '#ccc',
            	type: 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'
            	}
            	},
            	emphasis: {
            	color: '#4883b4',
            	label: {
            	show: false
            	},
            	borderWidth: 0
            	}
            	},
            	data:arr1 
            	}
            	]
            	}; 
            	myCharts.setOption(option);
            	myCharts.on('click',function(params){
            	var aname =params.name;
            	//判断点击的节点名称 
            	if(aname=="爷爷"){
            	//判断节点下的children是不是空 空展开即加入数据，不为空关闭
            	if(params.data.children==""){
            	arr1[0].children=arr2;
            	}else{
            	arr1[0].children="";
            	}
            	}else if(aname=="爸爸"){

            	if(params.data.children==""){
            	arr2[0].children=arr3;
            	}else{
            	arr2[0].children="";
            	}
            	}
            	myCharts.setOption(option,true);
            	})


            }
        );
