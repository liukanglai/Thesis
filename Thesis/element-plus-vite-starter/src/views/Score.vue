<template>
  <el-form :model="form" label-width="120px">
    <el-form-item label="操作ID">
      <el-input class="w-50 m-2" v-model="form.name" disabled />
      操作员<el-input class="w-50 m-2" v-model="form.name" disabled /> 操作日期<el-input
        class="w-50 m-2"
        v-model="form.name"
        disabled
      />
    </el-form-item>
    <el-form-item label="错误操作得分">
      <el-input class="w-50 m-2" v-model="form.name" disabled />
      操作错误次数<el-input class="w-50 m-2" v-model="form.wrongs" disabled />
    </el-form-item>
    <el-form-item label="操作时间得分">
      <el-input class="w-50 m-2" v-model="form.name" disabled />
      操作时间<el-input class="w-50 m-2" v-model="form.name" disabled />
    </el-form-item>
    <el-form-item label="操作质量得分">
      <el-input class="w-50 m-2" v-model="form.name" disabled />
    </el-form-item>
    <el-form-item label="专家预估分数">
      <el-input class="w-50 m-2" v-model="form.name" disabled />
    </el-form-item>
    <el-form-item label="综合得分">
      <el-input class="w-50 m-2" v-model="form.name" disabled />
    </el-form-item>

    <div>
      各操作变量详情
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="name" label="变量名" width="180" />
        <el-table-column prop="name" label="解释说明" width="180" />
        <el-table-column prop="norm" label="标准值" width="180" />
        <el-table-column prop="real" label="实际操作值" width="180" />
        <el-table-column prop="score" label="得分" />
      </el-table>
    </div>

    <el-form-item>
      <el-button type="info" @click="onLook">查询得分</el-button>
      <el-button type="primary" @click="onSubmit">打印</el-button>
      <el-button>Cancel</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import { reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";

// do not use same name with ref
const form = reactive({
  name: "1",
  wrongs: "1",
  region: "",
  date1: "",
  date2: "",
  delivery: false,
  type: [],
  resource: "",
  desc: "",
});

const tableData = [
  {
    name: "绳高度",
    norm: "70",
    real: "60",
    score: "3",
  },
  {
    name: "机械臂高度",
    norm: "7",
    real: "6",
    score: "3",
  },
  {
    name: "Tom",
    norm: "70",
    real: "60",
    score: "3",
  },
  {
    name: "Tom",
    norm: "70",
    real: "60",
    score: "3",
  },
  {
    name: "Tom",
    norm: "70",
    real: "60",
    score: "3",
  },
  {
    name: "Tom",
    norm: "70",
    real: "60",
    score: "3",
  },
];

const onSubmit = () => {
  console.log("submit!");
};
const onLook = () => {
  ElMessageBox.prompt("输入操作ID", "Tip", {
    confirmButtonText: "OK",
    cancelButtonText: "Cancel",
    // inputPattern:
    //   /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
    // inputErrorMessage: "Invalid Email",
  })
    .then(({ value }) => {
      ElMessage({
        type: "success",
        message: `Your email is:${value}`,
      });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "Input canceled",
      });
    });
};
</script>
