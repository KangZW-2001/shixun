<template>
  <div class="sub-page">
    <!-- 头部 -->
    <div class="header">
      <div class="title">个人页面</div>
      <div class="back-btn">
        <div class="btn">
          <el-button icon="el-icon-back" circle @click="back"></el-button>
        </div>
        <div class="back-info">点击返回主页面</div>
      </div>
    </div>

    <!-- 选择部分 -->
    <div class="select-part">
      <div class="info">请选择：</div>

      <!-- 选择职位 -->
      <div class="job">
        <el-select v-model="jobValue" placeholder="选择职业">
          <el-option
            v-for="item in jobInfo"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>

      <!-- 选择学历 -->
      <div class="edu" style="float: left">
        <el-select v-model="eduValue" placeholder="选择学历">
          <el-option
            v-for="item in eduInfo"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>

      <!-- 选择城市 -->
      <div class="city">
        <el-select v-model="cityValue" filterable placeholder="选择城市">
          <el-option-group
            v-for="group in cityInfo"
            :key="group.label"
            :label="group.label"
          >
            <el-option
              v-for="item in group.options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-option-group>
        </el-select>
      </div>

      <!-- 选择掌握的技能 -->
      <div class="skill">
        <el-select
          v-model="skillValue"
          multiple
          collapse-tags
          style="margin-left: 20px"
          placeholder="选择掌握的技术"
        >
          <el-option
            v-for="item in skillInfo"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>

      <!-- 选择工作的事件 -->
      <div class="time">
        <el-select v-model="timeValue" placeholder="选择工作时间">
          <el-option
            v-for="item in timeInfo"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>

      <!-- 选择公司性质 -->
      <div class="company-type">
        <el-select v-model="comTypeValue" placeholder="选择公司性质">
          <el-option
            v-for="item in comTypeInfo"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>

      <!-- 选择公司大小 -->
      <div class="company-size">
        <el-select v-model="comSizeValue" placeholder="选择公司大小">
          <el-option
            v-for="item in comSizeInfo"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>

      <div class="submit-btn" @click="submitForm">
        <el-button type="info" round>确认</el-button>
      </div>
    </div>

    <!-- 渲染从后端获取到的数据 -->
    <div class="answer">
      <!--  -->
      <div class="data-info">
        <div class="left-mask" v-if="isMaskShow"></div>
        <div class="left-info-box" v-show="isLeftInfo" v-for="(item,index) in leftInfo" :key="index">
          
          <div class="info-box">
            <div class="info-title">公司：</div>
            {{item.company}}
          </div>

          <div class="info-box">
            <div class="info-title">公司规模：</div>
            {{item.companyTotal}}
          </div>

          <div class="info-box">
            <div class="info-title">公司性质：</div>
            {{item.companyType}}
          </div>

          <div class="info-box">
            <div class="info-title">需要的学历：</div>
            {{item.education}}
          </div>

          <div class="info-box">
            <div class="info-title">需要的工作经历：</div>
            {{item.experience}}
          </div>

          <div class="info-box">
            <div class="info-title">链接：</div>
            <a :href="item.href" target="_blank">详细信息</a>
          </div>

          <div class="info-box">
            <div class="info-title">工作名字：</div>
            {{item.job_name}}
          </div>
          
          <div class="info-box">
            <div class="info-title">工作地点：</div>
            {{item.location}}
          </div>

          <div class="info-box">
            <div class="info-title">工作薪资：</div>
            {{item.salary}}
          </div>
        </div>
      </div>
      <!-- 图表部分 -->
      <div class="answer-chart">
        <el-carousel trigger="click" :autoplay="false" height="680px">
          <el-carousel-item v-for="item in chartIdArray" :key="item">
            <div class="chart-con">
              <div style="float: left; margin-top: 10px; margin-left=20px" v-if="item == 'chart5' && !isMaskShow">
                <el-slider
                  v-model="sliderValue"
                  vertical
                  height="200px"
                  :max="maxValue"
                  :min="minValue"
                  @change="changeValue"
                >
                </el-slider>
              </div>
              <div class="mask" v-if="isMaskShow"></div>
              <div class="chart" v-show="!isMaskShow" :id="item"></div>
            </div>
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SubPage",
  data: function () {
    return {
      chartIdArray: ["chart1", "chart2", "chart3", "chart4", "chart5"],
      sliderValue: 5,
      minValue: 5,
      maxValue: 20,
      jobInfo: [
        {
          value: "Java",
          label: "Java",
        },
        {
          value: "Web",
          label: "Web",
        },
        {
          value: "PHP",
          label: "PHP",
        },
        {
          value: "产品经理",
          label: "产品经理",
        },
        {
          value: "Python",
          label: "Python",
        },
        {
          value: "Android",
          label: "Android",
        },
        {
          value: "算法工程师",
          label: "算法工程师",
        },
        {
          value: "大数据",
          label: "大数据",
        },
        {
          value: "UI设计师",
          label: "UI设计师",
        },
        {
          value: "游戏开发",
          label: "游戏开发",
        },
        {
          value: "IT运维",
          label: "IT运维",
        },
        {
          value: "C++",
          label: "C++",
        },
        {
          value: "通信",
          label: "通信",
        },
        {
          value: "硬件工程师",
          label: "硬件工程师",
        },
      ],
      eduInfo: [
        {
          value: 0,
          label: "中专",
        },
        {
          value: 1,
          label: "高中",
        },
        {
          value: 2,
          label: "大专",
        },
        {
          value: 3,
          label: "本科",
        },
        {
          value: 4,
          label: "硕士",
        },
        {
          value: 5,
          label: "博士",
        },
        {
          value: 6,
          label: "学历不限",
        },
      ],
      skillInfo: [],
      cityInfo: [],
      skillAllInfo: {},
      skillInfo: [],
      timeInfo: [
        {
          value: 0,
          label: "1年以下",
        },
        {
          value: 1,
          label: "1年~2年",
        },
        {
          value: 2,
          label: "2年~3年",
        },
        {
          value: 3,
          label: "3年~4年",
        },
        {
          value: 4,
          label: "4年~5年",
        },
        {
          value: 5,
          label: "5年以上",
        },
      ],
      comTypeInfo: [
        {
          value: 0,
          label: "社会团体",
        },
        {
          value: 1,
          label: "港澳台公司",
        },
        {
          value: 2,
          label: "代表处",
        },
        {
          value: 3,
          label: "事业单位",
        },
        {
          value: 4,
          label: "外商独资",
        },
        {
          value: 5,
          label: "国企",
        },
        {
          value: 6,
          label: "上市公司",
        },
        {
          value: 7,
          label: "合资",
        },
        {
          value: 8,
          label: "股份制企业",
        },
        {
          value: 9,
          label: "民营",
        },
        {
          value: 11,
          label: "其他",
        },
      ],
      comSizeInfo: [
        {
          value: 1,
          label: "20~99人",
        },
        {
          value: 2,
          label: "100~299人",
        },
        {
          value: 3,
          label: "300~499人",
        },
        {
          value: 4,
          label: "500~999人",
        },
        {
          value: 5,
          label: "1000~9999人",
        },
        {
          value: 6,
          label: "10000人以上",
        },
        {
          value: 0,
          label: "20人以下",
        },
      ],

      jobValue: "",
      eduValue: "",
      cityValue: "",
      skillValue: [],
      timeValue: "",
      comTypeValue: "",
      comSizeValue: "",
      isMaskShow: true,
      //----------------------------------------------
      comSizeChartInfo: {},
      comSizeChart: null,

      comAttrChartInfo: {},
      comAttrChart: null,

      salaryPieInfo: {},
      salaryPieChart: null,

      salaryCurveInfo: {},
      salaryCurveChart: null,

      jobSkillInfo: {},
      jobSkillChart: null,

      isLeftInfo: false,
      leftInfo: [
      {
        test: "你好"
      },
      {
        test: "世界"
      }],

      // ipAddr: "http://192.168.30.51:8000",
      // ipAddr: "http://172.23.205.170:8000",
      ipAddr: "http://172.20.10.9:8000",
      test: false
    };
  },
  created: function () {
    var that = this;
    this.$axios({
      method: "get",
      url: "http://localhost:8080/radar",
    }).then(
      function (response) {
        // console.log(response.data);
        that.radarInfo = response.data;
        that.isRadarInfo = true;
      },
      function (error) {
        console.log(error);
      }
    );

    this.$axios({
      method: "get",
      url: "http://localhost:8080/city",
    }).then(
      function (response) {
        console.log(response.data);
        that.cityInfo = response.data;
      },
      function (error) {
        console.log(error);
      }
    );

    this.$axios({
      method: "get",
      url: "http://localhost:8080/skill",
    }).then(
      function (response) {
        that.skillAllInfo = response.data;
        // console.log(response.data);
        console.log("-------------------");
        // console.log(response.data['Web']);
      },
      function (error) {
        console.log(error);
      }
    );

    this.comSizeChartInfo = {
      title: {
        text: "公司规模分布",
        left: "center",
      },
      dataset: {
        source: [],
      },
      grid: { containLabel: true },
      xAxis: { name: "amount" },
      yAxis: { type: "category" },
      visualMap: {
        orient: "vertical",
        left: "left",
        // x: left,
        // y: center,
        min: 6000,
        max: 20000,
        text: ["最高平均薪资", "最低平均薪资"],
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

    this.comAttrChartInfo = {
      title: {
        text: "公司性质分布",
        left: "center",
      },
      dataset: {
        source: [],
      },
      grid: { containLabel: true },
      xAxis: { name: "amount" },
      yAxis: { type: "category" },
      visualMap: {
        orient: "horizontal",
        left: "center",
        min: 6000,
        max: 20000,
        text: ["最高平均薪资", "最低平均薪资"],
        // Map the score column to color
        dimension: 0,
        inRange: {
          color: ["#14B5A2", "#08308A", "#AA1A85"],
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

    this.salaryPieInfo = {
      title: {
        text: "薪资分布情况",
        left: "center",
      },
      tooltip: {
        trigger: "item",
      },
      legend: {
        orient: "vertical",
        left: "left",
      },
      series: [
        {
          name: "薪资情况",
          type: "pie",
          radius: "50%",
          data: [
            { value: 50, name: "康子文" },
            { value: 100, name: "Kag" },
            { value: 10, name: "test1" },
            { value: 20, name: "test2" },
            { value: 30, name: "test3" },
            { value: 40, name: "test4" },
            { value: 50, name: "test5" },
            { value: 60, name: "test6" },
            { value: 70, name: "test7" },
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: "rgba(0, 0, 0, 0.5)",
            },
          },
        },
      ],
    };

    this.salaryCurveInfo = {
      color: ["#80FFA5"],
      title: {
        text: "个人薪资图",
        left: "center",
      },
      tooltip: {
        trigger: "axis",
        axisPointer: {
          type: "cross",
          label: {
            backgroundColor: "#6a7985",
          },
        },
      },
      // legend: {
      //   data: ["Line 1", "Line 2", "Line 3", "Line 4", "Line 5"],
      // },
      toolbox: {
        feature: {
          saveAsImage: {},
        },
      },
      grid: {
        left: "3%",
        right: "4%",
        bottom: "3%",
        containLabel: true,
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
        },
      ],
      yAxis: [
        {
          type: "value",
        },
      ],
      series: [
        {
          name: "薪资",
          type: "line",
          stack: "总量",
          smooth: true,
          lineStyle: {
            width: 0,
          },
          showSymbol: false,
          areaStyle: {
            opacity: 0.8,
            color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: "rgba(128, 255, 165)",
              },
              {
                offset: 1,
                color: "rgba(1, 191, 236)",
              },
            ]),
          },
          emphasis: {
            focus: "series",
          },
          data: [140, 232, 101, 264, 90, 340, 250],
        },
      ],
    };

    this.jobSkillInfo = {
      title: {
        text: "技能图",
        left: "center",
      },
      angleAxis: {
        type: "category",
        data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
      },
      radiusAxis: {},
      polar: {},
      series: [
        {
          type: "bar",
          data: [1, 2, 3, 4, 3, 5, 1],
          coordinateSystem: "polar",
          name: "A",
          stack: "a",
          emphasis: {
            focus: "series",
          },
        },
      ],
    };


  },
  mounted: function () {
    var chartDom = document.getElementById("chart1");
    this.comSizeChart = this.$echarts.init(chartDom);
    this.comSizeChartInfo && this.comSizeChart.setOption(this.comSizeChartInfo);

    var chartDom2 = document.getElementById("chart3");
    this.comAttrChart = this.$echarts.init(chartDom2);
    this.comAttrChartInfo && this.comAttrChart.setOption(this.comAttrChartInfo);

    var chartDom3 = document.getElementById("chart2");
    this.salaryPieChart = this.$echarts.init(chartDom3);
    this.salaryPieInfo && this.salaryPieChart.setOption(this.salaryPieInfo);

    var chartDom4 = document.getElementById("chart4");
    this.salaryCurveChart = this.$echarts.init(chartDom4);
    this.salaryCurveInfo &&
      this.salaryCurveChart.setOption(this.salaryCurveInfo);

    var chartDom5 = document.getElementById("chart5");
    this.jobSkillChart = this.$echarts.init(chartDom5);
    this.jobSkillInfo && this.jobSkillChart.setOption(this.jobSkillInfo);
  },
  watch: {
    jobValue: function (value1, value2) {
      this.skillValue = [];
      this.skillInfo = this.skillAllInfo[value1];
    },
  },
  methods: {
    back: function () {
      this.$router.replace("/main");
    },
    showJob: function () {
      alert(this.jobValue);
    },
    showEdu: function () {
      alert(this.eduValue);
    },
    submitForm: function () {
      if (
        this.jobValue.length != 0 &&
        this.eduValue.length != "" &&
        this.cityValue.length != "" &&
        this.skillValue.length != 0 &&
        this.timeValue.length != "" &&
        this.comTypeValue.length != "" &&
        this.comSizeValue.length != ""
      ) {
        console.log("选择的职业为 : " + this.jobValue);
        console.log("选择的学历为 : " + this.eduValue);
        console.log("选择的城市为 : " + this.cityValue);
        console.log("选择的技能为 : " + this.skillValue);
        console.log("选择的时间为 : " + this.timeValue);
        console.log("选择的公司类型为 : " + this.comTypeValue);
        console.log("选择的公司大小为 : " + this.comSizeValue);
        this.refreshChart();
      } else {
        alert("请将表单填写完整");
      }
    },
    refreshChart: function () {
      var that = this;
      this.sliderValue = 5;
      if (this.test == false) {
        this.test = true;
      }
      if (this.isMaskShow == true) {
        this.isMaskShow = false;
      }
      // 拼接技能字符串
      var skillString = "";
      for (var i = 0; i < this.skillValue.length; i++) {
        if (i == this.skillValue.length - 1) {
          skillString += this.skillValue[i];
        } else {
          skillString += this.skillValue[i] + ",";
        }
      }

      // 获取左侧显示的信息
      this.$axios({
        method: "get",
        url: this.ipAddr+"/getList/location="+this.cityValue+"&experience="+this.timeValue+"&education="+this.eduValue+"&job="+this.jobValue+"&number=10"
      }).then(
        function(response){
          console.log("现在打印左侧边栏信息！");
          console.log(typeof response.data)
          console.log(response);
          if(typeof response.data == "string"){
            var json = JSON.parse(response.data.slice(1,response.data.length-1));
            that.leftInfo = json;
          }else{
            that.leftInfo = response.data;
          }
          that.isLeftInfo = true;
        },
        function(error){
          console.log(error);
        }
      )
            
      // 获取表1的信息
      this.$axios({
        method: "get",
        url:
          this.ipAddr +
          "/getCompanyScale/location=" +
          this.cityValue +
          "&job=" +
          this.jobValue,
      }).then(
        function (response) {
          // console.log(response);
          that.comSizeChartInfo.dataset.source = response.data.dataset.source;
          that.comSizeChartInfo &&
            that.comSizeChart.setOption(that.comSizeChartInfo);
        },
        function (error) {
          console.log(error);
        }
      );

      // 获取表2的信息
      this.$axios({
        method: "get",
        url:
          this.ipAddr +
          "/getCompanyCategory/location=" +
          this.cityValue +
          "&job=" +
          this.jobValue,
      }).then(
        function (response) {
          that.comAttrChartInfo.dataset.source = response.data.dataset.source;
          that.comAttrChartInfo &&
            that.comAttrChart.setOption(that.comAttrChartInfo);
        },
        function (error) {
          console.log(error);
        }
      );
      //获取表3的信息
      this.$axios({
        method: "get",
        url:
          this.ipAddr +
          "/getSalaryDistribution/location=" +
          this.cityValue +
          "&job=" +
          this.jobValue,
      }).then(
        function (response) {
          that.salaryPieInfo.series[0].data = response.data.data;
          that.salaryPieInfo &&
            that.salaryPieChart.setOption(that.salaryPieInfo);
        },
        function (error) {
          console.log(error);
        }
      );

      // 获取表4的信息
      this.$axios({
        method: "get",
        url: this.ipAddr+"/getSalaryChangeTime/location="+this.cityValue+"&job="+this.jobValue+"&education="+this.eduValue+"&experience="+this.timeValue+"&skill="+skillString+"&company_system="+this.comTypeValue+"&company_people="+this.comSizeValue+"&year=20"
      }).then(
        function(response){
          console.log("现在打印薪资变化");
          console.log(response);
          that.salaryCurveInfo.xAxis[0].data = response.data[0];
          that.salaryCurveInfo.series[0].data = response.data[1];
          that.salaryCurveInfo && that.salaryCurveChart.setOption(that.salaryCurveInfo);

        },
        function(error){
          console.log(error);
        }
      )
      // 获取表5的信息
      this.$axios({
        method: "get",
        url:
          this.ipAddr +
          "/getSkill/location=" +
          this.cityValue +
          "&job=" +
          this.jobValue +
          "&word_num="+this.sliderValue,
      }).then(
        function (response) {
          that.jobSkillInfo.angleAxis.data = response.data[0];
          var data1 = [];
          for (var i = 0; i < response.data[1].length; i++) {
            data1[i] = response.data[1][i] / 100;
          }
          that.jobSkillInfo.series[0].data = data1;
          that.jobSkillInfo && that.jobSkillChart.setOption(that.jobSkillInfo);
        },
        function (error) {
          console.log(error);
        }
      );
      console.log("执行了刷新函数");
    },
    changeValue: function (num) {
      var that = this;
      this.$axios({
        method: "get",
        url:
          this.ipAddr +
          "/getSkill/location=" +
          this.cityValue +
          "&job=" +
          this.jobValue +
          "&word_num=" +
          num,
      }).then(
        function (response) {
          that.jobSkillInfo.angleAxis.data = response.data[0];

          var data1 = [];
          for (var i = 0; i < response.data[1].length; i++) {
            data1[i] = response.data[1][i] / 100;
          }

          that.jobSkillInfo.series[0].data = data1;
          that.jobSkillInfo && that.jobSkillChart.setOption(that.jobSkillInfo);
        },
        function (error) {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
@import url("../css/reset.css");
@import url("../css/subpage.css");
</style>