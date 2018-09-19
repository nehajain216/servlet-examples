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

@WebFilter(filterName="ValidationFilter", urlPatterns= {"/*"})
public class ValidationFilter implements Filter {

    public ValidationFilter() {
    }
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse) response;
		System.out.println("filter:" + req.getAttribute("isAuthenticated"));
		
		System.out.println(req.getRequestURL());
		if(!req.getRequestURL().toString().equals("http://localhost:8080/servlet-examples/login"))
		{
			Boolean isAuthenticated = (Boolean) req.getSession().getAttribute("isAuthenticated");
			if(isAuthenticated == null || isAuthenticated==false)
			{
				res.sendRedirect("/servlet-examples/login");
				return;
			}
		}if(req.getRequestURL().toString().equalsIgnoreCase("http://localhost:8080/servlet-examples/login"))
		{
			System.out.println("login URL validation");
			chain.doFilter(req, res);
			return;
		}
		else
		if(req.getAttribute("isAuthenticated") == null)
		{
			res.sendRedirect("/login");
			return;
		}
		else
		if(req.getAttribute("isAuthenticated").equals(true))
		{
			System.out.println("filterrrrrrrrrrr");
			//res.sendRedirect("/home.jsp");
			chain.doFilter(req, res);
		}
		/**/
			
		chain.doFilter(req, res);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
