<template>
  <div class="radar" id="radar-echart">
  </div>
</template>

<script>
export default {
  name: 'radar',
  props: ['radarInfo'],
  data: function(){
    return {
      option : {}
    }
  },
  created: function(){
    this.option.title = this.radarInfo.title;
    this.option.legend = this.radarInfo.legend;
    this.option.radar = this.radarInfo.radar;
    this.option.series = this.radarInfo.series;
  },
  mounted: function(){
    this.loadGraph();
  },
  methods: {
    loadGraph: function(){
      var myChart = this.$echarts.init(document.getElementById("radar-echart"));
      this.option && myChart.setOption(this.option)
    }
  }
}
</script>

<style scoped>
  .radar{
    width: 400px;
    height: 450px;
    margin: 0 auto;
  }
</style>