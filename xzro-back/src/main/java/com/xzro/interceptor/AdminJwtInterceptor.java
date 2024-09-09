package com.xzro.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzro.bean.RespBean;
import com.xzro.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * ClassName: JwtInterceptor
 * Package: com.xzro.interceptor
 * Description:
 * 管理员放行
 * @Author Xzro
 * @Create 2024/9/2 17:48
 * @Version 1.0
 */
@Component
public class AdminJwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //对OPTIONS请求放行，不然会出现跨域问题
        if("OPTIONS".equals(request.getMethod().toUpperCase())) {
            return true;
        }
        //获取token
        String token = request.getHeader("token");
        RespBean respBean = null;
        try {
            //解析JWT，如果出现问题会抛出异常
            Map<String, Object> stringObjectMap = JwtUtils.parseJwtToMap(token);
            if (JwtUtils.verifyJwt(token)){
                //判断管理员权限
                if ("admin".equals(stringObjectMap.get("isAdmin"))) {
                    String string = JwtUtils.generateJwt(stringObjectMap);
                    response.setHeader("token",string);
                    response.setHeader("Access-Control-Expose-Headers","token");
                    return true;
                }
            }
            response.setHeader("token","");
            response.setHeader("Access-Control-Expose-Headers","token");
            respBean = RespBean.error("令牌无效");
        } catch (SignatureVerificationException e) {
            respBean = RespBean.error("无效签名");
        } catch (TokenExpiredException e) {
            respBean = RespBean.error("令牌超时");
        } catch (AlgorithmMismatchException e) {
            respBean = RespBean.error("算法不匹配");
        } catch (Exception e) {
            respBean = RespBean.error("令牌无效");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getOutputStream(), respBean);
        return false;

    }
}