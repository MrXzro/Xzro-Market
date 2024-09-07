<template>
  <div class="common-layout">
    <el-container>
      <el-header
        style="border-bottom: 1px #2a2e3a solid;padding: 0px;"
      >
      <el-header style="padding: 0px">
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          :ellipsis="false"
          @select="toPage"
        >
          <el-menu-item index="0">
            <img
              style="width: 100px"
              src="/src/assets/logo.png"
              alt="Element logo"
            />
          </el-menu-item>
          <el-menu-item @click="logout">退出登录</el-menu-item>
        </el-menu>
      </el-header>
      </el-header>
      <el-container>
        <!-- 菜单开始 -->
        <el-aside width="190px">
          <el-menu
            default-active="2"
            class="aside-menu"
            @select="toPage"
          >
          <el-menu-item index="/admin/index"><el-icon><House /></el-icon>首页</el-menu-item>
            <el-sub-menu index="1">
              <template #title>
                <el-icon><User /></el-icon>
                <span>客户管理</span>
              </template>
                <el-menu-item index="/admin/customer">客户信息</el-menu-item>
                <el-menu-item index="/admin/cgroups">客户分组</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="2">
              <template #title>
                <el-icon><Goods /></el-icon>
                <span>商品管理</span>
              </template>
                <el-menu-item index="/admin/goods">商品信息</el-menu-item>
                <el-menu-item index="/admin/ggroups">商品分组</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="3">
              <template #title>
                <el-icon><Tickets /></el-icon>
                <span>订单管理</span>
              </template>
                <el-menu-item index="/admin/orders">订单信息</el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-aside>
          <!-- 菜单结束 -->
        <el-container >
          <el-main style="height: 80vh;">
            <RouterView />
          </el-main>
          <el-footer
            style=" border-top: 1px #2a2e3a solid;"
            >Footer</el-footer
          >
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import { RouterView } from 'vue-router';
import router from '@/router'
import adminApi from '@/api/adminApi';
import { ElMessage } from 'element-plus';
function logout(){
  adminApi.logout().then(resp=>{
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
function toPage(indexPath) {
  //编程式导航
  router.push(indexPath)
}
</script>
<style scope>
.aside-menu{
  border-right:  1px #2a2e3a solid;
}
</style>

