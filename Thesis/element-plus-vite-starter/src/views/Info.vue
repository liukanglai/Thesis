<template>
  <div>
    <el-table :data="tableData0" stripe style="width: 1500px">
      <el-table-column prop="id" label="步骤ID" width="180" />
      <el-table-column prop="name" label="步骤名" width="280" />
      <el-table-column prop="info" label="操作描述" width="280" />
      <el-table-column prop="usetime" label="所用时间" width="180" />
      <el-table-column prop="distance" label="distance?" />
      <!-- <el-table-column prop="wrong" label="错误步数" /> -->
    </el-table>

    <h3>详细信息</h3>

    <el-table :data="tableData1" stripe style="width: 1500px">
      <el-table-column prop="id" label="步骤ID" width="120" />
      <el-table-column prop="objectid" label="模具id" width="120" />
      <el-table-column prop="objectx" label="objectx" width="120" />
      <el-table-column prop="objecty" label="objecty" width="120" />
      <el-table-column prop="objectz" label="objectz" width="120" />
      <el-table-column prop="anglex" label="anglex" width="120" />
      <el-table-column prop="angley" label="angley" width="120" />
      <el-table-column prop="anglez" label="anglez" width="120" />
      <el-table-column prop="speedx" label="speedx" width="120" />
      <el-table-column prop="speedy" label="speedy" width="120" />
      <el-table-column prop="speedz" label="speedz" />
      <!-- <el-table-column prop="wrong" label="错误步数" /> -->
    </el-table>

    <el-button type="warning" round @click="open">查看评分规则</el-button>
    <el-button type="success" round @click="calculate">开始评分</el-button>
    <!-- <el-button type="info" round>Info</el-button> -->
    <!-- <el-button type="warning" round>Warning</el-button> -->
    <!-- <el-button type="danger" round>Danger</el-button> -->
  </div>
</template>

<style>
.ep-button {
  margin: 4px;
}
.ep-button + .ep-button {
  margin-left: 0;
  margin: 4px;
}
</style>

<script setup lang="ts">
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
import axios from "axios";
import { ref, onMounted, reactive, toRaw, shallowReactive, toRefs, inject } from "vue";

const router = useRouter();
const open = () => {
  // ElMessage.success("Hello");
  router.push("/rule");
};

// 一：吊起管汇：吊机左移，钩住管汇，吊起管汇；
// 二：下放管汇：管汇入水，管汇下放海底；
// 三：ROV抓取管汇：ROV高度调整，ROV抓取管汇；
// 四：ROV推管汇进行对中
const tableData0 = reactive([
  {
    id: "1",
    name: "一：吊起管汇",
    info: "吊机左移",
    usetime: "",
    distance: "",
  },
  {
    id: "2",
    name: "一：吊起管汇",
    info: "钩住管汇",
    usetime: "",
    distance: "",
  },
  {
    id: "3",
    name: "一：吊起管汇",
    info: "吊起管汇",
    usetime: "",
    distance: "",
  },
  {
    id: "4",
    name: "二：下放管汇",
    info: "管汇入水",
    usetime: "",
    distance: "",
  },
  {
    id: "5",
    name: "二：下放管汇",
    info: "管汇下放海底",
    usetime: "",
    distance: "",
  },
  {
    id: "6",
    name: "三：ROV抓取管汇",
    info: "ROV高度调整",
    usetime: "",
    distance: "",
  },
  {
    id: "7",
    name: "三：ROV抓取管汇",
    info: "ROV抓取管汇",
    usetime: "",
    distance: "",
  },
  {
    id: "8",
    name: "四：ROV抓取管汇进行对中",
    info: "ROV抓取管汇进行水平对中",
    usetime: "",
    distance: "",
  },
]);
const tableData1 = reactive([]);
const user = localStorage.getItem("user");

axios
  .get("/info/" + user)
  .then((response) => {
    // ElMessage.error(response.data);
    // Object.assign(form, response.data);
    // tableData = response.data;
    const data = response.data;
    data.forEach((item, index) => {
      Object.assign(tableData0[index], item);
    });
    // Object.assign(tableData0, merged);
    // Object.assign(tableData0, response.data);
    // const tableData = reactive({ ...tableData0, ...response.data });
    // const tableData = Object.assign({}, tableData0, response.data);
  })
  .catch((error) => {
    ElMessage.error("后台未连接！");
    console.log(error);
  });

axios
  .get("/info1/" + user)
  .then((response) => {
    Object.assign(tableData1, response.data);
  })
  .catch((error) => {
    ElMessage.error("后台未连接！");
    console.log(error);
  });

const calculate = () => {
  axios
    .get("/score/calculate/" + user)
    .then((response) => {
      // ElMessage(response.data);
      alert(response.data);
      ElMessage("评分成功，请在成绩管理模块查看");
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  // router.push("/score");
};
</script>
<!-- "管汇安装准备", -->
<!-- "吊机起吊管汇", -->
<!-- "旋转吊机,将管汇置于海面上方", -->
<!-- "下放管汇通过飞溅区", -->
<!--  "减慢放缆速度,将管汇下放至基盘上方", -->
<!-- "利用 ROV 将管汇与基盘上较高的桩腿对中", -->
<!-- "利用 ROV 调整管汇主体位置,使其与基盘另一桩腿对中", -->
<!-- "继续下放并利用 ROV 将管汇主体和管汇基盘锁紧", -->
<!-- "解除索具，回收索具", -->
