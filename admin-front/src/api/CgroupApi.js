import service from ".";
const CgroupApi = {
    selectAll(){
        return service.get(`/api/cgroups/selectAll`);
    }

}
export default CgroupApi