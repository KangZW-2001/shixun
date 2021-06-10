<template>
<div>
  <div style="margin-top: 15px;">
    <el-input placeholder="请输入内容" v-model="input3" class="input-with-select">
      <el-button slot="append" icon="el-icon-search"></el-button>
    </el-input>
  </div>
</div>
</template>

<script>
export default {
  name: 'Input',
  data: function(){
    return {
      input1: "",
      input2: "",
      input3: "",
      select: ''
    }
  }
}
</script>

<style scoped>
  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
</style>