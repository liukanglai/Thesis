<template>
  <div class="table-container">
    <el-form-item>
      <!-- <el-button type="info" @click="onLook">查询得分</el-button> -->
      <el-button type="success" @click="calculate">开始评分</el-button>
      <!-- <el-button type="info" @click="toPdf">打印</el-button> -->
      <!-- <el-button>Cancel</el-button> -->
      <Fuzzy></Fuzzy>
    </el-form-item>
    <div class="content" ref="contentBox">
      <el-form :model="form" label-width="120px">
        <el-form-item label="">
          操作员
          <el-input class="w-50 m-2" v-model="user" disabled />
          总体标准分
          <el-input class="w-50 m-2" v-model="totolScore" disabled />
          您的得分
          <el-input class="w-50 m-2" v-model="yourScore" disabled />
          <!-- 操作日期 -->
        </el-form-item>
        <!-- 各操作步骤得分 -->
        <!-- <el-form-item label=""> -->
        <!--   <el-input class="w-40 m-2" v-model="form.name" disabled /> -->
        <!--   <el-input class="w-40 m-2" v-model="form.name" disabled /> -->
        <!--   <el-input class="w-40 m-2" v-model="form.name" disabled /> -->
        <!--   <el-input class="w-40 m-2" v-model="form.name" disabled /> -->
        <!--   <el-input class="w-40 m-2" v-model="form.name" disabled /> -->
        <!--   <el-input class="w-40 m-2" v-model="form.name" disabled /> -->
        <!--   <el-input class="w-40 m-2" v-model="form.name" disabled /> -->
        <!--   <el-input class="w-40 m-2" v-model="form.name" disabled /> -->
        <!-- </el-form-item> -->

        <!-- 各操作变量详情 -->
        <!-- <el-table :data="tableData" border style="width: 100%"> -->
        <!--   <el-table-column prop="name" label="变量名" width="180" /> -->
        <!--   <el-table-column prop="name" label="解释说明" width="180" /> -->
        <!--   <el-table-column prop="norm" label="标准值" width="180" /> -->
        <!--   <el-table-column prop="real" label="实际操作值" width="180" /> -->
        <!--   <el-table-column prop="score" label="得分" /> -->
        <!-- </el-table> -->
      </el-form>
      <!-- 步骤模糊矩阵，从左到右依次是总得分，八个步骤，步骤中3个变量，4个模具，操作质量的9个变量。 -->
      <div class="tablein-container">
        <el-table :data="finallyScore" border style="width: 100%">
          <el-table-column label="Name">
            <template #default="scope">
              {{ name[scope.$index].name }}
            </template>
          </el-table-column>
          <el-table-column prop="data1" label="优秀"></el-table-column>
          <el-table-column prop="data2" label="良好"></el-table-column>
          <el-table-column prop="data3" label="一般"></el-table-column>
          <el-table-column prop="data4" label="较差"></el-table-column>
        </el-table>
        <el-table :data="finallyFuzzy" border style="width: 100%">
          <el-table-column label="Name">
            <template #default="scope">
              {{ name1[scope.$index].name }}
            </template>
          </el-table-column>
          <el-table-column prop="0" label="优秀"></el-table-column>
          <el-table-column prop="1" label="良好"></el-table-column>
          <el-table-column prop="2" label="一般"></el-table-column>
          <el-table-column prop="3" label="较差"></el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
// 页面：各个模糊矩阵即可。
import html2canvas from "html2canvas";
import jspdf from "jspdf";
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import axios from "axios";
import Fuzzy from "./Fuzzy.vue";

const name = reactive([{ name: "最终等分" }]);
const name1 = reactive([
  { name: "步骤一" },
  { name: "步骤二" },
  { name: "步骤三" },
  { name: "步骤四" },
  { name: "步骤五" },
  { name: "步骤六" },
  { name: "步骤七" },
  { name: "步骤八" },
]);

const finallyScore = reactive([{ data1: "", data2: "", data3: "", data4: "" }]);
const finallyFuzzy = reactive([]);
const stepFuzzy = reactive([]);
const qualityFuzzy = reactive([]);
const objectFuzzy = reactive([]);
const user = localStorage.getItem("user");

const totolScore = ref(89.92802827638248);
const yourScore = ref();

// do not use same name with ref
const form = reactive({
  name: "1",
  wrongs: "1",
  region: "",
  date1: "",
  date2: "",
  delivery: false,
  type: [],
  resource: "",
  desc: "",
});

const tableData = [
  {
    name: "绳高度",
    norm: "70",
    real: "60",
    score: "3",
  },
  {
    name: "机械臂高度",
    norm: "7",
    real: "6",
    score: "3",
  },
  {
    name: "Tom",
    norm: "70",
    real: "60",
    score: "3",
  },
  {
    name: "Tom",
    norm: "70",
    real: "60",
    score: "3",
  },
  {
    name: "Tom",
    norm: "70",
    real: "60",
    score: "3",
  },
  {
    name: "Tom",
    norm: "70",
    real: "60",
    score: "3",
  },
];

const calculate = () => {
  axios
    .get("/score/calculate/" + user)
    .then((response) => {
      // Object.assign(finallyScore, response.data);
      const data = response.data;
      if (typeof data === "undefined" || data === null || data === "") {
        ElMessage.error("操作未完成！仅对已操作进行打分");
      } else {
        data.forEach((item, index) => {
          finallyScore[0]["data" + (index + 1)] = item;
        });
        yourScore.value =
          finallyScore[0]["data1"] * 100 +
          finallyScore[0]["data2"] * 80 +
          finallyScore[0]["data3"] * 60 +
          finallyScore[0]["data4"] * 40;
      }
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  axios
    .get("/score/finallyFuzzy/" + user)
    .then((response) => {
      // alert(response.data);
      Object.assign(finallyFuzzy, response.data);
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
};

const contentBox = ref(null);

const toPdf = () => {
  html2canvas(contentBox.value, {
    useCORS: true,
  }).then((canvas) => {
    const dataURL = canvas.toDataURL("image/png");

    const pdf = new jspdf("", "pt", "a4");
    const contentWidth = canvas.width;
    let leftHeight = contentBox.value.scrollHeight;
    const pageHeight = (contentWidth / 592.28) * 841.89;
    let position = 0;

    while (leftHeight > 0) {
      pdf.addImage(dataURL, "JPEG", 0, position, 595.28, 841.89);
      leftHeight -= pageHeight;
      position -= 841.89;
      if (leftHeight > 0) {
        // pdf.addPage();
      }
    }

    pdf.save("评分报告"); //导出及文档名称
  });
};
</script>
<style>
.table-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.tablein-container {
  display: flex;
  flex-direction: row;
  align-items: center;
}
</style>

<!-- [0.5740969929676946, 0.3482074278837349, 0.0776955791485706, 0.0] -->
