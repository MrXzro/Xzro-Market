import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/admin/HomeView.vue'
import GoodsView from '../views/admin/GoodsView.vue'
//暗黑模式样式 1
import 'element-plus/theme-chalk/dark/css-vars.css'
import GgrpupView from '@/views/admin/GgrpupView.vue'
import CustomerView from '@/views/admin/CustomerView.vue'
import CgrpupView from '@/views/admin/CgrpupView.vue'
import OrdersView from '@/views/admin/OrdersView.vue'
import CustomerHome from '@/views/user/CustomerHome.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/index',
      name: 'home',
      component: CustomerHome,
      children:[{
        path:'/shop',
        name:'shop',
        component:ShopView
      }]
    },
    {
      path: '/admin',
      name: 'admin',
      component: HomeView,
      children:[{
        path:'/admin/goods',
        name:'goods',
        component:GoodsView
      },{
        path:'/admin/ggroups',
        name:'ggroups',
        component:GgrpupView
      },{
        path:'/admin/customer',
        name:'customer',
        component:CustomerView
      },{
        path:'/admin/cgroups',
        name:'cgroups',
        component:CgrpupView
      },{
        path:'/admin/orders',
        name:'orders',
        component:OrdersView
      }]
    }
  ]
})



export default router
