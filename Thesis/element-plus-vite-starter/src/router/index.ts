import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const routes = [
  // { path: "/", redirect: "/index" },
  {
    path: "/",
    name: "login",
    component: () => import("../views/Login.vue"),
  },
  {
    path: "/home",
    name: "home",
    component: HomeView,
    meta: { index: "home" },
  },
  {
    path: "/info",
    name: "info",
    component: () => import("../views/Info.vue"),
    meta: { index: "info" },
  },
  {
    path: "/score",
    name: "score",
    component: () => import("../views/Score.vue"),
    meta: { index: "score" },
  },
  {
    path: "/rule",
    name: "rule",
    component: () => import("../views/Rule.vue"),
    meta: { index: "rule" },
  },
  {
    path: "/info",
    name: "info",
    component: () => import("../views/Info.vue"),
    meta: { index: "info" },
  },
  {
    path: "/user",
    name: "user",
    component: () => import("../views/User.vue"),
    meta: { index: "user" },
  },
  {
    path: "/test",
    name: "test",
    component: () => import("../views/Test.vue"),
    meta: { index: "test" },
  },
];

const router = createRouter({
  //history模式：createWebHistory , hash模式：createWebHashHistory
  // history: createWebHistory(process.env.BASE_URL),
  history: createWebHistory(),
  routes,
});

export default router;
