<template>
  <el-config-provider namespace="ep">
    <BaseHeader v-show="$route.name !== 'login'" />
    <div style="display: flex">
      <BaseSide v-show="$route.name !== 'login'" />
      <router-view />
      <!-- <div> -->
      <!-- <img alt="Vue logo" class="element-plus-logo" src="./assets/logo.png" /> -->
      <!-- <h1>欢迎来到仿真评分系统！</h1> -->
      <!-- <HelloWorld msg="Hello Vue 3.0 + Element Plus + Vite" /> -->
      <!-- <Score msg="欢迎来到仿真评分系统！" /> -->
      <!--   </div> -->
    </div>
  </el-config-provider>
</template>

<style>
#app {
  text-align: center;
  color: var(--ep-text-color-primary);
}

.element-plus-logo {
  width: 50%;
}
</style>
