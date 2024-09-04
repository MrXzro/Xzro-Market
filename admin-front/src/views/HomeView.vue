<template>
  <div class="common-layout">
    <el-container>
      <el-header
        style="border-bottom: 1px #2a2e3a solid;padding: 0px;"
      >

      </el-header>
      <el-container>
        <!-- 菜单开始 -->
        <el-aside width="210px">
          <el-menu
            default-active="2"
            class="aside-menu"
          >
            <el-sub-menu index="1">
              <template #title>
                <el-icon><location /></el-icon>
                <span>用户管理</span>
              </template>
                <el-menu-item index="1-1">用户管理</el-menu-item>
                <el-menu-item index="1-2">用户组管理</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="2">
              <template #title>
                <el-icon><location /></el-icon>
                <span>商品管理</span>
              </template>
                <el-menu-item index="1-1">商品信息</el-menu-item>
                <el-menu-item index="1-2">商品分组</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="3">
              <template #title>
                <el-icon><location /></el-icon>
                <span>订单管理</span>
              </template>
                <el-menu-item index="1-1">订单信息</el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-aside>
          <!-- 菜单结束 -->
        <el-container >
          <el-main>
            <RouterView />
          </el-main>
          <el-footer
            style=" border-top: 1px #2a2e3a solid"
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

</script>
<style scope>
.aside-menu{

  border-right:  1px #2a2e3a solid;
}
</style>

