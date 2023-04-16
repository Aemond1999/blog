package com.hya.blog.handler;

import com.alibaba.fastjson.JSON;
import com.hya.blog.enums.HttpCodeEnum;
import com.hya.blog.utils.Result;
import com.hya.blog.utils.WebUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Result result = new Result(HttpCodeEnum.NEED_LOGIN.getCode(), HttpCodeEnum.NEED_LOGIN.getMsg(),null);
        String s = JSON.toJSONString(result);
        WebUtil.renderString(httpServletResponse,s);
    }
}
