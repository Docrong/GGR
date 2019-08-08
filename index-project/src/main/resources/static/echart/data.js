//data
var data = [
    {
        name: '电子运维系统\n(EOMS)',
        value: '电子运维系统',
        symbol: 'rectangle',
        symbolSize: [80, 60],
        smooth: true,
        //barBorderRadius: [5, 5, 10, 10],
        itemStyle: {
            normal: {
                label: {
                    show: true,
                    textStyle: {
                        align: 'center',
                        verticalAlign: 'middle'
                    }
                },
                color: '#fff',
                borderWidth: 2,
                borderColor: '#EEEEEE'
            }
        },
        children: [
            {
                name: '故障工单',
                value: '故障工单',
                symbol: 'rectangle',
                symbolSize: [80, 40],
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            textStyle: {
                                align: 'center',
                                verticalAlign: 'middle'
                            }
                        },
                        color: '#fff',
                        borderWidth: 2,
                        borderColor: '#EEEEEE'
                    }
                },
                isExpand: false,
                children: [
                    {
                        name: '接口',
                        symbol: 'rectangle',
                        symbolSize: [80, 40],
                        value: '接口',
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle: {
                                        align: 'center',
                                        verticalAlign: 'middle'
                                    }
                                },
                                color: '#fff',
                                borderWidth: 2,
                                borderColor: '#EEEEEE'
                            }
                        },
                        children: [
                            {
                                name: '接口2',
                                symbol: 'rectangle',
                                symbolSize: [80, 40],
                                value: '接口2',
                                itemStyle: {
                                    normal: {
                                        label: {
                                            show: true,
                                            textStyle: {
                                                align: 'center',
                                                verticalAlign: 'middle'
                                            }
                                        },
                                        color: '#fff',
                                        borderWidth: 2,
                                        borderColor: '#EEEEEE'
                                    }
                                },
                            }, {
                                name: '接口2',
                                symbol: 'rectangle',
                                symbolSize: [80, 40],
                                value: '接口2',
                                itemStyle: {
                                    normal: {
                                        label: {
                                            show: true,
                                            textStyle: {
                                                align: 'center',
                                                verticalAlign: 'middle'
                                            }
                                        },
                                        color: '#fff',
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
                        symbolSize: [80, 40],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle: {
                                        align: 'center',
                                        verticalAlign: 'middle'
                                    }
                                },
                                color: '#fff',
                                borderWidth: 2,
                                borderColor: '#EEEEEE'
                            }
                        },
                        children: [
                            {
                                name: '接口2',
                                symbol: 'rectangle',
                                symbolSize: [80, 40],
                                value: '接口2',
                                itemStyle: {
                                    normal: {
                                        label: {
                                            show: true,
                                            textStyle: {
                                                align: 'center',
                                                verticalAlign: 'middle'
                                            }
                                        },
                                        color: '#fff',
                                        borderWidth: 2,
                                        borderColor: '#EEEEEE'
                                    }
                                },
                            }, {
                                name: '接口2',
                                symbol: 'rectangle',
                                symbolSize: [80, 40],
                                value: '接口2',
                                itemStyle: {
                                    normal: {
                                        label: {
                                            show: true,
                                            textStyle: {
                                                align: 'center',
                                                verticalAlign: 'middle'
                                            }
                                        },
                                        color: '#fff',
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
                        symbolSize: [80, 40],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle: {
                                        align: 'center',
                                        verticalAlign: 'middle'
                                    }
                                },
                                color: '#fff',
                                borderWidth: 2,
                                borderColor: '#EEEEEE'
                            }
                        },
                        children: [
                            {
                                name: '接口2',
                                symbol: 'rectangle',
                                symbolSize: [80, 40],
                                value: '接口2',
                                itemStyle: {
                                    normal: {
                                        label: {
                                            show: true,
                                            textStyle: {
                                                align: 'center',
                                                verticalAlign: 'middle'
                                            }
                                        },
                                        color: '#fff',
                                        borderWidth: 2,
                                        borderColor: '#EEEEEE'
                                    }
                                },
                            }, {
                                name: '接口2',
                                symbol: 'rectangle',
                                symbolSize: [80, 40],
                                value: '接口2',
                                itemStyle: {
                                    normal: {
                                        label: {
                                            show: true,
                                            textStyle: {
                                                align: 'center',
                                                verticalAlign: 'middle'
                                            }
                                        },
                                        color: '#fff',
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
                            textStyle: {
                                align: 'center',
                                verticalAlign: 'middle'
                            }
                        },
                        color: '#fff',
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
                            textStyle: {
                                align: 'center',
                                verticalAlign: 'middle'
                            }
                        },
                        color: '#fff',
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
                            textStyle: {
                                align: 'center',
                                verticalAlign: 'middle'
                            }
                        },
                        color: '#fff',
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