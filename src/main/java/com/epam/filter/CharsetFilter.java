package com.epam.filter;


import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter{
    private String code;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");
        System.out.println("filterConfig code= [" + code + "]");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       if (request.getCharacterEncoding()!=code) {
           request.setCharacterEncoding(code);
       }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
      code=null;
    }
}
