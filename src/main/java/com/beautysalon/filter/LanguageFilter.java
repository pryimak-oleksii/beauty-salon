package com.example.beautysaloneeservlets.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/*")
public class LanguageFilter implements Filter {
    private Cookie cookie;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {


        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getRequestURI();

        if (path.equals("/beauty_salonEE_servlets_war_exploded/")) {
            path = "/view/main";
        } else {
            path = path.substring(path.indexOf("view") - 1);
        }

        String language = req.getParameter("language");

        String langAttribute = (String) req.getSession().getAttribute(language);

        if (langAttribute == null && cookie == null) {
            req.getSession().setAttribute("language", "EN");
            cookie = new Cookie("language", "EN");
        }
        if (cookie != null) {
            req.getSession().setAttribute("language", cookie.getValue());
        }


        if (language != null) {
            boolean isEnglish = language.equals("EN");
            boolean isRussian = language.equals("UA");

            if (isEnglish) {
                req.getSession().setAttribute("language", "EN");
                cookie.setValue("EN");
            } else if (isRussian) {
                req.getSession().setAttribute("language", "UA");
                cookie.setValue("UA");
            }
        }

        req.getRequestDispatcher(path).forward(req, resp);
    }

}