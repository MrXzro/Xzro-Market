import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/admin/HomeView.vue'
import GoodsView from '../views/admin/GoodsView.vue'
//暗黑模式样式 1
import 'element-plus/theme-chalk/dark/css-vars.css'
import GgrpupView from '@/views/admin/GgrpupView.vue'
import CustomerView from '@/views/admin/CustomerView.vue'
import CgrpupView from '@/views/admin/CgrpupView.vue'
import OrdersView from '@/views/admin/OrdersView.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      redirect:'/orders',
      component: HomeView,
      children:[{
        path:'/goods',
        name:'goods',
        component:GoodsView
      },{
        path:'/ggroups',
        name:'ggroups',
        component:GgrpupView
      },{
        path:'/customer',
        name:'customer',
        component:CustomerView
      },{
        path:'/cgroups',
        name:'cgroups',
        component:CgrpupView
      },{
        path:'/orders',
        name:'orders',
        component:OrdersView
      }]
    }
  ]
})



export default router
