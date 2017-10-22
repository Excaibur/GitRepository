package com.eshop.fiter;

import javax.jms.Session;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CateFilter")
public class CateFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String uri=request.getRequestURI();
        if (uri.equalsIgnoreCase("/Admin/adminLogin.jsp")||uri.equalsIgnoreCase("/Admin/toLogin.htm")||uri.equalsIgnoreCase("/Admin/sorry.jsp")
                ||uri.equalsIgnoreCase("/Admin/isLogin.jsp")||uri.equalsIgnoreCase("/Admin/adminIndex.jsp")){
            chain.doFilter(req, resp);
        }else {
            String name= (String) session.getAttribute("name");
            if (name!=null){
                chain.doFilter(req, resp);
            }else {
                response.sendRedirect("sorry.jsp");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
