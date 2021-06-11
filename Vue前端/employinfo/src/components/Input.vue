<template>
  <div class="input-box" style="margin-top: 15px;">
    <el-input placeholder="请输入内容" v-model="input3" class="input-with-select" @keydown.enter="getInput">
      <el-button slot="append" icon="el-icon-search" @click="getInput"></el-button>
    </el-input>
  </div>
</template>

<script>
export default {
  name: 'Input',
  data: function(){
    return {
      input3: "",
    }
  },
  methods : {
    getInput : function(){
      console.log("调用了子组件的点击函数")
      console.log(this.input3);
      this.$emit('handleinputc', this.input3);
    }
  }
}
</script>

<style scoped>
  .input-with-select {
    width: 600px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
</style>