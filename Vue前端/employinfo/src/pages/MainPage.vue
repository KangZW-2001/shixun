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
      <el-button
        id="project-info-btn"
        @click="drawer1 = true"
        icon="el-icon-s-order"
        >项目简介</el-button
      >
      <el-button @click="drawer2 = true" icon="el-icon-s-custom"
        >人员信息</el-button
      >

      <el-drawer
        title="项目信息"
        :visible.sync="drawer1"
        :direction="'ltr'"
        :withHeader="false"
        @closeDrawer="closeDrawer"
      >
        <div class="drawer-title">项目简介</div>
        <div class="drawer-info">   
          <strong>基本信息：</strong>
          <br>
          职业推荐以及工薪预测项目是由来自天津大学的三位同学在实训期间协作开发的基于智联招聘的求职分析及预测系统。
        <br><br>
          <strong>使用技术</strong>
          <br>
          1. 爬虫部分：lxml，request
          <br>
          2. 数据库和后端部分：MySql、SpringBoot、Flask
          <br>
          3. 预测部分： sk-learn、pandas、numpy、gensim、matplotlib、flask、Cors
          <br>
          4. 前端部分：VUE框架、Echarts可视化图表、ElementUI组件
        <br><br>
          <strong>应用场景</strong>
          <br>
          该系统主要应用场景包括大学生求职，社会招聘求职等，主要功能包括各地区各职位职位数据的展示、职业工资预测分析等。
        <br><br>
          <strong>项目目的</strong>
          <br>
          1. 展示当前阶段互联网企业招聘的基本信息，包括每一种类型企业的工资、当前热门职业工资随着学历和工作经验的变化以及全国范围内每一个省市互联网行业的平均薪资。
          <br>
          2. 通过本系统可以实现对应聘者提供的各种详细信息进行分析，预测其在本阶段可以得到薪资以及在哪些城市工作更有前景等，为其选择职业提供帮助。
        </div>
      </el-drawer>

      <el-drawer
        title="我是标题"
        :visible.sync="drawer2"
        :direction="'ltr'"
        :withHeader="false"
        @closeDrawer="closeDrawer"
      >
        <div class="drawer-title">人员信息</div>
        <div class="drawer-info">
          <strong>学校：</strong><br>
          天津大学
          <br><br>
          <strong>学院：</strong><br>
          智能与计算学部
          <br><br>
          <strong>专业：</strong><br>
          软件工程专业
          <br><br>
          <strong>年级：</strong><br>
          2018级
          <br><br>
          <strong>组长：</strong><br>
          马力
          <br><br>
          <strong>组员：</strong><br>
          吕鸿江<br>
          康子文
          <br><br>
          
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
  
    <div class="chart">
      <el-carousel
        :interval="4000"
        type="card"
        height="750px"
        :autoplay="false"
      >
        <el-carousel-item v-for="(item, index) in graphIdArray" :key="item">
          <div class="graph" :id="'graph' + index">
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
import "../../static/china.js";

export default {
  name: "MainPage",
  components: {
    InputCom: Input,
  },
  data: function () {
    return {
      drawer1: false,
      drawer2: false,

      graphConfi: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"],
      graphIdArray: [
        "graph1-chart",
        "graph2-chart",
        "graph3-chart",
        "graph4-chart",
        "graph5-chart",
        "graph6-chart",
        "graph7-chart",
        "graph8-chart",
        "graph9-chart",
        "graph10-chart",
      ],

      isShow: false,
      // 公司规模分布
      comSizeInfo: {},
      // 公司性质分布
      comTypeInfo: {},
      // 学历等级随着工作年限的薪资分布情况
      salaryOfEdu: {},
      // 每个职位随着工作年限的的薪资分布情况
      salaryOfJob: {},
      // 各个职位都需要的技能统计
      skillsOfJob: {},
      // 所有职业的平均薪资图 ->
      averageSalaryInfo: {},
      // 不同性质公司的平均薪资图
      averageSalaryOfComType: {},
      // 招聘职位的占比
      ratioOfJob: {},

      // 学历薪资随时间变化图
      graph9Info: {},
      // 地图，各省的平均薪资
      chinaMapInfo: {},
      isGraph: false,
      isMainPage: false,
    };
  },
  created: function () {
    // 所有职业随着工作年限的图 表1
    {
      var app = {};
      var posList = [
        "left",
        "right",
        "top",
        "bottom",
        "inside",
        "insideTop",
        "insideLeft",
        "insideRight",
        "insideBottom",
        "insideTopLeft",
        "insideTopRight",
        "insideBottomLeft",
        "insideBottomRight",
      ];
      app.configParameters = {
        rotate: {
          min: -90,
          max: 90,
        },
        align: {
          options: {
            left: "left",
            center: "center",
            right: "right",
          },
        },
        verticalAlign: {
          options: {
            top: "top",
            middle: "middle",
            bottom: "bottom",
          },
        },
        position: {
          options: posList.reduce(function (map, pos) {
            map[pos] = pos;
            return map;
          }, {}),
        },
        distance: {
          min: 0,
          max: 100,
        },
      };
      app.config = {
        rotate: 90,
        align: "left",
        verticalAlign: "middle",
        position: "insideBottom",
        distance: 15,
        onChange: function () {
          var labelOption = {
            normal: {
              rotate: app.config.rotate,
              align: app.config.align,
              verticalAlign: app.config.verticalAlign,
              position: app.config.position,
              distance: app.config.distance,
            },
          };
          myChart.setOption({
            series: [
              {
                label: labelOption,
              },
              {
                label: labelOption,
              },
              {
                label: labelOption,
              },
              {
                label: labelOption,
              },
            ],
          });
        },
      };
      var labelOption = {
        show: false,
        position: app.config.position,
        distance: app.config.distance,
        align: app.config.align,
        verticalAlign: app.config.verticalAlign,
        rotate: app.config.rotate,
        formatter: "{c}  {name|{a}}",
        fontSize: 16,
        rich: {
          name: {
            color: "#000",
          },
        },
      };
      this.salaryOfJob = {
        title: {
          text: "各职位薪资随工作年限分布图",
          left: "center",
        },
        backgroundColor: "#fff",
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
        },
        legend: {
          data: [
            "Web",
            "Android",
            "Python",
            "通信",
            "C++",
            "硬件工程师",
            "PHP",
            "IT运维",
            "产品经理",
            "UI设计师",
            "大数据",
            "算法工程师",
            "Java",
            "游戏开发",
          ],
          selected: {
            Web: false,
            Android: false,
            通信: false,
            硬件工程师: false,
            IT运维: false,
            产品经理: false,
            UI设计师: false,
            大数据: false,
            游戏开发: false,
          },
          x: "right",
          y: "top",
          orient: "vertical",
        },
        toolbox: {
          show: true,
          orient: "vertical",
          left: "right",
          top: "bottom",
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ["line", "bar", "stack", "tiled"] },
            restore: { show: true },
            saveAsImage: { show: true },
          },
        },
        xAxis: [
          {
            type: "category",
            axisTick: { show: false },
            data: ["无经验", "1年以下", "1-3年", "3-5年", "5-10年", "10年以上"],
          },
        ],
        yAxis: [
          {
            type: "value",
          },
        ],
        series: [
          {
            name: "Web",
            type: "bar",
            barGap: 0,
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [10421, 7092, 10791, 13480, 17742, 18211],
          },
          {
            name: "Android",
            type: "bar",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [14745, 10413, 13556, 16843, 23113, 28366],
          },
          {
            name: "Python",
            type: "bar",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [11865, 8357, 13077, 17349, 20691, 36909],
          },
          {
            name: "通信",
            type: "bar",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [7484, 7447, 9722, 13792, 16503, 30866],
          },
          {
            name: "C++",
            type: "bar",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [12096, 10259, 13452, 16280, 20095, 25214],
          },
          {
            name: "硬件工程师",
            type: "bar",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [9557, 8606, 10738, 13991, 17520, 19502],
          },
          {
            name: "PHP",
            type: "bar",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [10593, 7746, 10158, 13913, 19541, 45000],
          },
          {
            name: "IT运维",
            type: "bar",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [7292, 5984, 8021, 11500, 16048, 19427],
          },
          {
            name: "产品经理",
            type: "bar",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [13326, 8429, 11331, 15114, 19427, 23893],
          },
          {
            name: "UI设计师",
            type: "bar",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [9288, 6556, 9148, 12914, 19812, 21195],
          },
          {
            name: "大数据",
            type: "bar",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [12204, 9109, 13222, 17126, 23279, 28469],
          },
          {
            name: "算法工程师",
            type: "bar",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [16176, 14474, 17579, 21135, 26378, 29575],
          },
          {
            name: "Java",
            type: "bar",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [8946, 6891, 10480, 13515, 16804, 20204],
          },
          {
            name: "游戏开发",
            type: "bar",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: [12763, 8285, 12637, 18428, 26836, 40750],
          },
        ],
      };
    }

    // 公司性质分布 表2
    {
      this.comTypeInfo = {
        title: {
          text: "公司性质分布情况",
          left: "center",
        },
        backgroundColor: "#fff",
        dataset: {
          source: [
            ["score", "amount", "product"],
            [13969.5122, 139390, "民营"],
            [11109.1304, 29089, "上市公司"],
            [14569.7674, 23812, "股份制企业"],
            [14172.6524, 15503, "国企"],
            [14277.432, 10402, "合资"],
            [13263.4813, 6004, "其他"],
            [15355.6957, 5832, "外商独资"],
            [14652.4018, 2531, "事业单位"],
            [11947.6363, 805, "港澳台公司"],
            [12537.5881, 82, "社会团体"],
            [11156.8649, 43, "代表处"],
          ],
        },
        grid: { containLabel: true },
        xAxis: { name: "amount" },
        yAxis: { type: "category" },
        visualMap: {
          orient: "horizontal",
          left: "center",
          min: 10,
          max: 16000,
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

    // 公司规模的分布图 表3
    {
      this.comSizeInfo = {
        title: {
          text: "公司规模分布情况",
          left: "center",
        },
        backgroundColor: "#fff",
        dataset: {
          source: [
            ["score", "amount", "product"],
            [10933.3545, 6292, "20人以下"],
            [11153.4317, 58217, "20-99人"],
            [11960.9449, 56550, "100-299人"],
            [12546.6878, 10874, "300-499人"],
            [13072.5825, 23302, "500-999人"],
            [14343.1177, 50998, "1000-9999人"],
            [17328.164, 26304, "10000人以上"],
          ],
        },
        grid: { containLabel: true },
        xAxis: { name: "amount" },
        yAxis: { type: "category" },
        visualMap: {
          orient: "horizontal",
          left: "center",
          min: 9000,
          max: 20000,
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

    // 每个职位需要的技能统计 表4
    {
      this.skillsOfJob = {
        backgroundColor: "#fff",

        title: {
          text: "职位需要的前几个技能",
          left: "center",
          textStyle: {
            color: "#000",
          },
        },

        tooltip: {
          trigger: "item",
        },

        visualMap: {
          show: false,
          min: 80,
          max: 600,
          inRange: {
            colorLightness: [0, 1],
          },
        },
        series: [
          {
            name: "技能",
            type: "pie",
            radius: "55%",
            center: ["50%", "50%"],
            data: [
              { value: 234, name: "Linux" },
              { value: 198, name: "Javascript" },
              { value: 172, name: "Python" },
              { value: 418, name: "Java" },
              { value: 327, name: "C#" },
              { value: 140, name: "Android" },
              { value: 295, name: "C++" },
            ].sort(function (a, b) {
              return a.value - b.value;
            }),
            roseType: "radius",
            label: {
              color: "rgba(0, 0, 0)",
            },
            labelLine: {
              lineStyle: {
                color: "rgba(0, 0, 0)",
              },
              smooth: 0.2,
              length: 10,
              length2: 20,
            },
            itemStyle: {
              color: "#c23531",
              shadowBlur: 200,
              shadowColor: "rgba(0, 0, 0, 0.5)",
            },

            animationType: "scale",
            animationEasing: "elasticOut",
            animationDelay: function (idx) {
              return Math.random() * 200;
            },
          },
        ],
      };
    }

    //学历等级以及工作年限的薪资分布 表5
    {
      this.salaryOfEdu = {
        title: {
          text: "各学历薪资随工作年限分布图",
          left: "center",
        },
        backgroundColor: "#fff",
        legend: {
          x: "right",
          y: "center",
          orient: "vertical",
        },
        tooltip: {
          trigger: "axis",
          showContent: false,
        },
        dataset: {
          source: [
            [
              "product",
              "无经验",
              "1年以下",
              "1-3年",
              "3-5年",
              "5-10年",
              "10年以上",
            ],
            ["中专", 5663.09, 6518.87, 6598.88, 9930.0, 12575.0, 17400.0],
            ["高中", 8304.02, 6826.92, 7263.36, 15108.11, 15291.67, 8750.0],
            ["大专", 7188.54, 6614.14, 9138.67, 12621.74, 15776.32, 21977.78],
            ["本科", 11613.93, 9098.97, 12354.19, 15540.85, 19795.01, 23221.45],
            ["硕士", 15241.78, 15665.22, 18428.45, 21549.0, 28198.95, 25687.02],
            ["博士", 26387.32, 22187.5, 29631.58, 31784.15, 31419.35, 56250.0],
            [
              "学历不限",
              13739.43,
              7969.23,
              9100.16,
              13115.56,
              17329.77,
              20708.33,
            ],
          ],
        },
        xAxis: { type: "category" },
        yAxis: { gridIndex: 0 },
        grid: { top: "55%" },
        series: [
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "pie",
            id: "pie",
            radius: "30%",
            center: ["50%", "25%"],
            emphasis: { focus: "data" },
            label: {
              formatter: "{b}: {@2012} ({d}%)",
            },
            encode: {
              itemName: "product",
              value: "2012",
              tooltip: "2012",
            },
          },
        ],
      };
    }

    // 不同公司的平均薪资图 表6
    {
      var dataAxis = [
        "社会团体",
        "港澳台",
        "代表处",
        "事业单位",
        "外商独资",
        "国企",
        "上市",
        "合资",
        "股份制",
        "民营",
        "其他",
      ];
      var data = [
        13969.51, 11109.13, 14569.77, 14172.65, 14277.43, 13263.48, 15355.7,
        14652.4, 11947.64, 12537.59, 11156.86,
      ];
      var yMax = 500;
      var dataShadow = [];

      for (var i = 0; i < data.length; i++) {
        dataShadow.push(yMax);
      }
      this.averageSalaryOfComType = {
        backgroundColor: "#fff",
        title: {
          text: "各性质公司的平均薪资",
          x: "center",
          y: "top",
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
                { offset: 0, color: "#9BBE66" },
                { offset: 0.5, color: "#D7C848" },
                { offset: 1, color: "#FE994A" },
              ]),
            },
            emphasis: {
              itemStyle: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  { offset: 0, color: "#FFC937" },
                  { offset: 0.7, color: "#FEA645" },
                  { offset: 1, color: "#FD7957" },
                ]),
              },
            },
            data: data,
          },
        ],
      };
    }

    // 招聘职位的占比 表7
    {
      this.ratioOfJob = {
        backgroundColor: "#fff",
        title: {
          text: "招聘职位的占比",
          x: "center",
          y: "top",
        },
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
            radius: [50, 250],
            center: ["50%", "50%"],
            roseType: "area",
            itemStyle: {
              borderRadius: 8,
            },
            data: [
              { value: 5586, name: "游戏开发" },
              { value: 6771, name: "PHP" },
              { value: 8140, name: "Python" },
              { value: 8419, name: "UI设计师" },
              { value: 10940, name: "大数据" },
              { value: 11454, name: "Android" },
              { value: 12848, name: "通信" },
              { value: 13195, name: "算法工程师" },
              { value: 13755, name: "产品经理" },
              { value: 23207, name: "硬件工程师" },
              { value: 24019, name: "C++" },
              { value: 26597, name: "IT运维" },
              { value: 29167, name: "Web" },
              { value: 39395, name: "Java" },
            ].sort(),
          },
        ],
      };
    }
    // 中国地图，每一个省份平均薪资 表8
    {
      let testData = [
        { name: "北京", value: 18442 },
        { name: "河北", value: 9210 },
        { name: "山西", value: 7830 },
        { name: "内蒙古", value: 7567 },
        { name: "辽宁", value: 9959 },
        { name: "吉林", value: 8349 },
        { name: "黑龙江", value: 8203 },
        { name: "上海", value: 17557 },
        { name: "江苏", value: 13981 },
        { name: "浙江", value: 14756 },
        { name: "安徽", value: 11786 },
        { name: "福建", value: 11407 },
        { name: "江西", value: 9626 },
        { name: "山东", value: 10707 },
        { name: "河南", value: 9521 },
        { name: "湖北", value: 12367 },
        { name: "湖南", value: 11873 },
        { name: "广东", value: 16014 },
        { name: "广西", value: 9220 },
        { name: "海南", value: 10412 },
        { name: "重庆", value: 11821 },
        { name: "四川", value: 12890 },
        { name: "贵州", value: 9287 },
        { name: "云南", value: 8696 },
        { name: "西藏", value: 11658 },
        { name: "陕西", value: 13294 },
        { name: "甘肃", value: 8476 },
        { name: "青海", value: 8895 },
        { name: "宁夏", value: 8732 },
        { name: "新疆", value: 9045 },
        { name: "香港", value: 21916 },
        { name: "澳门", value: 18076 },
        { name: "台湾", value: 12500 },
        { name: "天津", value: 12284 },
      ];

      this.chinaMapInfo = {
        backgroundColor: "#fff",
        // 进行相关配置
        title: {
          text: "各地区平均薪资",
          x: "center",
        },
        backgroundColor: "#FFF", //地图背景色
        tooltip: {
          trigger: "item",
        },
        visualMap: {
          show: true,
          x: "left",
          y: "bottom",
          textStyle: {
            fontSize: 16,
          },
          splitList: [
            { start: 5000, end: 7500 },
            { start: 7500, end: 10000 },
            { start: 10000, end: 12500 },
            { start: 12500, end: 15000 },
            { start: 15000 },
          ],
        },
        series: [
          {
            name: "平均薪资",
            type: "map",
            mapType: "china",
            roam: false,
            itemStyle: {
              normal: {
                borderWidth: 0.5,
                borderColor: "#009fe8",
                areaColor: "#ffefd5",
              },
              emphasis: {
                borderWidth: 0.5,
                borderColor: "#5c8f6e",
                // borderWidth: 5
                areaColor: "#fff",
              },
            },
            label: {
              normal: {
                //默认展示
                show: true,
              },
              emphasis: {
                //滑过展示
                show: true,
              },
            },
            data: testData,
          },
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
        ],
      };
    }

    // 不知道啥图 表9
    {
      this.graph9Info = {
        backgroundColor: "#fff",
        title: {
          text: "各职位工作年限薪资图",
          x: "center",

        },
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["邮件营销", "联盟广告", "视频广告", "直接访问", "搜索引擎"],
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        toolbox: {
          feature: {
            saveAsImage: {},
          },
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: ['无经验', '1年以下', '1-3年', '3-5年', '5-10年', '10年以上'],
        },
        yAxis: {
          type: "value",
        },
        series: [{"name": "Web", "type": "line", "stack": "总量", "data": [6515, 6994, 10439, 13188, 17625, 18211]}, {"name": "Android", "type": "line", "stack": "总量", "data": [11931, 10139, 13259, 16670, 23287, 31409]}, {"name": "Python", "type": "line", "stack": "总量", "data": [7167, 8357, 12769, 17177, 20737, 38600]}, {"name": "通信", "type": "line", "stack": "总量", "data": [6902, 7353, 9473, 13329, 16344, 31438]}, {"name": "C++", "type": "line", "stack": "总量", "data": [8579, 9889, 13226, 16103, 20099, 25509]}, {"name": "硬件工程师", "type": "line", "stack": "总量", "data": [7232, 8288, 10510, 13749, 17427, 19477]}, {"name": "PHP", "type": "line", "stack": "总量", "data": [6538, 7746, 9952, 13800, 19516, 45000]}, {"name": "IT运维", "type": "line", "stack": "总量", "data": [5052, 5669, 7706, 11123, 15860, 19416]}, {"name": "产品经理", "type": "line", "stack": "总量", "data": [7607, 8429, 10860, 14863, 19380, 24312]}, {"name": "UI设计师", "type": "line", "stack": "总量", "data": [4998, 6556, 8825, 12645, 19800, 21195]}, {"name": "大数据", "type": "line", "stack": "总量", "data": [7754, 9109, 12743, 16905, 23644, 29227]}, {"name": "算法工程师", "type": "line", "stack": "总量", "data": [11558, 14391, 17463, 21203, 26787, 30235]}, {"name": "Java", "type": "line", "stack": "总量", "data": [6033, 6683, 10018, 13229, 16612, 20204]}, {"name": "游戏开发", "type": "line", "stack": "总量", "data": [7230, 8285, 12344, 18379, 27020, 40750]}],
      };
    }

    // 动态变化表 表10
    {
      var dataMap = {};
      function dataFormatter(obj) {
        var pList = [
          "Web",
          "Android",
          "Python",
          "通信",
          "C++",
          "硬件工程师",
          "PHP",
          "IT运维",
          "产品经理",
          "UI设计师",
          "大数据",
          "算法工程师",
          "Java",
          "游戏开发",
        ];
        var temp;
        var experience_list = [
          "无经验",
          "1年以下",
          "1-3年",
          "3-5年",
          "5-10年",
          "10年以上",
        ];
        //for (var year = 2002; year <= 2011; year++) {
        for (var i = 0; i < 6; i++) {
          var year = experience_list[i];
          var max = 0;
          var sum = 0;
          temp = obj[year];
          for (var j = 0, l = temp.length; j < l; j++) {
            max = Math.max(max, temp[j]);
            sum += temp[j];
            obj[year][j] = {
              name: pList[j],
              value: temp[j],
            };
          }
          obj[year + "max"] = Math.floor(max / 100) * 100;
          obj[year + "sum"] = sum;
        }
        return obj;
      }

      dataMap.dataZhongzhuan = dataFormatter({
        无经验: [
          4284.67, 6340.28, 5107.89, 4241.71, 5921.12, 4310.65, 4779.0, 3533.17,
          5134.72, 3833.95, 4158.63, 5312.91, 3910.39, 3300.65,
        ],
        "1年以下": [
          7231.12, 8597.36, 7798.41, 6495.58, 10653.82, 6860.22, 7328.56,
          5533.85, 7141.61, 5696.1, 7191.27, 8386.37, 6704.41, 6133.12,
        ],
        "1-3年": [
          8415.2, 9378.07, 8136.28, 5280.03, 8168.56, 6221.93, 6885.54, 5302.52,
          7569.08, 7199.04, 10353.99, 7857.89, 9335.76, 9150.63,
        ],
        "3-5年": [
          10562.46, 11337.47, 11206.03, 7433.5, 10705.21, 8187.46, 9234.16,
          6662.99, 10332.74, 9271.18, 12666.87, 10962.78, 12004.8, 13979.66,
        ],
        "5-10年": [
          13879.88, 14593.9, 13000.41, 8574.33, 12087.45, 9454.07, 11004.84,
          8660.51, 11601.17, 11095.59, 14795.05, 9094.84, 12673.4, 17126.44,
        ],
        "10年以上": [
          12282.4, 14272.62, 13554.68, 16488.94, 15328.59, 11648.99, 16351.07,
          9691.77, 16372.69, 12833.46, 16702.7, 12542.7, 10210.48, 14361.15,
        ],
      });

      dataMap.dataGaozhong = dataFormatter({
        无经验: [
          3945.89, 6001.5, 4735.04, 3868.86, 5523.33, 4157.32, 4625.67, 3405.6,
          5004.65, 3703.88, 4028.56, 5196.31, 3793.79, 3184.71,
        ],
        "1年以下": [
          6890.76, 8257.0, 7423.98, 6121.15, 10256.03, 6706.88, 7175.23,
          5406.28, 7011.54, 5566.03, 7061.2, 8269.77, 6587.81, 6017.57,
        ],
        "1-3年": [
          8452.88, 9415.75, 8139.88, 5283.63, 9354.79, 6251.54, 6915.15,
          5363.95, 7628.01, 7257.97, 10412.92, 7930.29, 9078.03, 8893.95,
        ],
        "3-5年": [
          10651.32, 11426.34, 11260.83, 7488.29, 10749.01, 8269.86, 9316.56,
          6752.39, 10410.11, 9348.54, 12744.23, 11053.88, 11765.76, 13707.57,
        ],
        "5-10年": [
          13973.04, 14687.07, 13059.5, 8633.42, 12135.56, 9540.77, 11091.54,
          8754.21, 11682.83, 11177.26, 14876.71, 9190.23, 12438.65, 16860.36,
        ],
        "10年以上": [
          12375.56, 14365.79, 13613.77, 16548.03, 15376.69, 11735.69, 16437.77,
          9785.47, 16454.36, 12915.12, 16784.37, 12638.09, 9975.74, 14095.07,
        ],
      });

      dataMap.dataDaZhuan = dataFormatter({
        无经验: [
          4054.27, 6241.38, 5046.04, 3945.66, 5700.22, 4366.77, 4835.12,
          3750.96, 5340.39, 3963.59, 4364.3, 5533.52, 4131.01, 3507.96,
        ],
        "1年以下": [
          6113.87, 7749.68, 6987.78, 5450.75, 9685.72, 6169.14, 6637.49,
          5004.44, 6600.07, 5179.74, 6750.94, 7959.65, 6277.69, 5693.48,
        ],
        "1-3年": [
          8911.33, 10635.9, 9410.41, 6319.96, 10491.2, 7420.53, 8084.14,
          6003.65, 8258.08, 6837.75, 10068.73, 10702.73, 8447.16, 8249.11,
        ],
        "3-5年": [
          10876.59, 12468.94, 12353.8, 8284.08, 11707.88, 9318.41, 10365.1,
          8134.25, 10826.29, 8714.44, 12225.19, 13651.46, 10893.6, 11737.21,
        ],
        "5-10年": [
          14356.41, 15887.78, 14310.58, 10330.04, 13995.25, 11490.14, 13040.92,
          11036.9, 12999.85, 11520.01, 15258.5, 12692.07, 12874.97, 16014.12,
        ],
        "10年以上": [
          12647.47, 15267.89, 14566.26, 18243.23, 16815.96, 13438.54, 18140.63,
          11528.53, 17231.74, 13142.51, 17050.79, 14972.44, 15512.2, 18569.91,
        ],
      });

      dataMap.dataBenKe = dataFormatter({
        无经验: [
          6076.35, 8817.48, 6911.25, 6455.32, 7479.22, 6646.16, 5929.04,
          4948.25, 6539.24, 4582.11, 5373.93, 6845.71, 4725.63, 3985.35,
        ],
        "1年以下": [
          7333.15, 8524.54, 8053.3, 7377.12, 10416.52, 7901.73, 7184.61,
          6203.48, 7797.99, 6283.96, 7600.65, 10538.39, 6500.75, 6739.01,
        ],
        "1-3年": [
          9184.49, 11145.8, 10951.7, 9642.72, 10946.22, 9102.84, 8365.16,
          7396.3, 9051.44, 7537.41, 10484.81, 13970.53, 9035.91, 9797.38,
        ],
        "3-5年": [
          11610.73, 13496.46, 13749.46, 11940.47, 13262.0, 11643.53, 11669.96,
          10026.87, 12301.21, 10302.95, 13717.39, 16847.36, 11995.35, 13132.55,
        ],
        "5-10年": [
          15200.56, 16915.04, 15600.94, 13683.89, 15264.46, 13532.24, 15065.74,
          12907.2, 14588.63, 13352.71, 17826.97, 21929.36, 13943.39, 16326.91,
        ],
        "10年以上": [
          13917.65, 17511.01, 15474.58, 21215.05, 17861.74, 15269.18, 19893.89,
          14556.35, 18426.8, 13909.05, 18576.62, 23819.56, 17288.77, 18370.58,
        ],
      });

      dataMap.dataShuoShi = dataFormatter({
        无经验: [
          11818.64, 11486.32, 10569.59, 11248.51, 10215.31, 10224.18, 9026.89,
          7175.71, 8280.25, 6491.1, 7637.99, 9391.53, 8100.23, 5834.89,
        ],
        "1年以下": [
          13918.53, 14531.75, 14109.96, 14568.63, 15047.05, 13035.53, 11838.24,
          9989.21, 11064.65, 9651.83, 11293.11, 14607.02, 11392.3, 10105.51,
        ],
        "1-3年": [
          12497.16, 13462.51, 13317.86, 13184.13, 13217.45, 12928.99, 11711.14,
          9854.09, 10934.7, 9521.88, 12336.42, 16223.19, 11191.58, 10428.0,
        ],
        "3-5年": [
          15691.73, 16987.54, 17184.6, 16653.5, 16704.85, 15140.55, 14686.81,
          12049.4, 14405.05, 12428.03, 15264.0, 19744.39, 15461.69, 13879.4,
        ],
        "5-10年": [
          16888.12, 18020.96, 16661.38, 18542.87, 16878.11, 15112.47, 16159.08,
          12752.35, 14889.59, 14176.39, 21637.99, 27474.34, 15320.65, 14624.89,
        ],
        "10年以上": [
          20333.1, 23353.84, 21261.47, 23216.68, 19121.74, 15388.5, 19892.55,
          11881.91, 15030.95, 11697.44, 19142.84, 24484.33, 13753.23, 12504.04,
        ],
      });

      dataMap.dataBoShi = dataFormatter({
        无经验: [
          23803, 23601, 22899, 23159, 20105, 19437, 20006, 16390, 18604, 16959,
          24327, 23977, 16255.17, 15179,
        ],
        "1年以下": [
          25029, 25773, 25566, 25652, 26101, 23412, 23981, 19168, 21382, 20107,
          27529, 28739, 19540, 19443,
        ],
        "1-3年": [
          22002, 23098, 23065, 22559, 21113, 20147, 20689, 15868, 18087, 16811,
          26265, 29903, 16284, 16710,
        ],
        "3-5年": [
          27456.98, 28792.82, 29101.54, 28197.83, 27173.07, 25755.6, 26980.61,
          21486.49, 24129.15, 22296.76, 30279.54, 31258.55, 21882.28, 21495.82,
        ],
        "5-10年": [
          28043.98, 27839.78, 26591.86, 27959.14, 25218.27, 23626.11, 27896.97,
          20207.94, 22561.63, 21993.06, 29999.06, 33735.37, 19015.48, 19995.94,
        ],
        "10年以上": [
          35073, 36762, 34781, 36577, 30951, 28463, 35820.74, 23527, 27983,
          24642, 27046, 32317, 19024, 19554,
        ],
      });

      this.graph10Info = {
        baseOption: {
          backgroundColor: "#fff",
          timeline: {
            axisType: "category",
            // realtime: false,
            // loop: false,
            autoPlay: true,
            // currentIndex: 2,
            playInterval: 1000,
            // controlStyle: {
            //     position: 'left'
            // },
            data: ["无经验", "1年以下", "1-3年", "3-5年", "5-10年", "10年以上"],
            label: {},
          },
          title: {
            text: "各学历随工作年限平均薪资",
            subtext: "数据来自招聘网站",
          },
          tooltip: {},
          legend: {
            left: "right",
            data: ["中专", "高中", "大专", "本科", "硕士", "博士"],
            //初始状态是否选择展示
            selected: {
              大专: false,
              博士: false,
              中专: false,
              高中: false,
            },
          },
          calculable: true,
          grid: {
            top: 80,
            bottom: 100,
            tooltip: {
              trigger: "axis",
              axisPointer: {
                type: "shadow",
                label: {
                  show: true,
                  formatter: function (params) {
                    return params.value.replace("\n", "");
                  },
                },
              },
            },
          },
          xAxis: [
            {
              type: "category",
              axisLabel: { interval: 0 },
              data: [
                "Web",
                "Android",
                "Python",
                "通信",
                "C++",
                "硬件工程师",
                "PHP",
                "IT运维",
                "产品经理",
                "UI设计师",
                "大数据",
                "算法工程师",
                "Java",
                "游戏开发",
              ],
              splitLine: { show: false },
            },
          ],
          yAxis: [
            {
              type: "value",
              name: "salary（元）",
            },
          ],
          //['中专', '高中', '大专', '本科', '硕士', '博士']
          series: [
            { name: "中专", type: "bar" },
            { name: "高中", type: "bar" },
            { name: "大专", type: "bar" },
            { name: "本科", type: "bar" },
            { name: "硕士", type: "bar" },
            { name: "博士", type: "bar" },
            {
              name: "薪资占比",
              type: "pie",
              center: ["85%", "20%"],
              radius: "28%",
              z: 100,
            },
          ],
        },
        options: [
          {
            title: { text: "无经验" },
            series: [
              { data: dataMap.dataZhongzhuan["无经验"] },
              { data: dataMap.dataGaozhong["无经验"] },
              { data: dataMap.dataDaZhuan["无经验"] },
              { data: dataMap.dataBenKe["无经验"] },
              { data: dataMap.dataShuoShi["无经验"] },
              { data: dataMap.dataBoShi["无经验"] },
              {
                data: [
                  { name: "中专", value: dataMap.dataZhongzhuan["无经验sum"] },
                  { name: "高中", value: dataMap.dataGaozhong["无经验sum"] },
                  { name: "大专", value: dataMap.dataDaZhuan["无经验sum"] },
                  { name: "本科", value: dataMap.dataBenKe["无经验sum"] },
                  { name: "硕士", value: dataMap.dataShuoShi["无经验sum"] },
                  { name: "博士", value: dataMap.dataBoShi["无经验sum"] },
                ],
              },
            ],
          },
          {
            title: { text: "1年以下" },
            series: [
              { data: dataMap.dataZhongzhuan["1年以下"] },
              { data: dataMap.dataGaozhong["1年以下"] },
              { data: dataMap.dataDaZhuan["1年以下"] },
              { data: dataMap.dataBenKe["1年以下"] },
              { data: dataMap.dataShuoShi["1年以下"] },
              { data: dataMap.dataBoShi["1年以下"] },
              {
                data: [
                  { name: "中专", value: dataMap.dataZhongzhuan["1年以下sum"] },
                  { name: "高中", value: dataMap.dataGaozhong["1年以下sum"] },
                  { name: "大专", value: dataMap.dataDaZhuan["1年以下sum"] },
                  { name: "本科", value: dataMap.dataBenKe["1年以下sum"] },
                  { name: "硕士", value: dataMap.dataShuoShi["1年以下sum"] },
                  { name: "博士", value: dataMap.dataBoShi["1年以下sum"] },
                ],
              },
            ],
          },
          {
            title: { text: "1-3年" },
            series: [
              { data: dataMap.dataZhongzhuan["1-3年"] },
              { data: dataMap.dataGaozhong["1-3年"] },
              { data: dataMap.dataDaZhuan["1-3年"] },
              { data: dataMap.dataBenKe["1-3年"] },
              { data: dataMap.dataShuoShi["1-3年"] },
              { data: dataMap.dataBoShi["1-3年"] },
              {
                data: [
                  { name: "中专", value: dataMap.dataZhongzhuan["1-3年sum"] },
                  { name: "高中", value: dataMap.dataGaozhong["1-3年sum"] },
                  { name: "大专", value: dataMap.dataDaZhuan["1-3年sum"] },
                  { name: "本科", value: dataMap.dataBenKe["1-3年sum"] },
                  { name: "硕士", value: dataMap.dataShuoShi["1-3年sum"] },
                  { name: "博士", value: dataMap.dataBoShi["1-3年sum"] },
                ],
              },
            ],
          },
          {
            title: { text: "3-5年" },
            series: [
              { data: dataMap.dataZhongzhuan["3-5年"] },
              { data: dataMap.dataGaozhong["3-5年"] },
              { data: dataMap.dataDaZhuan["3-5年"] },
              { data: dataMap.dataBenKe["3-5年"] },
              { data: dataMap.dataShuoShi["3-5年"] },
              { data: dataMap.dataBoShi["3-5年"] },
              {
                data: [
                  { name: "中专", value: dataMap.dataZhongzhuan["3-5年sum"] },
                  { name: "高中", value: dataMap.dataGaozhong["3-5年sum"] },
                  { name: "大专", value: dataMap.dataDaZhuan["3-5年sum"] },
                  { name: "本科", value: dataMap.dataBenKe["3-5年sum"] },
                  { name: "硕士", value: dataMap.dataShuoShi["3-5年sum"] },
                  { name: "博士", value: dataMap.dataBoShi["3-5年sum"] },
                ],
              },
            ],
          },
          {
            title: { text: "5-10年" },
            series: [
              { data: dataMap.dataZhongzhuan["5-10年"] },
              { data: dataMap.dataGaozhong["5-10年"] },
              { data: dataMap.dataDaZhuan["5-10年"] },
              { data: dataMap.dataBenKe["5-10年"] },
              { data: dataMap.dataShuoShi["5-10年"] },
              { data: dataMap.dataBoShi["5-10年"] },
              {
                data: [
                  { name: "中专", value: dataMap.dataZhongzhuan["5-10年sum"] },
                  { name: "高中", value: dataMap.dataGaozhong["5-10年sum"] },
                  { name: "大专", value: dataMap.dataDaZhuan["5-10年sum"] },
                  { name: "本科", value: dataMap.dataBenKe["5-10年sum"] },
                  { name: "硕士", value: dataMap.dataShuoShi["5-10年sum"] },
                  { name: "博士", value: dataMap.dataBoShi["5-10年sum"] },
                ],
              },
            ],
          },
          {
            title: { text: "10年以上" },
            series: [
              { data: dataMap.dataZhongzhuan["10年以上"] },
              { data: dataMap.dataGaozhong["10年以上"] },
              { data: dataMap.dataDaZhuan["10年以上"] },
              { data: dataMap.dataBenKe["10年以上"] },
              { data: dataMap.dataShuoShi["10年以上"] },
              { data: dataMap.dataBoShi["10年以上"] },
              {
                data: [
                  {
                    name: "中专",
                    value: dataMap.dataZhongzhuan["10年以上sum"],
                  },
                  { name: "高中", value: dataMap.dataGaozhong["10年以上sum"] },
                  { name: "大专", value: dataMap.dataDaZhuan["10年以上sum"] },
                  { name: "本科", value: dataMap.dataBenKe["10年以上sum"] },
                  { name: "硕士", value: dataMap.dataShuoShi["10年以上sum"] },
                  { name: "博士", value: dataMap.dataBoShi["10年以上sum"] },
                ],
              },
            ],
          },
        ],
      };
    }
  },
  mounted: function () {
    this.loadAverageSalary();
    this.loadComType();
    this.loadSalaryOfEdu();
    this.loadComSize();
    this.loadSkillsOfJob();
    this.loadAverageSalaryOfComType();
    this.loadRatioOfJob();
    this.loadChinaMap();
    this.loadGraph9();
    this.loadGraph10();
  },
  destroyed: function () {
    document.removeEventListener("click", this.handleBodyClick);
  },
  methods: {
    handleinputp: function (inputValue) {
    },
    back: function () {
      this.$router.replace("/sub");
    },
    // 各职位平均薪资分布情况graph-1
    loadAverageSalary: function () {
      var chartDom = document.getElementById("graph1-chart");
      var myChart = this.$echarts.init(chartDom);
      this.salaryOfJob && myChart.setOption(this.salaryOfJob);
    },
    // 公司类型分布情况 graph-2
    loadComType: function () {
      var chartDom = document.getElementById("graph2-chart");
      var myChart = this.$echarts.init(chartDom);
      this.comTypeInfo && myChart.setOption(this.comTypeInfo);
    },
    // 公司大小分布情况 graph-3
    loadComSize: function () {
      var chartDom = document.getElementById("graph3-chart");
      var myChart = this.$echarts.init(chartDom);
      this.comSizeInfo && myChart.setOption(this.comSizeInfo);
    },
    // 所有职位的前几的技能 graph-4
    loadSkillsOfJob: function () {
      var chartDom = document.getElementById("graph4-chart");
      var myChart = this.$echarts.init(chartDom);
      this.skillsOfJob && myChart.setOption(this.skillsOfJob);
    },
    // 每个学历的平均薪资 graph-5
    loadSalaryOfEdu: function () {
      var chartDom = document.getElementById("graph5-chart");
      var myChart = this.$echarts.init(chartDom);
      myChart.on("updateAxisPointer", function (event) {
        var xAxisInfo = event.axesInfo[0];
        if (xAxisInfo) {
          var dimension = xAxisInfo.value + 1;
          myChart.setOption({
            series: {
              id: "pie",
              label: {
                formatter: "{b}: {@[" + dimension + "]} ({d}%)",
              },
              encode: {
                value: dimension,
                tooltip: dimension,
              },
            },
          });
        }
      });
      this.salaryOfEdu && myChart.setOption(this.salaryOfEdu);
    },
    // 每个类型公司的平均薪资 graph-6
    loadAverageSalaryOfComType: function () {
      var chartDom = document.getElementById("graph6-chart");
      var myChart = this.$echarts.init(chartDom);
      var zoomSize = 6;
      myChart.on("click", function (params) {
        myChart.dispatchAction({
          type: "dataZoom",
          startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
          endValue:
            dataAxis[
              Math.min(params.dataIndex + zoomSize / 2, data.length - 1)
            ],
        });
      });
      this.averageSalaryOfComType &&
        myChart.setOption(this.averageSalaryOfComType);
    },
    // 市场上招聘职位的占比 graph-7
    loadRatioOfJob: function () {
      var chartDom = document.getElementById("graph7-chart");
      var myChart = this.$echarts.init(chartDom);
      this.ratioOfJob && myChart.setOption(this.ratioOfJob);
    },

    loadChinaMap: function () {
      var chartDom = document.getElementById("graph8-chart");
      var myChart = this.$echarts.init(chartDom);
      this.chinaMapInfo && myChart.setOption(this.chinaMapInfo);
    },

    loadGraph9: function () {
      // var that = this;
      var chartDom = document.getElementById("graph9-chart");
      var myChart = this.$echarts.init(chartDom);
      this.graph9Info && myChart.setOption(this.graph9Info);
    },

    loadGraph10: function () {
      var chartDom = document.getElementById("graph10-chart");
      var myChart = this.$echarts.init(chartDom);
      this.graph10Info && myChart.setOption(this.graph10Info);
    },

    showProjectInfo: function () {
      this.isShow = !this.isShow;
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
    handleClose: function () {},
    closeDrawer: function () {
      this.drawer1 = false;
      this.drawer1 = false;
    },
  },
};
</script>

<style scoped>
@import url("../css/mainpage.css");
</style>