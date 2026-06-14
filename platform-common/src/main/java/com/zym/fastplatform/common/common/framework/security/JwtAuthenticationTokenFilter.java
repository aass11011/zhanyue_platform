package com.zym.fastplatform.common.common.framework.security;

import com.zym.fastplatform.common.common.framework.config.SecurityIgnoreUrl;
import com.zym.fastplatform.common.common.framework.enums.ErrorCode;
import com.zym.fastplatform.common.common.framework.exception.ZException;
import com.zym.fastplatform.common.common.framework.utils.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    @Resource
    private UserDetailsService userDetailsService;


    @Resource
    private SecurityIgnoreUrl securityIgnoreUrl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws  IOException, ServletException {
        String token = request.getHeader(AUTHORIZATION);
        for (String ignoreUrl : securityIgnoreUrl.getUrls()) {
            if (new AntPathRequestMatcher(ignoreUrl).matches(request)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        if(token == null){
            throw new ZException(ErrorCode.TOKEN_IS_EMPTY);
        }
        if(JwtUtil.verify(token) && JwtUtil.isExpired(token)){
            throw  new ZException(ErrorCode.TOKEN_IS_EXPIRED);
        }
        if(!JwtUtil.verify(token)){
            throw new ZException(ErrorCode.TOKEN_IS_ILLEGAL);
        }
        String username = JwtUtil.getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
