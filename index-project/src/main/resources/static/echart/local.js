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
        ]
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
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('mindPic')); 
                
                var option = {
                	    title : {
                	        //text: '手机品牌',
                	        //subtext: '线、节点样式'
                	    },
                	    tooltip : {
                	        trigger: 'item',
                	        formatter: "{b}: {c}"
                	    },
                	    toolbox: {
                	        show : true,
                	        feature : {
                	           // mark : {show: true},
                	            //dataView : {show: true, readOnly: false},
                	            //restore : {show: true},
                	            //saveAsImage : {show: true}
                	        }
                	    },
                	    calculable : false,

                	    series : [
                	        {
                	            name:'树图',
                	            type:'tree',
                	            orient: 'horizontal',  // vertical horizontal
                	            rootLocation: {x: 100, y: '60%'}, // 根节点位置  {x: 'center',y: 10}
                	            nodePadding: 20,
                	            symbol: 'roundRect',
                	            //symbolSize: 40,
                	            itemStyle: {
                	                normal: {
                	                    label: {
                	                        show: true,
                	                        position: 'inside',
                	                        textStyle: {
                	                            color: '#000000',
                	                            fontSize: 12,
                	                            fontWeight:  'bolder'
                	                        }
                	                    },
                	                    labelLine: {
                	                    	normal: {
                	                    		length: 80,
                    	                    	lineStyle :{
                    	                    		color : '#000000'
                    	                    	}
                	                    	}
                	                    },
                	                    lineStyle: {
                	                        color: '#EEEEEE',
                	                        width: 2,
                	                        type: 'broken' // 'curve'|'broken'|'solid'|'dotted'|'dashed'
                	                    }
                	                },
                	                emphasis: {
                	                    label: {
                	                        show: true
                	                    }
                	                }
                	            },
                	            data: data,
                	        }
                	    ]
                	};
                	                            
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
