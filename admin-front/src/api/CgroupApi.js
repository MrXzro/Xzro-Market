import service from ".";
const cgroupApi = {
    selectAll(){
        return service.get(`/api/cgroups/selectAll`);
    },
    selectById(id){
        return service.get(`/api/cgroups/selectById/${id}`)
    },
    delete(id){
        return service.post("/api/cgroups/deleteById",{id})
    },
    insert(data){
        return service.post("/api/cgroups/insertCgroup",data)
    },
    update(data){
        return service.post("/api/cgroups/updateCgroup",data)
    }

}
export default cgroupApi