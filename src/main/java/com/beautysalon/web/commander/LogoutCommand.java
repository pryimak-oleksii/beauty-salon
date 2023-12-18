package com.beautysalon.web.commander;



import com.beautysalon.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        req.getSession().setAttribute("language", "EN");

        CommandUtil.goToPage(req,resp,"/view/main");
    }
}
