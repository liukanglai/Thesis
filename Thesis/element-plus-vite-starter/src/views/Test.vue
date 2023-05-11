<template>
  <el-button type="danger" round @click="ahp">修改AHP判断矩阵</el-button>
  <el-dialog title="AHP矩阵" v-model="dialogVisible" @close="handleClose">
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="data1" label="" width="180"></el-table-column>
      <el-table-column prop="data2" label="" width="180"></el-table-column>
      <el-table-column prop="data3" label="" width="180"></el-table-column>
      <el-table-column prop="data4" label="" width="180"></el-table-column>
      <el-table-column prop="data5" label="" width="180"></el-table-column>
      <el-table-column prop="data6" label="" width="180"></el-table-column>
      <el-table-column prop="data7" label="" width="180"></el-table-column>
      <el-table-column prop="data8" label="" width="180"></el-table-column>
    </el-table>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" @click="handleClose">确定</el-button>
      </div>
    </template>
  </el-dialog>
  <el-button type="success" round @click="weight">计算权重</el-button>
  <el-dialog title="Weight矩阵" v-model="dialogVisible1" @close="handleClose">
    <!-- <el-table :data="tableData1" style="width: 100%" @cell-click="handleCellClick"> -->
    <el-table :data="tableData1" style="width: 100%">
      <el-table-column prop="data1" label="步骤1" width="180"></el-table-column>
      <el-table-column prop="data2" label="步骤2" width="180"></el-table-column>
      <el-table-column prop="data3" label="步骤3" width="180"></el-table-column>
      <el-table-column prop="data4" label="步骤4" width="180"></el-table-column>
      <el-table-column prop="data5" label="步骤5" width="180"></el-table-column>
      <el-table-column prop="data6" label="步骤6" width="180"></el-table-column>
      <el-table-column prop="data7" label="步骤7" width="180"></el-table-column>
      <el-table-column prop="data8" label="步骤8" width="180"></el-table-column>
    </el-table>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" @click="handleClose">确定</el-button>
      </div>
    </template>
  </el-dialog>
  <el-button type="default" round @click="fuzzy">查看模糊矩阵</el-button>
  <el-dialog title="模糊矩阵" v-model="dialogVisible2" @close="handleClose">
    <el-table :data="tableData2" style="width: 100%">
      <el-table-column prop="data1" label="好"></el-table-column>
      <el-table-column prop="data2" label="中"></el-table-column>
      <el-table-column prop="data3" label="差"></el-table-column>
    </el-table>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" @click="handleClose">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ElMessage } from "element-plus";
import { ref, reactive, toRaw, shallowReactive, toRefs, inject } from "vue";
import axios from "axios";

const dialogVisible = ref(false);
const tableData = reactive([
  { data1: 1, data2: 2, data3: 5, data4: 7, data5: 1, data6: 2, data7: 6, data8: 4 },
  { data1: 1 / 2.0, data2: 1, data3: 3, data4: 5, data5: 1 / 3.0, data6: 1, data7: 4, data8: 2 },
  {
    data1: 1 / 5.0,
    data2: 1 / 3.0,
    data3: 1,
    data4: 3,
    data5: 1 / 5.0,
    data6: 1 / 3.0,
    data7: 2,
    data8: 1,
  },
  {
    data1: 1 / 7.0,
    data2: 1 / 5.0,
    data3: 1 / 3.0,
    data4: 1,
    data5: 1 / 7.0,
    data6: 1 / 5.0,
    data7: 1 / 3.0,
    data8: 1 / 2.0,
  },
  { data1: 1, data2: 3, data3: 5, data4: 7, data5: 1, data6: 3, data7: 6, data8: 4 },
  { data1: 1 / 2.0, data2: 1, data3: 3, data4: 5, data5: 1 / 3.0, data6: 1, data7: 4, data8: 2 },
  {
    data1: 1 / 6.0,
    data2: 1 / 4.0,
    data3: 1 / 2.0,
    data4: 3,
    data5: 1 / 6.0,
    data6: 1 / 4.0,
    data7: 1,
    data8: 1 / 2.0,
  },
  {
    data1: 1 / 4.0,
    data2: 1 / 2.0,
    data3: 1,
    data4: 2,
    data5: 1 / 4.0,
    data6: 1 / 2.0,
    data7: 2,
    data8: 1,
  },
]);
const tableData1 = reactive([
  { data1: 1, data2: 2, data3: 5, data4: 7, data5: 1, data6: 2, data7: 6, data8: 4 },
]);
const tableData2 = reactive([]);
const user = localStorage.getItem("user");
const ahp = () => {
  dialogVisible.value = true;
};

const handleClose = () => {
  dialogVisible.value = false;
  dialogVisible1.value = false;
  dialogVisible2.value = false;
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

const dialogVisible1 = ref(false);
const weight = () => {
  axios
    .get("/rule1/weight")
    .then((response) => {
      // alert(response.data[1]);
      const data = response.data;
      data.forEach((item, index) => {
        tableData1[0]["data" + (index + 1)] = item;
      });
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  dialogVisible1.value = true;
};
const dialogVisible2 = ref(false);
let isFirstPush = true;
const fuzzy = () => {
  axios
    .get("/rule1/fuzzy/" + user)
    .then((response) => {
      // alert(response.data);
      const data = response.data;

      if (isFirstPush) {
        for (let i = 0; i < data.length; i++) {
          const dataObj = {};
          for (let j = 0; j < data[i].length; j++) {
            dataObj[`data${j + 1}`] = data[i][j];
          }
          tableData2.push(dataObj);
        }
        isFirstPush = false;
      } else {
        for (let i = 0; i < data.length; i++) {
          for (let j = 0; j < data[i].length; j++) {
            tableData2[i][`data${j + 1}`] = data[i][j];
          }
        }
      }
    })
    .catch((error) => {
      ElMessage.error("后台未连接！");
      console.log(error);
    });
  dialogVisible2.value = true;
};
</script>
