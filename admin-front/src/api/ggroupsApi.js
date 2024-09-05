import service from ".";
const ggroupsApi = {
    selectAll(){
        return service.get(`/api/ggroups/selectAll`);
    },
    selectById(id){
        return service.get(`/api/ggroups/selectById/${id}`)
    },
    delete(id){
        return service.post("/api/ggroups/deleteById",{id})
    },
    insert(data){
        return service.post("/api/ggroups/insertGgroup",data)
    },
    update(data){
        return service.post("/api/ggroups/updateGgroup",data)
    }

}
export default ggroupsApi