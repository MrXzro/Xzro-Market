<template>
  <el-breadcrumb :separator-icon="ArrowRight" style="margin-bottom: 20px">
    <el-breadcrumb-item :to="{ path: '/admin/index' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>概览</el-breadcrumb-item>
  </el-breadcrumb>

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
</style>
<script setup>
import { onMounted, ref } from "vue";
import * as echarts from "echarts";
import goodsApi from "@/api/goodsApi";


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
    console.log(resp);

    stockGoodName.value = resp.data.map((item) => item.name);

    goodStock.value = resp.data.map((item) => item.salesVolume);
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
  window.onresize = function () {
    myChart.resize();
  };
});
</script>