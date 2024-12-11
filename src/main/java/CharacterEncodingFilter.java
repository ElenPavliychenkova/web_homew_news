import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;

import javax.servlet.*;
import java.io.IOException;

@WebFilter("/*")
public class CharacterEncodingFilter extends HttpFilter implements Filter {

    public CharacterEncodingFilter() {
        super();
    }

    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        System.out.println("TRATATATTTTAAAAA");
        chain.doFilter(request, response);
        System.out.println("TRATATATTTTAAAAA222222");
    }
}
