<template>
  <div class="home">
    <img alt="Vue logo" class="element-plus-logo" src="../assets/logo.png" />
    <h1>欢迎来到仿真评分系统！</h1>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "../components/HelloWorld.vue";
// import { createStore } from "../store/index.js";
import { inject } from "vue";

export default {
  name: "HomeView",
  components: {
    HelloWorld,
  },
  mounted() {
    this.setHead();
  },
  methods: {
    setHead() {
      const userName = inject("user");
      let myHeading = document.querySelector("h1"); // 获取h1标题
      let user = localStorage.getItem("user");
      // console.log(user.value.name);
      // let user = this.$store.state.count;
      myHeading.textContent = user + "欢迎来到仿真评分系统！";
    },
  },
};
</script>
