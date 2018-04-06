/**
 * 
 */
package com.framework.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 任文龙
 *
 */
public class LoginFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

		System.out.println("进入登录过滤器init············");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		System.out.println("进入登录过滤器················");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		 // 不过滤的uri
        String[] notFilter = new String[] { "login.html","checkLogin.do","noLogin.html","error-404.html",".css",".js",".png",".jpg",".ico"};
  
        // 请求的uri
        String uri = httpRequest.getRequestURI();
        
        // uri中包不含app时才进行过滤
        if (uri.indexOf("app") == -1) {
            // 是否过滤
            boolean doFilter = true;
            for (String s : notFilter) {
                if (uri.indexOf(s) != -1) {
                    // 如果uri中包含不过滤的uri，则不进行过滤
                    doFilter = false;
                    break;
                }
            }
            if (doFilter) {
            	System.out.println(uri);
                // 执行过滤
                // 从session中获取登录者实体
                Object obj = httpRequest.getSession().getAttribute("user_login_flag");
                if (null == obj) {
                    // 如果session中不存在登录者实体，则弹出框提示重新登录
                	HttpServletResponse httpResponse = (HttpServletResponse) response;
        			//UserInfo userInfo = (UserInfo)objUser;
        			request.getRequestDispatcher("/url/noLogin.html").forward(httpRequest, httpResponse);
        			return;
                } else {
                    // 如果session中存在登录者实体，则继续
                	chain.doFilter(request, response);
                }
            } else {
                // 如果不执行过滤，则继续
            	chain.doFilter(request, response);
            }
        } else {
            // 如果uri中包含app，则继续
        	chain.doFilter(request, response);
        }
		
	}

	public void destroy() {
		System.out.println("进入登录过滤器销毁············");
	}

}
