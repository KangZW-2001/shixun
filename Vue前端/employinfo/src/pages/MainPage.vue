<template>  
<div>
  <h2>这里是主页面</h2>
    <div class="input-box">
      <input-com @handleinputc="handleinputp"></input-com>
      <el-button id="button-to-sub" icon="el-icon-back" circle @click="back"></el-button>
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

    }
  },
  methods : {
    handleinputp : function(inputValue){
      console.log("调用了enter函数");
      console.log(inputValue);
    },
    back : function(){
      this.$router.replace('/sub')
    }
  }
}

</script>

<style scoped>
  
</style>