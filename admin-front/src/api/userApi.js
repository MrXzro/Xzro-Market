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
    }

}
export default userApi