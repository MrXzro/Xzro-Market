import service from ".";
const ordersApi = {
    selectByPage(currentPage,orderNo,id) {
        return service.get(`/api/orders/selectByPage`,{
            params:{
                currentPage,
                orderNo,
                id
            }
        });
    },
    selectById(id) {
        return service.get(`/api/orders/selectById/${id}`)
    },
    selectByGroup(id,currentPage) {
        return service.get(`/api/orders/selectByGroup/${id}/${currentPage}`)
    },
    deleteById(id) {
        return service.post("/api/orders/deleteById", { "id": id })
    },
    insert(order, goods) {
        return service.post("/api/orders/insertOrder",{  order,  goods })
    },
    update(order, goods) {
        return service.post("/api/orders/updateOrder",  {  order,  goods })
    }

}
export default ordersApi