import axios from 'axios'
import { ElMessage } from 'element-plus';
import router from '@/router';
import { token } from '@/stores/token';


//返回的是axios的实例
const service = axios.create({
  baseURL: "http://localhost:8080"
})
// axios请求拦截器
service.interceptors.request.use(function (config) {
  if (!config.url.startsWith("/login")) {
    // config.headers.token = sessionStorage.getItem("token")
    config.headers.token = token().token
  }
  return config
}, function (error) {
  return Promise.reject(error)
})
// axios的响应拦截器
service.interceptors.response.use(response => {
  // let token = response.headers.token;
  // sessionStorage.setItem("token", token)
  token().token = response.headers.token
  return response.data;
}, error => {
  if (error.status == 403) {
    ElMessage.error({
      message: "令牌错误，请重新登录",
      duration: 1200,
      onClose: () => {
        // sessionStorage.removeItem("token")
        token().token = null
        router.push("/login")
      }
    })

  }
})

export default service

