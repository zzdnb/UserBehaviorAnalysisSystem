/**
 * Created by 30947 on 2018/7/18.
 */


//统计分析图
fetch('http://localhost:8080/static/result/selectDailyRatio.json', {
    method: "GET",
}).then(function (response) {
    // 拿到响应数据并序列化成json
    return response.json();
}).then(function (res) {
    var myChart = echarts.init($("#char1")[0]);

    option = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'right',
            textStyle : {
                color : '#ffffff',

            },
            data:['日报','周报']
        },

        calculable : false,
        series : [
            {
                name:'日周报',
                type:'pie',
                radius : ['40%', '70%'],
                itemStyle : {
                    normal : {
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    },
                    emphasis : {
                        label : {
                            show : true,
                            position : 'center',
                            textStyle : {
                                fontSize : '20',
                                fontWeight : 'bold'
                            }
                        }
                    }
                },
                data:[
                    {value:76977, name:'日报'},
                    {value:12542, name:'周报'}
                ]
            }
        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () {myChart.resize();})

});
//统计分析图
fetch('http://localhost:8080/static/result/selectDailyTrafficPeak.json', {
    method: "GET",
}).then(function (response) {
    // 拿到响应数据并序列化成json
    return response.json();
}).then(function (res) {

    var myChart = echarts.init($("#char2")[0]);

    option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {show:'true',borderWidth:'0'},
        legend: {
            data:['每日高峰期'],
            textStyle : {
                color : '#ffffff',

            }
        },

        calculable :false,
        xAxis : [
            {
                type : 'value',
                axisLabel: {
                    show: false,
                    textStyle: {
                        color: '#fff'
                    }
                },
                splitLine:{
                    lineStyle:{
                        color:['#f2f2f2'],
                        width:3,
                        type:'solid'
                    }
                }

            }
        ],
        yAxis : [
            {
                type : 'category',
                data : ['时间'],
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                splitLine:{
                    lineStyle:{
                        width:0,
                        type:'solid'
                    }
                }
            }
        ],
        series : [
            {
                name:'18:00',
                type:'bar',
                stack: '总量',
                itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                data:[21410]
            }

        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () {myChart.resize();})

});
//统计分析图
fetch('http://localhost:8080/static/result/selectDailySubmitNum.json', {
    method: "GET",
}).then(function (response) {
    // 拿到响应数据并序列化成json
    return response.json();
}).then(function (res) {

    var myChart = echarts.init($("#char3")[0]);

    option = {
        legend: {
            data:['24h提交数量'],
            textStyle : {
                color : '#ffffff',

            }
        },
        grid: {show:'true',borderWidth:'0'},

        calculable : false,
        tooltip : {
            trigger: 'axis',
            formatter: "一天 : <br/>{b}h : {c}条"
        },
        xAxis: {
            type: 'category',

            data: ['0', '1', '2', '3', '4', '5', '6', '7', '8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23']
        },
        yAxis: {
            type: 'value',
            axisLabel : {
                formatter: '{value} ',
                textStyle: {
                    color: '#fff'
                }
            }
        },
        series: [
            {
                data:[222,33,9,2,6,3,4,10,41,137,225,269,234,238,326,392,474,693,21410,18094,12769,10243,10976,11800],
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)'
                }
            }
        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () {myChart.resize();})

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
        grid: {show:'true',borderWidth:'0'},
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },

            formatter: function (params) {
                var tar = params[0];
                return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
            }
        },

        xAxis : [
            {
                type : 'category',
                splitLine: {show:false},
                data : ['客运车','危险品车','网约车','学生校车'],
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                }

            }
        ],
        yAxis : [
            {
                type : 'value',
                splitLine: {show:false},
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                }
            }
        ],
        series : [

            {
                name:'报警数量',
                type:'bar',
                stack: '总量',
                itemStyle : { normal: {label : {show: true, position: 'inside'}}},
                data:[2900, 1200, 300, 200]
            }
        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () {myChart.resize();})

});
