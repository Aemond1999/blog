package com.hya.interceptor;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hya.common.domain.UserDo;
import com.hya.constants.RedisPrefixConstants;
import com.hya.enums.AppExceptionEnum;
import com.hya.exception.AppException;
import com.hya.service.UserService;
import com.hya.utils.Result;
import com.hya.utils.UserThreadLocal;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.HashSet;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录拦截校验
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {

            throw new AppException(AppExceptionEnum.NOT_LOGIN);
        }
        String json = stringRedisTemplate.opsForValue().get((RedisPrefixConstants.TOKEN_KEY_PREFIX + token));
        if (StringUtils.isBlank(json)) {
            throw new AppException(AppExceptionEnum.NOT_LOGIN);
        }
        // 获取用户信息
        UserThreadLocal.put(JSON.parseObject(json, UserDo.class));
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
