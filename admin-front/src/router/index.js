import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import GoodsView from '../views/GoodsView.vue'
//暗黑模式样式 1
import 'element-plus/theme-chalk/dark/css-vars.css'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      redirect:'/goods',
      component: HomeView,
      children:[{
        path:'/goods',
        name:'goods',
        component:GoodsView
      }]
    }
  ]
})



export default router
