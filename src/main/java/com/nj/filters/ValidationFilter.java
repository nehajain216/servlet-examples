package com.nj.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="/", urlPatterns= {"/","/home","/login"})
public class ValidationFilter implements Filter {

    public ValidationFilter() {
    }
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse) response;
		System.out.println("filter:" + req.getAttribute("isAuthenticated"));
		
		if(req.getAttribute("isAuthenticated") == null)
		{
			req.getRequestDispatcher("/login").forward(req, response);
			//res.sendRedirect("/login.jsp");
			return;
		}
		else
		if(req.getAttribute("isAuthenticated").equals(true))
		{
			System.out.println("filterrrrrrrrrrr");
			res.sendRedirect("/home.jsp");
		}
			
		chain.doFilter(req, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
