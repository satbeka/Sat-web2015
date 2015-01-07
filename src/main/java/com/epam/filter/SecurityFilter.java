package com.epam.filter;

import com.epam.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter{
    private ServletContext login;
    //@Override
    public void init(ServletContext servletContext) throws ServletException {
        //this.login = servletContext;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        System.out.println("secur sess="+session);

        String url=httpRequest.getPathInfo();
        System.out.println("secur url="+url);
        //String role=
        String role=(String)session.getAttribute("ROLE");
        //String role=(String)request.getAttribute("ROLE");
        System.out.println("seecur role="+role);
        //System.out.println("seecur Attr role="+httpRequest.getAttributeNames().hashCode());
        if (role==User.Role.ADMINISTRATOR.toString()){
            /*
            RequestDispatcher dispatcher =request.getRequestDispatcher("/WEB-INF/view/errors/error_runtime.jsp");
            dispatcher.forward(request, response);return;
            */
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
