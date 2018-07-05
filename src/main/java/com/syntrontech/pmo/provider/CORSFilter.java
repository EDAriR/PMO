package com.syntrontech.pmo.provider;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		String VALID_METHODS = "GET, POST, PUT, DELETE, OPTIONS";

		if ("OPTIONS".equalsIgnoreCase(httpReq.getMethod())) {
			String origin = httpReq.getHeader("Origin");
			httpResp.setHeader("Access-Control-Allow-Origin", origin);
			httpResp.setHeader("Access-Control-Allow-Methods", VALID_METHODS);

			String allowHeaders = "Content-Type,X-Requested-With,Content-Length,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers, If-Modified-Since, Authorization";
			httpResp.setHeader("Access-Control-Allow-Headers", allowHeaders);
			httpResp.setHeader("Access-Control-Max-Age", "3600");
			httpResp.setStatus(200);
			return;
		} else {
			String origin = httpReq.getHeader("Origin");
			httpResp.setHeader("Access-Control-Allow-Origin", origin);
			httpResp.setHeader("Access-Control-Allow-Methods", VALID_METHODS);

			String allowHeaders = "Content-Type,X-Requested-With,Content-Length,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers, If-Modified-Since, Authorization";
			httpResp.setHeader("Access-Control-Allow-Headers", allowHeaders);
			httpResp.setHeader("Access-Control-Max-Age", "3600");
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
