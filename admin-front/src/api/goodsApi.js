import service from ".";
const goodsApi = {
    selectByPage(currentPage) {
        return service.get(`/api/goods/selectByPage/${currentPage}`);
    },
    selectById(id) {
        return service.get(`/api/goods/selectById/${id}`)
    },
    deleteById(id) {
        return service.post("/api/goods/deleteById", { "id": id })
    },
    insert(good, groups) {
        return service.post("/api/goods/addGood",{  good,  groups })
    },
    update(good, groups) {
        return service.post("/api/goods/updateGood",  {  good,  groups })
    }

}
export default goodsApi