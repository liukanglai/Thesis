<template>
  <el-menu
    default-active="currentActive"
    class="el-menu-vertical-demo"
    :collapse="isCollapse"
    @open="handleOpen"
    @close="handleClose"
    :router="routerOpen"
  >
    <el-menu-item index="/home">
      <template #title>
        <el-icon><HomeFilled /></el-icon>
        <span>主页</span>
      </template>
    </el-menu-item>
    <!-- <el-sub-menu index="2">
      <template #title>
        <el-icon><UserFilled /></el-icon>
        <span>用户信息</span>
      </template>
      <el-menu-item-group>
        <template #title><span>Group One</span></template>
        <el-menu-item index="1-1">item one</el-menu-item>
        <el-menu-item index="1-2"> <router-link to="/"> item two </router-link> </el-menu-item>
      </el-menu-item-group>
    </el-sub-menu> -->
    <el-menu-item index="user">
      <el-icon><UserFilled /></el-icon>
      <template #title>用户信息</template>
    </el-menu-item>
    <el-menu-item index="info">
      <el-icon><InfoFilled /></el-icon>
      <template #title>操作信息</template>
    </el-menu-item>
    <el-menu-item index="score">
      <el-icon><Histogram /></el-icon>
      <template #title>成绩管理</template>
    </el-menu-item>
    <el-menu-item index="rule">
      <el-icon><setting /></el-icon>
      <template #title>规则设置</template>
    </el-menu-item>
    <el-menu-item index="test">
      <el-icon><setting /></el-icon>
      <template #title>Test</template>
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { useRoute } from "vue-router";
import {
  HomeFilled,
  UserFilled,
  Histogram,
  Document,
  Menu as IconMenu,
  Location,
  InfoFilled,
  Setting,
} from "@element-plus/icons-vue";

const route = useRoute();
const currentActive = route.meta.index;

const isCollapse = ref(false);
const routerOpen = ref(true);
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
