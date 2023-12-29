package com.njpi.xyh.interceptors;

import cn.hutool.core.util.StrUtil;
import com.njpi.xyh.common.Constant;
import com.njpi.xyh.utils.JwtUtil;
import com.njpi.xyh.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * 登录校验拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(Constant.LOGIN_HEADER);

        try {
            // 没有出现异常代表认证通过
            Map<String, Object> stringObjectMap = JwtUtil.parseToken(token);
            // 将用户信息保存到拦截器中
            ThreadLocalUtil.set(stringObjectMap);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            // 不放行
            return false;
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            // 在当前线程结束之后将保存在ThreadLocal中的数据清除掉
            ThreadLocalUtil.remove();
    }
}
