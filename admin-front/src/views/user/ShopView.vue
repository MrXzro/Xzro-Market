<template>
  <el-row justify="center" style="margin-top: 15px">
    <el-col :span="14">
      <el-carousel
        style="border-radius: 20px"
        indicator-position="none"
        height="360px"
      >
        <el-carousel-item v-for="item in 4" :key="item">
          <h3 text="2xl" justify="center">
            <img height="100%" width="100%" :src="'http://img-api.xzro.com/biying.php?'+item" alt="">
          </h3>
        </el-carousel-item>
      </el-carousel>
    </el-col>
  </el-row>
  <el-row justify="center" style="margin-top: 30px">
    <el-col :span="14">
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="changePane">
        <el-tab-pane label="全部商品" name="0">
          <el-row>
            <!-- <el-col>
                <el-button @click="cart.test = !cart.test">{{
                  item.name
                }}</el-button>
              </el-col> -->
          </el-row>
          <el-row>
            <el-col>
              <el-empty
                v-if="goodsList.length == 0"
                description="当前分类下暂无商品"
              />
              <el-table
                v-if="goodsList.length != 0"
                :row-key="getRowKey"
                border
                :data="goodsList"
                style="width: 100%"
                @select="selectGoods"
                @select-all="selectGoods"
              >
                <el-table-column
                  type="selection"
                  :reserve-selection="true"
                  width="55"
                />
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
                    <el-tag v-if="scope.row.status == 0" type="primary"
                      >上架</el-tag
                    >
                    <el-tag v-if="!scope.row.status == 0" type="info"
                      >下架</el-tag
                    >
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
              <div
                style="display: flex; justify-content: center; margin-top: 15px"
              >
                <el-pagination
                  layout="prev, pager, next"
                  :page-count="allPage"
                  :current-page="currentPage"
                  @update:current-page="selectGoodsByGroup"
                />
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>
        <!-- 商品分组查询开始 -->
        <template v-for="item in ggroupsList" :key="item.id">
          <el-tab-pane
            :label="item.name"
            :name="item.id"
            @tab-click="changePane"
          >
            <el-row> </el-row>
            <el-row>
              <el-col>
                <el-empty
                  v-if="goodsList.length == 0"
                  description="当前分类下暂无商品"
                />
                <el-table
                  v-if="goodsList.length != 0"
                  border
                  :data="goodsList"
                  :row-key="getRowKey"
                  style="width: 100%"
                  @select="selectGoods"
                  @select-all="selectGoods"
                >
                  <el-table-column
                    type="selection"
                    :reserve-selection="true"
                    width="55"
                  />
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
                      <el-tag v-if="scope.row.status == 0" type="primary"
                        >上架</el-tag
                      >
                      <el-tag v-if="!scope.row.status == 0" type="info"
                        >下架</el-tag
                      >
                    </template>
                  </el-table-column>
                  <el-table-column prop="stock" label="库存" width="" />
                  <el-table-column label="分组" width="120">
                    <template #default="scope">
                      <el-popover
                        placement="right"
                        :width="300"
                        trigger="hover"
                      >
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
                <!-- 分页开始 -->
                <div
                  style="
                    display: flex;
                    justify-content: center;
                    margin-top: 15px;
                  "
                >
                  <el-pagination
                    layout="prev, pager, next"
                    :page-count="allPage"
                    :current-page="currentPage"
                    @update:current-page="selectGoodsByGroup"
                  />
                  <!-- 分页结束 -->
                </div>
              </el-col>
            </el-row>
          </el-tab-pane>
        </template>
        <!-- 商品分组查询结束 -->
      </el-tabs>
    </el-col>
  </el-row>
</template>
<script setup>
import { ElCarousel, ElCarouselItem, ElMessage } from "element-plus";
import { useCartStore } from "@/stores/cart";
import { ref } from "vue";
import ggroupsApi from "@/api/ggroupsApi";
import goodsApi from "@/api/goodsApi";

const cart = useCartStore();

//当前页面
const currentPage = ref(1);
//获取的总页数
const allPage = ref(0);
//商品分组
const ggroupsList = ref([]);
//商品列表
const goodsList = ref([]);
//分组ID
const groupId = ref(0);
//激活的选项卡(name)
const activeName = ref();

//将选择的
function selectGoods(selection) {
  cart.cart = selection.map((item) => item.id);
  // 计算 price 之和
  cart.totalPrice = selection
    .reduce((total, item) => total + item.price, 0)
    .toFixed(2);
  // console.log(selection);
}
//row-Key返回函数
function getRowKey(row) {
  return row.id;
}
//切换分页
function changePane(TabsPaneContext, e) {
  console.log(TabsPaneContext.paneName);
  groupId.value = TabsPaneContext.paneName;
  currentPage.value = 1;
  selectGoodsByGroup(1);
}
//根据分组查询商品
function selectGoodsByGroup(current) {
  currentPage.value = current;
  if (groupId.value != 0) {
    goodsApi.selectByGroup(groupId.value, currentPage.value).then((resp) => {
      if (resp.code == 10000) {
        // ElMessage({
        //   message: resp.msg,
        //   type: "success",
        //   duration: 1200,
        // });
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
  } else {
    goodsApi.selectByGroup(groupId.value, currentPage.value).then((resp) => {
      if (resp.code == 10000) {

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
}
//获取商品分组
function selectAllGgroups() {
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

selectAllGgroups();
selectGoodsByGroup(1);
</script>
<style scope>
.el-menu--horizontal > .el-menu-item:nth-child(1) {
  margin-right: auto;
}
</style>