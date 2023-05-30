<template>
  <div class="home">
    <div
      style="
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin-top: -50px;
      "
    >
      <img alt="Vue logo" class="element-plus-logo" src="../assets/院徽+校名+院名-外蓝内白.png" />
      <h1>欢迎来到仿真评分系统！</h1>
    </div>
    <!-- <img alt="Vue logo" class="element-plus-logo" src="../assets/院徽+校名+院名-外蓝内白.png" /> -->
    <!-- <h1>欢迎来到仿真评分系统！</h1> -->
    <!-- <h1 -->
    <!--   style=" -->
    <!--     text-align: center; -->
    <!--     position: fixed; -->
    <!--     top: 50%; -->
    <!--     left: 50%; -->
    <!--     transform: translate(-50%, -50%); -->
    <!--   " -->
    <!-- > -->
    <!--   欢迎来到仿真评分系统！ -->
    <!-- </h1> -->
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
      myHeading.textContent = user + "，欢迎来到仿真评分系统！";
    },
  },
};
</script>
