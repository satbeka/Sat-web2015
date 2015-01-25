package com.epam.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlRewriteFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(UrlRewriteFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public static String pathWithoutId(String path, HttpServletRequest request) {
        //String path="do/clientaddproduct&444";
        if (path.contains("orderId=")) {
            Pattern regexp = Pattern.compile("=\\d+");
            Matcher m2 = regexp.matcher(path);
            while (m2.find()) {
                String orderId = m2.group().replace("=", "");
                request.getSession().setAttribute("orderId", orderId);
                log.debug("orderId: " + m2.group().replace("=", ""));
            }
            //String[] words = regexp.split(path);
            String rez = path;
            /*
            for (String word : words) {
                rez = word;
                System.out.println("word=" + word);
            }
            System.out.println("rez=" + rez);
            */
            return rez;
        }
        return path;
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String requestURI = request.getRequestURI();
        log.debug("urirewr req.getRequestURL().toString()=" + request.getRequestURL().toString());
        //String path = req.getRequestURI().substring(req.getContextPath().length());
        //log.debug("path in resource filter: " + path);
        //String toReplace = requestURI.substring(requestURI.indexOf("/do"), requestURI.lastIndexOf("/") + 1);
        //String newURI = requestURI.replace(toReplace, "/ttt/");
        //System.out.println("urirewr newURI=" + newURI);

        String path = request.getRequestURI().substring(request.getContextPath().length());
        log.debug("path in rewr filter=" + path);

        //path=UrlRewriteFilter.pathWithoutId(path,request);
        if (path.startsWith("/do/")) {
            log.debug("req.getRequestDispatcher= " + path.replace("/do/", ""));
            req.getRequestDispatcher(path.replace("/do/", "")).forward(req, res);

        } else {
            log.debug("chain.doFilter(req, res)= ");
            chain.doFilter(req, res);

        }
    }

    @Override
    public void destroy() {

    }
}
