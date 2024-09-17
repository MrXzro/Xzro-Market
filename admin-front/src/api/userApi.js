import service from ".";
const userApi = {
    selectByPage(currentPage,orderNo,id) {
        return service.get(`/api/orders/selectByPage`,{
            params:{
                currentPage,
                orderNo,
                id
            }
        });
    },
    update(order, goods) {
        return service.post("/api/orders/updateOrder",  {  order,  goods })
    },login(username,password,captcha,captchaId){
        return service.post("/customer/login",{username,password,captchaId,captcha})
    },logout(){
        return service.post("/customer/logout")
    },
    insertOrder(paymentMethod, goods) {
        return service.post("/api/orders/userInsertOrder",{  paymentMethod,  goods })
    },payOrder(orderId){
        return service.post("/api/orders/userPay",{  orderId })
    },getCaptcha(){
        return service.get("/customer/captcha")
    }

}
export default userApi