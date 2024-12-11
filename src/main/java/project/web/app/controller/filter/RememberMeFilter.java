//package project.web.app.controller.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import project.web.app.bean.User;
//
//import java.io.IOException;
//
//public class RememberMeFilter extends HttpFilter {
//
//    public RememberMeFilter() {
//        super();
//    }
//
//    @Override
//    public void destroy() {
//        // Optional: код для очистки ресурсов, если нужно
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpSession session = httpRequest.getSession(false);
//
//        if (session == null || session.getAttribute("user") == null) {
//            Cookie[] cookies = httpRequest.getCookies();
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    if (cookie.getName().equals("remember-me")) {
//                        try {
//                            int userId = Integer.parseInt(cookie.getValue());
//
//                            User user = userService.getUserById(userId);
//
//                            if (user != null) {
//                                HttpSession newSession = httpRequest.getSession(true);
//                                newSession.setAttribute("user", user);
//                            }
//
//                        } catch (NumberFormatException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//
//
//        chain.doFilter(request, response);
//    }
//}
