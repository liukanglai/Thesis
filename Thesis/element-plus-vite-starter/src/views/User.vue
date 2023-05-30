<template>
  <el-form :model="form1" label-width="120px">
    <el-form-item label="">
      id<el-input class="w-30 m-2" v-model="form.id" disabled /> 姓名<el-input
        class="w-50 m-2"
        v-model="form.userName"
        disabled
      />
      性别<el-input class="w-30 m-2" v-model="form.sex" disabled /> 年龄<el-input
        class="w-30 m-2"
        v-model="form.age"
        disabled
      />
      联系电话<el-input class="w-50 m-2" v-model="form.phone" disabled />
    </el-form-item>
    <!-- 操作历史 -->
    <!-- <el-table :data="tableData" border style="width: 100%"> -->
    <!--   <el-table-column prop="id" label="操作ID" width="180" /> -->
    <!--   <el-table-column prop="sex" label="操作日期" width="180" /> -->
    <!--   <el-table-column prop="phone" label="操作日期" width="180" /> -->
    <!--   <el-table-column prop="age" label="得分" /> -->
    <!--   <el-table-column prop="userName" label="得分" /> -->
    <!-- </el-table> -->

    <!-- <el-form-item label="Activity form"> -->
    <!--   <el-input v-model="form.phone" type="textarea" /> -->
    <!-- </el-form-item> -->
    <el-form-item label="">
      密码<el-input
        :type="isPassword ? 'password' : 'text'"
        class="w-50 m-2"
        v-model="form.password"
        disabled
      />

      <el-button type="primary" @click="toggleDisabled">{{
        isPassword ? "显示" : "隐藏"
      }}</el-button>
      <el-button type="primary" @click="change">更改密码</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import axios from "axios";
import { onMounted, ref, reactive, toRaw, shallowReactive, toRefs } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";

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
const isPassword = ref(true);
const toggleDisabled = () => {
  isPassword.value = !isPassword.value;
};
const user = localStorage.getItem("user");

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
const form1 = toRefs(form);

const change = () => {
  ElMessageBox.prompt("请输入旧密码", "提示", {
    confirmButtonText: "OK",
    cancelButtonText: "Cancel",
    // inputPattern: /^(?!0\d)(\d|[1-9]\d)(\.\d+)?$/,
    // inputErrorMessage: "请输入",
    customClass: "custom-message-box",
  })
    .then(({ value }) => {
      axios
        .post("/login", { loginName: form.loginName, password: value })
        .then((response) => {
          if (response.data === 200) {
            ElMessage.success("密码正确！");
            ElMessageBox.prompt("请输入新密码", "提示", {
              confirmButtonText: "OK",
              cancelButtonText: "Cancel",
              // inputPattern: /^(?!0\d)(\d|[1-9]\d)(\.\d+)?$/,
              // inputErrorMessage: "请输入",
              customClass: "custom-message-box",
            })
              .then(({ value }) => {
                axios
                  .post("/user/change/" + form.loginName + "/" + value)
                  .then((response) => {
                    if (response.data === 200) {
                      ElMessage.success("更改成功！");
                    } else {
                      ElMessage.error("更改失败！");
                    }
                  })
                  .catch((error) => {
                    ElMessage.error("后台未连接！");
                  });
              })
              .catch(() => {
                ElMessage({
                  type: "info",
                  message: "Input canceled",
                });
              });
          }
          if (response.data === 400) {
            ElMessage.error("密码错误！");
          }
        })
        .catch((error) => {
          ElMessage.error("后台未连接！");
          console.log(error);
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
<style>
.custom-message-box {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /*border: 3px solid green;*/
  padding: 10px;
  width: 400px !important;
  height: 250px !important;
}
</style>
