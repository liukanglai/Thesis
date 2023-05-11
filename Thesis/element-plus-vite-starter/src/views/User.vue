<template>
  <el-form :model="form1" label-width="120px">
    <el-form-item label="">
      id<el-input class="w-50 m-2" v-model="form.id" disabled /> 姓名<el-input
        class="w-50 m-2"
        v-model="form.userName"
        disabled
      />
      性别<el-input class="w-50 m-2" v-model="form.sex" disabled /> 年龄<el-input
        class="w-50 m-2"
        v-model="form.age"
        disabled
      />
      联系电话<el-input class="w-50 m-2" v-model="form.phone" disabled /> 密码<el-input
        type="password"
        class="w-50 m-2"
        v-model="form.password"
        disabled
      />
    </el-form-item>
    操作历史
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="操作ID" width="180" />
      <el-table-column prop="sex" label="操作日期" width="180" />
      <el-table-column prop="phone" label="操作日期" width="180" />
      <el-table-column prop="age" label="得分" />
      <el-table-column prop="userName" label="得分" />
    </el-table>

    <el-form-item label="Activity form">
      <el-input v-model="form.phone" type="textarea" />
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import axios from "axios";
import { onMounted, reactive, toRaw, shallowReactive, toRefs } from "vue";
import { ElMessage } from "element-plus";

// do not use same name with ref
const form = reactive({
  id: "",
  userName: "",
  loginName: "",
  password: "",
  age: "",
  sex: "",
  phone: "",
});
const tableData = [
  {
    id: "1",
    date: "2023-03-01",
    real: "60",
    score: "3",
    userName: "",
  },
];
const onSubmit = () => {
  console.log("submit!");
};
const user = localStorage.getItem("user");
// form.userName = localStorage.getItem("user");
// onMounted(() => {
//   getUserList();
// });

// const getUserList = () => {
axios
  .get("/user/" + user)
  .then((response) => {
    // ElMessage.error(response.data);
    // Object.assign(form, response.data);
    // tableData = response.data;
    const data = response.data;
    Object.assign(form, response.data[0]);
  })
  .catch((error) => {
    ElMessage.error("后台未连接！");
    console.log(error);
  });
// };
const form1 = toRefs(form);
</script>
