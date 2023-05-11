<template>
  <div>
    <el-form-item label="水下管汇安装系统评分规则">
      <el-input v-model="describe" type="textarea" rows="10" disabled />
    </el-form-item>
    <el-table :data="tableData" stripe style="width: 1500px">
      <el-table-column prop="id" label="步骤ID" width="180" />
      <el-table-column prop="name" label="步骤名" width="280" />
      <el-table-column prop="info" label="操作描述" width="280" />
      <el-table-column prop="usetime" label="用时标准值" width="180" />
      <el-table-column prop="distance" label="distance标准" width="180" />
      <el-table-column fixed="right" width="">
        <template #default="scope">
          <el-button text type="primary" @click="changeTime(scope.row.id)">更改用时</el-button>
          <el-button text type="primary" @click="changeDis(scope.row.id)">更改距离</el-button>
        </template>
      </el-table-column>
    </el-table>
    <Test></Test>
    <el-button type="danger" round @click="membership">设置隶属度函数</el-button>
  </div>
</template>

<script lang="ts" setup>
import { ElMessage, ElMessageBox } from "element-plus";
import axios from "axios";
import { ref, onMounted, reactive, toRaw, shallowReactive, toRefs, inject } from "vue";
import Test from "./Test.vue";

const describe =
  "主要有三个模具(object)使用：1.管汇 2.ROV 3.吊机 \n具体仿真过程分三个步骤(itemid)：\n一：吊起管汇：吊机左移，钩住管汇，吊起管汇；\n二：下放管汇：管汇入水，管汇下放海底；\n三：ROV抓取管汇：ROV高度调整，ROV抓取管汇；\n四：ROV推管汇进行对中\n在仿真操作的过程中，会对三个模具的状态进行记录(数据在trail_n表中), objectid objectx objecty objectz anglex angley anglez intime speedx speedy speedz\n每一秒就有一个记录，怎么办？\n对8个步骤，会记录下每个步骤的仿真时间，以及距离？(数据在rec_n表中)itemid status utime distance timescore disscore score weight intime \ndistance 是什么？";

// TODO:
// 模糊矩阵修改，先是time和dis 的权重，打分，再8个步骤进行权重打分。
// 修改AHP矩阵
// 隶属度函数
// 在time和dis的基础上加上模型状态分 后续设置8个步骤结束时4个模型的状态，一共8个状态，每个步骤根据2个状态进行打分。在综合打分。
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
const membership = () => {
  axios
    .get("/score/membership" + membership)
    .then((response) => {
      // ElMessage(response.data);
      alert(response.data);
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  // router.push("/score");
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
