import service from ".";
const customerApi = {
    selectByPage(currentPage){
        return service.get(`/api/customer/selectByPage/${currentPage}`);
    },
    selectByGroup(id,currentPage) {
        return service.get(`/api/customer/selectByGroup/${id}/${currentPage}`)
    },
    selectById(id){
        return service.get(`/api/customer/selectById/${id}`)
    },
    delete(id){
        return service.post("/api/customer/deleteById",{id})
    },
    insert(data){
        return service.post("/api/customer/insertCustomer",data)
    },
    update(data){
        return service.post("/api/customer/updateCustomer",data)
    }

}
export default customerApi