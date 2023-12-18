package com.beautysalon.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    // TODO try to turn in it on
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession(false);


        String loginURI = req.getContextPath() + "/view/login";
        String registrationURI = req.getContextPath() + "/view/registration";
        String masterPageURI = req.getContextPath() + "/view/main/master";
        String orderPageURI = req.getContextPath() + "/view/order";


        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean signUpRequest = req.getRequestURI().equals(registrationURI);
        boolean masterPageRequest = req.getRequestURI().equals(masterPageURI);
        boolean masterOrderRequest = req.getRequestURI().equals(orderPageURI);

        if (loggedIn || loginRequest || signUpRequest || masterPageRequest || masterOrderRequest) {
            filterChain.doFilter(req, resp);
        } else if (req.getRequestURI().equals("/view/registration")) {
            req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/view/main").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
