package cn.dfusion.demo.token.authorization;

import cn.dfusion.demo.token.entity.TokenModel;
import cn.dfusion.demo.token.util.TokenManager;
import cn.dfusion.demo.config.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不映射到方法就直接返回
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //转换为方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        String authorization = request.getHeader(Constant.AUTHORIZATION);

        TokenModel tokenModel = tokenManager.getToken(authorization);

        if (tokenManager.checkToken(tokenModel)) {
            request.setAttribute(Constant.CURRENT_USER_ID,tokenModel.getUserId());
            return true;
        }
        //如果Token验证失败
        if (method.getAnnotation(Authorization.class) !=null ) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;

    }
}
