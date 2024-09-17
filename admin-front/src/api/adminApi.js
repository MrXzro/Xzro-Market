import service from ".";

const adminApi = {
    login(username,password,captchaId,captcha){
        return service.post("/admin/login",{username,password,captchaId,captcha})
    },logout(){
        return service.post("/admin/logout")
    }
}

export default adminApi