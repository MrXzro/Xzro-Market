import service from ".";
const goodsApi = {
    selectByPage(currentPage,name) {
        return service.get(`/api/goods/selectByPage`,{
            params:{
                currentPage,
                name
            }
        });
    },
    selectById(id) {
        return service.get(`/api/goods/selectById/${id}`)
    },
    selectByGroup(id,currentPage) {
        return service.get(`/api/goods/selectByGroup`,{
            params:{
                id,
                currentPage
            }
        })
    },
    deleteById(id) {
        return service.post("/api/goods/deleteById", { "id": id })
    },
    insert(good, groups) {
        return service.post("/api/goods/addGood",{  good,  groups })
    },
    update(good, groups) {
        return service.post("/api/goods/updateGood",  {  good,  groups })
    },selectGoodsSelas(){
        return service.get(`/api/goods/selectGoodsSelas`)
    },selectGoodsStock(){
        return service.get(`/api/goods/selectGoodsStock`)
    }

}
export default goodsApi