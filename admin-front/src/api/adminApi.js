import service from ".";

const adminApi = {
    login(username,password){
        return service.post("/admin/login",{username,password})
    },logout(){
        return service.post("/admin/logout")
    }
}

export default adminApi