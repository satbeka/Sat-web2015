package com.epam.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UrlRewriteFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String requestURI = request.getRequestURI();
        System.out.println("urirewr req.getRequestURL().toString()=" + request.getRequestURL().toString());
        //String path = req.getRequestURI().substring(req.getContextPath().length());
        //log.debug("path in resource filter: " + path);
        //String toReplace = requestURI.substring(requestURI.indexOf("/do"), requestURI.lastIndexOf("/") + 1);
        //String newURI = requestURI.replace(toReplace, "/ttt/");
        //System.out.println("urirewr newURI=" + newURI);

        String path = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("path in rewr filter=" + path);
        if (path.startsWith("/do/")) {
            System.out.println("req.getRequestDispatcher= " + path.replace("/do/",""));
            req.getRequestDispatcher(path.replace("/do/","")).forward(req, res);

        } else {
            System.out.println("chain.doFilter(req, res)= ");
            chain.doFilter(req, res);

        }
    }
    @Override
    public void destroy() {

    }
}
