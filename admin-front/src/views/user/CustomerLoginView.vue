<template>
    <div class="common-layout-login">
      <el-container>
        <!-- <el-header>Header</el-header> -->
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
            <el-row>
              <el-col :span="7">
                <el-button style="width: 240px" type="">登录</el-button>
              </el-col>
            </el-row>
          </div>
        </el-main>
        <!-- <el-footer>Footer</el-footer> -->
      </el-container>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  const username = ref("");
  const password = ref("");
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
  