<template>
  <el-button type="primary" @click="stepFuzzy">查看步骤操作详情模糊矩阵</el-button>
  <el-button type="primary" @click="qualityFuzzy">查看步骤模具模糊矩阵</el-button>
  <el-button type="primary" @click="objectFuzzy">查看具体模具模糊矩阵</el-button>
  <el-dialog title="模糊矩阵" v-model="dialogVisiblefuzzy" @close="handleClose">
    <el-table :data="tableDatafuzzy">
      <el-table-column label="Name">
        <template #default="scope">
          {{ name[scope.$index].name }}
        </template>
      </el-table-column>
      <el-table-column prop="0" label="优秀"></el-table-column>
      <el-table-column prop="1" label="良好"></el-table-column>
      <el-table-column prop="2" label="一般"></el-table-column>
      <el-table-column prop="3" label="较差"></el-table-column>
    </el-table>
    <!-- <el-table :data="tableDatafuzzy" style="width: 100%"> -->
    <!--   <el-table-column prop="0" label="优秀"></el-table-column> -->
    <!--   <el-table-column prop="1" label="良好"></el-table-column> -->
    <!--   <el-table-column prop="2" label="一般"></el-table-column> -->
    <!--   <el-table-column prop="3" label="较差"></el-table-column> -->
    <!-- </el-table> -->
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" @click="handleClose">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ElMessage, ElMessageBox } from "element-plus";
import { ref, reactive, toRaw, shallowReactive, toRefs, inject } from "vue";
import axios from "axios";

// const tableDatafuzzy = reactive([
//   [1, 2, 3],
//   [4, 5, 6],
//   [7, 8, 9],
// ]);
const name = reactive([]);
const name1 = reactive([{ name: "操作时间" }, { name: "操作精准度" }, { name: "操作质量" }]);
const name2 = reactive([{ name: "模具1" }, { name: "模具2" }, { name: "模具3" },{name: "模具4"}]);
const name3 = reactive([{ name: "objectx" }, { name: "objecty" }, { name: "objectz" }, { name: "anglex" }, { name: "angley" }, { name: "anglez" },{ name: "speedx" }, { name: "speedy" }, { name: "speedz" }]);
const dialogVisiblefuzzy = ref(false);
const tableDatafuzzy = reactive([]);
const user = localStorage.getItem("user");
const handleClose = () => {
  dialogVisiblefuzzy.value = false;
};

const stepFuzzy = () => {
  input()
    .then((value: string) => {
      console.log("输入的值为：" + value);
      axios
        .get("/score/stepFuzzy/" + user + "/" + value)
        .then((response) => {
          tableDatafuzzy.splice(0, tableDatafuzzy.length);
          Object.assign(tableDatafuzzy, response.data);
          name.splice(0, name.length, ...name1);
          dialogVisiblefuzzy.value = true;
        })
        .catch((error) => {
          ElMessage.error("后台未连接！");
          console.log(error);
        });
    })
    .catch((error: Error) => {
      console.log(error.message);
    });
};
const qualityFuzzy = () => {
  input()
    .then((value: string) => {
      console.log("输入的值为：" + value);
      axios
        .get("/score/qualityFuzzy/" + user + "/" + value)
        .then((response) => {
          tableDatafuzzy.splice(0, tableDatafuzzy.length);
          Object.assign(tableDatafuzzy, response.data);
          name.splice(0, name.length, ...name2);
          dialogVisiblefuzzy.value = true;
        })
        .catch((error) => {
          ElMessage.error("后台未连接！");
          console.log(error);
        });
    })
    .catch((error: Error) => {
      console.log(error.message);
    });
};
const objectFuzzy = () => {
  input32()
    .then((value: string) => {
      console.log("输入的值为：" + value);
      axios
        .get("/score/objectFuzzy/" + user + "/" + value)
        .then((response) => {
          tableDatafuzzy.splice(0, tableDatafuzzy.length);
          Object.assign(tableDatafuzzy, response.data);
          name.splice(0, name.length, ...name3);
          dialogVisiblefuzzy.value = true;
        })
        .catch((error) => {
          ElMessage.error("后台未连接！");
          console.log(error);
        });
    })
    .catch((error: Error) => {
      console.log(error.message);
    });
};

const input = (): Promise<string> => {
  return ElMessageBox.prompt("请输入0到7的数，表示各步骤", "提示", {
    confirmButtonText: "OK",
    cancelButtonText: "Cancel",
    inputPattern: /^[0-7]$/,
    inputErrorMessage: "请输入0到7的整数",
    customClass: "custom-message-box",
  })
    .then(({ value }) => {
      return value;
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "Input canceled",
      });
      throw new Error("Input canceled");
    });
};
const input32 = (): Promise<string> => {
  return ElMessageBox.prompt("请输入0到31的数，表示各步骤", "提示", {
    confirmButtonText: "OK",
    cancelButtonText: "Cancel",
    // inputPattern: /^[0-31]$/,
    inputPattern: /^[0-9]$|^1[0-9]$|^2[0-9]$|^3[0-1]$/,
    inputErrorMessage: "请输入0到31的整数",
    customClass: "custom-message-box",
  })
    .then(({ value }) => {
      return value;
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "Input canceled",
      });
      throw new Error("Input canceled");
    });
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
