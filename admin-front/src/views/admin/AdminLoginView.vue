<template>
  <div class="common-layout-login">
    <el-container>
      <!-- <el-header>Header</el-header> -->
      <el-main class="centered-login">
        <div>
          <el-row style="margin-bottom: 20px">
            <h1>后台管理系统</h1>
          </el-row>
          <el-row style="margin-bottom: 20px">
            <span>欢迎回来，请登录您的账户。</span>
          </el-row>
          <el-row style="margin-bottom: 20px">
            <el-input
              v-model="username"
              style="height: 35px; width: 240px"
              placeholder="请输入您的账号"
            />
          </el-row>
          <el-row style="margin-bottom: 20px">
            <el-input
              v-model="password"
              type="password"
              style="height: 35px; width: 240px"
              placeholder="请输入您的密码"
            />
          </el-row>
          <el-row>
            <el-col :span="7">
              <el-button style="width: 240px" type="" @click="login">登录</el-button>
            </el-col>
          </el-row>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import router from "@/router";
import adminApi from "@/api/adminApi";
import { token } from "@/stores/token";

const username = ref("");
const password = ref("");

// 参数校验函数
function validateInputs() {
  if (!username.value ) {
    ElMessage.error("账号不能为空");
    return false;
  }
  if (!password.value ) {
    ElMessage.error("密码不能为空");
    return false;
  }
  return true;
}

// 登录函数
function login() {
  if (!validateInputs()) {
    return; // 校验失败，终止登录操作
  }
  adminApi.login(username.value, password.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
        onClose: function () {
          // sessionStorage.setItem("token", resp.data);
          token().token = resp.data
          router.push("/admin/index");
        },
      });
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
</script>

<style scoped>
.common-layout-login {
  height: 100vh; /* 全屏高度 */
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  transform: translateY(-50px); /* 控制偏移量，向上偏移50px */
}

.centered-login {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
