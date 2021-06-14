<template>
  <div id="container">
    <header></header>
    <nav>
        <a href="map2d.html">进入地图</a>
        <a href="globe3d.html">进入3d地球</a>
        <a href="pb_studyPlan.html">学习计划进度</a>
    </nav>
    <main>
        <!--样式里的l,m,r,t,b分别代表左，中，右，上，下-->
        <div class="l">
            <div class="l-t">
                <div class="chart-wrap">
                    <h3>tiobe计算机语言流行趋势</h3>
                    <div class="chart" id="ec01_line_tiobe"></div>
                </div>
            </div>
            <div class="l-b">
                <div class="chart-wrap">
                    <h3>面积图</h3>
                    <div class="chart" id="ec02_area_accessSource"></div>
                </div>
            </div>
        </div>
        <div class="m">
            <div class="m-t">
                <div class="chart-wrap">
                    <h3>一周时间分布</h3>
                    <div class="chart" id="ec03_barV_timeDistribute"></div>
                </div>
            </div>
            <div class="m-b">
                <div class="chart-wrap">
                    <h3>当电脑坏了的时候</h3>
                    <div class="chart" id="ec04_pie_computerBroken"></div>
                </div>
            </div>
        </div>
        <div class="r">
            <div class="r-t">
                <div class="chart-wrap">
                    <h3>一周时间分布</h3>
                    <div class="chart" id="ec05_lineBar_timeDistribute"></div>
                </div>
            </div>
            <div class="r-b">
                <div class="chart-wrap">
                    <h3>当在网上找某首歌的时候</h3>
                    <div class="chart" id="ec06_pie_findSong"></div>
                </div>
            </div>
        </div>
    </main>  
  </div>
</template>

<script>
import '@/css/index.css';
export default {
  name: "indexpage",
  data: function(){
    return {

    }
  }
}
</script>

<style scoped>
</style>