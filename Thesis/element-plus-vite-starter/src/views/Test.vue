<template>
  <div>
    <el-button type="primary" @click="showDialog">弹出表格</el-button>
    <el-dialog title="AHP矩阵" v-model="dialogVisible" @close="closeDialog">
      <el-table :data="tableData" border>
        <el-table-column v-for="(item, index) in columns" :key="index" :label="item.label">
          <template v-slot:default="{ row }">
            <el-input v-model="row[item.prop]" placeholder="请输入数据"></el-input>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="primary" @click="handleConfirm">确认</el-button>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue";
const columns = [
  { label: "列1", prop: "col1" },
  { label: "列2", prop: "col2" },
  { label: "列3", prop: "col3" },
  { label: "列4", prop: "col4" },
];
const tableData = reactive([
  { col1: "", col2: "", col3: "", col4: "" },
  { col1: "", col2: "", col3: "", col4: "" },
  { col1: "", col2: "", col3: "", col4: "" },
  { col1: "", col2: "", col3: "", col4: "" },
]);
const dialogVisible = ref(false);
const data = ref();
const showDialog = () => {
  dialogVisible.value = true;
};
const closeDialog = () => {
  dialogVisible.value = false;
};
const handleConfirm = () => {
  const arrayData = tableData.map((item) => Object.values(item));
  data.value = arrayData;
  dialogVisible.value = false;
  alert(data.value);
};
</script>
