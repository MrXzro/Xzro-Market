import service from ".";
const customerApi = {
    selectByPage(currentPage,name,all){
        return service.get(`/api/customer/selectByPage`,{
            params:{
                currentPage,
                name,
                all,
            }
        });
    },
    selectAllCustomer(){
        return service.get("/api/customer/selectByPage",{
            params:{
                name:"",
                all:true
            }
        })
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
    },download(){
        return service.get("/api/excel/download",{
            responseType:"blob"
        })
    }

}
export default customerApi