<template>
  <el-row justify="space-evenly">
    <el-col :span="24">
      <el-card style="width: 100%">
        <template #header>
          <el-row justify="space-between">
            <el-col :span="6">
              <el-button @click="showAddDialog">新增</el-button>
            </el-col>
            <el-col :span="6">
              <el-input
                v-model="name"
                style="max-width: 300px"
                placeholder="请输入姓名"
                @input="selectByPage(1)"
              >
                <template #prepend>姓名</template>
              </el-input>
            </el-col>
          </el-row>
        </template>
        <el-table :data="CustomerList" style="width: 100%">
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
                title="你确定要删除该客户吗？"
                confirm-button-text="确认"
                cancel-button-text="取消"
                width="200px"
                @confirm="deleteCustomer(scope.row.id)"
              >
                <template #reference>
                  <el-button link type="primary" size="small">删除</el-button>
                </template>
              </el-popconfirm>
              <el-button
                link
                type="primary"
                size="small"
                @click="showGoods(scope.row.id)"
              >
                订单
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <div style="display: flex; justify-content: center; margin-top: 15px">
          <el-pagination
            layout="prev, pager, next"
            :page-count="allPage"
            :current-page="currentPage"
            @update:current-page="selectByPage"
            style="margin-top: 15px"
          />
        </div>
      </el-card>
    </el-col>
  </el-row>
  <!-- 添加客户的对话框开始 -->
  <el-dialog v-model="addDialogShow" title="添加客户" width="500">
    <el-form :model="customerInfo" label-width="15%">
      <el-form-item label="用户名" label-position="right">
        <el-input v-model="customerInfo.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="姓名" label-position="right">
        <el-input v-model="customerInfo.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="年龄" label-position="right">
        <el-input v-model="customerInfo.age" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" label-position="right">
        <el-input v-model="customerInfo.gender" autocomplete="off" />
      </el-form-item>
      <el-form-item label="电话" label-position="right">
        <el-input v-model="customerInfo.phone" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" label-position="right">
        <el-input
          v-model="customerInfo.password"
          type="password"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item label="状态" label-position="right">
        <el-select v-model="customerInfo.status" placeholder="请设置商品状态">
          <el-option label="正常" :value="0" />
          <el-option label="停用" :value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="用户组" label-position="right">
        <el-select v-model="groupList.id" placeholder="请选择用户组">
          <el-option
            v-for="(item, index) in groupList"
            :label="item.name"
            :key="index"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="头像" label-position="right">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/api/upload"
          name="pic"
          :value="customerInfo.img"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img width="100px" v-if="imageUrl" :src="imageUrl" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="addDialogShow = false">取消</el-button>
        <el-button type="primary" @click="insert()"> 确认 </el-button>
      </div>
    </template>
  </el-dialog>
  <!-- 添加客户的对话框结束 -->
  <!-- 修改客户的对话框开始 -->
  <el-dialog v-model="updateDialogShow" title="修改客户" width="500">
    <el-form :model="customerInfo" label-width="15%">
      <el-form-item label="用户名" label-position="right">
        <el-input v-model="customerInfo.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="姓名" label-position="right">
        <el-input v-model="customerInfo.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="年龄" label-position="right">
        <el-input v-model="customerInfo.age" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" label-position="right">
        <el-input v-model="customerInfo.gender" autocomplete="off" />
      </el-form-item>
      <el-form-item label="电话" label-position="right">
        <el-input v-model="customerInfo.phone" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" label-position="right">
        <el-input
          v-model="customerInfo.password"
          type="password"
          autocomplete="off"
          placeholder="不填写则不修改"
        />
      </el-form-item>
      <el-form-item label="状态" label-position="right">
        <el-select v-model="customerInfo.status" placeholder="请设置商品状态">
          <el-option label="正常" :value="0" />
          <el-option label="停用" :value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="用户组" label-position="right">
        <el-select v-model="customerInfo.groupId" placeholder="请选择用户组">
          <el-option
            v-for="(item, index) in groupList"
            :label="item.name"
            :key="index"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="头像" label-position="right">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/api/upload"
          name="pic"
          :value="customerInfo.img"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img width="100px" v-if="imageUrl" :src="imageUrl" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="updateDialogShow = false">取消</el-button>
        <el-button type="primary" @click="update()"> 确认 </el-button>
      </div>
    </template>
  </el-dialog>
  <!-- 修改客户的对话框结束 -->
</template>

<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import customerApi from "@/api/customerApi";
import cgroupApi from "@/api/cgroupApi";
//客户信息表
const CustomerList = ref([]);
//当前分页
const currentPage = ref(1);
//获取的总页数
const allPage = ref(0);
//当前分组信息
const customerInfo = ref({
  id: null,
  username: null,
  password: "",
  name: "",
  gender: "",
  age: null,
  phone: "",
  status: null,
  groupId: null,
  customerGroup: null,
  orders: null,
});
//是否显示添加对话框
const addDialogShow = ref(false);
//是否显示修改对话框
const updateDialogShow = ref(false);
//商品分组信息
const groupList = ref([]);
//头像
const imageUrl = ref("");
const name = ref("");

//删除客户
function deleteCustomer(id) {
  customerApi.delete(id).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      selectByPage(currentPage.value);
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//修改客户信息
function update() {
  customerApi.update(customerInfo.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      updateDialogShow.value = false;
      selectByPage(currentPage.value);
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//显示修改客户对话框
function showUpdateDialog(id) {
  customerApi.selectById(id).then((resp) => {
    if (resp.code == 10000) {
      customerInfo.value = resp.data;
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
//添加客户
function insert() {
  customerApi.insert(customerInfo.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      selectByPage(allPage.value);
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//显示添加对话框
function showAddDialog() {
  customerInfo.value = {
    id: null,
    username: null,
    password: "",
    name: "",
    gender: "",
    age: null,
    phone: "",
    status: null,
    groupId: null,
    customerGroup: null,
    orders: null,
  };
  addDialogShow.value = true;
}

//上传成功的钩子
function handleAvatarSuccess(response, uploadFile) {
  ElMessage({
    message: response.msg,
    type: "success",
    duration: 1200,
  });
  imageUrl.value = "http://localhost:8080/upload/" + response.data;
  customerInfo.value.img = response.data;
}
//判断是否可以上传的方法
function beforeAvatarUpload(rawFile) {
  if (rawFile.type !== "image/jpeg") {
    ElMessage.error("头像必须是JPG格式！");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error("头像大小超出2MB！");
    return false;
  }
  return true;
}
//匹配用户组显示
function getUserGroupName(id) {
  const group = groupList.value.find((group) => group.id === id);
  return group ? group.name : "未分组";
}
//获取所有分组信息
function getAllGroup() {
  cgroupApi.selectAll().then((resp) => {
    groupList.value = resp.data;
  });
}
//分页查询
function selectByPage(current) {
  currentPage.value = current;
  customerApi
    .selectByPage(currentPage.value, name.value, false)
    .then((resp) => {
      console.log(resp.data.pages);
      CustomerList.value = resp.data.list;
      allPage.value = resp.data.pages;
    });
}
getAllGroup();
selectByPage(1);
</script>