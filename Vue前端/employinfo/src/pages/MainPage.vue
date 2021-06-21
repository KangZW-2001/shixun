<template>
  <div class="main-page">
    <div class="mask" v-show="isMainPage"></div>
    <div class="info-min-page" v-if="isShow" id="project-info">
      <p id="project-text">这里是一个显示信息的地方</p>
    </div>
    <!-- 头部信息 -->
    <div class="header">
      <div class="title">招聘信息分析</div>
      <!-- <el-button
        id="project-info-btn"
        @click.stop="showProjectInfo"
        icon="el-icon-s-order"
        >项目简介</el-button
      > -->
      <!-- <el-button
        id="project-info-btn"
        @click.stop="showProjectInfo"
        icon="el-icon-s-custom"
        >人员信息</el-button
      > -->
      <el-button id="project-info-btn" @click="drawer1 = true" icon="el-icon-s-order">项目简介</el-button>
      <el-button @click="drawer2 = true" icon="el-icon-s-custom">人员信息</el-button>

      <el-drawer
        title="项目信息"
        :visible.sync="drawer1"
        :direction="'ltr'"
        @closeDrawer = "closeDrawer">
        <span>我来啦!</span>
      </el-drawer>

      <el-drawer
        title="我是标题"
        :visible.sync="drawer2" 
        :direction="'ltr'"
        :withHeader = "false"
        @closeDrawer = "closeDrawer">
        <div class="drawer-title">人员信息</div>
        <div class="drawer-info">
          
        </div>
      </el-drawer>



      <div class="input-box">
        <!-- <input-com @handleinputc="handleinputp"></input-com> -->
        <div id="button-info">点击进入个人页面：</div>
        <el-button
          id="button-to-sub"
          icon="el-icon-right"
          circle
          @click="back"
        ></el-button>
      </div>
    </div>
    <!-- Echart表格 -->
    <!-- <div class="echart-graph">
      <div class="row" id="row1">
        <div class="graph" id="graph1">
          <div class="graph-title" id="graph1-title">
            各个城市平均薪资柱状图
          </div>
          <div class="echart" id="graph1-chart"></div>
        </div>

        <div class="graph" id="graph2">
          <div class="graph-title" id="graph2-title">各个岗位的需求图</div>
          <div class="echart" id="graph2-chart"></div>
        </div>

        <div class="graph" id="graph3">
          <div class="graph-title" id="graph3-title">公司规模分布图</div>
          <div class="echart" id="graph3-chart"></div>
        </div>
      </div>
      <div class="row" id="row2">
        <div class="graph" id="graph4">
          <div class="graph-title" id="graph4-title">Graph4</div>
          <div class="echart" id="graph4-chart"></div>
        </div>

        <div class="graph" id="graph5">
          <div class="graph-title" id="graph5-title">Graph5</div>
          <div class="echart" id="graph5-chart"></div>
        </div>
        <div class="graph" id="graph6">
          <div class="graph-title" id="graph6-title">Graph6</div>
          <div class="echart" id="graph6-chart"></div>
        </div>
      </div>
    </div>
     -->
    <div class="chart">
      <el-carousel :interval="4000" type="card" height="750px" :autoplay="false">
        <el-carousel-item v-for="(item,index) in graphIdArray" :key="item">
          <div class="graph" :id="'graph'+index">
            <!-- <div class="graph-title" id="graph1-title">
              各个城市平均薪资柱状图
            </div> -->
            <div class="echart" :id="item"></div>
          </div>
        </el-carousel-item>
      </el-carousel>
    </div>


  </div>
</template>
<script>
import Input from "../components/Input";
import "../../static/china.js"


export default {
  name: "MainPage",
  components: {
    InputCom: Input,
  },
  data: function () {
    return {
      drawer1: false,
      drawer2: false, 

      graphConfi:['1', '2', '3', '4', '5', '6','7','8','9','10'],
      graphIdArray: ['graph1-chart','graph2-chart','graph3-chart','graph4-chart','graph5-chart','graph6-chart','graph7-chart','graph8-chart','graph9-chart','graph10-chart'],
      
      isShow: false,
      // 公司规模分布 -> 柱形图
      comSizeInfo: {},
      // 公司性质分布 -> 和上面一样的柱形图
      comTypeInfo: {},
      // 学历等级的薪资分布情况

      // 经验等级的薪资分布情况

      // 各个职位都需要的技能统计

      // 所有职业的平均薪资图 -> 渐变柱状图
      averageSalaryInfo: {},
      // 招聘职位的占比图

      // 各个岗位薪资随时间变化图

      // 学历薪资随时间变化图

      // 地图，各省的平均薪资


      graph2Info: {},
      graph4Info: {},
      graph5Info: {},
      graph6Info: {},
      isGraph: false,
      isMainPage: false,
    };
  },
  created: function () {
    
    // 获取echarts表的数据（表3）
    // 公司规模的分布图
    {
      this.comSizeInfo = {
        backgroundColor: "#fff",
        dataset: {
          source: [
            ["score", "amount", "product"],
            [89.3, 58212, "Matcha Latte"],
            [57.1, 78254, "Milk Tea"],
            [74.4, 41032, "Cheese Cocoa"],
            [50.1, 12755, "Cheese Brownie"],
            [89.7, 20145, "Matcha Cocoa"],
            [68.1, 79146, "Tea"],
            [19.6, 91852, "Orange Juice"],
            [10.6, 101852, "Lemon Juice"],
            [32.7, 20112, "Walnut Brownie"],
          ],
        },
        grid: { containLabel: true },
        xAxis: { name: "amount" },
        yAxis: { type: "category" },
        visualMap: {
          orient: "horizontal",
          left: "center",
          min: 10,
          max: 100,
          text: ["High Score", "Low Score"],
          // Map the score column to color
          dimension: 0,
          inRange: {
            color: ["#65B581", "#FFCE34", "#FD665F"],
          },
        },
        series: [
          {
            type: "bar",
            encode: {
              // Map the "amount" column to X axis.
              x: "amount",
              // Map the "product" column to Y axis
              y: "product",
            },
          },
        ],
      };
    }
    
    // 公司性质分布



    // 获取echarts表的数据（表1）
    // 所有职业的平均薪资图
    {
      var dataAxis = [
        "点",
        "击",
        "柱",
        "子",
        "或",
        "者",
        "两",
        "指",
        "在",
        "触",
        "屏",
        "上",
        "滑",
        "动",
        "能",
        "够",
        "自",
        "动",
        "缩",
        "放",
      ];
      var data = [
        220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122,
        133, 334, 198, 123, 125, 220,
      ];
      var yMax = 500;
      var dataShadow = [];
      for (var i = 0; i < data.length; i++) {
        dataShadow.push(yMax);
      }
      this.averageSalaryInfo = {
        backgroundColor: "#fff",
        title: {
          text: "特性示例：渐变色 阴影 点击缩放",
          subtext: "Feature Sample: Gradient Color, Shadow, Click Zoom",
        },
        xAxis: {
          data: dataAxis,
          axisLabel: {
            inside: true,
            textStyle: {
              color: "#fff",
            },
          },
          axisTick: {
            show: false,
          },
          axisLine: {
            show: false,
          },
          z: 10,
        },
        yAxis: {
          axisLine: {
            show: false,
          },
          axisTick: {
            show: false,
          },
          axisLabel: {
            textStyle: {
              color: "#999",
            },
          },
        },
        dataZoom: [
          {
            type: "inside",
          },
        ],
        series: [
          {
            type: "bar",
            showBackground: true,
            itemStyle: {
              color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                { offset: 0, color: "#83bff6" },
                { offset: 0.5, color: "#188df0" },
                { offset: 1, color: "#188df0" },
              ]),
            },
            emphasis: {
              itemStyle: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  { offset: 0, color: "#2378f7" },
                  { offset: 0.7, color: "#2378f7" },
                  { offset: 1, color: "#83bff6" },
                ]),
              },
            },
            data: data,
          },
        ],
      };
    }
    // 获取echarts表的数据（表2）

    {
      this.graph2Info = {
        backgroundColor: "#fff",
        legend: {
          top: "bottom",
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true },
          },
        },
        series: [
          {
            name: "面积模式",
            type: "pie",
            radius: [30, 150],
            center: ["50%", "50%"],
            roseType: "area",
            itemStyle: {
              borderRadius: 8,
            },
            data: [
              { value: 40, name: "rose 1" },
              { value: 38, name: "rose 2" },
              { value: 32, name: "rose 3" },
              { value: 30, name: "rose 4" },
              { value: 28, name: "rose 5" },
              { value: 26, name: "rose 6" },
              { value: 22, name: "rose 7" },
              { value: 18, name: "rose 8" },
            ],
          },
        ],
      };
    }
    // 获取echarts表的数据（表4）
    // 中国地图，每一个省份的岗位数量
    {
      let testData = [{'name':"上海","value":318},{'name':"云南","value":520}]
      this.graph4Info= { // 进行相关配置
          title:{
            text: "职位数量图",
            x: "center"
          },
          backgroundColor: '#FFF',//地图背景色
          tooltip: {
            trigger: "item"
          },
          visualMap: {
            show: true,
            x: "left",
            y: "bottom",
            textStyle: {
              fontSize: 16,
            },
            splitList:[
              {start: 1, end: 9},
              {start: 10, end: 99},
              {start: 100, end: 999},
              {start: 1000, end: 9999},
              {start: 10000},
            ]
          },
          // geo: { // 这个是重点配置区
          //   name: "职位数量",
          //   map: 'china', // 表示中国地图
          //   itemStyle: {
          //     normal: {
          //       borderWidth: .5,
          //       borderColor: "#009fe8",
          //       areaColor: "#ffefd5"
          //     },
          //     emphasis: {
          //       borderWidth: .5,
          //       borderColor: "4b0082",
          //       areaColor: "#026295"
          //     }
          //   },
          //   label: {
          //     normal:{
          //       show: true, // 是否显示对应地名
          //       textStyle: { //字体颜色
          //         color: '#797979'
          //       },
          //       // fontSize: 8
          //     },
          //     emphasis: {
          //       show: false,
          //       textStyle: {
          //         color: '#fff'
          //       }
          //     }
          //   },
          //   // data: testData,
          //   roam: false,
          // },
          //滑动显示数据
          // tooltip: {
          //     trigger: 'item',
          //     formatter: function(params) {
          //         return params.name + ' 已接入: ' + params.data.num[2];
          //     }
          // },
          series: [{
              name: '职位数量',
              type: 'map',
              mapType: "china",
              roam: false,
              // coordinateSystem: 'geo',
              // color:"#e1ebe3",//点的颜色
              // data: convertData(data),
              // symbolSize: 25,//点的大小
              // symbol:"pin",//点的样式
              // cursor:"pointer",//鼠标放上去的效果
              itemStyle: {
                normal: {
                  borderWidth: .5,
                  borderColor: "#009fe8",
                  areaColor: "#ffefd5",
                },
                emphasis: {
                  borderWidth: .5,
                  borderColor: '#5c8f6e',
                  // borderWidth: 5
                  areaColor: "#fff"
                }
              },
              label: {
                normal: {//默认展示
                    show: true
                },
                emphasis: {//滑过展示
                    show: true
                }
              },
              data: testData
            }
            // ,
            // {
            //   type: 'map',
            //   map: 'china',
            //   geoIndex: 0,
            //   aspectScale: 0.75,
            //   tooltip: {
            //       show: false
            //   }
            // },
              
          ]
      }
    }
  },
  mounted: function () {
    this.loadGraph1();
    this.loadGraph2();
    this.loadGraph3();
    this.loadGraph4();
    document.addEventListener("click", this.handleBodyClick);
  },
  destroyed: function () {
    document.removeEventListener("click", this.handleBodyClick);
  },
  methods: {
    handleinputp: function (inputValue) {
      console.log("调用了enter函数");
      console.log(inputValue);
    },
    back: function () {
      this.$router.replace("/sub");
    },
    loadGraph1: function () {
      var chartDom = document.getElementById("graph1-chart");
      var myChart = this.$echarts.init(chartDom);
      var zoomSize = 6;
      myChart.on("click", function (params) {
        console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
        myChart.dispatchAction({
          type: "dataZoom",
          startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
          endValue:
            dataAxis[
              Math.min(params.dataIndex + zoomSize / 2, data.length - 1)
            ],
        });
      });
      this.averageSalaryInfo && myChart.setOption(this.averageSalaryInfo);
    },
    loadGraph2: function () {
      var chartDom = document.getElementById("graph2-chart");
      var myChart = this.$echarts.init(chartDom);
      this.graph2Info && myChart.setOption(this.graph2Info);
    },
    loadGraph3: function(){
      console.log("调用了配置graph3的函数")
      console.log(this.comSizeInfo);
      var chartDom = document.getElementById("graph3-chart");
      console.log(chartDom);
      var myChart = this.$echarts.init(chartDom);
      this.comSizeInfo && myChart.setOption(this.comSizeInfo);
    },
    loadGraph4: function(){
      var chartDom = document.getElementById("graph4-chart");
      var myChart = this.$echarts.init(chartDom);
      this.graph4Info && myChart.setOption(this.graph4Info);
    },
    showProjectInfo: function () {
      this.isShow = !this.isShow;
      console.log("调用显示info函数");
      if (this.isMainPage == false) {
        this.isMainPage = true;
      } else {
        this.isMainPage = false;
      }
    },
    handleBodyClick: function () {
      if (this.isShow == true) {
        this.isShow = false;
      }
      if (this.isMainPage == true) {
        this.isMainPage = false;
      }
    },
    handleClose: function(){

    },
    closeDrawer: function(){
      this.drawer1 = false;
      this.drawer1 = false;
    }
  },
};
</script>

<style scoped>
@import url("../css/mainpage.css");
</style>