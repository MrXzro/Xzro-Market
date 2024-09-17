<template>
  <div class="common-layout-login">
    <el-container>
      <el-main class="centered-login">
        <div>
          <el-row style="margin-bottom: 20px">
            <h1>登录您的帐户</h1>
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
          <el-row style="margin-bottom: 20px" justify="space-between">
            <el-image
              style="width: 80px; height: 33px"
              :src="imageBase64Data"
              :fit="fit"
              @click="getCaptcha"
            />
            <el-input
              v-model="captcha"
              style="height: 35px; width: 150px"
              placeholder="请输入验证码"
            />
          </el-row>
          <el-row>
            <el-col :span="7">
              <el-button style="width: 240px" type="" @click="login"
                >登录</el-button
              >
            </el-col>
          </el-row>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import userApi from "@/api/userApi";
import { ref } from "vue";
import { ElMessage } from "element-plus";
import router from "@/router";
import { token } from "@/stores/token";

const username = ref("");
const password = ref("");
const loginToken = token();
const imageBase64Data = ref("");
const captchaId = ref("");
const captcha = ref("");

// 获取验证码
function getCaptcha() {
  userApi.getCaptcha().then((resp) => {
    if (resp.code == 10000) {
      imageBase64Data.value = resp.data.imageBase64Data;
      captchaId.value = resp.data.captchaId;
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
// 校验函数
function validateInputs() {
  if (!username.value) {
    ElMessage.error("账号不能为空");
    return false;
  }
  if (!password.value) {
    ElMessage.error("密码不能为空且长度不能小于6个字符");
    return false;
  }
  return true;
}

// 登录函数
function login() {
  if (!validateInputs()) {
    return; // 校验失败，终止登录操作
  }
  userApi
    .login(username.value, password.value, captcha.value, captchaId.value)
    .then((resp) => {
      if (resp.code == 10000) {
        ElMessage({
          message: resp.msg,
          type: "success",
          duration: 1200,
          onClose: function () {
            loginToken.token = resp.data;
            // sessionStorage.setItem("token", resp.data);
            router.push("/shop");
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
getCaptcha();
</script>

<style scoped>
.common-layout-login {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  transform: translateY(-50px);
}

.centered-login {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
