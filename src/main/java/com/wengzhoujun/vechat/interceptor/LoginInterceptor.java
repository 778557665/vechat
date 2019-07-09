package com.wengzhoujun.vechat.interceptor;

import cn.hutool.core.exceptions.ValidateException;
import com.wengzhoujun.vechat.constants.CommonConstant;
import com.wengzhoujun.vechat.domain.CheckAuthResponse;
import com.wengzhoujun.vechat.enums.ResponseCodeEnum;
import com.wengzhoujun.vechat.util.CommonUtil;
import com.wengzhoujun.vechat.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 2019/7/9.
 *
 * @author WengZhoujun
 */
@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER);
        boolean isAjax = CommonUtil.isAjax(request);
        if (StringUtils.isEmpty(authHeader)) {
            return response(isAjax, response);
        }

        CheckAuthResponse checkAuthResponse = validToken(authHeader);
        if (ResponseCodeEnum.SUCCESS.getCode().equals(checkAuthResponse.getCode())) {
            return super.preHandle(request, response, handler);
        }

        if (isAjax) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("{\"code\":\"" + checkAuthResponse.getCode() + "\"" +
                    ",\"msg\":\"" + checkAuthResponse.getMsg() + "\"}");
            return false;
        }
        response.sendRedirect(CommonConstant.SSO_ACCESS_URL);
        return false;
    }


    public CheckAuthResponse validToken(String token) {
        CheckAuthResponse response = new CheckAuthResponse();
        try {
            beforeValidateAuth(token);

            Claims claims = JwtTokenUtil.parseToken(token);
            response.setUid(claims.get("uid").toString());
            response.setCode(ResponseCodeEnum.SUCCESS.getCode());
            response.setMsg(ResponseCodeEnum.SUCCESS.getMsg());

        } catch (ExpiredJwtException e) {
            log.error("ExpiredJwtException :" + e);
            response.setCode(ResponseCodeEnum.TOKEN_EXPIRE.getCode());
            response.setMsg(ResponseCodeEnum.TOKEN_EXPIRE.getMsg());
        } catch (SignatureException e1) {
            log.error("SignatureException :" + e1);
            response.setCode(ResponseCodeEnum.SIGNATURE_ERROR.getCode());
            response.setMsg(ResponseCodeEnum.SIGNATURE_ERROR.getMsg());
        } catch (Exception e) {
            log.error("login occur exception :" + e);
            response.setCode(ResponseCodeEnum.SYSTEM_BUSY.getCode());
            response.setMsg(ResponseCodeEnum.SYSTEM_BUSY.getMsg());
        } finally {
            log.info("response:" + response);
        }

        return response;
    }

    private void beforeValidateAuth(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new ValidateException("token信息为空");
        }
    }

    private boolean response(boolean isAjax, HttpServletResponse response) throws IOException {
        if (isAjax) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("{\"code\":\"-1\",\"msg\":\"error\"}");
            return false;
        }
        response.sendRedirect(CommonConstant.SSO_ACCESS_URL);
        return false;
    }
}
