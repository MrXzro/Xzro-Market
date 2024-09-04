<template>
  <el-card style="max-width: 100%">
    <template #header>
      <div class="card-header">
        <el-button @click="showAddDialog">新增</el-button>
      </div>
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
      <el-table-column fixed="right" label="操作" min-width="120">
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
            title="你确定要删除该商品吗？"
            confirm-button-text="确认"
            cancel-button-text="取消"
            width="200px"
            @confirm="deleteById(scope.row.id)"
          >
            <template #reference>
              <el-button link type="primary" size="small">删除</el-button>
            </template>
          </el-popconfirm>
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
    <!-- <template #footer>Footer content</template> -->
  </el-card>
  <!-- 修改商品的对话框开始 -->
  <el-dialog v-model="updateDialogShow" title="修改商品" width="500">
    <el-form :model="goodInfo">
      <el-form-item label="名称" label-width="20%">
        <el-input v-model="goodInfo.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="成本" label-width="20%">
        <el-input v-model="goodInfo.cost" autocomplete="off" />
      </el-form-item>
      <el-form-item label="售价" label-width="20%">
        <el-input v-model="goodInfo.price" autocomplete="off" />
      </el-form-item>
      <el-form-item label="销量" label-width="20%">
        <el-input v-model="goodInfo.salesVolume" autocomplete="off" />
      </el-form-item>
      <el-form-item label="库存" label-width="20%">
        <el-input v-model="goodInfo.stock" autocomplete="off" />
      </el-form-item>
      <el-form-item label="描述" label-width="20%">
        <el-input
          type="textarea"
          autosize
          v-model="goodInfo.description"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item label="状态" label-width="20%">
        <el-select v-model="goodInfo.status" placeholder="请设置商品状态">
          <el-option label="上架" :value="0" />
          <el-option label="下架" :value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="分组" label-width="20%">
        <el-popover placement="right" :width="300" trigger="hover">
          <template #reference>
            <el-button size="default" @click="showSelectGgroupDialog"
              >商品组</el-button
            >
          </template>
          <el-table :data="goodInfo.ggroups">
            <el-table-column property="id" label="ID" />
            <el-table-column property="name" label="name" />
          </el-table>
        </el-popover>
      </el-form-item>
      <el-form-item label="图片" label-width="20%">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/api/upload"
          name="pic"
          :value="goodInfo.img"
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
  <!-- 修改商品的对话框结束 -->
  <!-- 添加商品的对话框开始 -->
  <el-dialog v-model="addDialogShow" title="添加商品" width="500">
    <el-form :model="goodInfo">
      <el-form-item label="名称" label-width="20%">
        <el-input v-model="goodInfo.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="成本" label-width="20%">
        <el-input v-model="goodInfo.cost" autocomplete="off" />
      </el-form-item>
      <el-form-item label="售价" label-width="20%">
        <el-input v-model="goodInfo.price" autocomplete="off" />
      </el-form-item>
      <el-form-item label="销量" label-width="20%">
        <el-input v-model="goodInfo.salesVolume" autocomplete="off" />
      </el-form-item>
      <el-form-item label="库存" label-width="20%">
        <el-input v-model="goodInfo.stock" autocomplete="off" />
      </el-form-item>
      <el-form-item label="描述" label-width="20%">
        <el-input
          type="textarea"
          autosize
          v-model="goodInfo.description"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item label="状态" label-width="20%">
        <el-select v-model="goodInfo.status" placeholder="请设置商品状态">
          <el-option label="上架" :value="0" />
          <el-option label="下架" :value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="分组" label-width="20%">
        <el-popover placement="right" :width="300" trigger="hover">
          <template #reference>
            <el-button size="default" @click="showSelectGgroupDialog"
              >商品组</el-button
            >
          </template>
          <el-table :data="goodInfo.ggroups">
            <el-table-column property="id" label="ID" />
            <el-table-column property="name" label="name" />
          </el-table>
        </el-popover>
      </el-form-item>
      <el-form-item label="图片" label-width="20%">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/api/upload"
          name="pic"
          :value="goodInfo.img"
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
  <!-- 添加商品的对话框结束 -->
  <!--选择分组对话框开始-->
  <el-dialog v-model="selectGgroupsDialog" title="选择分组" width="600">
    <el-transfer v-model="selectedGgroup" :data="ggroupsList" />
  </el-dialog>
  <!--选择对话框结束-->
</template>
<script setup>
import goodsApi from "@/api/goodsApi";
import { ref } from "vue";
import { ElMessage } from "element-plus";
import ggroupsApi from "@/api/ggroupsApi";
//商品列表
const goodsList = ref([]);
//商品图片
const imageUrl = ref("");
//商品更新表信息
const goodInfo = ref({
  id: null,
  name: "",
  img: "",
  description: "",
  cost: null,
  price: null,
  salesVolume: null,
  status: null,
  stock: null,
  ggroups: null,
});
//获取的总页数
const allPage = ref(0);
//所有商品组
const ggroupsList = ref([]);
//当前页面
const currentPage = ref(1);
//已选择的商品分组
const selectedGgroup = ref([]);
//是否显示分配商品组对话框
const selectGgroupsDialog = ref(false);
//是否显示修改对话框
const updateDialogShow = ref(false);
//是否显示添加对话框
const addDialogShow = ref(false);

//显示添加对话框
function showAddDialog(){
  //重置表单
  goodInfo.value = {
  id: null,
  name: "",
  img: "",
  description: "",
  cost: null,
  price: null,
  salesVolume: null,
  status: null,
  stock: null,
  ggroups: null,
  }
  //重置穿梭框选择
  selectedGgroup.value = []
  //重置图片
  imageUrl.value = ""
  addDialogShow.value = true
}
//显示分配商品分组对话框
function showSelectGgroupDialog() {
  selectGgroups();
  selectGgroupsDialog.value = true;
}

//添加商品
function insert(){
  goodsApi.insert(goodInfo.value, selectedGgroup.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      selectByPage(allPage.value);
      addDialogShow.value = false;
      // ggroupsList.value = resp.data.map((item, index) => ({
      //   label: item.name,
      //   key: item.id,
      // }));
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//更新商品
function update() {
  goodsApi.update(goodInfo.value, selectedGgroup.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      selectByPage(currentPage.value);
      updateDialogShow.value = false;
      // ggroupsList.value = resp.data.map((item, index) => ({
      //   label: item.name,
      //   key: item.id,
      // }));
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//删除商品
function deleteById(id) {
  goodsApi.deleteById(id).then((resp) => {
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

//查询所有商品组
function selectGgroups() {
  ggroupsApi.selectAll().then((resp) => {
    if (resp.code == 10000) {
      ggroupsList.value = resp.data.map((item, index) => ({
        label: item.name,
        key: item.id,
      }));
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//显示更新商品对话框
function showUpdateDialog(id) {
  goodsApi.selectById(id).then((resp) => {
    goodInfo.value = resp.data;
    imageUrl.value = "http://localhost:8080/upload/" + resp.data.img;
    selectedGgroup.value = resp.data.ggroups.map((group) => group.id);
    console.log(selectedGgroup.value);
    console.log(goodInfo.value);
    updateDialogShow.value = true;
  });
}
//上传成功的钩子
function handleAvatarSuccess(response, uploadFile) {
  ElMessage({
    message: response.msg,
    type: "success",
    duration: 1200,
  });
  imageUrl.value = "http://localhost:8080/upload/" + response.data;
  goodInfo.value.img = response.data;
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
//分页查询
function selectByPage(current) {
  goodsApi.selectByPage(current).then((resp) => {
    goodsList.value = resp.data.list;
    allPage.value = resp.data.pages;
    currentPage.value = current;
    console.log(resp.data);
  });
}
selectByPage(currentPage.value);
</script>