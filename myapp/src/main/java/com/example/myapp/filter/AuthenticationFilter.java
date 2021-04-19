package com.example.myapp.filter;

import com.example.myapp.service.AuthService;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    public static final String AUTHENTICATION_HEADER = "Authorization";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest){
            final HttpServletRequest httpServletRequest = (HttpServletRequest)  request;
            final String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

            final AuthService authService = new AuthService();
            final boolean authenticationStatus = authService.authenticate(authCredentials);

            if(authenticationStatus){
                chain.doFilter(request,response);
            }
            else{
                if(response instanceof HttpServletResponse){
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }

}
