package com.epam.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(SecurityFilter.class);
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
        log.debug("secur sess=" + session);

        String url = httpRequest.getPathInfo();
        log.debug("secur url=" + url);
        //System.out.println("session url="+session.getAttribute("URL"));
        //String role=
        String role = (String) session.getAttribute("ROLE");
        log.debug("role=" + role);
        //String role=(String)request.getAttribute("ROLE");
        //System.out.println("seecur role888="+role);
        //System.out.println("seecur Attr role="+httpRequest.getAttributeNames().hashCode());
        if (!(role == null)) {
            log.debug("url=" + url);
            if (!url.contains(role.toLowerCase()) & (!url.equals("/logout"))) {
                throw new SecurityFilterException("Attention!!! Url is not contains role= " + role);
            }
            /*
            RequestDispatcher dispatcher =request.getRequestDispatcher("/WEB-INF/view/errors/runtime.jsp");
            dispatcher.forward(request, response);return;
            */
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
