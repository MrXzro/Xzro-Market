<template>
  <el-breadcrumb :separator-icon="ArrowRight" style="margin-bottom: 20px">
    <el-breadcrumb-item :to="{ path: '/admin/index' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>概览</el-breadcrumb-item>
  </el-breadcrumb>
  <el-row style="margin-bottom: 20px">
    <el-col :span="24">
      <el-card style="max-width: 100%; background-color: rgba(0, 0, 0, 0)">
        <el-row>
          <el-col :span="6">
            <el-statistic title="会员数量" :value="outputMemberNum" />
          </el-col>
          <el-col :span="6">
            <el-statistic :value="outputOrderNum">
              <template #title>
                <div style="display: inline-flex; align-items: center">
                  订单数量
                  <el-icon style="margin-left: 4px" :size="12">
                    <Male />
                  </el-icon>
                </div>
              </template>
            </el-statistic>
          </el-col>
          <el-col :span="6">
            <el-statistic title="商品数量" :value="outputGoodNum" />
          </el-col>
          <el-col :span="6">
            <el-statistic title="总收益" :value="outputAllIncome">
              <template #suffix>
                <el-icon style="vertical-align: -0.125em">
                  <Money />
                </el-icon>
              </template>
            </el-statistic>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
  </el-row>
  <el-row :gutter="20">
    <el-col :span="12">
      <el-card style="max-width: 100%; background-color: rgba(0, 0, 0, 0)">
        <div id="selas" style="width: 100%; height: 300px"></div>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card style="max-width: 100%; background-color: rgba(0, 0, 0, 0)">
        <div id="stock" style="width: 100%; height: 300px"></div>
      </el-card>
    </el-col>
  </el-row>
</template>
<style scoped>
.el-col {
  text-align: center;
}

.number-transition-enter-active,
.number-transition-leave-active {
  transition: all 1.5s ease;
}
.number-transition-enter-from,
.number-transition-leave-to {
  opacity: 0;
  transform: translateY(30px);
}
</style>
<script setup>
import { onMounted, ref } from "vue";
import * as echarts from "echarts";
import goodsApi from "@/api/goodsApi";
import { useTransition } from "@vueuse/core";
import { ChatLineRound, Male, Money } from "@element-plus/icons-vue";
import infoApi from "@/api/infoApi";

// 定义统计信息
const memberNum = ref(0);
const orderNum = ref(0);
const goodNum = ref(0);
const allIncome = ref(0);

const outputMemberNum = useTransition(memberNum, { duration: 1500 });
const outputOrderNum = useTransition(orderNum, { duration: 1500 });
const outputGoodNum = useTransition(goodNum, { duration: 1500 });
const outputAllIncome = useTransition(allIncome, { duration: 1500 });

//获取统计信息
function getInfo() {
  infoApi.getInfo().then((resp) => {
    console.log(resp.data);
    // 使用 useTransition 过渡效果更新数据
    memberNum.value = resp.data.memberNum;
    orderNum.value = resp.data.orderNum;
    goodNum.value = resp.data.goodNum;
    allIncome.value = resp.data.allIncome;
  });
}

const selasGoodName = ref([]);
const goodSelas = ref([]);

const stockGoodName = ref([]);
const goodStock = ref([]);
onMounted(() => {

  //查询销量前五
  goodsApi.selectGoodsSelas().then((resp) => {
    selasGoodName.value = resp.data.map((item) => item.name);

    goodSelas.value = resp.data.map((item) => item.salesVolume);
    let myChart = echarts.init(document.getElementById("selas"));
    myChart.setOption({
      title: {
        text: "Top 5 销量",
      },
      tooltip: {},
      xAxis: {
        data: selasGoodName.value,
        axisLabel: {
          interval: 0,
          rotate: 40,
        },
      },
      yAxis: {},
      series: [
        {
          name: "销量",
          type: "line",
          data: goodSelas.value,
        },
      ],
    });
  });
  //查询需要补货
  goodsApi.selectGoodsStock().then((resp) => {
    stockGoodName.value = resp.data.map((item) => item.name);

    goodStock.value = resp.data.map((item) => item.stock);
    let myChart = echarts.init(document.getElementById("stock"));
    myChart.setOption({
      title: {
        text: "Bottom 5 库存",
      },
      tooltip: {},
      xAxis: {
        data: stockGoodName.value,
        axisLabel: {
          interval: 0,
          rotate: 40,
        },
      },
      yAxis: {},
      series: [
        {
          name: "库存",
          type: "line",
          data: goodStock.value,
        },
      ],
    });
  });
});
getInfo();
</script>