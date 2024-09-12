import service from ".";
const infoApi = {
    getInfo() {
        return service.get(`/api/info`);
    }

}
export default infoApi