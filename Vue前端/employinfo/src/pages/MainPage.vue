<template>  
<div class="main-page">
  <div class="info-min-page" v-if="isShow" id="project-info">
    这里是一个显示信息的地方
  </div>
  <!-- 头部信息 -->
  <div class="header">
    <div class="title">
      招聘信息分析
    </div>
    <el-button id="project-info-btn" @click.stop="showProjectInfo" icon="el-icon-s-order">项目简介</el-button>
    <el-button id="project-info-btn" @click.stop="showProjectInfo" icon="el-icon-s-custom">人员信息</el-button>
    <div class="input-box">
        
        <!-- <input-com @handleinputc="handleinputp"></input-com> -->
        <div id="button-info">
          点击进入个人定制页面：
        </div>
        <el-button id="button-to-sub" icon="el-icon-right" circle @click="back"></el-button>
    </div>
  </div>
  <!-- Echart表格 -->
  <div class="echart-graph">
    <div class="row" id="row1">

      <div class="graph" id="graph1">
        <div class="graph-title" id="graph1-title">
          各个城市平均薪资柱状图
        </div>
        <div class="echart" id="graph1-chart"></div>
      </div>
      
      <div class="graph" id="graph2">
        <div class="graph-title" id="graph2-title">
          各个岗位的需求图
        </div>
        <div class="echart" id="graph2-chart">
        </div>
      </div>
      
      <div class="graph" id="graph3">
        <div class="graph-title" id="graph3-title">
          Graph3
        </div>
        <div class="echart" id="graph3-chart">
        </div>
      </div>
      
    </div>
    <div class="row" id="row2">
      
      <div class="graph" id="graph4">
        <div class="graph-title" id="graph4-title">
          Graph4
        </div>
        <div class="echart" id="graph4-chart">
        </div>
      </div>
      
      <div class="graph" id="graph5">
        <div class="graph-title" id="graph5-title">
          Graph5
        </div>
        <div class="echart" id="graph5-chart">
        </div>
      </div>
      <div class="graph" id="graph6">
        <div class="graph-title" id="graph6-title">
          Graph6
        </div>
        <div class="echart" id="graph6-chart">
        </div>
      </div>
    </div>
  </div>
</div>
</template>
<script>
import Input from "../components/Input"

export default {
  name : 'MainPage',
  components : {
    InputCom : Input
  },
  data : function(){
    return {
      isShow: false,
      graph1Info: {},
      graph2Info: {},
      graph3Info: {},
      graph4Info: {},
      graph5Info: {},
      graph6Info: {},
      isGraph: false
    }
  },
  created: function(){

    // 获取echart表的数据（表1）
    {
      var dataAxis = ['点', '击', '柱', '子', '或', '者', '两', '指', '在', '触', '屏', '上', '滑', '动', '能', '够', '自', '动', '缩', '放'];
      var data = [220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122, 133, 334, 198, 123, 125, 220];
      var yMax = 500;
      var dataShadow = [];
      for(var i = 0; i < data.length; i++){
        dataShadow.push(yMax);
      }
      this.graph1Info = {
      title: {
        text: '特性示例：渐变色 阴影 点击缩放',
        subtext: 'Feature Sample: Gradient Color, Shadow, Click Zoom'
      },
      xAxis: {
        data: dataAxis,
        axisLabel: {
            inside: true,
            textStyle: {
                color: '#fff'
            }
        },
        axisTick: {
            show: false
        },
        axisLine: {
            show: false
        },
        z: 10
      },
      yAxis: {
        axisLine: {
            show: false
        },
        axisTick: {
            show: false
        },
        axisLabel: {
            textStyle: {
                color: '#999'
            }
        }
      },
      dataZoom: [
        {
            type: 'inside'
        }
      ],
      series: [
        {
            type: 'bar',
            showBackground: true,
            itemStyle: {
                color: new this.$echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#83bff6'},
                        {offset: 0.5, color: '#188df0'},
                        {offset: 1, color: '#188df0'}
                    ]
                )
            },
            emphasis: {
                itemStyle: {
                    color: new this.$echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            {offset: 0, color: '#2378f7'},
                            {offset: 0.7, color: '#2378f7'},
                            {offset: 1, color: '#83bff6'}
                        ]
                    )
                }
            },
            data: data
        }
    ]
      };
    }
    {
      this.graph2Info = {
        legend: {
        top: 'bottom'
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
      series: [
          {
              name: '面积模式',
              type: 'pie',
              radius: [30, 150],
              center: ['50%', '50%'],
              roseType: 'area',
              itemStyle: {
                  borderRadius: 8
              },
              data: [
                  {value: 40, name: 'rose 1'},
                  {value: 38, name: 'rose 2'},
                  {value: 32, name: 'rose 3'},
                  {value: 30, name: 'rose 4'},
                  {value: 28, name: 'rose 5'},
                  {value: 26, name: 'rose 6'},
                  {value: 22, name: 'rose 7'},
                  {value: 18, name: 'rose 8'}
              ]
          }
      ]
      }
    }

  },
  mounted: function(){
    this.loadGraph1();
    this.loadGraph2();
    document.addEventListener('click', this.handleBodyClick)
  },
  destroyed: function(){
    document.removeEventListener('click', this.handleBodyClick)
  },
  methods : {
    handleinputp : function(inputValue){
      console.log("调用了enter函数");
      console.log(inputValue);
    },
    back : function(){
      this.$router.replace('/sub')
    },
    loadGraph1: function(){
      var chartDom = document.getElementById("graph1-chart");
      var myChart = this.$echarts.init(chartDom);
      var zoomSize = 6;
      myChart.on('click',function(params){
        console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
      myChart.dispatchAction({
        type: 'dataZoom',
        startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
        endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.
        length - 1)]
    });
      });
      this.graph1Info && myChart.setOption(this.graph1Info);
    },
    loadGraph2: function(){
      var chartDom = document.getElementById('graph2-chart');
      var myChart = this.$echarts.init(chartDom);
      this.graph2Info && myChart.setOption(this.graph2Info);
    },
    showProjectInfo: function(){
      this.isShow = !this.isShow;
    },
    handleBodyClick: function(){
      if(this.isShow == true){
        this.isShow = false;
      }
    }
  }
}

</script>

<style scoped>
  
  @import url('../css/mainpage.css');
  
</style>