package com.tan.filter;

import com.tan.utils.SessionUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@WebFilter(filterName = "Filter")
public class Filter implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();
        if (!SessionUtils.hasAttribute(request,"authed")){
            if (uri.startsWith("/auth")){
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write("<html><body><h1>还未登录，请登录!</h1></body></html>");
                out.close();
            }
        }

        chain.doFilter(req, resp);
    }

}
