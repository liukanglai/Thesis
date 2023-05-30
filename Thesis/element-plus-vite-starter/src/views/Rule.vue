<template>
  <div>
    <h2>水下管汇安装系统评分规则</h2>
    <!-- <el-form-item label="水下管汇安装系统评分规则"> -->
    <!-- <el-input v-model="describe" type="textarea" rows="10" disabled /> -->
    <!-- </el-form-item> -->
    <el-table :data="tableData" stripe style="width: 1700px">
      <!-- <el-table :data="tableData" stripe> -->
      <el-table-column prop="id" label="步骤ID" width="70" />
      <el-table-column prop="name" label="步骤名" width="160" />
      <el-table-column prop="info" label="操作描述" width="160" />
      <el-table-column prop="usetime" label="用时标准值（均值）" width="120" />
      <el-table-column prop="usetimesd" label="用时标准差" width="120" />
      <el-table-column prop="distance" label="distance标准（均值）" width="120" />
      <el-table-column prop="distancesd" label="distance标准差" width="120" />
      <el-table-column fixed="right" width="">
        <template #default="scope">
          <el-button text type="primary" @click="changeTime(scope.row.id)">更改用时均值</el-button>
          <el-button text type="primary" @click="changeTimeSD(scope.row.id)"
            >更改用时标准差</el-button
          >
          <el-button text type="primary" @click="changeDis(scope.row.id)">更改距离均值</el-button>
          <el-button text type="primary" @click="changeDisSD(scope.row.id)"
            >更改距离标准差</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <AHPWeightVue></AHPWeightVue>
    <h3>详细信息</h3>
    <el-table :data="tableData1" stripe style="width: 1700px">
      <el-table-column prop="itemid" label="步骤ID" width="70" />
      <el-table-column prop="objectid" label="模具id" width="70" />
      <el-table-column prop="objectx" label="objectx标准值" width="100" />
      <el-table-column prop="objectxsd" label="objectx标准差" width="100" />
      <el-table-column prop="objecty" label="objecty标准值" width="100" />
      <el-table-column prop="objectysd" label="objecty标准差" width="100" />
      <el-table-column prop="objectz" label="objectz标准值" width="100" />
      <el-table-column prop="objectzsd" label="objectz标准差" width="100" />
      <el-table-column prop="anglex" label="anglex标准值" width="100" />
      <el-table-column prop="anglexsd" label="anglex标准差" width="100" />
      <el-table-column prop="angley" label="angley标准值" width="100" />
      <el-table-column prop="angleysd" label="angley标准差" width="100" />
      <el-table-column prop="anglez" label="anglez标准值" width="100" />
      <el-table-column prop="anglezsd" label="anglez标准差" width="100" />
      <el-table-column prop="speedx" label="speedx标准值" width="100" />
      <el-table-column prop="speedxsd" label="speedx标准差" width="100" />
      <el-table-column prop="speedy" label="speedy标准值" width="100" />
      <el-table-column prop="speedysd" label="speedy标准差" width="100" />
      <el-table-column prop="speedz" label="speedz标准值" width="100" />
      <el-table-column prop="speedzsd" label="speedz标准差" />
      <!-- <el-table-column prop="wrong" label="错误步数" /> -->
    </el-table>
  </div>
</template>

<script lang="ts" setup>
import { ElMessage, ElMessageBox } from "element-plus";
import axios from "axios";
import { ref, onMounted, reactive, toRaw, shallowReactive, toRefs, inject } from "vue";
import AHPWeightVue from "./AHPWeight.vue";

const describe =
  "主要有三个模具(object)使用：1.管汇 2.ROV 3.吊机 \n具体仿真过程分三个步骤(itemid)：\n一：吊起管汇：吊机左移，钩住管汇，吊起管汇；\n二：下放管汇：管汇入水，管汇下放海底；\n三：ROV抓取管汇：ROV高度调整，ROV抓取管汇；\n四：ROV推管汇进行对中\n在仿真操作的过程中，会对三个模具的状态进行记录(数据在trail_n表中), objectid objectx objecty objectz anglex angley anglez intime speedx speedy speedz\n每一秒就有好多记录\n对8个步骤，会记录下每个步骤的仿真时间，distance操作精准度(数据在rec_n表中)itemid status utime distance timescore disscore score weight intime";

// TODO:
// 1. 打印
// 操作质量，按时间来，直接操作它的数据库。操作时间也一样，改为用时
// 后续：研究一下vue+spring的代码？看基础学习，不要看些视频和文章了只适合速成。
// https://github.com/lin-xin/vue-manage-system
//
// tomorrow: 设置标准数据库，完善下面按钮，隶属度函数设置
// 成绩模块修改
// 加入空间标准
const tableData = reactive([
  {
    id: "1",
    name: "一：吊起管汇",
    info: "吊机左移",
    usetime: "",
    usetimesd: "",
    distance: "",
    distancesd: "",
  },
  {
    id: "2",
    name: "一：吊起管汇",
    info: "钩住管汇",
    usetime: "",
    usetimesd: "",
    distance: "",
    distancesd: "",
  },
  {
    id: "3",
    name: "一：吊起管汇",
    info: "吊起管汇",
    usetime: "",
    usetimesd: "",
    distance: "",
    distancesd: "",
  },
  {
    id: "4",
    name: "二：下放管汇",
    info: "管汇入水",
    usetime: "",
    usetimesd: "",
    distance: "",
    distancesd: "",
  },
  {
    id: "5",
    name: "二：下放管汇",
    info: "管汇下放海底",
    usetime: "",
    usetimesd: "",
    distance: "",
    distancesd: "",
  },
  {
    id: "6",
    name: "三：ROV抓取管汇",
    info: "ROV高度调整",
    usetime: "",
    usetimesd: "",
    distance: "",
    distancesd: "",
  },
  {
    id: "7",
    name: "三：ROV抓取管汇",
    info: "ROV抓取管汇",
    usetime: "",
    usetimesd: "",
    distance: "",
    distancesd: "",
  },
  {
    id: "8",
    name: "四：ROV抓取管汇进行对中",
    info: "ROV抓取管汇进行水平对中",
    usetime: "",
    usetimesd: "",
    distance: "",
    distancesd: "",
  },
]);
const tableData1 = reactive([]);
const user = localStorage.getItem("user");

axios
  .get("/rule1")
  .then((response) => {
    // Object.assign(form, response.data);
    const data = response.data;
    data.forEach((item, index) => {
      Object.assign(tableData[index], item);
    });
    // const tableData = reactive({ ...tableData0, ...response.data });
    // const tableData = Object.assign({}, tableData0, response.data);
  })
  .catch((error) => {
    ElMessage.error("后台未连接！");
    console.log(error);
  });

const changeTime = (id: string) => {
  ElMessageBox.prompt("请输入0到1000000的数", "提示", {
    confirmButtonText: "OK",
    cancelButtonText: "Cancel",
    inputPattern: /^\d{1,6}$/,
    inputErrorMessage: "请输入0到1000000的整数",
    customClass: "custom-message-box",
  })
    .then(({ value }) => {
      tableData[Number(id) - 1].usetime = value;
      axios
        .get("/rule1/setTime/" + id + "/" + value)
        .then((response) => {
          ElMessage.success("修改成功！");
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
const changeTimeSD = (id: string) => {
  ElMessageBox.prompt("请输入0到10000的数", "提示", {
    confirmButtonText: "OK",
    cancelButtonText: "Cancel",
    inputPattern: /^\d{1,4}$/,
    inputErrorMessage: "请输入0到10000的整数",
    customClass: "custom-message-box",
  })
    .then(({ value }) => {
      tableData[Number(id) - 1].usetimesd = value;
      axios
        .get("/rule1/setTimeSD/" + id + "/" + value)
        .then((response) => {
          ElMessage.success("修改成功！");
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
const changeDis = (id: string) => {
  ElMessageBox.prompt("请输入1到10之间的数字（可保留小数点后两位）", "提示", {
    confirmButtonText: "OK",
    cancelButtonText: "Cancel",
    inputPattern: /^(?!0\d)(\d|[1-9]\d)(\.\d+)?$/,
    inputErrorMessage: "请输入1到10之间的数字（可保留小数点后两位）",
    customClass: "custom-message-box",
  })
    .then(({ value }) => {
      tableData[Number(id) - 1].distance = value;
      axios
        .get("/rule1/setDis/" + id + "/" + value)
        .then((response) => {
          ElMessage.success("修改成功！");
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
const changeDisSD = (id: string) => {
  ElMessageBox.prompt("请输入1到10之间的数字（可保留小数点后两位）", "提示", {
    confirmButtonText: "OK",
    cancelButtonText: "Cancel",
    inputPattern: /^(?!0\d)(\d|[1-9]\d)(\.\d+)?$/,
    inputErrorMessage: "请输入1到10之间的数字（可保留小数点后两位）",
    customClass: "custom-message-box",
  })
    .then(({ value }) => {
      tableData[Number(id) - 1].distancesd = value;
      axios
        .get("/rule1/setDisSD/" + id + "/" + value)
        .then((response) => {
          ElMessage.success("修改成功！");
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
axios
  .get("/rule2")
  .then((response) => {
    Object.assign(tableData1, response.data);
  })
  .catch((error) => {
    ElMessage.error("后台未连接！");
    console.log(error);
  });
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
