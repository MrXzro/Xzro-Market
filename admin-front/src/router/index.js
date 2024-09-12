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
import ShopView from '@/views/user/ShopView.vue'
import CustomerOrdersView from '@/views/user/CustomerOrdersView.vue'
import CustomerLoginView from '@/views/user/CustomerLoginView.vue'
import AdminLoginView from '@/views/admin/AdminLoginView.vue'
import AdminIndexView from '@/views/admin/AdminIndexView.vue'
import { token } from '@/stores/token'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/index',
      name: 'home',
      redirect:'/shop',
      component: CustomerHome,
      children:[{
        path:'/shop',
        name:'shop',
        component:ShopView
      },{
        path:"/order",
        name:"customerOrder",
        component:CustomerOrdersView
      }]
    },
    {
      path: '/admin',
      name: 'admin',
      redirect:"/admin/index",
      component: HomeView,
      children:[{
        path:'/admin/index',
        name:'adminIndex',
        component:AdminIndexView
      },{
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
    },{
      path:"/login",
      name:"custoemrLogin",
      component:CustomerLoginView
    },{
      path:"/admin/login",
      name:"adminLogin",
      component:AdminLoginView
    }
  ]
})

router.beforeEach((to, from) => {
  //如果当前访问的登录页
  if(to.path == '/login'||to.path == '/admin/login') {
      //放行
      return true
  } else { //如果当前访问的不是登录页
      //获取token
      // let token = sessionStorage.getItem('token');
      if(token().token == null) { //如果token不存在去登录页
        if(to.path.search("/admin")==0){
          return "/admin/login"
        }
        return "/login"
      } else { //token存在放行
          return true
      }
  }
})

export default router
