/**
 * Created by 30947 on 2018/7/18.
 */

//统计分析图
fetch('http://localhost:8080/table/Top10_Sum_Brand', {
    method: "GET",
}).then(function (response) {
    // 拿到响应数据并序列化成json
    return response.json();
}).then(function (res) {

    var myChart = echarts.init($("#char1")[0]);

    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'right',
            textStyle: {
                color: '#ffffff',

            },
            data: ['参加考试', '未参加考试']
        },

        calculable: false,
        series: [
            {
                name: '考试占比',
                type: 'pie',
                radius: ['40%', '70%'],
                itemStyle: {
                    normal: {
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        }
                    },
                    emphasis: {
                        label: {
                            show: true,
                            position: 'center',
                            textStyle: {
                                fontSize: '20',
                                fontWeight: 'bold'
                            }
                        }
                    }
                },
                data: [
                    {value: 42403, name: '参加考试'},
                    {value: 11280, name: '未参加考试'}

                ]
            }
        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () {
        myChart.resize();
    })

});

//统计分析图
fetch('http://localhost:8080/table/Top10_Sum_Brand', {
    method: "GET",
}).then(function (response) {
    // 拿到响应数据并序列化成json
    return response.json();
}).then(function (res) {

    var myChart = echarts.init($("#char2")[0]);

    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {show: 'true', borderWidth: '0'},
        legend: {
            data: ['主观成绩前五学生'],
            textStyle: {
                color: '#ffffff',

            }
        },

        calculable: false,
        xAxis: [
            {
                type: 'value',
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                splitLine: {
                    lineStyle: {
                        color: ['#f2f2f2'],
                        width: 0,
                        type: 'solid'
                    }
                }

            }
        ],
        yAxis: [
            {
                type: 'category',
                data: ['第一名','第二名','第三名','第四名','第五名' ],
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                splitLine: {
                    lineStyle: {
                        width: 0,
                        type: 'solid'
                    }
                }
            }
        ],
        series: [
            {
                name: '行驶',
                type: 'bar',
                stack: '总量',
                itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                data: [1790, 3035, 2593, 3028,1415]
            },


        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () {
        myChart.resize();
    })

});
//统计分析图
fetch('http://localhost:8080/table/Top10_Sum_Brand', {
    method: "GET",
}).then(function (response) {
    // 拿到响应数据并序列化成json
    return response.json();
}).then(function (res) {

    var myChart = echarts.init($("#char3")[0]);

    option = {
        legend: {
            data: ['考试成绩前五'],
            textStyle: {
                color: '#ffffff',

            }
        },
        grid: {show: 'true', borderWidth: '0'},

        calculable: false,
        tooltip: {
            trigger: 'axis',
            formatter: " <br/>学生号{b}"
        },
        xAxis: [
            {
                type: 'value',
                axisLabel: {
                    formatter: '{value}',
                    textStyle: {
                        color: '#fff'
                    }
                },

                splitLine: {
                    lineStyle: {
                        width: 0,
                        type: 'solid'
                    }
                }
            }
        ],
        yAxis: [
            {
                type: 'category',
                axisLine: {onZero: false},
                axisLabel: {
                    formatter: '{value}',
                    textStyle: {
                        color: '#fff'
                    }
                },
                splitLine: {
                    lineStyle: {
                        width: 0,
                        type: 'solid'
                    }
                },
                boundaryGap: false,
                data: ['3057','3070','3056','2534','3066']
            }
        ],
        series: [
            {
                name: '学生号',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        lineStyle: {
                            shadowColor: 'rgba(0,0,0,0.4)'
                        }
                    }
                },
                data: [3057,3070,3056,2534,3066]
            }
        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () {
        myChart.resize();
    })

});

//统计分析图
fetch('http://localhost:8080/table/Top10_Sum_Brand', {
    method: "GET",
}).then(function (response) {
    // 拿到响应数据并序列化成json
    return response.json();
}).then(function (res) {
    var myChart = echarts.init($("#char4")[0]);

    option = {
        grid: {show: 'true', borderWidth: '0'},
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },

            formatter: function (params) {
                var tar = params[0];
                return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
            }
        },

        xAxis: [
            {
                type: 'category',
                splitLine: {show: false},
                data: ['学生号3380', '学号4023', '学生号4030', '学生号4024','学生号4019'],
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                }

            }
        ],
        yAxis: [
            {
                type: 'value',
                splitLine: {show: false},
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                }
            }
        ],
        series: [

            {
                name: '考试次数',
                type: 'bar',
                stack: '总量',
                itemStyle: {normal: {label: {show: true, position: 'inside'}}},
                data: [122, 116, 116, 115, 114]
            }
        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () {
        myChart.resize();
    })

});

//统计分析图
fetch('http://localhost:8080/table/Top10_Sum_Brand', {
    method: "GET",
}).then(function (response) {
    // 拿到响应数据并序列化成json
    return response.json();
}).then(function (res) {

    var myChart = echarts.init($("#char5")[0]);

    option = {
        grid: {show: 'true', borderWidth: '0'},
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },

            formatter: function (params) {
                var tar = params[0];
                return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
            }
        },

        xAxis: [
            {
                type: 'category',
                splitLine: {show: false},
                data: ['试卷910', '试卷905', '试卷898', '试卷894','试卷749'],
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                }

            }
        ],
        yAxis: [
            {
                type: 'value',
                splitLine: {show: false},
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                }
            }
        ],
        series: [

            {
                name: '提交数量',
                type: 'bar',
                stack: '总量',
                itemStyle: {normal: {label: {show: true, position: 'inside'}}},
                data: [260, 260, 259, 255, 136]
            }
        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () {
        myChart.resize();
    })

});