<template>
  <el-card style="max-width: 100%; height: 99%">
    <template #header>
      <el-row justify="space-between">
        <el-col :span="6">
          <el-button @click="showAddDialog">新增</el-button>
          <el-button @click="showAddDialog">导出</el-button>
          <el-button @click="showAddDialog">导入</el-button>
        </el-col>
        <el-col :span="6">
          <el-input
            v-model="orderNo"
            style="max-width: 600px"
            placeholder="请输入订单号"
            class="input-with-select"
            @input="selectByPage(1)"
            >
            <template #prepend>
              <el-select
                v-model="searchCustomer"
                placeholder="请选择客户"
                style="width: 115px"
                @change="selectByPage(1)"
              >
              <el-option label="全部客户" :value="0" />
              <template v-for="item in customerList" :key="item.id">
                  <el-option :label="item.name" :value="item.id" />
                </template>
              </el-select>
            </template>
            <!-- <template #append>
              <el-button :icon="Search" />
            </template> -->
          </el-input>
        </el-col>
      </el-row>
    </template>
    <el-skeleton
      style="width: 100%"
      direction="vertical"
      alignment="flex-start"
      :loading="loading"
      :rows="5"
      animated
      class="content-skeleton"
    >
      <el-table :data="ordersList" style="width: 100%">
        <el-table-column fixed prop="id" label="ID" width="50" />
        <el-table-column
          prop="orderNo"
          label="订单号"
          width=""
          show-overflow-tooltip
        />
        <el-table-column prop="paidPrice" label="付款金额" width="" />
        <el-table-column prop="paymentMethod" label="付款方式" width="" />
        <el-table-column prop="customer.name" label="客户" width="" />
        <el-table-column label="订单状态" width="">
          <template #default="scope">
            <el-tag v-if="scope.row.status == 0" type="primary">未付款</el-tag>
            <el-tag v-if="!scope.row.status == 0" type="info">已付款</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="startDate" label="创建时间" width="180" />
        <el-table-column prop="endDate" label="结束时间" width="180" />
        <el-table-column label="商品" width="120">
          <template #default="scope">
            <el-popover placement="right" :width="300" trigger="hover">
              <template #reference>
                <el-button size="default">商品</el-button>
              </template>
              <el-table :data="scope.row.goods">
                <el-table-column property="id" label="ID" width="50" />
                <el-table-column property="name" label="商品名" />
                <el-table-column property="price" label="售价" />
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
              title="你确定要删除该订单吗？"
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
    </el-skeleton>
    <div style="display: flex; justify-content: center">
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
  <!-- 修改订单的对话框开始 -->
  <el-dialog v-model="updateDialogShow" title="修改订单" width="500">
    <el-form :model="orderInfo">
      <el-form-item label="订单号" label-width="20%">
        <el-input disabled v-model="orderInfo.orderNo" autocomplete="off" />
      </el-form-item>
      <el-form-item label="付款方式" label-width="20%">
        <el-input v-model="orderInfo.paymentMethod" autocomplete="off" />
      </el-form-item>
      <el-form-item label="创建时间" label-width="20%">
        <el-date-picker
          v-model="orderInfo.startDate"
          type="datetime"
          placeholder="请选择创建时间"
          value-format="YYYY-MM-DD HH:mm:ss"
          format="YYYY-MM-DD HH:mm:ss"
          date-format="MMM DD, YYYY"
          time-format="HH:mm"
        />
      </el-form-item>
      <el-form-item label="结束时间" label-width="20%">
        <el-date-picker
          v-model="orderInfo.endDate"
          type="datetime"
          placeholder="请选择结束时间"
          value-format="YYYY-MM-DD HH:mm:ss"
          format="YYYY-MM-DD HH:mm:ss"
          date-format="MMM DD, YYYY"
          time-format="HH:mm"
        />
      </el-form-item>
      <el-form-item label="订单状态" label-width="20%">
        <el-select v-model="orderInfo.status" placeholder="请设置订单状态">
          <el-option label="未付款" :value="0" />
          <el-option label="已付款" :value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="选择客户" label-width="20%">
        <el-select v-model="orderInfo.customerId" placeholder="请选择客户">
          <template v-for="item in customerList" :key="item.id">
            <el-option :label="item.name" :value="item.id" />
          </template>
        </el-select>
      </el-form-item>
      <el-form-item label="选择商品" label-width="20%">
        <el-popover placement="right" :width="300" trigger="hover">
          <template #reference>
            <el-button size="default" @click="showSelectGgroupDialog"
              >商品</el-button
            >
          </template>
          <el-table :data="orderInfo.goods">
            <el-table-column property="id" label="ID" width="50" />
            <el-table-column property="name" label="name" />
            <el-table-column property="price" label="售价" />
          </el-table>
        </el-popover>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="updateDialogShow = false">取消</el-button>
        <el-button type="primary" @click="update()"> 确认 </el-button>
      </div>
    </template>
  </el-dialog>
  <!-- 修改订单的对话框结束 -->
  <!-- 添加订单的对话框开始 -->
  <el-dialog v-model="addDialogShow" title="添加商品" width="500">
    <el-form :model="orderInfo">
      <el-form-item label="付款方式" label-width="20%">
        <el-input
          v-model="orderInfo.paymentMethod"
          autocomplete="off"
          placeholder="请输入支付方式"
        />
      </el-form-item>
      <el-form-item label="创建时间" label-width="20%">
        <el-date-picker
          v-model="orderInfo.startDate"
          type="datetime"
          placeholder="请选择创建时间"
          value-format="YYYY-MM-DD HH:mm:ss"
          format="YYYY-MM-DD HH:mm:ss"
          date-format="MMM DD, YYYY"
          time-format="HH:mm"
        />
      </el-form-item>
      <el-form-item label="结束时间" label-width="20%">
        <el-date-picker
          v-model="orderInfo.endDate"
          type="datetime"
          placeholder="请选择结束时间"
          value-format="YYYY-MM-DD HH:mm:ss"
          format="YYYY-MM-DD HH:mm:ss"
          date-format="MMM DD, YYYY"
          time-format="HH:mm"
        />
      </el-form-item>
      <el-form-item label="订单状态" label-width="20%">
        <el-select v-model="orderInfo.status" placeholder="请设置订单状态">
          <el-option label="未付款" :value="0" />
          <el-option label="已付款" :value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="选择客户" label-width="20%">
        <el-select v-model="orderInfo.customerId" placeholder="请选择客户">
          <template v-for="item in customerList" :key="item.id">
            <el-option :label="item.name" :value="item.id" />
          </template>
        </el-select>
      </el-form-item>
      <el-form-item label="选择商品" label-width="20%">
        <el-button size="default" @click="showSelectGgroupDialog"
          >商品</el-button
        >
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="addDialogShow = false">取消</el-button>
        <el-button type="primary" @click="insert()"> 确认 </el-button>
      </div>
    </template>
  </el-dialog>
  <!-- 添加订单的对话框结束 -->
  <!--选择商品对话框开始-->
  <el-dialog
    v-model="selectGoodsDialog"
    :show-close="false"
    title="选择商品"
    width="700"
  >
    <el-transfer
      :titles="['未选', '已选']"
      filterable
      v-model="selectedGoods"
      :data="goodsList"
    />
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="cancleSelectDialog">Cancel</el-button>
        <el-button type="primary" @click="selectGoodsDialog = false">
          Confirm
        </el-button>
      </div>
    </template>
  </el-dialog>
  <!--选择商品对话框结束-->
</template>
<script setup>
import goodsApi from "@/api/goodsApi";
import { ref } from "vue";
import { ElMessage, ElSkeleton } from "element-plus";
import ordersApi from "@/api/oredersApi";
import customerApi from "@/api/customerApi";
import { Search } from "@element-plus/icons-vue";
//商品列表
const ordersList = ref([]);
//商品更新表信息
const orderInfo = ref({
  id: null,
  orderNo: null,
  status: null,
  paidPrice: null,
  paymentMethod: null,
  customerId: null,
  customer: null,
  startDate: null,
  endDate: null,
  goods: null,
});
//获取的总页数
const allPage = ref(0);
//所有商品
const goodsList = ref([]);
//当前页面
const currentPage = ref(1);
//已选择的商品分组
const selectedGoods = ref([]);
//是否显示分配商品对话框
const selectGoodsDialog = ref(false);
//是否显示修改对话框
const updateDialogShow = ref(false);
//是否显示添加对话框
const addDialogShow = ref(false);
//是否显示骨架屏
const loading = ref(true);
//穿梭框缓存
const cache = ref([]);
//所有客户列表
const customerList = ref([]);
//选择客户
const searchCustomer = ref(null);
//订单号
const orderNo = ref("");

//获取所有客户
function selectAllCustomer() {
  customerApi.selectAllCustomer().then((resp) => {
    customerList.value = resp.data;
  });
}
//取消添加商品对话框
function cancleSelectDialog() {
  //恢复
  selectedGoods.value = cache.value;
  selectGoodsDialog.value = false;
}
//显示添加对话框
function showAddDialog() {
  selectAllCustomer();
  //重置表单
  orderInfo.value = {
    id: null,
    orderNo: null,
    status: null,
    paidPrice: null,
    paymentMethod: null,
    customerId: null,
    customer: null,
    startDate: null,
    endDate: null,
    goods: [],
  };
  //重置穿梭框选择
  selectedGoods.value = [];
  addDialogShow.value = true;
}
//显示分配商品对话框
function showSelectGgroupDialog() {
  selectGoods();
  selectGoodsDialog.value = true;
  //备份原商品
  cache.value = selectedGoods.value;
}

//添加订单
function insert() {
  console.log(orderInfo.value);
  ordersApi.insert(orderInfo.value, selectedGoods.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      selectByPage(allPage.value);
      addDialogShow.value = false;
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//修改订单
function update() {
  ordersApi.update(orderInfo.value, selectedGoods.value).then((resp) => {
    if (resp.code == 10000) {
      ElMessage({
        message: resp.msg,
        type: "success",
        duration: 1200,
      });
      selectByPage(currentPage.value);
      updateDialogShow.value = false;
    } else {
      ElMessage.error({
        message: resp.msg,
        type: "error",
        duration: 2000,
      });
    }
  });
}
//删除订单
function deleteById(id) {
  ordersApi.deleteById(id).then((resp) => {
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

//查询全部商品
function selectGoods() {
  goodsApi.selectByGroup(0, 0).then((resp) => {
    if (resp.code == 10000) {
      goodsList.value = resp.data.map((item, index) => ({
        label: item.id + "." + item.name + " " + item.price + "￥",
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
//显示修改订单对话框
function showUpdateDialog(id) {
  ordersApi.selectById(id).then((resp) => {
    //获取客户列表
    selectAllCustomer();
    orderInfo.value = resp.data;
    selectedGoods.value = resp.data.goods.map((group) => group.id);
    console.log(selectedGoods.value);
    console.log(orderInfo.value);
    updateDialogShow.value = true;
  });
}

//分页查询
function selectByPage(current) {
  // loading.value = true
  ordersApi.selectByPage(current,orderNo.value,searchCustomer.value).then((resp) => {
    if (resp.code == 10000) {
      ordersList.value = resp.data.list;
      allPage.value = resp.data.pages;
      currentPage.value = current;
      loading.value = false;
      console.log(resp.data);
    }
  });
}
selectByPage(currentPage.value);
selectAllCustomer()
</script>
<style scope>
.el-transfer-panel {
  width: 250px; /* 左右两个穿梭框的高度和宽度 */
}
</style>