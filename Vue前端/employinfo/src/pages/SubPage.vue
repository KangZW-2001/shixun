<template>
  <div>
    <h2>这里是子页面,（私人定制页面）</h2>
    <el-button icon="el-icon-back" circle @click="back"></el-button>  
    <!-- 用来容纳 -->
    <!-- <div class="radar" id="radar-echart">
    </div> -->
    <echart-radar style="border:1px solid red" v-if="isRadarInfo" :radarInfo="radarInfo"></echart-radar>
  </div>
</template>

<script>
import EchartRadar from '../components/EchartRadar.vue'
export default {
  name: 'SubPage',
  components: {
    EchartRadar: EchartRadar
  },
  data: function(){
    return {
      radarInfo: {},
      isRadarInfo: false
    }
  },
  created: function(){
    // 获取雷达图数据
    // this.radarInfo = {
    //   title : {
    //     text : "雷达图"
    //   },
    //   legend : {
    //     data: ['预算分配（Allocated Budget）', '实际开销（Actual Spending）']
    //   },
    //   radar: {
    //     indicator: [
    //     { name: '销售（Sales）', max: 6500},
    //     { name: '管理（Administration）', max: 16000},
    //     { name: '信息技术（Information Technology）', max: 30000},
    //     { name: '客服（Customer Support）', max: 38000},
    //     { name: '研发（Development）', max: 52000},
    //     { name: '市场（Marketing）', max: 25000}
    //     ]
    //   },
    //   series: {
    //     name: '预算 vs 开销（Budget vs spending）',
    //     type: 'radar',
    //     data: [
    //       {
    //           value: [4200, 3000, 20000, 35000, 50000, 18000],
    //           name: '预算分配（Allocated Budget）'
    //       },
    //       {
    //           value: [5000, 14000, 28000, 26000, 42000, 21000],
    //           name: '实际开销（Actual Spending）'
    //       }
    //     ]
    //   }
    // };
    var that = this;
    this.$axios({
      method: "get",
      url: "http://localhost:8080/radar"      
    }).then(
      function(response){
        console.log(response.data);
        that.radarInfo = response.data;
        that.isRadarInfo = true;
      },
      function(error){
        console.log(error);
      }
    )
  },
  methods: {
    back: function(){
     this.$router.replace('/main'); 
    }
  }
}
</script>

<style scoped>

</style>