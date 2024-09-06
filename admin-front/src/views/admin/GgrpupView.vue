<template>
  <el-row justify="space-evenly">
    <el-col :span="8">
      <el-card style="width: 100%">
        <template #header>
          <div class="card-header">
            <el-button @click="showAddDialog">新增</el-button>
          </div>
        </template>
        <el-table :data="ggroupsList" style="width: 100%">
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
                @click="showGoods(scope.row.id)"
              >
                预览
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-col>
    <!--商品预览开始-->
    <el-col :span="15">
      <el-card style="max-width: 100%">
        <template #header>
          <div class="card-header">商品预览</div>
        </template>
        <el-table :data="goodsList" style="width: 100%">
          <el-table-column fixed prop="id" label="ID" width="50" />
          <el-table-column
            prop="name"
            label="商品名"
            width=""
            show-overflow-tooltip
          />
          <el-table-column
            prop="description"
            label="商品描述"
            width=""
            show-overflow-tooltip
          />
          <el-table-column prop="cost" label="成本" width="" />
          <el-table-column prop="price" label="售价" width="" />
          <el-table-column prop="salesVolume" label="销量" width="" />
          <el-table-column label="状态" width="">
            <template #default="scope">
              <el-tag v-if="scope.row.status == 0" type="primary">上架</el-tag>
              <el-tag v-if="!scope.row.status == 0" type="info">下架</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="stock" label="库存" width="" />
          <el-table-column label="分组" width="120">
            <template #default="scope">
              <el-popover placement="right" :width="300" trigger="hover">
                <template #reference>
                  <el-button size="default">商品组</el-button>
                </template>
                <el-table :data="scope.row.ggroups">
                  <el-table-column property="id" label="ID" />
                  <el-table-column property="name" label="name" />
                </el-table>
              </el-popover>
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
    <!--商品预览结束-->
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
import goodsApi from "@/api/goodsApi";
import { ref } from "vue";
import { ElMessage } from "element-plus";
import ggroupsApi from "@/api/ggroupsApi";
//商品分组信息
const ggroupsList = ref([]);
//当前分页
const currentPage = ref(1);
//获取的总页数
const allPage = ref(0);
//商品列表
const goodsList = ref([]);
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


//修改分组方法
function update(){
    ggroupsApi.update(groupInfo.value).then(resp=>{
      if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      updateDialogShow.value = false
      selectAll()
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
    })
}
//显示修改分组对话框
function showUpdateDialog(id){
  ggroupsApi.selectById(id).then(resp=>{
    if (resp.code == 10000) {
      groupInfo.value = resp.data
      updateDialogShow.value = true
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  })
}
//添加分组方法
function insert() {
  ggroupsApi.insert(groupInfo.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      addDialogShow.value = false
      selectAll()
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
//删除商品分组方法
function deleteGroup(id) {
  ggroupsApi.delete(id).then((resp) => {
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
//预览分组按钮
function showGoods(id) {
  currentPage.value = 1;
  groupId.value = id;
  selectByGroup(currentPage.value);
}

//分组查询
function selectByGroup(current) {
  currentPage.value = current;
  goodsApi.selectByGroup(groupId.value, currentPage.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      allPage.value = resp.data.pages;
      goodsList.value = resp.data.list;
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

//查询所有商品分组
function selectAll() {
  ggroupsApi.selectAll().then((resp) => {
    if (resp.code == 10000) {
      console.log(resp.data);
      ggroupsList.value = resp.data;
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