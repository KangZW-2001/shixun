<template>
  <div id="app">
    <ul>
      <li v-for="(item,id) of testInfo"> {{item}}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'App',
  data : function(){
    return {
      testInfo : {}
    }
  },
  created : function(){
    // 使用axios获取数据
    var that = this;
    this.$axios({
      method: "get",
      url: "http://localhost:8080/test"
    }).then(
      function(info){
        console.log(typeof info);
        console.log(info);
        that.testInfo = info;
      }
    )
  }
}
</script>

<style>

</style>
