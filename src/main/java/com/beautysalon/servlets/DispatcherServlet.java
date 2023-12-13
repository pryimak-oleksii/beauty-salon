package com.example.beautysaloneeservlets.servlets;

import com.example.beautysaloneeservlets.web.commander.Command;
import com.example.beautysaloneeservlets.web.commander.factory.CommandFactory;
import com.example.beautysaloneeservlets.web.commander.utils.CommandUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String path = req.getRequestURI();
        try {
            path = path.substring(path.indexOf("view") - 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Command command;
        try {
            command = CommandFactory.getCommand(path);
            command.execute(req, resp);
        } catch (Exception e) {
            CommandUtil.goToPage(req, resp, "/WEB-INF/view/not_found.jsp");
        }
    }
}
