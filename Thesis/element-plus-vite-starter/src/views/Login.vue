<template>
  <div class="login">
    <el-form :model="form" label-width="0px" class="login-container" label-position="left">
      <h3 class="login_title">系统登录</h3>
      <!-- <el-form-item label="姓名"> -->
      <!--   <el-input class="w-50 m-2" v-model="form.name" /> -->
      <!-- </el-form-item> -->
      <!-- <el-form-item label="密码"> -->
      <!--   <el-input class="w-50 m-2" v-model="form.name" /> -->
      <!-- </el-form-item> -->
      <el-form-item>
        <el-input
          type="text"
          v-model="form.loginName"
          auto-complete="off"
          placeholder="账号"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          type="password"
          v-model="form.password"
          auto-complete="off"
          placeholder="密码"
        ></el-input>
      </el-form-item>
    </el-form>
    <el-button type="primary" @click="login">登录</el-button>
    <el-button type="warning" @click="cancel">取消</el-button>
    <!-- <el-button @click="create">Reset</el-button> -->
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, provide } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import { ElMessage } from "element-plus";
// import store from "../store/";

const user = reactive({
  name: "",
});
const router = useRouter();
const login = () => {
  if (form.loginName === "") {
    ElMessage.error("请输入用户名！");
    return;
  }
  if (form.password === "") {
    ElMessage.error("请输入密码！");
    return;
  }
  axios
    .post("/login", { loginName: form.loginName, password: form.password })
    .then((response) => {
      if (response.data === 200) {
        // this.$router.replace({ path: "/" });
        localStorage.setItem("user", form.loginName);
        user.name = form.loginName;
        // store.dispatch("setParam", { key: "userName", value: form.loginName });
        provide("user", user);
        ElMessage.success(user.name);
        router.push({
          name: "home",
          params: {
            userId: form.loginName,
          },
        });
        ElMessage.success("欢迎" + form.loginName);
      }
      if (response.data === 400) {
        ElMessage.error("密码错误！");
      }
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
};

const form = reactive({
  loginName: "",
  password: "",
  name: "liu",
  age: "20",
  sex: "男",
  phone: "1203",
  resource: "",
  desc: "",
});
</script>

<style>
.login {
  text-align: center; /*让div内部文字居中*/
  /* background-color: #fff; */
  border-radius: 20px;
  width: 500px;
  height: 350px;
  margin: auto;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: url("../assets/lufei.jpg") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
