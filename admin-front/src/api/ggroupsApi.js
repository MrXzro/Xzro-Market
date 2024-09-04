import service from ".";
const ggroupsApi = {
    selectAll(){
        return service.get(`/api/ggroups/selectAll`);
    }

}
export default ggroupsApi