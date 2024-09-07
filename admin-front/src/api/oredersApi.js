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
    }

}
export default ordersApi