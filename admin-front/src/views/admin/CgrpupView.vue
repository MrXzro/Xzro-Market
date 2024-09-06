<template>
  <el-row justify="space-evenly">
    <el-col :span="8">
      <el-card style="width: 100%">
        <template #header>
          <div class="card-header">
            <el-button @click="showAddDialog">新增</el-button>
          </div>
        </template>
        <el-table :data="cgroupsList" style="width: 100%">
          <el-table-column fixed prop="id" label="ID" width="70" />
          <el-table-column
            prop="name"
            label="商品组"
            width=""
            show-overflow-tooltip
          />

          <el-table-column fixed="right" label="操作" min-width="">
            <template #default="scope">
              <el-button
                link
                type="primary"
                size="small"
                @click="showUpdateDialog(scope.row.id)"
              >
                修改
              </el-button>
              <el-popconfirm
                title="你确定要删除该分组吗？"
                confirm-button-text="确认"
                cancel-button-text="取消"
                width="200px"
                @confirm="deleteGroup(scope.row.id)"
              >
                <template #reference>
                  <el-button link type="primary" size="small">删除</el-button>
                </template>
              </el-popconfirm>
              <el-button
                link
                type="primary"
                size="small"
                @click="showCustomers(scope.row.id)"
              >
                预览
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-col>
    <!--客户预览开始-->
    <el-col :span="15">
      <el-card style="max-width: 100%">
        <template #header>
          <div class="card-header">客户预览</div>
        </template>
        <el-table :data="customersList" style="width: 100%">
          <el-table-column fixed prop="id" label="ID" width="70" />
          <el-table-column
            prop="username"
            label="用户名"
            width=""
            show-overflow-tooltip
          />
          <el-table-column
            prop="name"
            label="姓名"
            width=""
            show-overflow-tooltip
          />
          <el-table-column
            prop="age"
            label="年龄"
            width=""
            show-overflow-tooltip
          />
          <el-table-column
            prop="gender"
            label="性别"
            width=""
            show-overflow-tooltip
          /><el-table-column
            prop="phone"
            label="电话"
            width=""
            show-overflow-tooltip
          />
          <el-table-column label="状态" width="">
            <template #default="scope">
              <el-tag v-if="scope.row.status == 0" type="primary">正常</el-tag>
              <el-tag v-if="!scope.row.status == 0" type="info">停用</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="用户组">
            <template #default="scope">
              <el-tag type="info" effect="plain">
                {{ getUserGroupName(scope.row.groupId) }}
              </el-tag>
            </template>
          </el-table-column>
        </el-table>
        <div style="display: flex; justify-content: center; margin-top: 15px">
          <el-pagination
            layout="prev, pager, next"
            :page-count="allPage"
            :current-page="currentPage"
            @update:current-page="selectByGroup"
            style="margin-top: 15px"
          />
        </div>
        <!-- <template #footer>Footer content</template> -->
      </el-card>
    </el-col>
    <!--客户预览结束-->
  </el-row>
  <!-- 添加分组的对话框开始 -->
  <el-dialog v-model="addDialogShow" title="添加分组" width="500">
    <el-form :model="groupInfo">
      <el-form-item label="名称" label-width="10%">
        <el-input v-model="groupInfo.name" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="addDialogShow = false">取消</el-button>
        <el-button type="primary" @click="insert()"> 确认 </el-button>
      </div>
    </template>
  </el-dialog>
  <!-- 添加分组的对话框结束 -->
  <!-- 修改分组的对话框开始 -->
  <el-dialog v-model="updateDialogShow" title="修改分组" width="500">
    <el-form :model="groupInfo">
      <el-form-item label="名称" label-width="10%">
        <el-input v-model="groupInfo.name" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="updateDialogShow = false">取消</el-button>
        <el-button type="primary" @click="update()"> 确认 </el-button>
      </div>
    </template>
  </el-dialog>
  <!-- 修改分组的对话框结束 -->
</template>

<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import cgroupApi from "@/api/cgroupApi";
import customerApi from "@/api/customerApi";
//客户分组信息
const cgroupsList = ref([]);
//当前分页
const currentPage = ref(1);
//获取的总页数
const allPage = ref(0);
//客户列表
const customersList = ref([]);
//当前预览分组
const groupId = ref(1);
//当前分组信息
const groupInfo = ref({
  id: null,
  name: null,
});
//是否显示添加对话框
const addDialogShow = ref(false);
//是否显示修改对话框
const updateDialogShow = ref(false);



//匹配用户组显示
function getUserGroupName(id) {
  const group = cgroupsList.value.find((group) => group.id === id);
  return group ? group.name : "未分组";
}
//预览分组按钮
function showCustomers(id) {
  currentPage.value = 1;
  groupId.value = id;
  selectByGroup(currentPage.value);
}

//分组查询
function selectByGroup(current) {
  currentPage.value = current;
  customerApi.selectByGroup(groupId.value, currentPage.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      allPage.value = resp.data.pages;
      customersList.value = resp.data.list;
      currentPage.value = current;
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}

//修改分组方法
function update() {
  cgroupApi.update(groupInfo.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      updateDialogShow.value = false;
      selectAll();
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//显示修改分组对话框
function showUpdateDialog(id) {
  cgroupApi.selectById(id).then((resp) => {
    if (resp.code == 10000) {
      groupInfo.value = resp.data;
      updateDialogShow.value = true;
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//添加分组方法
function insert() {
  cgroupApi.insert(groupInfo.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      addDialogShow.value = false;
      selectAll();
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//显示添加对话框方法
function showAddDialog() {
  groupInfo.value = {
    id: null,
    name: null,
  };
  addDialogShow.value = true;
}
//删除客户分组方法
function deleteGroup(id) {
  cgroupApi.delete(id).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      selectAll();
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}

//查询所有客户分组
function selectAll() {
  cgroupApi.selectAll().then((resp) => {
    if (resp.code == 10000) {
      console.log(resp.data);
      cgroupsList.value = resp.data;
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
selectAll();
</script>