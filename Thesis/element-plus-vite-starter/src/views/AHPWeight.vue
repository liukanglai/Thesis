<template>
  <el-button type="danger" round @click="ahp">修改AHP判断矩阵</el-button>
  <el-dialog title="AHP矩阵" v-model="dialogVisibleahp" @close="handleClose">
    <h2>步骤判断矩阵</h2>
    <el-table :data="tableDataahp1" style="width: 100%">
      <el-table-column prop="0" label="步骤1"></el-table-column>
      <el-table-column prop="1" label="步骤2"></el-table-column>
      <el-table-column prop="2" label="步骤3"></el-table-column>
      <el-table-column prop="3" label="步骤4"></el-table-column>
      <el-table-column prop="4" label="步骤5"></el-table-column>
      <el-table-column prop="5" label="步骤6"></el-table-column>
      <el-table-column prop="6" label="步骤7"></el-table-column>
      <el-table-column prop="7" label="步骤8"></el-table-column>
    </el-table>
    CR:
    <el-input class="w-50 m-2" v-model="tableDataCR[0].data1" disabled />
    <h2>步骤操作层判断矩阵</h2>
    <el-table :data="tableDataahp2" style="width: 100%">
      <el-table-column prop="0" label="操作时间"></el-table-column>
      <el-table-column prop="1" label="操作精准度"></el-table-column>
      <el-table-column prop="2" label="操作质量"></el-table-column>
    </el-table>
    CR:
    <el-input class="w-50 m-2" v-model="tableDataCR[0].data2" disabled />
    <h2>模具判断矩阵</h2>
    <el-table :data="tableDataahp4" style="width: 100%">
      <el-table-column prop="0" label="模具1"></el-table-column>
      <el-table-column prop="1" label="模具2"></el-table-column>
      <el-table-column prop="2" label="模具3"></el-table-column>
      <el-table-column prop="3" label="模具4"></el-table-column>
    </el-table>
    CR:
    <el-input class="w-50 m-2" v-model="tableDataCR[0].data4" disabled />
    <h2>模具状态判断矩阵</h2>
    <el-table :data="tableDataahp3" style="width: 100%">
      <el-table-column prop="0" label="objectx"></el-table-column>
      <el-table-column prop="1" label="objecty"></el-table-column>
      <el-table-column prop="2" label="objectz"></el-table-column>
      <el-table-column prop="3" label="anglex"></el-table-column>
      <el-table-column prop="4" label="angley"></el-table-column>
      <el-table-column prop="5" label="anglez"></el-table-column>
      <el-table-column prop="6" label="speedx"></el-table-column>
      <el-table-column prop="7" label="speedy"></el-table-column>
      <el-table-column prop="8" label="speedz"></el-table-column>
    </el-table>
    CR:
    <el-input class="w-50 m-2" v-model="tableDataCR[0].data3" disabled />
    <template #footer>
      <div class="dialog-footer">
        <!-- <el-button @click="handleClose">取消</el-button> -->
        <!-- <el-button type="primary" @click="handleClose">确定</el-button> -->
        <el-button type="primary" @click="change1">修改AHP矩阵1</el-button>
        <el-button type="primary" @click="change2">修改AHP矩阵2</el-button>
        <el-button type="primary" @click="change3">修改AHP矩阵3</el-button>
        <el-button type="primary" @click="change4">修改AHP矩阵4</el-button>
        <el-button type="info" @click="back">恢复默认值</el-button>
      </div>
    </template>
  </el-dialog>
  <el-button type="success" round @click="weight">计算权重</el-button>
  <el-dialog title="Weight矩阵" v-model="dialogVisibleweight" @close="handleClose" width="1300px">
    <h2>步骤权重</h2>
    <el-table :data="tableDataweight1" style="width: 100%">
      <el-table-column prop="data1" label="步骤1"></el-table-column>
      <el-table-column prop="data2" label="步骤2"></el-table-column>
      <el-table-column prop="data3" label="步骤3"></el-table-column>
      <el-table-column prop="data4" label="步骤4"></el-table-column>
      <el-table-column prop="data5" label="步骤5"></el-table-column>
      <el-table-column prop="data6" label="步骤6"></el-table-column>
      <el-table-column prop="data7" label="步骤7"></el-table-column>
      <el-table-column prop="data8" label="步骤8"></el-table-column>
    </el-table>
    <h2>步骤操作层权重</h2>
    <el-table :data="tableDataweight2" style="width: 100%">
      <el-table-column prop="data1" label="操作时间"></el-table-column>
      <el-table-column prop="data2" label="操作精准度"></el-table-column>
      <el-table-column prop="data3" label="操作质量"></el-table-column>
    </el-table>
    <h2>模具权重</h2>
    <el-table :data="tableDataweight4" style="width: 100%">
      <el-table-column prop="data1" label="模具1"></el-table-column>
      <el-table-column prop="data2" label="模具2"></el-table-column>
      <el-table-column prop="data3" label="模具3"></el-table-column>
      <el-table-column prop="data4" label="模具4"></el-table-column>
    </el-table>
    <h2>模具状态权重</h2>
    <el-table :data="tableDataweight3" style="width: 100%">
      <el-table-column prop="data1" label="objectx"></el-table-column>
      <el-table-column prop="data2" label="objecty"></el-table-column>
      <el-table-column prop="data3" label="Objectz"></el-table-column>
      <el-table-column prop="data4" label="anglexx"></el-table-column>
      <el-table-column prop="data5" label="anglexy"></el-table-column>
      <el-table-column prop="data6" label="anglexz"></el-table-column>
      <el-table-column prop="data7" label="speedx"></el-table-column>
      <el-table-column prop="data8" label="speedy"></el-table-column>
      <el-table-column prop="data9" label="speedz"></el-table-column>
    </el-table>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" @click="handleClose">确定</el-button>
      </div>
    </template>
  </el-dialog>
  <div>
    <el-dialog title="请输入AHP矩阵" v-model="dialogVisible" @close="closeDialog">
      <el-table :data="tableData" border>
        <el-table-column v-for="(item, index) in columns" :key="index" :label="item.label">
          <template v-slot:default="{ row }">
            <el-input v-model="row[item.prop]" placeholder="请输入数据"></el-input>
          </template>
        </el-table-column>
      </el-table>
      <el-button @click="closeDialog">取消</el-button>
      <el-button type="primary" @click="handleConfirm">确认</el-button>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ElMessage } from "element-plus";
import { ref, reactive, toRaw, shallowReactive, toRefs, inject } from "vue";
import axios from "axios";

const dialogVisibleahp = ref(false);
const tableDataahp1 = reactive([]);
const tableDataahp2 = reactive([]);
const tableDataahp3 = reactive([]);
const tableDataahp4 = reactive([]);
const tableDataweight1 = reactive([
  { data1: "", data2: "", data3: "", data4: "", data5: "", data6: "", data7: "", data8: "" },
]);
const tableDataweight2 = reactive([{ data1: "", data2: "", data3: "" }]);
const tableDataweight3 = reactive([
  {
    data1: "",
    data2: "",
    data3: "",
    data4: "",
    data5: "",
    data6: "",
    data7: "",
    data8: "",
    data9: "",
  },
]);
const tableDataweight4 = reactive([{ data1: "", data2: "", data3: "", data4: "" }]);
const tableDataCR = reactive([{ data1: "", data2: "", data3: "", data4: "" }]);

const tableData = reactive([]);
const columns = reactive([]);
const columns1 = reactive([
  { label: "步骤1", prop: "col1" },
  { label: "步骤2", prop: "col2" },
  { label: "步骤3", prop: "col3" },
  { label: "步骤4", prop: "col4" },
  { label: "步骤5", prop: "col5" },
  { label: "步骤6", prop: "col6" },
  { label: "步骤7", prop: "col7" },
  { label: "步骤8", prop: "col8" },
]);
const columns2 = reactive([
  { label: "操作时间", prop: "col1" },
  { label: "操作精准度", prop: "col2" },
  { label: "操作质量", prop: "col3" },
]);
const columns3 = reactive([
  { label: "模具1", prop: "col1" },
  { label: "模具2", prop: "col2" },
  { label: "模具3", prop: "col3" },
  { label: "模具4", prop: "col4" },
  // { label: "列4", prop: "col4" },
]);
const columns4 = reactive([
  { label: "objectx", prop: "col1" },
  { label: "objecty", prop: "col2" },
  { label: "objectz", prop: "col3" },
  { label: "anglex", prop: "col4" },
  { label: "angley", prop: "col5" },
  { label: "anglez", prop: "col6" },
  { label: "speedx", prop: "col7" },
  { label: "speedy", prop: "col8" },
  { label: "speedz", prop: "col9" },
]);
const tableData1 = reactive([
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "" },
]);
const tableData2 = reactive([
  { col1: "", col2: "", col3: "" },
  { col1: "", col2: "", col3: "" },
  { col1: "", col2: "", col3: "" },
]);
const tableData3 = reactive([
  { col1: "", col2: "", col3: "", col4: "" },
  { col1: "", col2: "", col3: "", col4: "" },
  { col1: "", col2: "", col3: "", col4: "" },
  { col1: "", col2: "", col3: "", col4: "" },
]);
const tableData4 = reactive([
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "", col9: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "", col9: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "", col9: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "", col9: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "", col9: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "", col9: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "", col9: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "", col9: "" },
  { col1: "", col2: "", col3: "", col4: "", col5: "", col6: "", col7: "", col8: "", col9: "" },
]);
const dialogVisible = ref(false);
const data = ref();
const change1 = () => {
  dialogVisible.value = true;
  columns.splice(0, columns.length, ...columns1);
  tableData.splice(0, tableData.length, ...tableData1);
};
const change2 = () => {
  dialogVisible.value = true;
  columns.splice(0, columns.length, ...columns2);
  tableData.splice(0, tableData.length, ...tableData2);
};
const change3 = () => {
  dialogVisible.value = true;
  columns.splice(0, columns.length, ...columns3);
  tableData.splice(0, tableData.length, ...tableData3);
};
const change4 = () => {
  dialogVisible.value = true;
  columns.splice(0, columns.length, ...columns4);
  tableData.splice(0, tableData.length, ...tableData4);
};
const closeDialog = () => {
  dialogVisible.value = false;
};
const handleConfirm = () => {
  const arrayData = tableData.map((item) => Object.values(item));
  data.value = arrayData;
  dialogVisible.value = false;
  // alert(data.value);
  axios
    .get("/rule1/change/" + data.value)
    .then((response) => {
      // Object.assign(tableDataweight, response.data);
      // alert(response.data);
      if (response.data == -100) {
        ElMessage.error("请输入完整的矩阵！");
      } else if (response.data != 1) {
        ElMessage.info("修改不成功，CR 为: " + response.data);
      } else {
        ElMessage.success("修改成功，请刷新");
      }
    })
    .catch((error) => {
      ElMessage.error("出错了");
      console.log(error);
    });
};
const back = () => {
  axios
    .get("/rule1/back/")
    .then((response) => {
      ElMessage.success("恢复成功，请刷新");
    })
    .catch((error) => {
      ElMessage.error("出错了");
      console.log(error);
    });
};

const user = localStorage.getItem("user");
const handleClose = () => {
  dialogVisibleahp.value = false;
  dialogVisibleweight.value = false;
  // dialogVisiblefuzzy.value = false;
};
//const handleCellClick = (row, column, cell, event) => {
// if (column.property) {
//   const oldValue = row[column.property];
//   const newValue = prompt("请输入新值", oldValue);
//   if (newValue !== null && newValue !== oldValue) {
//     row[column.property] = newValue;
//   }
// }
//};

const ahp = () => {
  axios
    .get("/rule1/ahp1")
    .then((response) => {
      Object.assign(tableDataahp1, response.data);
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  axios
    .get("/rule1/ahp2")
    .then((response) => {
      Object.assign(tableDataahp2, response.data);
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  axios
    .get("/rule1/ahp3")
    .then((response) => {
      Object.assign(tableDataahp3, response.data);
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  axios
    .get("/rule1/ahp4")
    .then((response) => {
      Object.assign(tableDataahp4, response.data);
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  axios
    .get("/rule1/cr")
    .then((response) => {
      // Object.assign(tableDataweight, response.data);
      const data = response.data;
      data.forEach((item, index) => {
        tableDataCR[0]["data" + (index + 1)] = item;
      });
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  dialogVisibleahp.value = true;
};
// 多个权重值。
const dialogVisibleweight = ref(false);
const weight = () => {
  axios
    .get("/rule1/weight1")
    .then((response) => {
      // Object.assign(tableDataweight, response.data);
      const data = response.data;
      data.forEach((item, index) => {
        tableDataweight1[0]["data" + (index + 1)] = item;
      });
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  axios
    .get("/rule1/weight2")
    .then((response) => {
      // Object.assign(tableDataweight, response.data);
      const data = response.data;
      data.forEach((item, index) => {
        tableDataweight2[0]["data" + (index + 1)] = item;
      });
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  axios
    .get("/rule1/weight3")
    .then((response) => {
      // Object.assign(tableDataweight, response.data);
      const data = response.data;
      data.forEach((item, index) => {
        tableDataweight3[0]["data" + (index + 1)] = item;
      });
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  axios
    .get("/rule1/weight4")
    .then((response) => {
      // Object.assign(tableDataweight, response.data);
      const data = response.data;
      data.forEach((item, index) => {
        tableDataweight4[0]["data" + (index + 1)] = item;
      });
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  axios
    .get("/rule1/cr")
    .then((response) => {
      // Object.assign(tableDataweight, response.data);
      const data = response.data;
      data.forEach((item, index) => {
        tableDataweight4[0]["data" + (index + 1)] = item;
      });
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  dialogVisibleweight.value = true;
};
</script>
