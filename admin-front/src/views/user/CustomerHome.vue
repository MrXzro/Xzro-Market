<template>
  <div class="common-layout">
    <el-container>
      <el-header style="padding: 0px">
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          :ellipsis="false"
          @select="toPage"
        >
          <el-menu-item index="/shop">
            <img
              style="width: 100px"
              src="/src/assets/logo.png"
              alt="Element logo"
            />
          </el-menu-item>
          <el-menu-item disabled style="color: white;">你好，{{ username }}</el-menu-item>
          <el-menu-item index="/shop">下单</el-menu-item>
          <el-sub-menu index="2">
            <template #title>我的</template>
            <el-menu-item index="/order">订单</el-menu-item>
          </el-sub-menu>
          <el-menu-item @click="logout">退出登录</el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <RouterView />
      </el-main>
      <transition
        @before-enter="handleBeforeEnter"
        @after-enter="handleAfterEnter"
        @before-leave="handleBeforeLeave"
        @after-leave="handleAfterLeave"
      >
        <el-footer
          v-if="cart.ifShowCheckButton"
          height="80px"
          style="border-top: 1.5px solid rgb(65, 66, 67); width: 20"
        >
        <el-row justify="end" style="padding-top: 20px;padding-right: 50px;">

          <el-col :span="2">
            <div style="margin-top: 5px;">
              <h3>总计:{{ cart.totalPrice }}</h3>
            </div>
          </el-col>
          <el-col :span="1">
            <el-button type="primary" plain size="large" @click="showAddOrderDialog">提交订单</el-button>
          </el-col>
        </el-row>
        </el-footer>
      </transition>
    </el-container>
  </div>
</template>

<script setup>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from "@element-plus/icons-vue";
import { RouterView } from "vue-router";
import router from "@/router";
import { ref } from "vue";
import { useCartStore } from "@/stores/cart";
import userApi from "@/api/userApi";
import { ElMessage } from "element-plus";
import { token } from "@/stores/token";

const activeIndex = ref("/shop");
const cart = useCartStore();
const username = ref("")
function getUsername(){
  // let token = sessionStorage.getItem("token")
  // 拆分JWT，取出第二部分（Payload）
  const base64Payload = token().token.split('.')[1];
  // Base64 解码（处理 Base64 URL 编码）
  const payload = JSON.parse(atob(base64Payload.replace(/-/g, '+').replace(/_/g, '/')));
  // 输出解码后的结果
  console.log(payload.payload);
  username.value = payload.payload.username
  console.log(payload.payload.username)
}

//显示提交订单对话框
function showAddOrderDialog(){
  cart.ifShowAddOrderDialog = true
}
function toPage(indexPath) {
  //编程式导航
  router.push(indexPath);
}
function logout(){
  userApi.logout().then(resp=>{
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
        onClose: function () {
          sessionStorage.removeItem("token", resp.data);
          router.push("/login");
        },
      });
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }

  })
}
function handleBeforeEnter() {
  document.body.style.overflow = "hidden"; // 进入动画前隐藏滚动条
}
function handleAfterEnter() {
  document.body.style.overflow = ""; // 动画结束后恢复正常
}
function handleBeforeLeave() {
  document.body.style.overflow = "hidden"; // 离开动画前隐藏滚动条
}
function handleAfterLeave() {
  document.body.style.overflow = ""; // 动画结束后恢复正常
}
getUsername()
</script>
<style scope>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.v-enter-active {
  animation: slideup 0.3s linear;
}
.v-leave-active {
  animation: slideup 0.3s linear reverse;
}
@keyframes slideup {
  from {
    transform: translateY(100%); /* 从下方滑入 */
  }
  to {
    transform: translateY(0px); /* 到正常位置 */
  }
}
</style>

