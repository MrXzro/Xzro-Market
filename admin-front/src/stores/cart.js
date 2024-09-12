import { ref, computed } from 'vue'
import { defineStore } from 'pinia'


export const useCartStore = defineStore('counter', () => {
  // const count = ref(0)
  // const doubleCount = computed(() => count.value * 2)
  // function increment() {
  //   count.value++
  // }

  //存放已选商品
  const cart = ref([])
  const test = ref(false)
  //总价计算
  const totalPrice = ref(0)
  //付款方式
  const paymentMethod = ref("")
  //判断是否显示结算页脚
  const ifShowCheckButton = computed(() => {
    return cart.value.length != 0
  })
  const ifShowAddOrderDialog = ref(false)
  //提交订单
  //....
  return { cart, ifShowCheckButton, test, paymentMethod }
})
