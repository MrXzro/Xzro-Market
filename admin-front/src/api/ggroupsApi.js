import service from ".";
const ggroupsApi = {
    selectAll(){
        return service.get(`/api/ggroups/selectAll`);
    },
    delete(id){
        return service.post("/api/ggroups/deleteById",{id})
    },
    insert(data){
        return service.post("/api/ggroups/insertGgroup",data)
    }

}
export default ggroupsApi