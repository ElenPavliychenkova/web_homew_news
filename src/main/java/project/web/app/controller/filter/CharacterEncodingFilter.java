package project.web.app.controller.filter;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;

import java.io.IOException;

public class CharacterEncodingFilter extends HttpFilter implements Filter {

    public CharacterEncodingFilter() {
        super();
    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        System.out.println("FILTEEEEEEEEEEEEEEEEEEEEEEEEEER START");
        chain.doFilter(request, response);
        System.out.println("FILTEEEEEEEEEEEEEEEEEEEEEEEEEER END");
    }

    public void init(FilterConfig fConfig) {

    }

}
